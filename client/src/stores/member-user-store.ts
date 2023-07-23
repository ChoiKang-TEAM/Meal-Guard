import { defineStore } from 'pinia'
import { useQuery } from 'villus'
// import { FIND_ALL_MEMBER_USER } from 'src/graphql/member-user'
import { ref } from 'vue'

export const useMemberUserStore = defineStore(
  'member-user',
  () => {
    const token = ref<string | null>(null)

    // const findAllMemberUser = async () => {
    //   const { data } = await useQuery({
    //     query: FIND_ALL_MEMBER_USER,
    //     cachePolicy: 'network-only',
    //   })
    //   return data?.value?.findAllMemberUser
    // }

    const setToken = (loginToken: string) => {
      token.value = loginToken
    }

    const resetToken = () => {
      token.value = null
    }

    const state = { token }
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
