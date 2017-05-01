package model.objects.ship;

import display.view.GameWindow;
import io.GameConfig;
import model.Game;
import model.objects.projectile.Missile;
import model.superclasses.GameSprite;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public abstract class Ship extends GameSprite {
    // Missile storage
    ArrayList<Missile> storage;

    // Number of lives ship has
    int lives;
    private int MAX_LIVES = 5;
    private int bossesKilled = 0;
    private Game game;
    private int shield = 0;
    private int iframeCharge = 0;

    // Maximum number of shots allowed
    private static int MAX_SHOTS = 2, BONUS_SHOTS = 0;


    // Valid options for ship
    private boolean canMove, canFire,
	    isInvincible,
	    canThrottle,
	    multipleShots;
    
    enum ShipState {
    	LEFT,
    	RIGHT,
    	STOP
    };
    
    ShipState current;

	/**
	 * Keeps track of the ship death animation
	 */
	private boolean isDying, iframes;

	private int deathCount, iframeCount;

    /**
     * Ship constructor
     * @param str String to image file passed to Gamesprite
     */
    public Ship(String str, Game game){
    	super(str);
    	this.getImage().setDescription("Basic Ship");
    	this.lives = 2;
    	this.canMove = true;
    	this.canFire = true;
    	this.isInvincible = false;
    	this.canThrottle = false;
    	this.multipleShots = false;
    	this.isDying = false;
    	this.game = game;
    	current = ShipState.STOP;
    }


    public boolean fire() {
//        System.out.println(canFire + " M" + MAX_SHOTS + " B" + BONUS_SHOTS + " S" + storage.size());
        if (canFire && storage.size() < MAX_SHOTS) {
            if (!multipleShots) {
                checkBossKills();
            } else {
                multiShot();
            }
            return true;
        }
        return false;
    }

    private void checkBossKills() {
        switch (bossesKilled) {
        case 0:
            game.incrementShotsFired(1);
            addMissile(-4, -35);
            break;
        case 1:
            game.incrementShotsFired(2);
            addMissile(-24, -35);
            addMissile(16, -35);
            break;
        case 2:
            game.incrementShotsFired(3);
            addMissile(-4, -30);
            addMissile(-24, -35);
            addMissile(16, -35);
            break;
        case 3:
            game.incrementShotsFired(5);
            addMissile(-4, -30);
            addMissile(-24, -35);
            addMissile(16, -35);
            addMissile(-34, -25);
            addMissile(26, -25);
            break;
        default:
            if (bossesKilled > 3) {
                multipleShots = true;
                MAX_SHOTS += 20;
                multiShot();
            }
            break;
        }
    }

    private void multiShot() {
        game.incrementShotsFired(7);
        addMissile(-4, -30);
        addMissile(-4, -60);
        addMissile(-4, 0);
        addMissile(-24, -35);
        addMissile(16, -35);
        addMissile(-34, -25);
        addMissile(26, -25);
    }

    private void addMissile(int offset1, int offset2) {
        Missile missile = new Missile(this.x +
                image.getIconWidth()/2+offset1+3, 
                GameWindow.BOARD_HEIGHT-this.image.getIconHeight()+offset2 - Missile.HEIGHT);
        storage.add(missile);
    }


    public abstract void change();

    /**
     * Draws and moves ship's missiles each tick then removes 
     * them once they leave the screen
     */
    public void tick() {
        // Check shield state and change icon
        checkShield();
        moveMissiles();
        handleMovement();
        if (iframeCharge < 500)
            iframeCharge++;
    }

    private void moveMissiles() {
        // Move/Remove missiles
        for (int i = 0; i < storage.size(); i++) {
            Missile m = storage.get(i);
            m.move();
            if (m.getY() < 0) {
                storage.remove(i);
                i--;
            }
        }
    }

    private void handleMovement() {
        // Handle movement continuation
        if(this.canMove){
            switch(current){
                case LEFT:
                    if(this.x > 0)
                        this.x -= 3;
                    break;
                case RIGHT:
                    if(this.x < GameWindow.BOARD_WIDTH-this.image.getIconWidth())
                        this.x += 3;
                    break;
                case STOP:
                    break;
                default:
                    break;
            }
        } else {
            current = ShipState.STOP;
        }
    }

    private void checkShield() {
        if (shield > 0 && getImage().getDescription().equals("Basic Ship")) {
            this.setImage(GameConfig.getShieldShipPath());
            this.getImage().setDescription("Shield Ship");
        } else if(shield == 0 && getImage().getDescription().equals("Shield Ship")) {
            this.setImage(GameConfig.getShipPath());
            this.getImage().setDescription("Basic Ship");
        }
    }
    
    public void setLeft(){
    	current = ShipState.LEFT;
    }
    
    public void setRight(){
    	current = ShipState.RIGHT;
    }
    
    public void setStop(){
    	current = ShipState.STOP;
    }

    public boolean canFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        if (lives <= MAX_LIVES)
            this.lives = lives;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public boolean canThrottle() {
        return canThrottle;
    }

    public void setCanThrottle(boolean canThrottle) {
        this.canThrottle = canThrottle;
    }

    public ArrayList<Missile> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<Missile> input) {
        storage = input;
    }

    public boolean isMultipleShots() {
        return multipleShots;
    }

    public void setMultipleShots(boolean multipleShots) {
        this.multipleShots = multipleShots;
    }
    
    public void die(){
    	isDying = true;
    	deathCount = 1;
    	isInvincible = true;
    	canMove = false;
    }

    public void startIframes() {
        iframes = true;
        iframeCount = 1;
        isInvincible = true;
    }

    /**
	 * Checks if ship is performing death animation and updates ship icon.
	 * Ship is set to invincible and cannot move during this time.
	 * @param game TODO
	 */
	public void checkDead(Game game) {
	    if (isDying) {
	        deathCount++;
	        game.brd.playKill();
	        // Set animation states
	        if (deathCount == 1) {
	            setCanMove(false);
	            change();
	        }
	        else if(deathCount == 52){
	            deathCount = 0;
	            isDying = false;
	            setCanMove(true);
	            setInvincible(false);
	            game.resetAttack();
	        }
	        else if(deathCount % 10 == 2){
	        	change();
	        }
	    }
	}

	public void iframeAni() {
	    if (iframes) {
	        iframeCount++;
            if(iframeCount == 52) {
	            isInvincible = false;
	            iframeCount = 0;
	            iframes = false;
            }
        }
    }

    public static void setBonusShots(int bonusShots) {
        BONUS_SHOTS = bonusShots;
    }

    public void setBossesKilled(int bossesKilled) {
        this.bossesKilled = bossesKilled;
    }

    public static int getMaxShots() {
        return MAX_SHOTS;
    }

    public static void setMaxShots(int maxShots) {
        if (maxShots == 500) {
            MAX_SHOTS = maxShots;
        } else {
            MAX_SHOTS = 2 + BONUS_SHOTS;
        }
    }

    public int getMAX_LIVES() {
        return MAX_LIVES;
    }

    public int getNumShields() {
	    return shield;
    }

    public void incrementShield() {
	    shield++;
    }

    public void decrementShield() {
	    shield--;
    }

    public boolean isShielded() {
	    return shield > 0;
    }

    public int getBossesKilled() {
        return bossesKilled;
    }

    public void incrementBossKills() {
        bossesKilled++;
        BONUS_SHOTS += 4;
        MAX_SHOTS = MAX_SHOTS == 500 ? 500 : 2 + BONUS_SHOTS;
    }

    public int getIframeCharge() {
        return iframeCharge;
    }

    public void setIframeCharge(int iframeCharge) {
        this.iframeCharge = iframeCharge;
    }
}