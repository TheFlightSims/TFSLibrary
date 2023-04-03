/*    */ package com.world2xplane.XPlane;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FileSearch {
/*    */   private String fileNameToSearch;
/*    */   
/* 10 */   private List<File> result = new ArrayList<>();
/*    */   
/*    */   public String getFileNameToSearch() {
/* 13 */     return this.fileNameToSearch;
/*    */   }
/*    */   
/*    */   public void setFileNameToSearch(String fileNameToSearch) {
/* 17 */     this.fileNameToSearch = fileNameToSearch;
/*    */   }
/*    */   
/*    */   public List<File> getResult() {
/* 21 */     return this.result;
/*    */   }
/*    */   
/*    */   public List<File> getFiles(File directory, String extension) {
/* 26 */     FileSearch fileSearch = new FileSearch();
/* 29 */     fileSearch.searchDirectory(directory, extension);
/* 31 */     return fileSearch.getResult();
/*    */   }
/*    */   
/*    */   public void searchDirectory(File directory, String extension) {
/* 36 */     setFileNameToSearch(extension);
/* 38 */     if (directory.isDirectory())
/* 39 */       search(directory); 
/*    */   }
/*    */   
/*    */   private void search(File file) {
/* 46 */     if (file.isDirectory()) {
/* 47 */       System.out.println("Searching directory ... " + file.getAbsoluteFile());
/* 50 */       if (file.canRead())
/* 51 */         for (File temp : file.listFiles()) {
/* 52 */           if (temp.isDirectory()) {
/* 53 */             search(temp);
/* 55 */           } else if (temp.getName().toLowerCase().endsWith(this.fileNameToSearch)) {
/* 56 */             this.result.add(temp);
/*    */           } 
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FileSearch.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */