/*    */ package org.apache.commons.math3.geometry.euclidean.threed;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ 
/*    */ public class NotARotationMatrixException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = 5647178478658937642L;
/*    */   
/*    */   public NotARotationMatrixException(Localizable specifier, Object... parts) {
/* 45 */     super(specifier, parts);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\NotARotationMatrixException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */