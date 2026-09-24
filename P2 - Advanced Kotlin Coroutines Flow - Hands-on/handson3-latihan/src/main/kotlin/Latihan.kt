import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Hands-on 3: StateFlow untuk Counter
// Tugas: Implementasikan counter sederhana menggunakan StateFlow.
// Counter harus bisa increment, decrement, dan reset.

class CounterManager {

    // TODO 1: Buat MutableStateFlow dengan nilai awal 0
    private val _count = MutableStateFlow(0)

    // TODO 2: Expose sebagai StateFlow (read-only)
    val count: StateFlow<Int> = _count.asStateFlow()

    // Menambah nilai counter
    fun increment() {
        _count.value++
    }

    // Mengurangi nilai counter, tetapi minimum tetap 0
    fun decrement() {
        if (_count.value > 0) {
            _count.value--
        }
    }

    // Reset counter ke 0
    fun reset() {
        _count.value = 0
    }
}

fun main() = runBlocking {

    val counter = CounterManager()

    // Collect di background
    val job = launch {
        counter.count.collect {
            println("Count: $it")
        }
    }

    delay(100)

    counter.increment() // Count: 1
    delay(100)

    counter.increment() // Count: 2
    delay(100)

    counter.decrement() // Count: 1
    delay(100)

    counter.reset()     // Count: 0
    delay(100)

    job.cancel()
}