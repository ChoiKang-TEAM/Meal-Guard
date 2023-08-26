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

    // const parallaxStyle = computed(() => {
    //   console.log(yOffset.value)
    //   const transform = `translateY(${yOffset.value * 0.5 - 500}px)` // 조절 가능한 값
    //   return {
    //     transform,
    //   }
    // })

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
        <q-card-section class="q-pa-none q-ma-none">
          <q-img
            src="https://img.freepik.com/premium-photo/asian-korean-food-and-chopsticks-on-wooden-background_94255-4973.jpg?w=1800"
          />
          <div class="text-overlay text-border">
            <div class="text-h4">MEAL-GUARD</div>
          </div>
        </q-card-section>

        <q-intersection class="example-item" transition="flip-right" once>
          <q-card-section horizontal>
            <q-img
              class="col-6"
              src="https://t4.ftcdn.net/jpg/02/84/46/89/240_F_284468940_1bg6BwgOfjCnE3W0wkMVMVqddJgtMynE.jpg"
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

        <q-card-section class="q-pa-none q-ma-none bg-amber-2">
          <div class="parallax-container">
            <div class="parallax-content">
              <q-parallax :src="randomImgUrl" :speed="3" :height="400" />
            </div>
            <div class="text-overlay text-border">
              <div class="text-overline">SMART-MEAL</div>
              <div class="text-h2">맞춤 식사 추천</div>
              <br />
              <q-btn outline label="GO" />
            </div>
          </div>
        </q-card-section>

        <q-separator />

        <q-card-section horizontal>
          <div class="text-black text-position-middle col-6">
            <div class="text-h6">오늘의 요리</div>
            <div class="text-subtitle2 text-italic text-family-persive">
              MILL-RECIPE
            </div>
            <br />
            <div class="text-body2">더 건강한 재료</div>
            <div class="text-body2">더 풍성한 식탁</div>
            <div class="text-body2">더 보람찬 식사</div>
          </div>
          <q-img
            class="col-6"
            src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
          />
        </q-card-section>

        <q-separator />
        <q-card-section class="q-pa-none q-ma-none bg-amber-2">
          <q-img
            src="https://t3.ftcdn.net/jpg/01/79/59/92/240_F_179599293_7mePKnajSM4bggDa8NkKpcAHKl3pow2l.jpg"
          />

          <div class="text-overlay text-border">
            <div class="text-overline">SMART-RECIPE</div>
            <div class="text-h2">추천 레시피</div>
            <br />
            <q-btn outline color="black" label="GO" @click="openViewModal(2)" />
          </div>
        </q-card-section>

        <q-separator />

        <q-dialog v-model="viewModalState[2].state"
          ><recipe-table-dialog style="max-width: 1080px"></recipe-table-dialog
        ></q-dialog>

        <div class="flex justify-center items-center">
          <q-parallax src="src/assets/images/desserts.png" :height="500" />
        </div>

        <q-separator />
      </q-card>
    </header-layout>
  </q-layout>
</template>

<style lang="scss" scoped>
// .parallax-container {
//   position: relative;
//   width: 100%;
//   height: 100vh;
//   overflow: hidden;
// }

// .parallax-content {
//   position: relative;
//   width: 100%;
//   height: 100%;
//   background-size: cover;
//   background-position: center center;
//   margin: 0px 0px 0px 0px;
//   padding: 0%;
// }
</style>
