package ps1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PS15 {
	public static void main(String[] args)
	{
		long start = 0;
		long end = 0;
		long timeInMillSec = 0;
		
		String[] a = getAlphaNumericString(2000, 7);
//		for(int i = 0; i < a.length; i++)
//		{
//			System.out.println(a[i]);
//		}
		
		start = System.nanoTime();
		int aa = ana(a, 100, 5);
		end = System.nanoTime();
		timeInMillSec =  (end - start)/1000;
		System.out.println(timeInMillSec);
		System.out.println(a.length);
//		System.out.println(aa);
	}
	public static String sortString(String inputString) {
		// convert input string to char array
		char tempArray[] = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	public static int ana(String[] s, int n, int k) {

		Set<String> result = new HashSet<String>();
		Set<String> store = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			String temp = sortString(s[i]);
			if (!store.contains(temp)) {
				if (result.contains(temp)) {
					result.remove(temp);
					store.add(temp);
				} else {
					result.add(temp);
				}
			}
		}

		return result.size();

	}

	static String[] getAlphaNumericString(int n, int k) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		
		String[] result = new String[n];
		// create StringBuffer size of AlphaNumericString
		

		
		
		for(int i = 0; i < n; i++)
		{
			StringBuilder sb = new StringBuilder(k);
			for (int j = 0; j < k; j++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index = (int) (AlphaNumericString.length() * Math.random());

				// add Character one by one in end of sb
				sb.append(AlphaNumericString.charAt(index));
			}
			result[i] = sb.toString();
		}
		return result;
	}

}
