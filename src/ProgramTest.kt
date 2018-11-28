import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProgramTest {

    @Test
    fun testMySuspendingFunction() = runBlocking{
        delay(2000L)
    }
}
