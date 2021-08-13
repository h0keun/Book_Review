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
### [2021-08-12]

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
```KOTLIN
* 정의 : 데이터 집합들을 각각의 개별 아이템 단위로 구성하여 화면에 출력해주는 뷰 그룹이며,
         수많은 데이터를 스크롤 가능한 리스트의 형태로 표시해주는 위젯을 의미한다.
         리스트뷰와 비교하여 가장 큰 차이점은 ViewHolder를 이용하는지 여부에 있다.
  
* 주요 클래스
 1. xml
  : 리사이클러뷰를 사용할 엑티비티.xml 에 RecyclerView 를 추가하고
    리사이클러뷰에 담길 각 아이템(데이터)들은 아이템.xml 을 만들어서 뷰홀더와 어댑터 등을 활용하여 inflate
    
 2. ViewHolder 생성
  : 각각의 뷰를 보관하는 Holder객체 이다. Item 뷰들을 재활용하기위해 각 요소를 저장해두고 사용하게 된다.
    아이템 생성시 뷰바인딩은 한 번만 하며, 그 바인딩 된 객체를 가져다 사용한다.
    
    뷰홀더가 생성되었을 때는 뷰홀더에 연결된 데이터가 없는 상태이기 때문에,
    뷰홀더 생성이후 리사이클러뷰가 뷰홀더를 뷰의 데이터에 바인딩하게된다.
    
    뷰홀더는 현재 화면에 보이는 아이템 개수만큼 생성되고, 
    새롭게 그려져야할 아이템이 있다면(스크롤시) 가장 위의 ViewHolder를 재사용해서 데이터만 바꾸게 된다. 
    
    즉, 리스트뷰와 비교했을 때 수많은 아이템 레이아웃을 생성하여 자원을 낭비하는 것이 아니라, 
    재활용 함으로써 앱의 효율성을 상승시킨것이다.
 
 3. Adapter 생성
  : 리사이클러뷰가 뷰를 요청하면, 어댑터에서 메서드를 호출하여 데이터를 뷰에 바인딩한다.
    (뷰홀더를 호출하고, 뷰홀더를 바인딩하는 역할)
    클릭시 액션처리라던가 아이템을 몇개 생성할지, 아이템의 포지션을 어떤지 등등 과정들을 어댑터에서 처리한다.
    
    쉽게 말하자면, 데이터와 뷰를 잇는 일종의 다리역할이라고 볼 수 있다.
    
    어댑터를 정의할 때 재정의해야하는 핵심 메서드 3가지는 아래와 같다.
    onCreatViewHolder() 
    : 리사이클러뷰는 뷰홀더를 새로 만들어야 할 때마다 이 메서드를 호출한다. 
    이 메서드는 뷰홀더와 그에 연결된 뷰를 생성하고 초기화 하지만, 뷰의 콘텐츠를 채우는것은 아니다. 
    왜냐하면 뷰홀더가 아직 특정 데이터에 바인딩된 상태가 아니기 때문!
    onBindViewHolder() 
    : 리사이클러뷰는 뷰홀더를 데이터와 연결할때 이 메서드를 호출한다.
    적절한 데이터를 가져와서 그 데이터를 사용, 뷰홀더의 레이아웃(아이템.xml)을 채운다.
    getItemCount()
    : 리사이클러뷰는 데이터 세트 크기를 가져올 때 이 메서드를 호출한다.
    주소록 앱을 예로들면 총 주소 개수가 여기에 해당한다.
    리사이클러뷰는 이 메서드를 사용하여, 항목을 추가로 표시할 수 없는 상황을 확인한다.
     
 4. LayoutManager 생성
  : 아이템의 배치를 담당한다.
    LinearLayoutManager : 가로 / 세로
    GridLayoutManager : 그리드
    StaggeredGridLayoutManager : 지그재그형식의 그리드

여기까지가 기본적인 리사이클러뷰의 요약 내용이고 조금더 고급맞춤설정은 아래의 링크를 통해 확인하자!
https://developer.android.com/guide/topics/ui/layout/recyclerview-custom?hl=ko

또한, 이외에도 다양한 방법으로 리사이클러뷰를 사용하는데, 대표적으로 본 프로젝트에서 사용한 DiffUtil도 그중 하나이며, 
리사이클러뷰를 잘 이해한다면 여러가지 효율적인 방법들을 어렵지 않게 사용할 수 있다고 생각한다.
```
+ Retrofit [📌](https://square.github.io/retrofit/), [📌](https://github.com/square/retrofit)
```KOTLIN
* Restful API와 원할한 통신을 도와주는 Square 사의 라이브러리로, 
  안드로이드 개발시 네트워크통신이 필요할 때 거의 국룰로 사용하는 라이브러리 이다.
  REST 기반의 웹 서비스를 통해 JSON 구조의 데이터를 쉽게 가져오고 업로드 할 수 있다.
  즉, 안드로이드 앱에서 필요한 데이터를 서버로부터 가져고 서버에 데이터를 전송하기위한 것!
  
  사용시, build.gradle에 라이브러리를 추가하고 Manifest에 인터넷권한을 추가해야한다.
  
* 주요 클래스
1. JSON 형태의 모델 클래스 (data class ~Dto)
   > 서버 통신시 request body 또는 response body에서 사용할 Json 형태의 모델 클래스를 작성
     이때, 프로퍼티의 변수명은 실제 서버에서 사용하는 값과 똑같이 작성해야함!
     만약 앱 내에서 변수명을 다르게 사용하고 싶은 경우 @SerializedName 어노테이션을 사용하면 된다.
     
     // 약어 DTO 란 data transfer object로 데이터 전송 객체를 의미한다.(data class)
     // RoomDB 에서 언급되었던 약어 DAO는 data access object로 데이터 접근 객체를 의미한다.(interface)
     
2. HTTP 작업을 정의하는 인터페이스 (interface ~Service)
   > GET, POST 등 사용하여 서버에서 받아오거나 서버로 보낸다

3. Retrofit.Builder() 를 선언한 클래스 (baseUrl, addConverterFactory 등을 선언) 
   > 인터페이스는 구현체가 아니라 동작의 정의만 되어있는것이기 때문에, 클래스에서 따로 구현체를 만들어줘야 한다.
   
// 사용순서는 위에 언급한 내용들을 매칭시키면서 확인해볼것! //

* 사용순서
1. build.gradle에 라이브러리 추가 
   : implementation 'com.squareup.retrofit2:retrofit:2.9.0' // 레트로핏 (필수)
     implementation 'com.squareup.retrofit2:converter-gson:2.9.0' // 컨버터 (선택)

2. Manifest에 인터넷사용권한 추가
   : <uses-permission android:name="android.permission.INTERNET"/>

3. Retrofit 객체 생성
   : Retrofit을 사용하기위해 Retrofit객체를 생성해야하는데, 보통 메서드를 선언해두고 재사용한다.
     빅더 객체를 생성하고 필요한 설정사항을 추가해서 빌드한다.
     * baseUrl은 URL호출시 반복을 줄여주는 역할을 하는데,
       사이트 주소를 보면 대게 www.~~.com/ 까지는 고정이고 /이후 부분이 변하는 부분이기 때문에
       변하는 부분을 제외한 /앞쪽 www.~~.com/ 까지를 baseUrl로 세팅한다.
     * addConverterFactory는 HTTP통신시에 주고받는 데이터 형태를 변환시켜주는 컨버터를 지정하는 설정이다.
       Gson, Jacson 등 다양한데 본 프로젝트에서는 Gson컨버터를 지정하였다.
     
4. 서비스 클래스 생성
   : 여기서말하는 Service는 레트로핏의 용어를 지칭하는데, 
     Retrofit에서 Service는 API를 정의하는 인터페이스를 말한다.
     
     인터페이스로 선언하기 때문에 구현할 필요는 없고 어떤 형태와 방식으로 통신하는지를 
     어노테이션과 파라미터로 지정하면 Retrofit이 알아서 구현해준다.
     
     Service는 기본적으로 Call<T> 객체를 반환한다.
     (본 프로젝트에서는 Call<SearchBookDto> 과 Call<BestSellerDto> 반환) 
     만약 서버의 API가 String을 반환한다고 가정하면 클라이언트는 Retrofit을 통해 Call<String>을 받게 되는 것이며, 
     일반적으로는 JSON 형태의 모델 클래스 (data class ~Dto) 를 통해 받는다.
     
5. Retrofit - Service 연결하기 (Call<T> 객체 얻기 및 사용하기)
   : 3. 과정에서 Retrofit객체를 만들었으면 클라이언트와 서버가 통신할 통로가 열린 상태라고 볼 수 있다.
     본 프로젝트에서는 객체 생성이후에 바로 아래와 같은 코드를 MainActivity상에 입력하여,
     Call<T>객체를 바로 사용하였다. 
     
     bookService = retrofit.create(BookService::class.java)

     bookService.getBestSellerBooks(getString(R.string.interParkAPIKey))
         .enqueue(object : Callback<BestSellerDto> {

             override fun onResponse(
                 call: Call<BestSellerDto>,
                 response: Response<BestSellerDto>
             ) {
                 // todo 성공처리

                 if (response.isSuccessful.not()) {
                     return
                 }
                 response.body()?.let {
                     Log.d(TAG, it.toString())

                     it.books.forEach { book ->
                         Log.d(TAG, book.toString())
                     }
                     adapter.submitList(it.books)
                 }
             }

             override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                 // todo 실패처리
             }
        })
  
   * 하지만, 실제 개발시에는 재사용성을 높이고 유지보수를 편하게 하기위해
     MVVM 패턴을 적용하여 중간 매개체 역할을 하는 클래스인 Repository를 만들어두고,
     필요에 따른 Retrofit 생성 메서드를 저장한다고 한다!!
     (https://kyome.tistory.com/148 이 링크를 참조하자!)
     
   Call<T> 객체의 사용은 다음과 같다.
   Call<T> 는 인터페이스이며, Call인터페이스는 enqueue(Callback<T> callback) 메서드를 가지고 있어야 한다.
   그러므로 통신을 한 후에 받은 Call<T>객체는 enqueue가 구현된 상태 라고 볼 수 있다.
   
   이를통해 받은 통신의 결과에 대한 후처리를 할 수 있다.
   Retrofit은 통신의 결과에 따라 파라미터로 받는 Callback의 메서드를 실행해준다.
   성공시 onResponse를 실행하고, 실패시 onFailure를 실행한다.
  
```
+ Glide [📌](https://bumptech.github.io/glide/), [📌](https://github.com/bumptech/glide)
```KOTLIN
* 안드로이드에서 이미지를 빠르고 효율적으로 불러올 수 있게 도와주는 bumptech의 라이브러리로
  사용방법도 간단하고 확장성도 넓어서 안드로이드에서 두루사용하는 라이브러리이다. 
  비슷한 라이브러리로는 Picaso가 있는데 속도, 성능 면에서 glide가 우위라는 의견이 많다.
  
  ImageView에 사진을 띄우고자 하는 여러가지 경우가 있는데
  안드로이드 앱 안의 drawable폴더의 리소스를 보여주는 경우와,
  안드로이드 디바이스 안에 저장되어있는 사진을 보여주는 경우(갤러리 등), 그리고
  이미지 URL을 로드해서 보여주는 경우가 있다.
  앞선 두가지 경우는 안드로이드 기기 내부 리소스를 불러오는 작업이기 때문에 예외사항도 적고, 구현도 복잡하지 않다.
  그러나 마지막 경우처럼 이미지의 URL을 불러오는 경우에는 여러가지 고려해야할 사항이 많다.
  예를들면 로딩 실패시 처리, 재시도 처리, out of memory, 캐시, 병렬처리, 디코딩, 이미지 재활용 등등..
  이를 위해 HTTP를 통한 이미지 로딩시 안정적으로 처리하기위한 라이브러리로 Glide를 많이 사용한다.
  
  
  
* 사용방법은 정말 간단하다.
1. build.gradle에 라이브러리 추가 
implementation 'com.github.bumptech.glide:glide:4.12.0'

2. 외부로부터(HTTP) 이미지를 가져오는 경우 Manifest에 인터넷권한 추가
<uses-permission android:name="android.permission.INTERNET"/>

3. 뷰에 이미지 로드하기
특별한 옵션없이 단순하게 ImageView에 이미지를 할당하는 것이라면 with(), load(), into()로 표현이 가능하다.
(아래 예시는 리사이클러뷰 어뎁터에서 사용한 모습이며 retrofit을 통해 받아온 이미지 URL을 load 하는 모습이다. + viewbinding 사용)
Glide
   .with(binding.coverImageView.context)
   .load(bookModel.coverSmallUrl)
   .into(binding.coverImageView)

   // load 안에는 꼭 URL이 들어가야하는것이 아니라 uri, drawble 등도 사용이 가능하다.
  
* 주요 함수 : 괄호안에 들어갈 내용
with() : View, Fragment 또는 Activity로 부터 Context를 가져온다. (보통 this)
load() : 이미지를 로드한다.(Bitmap, Drawable, String, Uri, File, ResourID, ByteArray 등)         
into() : 이미지를 보여줄 View를 지정한다.

위 세개가 뼈대가되는 함수들이며 추가적으로 다음의 경우의 처리도 가능하다.

placeholder() : Glide로 이미지 로딩을 시작하기 전에 보여줄 이미지 설정 
                ex) .placeholder(R.drawable.loading)
error() : 이미지리소스를 불러오는데 에러가 발생할 경우 보여줄 이미지 설정
          ex) .error(R.drawable.error)
fallback() : 로드할 이미지 Url이 null인 경우 혹은 비어있는 경우 보여줄 이미지 설정
skipMemoryCache() : 메모리에 캐싱하지 않으려면 true로 설정
diskCacheStrategy() : 디스크에 캐싱하지 않으려면 DiskCacheStrategy.NONE로 설정
asGif() : gif 이미지 불러오기
override() : 이미지 사이즈 
             ex) .override(600, 300)
```
+ 이전에 진행한 프로젝트에서 정리한 RoomDB [🥕](https://github.com/h0keun/Calculator)
+ RoomDB migration [📌](https://developer.android.com/training/data-storage/room/migrating-db-versions?hl=ko)


💡 본 프로젝트를 리뷰하기위한 사전지식들은 위에 짤막하게 정리하였으니  
   위 개념들을 토대로 아래에 프로젝트를 다시한번 살펴보며 리뷰하겠따!  

#### ◼ kotlin.class
```KOTLIN
* 프로젝트 구조 살펴보기
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

#### 
