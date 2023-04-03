/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class DefaultFeatureReader implements FeatureReader<SimpleFeatureType, SimpleFeature> {
/*     */   private final AttributeReader attributeReader;
/*     */   
/*     */   private final SimpleFeatureType schema;
/*     */   
/*     */   protected final Object[] attributes;
/*     */   
/*     */   public DefaultFeatureReader(AttributeReader attributeReader, SimpleFeatureType schema) throws SchemaException {
/*  54 */     this.attributeReader = attributeReader;
/*  56 */     if (schema == null)
/*  57 */       schema = createSchema(); 
/*  60 */     this.schema = schema;
/*  61 */     this.attributes = new Object[attributeReader.getAttributeCount()];
/*     */   }
/*     */   
/*     */   public DefaultFeatureReader(AttributeReader attributeReader) throws SchemaException {
/*  66 */     this(attributeReader, null);
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/*  71 */     SimpleFeature f = null;
/*  73 */     if (this.attributeReader.hasNext()) {
/*  74 */       this.attributeReader.next();
/*  75 */       f = readFeature(this.attributeReader);
/*     */     } 
/*  78 */     return f;
/*     */   }
/*     */   
/*     */   protected SimpleFeatureType createSchema() throws SchemaException {
/*  83 */     SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
/*  84 */     for (int i = 0, ii = this.attributeReader.getAttributeCount(); i < ii; 
/*  85 */       i++)
/*  86 */       builder.add(this.attributeReader.getAttributeType(i)); 
/*  89 */     return builder.buildFeatureType();
/*     */   }
/*     */   
/*     */   protected SimpleFeature readFeature(AttributeReader atts) throws IllegalAttributeException, IOException {
/*  94 */     for (int i = 0, ii = atts.getAttributeCount(); i < ii; i++)
/*  95 */       this.attributes[i] = atts.read(i); 
/*  98 */     return SimpleFeatureBuilder.build(this.schema, this.attributes, null);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 102 */     this.attributeReader.close();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 106 */     return this.schema;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 110 */     return this.attributeReader.hasNext();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */