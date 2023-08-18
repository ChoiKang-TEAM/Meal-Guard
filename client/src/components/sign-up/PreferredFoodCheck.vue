<script lang="ts">
import { storeToRefs } from 'pinia'
import { CategoryFindModel } from 'src/common/models'
import { noticeDialog } from 'src/common/utils'
import { FindFilterFoodInput } from 'src/graphql/dto/food-input'
import { useAuthStore } from 'src/stores/auth-store'
import { useCategoryStore } from 'src/stores/category-store'
import { useFoodStore } from 'src/stores/food-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const categoryStore = useCategoryStore()
    const { findAllCategory } = categoryStore
    const { categoryData } = storeToRefs(categoryStore)
    const preferredFoodList = ref<number[]>([])

    onMounted(async () => {
      await findAllCategory()
    })

    const goNextStep = () => {
      console.log(1)
    }

    const checkByValidCheckBox = (event: number[]) => {
      if (event.length > 3) {
        event.pop()
        return noticeDialog('선호 음식은 3가지만 선택이 가능합니다.')
      }
    }

    const state = { categoryData, preferredFoodList }
    const action = { goNextStep, checkByValidCheckBox }
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
    <q-item-label header
      >선호 음식 종류
      <div class="caption-div">최대 3종류 선택</div></q-item-label
    >

    <q-item v-for="item in categoryData" :key="item.id" tag="label" v-ripple>
      <q-item-section side top>
        <q-checkbox
          v-model="preferredFoodList"
          :val="item.id"
          color="positive"
          @update:model-value="checkByValidCheckBox"
        />
      </q-item-section>
      <q-item-section>
        <q-item-label>{{ item.type }}</q-item-label>
      </q-item-section>
    </q-item>
  </q-card-section>
  <q-card-actions>
    <q-btn flat label="건너뛰기" class="bg-grey" @click="goNextStep" />
    <q-space />
    <q-btn flat label="다음으로" class="bg-grey" @click="goNextStep" />
  </q-card-actions>
</template>
