export const PASSWORD_REGEX = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=\S+$).{10,18}$/
export const PHONENUMBER_REGEX = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/
export const EMAIL_REGEX =
  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
export const NAME_REGEX = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]*$/
