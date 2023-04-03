/*     */ package org.geotools.filter.spatial;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.visitor.DuplicatingFilterVisitor;
/*     */ import org.geotools.geometry.jts.JTS;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Literal;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.BinarySpatialOperator;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectingFilterVisitor extends DuplicatingFilterVisitor {
/*     */   FeatureType featureType;
/*     */   
/*     */   public ReprojectingFilterVisitor(FilterFactory2 factory, FeatureType featureType) {
/*  73 */     super(factory);
/*  74 */     this.featureType = featureType;
/*     */   }
/*     */   
/*     */   private CoordinateReferenceSystem findPropertyCRS(PropertyName propertyName) {
/*  85 */     Object o = propertyName.evaluate(this.featureType);
/*  86 */     if (o instanceof GeometryDescriptor) {
/*  87 */       GeometryDescriptor gat = (GeometryDescriptor)o;
/*  88 */       return gat.getCoordinateReferenceSystem();
/*     */     } 
/*  90 */     return null;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object extraData) {
/*     */     ReferencedEnvelope referencedEnvelope;
/*  97 */     BoundingBox boundaries = filter.getBounds();
/*  99 */     CoordinateReferenceSystem crs = boundaries.getCoordinateReferenceSystem();
/* 102 */     if (crs == null)
/* 103 */       return super.visit(filter, extraData); 
/* 106 */     PropertyName propertyName = null;
/* 108 */     if (filter.getExpression1() instanceof PropertyName) {
/* 109 */       propertyName = (PropertyName)filter.getExpression1();
/* 110 */     } else if (filter.getExpression2() instanceof PropertyName) {
/* 111 */       propertyName = (PropertyName)filter.getExpression2();
/*     */     } 
/* 113 */     CoordinateReferenceSystem targetCrs = findPropertyCRS(propertyName);
/* 116 */     if (crs != null && targetCrs != null && !CRS.equalsIgnoreMetadata(crs, targetCrs)) {
/* 117 */       ReferencedEnvelope envelope = ReferencedEnvelope.reference(boundaries);
/*     */       try {
/* 119 */         envelope = envelope.transform(targetCrs, true);
/* 120 */       } catch (TransformException e) {
/* 121 */         throw new RuntimeException(e);
/* 122 */       } catch (FactoryException e) {
/* 123 */         throw new RuntimeException(e);
/*     */       } 
/* 125 */       referencedEnvelope = envelope;
/*     */     } 
/* 128 */     return getFactory(extraData).bbox((Expression)propertyName, (BoundingBox)referencedEnvelope);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsEqualTo filter, Object extraData) {
/* 133 */     return (new BinaryComparisonTransformer() {
/*     */         Object cloneFilter(BinaryComparisonOperator filter, Object extraData) {
/* 136 */           return ReprojectingFilterVisitor.this.visit((PropertyIsEqualTo)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinaryComparisonOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 141 */           return ReprojectingFilterVisitor.this.ff.equal(ex1, ex2, bso.isMatchingCase());
/*     */         }
/*     */       }).transform((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(PropertyIsNotEqualTo filter, Object extraData) {
/* 147 */     return (new BinaryComparisonTransformer() {
/*     */         Object cloneFilter(BinaryComparisonOperator filter, Object extraData) {
/* 150 */           return ReprojectingFilterVisitor.this.visit((PropertyIsNotEqualTo)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinaryComparisonOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 155 */           return ReprojectingFilterVisitor.this.ff.notEqual(ex1, ex2, bso.isMatchingCase());
/*     */         }
/*     */       }).transform((BinaryComparisonOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object extraData) {
/* 161 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 164 */           return ReprojectingFilterVisitor.this.visit((Beyond)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 169 */           Beyond filter = (Beyond)bso;
/* 170 */           return ReprojectingFilterVisitor.this.ff.beyond(ex1, ex2, filter.getDistance(), filter.getDistanceUnits());
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object extraData) {
/* 176 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 179 */           return ReprojectingFilterVisitor.this.visit((Contains)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 184 */           return ReprojectingFilterVisitor.this.ff.contains(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object extraData) {
/* 190 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 193 */           return ReprojectingFilterVisitor.this.visit((Crosses)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 198 */           return ReprojectingFilterVisitor.this.ff.crosses(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object extraData) {
/* 204 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 207 */           return ReprojectingFilterVisitor.this.visit((Disjoint)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 212 */           return ReprojectingFilterVisitor.this.ff.disjoint(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object extraData) {
/* 218 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 221 */           return ReprojectingFilterVisitor.this.visit((DWithin)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 226 */           DWithin filter = (DWithin)bso;
/* 227 */           return ReprojectingFilterVisitor.this.ff.dwithin(ex1, ex2, filter.getDistance(), filter.getDistanceUnits());
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object extraData) {
/* 233 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 236 */           return ReprojectingFilterVisitor.this.visit((Intersects)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 241 */           return ReprojectingFilterVisitor.this.ff.intersects(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object extraData) {
/* 247 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 250 */           return ReprojectingFilterVisitor.this.visit((Overlaps)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 255 */           return ReprojectingFilterVisitor.this.ff.overlaps(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object extraData) {
/* 261 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 264 */           return ReprojectingFilterVisitor.this.visit((Touches)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 269 */           return ReprojectingFilterVisitor.this.ff.touches(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object extraData) {
/* 275 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 278 */           return ReprojectingFilterVisitor.this.visit((Within)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 283 */           return ReprojectingFilterVisitor.this.ff.within(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object extraData) {
/* 289 */     return (new GeometryFilterTransformer() {
/*     */         Object cloneFilter(BinarySpatialOperator filter, Object extraData) {
/* 292 */           return ReprojectingFilterVisitor.this.visit((Equals)filter, extraData);
/*     */         }
/*     */         
/*     */         Object cloneFilter(BinarySpatialOperator bso, Object extraData, Expression ex1, Expression ex2) {
/* 297 */           return ReprojectingFilterVisitor.this.ff.equal(ex1, ex2);
/*     */         }
/*     */       }).transform((BinarySpatialOperator)filter, extraData);
/*     */   }
/*     */   
/*     */   public Object visit(Literal expression, Object extraData) {
/* 303 */     Object value = expression.getValue();
/* 304 */     if (value instanceof Geometry)
/* 305 */       value = reproject(value, this.featureType.getCoordinateReferenceSystem()); 
/* 308 */     return getFactory(extraData).literal(value);
/*     */   }
/*     */   
/*     */   protected Geometry reproject(Object value, CoordinateReferenceSystem propertyCrs) {
/* 315 */     if (value == null)
/* 316 */       return null; 
/* 319 */     if (!(value instanceof Geometry))
/* 320 */       throw new IllegalArgumentException("Binary geometry filter, but second expression is not a geometry literal? (it's a " + value.getClass() + ")"); 
/* 322 */     Geometry geom = (Geometry)value;
/* 325 */     if (geom.getUserData() == null || !(geom.getUserData() instanceof CoordinateReferenceSystem))
/* 327 */       return geom; 
/*     */     try {
/* 331 */       CoordinateReferenceSystem geomCRS = (CoordinateReferenceSystem)geom.getUserData();
/* 332 */       Geometry transformed = JTS.transform(geom, CRS.findMathTransform(geomCRS, propertyCrs, true));
/* 333 */       transformed.setUserData(propertyCrs);
/* 335 */       return transformed;
/* 336 */     } catch (Exception e) {
/* 337 */       throw new RuntimeException("Could not reproject geometry " + value, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   Expression reproject(Expression expression, CoordinateReferenceSystem propertyCrs, boolean forceReprojection) {
/* 344 */     if (expression instanceof Function) {
/* 346 */       Function delegate = (Function)expression;
/* 347 */       return (Expression)new FunctionReprojector(propertyCrs, delegate);
/*     */     } 
/* 348 */     if (expression instanceof Literal) {
/* 350 */       Object value = ((Literal)expression).getValue();
/* 351 */       return (Expression)this.ff.literal(reproject(value, propertyCrs));
/*     */     } 
/* 352 */     if (forceReprojection)
/* 353 */       throw new IllegalArgumentException("Binary geometry filter, but second expression is not a literal or function? (it's a " + expression.getClass() + ")"); 
/* 357 */     return null;
/*     */   }
/*     */   
/*     */   private abstract class GeometryFilterTransformer {
/*     */     private GeometryFilterTransformer() {}
/*     */     
/*     */     Object transform(BinarySpatialOperator filter, Object extraData) {
/* 370 */       if (!(filter.getExpression1() instanceof PropertyName))
/* 371 */         throw new IllegalArgumentException("Binary geometry filter, but first expression is not a property name? (it's a " + filter.getExpression1().getClass() + ")"); 
/* 374 */       CoordinateReferenceSystem propertyCrs = ReprojectingFilterVisitor.this.findPropertyCRS((PropertyName)filter.getExpression1());
/* 376 */       if (propertyCrs == null)
/* 377 */         return cloneFilter(filter, extraData); 
/* 380 */       Expression ex1 = (Expression)filter.getExpression1().accept((ExpressionVisitor)ReprojectingFilterVisitor.this, extraData);
/* 382 */       Expression ex2 = ReprojectingFilterVisitor.this.reproject(filter.getExpression2(), propertyCrs, true);
/* 384 */       return cloneFilter(filter, extraData, ex1, ex2);
/*     */     }
/*     */     
/*     */     abstract Object cloneFilter(BinarySpatialOperator param1BinarySpatialOperator, Object param1Object);
/*     */     
/*     */     abstract Object cloneFilter(BinarySpatialOperator param1BinarySpatialOperator, Object param1Object, Expression param1Expression1, Expression param1Expression2);
/*     */   }
/*     */   
/*     */   private abstract class BinaryComparisonTransformer {
/*     */     private BinaryComparisonTransformer() {}
/*     */     
/*     */     Object transform(BinaryComparisonOperator filter, Object extraData) {
/*     */       PropertyName name;
/*     */       Expression other;
/* 421 */       if (filter.getExpression1() instanceof PropertyName) {
/* 422 */         name = (PropertyName)filter.getExpression1();
/* 423 */         other = filter.getExpression2();
/* 424 */       } else if (filter.getExpression2() instanceof PropertyName) {
/* 425 */         name = (PropertyName)filter.getExpression2();
/* 426 */         other = filter.getExpression1();
/*     */       } else {
/* 428 */         return cloneFilter(filter, extraData);
/*     */       } 
/* 431 */       CoordinateReferenceSystem propertyCrs = ReprojectingFilterVisitor.this.findPropertyCRS(name);
/* 434 */       if (propertyCrs == null)
/* 435 */         return cloneFilter(filter, extraData); 
/* 438 */       Expression ex1 = (Expression)name.accept((ExpressionVisitor)ReprojectingFilterVisitor.this, extraData);
/* 439 */       Expression ex2 = ReprojectingFilterVisitor.this.reproject(other, propertyCrs, false);
/* 440 */       if (ex2 == null)
/* 441 */         ex2 = (Expression)other.accept((ExpressionVisitor)ReprojectingFilterVisitor.this, extraData); 
/* 443 */       return cloneFilter(filter, extraData, ex1, ex2);
/*     */     }
/*     */     
/*     */     abstract Object cloneFilter(BinaryComparisonOperator param1BinaryComparisonOperator, Object param1Object);
/*     */     
/*     */     abstract Object cloneFilter(BinaryComparisonOperator param1BinaryComparisonOperator, Object param1Object, Expression param1Expression1, Expression param1Expression2);
/*     */   }
/*     */   
/*     */   protected class FunctionReprojector implements Function {
/*     */     private final CoordinateReferenceSystem propertyCrs;
/*     */     
/*     */     private final Function delegate;
/*     */     
/*     */     protected FunctionReprojector(CoordinateReferenceSystem propertyCrs, Function delegate) {
/* 481 */       this.propertyCrs = propertyCrs;
/* 482 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 486 */       return this.delegate.getName();
/*     */     }
/*     */     
/*     */     public List<Expression> getParameters() {
/* 490 */       return this.delegate.getParameters();
/*     */     }
/*     */     
/*     */     public Object accept(ExpressionVisitor visitor, Object extraData) {
/* 494 */       return this.delegate.accept(visitor, extraData);
/*     */     }
/*     */     
/*     */     public Object evaluate(Object object) {
/* 498 */       Object value = this.delegate.evaluate(object);
/* 499 */       return ReprojectingFilterVisitor.this.reproject(value, this.propertyCrs);
/*     */     }
/*     */     
/*     */     public <T> T evaluate(Object object, Class<T> context) {
/* 503 */       T value = (T)this.delegate.evaluate(object, context);
/* 504 */       return (T)ReprojectingFilterVisitor.this.reproject(value, this.propertyCrs);
/*     */     }
/*     */     
/*     */     public Literal getFallbackValue() {
/* 508 */       return null;
/*     */     }
/*     */     
/*     */     public FunctionName getFunctionName() {
/* 512 */       return this.delegate.getFunctionName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\ReprojectingFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */