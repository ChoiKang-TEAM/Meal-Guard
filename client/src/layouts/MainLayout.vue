<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated class="bg-black">
      <q-toolbar>
        <q-toolbar-title align="center"> Meal Guard </q-toolbar-title>
      </q-toolbar>
      <q-tabs v-model="tab">
        <q-tab alert="white" name="meal" icon="restaurant" />
        <q-tab alert="purple" name="dessert" icon="icecream" />
        <q-tab alert="orange" name="custom" icon="settings_suggest" />
      </q-tabs>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'MainLayout',

  setup() {
    return {
      tab: ref(''),
    }
  },
})
</script>
