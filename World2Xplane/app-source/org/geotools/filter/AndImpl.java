/*    */ package org.geotools.filter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.filter.And;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ 
/*    */ public class AndImpl extends LogicFilterImpl implements And {
/*    */   protected AndImpl(FilterFactory factory, List children) {
/* 38 */     super(factory, children);
/* 41 */     this.filterType = 2;
/*    */   }
/*    */   
/*    */   public boolean evaluate(SimpleFeature feature) {
/* 46 */     for (Iterator<Filter> itr = this.children.iterator(); itr.hasNext(); ) {
/* 47 */       Filter filter = itr.next();
/* 48 */       if (!filter.evaluate(feature))
/* 49 */         return false; 
/*    */     } 
/* 52 */     return true;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 55 */     for (Iterator<Filter> itr = this.children.iterator(); itr.hasNext(); ) {
/* 56 */       Filter filter = itr.next();
/* 57 */       if (!filter.evaluate(object))
/* 58 */         return false; 
/*    */     } 
/* 61 */     return true;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 65 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AndImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */