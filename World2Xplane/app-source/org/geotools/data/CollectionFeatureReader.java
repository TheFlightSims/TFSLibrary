/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureCollection;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.collection.DelegateSimpleFeatureIterator;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class CollectionFeatureReader implements FeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   private SimpleFeatureIterator features;
/*     */   
/*     */   private SimpleFeatureType type;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   public CollectionFeatureReader(Collection<SimpleFeature> featuresArg, SimpleFeatureType typeArg) {
/*  54 */     assert !featuresArg.isEmpty();
/*  55 */     this.features = (SimpleFeatureIterator)new DelegateSimpleFeatureIterator(featuresArg.iterator());
/*  56 */     this.type = typeArg;
/*     */   }
/*     */   
/*     */   public CollectionFeatureReader(SimpleFeatureCollection featuresArg, SimpleFeatureType typeArg) {
/*  67 */     assert !featuresArg.isEmpty();
/*  68 */     this.features = featuresArg.features();
/*  69 */     this.type = typeArg;
/*     */   }
/*     */   
/*     */   public CollectionFeatureReader(SimpleFeature[] featuresArg) {
/*  78 */     assert featuresArg.length > 0;
/*  79 */     Iterator<SimpleFeature> iterator = Arrays.<SimpleFeature>asList(featuresArg).iterator();
/*  80 */     this.features = (SimpleFeatureIterator)new DelegateSimpleFeatureIterator(iterator);
/*  81 */     this.type = featuresArg[0].getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  88 */     return this.type;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/*  96 */     if (this.closed)
/*  97 */       throw new NoSuchElementException("Reader has been closed"); 
/* 100 */     return (SimpleFeature)this.features.next();
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 107 */     return (this.features != null && this.features.hasNext() && !this.closed);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 114 */     this.closed = true;
/* 116 */     if (this.features != null) {
/* 117 */       this.features.close();
/* 118 */       this.features = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\CollectionFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */