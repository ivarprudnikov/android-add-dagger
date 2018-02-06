package com.ivarprudnikov.customeruploader.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivarprudnikov.customeruploader.R;

public class AppSettingsLoginFragment extends DialogFragment {

    public static String TAG = "AppSettingsLoginFragment";

    public interface OnDetachListener {
        void onLoginFragmentDetached();
    }

    public interface OnAuthenticateListener {
        boolean onLoginFragmentAuthenticate(String candidate);
    }

    private OnDetachListener onDetachListener;
    private OnAuthenticateListener onAuthenticateListener;

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        onDetachListener = (OnDetachListener) getActivity();
        onAuthenticateListener = (OnAuthenticateListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (onDetachListener != null)
            onDetachListener.onLoginFragmentDetached();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.settings_login_dialog, null);

        final TextView message = (TextView) layout.findViewById(R.id.passwordMessage);
        final EditText pass = (EditText) layout.findViewById(R.id.password);

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                message.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final Button buttonCancel = (Button) layout.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(v -> {
            Log.d(TAG, "onCancel");
            getDialog().dismiss();
            getActivity().finish();
        });

        final Button buttonLogin = (Button) layout.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener((View view) -> {
            Log.d(TAG, "onLogin");
            if (onAuthenticateListener != null) {
                boolean result = onAuthenticateListener.onLoginFragmentAuthenticate(pass.getText().toString());
                if (result) {
                    message.setVisibility(View.GONE);
                    getDialog().dismiss();
                    Log.d(TAG, "Dismiss");
                } else {
                    message.setText(getResources().getString(R.string.pref_security_pass_failed_msg));
                    message.setVisibility(View.VISIBLE);
                }
            }
        });

        builder.setView(layout);

        return builder.create();
    }
}

