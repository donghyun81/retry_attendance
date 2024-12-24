package common

enum class UserAction(val type: String, val message: String) {
    ATTENDANCE_CONFIRMATION("1", "1. 출석 확인"),
    ATTENDANCE_CORRECTION("2", "2. 출석 수정"),
    CHECK_ATTENDANCE_RECORDS("3", "3. 크루별 출석 기록 확인"),
    CHECK_RISK_OF_EXPULSION("4", "4. 제적 위험자 확인"),
    END("Q", "Q. 종료");
}