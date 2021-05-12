package com.decwujot.mclowiczinternational.features.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.decwujot.mclowiczinternational.R
import com.decwujot.mclowiczinternational.utility.PREF_TITLE_LANG
import com.decwujot.mclowiczinternational.utility.PREF_TITLE_THEME
import com.decwujot.mclowiczinternational.utility.get
import com.decwujot.mclowiczinternational.utility.put
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_layout, rootKey)

        val sharedPreferences = requireContext().getSharedPreferences("app", Context.MODE_PRIVATE)
        val language = sharedPreferences.get(PREF_TITLE_LANG, "en")
        val theme = sharedPreferences.get(PREF_TITLE_THEME, "day")

        val languagePreferences: ListPreference? = findPreference(PREF_TITLE_LANG)
        languagePreferences?.let {
            initLangPrefVal(language, it)
            it.setOnPreferenceChangeListener { preference, newValue ->
                handleChangeLanguage(newValue.toString())
                true
            }
        }
        val themePreferences: ListPreference? = findPreference(PREF_TITLE_THEME)
        themePreferences?.let {
            initThemePrefVal(it, theme)
            it.setOnPreferenceChangeListener { preference, newValue ->
                handleThemeSwitch(newValue.toString())
                true
            }
        }
    }

    private fun initThemePrefVal(it: ListPreference, theme: String) {
        val array = requireContext().resources.getStringArray(R.array.theme_array)
        it.value = when (theme) {
            "day" -> array[0]
            else -> array[1]
        }
    }

    private fun initLangPrefVal(language: String, it: ListPreference) {
        val array = requireContext().resources.getStringArray(R.array.language_array)
        val langCode = when (language) {
            "pl" -> array[0]
            "en" -> array[1]
            "es" -> array[2]
            "de" -> array[3]
            else -> array[4]
        }
        it.value = langCode.toString()
    }

    private fun handleThemeSwitch(newTheme: String) {
        Log.e("mode", newTheme)
        val array = requireContext().resources.getStringArray(R.array.theme_array)
        val theme = when (newTheme) {
            array[0] -> "day"
            else -> "night"
        }
        requireContext().getSharedPreferences("app", Context.MODE_PRIVATE).apply {
            put(PREF_TITLE_THEME, theme)
        }
        requireActivity().recreate()
    }

    private fun handleChangeLanguage(newLang: String) {
        val array = requireContext().resources.getStringArray(R.array.language_array)
        val langCode = when (newLang) {
            array[0] -> "pl"
            array[1] -> "en"
            array[2] -> "es"
            array[3] -> "de"
            else -> "cs"
        }
        requireContext()
            .getSharedPreferences("app", Context.MODE_PRIVATE).apply {
                put(PREF_TITLE_LANG, langCode)
            }
        requireActivity().recreate()
    }
}