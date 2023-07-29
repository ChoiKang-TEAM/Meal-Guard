import { Inject, Injectable } from '@nestjs/common'
import { CrudService } from 'src/shared/interfaces/factory.interface'
import { Category } from './model/category.model'
import { PrismaService } from 'src/shared/prisma/prisma.service'
import { WINSTON_MODULE_PROVIDER } from 'nest-winston'
import { Logger } from 'winston'

@Injectable()
export class CategoryService implements CrudService<Category> {
  constructor(private readonly prisma: PrismaService, @Inject(WINSTON_MODULE_PROVIDER) private readonly logger: Logger) {}

  create(dto: Partial<Category>): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  delete(dto: Partial<Category>): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  update(dto: Partial<Category>): Promise<boolean> {
    throw new Error('Method not implemented.')
  }
  findUnique(dto: Partial<Category>): Promise<Category> {
    throw new Error('Method not implemented.')
  }
  findAll(): Promise<Category[]> {
    try {
      return this.prisma.category.findMany({})
    } catch (e) {
      this.logger.error(e)
    }
  }
  findByFilter(dto: Partial<Category>): Promise<Category[]> {
    throw new Error('Method not implemented.')
  }
}
