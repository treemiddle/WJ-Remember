package com.jay.wj_remember.ui.main.navigator

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.jay.wj_remember.R
import com.jay.wj_remember.ui.main.fragment.api.ApiFragment
import com.jay.wj_remember.ui.main.fragment.local.LocalFragment
import com.jay.wj_remember.utils.FragmentType
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {

    private val apiFragment = ApiFragment.newInstance()
    private val localFragment = LocalFragment.newInstance()

    override fun setMain() = initMain()

    override fun navigateTo(screen: FragmentType) = replaceFragment(screen)

    private fun initMain() {
        activity.supportFragmentManager.commit {
            add(R.id.fl_container, apiFragment)
            add(R.id.fl_container, localFragment)
            hide(localFragment)
            show(apiFragment)
        }
    }

    private fun replaceFragment(fragmentType: FragmentType) {
        activity.supportFragmentManager.commit {
            when (fragmentType) {
                FragmentType.API -> {
                    hide(localFragment)
                    show(apiFragment)
                }
                FragmentType.LOCAL -> {
                    hide(apiFragment)
                    show(localFragment)
                }
            }
        }
    }
}