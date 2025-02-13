# Board

## 📝 개요

이 프로젝트는 Java, JSP, JSTL, Tomcat, H2를 이용하여 아래의 요구사항 명세서를 바탕으로 진행한 텀 프로젝트입니다. 또한, 명세서엔 없지만 자체적으로 페이징 기능 구현을 통해 게시글 목록을 효율적으로 관리할 수 있습니다.

### 요구 사항

- **회원가입**: 사용자는 계정을 생성할 수 있어야 한다.
- **로그인**: 등록된 사용자만 로그인이 가능해야 한다.
- **게시글 작성**: 사용자는 게시글을 작성할 수 있어야 한다.
- **게시글 조회**: 작성된 게시글을 조회할 수 있어야 한다.
- **게시글 수정**: 작성자는 자신이 작성한 게시글을 수정할 수 있어야 한다.
- **게시글 삭제**: 작성자는 자신이 작성한 게시글을 삭제할 수 있어야 한다.
- **관리자 기능 (공지사항)**: 관리자는 공지사항을 게시하고 관리할 수 있어야 한다.
- **관리자 기능 (사용자 관리)**: 관리자는 사용자 계정을 관리할 수 있어야 한다.

### 화면 구성
|회원가입|로그인|메인 - 괸리자|메인 - 사용자|
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/b8653653-05ca-4fde-9c83-2560afe7c428"> | <img src="https://github.com/user-attachments/assets/8b834a2a-3779-48cc-b540-4a7c59e416ad"> | <img src="https://github.com/user-attachments/assets/d47260d9-4cb5-4b99-a2f1-008c4ae681a1"> | <img src="https://github.com/user-attachments/assets/7e8798d6-fd52-4c8c-b8a5-f30755e6df38"> |

| 게시글 작성 | 게시글 상세보기 | 게시글 수정 | 게시글 검색 |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/3049b7f1-2398-4010-a453-3d6abb494f1f"> | <img src="https://github.com/user-attachments/assets/0a1e10a4-738c-4a3c-ad32-31dcddb8a0c3"> | <img src="https://github.com/user-attachments/assets/f2fbce22-3153-461d-a38a-869597a04074"> | <img src="https://github.com/user-attachments/assets/888b99d2-8264-4d57-a2d6-495bde0339d7"> |

| 페이징 1 | 페이징 2 |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/1bebc035-96ea-4195-bfd6-170d6d533b16"> | <img src="https://github.com/user-attachments/assets/17115efd-4f3e-4147-ad71-c9e80c641a32"> |

| 공지사항 - 관리자 | 공지사항 - 사용자 | 사용자 관리 |
|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/a6929ea0-a534-421a-a090-642fc6eb69a7"> | <img src="https://github.com/user-attachments/assets/12d1cba3-416d-4a6b-918a-7b1112635bca"> | <img src="https://github.com/user-attachments/assets/33eec3c9-4b3e-44cc-a2b7-f5f25b83efce"> |

## 🛠 기술 스택

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-ED8B00?style=for-the-badge&logo=jsp&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![H2](https://img.shields.io/badge/H2-4CAF50?style=for-the-badge&logo=h2t&logoColor=white)

💡 기술적 구현 사항
- 서버 통신 : Servlet을 통한 통신
- 환경 변수 설정 : 코드와 설정을 분리
- 자동 테이블 생성 및 데이터 삽입 : 초기 설정을 위해 서버 실행 시 동작

## 💻 실행 방법

### 1. **설치**

```bash
$ git clone https://github.com/WXXZIN/Board.git
```

### 2. **데이터베이스 실행**
[H2 실행](https://blog.naver.com/jtcjtc/223117129981)

### 3. **.env 작성**
src/main/resources 경로에 .env 파일 생성

```bash
jdbc.driver=
jdbc.url=
jdbc.username=
jdbc.password=
```

### 4. **Tomcat 설정 및 실행**
Tomcat 9 버전을 사용하여 프로젝트 실행

[Tomcat 다운로드 링크](https://tomcat.apache.org/download-90.cgi)

## 💁‍♂️ 프로젝트 팀원
| Backend | Frontend |
|:---:|:---:|
| <img src="https://github.com/Mustache0318.png" width="100" /> | <img src="https://github.com/WXXZIN.png" width="100" /> |
| [김지희](https://github.com/Mustache0318) | [최원진](https://github.com/WXXZIN) |
