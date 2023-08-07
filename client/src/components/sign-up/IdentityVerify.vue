<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'
import { useForm, useField } from 'vee-validate'
import * as yup from 'yup'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const { goBackStep } = authStore
    const validationSchema = yup.object({
      userId: yup.string().defined().required('아이디 값은 필수 값입니다.'),
    })

    const { value: userId } = useField<string>('userId', {
      validateOnInput: true,
    })
    const { value: password } = useField<string>('password', {
      validateOnInput: true,
    })

    const isPwd = ref<boolean>(true)

    const state = {
      isPwd,
    }
    const action = { goBackStep }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section>
    <q-input rounded outlined v-model="userId" label="아이디" type="text" />
  </q-card-section>
  <q-card-section>
    <q-input
      rounded
      outlined
      v-model="password"
      label="비밀번호"
      :type="isPwd ? 'password' : 'text'"
      placeholder="비밀번호를 입력해주세요."
      autocomplete="off"
    >
      <template v-slot:append
        ><q-icon
          :name="isPwd ? 'visibility_off' : 'visibility'"
          class="cursor-pointer"
          @click="isPwd = !isPwd"
        />
      </template>
    </q-input>
  </q-card-section>

  <q-card-actions>
    <q-btn flat label="다음으로"></q-btn>
    <q-btn
      flat
      @click="goBackStep"
      color="primary"
      label="뒤로 가기"
      class="q-ml-sm"
    />
  </q-card-actions>
</template>
