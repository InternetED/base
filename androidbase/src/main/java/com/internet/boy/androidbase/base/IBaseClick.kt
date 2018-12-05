package com.internet.boy.androidbase.base

/**
 *@date: 2018/12/5 - 下午 05:30
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

  interface IBaseClick {


    /**
     * 間距時間
     * */
    var intervalsTime: Long


    /**
     * 上次點擊時間
     */
    var lastClickTime: Long


    /**
     * 判断是否快速點擊
     *
     * @return `true`: 是   `false`: 否
     */
    val isFastClick: Boolean
        get() {
            val now = System.currentTimeMillis()
            if (now - lastClickTime >= intervalsTime) {
                lastClickTime = now
                return false
            }
            return true
        }


}

