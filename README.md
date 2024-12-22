# kotlin-attendance-precourse

## 실행 흐름에 따른 기능 목록 작성

- 해당 날짜 출력 및 사용자 요구 기능 입력 기능 구현

- 출석 기능
    - 닉네임 입력
    - 등교 시간 입력
    - 출석 내용 출력

- 출석 수정 기능
    - 닉네임 입력
    - 수정하려는 날짜 입력
    - 수정 시간 입력
    - 수정 결과 출력

- 크루별 출석 결과 출력 기능
    - 출석 기록 출력
    - 출석 결과 횟수 출력
    - 관리 대상 여부 출력

- 전날까지의 크루 출석 기록을 바탕으로 제적 위험자를 파악 기능
  - 제적 위험자는 제적 대상자, 면담 대상자, 경고 대상자순으로 출력하며
  ,대상 항목별 정렬 순서는 지각을 결석으로 간주하여 내림차순한다. 출석 상태가 같으면 닉네임으로 오름차순 정렬한다.