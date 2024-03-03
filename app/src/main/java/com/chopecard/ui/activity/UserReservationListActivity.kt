package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import com.chopecard.R
import com.chopecard.data.storage.UserPreferences
import com.chopecard.domain.models.UserReservation
import com.chopecard.domain.usecases.GetUserReservationsUseCase
import com.chopecard.presentation.fragment.UserReservationListFragment
import org.koin.android.ext.android.inject

/** Represents the UI state. */

class UserReservationListActivity : BaseActivity() {
    private val getUserReservationsUseCase: GetUserReservationsUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations_list)
        setupFooter()
        setupListener()
        if (savedInstanceState == null) {
            lifecycleScope.launch {
                val reservations = getReservations()

                val bundle = Bundle().apply {
                    putParcelableArrayList("reservations", ArrayList(reservations))
                }

                val fragment = UserReservationListFragment().apply {
                    arguments = bundle
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_user_reservation_list, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }


    private fun setupListener() {
        findViewById<ImageButton>(R.id.btnLogout)?.setOnClickListener {
            Log.d("UserReservationListActivity", "Logout button clicked")
            UserPreferences.clearAllPreference(this)
            val intent = Intent(this, LoginRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private suspend fun getReservations(): List<UserReservation> {
            var reservations = emptyList<UserReservation>()
            try {
                val (userRole,userId,userName) = UserPreferences.getUserLogin(this)
                Log.d("UserReservations","$userId")
                reservations  = getUserReservationsUseCase.execute(userId)
                Log.d("UserReservations", "Stores: $reservations")
            } catch (e: Exception) {
                Log.d("dsfsfdsdffds","$e")
                Log.d("UserReservations", "ERROR: RESERVATION")
            }

        return reservations

    }
}
