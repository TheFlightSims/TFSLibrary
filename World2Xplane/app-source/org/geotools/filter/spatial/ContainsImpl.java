/*    */ package org.geotools.filter.spatial;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.opengis.filter.FilterFactory;
/*    */ import org.opengis.filter.FilterVisitor;
/*    */ import org.opengis.filter.MultiValuedFilter;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.spatial.Contains;
/*    */ 
/*    */ public class ContainsImpl extends AbstractPreparedGeometryFilter implements Contains {
/*    */   public ContainsImpl(FilterFactory factory, Expression e1, Expression e2) {
/* 34 */     super(factory, e1, e2);
/* 36 */     this.filterType = 11;
/*    */   }
/*    */   
/*    */   public ContainsImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 40 */     super(factory, e1, e2, matchAction);
/* 42 */     this.filterType = 11;
/*    */   }
/*    */   
/*    */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 48 */     switch (this.literals) {
/*    */       case BOTH:
/* 50 */         return this.cacheValue;
/*    */       case RIGHT:
/* 54 */         return basicEvaluate(left, right);
/*    */       case LEFT:
/* 57 */         return this.leftPreppedGeom.contains(right);
/*    */     } 
/* 60 */     return basicEvaluate(left, right);
/*    */   }
/*    */   
/*    */   protected boolean basicEvaluate(Geometry left, Geometry right) {
/* 67 */     Envelope envLeft = left.getEnvelopeInternal();
/* 68 */     Envelope envRight = right.getEnvelopeInternal();
/* 70 */     if (envLeft.contains(envRight))
/* 71 */       return left.contains(right); 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 76 */     return visitor.visit(this, extraData);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\ContainsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */