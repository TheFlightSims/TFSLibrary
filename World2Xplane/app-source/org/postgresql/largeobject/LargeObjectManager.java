/*     */ package org.postgresql.largeobject;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.fastpath.Fastpath;
/*     */ import org.postgresql.fastpath.FastpathArg;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class LargeObjectManager {
/*     */   private Fastpath fp;
/*     */   
/*     */   private BaseConnection conn;
/*     */   
/*     */   public static final int WRITE = 131072;
/*     */   
/*     */   public static final int READ = 262144;
/*     */   
/*     */   public static final int READWRITE = 393216;
/*     */   
/*     */   private LargeObjectManager() {}
/*     */   
/*     */   public LargeObjectManager(BaseConnection conn) throws SQLException {
/* 103 */     this.conn = conn;
/* 105 */     this.fp = conn.getFastpathAPI();
/* 112 */     if (conn.getMetaData().supportsSchemasInTableDefinitions()) {
/* 114 */       sql = "SELECT p.proname,p.oid  FROM pg_catalog.pg_proc p, pg_catalog.pg_namespace n  WHERE p.pronamespace=n.oid AND n.nspname='pg_catalog' AND (";
/*     */     } else {
/* 120 */       sql = "SELECT proname,oid FROM pg_proc WHERE ";
/*     */     } 
/* 122 */     String sql = sql + " proname = 'lo_open' or proname = 'lo_close' or proname = 'lo_creat' or proname = 'lo_unlink' or proname = 'lo_lseek' or proname = 'lo_tell' or proname = 'loread' or proname = 'lowrite' or proname = 'lo_truncate'";
/* 132 */     if (conn.getMetaData().supportsSchemasInTableDefinitions())
/* 134 */       sql = sql + ")"; 
/* 137 */     ResultSet res = conn.createStatement().executeQuery(sql);
/* 139 */     if (res == null)
/* 140 */       throw new PSQLException(GT.tr("Failed to initialize LargeObject API"), PSQLState.SYSTEM_ERROR); 
/* 142 */     this.fp.addFunctions(res);
/* 143 */     res.close();
/* 145 */     conn.getLogger().debug("Large Object initialised");
/*     */   }
/*     */   
/*     */   public LargeObject open(int oid) throws SQLException {
/* 159 */     return open(oid);
/*     */   }
/*     */   
/*     */   public LargeObject open(long oid) throws SQLException {
/* 172 */     return open(oid, 393216);
/*     */   }
/*     */   
/*     */   public LargeObject open(int oid, int mode) throws SQLException {
/* 186 */     return open(oid, mode);
/*     */   }
/*     */   
/*     */   public LargeObject open(long oid, int mode) throws SQLException {
/* 199 */     if (this.conn.getAutoCommit())
/* 200 */       throw new PSQLException(GT.tr("Large Objects may not be used in auto-commit mode."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/* 202 */     return new LargeObject(this.fp, oid, mode);
/*     */   }
/*     */   
/*     */   public int create() throws SQLException {
/* 216 */     return create(393216);
/*     */   }
/*     */   
/*     */   public long createLO() throws SQLException {
/* 228 */     return createLO(393216);
/*     */   }
/*     */   
/*     */   public long createLO(int mode) throws SQLException {
/* 240 */     if (this.conn.getAutoCommit())
/* 241 */       throw new PSQLException(GT.tr("Large Objects may not be used in auto-commit mode."), PSQLState.NO_ACTIVE_SQL_TRANSACTION); 
/* 243 */     FastpathArg[] args = new FastpathArg[1];
/* 244 */     args[0] = new FastpathArg(mode);
/* 245 */     return this.fp.getOID("lo_creat", args);
/*     */   }
/*     */   
/*     */   public int create(int mode) throws SQLException {
/* 258 */     long oid = createLO(mode);
/* 259 */     return (int)oid;
/*     */   }
/*     */   
/*     */   public void delete(long oid) throws SQLException {
/* 270 */     FastpathArg[] args = new FastpathArg[1];
/* 271 */     args[0] = Fastpath.createOIDArg(oid);
/* 272 */     this.fp.fastpath("lo_unlink", false, args);
/*     */   }
/*     */   
/*     */   public void unlink(int oid) throws SQLException {
/* 287 */     delete(oid);
/*     */   }
/*     */   
/*     */   public void unlink(long oid) throws SQLException {
/* 301 */     delete(oid);
/*     */   }
/*     */   
/*     */   public void delete(int oid) throws SQLException {
/* 313 */     delete(oid);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\largeobject\LargeObjectManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */