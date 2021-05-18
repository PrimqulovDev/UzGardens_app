package app.test.uzGardens.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import app.test.uzGardens.base.BaseViewModel
import app.test.uzGardens.network.model.CategoryResource
import app.test.uzGardens.network.model.UIResource
import app.test.uzGardens.usecases.category.CategoriesUseCase
import app.test.uzGardens.utils.cache.AppCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase,
    private val appCache: AppCache,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val mCategoriesLiveData: MutableLiveData<CategoryResource> = MutableLiveData()
    val categoriesLiveData: LiveData<CategoryResource> get() = mCategoriesLiveData

    init {
        loadCategories()
    }

    fun loadCategories() {
        launch {
            mCategoriesLiveData.apply {
                value = UIResource.Loading
                value = categoriesUseCase()
            }
        }
    }



}

