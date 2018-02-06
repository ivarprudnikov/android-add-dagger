package com.ivarprudnikov.customeruploader.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.ivarprudnikov.customeruploader.R;

public class PasswordService {

    private Context ctx;

    private PasswordService(){}

    public PasswordService(Context ctx){
        this.ctx = ctx;
    }

    public boolean checkCandidate(String candidate){
        return candidate != null && candidate.equals(getCurrentPassword());
    }

    private String getCurrentPassword(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        String newPass = sharedPref.getString(ctx.getResources().getString(R.string.pref_security_pass_key), "");

        if(!TextUtils.isEmpty(newPass))
            return newPass;

        return ctx.getResources().getString(R.string.pref_security_pass_default);
    }

}
