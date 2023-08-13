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

export const VALID_BY_IN_USE_CASE_FROM_USER_ID = gql`
  query validByInUseCaseFromUserId(
    $findMemberUserByUserId: FindMemberUserByUserId!
  ) {
    validByInUseCaseFromUserId(findMemberUserByUserId: $findMemberUserByUserId)
  }
`
