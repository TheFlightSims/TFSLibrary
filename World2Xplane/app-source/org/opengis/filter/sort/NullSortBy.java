/*    */ package org.opengis.filter.sort;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ 
/*    */ final class NullSortBy implements SortBy, Serializable {
/*    */   private static final long serialVersionUID = -4846119001746135007L;
/*    */   
/*    */   private final SortOrder order;
/*    */   
/*    */   NullSortBy(SortOrder order) {
/* 40 */     this.order = order;
/*    */   }
/*    */   
/*    */   public PropertyName getPropertyName() {
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   public SortOrder getSortOrder() {
/* 54 */     return this.order;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 62 */     return 0x99A6A821 ^ this.order.hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 70 */     if (object instanceof NullSortBy)
/* 71 */       return this.order.equals(((NullSortBy)object).order); 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   private Object readResolve() throws ObjectStreamException {
/* 80 */     if (this.order.equals(SortOrder.ASCENDING))
/* 80 */       return SortBy.NATURAL_ORDER; 
/* 81 */     if (this.order.equals(SortOrder.DESCENDING))
/* 81 */       return SortBy.REVERSE_ORDER; 
/* 82 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\sort\NullSortBy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */