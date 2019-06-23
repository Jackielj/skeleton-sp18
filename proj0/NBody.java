public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt();
		Double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int numOfPlanets = in.readInt();
		Planet[] planets = new Planet[numOfPlanets];
		double radius = in.readDouble(); 

		int i = 0;

		while(!in.isEmpty()) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i++] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			if (i == numOfPlanets) {
				break;
			}
		}
		return planets;
	}

	public static void main(String[] args) {
		double T, dt;
		String filename;

		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];

		double radius = readRadius(filename);
		StdDraw.setScale(-radius,radius);
		Planet[] planets = readPlanets(filename);

		StdDraw.clear();
		StdDraw.picture(0,0,"/Users/jackie_liu/Docs/Summer_2019/cs61b/skeleton-sp18/proj0/images/starfield.jpg");
		for(Planet p : planets) {
			p.draw();
		}
		StdDraw.show();

		StdDraw.enableDoubleBuffering();
		int time = 0;
		while (time < T) {
			int index = 0;
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (Planet p : planets) {
				xForces[index] = p.calcNetForceExertedByX(planets);
				yForces[index] = p.calcNetForceExertedByY(planets);
				index++;
			}
			for (int i = 0 ; i < planets.length; i++) {
				planets[i].update(dt,xForces[i],yForces[i]);
			}

			//StdDraw.clear();
			StdDraw.picture(0,0,"/Users/jackie_liu/Docs/Summer_2019/cs61b/skeleton-sp18/proj0/images/starfield.jpg");

			for (Planet p : planets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
					planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}

	}

}