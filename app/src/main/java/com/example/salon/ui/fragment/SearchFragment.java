package com.example.salon.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.salon.R;
import com.example.salon.data.api.Client;
import com.example.salon.data.api.Iclient;
import com.example.salon.data.model.retrofit.allcategorysearch.Response;
import com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem;
import com.example.salon.data.viewmodel.ItemCategorySearch;
import com.example.salon.databinding.FragmentSearchBinding;
import com.example.salon.ui.adapter.AdapterCategorySearch;
import com.example.salon.ui.adapter.Click;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        getDateCategory();
        return binding.getRoot();
    }

    private void getDateCategory() {
        Iclient iclient = Client.Companion.getClient().create(Iclient.class);
        iclient.allCategorySearch().enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, retrofit2.Response<List<ResponseItem>> response) {
                if (response.isSuccessful()&&response.body()!=null) {
                    setDataCategory(response.body());
                }else {
                    Toast.makeText(getContext(), "EE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Toast.makeText(getContext(), Objects.requireNonNull(t.getMessage()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataCategory(List<ResponseItem> response) {
        if (response.isEmpty()) {
            binding.tvCategory.setVisibility(View.GONE);
        } else {
            ArrayList<ItemCategorySearch> itemCategorySearchArrayList = new ArrayList<>();
            for (ResponseItem responseItem : response) {
                itemCategorySearchArrayList.add(new ItemCategorySearch(responseItem));
            }
            binding.rvCategorySearch.setAdapter(new AdapterCategorySearch(getContext(), itemCategorySearchArrayList, ClickOnItemCategory()));
        }

    }

    private Click ClickOnItemCategory() {
        return (b, itemCategorySearch) -> {
            Toast.makeText(getContext(), b+"", Toast.LENGTH_SHORT).show();
        };
    }

}