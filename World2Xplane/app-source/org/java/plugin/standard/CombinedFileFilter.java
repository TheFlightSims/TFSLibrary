/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ 
/*     */ final class CombinedFileFilter implements FileFilter {
/*     */   private final FileFilter includesFilter;
/*     */   
/*     */   private final FileFilter excludesFilter;
/*     */   
/*     */   CombinedFileFilter(FileFilter includes, FileFilter excludes) {
/* 926 */     this.includesFilter = includes;
/* 927 */     this.excludesFilter = excludes;
/*     */   }
/*     */   
/*     */   public boolean accept(File file) {
/* 934 */     if (this.includesFilter != null && 
/* 935 */       this.includesFilter.accept(file))
/* 936 */       return true; 
/* 939 */     if (this.excludesFilter != null && this.excludesFilter.accept(file))
/* 940 */       return false; 
/* 942 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\CombinedFileFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */