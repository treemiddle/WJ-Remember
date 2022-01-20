package com.jay.wj_remember.ui.main.fragment.local

import androidx.fragment.app.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.FragmentLocalBinding
import com.jay.wj_remember.ui.base.BaseFragment
import com.jay.wj_remember.ui.main.UserAdapter
import com.jay.wj_remember.utils.FragmentType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocalFragment : BaseFragment<FragmentLocalBinding, LocalFragmentViewModel>(R.layout.fragment_local) {

    override val viewModel: LocalFragmentViewModel by viewModels()

    @Inject lateinit var userAdapter: UserAdapter

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() {
        with(viewModel) {

        }
        with(activityViewModel) {
            fragmentType.observe(viewLifecycleOwner, {
                if (it.peekContent() == FragmentType.LOCAL) {
                    viewModel.call()
                }
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