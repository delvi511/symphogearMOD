package com.delvi511.symphogearmod.util;

public class Vec3 extends net.minecraft.util.Vec3 {
	public static Vec3 createVectorHelper(double x, double y, double z){
		return new Vec3(x, y, z);
	}
	
	private Vec3(double x, double y, double z){
		super(x, y, z);
	}
	
	private static Vec3 castFromSuper(net.minecraft.util.Vec3 vec){
		return new Vec3(vec.xCoord, vec.yCoord, vec.zCoord);
	}
	
	public Vec3 subtract(Vec3 vec){
		return castFromSuper(super.subtract(vec));
	}
	
	public Vec3 normalize(){
		return castFromSuper(super.normalize());
	}
	
	public Vec3 crossProduct(Vec3 vec){
		return castFromSuper(super.crossProduct(vec));
	}
	
	public Vec3 addVector(double x, double y, double z){
		return castFromSuper(super.addVector(x, y, z));
	}
	
	public Vec3 add(Vec3 vec){
		return castFromSuper(super.addVector(vec.xCoord, vec.yCoord, vec.zCoord));
	}
	
	public Vec3 multiply(double f){
		return createVectorHelper(this.xCoord * f, this.yCoord * f, this.zCoord * f);
	}
	
	public Vec3 getIntermediateWithXValue(Vec3 vec, double x){
		return castFromSuper(super.getIntermediateWithXValue(vec, x));
	}
	
	public Vec3 getIntermediateWithYValue(Vec3 vec, double y){
		return castFromSuper(super.getIntermediateWithYValue(vec, y));
	}
	
	public Vec3 getIntermediateWithZValue(Vec3 vec, double z){
		return castFromSuper(super.getIntermediateWithZValue(vec, z));
	}	
}
