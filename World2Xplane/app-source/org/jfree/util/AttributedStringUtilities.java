/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AttributedStringUtilities {
/*     */   public static boolean equal(AttributedString s1, AttributedString s2) {
/*  74 */     if (s1 == null)
/*  75 */       return (s2 == null); 
/*  77 */     if (s2 == null)
/*  78 */       return false; 
/*  80 */     AttributedCharacterIterator it1 = s1.getIterator();
/*  81 */     AttributedCharacterIterator it2 = s2.getIterator();
/*  82 */     char c1 = it1.first();
/*  83 */     char c2 = it2.first();
/*  84 */     int start = 0;
/*  85 */     while (c1 != Character.MAX_VALUE) {
/*  86 */       int limit1 = it1.getRunLimit();
/*  87 */       int limit2 = it2.getRunLimit();
/*  88 */       if (limit1 != limit2)
/*  89 */         return false; 
/*  92 */       Map m1 = it1.getAttributes();
/*  93 */       Map m2 = it2.getAttributes();
/*  94 */       if (!m1.equals(m2))
/*  95 */         return false; 
/*  98 */       for (int i = start; i < limit1; i++) {
/*  99 */         if (c1 != c2)
/* 100 */           return false; 
/* 102 */         c1 = it1.next();
/* 103 */         c2 = it2.next();
/*     */       } 
/* 105 */       start = limit1;
/*     */     } 
/* 107 */     return (c2 == Character.MAX_VALUE);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\AttributedStringUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */