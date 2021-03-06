package org.markensic.baselibrary.global

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.markensic.baselibrary.framework.lifecycle.FragmentLifecycle
import org.markensic.baselibrary.ui.widget.TabLayout
import java.lang.ref.WeakReference

object FragmentManager : FragmentLifecycle() {
  var weakCurrentFragment: WeakReference<Fragment>? = null

  override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
    super.onFragmentResumed(fm, f)
    weakCurrentFragment.also {
      it?.clear()
      weakCurrentFragment = WeakReference<Fragment>(f)
    }
  }

  fun addOrHideFragment(
    fm: FragmentManager,
    tabLayout: TabLayout,
    containerLayoutId: Int,
    toIndex: Int
  ) {
    val toFragment = tabLayout.fragments[toIndex]
    val fromFragment = tabLayout.fragments[tabLayout.selectedIndex]
    if (!fm.fragments.contains(toFragment)) {
      fm.beginTransaction().apply {
        if (fm.fragments.size == 0) {
          add(containerLayoutId, toFragment).commit()
        } else {
          hide(fromFragment)
            .add(containerLayoutId, toFragment)
            .commit()
        }
      }
    } else {
      fm.beginTransaction()
        .hide(fromFragment)
        .show(toFragment)
        .commit()
    }
  }

  fun addOrHideFragment(
    tabLayout: TabLayout,
    toIndex: Int,
    transaction: (from: Fragment, to: Fragment) -> Unit
  ) {
    val toFragment = tabLayout.fragments[toIndex]
    val fromFragment = tabLayout.fragments[tabLayout.selectedIndex]
    transaction(fromFragment, toFragment)
  }
}
