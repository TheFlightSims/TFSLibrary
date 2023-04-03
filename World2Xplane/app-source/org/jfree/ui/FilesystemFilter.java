/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ 
/*     */ public class FilesystemFilter extends FileFilter implements FilenameFilter {
/*     */   private String[] fileext;
/*     */   
/*     */   private String descr;
/*     */   
/*     */   private boolean accDirs;
/*     */   
/*     */   public FilesystemFilter(String fileext, String descr) {
/*  68 */     this(fileext, descr, true);
/*     */   }
/*     */   
/*     */   public FilesystemFilter(String fileext, String descr, boolean accDirs) {
/*  80 */     this(new String[] { fileext }, descr, accDirs);
/*     */   }
/*     */   
/*     */   public FilesystemFilter(String[] fileext, String descr, boolean accDirs) {
/*  93 */     this.fileext = (String[])fileext.clone();
/*  94 */     this.descr = descr;
/*  95 */     this.accDirs = accDirs;
/*     */   }
/*     */   
/*     */   public boolean accept(File dir, String name) {
/* 107 */     File f = new File(dir, name);
/* 108 */     if (f.isDirectory() && acceptsDirectories())
/* 109 */       return true; 
/* 112 */     for (int i = 0; i < this.fileext.length; i++) {
/* 113 */       if (name.endsWith(this.fileext[i]))
/* 114 */         return true; 
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public boolean accept(File dir) {
/* 128 */     if (dir.isDirectory() && acceptsDirectories())
/* 129 */       return true; 
/* 132 */     for (int i = 0; i < this.fileext.length; i++) {
/* 133 */       if (dir.getName().endsWith(this.fileext[i]))
/* 134 */         return true; 
/*     */     } 
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 146 */     return this.descr;
/*     */   }
/*     */   
/*     */   public void acceptDirectories(boolean b) {
/* 155 */     this.accDirs = b;
/*     */   }
/*     */   
/*     */   public boolean acceptsDirectories() {
/* 164 */     return this.accDirs;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\FilesystemFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */