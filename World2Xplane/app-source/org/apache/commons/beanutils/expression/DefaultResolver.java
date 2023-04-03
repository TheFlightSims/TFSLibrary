/*     */ package org.apache.commons.beanutils.expression;
/*     */ 
/*     */ public class DefaultResolver implements Resolver {
/*     */   private static final char NESTED = '.';
/*     */   
/*     */   private static final char MAPPED_START = '(';
/*     */   
/*     */   private static final char MAPPED_END = ')';
/*     */   
/*     */   private static final char INDEXED_START = '[';
/*     */   
/*     */   private static final char INDEXED_END = ']';
/*     */   
/*     */   public int getIndex(String expression) {
/*  80 */     if (expression == null || expression.length() == 0)
/*  81 */       return -1; 
/*  83 */     for (int i = 0; i < expression.length(); i++) {
/*  84 */       char c = expression.charAt(i);
/*  85 */       if (c == '.' || c == '(')
/*  86 */         return -1; 
/*  87 */       if (c == '[') {
/*  88 */         int end = expression.indexOf(']', i);
/*  89 */         if (end < 0)
/*  90 */           throw new IllegalArgumentException("Missing End Delimiter"); 
/*  92 */         String value = expression.substring(i + 1, end);
/*  93 */         if (value.length() == 0)
/*  94 */           throw new IllegalArgumentException("No Index Value"); 
/*  96 */         int index = 0;
/*     */         try {
/*  98 */           index = Integer.parseInt(value, 10);
/*  99 */         } catch (Exception e) {
/* 100 */           throw new IllegalArgumentException("Invalid index value '" + value + "'");
/*     */         } 
/* 103 */         return index;
/*     */       } 
/*     */     } 
/* 106 */     return -1;
/*     */   }
/*     */   
/*     */   public String getKey(String expression) {
/* 117 */     if (expression == null || expression.length() == 0)
/* 118 */       return null; 
/* 120 */     for (int i = 0; i < expression.length(); i++) {
/* 121 */       char c = expression.charAt(i);
/* 122 */       if (c == '.' || c == '[')
/* 123 */         return null; 
/* 124 */       if (c == '(') {
/* 125 */         int end = expression.indexOf(')', i);
/* 126 */         if (end < 0)
/* 127 */           throw new IllegalArgumentException("Missing End Delimiter"); 
/* 129 */         return expression.substring(i + 1, end);
/*     */       } 
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public String getProperty(String expression) {
/* 142 */     if (expression == null || expression.length() == 0)
/* 143 */       return expression; 
/* 145 */     for (int i = 0; i < expression.length(); i++) {
/* 146 */       char c = expression.charAt(i);
/* 147 */       if (c == '.')
/* 148 */         return expression.substring(0, i); 
/* 149 */       if (c == '(' || c == '[')
/* 150 */         return expression.substring(0, i); 
/*     */     } 
/* 153 */     return expression;
/*     */   }
/*     */   
/*     */   public boolean hasNested(String expression) {
/* 164 */     if (expression == null || expression.length() == 0)
/* 165 */       return false; 
/* 167 */     return (remove(expression) != null);
/*     */   }
/*     */   
/*     */   public boolean isIndexed(String expression) {
/* 179 */     if (expression == null || expression.length() == 0)
/* 180 */       return false; 
/* 182 */     for (int i = 0; i < expression.length(); i++) {
/* 183 */       char c = expression.charAt(i);
/* 184 */       if (c == '.' || c == '(')
/* 185 */         return false; 
/* 186 */       if (c == '[')
/* 187 */         return true; 
/*     */     } 
/* 190 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isMapped(String expression) {
/* 201 */     if (expression == null || expression.length() == 0)
/* 202 */       return false; 
/* 204 */     for (int i = 0; i < expression.length(); i++) {
/* 205 */       char c = expression.charAt(i);
/* 206 */       if (c == '.' || c == '[')
/* 207 */         return false; 
/* 208 */       if (c == '(')
/* 209 */         return true; 
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */   
/*     */   public String next(String expression) {
/* 223 */     if (expression == null || expression.length() == 0)
/* 224 */       return null; 
/* 226 */     boolean indexed = false;
/* 227 */     boolean mapped = false;
/* 228 */     for (int i = 0; i < expression.length(); i++) {
/* 229 */       char c = expression.charAt(i);
/* 230 */       if (indexed) {
/* 231 */         if (c == ']')
/* 232 */           return expression.substring(0, i + 1); 
/* 234 */       } else if (mapped) {
/* 235 */         if (c == ')')
/* 236 */           return expression.substring(0, i + 1); 
/*     */       } else {
/* 239 */         if (c == '.')
/* 240 */           return expression.substring(0, i); 
/* 241 */         if (c == '(') {
/* 242 */           mapped = true;
/* 243 */         } else if (c == '[') {
/* 244 */           indexed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 248 */     return expression;
/*     */   }
/*     */   
/*     */   public String remove(String expression) {
/* 260 */     if (expression == null || expression.length() == 0)
/* 261 */       return null; 
/* 263 */     String property = next(expression);
/* 264 */     if (expression.length() == property.length())
/* 265 */       return null; 
/* 267 */     int start = property.length();
/* 268 */     if (expression.charAt(start) == '.')
/* 269 */       start++; 
/* 271 */     return expression.substring(start);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\expression\DefaultResolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */