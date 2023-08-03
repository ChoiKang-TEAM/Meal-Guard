export interface HomeTabs {
  alert: string
  name: string
  icon: string
}

export interface SignUpSteps {
  title: string
  caption?: string | null
  icon: string
}

export type GenderType = 'MALE' | 'FEMALE' | 'OTHER'
export type AgeType = 'TEENAGER' | 'YOUTH' | 'MIDDLEAGE' | 'SENIOR'

export interface NutritionInfo {
  KCAL: number
  SODIUM: number
  PROTEIN: number
  CARBOHYDRATE: number
  FAT: number
}

export type NutritionConstants = Record<
  AgeType,
  Record<GenderType, NutritionInfo>
>

export interface LoginMemberUserInput {
  userId: string
  password: string
}
export interface SignUpMemberUserInput {
  userId: string
  password: string
  name?: string | null
}

export interface ColumnsModel {
  name: string
  align?: 'left' | 'right' | 'center'
  label: string
  field: string | ((row: any) => any)
  sortable?: boolean
  required?: boolean
  sort?: (a: any, b: any, rowA: any, rowB: any) => number
  headerClasses?: string
  sortOrder?: 'ad' | 'da'
  format?: (val: any, row: any) => any
  style?: string | ((row: any) => string)
  classes?: string | ((row: any) => string)
  headerStyle?: string
}

export interface BackgroundStyleModel {
  background?: string
  'background-size'?: 'cover' | 'contain'
  opacity?: number
}

export interface SetNutritionInputDto {
  nutrient: NutritionInfo
  ageGroup: AgeType
  gender: GenderType
}

export interface RecipeBlogCardModel {
  blogname: string
  contents: string
  thumbnail: string
  title: string
  url: string
}
