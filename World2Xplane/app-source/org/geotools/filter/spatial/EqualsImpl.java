/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.geotools.filter.GeometryFilterImpl;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Equals;
/*    */ 
/*    */ public class EqualsImpl extends GeometryFilterImpl implements Equals {
/*    */   public EqualsImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 35 */     super(factory, e1, e2);
/* 38 */     this.filterType = 5;
/*    */   }
/*    */   
/*    */   public EqualsImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 42 */     super(factory, e1, e2, matchAction);
/* 45 */     this.filterType = 5;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 50 */     Envelope envLeft = left.getEnvelopeInternal();
/* 51 */     Envelope envRight = right.getEnvelopeInternal();
/* 53 */     if (envRight.equals(envLeft))
/* 54 */       return left.equalsExact(right); 
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 60 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\EqualsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */