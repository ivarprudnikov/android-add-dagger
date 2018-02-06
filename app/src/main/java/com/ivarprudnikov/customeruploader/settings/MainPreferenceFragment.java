package com.ivarprudnikov.customeruploader.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

public class MainPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(screen);

        PreferenceCategory category = new PreferenceCategory(screen.getContext());
        category.setTitle("Home screen tweaks");
        screen.addPreference(category);

        EditTextPreference greeting = new EditTextPreference(screen.getContext());
        greeting.setKey("welcome");
        greeting.setDialogTitle("Welcome message");
        greeting.setTitle("Welcome message");
        greeting.setDialogMessage("Provide short message");
        greeting.setDefaultValue("Modify me in configuration screen");
        greeting.setSummary("");
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
        EditTextPreference pref = (EditTextPreference) findPreference("welcome");
        pref.setSummary("dummy"); // required or will not update
        pref.setSummary(pref.getText());
    }

}
