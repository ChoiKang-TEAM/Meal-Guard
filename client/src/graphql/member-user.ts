import gql from 'graphql-tag'

export const LOGIN_MEMBER_USER = gql`
  mutation loginMemberUser($loginMemberUserInput: LoginMemberUserInput!) {
    loginMemberUser(loginMemberUserInput: $loginMemberUserInput)
  }
`

export const SIGN_UP_MEMBER_USER = gql`
  mutation signUpMemberUser($signUpMemberUserInput: SignUpMemberUserInput!) {
    signUpMemberUser(signUpMemberUserInput: $signUpMemberUserInput)
  }
`
