package net.larntech.sahana

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.larntech.sahana.basket.BasketFragment
import net.larntech.sahana.catalogue.CatelogueFragment
import net.larntech.sahana.model.BasketModel
import net.larntech.sahana.model.CatalogueModel
import net.larntech.sahana.model.WishListModel
import net.larntech.sahana.viewmodel.AppViewModel
import net.larntech.sahana.wishlist.WishListFragment


class MainActivity : AppCompatActivity() {

    private lateinit var navigationView:BottomNavigationView
    private lateinit var toolbar:Toolbar
    private lateinit var catalogueList: MutableList<CatalogueModel.ProductsBean>
    private lateinit var wishList: MutableList<CatalogueModel.ProductsBean>
    private lateinit var basketListModel: MutableList<CatalogueModel.ProductsBean>
    private val viewModel: AppViewModel by viewModels()
    private lateinit var catalogueModel: CatalogueModel;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData(){
        catalogueList = arrayListOf()
        wishList = arrayListOf()
        basketListModel = arrayListOf()
        navigationView = findViewById(R.id.activity_main_bottom_navigation_view)
        toolbar = findViewById(R.id.toolbar)
        navigationView.setOnItemSelectedListener(selectedListener)
        handleViewModel()
        seearchCatalogue();
        setToolBar()
    }

    private fun setToolBar(){
        this.setSupportActionBar(toolbar);
        this.supportActionBar!!.title = "Catalogue"
    }

    private fun seearchCatalogue(){
        navigationView.selectedItemId = R.id.miCatalogue
        viewModel.getAllCatalogue()
    }



    private fun handleViewModel(){
        viewModel.selectedCategory.observe(this) { catalogue ->
            this.catalogueModel = catalogueModel
        }

        viewModel.searchCatalogueMessage.observe(this){
            if(it != null){
//                handleProgressBar(false)
                showMessage(it)
            }
        }

        viewModel.catalogueResponse.observe(this){
            //handleProgressBar(false)
            setCatalogueResponse(it)
        }

        viewModel.addCatalogueAddWishList.observe(this){
            if(it != null) {
                wishList.add(it)
            }
        }
        viewModel.addCatalogueBucketList.observe(this){
            if(it != null) {
                basketListModel.add(it)
            }
        }

        viewModel.removeCatalogueAddWishList.observe(this){
            if(it != null){
                wishList.remove(it)
            }
        }

        viewModel.removeCatalogueBucketList.observe(this){
            if(it != null){
                removeFromBucket(it)
            }
        }


    }

    private fun removeFromBucket(catalogueModel: CatalogueModel.ProductsBean){
        basketListModel.remove(catalogueModel)
    }

    private fun setCatalogueResponse(catalogueModel: CatalogueModel){
        catalogueList.addAll(catalogueModel.products!!)
    }

    private val selectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.miCatalogue -> {
                    val categoryFragment = CatelogueFragment.newInstance(catalogueList)
                    loadFragment(categoryFragment)
                    if(this.supportActionBar != null) {
                        this.supportActionBar!!.title = "Catalogue"
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.miWishList -> {
                    val wishListFragment = WishListFragment.newInstance(wishList)
                    loadFragment(wishListFragment)
                    if(this.supportActionBar != null) {
                        this.supportActionBar!!.title = "WishList"
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.miBasket -> {
                    val basketFragment = BasketFragment.newInstance(basketListModel)
                    loadFragment(basketFragment)

                    if(this.supportActionBar != null) {
                        this.supportActionBar!!.title = "Bucket"
                    }

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    private fun loadFragment(fragment: Fragment) {

        val fragmnt: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmnt.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrag,fragment)
        fragmentTransaction.commitAllowingStateLoss()

    }


    private fun showMessage(message: String){
        Toast.makeText(this@MainActivity,message, Toast.LENGTH_LONG).show()

    }


    override fun onResume() {
        super.onResume()
    }

}