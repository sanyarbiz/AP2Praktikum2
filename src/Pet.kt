import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.DisplayActor
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

class Pet {
    val actor = Actor(Assets.KODEE)
    var name = "Tamagochi"
    var health : Health = Health()
    var happiness = 50
    var hungry : Boolean = if (health.energy < 20)  true else false


    var minutesAwake : Int = 0
        set(value){
            if(value > 0 && value % 10 == 0){
                health.energy -= 1
                println("10 Minuten sind vergangen. Energie ist jetzt ${health.energy} und Happiness $happiness")

            }
            field =  value //Backing Field: setzt den neuen Wert jedes mal, wenn eine Spiel-Minute vergeht
        }

    var hoursAwake : Int //Hat keinen eigenen Wert, gibt stattdessen minutesAwake / 60 zurück.
        get() {
            return minutesAwake / 60
        }
        set(value) {
            minutesAwake = value * 60
        }

    val inventory =  mutableListOf<Item>()

    //Energie Anzeiger
    var energy = DisplayActor(health.energy.toString() + "E")

    fun lifesGoesOn(){
        val random : Int = Random.nextInt(100)
        happiness = health.energy
        energy.text.content = random.toString()+ "E"
    }

    fun addItem(item: Item){

        try {
            if(item.category != ItemCategory.FOOD){
                throw IllegalArgumentException("Fehler: ${item.name} ist kein Essen")
            }

            feed(item)
            hungry = health.energy < 20
        } catch (e: IllegalArgumentException) {

            println("Fehler abgefangen: ${e.message}")
            inventory.add(item)
            happiness += item.happinessImpact
            health.energy += item.energyImpact
            hungry = health.energy < 20


        }

        println(item.name + " hinzugefügt. Happiness: $happiness")
    }

    fun removeItem(item: Item){
        inventory.remove(item)
        happiness -= item.happinessImpact


        println(item.name + " entfernt. Happiness: $happiness")
    }

    fun feed(item: Item) {
        health.energy += item.energyImpact
        happiness += item.happinessImpact

    }

    fun doActivity(activtiy : Activity){
        activtiy.execute(this)
        energy.text.content = health.energy.toString() + "E"
    }

    fun hasItem(item: Item) : Boolean = inventory.contains(item)

    init {

        actor.animation.everyNsteps.timeSpan = 200
        actor.animation.everyNsteps.reactionForTimePassed = {
            lifesGoesOn()
            minutesAwake++
        }
    }
}