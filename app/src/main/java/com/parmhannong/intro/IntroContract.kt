package com.parmhannong



/**
 * MainContract
 */
interface IntroContract {

    interface View : BaseView<Presenter> {
        fun finishFlow()
    }

    interface Presenter : BasePresenter {
        fun startFlow()
    }

}
