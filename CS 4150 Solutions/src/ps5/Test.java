//package ps5;
//
//import java.util.*;
//
//public class Test {
//
//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//
//		Scanner s = new Scanner(System.in);
//		int count, friends, first;
//		List<String> studentList = new ArrayList<String>();
//		List<String> tempList = new ArrayList<String>();
//
//		Graph graph = new Graph();
//		count = Integer.valueOf(s.nextLine());
//		List<String> list = new ArrayList<String>();
//
//		
//
//		for (int i = 0; i < count; i++) {
//			list.add(s.nextLine());
//		}
//
//		friends = Integer.valueOf(s.nextLine());
//		for (int i = 0; i < friends; i++) {
//			String[] temp = s.nextLine().split(" ");
//
//			String temp0 = temp[0], temp1 = temp[1];
//			graph.addFriend(temp0, temp1);
//		}
//
//		first = Integer.valueOf(s.nextLine());
//		for (int i = 0; i < first; i++) {
//
//			String starter = s.nextLine();
//			tempList.add(starter);
//		}
//
//		for (String starter : tempList) {
//			Queue<String> queue = new PriorityQueue<String>();
//			List<String> result = new ArrayList<String>();
//			Set<String> studentSet = new HashSet<String>(list);
//
//			queue.add(starter);
//
//			while (queue.size() != 0) {
//				studentList.clear();
//
//				while (queue.size() != 0) {
//					String temp = queue.poll();
//					result.add(temp);
//					studentSet.remove(temp);
//					studentList.add(temp);
//				}
//
//				for (String name : studentList) {
//					Set<String> set = graph.getFriend(name);
//
//					if (set.size() != 0) {
//
//						for (String s1 : set) {
//
//							if (studentSet.contains(s1) & (!queue.contains(s1)))
//								queue.add(s1);
//						}
//
//					}
//				}
//			}
//
//			queue.clear();
//
//			for (String name : studentSet) {
//
//				queue.add(name);
//			}
//
//			while (queue.size() != 0) {
//				result.add(queue.poll());
//			}
//
//			for (int j = 0; j < result.size(); j++) {
//				
//				if (j != result.size() - 1) {
//					System.out.print(result.get(j) + " ");
//				} 
//				else
//				{
//					System.out.print(result.get(j));
//				}
//					
//			}
//
//			System.out.print('\n');
//		}
//
//	}
//
//}
//
///**
// * I use inner class Graph in order to create the relationship of rumor tells.
// * This includes some setters and getters to make it simple.
// * 
// * @author Yingjie Lian
// *
// */
//class Graph {
//	public Map<String, Set<String>> relations;
//	public Map<String, Set<String>> names;
//
//	/**
//	 * Constructor
//	 */
//	Graph() {
//		relations = new HashMap<String, Set<String>>();
//		names = new HashMap<String, Set<String>>();
//	}
//
//	public boolean hasRelations(String name) {
//		Set<String> temp;
//		
//		if (relations.containsKey(name)) {
//			temp = relations.get(name);
//			
//			if (temp.size() == 0)
//				return false;
//			else
//				return true;
//		}
//		return false;
//	}
//
//	public boolean hasFriend(String name) {
//		Set<String> temp;
//		
//		if (names.containsKey(name)) {
//			temp = names.get(name);
//			
//			if (temp.size() == 0)
//				return false;
//			else
//				return true;
//		}
//		return false;
//	}
//
//	public void addFriend(String name1, String name2) {
//		Set<String> temp;
//		
//		if (relations.containsKey(name1)) {
//			temp = relations.get(name1);
//			
//			if (!temp.contains(name2)) {
//				temp.add(name2);
//				relations.put(name1, temp);
//				
//				if (!names.containsKey(name2)) {
//					temp = new HashSet<String>();
//					temp.add(name1);
//					names.put(name2, temp);
//				} 
//				else {
//					temp = names.get(name2);
//					temp.add(name1);
//					names.put(name2, temp);
//				}
//			}
//		} 
//		else {
//			temp = new HashSet<String>();
//			temp.add(name2);
//			relations.put(name1, temp);
//			
//			if (!names.containsKey(name2)) {
//				temp = new HashSet<String>();
//				temp.add(name1);
//				names.put(name2, temp);
//			} 
//			else {
//				temp = names.get(name2);
//				temp.add(name1);
//				names.put(name2, temp);
//			}
//		}
//	}
//
//	public Set<String> getRelation(String s) {
//		if (relations.containsKey(s)) {
//			return relations.get(s);
//		}
//		return new HashSet<String>();
//	}
//
//	public Set<String> getName(String s) {
//		if (names.containsKey(s)) {
//			return names.get(s);
//		}
//		return new HashSet<String>();
//	}
//
//	public Set<String> getFriend(String s) {
//		Set<String> set = new HashSet<String>();
//		set.addAll(getName(s));
//		set.addAll(getRelation(s));
//		return set;
//	}
//}