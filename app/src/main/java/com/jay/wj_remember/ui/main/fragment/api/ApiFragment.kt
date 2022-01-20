package com.jay.wj_remember.ui.main.fragment.api

import androidx.fragment.app.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.FragmentApiBinding
import com.jay.wj_remember.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiFragment : BaseFragment<FragmentApiBinding, ApiFragmentViewModel>(R.layout.fragment_api) {

    override val viewModel: ApiFragmentViewModel by viewModels()


    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() {
        with(viewModel) {

        }
        with(activityViewModel) {

        }
    }

    companion object {
        fun newInstance(): ApiFragment {
            return ApiFragment()
        }
    }

}