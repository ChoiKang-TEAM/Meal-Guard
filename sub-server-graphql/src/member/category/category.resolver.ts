import { Query, Resolver } from '@nestjs/graphql'
import { CategoryService } from './category.service'
import { Category } from './model/category.model'

@Resolver()
export class CategoryResolver {
  constructor(private readonly categoryService: CategoryService) {}

  @Query(() => [Category])
  async findAllCategory(): Promise<Category[]> {
    return this.categoryService.findAll()
  }
}
