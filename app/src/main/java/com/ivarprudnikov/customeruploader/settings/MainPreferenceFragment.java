package com.ivarprudnikov.customeruploader.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

public class MainPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(screen);

        // if flavour 01 send using email

        // if flavour 02 send to firebase
    }

}
