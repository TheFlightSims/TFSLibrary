/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ import org.opengis.filter.sort.SortBy;
/*    */ import org.opengis.filter.sort.SortOrder;
/*    */ 
/*    */ public class SortByImpl implements SortBy {
/*    */   PropertyName propertyName;
/*    */   
/*    */   SortOrder sortOrder;
/*    */   
/*    */   public SortByImpl(PropertyName propertyName, SortOrder sortOrder) {
/* 35 */     this.propertyName = propertyName;
/* 36 */     this.sortOrder = sortOrder;
/*    */   }
/*    */   
/*    */   public PropertyName getPropertyName() {
/* 40 */     return this.propertyName;
/*    */   }
/*    */   
/*    */   public void setPropertyName(PropertyName propertyName) {
/* 44 */     this.propertyName = propertyName;
/*    */   }
/*    */   
/*    */   public SortOrder getSortOrder() {
/* 48 */     return this.sortOrder;
/*    */   }
/*    */   
/*    */   public void setSortOrder(SortOrder sortOrder) {
/* 52 */     this.sortOrder = sortOrder;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\SortByImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */