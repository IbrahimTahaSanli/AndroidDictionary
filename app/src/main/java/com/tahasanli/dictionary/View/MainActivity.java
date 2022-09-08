package com.tahasanli.dictionary.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tahasanli.dictionary.Adapter.MainActivityAdapter;
import com.tahasanli.dictionary.Model.WordModel;
import com.tahasanli.dictionary.R;
import com.tahasanli.dictionary.Services.DictionaryAPI;
import com.tahasanli.dictionary.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance;

    public ArrayList<WordModel> words;
    public static final String BASEURL = "https://api.dictionaryapi.dev/api/v2/";

    private ActivityMainBinding binding;

    public String word = "";

    private Handler hand;
    private Runnable runn;

    private MainActivityAdapter adapter;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        words = new ArrayList<>();

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hand = new Handler();
        runn = new Runnable() {
            @Override
            public void run() {
                GetData();
            }
        };

        binding.Recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.Recycler.setAdapter( adapter = new MainActivityAdapter());

        binding.MainActivityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                word = editable.toString();
                hand.removeCallbacks(runn);
                hand.postDelayed(runn, 1000);
            }
        });

        Gson g = new GsonBuilder().setLenient().create();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(
                        GsonConverterFactory.create(g)
                ).build();

        GetData();
    }

    private void GetData(){
        DictionaryAPI dicAPI = retrofit.create(DictionaryAPI.class);

        Call<List<WordModel>> call = dicAPI.getData(this.word);

        call.enqueue(new Callback<List<WordModel>>() {
            @Override
            public void onResponse(Call<List<WordModel>> call, Response<List<WordModel>> response) {
                if(response.isSuccessful()) {
                    words = new ArrayList<>(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<WordModel>> call, Throwable t) {
                Toast.makeText( MainActivity.this, getString(R.string.MainActivityGetFail), Toast.LENGTH_SHORT).show();
                t.getStackTrace();
            }
        });
    }
}