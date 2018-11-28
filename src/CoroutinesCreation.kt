import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

suspend fun createCoroutines(amount: Int) {
    val jobs = ArrayList<Job>()
    for (i in 1..amount) {
        jobs += GlobalScope.launch {
            delay(1000)
        }
    }
    jobs.forEach {
        it.join()
    }
}

fun createThreads(amount: Int) {
    val jobs = ArrayList<Thread>()
    for (i in 1..amount) {
        jobs += thread {
            Thread.sleep(1000)
        }
    }
    jobs.forEach {
        it.join()
    }
}

fun main2(args: Array<String>) = runBlocking {
    val timeCoroutines = measureTimeMillis {
        createCoroutines(1000)
    }

    val timeThreads = measureTimeMillis {
        createThreads(1000)
    }

    println("Coroutines Took $timeCoroutines ms")
    println("Threads Took $timeThreads ms")
}


fun getName() = GlobalScope.async {
    println("getName started")
    delay(1000)
    "Ayoub"
}

fun getAge() = GlobalScope.async {
    println("getAge started")
    delay(500)
    23
}

fun main(args: Array<String>) = runBlocking {

    val jobs = List(100_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
    jobs.forEach { it.join() }
    println()
    println("DONE")
}

suspend fun playWithAwait(){

    val name = getName()
    val age = getAge()
    println(name.await())
    println(age.await())
}