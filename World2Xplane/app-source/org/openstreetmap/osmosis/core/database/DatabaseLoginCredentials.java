/*     */ package org.openstreetmap.osmosis.core.database;
/*     */ 
/*     */ public class DatabaseLoginCredentials {
/*     */   private String datasourceJndiLocation;
/*     */   
/*     */   private String host;
/*     */   
/*     */   private String database;
/*     */   
/*     */   private String user;
/*     */   
/*     */   private String password;
/*     */   
/*     */   private boolean forceUtf8;
/*     */   
/*     */   private boolean profileSql;
/*     */   
/*     */   private DatabaseType dbType;
/*     */   
/*     */   public DatabaseLoginCredentials(String datasourceJndiLocation) {
/*  28 */     this.datasourceJndiLocation = datasourceJndiLocation;
/*     */   }
/*     */   
/*     */   public DatabaseLoginCredentials(String host, String database, String user, String password, boolean forceUtf8, boolean profileSql, DatabaseType dbType) {
/*  54 */     this.host = host;
/*  55 */     this.database = database;
/*  56 */     this.user = user;
/*  57 */     this.password = password;
/*  58 */     this.forceUtf8 = forceUtf8;
/*  59 */     this.profileSql = profileSql;
/*  60 */     this.dbType = dbType;
/*     */   }
/*     */   
/*     */   public String getDatasourceJndiLocation() {
/*  71 */     return this.datasourceJndiLocation;
/*     */   }
/*     */   
/*     */   public String getHost() {
/*  81 */     return this.host;
/*     */   }
/*     */   
/*     */   public void setHost(String host) {
/*  90 */     this.host = host;
/*     */   }
/*     */   
/*     */   public String getDatabase() {
/*  99 */     return this.database;
/*     */   }
/*     */   
/*     */   public void setDatabase(String database) {
/* 108 */     this.database = database;
/*     */   }
/*     */   
/*     */   public String getUser() {
/* 117 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(String user) {
/* 126 */     this.user = user;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 135 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 144 */     this.password = password;
/*     */   }
/*     */   
/*     */   public boolean getForceUtf8() {
/* 153 */     return this.forceUtf8;
/*     */   }
/*     */   
/*     */   public void setForceUtf8(boolean forceUtf8) {
/* 162 */     this.forceUtf8 = forceUtf8;
/*     */   }
/*     */   
/*     */   public boolean getProfileSql() {
/* 171 */     return this.profileSql;
/*     */   }
/*     */   
/*     */   public void setProfileSql(boolean profileSql) {
/* 180 */     this.profileSql = profileSql;
/*     */   }
/*     */   
/*     */   public DatabaseType getDbType() {
/* 189 */     return this.dbType;
/*     */   }
/*     */   
/*     */   public void setDbType(DatabaseType dbType) {
/* 198 */     this.dbType = dbType;
/*     */   }
/*     */   
/*     */   public void setDbType(String property) {
/* 209 */     this.dbType = DatabaseType.fromString(property);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\DatabaseLoginCredentials.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */