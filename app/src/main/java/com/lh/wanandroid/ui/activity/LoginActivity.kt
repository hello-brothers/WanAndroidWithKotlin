package com.lh.wanandroid.ui.activity

import android.view.MenuItem
import android.view.View
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.event.LoginEvent
import com.lh.wanandroid.ext.shortToast
import com.lh.wanandroid.mvp.contract.LoginContract
import com.lh.wanandroid.mvp.model.bean.LoginData
import com.lh.wanandroid.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
class LoginActivity: BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {


    override fun createPresenter() = LoginPresenter()

    override fun attachLayoutRes() = R.layout.activity_login

    override fun initData() {

    }

    override fun start() {

    }

    override fun initChildView() {
        toolbar.run {
            title = getString(R.string.user_login)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        if (mUserName.isNotBlank()){
            etUserName.setText(mUserName)
        }

        btnLogin.setOnClickListener(clickListener)
        btnToRegister.setOnClickListener(clickListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loginSuccess(data: LoginData) {
        resources.getString(R.string.login_success).shortToast()
        isLogin = true

        /** 保存用户名、密码、token **/
        mUserName = data.username
        mPassword = data.password
        mToken = data.token

        EventBus.getDefault().post(LoginEvent(true))
        finish()

    }

    /** 页面点击事件 **/
    private val clickListener = View.OnClickListener {
        when(it.id){
            R.id.btnLogin->{
                login()
            }
            R.id.btnToRegister->{
                "注册".shortToast()
            }
        }
    }

    private fun login(){
        if (validate())
            mPresenter?.loginWanAndroid(etUserName.text.trim().toString(), etPassword.text.toString())
    }

    private fun validate(): Boolean{
        etUserName.run {
            if (text.isEmpty()){
                error = resources.getString(R.string.username_not_empty)
                return false
            }
        }

        etPassword.run {
            if (text.isEmpty()){
                error = resources.getString(R.string.password_not_empty)
                return false
            }
        }

        return true
    }
}