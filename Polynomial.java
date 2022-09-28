import java.math.*;
import java.util.*;

public class Polynomial {
	private double coeffs[];
	private int exponents[];
	
	public Polynomial() {
		this.coeffs = null;
		this.exponents = null;
	}
	
	public Polynomial(File file) {
		return;
	}
	
	public Polynomial(double[] coeffs, int[] exponents) {
		this.coeffs = coeffs;
		this.exponents = exponents;
	}
	
	public Polynomial add(Polynomial addend) {
		int this_len = 0;
		int add_len = 0;
		
		if(this.coeffs != null) this_len = this.coeffs.length;
		if(addend.coeffs != null) add_len = addend.coeffs.length;
		
		Map<Integer, Double> dict = new HashMap<Integer, Double>();
		
		for(int i = 0; i < this_len; i++) {
			dict.put(this.exponents[i], this.coeffs[i]);
		}
		
		for(int i = 0; i < add_len; i++) {
			if(dict.containsKey(addend.exponents[i])) {
				double value = dict.get(addend.exponents[i]);
				value += addend.coeffs[i];
				dict.remove(addend.exponents[i]);
				dict.put(addend.exponents[i], value);
			} else {
				dict.put(addend.exponents[i], addend.coeffs[i]);
			}
		}
		
		int arr_size = dict.size();
		double rcoeffs[] = new double[arr_size];
		int rexponents[] = new int[arr_size];
		
		int i = 0;
		for(int key : dict.keySet()) {
			rexponents[i] = key;
			rcoeffs[i] = dict.get(key);
			i++;
		}
		
		return new Polynomial(rcoeffs, rexponents);
	}
	
	public double evaluate(double x) {
		if(coeffs == null) return 0;
		
		double sum = 0;
		for(int i = 0; i < this.coeffs.length; i++) {
			sum += coeffs[i] * Math.pow(x, exponents[i]);
		}
		
		return sum;
	}
	
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial multend) { 
		int this_len = 0;
		int mult_len = 0;
		
		if(this.coeffs != null) this_len = this.coeffs.length;
		if(multend.coeffs != null) mult_len = multend.coeffs.length;
		
		Map<Integer, Double> dict = new HashMap<Integer, Double>();
		
		for(int i = 0; i < this_len; i++) {
			for(int j = 0; j < mult_len; j++) {
				int rexp = this.exponents[i] + multend.exponents[j];
				double rcoeff = this.coeffs[i] * multend.coeffs[j];
				
				if(dict.containsKey(rexp)) {
					double value = dict.get(rexp);
					value += rcoeff;
					dict.remove(rexp);
					dict.put(rexp, value);
				} else {
					dict.put(rexp, rcoeff);
				}
			}
		}
		
		int arr_size = dict.size();
		double rcoeffs[] = new double[arr_size];
		int rexponents[] = new int[arr_size];
		
		int i = 0;
		for(int key : dict.keySet()) {
			rexponents[i] = key;
			rcoeffs[i] = dict.get(key);
			i++;
		}
		
		return new Polynomial(rcoeffs, rexponents);
	}
}



