/**
 * SHIP class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class Ship 
{
    private String shipName;
    private int xPos;
    private int yPos;
    private int noOfHitsMade;
    private int noOfHitsNeeded;

    /**
     * Constructor for objects of class SHIP
     */
    public Ship() 
    {
        shipName = "";
        xPos = 0;
        yPos = 0;
        noOfHitsMade = 0;
        noOfHitsNeeded = 0;
    }

    public Ship(String shipName, int xPos, int yPos, int noOfHitsMade, int noOfHitsNeeded) 
    {
        this.shipName = shipName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.noOfHitsMade = noOfHitsMade;
        this.noOfHitsNeeded = noOfHitsNeeded;
    }

    public Ship(String shipName, int xPos, int yPos, int noOfHitsNeeded) 
    {
        this.shipName = shipName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.noOfHitsMade = 0;
        this.noOfHitsNeeded = noOfHitsNeeded;
    }

    public boolean isDestroyed() 
    {
        return this.noOfHitsMade >= this.noOfHitsNeeded;
    }

    public boolean isDamaged() 
    {
        return this.noOfHitsMade > 0 && this.noOfHitsMade < this.noOfHitsNeeded;
    }

    public boolean isUntouched() 
    {
        return this.noOfHitsMade == 0;
    }

    public boolean isHit() 
    {
        if (this.isDestroyed()) 
            return false;
        else 
        {
            this.noOfHitsMade += 1;
            if (this.isDestroyed()) 
            {
                System.out.println("Unfortunately, ship " + this.shipName + " has been destroyed");
            }
            return true;
        }
    }

    public String getShipName() 
    {
        return this.shipName;
    }

    public void setShipName(String shipName) 
    {
        this.shipName = shipName;
    }

    public int getXPos() 
    {
        return this.xPos;
    }

    public void setXPos(int xPos) 
    {
        this.xPos = xPos;
    }

    public int getYPos() 
    {
        return this.yPos;
    }

    public void setYPos(int yPos) 
    {
        this.yPos = yPos;
    }

    public int getNoOfHitsMade() 
    {
        return this.noOfHitsMade;
    }

    public void setNoOfHitsMade(int noOfHitsMade) 
    {
        this.noOfHitsMade = noOfHitsMade;
    }

    public int getNoOfHitsNeeded() 
    {
        return this.noOfHitsNeeded;
    }

    public void setNoOfHitsNeeded(int noOfHitsNeeded) 
    {
        this.noOfHitsNeeded = noOfHitsNeeded;
    }
    
    public void displayShip()
    {
        System.out.println("Here are the ship information:");
        System.out.println(" Name:" + shipName);
        System.out.printf(" X Position: %d%n", this.xPos);
        System.out.printf(" Y Position: %d%n", this.yPos);
        System.out.printf(" Number of hits needed to destroy：%d%n", this.noOfHitsNeeded);
    }
}