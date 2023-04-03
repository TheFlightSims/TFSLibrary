/*     */ package org.geotools.data.sort;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.io.ParseException;
/*     */ import com.vividsolutions.jts.io.WKBReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ 
/*     */ class FeatureBlockReader {
/*     */   RandomAccessFile raf;
/*     */   
/*     */   SimpleFeatureType schema;
/*     */   
/*     */   SimpleFeatureBuilder builder;
/*     */   
/*     */   SimpleFeature curr;
/*     */   
/*     */   long offset;
/*     */   
/*     */   int count;
/*     */   
/*     */   public FeatureBlockReader(RandomAccessFile raf, long start, int count, SimpleFeatureType schema) {
/*  53 */     this.raf = raf;
/*  54 */     this.offset = start;
/*  55 */     this.count = count;
/*  56 */     this.schema = schema;
/*  57 */     this.builder = new SimpleFeatureBuilder(schema);
/*     */   }
/*     */   
/*     */   public SimpleFeature feature() throws IOException {
/*  61 */     if (this.curr == null && this.count > 0)
/*  62 */       this.curr = readNextFeature(); 
/*  64 */     return this.curr;
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  68 */     this.curr = readNextFeature();
/*  69 */     return this.curr;
/*     */   }
/*     */   
/*     */   private SimpleFeature readNextFeature() throws IOException {
/*  73 */     if (this.count <= 0)
/*  74 */       return null; 
/*  78 */     this.raf.seek(this.offset);
/*  81 */     String fid = this.raf.readUTF();
/*  83 */     for (AttributeDescriptor ad : this.schema.getAttributeDescriptors()) {
/*  84 */       Object att = readAttribute(ad);
/*  85 */       this.builder.add(att);
/*     */     } 
/*  88 */     this.offset = this.raf.getFilePointer();
/*  89 */     this.count--;
/*  92 */     return this.builder.buildFeature(fid);
/*     */   }
/*     */   
/*     */   Object readAttribute(AttributeDescriptor ad) throws IOException {
/* 105 */     boolean isNull = this.raf.readBoolean();
/* 106 */     if (isNull)
/* 107 */       return null; 
/* 109 */     Class<?> binding = ad.getType().getBinding();
/* 110 */     if (binding == Boolean.class)
/* 111 */       return Boolean.valueOf(this.raf.readBoolean()); 
/* 112 */     if (binding == Byte.class || binding == byte.class)
/* 113 */       return Byte.valueOf(this.raf.readByte()); 
/* 114 */     if (binding == Short.class || binding == short.class)
/* 115 */       return Short.valueOf(this.raf.readShort()); 
/* 116 */     if (binding == Integer.class || binding == int.class)
/* 117 */       return Integer.valueOf(this.raf.readInt()); 
/* 118 */     if (binding == Long.class || binding == long.class)
/* 119 */       return Long.valueOf(this.raf.readLong()); 
/* 120 */     if (binding == Float.class || binding == float.class)
/* 121 */       return Float.valueOf(this.raf.readFloat()); 
/* 122 */     if (binding == Double.class || binding == double.class)
/* 123 */       return Double.valueOf(this.raf.readDouble()); 
/* 124 */     if (binding == String.class)
/* 125 */       return this.raf.readUTF(); 
/* 126 */     if (binding == Date.class)
/* 127 */       return new Date(this.raf.readLong()); 
/* 128 */     if (binding == Time.class)
/* 129 */       return new Time(this.raf.readLong()); 
/* 130 */     if (binding == Timestamp.class)
/* 131 */       return new Timestamp(this.raf.readLong()); 
/* 132 */     if (binding == Date.class)
/* 133 */       return new Date(this.raf.readLong()); 
/* 134 */     if (Geometry.class.isAssignableFrom(binding)) {
/* 135 */       WKBReader reader = new WKBReader();
/* 136 */       int i = this.raf.readInt();
/* 137 */       byte[] arrayOfByte = new byte[i];
/* 138 */       this.raf.read(arrayOfByte);
/*     */       try {
/* 140 */         return reader.read(arrayOfByte);
/* 141 */       } catch (ParseException e) {
/* 142 */         throw new IOException("Failed to parse the geometry WKB", e);
/*     */       } 
/*     */     } 
/* 145 */     int length = this.raf.readInt();
/* 146 */     byte[] buffer = new byte[length];
/* 147 */     this.raf.read(buffer);
/* 148 */     ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
/* 149 */     ObjectInputStream ois = new ObjectInputStream(bis);
/*     */     try {
/* 151 */       return ois.readObject();
/* 152 */     } catch (ClassNotFoundException e) {
/* 153 */       throw new IOException("Could not read back object", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\FeatureBlockReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */