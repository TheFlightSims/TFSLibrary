/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.GeometryCoordinateSequenceTransformer;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ReprojectFeatureIterator implements Iterator<SimpleFeature>, SimpleFeatureIterator {
/*     */   FeatureIterator<SimpleFeature> reader;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*  74 */   GeometryCoordinateSequenceTransformer transformer = new GeometryCoordinateSequenceTransformer();
/*     */   
/*     */   public ReprojectFeatureIterator(FeatureIterator<SimpleFeature> reader, SimpleFeatureType schema, MathTransform transform) {
/*  78 */     this.reader = reader;
/*  79 */     this.schema = schema;
/*  80 */     this.transformer.setMathTransform(transform);
/*  83 */     this.transformer.setCoordinateReferenceSystem(schema.getCoordinateReferenceSystem());
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  99 */     if (this.schema == null)
/* 100 */       throw new IllegalStateException("Reader has already been closed"); 
/* 103 */     return this.schema;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws NoSuchElementException {
/* 124 */     if (this.reader == null)
/* 125 */       throw new IllegalStateException("Reader has already been closed"); 
/* 129 */     SimpleFeature next = (SimpleFeature)this.reader.next();
/* 131 */     Object[] attributes = next.getAttributes().toArray();
/*     */     try {
/* 134 */       for (int i = 0; i < attributes.length; i++) {
/* 135 */         if (attributes[i] instanceof Geometry)
/* 136 */           attributes[i] = this.transformer.transform((Geometry)attributes[i]); 
/*     */       } 
/* 139 */     } catch (TransformException e) {
/* 140 */       throw (IllegalStateException)(new IllegalStateException("A transformation exception occurred while reprojecting data on the fly")).initCause(e);
/*     */     } 
/* 143 */     return SimpleFeatureBuilder.build(this.schema, attributes, next.getID());
/*     */   }
/*     */   
/*     */   public void remove() {
/* 147 */     throw new UnsupportedOperationException("On the fly reprojection disables remove");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 163 */     if (this.reader == null)
/* 164 */       throw new IllegalStateException("Reader has already been closed"); 
/* 167 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public void close() {
/* 183 */     if (this.reader == null)
/*     */       return; 
/* 186 */     this.reader.close();
/* 187 */     this.reader = null;
/* 188 */     this.schema = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ReprojectFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */