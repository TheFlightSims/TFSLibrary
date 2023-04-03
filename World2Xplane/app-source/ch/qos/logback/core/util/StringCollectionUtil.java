/*     */ package ch.qos.logback.core.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class StringCollectionUtil {
/*     */   public static void retainMatching(Collection<String> values, String... patterns) {
/*  42 */     retainMatching(values, Arrays.asList(patterns));
/*     */   }
/*     */   
/*     */   public static void retainMatching(Collection<String> values, Collection<String> patterns) {
/*  58 */     if (patterns.isEmpty())
/*     */       return; 
/*  59 */     List<String> matches = new ArrayList<String>(values.size());
/*  60 */     for (String p : patterns) {
/*  61 */       Pattern pattern = Pattern.compile(p);
/*  62 */       for (String value : values) {
/*  63 */         if (pattern.matcher(value).matches())
/*  64 */           matches.add(value); 
/*     */       } 
/*     */     } 
/*  68 */     values.retainAll(matches);
/*     */   }
/*     */   
/*     */   public static void removeMatching(Collection<String> values, String... patterns) {
/*  84 */     removeMatching(values, Arrays.asList(patterns));
/*     */   }
/*     */   
/*     */   public static void removeMatching(Collection<String> values, Collection<String> patterns) {
/* 100 */     List<String> matches = new ArrayList<String>(values.size());
/* 101 */     for (String p : patterns) {
/* 102 */       Pattern pattern = Pattern.compile(p);
/* 103 */       for (String value : values) {
/* 104 */         if (pattern.matcher(value).matches())
/* 105 */           matches.add(value); 
/*     */       } 
/*     */     } 
/* 109 */     values.removeAll(matches);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\StringCollectionUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */