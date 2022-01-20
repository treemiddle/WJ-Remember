package com.jay.wj_remember.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.wj_remember.databinding.ItemUserBinding
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseListAdapter
import com.jay.wj_remember.ui.base.BaseViewHolder

class UserAdapter : BaseListAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> {
        return UserHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onViewRecycled(holder: BaseViewHolder<User>) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    class UserHolder(
        private val binding: ItemUserBinding
    ) : BaseViewHolder<User>(binding) {
        override fun bind(item: User) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {
            Glide.with(binding.ivImage).clear(binding.ivImage)
            Glide.with(binding.ivLike).clear(binding.ivLike)

            try {
                binding.tvHeader.text = null
                binding.tvName.text = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}