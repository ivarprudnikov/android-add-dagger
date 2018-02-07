package com.ivarprudnikov.customeruploader.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivarprudnikov.customeruploader.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppSettingsLoginFragment extends DialogFragment {

    public static String TAG = "AppSettingsLoginFragment";

    @BindView(R.id.passwordMessage)
    TextView message;
    @BindView(R.id.password)
    EditText pass;
    @BindView(R.id.buttonCancel)
    Button buttonCancel;
    @BindView(R.id.buttonLogin)
    Button buttonLogin;

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

        final LinearLayout layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.settings_login_dialog, null);
        ButterKnife.bind(this, layout);

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

        builder.setView(layout);

        return builder.create();
    }

    @OnClick(R.id.buttonCancel)
    public void onCancelClick(View view) {
        getDialog().dismiss();
        getActivity().finish();
    }

    @OnClick(R.id.buttonLogin)
    public void onLoginClick(View view) {
        if (onAuthenticateListener != null) {
            boolean result = onAuthenticateListener.onLoginFragmentAuthenticate(pass.getText().toString());
            if (result) {
                message.setVisibility(View.GONE);
                getDialog().dismiss();
            } else {
                message.setText(getString(R.string.pref_security_pass_failed_msg));
                message.setVisibility(View.VISIBLE);
            }
        }
    }
}

