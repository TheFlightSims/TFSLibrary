/*    */ package org.postgresql.jdbc3;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Savepoint;
/*    */ import org.postgresql.core.Utils;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class PSQLSavepoint implements Savepoint {
/*    */   private boolean _isValid;
/*    */   
/*    */   private boolean _isNamed;
/*    */   
/*    */   private int _id;
/*    */   
/*    */   private String _name;
/*    */   
/*    */   public PSQLSavepoint(int id) {
/* 27 */     this._isValid = true;
/* 28 */     this._isNamed = false;
/* 29 */     this._id = id;
/*    */   }
/*    */   
/*    */   public PSQLSavepoint(String name) {
/* 33 */     this._isValid = true;
/* 34 */     this._isNamed = true;
/* 35 */     this._name = name;
/*    */   }
/*    */   
/*    */   public int getSavepointId() throws SQLException {
/* 39 */     if (!this._isValid)
/* 40 */       throw new PSQLException(GT.tr("Cannot reference a savepoint after it has been released."), PSQLState.INVALID_SAVEPOINT_SPECIFICATION); 
/* 43 */     if (this._isNamed)
/* 44 */       throw new PSQLException(GT.tr("Cannot retrieve the id of a named savepoint."), PSQLState.WRONG_OBJECT_TYPE); 
/* 47 */     return this._id;
/*    */   }
/*    */   
/*    */   public String getSavepointName() throws SQLException {
/* 51 */     if (!this._isValid)
/* 52 */       throw new PSQLException(GT.tr("Cannot reference a savepoint after it has been released."), PSQLState.INVALID_SAVEPOINT_SPECIFICATION); 
/* 55 */     if (!this._isNamed)
/* 56 */       throw new PSQLException(GT.tr("Cannot retrieve the name of an unnamed savepoint."), PSQLState.WRONG_OBJECT_TYPE); 
/* 59 */     return this._name;
/*    */   }
/*    */   
/*    */   public void invalidate() {
/* 63 */     this._isValid = false;
/*    */   }
/*    */   
/*    */   public String getPGName() throws SQLException {
/* 67 */     if (!this._isValid)
/* 68 */       throw new PSQLException(GT.tr("Cannot reference a savepoint after it has been released."), PSQLState.INVALID_SAVEPOINT_SPECIFICATION); 
/* 71 */     if (this._isNamed)
/* 76 */       return Utils.appendEscapedIdentifier(null, this._name).toString(); 
/* 79 */     return "JDBC_SAVEPOINT_" + this._id;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\jdbc3\PSQLSavepoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */