package com.skillbox.homework.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.homework.databinding.MarsInfoItemBinding
import com.skillbox.homework.entity.MarsPhotos

class MarsPhotosAdapter : RecyclerView.Adapter<MarsPhotosViewHolder>() {
    private var data: List<MarsPhotos> = emptyList()

    fun setData(data: List<MarsPhotos>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            MarsInfoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            camera.text = "Camera: ${item?.camera?.name}"
            date.text = "Date: ${item?.earth_date}"
            sol.text = "Sol: ${item?.sol.toString()}"
            rover.text = "Rover: ${item?.rover?.name}"
            item?.let {
                Glide.with(image.context).load(it.img_src).into(image)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class MarsPhotosViewHolder(val binding: MarsInfoItemBinding) : RecyclerView.ViewHolder(binding.root)