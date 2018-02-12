package com.ivarprudnikov.daggerexample.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.ivarprudnikov.daggerexample.R;
import com.ivarprudnikov.daggerexample.settings.Theme;

import dagger.Module;
import dagger.Provides;

/**
 * Provide Activity scoped injectables
 */
@Module
public class ThemeModule {

    @Provides
    @ActivityScoped
    Theme provideTheme(Context context, SharedPreferences sharedPreferences) {
        String selectedTheme = context.getString(R.string.pref_customer_theme_key);
        String defaultTheme = Theme.DEFAULT.getLabel();
        String themeLabel = sharedPreferences.getString(selectedTheme, defaultTheme);
        return Theme.fromLabel(themeLabel);
    }
}
