<script lang="ts">
import { useQuasar } from 'quasar'
import { defineComponent, ref } from 'vue'

import HeaderLayout from 'src/layouts/HeaderLayout.vue'
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'

import { NUTRITION_TO_KOREAN } from 'src/common/constants'

export default defineComponent({
  components: { HeaderLayout },
  setup() {
    const recipeStore = useRecipeStore()
    const tab = ref<string>('manual01')
    const {
      recipeDetailData,
      recipeRecipeImageUrl,
      nutritionData,
      manualData,
    } = storeToRefs(recipeStore)

    const state = {
      recipeDetailData,
      recipeRecipeImageUrl,
      nutritionData,
      NUTRITION_TO_KOREAN,
      manualData,
      splitterModel: 20,
      tab,
    }
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
        <q-img class="col-4" :src="recipeRecipeImageUrl">
          <q-badge
            outline
            rounded
            color="white"
            :label="recipeDetailData?.part"
          />
        </q-img>

        <q-card-section>
          <div class="text-h5">
            {{ recipeDetailData?.name }}
            <q-badge
              v-if="recipeDetailData?.sodiumTip"
              outline
              rounded
              color="red"
              label="저염식"
            />
          </div>
          <q-separator />
          <div class="text-h6">
            영양 성분 정보 <span class="age-info">20대 남자 기준</span>
          </div>

          <q-circular-progress
            v-for="([key, data], index) in nutritionData"
            :key="index"
            show-value
            reverse
            :value="data"
            size="50px"
            :thickness="0.3"
            color="light-blue"
            track-color="grey-3"
            class="q-ma-md text-black"
          >
            {{ NUTRITION_TO_KOREAN[key] }}</q-circular-progress
          >
          <div>{{ recipeDetailData?.information }}</div>
        </q-card-section>
      </q-card-section>

      <q-separator />
    </q-card>
    <q-card class="text-black bg-opacity q-pa-ma">
      <q-card-section class="recipe-info"> </q-card-section>
      <q-card-section>
        <q-splitter v-model="splitterModel">
          <template v-slot:before>
            <q-tabs
              v-model="tab"
              vertical
              active-color="red-7"
              class="text-grey-9 shadow-2"
            >
              <q-tab
                v-for="([key], index) in manualData"
                :key="index"
                :name="key"
                :label="`STEP${index + 1}`"
              />
            </q-tabs>
          </template>

          <template v-slot:after>
            <q-tab-panels
              v-model="tab"
              animated
              swipeable
              vertical
              class="bg-opacity-100"
              transition-prev="jump-up"
              transition-next="jump-up"
            >
              <q-tab-panel
                v-for="([key, data], index) in manualData"
                :key="index"
                :name="key"
              >
                <div class="text-h4 q-mb-md">조리 방법</div>
                <div>{{ data?.manual }}</div>
                <q-img
                  :src="data?.manualImg"
                  class="q-mb-md"
                  style="height: 250px; max-width: 250px"
                />
              </q-tab-panel>
            </q-tab-panels>
          </template>
        </q-splitter>
      </q-card-section>
    </q-card>
  </header-layout>
</template>
