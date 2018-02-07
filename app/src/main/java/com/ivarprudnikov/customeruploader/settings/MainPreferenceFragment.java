package com.ivarprudnikov.customeruploader.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.ivarprudnikov.customeruploader.R;

public class MainPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(screen);

        PreferenceCategory category = new PreferenceCategory(screen.getContext());
        category.setTitle(getString(R.string.pref_home_category_title));
        screen.addPreference(category);

        EditTextPreference greeting = new EditTextPreference(screen.getContext());
        greeting.setKey(getString(R.string.pref_home_welcome_key));
        greeting.setDialogTitle(R.string.pref_home_welcome_title);
        greeting.setTitle(R.string.pref_home_welcome_title);
        greeting.setDialogMessage(R.string.pref_home_welcome_message);
        greeting.setDefaultValue(R.string.pref_home_welcome_default);
        category.addPreference(greeting);

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
        pref.setSummary("dummy"); // required or will not update
        pref.setSummary(pref.getText());
    }

}
