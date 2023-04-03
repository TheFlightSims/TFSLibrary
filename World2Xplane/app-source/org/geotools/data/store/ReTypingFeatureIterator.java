/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ 
/*     */ public class ReTypingFeatureIterator implements SimpleFeatureIterator {
/*     */   SimpleFeatureIterator delegate;
/*     */   
/*     */   SimpleFeatureType target;
/*     */   
/*     */   AttributeDescriptor[] types;
/*     */   
/*     */   SimpleFeatureBuilder builder;
/*     */   
/*     */   public ReTypingFeatureIterator(SimpleFeatureIterator delegate, SimpleFeatureType source, SimpleFeatureType target) {
/*  53 */     this.delegate = delegate;
/*  54 */     this.target = target;
/*  55 */     this.types = typeAttributes(source, target);
/*  56 */     this.builder = new SimpleFeatureBuilder(target);
/*     */   }
/*     */   
/*     */   public SimpleFeatureIterator getDelegate() {
/*  60 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  64 */     return this.delegate.hasNext();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() {
/*  68 */     SimpleFeature next = (SimpleFeature)this.delegate.next();
/*  69 */     String id = next.getID();
/*     */     try {
/*  72 */       for (int i = 0; i < this.types.length; i++) {
/*  73 */         String xpath = this.types[i].getLocalName();
/*  74 */         this.builder.add(next.getAttribute(xpath));
/*     */       } 
/*  77 */       return this.builder.buildFeature(id);
/*  78 */     } catch (IllegalAttributeException e) {
/*  79 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AttributeDescriptor[] typeAttributes(SimpleFeatureType original, SimpleFeatureType target) {
/*  99 */     if (target.equals(original))
/* 100 */       throw new IllegalArgumentException("FeatureReader allready produces contents with the correct schema"); 
/* 104 */     if (target.getAttributeCount() > original.getAttributeCount())
/* 105 */       throw new IllegalArgumentException("Unable to retype  FeatureReader<SimpleFeatureType, SimpleFeature> (origional does not cover requested type)"); 
/* 110 */     AttributeDescriptor[] types = new AttributeDescriptor[target.getAttributeCount()];
/* 112 */     for (int i = 0; i < target.getAttributeCount(); i++) {
/* 113 */       AttributeDescriptor attrib = target.getDescriptor(i);
/* 114 */       String xpath = attrib.getLocalName();
/* 115 */       types[i] = attrib;
/* 117 */       if (!attrib.equals(original.getDescriptor(xpath)))
/* 118 */         throw new IllegalArgumentException("Unable to retype  FeatureReader<SimpleFeatureType, SimpleFeature> (origional does not cover " + xpath + ")"); 
/*     */     } 
/* 124 */     return types;
/*     */   }
/*     */   
/*     */   public void close() {
/* 129 */     this.delegate.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReTypingFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */