package lab5;

public class Lab5Example
{
  public static void main(String[] args)
  {
    System.out.println(longestRun("aabbbccd"));
    System.out.println("Expected 3");
    System.out.println(longestRun("aaa"));
    System.out.println("Expected 3");
    System.out.println(longestRun("aabbbb"));
    System.out.println("Expected 4");
  }
  
 
  public static int longestRun(String s)
  {
    int count = 1;
    int max = 1;
    
    // start with the first character, see how long a run there is
    char current = s.charAt(0);
    for (int i = 1; i < s.length(); i += 1)
    {
      char c = s.charAt(i);
      if (c == current)
      {
        // matches the 'current' character, add 1
        count += 1;
        //if the run is at the end of the string
        if(i == s.length() - 1) {
        	max = count;
        	break;
        }
      }
      else
      {
        // that was the end of the run; if it was a longer run, make that the max
        if (count > max)
        {
        	//swapped two variables
          max = count;
          count = 1;
          
        }

        // start counting a new run of a different character
        current = c;       
      }
    }
    
    // this should be the length of the longest run we found
    return max;
  }

  
  
}
