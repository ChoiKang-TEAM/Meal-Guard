<script lang="ts">
import { useQuasar } from 'quasar'
import { computed, defineComponent, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import HeaderLayout from 'src/layouts/HeaderLayout.vue'
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'

export default defineComponent({
  components: { HeaderLayout },
  setup() {
    const route = useRoute()
    const store = useRecipeStore()
    const { getRecipeDetailData } = store
    const { recipeDetailData, recipeBackGroundImageUrl } = storeToRefs(store)
    onMounted(() => {
      const recipeId = Number(route.params.id)
      getRecipeDetailData(recipeId)
      console.log(recipeBackGroundImageUrl.value)
    })
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
  <header-layout>
    <template #append><q-tab>dd</q-tab></template>
    <q-card class="text-black" style="opacity: 0.3">
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
  </header-layout>
</template>

<style scoped>
.body {
  background: url('http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032057_1678342857757.jpg');
}
.ra {
  transform: matrix3d();
}
</style>
