package com.company;

import java.util.Random;

//All
//Bug class is similar to ant class but it has extra features like move(added top left, top right, bottom left, bottom right), starve, and eat ant.
public class Bug extends Organism{
	
	final int STARVE = 2;
	Random randomNum = new Random();
	
	//Ong Shuoh Chwen
	//Default constructor.
	public Bug(){
		
		super();
		this.setBreedCounter(0);//Initialize the breed = 0 for bug.
		this.setStarve(0);		//Initialize the eatCount = 0 for bug.
	}
	
	//Ong Shuoh Chwen
	//Parameterize constructor.
	public Bug(int type, int row, int column){
		
		super(type, row, column);
		this.setBreedCounter(0);//Initialize the breed = 0 for bug.
		this.setStarve(0);		//Initialize the eatCount = 0 for bug.
	}	
	
	//All
	//Yong Wen Kai and Ong Shuoh Chwen do the most.
	//Leong Kah Ming help in debug the problem and enhance the program/function. 
	//Bug move function.
	public void move(int type, int row, int column){
		
		int breedNum;
		int starveNum;
		int newRow = row;
		int newColumn = column;
		final int direction = 8;
		int option = randomNum.nextInt(direction);
		
		//To ensure it is bug object.
		if(type == 2){
			breedNum = GameLogic.world[row][column].getBreedCounter();
			starveNum = GameLogic.world[row][column].getStarve();
			switch(option){
				case 0: newRow = newRow - 1;	//Move up.
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if(GameLogic.world[newRow + 1][newColumn].getStarve() == STARVE){
							GameLogic.world[newRow + 1][newColumn] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == -1){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow + 1][newColumn] = null;
								this.setStarve(0);
								break;
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
						
				case 1:	newRow = newRow + 1;	//Move down.
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if (GameLogic.world[newRow - 1][newColumn].getStarve() == STARVE){
							GameLogic.world[newRow - 1][newColumn] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == 20){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow - 1][newColumn] = null;
								this.setStarve(0);
								break;
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
						
				case 2:	newColumn = newColumn - 1;	//Move left.
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if(GameLogic.world[newRow][newColumn + 1].getStarve() == STARVE){
							GameLogic.world[newRow][newColumn + 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newColumn == -1){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow][newColumn + 1] = null;
								this.setStarve(0);
								break;
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							else{
								//Check if the Bug is eligible to breed.
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
						
				case 3:	newColumn = newColumn + 1;	//Move right.
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if(GameLogic.world[newRow][newColumn - 1].getStarve() == STARVE){
							GameLogic.world[newRow][newColumn - 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newColumn == 20){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow][newColumn - 1] = null;
								this.setStarve(0);
								break;
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;	
						
				case 4:	newRow = newRow - 1;	//Move top left.
						newColumn = newColumn - 1; 
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if (GameLogic.world[newRow + 1][newColumn + 1].getStarve() == 2){
							GameLogic.world[newRow + 1][newColumn + 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == -1 || newColumn == -1){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow + 1][newColumn + 1] = null;
								this.setStarve(0);									
								break;		
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
						
				case 5:	newRow = newRow - 1;	//Move top right.
						newColumn = newColumn + 1; 
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if (GameLogic.world[newRow + 1][newColumn - 1].getStarve() == 2){
							GameLogic.world[newRow + 1][newColumn - 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == -1 || newColumn == 20){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow + 1][newColumn - 1] = null;
								this.setStarve(0);									
								break;		
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
				
				case 6:	newRow = newRow + 1;	//Move bottom left.
						newColumn = newColumn - 1; 
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if (GameLogic.world[newRow - 1][newColumn + 1].getStarve() == 2){
							GameLogic.world[newRow - 1][newColumn + 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == 20 || newColumn == -1){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow - 1][newColumn + 1] = null;
								this.setStarve(0);									
								break;		
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
								GameLogic.world[newRow][newColumn] = this;
								this.breed(type, option, breedNum, newRow, newColumn);
							}
						}
						break;
						
				case 7: newRow = newRow + 1;	//Move bottom right.
						newColumn = newColumn + 1; 
						//Check if the Bug has been starving for 3 rounds, if yes, the Bug dies.
						if (GameLogic.world[newRow - 1][newColumn - 1].getStarve() == 2){
							GameLogic.world[newRow - 1][newColumn - 1] = null;
							break;
						}
						//Check if the new location is out of bound, if yes, the Bug remain still.
						else if(newRow == 20 || newColumn == 20){
							breedNum++;
							this.setBreedCounter(breedNum);
							break;
						}
						else{
							//Check if the new location is an Ant, if yes, the Bug eats the Ant.
							if(GameLogic.world[newRow][newColumn] instanceof Ant){
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								GameLogic.world[newRow][newColumn] = this;
								GameLogic.world[newRow - 1][newColumn - 1] = null;
								this.setStarve(0);									
								break;										
							}
							//Check if the new location is a Bug, if yes, the Bug remain still.
							if(GameLogic.world[newRow][newColumn] instanceof Bug){
								breedNum++;
								this.setBreedCounter(breedNum);
								break;
							}
							//Check if the Bug is eligible to breed.
							else{
								this.setRow(newRow);
								this.setColumn(newColumn);
								this.setCheck(true);
								starveNum++;
								this.setStarve(starveNum);
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
		//Direction from 0 to 7 indicates the same as in the move function.
		//Inside the switch case:
		//- The if statement indicates if the Bug is eligible to breed(the breed counter must be 8), if yes, the Bug will breed a new Bug.
		//- The else statement indicates the Bug is no eligible to breed, so increase the breed counter by 1.
		switch(direction){
			case 0:	if(GameLogic.world[row + 1][column].getBreedCounter() >= 7){
						GameLogic.world[row + 1][column] = new Bug(type, row + 1, column);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row + 1][column] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 1:	if(GameLogic.world[row - 1][column].getBreedCounter() >= 7){
						GameLogic.world[row - 1][column] = new Bug(type, row - 1, column);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row - 1][column] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 2:	if(GameLogic.world[row][column + 1].getBreedCounter() >= 7){
						GameLogic.world[row][column + 1] = new Bug(type, row, column + 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row][column + 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 3:	if(GameLogic.world[row][column - 1].getBreedCounter() >= 7){
						GameLogic.world[row][column - 1] = new Bug(type, row, column - 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row][column - 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 4:	if(GameLogic.world[row + 1][column + 1].getBreedCounter() >= 7){
						GameLogic.world[row + 1][column + 1] = new Bug(type, row + 1, column + 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row + 1][column + 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 5:	if(GameLogic.world[row + 1][column - 1].getBreedCounter() >= 7){
						GameLogic.world[row + 1][column - 1] = new Bug(type, row + 1, column - 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row + 1][column - 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
			
			case 6:	if(GameLogic.world[row - 1][column + 1].getBreedCounter() >= 7){
						GameLogic.world[row - 1][column + 1] = new Bug(type, row - 1, column + 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row - 1][column + 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
					
			case 7:	if(GameLogic.world[row - 1][column - 1].getBreedCounter() >= 7){
						GameLogic.world[row - 1][column - 1] = new Bug(type, row - 1, column - 1);
						this.setBreedCounter(0);
					}
					else{
						GameLogic.world[row - 1][column - 1] = null;
						breedNum++;
						this.setBreedCounter(breedNum);
					}
					break;
		}
	}
}
