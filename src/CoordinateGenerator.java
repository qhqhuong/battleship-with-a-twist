import java.lang.Math;
import java.util.Random;
/**
 * COORDINATEGENERATOR class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class CoordinateGenerator 
{
    /**
     * Initial a RandomNumberGenerator object
     */
    public CoordinateGenerator() 
    {
    }

    /**
     * Generate a random integer in range [minimumValue, maximumValue]
     * @param minimumValue
     * @param maximumValue
     * @return
     */
    public int generateRandomNumber(int minimumValue, int maximumValue)
    {
        Random rand = new Random();
        return rand.nextInt((maximumValue - minimumValue) + 1) + minimumValue;
    }
}



