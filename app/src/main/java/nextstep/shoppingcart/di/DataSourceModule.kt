package nextstep.shoppingcart.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nextstep.shoppingcart.data.source.ProductDataSource
import nextstep.shoppingcart.data.source.ShoppingCartDataSource
import nextstep.shoppingcart.remote.source.ProductDataSourceImpl
import nextstep.shoppingcart.remote.source.ShoppingCartDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindProductDataSource(sourceImpl: ProductDataSourceImpl): ProductDataSource

    @Binds
    @Singleton
    abstract fun bindShoppingCartDataSource(sourceImpl: ShoppingCartDataSourceImpl): ShoppingCartDataSource
}