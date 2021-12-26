package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

//All
//This class is a GUI to display the game.
public class GameView extends JFrame{
	
    final int row = 20;         //Final the variable row so that it will not change.
    final int column = 20;      //Final the variable column so that it will not change.
    int counterVal = 0;         //Variable used for displaying the counter.
    ImageIcon antPic = loadImage("Image/Ant.png");      //Loading ant picture.
    ImageIcon bugPic = loadImage("Image/Bug.png");      //Loading bug picture.
    JButton[][] button = new JButton[row][column]; 	 //Creating the buttons for both insects to live in.
	
	GameLogic board = GameLogic.getSingleInstance(); //Singleton design pattern.

	//All
    //constructor.
    public GameView(String name, int width, int height) throws FileNotFoundException {
		
        super(name);
        this.setLayout(new BorderLayout());
        JPanel Enter = new JPanel(new GridLayout(2,1));
        JPanel gameBoard = new JPanel(new GridLayout(20,20));
		JPanel menuBar = new JPanel(new GridLayout(1,3));
		JLabel counter = new JLabel("Counter: ");
		final JLabel counterValue = new JLabel(Integer.toString(counterVal));
		JLabel Antcount = new JLabel("Ants: ");
		final JLabel antCounts = new JLabel(Integer.toString(GameLogic.checkAnt()));
		JLabel Bugcount = new JLabel("Bugs: ");
		final JLabel bugCounts = new JLabel(Integer.toString(GameLogic.checkBug()));
		//For Game Over
		final JDialog gameOver = new JDialog(this,"Game Over", true);	//JDialog to show game over.
		JPanel overText = new JPanel(new CardLayout(57,15));			//JPanel for game over.
		JLabel showOver = new JLabel("Game Over");			//Jlabel to display game over text.
		//For instruction
		final JDialog instruction = new JDialog(this,"Instructions", true);	//JDialog to show instructions.
		JPanel instructPanel = new JPanel(new CardLayout());

		//try catch block for file reading
		//change file path if file not found at line 50
		try{
			FileReader readTextFile=new FileReader("src/com/copany/text/Instruction.txt");
			Scanner fileReaderScan=new Scanner(readTextFile);
			String storeAllString="";
			while(fileReaderScan.hasNextLine()){
				String temp=fileReaderScan.nextLine()+"\n";
				storeAllString=storeAllString+temp;
			}
			JTextArea showInstruct = new JTextArea(storeAllString);
			showInstruct.setLineWrap(true);
			showInstruct.setWrapStyleWord(true);
			JScrollPane scrollBarForTextArea=new JScrollPane(showInstruct,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			instructPanel.add(scrollBarForTextArea);
		}catch (Exception e){
			JDialog popup = new JDialog(this, "File Not Found", true);
			JLabel fileNotFound = new JLabel(" File Not Found Instruction will not display");
			popup.setSize(250,100);
			popup.add(fileNotFound);
			popup.setLocationRelativeTo(null);
			popup.show(true);
			System.out.println("File not found");
		}


		//Loop through all 20x20 buttons.
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++){
				button[i][j] = new JButton();       					//Creating new button.
				button[i][j].setBackground(new Color(255,255,255));     //Setting the button background color to white.
				gameBoard.add(button[i][j]);        					//Adding the buttons to the panel gameBoard.
			}
		}

		//Setting the buttons.
		setButtonImage();

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonNull();
				counterValue.setText(Integer.toString(++counterVal));      	    //Display the the counter value.
				play();
				antCounts.setText(Integer.toString(GameLogic.checkAnt()));      //Display the ant counts.
				bugCounts.setText(Integer.toString(GameLogic.checkBug()));      //Display the bug counts.

				if (GameLogic.checkAnt() == 0 || GameLogic.checkBug() == 0){	//Check if all ant or all bug died then exit the program.
					gameOver.show(true);
					exit(0);
				}
			}
		});
		JButton instruc = new JButton("Instruction");
		instruc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instruction.show(true);
			}
		});

		//adding frame
		add(Enter, BorderLayout.SOUTH);
		add(gameBoard, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);

		//adding buttons to frame
		Enter.add(start);
		Enter.add(instruc);

		//adding things to menuBar panel
		menuBar.add(counter);
		menuBar.add(counterValue);
		menuBar.add(Antcount);
		menuBar.add(antCounts);
		menuBar.add(Bugcount);
		menuBar.add(bugCounts);

		//setting for gameOver JDialog
		gameOver.setSize(200,100);
		gameOver.setLocationRelativeTo(null);
		gameOver.add(overText);

		//setting for instruction JDialog
		instruction.setSize(400,400);
		instruction.setLocationRelativeTo(null);
		instruction.add(instructPanel);

		overText.add(showOver);
		//instructPanel.add(scrollBarForTextArea);
		this.setPreferredSize(new Dimension(500, 200)); //for instruction one
		setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null); //Set the window to middle
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
    }//end of constructor
	
	//Yong Wen Kai
    //Reset the buttons after every time before the game logic ended.
	public void setButtonNull(){
		
		for(int i = 0; i < row; i++){
				for(int j = 0; j < column; j++){
					button[i][j].setIcon(null);
				}
			}
	}
	
	//Leong Kah Ming
	//Function to set the ant bug image to the buttons.
	public void setButtonImage(){
		
		for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if((board.world[i][j] != null)){
					if(board.world[i][j].getType() == 1){
						button[i][j].setIcon(antPic);
					}
					if(board.world[i][j].getType() == 2){
						button[i][j].setIcon(bugPic);
					}
				}
            }
        }
	}
	
	//Leong Kah Ming
	//start the game sequences.
	public void play(){

		int passSignal = 1;
		board.extendRound(passSignal);
		setButtonImage();

	}

	//functions to load image.
	private ImageIcon loadImage(String path){
		
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        //set scale of the image here.
        Image scaledImage = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
