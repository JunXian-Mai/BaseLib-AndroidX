package org.markensic.baselibrary.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import org.markensic.baselibrary.framework.ui.BaseUiView

class TabLayout @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) :
  LinearLayout(context, attrs, defStyleAttr), BaseUiView {
  init {
    orientation = LinearLayout.HORIZONTAL
  }

  lateinit var tabItems: MutableList<TabView.TabItem>
  lateinit var fragments: MutableList<Fragment>
  var selectView: View? = null
  var tabCounts: Int = 0
  var selectedIndex: Int = 0

  fun initTab(
    tabs: MutableList<TabView.TabItem>,
    firstPos: Int = 0,
    onTabClick: (TabView.TabItem) -> Unit
  ) {
    val params = LayoutParams(0, LayoutParams.MATCH_PARENT)
    params.weight = 1F
    tabCounts = tabs.size
    if (tabCounts > 0) {
      tabItems = tabs
      fragments = tabItems.map {
        it.tabFragmentClz.newInstance()
      }.toMutableList()
      tabs.forEach { item ->
        TabView(context).apply {
          tag = item
          fillView(item)
          setOnClickListener {
            onTabClick(it.tag as TabView.TabItem)
            setCurrentTab(tabs.indexOf(item))
          }
          this@TabLayout.addView(this, params)
        }
      }
    }
    setCurrentTab(firstPos)
  }

  fun setCurrentTab(position: Int) {
    if (position in 0 until tabCounts) {
      getChildAt(position).apply {
        if (this != selectView) {
          isSelected = true
          selectView?.isSelected = false
          selectView = this
          selectedIndex = position
        }
      }
    }
  }
}
