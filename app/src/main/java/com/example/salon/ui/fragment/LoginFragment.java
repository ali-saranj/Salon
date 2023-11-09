package com.example.salon.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.salon.data.api.Client;
import com.example.salon.data.api.Iclient;
import com.example.salon.data.model.retrofit.login.Body;
import com.example.salon.data.model.retrofit.login.Response;
import com.example.salon.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtIsNotNull()){
                    login();
                }
            }
        });
        return view;
    }

    private void login() {
        Iclient iclient = Client.Companion.getClient().create(Iclient.class);

        iclient.login(new Body(binding.passwordLogin.getText().toString(),binding.usernameLogin.getText().toString())).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(getContext(), response.body().getStatos().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean edtIsNotNull() {
        if (binding.usernameLogin.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "نام کاربری را وارد کنید", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.passwordLogin.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "رمز عبور را وارد کنید", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}