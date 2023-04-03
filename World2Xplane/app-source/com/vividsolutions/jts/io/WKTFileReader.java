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
/*     */ public class WKTFileReader {
/*  51 */   private File file = null;
/*     */   
/*     */   private Reader reader;
/*     */   
/*     */   private WKTReader wktReader;
/*     */   
/*  55 */   private int count = 0;
/*     */   
/*  56 */   private int limit = -1;
/*     */   
/*  57 */   private int offset = 0;
/*     */   
/*     */   private static final int MAX_LOOKAHEAD = 1000;
/*     */   
/*     */   public WKTFileReader(File file, WKTReader wktReader) {
/*  68 */     this.file = file;
/*  69 */     this.wktReader = wktReader;
/*     */   }
/*     */   
/*     */   public WKTFileReader(String filename, WKTReader wktReader) {
/*  80 */     this(new File(filename), wktReader);
/*     */   }
/*     */   
/*     */   public WKTFileReader(Reader reader, WKTReader wktReader) {
/*  91 */     this.reader = reader;
/*  92 */     this.wktReader = wktReader;
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
/* 148 */       Geometry g = this.wktReader.read(bufferedReader);
/* 149 */       if (this.count >= this.offset)
/* 150 */         geoms.add(g); 
/* 151 */       this.count++;
/*     */     } 
/* 153 */     return geoms;
/*     */   }
/*     */   
/*     */   private boolean isAtLimit(List geoms) {
/* 158 */     if (this.limit < 0)
/* 158 */       return false; 
/* 159 */     if (geoms.size() < this.limit)
/* 159 */       return false; 
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isAtEndOfFile(BufferedReader bufferedReader) throws IOException {
/* 171 */     bufferedReader.mark(1000);
/* 173 */     StreamTokenizer tokenizer = new StreamTokenizer(bufferedReader);
/* 174 */     int type = tokenizer.nextToken();
/* 176 */     if (type == -1)
/* 177 */       return true; 
/* 179 */     bufferedReader.reset();
/* 180 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKTFileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */