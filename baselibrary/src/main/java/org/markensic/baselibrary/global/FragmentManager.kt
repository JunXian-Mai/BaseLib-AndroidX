package org.markensic.baselibrary.global

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.markensic.baselibrary.impl.FragmentLifecycle
import java.lang.ref.WeakReference

object FragmentManager: FragmentLifecycle() {
    var weakCurrentFragment: WeakReference<Fragment>? = null

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        weakCurrentFragment.also {
            it?.clear()
            weakCurrentFragment = WeakReference<Fragment>(f)
        }
    }
}