package com.sinoyd.Code.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View.INVISIBLE
import com.sinoyd.Code.Dialog.AboutDialog
import com.sinoyd.Code.Dialog.DownloadDialog
import com.sinoyd.Code.Dialog.VesionCheckDialog
import com.sinoyd.Code.Dialog.WaterExplainDialog
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_dbs__about.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

class DBS_AboutActivity : SinoBaseActivity(), DownloadDialog.DownloadListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbs__about)
        openSlideFinish(false)
        setview()
    }

    private fun setview() {
        titlename.text = "关于"
        titlename.gravity = Gravity.CENTER
        iv_right.visibility = INVISIBLE
        iv_left.onClick {
            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        //检查版本更新
        tv_check_version.onClick {
            VesionCheckDialog(this).show()
            //发请求  检查版本
        }
        //水质等级
        tv_level.onClick {

            WaterExplainDialog(this).show()
        }
        //使用帮助
        tv_help.onClick {
            startActivity<DBS_HelpActivity>()
        }
        //关于
        tv_about.onClick {
            AboutDialog(this).show()
        }
        //注销
        tv_cancellation.onClick {
            SharedPreferencesFactory.cleardata(activity, "loginId")
            SharedPreferencesFactory.cleardata(activity, "phonenumber")
            activity.startActivity<DBS_LoginActivity>()
            finish()
        }
    }

    override fun startDownload() {

    }

    override fun endDownload(filePath: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive")
        startActivity(intent)
    }
}
