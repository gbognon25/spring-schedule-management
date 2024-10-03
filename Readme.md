# API 명세서

## 1. 일정 생성
- **Method**: POST
- **URL**: /api/schedule
- **Request Body(JSON)**: {"title":"할일", "task":"내용", "author":"작성자명", "password":"비밀번호"}
- **Response (JSON)**: { "id": 1, "title": "할일", "task": "내용", "author": "작성자명", "createdAt": "YYYY-MM-DD hh:mm:ss", "updatedAt": "YYYY-MM-DD hh:mm:ss" }
- **상태 code**: 201 (CREATED)

## 2. 전체 일정 조회
- **Method**: GET
- **URL**: /api/schedule
- **Query Params(Optional)**: author, updatedAt
- **Response (JSON)**: [ { "id": 1, "title": "할일", "task": "내용", "author": "작성자명", "createdAt": "YYYY-MM-DD hh:mm:ss", "updatedAt": "YYYY-MM-DD hh:mm:ss" } ]
- **상태 code**: 200(OK)

## 3. 선택 일정 조회
- **Method**: GET
- **URL**: /api/schedule/{id}
- **Path Variable**: id
- **Response (JSON)**: { "id": 1, "title": "할일", "task": "내용", "author": "작성자명", "createdAt": "YYYY-MM-DD hh:mm:ss", "updatedAt": "YYYY-MM-DD hh:mm:ss" }
- **상태 code**: 200(OK)

## 4. 일정 수정
- **Method**: PUT
- **URL**: /api/schedule/{id}
- **Path Variable**: id
- **Request Body (JSON)**: { "title": "할일", "author": "작성자명" }
- **Query Param**: password=비밀번호
- **Response (JSON)**: { "id": 1, "title": "할일", "task": "내용", "author": "작성자명", "createdAt": "YYYY-MM-DD hh:mm:ss", "updatedAt": "YYYY-MM-DD hh:mm:ss" }
- **상태 code**: 200(OK)

## 5. 일정 삭제
- **Method**: DELETE
- **URL**: /api/schedule/{id}
- **Path Variable**: id
- **Query Param**: password=비밀번호
- **Response**: 내용 없음
- **상태 code**: 204 (NO CONTENT)

# Entity 생성
## Entity name
Schedule

## Attributes
1. id: 고유 식별자
2. title: 할일 (일정의 제목)
3. task: 일정의 내용
4. author: 작성자명
5. password: 비밀번호
6. createdAt: 일정의 작성일
7. updatedAt: 일정의 수정일


![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FJvL0o%2FbtsJT6BSgtL%2FTzJG4RGueDaXaGrnrUxRr1%2Fimg.png)