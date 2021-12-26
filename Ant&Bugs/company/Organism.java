package com.company;

//Leong Kah Ming
//This class is used to set the ant and bug information.
//The subclass Ant and Bug will use the super class function.
public abstract class Organism{
	
	private int type;
	private int row;
	private int column;
	private int starve;
	private int breedCounter;
	private boolean check;
	
	//Leong Kah Ming
	//Default constructor.
	public Organism(){
		this.setCheck(false);
		this.setRow(-1);
		this.setColumn(-1);
		this.setBreedCounter(0);
	}
	
	//Leong Kah Ming
	//Parameterize constructor.
	public Organism(int type, int row, int column){
		
		this.setCheck(false);
		this.setType(type);
		this.setBreedCounter(0);
		this.setRow(row);
		this.setColumn(column);
	}
	
	//Leong Kah Ming
	//Getter and setter functions.
	public void setCheck(boolean check){
		
		this.check = check;
	}
	
	public void setType(int type){
		
		this.type = type;
	}
	
	public void setRow(int row){
		
		this.row = row;
	}
	
	public void setColumn(int column){
		
		this.column = column;
	}
	
	public void setBreedCounter(int breedCounter){
		
		this.breedCounter = breedCounter;
	}
	
	public void setStarve(int starve){
		
		this.starve = starve;
	}
	
	public int getStarve(){
		
		return starve;
	}
	
	public boolean getCheck(){
		
		return check;
	}
	
	public int getType(){
		
		return type;	
	}
	
	public int getRow(){
		
		return row;
	}
	
	public int getColumn(){
		
		return column;
	}
	
	public int getBreedCounter(){
		
		return breedCounter;
	}
	
	//Leong Kah Ming
	//Abstract function which used to overriding.
	abstract public void move(int type, int row, int column);
	abstract public void breed(int type, int direction, int breedNum, int row, int column);
}		