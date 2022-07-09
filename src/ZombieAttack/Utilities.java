package ZombieAttack;

public class Utilities {
	//constants used for the program.
	public static int GUN_DEATH = 20;
	public static int NUM_DAYS=10;
	public static int NUMBER_OF_PEOPLE = 25, AMOUNT_OF_AMMO = 150, AMOUNT_OF_FOOD=300;
	public static int MIN_ZOMBIES = 10, MAX_ZOMBIES = 50;
	public static int HIT_CHANCE = 1, GUN_CHANCE_HIT = 3, GUN_INSTANT_KILL = 3;
	public static int BLUNT_CHANCE_HIT = 3, BLUNT_INSTANT_KILL = 10;
	public static int ZOMBIE_HP = 2;

	public enum Status{RUN, STOP}

	//static methods that will be used in Apocalypse
	public static int generateRandomInRange(int min, int max){		
	    return min + (int)(Math.random() * ((max - min) + 1));		
	}

	//used to generatezombies if need be
	public static int generateZombies(){
		return MIN_ZOMBIES + (int)(Math.random() * ((MAX_ZOMBIES - MIN_ZOMBIES) + 1));
	}

	//method used to generate gun probability for use in Apocalypse
	public static int generateGunProbability(){
		return HIT_CHANCE + (int)(Math.random() * ((GUN_CHANCE_HIT - HIT_CHANCE) + 1));
	}
	//method used to generate gun instant kill probability for use in Apocalypse
	public static int generateGunInstantKill(){
		return HIT_CHANCE + (int)(Math.random() * ((GUN_INSTANT_KILL - HIT_CHANCE) + 1));
	}

	//method used to generate attack miss death probability for use in Apocalypse
	public static int generatePersonMissDeath(){
		return HIT_CHANCE + (int)(Math.random() * ((GUN_DEATH - HIT_CHANCE) + 1));
	}

	//method used to generate blunt weapon probability for use in Apocalypse
	public static int generateBluntProbability(){
		return HIT_CHANCE + (int)(Math.random() * ((BLUNT_CHANCE_HIT - HIT_CHANCE) + 1));
	}

	//method used to generate blunt instant kill probability for use in Apocalypse
	public static int generateBluntInstantKill(){
		return HIT_CHANCE + (int)(Math.random() * ((BLUNT_INSTANT_KILL - HIT_CHANCE) + 1));
	}

	//methods used to generate text throughout the program based on variables in Apocalypse class
	public static void startOfDay(int day){
		System.out.printf("\n\nIt is day %d, good luck!",day);
		System.out.printf("\nEveryone has eaten lunch and they proceed back to their positions.");
	}

	public static void amountOfFoodLeft(int foodLeft){
		System.out.printf("\nThere is %d of food left for everyone.",foodLeft);
	}

	public static void startOfGame(){
		System.out.printf("\n\n\tNHCC Zombie Apocalypse simulation.\nThere are 25 survivors and the goal\nis to survive 10 days until help arrives.\n" +
				"A random amount of zombies will attack NHCC each day.\n");
	}

	public static void gameOver(){
		System.out.printf("\nNo one is alive, game over!");
	}

	public static void waitingScreen(int day){
		System.out.printf("\nYou are about to start day %d, are you ready?\nEnter 'yes' when you're ready.\n",day);
	}

	public static void errorMessage(){
		System.out.printf("\nPlease make sure you are typing in 'yes' when you're ready.\n");
	}

	public static void victoryMessage(int people, int days){
		System.out.printf("\n%d people have survived %d days! Help has arrived and all zombies have been killed!\n",people, days);
	}

	public static void defeatMessage(int days){
		System.out.printf("\nEveryone has died! They lasted %d days\n", days);
	}

	public static void zombieKilledMessage(int people){
		System.out.printf("\nStudent %s killed the zombie finally!\n",people);
	}

	public static void zombieInstantKill(int people){
		System.out.printf("\nStudent %s instantly killed the zombie with their first hit!\n",people);
	}

	public static void personMissDeath(int people){
		System.out.printf("\nStudent %s missed their attack and died!\n",people);
	}

	public static void personMiss(int people){
		System.out.printf("\nStudent %s missed their attack!\n", people);
	}

	public static void personAttackLanded(int people){
		System.out.printf("\nStudent %s landed an attack!\n", people);
	}
}
