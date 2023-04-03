/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class ForceCoordinateSystemIterator implements SimpleFeatureIterator, Iterator<SimpleFeature> {
/*     */   protected FeatureIterator<SimpleFeature> reader;
/*     */   
/*     */   protected SimpleFeatureBuilder builder;
/*     */   
/*     */   ForceCoordinateSystemIterator(FeatureIterator<SimpleFeature> reader, SimpleFeatureType schema) {
/*  77 */     this.reader = reader;
/*  78 */     this.builder = new SimpleFeatureBuilder(schema);
/*     */   }
/*     */   
/*     */   public ForceCoordinateSystemIterator(FeatureIterator<SimpleFeature> reader, SimpleFeatureType type, CoordinateReferenceSystem cs) throws SchemaException {
/*  93 */     if (cs == null)
/*  94 */       throw new NullPointerException("CoordinateSystem required"); 
/*  96 */     CoordinateReferenceSystem originalCs = type.getGeometryDescriptor().getCoordinateReferenceSystem();
/*  99 */     if (!cs.equals(originalCs))
/* 100 */       type = FeatureTypes.transform(type, cs); 
/* 102 */     this.builder = new SimpleFeatureBuilder(type);
/* 104 */     this.reader = reader;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 111 */     if (this.reader == null || this.builder == null)
/* 112 */       throw new IllegalStateException("Reader has already been closed"); 
/* 114 */     return this.builder.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws NoSuchElementException {
/* 122 */     if (this.reader == null)
/* 123 */       throw new IllegalStateException("Reader has already been closed"); 
/* 126 */     SimpleFeature next = (SimpleFeature)this.reader.next();
/* 127 */     if (this.builder == null)
/* 128 */       return next; 
/*     */     try {
/* 131 */       return SimpleFeatureBuilder.retype(next, this.builder);
/* 132 */     } catch (IllegalAttributeException eep) {
/* 133 */       throw (IllegalStateException)(new IllegalStateException(eep.getMessage())).initCause(eep);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 141 */     if (this.reader == null)
/* 142 */       throw new IllegalStateException("Reader has already been closed"); 
/* 145 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 149 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void close() {
/* 155 */     if (this.reader == null)
/*     */       return; 
/* 158 */     this.reader.close();
/* 159 */     this.reader = null;
/* 160 */     this.builder = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ForceCoordinateSystemIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */