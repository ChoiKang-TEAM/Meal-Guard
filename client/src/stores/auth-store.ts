import { defineStore } from 'pinia'
import { useMutation } from 'villus'
import { LOGIN_MEMBER_USER, SIGN_UP_MEMBER_USER } from 'src/graphql/member-user'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const signUpInputData = new Map()
  signUpInputData.set('privacy-policy', [])
  const formSteps = ref<{ [key: number]: boolean }>({
    1: false,
    2: false,
    3: false,
    4: true,
  })

  const { execute: loginMemberUser } = useMutation(LOGIN_MEMBER_USER)
  const { execute: signUpMemberUser } = useMutation(SIGN_UP_MEMBER_USER)
  const state = { signUpInputData, formSteps }
  const action = {
    loginMemberUser,
    signUpMemberUser,
  }

  return { ...state, ...action }
})
