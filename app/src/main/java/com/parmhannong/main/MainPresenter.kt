package com.parmhannong

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * MainPresenter
 */
class MainPresenter(private var view: MainContract.View) : MainContract.Presenter {

    private val activity: Activity
        get() = view as Activity

    override fun startFlow() {

        CoroutineScope(Dispatchers.Main).launch {

            val mVaccine: Deferred<Boolean> = async(Dispatchers.IO) {
                val data = listOf(1, 2, 3, 4, 5)
                if (data.contains(2)) {
                    true
                } else false
            }

            val result = mVaccine.await()
            println("shpbd11==" + result)

            if (!result) {
                return@launch
            }

            val mAppiron: Deferred<Boolean> = async(Dispatchers.IO) {
                val data = listOf(1, 2, 3, 4, 5)
                if (data.contains(2)) {
                    true
                } else true
            }
            val result1 = mAppiron.await()

            if (!result1) {
                return@launch
            }
            println("shpbd22==" + result1)

            view.finishFlow()


        }
    }


}