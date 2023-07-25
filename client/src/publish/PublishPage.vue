<script lang="ts">
import { defineComponent } from 'vue'
import RecipeBlogCardComponent from './components/RecipeBlogCardComponent.vue'

export default defineComponent({
  components: {
    RecipeBlogCardComponent,
  },
  setup() {
    const state = {}
    const action = {}
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <div>
    <recipe-blog-card-component></recipe-blog-card-component>
  </div>
</template>
