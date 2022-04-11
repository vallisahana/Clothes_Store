package net.example.clothes.catalogue

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import net.example.clothes.R
import net.example.clothes.model.CatalogueModel
import net.example.clothes.product_details.ProductDetailsSheet
import net.example.clothes.viewmodel.AppViewModel


class CatelogueFragment: Fragment() {


    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var catalogueModelList: List<CatalogueModel.ProductsBean>
    private lateinit var customAdapter: CustomAdapter;
    private lateinit var gridView: GridView;

    //2
    companion object {
        fun newInstance(catalogueModel: List<CatalogueModel.ProductsBean>): CatelogueFragment {
            val fragment = CatelogueFragment();
            fragment.catalogueModelList = catalogueModel
            return fragment;
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.frag_catalogue, container, false)
        findView(view)
        return view;
    }

    private fun findView(view: View){
        gridView = view.findViewById(R.id.gridView)
        initData();
    }

    private fun initData(){
        catalogueModelList = arrayListOf()



        viewModel.catalogueResponse.observe(viewLifecycleOwner) { catalogue ->
            catalogueModelList = catalogue.products!!
            customAdapter = CustomAdapter(catalogueModelList, requireContext())
            gridView.adapter = customAdapter;

        }

        gridView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
           val product = catalogueModelList[position]
            showDialog(product)
        }

    }

    private fun showDialog(catalogueModel: CatalogueModel.ProductsBean){
        var bottomFragment = ProductDetailsSheet.newInstance(catalogueModel)
        bottomFragment.show(childFragmentManager, "TAG")
    }


    class CustomAdapter(var catalogueModel: List<CatalogueModel.ProductsBean>?, var context: Context) : BaseAdapter() {

        private var layoutInflater: LayoutInflater? = null


        override fun getCount(): Int {
            return catalogueModel!!.size
        }

        override fun getItem(position: Int): Any {
            return catalogueModel!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var convertView = convertView
            if (layoutInflater == null) {
                layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
            if (convertView == null) {
                convertView = layoutInflater!!.inflate(R.layout.row_catalogue, null)
            }

            val catalogueModel = catalogueModel!![position]
            val names = convertView!!.findViewById<TextView>(R.id.tvName)
            val price = convertView!!.findViewById<TextView>(R.id.tvPrice)
            val image = convertView!!.findViewById<ImageView>(R.id.ivCatalogue)

            names.text = catalogueModel.name
            price.text = "$"+catalogueModel.price
            Picasso.get().load(catalogueModel.image).into(image);

            return convertView
        }



    }


}