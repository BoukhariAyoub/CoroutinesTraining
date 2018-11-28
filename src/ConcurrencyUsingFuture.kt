import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture

class ConcurrencyUsingFuture {
    companion object {
        private fun getToken(): String {
            println("getting Token...")
            Thread.sleep(1500)
            return TOKEN
        }

        private fun getUserId(token: String): String {
            println("getting UserId...")
            Thread.sleep(1000)
            return USERID
        }

        private fun getUser(userId: String): User {
            println("getting UserInfo...")
            Thread.sleep(500)
            return User(userId)
        }

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val userFuture = CompletableFuture.supplyAsync { getToken() }
                .thenApplyAsync { token -> getUserId(token) }
                .thenApplyAsync { userId -> getUser(userId) }

            val user = userFuture.get()

            println("GOT USER : $user")
        }
    }
}