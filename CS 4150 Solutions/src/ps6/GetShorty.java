package ps6;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class GetShorty {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		List<Dungeon> dungeons = search();

		DecimalFormat df = new DecimalFormat("#0.0000");
		for (Dungeon d : dungeons) {
			System.out.println(df.format(d.resize()));
		}
	}

	static List<Dungeon> search() {
		int interNum, corNum, home, dest;

		float f;
		boolean inputDone = false;

		String line = "";
		String[] lines;

		List<Dungeon> dungList = new ArrayList<Dungeon>();
		Corridor cor;
		Intersection[] interArray;

		Scanner s = new Scanner(System.in);
		while (!inputDone) {

			line = s.nextLine();
			if (line.equals("0 0")) {
				break;
			} else {
				lines = line.split(" ");
				interNum = Integer.valueOf(lines[0]);
				corNum = Integer.valueOf(lines[1]);
			}
			interArray = new Intersection[interNum];
			interArray[0] = new Intersection(0, 1);

			for (int i = 1; i < interNum; i++) {
				interArray[i] = new Intersection(i, Float.NEGATIVE_INFINITY);
			}

			for (int i = 0; i < corNum; i++) {
				lines = s.nextLine().split(" ");
				home = Integer.valueOf(lines[0]);
				dest = Integer.valueOf(lines[1]);
				f = Float.valueOf(lines[2]);

				cor = new Corridor(interArray[home], interArray[dest], f);
				interArray[home].addCor(cor);
				interArray[dest].addCor(cor);
			}
			dungList.add(new Dungeon(interArray));
		}

		return dungList;
	}
}

class Dungeon {

	private Queue<Intersection> interQueue;
	private Intersection[] interArray;

	/**
	 * Contructor for making a Dungeon and put values and params in it.
	 * 
	 * @param arr
	 */
	Dungeon(Intersection[] arr) {
		interArray = arr;
	}

	float resize() {

		float size;
		Intersection inter;
		Intersection i = interArray[0];

		interQueue = new PriorityQueue<Intersection>();
		interQueue.add(i);

		while (interQueue.size() != 0) {

			i = interQueue.poll();

			for (Corridor u : i.getCor()) {
				inter = u.getInter(i);
				size = i.getSize() * u.getFac();

				if (size > inter.getSize()) {
					inter.setCurrentSize(size);
					interQueue.add(inter);
				}
			}
		}

		// Return the size of last intersection
		return interArray[interArray.length - 1].getSize();
	}
}

class Corridor {

	private Intersection from, to;
	private float factor;

	/**
	 * Contructor for making a Corridor and put values in it.
	 * 
	 * @param home
	 *            Where from
	 * @param dest
	 *            Where to go
	 * @param fact
	 *            index
	 */
	Corridor(Intersection home, Intersection dest, float fact) {
		from = home;
		to = dest;
		factor = fact;
	}

	float getFac() {
		return factor;
	}

	Intersection getInter(Intersection inter) {

		if (inter.getNum() == from.getNum())
			return to;
		else
			return from;

	}
}

class Intersection implements Comparable<Intersection> {

	private int num;
	private List<Corridor> cor;

	private float size;

	/**
	 * Contructor for making a Intersection and put values in it.
	 * 
	 * @param n
	 * @param iniSize
	 */
	Intersection(int n, float iniSize) {
		num = n;
		cor = new ArrayList<Corridor>();
		size = iniSize;
	}

	int getNum() {
		return num;
	}

	float getSize() {
		return size;
	}

	List<Corridor> getCor() {
		return cor;
	}

	void setCurrentSize(float currentSize) {
		this.size = currentSize;
	}

	void addCor(Corridor co) {
		cor.add(co);
	}

	public int compareTo(Intersection other) {

		if (size > other.getSize())
			return -1;
		else
			return 1;
	}
}
