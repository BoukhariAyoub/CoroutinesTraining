import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}

@Volatile // in Kotlin `volatile` is an annotation
var volatileCounter = 0
var atomicCounter = AtomicInteger(0)
var counter = 0
var syncCounter = 0

@Synchronized
fun incrementSyncCounter() {
    syncCounter++
}

fun main() = runBlocking {

    GlobalScope.massiveRun {
        counter++
        volatileCounter++
        atomicCounter.getAndIncrement()
        incrementSyncCounter()
    }
    println("VolatileCounter = $volatileCounter")
    println("AtomicCounter = $atomicCounter")
    println("SyncCounter = $syncCounter")
    println("Counter = $counter")
}