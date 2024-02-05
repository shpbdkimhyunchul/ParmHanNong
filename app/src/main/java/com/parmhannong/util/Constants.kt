package com.parmhannong

import android.net.Uri
import android.webkit.ValueCallback

object Constants {

    val FILE_CHOOSER_RESULT_CODE = 10000
    var CAPTURE_CAMERA_RESULT = 10001
    var uploadMessage: ValueCallback<Uri>? = null
    var uploadMessageArray: ValueCallback<Array<Uri>>? = null

}