/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class ReTypeFeatureReader implements DelegatingFeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*     */   
/*     */   SimpleFeatureType featureType;
/*     */   
/*     */   AttributeDescriptor[] types;
/*     */   
/*     */   SimpleFeatureBuilder builder;
/*     */   
/*     */   boolean clone;
/*     */   
/*     */   public ReTypeFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeatureType featureType) {
/*  85 */     this(reader, featureType, true);
/*     */   }
/*     */   
/*     */   public ReTypeFeatureReader(FeatureReader<SimpleFeatureType, SimpleFeature> reader, SimpleFeatureType featureType, boolean clone) {
/*  97 */     this.reader = reader;
/*  98 */     this.featureType = featureType;
/*  99 */     this.clone = clone;
/* 100 */     this.types = typeAttributes(featureType, reader.getFeatureType());
/* 101 */     this.builder = new SimpleFeatureBuilder(featureType);
/*     */   }
/*     */   
/*     */   public FeatureReader getDelegate() {
/* 105 */     return this.reader;
/*     */   }
/*     */   
/*     */   protected AttributeDescriptor[] typeAttributes(SimpleFeatureType target, SimpleFeatureType origional) {
/* 125 */     if (FeatureTypes.equalsExact(origional, target))
/* 126 */       throw new IllegalArgumentException("FeatureReader allready produces contents with the correct schema"); 
/* 130 */     if (target.getAttributeCount() > origional.getAttributeCount())
/* 131 */       throw new IllegalArgumentException("Unable to retype  FeatureReader<SimpleFeatureType, SimpleFeature> (origional does not cover requested type)"); 
/* 136 */     AttributeDescriptor[] types = new AttributeDescriptor[target.getAttributeCount()];
/* 138 */     for (int i = 0; i < target.getAttributeCount(); i++) {
/* 139 */       AttributeDescriptor attrib = target.getDescriptor(i);
/* 140 */       String xpath = attrib.getLocalName();
/* 142 */       types[i] = attrib;
/* 144 */       AttributeDescriptor check = origional.getDescriptor(xpath);
/* 145 */       Class<?> targetBinding = attrib.getType().getBinding();
/* 146 */       Class<?> checkBinding = check.getType().getBinding();
/* 147 */       if (!targetBinding.isAssignableFrom(checkBinding))
/* 148 */         throw new IllegalArgumentException("Unable to retype FeatureReader for " + xpath + " as " + Classes.getShortName(checkBinding) + " cannot be assigned to " + Classes.getShortName(targetBinding)); 
/*     */     } 
/* 155 */     return types;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 162 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/* 170 */     if (this.reader == null)
/* 171 */       throw new IOException("FeatureReader has been closed"); 
/* 174 */     SimpleFeature next = this.reader.next();
/* 175 */     String id = next.getID();
/* 179 */     for (int i = 0; i < this.types.length; i++) {
/* 180 */       String xpath = this.types[i].getLocalName();
/* 181 */       if (this.clone) {
/* 182 */         this.builder.add(DataUtilities.duplicate(next.getAttribute(xpath)));
/*     */       } else {
/* 184 */         this.builder.add(next.getAttribute(xpath));
/*     */       } 
/*     */     } 
/* 187 */     return this.builder.buildFeature(id);
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 194 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 201 */     if (this.reader != null) {
/* 202 */       this.reader.close();
/* 203 */       this.reader = null;
/* 204 */       this.featureType = null;
/* 205 */       this.types = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ReTypeFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */