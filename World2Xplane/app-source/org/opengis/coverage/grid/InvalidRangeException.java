/*    */ package org.opengis.coverage.grid;
/*    */ 
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ 
/*    */ @UML(identifier = "GC_InvalidRange", specification = Specification.OGC_01004)
/*    */ public class InvalidRangeException extends IllegalArgumentException {
/*    */   private static final long serialVersionUID = 3165512862939920847L;
/*    */   
/*    */   public InvalidRangeException() {}
/*    */   
/*    */   public InvalidRangeException(String message) {
/* 48 */     super(message);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\InvalidRangeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */