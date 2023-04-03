/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class PathValidator {
/*     */   private File xplaneFolder;
/*     */   
/*     */   private File resourcesFolder;
/*     */   
/*  41 */   private Set<String> libraryFiles = new HashSet<>();
/*     */   
/*     */   public PathValidator(File xplaneFolder, File resourcesFolder) throws IOException {
/*  45 */     this.xplaneFolder = xplaneFolder;
/*  46 */     this.resourcesFolder = resourcesFolder;
/*  51 */     File customScenery = new File(xplaneFolder, "Custom Scenery");
/*  52 */     if (customScenery.exists()) {
/*  54 */       File[] sceneryFolders = customScenery.listFiles(new FileFilter() {
/*     */             public boolean accept(File file) {
/*  56 */               return file.isDirectory();
/*     */             }
/*     */           });
/*  59 */       for (File sceneryFolder : sceneryFolders) {
/*  61 */         File libraryTxt = new File(sceneryFolder, "library.txt");
/*  62 */         if (libraryTxt.exists())
/*  63 */           readLibraryFile(libraryTxt); 
/*     */       } 
/*     */     } 
/*  69 */     File defaultScenery = new File(xplaneFolder, "Resources" + File.separator + "default scenery");
/*  70 */     if (defaultScenery.exists()) {
/*  72 */       File[] sceneryFolders = defaultScenery.listFiles(new FileFilter() {
/*     */             public boolean accept(File file) {
/*  74 */               return file.isDirectory();
/*     */             }
/*     */           });
/*  77 */       for (File sceneryFolder : sceneryFolders) {
/*  79 */         File libraryTxt = new File(sceneryFolder, "library.txt");
/*  80 */         if (libraryTxt.exists())
/*  81 */           readLibraryFile(libraryTxt); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readLibraryFile(File libraryTxt) throws IOException {
/*  90 */     String libraryFile = FileUtils.readFileToString(libraryTxt);
/*  91 */     String[] lines = libraryFile.split("\n");
/*  92 */     for (String item : lines) {
/*  93 */       item = item.replaceAll("\t", " ");
/*  94 */       item = item.replaceAll("\r", " ");
/*  95 */       if (item.startsWith("EXPORT")) {
/*  96 */         String[] files = item.split(" ");
/*  97 */         for (int x = 0; x < files.length; x++) {
/*  98 */           String text = files[x];
/*  99 */           if (!text.isEmpty() && !text.equals("EXPORT"))
/* 100 */             this.libraryFiles.add(text.replaceAll("\r", "")); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean checkFileExists(String file) {
/* 115 */     if (this.libraryFiles.contains(file.trim()))
/* 116 */       return true; 
/* 120 */     File filename = new File(this.resourcesFolder, file);
/* 121 */     return filename.exists();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\PathValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */