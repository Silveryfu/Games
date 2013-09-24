package memento;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingWorker;


public class Piece extends JButton implements ActionListener{
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
