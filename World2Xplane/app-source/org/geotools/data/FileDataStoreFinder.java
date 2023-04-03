/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class FileDataStoreFinder {
/*  51 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   public static FileDataStore getDataStore(File file) throws IOException {
/*  69 */     return getDataStore(file.toURI().toURL());
/*     */   }
/*     */   
/*     */   public static FileDataStore getDataStore(URL url) throws IOException {
/*  86 */     Iterator<FileDataStoreFactorySpi> ps = getAvailableDataStores();
/*  88 */     while (ps.hasNext()) {
/*  89 */       FileDataStoreFactorySpi fac = ps.next();
/*  90 */       if (!fac.isAvailable())
/*     */         continue; 
/*     */       try {
/*  94 */         if (fac.canProcess(url))
/*  95 */           return fac.createDataStore(url); 
/*  97 */       } catch (Throwable t) {
/* 101 */         LOGGER.log(Level.WARNING, "Could not aquire " + fac.getDescription() + ":" + t, t);
/*     */       } 
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public static FileDataStoreFactorySpi getDataStoreFactory(String extension) {
/* 120 */     String extension2 = null;
/* 121 */     if (!extension.startsWith("."))
/* 122 */       extension2 = "." + extension; 
/* 124 */     Iterator<FileDataStoreFactorySpi> ps = getAvailableDataStores();
/* 125 */     while (ps.hasNext()) {
/* 126 */       FileDataStoreFactorySpi fac = ps.next();
/* 127 */       if (!fac.isAvailable())
/*     */         continue; 
/*     */       try {
/* 131 */         for (String ext : fac.getFileExtensions()) {
/* 132 */           if (extension.equalsIgnoreCase(ext))
/* 133 */             return fac; 
/* 135 */           if (extension2 != null && extension2.equalsIgnoreCase(ext))
/* 136 */             return fac; 
/*     */         } 
/* 139 */       } catch (Throwable t) {
/* 143 */         LOGGER.log(Level.WARNING, "Could not aquire " + fac.getDescription() + ":" + t, t);
/*     */       } 
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public static Iterator<FileDataStoreFactorySpi> getAvailableDataStores() {
/* 164 */     Set<FileDataStoreFactorySpi> availableDS = new HashSet();
/* 166 */     Set all = CommonFactoryFinder.getFileDataStoreFactories(null);
/* 168 */     for (Iterator<FileDataStoreFactorySpi> it = all.iterator(); it.hasNext(); ) {
/* 169 */       FileDataStoreFactorySpi dsFactory = it.next();
/* 172 */       if (dsFactory.isAvailable())
/* 173 */         availableDS.add(dsFactory); 
/*     */     } 
/* 177 */     return availableDS.iterator();
/*     */   }
/*     */   
/*     */   public static Set<String> getAvailableFileExtentions() {
/* 186 */     Set<String> extentions = new HashSet<String>();
/* 188 */     Iterator<FileDataStoreFactorySpi> ps = getAvailableDataStores();
/* 189 */     while (ps.hasNext()) {
/* 190 */       FileDataStoreFactorySpi fac = ps.next();
/*     */       try {
/* 192 */         for (String fileExtention : fac.getFileExtensions())
/* 193 */           extentions.add(fileExtention); 
/* 195 */       } catch (Throwable t) {
/* 199 */         LOGGER.log(Level.WARNING, "Could not aquire " + fac.getDescription() + ":" + t, t);
/*     */       } 
/*     */     } 
/* 207 */     return extentions;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FileDataStoreFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */