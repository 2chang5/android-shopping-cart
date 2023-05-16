package woowacourse.shopping.ui.shopping

import woowacourse.shopping.ui.model.UiProduct
import woowacourse.shopping.ui.model.UiRecentProduct

interface ShoppingContract {
    interface View {

        fun updateProducts(products: List<UiProduct>)

        fun updateRecentProducts(recentProducts: List<UiRecentProduct>)

        fun showProductDetail(product: UiProduct)

        fun updateMoreButtonState(isVisible: Boolean)
    }

    interface Presenter {
        val view: View

        fun fetchProducts()
        fun fetchRecentProducts()

        fun inquiryProductDetail(product: UiProduct)

        fun inquiryRecentProductDetail(recentProduct: UiProduct)

        fun fetchHasNext()
    }
}
