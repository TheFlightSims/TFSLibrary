/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.SchemaException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class FIDFeatureReader implements FeatureReader<SimpleFeatureType, SimpleFeature> {
/*  59 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   private final AttributeReader attributeReader;
/*     */   
/*     */   private final SimpleFeatureType schema;
/*     */   
/*     */   private final FIDReader fidReader;
/*     */   
/*     */   protected final Object[] attributes;
/*     */   
/*     */   private SimpleFeatureBuilder builder;
/*     */   
/*     */   private Boolean hasNextFlag;
/*     */   
/*     */   public FIDFeatureReader(AttributeReader attributeReader, FIDReader fidReader, SimpleFeatureType schema) throws SchemaException {
/*  79 */     this.attributeReader = attributeReader;
/*  80 */     this.fidReader = fidReader;
/*  82 */     if (schema == null)
/*  83 */       schema = createSchema(); 
/*  86 */     this.schema = schema;
/*  87 */     this.attributes = new Object[attributeReader.getAttributeCount()];
/*  88 */     this.builder = new SimpleFeatureBuilder(schema);
/*     */   }
/*     */   
/*     */   public FIDFeatureReader(AttributeReader attributeReader, FIDReader fidReader) throws SchemaException {
/*  93 */     this(attributeReader, fidReader, null);
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException, IllegalAttributeException, NoSuchElementException {
/*  98 */     if (hasNext()) {
/*  99 */       this.hasNextFlag = null;
/* 100 */       this.attributeReader.next();
/* 102 */       return readFeature(this.attributeReader);
/*     */     } 
/* 104 */     throw new NoSuchElementException("There are no more Features to be read");
/*     */   }
/*     */   
/*     */   protected SimpleFeatureType createSchema() throws SchemaException {
/* 110 */     SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
/* 111 */     b.setName("xxx");
/* 113 */     for (int i = 0, ii = this.attributeReader.getAttributeCount(); i < ii; 
/* 114 */       i++)
/* 115 */       b.add(this.attributeReader.getAttributeType(i)); 
/* 118 */     return b.buildFeatureType();
/*     */   }
/*     */   
/*     */   protected SimpleFeature readFeature(AttributeReader atts) throws IllegalAttributeException, IOException {
/* 128 */     String fid = this.fidReader.next();
/* 130 */     for (int i = 0, ii = atts.getAttributeCount(); i < ii; i++)
/* 131 */       this.builder.add(atts.read(i)); 
/* 133 */     return this.builder.buildFeature(fid);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 137 */     this.fidReader.close();
/* 138 */     this.attributeReader.close();
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 142 */     return this.schema;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 146 */     if (this.hasNextFlag == null)
/* 147 */       this.hasNextFlag = Boolean.valueOf(this.attributeReader.hasNext()); 
/* 149 */     return this.hasNextFlag.booleanValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FIDFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */