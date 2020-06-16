package grg.app.open.app.component

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import grg.app.open.app.MyApplication

//class CopyBoard {
//    ////获取剪贴板管理器：
//    //ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//    //// 创建普通字符型ClipData
//    //ClipData mClipData = ClipData.newPlainText("Label", "这里是要复制的文字");
//    //// 将ClipData内容放到系统剪贴板里。
//    //cm.setPrimaryClip(mClipData);
//}

private val clipboardManager: ClipboardManager by lazy {
    MyApplication.context.getSystemService(
        Context.CLIPBOARD_SERVICE
    ) as ClipboardManager
}

fun copyTextToClipBoard(text: CharSequence) {
    val newPlainText = ClipData.newPlainText("Label", text)
    clipboardManager.setPrimaryClip(newPlainText)
}

fun getFromClipBoard(): ClipData? {
    return clipboardManager.primaryClip
}
