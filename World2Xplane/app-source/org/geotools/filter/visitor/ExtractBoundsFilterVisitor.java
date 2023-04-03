/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.ExcludeFilter;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.IncludeFilter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.NilExpression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class ExtractBoundsFilterVisitor extends NullFilterVisitor {
/*  96 */   public static NullFilterVisitor BOUNDS_VISITOR = new ExtractBoundsFilterVisitor();
/*     */   
/*  98 */   private static Logger LOGGER = Logging.getLogger("org.geotools.index.rtree");
/*     */   
/*     */   private ReferencedEnvelope bbox(Object data) {
/* 117 */     if (data == null)
/* 118 */       return null; 
/* 120 */     if (data instanceof ReferencedEnvelope)
/* 121 */       return (ReferencedEnvelope)data; 
/* 123 */     if (data instanceof Envelope)
/* 124 */       return new ReferencedEnvelope((Envelope)data, null); 
/* 126 */     if (data instanceof CoordinateReferenceSystem)
/* 127 */       return new ReferencedEnvelope((CoordinateReferenceSystem)data); 
/* 129 */     throw new ClassCastException("Could not cast data to ReferencedEnvelope");
/*     */   }
/*     */   
/*     */   public Object visit(ExcludeFilter filter, Object data) {
/* 133 */     return new Envelope();
/*     */   }
/*     */   
/*     */   public Object visit(IncludeFilter filter, Object data) {
/* 137 */     return infinity();
/*     */   }
/*     */   
/*     */   Envelope infinity() {
/* 141 */     return new Envelope(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/* 146 */     ReferencedEnvelope bbox = bbox(data);
/* 149 */     Envelope bounds = new Envelope(filter.getMinX(), filter.getMaxX(), filter.getMinY(), filter.getMaxY());
/* 151 */     if (bbox != null) {
/* 152 */       bbox.expandToInclude(bounds);
/* 153 */       return bbox;
/*     */     } 
/* 155 */     return bbox(bounds);
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object data) {
/* 166 */     ReferencedEnvelope bbox = bbox(data);
/* 168 */     Object value = expression.getValue();
/* 169 */     if (value instanceof Geometry) {
/* 171 */       Geometry geometry = (Geometry)value;
/* 172 */       Envelope bounds = geometry.getEnvelopeInternal();
/* 174 */       if (bbox != null) {
/* 175 */         bbox.expandToInclude(bounds);
/* 176 */         return bbox;
/*     */       } 
/* 178 */       return bbox(bounds);
/*     */     } 
/* 181 */     LOGGER.finer("LiteralExpression ignored!");
/* 183 */     return bbox;
/*     */   }
/*     */   
/*     */   public Object visit(And filter, Object data) {
/* 188 */     Envelope mixed = infinity();
/* 189 */     for (Filter f : filter.getChildren()) {
/* 190 */       Envelope env = (Envelope)f.accept(this, data);
/* 191 */       mixed = mixed.intersection(env);
/*     */     } 
/* 193 */     return mixed;
/*     */   }
/*     */   
/*     */   public Object visit(Not filter, Object data) {
/* 204 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Or filter, Object data) {
/* 209 */     Envelope mixed = new Envelope();
/* 210 */     for (Filter f : filter.getChildren()) {
/* 211 */       Envelope env = (Envelope)f.accept(this, data);
/* 212 */       mixed.expandToInclude(env);
/*     */     } 
/* 214 */     return mixed;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/* 220 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/* 224 */     data = filter.getExpression1().accept(this, data);
/* 225 */     data = filter.getExpression2().accept(this, data);
/* 226 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/* 230 */     data = filter.getExpression1().accept(this, data);
/* 231 */     data = filter.getExpression2().accept(this, data);
/* 232 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/* 238 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/* 242 */     ReferencedEnvelope bbox = bbox(data);
/* 247 */     Literal geometry = null;
/* 248 */     if (filter.getExpression1() instanceof PropertyName && filter.getExpression2() instanceof Literal)
/* 250 */       geometry = (Literal)filter.getExpression2(); 
/* 251 */     if (filter.getExpression2() instanceof PropertyName && filter.getExpression1() instanceof Literal)
/* 253 */       geometry = (Literal)filter.getExpression2(); 
/* 257 */     if (geometry == null)
/* 258 */       return infinity(); 
/* 261 */     Geometry geom = (Geometry)geometry.evaluate(null, Geometry.class);
/* 262 */     if (geom == null)
/* 263 */       return infinity(); 
/* 266 */     Envelope env = geom.getEnvelopeInternal();
/* 267 */     env.expandBy(filter.getDistance());
/* 269 */     if (bbox != null) {
/* 270 */       bbox.expandToInclude(env);
/* 271 */       return bbox;
/*     */     } 
/* 273 */     return bbox(env);
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/* 278 */     data = filter.getExpression1().accept(this, data);
/* 279 */     data = filter.getExpression2().accept(this, data);
/* 280 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/* 284 */     data = filter.getExpression1().accept(this, data);
/* 285 */     data = filter.getExpression2().accept(this, data);
/* 287 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/* 291 */     data = filter.getExpression1().accept(this, data);
/* 292 */     data = filter.getExpression2().accept(this, data);
/* 294 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 298 */     data = filter.getExpression1().accept(this, data);
/* 299 */     data = filter.getExpression2().accept(this, data);
/* 301 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 305 */     data = filter.getExpression1().accept(this, data);
/* 306 */     data = filter.getExpression2().accept(this, data);
/* 308 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Add expression, Object data) {
/* 313 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Divide expression, Object data) {
/* 318 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Function expression, Object data) {
/* 323 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Id filter, Object data) {
/* 328 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(Multiply expression, Object data) {
/* 333 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(NilExpression expression, Object data) {
/* 338 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsBetween filter, Object data) {
/* 343 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object data) {
/* 348 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThan filter, Object data) {
/* 353 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsGreaterThanOrEqualTo filter, Object data) {
/* 358 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThan filter, Object data) {
/* 363 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLessThanOrEqualTo filter, Object data) {
/* 368 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsLike filter, Object data) {
/* 373 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object data) {
/* 378 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNull filter, Object data) {
/* 383 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visit(PropertyName expression, Object data) {
/* 388 */     return null;
/*     */   }
/*     */   
/*     */   public Object visit(Subtract expression, Object data) {
/* 393 */     return infinity();
/*     */   }
/*     */   
/*     */   public Object visitNullFilter(Object data) {
/* 398 */     return infinity();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\ExtractBoundsFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */