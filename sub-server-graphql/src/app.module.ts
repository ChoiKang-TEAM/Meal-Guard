import { Module } from '@nestjs/common'
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo'
import { GraphQLModule } from '@nestjs/graphql'
import { AppService } from './app.service'
import { SearchImgModule } from './search/search-img.module'
import { PrismaModule } from './prisma/prisma.module'
import { join } from 'path'

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema.gql')
    }),
    SearchImgModule,
    PrismaModule
  ],
  controllers: [],
  providers: [AppService]
})
export class AppModule {}
