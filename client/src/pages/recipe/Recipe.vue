<script lang="ts">
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'
import { defineComponent, onBeforeUnmount, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const isBlur = ref<boolean>(false)
    const store = useRecipeStore()
    const { recipeBackGroundImageUrl } = storeToRefs(store)
    const backgroundStyle = ref<string>('')

    const handleScroll = () => {
      isBlur.value = window.scrollY > 600
    }

    onMounted(() => {
      backgroundStyle.value = `height: 2000px; background: url(${recipeBackGroundImageUrl.value}); background-size: cover;  background-attachment: fixed;`
      window.addEventListener('scroll', handleScroll)
      handleScroll()
    })

    onBeforeUnmount(() => {
      window.removeEventListener('scroll', handleScroll)
    })

    const state = { isBlur, backgroundStyle }
    return state
  },
})
</script>

<template>
  <q-layout :style="backgroundStyle" view="hHh lpr fff">
    <div :class="{ 'bg-blur': isBlur }"></div>
    <router-view />
  </q-layout>
</template>

<style scoped lang="scss">
.bg-blur {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(8px); /* 블러 효과 적용 */
  -webkit-backdrop-filter: blur(8px); /* Safari 용 */
}
</style>
