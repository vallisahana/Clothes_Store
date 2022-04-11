package net.example.clothes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.example.clothes.model.CatalogueModel
import net.example.clothes.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel: ViewModel() {

    private val mutableSelectedCatalogue = MutableLiveData<CatalogueModel>()
    val selectedCategory: LiveData<CatalogueModel> get() = mutableSelectedCatalogue


    private val mutableSearchedCatalogue = MutableLiveData<CatalogueModel>()
    val catalogueResponse: LiveData<CatalogueModel> get() =mutableSearchedCatalogue;

    private val mutableWishList = MutableLiveData<List<CatalogueModel.ProductsBean>>()
    val wishList: LiveData<List<CatalogueModel.ProductsBean>> get() =mutableWishList;

    private val mutableBucketList = MutableLiveData<List<CatalogueModel.ProductsBean>>()
    val bucketList: LiveData<List<CatalogueModel.ProductsBean>> get() =mutableBucketList;

    private val mutableAddCatalogueAddWishList = MutableLiveData<CatalogueModel.ProductsBean>()
    val addCatalogueAddWishList: LiveData<CatalogueModel.ProductsBean> get() =mutableAddCatalogueAddWishList;

    private val mutableAddCatalogueAddBucketList = MutableLiveData<CatalogueModel.ProductsBean>()
    val addCatalogueBucketList: LiveData<CatalogueModel.ProductsBean> get() =mutableAddCatalogueAddBucketList;

    private val mutableRemoveCatalogueAddWishList = MutableLiveData<CatalogueModel.ProductsBean>()
    val removeCatalogueAddWishList: LiveData<CatalogueModel.ProductsBean> get() =mutableRemoveCatalogueAddWishList;

    private val mutableDeleteCatalogueAddWishList = MutableLiveData<CatalogueModel.ProductsBean>()
    val deleteCatalogueAddWishList: LiveData<CatalogueModel.ProductsBean> get() =mutableDeleteCatalogueAddWishList;

    private val mutableRemoveCatalogueAddBucketList = MutableLiveData<CatalogueModel.ProductsBean>()
    val removeCatalogueBucketList: LiveData<CatalogueModel.ProductsBean> get() =mutableRemoveCatalogueAddBucketList;

    private val catalogueResponseMessage = MutableLiveData<String>()
    val searchCatalogueMessage: LiveData<String> get() = catalogueResponseMessage;

    private val catalogueCheckOut = MutableLiveData<Boolean>()
    val checkOutCatalogue: LiveData<Boolean> get() = catalogueCheckOut;


    fun getAllCatalogue() {
        val catalogueModelList: Call<CatalogueModel> =
            ApiClient.getApiService().searchCatalogue()
           catalogueModelList.enqueue(object : Callback<CatalogueModel> {
            override fun onResponse(call: Call<CatalogueModel>, response: Response<CatalogueModel>) {
                if(response.isSuccessful){
                    if(response.body() != null) {
                        mutableSearchedCatalogue.postValue(response.body())
                    }else{
                        catalogueResponseMessage.postValue("No book found")
                    }
                }else{
                    catalogueResponseMessage.postValue("No book found")
                }
            }

            override fun onFailure(call: Call<CatalogueModel>, t: Throwable) {
                catalogueResponseMessage.postValue("An error occurred while fetching books "+t.localizedMessage)
            }
        })
    }


    fun addToBucket(catalogueModel: CatalogueModel.ProductsBean){
        mutableAddCatalogueAddBucketList.postValue(catalogueModel)
    }


    fun addToWishList(catalogueModel: CatalogueModel.ProductsBean){
        mutableAddCatalogueAddWishList.postValue(catalogueModel)
    }


    fun removeToBucket(catalogueModel: CatalogueModel.ProductsBean){
        mutableRemoveCatalogueAddBucketList.postValue(catalogueModel)
    }


    fun removeToWishList(catalogueModel: CatalogueModel.ProductsBean){
        mutableRemoveCatalogueAddWishList.postValue(catalogueModel)
    }

    fun deleteToWishList(catalogueModel: CatalogueModel.ProductsBean){
        mutableDeleteCatalogueAddWishList.postValue(catalogueModel)
    }

    fun setBucketList(catalogueModel: List<CatalogueModel.ProductsBean>){
        mutableBucketList.postValue(catalogueModel)
    }

    fun setWishList(catalogueModel: List<CatalogueModel.ProductsBean>){
        mutableWishList.postValue(catalogueModel)
    }


    fun checkOut(){
        catalogueCheckOut.postValue(true)
    }

}