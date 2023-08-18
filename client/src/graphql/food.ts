import gql from 'graphql-tag'

export const FIND_FOOD_BY_FILTER = gql`
  query findFoodByFilter($findFilterFoodInput: FindFilterFoodInput!) {
    findFoodByFilter(findFilterFoodInput: $findFilterFoodInput) {
      id
      name
    }
  }
`
