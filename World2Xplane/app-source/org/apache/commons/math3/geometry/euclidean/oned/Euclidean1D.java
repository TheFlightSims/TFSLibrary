/*    */ package org.apache.commons.math3.geometry.euclidean.oned;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ 
/*    */ public class Euclidean1D implements Serializable, Space {
/*    */   private static final long serialVersionUID = -1178039568877797126L;
/*    */   
/*    */   private Euclidean1D() {}
/*    */   
/*    */   public static Euclidean1D getInstance() {
/* 45 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 50 */     return 1;
/*    */   }
/*    */   
/*    */   public Space getSubSpace() throws MathUnsupportedOperationException {
/* 62 */     throw new MathUnsupportedOperationException(LocalizedFormats.NOT_SUPPORTED_IN_DIMENSION_N, new Object[] { Integer.valueOf(1) });
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 71 */     private static final Euclidean1D INSTANCE = new Euclidean1D();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 80 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\Euclidean1D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */