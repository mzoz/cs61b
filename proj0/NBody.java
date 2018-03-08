public class NBody {
	public static double readRadius(String s) {
		In in = new In(s);
		in.readInt();
		return in.readDouble();
	}

	public static String imgDir = "./images/";
	public static Planet[] readPlanets(String s) {
		In in = new In(s);
		int n = in.readInt();
		in.readDouble();

		Planet[] ps = new Planet[n];
		for (int i = 0; i < n; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = imgDir + in.readString();
			ps[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return ps;
	}

	public static String bgImg = "images/starfield.jpg";
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double r = readRadius(filename);
		Planet[] ps = readPlanets(filename);

		/*StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0,0, bgImg, 2*r, 2*r);
		for (Planet p : ps) {
			p.draw();
		}*/

		StdDraw.enableDoubleBuffering();

		double[] xForce = new double[ps.length];
		double[] yForce = new double[ps.length];

		for (double t=0; t<T; t+=dt) {
			StdDraw.setScale(-r, r);
			StdDraw.clear();
			StdDraw.picture(0,0, bgImg, 2*r, 2*r);
			for (Planet p : ps) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			for (int i=0; i<ps.length; i++) {
				xForce[i] = ps[i].calcNetForceExertedByX(ps);
				yForce[i] = ps[i].calcNetForceExertedByY(ps);
			}

			for (int i=0; i<ps.length; i++) {
				ps[i].update(dt, xForce[i], yForce[i]);
			}
		}
	}
}