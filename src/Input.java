import java.io.*;
import java.util.*;
/**
 * INPUT class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class Input 
{
    public Input() 
    {
    }

    public String readKeyboardInput() 
    {
        String inputString = "";
        try 
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));          
            inputString = reader.readLine();
        } 
        catch (IOException ioe) 
        {
            inputString = "";
        }
        return inputString;
    }
}