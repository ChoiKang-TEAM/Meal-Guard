import { ref, onMounted, onUnmounted } from 'vue'

interface IntersectionObserverOptions {
  root?: Element | null
  rootMargin?: string
  threshold?: number | number[]
}

interface IntersectionObserverResult {
  observe: () => void
  stopObserving: () => void
}

export function useIntersectionObserver(
  callback: (
    entries: IntersectionObserverEntry[],
    observer: IntersectionObserver
  ) => void,
  options: IntersectionObserverOptions = {}
): IntersectionObserverResult {
  const target = ref<Element | null>(null)
  let observer: IntersectionObserver | null = null

  const observe = () => {
    if (!target.value || observer) return

    observer = new IntersectionObserver((entries) => {
      callback(entries, observer!)
    }, options)

    observer.observe(target.value)
  }

  const stopObserving = () => {
    if (observer) {
      observer.disconnect()
      observer = null
    }
  }

  onMounted(() => {
    observe()
  })

  onUnmounted(() => {
    stopObserving()
  })

  return {
    observe,
    stopObserving,
  }
}
