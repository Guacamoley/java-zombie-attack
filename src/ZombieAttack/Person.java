package ZombieAttack;

public class Person {

    //instance variables for person class
   private int ammoAmount, zombiesKilled;
   private String personName;
   private boolean isAlive;

   //constructor used for each person in an array later on
    public Person(int ammoAmount, int zombiesKilled, String personName, boolean isAlive) {
        this.ammoAmount = ammoAmount;
        this.zombiesKilled = zombiesKilled;
        this.personName = personName;
        this.isAlive = isAlive;
    }

    //when a person shoots in the Apocalypse class, this will be used to decrement ammo
    public void useAmmo(){
        --ammoAmount;
    }

    //used to retrieve ammo ammount
    public int getAmmoAmount() {
        return ammoAmount;
    }

    public void setAmmoAmount(int ammoAmount) {
        this.ammoAmount = ammoAmount;
    }

    public int getZombiesKilled() {
        return zombiesKilled;
    }

    public void setZombiesKilled() {
        ++zombiesKilled;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    //if a person is alive, this will be used to check
    public boolean getAlive(){
        return this.isAlive;
    }

    //if a person is dead, this can be used to set them as "dead"
    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }
}
