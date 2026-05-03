import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.ButtonActor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.coordinatesystem.WorldConstants
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

class Game {
    val stage = Stage()
    var pet = Pet()

    val ball = Actor(Assets.misc.BALL)
    val ufo = Actor(Assets.misc.UFO)

    val ballItem = Item()
    val ufoItem = Item()

    val items = arrayOf<Actor>(ball, ufo)

    //Buttons

    val kekseBtn = ButtonActor("Kekse backen")
    val laufenBtn = ButtonActor("Laufen")
    val fussballBtn = ButtonActor("Fussball spielen")

    val buttons = arrayOf<ButtonActor>(kekseBtn, laufenBtn, fussballBtn)

    //Activities

    val kekseActivity = Activity()
    val laufenActivity = Activity()
    val fussballActivity = Activity()

    val activities = arrayOf<Activity>(kekseActivity, laufenActivity, fussballActivity)

    init {
        //Pet
        stage.addActor(pet.actor)
        pet.actor.x = 100

        //Pet Energy Anzeiger
        stage.addActor(pet.energy)
        pet.energy.text.content = pet.health.energy.toString() + "E"

        //Buttons
        stage.addActor(kekseBtn)
        kekseBtn.x = 200
        stage.addActor(laufenBtn)
        laufenBtn.x = 300
        stage.addActor(fussballBtn)
        fussballBtn.x = 400

        //Activities
        kekseActivity.energyImpact = -10
        kekseActivity.happinessImpact = 40
        kekseActivity.description = "Kekse backen!"

        laufenActivity.energyImpact = -25
        laufenActivity.happinessImpact = 10
        laufenActivity.description = "Laufen!"

        fussballActivity.energyImpact = -35
        fussballActivity.happinessImpact = 50
        fussballActivity.description = "Fussball spielen!"

        //Items
        ballItem.name = "Ball"
        ufoItem.name = "Ufo"
        ballItem.category = ItemCategory.TOY
        ufoItem.category = ItemCategory.TOY
        ballItem.energyImpact = 30
        ufoItem.energyImpact = 5
        ballItem.happinessImpact = 50
        ufoItem.happinessImpact = 100

        for (i in 0..items.size-1) {
            val randX = Random.nextInt(WorldConstants.STAGE_WIDTH)
            val randY = Random.nextInt(WorldConstants.STAGE_HEIGHT)

            stage.addActor(items[i])
            items[i].x = randX
            items[i].y = randY


            items[i].reactionForMousePressed = {
                if(items[i] == ball){
                    pet.inventory.add(ballItem)
                    
                }else if (items[i] == ufo){
                    pet.inventory.add(ufoItem)
                }

                println("saved!")
            }
        }


        for (i in 0..buttons.size-1) {
            buttons[i].reactionForMousePressed = {
                when(buttons[i]) {
                    kekseBtn -> pet.doActivity(kekseActivity)
                    laufenBtn -> pet.doActivity(laufenActivity)
                    fussballBtn -> pet.doActivity(fussballActivity)
                }
            }
        }


    }

}