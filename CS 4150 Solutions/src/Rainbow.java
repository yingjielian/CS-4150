package ps9;
import java.util.*;
public class Rainbow {
	
	public static class Node {
		private int penalty = 0, sum = 0;

		Node(int cost) {
			this.sum = Integer.MAX_VALUE;
			this.penalty = cost;
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ArrayList<Integer> lineList = new ArrayList<Integer>();
		ArrayList<Node> disList = new ArrayList<Node>();
		
		Scanner s = new Scanner(System.in);
		
		s.nextLine();
		String nextLine = "";
		while (s.hasNextLine()) {

			nextLine = s.nextLine();
			if (nextLine.length() == 0 || nextLine == null)
				break;
			lineList.add(Integer.valueOf(nextLine));
		}
		
		int lineSize = lineList.size();
		for (int i = 0; i < lineSize; i++) {
			disList.add(new Node(lineList.get(i)));
		}

		disList.get(0).sum = 0;
		
		int disSize = disList.size();
		for (int i = 0; i < disSize; i++) {
			newList(disList,i);
		}
		
		System.out.println(disList.get(disList.size() - 1).sum);
	}

	static void newList(ArrayList<Node> l, int a) {
		int size = l.size();
		int current = a + 1;
		while (current < size && (l.get(current).penalty - l.get(a).penalty) < 800) {
			int cost = l.get(current).penalty - l.get(a).penalty;
			int temp = (int) ((int) (l.get(a).sum)
					+ Math.pow(Math.abs(400 - cost), 2));
			if (temp < l.get(current).sum) {
				l.get(current).sum = temp;
			}
			current++;
		}
	}

}
