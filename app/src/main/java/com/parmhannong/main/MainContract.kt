package com.parmhannong


/**
 * MainContract
 */
interface MainContract {

    interface View : BaseView<Presenter> {
        fun finishFlow()
    }

    interface Presenter : BasePresenter {
        fun startFlow()
    }

}
