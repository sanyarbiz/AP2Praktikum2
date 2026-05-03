import kotlin.random.Random

class Health {
    var energy : Int = 0
    var health : Int = 0

    init {
        energy = Random.nextInt(100)
        health = Random.nextInt(100)

    }
}