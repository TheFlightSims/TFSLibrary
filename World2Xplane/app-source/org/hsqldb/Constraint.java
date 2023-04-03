package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public final class Constraint implements SchemaObject {
  ConstraintCore core;
  
  private HsqlNameManager.HsqlName name;
  
  int constType;
  
  boolean isForward;
  
  Expression check;
  
  private boolean isNotNull;
  
  int notNullColumnIndex;
  
  RangeVariable rangeVariable;
  
  OrderedHashSet mainColSet;
  
  OrderedHashSet refColSet;
  
  public static final Constraint[] emptyArray = new Constraint[0];
  
  private Constraint() {}
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Table paramTable, Index paramIndex, int paramInt) {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.core = new ConstraintCore();
    this.core.mainTable = paramTable;
    this.core.mainIndex = paramIndex;
    this.core.mainCols = paramIndex.getColumns();
    for (byte b = 0; b < this.core.mainCols.length; b++) {
      Type type = paramTable.getColumn(this.core.mainCols[b]).getDataType();
      if (type.isLobType())
        throw Error.error(5534); 
    } 
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Table paramTable, int[] paramArrayOfint, int paramInt) {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.core = new ConstraintCore();
    this.core.mainTable = paramTable;
    this.core.mainCols = paramArrayOfint;
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Constraint paramConstraint) {
    this.name = paramHsqlName;
    this.constType = 1;
    this.core = paramConstraint.core;
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, OrderedHashSet paramOrderedHashSet1, HsqlNameManager.HsqlName paramHsqlName3, OrderedHashSet paramOrderedHashSet2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.name = paramHsqlName1;
    this.constType = paramInt1;
    this.mainColSet = paramOrderedHashSet2;
    this.refColSet = paramOrderedHashSet1;
    this.core = new ConstraintCore();
    this.core.refTableName = paramHsqlName2;
    this.core.mainTableName = paramHsqlName3;
    this.core.deleteAction = paramInt2;
    this.core.updateAction = paramInt3;
    this.core.matchType = paramInt4;
    switch (this.core.deleteAction) {
      case 0:
      case 2:
      case 4:
        this.core.hasDeleteAction = true;
        break;
    } 
    switch (this.core.updateAction) {
      case 0:
      case 2:
      case 4:
        this.core.hasUpdateAction = true;
        break;
    } 
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet, int paramInt) {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.mainColSet = paramOrderedHashSet;
    this.core = new ConstraintCore();
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, HsqlNameManager.HsqlName paramHsqlName3, Table paramTable1, Table paramTable2, int[] paramArrayOfint1, int[] paramArrayOfint2, Index paramIndex1, Index paramIndex2, int paramInt1, int paramInt2) throws HsqlException {
    this.name = paramHsqlName3;
    this.constType = 0;
    this.core = new ConstraintCore();
    this.core.uniqueName = paramHsqlName1;
    this.core.mainName = paramHsqlName2;
    this.core.refName = paramHsqlName3;
    this.core.mainTable = paramTable1;
    this.core.refTable = paramTable2;
    this.core.mainCols = paramArrayOfint1;
    this.core.refCols = paramArrayOfint2;
    this.core.mainIndex = paramIndex1;
    this.core.refIndex = paramIndex2;
    this.core.deleteAction = paramInt1;
    this.core.updateAction = paramInt2;
  }
  
  Constraint duplicate() {
    Constraint constraint = new Constraint();
    constraint.core = this.core.duplicate();
    constraint.name = this.name;
    constraint.constType = this.constType;
    constraint.isForward = this.isForward;
    constraint.check = this.check;
    constraint.isNotNull = this.isNotNull;
    constraint.notNullColumnIndex = this.notNullColumnIndex;
    constraint.rangeVariable = this.rangeVariable;
    return constraint;
  }
  
  void setColumnsIndexes(Table paramTable) {
    if (this.constType == 0) {
      if (this.mainColSet == null) {
        this.core.mainCols = this.core.mainTable.getPrimaryKey();
        if (this.core.mainCols == null)
          throw Error.error(5581); 
      } else if (this.core.mainCols == null) {
        this.core.mainCols = this.core.mainTable.getColumnIndexes(this.mainColSet);
      } 
      if (this.core.refCols == null)
        this.core.refCols = paramTable.getColumnIndexes(this.refColSet); 
      for (byte b = 0; b < this.core.refCols.length; b++) {
        Type type = paramTable.getColumn(this.core.refCols[b]).getDataType();
        if (type.isLobType())
          throw Error.error(5534); 
      } 
      if (this.core.mainCols.length != this.core.refCols.length)
        throw Error.error(5593); 
    } else if (this.mainColSet != null) {
      this.core.mainCols = paramTable.getColumnIndexes(this.mainColSet);
      for (byte b = 0; b < this.core.mainCols.length; b++) {
        Type type = paramTable.getColumn(this.core.mainCols[b]).getDataType();
        if (type.isLobType())
          throw Error.error(5534); 
      } 
    } 
  }
  
  public int getType() {
    return 5;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    OrderedHashSet orderedHashSet1;
    int i;
    OrderedHashSet orderedHashSet2;
    switch (this.constType) {
      case 3:
        orderedHashSet1 = new OrderedHashSet();
        this.check.collectObjectNames((Set)orderedHashSet1);
        for (i = orderedHashSet1.size() - 1; i >= 0; i--) {
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet1.get(i);
          if (hsqlName.type == 9 || hsqlName.type == 3)
            orderedHashSet1.remove(i); 
        } 
        return orderedHashSet1;
      case 0:
        orderedHashSet2 = new OrderedHashSet();
        orderedHashSet2.add(this.core.uniqueName);
        return orderedHashSet2;
    } 
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    int[] arrayOfInt;
    StringBuffer stringBuffer = new StringBuffer();
    switch (getConstraintType()) {
      case 4:
        if ((getMainColumns()).length > 1 || ((getMainColumns()).length == 1 && !getName().isReservedName())) {
          if (!getName().isReservedName()) {
            stringBuffer.append("CONSTRAINT").append(' ');
            stringBuffer.append((getName()).statementName).append(' ');
          } 
          stringBuffer.append("PRIMARY").append(' ').append("KEY");
          stringBuffer.append(getMain().getColumnListSQL(getMainColumns(), (getMainColumns()).length));
        } 
        break;
      case 2:
        if (!getName().isReservedName()) {
          stringBuffer.append("CONSTRAINT").append(' ');
          stringBuffer.append((getName()).statementName);
          stringBuffer.append(' ');
        } 
        stringBuffer.append("UNIQUE");
        arrayOfInt = getMainColumns();
        stringBuffer.append(getMain().getColumnListSQL(arrayOfInt, arrayOfInt.length));
        break;
      case 0:
        if (this.isForward) {
          stringBuffer.append("ALTER").append(' ').append("TABLE").append(' ');
          stringBuffer.append(getRef().getName().getSchemaQualifiedStatementName());
          stringBuffer.append(' ').append("ADD").append(' ');
          getFKStatement(stringBuffer);
          break;
        } 
        getFKStatement(stringBuffer);
        break;
      case 3:
        if (isNotNull())
          break; 
        if (!getName().isReservedName()) {
          stringBuffer.append("CONSTRAINT").append(' ');
          stringBuffer.append((getName()).statementName).append(' ');
        } 
        stringBuffer.append("CHECK").append('(');
        stringBuffer.append(this.check.getSQL());
        stringBuffer.append(')');
        break;
    } 
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  private void getFKStatement(StringBuffer paramStringBuffer) {
    if (!getName().isReservedName()) {
      paramStringBuffer.append("CONSTRAINT").append(' ');
      paramStringBuffer.append((getName()).statementName);
      paramStringBuffer.append(' ');
    } 
    paramStringBuffer.append("FOREIGN").append(' ').append("KEY");
    int[] arrayOfInt = getRefColumns();
    paramStringBuffer.append(getRef().getColumnListSQL(arrayOfInt, arrayOfInt.length));
    paramStringBuffer.append(' ').append("REFERENCES").append(' ');
    paramStringBuffer.append(getMain().getName().getSchemaQualifiedStatementName());
    arrayOfInt = getMainColumns();
    paramStringBuffer.append(getMain().getColumnListSQL(arrayOfInt, arrayOfInt.length));
    if (getDeleteAction() != 3) {
      paramStringBuffer.append(' ').append("ON").append(' ').append("DELETE").append(' ');
      paramStringBuffer.append(getDeleteActionString());
    } 
    if (getUpdateAction() != 3) {
      paramStringBuffer.append(' ').append("ON").append(' ').append("UPDATE").append(' ');
      paramStringBuffer.append(getUpdateActionString());
    } 
  }
  
  public HsqlNameManager.HsqlName getMainTableName() {
    return this.core.mainTableName;
  }
  
  public HsqlNameManager.HsqlName getMainName() {
    return this.core.mainName;
  }
  
  public HsqlNameManager.HsqlName getRefName() {
    return this.core.refName;
  }
  
  public HsqlNameManager.HsqlName getUniqueName() {
    return this.core.uniqueName;
  }
  
  public int getConstraintType() {
    return this.constType;
  }
  
  public Table getMain() {
    return this.core.mainTable;
  }
  
  Index getMainIndex() {
    return this.core.mainIndex;
  }
  
  public Table getRef() {
    return this.core.refTable;
  }
  
  Index getRefIndex() {
    return this.core.refIndex;
  }
  
  private static String getActionString(int paramInt) {
    switch (paramInt) {
      case 1:
        return "RESTRICT";
      case 0:
        return "CASCADE";
      case 4:
        return "SET DEFAULT";
      case 2:
        return "SET NULL";
    } 
    return "NO ACTION";
  }
  
  public int getDeleteAction() {
    return this.core.deleteAction;
  }
  
  public String getDeleteActionString() {
    return getActionString(this.core.deleteAction);
  }
  
  public int getUpdateAction() {
    return this.core.updateAction;
  }
  
  public String getUpdateActionString() {
    return getActionString(this.core.updateAction);
  }
  
  public boolean hasTriggeredAction() {
    if (this.constType == 0) {
      switch (this.core.deleteAction) {
        case 0:
        case 2:
        case 4:
          return true;
      } 
      switch (this.core.updateAction) {
        case 0:
        case 2:
        case 4:
          return true;
      } 
    } 
    return false;
  }
  
  public int getDeferability() {
    return 0;
  }
  
  public int[] getMainColumns() {
    return this.core.mainCols;
  }
  
  public int[] getRefColumns() {
    return this.core.refCols;
  }
  
  public String getCheckSQL() {
    return this.check.getSQL();
  }
  
  public boolean isNotNull() {
    return this.isNotNull;
  }
  
  boolean hasColumnOnly(int paramInt) {
    switch (this.constType) {
      case 3:
        return (this.rangeVariable.usedColumns[paramInt] && ArrayUtil.countTrueElements(this.rangeVariable.usedColumns) == 1);
      case 2:
      case 4:
        return (this.core.mainCols.length == 1 && this.core.mainCols[0] == paramInt);
      case 1:
        return (this.core.mainCols.length == 1 && this.core.mainCols[0] == paramInt && this.core.mainTable == this.core.refTable);
      case 0:
        return (this.core.refCols.length == 1 && this.core.refCols[0] == paramInt && this.core.mainTable == this.core.refTable);
    } 
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean hasColumnPlus(int paramInt) {
    switch (this.constType) {
      case 3:
        return (this.rangeVariable.usedColumns[paramInt] && ArrayUtil.countTrueElements(this.rangeVariable.usedColumns) > 1);
      case 2:
      case 4:
        return (this.core.mainCols.length != 1 && ArrayUtil.find(this.core.mainCols, paramInt) != -1);
      case 1:
        return (ArrayUtil.find(this.core.mainCols, paramInt) != -1 && (this.core.mainCols.length != 1 || this.core.mainTable != this.core.refTable));
      case 0:
        return (ArrayUtil.find(this.core.refCols, paramInt) != -1 && (this.core.mainCols.length != 1 || this.core.mainTable != this.core.refTable));
    } 
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean hasColumn(int paramInt) {
    switch (this.constType) {
      case 3:
        return this.rangeVariable.usedColumns[paramInt];
      case 1:
      case 2:
      case 4:
        return (ArrayUtil.find(this.core.mainCols, paramInt) != -1);
      case 0:
        return (ArrayUtil.find(this.core.refCols, paramInt) != -1);
    } 
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean isUniqueWithColumns(int[] paramArrayOfint) {
    switch (this.constType) {
      case 2:
      case 4:
        if (this.core.mainCols.length == paramArrayOfint.length)
          return ArrayUtil.haveEqualSets(this.core.mainCols, paramArrayOfint, paramArrayOfint.length); 
        break;
    } 
    return false;
  }
  
  boolean isEquivalent(Table paramTable1, int[] paramArrayOfint1, Table paramTable2, int[] paramArrayOfint2) {
    switch (this.constType) {
      case 0:
      case 1:
        if (paramTable1 != this.core.mainTable || paramTable2 != this.core.refTable)
          return false; 
        if (this.core.mainCols.length == paramArrayOfint1.length && this.core.refCols.length == paramArrayOfint2.length)
          return (ArrayUtil.areEqualSets(this.core.mainCols, paramArrayOfint1) && ArrayUtil.areEqualSets(this.core.refCols, paramArrayOfint2)); 
        break;
    } 
    return false;
  }
  
  void updateTable(Session paramSession, Table paramTable1, Table paramTable2, int paramInt1, int paramInt2) {
    if (paramTable1 == this.core.mainTable) {
      this.core.mainTable = paramTable2;
      if (this.core.mainIndex != null) {
        this.core.mainIndex = this.core.mainTable.getIndex((this.core.mainIndex.getName()).name);
        this.core.mainCols = ArrayUtil.toAdjustedColumnArray(this.core.mainCols, paramInt1, paramInt2);
        this.core.mainIndex.setTable(paramTable2);
      } 
    } 
    if (paramTable1 == this.core.refTable) {
      this.core.refTable = paramTable2;
      if (this.core.refIndex != null) {
        this.core.refIndex = this.core.refTable.getIndex((this.core.refIndex.getName()).name);
        this.core.refCols = ArrayUtil.toAdjustedColumnArray(this.core.refCols, paramInt1, paramInt2);
        this.core.refIndex.setTable(paramTable2);
      } 
    } 
    if (this.constType == 3)
      recompile(paramSession, paramTable2); 
  }
  
  void checkInsert(Session paramSession, Table paramTable, Object[] paramArrayOfObject, boolean paramBoolean) {
    PersistentStore persistentStore;
    switch (this.constType) {
      case 3:
        if (!this.isNotNull)
          checkCheckConstraint(paramSession, paramTable, paramArrayOfObject); 
        return;
      case 0:
        persistentStore = this.core.mainTable.getRowStore(paramSession);
        if (ArrayUtil.hasNull(paramArrayOfObject, this.core.refCols)) {
          if (this.core.matchType == 59)
            return; 
          if (this.core.refCols.length == 1)
            return; 
          if (ArrayUtil.hasAllNull(paramArrayOfObject, this.core.refCols))
            return; 
        } else if (this.core.mainIndex.existsParent(paramSession, persistentStore, paramArrayOfObject, this.core.refCols)) {
          return;
        } 
        throw getException(paramArrayOfObject);
    } 
  }
  
  void checkCheckConstraint(Session paramSession, Table paramTable, Object[] paramArrayOfObject) {
    RangeVariable.RangeIteratorBase rangeIteratorBase = paramSession.sessionContext.getCheckIterator(this.rangeVariable);
    rangeIteratorBase.currentData = paramArrayOfObject;
    boolean bool = Boolean.FALSE.equals(this.check.getValue(paramSession));
    rangeIteratorBase.currentData = null;
    if (bool) {
      String[] arrayOfString = { this.name.name, (paramTable.getName()).name };
      throw Error.error(null, 157, 2, arrayOfString);
    } 
  }
  
  void checkCheckConstraint(Session paramSession, Table paramTable, ColumnSchema paramColumnSchema, Object paramObject) {
    paramSession.sessionData.currentValue = paramObject;
    boolean bool = Boolean.FALSE.equals(this.check.getValue(paramSession));
    paramSession.sessionData.currentValue = null;
    if (bool) {
      String[] arrayOfString = { this.name.statementName, (paramTable == null) ? "" : (paramTable.getName()).statementName, (paramColumnSchema == null) ? "" : (paramColumnSchema.getName()).statementName };
      throw Error.error(null, 157, 3, arrayOfString);
    } 
  }
  
  public HsqlException getException(Object[] paramArrayOfObject) {
    String[] arrayOfString1;
    StringBuffer stringBuffer;
    byte b2;
    String[] arrayOfString2;
    byte b1;
    switch (this.constType) {
      case 3:
        arrayOfString1 = new String[] { this.name.statementName };
        return Error.error(null, 157, 2, (Object[])arrayOfString1);
      case 0:
        stringBuffer = new StringBuffer();
        for (b2 = 0; b2 < this.core.refCols.length; b2++) {
          Object object = paramArrayOfObject[this.core.refCols[b2]];
          stringBuffer.append(this.core.refTable.getColumnTypes()[this.core.refCols[b2]].convertToString(object));
          stringBuffer.append(',');
        } 
        arrayOfString2 = new String[] { this.name.statementName, (this.core.refTable.getName()).statementName, stringBuffer.toString() };
        return Error.error(null, 177, 2, (Object[])arrayOfString2);
      case 2:
      case 4:
        stringBuffer = new StringBuffer();
        for (b1 = 0; b1 < this.core.mainCols.length; b1++) {
          Object object = paramArrayOfObject[this.core.mainCols[b1]];
          stringBuffer.append(this.core.mainTable.colTypes[this.core.mainCols[b1]].convertToString(object));
          stringBuffer.append(',');
        } 
        return Error.error(null, 104, 2, (Object[])new String[] { this.name.statementName, (this.core.mainTable.getName()).statementName, stringBuffer.toString() });
    } 
    throw Error.runtimeError(201, "Constraint");
  }
  
  RowIterator findFkRef(Session paramSession, Object[] paramArrayOfObject) {
    if (paramArrayOfObject == null || ArrayUtil.hasNull(paramArrayOfObject, this.core.mainCols))
      return this.core.refIndex.emptyIterator(); 
    PersistentStore persistentStore = this.core.refTable.getRowStore(paramSession);
    return this.core.refIndex.findFirstRow(paramSession, persistentStore, paramArrayOfObject, this.core.mainCols);
  }
  
  void checkReferencedRows(Session paramSession, Table paramTable) {
    RowIterator rowIterator = paramTable.rowIterator(paramSession);
    while (true) {
      Row row = rowIterator.getNextRow();
      if (row == null)
        break; 
      Object[] arrayOfObject = row.getData();
      checkInsert(paramSession, paramTable, arrayOfObject, false);
    } 
  }
  
  public Expression getCheckExpression() {
    return this.check;
  }
  
  public OrderedHashSet getCheckColumnExpressions() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    this.check.collectAllExpressions(orderedHashSet, Expression.columnExpressionSet, Expression.emptyExpressionSet);
    return orderedHashSet;
  }
  
  void recompile(Session paramSession, Table paramTable) {
    this.check = getNewCheckExpression(paramSession);
    QuerySpecification querySpecification = Expression.getCheckSelect(paramSession, paramTable, this.check);
    this.rangeVariable = querySpecification.rangeVariables[0];
    this.rangeVariable.setForCheckConstraint();
  }
  
  private Expression getNewCheckExpression(Session paramSession) {
    String str = this.check.getSQL();
    Scanner scanner = new Scanner(str);
    ParserDQL parserDQL = new ParserDQL(paramSession, scanner, null);
    parserDQL.compileContext.setNextRangeVarIndex(0);
    parserDQL.read();
    parserDQL.isCheckOrTriggerCondition = true;
    return parserDQL.XreadBooleanValueExpression();
  }
  
  void prepareCheckConstraint(Session paramSession, Table paramTable) {
    this.check.checkValidCheckConstraint();
    if (paramTable == null) {
      this.check.resolveTypes(paramSession, null);
    } else {
      QuerySpecification querySpecification = Expression.getCheckSelect(paramSession, paramTable, this.check);
      this.rangeVariable = querySpecification.rangeVariables[0];
      this.rangeVariable.setForCheckConstraint();
    } 
    if (this.check.getType() == 48 && this.check.getLeftNode().getType() == 47 && this.check.getLeftNode().getLeftNode().getType() == 2) {
      this.notNullColumnIndex = this.check.getLeftNode().getLeftNode().getColumnIndex();
      this.isNotNull = true;
    } 
  }
  
  void checkCheckConstraint(Session paramSession, Table paramTable) {
    if (paramTable.getRowStore(paramSession).elementCount() > 0L) {
      Expression expression = getNewCheckExpression(paramSession);
      QuerySpecification querySpecification = Expression.getCheckSelect(paramSession, paramTable, expression);
      Result result = querySpecification.getResult(paramSession, 1);
      if (result.getNavigator().getSize() != 0) {
        String[] arrayOfString = { this.name.statementName, (paramTable.getName()).statementName };
        throw Error.error(null, 157, 2, arrayOfString);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Constraint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */