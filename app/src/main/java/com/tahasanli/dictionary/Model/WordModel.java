package com.tahasanli.dictionary.Model;

import com.google.gson.annotations.SerializedName;

public class WordModel {
    @SerializedName("word") public String Word;
    @SerializedName("meanings") public WordMeaningModel[] Meanings;

    public class WordMeaningModel {
        @SerializedName("partOfSpeech") public String PartOfSpeech;
        @SerializedName("definitions") public WordDefinitionModel[] definitions;

        public class WordDefinitionModel{
            @SerializedName("definition") public String Definitions;
            @SerializedName("example") public String Example;
        }

    }

}
