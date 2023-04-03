/*    */ package org.opengis.coverage;
/*    */ 
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.geometry.DirectPosition;
/*    */ 
/*    */ @UML(identifier = "CV_PointOutsideCoverage", specification = Specification.OGC_01004)
/*    */ public class PointOutsideCoverageException extends CannotEvaluateException {
/*    */   private static final long serialVersionUID = -8718412090539227101L;
/*    */   
/*    */   private DirectPosition offendingLocation;
/*    */   
/*    */   public PointOutsideCoverageException() {}
/*    */   
/*    */   public PointOutsideCoverageException(String message) {
/* 60 */     super(message);
/*    */   }
/*    */   
/*    */   public DirectPosition getOffendingLocation() {
/* 73 */     return this.offendingLocation;
/*    */   }
/*    */   
/*    */   public void setOffendingLocation(DirectPosition location) {
/* 86 */     this.offendingLocation = location;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\PointOutsideCoverageException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */