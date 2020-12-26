package com.leasy.leasyAndroid.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.leasy.leasyAndroid.LoginActivity;
import com.leasy.leasyAndroid.R;
import com.leasy.leasyAndroid.api.ApiUtils;
import com.leasy.leasyAndroid.api.UiCallBack;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class RegisterFragment extends Fragment implements UiCallBack {

    private TextView txtLogin;
    private TextInputEditText edtName, edtUsername, edtEmail, edtPassword;
    private Button btnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        initialize(v);
        txtLogin.setOnClickListener(v1 -> {
            ((LoginActivity) getActivity()).showLogin();
        });

        btnRegister.setOnClickListener(v1 -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "Fill all the forms.", Toast.LENGTH_SHORT).show();
                return;
            }
            ApiUtils.requestPostRegister(
                    this,
                    0,
                    username,
                    name,
                    email,
                    "null",
                    password
            );
        });

        return v;
    }

    @Override
    public void onRequestSuccessful(Response response, int code) {
        Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
        ((LoginActivity) getActivity()).showLogin();
        Log.d(response.toString(), "asdf");
    }

    @Override
    public void onRequestError(Response response, int code) {
        Log.d(response.toString(), "asdf");

    }

    @Override
    public void onRequestSendFailure(Throwable t, int code) {
        Log.d(t.toString(), "asdf");

    }

    @Override
    public void onRefreshTokenExpired(Response response, int code) {
        Log.d(response.toString(), "asdf");

    }

    @Override
    public void onObtainAccessTokenError(Response response, int code) {
        Log.d(response.toString(), "asdf");

    }

    @Override
    public void onObtainAccessTokenFailure(Throwable t, int code) {
        Log.d(t.toString(), "asdf");

    }

    @Override
    public void onInternalErrorFailure(int code) {
        Log.d("internal", "asdf");
    }

    private void initialize(View v) {
        txtLogin = v.findViewById(R.id.txt_go_to_login);
        edtEmail = v.findViewById(R.id.edt_register_email);
        edtUsername = v.findViewById(R.id.edt_register_username);
        edtPassword = v.findViewById(R.id.edt_password_register);
        edtName = v.findViewById(R.id.edt_register_name);
        btnRegister = v.findViewById(R.id.btn_register);
    }
}