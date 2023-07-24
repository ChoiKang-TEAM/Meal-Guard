import { Field, ObjectType } from '@nestjs/graphql'

@ObjectType()
export class DocumentByDaumBlog {
  @Field(() => String, { nullable: true })
  readonly title?: string

  @Field(() => String, { nullable: true })
  readonly contents?: string

  @Field(() => String, { nullable: true })
  readonly url?: string

  @Field(() => String, { nullable: true })
  readonly blogname?: string

  @Field(() => String, { nullable: true })
  readonly thumbnail?: string

  @Field(() => Date, { nullable: true })
  readonly datetime?: Date
}
