package com.jay.wj_remember.ui.main.fragment.local

import androidx.fragment.app.viewModels
import com.jay.common.makeLog
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.FragmentLocalBinding
import com.jay.wj_remember.ui.base.BaseFragment
import com.jay.wj_remember.ui.main.fragment.UserAdapter
import com.jay.wj_remember.utils.FragmentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalFragment : BaseFragment<FragmentLocalBinding, LocalFragmentViewModel>(R.layout.fragment_local) {

    override val viewModel: LocalFragmentViewModel by viewModels()

    private val userAdapter by lazy {
        UserAdapter { user, position ->
            if (user.positionType == 1) {
                viewModel.localclick(user, position)
            }
        }
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() {
        with(viewModel) {

        }
        with(activityViewModel) {
            localUserList.observe(viewLifecycleOwner, {
                viewModel.setLocalUserList(it)
            })
        }
    }

    override fun setupViews() = with(binding) {
        localUserRv.adapter = userAdapter
    }

    companion object {
        fun newInstance(): LocalFragment {
            return LocalFragment()
        }
    }

}