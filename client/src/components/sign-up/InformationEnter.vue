<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'
import { useForm, useField } from 'vee-validate'
import * as yup from 'yup'
import { EMAIL_REGEX, PASSWORD_REGEX } from 'src/common/regexs'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const { goBackStep } = authStore

    const isPwd = ref<boolean>(true)

    const validationSchema = yup.object({
      userId: yup
        .string()
        .defined()
        .matches(EMAIL_REGEX, '이메일 형식이 아닙니다.')
        .required('아이디 값은 필수 값입니다.'),
      name: yup.string(),
      password: yup
        .string()
        .defined()
        .matches(PASSWORD_REGEX, '10~18자의 영문 숫자 포함을 권장합니다.')
        .min(10, '10~18자의 영문, 숫자 포함을 권장합니다.')
        .max(18, '10~18자의 영문, 숫자 포함을 권장합니다.')
        .required('비밀번호는 필수 정보입니다.'),
      passwordConfirm: yup
        .string()
        .defined()
        .oneOf(
          [yup.ref<string>('password'), ''],
          '비밀번호가 일치하지 않습니다.'
        ),
    })

    const { errors, meta } = useForm({
      validationSchema,
    })

    const { value: userId } = useField<string>('userId', {
      validateOnInput: true,
    })
    const { value: password } = useField<string>('password', {
      validateOnInput: true,
    })
    const { value: passwordConfirm } = useField<string>('passwordConfirm', {
      validateOnInput: true,
    })
    const { value: name } = useField<string>('name', {
      validateOnInput: true,
    })

    const goNextStep = (): void => {
      console.log(1)
    }

    const state = {
      isPwd,
      errors,
      meta,
      userId,
      name,
      password,
      passwordConfirm,
    }
    const action = { goNextStep }
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
      v-model="userId"
      placeholder="이메일 주소를 입력해주세요."
      type="text"
      no-error-icon
      :error="!!errors.userId"
      label="아이디"
      class="essential-input"
    >
      <template #error>
        {{ errors.userId }}
      </template>
    </q-input>
    <q-input
      stack-label
      clear-icon="close"
      clearable
      color="positive"
      v-model="password"
      :type="isPwd ? 'password' : 'text'"
      no-error-icon
      :error="!!errors.password"
      placeholder="비밀번호를 입력해주세요."
      autocomplete="off"
      label="비밀번호"
      class="essential-input"
    >
      <template v-slot:append
        ><q-icon
          :name="isPwd ? 'visibility_off' : 'visibility'"
          class="cursor-pointer"
          @click="isPwd = !isPwd"
        />
      </template>
      <template #error>
        {{ errors.password }}
      </template>
    </q-input>
    <q-input
      stack-label
      clear-icon="close"
      clearable
      color="positive"
      v-model="passwordConfirm"
      type="password"
      no-error-icon
      :error="!!errors.passwordConfirm"
      placeholder="비밀번호를 한번 더 입력해주세요."
      autocomplete="off"
      label="비밀번호"
      class="essential-input"
    >
      <template #error>
        {{ errors.password }}
      </template>
    </q-input>
  </q-card-section>

  <q-card-actions>
    <q-btn
      flat
      label="다음으로"
      :class="meta.valid ? 'bg-teal' : 'bg-grey'"
      @click="goNextStep"
    />
  </q-card-actions>
</template>
