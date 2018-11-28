import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ConcurrencyUsingCoroutines {
    companion object {
        private suspend fun getToken(): String {
            println("getting Token...")
            delay(1500)
            return TOKEN
        }

        private suspend fun getUserId(token: String): String {
            println("getting UserId...")
            delay(1000)
            return USERID
        }

        private suspend fun getUser(userId: String): User {
            println("getting UserInfo...")
            delay(500)
            return User(userId)
        }

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val token = getToken()
            val userId = getUserId(token)
            val user = getUser(userId)
            println("GOT USER : $user")
        }
    }
}