<script lang="ts">
import { useGoogleImgSotre } from 'src/stores/google-img-store'
import { computed, defineComponent, onMounted, ref } from 'vue'
import { IMAGE_GROUP } from 'src/common/images'
import { api } from 'src/boot/axios'
import { useFoodStore } from 'src/stores/food-store'
import { storeToRefs } from 'pinia'

export default defineComponent({
  setup() {
    const url = ref()
    const store = useFoodStore()
    const keyword = ref<string>('')
    const { rotateData, randomFoodData } = storeToRefs(store)
    const { getCurrentWeather } = store
    const imageData = ref({
      randomImgUrl: '',
      imgStyle: { opacity: 0 },
    })

    onMounted(async () => {
      await getCurrentWeather()
      const randomValue: number = Math.floor(Math.random() * IMAGE_GROUP.length)
      imageData.value.randomImgUrl = IMAGE_GROUP[randomValue]
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
    const state = {
      url,
      keyword,
      randomImgUrl,
      imgStyle,
      tabs,
      sections,
      activeTabIndex,
      rotateData,
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
      <div class="weather-widget">
        <div class="location">{{ rotateData?.region }}</div>
        <div class="temperature">{{ rotateData?.weather?.temp }}°C</div>
        <div class="description">
          <q-icon name="cloud" />{{ rotateData?.weather?.sky }}
        </div>
      </div>
      <div class="lb-wrap">
        <div class="lb-text">
          <h2 class="title-text">식사 지킴이</h2>
        </div>
        <div class="lb-image">
          <img class="meal-image" :src="randomImgUrl" :style="imgStyle" />
        </div>
      </div>

      <!-- <div>
        <div class="content-container">
          <div
            v-for="(section, index) in sections"
            :key="index"
            class="section"
          >
            {{ section }}
          </div>
        </div>
      </div> -->
    </q-card-section>
    <q-card-section>dasdasdasd</q-card-section>
  </q-card>
</template>

<style scoped>
.lb-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.lb-wrap .meal-image {
  width: 100%;
  height: auto;
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

.section {
  height: 500px;
  margin-bottom: 20px;
  background-color: #f2f2f2;
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
</style>
