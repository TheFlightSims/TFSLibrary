/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.GeometryFilterImpl;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Crosses;
/*    */ 
/*    */ public class CrossesImpl extends GeometryFilterImpl implements Crosses {
/*    */   public CrossesImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 35 */     super(factory, e1, e2);
/* 38 */     this.filterType = 9;
/*    */   }
/*    */   
/*    */   public CrossesImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 42 */     super(factory, e1, e2, matchAction);
/* 45 */     this.filterType = 9;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 51 */     Envelope envLeft = left.getEnvelopeInternal();
/* 52 */     Envelope envRight = right.getEnvelopeInternal();
/* 54 */     if (envRight.intersects(envLeft))
/* 55 */       return left.crosses(right); 
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 61 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\CrossesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */