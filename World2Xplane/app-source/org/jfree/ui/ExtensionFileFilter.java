/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.File;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ 
/*     */ public class ExtensionFileFilter extends FileFilter {
/*     */   private String description;
/*     */   
/*     */   private String extension;
/*     */   
/*     */   public ExtensionFileFilter(String description, String extension) {
/*  70 */     this.description = description;
/*  71 */     this.extension = extension;
/*     */   }
/*     */   
/*     */   public boolean accept(File file) {
/*  83 */     if (file.isDirectory())
/*  84 */       return true; 
/*  87 */     String name = file.getName().toLowerCase();
/*  88 */     if (name.endsWith(this.extension))
/*  89 */       return true; 
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 103 */     return this.description;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\ExtensionFileFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */