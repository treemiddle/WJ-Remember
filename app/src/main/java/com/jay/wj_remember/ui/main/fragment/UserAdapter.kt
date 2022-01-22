package com.jay.wj_remember.ui.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.wj_remember.databinding.ItemUserBinding
import com.jay.wj_remember.model.User
import com.jay.wj_remember.ui.base.BaseListAdapter
import com.jay.wj_remember.ui.base.BaseViewHolder

typealias UserClick = (User, Int) -> Unit

class UserAdapter(
    private val onItemClick: UserClick? = null
) : BaseListAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> {
        return UserHolder.create(parent).also {
            if (onItemClick == null) {
                return@also
            } else {
                it.itemView.setOnClickListener { _ ->
                    val currentUser = currentList.getOrNull(it.adapterPosition) ?: return@setOnClickListener
                    onItemClick.invoke(currentUser, it.adapterPosition)
                }
            }
        }
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

        companion object Factory {
            fun create(parent: ViewGroup): UserHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemUserBinding.inflate(layoutInflater, parent, false)

                return UserHolder(view)
            }
        }
    }

}