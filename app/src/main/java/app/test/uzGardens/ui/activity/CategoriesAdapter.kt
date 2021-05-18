package app.test.uzGardens.ui.activity

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.test.uzGardens.R
import app.test.uzGardens.network.model.CategoryDTO
import app.test.uzGardens.utils.Const.horizontal
import app.test.uzGardens.utils.Const.vertical
import app.test.uzGardens.utils.extensions.inflate
import app.test.uzGardens.utils.extensions.loadAny
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import kotlinx.android.extensions.LayoutContainer

class CategoriesAdapter : RecyclerView.Adapter<BaseVH>() {

    private val items: ArrayList<CategoryDTO> = arrayListOf()

    override fun getItemViewType(position: Int) =
        when (items[position].shape) {
            horizontal -> horizontalLayout
            vertical -> verticalLayout
            else -> squareLayout
        }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseVH, position: Int) = holder.onBind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val view = parent.inflate(viewType)
        return when (viewType) {
            verticalLayout -> VerticalVH(view)
            horizontalLayout -> HorizontalVH(view)
            else -> VerticalVH(view)
        }
    }

    inner class VerticalVH(override val containerView: View) : BaseVH(containerView) {
        private val tvCategoryName: AppCompatTextView = containerView.findViewById(R.id.categoryName)
        private val categoryImage: AppCompatImageView = containerView.findViewById(R.id.categoryImage)

        override fun onBind() {
            val d = items[layoutPosition]
            tvCategoryName.text = d.name

            categoryImage.loadAny("http://garden.inone.uz/api/${d.image}")
        }
    }

    inner class HorizontalVH(override val containerView: View) : BaseVH(containerView) {

        private val tvCategoryName: AppCompatTextView = containerView.findViewById(R.id.categoryName)
        private val categoryImage: AppCompatImageView = containerView.findViewById(R.id.categoryImage)

        override fun onBind() {
            val d = items[layoutPosition]
            tvCategoryName.text = d.name
            categoryImage.loadAny("http://garden.inone.uz/api/${d.image}")

            val layoutParams =  itemView.layoutParams as (StaggeredGridLayoutManager.LayoutParams)
            layoutParams.isFullSpan = true;


        }
    }

    inner class SquareVH(override val containerView: View) : BaseVH(containerView) {

        override fun onBind() {

        }
    }

    fun loadCategories(categories: List<CategoryDTO>) {
        items.apply {
            clear()
            addAll(categories)
            notifyDataSetChanged()
        }
    }


}

abstract class BaseVH(
    containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun onBind()
}

const val verticalLayout = R.layout.item_category_vertical
const val horizontalLayout = R.layout.item_category_horizantal
const val squareLayout = R.layout.item_category_square


