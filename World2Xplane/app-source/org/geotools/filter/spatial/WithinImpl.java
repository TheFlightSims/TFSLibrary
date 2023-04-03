/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Within;
/*    */ 
/*    */ public class WithinImpl extends AbstractPreparedGeometryFilter implements Within {
/*    */   public WithinImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 34 */     super(factory, e1, e2);
/* 37 */     this.filterType = 10;
/*    */   }
/*    */   
/*    */   public WithinImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 41 */     super(factory, e1, e2, matchAction);
/* 44 */     this.filterType = 10;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 50 */     switch (this.literals) {
/*    */       case BOTH:
/* 52 */         return this.cacheValue;
/*    */       case RIGHT:
/* 55 */         return this.rightPreppedGeom.contains(left);
/*    */       case LEFT:
/* 60 */         return basicEvaluate(this.leftPreppedGeom.getGeometry(), right);
/*    */     } 
/* 63 */     return basicEvaluate(left, right);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 69 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   protected boolean basicEvaluate(Geometry left, Geometry right) {
/* 75 */     Envelope envLeft = left.getEnvelopeInternal();
/* 76 */     Envelope envRight = right.getEnvelopeInternal();
/* 78 */     if (envRight.contains(envLeft))
/* 79 */       return left.within(right); 
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\WithinImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */