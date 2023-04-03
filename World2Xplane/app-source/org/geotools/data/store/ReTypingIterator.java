/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ 
/*     */ public class ReTypingIterator implements Iterator<SimpleFeature> {
/*     */   Iterator<SimpleFeature> delegate;
/*     */   
/*     */   SimpleFeatureType target;
/*     */   
/*     */   AttributeDescriptor[] types;
/*     */   
/*     */   SimpleFeatureBuilder builder;
/*     */   
/*     */   public ReTypingIterator(Iterator<SimpleFeature> delegate, SimpleFeatureType source, SimpleFeatureType target) {
/*  58 */     this.delegate = delegate;
/*  59 */     this.target = target;
/*  60 */     this.types = typeAttributes(source, target);
/*  61 */     this.builder = new SimpleFeatureBuilder(target);
/*     */   }
/*     */   
/*     */   public Iterator<SimpleFeature> getDelegate() {
/*  65 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public void remove() {
/*  69 */     this.delegate.remove();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  73 */     return this.delegate.hasNext();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() {
/*  77 */     SimpleFeature next = this.delegate.next();
/*  78 */     String id = next.getID();
/*     */     try {
/*  81 */       for (int i = 0; i < this.types.length; i++) {
/*  82 */         String xpath = this.types[i].getLocalName();
/*  83 */         this.builder.add(next.getAttribute(xpath));
/*     */       } 
/*  86 */       return this.builder.buildFeature(id);
/*  88 */     } catch (IllegalAttributeException e) {
/*  89 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AttributeDescriptor[] typeAttributes(SimpleFeatureType original, SimpleFeatureType target) {
/* 109 */     if (target.equals(original))
/* 110 */       throw new IllegalArgumentException("FeatureReader allready produces contents with the correct schema"); 
/* 114 */     if (target.getAttributeCount() > original.getAttributeCount())
/* 115 */       throw new IllegalArgumentException("Unable to retype  FeatureReader<SimpleFeatureType, SimpleFeature> (origional does not cover requested type)"); 
/* 120 */     AttributeDescriptor[] types = new AttributeDescriptor[target.getAttributeCount()];
/* 122 */     for (int i = 0; i < target.getAttributeCount(); i++) {
/* 123 */       AttributeDescriptor attrib = target.getDescriptor(i);
/* 124 */       String xpath = attrib.getLocalName();
/* 125 */       types[i] = attrib;
/* 127 */       if (!attrib.equals(original.getDescriptor(xpath)))
/* 128 */         throw new IllegalArgumentException("Unable to retype  FeatureReader<SimpleFeatureType, SimpleFeature> (origional does not cover " + xpath + ")"); 
/*     */     } 
/* 134 */     return types;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ReTypingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */