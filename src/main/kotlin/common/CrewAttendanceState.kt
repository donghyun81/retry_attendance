package common

enum class CrewAttendanceState(val state: String) {
    EXPULSION("제적"),
    INTERVIEW("면담"),
    WARNING("경고"),
    NON("");

    companion object {
        private const val ABSENCE_CRITERIA = 3
        private const val EXPULSION_CRITERIA = 5
        private const val INTERVIEW_CRITERIA = 3
        private const val WARNING_CRITERIA = 2

        fun getCrewAttendanceState(perceptionCount: Int, absenceCount: Int): CrewAttendanceState {
            if (perceptionCount.div(ABSENCE_CRITERIA) + absenceCount >= EXPULSION_CRITERIA) return EXPULSION
            if (perceptionCount.div(ABSENCE_CRITERIA) + absenceCount >= INTERVIEW_CRITERIA) return INTERVIEW
            if (perceptionCount.div(ABSENCE_CRITERIA) + absenceCount >= WARNING_CRITERIA) return WARNING
            return NON
        }
    }
}