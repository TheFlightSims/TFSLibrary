package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.result.Result;

public class StatementHandler extends Statement {
  public static final int NONE = 0;
  
  public static final int SQL_EXCEPTION = 1;
  
  public static final int SQL_WARNING = 2;
  
  public static final int SQL_NOT_FOUND = 3;
  
  public static final int SQL_STATE = 4;
  
  public static final int CONTINUE = 5;
  
  public static final int EXIT = 6;
  
  public static final int UNDO = 7;
  
  public final int handlerType;
  
  OrderedIntHashSet conditionGroups = new OrderedIntHashSet();
  
  OrderedHashSet conditionStates = new OrderedHashSet();
  
  Statement statement;
  
  public static final StatementHandler[] emptyExceptionHandlerArray = new StatementHandler[0];
  
  StatementHandler(int paramInt) {
    super(1212, 2007);
    this.handlerType = paramInt;
  }
  
  public void addConditionState(String paramString) {
    boolean bool = this.conditionStates.add(paramString);
    bool &= this.conditionGroups.isEmpty();
    if (!bool)
      throw Error.error(5604); 
  }
  
  public void addConditionType(int paramInt) {
    boolean bool = this.conditionGroups.add(paramInt);
    bool &= this.conditionStates.isEmpty();
    if (!bool)
      throw Error.error(5604); 
  }
  
  public void addStatement(Statement paramStatement) {
    this.statement = paramStatement;
  }
  
  public boolean handlesConditionType(int paramInt) {
    return this.conditionGroups.contains(paramInt);
  }
  
  public boolean handlesCondition(String paramString) {
    if (this.conditionStates.contains(paramString))
      return true; 
    String str = paramString.substring(0, 2);
    return this.conditionStates.contains(str) ? true : (str.equals("01") ? this.conditionGroups.contains(2) : (str.equals("02") ? this.conditionGroups.contains(3) : this.conditionGroups.contains(1)));
  }
  
  public int[] getConditionTypes() {
    return this.conditionGroups.toArray();
  }
  
  public String[] getConditionStates() {
    String[] arrayOfString = new String[this.conditionStates.size()];
    this.conditionStates.toArray((Object[])arrayOfString);
    return arrayOfString;
  }
  
  public void resolve(Session paramSession) {
    if (this.statement != null) {
      this.statement.resolve(paramSession);
      this.readTableNames = this.statement.getTableNamesForRead();
      this.writeTableNames = this.statement.getTableNamesForWrite();
    } 
  }
  
  public Result execute(Session paramSession) {
    return (this.statement != null) ? this.statement.execute(paramSession) : Result.updateZeroResult;
  }
  
  public String describe(Session paramSession) {
    return "";
  }
  
  public OrderedHashSet getReferences() {
    return (this.statement == null) ? new OrderedHashSet() : this.statement.getReferences();
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    String str = (this.handlerType == 5) ? "CONTINUE" : ((this.handlerType == 6) ? "EXIT" : "UNDO");
    stringBuffer.append("DECLARE").append(' ').append(str).append(' ');
    stringBuffer.append("HANDLER").append(' ').append("FOR");
    stringBuffer.append(' ');
    byte b;
    for (b = 0; b < this.conditionStates.size(); b++) {
      if (b > 0)
        stringBuffer.append(','); 
      stringBuffer.append("SQLSTATE").append(' ');
      stringBuffer.append('\'').append(this.conditionStates.get(b)).append('\'');
    } 
    for (b = 0; b < this.conditionGroups.size(); b++) {
      if (b > 0)
        stringBuffer.append(','); 
      switch (this.conditionGroups.get(b)) {
        case 1:
          stringBuffer.append("SQLEXCEPTION");
          break;
        case 2:
          stringBuffer.append("SQLWARNING");
          break;
        case 3:
          stringBuffer.append("NOT").append(' ').append(404);
          break;
      } 
    } 
    if (this.statement != null)
      stringBuffer.append(' ').append(this.statement.getSQL()); 
    return stringBuffer.toString();
  }
  
  public boolean isCatalogLock() {
    return false;
  }
  
  public boolean isCatalogChange() {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */