import { Inject, Injectable } from '@nestjs/common'
import { CrudService } from 'src/shared/interfaces/factory.interface'
import { Food } from './model/food.model'
import { PrismaService } from 'src/shared/prisma/prisma.service'
import { WINSTON_MODULE_PROVIDER } from 'nest-winston'
import { Logger } from 'winston'
import { CreateFoodInput, FindFilterFoodInput } from './dto/food.input'

@Injectable()
export class FoodService implements CrudService<Food> {
  constructor(private readonly prisma: PrismaService, @Inject(WINSTON_MODULE_PROVIDER) private readonly logger: Logger) {}
  async create(dto: CreateFoodInput): Promise<boolean> {
    try {
      await this.prisma.food.create({
        data: {
          name: dto.name,
          categoryId: dto.categoryId
        }
      })

      return true
    } catch (e) {
      this.logger.error(e)
      return false
    }
  }
  delete(dto: Partial<Food>): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  update(dto: Partial<Food>): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  findUnique(dto: Partial<Food>): Promise<Food> {
    throw new Error('Method not implemented.')
  }
  findAll(): Promise<Food[]> {
    throw new Error('Method not implemented.')
  }
  async findByFilter(dto: FindFilterFoodInput): Promise<Food[]> {
    try {
      return await this.prisma.food.findMany({
        where: {
          categoryId: dto.categoryId
        },
        include: {
          category: true
        }
      })
    } catch (e) {
      this.logger.error(e)
    }
  }
}
