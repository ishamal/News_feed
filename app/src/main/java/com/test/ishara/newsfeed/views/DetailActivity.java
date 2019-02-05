package com.test.ishara.newsfeed.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.dto.ArticlesDto;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    ImageView newsImage;
    TextView titleText, details, discription, link;
    ArticlesDto articlesDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        newsImage = findViewById(R.id.image);
        titleText = findViewById(R.id.title);
        details = findViewById(R.id.detail);
        discription = findViewById(R.id.discription);
        link = findViewById(R.id.textView5);

        articlesDto = (ArticlesDto) getIntent().getExtras().getParcelable("MyClass");
        if (articlesDto == null) {
            finish();
        } else {
            buindData();
        }

    }

    public void buindData() {
        Picasso.get().load(articlesDto.getUrlToImage()).placeholder(R.mipmap.ic_launcher).into(newsImage);
        titleText.setText(articlesDto.getTitle());
        details.setText(articlesDto.getContent());
        details.setMovementMethod(new ScrollingMovementMethod());
        discription.setMovementMethod(new ScrollingMovementMethod());
        discription.setText(articlesDto.getDescription());
        String linkText = new String("Go to website....");
        SpannableString content = new SpannableString(linkText);
        content.setSpan(new UnderlineSpan(), 0, linkText.length(), 0);
        link.setText(content);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(DetailActivity.this, WebViewActivity.class);
                detailIntent.putExtra("MyClass", articlesDto);
//        Toast.makeText(this, "You clicked " + newsAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
                startActivity(detailIntent);
            }
        });
    }
}
