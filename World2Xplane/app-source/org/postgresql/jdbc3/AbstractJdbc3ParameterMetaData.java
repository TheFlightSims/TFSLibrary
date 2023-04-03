/*    */ package org.postgresql.jdbc3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import org.postgresql.core.BaseConnection;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public abstract class AbstractJdbc3ParameterMetaData {
/*    */   private final BaseConnection _connection;
/*    */   
/*    */   private final int[] _oids;
/*    */   
/*    */   public AbstractJdbc3ParameterMetaData(BaseConnection connection, int[] oids) {
/* 27 */     this._connection = connection;
/* 28 */     this._oids = oids;
/*    */   }
/*    */   
/*    */   public String getParameterClassName(int param) throws SQLException {
/* 32 */     checkParamIndex(param);
/* 33 */     return this._connection.getTypeInfo().getJavaClass(this._oids[param - 1]);
/*    */   }
/*    */   
/*    */   public int getParameterCount() {
/* 37 */     return this._oids.length;
/*    */   }
/*    */   
/*    */   public int getParameterMode(int param) throws SQLException {
/* 43 */     checkParamIndex(param);
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */   public int getParameterType(int param) throws SQLException {
/* 48 */     checkParamIndex(param);
/* 49 */     return this._connection.getTypeInfo().getSQLType(this._oids[param - 1]);
/*    */   }
/*    */   
/*    */   public String getParameterTypeName(int param) throws SQLException {
/* 53 */     checkParamIndex(param);
/* 54 */     return this._connection.getTypeInfo().getPGType(this._oids[param - 1]);
/*    */   }
/*    */   
/*    */   public int getPrecision(int param) throws SQLException {
/* 59 */     checkParamIndex(param);
/* 60 */     return 0;
/*    */   }
/*    */   
/*    */   public int getScale(int param) throws SQLException {
/* 65 */     checkParamIndex(param);
/* 66 */     return 0;
/*    */   }
/*    */   
/*    */   public int isNullable(int param) throws SQLException {
/* 71 */     checkParamIndex(param);
/* 72 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean isSigned(int param) throws SQLException {
/* 77 */     checkParamIndex(param);
/* 78 */     return this._connection.getTypeInfo().isSigned(this._oids[param - 1]);
/*    */   }
/*    */   
/*    */   private void checkParamIndex(int param) throws PSQLException {
/* 82 */     if (param < 1 || param > this._oids.length)
/* 83 */       throw new PSQLException(GT.tr("The parameter index is out of range: {0}, number of parameters: {1}.", new Object[] { new Integer(param), new Integer(this._oids.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\AbstractJdbc3ParameterMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */