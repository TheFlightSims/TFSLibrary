/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class GeometryFilterImpl extends BinaryComparisonAbstract implements GeometryFilter {
/*  77 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   protected MultiValuedFilter.MatchAction matchAction;
/*     */   
/*     */   protected GeometryFilterImpl(FilterFactory factory, MultiValuedFilter.MatchAction matchAction) {
/*  82 */     super(factory);
/*  83 */     this.matchAction = matchAction;
/*     */   }
/*     */   
/*     */   protected GeometryFilterImpl(FilterFactory factory, Expression e1, Expression e2, MultiValuedFilter.MatchAction matchAction) {
/*  87 */     super(factory, e1, e2);
/*  88 */     this.matchAction = matchAction;
/*     */   }
/*     */   
/*     */   protected GeometryFilterImpl(FilterFactory factory) {
/*  92 */     this(factory, MultiValuedFilter.MatchAction.ANY);
/*     */   }
/*     */   
/*     */   protected GeometryFilterImpl(FilterFactory factory, Expression e1, Expression e2) {
/*  96 */     this(factory, e1, e2, MultiValuedFilter.MatchAction.ANY);
/*     */   }
/*     */   
/*     */   protected GeometryFilterImpl(short filterType) throws IllegalFilterException {
/* 109 */     super(CommonFactoryFinder.getFilterFactory(null));
/* 111 */     if (isGeometryFilter(filterType)) {
/* 112 */       this.filterType = filterType;
/*     */     } else {
/* 114 */       throw new IllegalFilterException("Attempted to create geometry filter with non-geometry type.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void addLeftGeometry(Expression leftGeometry) throws IllegalFilterException {
/* 131 */     setExpression1(leftGeometry);
/*     */   }
/*     */   
/*     */   public void setExpression1(Expression expression) {
/* 135 */     if (expression instanceof Expression) {
/* 136 */       Expression leftGeometry = (Expression)expression;
/* 139 */       if (DefaultExpression.isGeometryExpression(leftGeometry.getType()) || this.permissiveConstruction) {
/* 141 */         super.setExpression1(leftGeometry);
/*     */       } else {
/* 143 */         throw new IllegalFilterException("Attempted to add (left) non-geometry expression to geometry filter.");
/*     */       } 
/*     */     } else {
/* 148 */       super.setExpression1(expression);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void addRightGeometry(Expression rightGeometry) throws IllegalFilterException {
/* 166 */     setExpression2(rightGeometry);
/*     */   }
/*     */   
/*     */   public void setExpression2(Expression expression) {
/* 170 */     if (expression instanceof Expression) {
/* 171 */       Expression rightGeometry = (Expression)expression;
/* 174 */       if (DefaultExpression.isGeometryExpression(rightGeometry.getType()) || this.permissiveConstruction) {
/* 176 */         super.setExpression2(rightGeometry);
/*     */       } else {
/* 178 */         throw new IllegalFilterException("Attempted to add (right) non-geometryexpression to geometry filter.");
/*     */       } 
/*     */     } else {
/* 183 */       super.setExpression2(expression);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Expression getLeftGeometry() {
/* 194 */     return (Expression)getExpression1();
/*     */   }
/*     */   
/*     */   public final Expression getRightGeometry() {
/* 204 */     return (Expression)getExpression2();
/*     */   }
/*     */   
/*     */   protected Geometry getLeftGeometry(Object feature) {
/* 214 */     Expression leftGeometry = getExpression1();
/* 216 */     if (leftGeometry != null) {
/* 217 */       Object obj = leftGeometry.evaluate(feature, Geometry.class);
/* 220 */       return (Geometry)obj;
/*     */     } 
/* 221 */     if (feature instanceof SimpleFeature)
/* 222 */       return (Geometry)((SimpleFeature)feature).getDefaultGeometry(); 
/* 224 */     return null;
/*     */   }
/*     */   
/*     */   protected Geometry getRightGeometry(Object feature) {
/* 234 */     Expression rightGeometry = getExpression2();
/* 236 */     if (rightGeometry != null)
/* 237 */       return (Geometry)rightGeometry.evaluate(feature, Geometry.class); 
/* 238 */     if (feature instanceof SimpleFeature)
/* 239 */       return (Geometry)((SimpleFeature)feature).getDefaultGeometry(); 
/* 241 */     return null;
/*     */   }
/*     */   
/*     */   protected static Object getGeometries(Expression expr, Object feature) {
/* 250 */     Object o = expr.evaluate(feature);
/* 252 */     if (o instanceof Collection) {
/* 253 */       List<Geometry> list = new ArrayList<Geometry>();
/* 254 */       for (Object item : o) {
/* 255 */         Geometry geometry = (Geometry)Converters.convert(item, Geometry.class);
/* 256 */         if (geometry != null)
/* 257 */           list.add(geometry); 
/*     */       } 
/* 260 */       return (list.size() > 0) ? list : null;
/*     */     } 
/* 263 */     return Converters.convert(o, Geometry.class);
/*     */   }
/*     */   
/*     */   public boolean evaluate(SimpleFeature feature) {
/* 274 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 283 */     String operator = null;
/* 286 */     if (this.filterType == 5) {
/* 287 */       operator = " equals ";
/* 288 */     } else if (this.filterType == 6) {
/* 289 */       operator = " disjoint ";
/* 290 */     } else if (this.filterType == 7) {
/* 291 */       operator = " intersects ";
/* 292 */     } else if (this.filterType == 8) {
/* 293 */       operator = " touches ";
/* 294 */     } else if (this.filterType == 9) {
/* 295 */       operator = " crosses ";
/* 296 */     } else if (this.filterType == 10) {
/* 297 */       operator = " within ";
/* 298 */     } else if (this.filterType == 11) {
/* 299 */       operator = " contains ";
/* 300 */     } else if (this.filterType == 12) {
/* 301 */       operator = " overlaps ";
/* 302 */     } else if (this.filterType == 13) {
/* 303 */       operator = " beyond ";
/* 304 */     } else if (this.filterType == 4) {
/* 305 */       operator = " bbox ";
/*     */     } 
/* 308 */     Expression leftGeometry = getExpression1();
/* 309 */     Expression rightGeometry = getExpression2();
/* 311 */     if (this.expression1 == null && rightGeometry == null)
/* 312 */       return "[ null" + operator + "null" + " ]"; 
/* 313 */     if (leftGeometry == null)
/* 314 */       return "[ null" + operator + rightGeometry.toString() + " ]"; 
/* 315 */     if (rightGeometry == null)
/* 316 */       return "[ " + leftGeometry.toString() + operator + "null" + " ]"; 
/* 319 */     return "[ " + leftGeometry.toString() + operator + rightGeometry.toString() + " ]";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 333 */     if (obj instanceof GeometryFilterImpl) {
/* 334 */       GeometryFilterImpl geomFilter = (GeometryFilterImpl)obj;
/* 335 */       boolean isEqual = true;
/* 337 */       isEqual = (geomFilter.getFilterType() == this.filterType);
/* 338 */       if (LOGGER.isLoggable(Level.FINEST))
/* 339 */         LOGGER.finest("filter type match:" + isEqual + "; in:" + geomFilter.getFilterType() + "; out:" + this.filterType); 
/* 342 */       isEqual = (geomFilter.expression1 != null) ? ((isEqual && geomFilter.expression1.equals(this.expression1))) : ((isEqual && this.expression1 == null));
/* 345 */       if (LOGGER.isLoggable(Level.FINEST))
/* 346 */         LOGGER.finest("left geom match:" + isEqual + "; in:" + geomFilter.expression1 + "; out:" + this.expression1); 
/* 349 */       isEqual = (geomFilter.expression2 != null) ? ((isEqual && geomFilter.expression2.equals(this.expression2))) : ((isEqual && this.expression2 == null));
/* 353 */       if (LOGGER.isLoggable(Level.FINEST))
/* 354 */         LOGGER.finest("right geom match:" + isEqual + "; in:" + geomFilter.expression2 + "; out:" + this.expression2); 
/* 357 */       return isEqual;
/*     */     } 
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 369 */     Expression leftGeometry = getExpression1();
/* 370 */     Expression rightGeometry = getExpression2();
/* 372 */     int result = 17;
/* 373 */     result = 37 * result + this.filterType;
/* 374 */     result = 37 * result + ((leftGeometry == null) ? 0 : leftGeometry.hashCode());
/* 376 */     result = 37 * result + ((rightGeometry == null) ? 0 : rightGeometry.hashCode());
/* 379 */     return result;
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/* 383 */     return this.matchAction;
/*     */   }
/*     */   
/*     */   public final boolean evaluate(Object feature) {
/* 388 */     Object object1 = getGeometries(getExpression1(), feature);
/* 389 */     Object object2 = getGeometries(getExpression2(), feature);
/* 391 */     if (object1 == null || object2 == null)
/* 394 */       return false; 
/* 397 */     if (!(object1 instanceof Collection) && !(object2 instanceof Collection))
/* 398 */       return evaluateInternal((Geometry)object1, (Geometry)object2); 
/* 401 */     Collection<Geometry> leftValues = (object1 instanceof Collection) ? (Collection<Geometry>)object1 : Collections.<Geometry>singletonList((Geometry)object1);
/* 403 */     Collection<Geometry> rightValues = (object2 instanceof Collection) ? (Collection<Geometry>)object2 : Collections.<Geometry>singletonList((Geometry)object2);
/* 406 */     int count = 0;
/* 407 */     for (Geometry leftValue : leftValues) {
/* 408 */       for (Geometry rightValue : rightValues) {
/* 410 */         boolean temp = evaluateInternal(leftValue, rightValue);
/* 411 */         if (temp)
/* 412 */           count++; 
/* 415 */         switch (this.matchAction) {
/*     */           case ONE:
/* 416 */             if (count > 1)
/* 416 */               return false; 
/*     */           case ALL:
/* 417 */             if (!temp)
/* 417 */               return false; 
/*     */           case ANY:
/* 418 */             if (temp)
/* 418 */               return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 423 */     switch (this.matchAction) {
/*     */       case ONE:
/* 424 */         return (count == 1);
/*     */       case ALL:
/* 425 */         return true;
/*     */       case ANY:
/* 426 */         return false;
/*     */     } 
/* 427 */     return false;
/*     */   }
/*     */   
/*     */   protected abstract boolean evaluateInternal(Geometry paramGeometry1, Geometry paramGeometry2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\GeometryFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */