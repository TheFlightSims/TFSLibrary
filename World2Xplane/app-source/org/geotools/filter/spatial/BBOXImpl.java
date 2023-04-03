/*     */ package org.geotools.filter.spatial;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import org.geotools.filter.AttributeExpressionImpl;
/*     */ import org.geotools.filter.BBoxExpressionImpl;
/*     */ import org.geotools.filter.FilterFactoryImpl;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class BBOXImpl extends AbstractPreparedGeometryFilter implements BBOX {
/*     */   double minx;
/*     */   
/*     */   double miny;
/*     */   
/*     */   double maxx;
/*     */   
/*     */   double maxy;
/*     */   
/*     */   String srs;
/*     */   
/*     */   public BBOXImpl(FilterFactory factory, Expression e1, Expression e2) {
/*  51 */     super(factory, e1, e2);
/*  54 */     this.filterType = 4;
/*  55 */     if (e1 != null)
/*  56 */       setExpression1(e1); 
/*  57 */     if (e2 != null)
/*  58 */       setExpression2(e2); 
/*     */   }
/*     */   
/*     */   public BBOXImpl(FilterFactoryImpl factory, Expression name, double minx, double miny, double maxx, double maxy, String srs) {
/*  63 */     this((FilterFactory)factory, name, (Expression)factory.createBBoxExpression(new Envelope(minx, maxx, miny, maxy)));
/*  64 */     this.srs = srs;
/*     */   }
/*     */   
/*     */   public BBOXImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/*  68 */     super(factory, e1, e2, matchAction);
/*  71 */     this.filterType = 4;
/*  72 */     if (e1 != null)
/*  73 */       setExpression1(e1); 
/*  74 */     if (e2 != null)
/*  75 */       setExpression2(e2); 
/*     */   }
/*     */   
/*     */   public BBOXImpl(FilterFactoryImpl factory, Expression name, double minx, double miny, double maxx, double maxy, String srs, MultiValuedFilter.MatchAction matchAction) {
/*  80 */     this((FilterFactory)factory, name, (Expression)factory.createBBoxExpression((Envelope)buildEnvelope(minx, maxx, miny, maxy, srs)), matchAction);
/*  81 */     this.srs = srs;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/*  87 */     if (getExpression1() instanceof PropertyName) {
/*  88 */       PropertyName propertyName = (PropertyName)getExpression1();
/*  89 */       return propertyName.getPropertyName();
/*     */     } 
/*  90 */     if (getExpression2() instanceof PropertyName) {
/*  91 */       PropertyName propertyName = (PropertyName)getExpression2();
/*  92 */       return propertyName.getPropertyName();
/*     */     } 
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public void setPropertyName(String propertyName) {
/*  99 */     setExpression1((Expression)new AttributeExpressionImpl(propertyName));
/*     */   }
/*     */   
/*     */   public String getSRS() {
/* 103 */     return this.srs;
/*     */   }
/*     */   
/*     */   public void setSRS(String srs) {
/* 110 */     this.srs = srs;
/* 111 */     updateExpression2();
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 115 */     return this.minx;
/*     */   }
/*     */   
/*     */   public void setMinX(double minx) {
/* 122 */     this.minx = minx;
/* 123 */     updateExpression2();
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 127 */     return this.miny;
/*     */   }
/*     */   
/*     */   public void setMinY(double miny) {
/* 134 */     this.miny = miny;
/* 135 */     updateExpression2();
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 139 */     return this.maxx;
/*     */   }
/*     */   
/*     */   public void setMaxX(double maxx) {
/* 146 */     this.maxx = maxx;
/* 147 */     updateExpression2();
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 151 */     return this.maxy;
/*     */   }
/*     */   
/*     */   public void setMaxY(double maxy) {
/* 158 */     this.maxy = maxy;
/* 159 */     updateExpression2();
/*     */   }
/*     */   
/*     */   private void updateExpression2() {
/* 164 */     BBoxExpressionImpl expression = new BBoxExpressionImpl((Envelope)buildEnvelope(this.minx, this.maxx, this.miny, this.maxy, this.srs)) {
/*     */       
/*     */       };
/* 165 */     super.setExpression2((Expression)expression);
/*     */   }
/*     */   
/*     */   public boolean evaluateInternal(Geometry left, Geometry right) {
/* 170 */     switch (this.literals) {
/*     */       case BOTH:
/* 172 */         return this.cacheValue;
/*     */       case RIGHT:
/* 174 */         return preppedEvaluate(this.rightPreppedGeom, left);
/*     */       case LEFT:
/* 177 */         return preppedEvaluate(this.leftPreppedGeom, right);
/*     */     } 
/* 180 */     return basicEvaluate(left, right);
/*     */   }
/*     */   
/*     */   protected boolean basicEvaluate(Geometry left, Geometry right) {
/* 186 */     Envelope envLeft = left.getEnvelopeInternal();
/* 187 */     Envelope envRight = right.getEnvelopeInternal();
/* 189 */     if (envRight.intersects(envLeft))
/* 190 */       return left.intersects(right); 
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   private boolean preppedEvaluate(PreparedGeometry prepped, Geometry other) {
/* 201 */     Envelope envLeft = prepped.getGeometry().getEnvelopeInternal();
/* 202 */     Envelope envRight = other.getEnvelopeInternal();
/* 204 */     if (envRight.intersects(envLeft))
/* 205 */       return prepped.intersects(other); 
/* 207 */     return false;
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 216 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression expression) {
/* 222 */     updateMinMaxFields(expression);
/* 223 */     super.setExpression1(expression);
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression expression) {
/* 229 */     updateMinMaxFields(expression);
/* 230 */     super.setExpression2(expression);
/*     */   }
/*     */   
/*     */   private void updateMinMaxFields(Expression expression) {
/* 234 */     if (expression instanceof Literal) {
/* 235 */       Literal bbox = (Literal)expression;
/* 236 */       Object value = bbox.getValue();
/* 237 */       if (value instanceof BoundingBox) {
/* 238 */         BoundingBox env = (BoundingBox)value;
/* 239 */         this.minx = env.getMinX();
/* 240 */         this.maxx = env.getMaxX();
/* 241 */         this.miny = env.getMinY();
/* 242 */         this.maxy = env.getMaxY();
/* 243 */         this.srs = CRS.toSRS(env.getCoordinateReferenceSystem());
/*     */       } else {
/* 245 */         Envelope env = null;
/* 246 */         if (value instanceof Envelope) {
/* 247 */           env = (Envelope)value;
/* 248 */         } else if (value instanceof Geometry) {
/* 249 */           Geometry geom = (Geometry)value;
/* 250 */           env = geom.getEnvelopeInternal();
/* 251 */           if (geom.getUserData() != null)
/* 252 */             if (geom.getUserData() instanceof String) {
/* 253 */               this.srs = (String)geom.getUserData();
/* 254 */             } else if (geom.getUserData() instanceof CoordinateReferenceSystem) {
/* 255 */               this.srs = CRS.toSRS((CoordinateReferenceSystem)geom.getUserData());
/*     */             }  
/*     */         } else {
/* 259 */           env = (Envelope)bbox.evaluate(null, Envelope.class);
/*     */         } 
/* 261 */         if (env == null)
/*     */           return; 
/* 263 */         this.minx = env.getMinX();
/* 264 */         this.maxx = env.getMaxX();
/* 265 */         this.miny = env.getMinY();
/* 266 */         this.maxy = env.getMaxY();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ReferencedEnvelope buildEnvelope(double minx, double maxx, double miny, double maxy, String srs) {
/* 272 */     CoordinateReferenceSystem crs = null;
/* 274 */     if (srs != null && !"".equals(srs))
/*     */       try {
/*     */         try {
/* 277 */           crs = CRS.decode(srs);
/* 278 */         } catch (MismatchedDimensionException e) {
/* 279 */           throw new RuntimeException(e);
/* 280 */         } catch (NoSuchAuthorityCodeException e) {
/* 281 */           CRS.parseWKT(srs);
/*     */         } 
/* 283 */       } catch (FactoryException e) {} 
/* 287 */     return new ReferencedEnvelope(minx, maxx, miny, maxy, crs);
/*     */   }
/*     */   
/*     */   public BoundingBox getBounds() {
/* 293 */     Object value = ((Literal)getExpression2()).getValue();
/* 294 */     if (value instanceof BoundingBox)
/* 295 */       return (BoundingBox)value; 
/* 298 */     return (BoundingBox)buildEnvelope(this.minx, this.maxx, this.miny, this.maxy, this.srs);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\BBOXImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */