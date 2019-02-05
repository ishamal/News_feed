package com.test.ishara.newsfeed.views;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.adapter.NewsAdapter;
import com.test.ishara.newsfeed.adapter.SourceAdapter;
import com.test.ishara.newsfeed.dto.ResultDto;
import com.test.ishara.newsfeed.dto.SourceResultDto;
import com.test.ishara.newsfeed.rest.DataService;
import com.test.ishara.newsfeed.rest.RetrofitClientInstance;
import com.test.ishara.newsfeed.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourseFeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sourse_feed);
        recyclerView = findViewById(R.id.news_list);
        progressDoalog = new ProgressDialog(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData("us");
    }


    public void getData(String country){
        progressDoalog.show();
        DataService dataService = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
        Call<SourceResultDto> call = dataService.getSourse(country, Utils.API_Key);
        call.enqueue(new Callback<SourceResultDto>() {
            @Override
            public void onResponse(Call<SourceResultDto> call, Response<SourceResultDto> response) {
                SourceAdapter sourceAdapter = new SourceAdapter(response.body().getSourceDtos(), SourseFeedActivity.this);
                recyclerView.setAdapter(sourceAdapter);
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<SourceResultDto> call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }

}
