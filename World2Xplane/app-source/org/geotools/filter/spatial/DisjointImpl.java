/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Disjoint;
/*    */ 
/*    */ public class DisjointImpl extends AbstractPreparedGeometryFilter implements Disjoint {
/*    */   public DisjointImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 34 */     super(factory, e1, e2);
/* 37 */     this.filterType = 6;
/*    */   }
/*    */   
/*    */   public DisjointImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 41 */     super(factory, e1, e2, matchAction);
/* 44 */     this.filterType = 6;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 50 */     switch (this.literals) {
/*    */       case BOTH:
/* 52 */         return this.cacheValue;
/*    */       case RIGHT:
/* 54 */         return this.rightPreppedGeom.disjoint(left);
/*    */       case LEFT:
/* 57 */         return this.leftPreppedGeom.disjoint(right);
/*    */     } 
/* 60 */     return basicEvaluate(left, right);
/*    */   }
/*    */   
/*    */   protected boolean basicEvaluate(Geometry left, Geometry right) {
/* 68 */     Envelope envLeft = left.getEnvelopeInternal();
/* 69 */     Envelope envRight = right.getEnvelopeInternal();
/* 71 */     if (envRight.intersects(envLeft))
/* 72 */       return left.disjoint(right); 
/* 74 */     return true;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 78 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\DisjointImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */