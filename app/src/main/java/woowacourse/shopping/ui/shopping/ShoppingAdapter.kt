package woowacourse.shopping.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.shopping.databinding.ItemRecentProductsBinding
import woowacourse.shopping.databinding.ItemShoppingBinding
import woowacourse.shopping.ui.model.UiProduct
import woowacourse.shopping.ui.shopping.ShoppingViewHolderType.PRODUCT
import woowacourse.shopping.ui.shopping.ShoppingViewHolderType.RECENT_PRODUCTS
import woowacourse.shopping.ui.shopping.recentproduct.RecentProductAdapter
import woowacourse.shopping.ui.shopping.recentproduct.RecentProductsViewHolder

class ShoppingAdapter(
    private val onItemClick: (UiProduct) -> Unit
) : ListAdapter<UiProduct, RecyclerView.ViewHolder>(productDiffUtil) {

    lateinit var layoutInflater: LayoutInflater
    private val recentProductAdapter: RecentProductAdapter = RecentProductAdapter(onItemClick)

    fun updateRecentProduct(recentProducts: List<UiProduct>) {
        recentProductAdapter.submitList(recentProducts)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) RECENT_PRODUCTS.value
        else PRODUCT.value
    }

    override fun getItemCount(): Int {
        if (recentProductAdapter.itemCount != 0) return currentList.size + 1
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::layoutInflater.isInitialized) layoutInflater = LayoutInflater.from(parent.context)
        return when (ShoppingViewHolderType.getName(viewType)) {
            RECENT_PRODUCTS -> {
                RecentProductsViewHolder(
                    ItemRecentProductsBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    ),
                    recentProductAdapter
                )
            }
            PRODUCT -> {
                ShoppingViewHolder(
                    ItemShoppingBinding.inflate(layoutInflater, parent, false),
                    onItemClick
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecentProductsViewHolder -> holder.bind(recentProductAdapter.itemCount != 0)
            is ShoppingViewHolder -> holder.bind(getItem(position - 1))
        }
    }

    companion object {
        private val productDiffUtil = object : DiffUtil.ItemCallback<UiProduct>() {
            override fun areItemsTheSame(oldItem: UiProduct, newItem: UiProduct): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: UiProduct, newItem: UiProduct): Boolean =
                oldItem == newItem
        }
    }
}
