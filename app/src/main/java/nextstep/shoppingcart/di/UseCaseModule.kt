package nextstep.shoppingcart.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nextstep.shoppingcart.domain.repository.ProductRepository
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository
import nextstep.shoppingcart.domain.usecase.product.GetProduct
import nextstep.shoppingcart.domain.usecase.product.GetProducts
import nextstep.shoppingcart.domain.usecase.product.ProductUseCase
import nextstep.shoppingcart.domain.usecase.shoppingcart.AddProduct
import nextstep.shoppingcart.domain.usecase.shoppingcart.ClearProducts
import nextstep.shoppingcart.domain.usecase.shoppingcart.DecreaseProductQuantity
import nextstep.shoppingcart.domain.usecase.shoppingcart.GetQuantityByProduct
import nextstep.shoppingcart.domain.usecase.shoppingcart.GetShoppingCartProducts
import nextstep.shoppingcart.domain.usecase.shoppingcart.RemoveProduct
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideProductUseCase(repository: ProductRepository): ProductUseCase {
        return ProductUseCase(
            getProducts = GetProducts(repository),
            getProduct = GetProduct(repository)
        )
    }

    @Provides
    @Singleton
    fun provideShoppingCartUseCase(repository: ShoppingCartRepository): ShoppingCartUseCase {
        return ShoppingCartUseCase(
            getShoppingCartProducts = GetShoppingCartProducts(repository),
            getQuantityByProduct = GetQuantityByProduct(repository),
            addProduct = AddProduct(repository),
            decreaseProductQuantity = DecreaseProductQuantity(repository),
            removeProduct = RemoveProduct(repository),
            clearProducts = ClearProducts(repository)
        )
    }
}
