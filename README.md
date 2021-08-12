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

```
+ Glide [📌](https://bumptech.github.io/glide/), [📌](https://github.com/bumptech/glide)
```KOTLIN

```
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
