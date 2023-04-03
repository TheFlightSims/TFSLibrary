/*     */ package org.jfree.base.config;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class PropertyFileConfiguration extends HierarchicalConfiguration {
/*     */   public void load(String resourceName) {
/*  67 */     InputStream in = ObjectUtilities.getResourceRelativeAsStream(resourceName, PropertyFileConfiguration.class);
/*  69 */     if (in != null) {
/*  71 */       load(in);
/*     */     } else {
/*  75 */       Log.debug("Configuration file not found in the classpath: " + resourceName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load(InputStream in) {
/*  89 */     if (in == null)
/*  91 */       throw new NullPointerException(); 
/*     */     try {
/*  96 */       BufferedInputStream bin = new BufferedInputStream(in);
/*  97 */       Properties p = new Properties();
/*  98 */       p.load(bin);
/*  99 */       getConfiguration().putAll(p);
/* 100 */       bin.close();
/* 102 */     } catch (IOException ioe) {
/* 104 */       Log.warn("Unable to read configuration", ioe);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\config\PropertyFileConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */