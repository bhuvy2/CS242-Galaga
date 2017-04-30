package model.objects.aliens;

import java.util.LinkedList;
import java.util.Queue;

import display.view.GameWindow;
import model.objects.Delta;

public abstract class RedAlienState {
   public static final class Normal extends RedAlienState {
	   
   }

   public static final class Attacking extends RedAlienState {
	   public final Queue<Delta> path;
	   
	   static final Delta ldiag = new Delta(-1, 1);
	   static final Delta down = new Delta(0, 1);
	   static final Delta rdiag = new Delta(1, 1);
	   
	   public RedAlien counter;
	   
	   public Attacking(int x, int y){
		   counter = new RedAlien(x, y);
		   counter.setMoving(true);
		   path = new LinkedList<Delta>();
		   
		   
		   while(y < GameWindow.BOARD_HEIGHT){
			   Delta cur = pickRandomDirection();
			   for(int i = 0; i < 50; ++i){
				   path.add(cur);
				   x += cur.xd;
				   y += cur.yd;
				   counter.move();
			   }
		   }
	   }

	private Delta pickRandomDirection() {
		Delta cur = null;
		int r = (int)(Math.random()*3);
		switch(r){
		case 0:
			cur = ldiag;
			break;
		case 1:
	   		cur = down;
	   		break;
		default:
			cur = rdiag;
			break;
		}
		return cur;
	}
   }
}