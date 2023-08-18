<script lang="ts">
import { storeToRefs } from 'pinia'
import { CategoryFindModel } from 'src/common/models'
import { FindFilterFoodInput } from 'src/graphql/dto/food-input'
import { useAuthStore } from 'src/stores/auth-store'
import { useCategoryStore } from 'src/stores/category-store'
import { useFoodStore } from 'src/stores/food-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const authStore = useAuthStore()
    const categoryStore = useCategoryStore()
    const foodStore = useFoodStore()
    const { findAllCategory } = categoryStore
    const { findFoodByFilter } = foodStore
    const { categoryTabData } = storeToRefs(categoryStore)
    const tab = ref<string>('')
    const foodData = ref<{ id: number; name: string }[]>([])
    const preferredFoodList = ref<number[]>([])

    onMounted(async () => {
      await findAllCategory()
      tab.value = categoryTabData.value[0].type
      await changeFoodData()
    })

    const goNextStep = () => {
      console.log(1)
    }

    const changeFoodData = async (): Promise<void> => {
      const categoryId: number = findCategoryIdByCategoryName()
      foodData.value = await findFoodByFilter({ categoryId })
    }

    const findCategoryIdByCategoryName = (): number => {
      const foundData: CategoryFindModel | undefined =
        categoryTabData.value.find(
          (val: CategoryFindModel) => val.type === tab.value
        )

      return foundData?.id ?? 0
    }

    const test = (event: any) => {
      console.log(preferredFoodList.value)
    }

    const state = { categoryTabData, tab, foodData, preferredFoodList }
    const action = { goNextStep, changeFoodData, test }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card-section class="text-black">
    <div>선호 음식 조사 [선택]</div>
    <div class="essential-div caption-div">
      선호 음식은 맞춤 음식 제공 서비스에 이용됩니다.
    </div></q-card-section
  >
  <q-card-section class="text-black">
    <q-tabs
      v-model="tab"
      dense
      class="text-grey"
      active-color="positive"
      indicator-color="positive"
      align="justify"
      narrow-indicator
      @update:model-value="changeFoodData"
    >
      <q-tab
        v-for="(data, index) in categoryTabData"
        :key="index"
        :name="data.type"
        :label="data.type"
        style="border-radius: 40px"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels v-model="tab" animated>
      <q-tab-panel
        v-for="(data, index) in categoryTabData"
        :key="index"
        :name="data.type"
      >
        <q-item-label header>{{ data.type }}</q-item-label>

        <q-item v-for="item in foodData" :key="item.id" tag="label" v-ripple>
          <q-item-section side top>
            <q-checkbox
              v-model="preferredFoodList"
              @click="test"
              :val="item.id"
            />
          </q-item-section>
          <q-item-section>
            <q-item-label>{{ item.name }}</q-item-label>
          </q-item-section>
        </q-item>
      </q-tab-panel>
    </q-tab-panels></q-card-section
  >
  <q-card-actions>
    <q-btn flat label="건너뛰기" class="bg-grey" @click="goNextStep" />
    <q-space />
    <q-btn flat label="다음으로" class="bg-grey" @click="goNextStep" />
  </q-card-actions>
</template>
