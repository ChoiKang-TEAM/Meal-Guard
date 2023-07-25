<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  props: {
    blogData: String,
  },
  setup(props) {
    const isHovered = ref(false)
    const showButtons = () => {
      isHovered.value = true
    }

    const hideButtons = () => {
      isHovered.value = false
    }

    const handleButton1Click = () => {
      // Logic for Button 1 click
    }

    const handleButton2Click = () => {
      // Logic for Button 2 click
    }
    console.log(props.blogData)
    const state = { isHovered }
    const action = { showButtons, hideButtons }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <div class="best-blog">
    <q-card
      class="recipe-blog-card text-black wrapper bg-opacity"
      @mouseenter="showButtons"
      @mouseleave="hideButtons"
      :style="{
        backgroundColor: isHovered
          ? 'rgba(128, 128, 128, 0.8)'
          : 'rgba(255, 255, 255, 1)',
      }"
    >
      <q-img
        src="https://cdn.quasar.dev/img/mountains.jpg"
        spinner-color="white"
      >
        <div
          v-if="isHovered"
          class="absolute-full text-subtitle2 flex flex-center"
        />
      </q-img>

      <q-card-section>
        <div class="text-h6" style="height: 100px">블로그 이름</div>
      </q-card-section>
      <q-separator inset />
      <q-card-section>내용 간략히</q-card-section>
      <q-card-actions align="center" v-if="isHovered"
        ><q-btn outline label="최진영" icon="star" />
        <q-btn outline label="안찬양"
      /></q-card-actions>
    </q-card>
  </div>
</template>

<style>
.wrapper {
  position: relative;
  /* Add other styles as needed */
}

.buttons {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  /* Add other styles for the buttons container */
}

.buttons button {
  margin: 5px;
  /* Add other styles for the buttons as needed */
}
</style>
