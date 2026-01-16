# Lost & Found - 분실물 관리 시스템

Android 기반 분실물/습득물 관리 애플리케이션

![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white)
![PHP](https://img.shields.io/badge/PHP-777BB4?style=flat&logo=php&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)

## 📌 프로젝트 개요

학교, 회사, 공공장소 등에서 발생하는 분실물과 습득물을 효율적으로 관리하기 위한 Android 애플리케이션입니다. 사용자는 분실물을 등록하고, 카테고리별로 검색하여 자신의 물건을 찾을 수 있습니다.

### 주요 특징
- 📱 **모바일 최적화**: Android Native 앱으로 빠른 성능
- 🔐 **회원 시스템**: 로그인/회원가입으로 개인정보 관리
- 🗂️ **카테고리 분류**: 전자기기, 지갑, 카드 등 체계적 분류
- 🔄 **실시간 업데이트**: 새로고침으로 최신 정보 확인
- ✏️ **CRUD 완벽 지원**: 등록, 조회, 수정, 삭제 기능

## 🎯 사용 사례

- 🏫 **학교**: 학생들의 분실물 관리
- 🏢 **회사**: 사무실 내 분실물 센터 운영
- 🚇 **대중교통**: 지하철, 버스 분실물 센터
- 🏛️ **공공기관**: 도서관, 체육관 등 분실물 관리

## 🛠️ 기술 스택

### Frontend (Android)
- **Language**: Java
- **Min SDK**: API 21 (Android 5.0 Lollipop)
- **Target SDK**: API 30+ (Android 11+)
- **Build Tool**: Gradle
- **IDE**: Android Studio

### Backend
- **Language**: PHP
- **Database**: MySQL
- **Server**: Apache/Nginx

### Libraries
- **Volley**: HTTP 네트워킹
- **RecyclerView**: 목록 표시
- **SwipeRefreshLayout**: 새로고침 기능
- **AppCompat**: 하위 호환성

## 📂 프로젝트 구조

```
lost_Found/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/calender/
│           │   ├── MainActivity.java              # 로그인 화면
│           │   ├── membership.java                # 회원가입 화면
│           │   ├── detailsCalender.java          # 분실물 목록
│           │   ├── scheduleForm.java             # 등록/수정/삭제
│           │   ├── ModifyInformation.java        # 회원정보 수정
│           │   │
│           │   ├── model/
│           │   │   └── Schedule.java             # 분실물 데이터 모델
│           │   │
│           │   ├── requests/
│           │   │   ├── LoginRequest.java         # 로그인 요청
│           │   │   └── schedule/
│           │   │       ├── FilteredFoundListRequest.java  # 목록 조회
│           │   │       ├── ScheduleUpdateRequest.java     # 수정
│           │   │       └── ScheduleRemoveRequest.java     # 삭제
│           │   │
│           │   └── ScheduleAdapter.java          # RecyclerView 어댑터
│           │
│           ├── res/
│           │   ├── layout/
│           │   │   ├── activity_main.xml              # 로그인 UI
│           │   │   ├── activity_details_calender.xml # 목록 UI
│           │   │   └── activity_schedule_form.xml    # 등록/수정 UI
│           │   │
│           │   ├── values/
│           │   │   ├── strings.xml               # 문자열 리소스
│           │   │   └── colors.xml                # 색상 정의
│           │   │
│           │   └── menu/
│           │       └── menu_schedule_edit.xml    # 삭제 메뉴
│           │
│           └── AndroidManifest.xml               # 앱 설정
│
└── server/                                        # 서버 코드 (별도 저장소)
    ├── login.php                                 # 로그인 API
    ├── insert1.php                               # 등록 API
    ├── update.php                                # 수정 API
    ├── delete.php                                # 삭제 API
    └── filtered_list.php                         # 목록 조회 API
```

## 🚀 주요 기능

### 1. 회원 관리
- ✅ 회원가입
- ✅ 로그인
- ✅ 회원정보 수정

### 2. 분실물 관리
- ✅ 분실물 등록
- ✅ 카테고리별 조회
- ✅ 상세 정보 확인
- ✅ 분실물 수정
- ✅ 분실물 삭제

### 3. 편의 기능
- ✅ 카테고리 필터링 (Spinner)
- ✅ 아래로 당겨서 새로고침
- ✅ 등록/수정 후 자동 목록 갱신

## 📊 카테고리 분류

| 카테고리 | 설명 | 예시 |
|----------|------|------|
| 전자기기 | 스마트폰, 노트북 등 | 아이폰, 갤럭시, 에어팟 |
| 지갑/현금 | 지갑, 현금 | 가죽 지갑, 만원권 |
| 카드 | 신용카드, 교통카드 등 | 신한카드, 티머니 |
| 가방 | 백팩, 핸드백 등 | 검정 백팩, 크로스백 |
| 의류 | 옷, 신발, 모자 등 | 패딩, 운동화 |
| 기타 | 위 항목 외 | 우산, 책, 액세서리 |

## 🎨 화면 구성

### 1. 로그인 화면 (MainActivity)
```
┌────────────────────────────┐
│    Lost & Found            │
│                            │
│  아이디: [______________] │
│                            │
│  비밀번호: [______________] │
│                            │
│  [     로그인     ]        │
│  [    회원가입    ]        │
└────────────────────────────┘
```

### 2. 분실물 목록 (detailsCalender)
```
┌────────────────────────────────┐
│  [←]  분실물 목록       [내정보] │
├────────────────────────────────┤
│  카테고리: [전체 ▼]            │
├────────────────────────────────┤
│  ┌──────────────────────────┐ │
│  │ 전자기기 | 아이폰 분실    │ │
│  │ 강남역 | 2025-01-15      │ │
│  ├──────────────────────────┤ │
│  │ 지갑 | 검정 가죽지갑      │ │
│  │ 홍대입구 | 2025-01-14    │ │
│  └──────────────────────────┘ │
├────────────────────────────────┤
│          [+ 등록하기]          │
└────────────────────────────────┘
```

### 3. 등록/수정 화면 (scheduleForm)
```
┌────────────────────────────────┐
│  분실물 등록              [삭제] │
├────────────────────────────────┤
│  카테고리: [전자기기 ▼]        │
│                                │
│  제목: [아이폰 14 Pro 분실]    │
│                                │
│  내용:                         │
│  [검정색 아이폰 14 Pro입니다.] │
│  [강남역 3번 출구 근처에서]    │
│  [분실했습니다.]               │
│                                │
├────────────────────────────────┤
│  [   등록   ]    [   취소   ]  │
└────────────────────────────────┘
```

## ⚙️ 설치 및 실행

### 1. 사전 요구사항
- Android Studio (최신 버전)
- JDK 8 이상
- Android SDK (API 21+)
- PHP 7.0+ (서버)
- MySQL 5.7+ (데이터베이스)
- Apache/Nginx 웹 서버

### 2. 데이터베이스 설정

#### MySQL 테이블 생성
```sql
-- 회원 테이블
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 분실물 테이블
CREATE TABLE schedules (
    numberId INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    ga VARCHAR(50) NOT NULL,           -- 카테고리
    title VARCHAR(200) NOT NULL,       -- 제목
    content TEXT,                      -- 내용
    location VARCHAR(100),             -- 분실 위치
    status VARCHAR(20) DEFAULT '분실',  -- 상태 (분실/습득/완료)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 샘플 데이터
INSERT INTO users VALUES ('test', 'test1234', '테스트유저', 'test@example.com', NOW());
INSERT INTO schedules (user_id, ga, title, content, location) 
VALUES ('test', '전자기기', '아이폰 분실', '검정색 아이폰 14 Pro', '강남역');
```

### 3. 서버 설정 (PHP)

#### config.php (데이터베이스 연결)
```php
<?php
$host = "localhost";
$username = "root";
$password = "your_password";
$database = "lost_found_db";

$conn = mysqli_connect($host, $username, $password, $database);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

mysqli_set_charset($conn, "utf8");
?>
```

#### login.php
```php
<?php
require_once('config.php');

$id = $_POST['id'];
$password = $_POST['password'];

$sql = "SELECT * FROM users WHERE id='$id' AND password='$password'";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    $row = mysqli_fetch_assoc($result);
    echo json_encode(array(
        "success" => true,
        "id" => $row['id'],
        "password" => $row['password']
    ));
} else {
    echo json_encode(array("success" => false));
}

mysqli_close($conn);
?>
```

#### insert1.php
```php
<?php
require_once('config.php');

$ga = $_POST['ga'];
$title = $_POST['title'];
$content = $_POST['content'];

$sql = "INSERT INTO schedules (user_id, ga, title, content) VALUES ('test', '$ga', '$title', '$content')";

if (mysqli_query($conn, $sql)) {
    echo json_encode(array("success" => true));
} else {
    echo json_encode(array("success" => false));
}

mysqli_close($conn);
?>
```

### 4. Android 앱 설정

#### build.gradle (Module: app)
```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'com.android.volley:volley:1.2.1'
}
```

#### API 서버 주소 설정
`strings.xml` 또는 `BuildConfig`에 서버 URL 추가:
```xml
<string name="api_base_url">http://your-server-ip</string>
```

코드에서 하드코딩된 IP 변경:
```java
// scheduleForm.java
String apiUrl = getString(R.string.api_base_url) + "/insert1.php";
postRequest(apiUrl, bodyMap);
```

### 5. 앱 빌드 및 실행

```bash
# 프로젝트 클론
git clone https://github.com/yesgosu/lost_Found.git
cd lost_Found

# Android Studio에서 프로젝트 열기
# File → Open → lost_Found 폴더 선택

# 빌드 및 실행
./gradlew assembleDebug
./gradlew installDebug

# 또는 Android Studio에서 Run 버튼 클릭
```

## 🔄 사용 흐름

### 일반 사용자 시나리오
```
1. 앱 설치 및 회원가입
2. 로그인
3. 분실물 목록 확인
4. 카테고리 선택하여 검색
5. 내 물건 발견 시 상세 정보 확인
6. 찾지 못한 경우 새로운 분실물 등록
```

### 습득자 시나리오
```
1. 로그인
2. [+ 등록하기] 버튼 클릭
3. 카테고리 선택 (습득물)
4. 제목, 내용, 위치 입력
5. 등록 완료
```

## 📡 API 엔드포인트

### 회원 관리
| Method | Endpoint | 설명 | Parameters |
|--------|----------|------|------------|
| POST | `/login.php` | 로그인 | id, password |
| POST | `/register.php` | 회원가입 | id, password, name, email |
| POST | `/update_user.php` | 정보 수정 | id, name, email |

### 분실물 관리
| Method | Endpoint | 설명 | Parameters |
|--------|----------|------|------------|
| POST | `/insert1.php` | 등록 | ga, title, content |
| GET | `/filtered_list.php` | 목록 조회 | category |
| POST | `/update.php` | 수정 | numberId, ga, title, content |
| POST | `/delete.php` | 삭제 | numberId |

### 응답 형식 (JSON)
```json
{
  "success": true,
  "result": [
    {
      "numberId": 1,
      "ga": "전자기기",
      "title": "아이폰 분실",
      "content": "검정색 아이폰 14 Pro",
      "location": "강남역",
      "created_at": "2025-01-15 10:30:00"
    }
  ]
}
```

## 🐛 문제 해결

### 로그인 실패
```
증상: 올바른 계정 정보를 입력해도 로그인 안 됨
해결:
1. 서버 PHP 파일 인코딩 확인 (UTF-8)
2. 데이터베이스 charset 확인 (utf8mb4)
3. 네트워크 연결 확인
4. 서버 URL 확인
```

### 목록이 표시되지 않음
```
증상: 빈 화면만 표시됨
해결:
1. Logcat에서 에러 확인
2. 서버 응답 JSON 형식 확인
3. 데이터베이스에 데이터 존재 여부 확인
4. Volley 요청 타임아웃 확인
```

### 등록 버튼 눌러도 반응 없음
```
증상: 등록 버튼 클릭 시 아무 일도 일어나지 않음
해결:
1. 서버 insert1.php 파일 존재 확인
2. PHP 에러 로그 확인
3. 네트워크 권한 확인 (AndroidManifest.xml)
4. HTTP 통신 허용 설정 확인
```

### HTTP 통신 오류 (Android 9+)
```
증상: java.io.IOException: Cleartext HTTP traffic not permitted
해결:
AndroidManifest.xml에 추가:
<application
    android:usesCleartextTraffic="true"
    ...>
```

## 🔒 보안 고려사항

### 현재 구현의 보안 이슈
⚠️ **주의**: 이 프로젝트는 학습용으로, 프로덕션 환경에서는 다음 사항을 반드시 개선해야 합니다.

1. **비밀번호 평문 저장**
   - 현재: 데이터베이스에 평문 저장
   - 개선: bcrypt, SHA-256 등 해시 알고리즘 사용

2. **HTTP 통신**
   - 현재: HTTP 프로토콜 사용
   - 개선: HTTPS 적용 (SSL 인증서)

3. **SQL Injection 취약**
   - 현재: Prepared Statement 미사용
   - 개선: PDO 또는 mysqli_prepare 사용

4. **API 키 하드코딩**
   - 현재: 서버 IP가 코드에 직접 입력
   - 개선: BuildConfig 또는 환경 변수 사용

### 권장 보안 개선사항
```java
// 1. 비밀번호 해시화 (서버 측)
$hashed_password = password_hash($password, PASSWORD_BCRYPT);

// 2. Prepared Statement (SQL Injection 방지)
$stmt = $conn->prepare("SELECT * FROM users WHERE id=? AND password=?");
$stmt->bind_param("ss", $id, $password);

// 3. HTTPS 적용
// SSL 인증서 설치 및 Apache/Nginx 설정

// 4. JWT 토큰 인증
// 로그인 시 JWT 발급, 이후 요청에 토큰 사용
```

## 📈 향후 개선 계획

### 기능 추가
- [ ] 사진 업로드 기능
- [ ] 위치 기반 검색 (GPS)
- [ ] 푸시 알림 (새 습득물 등록 시)
- [ ] 채팅 기능 (직접 연락)
- [ ] 신고 기능 (부적절한 게시물)
- [ ] 통계 대시보드
- [ ] QR 코드 스캔

### UI/UX 개선
- [ ] Material Design 3 적용
- [ ] 다크 모드 지원
- [ ] 애니메이션 효과
- [ ] 이미지 갤러리 뷰
- [ ] 검색 기능 강화

### 기술적 개선
- [ ] MVVM 아키텍처 적용
- [ ] Kotlin 마이그레이션
- [ ] Retrofit + RxJava
- [ ] Room Database (오프라인 지원)
- [ ] Firebase 연동
- [ ] JWT 인증

## 🤝 기여하기

버그 리포트, 기능 제안, 코드 개선은 언제나 환영합니다!

### 기여 방법
1. Fork this repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### 코드 스타일
- Java 8 코드 스타일 가이드 준수
- 주석은 한글로 작성
- 변수명은 camelCase 사용

## 📚 참고 자료

### Android 개발
- [Android Developers](https://developer.android.com/)
- [Volley Documentation](https://developer.android.com/training/volley)
- [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)

### PHP & MySQL
- [PHP Manual](https://www.php.net/manual/en/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [PHP PDO Tutorial](https://www.php.net/manual/en/book.pdo.php)

### 관련 프로젝트
- [LED_Control_Arduino](https://github.com/yesgosu/LED_Control_Arduino)
- [LED_Control_Android](https://github.com/yesgosu/LED_Control_Android)
- [WebProJect_POST](https://github.com/yesgosu/WebProJect_POST)

## 📝 라이선스

MIT License

## 👨‍💻 개발자

**yesgosu** - [GitHub](https://github.com/yesgosu)

## 📧 문의

프로젝트 관련 문의는 GitHub Issues를 이용해주세요.

---

**개발 기간**: 2024-2025년  
**마지막 업데이트**: 2026년 1월

⭐ 이 프로젝트가 도움이 되셨다면 Star를 눌러주세요!
