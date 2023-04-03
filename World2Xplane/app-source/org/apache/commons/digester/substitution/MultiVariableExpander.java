/*     */ package org.apache.commons.digester.substitution;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MultiVariableExpander implements VariableExpander {
/*  31 */   private int nEntries = 0;
/*     */   
/*  32 */   private ArrayList markers = new ArrayList(2);
/*     */   
/*  33 */   private ArrayList sources = new ArrayList(2);
/*     */   
/*     */   public void addSource(String marker, Map source) {
/*  39 */     this.nEntries++;
/*  40 */     this.markers.add(marker);
/*  41 */     this.sources.add(source);
/*     */   }
/*     */   
/*     */   public String expand(String param) {
/*  52 */     for (int i = 0; i < this.nEntries; i++)
/*  53 */       param = expand(param, this.markers.get(i), this.sources.get(i)); 
/*  58 */     return param;
/*     */   }
/*     */   
/*     */   public String expand(String str, String marker, Map source) {
/*  77 */     String startMark = marker + "{";
/*  78 */     int markLen = startMark.length();
/*  80 */     int index = 0;
/*     */     while (true) {
/*  83 */       index = str.indexOf(startMark, index);
/*  84 */       if (index == -1)
/*  86 */         return str; 
/*  89 */       int startIndex = index + markLen;
/*  90 */       if (startIndex > str.length())
/*  92 */         throw new IllegalArgumentException("var expression starts at end of string"); 
/*  96 */       int endIndex = str.indexOf("}", index + markLen);
/*  97 */       if (endIndex == -1)
/*  99 */         throw new IllegalArgumentException("var expression starts but does not end"); 
/* 103 */       String key = str.substring(index + markLen, endIndex);
/* 104 */       Object value = source.get(key);
/* 105 */       if (value == null)
/* 106 */         throw new IllegalArgumentException("parameter [" + key + "] is not defined."); 
/* 109 */       String varValue = value.toString();
/* 111 */       str = str.substring(0, index) + varValue + str.substring(endIndex + 1);
/* 112 */       index += varValue.length();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\substitution\MultiVariableExpander.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */