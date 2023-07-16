import { ColumnsModel } from './models'

export const RECIPE_COLUMNS: ColumnsModel[] = [
  {
    name: 'recipeId',
    align: 'left',
    label: 'No',
    field: 'recipeId',
    sortable: true,
  },
  {
    name: 'part',
    align: 'center',
    label: '종류',
    field: 'part',
  },
  {
    name: 'foodPicture',
    align: 'center',
    label: '음식 사진',
    field: 'foodPicture',
  },
  {
    name: 'name',
    align: 'center',
    label: '음식 이름',
    field: 'name',
    sortable: true,
  },
  {
    name: 'way',
    align: 'center',
    label: '조리 방법',
    field: 'way',
  },

  {
    name: 'recipePicture',
    align: 'center',
    label: '재료 사진',
    field: 'recipePicture',
  },
]
