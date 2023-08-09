<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, onMounted, ref } from 'vue'
import { useForm, useField } from 'vee-validate'
import * as yup from 'yup'
import { api } from 'src/boot/axios'
import { PHONENUMBER_REGEX } from 'src/common/regexs'
import { storeToRefs } from 'pinia'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const { signUpInputData } = authStore
    const { formStepNumber } = storeToRefs(authStore)
    const { goBackStep } = authStore
    const phoneNumberAgreeValue = ref<boolean>(false)

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

    const goNextStep = () => {
      console.log(phoneNumber.value)
      signUpInputData.set('phone-number', phoneNumber.value)
      formStepNumber.value += 1
    }

    const state = {
      phoneNumber,
      phoneNumberMeta,
      errorMessage,
      phoneNumberAgreeValue,
    }
    const action = { goNextStep, goBackStep }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section>
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
    >
      <template #error>
        {{ errorMessage }}
      </template>
    </q-input>
  </q-card-section>

  <q-checkbox
    v-model="phoneNumberAgreeValue"
    color="positive"
    checked-icon="task_alt"
    unchecked-icon="highlight_off"
    label="인증 약관 동의"
    class="text-black essential-checkbox"
  />

  <q-card-section>
    <q-btn
      flat
      dense
      :disable="!phoneNumberMeta.valid || !phoneNumberAgreeValue"
      label="인증번호"
      :class="
        phoneNumberMeta.valid && phoneNumberAgreeValue
          ? 'bg-positive'
          : 'bg-grey'
      "
    ></q-btn>
  </q-card-section>

  <q-card-actions>
    <q-btn
      flat
      :disable="!phoneNumberMeta.valid"
      label="다음으로"
      :class="phoneNumberMeta.valid ? 'bg-teal' : 'bg-grey'"
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
