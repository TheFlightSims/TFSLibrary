/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Intersects;
/*    */ 
/*    */ public class IntersectsImpl extends AbstractPreparedGeometryFilter implements Intersects {
/*    */   public IntersectsImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 36 */     super(factory, e1, e2);
/* 39 */     this.filterType = 7;
/*    */   }
/*    */   
/*    */   public IntersectsImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 44 */     super(factory, e1, e2, matchAction);
/* 47 */     this.filterType = 7;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 52 */     switch (this.literals) {
/*    */       case BOTH:
/* 54 */         return this.cacheValue;
/*    */       case RIGHT:
/* 56 */         return this.rightPreppedGeom.intersects(left);
/*    */       case LEFT:
/* 59 */         return this.leftPreppedGeom.intersects(right);
/*    */     } 
/* 62 */     return basicEvaluate(left, right);
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 68 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   protected final boolean basicEvaluate(Geometry left, Geometry right) {
/* 72 */     Envelope envLeft = left.getEnvelopeInternal();
/* 73 */     Envelope envRight = right.getEnvelopeInternal();
/* 74 */     return (envRight.intersects(envLeft) && left.intersects(right));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\IntersectsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */