/*    */ package com.world2xplane.GUI;
/*    */ 
/*    */ import java.io.File;
/*    */ import javax.swing.filechooser.FileFilter;
/*    */ 
/*    */ public class FileTypeFilter extends FileFilter {
/*    */   private String extension;
/*    */   
/*    */   private String description;
/*    */   
/*    */   public FileTypeFilter(String extension, String description) {
/* 34 */     this.extension = extension;
/* 35 */     this.description = description;
/*    */   }
/*    */   
/*    */   public boolean accept(File file) {
/* 40 */     if (file.isDirectory())
/* 41 */       return true; 
/* 43 */     return file.getName().toLowerCase().endsWith(this.extension);
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 47 */     return this.description + String.format(" (*%s)", new Object[] { this.extension });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\FileTypeFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */