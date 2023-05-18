package woowacourse.shopping.data.database.dao.basket

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.DatabaseUtils
import android.provider.BaseColumns
import woowacourse.shopping.data.database.ShoppingDatabase
import woowacourse.shopping.data.database.contract.BasketContract
import woowacourse.shopping.data.database.contract.ProductContract
import woowacourse.shopping.data.model.DataBasketProduct
import woowacourse.shopping.data.model.DataCount
import woowacourse.shopping.data.model.DataPrice
import woowacourse.shopping.data.model.DataProduct

class BasketDaoImpl(private val database: ShoppingDatabase) : BasketDao {
    @SuppressLint("Range")
    override fun getPartiallyIncludeStartId(size: Int, standard: Int): List<DataBasketProduct> {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor =
                db.rawQuery(
                    GET_PARTIALLY_INCLUDE_START_ID_QUERY,
                    arrayOf(standard.toString(), size.toString())
                )
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products
    }

    @SuppressLint("Range")
    override fun getPartiallyNotIncludeStartId(size: Int, standard: Int): List<DataBasketProduct> {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor =
                db.rawQuery(
                    GET_PARTIALLY_NOT_INCLUDE_START_ID_QUERY,
                    arrayOf(standard.toString(), size.toString())
                )
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products
    }

    @SuppressLint("Range")
    override fun getPreviousPartiallyIncludeStartId(
        size: Int,
        standard: Int
    ): List<DataBasketProduct> {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor =
                db.rawQuery(
                    GET_PREVIOUS_PARTIALLY_INCLUDE_START_ID_QUERY,
                    arrayOf(standard.toString(), size.toString())
                )
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products.sortedBy { it.id }
    }

    @SuppressLint("Range")
    override fun getPreviousPartiallyNotIncludeStartId(
        size: Int,
        standard: Int
    ): List<DataBasketProduct> {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor =
                db.rawQuery(
                    GET_PREVIOUS_PARTIALLY_NOT_INCLUDE_START_ID_QUERY,
                    arrayOf(standard.toString(), size.toString())
                )
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products.sortedBy { it.id }
    }

    @SuppressLint("Range")
    override fun getAll(): List<DataBasketProduct> {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor =
                db.rawQuery(GET_ALL, arrayOf())
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products
    }

    @SuppressLint("Range")
    override fun getByProductId(productId: Int): DataBasketProduct {
        val products = mutableListOf<DataBasketProduct>()
        database.writableDatabase.use { db ->
            val cursor = db.rawQuery(GET_ITEM_BY_PRODUCT_ID, arrayOf(productId.toString()))
            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                val count: DataCount = DataCount(cursor.getColumnIndex(BasketContract.BASKET_COUNT))
                val productId: Int =
                    cursor.getInt(cursor.getColumnIndex("${ProductContract.TABLE_NAME}${BaseColumns._ID}"))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_NAME))
                val price: DataPrice =
                    DataPrice(cursor.getInt(cursor.getColumnIndex(ProductContract.COLUMN_PRICE)))
                val imageUrl: String =
                    cursor.getString(cursor.getColumnIndex(ProductContract.COLUMN_IMAGE_URL))
                products.add(
                    DataBasketProduct(
                        id,
                        count,
                        DataProduct(productId, name, price, imageUrl)
                    )
                )
            }
            cursor.close()
        }
        return products.first()
    }

    override fun add(basketProduct: DataBasketProduct) {
        val whereClause = "${ProductContract.TABLE_NAME}${BaseColumns._ID} = ?"
        val whereArgs = arrayOf(basketProduct.product.id.toString())

        val contentValues = ContentValues().apply {
            put("${ProductContract.TABLE_NAME}${BaseColumns._ID}", basketProduct.product.id)
            put(BasketContract.BASKET_COUNT, basketProduct.count.value)
        }

        database.writableDatabase.use { db ->
            val count =
                DatabaseUtils.queryNumEntries(
                    db,
                    BasketContract.TABLE_NAME,
                    whereClause,
                    whereArgs
                )
            if (count > 0) {
                db.execSQL(
                    UPDATE_BASKET_COUNT,
                    arrayOf(
                        basketProduct.count.value.toString(),
                        basketProduct.product.id.toString()
                    )
                )
            } else {
                db.insert(BasketContract.TABLE_NAME, null, contentValues)
            }
        }
    }

    override fun remove(basketProduct: DataBasketProduct) {
        database.writableDatabase.use { db ->
            db.delete(
                BasketContract.TABLE_NAME,
                "${BaseColumns._ID} = ?",
                arrayOf(basketProduct.id.toString())
            )
        }
    }

    companion object {
        private val GET_PARTIALLY_INCLUDE_START_ID_QUERY = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
            WHERE ${BasketContract.TABLE_NAME}.${BaseColumns._ID} >= ?
            ORDER BY ${BasketContract.TABLE_NAME}.${BaseColumns._ID} LIMIT ?        
        """.trimIndent()

        private val GET_PARTIALLY_NOT_INCLUDE_START_ID_QUERY = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
            WHERE ${BasketContract.TABLE_NAME}.${BaseColumns._ID} > ?
            ORDER BY ${BasketContract.TABLE_NAME}.${BaseColumns._ID} LIMIT ?        
        """.trimIndent()

        private val GET_PREVIOUS_PARTIALLY_INCLUDE_START_ID_QUERY = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
            WHERE ${BasketContract.TABLE_NAME}.${BaseColumns._ID} <= ?
            ORDER BY ${BasketContract.TABLE_NAME}.${BaseColumns._ID} DESC LIMIT ?        
        """.trimIndent()

        private val GET_PREVIOUS_PARTIALLY_NOT_INCLUDE_START_ID_QUERY = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
            WHERE ${BasketContract.TABLE_NAME}.${BaseColumns._ID} < ?
            ORDER BY ${BasketContract.TABLE_NAME}.${BaseColumns._ID} DESC LIMIT ?        
        """.trimIndent()

        private val UPDATE_BASKET_COUNT = """
            UPDATE ${BasketContract.TABLE_NAME}
            SET ${BasketContract.BASKET_COUNT} = ${BasketContract.BASKET_COUNT} + ?
            WHERE ${ProductContract.TABLE_NAME}${BaseColumns._ID} = ?
        """.trimIndent()

        private val GET_ALL = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
        """.trimIndent()

        private val GET_ITEM_BY_PRODUCT_ID = """
            SELECT ${BasketContract.TABLE_NAME}.*, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_NAME}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_PRICE}, ${ProductContract.TABLE_NAME}.${ProductContract.COLUMN_IMAGE_URL}
            FROM ${BasketContract.TABLE_NAME}
            INNER JOIN ${ProductContract.TABLE_NAME} ON ${BasketContract.TABLE_NAME}.${ProductContract.TABLE_NAME}${BaseColumns._ID} = ${ProductContract.TABLE_NAME}.${BaseColumns._ID}
            WHERE ${ProductContract.TABLE_NAME}.${BaseColumns._ID} = ?
        """.trimIndent()
    }
}
