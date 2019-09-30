package ps1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Anaga {
	
	public static String sortString(String inputString) 
    { 
        // convert input string to char array 
        char tempArray[] = inputString.toCharArray(); 
          
        // sort tempArray 
        Arrays.sort(tempArray); 
          
        // return new sorted string 
        return new String(tempArray); 
    } 
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int n = s.nextInt(), k = s.nextInt();
		Set<String> result = new HashSet<String>();
		Set<String> store = new HashSet<String>();
		for(int i = 0; i < n; i++)
		{
			String temp = sortString(s.next());
			if(!store.contains(temp))
			{
				if(result.contains(temp))
				{
					result.remove(temp);
					store.add(temp);
				}
				else{
					result.add(temp);
				}
			}
		}
		System.out.println(result.size());
	}

}
