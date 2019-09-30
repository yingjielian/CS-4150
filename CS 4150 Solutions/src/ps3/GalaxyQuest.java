package ps3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GalaxyQuest {

	/*
	 * Constructor for star that has x, y coordinates
	 */
	static class Star{
		public double x;
		public double y;
		public double galaxy;

		public Star(double d, double e) {
			x = d;
			y = e;
			galaxy = 0;
		}
	}

	/*
	 * Find the position of the star
	 */
	static Star findStar(Star[] stars, double d) {
		int len = stars.length;

		if (len == 0) {
			return null;
		} else if (len == 1) {
			return stars[0];
		} else {
			Star[] prime = newPrime(stars, d);
			Star x = findStar(prime, d);
			if (x != null) {
				double count = countStars(stars, x, d);
				if (count > (len / 2)) {
					return x;
				} else {
					return null;
				}

			} else {
				if (len % 2 != 0) {
					double count = countStars(stars, stars[len - 1], d);
					if (count > (len / 2)) {
						return stars[len - 1];
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
		}
	}

	/*
	 * Count how many times the stars appear
	 */
	static double countTheStars(Star majStar, Star[] stars, double d) {
		double starCount = 0;
		for (Star s : stars) {
			if (countDis(s, majStar) < d) {
				starCount++;
			}
		}

		return starCount;
	}

	static Star[] newPrime(Star[] stars, double d) {
		List<Star> prime = new ArrayList<Star>();
		for (int i = 0; i < stars.length - 1; i += 2) {
			if (countDis(stars[i], stars[i + 1]) < d) {
				prime.add(stars[i]);
			}
		}

		return prime.toArray(new Star[0]);
	}

	/*
	 * Count how many stars we have
	 */
	static double countStars(Star[] stars, Star cand, double d) {
		double count = 0;
		for (Star s : stars) {
			if (countDis(s, cand) < d) {
				count++;
			}
		}
		return count;
	}

	/*
	 * Find the major star from target galaxy
	 */
	static double findMajStar(List<Double> galaxies) {
		if (galaxies.size() == 0) {
			return 0;
		} else if (galaxies.size() == 1) {
			return galaxies.get(0);
		} else {
			List<Double> aPrime = aPrime(galaxies);
			double x = findMajStar(aPrime);
			if (x == 0) {
				double count = countCand(galaxies, x);
				if (count > (galaxies.size() / 2)) {
					return x;
				} else {
					return 0;
				}

			} else {
				if (galaxies.size() % 2 != 0) {
					double count = countCand(galaxies, galaxies.get(galaxies.size() - 1));
					if (count > (galaxies.size() / 2)) {
						return galaxies.get(galaxies.size() - 1);
					} else {
						return 0;
					}
				} else {
					return 0;
				}

			}
		}
	}

	static double countCand(List<Double> nums, double cand) {
		double count = 0;
		for (double l : nums) {
			if (l == cand) {
				count++;
			}
		}
		return count;
	}


	static List<Double> aPrime(List<Double> galaxies) {
		List<Double> prime = new ArrayList<Double>();
		for (int i = 0; i < galaxies.size() - 1; i += 2) {
			if (galaxies.get(i) == galaxies.get(i + 1)) {
				prime.add(galaxies.get(i));
			}
		}
		return prime;
	}

	/*
	 * Find the target Galaxy
	 */
	static List<Double> findGalaxies(Star[] stars, long d) {
		List<Star> uniqueStar = new ArrayList<Star>();
		List<Double> galaxies = new ArrayList<Double>(stars.length);

		double index = 1;
		for (Star s : stars) {
			if (uniqueStar.isEmpty()) {
				s.galaxy = index;
				uniqueStar.add(s);
				galaxies.add(index);
			} else {
				int i = 0;
				boolean found = false;
				while (i < uniqueStar.size() && !found) {
					if (countDis(s, uniqueStar.get(i)) <= d) {
						galaxies.add(uniqueStar.get(i).galaxy);
						found = true;
					}
					i++;
				}

				if (!found) {
					index++;
					s.galaxy = index;
					galaxies.add(index);
					uniqueStar.add(s);
				}
			}
		}
		return galaxies;
	}

	/*
	 * Count the distance to target galaxy
	 */
	static double countDis(Star s1, Star s2) {
		return (s2.x - s1.x) * (s2.x - s1.x) + (s2.y - s1.y) * (s2.y - s1.y);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);

		String firstLine = s.nextLine();
		String[] arr = firstLine.split(" ");

		double d = Double.parseDouble(arr[0]);
		double disSquar = d * d;
		int quantity = Integer.parseInt(arr[1]);

		Star[] stars = new Star[quantity];

		for (int i = 0; i < quantity; i++) {
			String star = s.nextLine();
			String[] starArray = star.split(" ");
			Star newStar = new Star(Double.parseDouble(starArray[0]), Double.parseDouble(starArray[1]));
			stars[i] = newStar;
		}

		Star bigStar = findStar(stars, disSquar);

		if (bigStar != null) {
			System.out.println((int) countTheStars(bigStar, stars, disSquar));
		} else {
			System.out.println("NO");
		}
	}
}
