package uttt;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;

public class UlTicatoe {

	private static UlTicatoe tic=null;
	private UlTicatoe(){};
	
	private static ArrayList<Piece> pl=new ArrayList<Piece>();
	
	private static int i_NumOfRow=9;
	private static int i_NumOfColumn=9;
	
	public static UlTicatoe getTic(){  //singleton
		if(tic==null){
			tic=new UlTicatoe();
		}
		return tic;
	}
	
	public void setGame(){
		JFrame f=new JFrame("Ticatoc-Ultimate Tic Tac Toc!");
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
	
	public static void main(String[] args) {

		UlTicatoe game=UlTicatoe.getTic();
		game.setGame();
	}

}


class Piece extends JButton{
	private Integer i_serialNum;
	
	Piece(int i){
		i_serialNum=i;
		
		setText(i_serialNum.toString());
		setBackground(new Color(26, 188, 156));  //Turquoise rgb(26, 188, 156)
		setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(22, 160, 133))); //green sea rgb(22, 160, 133)  ALizarin: rgb(231, 76, 60)
	}
}

