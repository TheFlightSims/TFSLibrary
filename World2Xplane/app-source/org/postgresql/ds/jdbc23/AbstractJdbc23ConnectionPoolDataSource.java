/*     */ package org.postgresql.ds.jdbc23;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import javax.sql.PooledConnection;
/*     */ import org.postgresql.Driver;
/*     */ import org.postgresql.ds.PGPooledConnection;
/*     */ import org.postgresql.ds.common.BaseDataSource;
/*     */ 
/*     */ public class AbstractJdbc23ConnectionPoolDataSource extends BaseDataSource implements Serializable {
/*     */   private boolean defaultAutoCommit = true;
/*     */   
/*     */   public String getDescription() {
/*  48 */     return "ConnectionPoolDataSource from " + Driver.getVersion();
/*     */   }
/*     */   
/*     */   public PooledConnection getPooledConnection() throws SQLException {
/*  60 */     return (PooledConnection)new PGPooledConnection(getConnection(), this.defaultAutoCommit);
/*     */   }
/*     */   
/*     */   public PooledConnection getPooledConnection(String user, String password) throws SQLException {
/*  72 */     return (PooledConnection)new PGPooledConnection(getConnection(user, password), this.defaultAutoCommit);
/*     */   }
/*     */   
/*     */   public boolean isDefaultAutoCommit() {
/*  82 */     return this.defaultAutoCommit;
/*     */   }
/*     */   
/*     */   public void setDefaultAutoCommit(boolean defaultAutoCommit) {
/*  92 */     this.defaultAutoCommit = defaultAutoCommit;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  97 */     writeBaseObject(out);
/*  98 */     out.writeBoolean(this.defaultAutoCommit);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 103 */     readBaseObject(in);
/* 104 */     this.defaultAutoCommit = in.readBoolean();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ds\jdbc23\AbstractJdbc23ConnectionPoolDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */