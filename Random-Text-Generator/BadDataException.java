//Name: Jingyu Xie
//USC loginid:jingyuxi
//CSCI 455 PA4
//Spring 2016
import java.io.IOException;

/**
   This class reports bad input data.
*/
public class BadDataException extends IOException
{
   public BadDataException() {}
   public BadDataException(String message)
   {
      super(message);
   }
}