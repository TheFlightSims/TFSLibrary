/*    */ package org.opengis.filter;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public final class IncludeFilter implements Filter, Serializable {
/*    */   private static final long serialVersionUID = -8429407144421087160L;
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 50 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 65 */     return "Filter.INCLUDE";
/*    */   }
/*    */   
/*    */   private Object readResolve() throws ObjectStreamException {
/* 72 */     return Filter.INCLUDE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\IncludeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */