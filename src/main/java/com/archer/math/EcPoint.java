package com.archer.math;

public class EcPoint {
	
	
	public byte[] x;
	
	public byte[] y;

	public EcPoint() {}

	public EcPoint(byte[] x, byte[] y) {
		this.x = x;
		this.y = y;
	}
	
	public byte[] getX() {
		return x;
	}

	public byte[] getY() {
		return y;
	}

	public void setX(byte[] x) {
		this.x = x;
	}

	public void setY(byte[] y) {
		this.y = y;
	}
	
	public byte[] getEncoded() {
		if(x == null || y == null) {
			return null;
		}
		byte[] rv = new byte[x.length + y.length + 1];
		rv[0] = 4;
		System.arraycopy(x, 0, rv, 1, x.length);
		System.arraycopy(y, 0, rv, 1 + x.length, y.length);
		return rv;
	}

	static {
		Library.loadMathLib();
		init();
	}
	
	public static final int CURVE_secp256k1 = 1;

	public static final int CURVE_sm2p256v1 = 2;
	

	protected static native void init();

	public static native EcPoint add(EcPoint point1, EcPoint point2, byte[] p);
	
	public static native EcPoint sub(EcPoint point1, EcPoint point2, byte[] p);
	
	public static native EcPoint mulCurveAdd(byte[] d, Curve curve);
	
	public static native EcPoint mul(byte[] d, int curveId);
	
	public static native EcPoint mulCurve(byte[] d, Curve curve);

	public static native EcPoint mulPoint(byte[] d, int curveId, EcPoint point);
	
	public static native EcPoint mulCurvePoint(byte[] d, Curve curve, EcPoint point);
}
