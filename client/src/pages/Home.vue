<script lang="ts">
import {
  computed,
  defineComponent,
  onBeforeUnmount,
  onMounted,
  reactive,
  ref,
} from 'vue'
import { useIntersectionObserver } from 'src/composables/useIntersectionObserver'
import { IMAGE_GROUP } from 'src/common/images'
import { useFoodStore } from 'src/stores/food-store'
import { storeToRefs } from 'pinia'
import { HOME_TABS, WHEATER_ICONS } from 'src/common/constants'
import RecipeTableDialog from 'src/components/recipe/RecipeTableDialog.vue'
import HeaderLayout from 'src/layouts/HeaderLayout.vue'

export default defineComponent({
  components: {
    RecipeTableDialog,
    HeaderLayout,
  },
  setup() {
    const url = ref()
    const store = useFoodStore()
    const keyword = ref<string>('')
    const { rotateData, randomFoodData } = storeToRefs(store)
    const { getCurrentWeather } = store
    const parallax = ref<HTMLElement | null>(null)
    const isVisible = ref(false)

    const yOffset = ref(0)

    const imageData = ref({
      randomImgUrl: '',
      imgStyle: { opacity: 0 },
    })

    const handleScroll = () => {
      yOffset.value = window.scrollY
    }

    onMounted(async () => {
      const randomValue: number = Math.floor(Math.random() * IMAGE_GROUP.length)
      imageData.value.randomImgUrl = IMAGE_GROUP[randomValue]
      window.addEventListener('scroll', handleScroll)
      //await getCurrentWeather()
    })

    const fadeImage = () => {
      imgStyle.value.opacity = 0

      setTimeout(() => {
        imgStyle.value.opacity = 1
        const randomValue: number = Math.floor(
          Math.random() * IMAGE_GROUP.length
        )
        imageData.value.randomImgUrl = IMAGE_GROUP[randomValue]
        setTimeout(() => {
          imgStyle.value.opacity = 0
          fadeImage()
        }, 2000)
      }, 1000)
    }
    const randomImgUrl = computed(() => {
      return imageData.value.randomImgUrl
    })
    const imgStyle = computed(() => imageData.value.imgStyle)

    fadeImage() // 초기 이미지 애니메이션 적용

    const activeTabIndex = ref(0)
    const scrollToSection = (index: number) => {
      const sectionElement = document.querySelectorAll('.section')[
        index
      ] as HTMLElement
      sectionElement.scrollIntoView({ behavior: 'smooth' })
    }

    const parallaxStyle = computed(() => {
      const transform = `translateY(${yOffset.value * 0.5}px)` // 조절 가능한 값
      return {
        transform,
      }
    })

    const viewModalState = reactive<{ name: string; state: boolean }[]>([
      {
        name: 'food',
        state: false,
      },
      {
        name: 'dessert',
        state: false,
      },
      {
        name: 'recipe',
        state: false,
      },
    ])
    const openViewModal = (viewStateIndex: number): void => {
      viewModalState[viewStateIndex].state = true
    }

    const state = {
      url,
      keyword,
      randomImgUrl,
      imgStyle,
      HOME_TABS,
      parallaxStyle,
      activeTabIndex,
      rotateData,
      randomFoodData,
      parallax,
      isVisible,
      WHEATER_ICONS,
      viewModalState,
    }
    const action = {
      scrollToSection,
      openViewModal,
    }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-layout class="common-layout" view="lHh Lpr lFf">
    <header-layout>
      <template #expand>
        <q-toolbar-title class="toolbar-title">
          <div class="home-header-text">MEAL-GUARD</div>
          <q-tabs v-model="activeTabIndex">
            <q-tab
              v-for="(tab, index) in HOME_TABS"
              :key="index"
              @click="scrollToSection(index)"
              :alert="tab.alert"
              :icon="tab.icon"
              :name="tab.name"
            />
          </q-tabs>
        </q-toolbar-title>
      </template>

      <div class="weather-widget">
        <div class="location">{{ rotateData?.region }}</div>
        <div class="temperature">{{ rotateData?.weather?.temp }}°C</div>
        <div class="description">
          <!-- <ion-icon v-if="rotateData?.weather?.sky" name="snow" /> -->
          {{ rotateData?.weather?.sky }}
        </div>
      </div>

      <q-card flat class="common-content">
        <q-card-section>
          <div>
            <div class="text-subtitle1 text-overline">SMART-MEAL</div>
          </div>
        </q-card-section>

        <q-card-section class="q-pa-none q-ma-none bg-amber-2">
          <div class="parallax-container">
            <div class="parallax-content" :style="parallaxStyle">
              <q-img :src="randomImgUrl" />
            </div>
            <div class="text-overlay text-border">
              <div class="text-subtitle1 text-overline">SMART-MEAL</div>
              <div class="text-h2">맞춤 식사 추천</div>
              <q-btn outline label="GO" />
            </div>
          </div>
        </q-card-section>
        <q-intersection class="example-item" transition="flip-right" once>
          <q-card-section horizontal>
            <q-img
              class="col-6"
              src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
            />
            <div class="text-black text-position-middle col-6">
              <div class="text-h6">더 건강한 식사</div>
              <div class="text-subtitle2 text-italic text-family-persive">
                MEAL-GUARD
              </div>
              <br />
              <div class="text-body2">한 끼를 소중하게</div>
              <div class="text-body2">한 끼를 더 가치있게</div>
              <div class="text-body2">한 끼를 보람차게</div>
            </div>
          </q-card-section>
        </q-intersection>
        <q-separator />

        <div class="flex justify-center items-center">
          <q-parallax src="src/assets/images/desserts.png" :height="500" />
        </div>

        <q-card-section horizontal>
          <q-img
            class="col-5"
            src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
          />

          <q-card-section class="text-black"> dd </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-section horizontal>
          <q-card-section class="q-pt-xs">
            <div class="text-overline text-black">Meal Guard</div>
            <div class="text-h5 q-mt-sm q-mb-xs text-black">추천 레시피</div>
            <div class="text-caption text-grey">
              오늘의 한 끼, 건강한 한 끼, 모두의 한 끼
            </div>
          </q-card-section>

          <q-card-section class="col-5 flex flex-center">
            <q-img
              class="rounded-borders"
              src="https://cdn.quasar.dev/img/parallax2.jpg"
            />
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn
            @click="openViewModal(2)"
            flat
            color="primary"
            label="레시피 보기"
          />
        </q-card-actions>
        <q-dialog v-model="viewModalState[2].state"
          ><recipe-table-dialog style="max-width: 1080px"></recipe-table-dialog
        ></q-dialog>
      </q-card>
    </header-layout>
  </q-layout>
</template>

<style lang="scss" scoped>
.parallax-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.parallax-content {
  position: relative;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center center;
  margin: 0px 0px 0px 0px;
  padding: 0%;
}

.text-overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  text-align: center;
  color: white;
}
</style>
