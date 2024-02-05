package com.parmhannong

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

interface IonActivityResult {
    fun onActivityResult(requestCode: Int, intent: ActivityResult)



}