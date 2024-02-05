package com.parmhannong

import android.app.Activity
import android.content.Intent


object Router {

//    fun gotoKeyPad(activity: Activity){
//        Intent(activity, TransKeyFragmentView::class.java).run {
//            activity.startActivity(this)
//        }
//}

//    fun gotoOnePassMainActivity(activity: Activity){
//        Intent(activity, OnePassMainActivity::class.java).run {
//            activity.startActivity(this)
//        }
//    }

//    fun gotoWebView(activity: Activity, url: String) {
//        Intent(activity, WebActivity::class.java).run {
//            this.putExtra("url", url)
//            activity.startActivity(this)
//        }
//    }

//    fun gotoImportCertActivity(activity: Activity) {
//        Intent(activity, KSW_Activity_ICRSImportCert::class.java).run {
//            activity.startActivity(this)
//        }
//    }

//    fun gotoQRImportCertActivity(activity: Activity){
//        Intent(activity, KSW_Activity_CertQRCopy::class.java).run {
//            activity.startActivity(this)
//        }
//    }

//    fun gotoCertListActivity(activity: Activity) {
//        Intent(activity, KSW_Activity_CertList::class.java).run {
//            val bundle =Bundle()
//            bundle.putString("CATEGORY", "update")
//            this.putExtra("BUNDLE", bundle)
//            activity.startActivity(this)
//        }
//    }

//    fun gotoExportCertActivity(activity: Activity) {
//        Intent(activity, KSW_Activity_CertList::class.java).run {
//            val bundle = Bundle()
//            bundle.putString("CATEGORY", "certcopy")
//            bundle.putString("FUNCTION", "icrs_export")
//            this.putExtra("BUNDLE", bundle)
//            activity.startActivity(this)
//        }
//    }


//    fun gotoCertIssueActivity(activity: Activity) {
//        Intent(activity, KSW_Activity_Cmp_Issue::class.java).run {
//            val bundle = Bundle()
//            bundle.putString("CATEGORY", "manage")
//            this.putExtra("BUNDLE", bundle)
//            activity.startActivity(this)
//        }
//    }

//    fun gotoCertManageActivity(activity: Activity) {
//        Intent(activity, KSW_Activity_CertList::class.java).run {
//            val bundle = Bundle()
//            bundle.putString("CATEGORY", "manage")
//            this.putExtra("BUNDLE", bundle)
//            activity.startActivity(this)
//        }
//    }


//    fun gotoMenuActivity(activity: Activity) {
//        Intent(activity, SettingActivity::class.java).run {
//            activity.startActivity(this)
//        }
//    }

    fun gotoMainActivity(activity: Activity) {
        Intent(activity, MainActivity::class.java).run {
            flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(this)
        }
    }

//    fun miniScanActivity(context: Context) =
//        Intent(context, BackgroundScanActivity::class.java).apply {
//            //BackgroundScanActivity로 넘길 옵션값 설정
//            putExtra("useBlackAppCheck", true) // 루팅 검사를 실시하면 루팅 우회 앱 설치 여부까지 검사
//            putExtra("showBlackAppName", true)
//            putExtra("scan_rooting", true)
//            putExtra("scan_package", true)
//            putExtra("useDualEngine", false)
//            putExtra("backgroundScan", true) // mini 전용
//            putExtra("rootingexitapp", true)
//            putExtra("rootingyesorno", false)
//            putExtra("rootingyes", false)
//            putExtra("rooting_delay_time", 0)
//            putExtra("show_update", false)
//            putExtra("show_license", false)
//            putExtra("show_notify", true) // mini 전용
//            putExtra("notifyClearable", false) // mini 전용
//            putExtra("notifyAutoClear", false) // mini 전용
//            putExtra("show_toast", true)
//            putExtra("show_warning", false)
//            putExtra("show_scan_ui", true) // mini 전용
//            putExtra("show_badge", false) // mini 전용
//            putExtra("bg_rooting", false) // mini 전용
//            putExtra("show_about", true) // mini 전용
//        }


}