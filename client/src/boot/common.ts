import { boot } from 'quasar/wrappers'
import { useInterceptor } from 'src/composables/interceptor'
import { useClient } from 'villus'

export default boot(({ app }) => {
  const { defaultVillusPlugin } = useInterceptor()
  const client = useClient({
    url: '/api/graphql',
    use: defaultVillusPlugin(),
  })

  app.use(client)
})
