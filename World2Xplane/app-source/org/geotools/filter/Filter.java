/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public interface Filter extends FilterType, Filter {
/* 34 */   public static final Filter ALL = (Filter)Filter.EXCLUDE;
/*    */   
/* 35 */   public static final Filter NONE = (Filter)Filter.INCLUDE;
/*    */   
/*    */   boolean evaluate(SimpleFeature paramSimpleFeature);
/*    */   
/*    */   boolean contains(SimpleFeature paramSimpleFeature);
/*    */   
/*    */   Filter and(Filter paramFilter);
/*    */   
/*    */   Filter or(Filter paramFilter);
/*    */   
/*    */   Filter not();
/*    */   
/*    */   short getFilterType();
/*    */   
/*    */   void accept(FilterVisitor paramFilterVisitor);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */