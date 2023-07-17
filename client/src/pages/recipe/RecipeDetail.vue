<script lang="ts">
import { useQuasar } from 'quasar'
import { computed, defineComponent, onMounted, reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
import { useFoodStore } from 'src/stores/food-store'
import { storeToRefs } from 'pinia'

export default defineComponent({
  setup() {
    const route = useRoute()
    const store = useFoodStore()
    const { getRecipeDetailData } = store
    const { recipeDetailData } = storeToRefs(store)
    // onMounted(() => {
    //   const recipeId = Number(route.params.id)
    //   getRecipeDetailData(recipeId)
    // })
    const count = ref(0)
    const color = ref('red')
    const test = () => {
      const COLOR = ['red', 'white', 'black', 'green']
      count.value += 10
      color.value = COLOR[Math.floor(Math.random() * 3)]
    }
    const filter = computed(
      () =>
        `transform: rotateX(${count.value}deg) translateZ(${count.value}px); background-color: ${color.value}`
    )
    console.log(route.params.id)
    const state = { recipeDetailData, count, filter }
    const action = { test }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-layout
    style="
      background-image: url(http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032057_1678342857757.jpg);
    "
  >
    <q-card class="text-black">
      {{ count }}
      {{ filter }}
      <q-card-section class="recipe-info">
        <q-btn @click="test()" :style="filter" />
        <!-- <q-img :src="recipeDetailData?.mainImage" /> -->
      </q-card-section>
      <q-card-section class="text-black">
        <div @focus="test">
          {{ recipeDetailData }}
        </div>
      </q-card-section>
    </q-card>
  </q-layout>
</template>

<style>
.ra {
  transform: matrix3d();
}
</style>
