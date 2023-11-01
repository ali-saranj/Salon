package com.example.salon.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.salon.R;
import com.example.salon.data.api.Client;
import com.example.salon.data.api.Iclient;

import com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem;
import com.example.salon.data.viewmodel.ItemCategorySearch;
import com.example.salon.data.viewmodel.SalonCard;
import com.example.salon.databinding.FragmentSearchBinding;
import com.example.salon.ui.adapter.AdapterCategorySearch;
import com.example.salon.ui.adapter.AdapterSalonCardGraid;
import com.example.salon.ui.adapter.Click;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

    ArrayList<String> categorys = new ArrayList<>();
    String search = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        getDateCategory();

        binding.btnSearch.setOnClickListener(clickBtnSearch());
        return binding.getRoot();
    }

    private View.OnClickListener clickBtnSearch() {
        return v -> {
            search = binding.edtSearch.getText().toString();
            search(search,String.join(",", categorys));
        };
    }


    private void getDateCategory() {
        Iclient iclient = Client.Companion.getClient().create(Iclient.class);
        iclient.allCategorySearch().enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, retrofit2.Response<List<ResponseItem>> response) {
                if (response.isSuccessful()&&response.body()!=null) {
                    setDataCategory(response.body());
                    search(search,String.join(",", categorys));
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
            if (b) {
                categorys.add(itemCategorySearch.getTitle());
            }else {
                categorys.remove(itemCategorySearch.getTitle());
            }
            search(search,String.join(",", categorys));
        };
    }

    private void search(String s,String category){
        Iclient iclient = Client.Companion.getClient().create(Iclient.class);

        iclient.search(s, category).enqueue(new Callback<List<com.example.salon.data.model.retrofit.search.ResponseItem>>() {
            @Override
            public void onResponse(Call<List<com.example.salon.data.model.retrofit.search.ResponseItem>> call, Response<List<com.example.salon.data.model.retrofit.search.ResponseItem>> response) {
                    if (response.body()!=null){
                        setDataSalon(response.body());
                        Log.d("onResponse: ","ok");
                    }else {
                        Log.d("onResponse: ","ee1");
                    }
            }

            @Override
            public void onFailure(Call<List<com.example.salon.data.model.retrofit.search.ResponseItem>> call, Throwable t) {
                Log.e("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void setDataSalon(List<com.example.salon.data.model.retrofit.search.ResponseItem> body) {
        if (!body.isEmpty()) {
            ArrayList<SalonCard> cardArrayList = new ArrayList<>();
            for (com.example.salon.data.model.retrofit.search.ResponseItem responseItem : body) {
                cardArrayList.add(new SalonCard(responseItem));
            }
            Log.d( "size: ",cardArrayList.size()+"");
            binding.rvSalon.setAdapter(new AdapterSalonCardGraid(cardArrayList));
        }
    }

}