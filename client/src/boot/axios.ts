import { boot } from 'quasar/wrappers'
import axios, { AxiosInstance } from 'axios'
import { useMemberUserStore } from 'src/stores/member-user-store'
import { storeToRefs } from 'pinia'

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance
  }
}

const setAuthToken = (token: string) => {
  api.defaults.headers.common['Authorization'] = `Bearer ${token}`
}
// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({
  baseURL: 'https://d26f-124-111-225-247.ngrok-free.app/',
})

export default boot(({ app }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api
  const memberUserStore = useMemberUserStore()
  const { token }: any = storeToRefs(memberUserStore)
  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API

  console.log(token)
  if (token.value) {
    setAuthToken(token.value)
  }
})

export { api }
