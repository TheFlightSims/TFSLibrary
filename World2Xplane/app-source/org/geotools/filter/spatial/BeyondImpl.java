/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.CartesianDistanceFilter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Beyond;
/*    */ 
/*    */ public class BeyondImpl extends CartesianDistanceFilter implements Beyond {
/*    */   public BeyondImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 35 */     super(factory, e1, e2);
/* 38 */     this.filterType = 13;
/*    */   }
/*    */   
/*    */   public BeyondImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 42 */     super(factory, e1, e2, matchAction);
/* 45 */     this.filterType = 13;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 50 */     if (left == null || right == null)
/* 51 */       return false; 
/* 53 */     return !left.isWithinDistance(right, getDistance());
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 57 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\BeyondImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */