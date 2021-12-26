package com.company;

import java.util.Random;

//All 
//Ant class only has normal features.
public class Ant extends Organism{
	
	//Ong Shuoh Chwen	
	//Default constructor.
	public Ant(){
		
		super();
		this.setBreedCounter(0);	//Initialize the breed = 0 for ant.
	}
	
	//Ong Shuoh Chwen
	//Parameterize constructor.
	public Ant(int type, int row, int column){
		
		super(type, row, column);
		this.setBreedCounter(0);	//Initialize the breed = 0 for ant.
	}
	
	//All
	//Yong Wen Kai and Ong Shuoh Chwen do the most.
	//Leong Kah Ming help in debug the problem and enhance the program/function. 
	//Ant move function.
	public void move(int type, int row, int column){
		
		Random randomNum = new Random();
		
		int breedNum;
		int newRow = row;
		int newColumn = column;
		final int direction = 4;
		int option = randomNum.nextInt(direction);
		
		//To ensure it is ant object.
		if(type == 1){
			breedNum = GameLogic.world[row][column].getBreedCounter();
			switch(option){
				case 0: newRow = newRow - 1;	//Move up
						//Check if the new location is out of bound, if yes, the Ant remain still.
						if(newRow == -1){	
							break;
						}
						else{
							//Check if the new location is a Bug, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								break;
							}
							//Check if the new location is an Ant, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								break;
							}
							else{
								//Check if the Ant is eligible to breed.
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);								
							}
						}
						break;
						
				case 1:	newRow = newRow + 1;	//Move down
						//Check if the new location is out of bound, if yes, the Ant remain still.
						if(newRow == 20){
							break;
						}
						else{
							//Check if the new location is a Bug, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								break;
							}
							//Check if the new location is an Ant, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								break;
							}
							else{
								//Check if the Ant is eligible to breed.
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;	
						
				case 2:	newColumn = newColumn - 1;	//Move left
						//Check if the new location is out of bound, if yes, the Ant remain still.
						if(newColumn == -1){
							break;
						}
						else{
							//Check if the new location is a Bug, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								break;
							}
							//Check if the new location is an Ant, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								break;
							}
							else{
								//Check if the Ant is eligible to breed.
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);	
							}
						}
						break;
						
				case 3:	newColumn = newColumn + 1;	//Move right
						//Check if the new location is out of bound, if yes, the Ant remain still.
						if(newColumn == 20){
							break;
						}
						else{
							//Check if the new location is a Bug, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								break;
							}
							//Check if the new location is an Ant, if yes, the Ant remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								break;
							}
							else{
								//Check if the Ant is eligible to breed.
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
			}
		}			
	}
	
	//All
	//Yong Wen Kai and Ong Shuoh Chwen do the most.
	//Leong Kah Ming help in debug the problem and enhance the program/function. 
	public void breed(int type, int direction, int breedNum, int row, int column){
		//Direction from 0 to 3 indicates the same as in the move function.
		//Inside the switch case:
		//- The if statement indicates if the Ant is eligible to breed(the breed counter must be 3), if yes, the Ant will breed a new Ant.
		//- The else statement indicates the Ant is no eligible to breed, so increase the breed counter by 1.
		switch(direction){
			case 0:	if(GameLogic.world[row + 1][column].getBreedCounter() == 2){
						GameLogic.world[row + 1][column] = new Ant(type, row + 1, column);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row + 1][column] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 1:	if(GameLogic.world[row - 1][column].getBreedCounter() == 2){
						GameLogic.world[row][column] = new Ant(type, row - 1, column);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row - 1][column] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 2:	if(GameLogic.world[row][column + 1].getBreedCounter() == 2){
						GameLogic.world[row][column + 1] = new Ant(type, row, column + 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row][column + 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 3:	if(GameLogic.world[row][column - 1].getBreedCounter() == 2){
						GameLogic.world[row][column - 1] = new Ant(type, row, column - 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row][column - 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
		}
	}
}