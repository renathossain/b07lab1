import java.io.*;

public class Driver {
	public static void main(String [] args) throws IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int [] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,-9};
		int [] e2 = {1,4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		Polynomial q = new Polynomial(new File("sample.txt"));
		System.out.println(q.evaluate(3));
		q.saveToFile("sample2.txt");
		
		Polynomial q2 = new Polynomial(new File("sample2.txt"));
		System.out.println(q2.evaluate(3));
		
		double [] c3 = {0, 1, 2, 3, 4, 5};
		int [] e3 = {0, 1, 2, 3, 4, 5};
		Polynomial aa = new Polynomial(c3, e3);
		Polynomial bb = aa.multiply(aa);
		Polynomial cc = bb.multiply(aa);
		System.out.println(cc.evaluate(2));
		cc.saveToFile("sample3.txt");
	}
}