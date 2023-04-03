/*    */ package org.poly2tri.triangulation;
/*    */ 
/*    */ public abstract class TriangulationDebugContext {
/*    */   protected TriangulationContext<?> _tcx;
/*    */   
/*    */   public TriangulationDebugContext(TriangulationContext<?> tcx) {
/* 31 */     this._tcx = tcx;
/*    */   }
/*    */   
/*    */   public abstract void clear();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\TriangulationDebugContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */