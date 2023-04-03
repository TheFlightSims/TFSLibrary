/*     */ package org.openstreetmap.osmosis.core.database;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class AuthenticationPropertiesLoader {
/*  29 */   private static final Logger LOG = Logger.getLogger(AuthenticationPropertiesLoader.class.getName());
/*     */   
/*     */   private static final String KEY_HOST = "host";
/*     */   
/*     */   private static final String KEY_DATABASE = "database";
/*     */   
/*     */   private static final String KEY_USER = "user";
/*     */   
/*     */   private static final String KEY_PASSWORD = "password";
/*     */   
/*     */   private static final String KEY_DBTYPE = "dbType";
/*     */   
/*     */   private final File propertiesFile;
/*     */   
/*     */   private Properties properties;
/*     */   
/*     */   public AuthenticationPropertiesLoader(File propertiesFile) {
/*  52 */     this.propertiesFile = propertiesFile;
/*     */   }
/*     */   
/*     */   private Properties loadProperties(File configFile) {
/*  57 */     FileInputStream fileInputStream = null;
/*  59 */     Properties loadedProperties = new Properties();
/*     */     try {
/*  62 */       fileInputStream = new FileInputStream(configFile);
/*  64 */       loadedProperties.load(fileInputStream);
/*  66 */     } catch (IOException e) {
/*  67 */       throw new OsmosisRuntimeException("Unable to load properties from config file " + configFile + ".", e);
/*     */     } finally {
/*  69 */       if (fileInputStream != null)
/*     */         try {
/*  71 */           fileInputStream.close();
/*  72 */         } catch (IOException e) {
/*  73 */           LOG.log(Level.WARNING, "Unable to close input stream for properties file " + configFile + ".", e);
/*     */         }  
/*     */     } 
/*  78 */     return loadedProperties;
/*     */   }
/*     */   
/*     */   public void updateLoginCredentials(DatabaseLoginCredentials loginCredentials) {
/*  88 */     if (this.properties == null)
/*  89 */       this.properties = loadProperties(this.propertiesFile); 
/*  92 */     if (this.properties.containsKey("host"))
/*  93 */       loginCredentials.setHost(this.properties.getProperty("host")); 
/*  95 */     if (this.properties.containsKey("database"))
/*  96 */       loginCredentials.setDatabase(this.properties.getProperty("database")); 
/*  98 */     if (this.properties.containsKey("user"))
/*  99 */       loginCredentials.setUser(this.properties.getProperty("user")); 
/* 101 */     if (this.properties.containsKey("password"))
/* 102 */       loginCredentials.setPassword(this.properties.getProperty("password")); 
/* 105 */     if (this.properties.containsKey("dbType"))
/* 106 */       loginCredentials.setDbType(this.properties.getProperty("dbType")); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\AuthenticationPropertiesLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */