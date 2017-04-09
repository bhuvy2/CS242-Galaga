package Ships;

import Gametypes.Projectile;
import display.view.GameWindow;

/**
 * Created by mscislowski on 4/9/17.
 */
public class AlienMissile extends Missile implements Projectile {
    //Selects a random slope to fire if none is provided
    private static final int[] slopes = {-3, -2, -1, 0, 0, 1, 2, 3};

    protected int toRightNext,            //This is the next x coordinate of the missile
            toEdgeNext,                   //The next y coordinate of the missile
            slope,                        //What slope the missile is at
            count;                        //Mediate how fast the missile gets fired
    private static final int LENGTH = 2;  //Length of how long the missile is


    /**
     * Instantiates a new alien missile.
     * @param Owner is the owner of said missile
     */
    public AlienMissile(Alien Owner) {
        super(Owner.getToRight(), Owner.getToEdge());
        int r;
        if(Owner.getToRight() / GameWindow.getBoardWidth() * 100 < GameWindow.getBoardWidth() / 2)
            r = (int) (Math.random() * slopes.length/2);
        else
            r = (int) (Math.random() * slopes.length/2 + slopes.length/2);
        slope = slopes[r];
        count = 0;
    }

    /**
     * Instantiates a new alien missile. This is the second constructor for a specific slope.
     * @param a the Alien a is the Owner of the missile
     * @param slope the slope is a slope that is presumed to be in the array slopes or within {-3, -2, -1, 0, 0, 1, 2, 3}
     */
    public AlienMissile(Alien alien, int slope) {
        super(alien.getToRight() + GameWindow.getBoardWidth()/(4*alien.getImage().getIconWidth())+1,
                alien.getToEdge()+ GameWindow.getBoardHeight()/(4*alien.getImage().getIconHeight()));
        this.slope = slope;
        count = 0;
    }

    public void move() {
        if(count % Alien.DELAY == 0) {
            switch(slope) {
                case 0:
                    toEdge++;
                    toRightNext = toRight;
                    toEdgeNext = toEdge+LENGTH;
                    break;
                case -1:
                    toEdge++;
                    toRight++;
                    toEdgeNext = (int)(toEdge + LENGTH * Math.sqrt(2));
                    toRightNext = (int)(toRight + LENGTH * Math.sqrt(2));
                    break;
                case 1:
                    toEdge++;
                    toRight--;
                    toEdgeNext = (int)(toEdge + LENGTH * Math.sqrt(2));
                    toRightNext = (int)(toRight - LENGTH * Math.sqrt(2));
                    break;
                case -2:
                    toEdge+= 2;
                    toRight++;
                    toEdgeNext = (int)(toEdge + LENGTH * Math.sqrt(5));
                    toRightNext = (int)(toRight + LENGTH * Math.sqrt(5));
                    break;
                case 2:
                    toEdge+=2;
                    toRight--;
                    toEdgeNext = (int)(toEdge + LENGTH * Math.sqrt(5));
                    toRightNext = (int)(toRight - LENGTH * Math.sqrt(5));
                    break;
                default:
                    break;
            }
        }
        count++;

    }

    public boolean isVisible() {
        return (toEdge < GameWindow.getBoardHeight() && toEdge > 0) && (toRight < GameWindow.getBoardWidth() && toRight > 0);
    }
}
