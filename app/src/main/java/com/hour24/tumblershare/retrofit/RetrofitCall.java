package com.hour24.tumblershare.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCall {

    public static <T> void enqueueWithRetry(Call<T> call, final Callback<T> callback) {

        call.enqueue(new CallbackWithRetry<T>(call) {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                super.onFailure(call, t);
                callback.onFailure(call, t);
            }
        });

    }
}
