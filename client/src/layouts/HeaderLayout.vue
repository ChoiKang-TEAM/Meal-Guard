<template>
  <!-- S : header -->
  <q-header elevated class="bg-black">
    <q-toolbar>
      <slot name="expand" />
      <slot name="tabs" />
    </q-toolbar>
    <div></div>
  </q-header>
  <!-- E : header -->
  <q-page-container>
    <q-page>
      <slot />
    </q-page>
  </q-page-container>
  <q-footer class="bg-black">
    <q-toolbar>
      <slot name="footer" />
    </q-toolbar>
  </q-footer>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  setup() {
    return {}
  },
})
</script>
