package com.lh.wanandroid.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import com.lh.wanandroid.R
import org.jetbrains.anko.backgroundColor
import kotlin.math.min

/**
 *@author: lh
 *CreateDate: 2020/6/24
 */
class CircleImageView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    private var mType: Int = DEFAULT_TYPE
    private var mBorderColor = DEFAULT_BORDER_COLOR
    private var mBorderWidth = DEFAULT_BORDER_WIDTH
    private var mRectRoundRadius = DEFAULT_RECT_ROUND_RADIUS

    private val mPaintBitmap = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintBorder = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mRectBorder = RectF()
    private val mRectBitmap = RectF()


    private var mRawBitmap: Bitmap? = null
    private var mShader: BitmapShader? = null
    private val mMatrix = Matrix()

    private var bkgroundColor : Int? = null
        set(value) {
            field = value
            invalidate()
        }

    companion object{

        const val TYPE_NONE = 0

        /** 圆形 **/
        const val TYPE_CIRCLE = 1

        /** 圆角矩形 **/
        const val TYPE_ROUNDED_RECT = 2

        private const val DEFAULT_TYPE = TYPE_NONE
        private const val DEFAULT_BORDER_COLOR = Color.TRANSPARENT
        private const val DEFAULT_BORDER_WIDTH = 0
        private const val DEFAULT_RECT_ROUND_RADIUS = 0

    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CircleImageView).use {
            mType = it.getInt(R.styleable.CircleImageView_type, DEFAULT_TYPE)
            mBorderColor = it.getColor(R.styleable.CircleImageView_borderColor, DEFAULT_BORDER_COLOR)
            mBorderWidth = it.getDimensionPixelSize(R.styleable.CircleImageView_borderWidth, dip2px(DEFAULT_BORDER_WIDTH))
            mRectRoundRadius = it.getDimensionPixelSize(R.styleable.CircleImageView_rectRoundRadius, dip2px(DEFAULT_RECT_ROUND_RADIUS))
        }
    }

    override fun onDraw(canvas: Canvas) {
        val rawBitmap = getBitmap(drawable)
        if (rawBitmap != null && mType != TYPE_NONE){
            val viewWidth = width
            val viewHeight = height
            val viewMinSize = min(viewHeight, viewWidth)

            val dstWidth = (if (mType == TYPE_CIRCLE) viewMinSize else viewWidth).toFloat()
            val dstHeight = (if (mType == TYPE_CIRCLE) viewMinSize else viewHeight).toFloat()

            val halfBorderWidth = mBorderWidth / 2.0f
            val doubleBorderWidth = mBorderWidth * 2.0f

            if (mShader == null || rawBitmap != mRawBitmap){
                mRawBitmap = rawBitmap
                //创建着色器，x、y方向都设置为用边缘色彩填充多余空间
                mShader = BitmapShader(mRawBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            }

            //用于缩放BitmapShader
            if (mShader != null){
                mMatrix.setScale((dstWidth - doubleBorderWidth) /  rawBitmap.width, (dstHeight - doubleBorderWidth) / rawBitmap.height)
                mShader!!.setLocalMatrix(mMatrix)
            }

            mPaintBitmap.shader = mShader
            mPaintBorder.style = Paint.Style.STROKE
            mPaintBorder.strokeWidth = mBorderWidth.toFloat()
            mPaintBorder.color = if (mBorderWidth > 0) mBorderColor else Color.TRANSPARENT

            if (mType == TYPE_CIRCLE){
                val radius = viewMinSize / 2.0f
                canvas.drawCircle(radius, radius, radius - halfBorderWidth, mPaintBorder)
                canvas.translate(mBorderWidth.toFloat(), mBorderWidth.toFloat())
                canvas.drawCircle(radius - mBorderWidth, radius - mBorderWidth, radius - mBorderWidth, mPaintBitmap)

            }else if (mType == TYPE_ROUNDED_RECT){
                mRectBorder.set(halfBorderWidth, halfBorderWidth, dstWidth - halfBorderWidth, dstHeight - halfBorderWidth)
                mRectBitmap.set(0.0f, 0.0f, dstWidth - doubleBorderWidth, dstHeight - doubleBorderWidth)
                val borderRadius = if (mRectRoundRadius - halfBorderWidth > 0.0f) mRectRoundRadius - halfBorderWidth else 0.0f
                val bitmapRadius = if (mRectRoundRadius - mBorderWidth > 0.0f) (mRectRoundRadius - mBorderWidth).toFloat() else 0.0f
                canvas.drawRoundRect(mRectBorder, borderRadius, borderRadius, mPaintBorder)
                canvas.translate(mBorderWidth.toFloat(), mBorderWidth.toFloat())
                canvas.drawRoundRect(mRectBitmap, bitmapRadius, bitmapRadius, mPaintBitmap)
            }
        }else{
            super.onDraw(canvas)
        }
    }

    private fun dip2px(dipVal: Int): Int{
        val scale = resources.displayMetrics.density
        return (dipVal * scale + 0.5f).toInt()
    }

    /** 根据drawable类型返回生成Bitmap **/
    private fun getBitmap(drawable: Drawable?): Bitmap?{
        if (drawable == null) return null
        return when(drawable){
            is BitmapDrawable -> drawable.bitmap
            is ColorDrawable ->{
                val bounds = drawable.bounds
                val width = bounds.right - bounds.left
                val height = bounds.bottom - bounds.top
                val color = bkgroundColor?: drawable.color
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color))
                bitmap
            }
            else -> null
        }
    }

    fun setBkColor(@ColorInt color:  Int){
        bkgroundColor = color
    }

}