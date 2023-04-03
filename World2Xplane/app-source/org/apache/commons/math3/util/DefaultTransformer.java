/*    */ package org.apache.commons.math3.util;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.NullArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class DefaultTransformer implements NumberTransformer, Serializable {
/*    */   private static final long serialVersionUID = 4019938025047800455L;
/*    */   
/*    */   public double transform(Object o) throws NullArgumentException, MathIllegalArgumentException {
/* 50 */     if (o == null)
/* 51 */       throw new NullArgumentException(LocalizedFormats.OBJECT_TRANSFORMATION, new Object[0]); 
/* 54 */     if (o instanceof Number)
/* 55 */       return ((Number)o).doubleValue(); 
/*    */     try {
/* 59 */       return Double.valueOf(o.toString()).doubleValue();
/* 60 */     } catch (NumberFormatException e) {
/* 61 */       throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_TRANSFORM_TO_DOUBLE, new Object[] { o.toString() });
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 69 */     if (this == other)
/* 70 */       return true; 
/* 72 */     if (other == null)
/* 73 */       return false; 
/* 75 */     return other instanceof DefaultTransformer;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 82 */     return 401993047;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\DefaultTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */