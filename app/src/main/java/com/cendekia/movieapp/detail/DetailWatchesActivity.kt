package com.cendekia.movieapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.cendekia.movieapp.R
import com.cendekia.movieapp.core.domain.model.Watches
import com.cendekia.movieapp.databinding.ActivityDetailWatchesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailWatchesActivity : AppCompatActivity() {



    private val detailWatchesViewModel: DetailWatchesViewModel by viewModel()
    private lateinit var binding: ActivityDetailWatchesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Watches>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailWatches: Watches?) {
        val links = "https://image.tmdb.org/t/p/w500"

        detailWatches?.let {

            val poster = "$links/${it.poster_path}"
            supportActionBar?.title = detailWatches.title

            binding.content.tvDetailDescription.text = detailWatches.overview
            Glide.with(this@DetailWatchesActivity)
                .load(poster)
                .into(binding.ivDetailImage)

            var statusFavorite = detailWatches.isFavorite
            setStatusFavorites(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailWatchesViewModel.setFavoritesWatches(detailWatches, statusFavorite)
                setStatusFavorites(statusFavorite)
            }
        }
    }

    private fun setStatusFavorites(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            )
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white)
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
