<script lang="ts">
import { useAuthStore } from 'src/stores/auth-store'
import { computed, defineComponent, ref } from 'vue'
import { useField } from 'vee-validate'
import * as yup from 'yup'
import { PHONENUMBER_REGEX } from 'src/common/regexs'
import { storeToRefs } from 'pinia'
import { noticeDialog } from 'src/common/utils'

export default defineComponent({
  setup() {
    const authStore = useAuthStore()
    const { signUpInputData } = authStore
    const { formStepNumber } = storeToRefs(authStore)
    const { goBackStep } = authStore
    const phoneNumberAgreeValue = ref<boolean>(false)
    const confirmRequest = ref<boolean>(false)
    const verifyConfirm = ref<string>('')
    const verifyConfirmValid = ref<boolean>(false)
    const timer = ref(3)
    const verify = '111111'

    let nIntervId: ReturnType<typeof setInterval>

    const verifyConfirmTimer = computed(() => {
      if (timer.value > 0) return formatTimer(timer.value)
      else return stopTimer()
    })

    const {
      value: phoneNumber,
      meta: phoneNumberMeta,
      errorMessage,
    } = useField<string>(
      'phoneNumber',
      yup
        .string()
        .defined()
        .matches(PHONENUMBER_REGEX, '잘못된 형식입니다.')
        .required('휴대폰 번호는 필수 정보입니다.')
    )

    const goNextStep = (): void => {
      signUpInputData.set('phone-number', phoneNumber.value)
      formStepNumber.value += 1
    }

    const getConfirmNumber = (): void => {
      confirmRequest.value = true
      timer.value = 3
      nIntervId = setInterval(startTimer, 1000)
    }

    const startTimer = (): void => {
      timer.value--
    }

    const resetTimer = (): void => {
      getConfirmNumber()
    }

    const stopTimer = (): string => {
      clearInterval(nIntervId)
      return '응답 시간이 경과되었습니다.'
    }

    const formatTimer = (timer: number): string => {
      const minutes = Math.floor(timer / 60)
      const seconds = timer % 60

      return `0${minutes} : ${seconds < 10 ? '0' : ''}${seconds}`
    }

    const confirmVerify = () => {
      if (timer.value <= 0) return noticeDialog('인증번호를 재발급 받아주세요.')
      if (verifyConfirm.value !== verify)
        return noticeDialog('인증번호를 확인해주세요.')
      if (verifyConfirm.value === verify) {
        verifyConfirmValid.value = true
        confirmRequest.value = false
        stopTimer()
        return noticeDialog('인증이 완료되었습니다.')
      }
    }

    const state = {
      phoneNumber,
      phoneNumberMeta,
      errorMessage,
      phoneNumberAgreeValue,
      confirmRequest,
      verifyConfirm,
      verifyConfirmTimer,
      verifyConfirmValid,
    }
    const action = {
      getConfirmNumber,
      goNextStep,
      goBackStep,
      resetTimer,
      confirmVerify,
    }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section class="flex-container">
    <q-input
      stack-label
      clear-icon="close"
      clearable
      color="positive"
      v-model="phoneNumber"
      placeholder="휴대폰 번호를 입력해주세요 (-제외)"
      type="text"
      no-error-icon
      :error="!!errorMessage"
      label="본인 인증"
      :disable="confirmRequest"
      class="w-80"
    >
      <template #error>
        {{ errorMessage }}
      </template>
    </q-input>
    <q-btn
      v-if="!confirmRequest"
      flat
      class="w-20 ml-05"
      :disable="!phoneNumberMeta.valid || !phoneNumberAgreeValue"
      label="인증번호 받기"
      :class="
        phoneNumberMeta.valid && phoneNumberAgreeValue
          ? 'bg-positive'
          : 'bg-grey'
      "
      @click="getConfirmNumber"
    />
    <q-btn
      v-else
      flat
      label="인증번호 재전송"
      class="bg-positive w-20 ml-05"
      @click="resetTimer"
    />
  </q-card-section>

  <q-checkbox
    v-model="phoneNumberAgreeValue"
    color="positive"
    checked-icon="task_alt"
    unchecked-icon="highlight_off"
    label="인증 약관 동의"
    class="text-black essential-checkbox"
    :disable="confirmRequest"
  />

  <q-card-section v-if="confirmRequest" class="text-red">
    <q-input
      stack-label
      clear-icon="close"
      clearable
      color="positive"
      v-model="verifyConfirm"
      placeholder="인증번호를 입력해주세요"
      type="text"
      mask="######"
      label="인증 번호"
    >
      <template v-slot:append>
        <q-btn
          flat
          label="확인"
          class="bg-positive text-white"
          @click="confirmVerify"
        />
      </template> </q-input
    >{{ verifyConfirmTimer }}
  </q-card-section>

  <q-card-actions>
    <q-btn
      flat
      :disable="!verifyConfirmValid"
      label="다음으로"
      :class="verifyConfirmValid ? 'bg-teal' : 'bg-grey'"
      @click="goNextStep"
    />
    <q-btn
      flat
      @click="goBackStep"
      color="primary"
      label="뒤로 가기"
      class="q-ml-sm"
    />
  </q-card-actions>
</template>
