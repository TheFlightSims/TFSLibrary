/*     */ package org.postgresql.fastpath;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Hashtable;
/*     */ import org.postgresql.core.BaseConnection;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.QueryExecutor;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class Fastpath {
/*     */   private static final long NUM_OIDS = 4294967296L;
/*     */   
/*  40 */   private final Hashtable func = new Hashtable<Object, Object>();
/*     */   
/*     */   private final QueryExecutor executor;
/*     */   
/*     */   private final BaseConnection connection;
/*     */   
/*     */   public Fastpath(BaseConnection conn) {
/*  51 */     this.connection = conn;
/*  52 */     this.executor = conn.getQueryExecutor();
/*     */   }
/*     */   
/*     */   public Object fastpath(int fnId, boolean resultType, FastpathArg[] args) throws SQLException {
/*  67 */     ParameterList params = this.executor.createFastpathParameters(args.length);
/*  68 */     for (int i = 0; i < args.length; i++)
/*  70 */       args[i].populateParameter(params, i + 1); 
/*  74 */     byte[] returnValue = this.executor.fastpathCall(fnId, params, this.connection.getAutoCommit());
/*  77 */     if (!resultType || returnValue == null)
/*  78 */       return returnValue; 
/*  80 */     if (returnValue.length != 4)
/*  81 */       throw new PSQLException(GT.tr("Fastpath call {0} - No result was returned and we expected an integer.", new Integer(fnId)), PSQLState.NO_DATA); 
/*  84 */     return new Integer(returnValue[3] & 0xFF | (returnValue[2] & 0xFF) << 8 | (returnValue[1] & 0xFF) << 16 | (returnValue[0] & 0xFF) << 24);
/*     */   }
/*     */   
/*     */   public Object fastpath(String name, boolean resulttype, FastpathArg[] args) throws SQLException {
/* 112 */     if (this.connection.getLogger().logDebug())
/* 113 */       this.connection.getLogger().debug("Fastpath: calling " + name); 
/* 114 */     return fastpath(getID(name), resulttype, args);
/*     */   }
/*     */   
/*     */   public int getInteger(String name, FastpathArg[] args) throws SQLException {
/* 126 */     Integer i = (Integer)fastpath(name, true, args);
/* 127 */     if (i == null)
/* 128 */       throw new PSQLException(GT.tr("Fastpath call {0} - No result was returned and we expected an integer.", name), PSQLState.NO_DATA); 
/* 130 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public long getOID(String name, FastpathArg[] args) throws SQLException {
/* 141 */     long oid = getInteger(name, args);
/* 142 */     if (oid < 0L)
/* 143 */       oid += 4294967296L; 
/* 144 */     return oid;
/*     */   }
/*     */   
/*     */   public byte[] getData(String name, FastpathArg[] args) throws SQLException {
/* 156 */     return (byte[])fastpath(name, false, args);
/*     */   }
/*     */   
/*     */   public void addFunction(String name, int fnid) {
/* 172 */     this.func.put(name, new Integer(fnid));
/*     */   }
/*     */   
/*     */   public void addFunctions(ResultSet rs) throws SQLException {
/* 209 */     while (rs.next())
/* 211 */       this.func.put(rs.getString(1), new Integer(rs.getInt(2))); 
/*     */   }
/*     */   
/*     */   public int getID(String name) throws SQLException {
/* 227 */     Integer id = (Integer)this.func.get(name);
/* 236 */     if (id == null)
/* 237 */       throw new PSQLException(GT.tr("The fastpath function {0} is unknown.", name), PSQLState.UNEXPECTED_ERROR); 
/* 239 */     return id.intValue();
/*     */   }
/*     */   
/*     */   public static FastpathArg createOIDArg(long oid) {
/* 250 */     if (oid > 2147483647L)
/* 251 */       oid -= 4294967296L; 
/* 252 */     return new FastpathArg((int)oid);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\fastpath\Fastpath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */