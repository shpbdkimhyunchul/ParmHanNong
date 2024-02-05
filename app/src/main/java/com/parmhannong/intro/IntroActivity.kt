package com.parmhannong

import android.os.Bundle
import com.parmhannong.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity<ActivityIntroBinding>(), IntroContract.View {
    override fun getViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater)
    }

    private val presenter: IntroContract.Presenter by lazy {
        IntroPresenter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.startFlow()
    }

    override fun finishFlow() {
        Router.gotoMainActivity(this)
    }
}