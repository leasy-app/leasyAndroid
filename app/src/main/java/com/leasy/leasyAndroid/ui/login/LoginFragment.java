package com.leasy.leasyAndroid.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.App;
import com.leasy.leasyAndroid.LoginActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;

import retrofit2.Response;

public class LoginFragment extends Fragment implements UiCallBack {

    private TextView txtRegister;
    private TextInputEditText edtUsername, edtPassword;
    private Button btnLogin;

    private String username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initialize(v);
        if(!App.username().equals("")){
            ((LoginActivity) getActivity()).authSucceeded(App.username(), "");
        }
        txtRegister.setOnClickListener(v1 -> {
            ((LoginActivity) getActivity()).showRegister();
        });

        btnLogin.setOnClickListener(v1 -> {
            username = edtUsername.getText().toString();
            password = edtPassword.getText().toString();
            if (password.isEmpty() || username.isEmpty())
                return;
            edtUsername.setEnabled(false);
            edtPassword.setEnabled(false);
            ApiUtils.requestPostLogin(this, 0, username, password);
        });

        return v;
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        Log.d("success", "asdf");
        ((LoginActivity) getActivity()).authSucceeded(username, password);
    }

    @Override
    public void onRequestError(Response response, int code) {
        Log.d(response.toString(), "asdf");

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {
        Log.d("asdf", t.toString());

    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {
        Log.d("asdf", response.toString());

    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {
        Toast.makeText(getContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
        Log.d("asdf", "faillll");
        edtPassword.setEnabled(true);
        edtUsername.setEnabled(true);
    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {
        Log.d("asdf", t.toString());

    }

    @Override
    public void onInternalErrorFailure(int code) {
        Log.d("internal", "asdf");
    }

    private void initialize(View v) {
        txtRegister = v.findViewById(R.id.txt_go_to_register);
        edtPassword = v.findViewById(R.id.edt_password_login);
        edtUsername = v.findViewById(R.id.edt_login_username);
        btnLogin = v.findViewById(R.id.btn_login);
    }
}