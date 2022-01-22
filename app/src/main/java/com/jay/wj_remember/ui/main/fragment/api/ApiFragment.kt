package com.jay.wj_remember.ui.main.fragment.api

import androidx.fragment.app.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.FragmentApiBinding
import com.jay.wj_remember.ui.base.BaseFragment
import com.jay.wj_remember.ui.main.fragment.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiFragment : BaseFragment<FragmentApiBinding, ApiFragmentViewModel>(R.layout.fragment_api) {

    override val viewModel: ApiFragmentViewModel by viewModels()

    private val userAdapter by lazy {
        UserAdapter { user, position ->
            viewModel.onClickUser(user, position)
        }
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() {
        with(viewModel) {

        }
        with(activityViewModel) {
            apiUserList.observe(viewLifecycleOwner, {
                viewModel.setApiUserList(it)
            })
        }
    }

    override fun setupViews() = with(binding) {
        apiUserRv.adapter = userAdapter
    }

    companion object {
        fun newInstance(): ApiFragment {
            return ApiFragment()
        }
    }

}