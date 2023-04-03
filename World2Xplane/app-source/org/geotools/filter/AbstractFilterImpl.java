/*    */ package org.geotools.filter;
/*    */ 
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ 
/*    */ public abstract class AbstractFilterImpl extends AbstractFilter {
/*    */   protected AbstractFilterImpl(FilterFactory factory) {
/* 38 */     super(factory);
/*    */   }
/*    */   
/*    */   public Filter or(Filter filter) {
/*    */     try {
/* 50 */       return (Filter)this.factory.or(this, filter);
/* 51 */     } catch (IllegalFilterException ife) {
/* 52 */       return (Filter)filter;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Filter and(Filter filter) {
/*    */     try {
/* 65 */       return (Filter)this.factory.and(this, filter);
/* 66 */     } catch (IllegalFilterException ife) {
/* 67 */       return (Filter)filter;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Filter not() {
/*    */     try {
/* 78 */       return (Filter)this.factory.not(this);
/* 79 */     } catch (IllegalFilterException ife) {
/* 80 */       return this;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AbstractFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */