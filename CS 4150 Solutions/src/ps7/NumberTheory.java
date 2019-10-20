package ps7;

import java.util.Scanner;

public class NumberTheory {

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);

		while (s.hasNextLine()) {
			String[] line = s.nextLine().split(" ");
			String input = line[0];

			if (input.equals("gcd")) {
				int a = Integer.valueOf(line[1]);
				long b = Long.valueOf(line[2]);
				
				System.out.println(gcd(a, b));

			}else if (input.equals("inverse")) {
				long a = Long.valueOf(line[1]);
				int b = Integer.valueOf(line[2]);
				
				System.out.println(inverse(a, b));

			}else if (input.equals("isprime")) {
				
				int a = Integer.valueOf(line[1]);
				
				System.out.println(isPrime(a));

			}else if (input.equals("exp")) {
				int a = Integer.valueOf(line[1]);
				long b = Long.valueOf(line[2]);
				int c = Integer.valueOf(line[3]);
				
				System.out.println(modexp(a, b, c));

			}
			else if(input.equals("key")){
				int a = Integer.valueOf(line[1]);
				long b = Long.valueOf(line[2]);
				
				System.out.println(key(a, b));
			}
		}
	}

	static long mod(long a, long b) {
		return (((a % b) + b) % b);
	}

	static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, mod(a, b));
	}

	static int modexp(long a, long b, long n) {
		long x = 1;
		long y = a;
		while (b > 0) {
			
			if (b % 2 == 1) {
				x = (x * y) % n;
			}
			y = (y * y) % n;
			b /= 2;
		}
		return (int) (x % n);
	}

	static String inverse(long a, long b) {
		long i = b, v = 0, d = 1;
		if (gcd(a, b) != 1) {
			return "none";
		}
		while (a > 0) {
			long t = i / a, x = a;
			a = i % x;
			i = x;
			x = d;
			d = v - t * x;
			v = x;
		}
		v %= b;
		if (v < 0) {
			v = (v + b) % b;
		}
		return v + "";
	}

	static String isPrime(long a) {
		long a1 = 2;
		long a2 = 3;
		long a3 = 5;

		if (modexp(a1, a - 1, a) == 1 && modexp(a2, a - 1, a) == 1 && modexp(a3, a - 1, a) == 1) {
			return "yes";
		} else {
			return "no";
		}
	}
	
	static String key(long a, long b)
	{
		return a * b + " " + expIn(a,b) + " " + expOut(a,b);
	}
	
	static long expIn(long a, long b)
	{
		long result = (a-1)*(b-1);
		for(long i=2; i<result; i++)
		{
			if(gcd(i,result)==1)
			{
				return i;
			}
		}
		return -1;
	}
	
	static long expOut(long a, long b)
	{
		long e = expIn(a, b);
		long phi = (a-1)*(b-1);
		
		long d = Long.parseLong(inverse(e,phi));
		
		return d;
	}

}
