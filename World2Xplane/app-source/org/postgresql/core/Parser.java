/*     */ package org.postgresql.core;
/*     */ 
/*     */ public class Parser {
/*     */   public static int parseSingleQuotes(char[] query, int offset, boolean standardConformingStrings) {
/*  30 */     if (standardConformingStrings && offset >= 2 && (query[offset - 1] == 'e' || query[offset - 1] == 'E') && charTerminatesIdentifier(query[offset - 2]))
/*  35 */       standardConformingStrings = false; 
/*  38 */     if (standardConformingStrings) {
/*  41 */       while (++offset < query.length) {
/*  43 */         switch (query[offset]) {
/*     */           case '\'':
/*  46 */             return offset;
/*     */         } 
/*     */       } 
/*     */     } else {
/*  55 */       while (++offset < query.length) {
/*  57 */         switch (query[offset]) {
/*     */           case '\\':
/*  60 */             offset++;
/*     */           case '\'':
/*  63 */             return offset;
/*     */         } 
/*     */       } 
/*     */     } 
/*  70 */     return query.length;
/*     */   }
/*     */   
/*     */   public static int parseDoubleQuotes(char[] query, int offset) {
/*  82 */     while (++offset < query.length && query[offset] != '"');
/*  83 */     return offset;
/*     */   }
/*     */   
/*     */   public static int parseDollarQuotes(char[] query, int offset) {
/*  92 */     if (offset + 1 < query.length && (offset == 0 || !isIdentifierContChar(query[offset - 1]))) {
/*  95 */       int endIdx = -1;
/*  96 */       if (query[offset + 1] == '$') {
/*  97 */         endIdx = offset + 1;
/*  98 */       } else if (isDollarQuoteStartChar(query[offset + 1])) {
/* 100 */         for (int d = offset + 2; d < query.length; d++) {
/* 102 */           if (query[d] == '$') {
/* 104 */             endIdx = d;
/*     */             break;
/*     */           } 
/* 107 */           if (!isDollarQuoteContChar(query[d]))
/*     */             break; 
/*     */         } 
/*     */       } 
/* 111 */       if (endIdx > 0) {
/* 114 */         int tagIdx = offset, tagLen = endIdx - offset + 1;
/* 115 */         offset = endIdx;
/* 116 */         for (; ++offset < query.length; offset++) {
/* 118 */           if (query[offset] == '$' && subArraysEqual(query, tagIdx, offset, tagLen)) {
/* 121 */             offset += tagLen - 1;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 127 */     return offset;
/*     */   }
/*     */   
/*     */   public static int parseLineComment(char[] query, int offset) {
/* 136 */     if (offset + 1 < query.length && query[offset + 1] == '-')
/* 138 */       while (++offset < query.length) {
/* 140 */         if (query[offset] == '\r' || query[offset] == '\n')
/*     */           break; 
/*     */       }  
/* 144 */     return offset;
/*     */   }
/*     */   
/*     */   public static int parseBlockComment(char[] query, int offset) {
/* 152 */     if (offset + 1 < query.length && query[offset + 1] == '*') {
/* 155 */       int level = 1;
/* 156 */       for (offset += 2; offset < query.length; offset++) {
/* 158 */         switch (query[offset - 1]) {
/*     */           case '*':
/* 161 */             if (query[offset] == '/') {
/* 163 */               level--;
/* 164 */               offset++;
/*     */             } 
/*     */             break;
/*     */           case '/':
/* 168 */             if (query[offset] == '*') {
/* 170 */               level++;
/* 171 */               offset++;
/*     */             } 
/*     */             break;
/*     */         } 
/* 178 */         if (level == 0) {
/* 180 */           offset--;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 185 */     return offset;
/*     */   }
/*     */   
/*     */   public static boolean isSpace(char c) {
/* 193 */     return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f');
/*     */   }
/*     */   
/*     */   public static boolean isOperatorChar(char c) {
/* 205 */     return (",()[].;:+-*/%^<>=~!@#&|`?".indexOf(c) != -1);
/*     */   }
/*     */   
/*     */   public static boolean isIdentifierStartChar(char c) {
/* 221 */     return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c > '');
/*     */   }
/*     */   
/*     */   public static boolean isIdentifierContChar(char c) {
/* 233 */     return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c > '' || (c >= '0' && c <= '9') || c == '$');
/*     */   }
/*     */   
/*     */   public static boolean charTerminatesIdentifier(char c) {
/* 243 */     return (c == '"' || isSpace(c) || isOperatorChar(c));
/*     */   }
/*     */   
/*     */   public static boolean isDollarQuoteStartChar(char c) {
/* 258 */     return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c > '');
/*     */   }
/*     */   
/*     */   public static boolean isDollarQuoteContChar(char c) {
/* 271 */     return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c > '' || (c >= '0' && c <= '9'));
/*     */   }
/*     */   
/*     */   private static boolean subArraysEqual(char[] arr, int offA, int offB, int len) {
/* 290 */     if (offA < 0 || offB < 0 || offA >= arr.length || offB >= arr.length || offA + len > arr.length || offB + len > arr.length)
/* 293 */       return false; 
/* 295 */     for (int i = 0; i < len; i++) {
/* 297 */       if (arr[offA + i] != arr[offB + i])
/* 298 */         return false; 
/*     */     } 
/* 301 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */