/*     */ package org.postgresql.ds.common;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.Name;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.spi.ObjectFactory;
/*     */ import org.postgresql.ds.PGConnectionPoolDataSource;
/*     */ import org.postgresql.ds.PGPoolingDataSource;
/*     */ import org.postgresql.ds.PGSimpleDataSource;
/*     */ 
/*     */ public class PGObjectFactory implements ObjectFactory {
/*     */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment) throws Exception {
/*  37 */     Reference ref = (Reference)obj;
/*  38 */     String className = ref.getClassName();
/*  39 */     if (className.equals("org.postgresql.ds.PGSimpleDataSource") || className.equals("org.postgresql.jdbc2.optional.SimpleDataSource") || className.equals("org.postgresql.jdbc3.Jdbc3SimpleDataSource"))
/*  43 */       return loadSimpleDataSource(ref); 
/*  45 */     if (className.equals("org.postgresql.ds.PGConnectionPoolDataSource") || className.equals("org.postgresql.jdbc2.optional.ConnectionPool") || className.equals("org.postgresql.jdbc3.Jdbc3ConnectionPool"))
/*  49 */       return loadConnectionPool(ref); 
/*  51 */     if (className.equals("org.postgresql.ds.PGPoolingDataSource") || className.equals("org.postgresql.jdbc2.optional.PoolingDataSource") || className.equals("org.postgresql.jdbc3.Jdbc3PoolingDataSource"))
/*  55 */       return loadPoolingDataSource(ref); 
/*  59 */     return null;
/*     */   }
/*     */   
/*     */   private Object loadPoolingDataSource(Reference ref) {
/*  66 */     String name = getProperty(ref, "dataSourceName");
/*  67 */     PGPoolingDataSource pds = PGPoolingDataSource.getDataSource(name);
/*  68 */     if (pds != null)
/*  70 */       return pds; 
/*  73 */     pds = new PGPoolingDataSource();
/*  74 */     pds.setDataSourceName(name);
/*  75 */     loadBaseDataSource((BaseDataSource)pds, ref);
/*  76 */     String min = getProperty(ref, "initialConnections");
/*  77 */     if (min != null)
/*  79 */       pds.setInitialConnections(Integer.parseInt(min)); 
/*  81 */     String max = getProperty(ref, "maxConnections");
/*  82 */     if (max != null)
/*  84 */       pds.setMaxConnections(Integer.parseInt(max)); 
/*  86 */     return pds;
/*     */   }
/*     */   
/*     */   private Object loadSimpleDataSource(Reference ref) {
/*  91 */     PGSimpleDataSource ds = new PGSimpleDataSource();
/*  92 */     return loadBaseDataSource((BaseDataSource)ds, ref);
/*     */   }
/*     */   
/*     */   private Object loadConnectionPool(Reference ref) {
/*  97 */     PGConnectionPoolDataSource cp = new PGConnectionPoolDataSource();
/*  98 */     return loadBaseDataSource((BaseDataSource)cp, ref);
/*     */   }
/*     */   
/*     */   protected Object loadBaseDataSource(BaseDataSource ds, Reference ref) {
/* 103 */     ds.setDatabaseName(getProperty(ref, "databaseName"));
/* 104 */     ds.setPassword(getProperty(ref, "password"));
/* 105 */     String port = getProperty(ref, "portNumber");
/* 106 */     if (port != null)
/* 108 */       ds.setPortNumber(Integer.parseInt(port)); 
/* 110 */     ds.setServerName(getProperty(ref, "serverName"));
/* 111 */     ds.setUser(getProperty(ref, "user"));
/* 113 */     String prepareThreshold = getProperty(ref, "prepareThreshold");
/* 114 */     if (prepareThreshold != null)
/* 115 */       ds.setPrepareThreshold(Integer.parseInt(prepareThreshold)); 
/* 117 */     return ds;
/*     */   }
/*     */   
/*     */   protected String getProperty(Reference ref, String s) {
/* 122 */     RefAddr addr = ref.get(s);
/* 123 */     if (addr == null)
/* 125 */       return null; 
/* 127 */     return (String)addr.getContent();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\common\PGObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */