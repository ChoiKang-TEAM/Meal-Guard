import { defineStore } from 'pinia'
import { useMutation } from 'villus'
import { LOGIN_MEMBER_USER, SIGN_UP_MEMBER_USER } from 'src/graphql/member-user'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const signUpInputData = new Map()
  signUpInputData.set('privacy-policy', [])
  const formSteps = ref<{ [key: string]: boolean }>({
    'step-1': false,
    'step-2': false,
    'step-3': false,
    'step-4': true,
  })

  const formStepNumber = ref<number>(1)

  const goBackStep = () => {
    formStepNumber.value -= 1
  }

  const { execute: loginMemberUser } = useMutation(LOGIN_MEMBER_USER)
  const { execute: signUpMemberUser } = useMutation(SIGN_UP_MEMBER_USER)
  const state = { signUpInputData, formSteps, formStepNumber }
  const action = {
    loginMemberUser,
    signUpMemberUser,
    goBackStep,
  }

  return { ...state, ...action }
})
