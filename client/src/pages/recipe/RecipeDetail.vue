<script lang="ts">
import { defineComponent, ref } from 'vue'
import HeaderLayout from 'src/layouts/HeaderLayout.vue'
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'
import { NUTRITION_TO_KOREAN } from 'src/common/constants'
import { useFoodStore } from 'src/stores/food-store'
import RecipeBlogCard from 'src/components/recipe/RecipeBlogCard.vue'

export default defineComponent({
  components: { HeaderLayout, RecipeBlogCard },
  setup() {
    const recipeStore = useRecipeStore()
    const foodStore = useFoodStore()
    const tab = ref<string>('manual01')
    const slide = ref<number>(1)
    const {
      recipeDetailData,
      recipeRecipeImageUrl,
      nutritionData,
      manualData,
    } = storeToRefs(recipeStore)
    const { recipeBlogData } = storeToRefs(foodStore)

    const state = {
      recipeDetailData,
      recipeRecipeImageUrl,
      nutritionData,
      NUTRITION_TO_KOREAN,
      manualData,
      splitterModel: 20,
      tab,
      recipeBlogData,
      slide,
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
    <div class="recipe-card-container">
      <q-card class="text-black bg-opacity recipe-info-card">
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
    </div>
    <div class="recipe-card-container">
      <q-card class="text-black bg-opacity q-pa-ma recipe-menual-card">
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
    </div>

    <div class="recipe-card-container">
      <q-card class="text-black bg-opacity-100 q-pa-ma recipe-menual-card">
        <q-card-section> <div class="text-h2">추천 블로그</div></q-card-section>
        <q-card-section>
          <q-carousel
            v-model="slide"
            transition-prev="slide-right"
            transition-next="slide-left"
            swipeable
            animated
            control-color="black"
            navigation
            arrows
            height="750px"
            class="bg-grey-2 shadow-2 rounded-borders"
          >
            <q-carousel-slide
              v-for="(groupData, index) in recipeBlogData"
              :key="index"
              :name="index + 1"
              class="column no-wrap"
            >
              <div class="row best-blog">
                <recipe-blog-card
                  v-for="data in groupData.data"
                  :key="data.title"
                  :blog-data="data"
                  >{{ data }}</recipe-blog-card
                >
              </div>
            </q-carousel-slide>
          </q-carousel>
        </q-card-section>
      </q-card>
    </div>
    <template #footer>
      <div class="text-h6">(주) 싸홈 강대현(우리은행 1002-050-904892)</div>
    </template>
  </header-layout>
</template>
