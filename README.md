```💡 FastCampus 강의 수강 및 정리```

### Book_Review
+ RecyclerView
+ View Binding
+ Retrofit [🧷](https://square.github.io/retrofit/)
+ Glide
+ RoomDB (migration)
+ Open API [인터파크 도서 - 북피니언](http://book.interpark.com/blog/bookpinion/bookpinionOpenAPIInfo.rdo)
 
### 구현기능
+ 인터파크 Open API를 통해 베스트셀러 정보를 가져와서 RecyclerView 화면에 그린다.
+ 인터파크 Open API를 통해 검색어에 해당하는 책 목록을 가져온다.
+ RoomDB를 이용해 검색기록을 저장하고 삭제한다.
+ RoomDB를 이용해 리뷰를 남기고 저장한다.

### Postman
크롬 웹스토어 확장기능중에 Postman 이라고있는데 api반환 결과값을 쉽게 보여주는 기능을 제공한다. 이를 통해 간단하기 API가 정상 동작하는지 미리 확인이 가능하다.  

### 스크린샷  
<img src="https://user-images.githubusercontent.com/63087903/119836552-c93b9400-bf3c-11eb-9a10-471ec545e2dd.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836556-c9d42a80-bf3c-11eb-8609-390792e71cae.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836564-cb055780-bf3c-11eb-9973-6d129a92316f.jpg" width="200" height="430"> <img src="https://user-images.githubusercontent.com/63087903/119836560-ca6cc100-bf3c-11eb-856e-78bf61618f3d.jpg" width="200" height="430">

### [2021-07-09]
### [2021-07-26]
### [2021-08-03 ~ ing]
다우기술 서류전형에 덜컥 합격해버리고 인적성 cos pro 준비하느라 1주정도 공백,,  
#### ◼ manifests
+ uses-permission 인터넷 권한 추가
 ```KOTLIN
 <uses-permission android:name="android.permission.INTERNET"/>
 ```
+ DetailActivity 추가
 ```KOTLIN
 <activity android:name =".DetailActivity"/>
 ```
#### ◼ build.gradle
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
#### ◼ xml
+ drawable
 ```KOTLIN
 * 이미지 영역을 특정하기위한 백그라운드 이미지
 <shape> android:shape = "rectangle"  // 사각형
 <stroke> width = 1dp, color = "@color/gray"  // 테두리의 굵기는 1dp, 색상은 회색
 <coners> radius = "16dp"   // 라운드한 코너
 
 * 검색기록 삭제를 표시하는 vetor 이미지
 ```
+ layout  
 1. activity_main.xml
  ```KOTLIN
  * 검색을 위한 EditText 1개와 북리스트들을 보여주는 recyclerView 1개 그리고 검색기록을 보여주는 recylcerView 1개가 존재
  각 뷰들은 contraintlayout으로 배치 하였으며, 초기에는 검색창 아래에 북리스트들이 보여지는 형태이고,
  검색창 클릭시에는 모션이벤트를 지정하여 검색기록 recylclerView가 화면을 덮는다.
  ```
 3. activity_detail.xml  
 ```KOTLIN
 * 북리스트의 아이템을 클릭하였을때 해당 아이템의 상세정보(도서이미지, 설명)를 띄워주며, 리뷰를 남길 수 있다.
 * 이 때, 작성한 리뷰는 RommDB를 통해 저장하게된다.
 ```
 4. item_book.xml
  ```KOTLIN
  * activity_main의 북리스트 recyclerView의 아이템
  이 때 책의 이미지는 Glide를 통해 받아오고 테두리는 위에서 지정한 drawablelayout을 백그라운드로 두었다.
  책의 제목과 간략한 내용은 textView에 입력되며 android:ellipsize="end" 를 통해 넘어가는 부분은 ... 으로 표시하였다.
  ```
 5. item_history.xml  
 ```KOTLIN
 * activity_main의 검색기록 recyclerView의 아이템
 검색기록은 textView에 보여지고 검색기록 삭제는 위에서 지정한 vector 이미지를 버튼으로 두었다.
 ```
 
#### 사전지식 간략정리
```KOTLIN 
RecyclerView, RoomDB, Retrofit, Glide
```
+ RecyclerView [📌](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ko)
+ Retrofit [📌](https://square.github.io/retrofit/), [📌](https://github.com/square/retrofit)
+ Glide [📌](https://bumptech.github.io/glide/), [📌](https://github.com/bumptech/glide)
+ 이전에 진행한 프로젝트에서 정리한 RoomDB [🥕](https://github.com/h0keun/Calculator)
+ RoomDB migration [📌](https://developer.android.com/training/data-storage/room/migrating-db-versions?hl=ko)

#### ◼ kotlin.class
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
