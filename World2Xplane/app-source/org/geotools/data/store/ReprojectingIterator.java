/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.GeometryCoordinateSequenceTransformer;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.OperationNotFoundException;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectingIterator implements Iterator<SimpleFeature> {
/*     */   Iterator<SimpleFeature> delegate;
/*     */   
/*     */   CoordinateReferenceSystem target;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*     */   GeometryCoordinateSequenceTransformer tx;
/*     */   
/*     */   public ReprojectingIterator(Iterator<SimpleFeature> delegate, MathTransform transform, SimpleFeatureType schema, GeometryCoordinateSequenceTransformer transformer) throws OperationNotFoundException, FactoryRegistryException, FactoryException {
/*  70 */     this.delegate = delegate;
/*  71 */     this.schema = schema;
/*  73 */     this.tx = transformer;
/*  74 */     this.tx.setMathTransform(transform);
/*     */   }
/*     */   
/*     */   public ReprojectingIterator(Iterator<SimpleFeature> delegate, CoordinateReferenceSystem source, CoordinateReferenceSystem target, SimpleFeatureType schema, GeometryCoordinateSequenceTransformer transformer) throws OperationNotFoundException, FactoryRegistryException, FactoryException {
/*  81 */     this.delegate = delegate;
/*  82 */     this.target = target;
/*  83 */     this.schema = schema;
/*  84 */     this.tx = transformer;
/*  86 */     MathTransform transform = ReferencingFactoryFinder.getCoordinateOperationFactory(null).createOperation(source, target).getMathTransform();
/*  88 */     this.tx.setMathTransform(transform);
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> getDelegate() {
/*  92 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public void remove() {
/*  96 */     this.delegate.remove();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 100 */     return this.delegate.hasNext();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() {
/* 104 */     SimpleFeature feature = this.delegate.next();
/*     */     try {
/* 106 */       return reproject(feature);
/* 107 */     } catch (IOException e) {
/* 108 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   SimpleFeature reproject(SimpleFeature feature) throws IOException {
/* 114 */     List<Object> attributes = feature.getAttributes();
/* 116 */     for (int i = 0; i < attributes.size(); i++) {
/* 117 */       Object object = attributes.get(i);
/* 118 */       if (object instanceof Geometry) {
/* 120 */         Geometry geometry = (Geometry)object;
/*     */         try {
/* 122 */           attributes.set(i, this.tx.transform(geometry));
/* 123 */         } catch (TransformException e) {
/* 124 */           String msg = "Error occured transforming " + geometry.toString();
/* 126 */           throw (IOException)(new IOException(msg)).initCause(e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     try {
/* 132 */       return SimpleFeatureBuilder.build(this.schema, attributes, feature.getID());
/* 133 */     } catch (IllegalAttributeException e) {
/* 134 */       String msg = "Error creating reprojeced feature";
/* 135 */       throw (IOException)(new IOException(msg)).initCause(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReprojectingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */