<script lang="ts">
import { useGoogleImgSotre } from 'src/stores/google-img-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const url = ref()
    const store = useGoogleImgSotre()
    const keyword = ref<string>('')
    const { searchUrl } = store
    // onMounted(async () => {
    //   url.value = await searchUrl()
    // })

    const change = async () => {
      url.value = await searchUrl(keyword.value)
    }

    return { url, keyword, change }
  },
})
</script>

<template>
  <q-page class="row items-center justify-evenly">
    <q-input type="text" v-model="keyword" />
    <q-btn flat @click="change" label="검색" />
    <q-img
      :src="url"
      spinner-color="white"
      style="height: 140px; max-width: 150px"
    />
  </q-page>
</template>
