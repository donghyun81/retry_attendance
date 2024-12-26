package attendance

import attendance.domain.validator.DateValidator
import camp.nextstep.edu.missionutils.DateTimes
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class DateValidatorTest {
    private lateinit var dateValidator: DateValidator
    private lateinit var dateTime: LocalDateTime

    @BeforeEach
    fun setUp() {
        dateValidator = DateValidator()
        dateTime = DateTimes.now()
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "32", "1a", "2313142"])
    fun `업데이트 날짜 예외 테스트`(dayInput: String) {
        val today = dateTime
        assertThrows<IllegalArgumentException> {
            dateValidator.validateUpdateDay(today, dayInput)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1,2",
        "30,31",
        "1,31"
    )
    fun `업데이트 미래 날짜 예외 테스트`(day:Int,dayInput: Int) {
        val today = dateTime.withDayOfMonth(day)
        assertThrows<IllegalArgumentException> {
            dateValidator.validateUpdateDay(today, dayInput.toString())
        }
    }
}