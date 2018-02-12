package com.ivarprudnikov.daggerexample.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provide Singleton injectables used across the app
 */
@Module
public class SharedModule {

    /**
     * Context is provided by {@link AppModule} and no it cannot go there
     * as well, because AppModule uses abstract @Binds method and here
     * its @Provides one, they cannot be mixed.
     *
     * @param ctx {@link Context}
     * @return SharedPreferences {@link SharedPreferences}
     */
    @Provides
    SharedPreferences provideSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    // another example could be OkHttpClient
}
