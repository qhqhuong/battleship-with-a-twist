/**
 * VALIDATION class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class Validation 
{
    public Validation() 
    {
    }

    /**
     * Check whether ship name string is valid
     * @param shipName
     * @return
     */
    public boolean validateShipName(String shipName) 
    {
        boolean isValid = (shipName.length() >= 3) && (shipName.length() <= 15);
        return isValid;
    }

    /**
     * Check whether x/y coordinate is valid within an upperbound
     * @param input
     * @param upperbound
     * @return
     */
    public boolean validateCoordinate(String input, int upperbound) 
    {
        boolean isValid = false;
        try 
        {
            int coordinate = Integer.parseInt(input);
            isValid = coordinate >= 0 && coordinate < upperbound;
        } 
        catch (Exception e) 
        {
            return false;
        }
        return isValid;
    }

    public boolean validateInteger(String input) 
    {
        try 
        {
            Integer.parseInt(input);
        } 
        catch (Exception e) 
        {
            return false;
        }
        return true;
    }

    public boolean validateBoolean(String input) 
    {
        try 
        {
            Boolean.parseBoolean(input);
        } 
        catch (Exception e) 
        {
            return false;
        }
        return true;
    }
}
    
