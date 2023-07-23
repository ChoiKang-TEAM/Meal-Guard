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
    const value = ref<number>(70)
    onMounted(() => {
      const recipeId = Number(route.params.id)
      getRecipeDetailData(recipeId)
      console.log(recipeBackGroundImageUrl.value)
    })

    const state = { recipeDetailData, recipeBackGroundImageUrl, value }
    const action = {}
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <header-layout>
    <template #expand>
      <q-toolbar-title class="toolbar-title">
        <div class="text-center">{{ recipeDetailData?.name }}</div>
      </q-toolbar-title></template
    >

    <q-card class="text-black bg-opacity">
      <q-card-section horizontal>
        <q-img class="col-4" :src="recipeBackGroundImageUrl">
          <q-badge
            outline
            rounded
            color="white"
            :label="recipeDetailData?.part"
        /></q-img>

        <q-card-section>
          <div class="text-h5">{{ recipeDetailData?.name }}</div>
          <q-separator />
          <div class="text-h6">
            영양 성분 정보 <span class="age-info"></span>
          </div>
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
          <q-circular-progress
            show-value
            reverse
            :value="value"
            size="50px"
            :thickness="0.22"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
            >소금</q-circular-progress
          >
        </q-card-section>
      </q-card-section>

      <q-separator />

      <q-card-actions>
        <q-btn flat round icon="event" />
        <q-btn flat> 5:30PM </q-btn>
        <q-btn flat> 7:00PM </q-btn>
        <q-btn flat color="primary"> Reserve </q-btn>
      </q-card-actions>

      <q-card-section class="recipe-info"> </q-card-section>
      <q-card-section>
        {{ recipeDetailData }}
      </q-card-section>
    </q-card>
  </header-layout>
</template>
