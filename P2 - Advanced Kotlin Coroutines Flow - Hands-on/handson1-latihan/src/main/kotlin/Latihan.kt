import kotlinx.coroutines.*

// Hands-on 1: Coroutines Dasar
// Tugas: Ambil data dari 2 sumber secara PARALEL menggunakan async/await,
// lalu gabungkan hasilnya. Total waktu eksekusi harus < 2 detik.

suspend fun fetchUserProfile(userId: String): String {
    delay(1000) // Simulasi network delay
    return "User: John Doe"
}

suspend fun fetchUserPosts(userId: String): List<String> {
    delay(800) // Simulasi network delay
    return listOf("Post 1", "Post 2", "Post 3")
}

fun main() = runBlocking {

    val startTime = System.currentTimeMillis()

    // TODO 1: Jalankan kedua fungsi secara PARALEL dengan async
    val profileDeferred = async {
        fetchUserProfile("123")
    }
    val postsDeferred = async {
        fetchUserPosts("123")
    }

    // TODO 2: Tunggu kedua hasil dengan await()
    val profile = profileDeferred.await()
    val posts = postsDeferred.await()

    // Tampilkan hasil
    println(profile)

    println("Posts:")
    for (post in posts) {
        println("- $post")
    }

    // TODO 3: Ukur waktu eksekusi
    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}