package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.navigator.RowSetNavigatorDataChange;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class StatementDML extends StatementDMQL {
  Expression[] targets;
  
  boolean isTruncate;
  
  boolean isSimpleInsert;
  
  int generatedType;
  
  ResultMetaData generatedInputMetaData;
  
  int[] generatedIndexes;
  
  ResultMetaData generatedResultMetaData;
  
  public StatementDML(int paramInt1, int paramInt2, HsqlNameManager.HsqlName paramHsqlName) {
    super(paramInt1, paramInt2, paramHsqlName);
  }
  
  StatementDML(Session paramSession, Table paramTable, RangeVariable paramRangeVariable, RangeVariable[] paramArrayOfRangeVariable, ParserDQL.CompileContext paramCompileContext, boolean paramBoolean, int paramInt) {
    super(19, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targetTable = paramTable;
    this.baseTable = paramTable.isTriggerDeletable() ? paramTable : paramTable.getBaseTable();
    this.targetRangeVariables = paramArrayOfRangeVariable;
    this.restartIdentity = paramBoolean;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    if (paramInt == 1215)
      this.isTruncate = true; 
    paramRangeVariable.addAllColumns();
  }
  
  StatementDML(Session paramSession, Expression[] paramArrayOfExpression1, Table paramTable, RangeVariable paramRangeVariable, RangeVariable[] paramArrayOfRangeVariable, int[] paramArrayOfint, Expression[] paramArrayOfExpression2, boolean[] paramArrayOfboolean, ParserDQL.CompileContext paramCompileContext) {
    super(82, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targets = paramArrayOfExpression1;
    this.targetTable = paramTable;
    this.baseTable = paramTable.isTriggerUpdatable() ? paramTable : paramTable.getBaseTable();
    this.updateColumnMap = paramArrayOfint;
    this.updateExpressions = paramArrayOfExpression2;
    this.updateCheckColumns = paramArrayOfboolean;
    this.targetRangeVariables = paramArrayOfRangeVariable;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    paramRangeVariable.addAllColumns();
  }
  
  StatementDML(Session paramSession, Expression[] paramArrayOfExpression1, RangeVariable paramRangeVariable1, RangeVariable paramRangeVariable2, RangeVariable[] paramArrayOfRangeVariable, int[] paramArrayOfint1, int[] paramArrayOfint2, boolean[] paramArrayOfboolean, Expression paramExpression1, Expression paramExpression2, Expression[] paramArrayOfExpression2, ParserDQL.CompileContext paramCompileContext) {
    super(128, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targets = paramArrayOfExpression1;
    this.sourceTable = paramRangeVariable1.rangeTable;
    this.targetTable = paramRangeVariable2.rangeTable;
    this.baseTable = this.targetTable.isTriggerUpdatable() ? this.targetTable : this.targetTable.getBaseTable();
    this.insertCheckColumns = paramArrayOfboolean;
    this.insertColumnMap = paramArrayOfint1;
    this.updateColumnMap = paramArrayOfint2;
    this.insertExpression = paramExpression2;
    this.updateExpressions = paramArrayOfExpression2;
    this.targetRangeVariables = paramArrayOfRangeVariable;
    this.condition = paramExpression1;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  StatementDML() {
    super(81, 2004, (HsqlNameManager.HsqlName)null);
  }
  
  void setupChecks() {
    if (this.targetTable != this.baseTable) {
      QuerySpecification querySpecification = ((TableDerived)this.targetTable).getQueryExpression().getMainSelect();
      this.updatableTableCheck = querySpecification.checkQueryCondition;
      this.checkRangeVariable = querySpecification.rangeVariables[querySpecification.rangeVariables.length - 1];
    } 
  }
  
  Result getResult(Session paramSession) {
    Result result = null;
    switch (this.type) {
      case 82:
        result = executeUpdateStatement(paramSession);
        paramSession.sessionContext.diagnosticsVariables[2] = Integer.valueOf(result.getUpdateCount());
        return result;
      case 128:
        result = executeMergeStatement(paramSession);
        paramSession.sessionContext.diagnosticsVariables[2] = Integer.valueOf(result.getUpdateCount());
        return result;
      case 19:
        if (this.isTruncate) {
          result = executeDeleteTruncateStatement(paramSession);
        } else {
          result = executeDeleteStatement(paramSession);
        } 
        paramSession.sessionContext.diagnosticsVariables[2] = Integer.valueOf(result.getUpdateCount());
        return result;
    } 
    throw Error.runtimeError(201, "StatementDML");
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet) {
    if (this.baseTable.isView()) {
      getTriggerTableNames(paramOrderedHashSet, false);
    } else if (!this.baseTable.isTemp()) {
      for (byte b1 = 0; b1 < this.baseTable.fkConstraints.length; b1++) {
        Constraint constraint = this.baseTable.fkConstraints[b1];
        switch (this.type) {
          case 82:
            if (ArrayUtil.haveCommonElement(constraint.getRefColumns(), this.updateColumnMap))
              paramOrderedHashSet.add(this.baseTable.fkConstraints[b1].getMain().getName()); 
            break;
          case 50:
            paramOrderedHashSet.add(this.baseTable.fkConstraints[b1].getMain().getName());
            break;
          case 128:
            if (this.updateColumnMap != null && ArrayUtil.haveCommonElement(constraint.getRefColumns(), this.updateColumnMap))
              paramOrderedHashSet.add(this.baseTable.fkConstraints[b1].getMain().getName()); 
            if (this.insertExpression != null)
              paramOrderedHashSet.add(this.baseTable.fkConstraints[b1].getMain().getName()); 
            break;
        } 
      } 
      if (this.type == 82 || this.type == 128) {
        this.baseTable.collectFKReadLocks(this.updateColumnMap, paramOrderedHashSet);
      } else if (this.type == 19) {
        this.baseTable.collectFKReadLocks((int[])null, paramOrderedHashSet);
      } 
      getTriggerTableNames(paramOrderedHashSet, false);
    } 
    byte b;
    for (b = 0; b < this.rangeVariables.length; b++) {
      Table table = (this.rangeVariables[b]).rangeTable;
      HsqlNameManager.HsqlName hsqlName = table.getName();
      if (!table.isDataReadOnly() && !table.isTemp() && hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        paramOrderedHashSet.add(hsqlName); 
    } 
    for (b = 0; b < this.subqueries.length; b++) {
      if ((this.subqueries[b]).queryExpression != null)
        (this.subqueries[b]).queryExpression.getBaseTableNames(paramOrderedHashSet); 
    } 
    for (b = 0; b < this.routines.length; b++)
      paramOrderedHashSet.addAll((Object[])this.routines[b].getTableNamesForRead()); 
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {
    if (this.baseTable.isView()) {
      getTriggerTableNames(paramOrderedHashSet, true);
    } else if (!this.baseTable.isTemp()) {
      paramOrderedHashSet.add(this.baseTable.getName());
      if (this.type == 82 || this.type == 128) {
        if (this.updateExpressions.length != 0)
          this.baseTable.collectFKWriteLocks(this.updateColumnMap, paramOrderedHashSet); 
      } else if (this.type == 19) {
        this.baseTable.collectFKWriteLocks((int[])null, paramOrderedHashSet);
      } 
      getTriggerTableNames(paramOrderedHashSet, true);
    } 
  }
  
  public void setGeneratedColumnInfo(int paramInt, ResultMetaData paramResultMetaData) {
    int j;
    String[] arrayOfString;
    byte b2;
    if (this.type != 50)
      return; 
    int i = this.baseTable.getIdentityColumnIndex();
    this.generatedType = paramInt;
    this.generatedInputMetaData = paramResultMetaData;
    switch (paramInt) {
      case 2:
        return;
      case 21:
        this.generatedIndexes = paramResultMetaData.getGeneratedColumnIndexes();
        for (j = 0; j < this.generatedIndexes.length; j++) {
          if (this.generatedIndexes[j] < 0 || this.generatedIndexes[j] >= this.baseTable.getColumnCount())
            throw Error.error(5501); 
        } 
        break;
      case 1:
        if (this.baseTable.hasGeneratedColumn()) {
          if (i >= 0) {
            j = ArrayUtil.countTrueElements(this.baseTable.colGenerated) + 1;
            this.generatedIndexes = new int[j];
            byte b3 = 0;
            byte b4 = 0;
            while (b3 < this.baseTable.colGenerated.length) {
              if (this.baseTable.colGenerated[b3] || b3 == i)
                this.generatedIndexes[b4++] = b3; 
              b3++;
            } 
            break;
          } 
          this.generatedIndexes = ArrayUtil.booleanArrayToIntIndexes(this.baseTable.colGenerated);
          break;
        } 
        if (i >= 0) {
          this.generatedIndexes = new int[] { i };
          break;
        } 
        return;
      case 11:
        arrayOfString = paramResultMetaData.getGeneratedColumnNames();
        this.generatedIndexes = this.baseTable.getColumnIndexes(arrayOfString);
        for (b2 = 0; b2 < this.generatedIndexes.length; b2++) {
          if (this.generatedIndexes[b2] < 0)
            throw Error.error(5501, arrayOfString[0]); 
        } 
        break;
    } 
    this.generatedResultMetaData = ResultMetaData.newResultMetaData(this.generatedIndexes.length);
    for (byte b1 = 0; b1 < this.generatedIndexes.length; b1++) {
      ColumnSchema columnSchema = this.baseTable.getColumn(this.generatedIndexes[b1]);
      this.generatedResultMetaData.columns[b1] = columnSchema;
    } 
    this.generatedResultMetaData.prepareData();
    this.isSimpleInsert = false;
  }
  
  Object[] getGeneratedColumns(Object[] paramArrayOfObject) {
    if (this.generatedIndexes == null)
      return null; 
    Object[] arrayOfObject = new Object[this.generatedIndexes.length];
    for (byte b = 0; b < this.generatedIndexes.length; b++)
      arrayOfObject[b] = paramArrayOfObject[this.generatedIndexes[b]]; 
    return arrayOfObject;
  }
  
  public boolean hasGeneratedColumns() {
    return (this.generatedIndexes != null);
  }
  
  public ResultMetaData generatedResultMetaData() {
    return this.generatedResultMetaData;
  }
  
  void getTriggerTableNames(OrderedHashSet paramOrderedHashSet, boolean paramBoolean) {
    for (byte b = 0; b < this.baseTable.triggerList.length; b++) {
      TriggerDef triggerDef = this.baseTable.triggerList[b];
      switch (this.type) {
        case 50:
        
        case 82:
        
        case 19:
        
        case 128:
        
        default:
          throw Error.runtimeError(201, "StatementDML");
      } 
      continue;
    } 
  }
  
  Result executeUpdateStatement(Session paramSession) {
    int i = 0;
    Expression[] arrayOfExpression = this.updateExpressions;
    RowSetNavigatorDataChange rowSetNavigatorDataChange = paramSession.sessionContext.getRowSetDataChange();
    Type[] arrayOfType = this.baseTable.getColumnTypes();
    RangeIterator rangeIterator = RangeVariable.getIterator(paramSession, this.targetRangeVariables);
    Result result = null;
    RowSetNavigator rowSetNavigator = null;
    if (this.generatedIndexes != null) {
      result = Result.newUpdateCountResult(this.generatedResultMetaData, 0);
      rowSetNavigator = result.getChainedResult().getNavigator();
    } 
    paramSession.sessionContext.rownum = 1;
    while (rangeIterator.next()) {
      paramSession.sessionData.startRowProcessing();
      Row row = rangeIterator.getCurrentRow();
      Object[] arrayOfObject1 = row.getData();
      Object[] arrayOfObject2 = getUpdatedData(paramSession, this.targets, this.baseTable, this.updateColumnMap, arrayOfExpression, arrayOfType, arrayOfObject1);
      if (this.updatableTableCheck != null) {
        rangeIterator.setCurrent(arrayOfObject2);
        boolean bool = this.updatableTableCheck.testCondition(paramSession);
        if (!bool) {
          rangeIterator.release();
          throw Error.error(5700);
        } 
      } 
      rowSetNavigatorDataChange.addRow(paramSession, row, arrayOfObject2, arrayOfType, this.updateColumnMap);
      paramSession.sessionContext.rownum++;
    } 
    rowSetNavigatorDataChange.endMainDataSet();
    rangeIterator.release();
    rowSetNavigatorDataChange.beforeFirst();
    i = update(paramSession, this.baseTable, rowSetNavigatorDataChange, rowSetNavigator);
    if (result == null) {
      if (i == 1)
        return Result.updateOneResult; 
      if (i == 0) {
        paramSession.addWarning(HsqlException.noDataCondition);
        return Result.updateZeroResult;
      } 
      return new Result(1, i);
    } 
    result.setUpdateCount(i);
    if (i == 0)
      paramSession.addWarning(HsqlException.noDataCondition); 
    return result;
  }
  
  static Object[] getUpdatedData(Session paramSession, Expression[] paramArrayOfExpression1, Table paramTable, int[] paramArrayOfint, Expression[] paramArrayOfExpression2, Type[] paramArrayOfType, Object[] paramArrayOfObject) {
    Object[] arrayOfObject = paramTable.getEmptyRowData();
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, arrayOfObject.length);
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < paramArrayOfint.length) {
      Expression expression = paramArrayOfExpression2[b2++];
      if (expression.getType() == 25) {
        Object[] arrayOfObject1 = expression.getRowValue(paramSession);
        byte b = 0;
        while (b < arrayOfObject1.length) {
          int j = paramArrayOfint[b1];
          Expression expression1 = expression.nodes[b];
          if (paramTable.identityColumn != j || expression1.getType() != 1 || expression1.valueData != null)
            if (expression1.getType() == 4) {
              if (paramTable.identityColumn != j)
                arrayOfObject[j] = paramTable.colDefaults[j].getValue(paramSession); 
            } else {
              arrayOfObject[j] = paramArrayOfType[j].convertToType(paramSession, arrayOfObject1[b], expression1.dataType);
            }  
          b++;
          b1++;
        } 
        continue;
      } 
      if (expression.getType() == 22) {
        Object[] arrayOfObject1 = expression.getRowValue(paramSession);
        byte b = 0;
        while (b < arrayOfObject1.length) {
          int j = paramArrayOfint[b1];
          Type type = (expression.table.queryExpression.getMetaData()).columnTypes[b];
          arrayOfObject[j] = paramArrayOfType[j].convertToType(paramSession, arrayOfObject1[b], type);
          b++;
          b1++;
        } 
        continue;
      } 
      int i = paramArrayOfint[b1];
      if (expression.getType() == 4) {
        if (paramTable.identityColumn == i) {
          b1++;
          continue;
        } 
        arrayOfObject[i] = paramTable.colDefaults[i].getValue(paramSession);
        b1++;
        continue;
      } 
      Object object = expression.getValue(paramSession);
      if (paramArrayOfExpression1[b1].getType() == 99) {
        arrayOfObject[i] = ((ExpressionAccessor)paramArrayOfExpression1[b1]).getUpdatedArray(paramSession, (Object[])arrayOfObject[i], object, true);
      } else {
        arrayOfObject[i] = paramArrayOfType[i].convertToType(paramSession, object, expression.dataType);
      } 
      b1++;
    } 
    return arrayOfObject;
  }
  
  Result executeMergeStatement(Session paramSession) {
    Type[] arrayOfType = this.baseTable.getColumnTypes();
    Result result = null;
    RowSetNavigator rowSetNavigator = null;
    if (this.generatedIndexes != null) {
      result = Result.newUpdateCountResult(this.generatedResultMetaData, 0);
      rowSetNavigator = result.getChainedResult().getNavigator();
    } 
    int i = 0;
    RowSetNavigatorClient rowSetNavigatorClient = new RowSetNavigatorClient(8);
    RowSetNavigatorDataChange rowSetNavigatorDataChange = paramSession.sessionContext.getRowSetDataChange();
    RangeVariable[] arrayOfRangeVariable = this.targetRangeVariables;
    RangeIterator[] arrayOfRangeIterator = new RangeIterator[arrayOfRangeVariable.length];
    byte b;
    for (b = 0; b < arrayOfRangeVariable.length; b++)
      arrayOfRangeIterator[b] = arrayOfRangeVariable[b].getIterator(paramSession); 
    b = 0;
    while (b >= 0) {
      RangeIterator rangeIterator = arrayOfRangeIterator[b];
      boolean bool = rangeIterator.isBeforeFirst();
      if (rangeIterator.next()) {
        if (b < arrayOfRangeVariable.length - 1) {
          b++;
          continue;
        } 
      } else {
        if (b == 1 && bool && this.insertExpression != null) {
          Object[] arrayOfObject = getInsertData(paramSession, arrayOfType, (this.insertExpression.nodes[0]).nodes);
          if (arrayOfObject != null)
            rowSetNavigatorClient.add(arrayOfObject); 
        } 
        rangeIterator.reset();
        b--;
        continue;
      } 
      if (this.updateExpressions.length != 0) {
        Row row = rangeIterator.getCurrentRow();
        paramSession.sessionData.startRowProcessing();
        Object[] arrayOfObject = getUpdatedData(paramSession, this.targets, this.baseTable, this.updateColumnMap, this.updateExpressions, arrayOfType, row.getData());
        try {
          rowSetNavigatorDataChange.addRow(paramSession, row, arrayOfObject, arrayOfType, this.updateColumnMap);
        } catch (HsqlException hsqlException) {
          for (byte b1 = 0; b1 < arrayOfRangeVariable.length; b1++)
            arrayOfRangeIterator[b1].reset(); 
          throw Error.error(3201);
        } 
      } 
    } 
    rowSetNavigatorDataChange.endMainDataSet();
    for (b = 0; b < arrayOfRangeVariable.length; b++)
      arrayOfRangeIterator[b].reset(); 
    if (this.updateExpressions.length != 0)
      i = update(paramSession, this.baseTable, rowSetNavigatorDataChange, rowSetNavigator); 
    if (rowSetNavigatorClient.getSize() > 0) {
      insertRowSet(paramSession, rowSetNavigator, (RowSetNavigator)rowSetNavigatorClient);
      i += rowSetNavigatorClient.getSize();
    } 
    if (this.insertExpression != null && (this.baseTable.triggerLists[0]).length > 0)
      this.baseTable.fireTriggers(paramSession, 0, (RowSetNavigator)rowSetNavigatorClient); 
    if (result == null) {
      if (i == 1)
        return Result.updateOneResult; 
      if (i == 0) {
        paramSession.addWarning(HsqlException.noDataCondition);
        return Result.updateZeroResult;
      } 
      return new Result(1, i);
    } 
    result.setUpdateCount(i);
    if (i == 0)
      paramSession.addWarning(HsqlException.noDataCondition); 
    return result;
  }
  
  void insertRowSet(Session paramSession, RowSetNavigator paramRowSetNavigator1, RowSetNavigator paramRowSetNavigator2) {
    PersistentStore persistentStore = this.baseTable.getRowStore(paramSession);
    RangeVariable.RangeIteratorMain rangeIteratorMain = null;
    if (this.updatableTableCheck != null)
      rangeIteratorMain = this.checkRangeVariable.getIterator(paramSession); 
    paramRowSetNavigator2.beforeFirst();
    if ((this.baseTable.triggerLists[6]).length > 0) {
      while (paramRowSetNavigator2.hasNext()) {
        Object[] arrayOfObject = paramRowSetNavigator2.getNext();
        this.baseTable.fireTriggers(paramSession, 6, (Object[])null, arrayOfObject, (int[])null);
      } 
      paramRowSetNavigator2.beforeFirst();
    } 
    while (paramRowSetNavigator2.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigator2.getNext();
      paramSession.sessionData.startRowProcessing();
      this.baseTable.insertSingleRow(paramSession, persistentStore, arrayOfObject, (int[])null);
      if (rangeIteratorMain != null) {
        rangeIteratorMain.setCurrent(arrayOfObject);
        boolean bool = this.updatableTableCheck.testCondition(paramSession);
        if (!bool)
          throw Error.error(5700); 
      } 
      if (paramRowSetNavigator1 != null) {
        Object[] arrayOfObject1 = getGeneratedColumns(arrayOfObject);
        paramRowSetNavigator1.add(arrayOfObject1);
      } 
    } 
    paramRowSetNavigator2.beforeFirst();
    while (paramRowSetNavigator2.hasNext()) {
      Object[] arrayOfObject = paramRowSetNavigator2.getNext();
      performIntegrityChecks(paramSession, this.baseTable, (Object[])null, arrayOfObject, (int[])null);
    } 
    paramRowSetNavigator2.beforeFirst();
    if ((this.baseTable.triggerLists[3]).length > 0) {
      while (paramRowSetNavigator2.hasNext()) {
        Object[] arrayOfObject = paramRowSetNavigator2.getNext();
        this.baseTable.fireTriggers(paramSession, 3, (Object[])null, arrayOfObject, (int[])null);
      } 
      paramRowSetNavigator2.beforeFirst();
    } 
  }
  
  Result insertSingleRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    if ((this.baseTable.triggerLists[6]).length > 0)
      this.baseTable.fireTriggers(paramSession, 6, (Object[])null, paramArrayOfObject, (int[])null); 
    this.baseTable.insertSingleRow(paramSession, paramPersistentStore, paramArrayOfObject, (int[])null);
    performIntegrityChecks(paramSession, this.baseTable, (Object[])null, paramArrayOfObject, (int[])null);
    if (paramSession.database.isReferentialIntegrity()) {
      byte b = 0;
      int i = this.baseTable.fkConstraints.length;
      while (b < i) {
        this.baseTable.fkConstraints[b].checkInsert(paramSession, this.baseTable, paramArrayOfObject, true);
        b++;
      } 
    } 
    if ((this.baseTable.triggerLists[3]).length > 0)
      this.baseTable.fireTriggers(paramSession, 3, (Object[])null, paramArrayOfObject, (int[])null); 
    if ((this.baseTable.triggerLists[0]).length > 0)
      this.baseTable.fireTriggers(paramSession, 0, (RowSetNavigator)null); 
    return Result.updateOneResult;
  }
  
  Object[] getInsertData(Session paramSession, Type[] paramArrayOfType, Expression[] paramArrayOfExpression) {
    Object[] arrayOfObject = this.baseTable.getNewRowData(paramSession);
    paramSession.sessionData.startRowProcessing();
    for (byte b = 0; b < paramArrayOfExpression.length; b++) {
      Expression expression = paramArrayOfExpression[b];
      int i = this.insertColumnMap[b];
      if (expression.opType == 4) {
        if (this.baseTable.identityColumn != i && this.baseTable.colDefaults[i] != null)
          arrayOfObject[i] = this.baseTable.colDefaults[i].getValue(paramSession); 
      } else {
        Object object = expression.getValue(paramSession);
        Type type = paramArrayOfType[i];
        if (paramSession.database.sqlSyntaxMys || paramSession.database.sqlSyntaxPgs) {
          try {
            object = type.convertToType(paramSession, object, expression.dataType);
          } catch (HsqlException hsqlException) {
            if (type.typeCode == 91) {
              object = Type.SQL_TIMESTAMP.convertToType(paramSession, object, expression.dataType);
              object = type.convertToType(paramSession, object, (Type)Type.SQL_TIMESTAMP);
            } else if (type.typeCode == 93) {
              object = Type.SQL_DATE.convertToType(paramSession, object, expression.dataType);
              object = type.convertToType(paramSession, object, (Type)Type.SQL_DATE);
            } else {
              throw hsqlException;
            } 
          } 
        } else if (expression.dataType == null || type.typeDataGroup != expression.dataType.typeDataGroup || type.isArrayType()) {
          object = type.convertToType(paramSession, object, expression.dataType);
        } 
        arrayOfObject[i] = object;
      } 
    } 
    return arrayOfObject;
  }
  
  int update(Session paramSession, Table paramTable, RowSetNavigatorDataChange paramRowSetNavigatorDataChange, RowSetNavigator paramRowSetNavigator) {
    int i = paramRowSetNavigatorDataChange.getSize();
    for (byte b = 0; b < i; b++) {
      paramRowSetNavigatorDataChange.next();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      paramSession.sessionData.startRowProcessing();
      paramTable.setIdentityColumn(paramSession, arrayOfObject);
      paramTable.setGeneratedColumns(paramSession, arrayOfObject);
    } 
    paramRowSetNavigatorDataChange.beforeFirst();
    if (paramTable.fkMainConstraints.length > 0) {
      HashSet hashSet = paramSession.sessionContext.getConstraintPath();
      for (byte b1 = 0; b1 < i; b1++) {
        paramRowSetNavigatorDataChange.next();
        Row row = paramRowSetNavigatorDataChange.getCurrentRow();
        Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
        performReferentialActions(paramSession, paramTable, paramRowSetNavigatorDataChange, row, arrayOfObject, this.updateColumnMap, hashSet);
        hashSet.clear();
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    while (paramRowSetNavigatorDataChange.next()) {
      Row row = paramRowSetNavigatorDataChange.getCurrentRow();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
      Table table = (Table)row.getTable();
      if (table instanceof TableDerived)
        table = ((TableDerived)table).view; 
      if ((table.triggerLists[8]).length > 0) {
        table.fireTriggers(paramSession, 8, row.getData(), arrayOfObject, arrayOfInt);
        table.enforceRowConstraints(paramSession, arrayOfObject);
      } 
    } 
    if (paramTable.isView)
      return i; 
    paramRowSetNavigatorDataChange.beforeFirst();
    while (paramRowSetNavigatorDataChange.next()) {
      Row row = paramRowSetNavigatorDataChange.getCurrentRow();
      Table table = (Table)row.getTable();
      int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
      PersistentStore persistentStore = table.getRowStore(paramSession);
      paramSession.addDeleteAction(table, persistentStore, row, arrayOfInt);
    } 
    paramRowSetNavigatorDataChange.beforeFirst();
    while (paramRowSetNavigatorDataChange.next()) {
      Row row1 = paramRowSetNavigatorDataChange.getCurrentRow();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      Table table = (Table)row1.getTable();
      int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
      PersistentStore persistentStore = table.getRowStore(paramSession);
      if (arrayOfObject == null)
        continue; 
      Row row2 = table.insertSingleRow(paramSession, persistentStore, arrayOfObject, arrayOfInt);
      if (paramRowSetNavigator != null) {
        Object[] arrayOfObject1 = getGeneratedColumns(arrayOfObject);
        paramRowSetNavigator.add(arrayOfObject1);
      } 
    } 
    paramRowSetNavigatorDataChange.beforeFirst();
    OrderedHashSet orderedHashSet = null;
    boolean bool = ((paramTable.triggerLists[5]).length > 0) ? true : false;
    while (paramRowSetNavigatorDataChange.next()) {
      Row row = paramRowSetNavigatorDataChange.getCurrentRow();
      Table table = (Table)row.getTable();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
      performIntegrityChecks(paramSession, table, row.getData(), arrayOfObject, arrayOfInt);
      if (table != paramTable) {
        if (orderedHashSet == null)
          orderedHashSet = new OrderedHashSet(); 
        orderedHashSet.add(table);
        if ((table.triggerLists[5]).length > 0)
          bool = true; 
      } 
    } 
    paramRowSetNavigatorDataChange.beforeFirst();
    if (bool) {
      while (paramRowSetNavigatorDataChange.next()) {
        Row row = paramRowSetNavigatorDataChange.getCurrentRow();
        Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
        int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
        Table table = (Table)row.getTable();
        table.fireTriggers(paramSession, 5, row.getData(), arrayOfObject, arrayOfInt);
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    this.baseTable.fireTriggers(paramSession, 2, paramRowSetNavigatorDataChange);
    if (orderedHashSet != null)
      for (byte b1 = 0; b1 < orderedHashSet.size(); b1++) {
        Table table = (Table)orderedHashSet.get(b1);
        table.fireTriggers(paramSession, 2, paramRowSetNavigatorDataChange);
      }  
    return i;
  }
  
  Result executeDeleteStatement(Session paramSession) {
    int i = 0;
    RangeIterator rangeIterator = RangeVariable.getIterator(paramSession, this.targetRangeVariables);
    RowSetNavigatorDataChange rowSetNavigatorDataChange = paramSession.sessionContext.getRowSetDataChange();
    paramSession.sessionContext.rownum = 1;
    while (rangeIterator.next()) {
      Row row = rangeIterator.getCurrentRow();
      rowSetNavigatorDataChange.addRow(row);
      paramSession.sessionContext.rownum++;
    } 
    rangeIterator.release();
    rowSetNavigatorDataChange.endMainDataSet();
    if (rowSetNavigatorDataChange.getSize() > 0) {
      i = delete(paramSession, this.baseTable, rowSetNavigatorDataChange);
    } else {
      paramSession.addWarning(HsqlException.noDataCondition);
      return Result.updateZeroResult;
    } 
    return (i == 1) ? Result.updateOneResult : new Result(1, i);
  }
  
  Result executeDeleteTruncateStatement(Session paramSession) {
    PersistentStore persistentStore = this.targetTable.getRowStore(paramSession);
    RowIterator rowIterator = this.targetTable.getPrimaryIndex().firstRow(persistentStore);
    boolean bool = rowIterator.hasNext();
    for (byte b = 0; b < this.targetTable.fkMainConstraints.length; b++) {
      if (this.targetTable.fkMainConstraints[b].getRef() != this.targetTable) {
        HsqlNameManager.HsqlName hsqlName = this.targetTable.fkMainConstraints[b].getRef().getName();
        Table table = paramSession.database.schemaManager.getUserTable(paramSession, hsqlName);
        if (!table.isEmpty(paramSession))
          throw Error.error(8, (table.getName()).name); 
      } 
    } 
    try {
      while (rowIterator.hasNext()) {
        Row row = rowIterator.getNextRow();
        paramSession.addDeleteAction((Table)row.getTable(), persistentStore, row, null);
      } 
      if (this.restartIdentity && this.targetTable.identitySequence != null)
        this.targetTable.identitySequence.reset(); 
    } finally {
      rowIterator.release();
    } 
    if (!bool)
      paramSession.addWarning(HsqlException.noDataCondition); 
    return Result.updateOneResult;
  }
  
  int delete(Session paramSession, Table paramTable, RowSetNavigatorDataChange paramRowSetNavigatorDataChange) {
    int i = paramRowSetNavigatorDataChange.getSize();
    paramRowSetNavigatorDataChange.beforeFirst();
    if (paramTable.fkMainConstraints.length > 0) {
      HashSet hashSet = paramSession.sessionContext.getConstraintPath();
      for (byte b = 0; b < i; b++) {
        paramRowSetNavigatorDataChange.next();
        Row row = paramRowSetNavigatorDataChange.getCurrentRow();
        performReferentialActions(paramSession, paramTable, paramRowSetNavigatorDataChange, row, (Object[])null, (int[])null, hashSet);
        hashSet.clear();
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    while (paramRowSetNavigatorDataChange.next()) {
      Row row = paramRowSetNavigatorDataChange.getCurrentRow();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
      Table table = (Table)row.getTable();
      if (table instanceof TableDerived)
        table = ((TableDerived)table).view; 
      if (arrayOfObject == null) {
        table.fireTriggers(paramSession, 7, row.getData(), (Object[])null, (int[])null);
        continue;
      } 
      table.fireTriggers(paramSession, 8, row.getData(), arrayOfObject, arrayOfInt);
    } 
    if (paramTable.isView)
      return i; 
    paramRowSetNavigatorDataChange.beforeFirst();
    boolean bool1 = false;
    while (paramRowSetNavigatorDataChange.next()) {
      Row row = paramRowSetNavigatorDataChange.getCurrentRow();
      Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
      Table table = (Table)row.getTable();
      PersistentStore persistentStore = table.getRowStore(paramSession);
      paramSession.addDeleteAction(table, persistentStore, row, null);
      if (arrayOfObject != null)
        bool1 = true; 
    } 
    paramRowSetNavigatorDataChange.beforeFirst();
    if (bool1) {
      while (paramRowSetNavigatorDataChange.next()) {
        Row row1 = paramRowSetNavigatorDataChange.getCurrentRow();
        Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
        Table table = (Table)row1.getTable();
        int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
        PersistentStore persistentStore = table.getRowStore(paramSession);
        if (arrayOfObject == null)
          continue; 
        Row row2 = table.insertSingleRow(paramSession, persistentStore, arrayOfObject, arrayOfInt);
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    OrderedHashSet orderedHashSet1 = null;
    OrderedHashSet orderedHashSet2 = null;
    boolean bool2 = ((paramTable.triggerLists[4]).length > 0) ? true : false;
    if (i != paramRowSetNavigatorDataChange.getSize()) {
      while (paramRowSetNavigatorDataChange.next()) {
        Row row = paramRowSetNavigatorDataChange.getCurrentRow();
        Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
        int[] arrayOfInt = paramRowSetNavigatorDataChange.getCurrentChangedColumns();
        Table table = (Table)row.getTable();
        if (arrayOfObject != null)
          performIntegrityChecks(paramSession, table, row.getData(), arrayOfObject, arrayOfInt); 
        if (table != paramTable) {
          if (arrayOfObject == null) {
            if ((table.triggerLists[4]).length > 0)
              bool2 = true; 
            if (orderedHashSet2 == null)
              orderedHashSet2 = new OrderedHashSet(); 
            orderedHashSet2.add(table);
            continue;
          } 
          if ((table.triggerLists[5]).length > 0)
            bool2 = true; 
          if (orderedHashSet1 == null)
            orderedHashSet1 = new OrderedHashSet(); 
          orderedHashSet1.add(table);
        } 
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    if (bool2) {
      while (paramRowSetNavigatorDataChange.next()) {
        Row row = paramRowSetNavigatorDataChange.getCurrentRow();
        Object[] arrayOfObject = paramRowSetNavigatorDataChange.getCurrentChangedData();
        Table table = (Table)row.getTable();
        if (arrayOfObject == null) {
          table.fireTriggers(paramSession, 4, row.getData(), (Object[])null, (int[])null);
          continue;
        } 
        table.fireTriggers(paramSession, 5, row.getData(), arrayOfObject, (int[])null);
      } 
      paramRowSetNavigatorDataChange.beforeFirst();
    } 
    paramTable.fireTriggers(paramSession, 1, paramRowSetNavigatorDataChange);
    if (orderedHashSet1 != null)
      for (byte b = 0; b < orderedHashSet1.size(); b++) {
        Table table = (Table)orderedHashSet1.get(b);
        table.fireTriggers(paramSession, 2, paramRowSetNavigatorDataChange);
      }  
    if (orderedHashSet2 != null)
      for (byte b = 0; b < orderedHashSet2.size(); b++) {
        Table table = (Table)orderedHashSet2.get(b);
        table.fireTriggers(paramSession, 1, paramRowSetNavigatorDataChange);
      }  
    return i;
  }
  
  static void performIntegrityChecks(Session paramSession, Table paramTable, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint) {
    if (paramArrayOfObject2 == null)
      return; 
    byte b = 0;
    int i = paramTable.checkConstraints.length;
    while (b < i) {
      paramTable.checkConstraints[b].checkInsert(paramSession, paramTable, paramArrayOfObject2, (paramArrayOfObject1 == null));
      b++;
    } 
    if (!paramSession.database.isReferentialIntegrity())
      return; 
    b = 0;
    i = paramTable.fkConstraints.length;
    while (b < i) {
      boolean bool = (paramArrayOfObject1 == null);
      Constraint constraint = paramTable.fkConstraints[b];
      if (!bool)
        bool = ArrayUtil.haveCommonElement(constraint.getRefColumns(), paramArrayOfint); 
      if (bool)
        constraint.checkInsert(paramSession, paramTable, paramArrayOfObject2, (paramArrayOfObject1 == null)); 
      b++;
    } 
  }
  
  static void performReferentialActions(Session paramSession, Table paramTable, RowSetNavigatorDataChange paramRowSetNavigatorDataChange, Row paramRow, Object[] paramArrayOfObject, int[] paramArrayOfint, HashSet paramHashSet) {
    if (!paramSession.database.isReferentialIntegrity())
      return; 
    boolean bool = (paramArrayOfObject == null) ? true : false;
    byte b = 0;
    int i = paramTable.fkMainConstraints.length;
    while (b < i) {
      Constraint constraint = paramTable.fkMainConstraints[b];
      int j = bool ? constraint.core.deleteAction : constraint.core.updateAction;
      if (bool || (ArrayUtil.haveCommonElement(paramArrayOfint, constraint.core.mainCols) && constraint.core.mainIndex.compareRowNonUnique(paramSession, paramRow.getData(), paramArrayOfObject, constraint.core.mainCols) != 0)) {
        RowIterator rowIterator = constraint.findFkRef(paramSession, paramRow.getData());
        if (!rowIterator.hasNext()) {
          rowIterator.release();
        } else {
          while (rowIterator.hasNext()) {
            byte b1;
            String[] arrayOfString;
            Row row = rowIterator.getNextRow();
            Object[] arrayOfObject = null;
            if (constraint.core.refIndex.compareRowNonUnique(paramSession, row.getData(), paramRow.getData(), constraint.core.mainCols) != 0)
              break; 
            if (bool && row.getId() == paramRow.getId())
              continue; 
            switch (j) {
              case 0:
                if (bool) {
                  boolean bool1;
                  try {
                    bool1 = paramRowSetNavigatorDataChange.addRow(row);
                  } catch (HsqlException hsqlException) {
                    String[] arrayOfString1 = getConstraintInfo(constraint);
                    rowIterator.release();
                    throw Error.error(null, 3900, 2, arrayOfString1);
                  } 
                  if (bool1)
                    performReferentialActions(paramSession, constraint.core.refTable, paramRowSetNavigatorDataChange, row, (Object[])null, (int[])null, paramHashSet); 
                  continue;
                } 
                arrayOfObject = constraint.core.refTable.getEmptyRowData();
                System.arraycopy(row.getData(), 0, arrayOfObject, 0, arrayOfObject.length);
                for (b1 = 0; b1 < constraint.core.refCols.length; b1++)
                  arrayOfObject[constraint.core.refCols[b1]] = paramArrayOfObject[constraint.core.mainCols[b1]]; 
                break;
              case 2:
                arrayOfObject = constraint.core.refTable.getEmptyRowData();
                System.arraycopy(row.getData(), 0, arrayOfObject, 0, arrayOfObject.length);
                for (b1 = 0; b1 < constraint.core.refCols.length; b1++)
                  arrayOfObject[constraint.core.refCols[b1]] = null; 
                break;
              case 4:
                arrayOfObject = constraint.core.refTable.getEmptyRowData();
                System.arraycopy(row.getData(), 0, arrayOfObject, 0, arrayOfObject.length);
                for (b1 = 0; b1 < constraint.core.refCols.length; b1++) {
                  ColumnSchema columnSchema = constraint.core.refTable.getColumn(constraint.core.refCols[b1]);
                  arrayOfObject[constraint.core.refCols[b1]] = columnSchema.getDefaultValue(paramSession);
                } 
                break;
              case 3:
                if (paramRowSetNavigatorDataChange.containsDeletedRow(row))
                  continue; 
              case 1:
                b1 = (constraint.core.deleteAction == 3) ? 8 : 3501;
                arrayOfString = getConstraintInfo(constraint);
                rowIterator.release();
                throw Error.error(null, b1, 2, arrayOfString);
              default:
                continue;
            } 
            try {
              arrayOfObject = paramRowSetNavigatorDataChange.addRow(paramSession, row, arrayOfObject, constraint.core.refTable.getColumnTypes(), constraint.core.refCols);
            } catch (HsqlException hsqlException) {
              arrayOfString = getConstraintInfo(constraint);
              rowIterator.release();
              throw Error.error(null, 3900, 2, arrayOfString);
            } 
            if (arrayOfObject == null || !paramHashSet.add(constraint))
              continue; 
            performReferentialActions(paramSession, constraint.core.refTable, paramRowSetNavigatorDataChange, row, arrayOfObject, constraint.core.refCols, paramHashSet);
            paramHashSet.remove(constraint);
          } 
          rowIterator.release();
        } 
      } 
      b++;
    } 
  }
  
  static String[] getConstraintInfo(Constraint paramConstraint) {
    return new String[] { paramConstraint.core.refName.name, (paramConstraint.core.refTable.getName()).name };
  }
  
  public void clearStructures(Session paramSession) {
    paramSession.sessionContext.clearStructures(this);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementDML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */