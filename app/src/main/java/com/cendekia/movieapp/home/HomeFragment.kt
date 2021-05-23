package com.cendekia.movieapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cendekia.movieapp.R
import com.cendekia.movieapp.core.data.Resource
import com.cendekia.movieapp.core.ui.WatchesAdapter
import com.cendekia.movieapp.databinding.FragmentHomeBinding
import com.cendekia.movieapp.detail.DetailWatchesActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val watchesAdapter = WatchesAdapter()
            watchesAdapter.onItemClick = { selectedData ->

                val intent = Intent(activity, DetailWatchesActivity::class.java)
                intent.putExtra(DetailWatchesActivity.EXTRA_DATA, selectedData)
                startActivity(intent)

            }

            homeViewModel.watchess.observe(viewLifecycleOwner, Observer { watches ->

                if (watches != null) {
                    when (watches) {

                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            watchesAdapter.setData(watches.data)

                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                watches.message ?: getString(R.string.something_wrong)

                        }
                    }
                }
            })

            with(binding.rvPoster) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = watchesAdapter
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
