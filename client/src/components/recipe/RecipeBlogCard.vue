<script lang="ts">
import { computed, defineComponent, ref } from 'vue'
import { RecipeBlogCardModel } from 'src/common/models'

export default defineComponent({
  props: {
    blogData: {
      type: Object as () => RecipeBlogCardModel,
      required: true,
    },
  },
  setup(props) {
    const blodCardData = computed(() => props.blogData)
    const isHovered = ref(false)
    const urltest =
      'https://hanamsport.or.kr/www/images/contents/thum_detail.jpg'
    const showButtons = () => {
      isHovered.value = true
    }

    const hideButtons = () => {
      isHovered.value = false
    }

    const goBlogUrl = (url: string) => {
      window.open(url, '_blank', 'noopener,noreferrer')
    }
    const state = { isHovered, blodCardData, urltest }
    const action = { showButtons, hideButtons, goBlogUrl }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <q-card
    class="recipe-blog-card text-black border-rounded"
    @mouseenter="showButtons"
    @mouseleave="hideButtons"
    :style="{
      backgroundColor: isHovered
        ? 'rgba(128, 128, 128, 0.8)'
        : 'rgba(255, 255, 255, 1)',
    }"
  >
    <q-img
      :src="
        blodCardData.thumbnail.length === 0 ? urltest : blodCardData.thumbnail
      "
      spinner-color="white"
      height="325.33px"
    >
      <div
        v-if="isHovered"
        class="absolute-full text-subtitle2 flex flex-center"
      >
        <q-btn
          size="xl"
          rounded
          flat
          outline
          icon="bookmark_border"
          color="gray"
        />
        <q-btn
          size="xl"
          rounded
          flat
          outline
          icon="login"
          color="gray"
          @click="goBlogUrl(blodCardData.url)"
        >
          <q-tooltip class="bg-white text-black"
            >블로그로 이동</q-tooltip
          ></q-btn
        >
      </div>
    </q-img>

    <q-card-section>
      <div class="text-subtitle2" style="height: 100px">
        <p v-html="blodCardData.title"></p>
      </div>
    </q-card-section>
    <q-separator inset />
    <q-card-section><p v-html="blodCardData.contents"></p></q-card-section>
  </q-card>
</template>
