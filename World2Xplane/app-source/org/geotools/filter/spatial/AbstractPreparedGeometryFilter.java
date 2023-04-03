/*     */ package org.geotools.filter.spatial;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import org.geotools.filter.GeometryFilterImpl;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ 
/*     */ public abstract class AbstractPreparedGeometryFilter extends GeometryFilterImpl {
/*     */   private final PreparedGeometryFactory pGeomFac;
/*     */   
/*     */   protected Literals literals;
/*     */   
/*     */   protected PreparedGeometry leftPreppedGeom;
/*     */   
/*     */   protected PreparedGeometry rightPreppedGeom;
/*     */   
/*     */   protected boolean cacheValue;
/*     */   
/*     */   protected enum Literals {
/* 104 */     NEITHER, RIGHT, LEFT, BOTH;
/*     */     
/*     */     private static Literals calculate(Expression expression1, Expression expression2) {
/* 120 */       boolean left = (expression1 instanceof Literal && ((Literal)expression1).getValue() instanceof Geometry);
/* 121 */       boolean right = (expression2 instanceof Literal && ((Literal)expression2).getValue() instanceof Geometry);
/* 122 */       if (left && right)
/* 123 */         return BOTH; 
/* 125 */       if (left)
/* 126 */         return LEFT; 
/* 128 */       if (right)
/* 129 */         return RIGHT; 
/* 131 */       return NEITHER;
/*     */     }
/*     */   }
/*     */   
/*     */   protected AbstractPreparedGeometryFilter(FilterFactory factory, Expression e1, Expression e2) {
/* 159 */     super(factory, e1, e2);
/* 160 */     this.pGeomFac = new PreparedGeometryFactory();
/* 161 */     if (e1 != null)
/* 161 */       setExpression1(e1); 
/* 162 */     if (e2 != null)
/* 162 */       setExpression2(e2); 
/*     */   }
/*     */   
/*     */   protected AbstractPreparedGeometryFilter(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/* 168 */     super(factory, e1, e2, matchAction);
/* 169 */     this.pGeomFac = new PreparedGeometryFactory();
/* 170 */     if (e1 != null)
/* 170 */       setExpression1(e1); 
/* 171 */     if (e2 != null)
/* 171 */       setExpression2(e2); 
/*     */   }
/*     */   
/*     */   private void prepare() {
/*     */     Geometry left, right, geometry1;
/* 176 */     if (this.expression1 == null || this.expression2 == null)
/*     */       return; 
/* 180 */     this.literals = Literals.calculate(this.expression1, this.expression2);
/* 181 */     switch (this.literals) {
/*     */       case BOTH:
/* 183 */         left = (Geometry)((Literal)this.expression1).getValue();
/* 184 */         geometry1 = (Geometry)((Literal)this.expression2).getValue();
/* 185 */         this.cacheValue = basicEvaluate(left, geometry1);
/* 186 */         this.leftPreppedGeom = this.rightPreppedGeom = null;
/*     */         return;
/*     */       case LEFT:
/* 190 */         left = (Geometry)((Literal)this.expression1).getValue();
/* 191 */         this.leftPreppedGeom = this.pGeomFac.create(left);
/* 192 */         this.rightPreppedGeom = null;
/* 193 */         this.cacheValue = false;
/*     */         return;
/*     */       case RIGHT:
/* 197 */         right = (Geometry)((Literal)this.expression2).getValue();
/* 198 */         this.rightPreppedGeom = this.pGeomFac.create(right);
/* 199 */         this.leftPreppedGeom = null;
/* 200 */         this.cacheValue = false;
/*     */         return;
/*     */     } 
/* 204 */     this.leftPreppedGeom = this.rightPreppedGeom = null;
/* 205 */     this.cacheValue = false;
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression expression) {
/* 212 */     super.setExpression1(expression);
/* 213 */     prepare();
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression expression) {
/* 218 */     super.setExpression2(expression);
/* 219 */     prepare();
/*     */   }
/*     */   
/*     */   protected abstract boolean basicEvaluate(Geometry paramGeometry1, Geometry paramGeometry2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\AbstractPreparedGeometryFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */