/*    */ package org.geotools.filter;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ 
/*    */ public abstract class BinaryLogicAbstract extends AbstractFilter {
/*    */   protected List children;
/*    */   
/*    */   protected BinaryLogicAbstract(FilterFactory factory, List children) {
/* 33 */     super(factory);
/* 34 */     this.children = children;
/*    */   }
/*    */   
/*    */   public List<Filter> getChildren() {
/* 41 */     return Collections.unmodifiableList(this.children);
/*    */   }
/*    */   
/*    */   public void setChildren(List children) {
/* 45 */     this.children = children;
/*    */   }
/*    */   
/*    */   public Filter and(Filter filter) {
/* 49 */     return (Filter)this.factory.and(this, filter);
/*    */   }
/*    */   
/*    */   public Filter or(Filter filter) {
/* 53 */     return (Filter)this.factory.or(this, filter);
/*    */   }
/*    */   
/*    */   public Filter not() {
/* 57 */     return (Filter)this.factory.not(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BinaryLogicAbstract.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */