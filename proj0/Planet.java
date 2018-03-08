public class Planet {
	public static final double G = 6.67e-11;
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	public void draw() {
		StdDraw.picture(xxPos, yyPos,imgFileName);
	}

	public Planet(double xP, double yP, double xV,
    			  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	double calcDistance(Planet p) {
		double xxDis = xxPos - p.xxPos;
		double yyDis = yyPos - p.yyPos;
		return Math.sqrt(xxDis*xxDis + yyDis*yyDis);
	}

	double calcForceExertedBy(Planet p) {
		return G*mass*p.mass/Math.pow(this.calcDistance(p), 2);
	}

	double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		return this.calcForceExertedBy(p) 
			 * dx 
			 / this.calcDistance(p);
	}

	double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		return this.calcForceExertedBy(p) 
			 * dy 
			 / this.calcDistance(p);
	}

	double calcNetForceExertedByX(Planet[] pArr) {
		double fx = 0;
		for (Planet p : pArr) {
			if (this.equals(p)) {
				continue;
			}
			fx += this.calcForceExertedByX(p);
		}
		return fx;
	}

	double calcNetForceExertedByY(Planet[] pArr) {
		double fy = 0;
		for (Planet p : pArr) {
			if (this.equals(p)) {
				continue;
			}
			fy += this.calcForceExertedByY(p);
		}
		return fy;
	}

	void update(double dt, double xf, double yf) {
		double ax = xf / mass;
		double ay = yf / mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}
}