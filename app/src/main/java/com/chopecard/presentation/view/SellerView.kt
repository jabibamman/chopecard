package com.chopecard.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.chopecard.R
import com.chopecard.data.model.DeleteProductDTO
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.domain.models.Store
import com.chopecard.presentation.viewModel.SellerViewModel
import com.chopecard.ui.activity.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SellerView : BaseActivity() {

    private val viewModel: SellerViewModel by viewModel()
    private var store : Store? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        setupFooter()

        store = intent.getParcelableExtra<Store>("store")
        val tvShopName = findViewById<TextView>(R.id.tvShopName)

        tvShopName.text = store?.name ?: "Unknown"
        Log.d("SellerView", "Successfully received store")

        findViewById<Button>(R.id.btnGoBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnAddProduct).setOnClickListener {
            onAddProduct()
        }

        findViewById<Button>(R.id.btnDeleteProduct).setOnClickListener {
            onDeleteProduct()
        }

        viewModel.alertMessage.observe(this) { message ->
            message?.let {
                showAlert(it)
                viewModel.alertMessage.value = null
            }
        }
    }


    private fun onAddProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null)
        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setTitle("Add New Product")
            setPositiveButton("Add") { dialog, _ ->
                val productId =
                    dialogView.findViewById<EditText>(R.id.etProductId).text.toString().toInt()
                val quantity =
                    dialogView.findViewById<EditText>(R.id.etQuantity).text.toString().toInt()
                val price =
                    dialogView.findViewById<EditText>(R.id.etPrice).text.toString().toFloat()

                val productStoreDTO = ProductStoreDTO(
                    quantity,
                    price
                )
                viewModel.addProduct(getStoreNotNul().id, productId, productStoreDTO)
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        }.create().show()
    }

    private fun onDeleteProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_delete_product, null)
        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setTitle("Add New Product")
            setPositiveButton("Add") { dialog, _ ->
                val productId =
                    dialogView.findViewById<EditText>(R.id.etProductId).text.toString().toInt()


                viewModel.deleteProduct(
                    DeleteProductDTO(
                        getStoreNotNul().id,
                        productId
                    )
                )

                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        }.create().show()
    }

    private fun getStoreNotNul(): Store {
        return store ?: throw IllegalStateException("Store is null")
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this).apply {
            setMessage(message)
            setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        }.show()
    }
}
