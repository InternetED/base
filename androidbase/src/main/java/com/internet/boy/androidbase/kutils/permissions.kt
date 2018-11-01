package com.internet.boy.androidbase.kutils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.AppOpsManagerCompat
import androidx.core.content.ContextCompat

/**
 * Created by zyyoona7 on 2017/9/5.
 *
 * 動態權限相關擴展函數
 */
/*
 所有危險的Android系統權限都屬於權限組。
 如果設備運行的是Android 6.0（API級別23），並且應用的targetSdkVersion是23或更高版本，
 則當用戶請求危險權限時系統會發生以下行為：

 1.如果應用請求其清單中列出的危險權限，而應用目前在權限組中沒有任何權限，
   則系統會向用戶顯示一個對話框，描述應用要訪問的權限組。對話框不描述該組內的具體權限。
   例如，如果應用請求READ_CONTACTS權限，系統對話框只說明該應用需要訪問設備的聯繫信息。
   如果用戶批准，系統將向應用授予其請求的權限。

 2.如果應用請求其清單中列出的危險權限，而應用在同一權限組中已有另一項危險權限，則系統會立即授予該權限，
   而無需與用戶進行任何交互。例如，如果某應用已經請求並且被授予了READ_CONTACTS權限，
   然後它又請求WRITE_CONTACTS，系統將立即授予該權限。

 危險權限和權限組。

 權限組權限

 CALENDAR READ_CALENDAR日曆相關
                                             WRITE_CALENDAR
 CAMERA CAMERA訪問相機或者拍照錄像
 聯繫方式READ_CONTACTS聯繫人相關
                                             WRITE_CONTACTS
                                             GET_ACCOUNTS
 LOCATION ACCESS_FINE_LOCATION訪問設備位置
                                             ACCESS_COARSE_LOCATION
 MICROPHONE RECORD_AUDIO訪問麥克風音頻相關
 PHONE READ_PHONE_STATE電話相關
                                             CALL_PHONE
                                             READ_CALL_LOG
                                             WRITE_CALL_LOG
                                             ADD_VOICEMAIL
                                             USE_SIP
                                             PROCESS_OUTGOING_CALLS
 SENSORS BODY_SENSORS身體傳感器相關如心率
 短信SEND_SMS短信消息相關
                                             RECEIVE_SMS
                                             READ_SMS
                                             RECEIVE_WAP_PUSH
                                             RECEIVE_MMS
 存儲READ_EXTERNAL_STORAGE共享外部存儲相關
                                             WRITE_EXTERNAL_STORAGE
*/

/**
 * [permission]权限是否已经授权
 * from https://github.com/yanzhenjie/AndPermission
 *
 * @param permission
 */
fun Context.isPermissionGranted(permission: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
    val opPermission: String? = AppOpsManagerCompat.permissionToOp(permission)
    if (!opPermission.isNullOrEmpty()) {
        val result = AppOpsManagerCompat.noteProxyOp(this, opPermission!!, packageName)
        if (result == AppOpsManagerCompat.MODE_IGNORED) return false
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) return false
    }

    return true
}

/**
 * [permissions]所有权限是否已经授权
 *
 * @param permissions
 */
fun Context.arePermissionGranted(vararg permissions: String): Boolean =
        permissions.all { isPermissionGranted(it) }

/**
 * [permission]权限是否被拒绝或不再提示
 *
 * @param permission
 */
fun Activity.isPermissionAlwaysDenied(permission: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false
    return !shouldShowRequestPermissionRationale(permission)
}

/**
 * [permissions]所有权限是否被拒绝或不再提示
 *
 * @param permissions
 */
fun Activity.arePermissionAlwaysDenied(vararg permissions: String): Boolean =
        permissions.all { isPermissionAlwaysDenied(it) }

/**
 * 请求[permissions]授权
 *
 * @param permissions
 * @param requestCode
 */
fun Activity.requestPermission(permissions: Array<out String>, requestCode: Int) =
        ActivityCompat.requestPermissions(this, permissions, requestCode)

/**
 * 请求[permissions]授权
 *
 * @param permissions
 * @param requestCode
 * @param rationale
 */
inline fun Activity.requestPermissionWithRationale(permissions: Array<out String>, requestCode: Int, rationale: ((permissions: Array<out String>) -> Unit)) {
    permissions.forEach {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, it)) {
            rationale(permissions)
            return
        }
    }
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

/**
 * 处理请求权限结果
 *
 * @param permissions
 * @param grantResults
 * @param success
 * @param fail
 */
fun handlePermissionResult(permissions: Array<out String>, grantResults: IntArray,
                           success: ((permissions: List<String>) -> Unit) = {},
                           fail: ((permissions: List<String>) -> Unit) = {}) {
    val deniedList = arrayListOf<String>()
    permissions.indices
            .filter { grantResults[it] != PackageManager.PERMISSION_GRANTED }
            .forEach { deniedList += permissions[it] }
    if (deniedList.isNotEmpty()) {
        fail(deniedList)
    } else {
        success(arrayListOf(*permissions))
    }
}

