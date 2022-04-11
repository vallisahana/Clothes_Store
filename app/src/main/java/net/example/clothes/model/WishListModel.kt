package net.example.clothes.model

class WishListModel {
    /**
     * name : Blue Shirt
     * image : https://firebasestorage.googleapis.com/v0/b/techtest-1f1da.appspot.com/o/blue.jpg?alt=media&token=5be5eadd-c006-4bb9-83af-d6afc496475a
     * price : 7.99
     * stock : 3
     * category : Tops
     * oldPrice : 8.99
     * productId : 1
     */
    var products: List<ProductsBean>? = null

    class ProductsBean {
        var name: String? = null
        var image: String? = null
        var price = 0.0
        var stock = 0
        var category: String? = null
        var oldPrice = 0.0
        var productId: String? = null
    }
}