package com.internet.boy.androidbase.utils


/**
 * 將文本中的半形字符，轉換成全形字符
 *
 * @return 轉換後的全形字符
 */

fun String.halfToFull(): String {
    val c = this.toCharArray()
    for (i in c.indices) {
        //半形空格
        if (c[i].toInt() == 32) {
            c[i] = 12288.toChar()
            continue
        }
        //根據實際情況，過濾不需要轉換的符號
        //if (c[i] == 46) //半形點號，不轉號
        // continue;

        //其他符號都轉換為全形
        if (c[i].toInt() in 33..126) {
            c[i] = (c[i].toInt() + 65248).toChar()
        }
    }
    return String(c)
}


/**
 * 將文本中的全形字符，轉換成半形字符
 *
 * @param input
 * @return 轉換後的半形字符
 */
fun String.fullToHalf(): String {
    val c = this.toCharArray()
    for (i in c.indices) {
        //全角空格
        if (c[i].toInt() == 12288) {
            c[i] = 32.toChar()
            continue
        }
        //其他符號都轉換為半形
        if (c[i].toInt() in 65281..65374) {
            c[i] = (c[i].toInt() - 65248).toChar()
        }
    }
    return String(c)
}



