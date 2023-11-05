package com.example.salon.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.salon.R;
import com.example.salon.databinding.FragmentRegisterBinding;

public class Register extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //connection_Django   save User info


            }
        });

        return view;
    }
}