package com.ivarprudnikov.daggerexample.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.ivarprudnikov.daggerexample.R;

public class MainPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(screen);

        PreferenceCategory homeScreenCategory = new PreferenceCategory(screen.getContext());
        homeScreenCategory.setTitle(getString(R.string.pref_home_category_title));
        screen.addPreference(homeScreenCategory);

        EditTextPreference greeting = new EditTextPreference(screen.getContext());
        greeting.setKey(getString(R.string.pref_home_welcome_key));
        greeting.setDialogTitle(R.string.pref_home_welcome_title);
        greeting.setTitle(R.string.pref_home_welcome_title);
        greeting.setDialogMessage(R.string.pref_home_welcome_message);
        greeting.setDefaultValue(getString(R.string.pref_home_welcome_default));
        homeScreenCategory.addPreference(greeting);

        PreferenceCategory customerScreenCategory = new PreferenceCategory(screen.getContext());
        customerScreenCategory.setTitle(getString(R.string.pref_customer_category_title));
        screen.addPreference(customerScreenCategory);

        ListPreference theme = new ListPreference(screen.getContext());
        theme.setKey(getString(R.string.pref_customer_theme_key));
        theme.setTitle(R.string.pref_customer_theme_title);
        theme.setDialogTitle(R.string.pref_customer_theme_title);
        theme.setEntries(Theme.getLabels());
        theme.setEntryValues(Theme.getLabels());
        theme.setDefaultValue(Theme.DEFAULT.getLabel());
        customerScreenCategory.addPreference(theme);


        onSharedPreferenceChanged(null, "");
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        EditTextPreference pref = (EditTextPreference) findPreference(getString(R.string.pref_home_welcome_key));
        pref.setSummary("dummy");
        pref.setSummary(pref.getText());

        ListPreference pref2 = (ListPreference) findPreference(getString(R.string.pref_customer_theme_key));
        pref2.setSummary("dummy");
        pref2.setSummary(pref2.getValue());
    }

}
