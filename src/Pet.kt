import de.th_koeln.basicstage.Actor
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

class Pet {
    val actor = Actor(Assets.KODEE)
    var name = "Tamagochi"
    var health : Health = Health()
    var happiness = 50
    var isHungry : Boolean = if (health.energy < 20)  true else false

    val inventory =  mutableListOf<Item>()

    //Energie Anzeiger
    var energy = Actor()

    fun lifesGoesOn(){
        val random : Int = Random.nextInt(100)
        happiness = health.energy
        energy.text.content = random.toString()+ "E"
    }

    fun addItem(item: Item){

        if(item.category == ItemCategory.FOOD){
            feed(item)
           // hungry = if (health.energy < 20)  true else false
            isHungry = health.energy < 20
        }else{
            inventory.add(item)
            happiness += item.happinessImpact
            health.energy += item.energyImpact
            isHungry = health.energy < 20
        }

        println(item.name + " hinzugefügt. Happiness: $happiness")
    }

    fun removeItem(item: Item){
        inventory.remove(item)
        happiness -= item.happinessImpact


        println(item.name + " entfernt. Happiness: $happiness")
    }

    fun feed(item: Item){
            health.energy += item.energyImpact
            happiness += item.happinessImpact

    }

    val activity = Activity()


    fun doActivity(activtiy : Activity){
        activtiy.execute(this)
    }

    init {

        actor.animation.everyNsteps.timeSpan = 200
        actor.animation.everyNsteps.reactionForTimePassed = {
            lifesGoesOn()
        }
    }
}