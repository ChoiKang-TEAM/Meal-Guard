import gql from 'graphql-tag'

export const SAMPLE_QUERY = gql`
  query sampleQuery($keyword: String!) {
    sampleQuery(keyword: $keyword)
  }
`
