<script lang="ts">
import { useQuasar } from 'quasar'
import { SignUpMemberUserInput } from 'src/common/models'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'
import { Form as ValidationForm, useForm, useField } from 'vee-validate'
import * as yup from 'yup'

export default defineComponent({
  setup() {
    const $q = useQuasar()
    const authStore = useAuthStore()
    const { signUpMemberUser } = authStore
    const userId = ref<string>('')
    const password = ref<string>('')
    const userList = ref<any>()
    const isPwd = ref<boolean>(true)

    const memberSingUp = async () => {
      try {
        const signUpMemberUserInput: SignUpMemberUserInput = {
          userId: userId.value,
          password: password.value,
        }
        const { error } = await signUpMemberUser({
          signUpMemberUserInput: signUpMemberUserInput,
        })
        if (error) {
        } else return $q
      } catch (e) {
        console.error(e)
      }
    }

    const state = {
      userList,
      userId,
      password,
      isPwd,
    }
    const action = { memberSingUp }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section> 개인 정보 방침 </q-card-section>
</template>
