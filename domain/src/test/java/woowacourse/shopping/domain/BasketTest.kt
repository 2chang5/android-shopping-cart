package woowacourse.shopping.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasketTest {
    @Test
    fun `장바구니에 상품을 담는다`() {
        val basketProducts = listOf<BasketProduct>()
        val basket = Basket(basketProducts)
        val basketProduct = BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url"))

        val actual = basket.add(basketProduct)
        val expected = Basket(basketProducts + basketProduct)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `장바구니에 이미 같은 상품이 담겨있는데 상품을 추가하면 상품 갯수가 늘어난다 `() {
        val basketProducts =
            listOf<BasketProduct>(BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")))
        val basket = Basket(basketProducts)
        val basketProduct = BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url"))

        val actual = basket.add(basketProduct)
        val expected = Basket(
            listOf<BasketProduct>(
                BasketProduct(
                    1,
                    Count(10),
                    Product(1, "새상품", Price(1000), "url")
                )
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `장바구니에 이미 같은 상품이 담겨있지 않은데 제품을 추가하면 새로운 상품으로 담긴다 `() {
        val basketProducts = listOf<BasketProduct>(
            BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")),
            BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))
        )
        val basket = Basket(basketProducts)
        val basketProduct = BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))

        val actual = basket.add(basketProduct)
        val expected = Basket(
            listOf<BasketProduct>(
                BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")),
                BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `장바구니에 이미 같은 상품이 담겨있는데 상품을 뺴면 갯수가 줄어든다(갯수가 0개가 되면 항목자체가 사라진다) `() {
        val basketProducts = listOf<BasketProduct>(
            BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")),
            BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))
        )
        val basket = Basket(basketProducts)
        val basketProduct = BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))

        val actual = basket.delete(basketProduct)
        val expected = Basket(
            listOf<BasketProduct>(
                BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url"))
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `장바구니에 이미 같은 상품이 담겨있는데 상품을 뺴면 갯수가 줄어든다(갯수가 0이 아니라면 항목은 남아있다) `() {
        val basketProducts = listOf<BasketProduct>(
            BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")),
            BasketProduct(2, Count(5), Product(3, "새상품", Price(1000), "url"))
        )
        val basket = Basket(basketProducts)
        val basketProduct = BasketProduct(2, Count(3), Product(3, "새상품", Price(1000), "url"))

        val actual = basket.delete(basketProduct)
        val expected = Basket(
            listOf<BasketProduct>(
                BasketProduct(1, Count(5), Product(1, "새상품", Price(1000), "url")),
                BasketProduct(2, Count(2), Product(3, "새상품", Price(1000), "url"))
            )
        )

        assertThat(actual).isEqualTo(expected)
    }
}
