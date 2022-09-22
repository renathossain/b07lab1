import java.math.*;

public class Polynomial {
	private double coeffs[];
	
	public Polynomial() {
		this.coeffs = new double[1];
	}
	
	public Polynomial(double[] user_coeffs) {
		this.coeffs = user_coeffs;
	}

	public Polynomial add(Polynomial addend) {
		int this_len = this.coeffs.length;
		int add_len = addend.coeffs.length;
		int max_len = Math.max(this_len, add_len);
		double result[] = new double[max_len];
		
		for(int i = 0; i < max_len; i++) {
			if(i < this_len) {
				result[i] += this.coeffs[i];
			}
			if(i < add_len) {
				result[i] += addend.coeffs[i];
			}
		}
		
		return new Polynomial(result);
	}
	
	public double evaluate(double x) {
		double sum = 0;
		
		for(int i = 0; i < this.coeffs.length; i++) {
			sum += this.coeffs[i] * Math.pow(x, i);
		}
		
		return sum;
	}
	
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}