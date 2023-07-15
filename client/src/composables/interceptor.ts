import { useMemberUserStore } from 'src/stores/member-user-store'
import { storeToRefs } from 'pinia'
import { ClientPlugin, fetch, cache, dedup } from 'villus'

export const useInterceptor = () => {
  const memberUserStore = useMemberUserStore()
  const { token }: any = storeToRefs(memberUserStore)

  const TIMEOUT = 60000
  const { fetch: originalFetch } = window
  const newfetch = async (...args: any) => {
    console.log(args)
    const [resource, config] = args
    if (token.value) {
      config.headers = {
        ...config.headers,
        Authorization: `Bearer ${token.value}`,
      }
    }

    const id = setTimeout(() => controller.abort(), TIMEOUT)
    const controller = new AbortController()

    try {
      const response = await originalFetch(resource, {
        ...config,
        signal: controller.signal,
      })
      return response
    } catch (e) {
      console.log(e)
      throw e
    } finally {
      clearTimeout(id)
    }
  }
  const cachePlugin: any = cache()
  const defaultVillusPlugin = (): ClientPlugin[] => {
    return [cachePlugin, dedup(), fetch({ fetch: newfetch })]
  }

  return { defaultVillusPlugin }
}
