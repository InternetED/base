//package com.internet.boy.androidbase.utils
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import com.internet.boy.androidbase.kutils.inTransaction
//
///**
// *@date: 2018/9/8 : 下午 10:12
// *@author: Ed
// *@email: salahayo3192@gmail.com
// **/
//
////inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
////    val fragmentTransaction = beginTransaction()
////    fragmentTransaction.func()
////    fragmentTransaction.commit()
////}
//
//inline fun FragmentManager.inTransaction(block: FragmentTransaction.() -> FragmentTransaction) {
//    beginTransaction()
//        .block()
//        .commit()
//}
//
///**
// * @param frameId 傳入 ResLayoid
// * @param fragments Fragment 加入 -> supportFragmentManager
// *
// * */
//fun FragmentActivity.initFragment(frameId: Int, fragments: ArrayList<Fragment>) {
//    supportFragmentManager.inTransaction {
//        fragments.forEach {
//            this.add(frameId, it)
//            this.hide(it)
//        }
//        this
//    }
//}
//
//fun FragmentActivity.showFragment(nextFragment: Fragment, toBackStack: Boolean = false) {
//    supportFragmentManager.inTransaction {
//        //        hide(curFragment)
//        curFragment?.let { hide(it) }
//        show(nextFragment)
//        if (toBackStack) {
//            addToBackStack(null)
//        }
//        this
//    }
//}
//
//
//fun FragmentActivity.hideFragment(curFragment: Fragment) {
//    supportFragmentManager.inTransaction {
//        hide(curFragment)
//        this
//    }
//}
//
//fun FragmentActivity.cleanFragment(fff: List<Fragment>) {
//    supportFragmentManager.inTransaction {
//        fff.forEach {
//            this.remove(it)
//        }
//        this
//    }
//}
//
//
//fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) {
//    supportFragmentManager.inTransaction {
//        add(frameId, fragment)
//        hide(fragment)
//    }
//}
//
//fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, toBackStack: Boolean = false) {
//    supportFragmentManager.inTransaction {
//        replace(frameId, fragment)
//            .also {
//                if (toBackStack) {
//                    it.addToBackStack(null)
//                }
//            }
//    }
//}