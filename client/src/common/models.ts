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

export interface ColumnsModel {
  name: string
  align?: 'left' | 'right' | 'center'
  label: string
  field: string | ((row: any) => any)
  sortable?: boolean
  required?: boolean
  sort?: (a: any, b: any, rowA: any, rowB: any) => number
  headerClasses?: string
  sortOrder?: 'ad' | 'da'
  format?: (val: any, row: any) => any
  style?: string | ((row: any) => string)
  classes?: string | ((row: any) => string)
  headerStyle?: string
}
