import gql from 'graphql-tag'

export const FIND_ALL_CATEGORY = gql`
  query {
    findAllCategory {
      type
    }
  }
`
