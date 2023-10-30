package com.example.salon.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.PopupMenu;

import com.example.salon.R;
import com.example.salon.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNav();
    }

    private void setUpNav() {
        PopupMenu popupmenu = new PopupMenu(getApplicationContext(),null);
        popupmenu.inflate(R.menu.menu_bottom);
        binding.bottomBar.setupWithNavController(popupmenu.getMenu(),((NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController());
    }
}