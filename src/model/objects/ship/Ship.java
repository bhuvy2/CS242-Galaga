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

    /**
     * Fires missiles
     * @return true if missiles fired
     */
    public boolean fire() {
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

    /**
     * Missile patterns per boss kills
     */
    private void checkBossKills() {
        switch (bossesKilled) {
        case 0: // 0 bosses
            game.incrementShotsFired(1);
            addMissile(-4, -35);
            break;
        case 1: // 1 boss
            game.incrementShotsFired(2);
            addMissile(-24, -35);
            addMissile(16, -35);
            break;
        case 2: // 2 bosses
            game.incrementShotsFired(3);
            addMissile(-4, -30);
            addMissile(-24, -35);
            addMissile(16, -35);
            break;
        case 3: // 3 bosses
            game.incrementShotsFired(5);
            addMissile(-4, -30);
            addMissile(-24, -35);
            addMissile(16, -35);
            addMissile(-34, -25);
            addMissile(26, -25);
            break;
        default: // 4+ bosses
            if (bossesKilled > 3) {
                multipleShots = true;
                MAX_SHOTS += 20;
                multiShot();
            }
            break;
        }
    }

    /**
     * Multishot missile pattern
     */
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

    /**
     * Adds missiles to storage with an x and y offset
     * @param offset1 x offset
     * @param offset2 y offset
     */
    private void addMissile(int offset1, int offset2) {
        Missile missile = new Missile(this.x +
                image.getIconWidth()/2+offset1+3, 
                GameWindow.BOARD_HEIGHT-this.image.getIconHeight()+offset2);
        missile.setY(missile.getY() - missile.getImage().getIconHeight());
        storage.add(missile);
    }

    /**
     * Changes ship icon
     */
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

    /**
     * Moves missiles on ticks
     */
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

    /**
     * Checks movement to cause smoother response time
     */
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

    /**
     * Handles ship sprites when gaining/losing shields
     */
    private void checkShield() {
        // Shield Icon if has shield
        if (shield > 0 && getImage().getDescription().equals("Basic Ship")) {
            this.setImage(GameConfig.getShieldShipPath());
            this.getImage().setDescription("Shield Ship");
        } else if(shield == 0 && getImage().getDescription().equals("Shield Ship")) { // Ship icon no shield
            this.setImage(GameConfig.getShipPath());
            this.getImage().setDescription("Basic Ship");
        }
    }

    /**
     * Sets ship state to left
     */
    public void setLeft(){
    	current = ShipState.LEFT;
    }

    /**
     * Sets ship state to right
     */
    public void setRight(){
    	current = ShipState.RIGHT;
    }

    /**
     * Sets ship state to stop
     */
    public void setStop(){
    	current = ShipState.STOP;
    }

    /**
     * Checks if ship can fire
     * @return true if canFire
     */
    public boolean canFire() {
        return canFire;
    }

    /**
     * Checks if ship can move
     * @return true if can move
     */
    public boolean canMove() {
        return canMove;
    }

    /**
     * Sets ship movement capability
     * @param canMove true if allowing ship to move
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * Retrieves number of lives the ship has
     * @return number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the number of lives the ship has
     * @param lives new life count
     */
    public void setLives(int lives) {
        if (lives <= MAX_LIVES)
            this.lives = lives;
    }

    /**
     * Checks if ship is invincible
     * @return true if invincible
     */
    public boolean isInvincible() {
        return isInvincible;
    }

    /**
     * Sets ship as invincible or not
     * @param isInvincible state of invincibility
     */
    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    /**
     * Checks if ship allows for throttling the game
     * @return true if game is throttlable
     */
    public boolean canThrottle() {
        return canThrottle;
    }

    /**
     * Sets throttle state
     * @param canThrottle true if allowing throttling
     */
    public void setCanThrottle(boolean canThrottle) {
        this.canThrottle = canThrottle;
    }

    /**
     * Retrieves ship storage for missiles
     * @return
     */
    public ArrayList<Missile> getStorage() {
        return storage;
    }

    /**
     * Checks if ship has multi shot enabled
     * @return true if enabled
     */
    public boolean isMultipleShots() {
        return multipleShots;
    }

    /**
     * Sets multi shot state
     * @param multipleShots state to set multi shot
     */
    public void setMultipleShots(boolean multipleShots) {
        this.multipleShots = multipleShots;
    }

    /**
     * Starts Ship death states, death animation is started,
     * ship is invincible for animation, no user interaction
     */
    public void die(){
    	isDying = true;
    	deathCount = 1;
    	isInvincible = true;
    	canMove = false;
    }

    /**
     * Starts iFrame animation. Ship is invincible for 52 ticks
     * Used as a power up with a charge rate and as a usage for shields.
     */
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

    /**
     * Frame count where ship is invincible
     */
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

    /**
     * Sets bonus shots to new count. Incremented on boss kills
     * @param bonusShots new amount of bonus shots
     */
    public static void setBonusShots(int bonusShots) {
        BONUS_SHOTS = bonusShots;
    }

    /**
     * Sets number of bosses killed
     * @param bossesKilled new number of bosses killed
     */
    public void setBossesKilled(int bossesKilled) {
        this.bossesKilled = bossesKilled;
    }

    /**
     * Retrieves number of shots ship can fire
     * @return number of missiles allowed to fire
     */
    public static int getMaxShots() {
        return MAX_SHOTS;
    }

    /**
     * Sets new missile limit to 2 + Bonus shots, or to 500 if using cheat code
     * @param maxShots number of shots allowed for ship
     */
    public static void setMaxShots(int maxShots) {
        if (maxShots == 500) {
            MAX_SHOTS = maxShots;
        } else {
            MAX_SHOTS = 2 + BONUS_SHOTS;
        }
    }

    /**
     * Retrieves the max number of lives a ship can have at any point
     * @return max number of lives
     */
    public int getMAX_LIVES() {
        return MAX_LIVES;
    }

    /**
     * Retrieves number of shields the ship has
     * @return number of shields present
     */
    public int getNumShields() {
	    return shield;
    }

    /**
     * Increments the number of shields
     */
    public void incrementShield() {
	    shield++;
    }

    /**
     * Decrements the number of shields
     */
    public void decrementShield() {
	    shield--;
    }

    /**
     * Checks if ship is shielded
     * @return true if ship has shields
     */
    public boolean isShielded() {
	    return shield > 0;
    }

    /**
     * Retrieves the number of bosses killed
     * @return number of killed bosses
     */
    public int getBossesKilled() {
        return bossesKilled;
    }

    /**
     * Increments boss kills and bonus shots then updates max shots unless cheat is on
     */
    public void incrementBossKills() {
        bossesKilled++;
        BONUS_SHOTS += 4;
        MAX_SHOTS = MAX_SHOTS == 500 ? 500 : 2 + BONUS_SHOTS;
    }

    /**
     * Checks current iframe charge
     * @return current charge
     */
    public int getIframeCharge() {
        return iframeCharge;
    }

    /**
     * Sets current charge
     * @param iframeCharge new charge
     */
    public void setIframeCharge(int iframeCharge) {
        this.iframeCharge = iframeCharge;
    }

    /**
     * Clears ship missile storage
     */
    public void clearStorage() {
	    storage.removeAll(storage);
    }
}