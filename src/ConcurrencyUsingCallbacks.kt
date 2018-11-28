import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class ConcurrencyUsingCallbacks {

    companion object {

        private fun getToken(callback: (String) -> Unit) {
            println("getting Token...")
            thread {
                Thread.sleep(1500)
                callback(TOKEN)
            }
        }

        private fun getUserId(token: String, callback: (String) -> Unit) {
            println("getting UserId...")
            thread {
                Thread.sleep(1000)
                callback(USERID)
            }
        }

        private fun getUser(userId: String, callback: (User) -> Unit) {
            println("getting UserInfo...")
            thread {
                Thread.sleep(500)
                callback(User(userId))
            }
        }

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            getToken { token ->
                getUserId(token) { userId ->
                    getUser(userId) { user ->
                        println("GOT USER : $user")
                    }
                }
            }
        }
    }
}