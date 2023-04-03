/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ public final class RotationOrder {
/*  38 */   public static final RotationOrder XYZ = new RotationOrder("XYZ", Vector3D.PLUS_I, Vector3D.PLUS_J, Vector3D.PLUS_K);
/*     */   
/*  45 */   public static final RotationOrder XZY = new RotationOrder("XZY", Vector3D.PLUS_I, Vector3D.PLUS_K, Vector3D.PLUS_J);
/*     */   
/*  52 */   public static final RotationOrder YXZ = new RotationOrder("YXZ", Vector3D.PLUS_J, Vector3D.PLUS_I, Vector3D.PLUS_K);
/*     */   
/*  59 */   public static final RotationOrder YZX = new RotationOrder("YZX", Vector3D.PLUS_J, Vector3D.PLUS_K, Vector3D.PLUS_I);
/*     */   
/*  66 */   public static final RotationOrder ZXY = new RotationOrder("ZXY", Vector3D.PLUS_K, Vector3D.PLUS_I, Vector3D.PLUS_J);
/*     */   
/*  73 */   public static final RotationOrder ZYX = new RotationOrder("ZYX", Vector3D.PLUS_K, Vector3D.PLUS_J, Vector3D.PLUS_I);
/*     */   
/*  80 */   public static final RotationOrder XYX = new RotationOrder("XYX", Vector3D.PLUS_I, Vector3D.PLUS_J, Vector3D.PLUS_I);
/*     */   
/*  87 */   public static final RotationOrder XZX = new RotationOrder("XZX", Vector3D.PLUS_I, Vector3D.PLUS_K, Vector3D.PLUS_I);
/*     */   
/*  94 */   public static final RotationOrder YXY = new RotationOrder("YXY", Vector3D.PLUS_J, Vector3D.PLUS_I, Vector3D.PLUS_J);
/*     */   
/* 101 */   public static final RotationOrder YZY = new RotationOrder("YZY", Vector3D.PLUS_J, Vector3D.PLUS_K, Vector3D.PLUS_J);
/*     */   
/* 108 */   public static final RotationOrder ZXZ = new RotationOrder("ZXZ", Vector3D.PLUS_K, Vector3D.PLUS_I, Vector3D.PLUS_K);
/*     */   
/* 115 */   public static final RotationOrder ZYZ = new RotationOrder("ZYZ", Vector3D.PLUS_K, Vector3D.PLUS_J, Vector3D.PLUS_K);
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final Vector3D a1;
/*     */   
/*     */   private final Vector3D a2;
/*     */   
/*     */   private final Vector3D a3;
/*     */   
/*     */   private RotationOrder(String name, Vector3D a1, Vector3D a2, Vector3D a3) {
/* 140 */     this.name = name;
/* 141 */     this.a1 = a1;
/* 142 */     this.a2 = a2;
/* 143 */     this.a3 = a3;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 151 */     return this.name;
/*     */   }
/*     */   
/*     */   public Vector3D getA1() {
/* 158 */     return this.a1;
/*     */   }
/*     */   
/*     */   public Vector3D getA2() {
/* 165 */     return this.a2;
/*     */   }
/*     */   
/*     */   public Vector3D getA3() {
/* 172 */     return this.a3;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\RotationOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */