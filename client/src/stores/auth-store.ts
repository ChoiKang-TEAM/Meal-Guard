import { defineStore } from 'pinia'
import { useMutation } from 'villus'
import { LOGIN_MEMBER_USER, SIGN_UP_MEMBER_USER } from 'src/graphql/member-user'

export const useAuthStore = defineStore('auth', () => {
  const signUpInputData = new Map()
  signUpInputData.set('privacy-policy', {
    one: true,
    two: true,
  })

  const { execute: loginMemberUser } = useMutation(LOGIN_MEMBER_USER)
  const { execute: signUpMemberUser } = useMutation(SIGN_UP_MEMBER_USER)
  const state = { signUpInputData }
  const action = {
    loginMemberUser,
    signUpMemberUser,
  }

  return { ...state, ...action }
})
