package com.parmhannong

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.react.flipper.ReactNativeFlipper
import com.facebook.soloader.SoLoader
import com.parmhannong.react.ToastPackage

class MainApplication : Application(), ReactApplication {

    override val reactNativeHost: ReactNativeHost =
        object : DefaultReactNativeHost(this) {

            override fun getPackages(): List<ReactPackage> =
                PackageList(this).packages.apply {
                    // Packages that cannot be autolinked yet can be added manually here, for example:
                    // add(MyReactNativePackage())
                    add(ToastPackage())


                }

            override fun getJSMainModuleName(): String = "index"

            override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

            override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
            override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
        }

    override val reactHost: ReactHost
        get() = getDefaultReactHost(this.applicationContext, reactNativeHost)

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
            // If you opted-in for the New Architecture, we load the native entry point for this app.
            load()
        }
        ReactNativeFlipper.initializeFlipper(this, reactNativeHost.reactInstanceManager)
        registerActivityLifecycleCallbacks(myActivityLifecycleCallbacks) //액티비티 감시객체
        instance = this
    }

    private val myActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        private fun log(methodName: String, activity: Activity) {
            val activityName = activity.javaClass.simpleName
            val taskId = activity.taskId
        }


        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            sActivityCount++
        }

        override fun onActivityStarted(activity: Activity) {
            sRunningActivity = activity
        }

        override fun onActivityResumed(activity: Activity) {
            sRunningActivity = activity
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (sActivityCount == 0) {
                sRunningActivity = null
            }
        }
    }

    companion object {
        private var instance: MainApplication? = null

        @JvmStatic
        fun applicationContext(): Context = instance!!.applicationContext

        @JvmStatic
        fun application(): Application = instance!!

        fun resources(): Resources = instance!!.applicationContext.resources

        /**
         * 실행중인 Activity Count
         */
        private var sActivityCount: Int = 0

        /**
         * 실행중인 Activity
         */
        var sRunningActivity: Activity? = null

        /**
         * @return 실행중인 Activity를 리턴한다.
         */
        fun getRunningActivity(): Activity? {
            return sRunningActivity
        }

        /**
         * @return 실행중인 Activity 수를 리턴한다.
         */
        fun getActivityCount(): Int {
            return sActivityCount
        }
    }
}
