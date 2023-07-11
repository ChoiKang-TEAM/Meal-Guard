<script lang="ts">
import { computed, defineComponent, onBeforeUnmount, onMounted, ref } from 'vue'
import { useIntersectionObserver } from 'src/composables/useIntersectionObserver'
import { IMAGE_GROUP } from 'src/common/images'
import { useFoodStore } from 'src/stores/food-store'
import { storeToRefs } from 'pinia'

export default defineComponent({
  setup() {
    const url = ref()
    const store = useFoodStore()
    const keyword = ref<string>('')
    const { rotateData, randomFoodData } = storeToRefs(store)
    const { getCurrentWeather } = store
    const parallax = ref<HTMLElement | null>(null)
    const parallax2 = ref<HTMLElement | null>(null)
    const section = ref<HTMLElement | null>(null)
    const isVisible = ref(false)

    const { observe, stopObserving } = useIntersectionObserver(
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

    const parallaxBodyScroll = () => {
      if (parallax2.value) {
        const scrollTop =
          window.pageYOffset || document.documentElement.scrollTop
        const parallaxOffset = scrollTop * 0.5
        parallax2.value.style.transform = `translate3d(0, ${parallaxOffset}px, 0)`
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

    const tabs = ref<
      {
        alert: string
        name: string
        icon: string
      }[]
    >([
      {
        alert: 'white',
        name: 'meal',
        icon: 'restaurant',
      },
      { alert: 'purple', name: 'dessert', icon: 'icecream' },
      {
        alert: 'orange',
        name: 'custom',
        icon: 'settings_suggest',
      },
    ])
    const sections = ref<string[]>(['Section 1', 'Section 2', 'Section 3'])
    const activeTabIndex = ref(0)
    const scrollToSection = (index: number) => {
      const sectionElement = document.querySelectorAll('.section')[
        index
      ] as HTMLElement
      sectionElement.scrollIntoView({ behavior: 'smooth' })
    }
    onBeforeUnmount(() => {
      window.removeEventListener('scroll', parallaxScroll)
      window.removeEventListener('scroll', parallaxBodyScroll)
    })
    const state = {
      url,
      keyword,
      randomImgUrl,
      imgStyle,
      tabs,
      sections,
      activeTabIndex,
      rotateData,
      randomFoodData,
      parallax,
      parallax2,
      section,
      isVisible,
    }
    const action = {
      scrollToSection,
    }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-page-container>
    <q-header style="margin-top: 50px">
      <q-tabs class="bg-black" v-model="activeTabIndex">
        <q-tab
          v-for="(tab, index) in tabs"
          :key="index"
          @click="scrollToSection(index)"
          :alert="tab.alert"
          :icon="tab.icon"
          :name="tab.name"
        />
      </q-tabs>
    </q-header>

    <q-card class="bg-yellow-3">
      <q-card-section>
        <div class="q-card-section">
          <div class="weather-widget">
            {{ randomFoodData?.data }}
            <div class="location">{{ rotateData?.region }}</div>
            <div class="temperature">{{ rotateData?.weather?.temp }}°C</div>
            <div class="description">
              <q-icon name="cloud" />{{ rotateData?.weather?.sky }}
            </div>
          </div>
          <div class="parallax" ref="parallax">
            <div class="lb-wrap">
              <div class="lb-text">
                <h2 class="title-text">식사 지킴이</h2>
              </div>
              <q-parallax class="meal-image" :src="randomImgUrl" />
            </div>
          </div>
        </div>
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section horizontal>
        <q-img
          class="col-5"
          src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
          style="height: 500px"
        />

        <q-card-section> dd </q-card-section>
      </q-card-section>

      <q-separator />
    </q-card>

    <q-card class="bg-pink-2">
      <q-card-section>
        <q-parallax
          class="img-50"
          src="src/assets/images/cake2.png"
          :height="100"
          style="width: 500px"
        />
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section horizontal>
        <q-img
          class="col-5"
          src="https://cdn.pixabay.com/photo/2015/08/25/03/50/herbs-906140_1280.jpg"
        />

        <q-card-section> dd </q-card-section>
      </q-card-section>

      <q-separator />
    </q-card>
    <!-- <div class="content-container">
          <div
            v-for="(section, index) in sections"
            :key="index"
            class="section"
          >
            {{ section }}
          </div>
        </div> -->
  </q-page-container>
</template>

<style scoped>
.meal-image {
  min-width: 500px;
  max-width: 500px;
  transition: opacity 1s ease-in-out;
}

.lb-text {
  padding: 10px 20px;
  text-align: center;
}
.title-text {
  color: white; /* 텍스트 색상 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 그림자 효과 */
}
.content-container {
  height: 400px;
  overflow-y: scroll;
}

.tab-sticky {
  position: sticky;
  top: 0;
  z-index: 1;
}

.weather-widget {
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* 화면 오른쪽에 정렬 */
  position: fixed; /* 고정 위치 */
  top: 110px; /* 상단 여백 */
  right: 20px; /* 오른쪽 여백 */
  padding: auto;
  background: linear-gradient(45deg, rgb(240, 240, 116), pink);
  border-radius: 20px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 그림자 효과 */
  z-index: 1000;
}

.location {
  margin-top: 15px;
  font-size: 20px;
  font-weight: bold;
}

.temperature {
  font-size: 48px;
  margin: 20px 0;
}

.description {
  font-size: 22px;
  color: rgb(228, 222, 222);
  margin-bottom: 15px;
}

.parallax {
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  perspective: 1000px;
}

.parallax-content {
  transform-style: preserve-3d;
  animation: float 6s infinite;
}

.parallax-title {
  font-size: 60px;
  font-weight: bold;
  color: #fff;
  margin: 0;
  text-align: center;
  opacity: 0;
  transform: translate3d(0, 100px, -100px);
  animation: fadeUp 2s forwards;
}

.parallax-subtitle {
  font-size: 24px;
  color: #fff;
  margin: 0;
  text-align: center;
  opacity: 0;
  transform: translate3d(0, 50px, -100px);
  animation: fadeUp 2s forwards;
}

.section {
  background-color: rgb(181, 232, 213);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #333;
  font-size: 24px;
}

.section-title {
  font-size: 36px;
  color: #333;
  margin: 0;
  opacity: 0;
  transform: translateY(100px);
  animation: fadeUp 1s forwards;
}

.section-content {
  font-size: 18px;
  color: #333;
  margin: 0;
  opacity: 0;
  transform: translateY(50px);
  animation: fadeUp 1s forwards;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@keyframes float {
  0% {
    transform: translate3d(0, 0, -100px);
  }
  50% {
    transform: translate3d(0, 50px, -100px);
  }
  100% {
    transform: translate3d(0, 0, -100px);
  }
}

@keyframes fadeUp {
  0% {
    opacity: 0;
    transform: translateY(100px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
