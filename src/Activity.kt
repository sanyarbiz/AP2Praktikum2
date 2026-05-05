class Activity {
    var energyImpact : Int = 0
    var happinessImpact : Int = 0
    var description : String = ""

    fun execute(pet : Pet){
        pet.happiness += happinessImpact
        pet.health.energy += energyImpact

    }

}