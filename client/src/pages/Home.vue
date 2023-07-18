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

export default defineComponent({
  components: {
    RecipeTableDialog,
  },
  setup() {
    const url = ref()
    const store = useFoodStore()
    const keyword = ref<string>('')
    const { rotateData, randomFoodData } = storeToRefs(store)
    const { getCurrentWeather, favoriteApi } = store
    const parallax = ref<HTMLElement | null>(null)

    const isVisible = ref(false)

    const { observe } = useIntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            isVisible.value = true
          } else {
            isVisible.value = false
          }
        })
      },
      {
        root: null,
        rootMargin: '0px',
        threshold: 0.5,
      }
    )

    const imageData = ref({
      randomImgUrl: '',
      imgStyle: { opacity: 0 },
    })

    onMounted(async () => {
      observe()
      favoriteApi()
      const randomValue: number = Math.floor(Math.random() * IMAGE_GROUP.length)
      imageData.value.randomImgUrl = IMAGE_GROUP[randomValue]
      window.addEventListener('scroll', parallaxScroll)

      await getCurrentWeather()
    })

    const parallaxScroll = () => {
      if (parallax.value) {
        const scrollTop =
          window.pageYOffset || document.documentElement.scrollTop
        const parallaxOffset = scrollTop * 0.5
        parallax.value.style.transform = `translate3d(0, ${parallaxOffset}px, 0)`
      }
    }

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
    onBeforeUnmount(() => {
      window.removeEventListener('scroll', parallaxScroll)
    })

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
  <q-header style="margin-top: 50px">
    <q-tabs class="bg-black" v-model="activeTabIndex">
      <q-tab
        v-for="(tab, index) in HOME_TABS"
        :key="index"
        @click="scrollToSection(index)"
        :alert="tab.alert"
        :icon="tab.icon"
        :name="tab.name"
      />
    </q-tabs>
  </q-header>

  <q-card class="bg-yellow-3 section">
    <q-card-section>
      <div class="weather-widget">
        <div class="location">{{ rotateData?.region }}</div>
        <div class="temperature">{{ rotateData?.weather?.temp }}°C</div>
        <div class="description">
          <!-- <ion-icon v-if="rotateData?.weather?.sky" name="snow" /> -->
          {{ rotateData?.weather?.sky }}
        </div>
      </div>
      <div class="parallax" ref="parallax">
        <div>
          <div class="lb-text">
            <h2 class="title-text">식사 지킴이</h2>
          </div>
          <q-parallax class="meal-image" :src="randomImgUrl" />
        </div>
      </div>
    </q-card-section>
  </q-card>
  <q-card>
    <q-card-section horizontal>
      <q-img
        class="col-5"
        src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
        style="height: 500px"
      />
    </q-card-section>

    <q-separator />
  </q-card>

  <q-card class="bg-pink-2 section">
    <div class="flex justify-center items-center">
      <q-parallax
        src="src/assets/images/desserts.png"
        :height="500"
        style="width: 800px"
      />
    </div>
  </q-card>
  <q-card flat>
    <q-card-section horizontal>
      <q-img
        class="col-5"
        src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
      />

      <q-card-section class="text-black"> dd </q-card-section>
    </q-card-section>

    <q-separator />
  </q-card>
  <q-card class="my-card section" flat bordered>
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
</template>

<style lang="scss" scoped>
.meal-image {
  width: 500px;
}
</style>
