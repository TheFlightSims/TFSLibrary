/*     */ package org.geotools.feature;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeImpl;
/*     */ import org.geotools.geometry.jts.JTS;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.BinaryComparisonOperator;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class FeatureTypes {
/*     */   public static final URI DEFAULT_NAMESPACE;
/*     */   
/*     */   public static final SimpleFeatureType ABSTRACT_FEATURE_TYPE;
/*     */   
/*     */   static {
/*     */     URI uRI;
/*     */     try {
/*  81 */       uRI = new URI("http://www.opengis.net/gml");
/*  83 */     } catch (URISyntaxException e) {
/*  84 */       uRI = null;
/*     */     } 
/*  86 */     DEFAULT_NAMESPACE = uRI;
/*  92 */     SimpleFeatureType featureType = null;
/*     */     try {
/*  94 */       featureType = newFeatureType(null, "Feature", new URI("http://www.opengis.net/gml"), true);
/*  96 */     } catch (Exception e) {}
/*  99 */     ABSTRACT_FEATURE_TYPE = featureType;
/*     */   }
/*     */   
/* 103 */   public static final NameImpl DEFAULT_TYPENAME = new NameImpl("AbstractFeatureCollectionType", DEFAULT_NAMESPACE.toString());
/*     */   
/*     */   public static final int ANY_LENGTH = -1;
/*     */   
/* 110 */   public static final SimpleFeatureType EMPTY = (SimpleFeatureType)new SimpleFeatureTypeImpl(new NameImpl("Empty"), Collections.EMPTY_LIST, null, false, Collections.EMPTY_LIST, null, null);
/*     */   
/*     */   public static int getFieldLength(PropertyDescriptor descriptor) {
/* 127 */     PropertyType type = descriptor.getType();
/* 128 */     Integer length = null;
/* 129 */     while (type != null) {
/* 133 */       for (Filter f : type.getRestrictions()) {
/* 134 */         Integer filterLength = null;
/*     */         try {
/* 136 */           if (f == null)
/*     */             continue; 
/* 139 */           if (f instanceof org.opengis.filter.PropertyIsLessThan) {
/* 140 */             BinaryComparisonOperator cf = (BinaryComparisonOperator)f;
/* 141 */             if (cf.getExpression1() instanceof org.geotools.filter.LengthFunction)
/* 142 */               filterLength = Integer.valueOf(((Integer)cf.getExpression2().evaluate(null, Integer.class)).intValue() - 1); 
/* 144 */           } else if (f instanceof org.opengis.filter.PropertyIsLessThanOrEqualTo) {
/* 145 */             BinaryComparisonOperator cf = (BinaryComparisonOperator)f;
/* 146 */             if (cf.getExpression1() instanceof org.geotools.filter.LengthFunction)
/* 147 */               filterLength = (Integer)cf.getExpression2().evaluate(null, Integer.class); 
/* 149 */           } else if (f instanceof org.opengis.filter.PropertyIsGreaterThan) {
/* 150 */             BinaryComparisonOperator cf = (BinaryComparisonOperator)f;
/* 151 */             if (cf.getExpression2() instanceof org.geotools.filter.LengthFunction)
/* 152 */               filterLength = Integer.valueOf(((Integer)cf.getExpression1().evaluate(null, Integer.class)).intValue() - 1); 
/* 154 */           } else if (f instanceof org.opengis.filter.PropertyIsGreaterThanOrEqualTo) {
/* 155 */             BinaryComparisonOperator cf = (BinaryComparisonOperator)f;
/* 156 */             if (cf.getExpression2() instanceof org.geotools.filter.LengthFunction)
/* 157 */               filterLength = (Integer)cf.getExpression1().evaluate(null, Integer.class); 
/*     */           } 
/* 160 */         } catch (NullPointerException e) {}
/* 164 */         if (filterLength != null && (
/* 165 */           length == null || filterLength.intValue() < length.intValue()))
/* 166 */           length = filterLength; 
/*     */       } 
/* 170 */       type = type.getSuper();
/*     */     } 
/* 173 */     return (length != null) ? length.intValue() : -1;
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType transform(SimpleFeatureType schema, CoordinateReferenceSystem crs) throws SchemaException {
/* 185 */     return transform(schema, crs, false);
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType transform(SimpleFeatureType schema, CoordinateReferenceSystem crs, boolean forceOnlyMissing) throws SchemaException {
/* 199 */     SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 200 */     tb.setName(schema.getTypeName());
/* 201 */     tb.setNamespaceURI(schema.getName().getNamespaceURI());
/* 202 */     tb.setAbstract(schema.isAbstract());
/* 204 */     GeometryDescriptor defaultGeometryType = null;
/* 205 */     for (int i = 0; i < schema.getAttributeCount(); i++) {
/* 206 */       AttributeDescriptor attributeType = schema.getDescriptor(i);
/* 207 */       if (attributeType instanceof GeometryDescriptor) {
/* 208 */         GeometryDescriptor geometryType = (GeometryDescriptor)attributeType;
/* 211 */         tb.descriptor((AttributeDescriptor)geometryType);
/* 212 */         if (!forceOnlyMissing || geometryType.getCoordinateReferenceSystem() == null)
/* 213 */           tb.crs(crs); 
/* 216 */         tb.add(geometryType.getLocalName(), geometryType.getType().getBinding());
/*     */       } else {
/* 218 */         tb.add(attributeType);
/*     */       } 
/*     */     } 
/* 221 */     if (schema.getGeometryDescriptor() != null)
/* 222 */       tb.setDefaultGeometry(schema.getGeometryDescriptor().getLocalName()); 
/* 225 */     tb.setSuperType((SimpleFeatureType)schema.getSuper());
/* 227 */     return tb.buildFeatureType();
/*     */   }
/*     */   
/*     */   public static SimpleFeature transform(SimpleFeature feature, SimpleFeatureType schema, MathTransform transform) throws MismatchedDimensionException, TransformException, IllegalAttributeException {
/* 243 */     feature = SimpleFeatureBuilder.copy(feature);
/* 245 */     GeometryDescriptor geomType = schema.getGeometryDescriptor();
/* 246 */     Geometry geom = (Geometry)feature.getAttribute(geomType.getLocalName());
/* 248 */     geom = JTS.transform(geom, transform);
/* 250 */     feature.setAttribute(geomType.getLocalName(), geom);
/* 252 */     return feature;
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name, URI ns, boolean isAbstract, SimpleFeatureType[] superTypes) throws FactoryRegistryException, SchemaException {
/* 271 */     return newFeatureType(types, name, ns, isAbstract, superTypes, (GeometryDescriptor)null);
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name, URI ns, boolean isAbstract, SimpleFeatureType[] superTypes, AttributeDescriptor defaultGeometry) throws FactoryRegistryException, SchemaException {
/* 290 */     SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 292 */     tb.setName(name);
/* 293 */     tb.setNamespaceURI(ns);
/* 294 */     tb.setAbstract(isAbstract);
/* 295 */     if (types != null)
/* 296 */       tb.addAll(types); 
/* 299 */     if (defaultGeometry != null) {
/* 301 */       boolean add = true;
/* 302 */       for (int i = 0; i < types.length; i++) {
/* 303 */         if (types[i] == defaultGeometry) {
/* 304 */           add = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 308 */       if (add)
/* 309 */         tb.add(defaultGeometry); 
/* 311 */       tb.setDefaultGeometry(defaultGeometry.getLocalName());
/*     */     } 
/* 313 */     if (superTypes != null && superTypes.length > 0) {
/* 314 */       if (superTypes.length > 1)
/* 315 */         throw new SchemaException("Can only specify a single super type"); 
/* 317 */       tb.setSuperType(superTypes[0]);
/*     */     } else {
/* 322 */       tb.setSuperType(ABSTRACT_FEATURE_TYPE);
/*     */     } 
/* 324 */     return tb.buildFeatureType();
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name, URI ns, boolean isAbstract, SimpleFeatureType[] superTypes, GeometryDescriptor defaultGeometry) throws FactoryRegistryException, SchemaException {
/* 343 */     return newFeatureType(types, name, ns, isAbstract, superTypes, (AttributeDescriptor)defaultGeometry);
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name, URI ns, boolean isAbstract) throws FactoryRegistryException, SchemaException {
/* 360 */     return newFeatureType(types, name, ns, isAbstract, null);
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name, URI ns) throws FactoryRegistryException, SchemaException {
/* 376 */     return newFeatureType(types, name, ns, false);
/*     */   }
/*     */   
/*     */   public static SimpleFeatureType newFeatureType(AttributeDescriptor[] types, String name) throws FactoryRegistryException, SchemaException {
/* 392 */     return newFeatureType(types, name, DEFAULT_NAMESPACE, false);
/*     */   }
/*     */   
/*     */   public static List<FeatureType> getAncestors(FeatureType featureType) {
/* 401 */     List<FeatureType> ancestors = new ArrayList<FeatureType>();
/* 402 */     while (featureType.getSuper() instanceof FeatureType) {
/* 403 */       FeatureType superType = (FeatureType)featureType.getSuper();
/* 404 */       ancestors.add(superType);
/* 405 */       featureType = superType;
/*     */     } 
/* 407 */     return ancestors;
/*     */   }
/*     */   
/*     */   public static boolean isDecendedFrom(FeatureType featureType, URI namespace, String typeName) {
/* 432 */     if (featureType == null)
/* 433 */       return false; 
/* 434 */     List<FeatureType> ancestors = getAncestors(featureType);
/* 435 */     for (FeatureType superType : ancestors) {
/* 436 */       if (namespace == null) {
/* 438 */         if (Utilities.equals(superType.getName().getLocalPart(), typeName))
/* 439 */           return true; 
/*     */         continue;
/*     */       } 
/* 442 */       if (Utilities.equals(superType.getName().getNamespaceURI(), namespace.toString()) && Utilities.equals(superType.getName().getLocalPart(), typeName))
/* 444 */         return true; 
/*     */     } 
/* 448 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isDecendedFrom(FeatureType featureType, FeatureType isParentType) {
/*     */     try {
/* 453 */       return isDecendedFrom(featureType, new URI(isParentType.getName().getNamespaceURI()), isParentType.getName().getLocalPart());
/* 455 */     } catch (URISyntaxException e) {
/* 456 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean equals(SimpleFeatureType typeA, SimpleFeatureType typeB) {
/* 462 */     return equals(typeA, typeB, false);
/*     */   }
/*     */   
/*     */   public static boolean equalsExact(SimpleFeatureType typeA, SimpleFeatureType typeB) {
/* 467 */     return equals(typeA, typeB, true);
/*     */   }
/*     */   
/*     */   static boolean equals(SimpleFeatureType typeA, SimpleFeatureType typeB, boolean compareUserMaps) {
/* 472 */     if (typeA == typeB)
/* 473 */       return true; 
/* 475 */     if (typeA == null || typeB == null)
/* 476 */       return false; 
/* 479 */     if (compareUserMaps && 
/* 480 */       !equals(typeA.getUserData(), typeB.getUserData()))
/* 481 */       return false; 
/* 484 */     return (equalsId(typeA, typeB) && equals(typeA.getAttributeDescriptors(), typeB.getAttributeDescriptors(), compareUserMaps) && equalsAncestors(typeA, typeB));
/*     */   }
/*     */   
/*     */   static boolean equals(List attributesA, List attributesB, boolean compareUserMaps) {
/* 490 */     return equals((AttributeDescriptor[])attributesA.toArray((Object[])new AttributeDescriptor[attributesA.size()]), (AttributeDescriptor[])attributesB.toArray((Object[])new AttributeDescriptor[attributesB.size()]), compareUserMaps);
/*     */   }
/*     */   
/*     */   public static boolean equals(List attributesA, List attributesB) {
/* 497 */     return equals(attributesA, attributesB, false);
/*     */   }
/*     */   
/*     */   public static boolean equalsExact(List attributesA, List attributesB) {
/* 501 */     return equals(attributesA, attributesB, true);
/*     */   }
/*     */   
/*     */   public static boolean equals(AttributeDescriptor[] attributesA, AttributeDescriptor[] attributesB) {
/* 505 */     return equals(attributesA, attributesB, false);
/*     */   }
/*     */   
/*     */   public static boolean equalsExact(AttributeDescriptor[] attributesA, AttributeDescriptor[] attributesB) {
/* 509 */     return equals(attributesA, attributesB, true);
/*     */   }
/*     */   
/*     */   static boolean equals(AttributeDescriptor[] attributesA, AttributeDescriptor[] attributesB, boolean compareUserMaps) {
/* 513 */     if (attributesA.length != attributesB.length)
/* 514 */       return false; 
/* 516 */     for (int i = 0, length = attributesA.length; i < length; i++) {
/* 517 */       if (!equals(attributesA[i], attributesB[i], compareUserMaps))
/* 518 */         return false; 
/*     */     } 
/* 520 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equalsAncestors(SimpleFeatureType typeA, SimpleFeatureType typeB) {
/* 532 */     return ancestors(typeA).equals(ancestors(typeB));
/*     */   }
/*     */   
/*     */   public static Set ancestors(SimpleFeatureType featureType) {
/* 536 */     if (featureType == null || getAncestors((FeatureType)featureType).isEmpty())
/* 537 */       return Collections.EMPTY_SET; 
/* 539 */     return new HashSet<FeatureType>(getAncestors((FeatureType)featureType));
/*     */   }
/*     */   
/*     */   public static boolean equals(AttributeDescriptor a, AttributeDescriptor b) {
/* 543 */     return equals(a, b, false);
/*     */   }
/*     */   
/*     */   public static boolean equalsExact(AttributeDescriptor a, AttributeDescriptor b) {
/* 547 */     return equals(a, b, true);
/*     */   }
/*     */   
/*     */   static boolean equals(AttributeDescriptor a, AttributeDescriptor b, boolean compareUserMaps) {
/* 551 */     if (a == b)
/* 552 */       return true; 
/* 554 */     if (a == null)
/* 555 */       return true; 
/* 557 */     if (!a.equals(b))
/* 558 */       return false; 
/* 560 */     if (compareUserMaps) {
/* 561 */       if (!equals(a.getUserData(), b.getUserData()))
/* 562 */         return false; 
/* 563 */       if (!equals(a.getType().getUserData(), b.getType().getUserData()))
/* 564 */         return false; 
/*     */     } 
/* 567 */     return true;
/*     */   }
/*     */   
/*     */   static boolean equals(Map a, Map b) {
/* 577 */     if (a == b)
/* 578 */       return true; 
/* 581 */     if (a == null || b == null)
/* 582 */       return false; 
/* 584 */     if (a != null && a.size() == 0 && b == null)
/* 585 */       return true; 
/* 587 */     if (b != null && b.size() == 0 && a == null)
/* 588 */       return true; 
/* 590 */     return a.equals(b);
/*     */   }
/*     */   
/*     */   public static boolean equalsId(SimpleFeatureType typeA, SimpleFeatureType typeB) {
/* 595 */     if (typeA == typeB)
/* 596 */       return true; 
/* 598 */     if (typeA == null || typeB == null)
/* 599 */       return false; 
/* 602 */     String typeNameA = typeA.getTypeName();
/* 603 */     String typeNameB = typeB.getTypeName();
/* 604 */     if (typeNameA == null && typeNameB != null)
/* 605 */       return false; 
/* 606 */     if (!typeNameA.equals(typeNameB))
/* 607 */       return false; 
/* 609 */     String namespaceA = typeA.getName().getNamespaceURI();
/* 610 */     String namespaceB = typeB.getName().getNamespaceURI();
/* 611 */     if (namespaceA == null && namespaceB == null)
/* 612 */       return true; 
/* 614 */     if (namespaceA == null && namespaceB != null)
/* 615 */       return false; 
/* 616 */     if (!namespaceA.equals(namespaceB))
/* 617 */       return false; 
/* 619 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */