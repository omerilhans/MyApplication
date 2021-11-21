package com.omerilhanli.myapplication.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding : ViewDataBinding>(
    @LayoutRes val layoutResId: Int
) : AppCompatActivity() {

    lateinit var binding: Binding

    abstract fun init()
    abstract fun observeOn()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind()
        init()
        observeOn()
    }

    private fun viewBind() {
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
    }

    fun showToastUI(message: String?) {
        message
            ?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
    }
}