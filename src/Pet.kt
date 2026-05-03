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

    val inventory =  mutableListOf<Item>()

    //Energie Anzeiger
    var energy = DisplayActor(health.energy.toString() + "E")

    fun lifesGoesOn(){
        val random : Int = Random.nextInt(100)
        happiness = health.energy
        energy.text.content = random.toString()+ "E"
    }

    fun addItem(item: Item){

        if(item.category == ItemCategory.FOOD){
            feed(item)
            hungry = health.energy < 20
        }else{
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

    init {

        actor.animation.everyNsteps.timeSpan = 200
        actor.animation.everyNsteps.reactionForTimePassed = {
            lifesGoesOn()
        }
    }
}