package com.skillbox.homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.skillbox.homework.App
import com.skillbox.homework.R
import com.skillbox.homework.databinding.FragmentFirstBinding
import com.skillbox.homework.entity.MarsPhotos
import com.skillbox.homework.presentation.recycler.MarsPhotosListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by viewModels { mainViewModelFactory }

    private lateinit var binding: FragmentFirstBinding

    private val listAdapter = MarsPhotosListAdapter { onItemClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = listAdapter

        binding.swipe.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.photos.onEach {
            listAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach {
            binding.swipe.isRefreshing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onItemClick(item: MarsPhotos) {
        val bundle = Bundle()
        bundle.putString("ImageKey", item.img_src)
        bundle.putParcelable("ParcelableKey", item)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}