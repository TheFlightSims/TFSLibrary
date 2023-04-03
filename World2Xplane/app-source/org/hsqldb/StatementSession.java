package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.User;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.Type;

public class StatementSession extends Statement {
  public static final StatementSession commitNoChainStatement = new StatementSession(11, new Object[] { Boolean.FALSE });
  
  public static final StatementSession rollbackNoChainStatement = new StatementSession(62, new Object[] { Boolean.FALSE });
  
  public static final StatementSession commitAndChainStatement = new StatementSession(11, new Object[] { Boolean.TRUE });
  
  public static final StatementSession rollbackAndChainStatement = new StatementSession(62, new Object[] { Boolean.TRUE });
  
  Expression[] expressions;
  
  Object[] parameters;
  
  StatementSession(int paramInt, Expression[] paramArrayOfExpression) {
    super(paramInt);
    this.expressions = paramArrayOfExpression;
    this.isTransactionStatement = false;
    this.isLogged = false;
    switch (paramInt) {
      case 66:
      case 69:
      case 71:
      case 72:
      case 73:
      case 74:
      case 76:
      case 136:
        this.group = 2008;
        return;
    } 
    throw Error.runtimeError(201, "StateemntSession");
  }
  
  StatementSession(int paramInt, Object[] paramArrayOfObject) {
    super(paramInt);
    this.parameters = paramArrayOfObject;
    this.isTransactionStatement = false;
    this.isLogged = false;
    switch (paramInt) {
      case 74:
        this.group = 2008;
        this.isLogged = true;
        return;
      case 1075:
        this.group = 2011;
        this.isLogged = true;
        return;
      case 1:
        this.group = 2003;
        return;
      case 2:
      case 15:
      case 16:
        this.group = 2010;
        return;
      case 38:
        this.group = 2004;
        return;
      case 37:
      case 39:
      case 40:
        this.group = 2003;
        return;
      case 45:
      case 47:
      case 53:
      case 98:
      case 99:
        this.group = 2003;
        return;
      case 54:
      case 55:
      case 56:
        this.group = 2010;
        return;
      case 22:
        this.group = 2006;
        return;
      case 67:
      case 68:
      case 70:
      case 109:
      case 118:
      case 136:
      case 1064:
      case 1065:
      case 1066:
        this.group = 2011;
        return;
      case 1048:
        this.isLogged = true;
        this.group = 2011;
        return;
      case 11:
      case 57:
      case 62:
      case 63:
      case 75:
      case 111:
      case 1067:
        this.group = 2005;
        return;
      case 32:
      case 1068:
        this.group = 2008;
        return;
    } 
    throw Error.runtimeError(201, "StatementSession");
  }
  
  StatementSession(int paramInt, HsqlNameManager.HsqlName[] paramArrayOfHsqlName1, HsqlNameManager.HsqlName[] paramArrayOfHsqlName2) {
    super(paramInt);
    this.isTransactionStatement = true;
    this.isLogged = false;
    this.readTableNames = paramArrayOfHsqlName1;
    this.writeTableNames = paramArrayOfHsqlName2;
    switch (paramInt) {
      case 1063:
        this.group = 2015;
        return;
    } 
    throw Error.runtimeError(201, "StatementSession");
  }
  
  public Result execute(Session paramSession) {
    Result result;
    try {
      result = getResult(paramSession);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(throwable, null);
    } 
    if (result.isError()) {
      result.getException().setStatementType(this.group, this.type);
      return result;
    } 
    try {
      if (this.isLogged)
        paramSession.database.logger.writeOtherStatement(paramSession, this.sql); 
    } catch (Throwable throwable) {
      return Result.newErrorResult(throwable, this.sql);
    } 
    return result;
  }
  
  Result getResult(Session paramSession) {
    String str;
    boolean bool2;
    Object object;
    boolean bool1;
    ColumnSchema[] arrayOfColumnSchema;
    int i;
    Table table1;
    HsqlNameManager.HsqlName hsqlName;
    long l;
    Grantee grantee;
    HsqlArrayList hsqlArrayList;
    Boolean bool3;
    StatementDMQL statementDMQL;
    Table table2;
    boolean bool = false;
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession)); 
    switch (this.type) {
      case 1:
      case 2:
        return Result.updateZeroResult;
      case 11:
        try {
          boolean bool4 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.commit(bool4);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 15:
      case 16:
        return Result.updateZeroResult;
      case 22:
        paramSession.close();
        return Result.updateZeroResult;
      case 37:
      case 38:
      case 39:
      case 40:
      case 45:
      case 47:
      case 53:
      case 54:
      case 55:
      case 56:
      case 98:
      case 99:
        return Result.updateZeroResult;
      case 1063:
        return Result.updateZeroResult;
      case 57:
        str = (String)this.parameters[0];
        try {
          paramSession.releaseSavepoint(str);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 62:
        bool2 = ((Boolean)this.parameters[0]).booleanValue();
        paramSession.rollback(bool2);
        return Result.updateZeroResult;
      case 1067:
        object = this.parameters[0];
        try {
          paramSession.rollbackToSavepoint((String)object);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 63:
        object = this.parameters[0];
        paramSession.savepoint((String)object);
        return Result.updateZeroResult;
      case 66:
        try {
          object = this.expressions[0].getValue(paramSession);
          object = Type.SQL_VARCHAR.trim(paramSession, object, ' ', true, true);
          return (paramSession.database.getCatalogName()).name.equals(object) ? Result.updateZeroResult : Result.newErrorResult(Error.error(4840), this.sql);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 67:
      case 68:
      case 70:
        return Result.updateZeroResult;
      case 71:
        object = null;
        if (this.expressions[0].getType() == 1 && this.expressions[0].getConstantValueNoCheck(paramSession) == null) {
          paramSession.setZoneSeconds(paramSession.sessionTimeZoneSeconds);
          return Result.updateZeroResult;
        } 
        try {
          object = this.expressions[0].getValue(paramSession);
        } catch (HsqlException hsqlException) {}
        if (object instanceof Result) {
          Result result = (Result)object;
          if (result.isData()) {
            Object[] arrayOfObject = result.getNavigator().getNext();
            boolean bool4 = !result.getNavigator().next() ? true : false;
            if (bool4 && arrayOfObject != null && arrayOfObject[0] != null) {
              object = arrayOfObject[0];
              result.getNavigator().release();
            } else {
              result.getNavigator().release();
              return Result.newErrorResult(Error.error(3409), this.sql);
            } 
          } else {
            return Result.newErrorResult(Error.error(3409), this.sql);
          } 
        } else if (object == null) {
          return Result.newErrorResult(Error.error(3409), this.sql);
        } 
        l = ((IntervalSecondData)object).getSeconds();
        if (-50400L <= l && l <= 50400L) {
          paramSession.setZoneSeconds((int)l);
          return Result.updateZeroResult;
        } 
        return Result.newErrorResult(Error.error(3409), this.sql);
      case 72:
        return Result.updateZeroResult;
      case 69:
        return Result.updateZeroResult;
      case 73:
        grantee = null;
        try {
          object = this.expressions[0].getValue(paramSession);
          if (object != null) {
            object = Type.SQL_VARCHAR.trim(paramSession, object, ' ', true, true);
            grantee = paramSession.database.granteeManager.getRole((String)object);
          } 
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(Error.error(2200), this.sql);
        } 
        if (paramSession.isInMidTransaction())
          return Result.newErrorResult(Error.error(3701), this.sql); 
        if (grantee == null)
          paramSession.setRole(null); 
        if (paramSession.getGrantee().hasRole(grantee)) {
          paramSession.setRole(grantee);
          return Result.updateZeroResult;
        } 
        return Result.newErrorResult(Error.error(2200), this.sql);
      case 74:
        try {
          if (this.expressions == null) {
            object = ((HsqlNameManager.HsqlName)this.parameters[0]).name;
          } else {
            object = this.expressions[0].getValue(paramSession);
          } 
          object = Type.SQL_VARCHAR.trim(paramSession, object, ' ', true, true);
          HsqlNameManager.HsqlName hsqlName1 = paramSession.database.schemaManager.getSchemaHsqlName((String)object);
          paramSession.setCurrentSchemaHsqlName(hsqlName1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 76:
        if (paramSession.isInMidTransaction())
          return Result.newErrorResult(Error.error(3701), this.sql); 
        try {
          String str1;
          User user;
          grantee = null;
          object = this.expressions[0].getValue(paramSession);
          object = Type.SQL_VARCHAR.trim(paramSession, object, ' ', true, true);
          if (this.expressions[1] != null)
            str1 = (String)this.expressions[1].getValue(paramSession); 
          if (str1 == null) {
            user = paramSession.database.userManager.get((String)object);
          } else {
            user = paramSession.database.getUserManager().getUser((String)object, str1);
          } 
          if (user == null)
            throw Error.error(4001); 
          this.sql = user.getConnectUserSQL();
          if (user == paramSession.getGrantee())
            return Result.updateZeroResult; 
          if (str1 == null && !paramSession.isProcessingLog() && user.isAdmin() && !paramSession.getGrantee().isAdmin())
            throw Error.error(4000); 
          if (paramSession.getGrantee().canChangeAuthorisation()) {
            paramSession.setUser(user);
            paramSession.setRole(null);
            paramSession.resetSchema();
            return Result.updateZeroResult;
          } 
          throw Error.error(4000);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 109:
        try {
          if (this.parameters[0] != null) {
            boolean bool4 = ((Boolean)this.parameters[0]).booleanValue();
            paramSession.setReadOnlyDefault(bool4);
          } 
          if (this.parameters[1] != null) {
            int j = ((Integer)this.parameters[1]).intValue();
            paramSession.setIsolationDefault(j);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 136:
        return Result.updateZeroResult;
      case 118:
        return Result.updateZeroResult;
      case 111:
        bool = true;
      case 75:
        try {
          if (this.parameters[0] != null) {
            boolean bool4 = ((Boolean)this.parameters[0]).booleanValue();
            paramSession.setReadOnly(bool4);
          } 
          if (this.parameters[1] != null) {
            int j = ((Integer)this.parameters[1]).intValue();
            paramSession.setIsolation(j);
          } 
          if (bool)
            paramSession.startTransaction(); 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1064:
        bool1 = ((Boolean)this.parameters[0]).booleanValue();
        try {
          paramSession.setAutoCommit(bool1);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1075:
        arrayOfColumnSchema = (ColumnSchema[])this.parameters[0];
        try {
          for (byte b = 0; b < arrayOfColumnSchema.length; b++)
            paramSession.sessionContext.addSessionVariable(arrayOfColumnSchema[b]); 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1065:
        i = ((Integer)this.parameters[0]).intValue();
        paramSession.setSQLMaxRows(i);
        return Result.updateZeroResult;
      case 1066:
        i = ((Integer)this.parameters[0]).intValue();
        paramSession.setResultMemoryRowCount(i);
        return Result.updateZeroResult;
      case 1048:
        try {
          boolean bool4 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.setIgnoreCase(bool4);
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 1068:
        table1 = (Table)this.parameters[0];
        hsqlArrayList = (HsqlArrayList)this.parameters[1];
        statementDMQL = (StatementDMQL)this.parameters[2];
        try {
          if (hsqlArrayList.size() != 0)
            table1 = ParserDDL.addTableConstraintDefinitions(paramSession, table1, hsqlArrayList, null, false); 
          table1.compile(paramSession, (SchemaObject)null);
          paramSession.sessionContext.addSessionTable(table1);
          if (table1.hasLobColumn)
            throw Error.error(5534); 
          if (statementDMQL != null) {
            Result result = statementDMQL.execute(paramSession);
            table1.insertIntoTable(paramSession, result);
          } 
          return Result.updateZeroResult;
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult(hsqlException, this.sql);
        } 
      case 32:
        hsqlName = (HsqlNameManager.HsqlName)this.parameters[0];
        bool3 = (Boolean)this.parameters[1];
        table2 = paramSession.sessionContext.findSessionTable(hsqlName.name);
        if (table2 == null) {
          if (bool3.booleanValue())
            return Result.updateZeroResult; 
          throw Error.error(5501, hsqlName.name);
        } 
        paramSession.sessionContext.dropSessionTable(hsqlName.name);
        return Result.updateZeroResult;
    } 
    throw Error.runtimeError(201, "StatementSession");
  }
  
  public boolean isAutoCommitStatement() {
    return false;
  }
  
  public String describe(Session paramSession) {
    return this.sql;
  }
  
  public boolean isCatalogLock() {
    return false;
  }
  
  public boolean isCatalogChange() {
    return false;
  }
  
  static {
    commitNoChainStatement.sql = "COMMIT";
    commitAndChainStatement.sql = "COMMIT CHAIN";
    rollbackNoChainStatement.sql = "ROLLBACK";
    rollbackAndChainStatement.sql = "ROLLBACK CHAIN";
    commitNoChainStatement.compileTimestamp = Long.MAX_VALUE;
    commitAndChainStatement.compileTimestamp = Long.MAX_VALUE;
    rollbackNoChainStatement.compileTimestamp = Long.MAX_VALUE;
    rollbackAndChainStatement.compileTimestamp = Long.MAX_VALUE;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */