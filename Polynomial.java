import java.math.*;
import java.util.*;
import java.io.*;

public class Polynomial {
	private double coeffs[];
	private int exponents[];
	
	public Polynomial() {
		this.coeffs = null;
		this.exponents = null;
	}
	
	public Polynomial(File file) throws IOException {
		BufferedReader b = new BufferedReader(new FileReader(file));
		String text = b.readLine();
		String[] terms = text.split("((?=\\+))|((?=\\-))");
		
		this.coeffs = new double[terms.length];
		this.exponents = new int[terms.length];
		
		int i = 0;
	    for(String term : terms) {
	    	String[] s = term.split("x");
	    	if(Double.parseDouble(s[0]) != 0) {
	    		if(s.length == 1) {
		    		this.coeffs[i] = Double.parseDouble(s[0]);
		    		this.exponents[i] = 0;
		    		if(term.contains("x")) this.exponents[i] = 1;
		    	}
		    	if(s.length == 2) {
		    		this.coeffs[i] = Double.parseDouble(s[0]);
		    		this.exponents[i] = Integer.parseInt(s[1]);
		    	}
		    	i++;
	    	}
	    }
	}
	
	public Polynomial(double[] coeffs, int[] exponents) {
		this.coeffs = coeffs;
		this.exponents = exponents;
	}
	
	public void saveToFile(String file) throws IOException {
		String result = "";
		for(int i = 0; i < this.coeffs.length; i++) {
			if(this.coeffs[i] >= 0) result = result.concat("+");
			result = result.concat(Double.toString(this.coeffs[i]));
			result = result.concat("x");
			result = result.concat(Integer.toString(this.exponents[i]));
		}
		FileWriter p = new FileWriter(new File(file));
		p.write(result);
		p.close();
	}
	
	public Polynomial add(Polynomial addend) {
		int this_len = 0;
		int add_len = 0;
		
		if(this.coeffs != null) this_len = this.coeffs.length;
		if(addend.coeffs != null) add_len = addend.coeffs.length;
		
		Map<Integer, Double> dict = new HashMap<Integer, Double>();
		
		for(int i = 0; i < this_len; i++) {
			if(this.coeffs[i] != 0) {
				dict.put(this.exponents[i], this.coeffs[i]);
			}
		}
		
		for(int i = 0; i < add_len; i++) {
			if(dict.containsKey(addend.exponents[i])) {
				double value = dict.get(addend.exponents[i]);
				value += addend.coeffs[i];
				dict.remove(addend.exponents[i]);
				if(value != 0) {
					dict.put(addend.exponents[i], value);
				}
			} else {
				if(addend.coeffs[i] != 0) {
					dict.put(addend.exponents[i], addend.coeffs[i]);
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
					if(value != 0) dict.put(rexp, value);
				} else {
					if(rcoeff != 0) dict.put(rexp, rcoeff);
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



