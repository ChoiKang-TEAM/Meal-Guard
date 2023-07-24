import gql from 'graphql-tag'

export const SAMPLE_QUERY = gql`
  query sampleQuery($keyword: String!) {
    sampleQuery(keyword: $keyword)
  }
`

export const GET_DAUM_BLOG_API = gql`
  query getDaumBlogApi($keyword: String!) {
    getDaumBlogApi(keyword: $keyword) {
      title
      thumbnail
      contents
      url
      blogname
    }
  }
`
