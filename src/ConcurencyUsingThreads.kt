import kotlinx.coroutines.runBlocking

class ConcurencyUsingThreads {
    class ValueProvider<T>(private val result: T, private val sleepTime: Long) : Runnable {

        @Volatile
        var value: T? = null

        override fun run() {
            Thread.sleep(sleepTime)
            value = result
        }
    }

    companion object {

        private fun getName(): String? {
            println("getName started")
            val provider = ValueProvider("Ayoub", 1000)
            val thread = Thread(provider)
            thread.start()
            thread.join()
            return provider.value
        }

        private fun getAge(): Int {
            println("getAge started")
            Thread.sleep(500)
            val provider = ValueProvider(23, 500)
            val thread = Thread(provider)
            thread.start()
            thread.join()
            return provider.value ?: 0
        }

        private fun main(args: Array<String>) = runBlocking {
            val name = getName()
            val age = getAge()
            println(name)
            println(age)
        }
    }
}