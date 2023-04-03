/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.Not;
/*    */ 
/*    */ public class NotImpl extends LogicFilterImpl implements Not {
/*    */   protected NotImpl(FilterFactory factory) {
/* 32 */     super(factory);
/* 35 */     this.filterType = 3;
/*    */   }
/*    */   
/*    */   protected NotImpl(FilterFactory factory, Filter filter) {
/* 39 */     super(factory);
/* 40 */     this.children.add(filter);
/* 43 */     this.filterType = 3;
/*    */   }
/*    */   
/*    */   public Filter getFilter() {
/* 47 */     return this.children.get(0);
/*    */   }
/*    */   
/*    */   public void setFilter(Filter filter) {
/* 51 */     if (this.children.isEmpty()) {
/* 52 */       this.children.add(filter);
/*    */     } else {
/* 55 */       this.children.set(0, filter);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object feature) {
/* 61 */     return !getFilter().evaluate(feature);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 65 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\NotImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */