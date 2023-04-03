/*    */ package org.geotools.feature.visitor;
/*    */ 
/*    */ import org.geotools.filter.visitor.AbstractFinderFilterVisitor;
/*    */ import org.opengis.filter.Id;
/*    */ 
/*    */ public class IdFinderFilterVisitor extends AbstractFinderFilterVisitor {
/*    */   public Object visit(Id filter, Object data) {
/* 34 */     this.found = true;
/* 35 */     return Boolean.valueOf(this.found);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\IdFinderFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */