package com.test.ishara.newsfeed.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.adapter.NewsAdapter;
import com.test.ishara.newsfeed.dto.ResultDto;
import com.test.ishara.newsfeed.rest.DataService;
import com.test.ishara.newsfeed.rest.RetrofitClientInstance;
import com.test.ishara.newsfeed.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener{

    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.news_list);
        progressDoalog = new ProgressDialog(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData("us", true);
    }

    public void getData(String country, final boolean init){
        progressDoalog.show();
        DataService dataService = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
        Call<ResultDto> call = dataService.getNewsList(country, Utils.API_Key);
        call.enqueue(new Callback<ResultDto>() {
            @Override
            public void onResponse(Call<ResultDto> call, Response<ResultDto> response) {
                newsAdapter = new NewsAdapter(response.body().getArticlesDtos(), NewsActivity.this);
                newsAdapter.setClickListener(NewsActivity.this);
//                recyclerView.setAdapter(newsAdapter);
                if(init){
                    recyclerView.setAdapter(newsAdapter);
                } else {
                    recyclerView.swapAdapter(newsAdapter, false);

                }
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<ResultDto> call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.us:
                getData("us", false);
                return true;
            case R.id.fr:
                getData("fr", false);
                return true;
            case R.id.de:
                getData("de", false);
                return true;
            case R.id.search:
                // do your code
                return true;
            case R.id.editor:
                Intent intent = new Intent(NewsActivity.this, SourseFeedActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent detailIntent = new Intent(NewsActivity.this, DetailActivity.class);
        detailIntent.putExtra("MyClass", newsAdapter.getItem(position));
//        Toast.makeText(this, "You clicked " + newsAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        startActivity(detailIntent);
    }
}
