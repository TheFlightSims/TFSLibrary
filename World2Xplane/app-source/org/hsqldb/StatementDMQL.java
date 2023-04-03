package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.Grantee;

public abstract class StatementDMQL extends Statement {
  public static final String PCOL_PREFIX = "@p";
  
  static final String RETURN_COLUMN_NAME = "@p0";
  
  Table targetTable;
  
  Table baseTable;
  
  int[] baseColumnMap;
  
  RangeVariable[] targetRangeVariables = RangeVariable.emptyArray;
  
  Table sourceTable;
  
  Expression condition;
  
  boolean restartIdentity;
  
  int[] insertColumnMap = ValuePool.emptyIntArray;
  
  int[] updateColumnMap = ValuePool.emptyIntArray;
  
  int[] baseUpdateColumnMap = ValuePool.emptyIntArray;
  
  Expression[] updateExpressions = Expression.emptyArray;
  
  Expression[][] multiColumnValues;
  
  Expression insertExpression;
  
  boolean[] insertCheckColumns;
  
  boolean[] updateCheckColumns;
  
  Expression updatableTableCheck;
  
  RangeVariable checkRangeVariable;
  
  QueryExpression queryExpression;
  
  HsqlNameManager.SimpleName cursorName;
  
  ExpressionColumn[] parameters;
  
  ResultMetaData parameterMetaData;
  
  TableDerived[] subqueries = TableDerived.emptyArray;
  
  int rangeIteratorCount;
  
  NumberSequence[] sequences;
  
  Routine[] routines;
  
  RangeVariable[] rangeVariables;
  
  StatementDMQL(int paramInt1, int paramInt2, HsqlNameManager.HsqlName paramHsqlName) {
    super(paramInt1, paramInt2);
    this.schemaName = paramHsqlName;
    this.isTransactionStatement = true;
  }
  
  void setBaseIndexColumnMap() {
    if (this.targetTable != this.baseTable)
      this.baseColumnMap = this.targetTable.getBaseTableColumnMap(); 
  }
  
  public void setCursorName(HsqlNameManager.SimpleName paramSimpleName) {
    this.cursorName = paramSimpleName;
  }
  
  public HsqlNameManager.SimpleName getCursorName() {
    return this.cursorName;
  }
  
  public Result execute(Session paramSession) {
    Result result;
    if (this.targetTable != null && paramSession.isReadOnly() && !this.targetTable.isTemp()) {
      HsqlException hsqlException = Error.error(3706);
      return Result.newErrorResult(hsqlException);
    } 
    if (this.isExplain)
      return getExplainResult(paramSession); 
    try {
      if (this.subqueries.length > 0)
        materializeSubQueries(paramSession); 
      result = getResult(paramSession);
      clearStructures(paramSession);
    } catch (Throwable throwable) {
      clearStructures(paramSession);
      result = Result.newErrorResult(throwable, null);
      result.getException().setStatementType(this.group, this.type);
    } 
    return result;
  }
  
  private Result getExplainResult(Session paramSession) {
    Result result = Result.newSingleColumnStringResult("OPERATION", describe(paramSession));
    OrderedHashSet orderedHashSet = getReferences();
    result.navigator.add(new Object[] { "Object References" });
    byte b;
    for (b = 0; b < orderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      result.navigator.add(new Object[] { hsqlName.getSchemaQualifiedStatementName() });
    } 
    result.navigator.add(new Object[] { "Read Locks" });
    for (b = 0; b < this.readTableNames.length; b++) {
      HsqlNameManager.HsqlName hsqlName = this.readTableNames[b];
      result.navigator.add(new Object[] { hsqlName.getSchemaQualifiedStatementName() });
    } 
    result.navigator.add(new Object[] { "WriteLocks" });
    for (b = 0; b < this.writeTableNames.length; b++) {
      HsqlNameManager.HsqlName hsqlName = this.writeTableNames[b];
      result.navigator.add(new Object[] { hsqlName.getSchemaQualifiedStatementName() });
    } 
    return result;
  }
  
  abstract Result getResult(Session paramSession);
  
  abstract void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet);
  
  abstract void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet);
  
  boolean[] getInsertOrUpdateColumnCheckList() {
    boolean[] arrayOfBoolean;
    switch (this.type) {
      case 50:
        return this.insertCheckColumns;
      case 82:
        return this.updateCheckColumns;
      case 128:
        arrayOfBoolean = (boolean[])ArrayUtil.duplicateArray(this.insertCheckColumns);
        ArrayUtil.orBooleanArray(this.updateCheckColumns, arrayOfBoolean);
        return arrayOfBoolean;
    } 
    return null;
  }
  
  void materializeSubQueries(Session paramSession) {
    HashSet hashSet = new HashSet();
    for (byte b = 0; b < this.subqueries.length; b++) {
      TableDerived tableDerived = this.subqueries[b];
      if (hashSet.add(tableDerived) && !tableDerived.isCorrelated())
        tableDerived.materialise(paramSession); 
    } 
  }
  
  TableDerived[] getSubqueries(Session paramSession) {
    OrderedHashSet orderedHashSet = null;
    byte b;
    for (b = 0; b < this.targetRangeVariables.length; b++) {
      if (this.targetRangeVariables[b] != null) {
        OrderedHashSet orderedHashSet1 = this.targetRangeVariables[b].getSubqueries();
        orderedHashSet = OrderedHashSet.addAll(orderedHashSet, orderedHashSet1);
      } 
    } 
    for (b = 0; b < this.updateExpressions.length; b++)
      orderedHashSet = this.updateExpressions[b].collectAllSubqueries(orderedHashSet); 
    if (this.insertExpression != null)
      orderedHashSet = this.insertExpression.collectAllSubqueries(orderedHashSet); 
    if (this.condition != null)
      orderedHashSet = this.condition.collectAllSubqueries(orderedHashSet); 
    if (this.queryExpression != null) {
      OrderedHashSet orderedHashSet1 = this.queryExpression.getSubqueries();
      orderedHashSet = OrderedHashSet.addAll(orderedHashSet, orderedHashSet1);
    } 
    if (this.updatableTableCheck != null) {
      OrderedHashSet orderedHashSet1 = this.updatableTableCheck.getSubqueries();
      orderedHashSet = OrderedHashSet.addAll(orderedHashSet, orderedHashSet1);
    } 
    if (orderedHashSet == null || orderedHashSet.size() == 0)
      return TableDerived.emptyArray; 
    TableDerived[] arrayOfTableDerived = new TableDerived[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfTableDerived);
    return arrayOfTableDerived;
  }
  
  void setDatabseObjects(Session paramSession, ParserDQL.CompileContext paramCompileContext) {
    this.parameters = paramCompileContext.getParameters();
    setParameterMetaData();
    this.subqueries = getSubqueries(paramSession);
    this.rangeIteratorCount = paramCompileContext.getRangeVarCount();
    this.rangeVariables = paramCompileContext.getAllRangeVariables();
    this.sequences = paramCompileContext.getSequences();
    this.routines = paramCompileContext.getRoutines();
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    collectTableNamesForWrite(orderedHashSet);
    if (orderedHashSet.size() > 0) {
      this.writeTableNames = new HsqlNameManager.HsqlName[orderedHashSet.size()];
      orderedHashSet.toArray((Object[])this.writeTableNames);
      orderedHashSet.clear();
    } 
    collectTableNamesForRead(orderedHashSet);
    orderedHashSet.removeAll((Object[])this.writeTableNames);
    if (orderedHashSet.size() > 0) {
      this.readTableNames = new HsqlNameManager.HsqlName[orderedHashSet.size()];
      orderedHashSet.toArray((Object[])this.readTableNames);
    } 
    if (this.readTableNames.length == 0 && this.writeTableNames.length == 0 && (this.type == 85 || this.type == 65))
      this.isTransactionStatement = false; 
    this.references = paramCompileContext.getSchemaObjectNames();
    if (this.targetTable != null)
      this.references.add(this.targetTable.getName()); 
  }
  
  void checkAccessRights(Session paramSession) {
    if (this.targetTable != null && !this.targetTable.isTemp()) {
      if (!paramSession.isProcessingScript())
        this.targetTable.checkDataReadOnly(); 
      Grantee grantee = this.targetTable.getOwner();
      if (grantee != null && grantee.isSystem() && !paramSession.getUser().isSystem())
        throw Error.error(5501, (this.targetTable.getName()).name); 
      paramSession.checkReadWrite();
    } 
    if (paramSession.isAdmin())
      return; 
    byte b;
    for (b = 0; b < this.sequences.length; b++)
      paramSession.getGrantee().checkAccess(this.sequences[b]); 
    for (b = 0; b < this.routines.length; b++) {
      if (!this.routines[b].isLibraryRoutine())
        paramSession.getGrantee().checkAccess(this.routines[b]); 
    } 
    for (b = 0; b < this.rangeVariables.length; b++) {
      RangeVariable rangeVariable = this.rangeVariables[b];
      if (rangeVariable.rangeTable.getSchemaName() != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        paramSession.getGrantee().checkSelect(rangeVariable.rangeTable, rangeVariable.usedColumns); 
    } 
    switch (this.type) {
      case 50:
        paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
        break;
      case 19:
        paramSession.getGrantee().checkDelete(this.targetTable);
        break;
      case 82:
        paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
        break;
      case 128:
        paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
        paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
        break;
    } 
  }
  
  Result getWriteAccessResult(Session paramSession) {
    try {
      if (this.targetTable != null && !this.targetTable.isTemp())
        paramSession.checkReadWrite(); 
    } catch (HsqlException hsqlException) {
      return Result.newErrorResult(hsqlException);
    } 
    return null;
  }
  
  public ResultMetaData getResultMetaData() {
    switch (this.type) {
      case 19:
      case 50:
      case 82:
      case 128:
        return ResultMetaData.emptyResultMetaData;
    } 
    throw Error.runtimeError(201, "StatementDMQL");
  }
  
  public ResultMetaData getParametersMetaData() {
    return this.parameterMetaData;
  }
  
  void setParameterMetaData() {
    byte b1 = 0;
    if (this.parameters.length == 0) {
      this.parameterMetaData = ResultMetaData.emptyParamMetaData;
      return;
    } 
    this.parameterMetaData = ResultMetaData.newParameterMetaData(this.parameters.length);
    for (byte b2 = 0; b2 < this.parameters.length; b2++) {
      int i = b2 + b1;
      this.parameterMetaData.columnLabels[i] = "@p" + (b2 + 1);
      this.parameterMetaData.columnTypes[i] = (this.parameters[b2]).dataType;
      if ((this.parameters[b2]).dataType == null)
        throw Error.error(5567); 
      byte b = 1;
      if ((this.parameters[b2]).column != null && (this.parameters[b2]).column.getParameterMode() != 0)
        b = (this.parameters[b2]).column.getParameterMode(); 
      this.parameterMetaData.paramModes[i] = b;
      this.parameterMetaData.paramNullable[i] = ((this.parameters[b2]).column == null) ? 1 : (this.parameters[b2]).column.getNullability();
    } 
  }
  
  public String describe(Session paramSession) {
    try {
      return describeImpl(paramSession);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return throwable.toString();
    } 
  }
  
  String describeImpl(Session paramSession) throws Exception {
    byte b;
    StringBuffer stringBuffer = new StringBuffer();
    boolean bool = false;
    switch (this.type) {
      case 85:
        stringBuffer.append(this.queryExpression.describe(paramSession, 0));
        appendParms(stringBuffer).append('\n');
        appendSubqueries(paramSession, stringBuffer, 2);
        return stringBuffer.toString();
      case 50:
        if (this.queryExpression == null) {
          stringBuffer.append("INSERT VALUES");
          stringBuffer.append('[').append('\n');
          appendMultiColumns(stringBuffer, this.insertColumnMap).append('\n');
          appendTable(stringBuffer).append('\n');
          appendParms(stringBuffer).append('\n');
          appendSubqueries(paramSession, stringBuffer, 2).append(']');
          return stringBuffer.toString();
        } 
        stringBuffer.append("INSERT SELECT");
        stringBuffer.append('[').append('\n');
        appendColumns(stringBuffer, this.insertColumnMap).append('\n');
        appendTable(stringBuffer).append('\n');
        stringBuffer.append(this.queryExpression.describe(paramSession, bool)).append('\n');
        appendParms(stringBuffer).append('\n');
        appendSubqueries(paramSession, stringBuffer, 2).append(']');
        return stringBuffer.toString();
      case 82:
        stringBuffer.append("UPDATE");
        stringBuffer.append('[').append('\n');
        appendColumns(stringBuffer, this.updateColumnMap).append('\n');
        appendTable(stringBuffer).append('\n');
        appendCondition(paramSession, stringBuffer);
        for (b = 0; b < this.targetRangeVariables.length; b++)
          stringBuffer.append(this.targetRangeVariables[b].describe(paramSession, bool)).append('\n'); 
        appendParms(stringBuffer).append('\n');
        appendSubqueries(paramSession, stringBuffer, 2).append(']');
        return stringBuffer.toString();
      case 19:
        stringBuffer.append("DELETE");
        stringBuffer.append('[').append('\n');
        appendTable(stringBuffer).append('\n');
        appendCondition(paramSession, stringBuffer);
        for (b = 0; b < this.targetRangeVariables.length; b++)
          stringBuffer.append(this.targetRangeVariables[b].describe(paramSession, bool)).append('\n'); 
        appendParms(stringBuffer).append('\n');
        appendSubqueries(paramSession, stringBuffer, 2).append(']');
        return stringBuffer.toString();
      case 7:
        stringBuffer.append("CALL");
        stringBuffer.append('[').append(']');
        return stringBuffer.toString();
      case 128:
        stringBuffer.append("MERGE");
        stringBuffer.append('[').append('\n');
        appendMultiColumns(stringBuffer, this.insertColumnMap).append('\n');
        appendColumns(stringBuffer, this.updateColumnMap).append('\n');
        appendTable(stringBuffer).append('\n');
        appendCondition(paramSession, stringBuffer);
        for (b = 0; b < this.targetRangeVariables.length; b++)
          stringBuffer.append(this.targetRangeVariables[b].describe(paramSession, bool)).append('\n'); 
        appendParms(stringBuffer).append('\n');
        appendSubqueries(paramSession, stringBuffer, 2).append(']');
        return stringBuffer.toString();
    } 
    return "UNKNOWN";
  }
  
  private StringBuffer appendSubqueries(Session paramSession, StringBuffer paramStringBuffer, int paramInt) {
    paramStringBuffer.append("SUBQUERIES[");
    for (byte b = 0; b < this.subqueries.length; b++) {
      paramStringBuffer.append("\n[level=").append((this.subqueries[b]).depth).append('\n');
      if ((this.subqueries[b]).queryExpression == null) {
        for (byte b1 = 0; b1 < paramInt; b1++)
          paramStringBuffer.append(' '); 
        paramStringBuffer.append("value expression");
      } else {
        paramStringBuffer.append((this.subqueries[b]).queryExpression.describe(paramSession, paramInt));
      } 
      paramStringBuffer.append("]");
    } 
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendTable(StringBuffer paramStringBuffer) {
    paramStringBuffer.append("TABLE[").append((this.targetTable.getName()).name).append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendSourceTable(StringBuffer paramStringBuffer) {
    paramStringBuffer.append("SOURCE TABLE[").append((this.sourceTable.getName()).name).append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendColumns(StringBuffer paramStringBuffer, int[] paramArrayOfint) {
    if (paramArrayOfint == null || this.updateExpressions.length == 0)
      return paramStringBuffer; 
    paramStringBuffer.append("COLUMNS=[");
    byte b;
    for (b = 0; b < paramArrayOfint.length; b++)
      paramStringBuffer.append('\n').append(paramArrayOfint[b]).append(':').append(' ').append(this.targetTable.getColumn(paramArrayOfint[b]).getNameString()); 
    for (b = 0; b < this.updateExpressions.length; b++)
      paramStringBuffer.append('[').append(this.updateExpressions[b]).append(']'); 
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendMultiColumns(StringBuffer paramStringBuffer, int[] paramArrayOfint) {
    if (paramArrayOfint == null || this.multiColumnValues == null)
      return paramStringBuffer; 
    paramStringBuffer.append("COLUMNS=[");
    for (byte b = 0; b < this.multiColumnValues.length; b++) {
      for (byte b1 = 0; b1 < paramArrayOfint.length; b1++)
        paramStringBuffer.append('\n').append(paramArrayOfint[b1]).append(':').append(' ').append((this.targetTable.getColumn(paramArrayOfint[b1]).getName()).name).append('[').append(this.multiColumnValues[b][b1]).append(']'); 
    } 
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendParms(StringBuffer paramStringBuffer) {
    paramStringBuffer.append("PARAMETERS=[");
    for (byte b = 0; b < this.parameters.length; b++)
      paramStringBuffer.append('\n').append('@').append(b).append('[').append(this.parameters[b].describe(null, 0)).append(']'); 
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendCondition(Session paramSession, StringBuffer paramStringBuffer) {
    return (this.condition == null) ? paramStringBuffer.append("CONDITION[]\n") : paramStringBuffer.append("CONDITION[").append(this.condition.describe(paramSession, 0)).append("]\n");
  }
  
  public void resolve(Session paramSession) {}
  
  public final boolean isCatalogLock() {
    return false;
  }
  
  public boolean isCatalogChange() {
    return false;
  }
  
  public void clearStructures(Session paramSession) {
    paramSession.sessionContext.clearStructures(this);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementDMQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */