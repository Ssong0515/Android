# 안드로이드 메모

## 안드로이드 DB

### SQLiteOpenHelper
- 안드로이드에서 제공하는 데이터베이스 관리 클래스

#### DB 생성
- 상속 받아서 (context, DB명, factory, 버전) 생성자 호출

  - onCreate() : 최초 한번 호출
  - onUpgrade() : 버전이 변경될 때 호출

  - execSQL(sql구문) : sql구문을 실행하는 메서드
  - insert(table, null, values) : 이렇게 명령문으로 추가도 가능

- SQLiteDatabase
  - .execSQL()은 DDL
  - .rawQuery()는 DML

### Interface를 이용 해서 Class 간 데이터 전달(swift의 delegate 개념)

## retrofit을 이용한 서버와 데이터 통신
- 서버와 통신 하기 위해 중간 변환 역할

1. retrofit 의존성 추가
2. 주고 받을 객체 생성 (data class)
3. 엔드포인트 interface 설정
4. retrofit 서버 설정 구성

- enqueue(object: retrofit2.Callback<반환값>{...}) : 반환값이 있을 경우 받아오는 콜백 함수

## spring에 간단하게 DB 연결

- application.properties
`spring.application.name=androidPhone`
`server.port=8899`

`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`
`spring.datasource.url=jdbc:mysql://localhost:3306/androidDB?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8`
`spring.datasource.hikari.username=root`
`spring.datasource.hikari.password=full405`

`spring.devtools.livereload.enabled=true`

`spring.jpa.show-sql=true`
`spring.jpa.hibernate.ddl-auto=update`

- 안드로이드 (@Path) -> 스프링 (@PathVariable)
- 안드로이드 (@Body) -> 스프링 (@RequestBody)

- update (service 클래스)
  - 영속성에 있는 객체를 불러와 수정 : 더티체킹
  - @Transactional : 서비스 클래스에서 사용 가능, 업데이트 하면 자동으로 flush가 반영되어 변경 저장 됨
  