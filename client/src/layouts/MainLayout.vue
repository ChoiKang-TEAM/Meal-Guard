<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated class="bg-black">
      <q-toolbar>
        <q-toolbar-title class="toolbar-title">
          <div class="text-center">Meal Guard</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { useFoodStore } from 'src/stores/food-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  name: 'MainLayout',

  setup() {
    const store = useFoodStore()
    const { getCurrentWeather } = store
    const rotateData = ref()
    onMounted(async () => {
      rotateData.value = await getCurrentWeather()
    })
    return {
      tab: ref<number>(0),
      rotateData,
    }
  },
})
</script>

<style>
/* .toolbar-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
} */
.float-right {
  margin-left: auto;
}
.float-left {
  margin-right: auto;
}
</style>
