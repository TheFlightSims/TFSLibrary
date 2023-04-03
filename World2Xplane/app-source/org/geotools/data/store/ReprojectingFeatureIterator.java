/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.GeometryCoordinateSequenceTransformer;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectingFeatureIterator implements SimpleFeatureIterator {
/*     */   SimpleFeatureIterator delegate;
/*     */   
/*     */   CoordinateReferenceSystem target;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*     */   GeometryCoordinateSequenceTransformer tx;
/*     */   
/*     */   public ReprojectingFeatureIterator(SimpleFeatureIterator delegate, MathTransform transform, SimpleFeatureType schema, GeometryCoordinateSequenceTransformer transformer) throws OperationNotFoundException, FactoryRegistryException, FactoryException {
/*  70 */     this.delegate = delegate;
/*  72 */     this.schema = schema;
/*  74 */     this.tx = transformer;
/*  75 */     this.tx.setMathTransform(transform);
/*     */   }
/*     */   
/*     */   public ReprojectingFeatureIterator(SimpleFeatureIterator delegate, CoordinateReferenceSystem source, CoordinateReferenceSystem target, SimpleFeatureType schema, GeometryCoordinateSequenceTransformer transformer) throws OperationNotFoundException, FactoryRegistryException, FactoryException {
/*  82 */     this.delegate = delegate;
/*  83 */     this.target = target;
/*  84 */     this.schema = schema;
/*  85 */     this.tx = transformer;
/*  87 */     MathTransform transform = ReferencingFactoryFinder.getCoordinateOperationFactory(null).createOperation(source, target).getMathTransform();
/*  89 */     this.tx.setMathTransform(transform);
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator getDelegate() {
/*  93 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  97 */     return this.delegate.hasNext();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() {
/* 101 */     SimpleFeature feature = (SimpleFeature)this.delegate.next();
/*     */     try {
/* 103 */       return reproject(feature);
/* 104 */     } catch (IOException e) {
/* 105 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   SimpleFeature reproject(SimpleFeature feature) throws IOException {
/* 111 */     List<Object> attributes = feature.getAttributes();
/* 113 */     for (int i = 0; i < attributes.size(); i++) {
/* 114 */       Object object = attributes.get(i);
/* 115 */       if (object instanceof Geometry) {
/* 117 */         Geometry geometry = (Geometry)object;
/*     */         try {
/* 119 */           attributes.set(i, this.tx.transform(geometry));
/* 120 */         } catch (TransformException e) {
/* 121 */           String msg = "Error occured transforming " + geometry.toString();
/* 123 */           throw (IOException)(new IOException(msg)).initCause(e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     try {
/* 129 */       return SimpleFeatureBuilder.build(this.schema, attributes, feature.getID());
/* 130 */     } catch (IllegalAttributeException e) {
/* 131 */       String msg = "Error creating reprojeced feature";
/* 132 */       throw (IOException)(new IOException(msg)).initCause(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 138 */     this.delegate.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReprojectingFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */