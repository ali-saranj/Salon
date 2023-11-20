package com.example.salon.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.PopupMenu;

import com.example.salon.R;
import com.example.salon.databinding.ActivityLoginBinding;


public class Activity_Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNav();
    }

    private void setUpNav() {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(),null);
        popupMenu.inflate(R.menu.login_menu);
        binding.bottomBar.setupWithNavController(popupMenu.getMenu(),((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_login)).getNavController());
    }
}
