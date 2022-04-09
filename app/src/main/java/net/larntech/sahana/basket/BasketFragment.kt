package net.larntech.sahana.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.larntech.sahana.R
import net.larntech.sahana.model.BasketModel
import net.larntech.sahana.model.BasketRecyclerItemTouchHelper
import net.larntech.sahana.model.CatalogueModel
import net.larntech.sahana.model.WishListModel
import net.larntech.sahana.viewmodel.AppViewModel
import net.larntech.sahana.wishlist.RecyclerItemTouchHelper
import net.larntech.sahana.wishlist.WishListAdapter
import java.math.BigDecimal

class BasketFragment: Fragment(), BucketListAdapter.clickedListener, BasketRecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var basketList: MutableList<CatalogueModel.ProductsBean>

    private lateinit var noData: TextView
    private lateinit var totalPrice: TextView
    private lateinit var llCheckOut: CardView
    private lateinit var btnCheckOut: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var bucketListAdapter: BucketListAdapter

    //2
    companion object {
        fun newInstance(basketList: MutableList<CatalogueModel.ProductsBean>): BasketFragment {
            val fragment = BasketFragment();
            fragment.basketList = basketList
            return fragment;
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.frag_basket, container, false)
        recyclerView = view.findViewById(R.id.rvRecyclerV)
        totalPrice = view.findViewById(R.id.totalPrice)
        llCheckOut = view.findViewById(R.id.llCheckOut)
        noData = view.findViewById(R.id.noData)
        btnCheckOut = view.findViewById(R.id.btnCheckOut)

        initData()
        clickListener()
        return view;
    }

    private fun clickListener(){
        btnCheckOut.setOnClickListener {
            basketList.clear()
            viewModel.checkOut()
            bucketListAdapter.notifyDataSetChanged()
            checkViews()
        }
    }

    private fun initData(){
        bucketListAdapter = BucketListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = bucketListAdapter
        bucketListAdapter.setData(basketList)

        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            BasketRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

        checkViews()
    }

    override fun Clicked(product: CatalogueModel.ProductsBean) {

        basketList.remove(product)
        viewModel.removeToBucket(product)
        bucketListAdapter.notifyDataSetChanged()
        checkViews()
    }


    private fun checkViews(){
        if(basketList.size > 0){
            noData.visibility = View.GONE
            llCheckOut.visibility = View.VISIBLE
            setCartPrice()
        }else{
            noData.visibility = View.VISIBLE
            llCheckOut.visibility = View.GONE


        }
    }


    private fun setCartPrice(){
        var total: Double = 0.0
        for(catelog in basketList){
            total += catelog.price
        }

        totalPrice.text = "$$total"
    }



    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        val position = viewHolder!!.adapterPosition
        viewModel.removeToBucket(basketList[position])
        basketList.removeAt(position)
        bucketListAdapter.notifyDataSetChanged()
        checkViews()
    }


}