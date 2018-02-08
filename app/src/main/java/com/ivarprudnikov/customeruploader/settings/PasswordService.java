package com.ivarprudnikov.customeruploader.settings;

import android.content.Context;

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
        return ctx.getResources().getString(R.string.pref_security_pass_default);
    }

}
