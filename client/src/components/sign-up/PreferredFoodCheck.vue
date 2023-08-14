<script lang="ts">
import { storeToRefs } from 'pinia'
import { useAuthStore } from 'src/stores/auth-store'
import { useCategoryStore } from 'src/stores/category-store'
import { defineComponent, onMounted, ref } from 'vue'

export default defineComponent({
  setup() {
    const authStore = useAuthStore()
    const categoryStore = useCategoryStore()
    const { findAllCategory } = categoryStore
    const { categoryTab } = storeToRefs(categoryStore)
    const tab = ref<string>('')

    onMounted(async () => {
      await findAllCategory()
      tab.value = categoryTab.value[0] ?? ''
    })
    const goNextStep = () => {
      console.log(1)
    }

    const state = { categoryTab, tab }
    const action = { goNextStep }
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
    >
      <q-tab
        v-for="(data, index) in categoryTab"
        :key="index"
        :name="data"
        :label="data"
        style="border-radius: 40px"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels v-model="tab" animated>
      <q-tab-panel
        v-for="(data, index) in categoryTab"
        :key="index"
        :name="data"
      >
        <div class="text-h6">{{ data }}</div>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
      </q-tab-panel>
    </q-tab-panels></q-card-section
  >
  <q-card-actions>
    <q-btn flat label="건너뛰기" class="bg-grey" @click="goNextStep" />
    <q-space />
    <q-btn flat label="다음으로" class="bg-grey" @click="goNextStep" />
  </q-card-actions>
</template>
