package com.lh.multiple

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

/**
 *@author: lh
 *CreateDate: 2020/7/7
 */
class MultipleStatusView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr) {

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    /** 各种视图layout得资源id **/
    private val mEmptyViewResId: Int
    private val mErrorViewResId: Int
    private val mLoadingViewResId: Int
    private val mNoNetworkViewResId: Int

    /** 何种视图view **/
    private lateinit var mEmptyView: View
    private lateinit var mErrorView: View
    private lateinit var mLoadingView: View
    private lateinit var mNoNetworkView: View

    private var mViewStatus: Int = STATUS_CONTENT

    private val mInflater: LayoutInflater

    private val mOtherIds = ArrayList<Int>()

    companion object{
        val DEFAULT_LAYOUT_PARAMS = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        const val STATUS_CONTENT    = 0x00
        const val STATUS_LOADING    = 0x01
        const val STATUS_EMPTY      = 0x02
        const val STATUS_ERROR      = 0x03
        const val STATUS_NO_NETWORK = 0x04

    }
    init {
        val ta =
            context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0)
        mEmptyViewResId =
            ta.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view)
        mErrorViewResId =
            ta.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view)
        mLoadingViewResId =
            ta.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view)
        mNoNetworkViewResId =
            ta.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.no_network_view)
        ta.recycle()

        mInflater = LayoutInflater.from(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mErrorView, mEmptyView, mLoadingView, mNoNetworkView)
        mOtherIds.clear()

    }

    //显示内容视图
    fun showContent(){
        showContentView()
        mViewStatus = STATUS_CONTENT

    }
    private fun showContentView(){
        for (i in 0 until childCount){
            val view = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) View.GONE else View.VISIBLE
        }
    }



    //显示空视图
    fun showEmpty(){
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS)
        mViewStatus = STATUS_EMPTY
    }
    private fun showEmpty(layoutId: Int, layoutParams: ViewGroup.LayoutParams){

        mEmptyView = if (!this::mEmptyView.isInitialized) inflateView(layoutId) else mEmptyView
        showEmpty(mEmptyView, layoutParams)
    }
    private fun showEmpty(view: View, layoutParams: ViewGroup.LayoutParams){
        checkNull(view, "empty view is null")
        checkNull(layoutParams, " layout params is null")
        mOtherIds.add(mEmptyView.id)
        addView(mEmptyView, 0, layoutParams)
        showViewById(mEmptyView.id)
    }



    //显示错误视图
    fun showError(){
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS)
        mViewStatus = STATUS_ERROR
    }
    private fun showError(layoutId: Int, layoutParams: ViewGroup.LayoutParams){
        mErrorView = if (!this::mErrorView.isInitialized) inflateView(layoutId) else mErrorView
        showError(mErrorView, layoutParams)
    }
    private fun showError(view: View, layoutParams: ViewGroup.LayoutParams){
        checkNull(view, "error view is null")
        checkNull(layoutParams, "layout params is null")
        mOtherIds.add(mErrorView.id)
        addView(mErrorView, 0, layoutParams)
        showViewById(mEmptyView.id)
    }



    //显示加载视图
    fun showLoading(){
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS)
        mViewStatus = STATUS_LOADING

    }
    private fun showLoading(layoutId: Int, layoutParams: ViewGroup.LayoutParams){
        mLoadingView = if (!this::mLoadingView.isInitialized) inflateView(layoutId) else mLoadingView
        showLoading(mLoadingView, layoutParams)
    }
    private fun showLoading(view: View, layoutParams: ViewGroup.LayoutParams){
        checkNull(view, "loading view is null")
        checkNull(layoutParams, "layout params is null")
        mOtherIds.add(mLoadingView.id)
        addView(mLoadingView, 0, layoutParams)

        showViewById(mLoadingView.id)
    }



    //显示无网络视图
    fun showNoNetwork(){
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS)
        mViewStatus = STATUS_NO_NETWORK

    }
    private fun showNoNetwork(layoutId: Int, layoutParams: ViewGroup.LayoutParams){
        mNoNetworkView = if (!this::mNoNetworkView.isInitialized) inflateView(layoutId) else mNoNetworkView
        showNoNetwork(mNoNetworkView, layoutParams)
    }
    private fun showNoNetwork(view: View, layoutParams: ViewGroup.LayoutParams){
        checkNull(view, "loading view is null")
        checkNull(layoutParams, "layout params is null")
        mOtherIds.add(mNoNetworkView.id)
        addView(mNoNetworkView, 0, layoutParams)
        showViewById(mNoNetworkView.id)
    }



    private fun inflateView(layoutId: Int) = mInflater.inflate(layoutId, null)

    private fun checkNull(a: Any?, hint: String){
        if (a == null)
            throw NullPointerException(hint)
    }

    private fun clear(vararg views: View?){
        for (view in views) {
            if (view != null){
                removeView(view)
            }
        }
    }

    private fun showViewById(viewId: Int){
        for (i in 0 until childCount){
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) View.VISIBLE else View.GONE
        }
    }

    fun getViewStatus() = mViewStatus
}