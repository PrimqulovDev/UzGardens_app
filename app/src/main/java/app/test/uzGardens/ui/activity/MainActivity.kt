package app.test.uzGardens.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import app.test.uzGardens.R
import app.test.uzGardens.base.BaseActivity
import app.test.uzGardens.network.model.CategoryResource
import app.test.uzGardens.network.model.UIResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainActivityVM by viewModels()
    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        rvCategories.apply {
            layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            adapter = categoriesAdapter
        }
        appCache.apply {
            token = getString(R.string.fakeToken)
            tokenType = getString(R.string.defaultTokenType)
        }
        viewModel.categoriesLiveData.observe(this, categoriesObserver)

    }

    private val categoriesObserver: Observer<CategoryResource> = Observer { resources ->
        isLoading = resources is UIResource.Loading
        if (resources is UIResource.Resource) {
            categoriesAdapter.loadCategories(resources.data)
        }
    }


}