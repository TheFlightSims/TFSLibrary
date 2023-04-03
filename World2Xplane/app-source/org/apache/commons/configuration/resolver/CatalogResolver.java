/*     */ package org.apache.commons.configuration.resolver;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.FileNameMap;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Vector;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.configuration.ConfigurationUtils;
/*     */ import org.apache.commons.configuration.FileSystem;
/*     */ import org.apache.commons.lang.text.StrSubstitutor;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xml.resolver.readers.CatalogReader;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class CatalogResolver implements EntityResolver {
/*     */   private static final int DEBUG_ALL = 9;
/*     */   
/*     */   private static final int DEBUG_NORMAL = 4;
/*     */   
/*     */   private static final int DEBUG_NONE = 0;
/*     */   
/*  67 */   protected CatalogManager manager = new CatalogManager();
/*     */   
/*  72 */   protected FileSystem fs = FileSystem.getDefaultFileSystem();
/*     */   
/*     */   private org.apache.xml.resolver.tools.CatalogResolver resolver;
/*     */   
/*     */   private Log log;
/*     */   
/*     */   public CatalogResolver() {
/*  89 */     this.manager.setIgnoreMissingProperties(true);
/*  90 */     this.manager.setUseStaticCatalog(false);
/*  91 */     this.manager.setFileSystem(this.fs);
/*  92 */     setLogger(null);
/*     */   }
/*     */   
/*     */   public void setCatalogFiles(String catalogs) {
/* 102 */     this.manager.setCatalogFiles(catalogs);
/*     */   }
/*     */   
/*     */   public void setFileSystem(FileSystem fileSystem) {
/* 111 */     this.fs = fileSystem;
/* 112 */     this.manager.setFileSystem(fileSystem);
/*     */   }
/*     */   
/*     */   public void setBaseDir(String baseDir) {
/* 121 */     this.manager.setBaseDir(baseDir);
/*     */   }
/*     */   
/*     */   public void setSubstitutor(StrSubstitutor substitutor) {
/* 130 */     this.manager.setSubstitutor(substitutor);
/*     */   }
/*     */   
/*     */   public void setDebug(boolean debug) {
/* 139 */     if (debug) {
/* 141 */       this.manager.setVerbosity(9);
/*     */     } else {
/* 145 */       this.manager.setVerbosity(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/* 178 */     String resolved = getResolver().getResolvedEntity(publicId, systemId);
/* 180 */     if (resolved != null) {
/* 182 */       String badFilePrefix = "file://";
/* 183 */       String correctFilePrefix = "file:///";
/* 186 */       if (resolved.startsWith(badFilePrefix) && !resolved.startsWith(correctFilePrefix))
/* 188 */         resolved = correctFilePrefix + resolved.substring(badFilePrefix.length()); 
/*     */       try {
/* 193 */         InputStream is = this.fs.getInputStream(null, resolved);
/* 194 */         InputSource iSource = new InputSource(resolved);
/* 195 */         iSource.setPublicId(publicId);
/* 196 */         iSource.setByteStream(is);
/* 197 */         return iSource;
/* 199 */       } catch (Exception e) {
/* 201 */         this.log.warn("Failed to create InputSource for " + resolved + " (" + e.toString() + ")");
/* 203 */         return null;
/*     */       } 
/*     */     } 
/* 207 */     return null;
/*     */   }
/*     */   
/*     */   public Log getLogger() {
/* 217 */     return this.log;
/*     */   }
/*     */   
/*     */   public void setLogger(Log log) {
/* 231 */     this.log = (log != null) ? log : LogFactory.getLog(CatalogResolver.class);
/*     */   }
/*     */   
/*     */   private synchronized org.apache.xml.resolver.tools.CatalogResolver getResolver() {
/* 236 */     if (this.resolver == null)
/* 238 */       this.resolver = new org.apache.xml.resolver.tools.CatalogResolver(this.manager); 
/* 240 */     return this.resolver;
/*     */   }
/*     */   
/*     */   public static class CatalogManager extends org.apache.xml.resolver.CatalogManager {
/*     */     private static org.apache.xml.resolver.Catalog staticCatalog;
/*     */     
/*     */     private FileSystem fs;
/*     */     
/* 255 */     private String baseDir = System.getProperty("user.dir");
/*     */     
/*     */     private StrSubstitutor substitutor;
/*     */     
/*     */     public void setFileSystem(FileSystem fileSystem) {
/* 266 */       this.fs = fileSystem;
/*     */     }
/*     */     
/*     */     public FileSystem getFileSystem() {
/* 275 */       return this.fs;
/*     */     }
/*     */     
/*     */     public void setBaseDir(String baseDir) {
/* 284 */       if (baseDir != null)
/* 286 */         this.baseDir = baseDir; 
/*     */     }
/*     */     
/*     */     public String getBaseDir() {
/* 296 */       return this.baseDir;
/*     */     }
/*     */     
/*     */     public void setSubstitutor(StrSubstitutor substitutor) {
/* 301 */       this.substitutor = substitutor;
/*     */     }
/*     */     
/*     */     public StrSubstitutor getStrSubstitutor() {
/* 306 */       return this.substitutor;
/*     */     }
/*     */     
/*     */     public org.apache.xml.resolver.Catalog getPrivateCatalog() {
/* 320 */       org.apache.xml.resolver.Catalog catalog = staticCatalog;
/* 322 */       if (catalog == null || !getUseStaticCatalog()) {
/*     */         try {
/* 326 */           catalog = new CatalogResolver.Catalog();
/* 327 */           catalog.setCatalogManager(this);
/* 328 */           catalog.setupReaders();
/* 329 */           catalog.loadSystemCatalogs();
/* 331 */         } catch (Exception ex) {
/* 333 */           ex.printStackTrace();
/*     */         } 
/* 336 */         if (getUseStaticCatalog())
/* 338 */           staticCatalog = catalog; 
/*     */       } 
/* 342 */       return catalog;
/*     */     }
/*     */     
/*     */     public org.apache.xml.resolver.Catalog getCatalog() {
/* 354 */       return getPrivateCatalog();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Catalog extends org.apache.xml.resolver.Catalog {
/*     */     private FileSystem fs;
/*     */     
/* 367 */     private FileNameMap fileNameMap = URLConnection.getFileNameMap();
/*     */     
/*     */     public void loadSystemCatalogs() throws IOException {
/* 375 */       this.fs = ((CatalogResolver.CatalogManager)this.catalogManager).getFileSystem();
/* 376 */       String base = ((CatalogResolver.CatalogManager)this.catalogManager).getBaseDir();
/* 378 */       Vector catalogs = this.catalogManager.getCatalogFiles();
/* 379 */       if (catalogs != null) {
/* 381 */         int count = 0;
/*     */         while (true) {
/*     */           String fileName;
/* 381 */           if (count < catalogs.size()) {
/* 383 */             fileName = catalogs.elementAt(count);
/* 385 */             URL url = null;
/* 386 */             InputStream is = null;
/*     */             try {
/* 390 */               url = ConfigurationUtils.locate(this.fs, base, fileName);
/* 391 */               if (url != null)
/* 393 */                 is = this.fs.getInputStream(url); 
/* 396 */             } catch (ConfigurationException ce) {
/* 398 */               String name = (url == null) ? fileName : url.toString();
/* 400 */               this.catalogManager.debug.message(9, "Unable to get input stream for " + name + ". " + ce.getMessage());
/*     */             } 
/* 403 */             if (is != null) {
/* 405 */               String mimeType = this.fileNameMap.getContentTypeFor(fileName);
/*     */               try {
/* 408 */                 if (mimeType != null) {
/* 410 */                   parseCatalog(mimeType, is);
/* 423 */                   is.close();
/*     */                 } else {
/* 423 */                   is.close();
/* 426 */                   parseCatalog(base, fileName);
/*     */                 } 
/*     */               } catch (Exception ex) {
/*     */                 this.catalogManager.debug.message(9, "Exception caught parsing input stream for " + fileName + ". " + ex.getMessage());
/*     */               } finally {
/*     */                 is.close();
/*     */               } 
/*     */             } 
/*     */           } else {
/*     */             break;
/*     */           } 
/* 426 */           parseCatalog(base, fileName);
/*     */           count++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void parseCatalog(String baseDir, String fileName) throws IOException {
/* 440 */       this.base = ConfigurationUtils.locate(this.fs, baseDir, fileName);
/* 441 */       this.catalogCwd = this.base;
/* 442 */       this.default_override = this.catalogManager.getPreferPublic();
/* 443 */       this.catalogManager.debug.message(4, "Parse catalog: " + fileName);
/* 445 */       boolean parsed = false;
/* 447 */       for (int count = 0; !parsed && count < this.readerArr.size(); count++) {
/*     */         InputStream inStream;
/* 449 */         CatalogReader reader = this.readerArr.get(count);
/*     */         try {
/*     */         
/* 456 */         } catch (Exception ex) {
/*     */           break;
/*     */         } 
/*     */       } 
/* 496 */       if (parsed)
/* 498 */         parsePendingCatalogs(); 
/*     */     }
/*     */     
/*     */     protected String normalizeURI(String uriref) {
/* 510 */       StrSubstitutor substitutor = ((CatalogResolver.CatalogManager)this.catalogManager).getStrSubstitutor();
/* 511 */       String resolved = (substitutor != null) ? substitutor.replace(uriref) : uriref;
/* 512 */       return super.normalizeURI(resolved);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\resolver\CatalogResolver.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */