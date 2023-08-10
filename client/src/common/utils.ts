import { Dialog } from 'quasar'

export const noticeDialog = (message: string) => {
  return Dialog.create({
    dark: true,
    title: '알림',
    message: message,
  })
}
