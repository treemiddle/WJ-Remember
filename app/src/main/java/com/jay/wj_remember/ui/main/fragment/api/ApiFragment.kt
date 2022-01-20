package com.jay.wj_remember.ui.main.fragment.api

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.FragmentApiBinding
import com.jay.wj_remember.ui.base.BaseFragment
import com.jay.wj_remember.ui.main.MainViewModel
import com.jay.wj_remember.ui.main.UserAdapter
import com.jay.wj_remember.utils.FragmentType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApiFragment : BaseFragment<FragmentApiBinding, ApiFragmentViewModel>(R.layout.fragment_api) {

    override val viewModel: ApiFragmentViewModel by viewModels()

    @Inject lateinit var userAdapter: UserAdapter

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() {
        with(viewModel) {

        }
        with(activityViewModel) {
            fragmentType.observe(viewLifecycleOwner, {
                if (it.peekContent() == FragmentType.API) {
                    viewModel.call()
                }
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