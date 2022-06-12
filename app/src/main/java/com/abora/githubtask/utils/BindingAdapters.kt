package com.abora.githubtask.utils

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.ParseException
import android.net.Uri
import android.os.CountDownTimer
import android.text.Html
import android.text.TextUtils
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

import android.graphics.BitmapFactory
import android.widget.*
import com.abora.githubtask.R
import java.io.File
import java.lang.Math.abs


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:cardBg")
    fun cardBg(cardView: CardView, color: String?) {
        color ?: return
        cardView.setCardBackgroundColor(Color.parseColor(color))
    }


    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: EditText, errorMessage: String?) {
        view.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("app:htmlText")
    fun htmlText(textView: TextView, text: String?) {
        text ?: return
        textView.text = Html.fromHtml(text)
    }


    @JvmStatic
    @BindingAdapter("app:rateValue")
    fun setRating(ratingBar: RatingBar, mVoteAverage: Double) {
        ratingBar.rating = mVoteAverage.toFloat()
    }

    @JvmStatic
    @BindingAdapter("app:setTextColor")
    fun setTextColor(textView: TextView, color: String?) {
        color ?: return
        textView.setTextColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:setBackGroundColor")
    fun setTextColor(view: View, color: String?) {
        color ?: return
        view.setBackgroundColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:setunderLine")
    fun setUnderLine(textView: TextView, set: Boolean) {
        textView.setPaintFlags(textView.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    @JvmStatic
    @BindingAdapter("app:setCenterLine")
    fun setCenterLine(textView: TextView, set: Boolean) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

    }

    @JvmStatic
    @BindingAdapter("app:currency", "app:amount")
    fun setCurrencyAndAmount(
        textView: TextView,
        currency: String,
        amount: Double
    ) {
        textView.text = String.format(Locale.US, "%.2f", amount) + " " + currency
    }

    
    @JvmStatic
    @BindingAdapter("colorForText")
    fun colorForText(textView: TextView, color: String?) {

        color ?: return

        textView.setTextColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:dateFormat")
    fun dateFormat(textView: TextView, date: String?) {
        date ?: return
        val strDate = date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var convertedDate: Date? = Date()
        try {
            convertedDate = dateFormat.parse(strDate)
            val sdfnewformat = SimpleDateFormat("MMM dd yyyy")
            val finalDateString = sdfnewformat.format(convertedDate)
            textView.text = finalDateString
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["loadImg", "placeholder"], requireAll = false)
    fun loadImg(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        if (!TextUtils.isEmpty(url)) {
            var placeHolderId = placeHolder

            if (placeHolderId == null) {
                placeHolderId =
                    imageView.context.resources.getDrawable(R.drawable.ic_placeholder_circle)
            }

            Picasso.get()
                .load(url)
                .error(placeHolderId!!)
                .placeholder(imageView.context.resources.getDrawable(R.drawable.ic_placeholder_circle))
                .into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["loadImgLocal"])
    fun loadImgLocal(imageView: ImageView, url: String?) {
        val imgFile = File(url)

        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            imageView.setImageBitmap(myBitmap)
        }
    }


    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: String?) {
        if (imageUri == null) {
            view.setImageURI(null)
        } else {
            view.setImageURI(Uri.parse(imageUri))
        }
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }



    @JvmStatic
    @BindingAdapter("setMarquee")
    fun setMarquee(textView: TextView, selected: Boolean) {
        textView.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView.isSingleLine = true
        textView.marqueeRepeatLimit = -1
        textView.setHorizontallyScrolling(true)
        textView.isSelected = selected
    }
}