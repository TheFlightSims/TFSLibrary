/*    */ package org.opengis.filter.sort;
/*    */ 
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ 
/*    */ public interface SortBy {
/* 54 */   public static final SortBy[] UNSORTED = new SortBy[0];
/*    */   
/* 74 */   public static final SortBy NATURAL_ORDER = new NullSortBy(SortOrder.ASCENDING);
/*    */   
/* 82 */   public static final SortBy REVERSE_ORDER = new NullSortBy(SortOrder.DESCENDING);
/*    */   
/*    */   PropertyName getPropertyName();
/*    */   
/*    */   SortOrder getSortOrder();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\sort\SortBy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */