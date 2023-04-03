/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class SystemConfiguration extends MapConfiguration {
/* 35 */   private static Log log = LogFactory.getLog(SystemConfiguration.class);
/*    */   
/*    */   public SystemConfiguration() {
/* 44 */     super(System.getProperties());
/*    */   }
/*    */   
/*    */   public static void setSystemProperties(String fileName) throws Exception {
/* 55 */     setSystemProperties(null, fileName);
/*    */   }
/*    */   
/*    */   public static void setSystemProperties(String basePath, String fileName) throws Exception {
/* 67 */     PropertiesConfiguration config = fileName.endsWith(".xml") ? new XMLPropertiesConfiguration() : new PropertiesConfiguration();
/* 69 */     if (basePath != null)
/* 71 */       config.setBasePath(basePath); 
/* 73 */     config.setFileName(fileName);
/* 74 */     config.load();
/* 75 */     setSystemProperties(config);
/*    */   }
/*    */   
/*    */   public static void setSystemProperties(PropertiesConfiguration systemConfig) {
/* 85 */     Iterator iter = systemConfig.getKeys();
/* 86 */     while (iter.hasNext()) {
/* 88 */       String key = iter.next();
/* 89 */       String value = (String)systemConfig.getProperty(key);
/* 90 */       if (log.isDebugEnabled())
/* 92 */         log.debug("Setting system property " + key + " to " + value); 
/* 94 */       System.setProperty(key, value);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\SystemConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */