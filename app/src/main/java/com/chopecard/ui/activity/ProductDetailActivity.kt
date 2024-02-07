import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chopecard.R
import com.chopecard.presentation.viewModel.LoginViewModel
import com.chopecard.presentation.viewModel.ProductDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailActivityActivity : AppCompatActivity() {

    private val productDetailViewModel : ProductDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_layout)

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        if (productId != -1) {
            productDetailViewModel.loadProductDetail(productId)
        }


    }
}