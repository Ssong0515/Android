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

## Interface를 이용 해서 Class 간 데이터 전달(swift의 delegate 개념)
