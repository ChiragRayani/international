package com.decwujot.mclowiczinternational.features.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.decwujot.mclowiczinternational.utility.LocaleService

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleService.updateBaseContextLocale(newBase))
    }
}