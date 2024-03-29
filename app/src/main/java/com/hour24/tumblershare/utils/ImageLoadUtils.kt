package com.hour24.tumblershare.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * 이미지 로더, 데이터 바인딩 활용
 */
object ImageLoadUtils {

    private val TAG = ImageLoadUtils::class.java.name

    /**
     * @param view ImageView
     * @param url  이미지 주소
     * @author 장세진
     * @description 단순 이미지 로드
     */
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {

        try {

            if (ObjectUtils.isEmpty(url)) {
                return
            }

            view.post {

                Glide.with(view)
                        .load(url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                Logger.e(TAG, e!!.message + " / " + url)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        })
                        .into(view)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
