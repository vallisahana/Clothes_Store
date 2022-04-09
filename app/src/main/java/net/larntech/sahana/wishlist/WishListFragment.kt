package net.larntech.sahana.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.larntech.sahana.R
import net.larntech.sahana.model.CatalogueModel
import net.larntech.sahana.viewmodel.AppViewModel


class WishListFragment: Fragment(), WishListAdapter.clickedListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var noData: TextView
    private lateinit var wishListAdapter: WishListAdapter

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var wishListModel: MutableList<CatalogueModel.ProductsBean>

    //2
    companion object {
        fun newInstance(wishListModel: MutableList<CatalogueModel.ProductsBean>): WishListFragment {
            val fragment = WishListFragment();
            fragment.wishListModel = wishListModel
            return fragment;
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.frag_wishlist, container, false)
        recyclerView = view.findViewById(R.id.rvRecyclerV)
        noData = view.findViewById(R.id.noData)
        initData()
        return view;
    }

    private fun initData(){
        wishListAdapter = WishListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = wishListAdapter
        wishListAdapter.setData(wishListModel)
        checkViews()
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

    }

    private fun checkViews(){
        if(wishListModel.size > 0){
            noData.visibility = View.GONE
        }else{
            noData.visibility = View.VISIBLE

        }
    }





    override fun Clicked(story: CatalogueModel.ProductsBean) {

    }

    override fun addBucketClicked(product: CatalogueModel.ProductsBean) {

        wishListModel.remove(product)
        viewModel.addToBucket(product)
        viewModel.removeToWishList(product)
        wishListAdapter.notifyDataSetChanged()
        checkViews()

    }



    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        val position = viewHolder!!.adapterPosition
        viewModel.removeToWishList(wishListModel[position])
        wishListModel.removeAt(position)
        wishListAdapter.notifyDataSetChanged()
        checkViews()
    }


}