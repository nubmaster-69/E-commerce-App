package com.hisu.hisumal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {
    
    private FrameLayout frameLayout;
    private TextInputEditText mEdtUserName, mEdtPwd;
    private TextView txtNoAccount;
    private Button btnLogin;

    private static final String BTN_DISABLE = "#bdc3c7";
    private static final String BTN_ENABLE = "#EF984B";

    public LoginFragment(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initUI(view);
        addActionForNoAccountEvent();
        addActionForBtnLogin();

        addListenerForEditText(mEdtUserName);
        addListenerForEditText(mEdtPwd);

        return view;
    }

    private void initUI(View view) {
        txtNoAccount = view.findViewById(R.id.txt_have_account);
        txtNoAccount.append(Html.fromHtml("<u>Sign up now!</u>"));

        mEdtUserName = view.findViewById(R.id.edt_username);
        mEdtPwd = view.findViewById(R.id.edt_pwd);

        btnLogin = view.findViewById(R.id.btn_login);
        changeButtonState(false, BTN_DISABLE);
    }

    private void changeButtonState(boolean state, String color) {
        btnLogin.setEnabled(state);
        btnLogin.setBackgroundColor(Color.parseColor(color));
    }

    private void addActionForNoAccountEvent() {
        txtNoAccount.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(frameLayout.getId(), new RegisterFragment(frameLayout)).commit();
        });
    }

    private void addActionForBtnLogin() {
        btnLogin.setOnClickListener(view1 -> {

            if (!isValidAccount(mEdtUserName.getText().toString().trim(), mEdtPwd.getText().toString().trim()))
                return;

            Intent intent = new Intent(getContext(), HomeActivity.class);

            intent.putExtra(HomeActivity.TEST_INTENT_KEY, "Login Successfully!");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            getActivity().finish();
        });
    }

    private boolean isValidAccount(String userName, String password) {
        if (!userName.matches("[a-zA-Z0-9]{0,12}")) {
            mEdtUserName.setError("Invalid Username! Please check your Username!");
            mEdtUserName.requestFocus();
            return false;
        }

        //Mock up user account
        if (!(userName.equals("harry") && password.equals("123Nguyen"))) {
            mEdtUserName.setError("Username or Password not correct!");
            mEdtUserName.requestFocus();
            return false;
        }

        //Todo: Add logic to check for user account here
        return true;
    }

    private void addListenerForEditText(TextInputEditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mEdtPwd.getText().toString().trim().isEmpty() && !mEdtUserName.getText().toString().trim().isEmpty())
                    changeButtonState(true, BTN_ENABLE);
                else
                    changeButtonState(false, BTN_DISABLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}