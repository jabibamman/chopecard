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
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.UpdateProductDTO
import com.chopecard.data.storage.UserPreferences
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.presentation.viewModel.SellerViewModel
import com.chopecard.ui.activity.BaseActivity
import com.chopecard.ui.utils.showAlert
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

        findViewById<Button>(R.id.btnUpdateProduct).setOnClickListener {
            onEditProduct(store?.products?: throw IllegalStateException("No products in store"))
        }

        findViewById<Button>(R.id.btnReserveProduct).setOnClickListener {
            onReserveProduct()
        }

        findViewById<Button>(R.id.btnDeleteProduct).setOnClickListener {
            onDeleteProduct()
        }

        viewModel.alertMessage.observe(this) { message ->
            message?.let {
                showAlert(it, this)
                viewModel.alertMessage.value = null
            }
        }
    }

    private fun onAddProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null)
        val productIdEditText = dialogView.findViewById<EditText>(R.id.etProductId)
        val quantityEditText = dialogView.findViewById<EditText>(R.id.etQuantity)
        val priceEditText = dialogView.findViewById<EditText>(R.id.etPrice)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add New Product")
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val productIdText = productIdEditText.text.toString()
                val quantityText = quantityEditText.text.toString()
                val priceText = priceEditText.text.toString()

                if (productIdText.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                    showAlert("Please fill in all fields", this)
                } else {
                    val productId = productIdText.toInt()
                    val quantity = quantityText.toInt()
                    val price = priceText.toFloat()

                    val productStoreDTO = ProductStoreDTO(quantity, price)
                    viewModel.addProduct(getStoreNotNul().id, productId, productStoreDTO)
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    private fun onDeleteProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_delete_product, null)
        val productIdEditText = dialogView.findViewById<EditText>(R.id.etProductId)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Delete Product")
            .setPositiveButton("Delete", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()

            dialog.setOnShowListener {
                val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                button.setOnClickListener {
                    val productIdText = productIdEditText.text.toString()
                    if (productIdText.isEmpty()) {
                        showAlert("Please enter a product ID", this)
                    } else {
                        val productId = productIdText.toInt()

                        viewModel.deleteProduct(
                            DeleteProductDTO(
                                getStoreNotNul().id,
                                productId
                            )
                        )
                        dialog.dismiss()
                    }
                }
            }

            dialog.show()
    }

    private fun onEditProduct(productStore: List<ProductStore>) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, null)
        val quantityEditText = dialogView.findViewById<EditText>(R.id.etQuantity)
        val priceEditText = dialogView.findViewById<EditText>(R.id.etPrice)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Edit Product")
            .setPositiveButton("Edit", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()


        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val quantityText = quantityEditText.text.toString()
                val priceText = priceEditText.text.toString()

                if (quantityText.isEmpty() || priceText.isEmpty()) {
                    showAlert("Please fill in all fields", this)
                } else {
                    val quantity = quantityText.toInt()
                    val price = priceText.toFloat()

                    val productStoreDTO = ProductStoreDTO(quantity, price)
                    viewModel.updateProduct(
                       UpdateProductDTO(
                           getStoreNotNul().id,
                           productStore[0].id,
                           productStoreDTO
                       )
                    )
                    dialog.dismiss()
                }
            }
        }

        dialog.show()

    }

    private fun onReserveProduct() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_reserve_product, null)
        val productIdEditText = dialogView.findViewById<EditText>(R.id.etProductId)
        val quantityEditText = dialogView.findViewById<EditText>(R.id.etQuantity)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Reserve Product")
            .setPositiveButton("Reserve", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val productIdText = productIdEditText.text.toString()
                val quantityText = quantityEditText.text.toString()

                if (productIdText.isEmpty() || quantityText.isEmpty()) {
                    showAlert("Please fill in all fields", this)
                } else {
                    val productId = productIdText.toInt()
                    val quantity = quantityText.toInt()

                    val reserveDTO = ReserveDTO(productId, quantity)

                    viewModel.reserveProduct(
                        getStoreNotNul().id,
                        UserPreferences.getUserLogin(this).second,
                        reserveDTO
                    )
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }




    private fun getStoreNotNul(): Store {
        return store ?: throw IllegalStateException("Store is null")
    }

}
