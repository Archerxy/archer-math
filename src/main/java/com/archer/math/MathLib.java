package com.archer.math;

public class MathLib {
	
	static {
		Library.loadMathLib();
	}

	/**
	 * return a > b ? 1; a == b ? 0; a < b ? -1;
	 * */
	public static int cmp(byte[] a, byte[] b) {
		if(a.length > b.length) {
			return 1;
		} else if(a.length < b.length) {
			return -1;
		} else {
			for(int i = 0; i < a.length; i++) {
				int ai = ((int)a[i]) + 256;
				int bi = ((int)b[i]) + 256;
				if(ai > bi) {
					return 1;
				} else if(ai < bi) {
					return -1;
				}
			}
			return 0;
		}
	}
	
	/**
	 * return a + b
	 * */
	public static byte[] add(byte[] a, int b) {
		byte[] r = new byte[a.length];
		int ai = a[a.length-1] < 0 ? a[a.length-1] + 256 : a[a.length-1];
		int sum = ai  + b;
		int re = sum % 256, add = sum /256;
		r[r.length - 1] = (byte) re;
		for(int i = r.length - 2; i >= 0; i--) {
			ai = a[i] < 0 ? a[i] + 256 : a[i];
			sum = ai + add;
			
			re = sum % 256;
			add = sum / 256;
			
			r[i] = (byte) re;
		}
		if(add > 0) {
			byte[] nr = new byte[r.length + 1];
			System.arraycopy(r, 0, nr, 1, r.length);
			nr[0] = 1;
			return nr;
		} else {
			return r;
		}
	}
	

	/**
	 * return a + b
	 * */
	public static byte[] add(byte[] a, byte[] b) {
		int add = 0, maxl, minl;
		byte[] max, min;
		if(a.length > b.length) {
			min = b;
			max = a;
		} else {
			min = a;
			max = b;
		}
		maxl = max.length;
		minl = min.length;
		byte[] r = new byte[maxl];
		int j = maxl - 1;
		for(int i = minl - 1; i >= 0; i--) {
			int ai = max[j] < 0 ? max[j] + 256 : max[j];
			int bi = min[i] < 0 ? min[i] + 256 : min[i];
			int sum = ai + bi + add;
			if(sum >= 256) {
				add = 1;
				sum -= 256;
			} else {
				add = 0;
			}
			r[j--] = (byte) sum;
		}
		for(; j >= 0; j--) {
			int ai = max[j] < 0 ? max[j] + 256 : max[j];
			int sum = ai + add;
			if(sum >= 256) {
				add = 1;
				sum -= 256;
			} else {
				add = 0;
			}
			r[j] = (byte) sum;
		}
		if(add >= 256) {
			byte[] pre = new byte[r.length];
			int i = r.length - 1, addTmp = add;
			while(addTmp >= 256) {
				pre[i--] = (byte) (addTmp % 256);
				addTmp /= 256;
			}
			if(addTmp > 0) {
				pre[i--] = (byte) addTmp;
			}
			byte[] nr = new byte[pre.length-i-1+r.length];
			System.arraycopy(pre, i+1, nr, 0, pre.length-i-1);
			System.arraycopy(r, 0, nr, pre.length-i-1, r.length);
			return nr;
		} else if(add > 0) {
			byte[] nr = new byte[r.length + 1];
			System.arraycopy(r, 0, nr, 1, r.length);
			nr[0] = 1;
			return nr;
		} else {
			return r;
		}
	}
	
	public static byte[] mul(byte[] a, int b) {
		int add = 0;
		byte[] r = new byte[a.length];
		for(int i = a.length - 1; i >= 0; i--) {
			int ai = a[i] < 0 ? a[i] + 256: a[i];
			int mul = ai * b + add;

			add = mul / 256;
			mul = mul % 256;
			
			r[i] = (byte) mul;
		}
		if(add >= 256) {
			byte[] pre = new byte[r.length];
			int i = r.length - 1;
			while(add >= 256) {
				pre[i--] = (byte) (add % 256);
				add /= 256;
			}
			if(add > 0) {
				pre[i--] = (byte) add;
			}
			byte[] nr = new byte[pre.length-i-1+r.length];
			System.arraycopy(pre, i+1, nr, 0, pre.length-i-1);
			System.arraycopy(r, 0, nr, pre.length-i-1, r.length);
			return nr;
		} else if(add > 0) {
			byte[] nr = new byte[r.length + 1];
			System.arraycopy(r, 0, nr, 1, r.length);
			nr[0] = (byte) add;
			return nr;
		} else {
			return r;
		}
	}

	/**
	 * return a - b, a > b is required.
	 * */
	public static byte[] sub(byte[] a, byte[] b) {
		int borrow = 0, j = a.length - 1;
		byte[] r = new byte[a.length];
		for(int i = b.length - 1; i >= 0; i--) {
			int ai = a[j] < 0 ? a[j] + 256 : a[j];
			int bi = b[i] < 0 ? b[i] + 256 : b[i];
			int sub = ai - bi - borrow;
			if(sub < 0) {
				sub += 256;
				borrow = 1;
			} else {
				borrow = 0;
			}
			r[j] = (byte) sub;
			j--;
		}
		if(borrow > 0 && j >= 0) {
			int aj = a[j] < 0 ? a[j] + 256 : a[j];
			r[j] = (byte) (aj - borrow);
			j--;
		}
		for(;j >= 0; j--) {
			r[j] = a[j];
		}
		return r;
	}

	/**
	 * return a - b
	 * */
	public static native byte[] subm(byte[] a, byte[] b, byte[] p);


	/**
	 * return a * b
	 * */
	public static native byte[] mul(byte[] a, byte[] b);


	/**
	 * return a / b
	 * */
	public static native byte[] divui(byte[] a, int b);
	
	/**
	 * return a / b
	 * */
	public static native byte[] div(byte[] a, byte[] b);

	/**
	 * return a mod b
	 * */
	public static native byte[] mod(byte[] a, byte[] b);
	

	/**
	 * return a ^ b
	 * */
	public static native byte[] pow(byte[] a, int b);
	

	/**
	 * return √a
	 * */
	public static native byte[] sqrt(byte[] a);
	

	/**
	 * return r = √a if r * r = a else null; 
	 * */
	public static native byte[] sqrtif(byte[] a);
	

	/**
	 * return a ^ b mod m
	 * */
	public static native byte[] powmui(byte[] a, int b, byte[] m);
	
	/**
	 * return (a ^ b) mod m
	 * */
	public static native byte[] powm(byte[] a, byte[] b, byte[] m);
	

	/**
	 * return inverse(a) mod m
	 * */
	public static native byte[] inv(byte[] a, byte[] m);
	

	/**
	 * return (a * b) mod m
	 * */
	public static native byte[] mulm(byte[] a, byte[] b, byte[] m);
	

	/**
	 * return (a / b) mod m
	 * */
	public static native byte[] mulInvm(byte[] a, byte[] b, byte[] m);
}
