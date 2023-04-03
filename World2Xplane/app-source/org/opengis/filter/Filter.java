/*    */ package org.opengis.filter;
/*    */ 
/*    */ import org.opengis.annotation.XmlElement;
/*    */ 
/*    */ @XmlElement("Filter")
/*    */ public interface Filter {
/* 55 */   public static final IncludeFilter INCLUDE = new IncludeFilter();
/*    */   
/* 62 */   public static final ExcludeFilter EXCLUDE = new ExcludeFilter();
/*    */   
/*    */   boolean evaluate(Object paramObject);
/*    */   
/*    */   Object accept(FilterVisitor paramFilterVisitor, Object paramObject);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */