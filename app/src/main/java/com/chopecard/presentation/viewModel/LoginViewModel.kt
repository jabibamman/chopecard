package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.UserDTO
import com.chopecard.domain.models.User
import com.chopecard.domain.usecases.CreateUserUseCase
import com.chopecard.domain.usecases.GetUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val userCreationResult = MutableLiveData<UserCreationResult>()
    val userData = MutableLiveData<User>()

    fun createUser(user: UserDTO) {
        viewModelScope.launch {
            try {
                val user = createUserUseCase.execute(user)
                userCreationResult.value = UserCreationResult.Success(user)
                Log.d("LoginViewModel", "User created: $user")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error creating user", e)
                userCreationResult.value = UserCreationResult.Failure(e)
            }
        }
    }

    fun getUser(userId: Int, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase.execute(userId)
                onResult(user)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
}

sealed class UserCreationResult {
    data class Success(val user: User) : UserCreationResult()
    data class Failure(val error: Exception) : UserCreationResult()
}

    // TODO: other actions (updateProduct, deleteProduct...)
