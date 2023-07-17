<script lang="ts">
import { storeToRefs } from 'pinia'
import { useFoodStore } from 'src/stores/food-store'
import { defineComponent, onMounted, ref } from 'vue'
import { Form as ValidationForm, useForm, useField } from 'vee-validate'
import * as yup from 'yup'
import { RECIPE_COLUMNS } from 'src/common/columns'
import { useRouter } from 'vue-router'

export default defineComponent({
  components: { ValidationForm },
  setup() {
    const store = useFoodStore()
    const { recipeData, recipeTotalPage } = storeToRefs(store)
    const { getRecipeData } = store
    const pageIndex = ref<number>(1)
    const router = useRouter()

    const validationSchema = yup.object({
      filter: yup.string().defined().required('필수 값을 입력해주세요.'),
    })

    const { errors, meta } = useForm({
      validationSchema,
      initialValues: {
        filter: '',
      },
    })
    const { value: filter } = useField<string>('filter', {
      validateOnInput: true,
    })

    onMounted(() => {
      const dto = {
        page: 1,
        size: 10,
        name: '',
      }
      getRecipeData(dto)
    })

    const pageChange = async () => {
      const dto = {
        page: pageIndex.value,
        size: 10,
        name: filter.value,
      }
      await getRecipeData(dto)
    }

    const getRecipeDataBySearch = async () => {
      const dto = {
        page: 1,
        size: 10,
        name: filter.value,
      }
      await getRecipeData(dto)
      pageIndex.value = 1
    }

    const goDetailPage = (id: number) => {
      console.log(id)
      router.push({ name: 'RecipeDetail', params: { id: id } })
    }

    const state = {
      RECIPE_COLUMNS,
      recipeData,
      pageIndex,
      recipeTotalPage,
      filter,
      errors,
    }
    const action = { pageChange, getRecipeDataBySearch, goDetailPage }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card>
    <q-table
      title="음식 레시피"
      :rows="recipeData"
      :columns="RECIPE_COLUMNS"
      row-key="recipeId"
      hide-pagination
      flat
      bordered
      :rows-per-page-options="[10]"
      no-data-label="데이터가 없습니다."
    >
      <template v-slot:top-right>
        <validation-form @submit="getRecipeDataBySearch">
          <q-input
            flat
            bordered
            square
            debounce="300"
            v-model="filter"
            placeholder="Search"
            no-error-icon
            :error="!!errors.filter"
          >
            <template v-slot:append>
              <q-btn @click="getRecipeDataBySearch" type="submit" flat rounded
                ><q-icon name="search"
              /></q-btn>
            </template>
            <template #error>{{ errors.filter }} </template>
          </q-input>
        </validation-form>
      </template>
      <template v-slot:body="props">
        <q-tr :props="props" @click="goDetailPage(props.row?.recipeId)">
          <q-td key="recipeId" :props="props">{{ props.row?.recipeId }}</q-td>
          <q-td key="part" :props="props">{{ props.row?.part }}</q-td>
          <q-td key="foodPicture" :props="props"
            ><q-img :src="props.row?.foodUrlImage"
          /></q-td>
          <q-td key="name" :props="props">{{ props.row?.name }}</q-td>
          <q-td key="way" :props="props">{{ props.row?.way }}</q-td>

          <q-td key="recipePicture" :props="props"
            ><q-img :src="props.row?.mainImage"
          /></q-td>
        </q-tr>
      </template>
    </q-table>
    <div class="q-pa-lg flex flex-center">
      <q-pagination
        color="grey-7"
        text-color="black"
        v-model="pageIndex"
        :max="recipeTotalPage"
        v-if="recipeTotalPage > 1"
        :max-pages="6"
        direction-links
        boundary-links
        icon-first="skip_previous"
        icon-last="skip_next"
        icon-prev="fast_rewind"
        icon-next="fast_forward"
        @update:model-value="pageChange()"
      />
    </div>
  </q-card>
</template>
