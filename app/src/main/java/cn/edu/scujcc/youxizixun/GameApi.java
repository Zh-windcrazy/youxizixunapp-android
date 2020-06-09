package cn.edu.scujcc.youxizixun;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameApi {
    @GET("/game")
    Call<List<Game>> getAllGames();
}
