/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.collection.DelegateFeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.collection.DecoratingSimpleFeatureCollection;
/*     */ import org.geotools.feature.visitor.FeatureAttributeVisitor;
/*     */ import org.geotools.filter.FilterAttributeExtractor;
/*     */ import org.geotools.filter.spatial.DefaultCRSFilterVisitor;
/*     */ import org.geotools.filter.spatial.ReprojectingFilterVisitor;
/*     */ import org.geotools.geometry.jts.GeometryCoordinateSequenceTransformer;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.opengis.feature.FeatureVisitor;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.ExpressionVisitor;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class ReprojectingFeatureCollection extends DecoratingSimpleFeatureCollection {
/*  67 */   static final FilterFactory2 FF = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/*     */   MathTransform transform;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*     */   CoordinateReferenceSystem target;
/*     */   
/*     */   GeometryCoordinateSequenceTransformer transformer;
/*     */   
/*     */   public ReprojectingFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate, CoordinateReferenceSystem target) {
/*  92 */     this(DataUtilities.simple(delegate), target);
/*     */   }
/*     */   
/*     */   public ReprojectingFeatureCollection(SimpleFeatureCollection delegate, CoordinateReferenceSystem target) {
/*  96 */     this(delegate, ((SimpleFeatureType)delegate.getSchema()).getGeometryDescriptor().getCoordinateReferenceSystem(), target);
/*     */   }
/*     */   
/*     */   public ReprojectingFeatureCollection(FeatureCollection<SimpleFeatureType, SimpleFeature> delegate, CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
/* 101 */     this(DataUtilities.simple(delegate), source, target);
/*     */   }
/*     */   
/*     */   public ReprojectingFeatureCollection(SimpleFeatureCollection delegate, CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
/* 107 */     super(delegate);
/* 108 */     this.target = target;
/* 109 */     SimpleFeatureType schema = (SimpleFeatureType)delegate.getSchema();
/* 110 */     this.schema = reType(schema, target);
/* 112 */     if (source == null)
/* 113 */       throw new NullPointerException("source crs"); 
/* 115 */     if (target == null)
/* 116 */       throw new NullPointerException("destination crs"); 
/* 119 */     this.transform = transform(source, target);
/* 120 */     this.transformer = new GeometryCoordinateSequenceTransformer();
/*     */   }
/*     */   
/*     */   public void setTransformer(GeometryCoordinateSequenceTransformer transformer) {
/* 124 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   private MathTransform transform(CoordinateReferenceSystem source, CoordinateReferenceSystem target) {
/*     */     try {
/* 130 */       return CRS.findMathTransform(source, target, true);
/* 131 */     } catch (FactoryException e) {
/* 132 */       throw new IllegalArgumentException("Could not create math transform", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private SimpleFeatureType reType(SimpleFeatureType type, CoordinateReferenceSystem target) {
/*     */     try {
/* 140 */       return FeatureTypes.transform(type, target);
/* 141 */     } catch (SchemaException e) {
/* 142 */       throw new IllegalArgumentException("Could not transform source schema", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> reader() throws IOException {
/* 148 */     return (FeatureReader<SimpleFeatureType, SimpleFeature>)new DelegateFeatureReader((FeatureType)getSchema(), (FeatureIterator)features());
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator features() {
/*     */     try {
/* 153 */       return new ReprojectingFeatureIterator(this.delegate.features(), this.transform, this.schema, this.transformer);
/* 154 */     } catch (Exception e) {
/* 155 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema() {
/* 160 */     return this.schema;
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection subCollection(Filter filter) {
/* 165 */     CoordinateReferenceSystem crs = getSchema().getCoordinateReferenceSystem();
/* 166 */     CoordinateReferenceSystem crsDelegate = ((SimpleFeatureType)this.delegate.getSchema()).getCoordinateReferenceSystem();
/* 167 */     if (crs != null) {
/* 168 */       DefaultCRSFilterVisitor defaulter = new DefaultCRSFilterVisitor(FF, crs);
/* 169 */       filter = (Filter)filter.accept((FilterVisitor)defaulter, null);
/* 170 */       if (crsDelegate != null && !CRS.equalsIgnoreMetadata(crs, crsDelegate)) {
/* 171 */         ReprojectingFilterVisitor reprojector = new ReprojectingFilterVisitor(FF, this.delegate.getSchema());
/* 172 */         filter = (Filter)filter.accept((FilterVisitor)reprojector, null);
/*     */       } 
/*     */     } 
/* 176 */     return (SimpleFeatureCollection)new ReprojectingFeatureCollection(this.delegate.subCollection(filter), this.target);
/*     */   }
/*     */   
/*     */   public SimpleFeatureCollection sort(SortBy order) {
/* 181 */     throw new UnsupportedOperationException("Not yet");
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 185 */     return toArray(new Object[size()]);
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 190 */     List<T> list = new ArrayList<T>();
/* 191 */     SimpleFeatureIterator i = features();
/*     */     try {
/* 193 */       while (i.hasNext())
/* 194 */         list.add((T)i.next()); 
/* 196 */       return (T[])list.toArray((Object[])a);
/*     */     } finally {
/* 198 */       i.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature o) {
/* 204 */     throw new UnsupportedOperationException("Not yet");
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 219 */     SimpleFeatureIterator r = features();
/*     */     try {
/* 221 */       Envelope newBBox = new Envelope();
/* 225 */       while (r.hasNext()) {
/* 226 */         SimpleFeature feature = (SimpleFeature)r.next();
/* 227 */         Geometry geom = (Geometry)feature.getDefaultGeometry();
/* 228 */         if (geom != null) {
/* 229 */           Envelope internal = geom.getEnvelopeInternal();
/* 230 */           newBBox.expandToInclude(internal);
/*     */         } 
/*     */       } 
/* 233 */       return new ReferencedEnvelope(newBBox, this.target);
/* 234 */     } catch (Exception e) {
/* 235 */       throw new RuntimeException("Exception occurred while computing reprojected bounds", e);
/*     */     } finally {
/* 238 */       r.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean canDelegate(FeatureVisitor visitor) {
/* 244 */     if (visitor instanceof FeatureAttributeVisitor) {
/* 246 */       FilterAttributeExtractor extractor = new FilterAttributeExtractor(this.schema);
/* 247 */       for (Expression e : ((FeatureAttributeVisitor)visitor).getExpressions())
/* 248 */         e.accept((ExpressionVisitor)extractor, null); 
/* 251 */       for (PropertyName pname : extractor.getPropertyNameSet()) {
/* 252 */         AttributeDescriptor att = (AttributeDescriptor)pname.evaluate(this.schema);
/* 253 */         if (att instanceof org.opengis.feature.type.GeometryDescriptor)
/* 254 */           return false; 
/*     */       } 
/* 257 */       return true;
/*     */     } 
/* 259 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReprojectingFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */