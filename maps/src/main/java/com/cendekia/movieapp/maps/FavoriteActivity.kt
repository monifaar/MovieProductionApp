package com.cendekia.movieapp.maps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cendekia.movieapp.core.ui.WatchesAdapter
import com.cendekia.movieapp.detail.DetailWatchesActivity
import com.cendekia.movieapp.maps.databinding.ActivityFavoriteBinding
import com.cendekia.movieapp.maps.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteWatchesViewModel: FavoriteWatchesViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        supportActionBar?.title = "Watches Maps"

        getDataWatches()
    }

    private fun getDataWatches() {

        val watchesAdapter = WatchesAdapter()
        watchesAdapter.onItemClick = { selectedDataWatches ->
            val intents = Intent(this, DetailWatchesActivity::class.java)
            intents.putExtra(DetailWatchesActivity.EXTRA_DATA, selectedDataWatches)
            startActivity(intents)
        }

        favoriteWatchesViewModel.favoriteWatches.observe(this, Observer{ dataWatches ->
            watchesAdapter.setData(dataWatches)
            binding.viewEmpty.root.visibility =
                if (dataWatches.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = watchesAdapter
        }
    }
}