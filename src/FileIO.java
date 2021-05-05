import java.io.*;
import java.util.*;
/**
 * FILEIO class.
 * 
 * @author Quynh Huong Nguyen 
 * @version 3 (21 Oct 2018)
 */
public class FileIO 
{
    private String fileName;

    public FileIO(String fileName) 
    {
        this.fileName = fileName;
    }


    public String getFileName() 
    {
        return this.fileName;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String[] readFile() 
    {
        String contentString = "";
        try 
        {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            contentString = in.readLine();
            in.close();
        } 
        catch (IOException e) 
        {
            System.out.println("File Read Error");
        }
        return contentString.split(",");
    }

    public void writeFile(String content) 
    {
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(content);
            out.close();
        } 
        catch (IOException e) 
        {
            System.out.println("File Write Error");
        }

    }
}