/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class WKBHexFileReader {
/*  52 */   private File file = null;
/*     */   
/*     */   private Reader reader;
/*     */   
/*     */   private WKBReader wkbReader;
/*     */   
/*  55 */   private int count = 0;
/*     */   
/*  56 */   private int limit = -1;
/*     */   
/*  57 */   private int offset = 0;
/*     */   
/*     */   private static final int MAX_LOOKAHEAD = 1000;
/*     */   
/*     */   public WKBHexFileReader(File file, WKBReader wkbReader) {
/*  68 */     this.file = file;
/*  69 */     this.wkbReader = wkbReader;
/*     */   }
/*     */   
/*     */   public WKBHexFileReader(String filename, WKBReader wkbReader) {
/*  80 */     this(new File(filename), wkbReader);
/*     */   }
/*     */   
/*     */   public WKBHexFileReader(Reader reader, WKBReader wkbReader) {
/*  91 */     this.reader = reader;
/*  92 */     this.wkbReader = wkbReader;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit) {
/* 102 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public void setOffset(int offset) {
/* 112 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public List read() throws IOException, ParseException {
/* 128 */     if (this.file != null)
/* 129 */       this.reader = new FileReader(this.file); 
/* 131 */     this.count = 0;
/*     */     try {
/* 133 */       BufferedReader bufferedReader = new BufferedReader(this.reader);
/*     */     } finally {
/* 140 */       this.reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private List read(BufferedReader bufferedReader) throws IOException, ParseException {
/* 146 */     List<Geometry> geoms = new ArrayList();
/* 147 */     while (!isAtEndOfFile(bufferedReader) && !isAtLimit(geoms)) {
/* 148 */       String line = bufferedReader.readLine().trim();
/* 149 */       if (line.length() == 0)
/*     */         continue; 
/* 151 */       Geometry g = this.wkbReader.read(WKBReader.hexToBytes(line));
/* 152 */       if (this.count >= this.offset)
/* 153 */         geoms.add(g); 
/* 154 */       this.count++;
/*     */     } 
/* 156 */     return geoms;
/*     */   }
/*     */   
/*     */   private boolean isAtLimit(List geoms) {
/* 161 */     if (this.limit < 0)
/* 161 */       return false; 
/* 162 */     if (geoms.size() < this.limit)
/* 162 */       return false; 
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isAtEndOfFile(BufferedReader bufferedReader) throws IOException {
/* 174 */     bufferedReader.mark(1000);
/* 176 */     StreamTokenizer tokenizer = new StreamTokenizer(bufferedReader);
/* 177 */     int type = tokenizer.nextToken();
/* 179 */     if (type == -1)
/* 180 */       return true; 
/* 182 */     bufferedReader.reset();
/* 183 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKBHexFileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */