package com.internet.boy.androidbase.kutils

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


/**
 * Created by zyyoona7 on 2017/8/28.
 * 文件相关的扩展函数
 */

/*
  ---------- Context ----------
 */
/**
 * 獲取應用程式文件目錄
 *
 * @see filesDir File格式
 * 應用程式文件目錄("/data/data/<包名>/files")
 */
val Context.fileDirPath: String
    get() = filesDir.absolutePath

/**
 * 獲取應用緩存目錄
 *
 * @see cacheDir File格式
 * 應用緩存目錄("/data/data/<包名>/cache")
 */
val Context.cacheDirPath: String
    get() = cacheDir.absolutePath


/**
 * 手機是否有SD卡
 */
val isExternalStorageWritable: Boolean
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * 獲取應用外置文件目錄
 *
 * 應用外置文件目錄("/Android/data/<包名>/files")
 */
val Context.externalFileDirPath: String
    get() = getExternalFilesDir("")!!.absolutePath

/**
 * 獲取應用外置緩存目錄
 *
 * 應用外置緩存目錄("/Android/data/<包名>/cache")
 */
val Context.externalCacheDirPath: String
    get() = externalCacheDir!!.absolutePath


/*
  ---------- File/Any ----------
 */


inline fun androidx.fragment.app.FragmentManager.inTransaction(block: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) {
    beginTransaction()
            .block()
            .commit()
}

/**
 * 獲取公共路徑
 * @param target 目標路徑
 * */
private inline fun publicDir(target: String): String {
    return Environment.getExternalStoragePublicDirectory(target).absolutePath
}

/**
 * 獲取公共的下載文件夾路徑
 */
val publicDownloadDir: String by lazy { publicDir(Environment.DIRECTORY_DOWNLOADS) }

/**
 * 獲取公共的照片文件夾路徑
 */
val publicDCIMDir: String by lazy { publicDir(Environment.DIRECTORY_DCIM) }

/**
 * 獲取公共的圖片文件夾路徑
 */
val publicPictureDir: String by lazy { publicDir(Environment.DIRECTORY_PICTURES) }

/**
 * 獲取公共的音樂文件夾路徑
 */
val publicMusicDir: String by lazy { publicDir(Environment.DIRECTORY_MUSIC) }

/**
 * 獲取公共的電影文件夾路徑
 */
val publicMovieDir: String by lazy { publicDir(Environment.DIRECTORY_MOVIES) }


/**
 * 通过文件路径获取File对象
 *
 * @param filePath
 * @return nullable
 */
fun getFileByPath(filePath: String): File? = if (filePath.isBlank()) null else File(filePath)


/**
 * 判断文件是否存在
 *
 */
val File.isFileExists: Boolean get() = exists() && isFile


/**
 * 判断文件夹是否存在
 *
 */
val File.isDirExists: Boolean get() = exists() && isDirectory


/**
 * 判断目录是否存在，不存在则判断是否创建成功
 *
 * @return true 文件夹存在或者创建成功  false 文件夹不存在或者创建失败
 */
fun File.createOrExistsDir(): Boolean =
// 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        if (exists()) isDirectory else mkdirs()


/**
 * 判断文件是否存在，不存在则判断是否创建成功
 *
 * @return true 文件存在或者创建成功  false 文件不存在或者创建失败
 */
fun File.createOrExistsFile(): Boolean {
    if (exists()) return isFile
    if (parentFile?.createOrExistsDir() != true) return false

    return try {
        createNewFile()
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }
}


/**
 * 获取文件夹目录大小
 *
 * @return 文件大小 单位：B、KB、MB、GB
 */
val File.dirSize: String
    get() = if (dirLength == -1L) "" else byte2FitMemorySize(dirLength)

/**
 * 获取目录长度
 *
 * @return 目录长度
 */
val File.dirLength: Long
    get() {
        if (!isDirExists) return -1
        var len: Long = 0
        val files: Array<File>? = listFiles()
        files?.forEach {
            len += if (it.isDirectory) it.dirLength else it.length()
        }
        return len
    }

/**
 * 获取文件大小
 *
 * @return 文件大小 单位：B、KB、MB、GB
 */
val File.fileSize: String
    get() = if (fileLength == -1L) "" else byte2FitMemorySize(fileLength)


/**
 * 获取文件长度
 *
 * @return 文件长度
 */
val File.fileLength: Long get() = if (!isFileExists) -1 else length()


/**
 * 字节数转合适内存大小
 *
 * 保留3位小数
 *
 * @param byteNum 字节数
 * @return 合适内存大小
 */
private fun byte2FitMemorySize(byteNum: Long): String = when {
    byteNum < 0 -> "shouldn't be less than zero!"
    byteNum < 1024 -> String.format("%.3fB", byteNum.toDouble() + 0.0005)
    byteNum < 1048576 -> String.format("%.3fKB", byteNum.toDouble() / 1024 + 0.0005)
    byteNum < 1073741824 -> String.format("%.3fMB", byteNum.toDouble() / 1048576 + 0.0005)
    else -> String.format("%.3fGB", byteNum.toDouble() / 1073741824 + 0.0005)
}


/*
  ---------- 讀/寫文件----------
*/


fun File.write(text: String, charset: Charset = Charsets.UTF_8, isAppend: Boolean = false): Boolean {

    if (exists() || !canWrite()) return false

    if (isAppend) {
        appendText(text, charset)
    } else {
        writeText(text, charset)
    }
    return true
}

fun File.read(charset: Charset = Charsets.UTF_8): String {
    if (exists() || !canRead()) return ""

    return readText(charset)
}


/*
  ---------- 文件操作：複製、移動、删除----------
  ---------- thanks for https://github.com/Blankj/AndroidUtilCode ----------
 */


/**
 * 删除文件夹
 *
 * @return true  false
 *
 */
fun File.deleteDir(): Boolean {
    if (!exists()) return true
    if (!isDirectory) return false

    val files = listFiles()
    files?.forEach {
        if (it.isFile) {
            if (!it.delete()) return false
        } else if (it.isDirectory) {
            if (!deleteDir()) return false
        }
    }

    return delete()
}


/**
 * 删除文件
 *
 * @return true  false
 */
fun File.deleteFile(): Boolean = !exists() || (isFile && delete())