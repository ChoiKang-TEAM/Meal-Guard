export interface HomeTabs {
  alert: string
  name: string
  icon: string
}

export interface LoginMemberUserInput {
  userId: string
  password: string
}
export interface SignUpMemberUserInput {
  userId: string
  password: string
  name?: string | null
}
