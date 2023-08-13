import { defineStore } from 'pinia'
import jwt_decode from 'jwt-decode'
import { computed, ref } from 'vue'

export const useMemberUserStore = defineStore(
  'member-user',
  () => {
    const token = ref<string | null>(null)

    const userData: any = computed(() => decodeToken(token.value))

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
