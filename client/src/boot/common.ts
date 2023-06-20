import { boot } from 'quasar/wrappers'
import { useClient } from 'villus'

export default boot(({ app }) => {
  const client = useClient({
    url: '/api/graphql',
  })
  // const { defaultVillusPlugin } = useInterceptor()
  // const client = useClient({
  //   url: '/api/graphql',
  //   use: defaultVillusPlugin(),
  // })
  // console.log(client)
  app.use(client)
})
