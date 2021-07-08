```💡 FastCampus 강의 수강 및 정리```

### Book_Review
+ RecyclerView
+ View Binding
+ Retrofit [🧷](https://square.github.io/retrofit/)
+ Glide
+ RoomDB
+ Open API
 
### 구현기능
+ 인터파크 Open API를 통해 베스트셀러 정보를 가져와서 RecyclerView 화면에 그린다.
+ 인터파크 Open API를 통해 검색어에 해당하는 책 목록을 가져온다.
+ RoomDB를 이용해 검색기록을 저장하고 삭제한다.
+ RoomDB를 이용해 리뷰를 남기고 저장한다.

### Postman
크롬 웹스토어 확장기능중에 Postman 이라고있는데 api반환 결과값을 쉽게 보여주는 기능을 제공한다. 


<img src="https://user-images.githubusercontent.com/63087903/119836552-c93b9400-bf3c-11eb-9a10-471ec545e2dd.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836556-c9d42a80-bf3c-11eb-8609-390792e71cae.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836564-cb055780-bf3c-11eb-9973-6d129a92316f.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836560-ca6cc100-bf3c-11eb-856e-78bf61618f3d.jpg" width="200" height="430">


### [2021-07-08]
#### manifests
+ uses-permission 인터넷 권한 추가
 ```KOTLIN
 <uses-permission android:name="android.permission.INTERNET"/>
 ```
+ DetailActivity 추가
 ```KOTLIN
 <activity android:name =".DetailActivity"/>
 ```
#### build.gradle
+ viewbinding 사용
 ```KOTLIN
 buildFeatures {
     viewBinding true
 }
 ```
+ Room, Glide, Retrofit, gson_converter 추가
 ```KOTLIN
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
 implementation 'com.github.bumptech.glide:glide:4.12.0'
 implementation 'androidx.room:room-runtime:2.2.6'
 kapt 'androidx.room:room-compiler:2.2.6'
 ```
#### xml
+ drawable
 ```KOTLIN
 * 이미지 영역을 특정하기위한 백그라운드 이미지
 <shape> android:shape = "rectangle"  // 사각형
 <stroke> width = 1dp, color = "@color/gray"  // 테두리의 굵기는 1dp, 색상은 회색
 <coners> radius = "16dp"   // 라운드한 코너
 
 * 검색기록 삭제를 위한 vetor 이미지
 ```
+ layout  
 1.activity_main.xml  
 2.activity_detail.xml  
 3.item_book.xml  
 4.item_history.xml  
 ```KOTLIN
 * activity_main
 
 
 * activity_detail  
 
 
 * item_book  
 
 
 * item_history
 ```
 
#### kotlin.class
```KOTLIN
📂 app ⁅
   📂 manifest
   📂 java ⁅
      📂 com ⁅
         📂 com ⁅
            📂 book_review ⁅
               📂 adapter
                   ‣ BookAdapter
                   ‣ HistoryAdapter
               📂 api
                   ‣ BookService
               📂 dao
                   ‣ HistoryDao
                   ‣ ReviewDao
               📂 model
                   ‣ BestSellerDto
                   ‣ Book
                   ‣ History
                   ‣ Review
                   ‣ SearchBookDto
               ‣ AppDatabse.kt
               ‣ DetailActivity
               ‣ MainActivity            
```


💡 어댑터 확실히 알기, 리사이클러뷰 확실히 알기, retrofit, glide 심화공부, 한글깨짐현상 해결하기
