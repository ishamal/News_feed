package com.test.ishara.newsfeed.rest;

import com.test.ishara.newsfeed.dto.ResultDto;
import com.test.ishara.newsfeed.dto.SourceResultDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("/v2/top-headlines")
    Call<ResultDto> getNewsList(@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("/v2/sources")
    Call<SourceResultDto> getSourse(@Query("country") String country, @Query("apiKey") String apiKey);
    
}
