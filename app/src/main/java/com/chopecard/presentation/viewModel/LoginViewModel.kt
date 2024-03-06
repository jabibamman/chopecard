package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.LoginUserDTO
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

    fun createUser(userDTO: UserDTO) {
        viewModelScope.launch {
            try {
                val createdUser = createUserUseCase.execute(userDTO)
                userCreationResult.value = UserCreationResult.Success(createdUser)
                Log.d("LoginViewModel", "User created: $createdUser")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error creating user", e)
                userCreationResult.value = UserCreationResult.Failure(e)
            }
        }
    }

    fun getUser(userId: Int, ignoredOnResult: (User?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase.execute(userId)
                ignoredOnResult(user)
            } catch (e: Exception) {
                ignoredOnResult(null)
            }
        }
    }

    fun loginUser(loginUserDTO: LoginUserDTO) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase.execute(loginUserDTO)
                userCreationResult.value = UserCreationResult.Success(user)
                Log.d("LoginViewModel", "User logged: $user")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error logging user", e)
                userCreationResult.value = UserCreationResult.Failure(e)
            }
        }
    }
}

sealed class UserCreationResult {
    data class Success(val user: User) : UserCreationResult()
    data class Failure(val error: Exception) : UserCreationResult()
}

    // TODO: other actions (updateProduct, deleteProduct...)
