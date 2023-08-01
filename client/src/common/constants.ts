import { HomeTabs, NutritionConstants, SignUpSteps } from './models'

export const HOME_TABS: HomeTabs[] = [
  {
    alert: 'white',
    name: 'meal',
    icon: 'restaurant',
  },
  {
    alert: 'pink',
    name: 'description',
    icon: 'description',
  },
  { alert: 'purple', name: 'dessert', icon: 'icecream' },

  {
    alert: 'orange',
    name: 'custom',
    icon: 'settings_suggest',
  },
]

export const WHEATER_ICONS: { [key: string]: { name: string; color: string } } =
  {
    맑음: {
      name: 'sunny',
      color: 'yellow',
    },
    구름많음: {
      name: 'cloudy',
      color: 'grey',
    },
    흐림: {
      name: 'cloud',
      color: 'grey',
    },
    비: {
      name: 'rainy',
      color: 'grey',
    },
    '비/눈': {
      name: 'rainy',
      color: 'skyblue',
    },
    눈: {
      name: 'snow',
      color: 'skyblue',
    },
    소나기: {
      name: 'thunderstorm',
      color: 'black',
    },
  }

export const NUTRITION_CONSTATS: NutritionConstants = {
  TEENAGER: {
    MALE: {
      KCAL: 2700,
      SODIUM: 2000,
      PROTEIN: 65,
      FAT: 65,
      CARBOHYDRATE: 130,
    },
    FEMALE: {
      KCAL: 2000,
      SODIUM: 2000,
      PROTEIN: 55,
      FAT: 50,
      CARBOHYDRATE: 130,
    },
    OTHER: {
      KCAL: 2400,
      SODIUM: 2000,
      PROTEIN: 60,
      FAT: 55,
      CARBOHYDRATE: 130,
    },
  },
  YOUTH: {
    MALE: {
      KCAL: 2600,
      SODIUM: 2000,
      PROTEIN: 65,
      FAT: 70,
      CARBOHYDRATE: 130,
    },
    FEMALE: {
      KCAL: 2100,
      SODIUM: 2000,
      PROTEIN: 55,
      FAT: 50,
      CARBOHYDRATE: 130,
    },
    OTHER: {
      KCAL: 2300,
      SODIUM: 2000,
      PROTEIN: 60,
      FAT: 60,
      CARBOHYDRATE: 130,
    },
  },

  MIDDLEAGE: {
    MALE: {
      KCAL: 2400,
      SODIUM: 2000,
      PROTEIN: 65,
      FAT: 70,
      CARBOHYDRATE: 130,
    },
    FEMALE: {
      KCAL: 1900,
      SODIUM: 2000,
      PROTEIN: 55,
      FAT: 50,
      CARBOHYDRATE: 130,
    },
    OTHER: {
      KCAL: 2200,
      SODIUM: 2000,
      PROTEIN: 60,
      FAT: 60,
      CARBOHYDRATE: 130,
    },
  },
  SENIOR: {
    MALE: {
      KCAL: 2200,
      SODIUM: 2000,
      PROTEIN: 60,
      FAT: 65,
      CARBOHYDRATE: 130,
    },
    FEMALE: {
      KCAL: 1800,
      SODIUM: 2000,
      PROTEIN: 50,
      FAT: 50,
      CARBOHYDRATE: 130,
    },
    OTHER: {
      KCAL: 2000,
      SODIUM: 2000,
      PROTEIN: 55,
      FAT: 55,
      CARBOHYDRATE: 130,
    },
  },
}

export const NUTRITION_TO_KOREAN: { [key: string]: string } = {
  KCAL: '칼로리',
  SODIUM: '나트륨',
  PROTEIN: '단백질',
  CARBOHYDRATE: '탄수화물',
  FAT: '지방',
}

export const INDEX_LIST: string[] = Array.from({ length: 20 }, (_, index) =>
  (index + 1).toString().padStart(2, '0')
)

export const SIGN_UP_QSTEP_LIST: SignUpSteps[] = [
  {
    title: '개인 정보 동의',
    icon: '',
    caption: '(필수 항목)',
    require: true,
  },
  {
    title: '본인 확인',
    icon: '',
    caption: '(필수 항목)',
    require: true,
  },
  {
    title: '개인 정보 입력',
    icon: '',
    caption: '(필수 항목)',
    require: false,
  },
  {
    title: '선호 음식 선택',
    icon: '',
    caption: '(선택 항목)',
    require: false,
  },
]
