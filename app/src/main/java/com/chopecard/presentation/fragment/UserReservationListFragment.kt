package com.chopecard.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.UserReservation
import com.chopecard.ui.adapter.UserReservationListAdapter

class UserReservationListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_reservation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvUserReservationList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Récupérer et afficher les réservations
        arguments?.getParcelableArrayList<UserReservation>("reservations")?.let { reservations ->
            updateRecyclerView(reservations)
        }
    }

    private fun updateRecyclerView(reservations: List<UserReservation>) {
        recyclerView.adapter = UserReservationListAdapter(reservations) { userReservation ->
            // Implémentez la logique de clic sur l'élément ici si nécessaire
        }
    }
}