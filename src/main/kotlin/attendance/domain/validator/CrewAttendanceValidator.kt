package attendance.domain.validator

class CrewAttendanceValidator {
    fun validateNickName(crewNames: List<String>, inputName: String) {
        val containsCrewName = crewNames.contains(inputName)
        require(containsCrewName) { "[ERROR] 등록되지 않은 닉네임입니다." }
    }

    fun validateAttendance(hasAttendance: Boolean) {
        require(hasAttendance.not()) { "[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요." }
    }

    fun validateUpdateAttendance(hasAttendance: Boolean) {
        require(hasAttendance) { "[ERROR] 출석 기록이 없습니다. 필요한 경우 확인 기능을 이용해 주세요." }
    }
}