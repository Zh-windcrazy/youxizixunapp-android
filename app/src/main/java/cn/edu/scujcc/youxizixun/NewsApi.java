package cn.edu.scujcc.youxizixun;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("/news")
    Call<List<News>> getAllNews();
}
