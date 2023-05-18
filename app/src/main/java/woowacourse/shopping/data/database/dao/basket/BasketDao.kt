package woowacourse.shopping.data.database.dao.basket

import woowacourse.shopping.data.model.DataBasketProduct

interface BasketDao {
    fun getPartiallyIncludeStartId(size: Int, standard: Int): List<DataBasketProduct>

    fun getPartiallyNotIncludeStartId(size: Int, standard: Int): List<DataBasketProduct>

    fun getPreviousPartiallyIncludeStartId(size: Int, standard: Int): List<DataBasketProduct>

    fun getPreviousPartiallyNotIncludeStartId(size: Int, standard: Int): List<DataBasketProduct>

    fun add(basketProduct: DataBasketProduct)

    fun remove(basketProduct: DataBasketProduct)
}
