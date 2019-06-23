public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;
	public double xdif;
	public double ydif;
	public double r;

	public Planet(double xxPos, double yyPos, double xxVel,
              double yyVel, double mass, String imgFileName) {
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		xdif = p.xxPos - this.xxPos;
		ydif = p.yyPos - this.yyPos;
		r = Math.sqrt(xdif * xdif + ydif * ydif);
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double force = G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double xForce;
		xForce = calcForceExertedBy(p) * xdif /calcDistance(p);
		return xForce;
	}

	public double calcForceExertedByY(Planet p) {
		double yForce;
		yForce = calcForceExertedBy(p) * ydif /calcDistance(p);
		return yForce;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double nfX = 0.0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				nfX += this.calcForceExertedByX(p);
			}
		}
		return nfX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double nfY = 0.0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				nfY += this.calcForceExertedByY(p);
			}
		}
		return nfY;
	}

	public void update(double dt, double fX, double fY) {
		double accx = 0.0;
		double accy = 0.0;
		accx = fX / this.mass;
		accy = fY / this.mass;
		xxVel += dt * accx;
		yyVel += dt * accy;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
		return;
	}

	public void draw() {
		String imageToDraw = "/Users/jackie_liu/Docs/Summer_2019/cs61b/skeleton-sp18/proj0/images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,imageToDraw);
		return;
	}
}