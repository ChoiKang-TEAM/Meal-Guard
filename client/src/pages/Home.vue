<script lang="ts">
import { useGoogleImgSotre } from 'src/stores/google-img-store'
import { computed, defineComponent, onMounted, ref } from 'vue'
import { IMAGE_GROUP } from 'src/common/images'

export default defineComponent({
  setup() {
    const url = ref()
    const store = useGoogleImgSotre()
    const keyword = ref<string>('')
    const { searchUrl } = store
    const imageData = ref({
      randomImgUrl: '',
      imgStyle: { opacity: 0 },
    })
    onMounted(async () => {
      const randomValue: number = Math.floor(Math.random() * IMAGE_GROUP.length)
      imageData.value.randomImgUrl = IMAGE_GROUP[randomValue]
    })

    const change = async () => {
      url.value = await searchUrl(keyword.value)
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
          console.log(2)
          imgStyle.value.opacity = 0
          fadeImage()
        }, 2000)
      }, 1000)
    }
    const randomImgUrl = computed(() => {
      console.log(1)
      return imageData.value.randomImgUrl
    })
    const imgStyle = computed(() => imageData.value.imgStyle)

    fadeImage() // 초기 이미지 애니메이션 적용
    return { url, keyword, randomImgUrl, imgStyle, change }
  },
})
</script>

<template>
  <q-page>
    <q-card>
      <q-card-section class="bg-yellow-3">
        <div class="lb-wrap">
          <div class="lb-text">
            <h2 class="title-text">식사 지킴이</h2>
          </div>
          <div class="lb-image">
            <img class="meal-image" :src="randomImgUrl" :style="imgStyle" />
          </div>
        </div>
      </q-card-section>
    </q-card>
  </q-page>
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
</style>
