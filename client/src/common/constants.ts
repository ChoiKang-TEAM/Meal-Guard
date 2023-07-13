import { HomeTabs } from './models'

export const HOME_TABS: HomeTabs[] = [
  {
    alert: 'white',
    name: 'meal',
    icon: 'restaurant',
  },
  {
    alert: 'pink',
    name: 'description',
    icon: 'description',
  },
  { alert: 'purple', name: 'dessert', icon: 'icecream' },

  {
    alert: 'orange',
    name: 'custom',
    icon: 'settings_suggest',
  },
]

export const WHEATER_ICONS: { [key: string]: { name: string; color: string } } =
  {
    맑음: {
      name: 'sunny',
      color: 'yellow',
    },
    구름많음: {
      name: 'cloudy',
      color: 'grey',
    },
    흐림: {
      name: 'cloud',
      color: 'grey',
    },
    비: {
      name: 'rainy',
      color: 'grey',
    },
    '비/눈': {
      name: 'rainy',
      color: 'skyblue',
    },
    눈: {
      name: 'snow',
      color: 'skyblue',
    },
    소나기: {
      name: 'thunderstorm',
      color: 'black',
    },
  }
