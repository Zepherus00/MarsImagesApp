package com.skillbox.homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.skillbox.homework.R
import com.skillbox.homework.databinding.FragmentSecondBinding
import com.skillbox.homework.entity.MarsPhotos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<MarsPhotos>("ParcelableKey")
        with(binding) {
            camera.text = getString(R.string.camera, data?.camera?.name.toString())
            date.text = getString(R.string.date, data?.earth_date.toString())
            sol.text = getString(R.string.sol, data?.sol.toString())
            rover.text = getString(R.string.rover, data?.rover?.name.toString())
        }
        Glide.with(binding.image.context).load(data?.img_src).into(binding.image)
    }
}