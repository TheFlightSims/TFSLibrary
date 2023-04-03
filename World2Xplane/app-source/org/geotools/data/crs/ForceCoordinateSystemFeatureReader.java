/*     */ package org.geotools.data.crs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class ForceCoordinateSystemFeatureReader implements FeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*     */   
/*     */   protected SimpleFeatureBuilder builder;
/*     */   
/*     */   ForceCoordinateSystemFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeatureType schema) {
/*  76 */     this.reader = reader;
/*  77 */     this.builder = new SimpleFeatureBuilder(schema);
/*     */   }
/*     */   
/*     */   public ForceCoordinateSystemFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, CoordinateReferenceSystem cs) throws SchemaException {
/*  92 */     this(reader, cs, false);
/*     */   }
/*     */   
/*     */   public ForceCoordinateSystemFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, CoordinateReferenceSystem cs, boolean forceOnlyMissing) throws SchemaException {
/* 107 */     if (cs == null)
/* 108 */       throw new NullPointerException("CoordinateSystem required"); 
/* 111 */     SimpleFeatureType type = (SimpleFeatureType)reader.getFeatureType();
/* 112 */     CoordinateReferenceSystem originalCs = type.getCoordinateReferenceSystem();
/* 114 */     if (!cs.equals(originalCs))
/* 115 */       type = FeatureTypes.transform(type, cs, forceOnlyMissing); 
/* 117 */     this.builder = new SimpleFeatureBuilder(type);
/* 119 */     this.reader = reader;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 126 */     if (this.reader == null)
/* 127 */       throw new IllegalStateException("Reader has already been closed"); 
/* 130 */     if (this.builder == null)
/* 131 */       return (SimpleFeatureType)this.reader.getFeatureType(); 
/* 133 */     return this.builder.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 141 */     if (this.reader == null)
/* 142 */       throw new IllegalStateException("Reader has already been closed"); 
/* 145 */     SimpleFeature next = (SimpleFeature)this.reader.next();
/* 146 */     if (this.builder == null)
/* 147 */       return next; 
/* 150 */     return SimpleFeatureBuilder.retype(next, this.builder);
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 157 */     if (this.reader == null)
/* 158 */       throw new IllegalStateException("Reader has already been closed"); 
/* 161 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 168 */     if (this.reader == null)
/* 169 */       throw new IllegalStateException("Reader has already been closed"); 
/* 172 */     this.reader.close();
/* 173 */     this.reader = null;
/* 174 */     this.builder = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\crs\ForceCoordinateSystemFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */