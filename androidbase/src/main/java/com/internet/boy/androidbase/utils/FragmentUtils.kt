package com.internet.boy.androidbase.utils

import com.internet.boy.androidbase.base.BaseFragment
import boy.internet.lin.curFragment

/**
 *@date: 2018/9/8 : 下午 10:12
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

//inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
//    val fragmentTransaction = beginTransaction()
//    fragmentTransaction.func()
//    fragmentTransaction.commit()
//}

inline fun androidx.fragment.app.FragmentManager.inTransaction(block: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) {
    beginTransaction()
            .block()
            .commit()
}

/**
 * @param frameId 傳入 ResLayoid
 * @param fragments Fragment 加入 -> supportFragmentManager
 *
 * */
fun androidx.fragment.app.FragmentActivity.initFragment(frameId: Int, fragments: ArrayList<BaseFragment>) {
    supportFragmentManager.inTransaction {
        fragments.forEach {
            this.add(frameId, it)
            this.hide(it)
        }
        this
    }
}

fun androidx.fragment.app.FragmentActivity.showFragment(nextFragment: androidx.fragment.app.Fragment, toBackStack: Boolean = false) {
    supportFragmentManager.inTransaction {
        //        hide(curFragment)
        curFragment?.let { hide(it) }
        show(nextFragment)
        if (toBackStack) {
            addToBackStack(null)
        }
        this
    }
}


fun androidx.fragment.app.FragmentActivity.hideFragment(curFragment: androidx.fragment.app.Fragment) {
    supportFragmentManager.inTransaction {
        hide(curFragment)
        this
    }
}

fun androidx.fragment.app.FragmentActivity.cleanFragment(fff: List<androidx.fragment.app.Fragment>) {
    supportFragmentManager.inTransaction {
        fff.forEach {
            this.remove(it)
        }
        this
    }
}


fun androidx.fragment.app.FragmentActivity.addFragment(fragment: androidx.fragment.app.Fragment, frameId: Int) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        hide(fragment)
    }
}

fun androidx.fragment.app.FragmentActivity.replaceFragment(fragment: androidx.fragment.app.Fragment, frameId: Int, toBackStack: Boolean = false) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
                .also {
                    if (toBackStack) {
                        it.addToBackStack(null)
                    }
                }
    }
}