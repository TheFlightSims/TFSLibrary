/*    */ package org.geotools.filter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.opengis.filter.Filter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.Or;
/*    */ 
/*    */ public class OrImpl extends LogicFilterImpl implements Or {
/*    */   protected OrImpl(FilterFactory factory, List children) {
/* 35 */     super(factory, children);
/* 38 */     this.filterType = 1;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object feature) {
/* 42 */     for (Iterator<Filter> itr = this.children.iterator(); itr.hasNext(); ) {
/* 43 */       Filter filter = itr.next();
/* 44 */       if (filter.evaluate(feature))
/* 45 */         return true; 
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 52 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\OrImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */