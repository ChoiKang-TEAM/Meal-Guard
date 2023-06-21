<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated class="bg-red">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> 식사 냠냠이 </q-toolbar-title>

        <q-tabs v-model="tab">
          <q-tab alert="white" name="meal" icon="restaurant" />
          <q-tab alert="purple" name="dessert" icon="icecream" />
          <q-tab alert="orange" name="custom" icon="settings_suggest" />
        </q-tabs>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header> Essential Links </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import EssentialLink from 'components/EssentialLink.vue'

const linksList = [
  {
    title: 'Github',
    caption: 'github.com/ChoiKang-TEAM/Meal-Guard',
    icon: 'code',
    link: 'https://github.com/ChoiKang-TEAM/Meal-Guard',
  },
  {
    title: 'Discord Chat Channel',
    caption: 'chat.discord.ahn-kang-choi',
    icon: 'discord',
    link: 'https://discord.com/channels/956122442068279317/956122442655469572',
  },
]

export default defineComponent({
  name: 'MainLayout',

  components: {
    EssentialLink,
  },

  setup() {
    const leftDrawerOpen = ref(false)

    return {
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value
      },
      tab: ref(''),
    }
  },
})
</script>
