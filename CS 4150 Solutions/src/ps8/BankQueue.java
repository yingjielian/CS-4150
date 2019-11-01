package ps8;

import java.util.*;

public class BankQueue {

	static class People implements Comparator<People> {
		int m = 0;
		int t = 0;

		People(int money, int time) {
			this.m = money;
			this.t = time;
		}
		
		People() {
		}

		@Override
		public int compare(People a, People b) {
			return b.m - a.m;
		}
	}
	
	static void queueCostumer(List<Integer> list, People c) {
		
		for(int t = c.t; t >= 0; t--)
		{
			if (list.get(t) == 0) {

				list.set(t, c.m);
				return;
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<Integer> sumList = new ArrayList<Integer>();
		Scanner s = new Scanner(System.in);
		String[] lines = s.nextLine().split(" ");
		
		List<People> costumerList = new ArrayList<People>();
		
		int count = 0, countMin = 0;
		count = Integer.valueOf(lines[0]);
		countMin = Integer.valueOf(lines[1]);

		int total = count + countMin;
		
		for(int i = 0; i < count; i++)
		{
			String[] nextLine = s.nextLine().split(" ");
			int money = Integer.valueOf(nextLine[0]);
			int min = Integer.valueOf(nextLine[1]);
			People c = new People(money, min);
			total += min;
			
			costumerList.add(c);
		}
		
		Collections.sort(costumerList, new People());

		for (int i = 0; i < total; i++) {
			sumList.add(0);
		}

		int sum = 0;
		int cosSize = costumerList.size();
		int sumSize = sumList.size();
		for (int i = 0; i < cosSize; i++)
		{
			queueCostumer(sumList, costumerList.get(i));
		}
		for (int i = 0; i < sumSize; i++)
		{
			sum += sumList.get(i);
		}		
		System.out.println(sum);
	}

}
