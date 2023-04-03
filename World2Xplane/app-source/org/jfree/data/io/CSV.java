/*     */ package org.jfree.data.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ 
/*     */ public class CSV {
/*     */   private char fieldDelimiter;
/*     */   
/*     */   private char textDelimiter;
/*     */   
/*     */   public CSV() {
/*  71 */     this(',', '"');
/*     */   }
/*     */   
/*     */   public CSV(char fieldDelimiter, char textDelimiter) {
/*  83 */     this.fieldDelimiter = fieldDelimiter;
/*  84 */     this.textDelimiter = textDelimiter;
/*     */   }
/*     */   
/*     */   public CategoryDataset readCategoryDataset(Reader in) throws IOException {
/*  98 */     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
/*  99 */     BufferedReader reader = new BufferedReader(in);
/* 100 */     List columnKeys = null;
/* 101 */     int lineIndex = 0;
/* 102 */     String line = reader.readLine();
/* 103 */     while (line != null) {
/* 104 */       if (lineIndex == 0) {
/* 105 */         columnKeys = extractColumnKeys(line);
/*     */       } else {
/* 108 */         extractRowKeyAndData(line, dataset, columnKeys);
/*     */       } 
/* 110 */       line = reader.readLine();
/* 111 */       lineIndex++;
/*     */     } 
/* 113 */     return (CategoryDataset)dataset;
/*     */   }
/*     */   
/*     */   private List extractColumnKeys(String line) {
/* 125 */     List keys = new ArrayList();
/* 126 */     int fieldIndex = 0;
/* 127 */     int start = 0;
/* 128 */     for (int i = 0; i < line.length(); i++) {
/* 129 */       if (line.charAt(i) == this.fieldDelimiter) {
/* 130 */         if (fieldIndex > 0) {
/* 132 */           String str = line.substring(start, i);
/* 133 */           keys.add(removeStringDelimiters(str));
/*     */         } 
/* 135 */         start = i + 1;
/* 136 */         fieldIndex++;
/*     */       } 
/*     */     } 
/* 139 */     String key = line.substring(start, line.length());
/* 140 */     keys.add(removeStringDelimiters(key));
/* 141 */     return keys;
/*     */   }
/*     */   
/*     */   private void extractRowKeyAndData(String line, DefaultCategoryDataset dataset, List columnKeys) {
/* 154 */     Comparable rowKey = null;
/* 155 */     int fieldIndex = 0;
/* 156 */     int start = 0;
/* 157 */     for (int i = 0; i < line.length(); i++) {
/* 158 */       if (line.charAt(i) == this.fieldDelimiter) {
/* 159 */         if (fieldIndex == 0) {
/* 160 */           String key = line.substring(start, i);
/* 161 */           rowKey = removeStringDelimiters(key);
/*     */         } else {
/* 164 */           Double double_ = Double.valueOf(removeStringDelimiters(line.substring(start, i)));
/* 167 */           dataset.addValue(double_, rowKey, columnKeys.get(fieldIndex - 1));
/*     */         } 
/* 172 */         start = i + 1;
/* 173 */         fieldIndex++;
/*     */       } 
/*     */     } 
/* 176 */     Double value = Double.valueOf(removeStringDelimiters(line.substring(start, line.length())));
/* 179 */     dataset.addValue(value, rowKey, columnKeys.get(fieldIndex - 1));
/*     */   }
/*     */   
/*     */   private String removeStringDelimiters(String key) {
/* 193 */     String k = key.trim();
/* 194 */     if (k.charAt(0) == this.textDelimiter)
/* 195 */       k = k.substring(1); 
/* 197 */     if (k.charAt(k.length() - 1) == this.textDelimiter)
/* 198 */       k = k.substring(0, k.length() - 1); 
/* 200 */     return k;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\io\CSV.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */