/*    */ package org.apache.commons.math3.geometry.euclidean.threed;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
/*    */ 
/*    */ public class Euclidean3D implements Serializable, Space {
/*    */   private static final long serialVersionUID = 6249091865814886817L;
/*    */   
/*    */   private Euclidean3D() {}
/*    */   
/*    */   public static Euclidean3D getInstance() {
/* 44 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 49 */     return 3;
/*    */   }
/*    */   
/*    */   public Euclidean2D getSubSpace() {
/* 54 */     return Euclidean2D.getInstance();
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 63 */     private static final Euclidean3D INSTANCE = new Euclidean3D();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 72 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\Euclidean3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */