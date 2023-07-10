<script lang="ts">
import { useGoogleImgSotre } from 'src/stores/google-img-store'
import { defineComponent, onMounted, ref, watch } from 'vue'
import { IMAGE_GROUP } from 'src/common/images'

export default defineComponent({
  setup() {
    const url = ref()
    const store = useGoogleImgSotre()
    const keyword = ref<string>('')
    const { searchUrl } = store
    const randomImgUrl = ref<string>(IMAGE_GROUP[0])
    const imgStyle = ref<{ opacity: number }>({ opacity: 0 })
    onMounted(async () => {
      setInterval(() => {
        const randomValue: number = Math.floor(
          Math.random() * IMAGE_GROUP.length
        )
        randomImgUrl.value = IMAGE_GROUP[randomValue]
      }, 3000)
    })

    const change = async () => {
      url.value = await searchUrl(keyword.value)
    }
    const fadeImage = () => {
      imgStyle.value.opacity = 0

      setTimeout(() => {
        imgStyle.value.opacity = 1

        setTimeout(() => {
          imgStyle.value.opacity = 0
        }, 1000) // 이미지가 나타난 후 1초 후에 사라지도록 설정
      }, 500) // 이미지 변경 후 0.5초 후에 나타나도록 설정
    }
    watch(randomImgUrl, fadeImage)
    return { url, keyword, randomImgUrl, imgStyle, change }
  },
})
</script>

<template>
  <q-page>
    <q-card>
      <q-card-section class="bg-yellow-5">
        <div class="lb-wrap">
          <div class="lb-text">
            <h2>식사 지킴이</h2>
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
</style>
