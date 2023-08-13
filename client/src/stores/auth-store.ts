import { defineStore } from 'pinia'
import { useMutation, useQuery } from 'villus'
import {
  LOGIN_MEMBER_USER,
  SIGN_UP_MEMBER_USER,
  VALID_BY_IN_USE_CASE_FROM_USER_ID,
} from 'src/graphql/member-user'
import { ref } from 'vue'
import { FindMemberUserByUserId } from 'src/graphql/dto/member-user-input'

export const useAuthStore = defineStore('auth', () => {
  const signUpInputData = new Map()

  const formSteps = ref<{ [key: string]: boolean }>({
    'step-1': false,
    'step-2': false,
    'step-3': false,
    'step-4': true,
  })

  const formStepNumber = ref<number>(1)

  const checkedInUseCaseFromUserId = async (
    dto: FindMemberUserByUserId
  ): Promise<boolean> => {
    const { data } = await useQuery({
      query: VALID_BY_IN_USE_CASE_FROM_USER_ID,
      variables: {
        findMemberUserByUserId: dto,
      },
      cachePolicy: 'network-only',
    })
    console.log(data.value)

    if (data?.value?.validByInUseCaseFromUserId) return false
    else return true
  }

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
    checkedInUseCaseFromUserId,
  }

  return { ...state, ...action }
})
