/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.CartesianDistanceFilter;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.DWithin;
/*    */ 
/*    */ public class DWithinImpl extends CartesianDistanceFilter implements DWithin {
/*    */   public DWithinImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 34 */     super(factory, e1, e2);
/* 37 */     this.filterType = 24;
/*    */   }
/*    */   
/*    */   public DWithinImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 41 */     super(factory, e1, e2, matchAction);
/* 44 */     this.filterType = 24;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 49 */     return left.isWithinDistance(right, getDistance());
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 53 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\DWithinImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */