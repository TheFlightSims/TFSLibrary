/*    */ package org.opengis.coverage.grid;
/*    */ 
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ 
/*    */ @UML(identifier = "GC_GridNotEditable", specification = Specification.OGC_01004)
/*    */ public class GridNotEditableException extends IllegalStateException {
/*    */   private static final long serialVersionUID = 612186655921122650L;
/*    */   
/*    */   public GridNotEditableException() {}
/*    */   
/*    */   public GridNotEditableException(String message) {
/* 52 */     super(message);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\grid\GridNotEditableException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */