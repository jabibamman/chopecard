package com.chopecard

import CollectorViewModel
import YourCardListAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.data.model.Card
import com.chopecard.data.model.CardImage
import com.chopecard.data.model.CardPrice
import com.chopecard.data.model.CardSet
import com.chopecard.data.storage.UserPreferences
import com.chopecard.databinding.ActivityMainBinding
import com.chopecard.ui.activity.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val cardList: List<Card> = listOf(
        Card(
            0,
            "Dragon Blanc aux yeux bleu",
            "spell",
            "",
            "bien craqué", 0,0,0,"", "",
            List<CardSet>(0) { CardSet("","","","","")},
            List<CardImage>(0) { CardImage(0,"","","")},
            List<CardPrice>(0) { CardPrice("","","","","")}
        ),
        Card(
            0,
            "Fusion",
            "spell",
            "",
            "bien craqué", 0,0,0,"", "",
            List<CardSet>(0) { CardSet("","","","","")},
            List<CardImage>(0) { CardImage(0,"","","")},
            List<CardPrice>(0) { CardPrice("","","","","")}
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFooter()

        binding.tvWelcome.text = getString(R.string.welcome, UserPreferences.getUserName(this))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = YourCardListAdapter(cardList, this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        recyclerView.adapter = adapter
    }
}
