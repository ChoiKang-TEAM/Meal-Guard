import { defineStore } from 'pinia'
import { useQuery } from 'villus'
import jwt_decode from 'jwt-decode'
// import { FIND_ALL_MEMBER_USER } from 'src/graphql/member-user'
import { computed, ref } from 'vue'

export const useMemberUserStore = defineStore(
  'member-user',
  () => {
    const token = ref<string | null>(null)

    const userData: any = computed(() => decodeToken(token.value))

    // const findAllMemberUser = async () => {
    //   const { data } = await useQuery({
    //     query: FIND_ALL_MEMBER_USER,
    //     cachePolicy: 'network-only',
    //   })
    //   return data?.value?.findAllMemberUser
    // }

    const decodeToken = (token: string | null) => {
      if (token) {
        try {
          return jwt_decode(token)
        } catch (e) {
          console.error('Token decoding failed', e)
          return null
        }
      } else return null
    }

    const setToken = (loginToken: string) => {
      token.value = loginToken
    }

    const resetToken = () => {
      token.value = null
    }

    const state = { token, userData }
    const action = {
      setToken,
      resetToken,
    }

    return { ...state, ...action }
  },
  {
    persist: { key: 'user', paths: ['token'] },
  }
)
