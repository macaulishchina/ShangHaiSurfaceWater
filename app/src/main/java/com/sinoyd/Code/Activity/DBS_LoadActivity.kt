package com.sinoyd.Code.Activity

import android.os.Bundle
import android.os.Handler
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DBS_LoadActivity : SinoBaseActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nbBar.hide()
        setLayout(R.layout.activity_load)
        openSlideFinish(false)
        //延迟跳转界面
        handler.postDelayed({
            startintent()
        }, 2000)

    }

    private fun startintent() {
        startActivity<DBS_LoginActivity>()
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}
