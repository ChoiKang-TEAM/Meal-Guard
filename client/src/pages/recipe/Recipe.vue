<script lang="ts">
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'
import { defineComponent, onBeforeUnmount, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const isTransparent = ref(false)
    const store = useRecipeStore()
    const { recipeBackGroundImageUrl } = storeToRefs(store)

    const handleScroll = () => {
      isTransparent.value = window.scrollY > 600
    }

    onMounted(() => {
      window.addEventListener('scroll', handleScroll)
      handleScroll()
    })

    onBeforeUnmount(() => {
      window.removeEventListener('scroll', handleScroll)
    })

    const state = { isTransparent, recipeBackGroundImageUrl }
    return state
  },

  computed: {
    backgroundStyle() {
      const opacity = this.isTransparent ? 0.5 : 1
      return `height: 2000px; background: url(${this.recipeBackGroundImageUrl}); background-size: cover; opacity: ${opacity}; background-attachment: fixed;`
    },
  },
})
</script>

<template>
  <q-layout :style="backgroundStyle" view="hHh lpr fff">
    <router-view />
  </q-layout>
</template>
