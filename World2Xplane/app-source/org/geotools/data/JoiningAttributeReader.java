/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ 
/*     */ public class JoiningAttributeReader implements AttributeReader {
/*     */   private AttributeReader[] readers;
/*     */   
/*     */   private int[] index;
/*     */   
/*     */   private AttributeDescriptor[] metaData;
/*     */   
/*     */   public JoiningAttributeReader(AttributeReader[] readers) {
/*  44 */     this.readers = readers;
/*  46 */     this.metaData = joinMetaData(readers);
/*     */   }
/*     */   
/*     */   private AttributeDescriptor[] joinMetaData(AttributeReader[] readers) {
/*  50 */     int total = 0;
/*  51 */     this.index = new int[readers.length];
/*  53 */     for (int i = 0, ii = readers.length; i < ii; i++) {
/*  54 */       this.index[i] = total;
/*  55 */       total += readers[i].getAttributeCount();
/*     */     } 
/*  58 */     AttributeDescriptor[] md = new AttributeDescriptor[total];
/*  59 */     int idx = 0;
/*  61 */     for (int j = 0, k = readers.length; j < k; j++) {
/*  62 */       for (int m = 0, jj = readers[j].getAttributeCount(); m < jj; m++) {
/*  63 */         md[idx] = readers[j].getAttributeType(m);
/*  64 */         idx++;
/*     */       } 
/*     */     } 
/*  68 */     return md;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  72 */     IOException dse = null;
/*  74 */     for (int i = 0, ii = this.readers.length; i < ii; i++) {
/*     */       try {
/*  76 */         this.readers[i].close();
/*  77 */       } catch (IOException e) {
/*  78 */         dse = e;
/*     */       } 
/*     */     } 
/*  82 */     if (dse != null)
/*  83 */       throw dse; 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/*  88 */     for (int i = 0, ii = this.readers.length; i < ii; i++) {
/*  89 */       if (this.readers[i].hasNext())
/*  90 */         return true; 
/*     */     } 
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public void next() throws IOException {
/*  98 */     for (int i = 0, ii = this.readers.length; i < ii; i++) {
/*  99 */       if (this.readers[i].hasNext())
/* 100 */         this.readers[i].next(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object read(int idx) throws IOException {
/* 106 */     AttributeReader reader = null;
/* 108 */     for (int i = this.index.length - 1; i >= 0; i--) {
/* 109 */       if (idx >= this.index[i]) {
/* 110 */         idx -= this.index[i];
/* 111 */         reader = this.readers[i];
/*     */         break;
/*     */       } 
/*     */     } 
/* 117 */     if (reader == null)
/* 118 */       throw new ArrayIndexOutOfBoundsException(idx); 
/* 121 */     return reader.read(idx);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 125 */     return this.metaData.length;
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getAttributeType(int i) {
/* 129 */     return this.metaData[i];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\JoiningAttributeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */