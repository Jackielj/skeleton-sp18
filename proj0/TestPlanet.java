public class TestPlanet{
	public static void main(String[] args) {
		Planet p1 = new Planet(0, 0, 1, 1, 5, "planet1");
		Planet p2 = new Planet(3, 3, 1, 1, 5, "planet2");

		System.out.println(p1.calcForceExertedByX(p2));
	}

}