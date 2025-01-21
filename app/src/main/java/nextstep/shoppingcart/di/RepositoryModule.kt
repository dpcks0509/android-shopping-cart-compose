package nextstep.shoppingcart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nextstep.shoppingcart.data.repository.ProductRepositoryImpl
import nextstep.shoppingcart.data.repository.ShoppingCartRepositoryImpl
import nextstep.shoppingcart.domain.repository.ProductRepository
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindShoppingCartRepository(repositoryImpl: ShoppingCartRepositoryImpl): ShoppingCartRepository

}