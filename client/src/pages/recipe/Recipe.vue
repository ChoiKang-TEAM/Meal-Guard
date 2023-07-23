<script lang="ts">
import { SetNutritionInputDto } from 'src/common/models'
import { storeToRefs } from 'pinia'
import { useRecipeStore } from 'src/stores/recipe-store'
import { defineComponent, onBeforeUnmount, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { useStatusStore } from 'src/stores/status-store'
import { useMemberUserStore } from 'src/stores/member-user-store'
import { useFoodStore } from 'src/stores/food-store'

export default defineComponent({
  setup() {
    const route = useRoute()
    const isBlur = ref<boolean>(false)
    const recipeStore = useRecipeStore()
    const memberUserStore = useMemberUserStore()
    const statusStore = useStatusStore()
    const foodStore = useFoodStore()

    const { getRecipeDetailData, setNutritionData } = recipeStore
    const { getNaverBlogByFoodName } = foodStore
    const { getAgeGroup } = statusStore

    const { userData } = storeToRefs(memberUserStore)
    const { recipeBackGroundImageUrl, recipeDetailData } =
      storeToRefs(recipeStore)
    const backgroundStyle = ref<string>('')

    const handleScroll = () => {
      isBlur.value = window.scrollY > 100
    }

    onMounted(async () => {
      const recipeId = Number(route.params.id)
      await getRecipeDetailData(recipeId)
      await getNaverBlogByFoodName(recipeDetailData?.value?.name)
      backgroundStyle.value = ` background: url(${recipeBackGroundImageUrl.value}); background-size: cover; background-attachment: fixed; background-position: center;`
      window.addEventListener('scroll', handleScroll)
      handleScroll()

      const dto: SetNutritionInputDto = {
        nutrient: {
          KCAL: recipeDetailData.value.kcal,
          CARBOHYDRATE: recipeDetailData.value.carbohydrate,
          PROTEIN: recipeDetailData.value.protein,
          FAT: recipeDetailData.value.fat,
          SODIUM: recipeDetailData.value.sodium,
        },
        ageGroup: getAgeGroup(userData.value.age),
        gender: userData.value.gender,
      }
      setNutritionData(dto)
    })

    onBeforeUnmount(() => {
      window.removeEventListener('scroll', handleScroll)
    })

    const state = { isBlur, backgroundStyle }
    return state
  },
})
</script>

<template>
  <q-layout :style="backgroundStyle" view="hHh lpr fff">
    <div :class="{ 'bg-blur': isBlur }"></div>
    <router-view />
  </q-layout>
</template>

<style scoped lang="scss">
.bg-blur {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(8px); /* 블러 효과 적용 */
  -webkit-backdrop-filter: blur(8px); /* Safari 용 */
}
</style>
