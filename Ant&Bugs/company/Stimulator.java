package com.company;

import java.io.FileNotFoundException;

//This class is use to run the whole program.
public class Stimulator{
	
	public static void main(String []args) throws FileNotFoundException {
		
		new GameView("Ant vs Bug", 700, 700);
	}
}