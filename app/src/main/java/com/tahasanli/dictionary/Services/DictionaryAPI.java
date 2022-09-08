package com.tahasanli.dictionary.Services;

import com.tahasanli.dictionary.Model.WordModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryAPI {
    @GET("entries/en/{word}")
    Call<List<WordModel>> getData(@Path(value = "word", encoded = true) String word);
}
