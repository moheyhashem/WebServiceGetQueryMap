package com.example.webservicegetquerymap;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public class MainActivity extends AppCompatActivity {
    TextView contentTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public interface NewsService{
        @GET ("top-headlines") Call<Void> getNews(@QueryMap Map<String,String> QueryParameters);

    }
    public void LoadData(View view){

        String NEWS_API_URL ="http://newsapi.org/v2";
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();
        NewsService newsService = retrofit.create(NewsService.class);
        Map<String,String> parms = new HashMap<>();
        parms.put("apiKey","4afafad986e2458d95ab9eab3e71bcce");
        parms.put("country","us");
        parms.put("category","business");
        newsService.getNews(parms).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                contentTV.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}
