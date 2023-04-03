/*     */ package org.openstreetmap.osmosis.core.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class PropertiesPersister {
/*  29 */   private static final Logger LOG = Logger.getLogger(PropertiesPersister.class.getName());
/*     */   
/*     */   private AtomicFileCreator atomicFileCreator;
/*     */   
/*     */   public PropertiesPersister(File propertiesFile) {
/*  42 */     this.atomicFileCreator = new AtomicFileCreator(propertiesFile);
/*     */   }
/*     */   
/*     */   public Properties load() {
/*  53 */     FileInputStream fileInputStream = null;
/*     */     try {
/*  59 */       fileInputStream = new FileInputStream(this.atomicFileCreator.getFile());
/*  60 */       Reader reader = new InputStreamReader(new BufferedInputStream(fileInputStream), Charset.forName("UTF-8"));
/*  62 */       Properties properties = new Properties();
/*  63 */       properties.load(reader);
/*  65 */       fileInputStream.close();
/*  66 */       fileInputStream = null;
/*  68 */       return properties;
/*  70 */     } catch (IOException e) {
/*  71 */       throw new OsmosisRuntimeException("Unable to read the properties from file " + this.atomicFileCreator.getFile() + ".", e);
/*     */     } finally {
/*  74 */       if (fileInputStream != null)
/*     */         try {
/*  76 */           fileInputStream.close();
/*  77 */         } catch (Exception e) {
/*  78 */           LOG.log(Level.WARNING, "Unable to close properties file " + this.atomicFileCreator.getFile() + ".", e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<String, String> loadMap() {
/*  92 */     return new HashMap<String, String>(load());
/*     */   }
/*     */   
/*     */   public void store(Properties properties) {
/* 103 */     FileOutputStream fileOutputStream = null;
/*     */     try {
/* 108 */       fileOutputStream = new FileOutputStream(this.atomicFileCreator.getTmpFile());
/* 109 */       Writer writer = new OutputStreamWriter(new BufferedOutputStream(fileOutputStream));
/* 111 */       properties.store(writer, (String)null);
/* 113 */       writer.close();
/* 115 */       this.atomicFileCreator.renameTmpFileToCurrent();
/* 117 */     } catch (IOException e) {
/* 118 */       throw new OsmosisRuntimeException("Unable to write the properties to temporary file " + this.atomicFileCreator.getTmpFile() + ".", e);
/*     */     } finally {
/* 121 */       if (fileOutputStream != null)
/*     */         try {
/* 123 */           fileOutputStream.close();
/* 124 */         } catch (Exception e) {
/* 125 */           LOG.log(Level.WARNING, "Unable to close temporary state file " + this.atomicFileCreator.getTmpFile() + ".", e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void store(Map<String, String> propertiesMap) {
/* 140 */     Properties properties = new Properties();
/* 141 */     properties.putAll(propertiesMap);
/* 142 */     store(properties);
/*     */   }
/*     */   
/*     */   public boolean exists() {
/* 152 */     return this.atomicFileCreator.exists();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\PropertiesPersister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */