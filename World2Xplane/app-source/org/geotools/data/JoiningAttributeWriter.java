/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ 
/*     */ public class JoiningAttributeWriter implements AttributeWriter {
/*     */   private AttributeWriter[] writers;
/*     */   
/*     */   private int[] index;
/*     */   
/*     */   private AttributeDescriptor[] metaData;
/*     */   
/*     */   public JoiningAttributeWriter(AttributeWriter[] writers) {
/*  39 */     this.writers = writers;
/*  40 */     this.metaData = joinMetaData(writers);
/*     */   }
/*     */   
/*     */   private AttributeDescriptor[] joinMetaData(AttributeWriter[] writers) {
/*  44 */     int total = 0;
/*  45 */     this.index = new int[writers.length];
/*  46 */     for (int i = 0, ii = writers.length; i < ii; i++) {
/*  47 */       this.index[i] = total;
/*  48 */       total += writers[i].getAttributeCount();
/*     */     } 
/*  50 */     AttributeDescriptor[] md = new AttributeDescriptor[total];
/*  51 */     int idx = 0;
/*  52 */     for (int j = 0, k = writers.length; j < k; j++) {
/*  53 */       for (int m = 0, jj = writers[j].getAttributeCount(); m < jj; m++) {
/*  54 */         md[idx] = writers[j].getAttributeType(m);
/*  55 */         idx++;
/*     */       } 
/*     */     } 
/*  58 */     return md;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  65 */     IOException dse = null;
/*  66 */     for (int i = 0, ii = this.writers.length; i < ii; i++) {
/*     */       try {
/*  68 */         this.writers[i].close();
/*  69 */       } catch (IOException e) {
/*  70 */         dse = e;
/*     */       } 
/*     */     } 
/*  73 */     if (dse != null)
/*  74 */       throw dse; 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/*  79 */     for (int i = 0, ii = this.writers.length; i < ii; i++) {
/*  80 */       if (this.writers[i].hasNext())
/*  81 */         return true; 
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public void next() throws IOException {
/*  88 */     for (int i = 0, ii = this.writers.length; i < ii; i++)
/*  90 */       this.writers[i].next(); 
/*     */   }
/*     */   
/*     */   public void write(int position, Object attribute) throws IOException {
/*  98 */     AttributeWriter writer = null;
/*  99 */     for (int i = this.index.length - 1; i >= 0; i--) {
/* 100 */       if (position >= this.index[i]) {
/* 101 */         position -= this.index[i];
/* 102 */         writer = this.writers[i];
/*     */         break;
/*     */       } 
/*     */     } 
/* 106 */     if (writer == null)
/* 107 */       throw new ArrayIndexOutOfBoundsException(position); 
/* 109 */     writer.write(position, attribute);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 113 */     return this.metaData.length;
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getAttributeType(int i) {
/* 117 */     return this.metaData[i];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\JoiningAttributeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */