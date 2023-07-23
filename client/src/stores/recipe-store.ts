import { defineStore } from 'pinia'
import { api } from 'src/boot/axios'
import { INDEX_LIST, NUTRITION_CONSTATS } from 'src/common/constants'
import { NutritionInfo, SetNutritionInputDto } from 'src/common/models'
import { ref } from 'vue'

export const useRecipeStore = defineStore('recipe', () => {
  const recipeBackGroundImageUrl = ref<string>('')
  const recipeRecipeImageUrl = ref<string>('')
  const manualData = ref()
  const nutritionData = ref(
    new Map<keyof NutritionInfo, number>([
      ['KCAL', 0],
      ['SODIUM', 0],
      ['PROTEIN', 0],
      ['CARBOHYDRATE', 0],
      ['FAT', 0],
    ])
  )
  const recipeDetailData = ref()

  const setNutritionData = (dto: SetNutritionInputDto) => {
    for (const key of nutritionData.value.keys()) {
      nutritionData.value.set(
        key,
        (dto.nutrient[key] /
          NUTRITION_CONSTATS[dto.ageGroup][dto.gender][key]) *
          100
      )
    }
  }

  const getRecipeDetailData = async (recipeId: number) => {
    const result = await api.get(`/recipes/${recipeId}`, {
      headers: {
        'Content-Type': 'application / json',
        'ngrok-skip-browser-warning': '69420',
      },
    })
    recipeBackGroundImageUrl.value = result?.data?.thumbnail
    recipeRecipeImageUrl.value = result?.data?.mainImage
    recipeDetailData.value = result?.data
    // recipeDetailData.value = {
    //   recipeId: 3367,
    //   name: '오징어 가라아게&칠리소스',
    //   way: '튀기기',
    //   part: '반찬',
    //   weight: '200',
    //   kcal: '595.1',
    //   carbohydrate: '30.4',
    //   protein: '25.1',
    //   fat: '41.5',
    //   sodium: '307.7',
    //   hashtag: '',
    //   mainImage:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032057_1678342857757.jpg',
    //   information:
    //     '•필수 재료 : 오징어(80g), 대파(20g), 마늘(10g), 달걀노른자(25g), 밀가루(20g), 식용유(3g)\n•양념 : 토마토(20g), 고춧가루(15g), 식초(50g), 설탕(15g), 전분(10g)',
    //   sodiumTip: '•케첩 대신 토마토를 사용하여 나트륨을 줄였어요.',
    //   manualImg01:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032201_1678342921809.jpg',
    //   manual01: '1. 오징어는 채 썰고, 마늘과 대파는 곱게 다진다.',
    //   manualImg02:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032221_1678342941060.jpg',
    //   manual02:
    //     '2. 오징어, 대파, 마늘, 달걀노른자, 밀가루, 물(10g)을 섞어 반죽한 뒤 동그랗게 빚는다.',
    //   manualImg03:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032235_1678342955393.jpg',
    //   manual03: '3. 가라아게를 180℃ 온도의 기름에서 튀긴다.',
    //   manualImg04:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032306_1678342986735.jpg',
    //   manual04: '4. 토마토는 껍질을 제거한 뒤 잘게 다진다.',
    //   manualImg05:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032326_1678343006043.jpg',
    //   manual05:
    //     '5. 냄비에 다진 토마토, 식초, 설탕, 물(50g), 고춧가루, 전분을 넣고 끓여 칠리소스를 만든다.',
    //   manualImg06:
    //     'http://www.foodsafetykorea.go.kr/uploadimg/20230309/20230309032352_1678343032997.jpg',
    //   manual06: '6. 오징어 가라아게에 칠리소스를 곁들여 마무리한다.',
    //   manualImg07: '',
    //   manual07: '',
    //   manualImg08: '',
    //   manual08: '',
    //   manualImg09: '',
    //   manual09: '',
    //   manualImg10: '',
    //   manual10: '',
    //   manualImg11: '',
    //   manual11: '',
    //   manualImg12: '',
    //   manual12: '',
    //   manualImg13: '',
    //   manual13: '',
    //   manualImg14: '',
    //   manual14: '',
    //   manualImg15: '',
    //   manual15: '',
    //   manualImg16: '',
    //   manual16: '',
    //   manualImg17: '',
    //   manual17: '',
    //   manualImg18: '',
    //   manual18: '',
    //   manualImg19: '',
    //   manual19: '',
    //   manualImg20: '',
    //   manual20: '',
    // }
    setRecipeMenualData(recipeDetailData.value)
  }

  const setRecipeMenualData = (data: any) => {
    const result = new Map()
    let index = '01'
    for (const key in data) {
      if (key.includes('manual') && !key.includes('Img') && data[key]) {
        const dto = {
          manual: data[key],
          manualImg: data[`manualImg${index}`],
        }

        result.set(key, dto)

        index = nextIndex(index)
      }
    }
    manualData.value = result
  }

  const nextIndex = (currentIndex: string) => {
    const currentIndexPosition = INDEX_LIST.indexOf(currentIndex)
    if (
      currentIndexPosition === -1 ||
      currentIndexPosition === INDEX_LIST.length - 1
    ) {
      return '' // 현재 인덱스가 배열에 없거나 배열의 마지막 인덱스라면 다음 인덱스를 가져올 수 없으므로 null 반환
    }
    return INDEX_LIST[currentIndexPosition + 1]
  }

  const state = {
    recipeBackGroundImageUrl,
    recipeDetailData,
    nutritionData,
    recipeRecipeImageUrl,
    manualData,
  }
  const action = { getRecipeDetailData, setNutritionData }

  return { ...state, ...action }
})
