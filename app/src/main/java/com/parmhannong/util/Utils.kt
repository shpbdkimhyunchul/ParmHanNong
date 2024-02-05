//package com.dgbcap.moffice.util
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.content.pm.PackageInfo
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.viewbinding.ViewBinding
//import com.dgbcap.moffice.base.BaseActivity
//import com.dgbcap.moffice.intro.IntroActivity
//import com.dgbcap.moffice.main.MainActivity
//import com.dgbcap.moffice.web.HybridCallback
//import com.dgbcap.moffice.web.WebActivity
//import org.json.JSONObject
//import java.io.FileNotFoundException
//import java.util.*
//
//
//class Utils {
//
//    companion object {
//        fun genParams(params: JSONObject): String = JSONObject().apply {
//            put("RESULT_CODE", "0000")
//            put("RESULT_MSG", "성공")
//            put("RESULT_DATASET", params)
//        }.toString()
//
//        fun getAppVersion(context: Context): String {
//            return try {
//                val pInfo: PackageInfo =
//                    context.packageManager.getPackageInfo(context.packageName, 0)
//                pInfo.versionName
//            } catch (e: PackageManager.NameNotFoundException) {
//                Log.d("", "exception :: ${e.message}")
//                ""
//            }
//        }
//
//        fun getJsonObject(params: String): JSONObject {
//            return try {
//                return JSONObject(params)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                JSONObject()
//            }
//        }
//
//        fun exitProcess(context: Context) {
//            (context as Activity).finishAffinity()
//            System.runFinalization()
//            kotlin.system.exitProcess(0)
//        }
//
//        fun getActivity(hybridCallback: HybridCallback): BaseActivity<out ViewBinding> {
//            return if (hybridCallback.webView.context is MainActivity) {
//                hybridCallback.webView.context as MainActivity
//            } else {
//                hybridCallback.webView.context as WebActivity
//            }
//        }
//
//        fun getActivity(context: Context): BaseActivity<out ViewBinding> {
//            return when (context) {
//                is MainActivity -> context
//                is WebActivity -> context
//                is IntroActivity -> context
//                else -> ({
//                    context
//                }) as BaseActivity<out ViewBinding>
//            }
//        }
//
//
//        fun getBitmapForUri(context: Context, uri: Uri): Bitmap? {
//            var resizeBitmap: Bitmap? = null
//            val options: BitmapFactory.Options = BitmapFactory.Options()
//            try {
//                resizeBitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options)
//
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//            return resizeBitmap
//        }
//
//
//        fun requestPermission(activity: Activity) {
//            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), 100)
//        }
//
//        fun isPermission(activity: Activity): Boolean {
//            return when {
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED -> false
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED -> false
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED -> false
//                else -> true
//            }
//        }
//    }
//}