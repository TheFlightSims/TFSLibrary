/*    */ package org.geotools.data.directory;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
/*    */ import java.net.URI;
/*    */ import java.net.URL;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.data.DataAccessFactory;
/*    */ import org.geotools.data.DataStore;
/*    */ import org.geotools.data.DataStoreFactorySpi;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ class FactoryAdapter {
/* 43 */   static final Logger LOGGER = Logging.getLogger(DirectoryTypeCache.class);
/*    */   
/*    */   DataStoreFactorySpi factory;
/*    */   
/*    */   DataAccessFactory.Param fileParam;
/*    */   
/*    */   DataAccessFactory.Param nsParam;
/*    */   
/*    */   public FactoryAdapter(DataStoreFactorySpi factory, DataAccessFactory.Param fileParam, DataAccessFactory.Param nsParam) {
/* 53 */     this.factory = factory;
/* 54 */     this.fileParam = fileParam;
/* 55 */     this.nsParam = nsParam;
/*    */   }
/*    */   
/*    */   public DataStore getStore(File curr, URI namespaceURI) throws IOException {
/* 60 */     Map<String, Serializable> params = new HashMap<String, Serializable>();
/* 61 */     if (this.nsParam != null)
/* 62 */       if (String.class.isAssignableFrom(this.nsParam.type)) {
/* 63 */         params.put(this.nsParam.key, namespaceURI.toString());
/* 64 */       } else if (URI.class.isAssignableFrom(this.nsParam.type)) {
/* 65 */         params.put(this.nsParam.key, namespaceURI);
/*    */       } else {
/* 67 */         throw new RuntimeException("Don't know how to handle namespace param: " + this.nsParam.key);
/*    */       }  
/* 72 */     if (File.class.isAssignableFrom(this.fileParam.type)) {
/* 73 */       params.put(this.fileParam.key, curr);
/* 74 */     } else if (URL.class.isAssignableFrom(this.fileParam.type)) {
/* 75 */       params.put(this.fileParam.key, curr.toURI().toURL());
/*    */     } 
/*    */     try {
/* 78 */       if (this.factory.canProcess(params))
/* 79 */         return this.factory.createDataStore(params); 
/* 80 */     } catch (Exception e) {
/* 81 */       LOGGER.log(Level.FINE, "Factory " + this.factory.getClass() + " reports it can process parameters, " + "but then fails during creation", e);
/*    */     } 
/* 85 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 90 */     return this.factory.getClass().toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\FactoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */