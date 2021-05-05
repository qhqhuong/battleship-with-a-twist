import java.util.ArrayList;
/**
 * SHIPLIST class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class ShipList 
{
    private ArrayList<Ship> ships; 

    public ShipList() 
    {
        ships = new ArrayList<Ship>();
    }

    public ShipList(ArrayList<Ship> ships) 
    {
        this.ships = ships;
    }

    public ArrayList<Ship> getShips() 
    {
        return this.ships;
    }

    public void setShips(ArrayList<Ship> ships) 
    {
        this.ships = ships;
    }

    public void addShip(String shipName, int xPos, int yPos, int noOfHitsNeeded) 
    {
        Ship newShip = new Ship(shipName, xPos, yPos, noOfHitsNeeded);
        this.ships.add(newShip);
    }

    public boolean hasShipAtCoordinate(int x, int y) 
    {
        for (Ship ship : this.ships) 
        {
            if (ship.getXPos() == x && ship.getYPos() == y) 
                return true;
        }
        return false;
    }

    //printwriter  dimension
    
    public String getShipMarkerAtCoordinate(int x, int y, boolean shouldShowShip) 
    {
        for (Ship ship : this.ships) 
        {
            if (ship.getXPos() == x && ship.getYPos() == y) 
            {
                if (ship.isDestroyed()) 
                    return "X";
                else if (ship.isDamaged()) 
                    return "D";
                else if (ship.isUntouched() && shouldShowShip) 
                    return "O";
                else 
                    return "~";
            } 
        }
        return "~";
    }

    public boolean isHit(int x, int y) 
    {
        for (Ship ship : this.ships) 
        {
            if (ship.getXPos() == x && ship.getYPos() == y) 
                return ship.isHit();
        }
        return false;
    }

    public boolean isWipedOut() 
    {
        for (Ship ship : this.ships) 
        {
            if (!ship.isDestroyed()) 
                return false;
        }
        return true;
    }

    public int getNumberOfShips() 
    {
        return ships.size();
    }
}