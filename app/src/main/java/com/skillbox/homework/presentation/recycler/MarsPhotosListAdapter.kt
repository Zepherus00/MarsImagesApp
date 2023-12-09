package com.skillbox.homework.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.skillbox.homework.databinding.MarsInfoItemBinding
import com.skillbox.homework.entity.MarsPhotos

class MarsPhotosListAdapter(
    private val onClick: (MarsPhotos) -> Unit
) : ListAdapter<MarsPhotos, MarsPhotosViewHolder>(DiffUtilCallback()) {

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
        val item = getItem(position)
        with(holder.binding) {
            camera.text = "Camera: ${item?.camera?.name}"
            date.text = "Date: ${item?.earth_date}"
            sol.text = "Sol: ${item?.sol.toString()}"
            rover.text = "Rover: ${item?.rover?.name}"
            item?.let {
                Glide.with(image.context).load(it.img_src).into(image)
            }
        }
        holder.binding.image.setOnClickListener {
            onClick(item)
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<MarsPhotos>() {
    override fun areItemsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean =
        oldItem == newItem
}