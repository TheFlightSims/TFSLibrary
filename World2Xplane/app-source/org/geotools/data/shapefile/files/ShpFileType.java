/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ 
/*     */ public enum ShpFileType {
/*  35 */   SHP("shp"),
/*  39 */   DBF("dbf"),
/*  43 */   SHX("shx"),
/*  47 */   PRJ("prj"),
/*  52 */   QIX("qix"),
/*  57 */   FIX("fix"),
/*  61 */   SHP_XML("shp.xml");
/*     */   
/*     */   public final String extension;
/*     */   
/*     */   public final String extensionWithPeriod;
/*     */   
/*     */   ShpFileType(String extension) {
/*  67 */     this.extension = extension.toLowerCase();
/*  68 */     this.extensionWithPeriod = "." + this.extension;
/*     */   }
/*     */   
/*     */   public String toBase(File file) {
/*  79 */     String path = file.getPath();
/*  80 */     return toBase(path);
/*     */   }
/*     */   
/*     */   public String toBase(String path) {
/*  91 */     if (!path.toLowerCase().endsWith(this.extensionWithPeriod) || path.equalsIgnoreCase(this.extensionWithPeriod))
/*  93 */       return null; 
/*  96 */     int indexOfExtension = path.toLowerCase().lastIndexOf(this.extensionWithPeriod);
/*  98 */     return path.substring(0, indexOfExtension);
/*     */   }
/*     */   
/*     */   public String toBase(URL url) {
/* 109 */     return toBase(url.toExternalForm());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\ShpFileType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */