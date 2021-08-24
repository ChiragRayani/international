package com.decwujot.mclowiczinternational.features.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.decwujot.mclowiczinternational.R
import com.decwujot.mclowiczinternational.utility.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_layout, rootKey)

        val sharedPreferences =
            requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE)
        val language = sharedPreferences.get(PREF_TITLE_LANG, LANGUAGE_DEFAULT)
        val theme = sharedPreferences.get(PREF_TITLE_THEME, THEME_DEFAULT)

        val languagePreferences: ListPreference? = findPreference(PREF_TITLE_LANG)
        languagePreferences?.let {
            initLangPrefVal(language, it)
            it.setOnPreferenceChangeListener { _, newValue ->
                handleChangeLanguage(newValue.toString())
                true
            }
        }
        val themePreferences: ListPreference? = findPreference(PREF_TITLE_THEME)
        themePreferences?.let {
            initThemePrefVal(it, theme)
            it.setOnPreferenceChangeListener { _, newValue ->
                handleThemeSwitch(newValue.toString())
                true
            }
        }
    }

    private fun initThemePrefVal(it: ListPreference, theme: String) {
        val array = requireContext().resources.getStringArray(R.array.theme_array)
        it.value = when (theme) {
            THEME_DEFAULT -> array[0]
            else -> array[1]
        }
    }

    private fun initLangPrefVal(language: String, it: ListPreference) {
        val array = requireContext().resources.getStringArray(R.array.language_array)
        val langCode = when (language) {
            LANGUAGE_PL -> array[0]
            LANGUAGE_EN -> array[1]
            LANGUAGE_ES -> array[2]
            LANGUAGE_DE -> array[3]
            else -> array[4]
        }
        it.value = langCode.toString()
    }

    private fun handleThemeSwitch(newTheme: String) {
        val array = requireContext().resources.getStringArray(R.array.theme_array)
        val theme = when (newTheme) {
            array[0] -> THEME_DEFAULT
            else -> THEME_NIGHT
        }
        requireContext().getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE).apply {
            put(PREF_TITLE_THEME, theme)
        }
        requireActivity().recreate()
    }

    private fun handleChangeLanguage(newLang: String) {
        val array = requireContext().resources.getStringArray(R.array.language_array)
        val langCode = when (newLang) {
            array[0] -> LANGUAGE_PL
            array[1] -> LANGUAGE_EN
            array[2] -> LANGUAGE_ES
            array[3] -> LANGUAGE_DE
            else -> LANGUAGE_CS
        }
        requireContext()
            .getSharedPreferences(PREF_DB_NAME, Context.MODE_PRIVATE).apply {
                put(PREF_TITLE_LANG, langCode)
            }
        requireActivity().recreate()
    }

    companion object {
        const val LANGUAGE_PL = "pl"
        const val LANGUAGE_EN = "en"
        const val LANGUAGE_ES = "es"
        const val LANGUAGE_DE = "de"
        const val LANGUAGE_CS = "cs"
        const val THEME_NIGHT = "night"
    }
}