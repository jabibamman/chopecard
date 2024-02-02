package com.chopecard.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.R
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.presentation.viewModel.SellerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SellerView : AppCompatActivity() {

    private val viewModel: SellerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)

        // Example of button click listener that calls ViewModel to add product
        findViewById<Button>(R.id.btnAddProduct).setOnClickListener {
            // Example data, replace with actual input from user interface
            // open input dialog to get storeId, productId, and productStoreDTO
            onAddProduct()

            viewModel.addProduct(1, 1, ProductStoreDTO(1, 1f))
        }

        // Setup observers and other UI components here
    }


    private fun onAddProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null)
        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setTitle("Add New Product")
            setPositiveButton("Add") { dialog, _ ->
                val storeId =
                    dialogView.findViewById<EditText>(R.id.etStoreId).text.toString().toInt()
                val productId =
                    dialogView.findViewById<EditText>(R.id.etProductId).text.toString().toInt()
                val quantity =
                    dialogView.findViewById<EditText>(R.id.etQuantity).text.toString().toInt()
                val price =
                    dialogView.findViewById<EditText>(R.id.etPrice).text.toString().toFloat()

                val productStoreDTO = ProductStoreDTO(
                    quantity,
                    price
                ) // Assurez-vous que cela correspond à la structure de votre ProductStoreDTO
                viewModel.addProduct(storeId, productId, productStoreDTO)
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        }.create().show()
    }
}