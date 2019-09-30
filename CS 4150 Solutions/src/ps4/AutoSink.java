package ps4;

import java.util.*;

class AutoSink {
	
	static void findHiway(Node node) {
		
		node.minValue = 0.0;
		Queue<Node> nodeQueue = new PriorityQueue<Node>();
		nodeQueue.add(node);
		
		while (nodeQueue.size() != 0) {
			Node n = nodeQueue.poll();
			
			for (SideNode s : n.sideNodes) {
				double cost = s.value;
				double dis = n.minValue + cost;
				Node n1 = s.node;
				
				if (dis < n1.minValue) {
					n1.minValue = dis;
					n1.previous = n;
					nodeQueue.remove(n1);
					nodeQueue.add(n1);
				}
			}
		}
	}

	static void reset(Map<String, Node> map) {
		for (Map.Entry<String, Node> entry : map.entrySet()) {
			map.get(entry.getKey()).minValue = Double.POSITIVE_INFINITY;
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int cities = 0;
		int highways = 0;
		int trips = 0;
		Scanner s = new Scanner(System.in);
		
		cities = Integer.parseInt(s.nextLine());
		
		Map<String, Integer> money = new HashMap<String, Integer>();
		Map<String, Node> nodes = new HashMap<String, Node>();
		
		for (int i = 0; i < cities; i++) {
			String[] lines = s.nextLine().split(" ");
			String first = lines[0].toString();
			nodes.put(first, new Node(first));
			
			int second = Integer.parseInt(lines[1]);
			money.put(first, second);
		}
		
		highways = Integer.parseInt(s.nextLine());
		
		for (int i = 0; i < highways; i++) {
			String[] newLine = s.nextLine().split(" ");
			String first = newLine[0].toString();
			String second = newLine[1].toString();
			nodes.get(first).sideNodes.add(new SideNode(nodes.get(second), money.get(second)));
		}
		
		trips = Integer.parseInt(s.nextLine());
		List<String> result = new ArrayList<String>();
		
		for (int i = 0; i < trips; i++) {
			
			if (s.hasNextLine()) {
				String[] nextLine = s.nextLine().split(" ");
				String first = nextLine[0].toString();
				String second = nextLine[1].toString();
				
				if (first.equals(second)) {
					result.add("" + 0);
					continue;
				}
				
				findHiway(nodes.get(first));
				
				if (nodes.get(second).minValue != Double.POSITIVE_INFINITY) {
					result.add("" + (int) nodes.get(second).minValue);
				} else {
					result.add("NO");
				}
				
				reset(nodes);
			}
		}
		
		for (String str : result) {
			System.out.println(str);
		}
		
		return;
	}
}

/**
 * Comparable constructor in order to compare the node's value.
 * 
 * @author Yingjie Lian
 *
 */
class Node implements Comparable<Node> {
	public String name;
	public LinkedList<SideNode> sideNodes = new LinkedList<SideNode>();
	public double minValue = Double.POSITIVE_INFINITY;
	public Node previous;

	public String toString() {
		return this.name;
	}

	public int compareTo(Node a) {
		return Double.compare(this.minValue, a.minValue);
	}

	public Node(String name) {
		this.name = name;
	}

}

/**
 * Construct our edge nodes.
 * @author Yingjie Lian
 *
 */
class SideNode {
	public Node node;
	public double value;

	public SideNode(Node node, double value) {
		this.node = node;
		this.value = value;
	}
}
