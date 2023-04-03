/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ final class RegexpFileFilter implements FileFilter {
/*     */   private final Pattern[] patterns;
/*     */   
/*     */   RegexpFileFilter(String str) {
/* 894 */     StringTokenizer st = new StringTokenizer(str, "|", false);
/* 895 */     this.patterns = new Pattern[st.countTokens()];
/* 896 */     for (int i = 0; i < this.patterns.length; i++) {
/* 897 */       String pattern = st.nextToken();
/* 898 */       if (pattern != null && pattern.trim().length() != 0)
/* 901 */         this.patterns[i] = Pattern.compile(pattern.trim()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean accept(File file) {
/* 909 */     for (int i = 0; i < this.patterns.length; i++) {
/* 910 */       if (this.patterns[i] != null)
/* 913 */         if (this.patterns[i].matcher(file.getName()).matches())
/* 914 */           return true;  
/*     */     } 
/* 917 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\RegexpFileFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */