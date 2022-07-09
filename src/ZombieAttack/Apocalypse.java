package ZombieAttack;

import java.util.Scanner;

public class Apocalypse {

    //instance variables as well as static variables. Person array to hold data for all survivors. Scanner used to move onto the next day.
    private static int DAYS_TO_SURVIVE=Utilities.NUM_DAYS;
    private static int NUM_AMMO = Utilities.AMOUNT_OF_AMMO, NUM_FOOD;
    private int daysAlive=1;
    private static int amountOfZombies, previousZombies;
    private int survivorCount;
    private static int startingPeople = 30;
    private Person[] survivors;
    private Scanner inputScanner;


    public Apocalypse(){
        //instantiate scanner
        inputScanner = new Scanner(System.in);
    }

    //runSimulation which will run the whole program until either everyone dies, or someone survives 10 days.
    public void runSimulation(){
        //calling createSurvivors to create amount specified in startingPeople which is 30 in this case.
        createSurvivors(startingPeople);
        //instantiating variable NUM_FOOD used in this program with constant from Utilities class
        NUM_FOOD=Utilities.AMOUNT_OF_FOOD;
        //Calling a message method from utilities
        Utilities.startOfGame();

        //do while loop
        do{
            //calling the getNumberOfSurvivors to start off the count of survivors alive
            getNumberOfSurvivors();
            //starts the day and this also feeds survivors
            generateDay();
            //if the amount of survivors is less than or equal to 10, the game ends
            if(survivorCount<=0){
                Utilities.gameOver();
                break;
            }
            //temporary userInput string variable used for inputting commands
            String userInput;
            do{
                //message method from Utilities class
                Utilities.waitingScreen(daysAlive);
                //asking for user input
                userInput = inputScanner.next();
                //if the user input does not equal yes, then it sends out an error message and asks user to re-enter the correct message
                if(!userInput.equalsIgnoreCase("yes")){
                    Utilities.errorMessage();
                    userInput = inputScanner.next();
                }
                //if the message is correct, the if statement ends and moves on
                if(userInput.equalsIgnoreCase("yes")){
                    break;
                }
            }
            //after typing yes, the system will run more methods
            while(!userInput.equalsIgnoreCase("yes"));
            //startFight method starts the combat scenario for each survivor alive
            startFight();
            //gets the number of survivors after combat
            getNumberOfSurvivors();
            //increments daysAlive variable by 1
            daysAlive++;
            //if no survivors, the game ends and if statements ends
            if(survivorCount<=0){
                Utilities.gameOver();
                break;
            }
            //if daysAlive equals days to survive, if statement ends
            if(daysAlive==DAYS_TO_SURVIVE){
                break;
            }
        }
        //do while will keep running so long as daysAlive is less than days required to win
        while (daysAlive<DAYS_TO_SURVIVE);
        //after the do while ends, itll check if there are any survivors, if there is you win, if not, you lose
        if(survivorCount>0){
            Utilities.victoryMessage(survivorCount,daysAlive);
        }
        if(survivorCount<=0){
            Utilities.defeatMessage(daysAlive);
        }
    }

    //method for generating a new day after each fight scenario
    public void generateDay(){
        Utilities.startOfDay(daysAlive);
        feedSurvivors();
        Utilities.amountOfFoodLeft(NUM_FOOD);
    }

    public void feedSurvivors(){
        //temporary variable for while loop
        int i=0;
        while(true){
            //while survivors is less than total amount of starting people
            while(++i < startingPeople){
                //checks if there are survivors
                if(this.survivors[survivorCount].getAlive()){
                    //if theres no food, people die
                    if(NUM_FOOD<=0){
                        survivors[survivorCount].setAlive(false);
                    }
                    //if there is food, then food is decremented
                    else{
                        NUM_FOOD--;
                    }
                }
                //returns with number of survivors
                if(NUM_FOOD<=0 && i>Utilities.NUMBER_OF_PEOPLE){
                    getNumberOfSurvivors();
                    return;
                }
            }
        }
    }

    //used to instantiate and create every survivor
    public void createSurvivors(int people){
        //since its in an array, i need to increment by 1 for the number label of the person to display correctly
        survivors = new Person[people+1];
        for(int student=1; student<=people; ++student){
            this.survivors[student] = new Person(NUM_AMMO,0,"Student ",true);
        }
    }

    //calculates the number of survivors for after a combat scenario
    public void getNumberOfSurvivors(){
        survivorCount=0;
        //checks the survivors in the array if they are alive, if they are, survivor count is incremented until theres nothing left to check
        for(int i = 1; i<=startingPeople; i++){
            if(survivors[i].getAlive()){
                ++survivorCount;
            }
        }
    }

    //combat scenario for each day
    public void startFight(){
        //generate zombies for the day/fight
        amountOfZombies = Utilities.generateRandomInRange(Utilities.MIN_ZOMBIES,Utilities.MAX_ZOMBIES);
        //used to hold the number in amountofzombies for text use
        previousZombies = amountOfZombies;
        //prints out the day as well as how many zombies were in the last round
        System.out.printf("\n\nIt is day %d. Good luck surviving! There are %d zombies",daysAlive, amountOfZombies);

        //do while loop
        do{
            //checks the array for people alive, if they're alive, they get added into a combat scenario (initiate combat method)
            for(int i=1; i<=startingPeople; ++i){
                if(survivors[i].getAlive());{
                    initiateCombat(i);
                }
            }
            //after the combat ends, checks for survivors with getAlive
            getNumberOfSurvivors();
            //if there are no survivors, the game ends
            if(survivorCount<=0){
                break;
            }
        }
        //loop keeps running as long as there are zombies left
        while(amountOfZombies>0);
        //if there are survivors remaining, text is printed, and the game moves onto the next day
        if(survivorCount>0){
            System.out.printf("\n%d zombies attacked you today, but you survived after killing all of them." +
                    "\nHowever only %d survived after the attack!",previousZombies,survivorCount);
        }
    }

    //used to calculate combat scenarios
    public void initiateCombat(final int numStudents){
        //variable for zombiehp incase firstshot isnt instant kill
        int zombieHP = Utilities.ZOMBIE_HP;
        //if survivors have ammo, then they will shoot a zombie
        if(survivors[numStudents].getAmmoAmount()>0){
            //while zombie has hp, the survivor will continue to shoot until the zombie dies or the survivor misses and dies
            do{
                survivors[numStudents].useAmmo();
                if(Utilities.generateGunProbability()==1){
                    //if its a instantkill, the if statement breaks and amountOfZombies gets decremented
                    if(Utilities.generateGunInstantKill()==1){
                        --amountOfZombies;
                        survivors[numStudents].setZombiesKilled();
                        Utilities.zombieInstantKill(numStudents);
                        break;
                    }
                    //otherwise zombie loses hp
                    --zombieHP;
                    Utilities.personAttackLanded(numStudents);
                }
                else{
                    //if person misses, they have 1/20 chance of dying (probability easily changeable in utilities class)
                    if(Utilities.generatePersonMissDeath()==1){
                        survivors[numStudents].setAlive(false);
                        Utilities.personMissDeath(numStudents);
                    }
                }
                //amountOfZombies gets decremented if its hp is 0
                if(zombieHP==0){
                    amountOfZombies--;
                    survivors[numStudents].setZombiesKilled();
                    Utilities.zombieKilledMessage(numStudents);
                }
            }
            //continues to run while zombie has hp, and survivors are alive and has ammo
            while( (zombieHP>0) && (survivors[numStudents].getAmmoAmount()>0) && (survivors[numStudents].getAlive()) );
        }
        //if survivors have no ammo, they will use their blunt weapon
        if(survivors[numStudents].getAmmoAmount()<=0){
            do{
                //if its a hit, it'll test for instant kill and break
                if(Utilities.generateBluntProbability()==1){
                    if(Utilities.generateBluntInstantKill()==1){
                        --amountOfZombies;
                        survivors[numStudents].setZombiesKilled();
                        Utilities.zombieInstantKill(numStudents);
                        break;
                    }
                    //otherwise the zombie loses hp
                    --zombieHP;
                    Utilities.personAttackLanded(numStudents);
                }
                else{
                    //person who misses has 1/20 chance of dying (probability easily changeable in utilities class)
                    if(Utilities.generatePersonMissDeath()==1){
                        //if person does miss, they will die and their setalive is set to false for use later
                        survivors[numStudents].setAlive(false);
                        //tells the user that someone missed their attack and died
                        Utilities.personMissDeath(numStudents);
                    }
                    //if person misses, but is still alive, the user will be notified
                    if(Utilities.generatePersonMissDeath()!=1){
                        Utilities.personMiss(numStudents);
                    }
                }
                //if zombiehp is 0, amountOfZombie is decremented
                if(zombieHP==0){
                    --amountOfZombies;
                    survivors[numStudents].setZombiesKilled();
                    Utilities.zombieKilledMessage(numStudents);
                }
            }
            //do while loop will keep running as long as zombie has hp and there are survivors alive
            while( (zombieHP>0) && (survivors[numStudents].getAlive()) );
        }
    }

}
