package com.com.book_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.com.book_review.api.BookService
import com.com.book_review.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder() // 인터페이스형식은 구현체가 아님(동작의 정의만 되어있는것) 따라서 이렇게 따로 구현체를 만들어주어야함
                .baseUrl("https://book.interpark.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("private")
                .enqueue(object: Callback<BestSellerDto> {

                    override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                        // todo 성공처리

                        if(response.isSuccessful.not()){
                            return
                        }
                        response.body()?.let {
                            Log.d(TAG, it.toString())

                            it.books.forEach { book ->
                                Log.d(TAG, book.toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                        // todo 실패처리
                    }

                })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}