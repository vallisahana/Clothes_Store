package net.example.clothes.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import net.example.clothes.R
import net.example.clothes.model.CatalogueModel
import net.example.clothes.viewmodel.AppViewModel

class ProductDetailsSheet(): BottomSheetDialogFragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var ivClose: ImageView
    private lateinit var ivCatalogue: ImageView
    private lateinit var tvIsPrice: TextView
    private lateinit var tvWasPrice: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvName: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvStock: TextView
    private lateinit var btnWishList: Button
    private lateinit var btnAddToCart: Button
    private lateinit var catalogueModel: CatalogueModel.ProductsBean


    //2
    companion object {
        fun newInstance(catalogueModel: CatalogueModel.ProductsBean): ProductDetailsSheet {
            val bottomSheet = ProductDetailsSheet();
            bottomSheet.catalogueModel = catalogueModel
            return bottomSheet;
        }
    }

    override fun onStart() {
        super.onStart()
        BottomSheetBehavior.from(requireView().parent as View).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_details, container, false);
        initData(view)
        return view;
    }

    private fun initData(view: View){
        ivClose = view.findViewById(R.id.ivClose)
        ivCatalogue = view.findViewById(R.id.ivCatalogue)
        tvIsPrice = view.findViewById(R.id.tvIsPrice)
        tvWasPrice = view.findViewById(R.id.tvWasPrice)
        tvStatus = view.findViewById(R.id.tvStatus)
        tvName = view.findViewById(R.id.tvName)
        tvCategory = view.findViewById(R.id.tvCategory)
        tvStock = view.findViewById(R.id.tvStock)
        btnWishList = view.findViewById(R.id.btnWishList)
        btnAddToCart = view.findViewById(R.id.btnAddToCart)
        clickListener()
        setData()
    }


    private fun clickListener(){

        ivClose.setOnClickListener{
            dismiss()
        }

        btnWishList.setOnClickListener {
            viewModel.addToWishList(catalogueModel)
            dismiss()
        }

        btnAddToCart.setOnClickListener {
            viewModel.addToBucket(catalogueModel)
            dismiss()
        }

    }

    private fun setData(){
        tvIsPrice.text = catalogueModel.price.toString()
        tvWasPrice.text = catalogueModel.oldPrice.toString()
        tvStatus.text = "In Stock "
        tvName.text = catalogueModel.name
        tvCategory.text = catalogueModel.category
        tvStock.text = catalogueModel.stock.toString()
        Picasso.get().load(catalogueModel.image).into(ivCatalogue);


    }







}