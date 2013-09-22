package memento;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Memento extends Frame{

	private static Memento game=new Memento();
	private static ArrayList<Piece> pl=new ArrayList<Piece>();
	private static int i_NumOfRow=8;
	private static int i_NumOfColumn=8;
	private static Integer step=1;
	private static int interval=1000;  //time interval
	private static int interval2=1000;  //time interval
	private static int intervalMinus=-0; //decrease time interval, to control game speed
	private static Set<Integer> randomNum = new LinkedHashSet<Integer>();  //unique numbers
	private static int chipLeft;
	private static boolean isCorrect;
	private static int state=0;
	private static int[] codeStart={27,26,19,20,29,34,43,44,37};
	private static int[] codeStep={26,27,28,29,35,36};
	private static int[] codeWord={8,9,10,11,12,13,14,15};
	private static Clip clip1;   //bmg
	private static Clip clip2;   //button
	private static Clip clip3;   //do you hear
	private static Clip clip4;   //fail sound
	
	Memento(){}

	public void setGame(){
		JFrame f=new JFrame("Memento, once remembered but chose to forget...");
		f.setLayout(new GridLayout(i_NumOfRow,i_NumOfColumn));
		for(int i=0;i<i_NumOfRow*i_NumOfColumn;i++){
			pl.add(new Piece(i));
			f.add(pl.get(i));
		}
		f.pack();
		f.setBounds(400, 100, 700, 700);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void startAnimation(){
		pl.get(codeStart[0]).setText("Start");
		pl.get(codeStart[0]).setMark(2);
		
		while (state == 0) {
			for (int i = 1; i < codeStart.length; i++) {
				pl.get(codeStart[i]).changeColor(2);
				try {
					Thread.sleep(interval2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (int i = 1; i < codeStart.length; i++) {
				pl.get(codeStart[i]).changeColor(1);
				try {
					Thread.sleep(interval2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void failAnimation(){
		clip1.stop();
		clip4.setMicrosecondPosition(0);
	    clip4.start();
		for(int i=0;i<i_NumOfRow*i_NumOfColumn;i++){
		    pl.get(i).clearUp();
		}
		pl.get(codeWord[1]).setText("Lost");
		pl.get(codeWord[2]).setText("It!");
		pl.get(codeWord[3]).setText("Now");
		pl.get(codeWord[4]).setText("You");
		pl.get(codeWord[5]).setText("Do");
		pl.get(codeWord[6]).setText("Know U");
		pl.get(codeWord[7]).setText("");
		pl.get(18).setText("Have");
		pl.get(19).setText("A");
		pl.get(20).setText("BAD");
		pl.get(21).setText("Memory");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stepAnimation(){
		try {
		for(int i=0;i<i_NumOfRow*i_NumOfColumn;i++){
		    pl.get(i).clearUp();
		}
		pl.get(codeStart[0]).setText("");
		switch(step){
			case 1:{
				pl.get(codeWord[0]).setText("");
				pl.get(codeWord[1]).setText("Where");
				pl.get(codeWord[2]).setText("is");
				pl.get(codeWord[3]).setText("the");
				Thread.sleep(1000+intervalMinus);
				pl.get(codeWord[4]).setText("Blue");
				Thread.sleep(1000+intervalMinus);
				pl.get(codeWord[5]).setText("Chip");
				pl.get(codeWord[6]).setText("?");
				pl.get(codeWord[7]).setText("!");
				Thread.sleep(2000+intervalMinus);
				pl.get(codeWord[4]).changeColor(2);
				Thread.sleep(2000+intervalMinus);
				break;
			}
			case 2:{
				interval=1500;
				pl.get(codeWord[1]).setText("Even");
				pl.get(codeWord[2]).setText("A");
				pl.get(codeWord[3]).setText("Blind");
				pl.get(codeWord[4]).setText("Can");
				pl.get(codeWord[5]).setText("Do");
				pl.get(codeWord[6]).setText("THAT");
				Thread.sleep(2000+intervalMinus);
				break;
			}
			case 3:{
				interval=1500;
				pl.get(codeWord[1]).setText("Just");
				pl.get(codeWord[2]).setText("Prove");
				pl.get(codeWord[3]).setText("You");
				pl.get(codeWord[4]).setText("Are");
				pl.get(codeWord[5]).setText("Not");
				pl.get(codeWord[6]).setText("Blind");
				Thread.sleep(2000+intervalMinus);
				break;
			}
			case 4:{
				interval=2000;
				pl.get(codeWord[1]).setText("See");
				pl.get(codeWord[2]).setText("What");
				pl.get(codeWord[3]).setText("You");
				pl.get(codeWord[4]).setText("Can");
				pl.get(codeWord[5]).setText("Do");
				pl.get(codeWord[6]).setText("NEXT");
				Thread.sleep(2000+intervalMinus);
				break;	
			}
			case 5:{
				interval=2000;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("Not");
				pl.get(codeWord[3]).setText("Too");
				pl.get(codeWord[4]).setText("Bad");
				pl.get(codeWord[5]).setText("Though");
				pl.get(codeWord[6]).setText("");
				Thread.sleep(2000+intervalMinus);
				break;	
			}
			case 6:{
				interval=2500;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("");
				pl.get(codeWord[3]).setText("Taste");
				pl.get(codeWord[4]).setText("THIS");
				pl.get(codeWord[5]).setText("");
				pl.get(codeWord[6]).setText("");
				Thread.sleep(1000+intervalMinus);
				break;		
			}
			case 7:{
				interval=3000;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("OK");
				pl.get(codeWord[3]).setText(",");
				pl.get(codeWord[4]).setText("Lucky");
				pl.get(codeWord[5]).setText("You");
				pl.get(codeWord[6]).setText("");
				Thread.sleep(1000+intervalMinus);
				break;	
			}
			
			case 8:{
				interval=3000;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("Want");
				pl.get(codeWord[3]).setText("Some");
				pl.get(codeWord[4]).setText("More");
				pl.get(codeWord[5]).setText("!?");
				pl.get(codeWord[6]).setText("");
				Thread.sleep(1000+intervalMinus);
				break;	
			}
			
			case 9:{
				interval=4000;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("You");
				pl.get(codeWord[3]).setText("Almost");
				pl.get(codeWord[4]).setText("There");
				pl.get(codeWord[5]).setText("!..");
				pl.get(codeWord[6]).setText("");
				Thread.sleep(1000+intervalMinus);
				break;	
			}
			
			case 10:{				
				clip1.stop();
			    clip3.start();
				interval=5000;
				pl.get(codeWord[1]).setText("You");
				pl.get(codeWord[2]).setText("Really");
				pl.get(codeWord[3]).setText("Know");
				pl.get(codeWord[4]).setText("What");
				pl.get(codeWord[5]).setText("To");
				pl.get(codeWord[6]).setText("Do");
				pl.get(codeWord[7]).setText("?...");
				Thread.sleep(5000);
				for(int i=0;i<i_NumOfRow*i_NumOfColumn;i++){
					pl.get(i).clearUp();
				}
				pl.get(16).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(17).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(18).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(19).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(20).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(23).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(24).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(25).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(26).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(27).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(28).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(29).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(30).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(31).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(34).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(35).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(36).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(37).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(38).setFont(new Font("Comic Sans MS", Font.BOLD,29));
				pl.get(39).setFont(new Font("Comic Sans MS", Font.BOLD,29));

				pl.get(16).setText("H");
				pl.get(17).setText("A");
				pl.get(18).setText("P");
				pl.get(19).setText("P");
				pl.get(20).setText("Y");
				Thread.sleep(2000);
				pl.get(24).setText("B");
				pl.get(25).setText("I");
				pl.get(26).setText("R");
				pl.get(27).setText("T");
				pl.get(28).setText("H");
				pl.get(29).setText("D");
				pl.get(30).setText("A");
				pl.get(31).setText("Y");
				Thread.sleep(1000);
				pl.get(34).setText("L");
				pl.get(35).setText("O");
				pl.get(36).setText("U");
				pl.get(37).setText("I");
				pl.get(38).setText("S");
				pl.get(39).setText("!!!");
				Thread.sleep(70000);
				break;	
			}
			
			case 11:{
				interval=4000;
				pl.get(codeWord[1]).setText("");
				pl.get(codeWord[2]).setText("You");
				pl.get(codeWord[3]).setText("Always");
				pl.get(codeWord[4]).setText("Can");
				pl.get(codeWord[5]).setText("Do");
				pl.get(codeWord[6]).setText("More:-)");
				Thread.sleep(1000+intervalMinus);
				break;	
			}
		}
			pl.get(codeStep[0]).setText("S");
			Thread.sleep(100);
			pl.get(codeStep[1]).setText("T");
			Thread.sleep(100);
			pl.get(codeStep[2]).setText("E");
			Thread.sleep(100);
			pl.get(codeStep[3]).setText("P");
			Thread.sleep(100);
			pl.get(codeStep[4]).setText(":");
			Thread.sleep(1000+intervalMinus);
			pl.get(codeStep[5]).setText(step.toString());
			Thread.sleep(2000+intervalMinus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setBoard(){
		Integer i;
		stepAnimation();
		//clear the buttons
		for(i=0;i<i_NumOfRow*i_NumOfColumn;i++){
			pl.get(i).clearUp();
		}
		randomNum.clear();
		chipLeft=step;
		
		//generate random sequence to color the chips
		Random randomGenerator=new Random();
		while(randomNum.size()<step){
			randomNum.add(randomGenerator.nextInt(i_NumOfRow*i_NumOfColumn)); 
	    }
		//show the solution for interval secs
		Iterator<Integer> it=randomNum.iterator();
		while((it.hasNext())){	
            Integer j=(int)it.next();
			pl.get(j).changeColor(2);
	    }
		
		it=randomNum.iterator();
		try {
				Thread.sleep(interval);    //hold on for secs
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		//change color back
		while((it.hasNext())){	
			   pl.get((int)it.next()).changeColor(1);   //change color back
		    }
		
		//make buttons clickable
		for(i=0;i<i_NumOfRow*i_NumOfColumn;i++){
		    pl.get(i).makeClickable();
		}
	}
	
	public static void resetGame(){
		step=1;
		state=0;
        interval=1000;  //time interval
        interval2=1000;  //time interval
        intervalMinus=-700; //faster the game speed
	    for(int i=0;i<i_NumOfRow*i_NumOfColumn;i++){
		    pl.get(i).clearUp();
		}
	    game.startAnimation();
		game.startGame();
	}
	
	public static void onlineJudge() {
		isCorrect=true;
		Iterator<Integer> it = randomNum.iterator();
		chipLeft--;   //every click chip left subtracted
		while (it.hasNext()) {
			if (!pl.get((int) it.next()).isMarkedBlue()){//check EVERY blue chips, if one not clicked, then not correct
				isCorrect = false;
			}
		}
		
		if (isCorrect) {
			step++;
			game.setBoard();
		}
		
		if(chipLeft==0){       
			game.failAnimation();
			clip1.setMicrosecondPosition(0);
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
			resetGame();
			return;
		}
	}
	
	public static void changeState(int i){
		state=i;
	}
	
	public static void changeInterval(int i){
		interval2=i;
	}
	
	public void startGame(){
//		pl.get(i_NumOfColumn*i_NumOfRow/2).setText("X");
		setBoard();
	}
	
	public void initializeSound(){
		try{
		String soundName ="rsc/pol.wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
		clip1 = AudioSystem.getClip();
		clip1.open(audioInputStream);
		clip1.loop(Clip.LOOP_CONTINUOUSLY);
		soundName="rsc/button.wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
		clip2 = AudioSystem.getClip();
		clip2.open(audioInputStream);
		soundName="rsc/do.wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
		clip3 = AudioSystem.getClip();
		clip3.open(audioInputStream);
		soundName="rsc/fail.wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
		clip4 = AudioSystem.getClip();
		clip4.open(audioInputStream);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void makeSound(int i){
		if(i==1) clip1.stop();
		else{
			clip2.setMicrosecondPosition(0);
		    clip2.start();
		}
	}
	
	public static void main(String[] args){
		game.initializeSound();
		game.setGame();
		game.startAnimation();
		game.startGame();
	}
}

class Piece extends JButton implements ActionListener{
	private int mark=0;   //mark==1 when it is painted blue, 2 used as a start button
	private Integer i_serialNum;
	private boolean clickable=false;
	
	
	Piece(int i){
		i_serialNum=i;
		setBackground(new Color(230, 126, 34));  //green rgb(46, 204, 113) pumpkin rgb(211, 84, 0) blue (52, 152, 219)
		setBorder(BorderFactory.createLineBorder(new Color(211, 84, 0),3)); //carrot rgb(230, 126, 34)
		this.addActionListener(this);
		setFont(new Font("Comic Sans MS", Font.BOLD,20));
		setFocusPainted(false);   //to diminish the focus
//		setText(i_serialNum.toString());
	}
	
	public void clearUp(){
		mark=0;
		clickable=false;
		setText("");
//		changeColor(1);
		setBackground(new Color(230, 126, 34));
		makeUnClickable();
	}
	
	public void actionPerformed(ActionEvent ae){
        if(mark==1) return;    //a very simple solution, disable this chip when it has been blued
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		        @Override
		        public Void doInBackground() {
		        	if(mark==2) noticeMemento(1);
		        	if(clickable){
		        		noticeMemento(2);
		        	}
		        	return null;
		        }
		        @Override
		        protected void done() {
		        }
		    };
		worker.execute();   //remeber to execute the worker
		if(clickable){
        setBackground(new Color(52, 152, 219)); 
        mark=1;
		}
	}
	
	public void changeColor(int i){    //when i=1, orange; 2, blue
		if(i==1) {setBackground(new Color(230, 126, 34));}
		else if(i==2) setBackground(new Color(52, 152, 219)); 	

	}
	
	public void setMark(int i){
		mark=i;
	}
	
	public void noticeMemento(int i){
		if(i==2){
			Memento.makeSound(2);
			Memento.onlineJudge();   //any other way?
		}
		else if(i==1){
			Memento.makeSound(2);
			Memento.changeState(1);
			Memento.changeInterval(100);
		}
	}
	
	public int getMark(){
		return mark;
	}
	
	public void makeClickable(){
		clickable=true;
	}
	
	public void makeUnClickable(){
		clickable=false;
	}
	
	public boolean isMarkedBlue(){
		return mark==1;
	}
	
}
