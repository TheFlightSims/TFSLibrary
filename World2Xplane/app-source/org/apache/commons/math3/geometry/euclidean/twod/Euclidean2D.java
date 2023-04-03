/*    */ package org.apache.commons.math3.geometry.euclidean.twod;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*    */ 
/*    */ public class Euclidean2D implements Serializable, Space {
/*    */   private static final long serialVersionUID = 4793432849757649566L;
/*    */   
/*    */   private Euclidean2D() {}
/*    */   
/*    */   public static Euclidean2D getInstance() {
/* 44 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 49 */     return 2;
/*    */   }
/*    */   
/*    */   public Euclidean1D getSubSpace() {
/* 54 */     return Euclidean1D.getInstance();
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 63 */     private static final Euclidean2D INSTANCE = new Euclidean2D();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 72 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\Euclidean2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */