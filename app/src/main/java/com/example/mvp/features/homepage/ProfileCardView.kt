package com.example.mvp.features.homepage

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.mvp.databinding.LayoutCardProfileBinding

class ProfileCardView @JvmOverloads constructor (
    context: Context,
    attributeSet: AttributeSet? = null,
    styleAttribute: Int = 0

): ConstraintLayout(context, attributeSet, styleAttribute) {

    var bootomText: String = ""
        get() = binding.botText.text.toString()
        set(value) {
            field = value
            binding.botText.text = field
        }

    private var listener: ClickListener? = null

    private var endIconListener: (() -> Unit)? = null

    //Binding dari XML Layout
    private var binding: LayoutCardProfileBinding
    init {
        //inflate Binding
        binding = LayoutCardProfileBinding.inflate(LayoutInflater.from(context),this,true)



        binding.btnX.setOnClickListener {
            listener?.onClickEndIcon()
            endIconListener?.invoke()
        }
    }


    fun setTopTest(text: String) {
        binding.topText.text = text
    }

    fun setTextColor(topColorId: Int? = null, bottomColorId: Int? = null){
        if (topColorId != null){
            binding.topText.setTextColor(ContextCompat.getColor(context,topColorId))
        }
        if (bottomColorId != null)
            binding.botText.setTextColor(ContextCompat.getColor(context,bottomColorId))

    }

    fun setFamily(topFrontFamily: Int? = null, bottomFrontFamily: Int? = null){
        topFrontFamily?.let {
            binding.topText.typeface = ResourcesCompat.getFont(context,it)
        }
    }
    fun setIcon(setIcon: Int? = null){
        if(setIcon != null){
            binding.btnX.setImageDrawable(ContextCompat.getDrawable(context, setIcon))
        }
    }
    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    fun setEndIconListener(listener: () -> Unit) {
        endIconListener = listener
    }

    interface ClickListener {
        fun onClickEndIcon()
    }
}