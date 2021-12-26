package com.company;

import java.lang.*;
import java.util.Random;

//Yong Wen Kai and Leong Kah Ming
//This class is used to run the game logic.
public class GameLogic{
	
	Random randomNum = new Random();
	
	final static int numRange = 20;
	private int numAnt;
	private int numBug;
	static Organism[][] world = new Organism[20][20];	//Declared static, so it can use by other class.
	
	//Singleton design pattern.
	//Allow create one Board ONLY.
	private static GameLogic singleInstance = null;	
	
	//Leong Kah Ming
	//Call the default constructor.
	public static GameLogic getSingleInstance(){
		
		if(singleInstance == null){
			//Create board constructor.
			singleInstance = new GameLogic();
		}
		return singleInstance;
	}
	
	//Leong Kah Ming
	//Call the parameterize constructor.
	public static GameLogic getSingleInstance(int numAnt, int numBug){
		
		if(singleInstance == null){
			//Create board constructor.
			singleInstance = new GameLogic(numAnt, numBug);
		}
		return singleInstance;
	}
	
	//---------------- Start ------------------------//
	//------- Initialize the field ------------------//
	
	//Leong Kah Ming
	//Default constructor.
	private GameLogic(){
		
		//Initialize the default insects value into the field.
		this.setNumAnt(100);
		this.setNumBug(5);
		this.setBoard(this.getNumAnt(), this.getNumBug());
	}
	
	//Leong Kah Ming
	//Parameterize constructor.
	private GameLogic(int numAnt, int numBug){
		
		//Initialize the insects value into the field.
		this.setNumAnt(numAnt);
		this.setNumBug(numBug);
		this.setBoard(this.getNumAnt(), this.getNumBug());
	}
	
	//Leong Kah Ming
	//Setter and getter functions.
	public void setNumAnt(int numAnt){
		
		this.numAnt = numAnt;
	}
	
	public void setNumBug(int numBug){
		
		this.numBug = numBug;
	}
	
	public int getNumAnt(){
		
		return numAnt;
	}
	
	public int getNumBug(){
		
		return numBug;
	}
		
	//Leong Kah Ming
	//Create the field.
	public void setBoard(int numAnt, int numBug){
		
		for (int i = 0; i < numAnt; i++){
			this.insertInsect(new Ant(), numAnt);
		}
		
		for (int i = 0; i < numBug; i++){
			this.insertInsect(new Bug(), numBug);
		}
	}
	
	//Leong Kah Ming
	//Check the position on the field whether it is occupied or not.
	public boolean isOccupied(int row, int column){	
	
		boolean check = false;
		if(world[row][column] != null){
			check = true;
		}
		
		return check;	//Return true or false for the position.
	}
	
	//Leong Kah Ming
	//Set the insect type to the field, random position and the position which is not occupied.
	public void insertInsect(Organism insect, int numInsect){
		
		int type = 0;
		int row;
		int column;
		
		if(insect instanceof Ant){
			type = 1;
		}
		
		if(insect instanceof Bug){
			type = 2;
		}
		
		//Random assign the location.
		//Loop until found the available position.
		do{
			row = randomNum.nextInt(numRange);
			column = randomNum.nextInt(numRange);
		}while(this.isOccupied(row,column));
			
		try{
			switch(type){
				case 1:	world[row][column] = new Ant(type, row, column);
						break;
						
				case 2: world[row][column] = new Bug(type, row, column);
						break;
						
				default: throw new ClassNotFoundException("Error, class not found"); //Throw exception.
			}
		}
		catch(ClassNotFoundException e){
			System.out.println(e.getException());
		}
	}
	//---------------- End ------------------------//
	
		
	//------------ Run Some Operation -------------//
	
	//Leong Kah Ming
	//This function is for receive the signal(value) from GameView.
	//and then do some operation.
	public void extendRound(int signal){	
	
		if(signal == 1){
			//Run the game logic
			this.run();
			this.resetCheck();
		}
	}
	
	//Leong Kah Ming
	//Run the game.
	public void run(){
		
		//Run the bug first.
		for(int i = 0; i < numRange; i++){
			for(int j = 0; j < numRange; j++){
				if(world[i][j] instanceof Bug && world[i][j].getCheck() == false){	
					world[i][j].move(world[i][j].getType(), i, j);	//Call move function in bug class.
				}
			}
		}
		
		//Ant run at second.
		for(int i = 0; i < numRange; i++){
			for(int j = 0; j < numRange; j++){
				if(world[i][j] instanceof Ant && world[i][j].getCheck() == false){
					world[i][j].move(world[i][j].getType(), i, j);	//Call move function in ant class.
				}
			}
		}
	}
	
	//Leong Kah Ming
	//This function is used to reset the setCheck after you executed the run function.	
	//Ensure the bug and ant only move one time each round.
	//Without this the bug or ant will run until the end when looping.
	public void resetCheck(){
		
		for(int i = 0; i < numRange; i++){
			for(int j = 0; j < numRange; j++){
				if(world[i][j] instanceof Ant || world[i][j] instanceof Bug){
					world[i][j].setCheck(false);
				}
			}
		}
	}
	
	//Yong Wen Kai
	//Check the ant number in the field.
	public static int checkAnt(){
		
		int antCount = 0;
		for(int i = 0; i < numRange; i++){
			for(int j = 0; j < numRange; j++){
				if(world[i][j] instanceof Ant){
					antCount++;
				}
			}
		}

		return antCount;
	}
	
	//Yong Wen Kai
	//Check the ant number in the field.
	public static int checkBug(){
		
		int bugCount = 0;
		for(int i = 0; i < numRange; i++){
			for(int j = 0; j < numRange; j++){
				if(world[i][j] instanceof Bug){
					bugCount++;
				}
			}
		}
		return bugCount;
	}
}
