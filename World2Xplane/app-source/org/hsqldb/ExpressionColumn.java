package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ExpressionColumn extends Expression {
  public static final ExpressionColumn[] emptyArray = new ExpressionColumn[0];
  
  static final HsqlNameManager.SimpleName rownumName = HsqlNameManager.getSimpleName("ROWNUM", false);
  
  public static final HashMappedList diagnosticsList = new HashMappedList();
  
  static final String[] diagnosticsVariableTokens = new String[] { "NUMBER", "MORE", "ROW_COUNT" };
  
  public static final int idx_number = 0;
  
  public static final int idx_more = 1;
  
  public static final int idx_row_count = 2;
  
  ColumnSchema column;
  
  String schema;
  
  String tableName;
  
  String columnName;
  
  RangeVariable rangeVariable;
  
  NumberSequence sequence;
  
  boolean isWritable;
  
  boolean isParam;
  
  ExpressionColumn(String paramString1, String paramString2, String paramString3) {
    super(2);
    this.schema = paramString1;
    this.tableName = paramString2;
    this.columnName = paramString3;
  }
  
  ExpressionColumn(ColumnSchema paramColumnSchema) {
    super(2);
    this.column = paramColumnSchema;
    this.dataType = paramColumnSchema.getDataType();
    this.columnName = (paramColumnSchema.getName()).name;
  }
  
  ExpressionColumn(RangeVariable paramRangeVariable, int paramInt) {
    super(2);
    this.columnIndex = paramInt;
    setAutoAttributesAsColumn(paramRangeVariable, this.columnIndex);
  }
  
  ExpressionColumn(Expression paramExpression, int paramInt1, int paramInt2) {
    super(5);
    this.dataType = paramExpression.dataType;
    this.columnIndex = paramInt1;
    this.alias = paramExpression.alias;
    this.rangePosition = paramInt2;
  }
  
  ExpressionColumn() {
    super(11);
  }
  
  ExpressionColumn(int paramInt) {
    super(paramInt);
    if (paramInt == 8) {
      this.isParam = true;
    } else if (paramInt == 14) {
      this.columnName = rownumName.name;
      this.dataType = (Type)Type.SQL_INTEGER;
    } 
  }
  
  ExpressionColumn(int paramInt1, int paramInt2) {
    super(paramInt1);
    this.column = (ColumnSchema)diagnosticsList.get(paramInt2);
    this.columnIndex = paramInt2;
    this.dataType = this.column.dataType;
  }
  
  ExpressionColumn(Expression[] paramArrayOfExpression, String paramString) {
    super(3);
    this.nodes = paramArrayOfExpression;
    this.columnName = paramString;
  }
  
  ExpressionColumn(String paramString1, String paramString2) {
    super(97);
    this.schema = paramString1;
    this.tableName = paramString2;
  }
  
  ExpressionColumn(NumberSequence paramNumberSequence, int paramInt) {
    super(paramInt);
    this.sequence = paramNumberSequence;
    this.dataType = paramNumberSequence.getDataType();
  }
  
  void setAutoAttributesAsColumn(RangeVariable paramRangeVariable, int paramInt) {
    this.columnIndex = paramInt;
    this.column = paramRangeVariable.getColumn(paramInt);
    this.dataType = this.column.getDataType();
    this.columnName = (paramRangeVariable.getColumnAlias(paramInt)).name;
    this.tableName = (paramRangeVariable.getTableAlias()).name;
    this.rangeVariable = paramRangeVariable;
    this.rangeVariable.addColumn(this.columnIndex);
  }
  
  void setAttributesAsColumn(RangeVariable paramRangeVariable, int paramInt) {
    this.columnIndex = paramInt;
    this.column = paramRangeVariable.getColumn(paramInt);
    this.dataType = this.column.getDataType();
    this.rangeVariable = paramRangeVariable;
    this.rangeVariable.addColumn(this.columnIndex);
  }
  
  public byte getNullability() {
    switch (this.opType) {
      case 2:
        return (this.nullability == 2) ? this.column.getNullability() : this.nullability;
      case 3:
      case 12:
      case 14:
        return 0;
    } 
    return 2;
  }
  
  void setAttributesAsColumn(ColumnSchema paramColumnSchema, boolean paramBoolean) {
    this.column = paramColumnSchema;
    this.dataType = paramColumnSchema.getDataType();
    this.isWritable = paramBoolean;
  }
  
  HsqlNameManager.SimpleName getSimpleName() {
    return (this.alias != null) ? this.alias : ((this.rangeVariable != null && this.rangeVariable.hasColumnAlias()) ? this.rangeVariable.getColumnAlias(this.columnIndex) : ((this.column != null) ? this.column.getName() : ((this.opType == 3) ? this.nodes[0].getSimpleName() : ((this.opType == 14) ? rownumName : null))));
  }
  
  String getAlias() {
    if (this.alias != null)
      return this.alias.name; 
    switch (this.opType) {
      case 2:
      case 3:
      case 14:
        return this.columnName;
    } 
    return "";
  }
  
  void collectObjectNames(Set paramSet) {
    HsqlNameManager.HsqlName hsqlName;
    switch (this.opType) {
      case 12:
        hsqlName = this.sequence.getName();
        paramSet.add(hsqlName);
        return;
      case 2:
        paramSet.add(this.column.getName());
        if ((this.column.getName()).parent != null)
          paramSet.add((this.column.getName()).parent); 
        return;
    } 
  }
  
  String getColumnName() {
    switch (this.opType) {
      case 2:
      case 6:
      case 7:
        if (this.column != null)
          return (this.column.getName()).name; 
        if (this.columnName != null)
          return this.columnName; 
        break;
    } 
    return getAlias();
  }
  
  public ColumnSchema getColumn() {
    return this.column;
  }
  
  String getSchemaName() {
    return this.schema;
  }
  
  RangeVariable getRangeVariable() {
    return this.rangeVariable;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    ArrayListIdentity arrayListIdentity;
    byte b;
    boolean bool;
    RangeVariable[] arrayOfRangeVariable;
    int i;
    switch (this.opType) {
      case 12:
        if (!paramBoolean)
          throw Error.error(5598); 
        break;
      case 3:
        for (b = 0; b < this.nodes.length; b++)
          this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramArrayOfRangeGroup, paramHsqlList); 
        break;
      case 2:
      case 6:
      case 7:
        b = 0;
        bool = (this.tableName != null) ? true : false;
        if (this.rangeVariable != null)
          return paramHsqlList; 
        arrayOfRangeVariable = paramRangeGroup.getRangeVariables();
        for (i = 0; i < paramInt; i++) {
          RangeVariable rangeVariable = arrayOfRangeVariable[i];
          if (rangeVariable != null)
            if (b != 0) {
              if (paramSession.database.sqlEnforceRefs && resolvesDuplicateColumnReference(rangeVariable)) {
                String str = getColumnName();
                if (this.alias != null) {
                  StringBuffer stringBuffer = new StringBuffer(str);
                  stringBuffer.append(' ').append("AS").append(' ').append(this.alias.getStatementName());
                  str = stringBuffer.toString();
                } 
                throw Error.error(5580, str);
              } 
            } else if (resolveColumnReference(rangeVariable, false)) {
              if (bool)
                return paramHsqlList; 
              b = 1;
            }  
        } 
        if (b != 0)
          return paramHsqlList; 
        if ((paramSession.database.sqlSyntaxOra || paramSession.database.sqlSyntaxDb2) && paramBoolean && this.tableName != null)
          if ("CURRVAL".equals(this.columnName) || "PREVVAL".equals(this.columnName)) {
            NumberSequence numberSequence = paramSession.database.schemaManager.getSequence(this.tableName, paramSession.getSchemaName(this.schema), false);
            if (numberSequence != null) {
              this.opType = 13;
              this.dataType = numberSequence.getDataType();
              this.sequence = numberSequence;
              this.schema = null;
              this.tableName = null;
              this.columnName = null;
              b = 1;
            } 
          } else if ("NEXTVAL".equals(this.columnName)) {
            NumberSequence numberSequence = paramSession.database.schemaManager.getSequence(this.tableName, paramSession.getSchemaName(this.schema), false);
            if (numberSequence != null) {
              this.opType = 12;
              this.dataType = numberSequence.getDataType();
              this.sequence = numberSequence;
              this.schema = null;
              this.tableName = null;
              this.columnName = null;
              b = 1;
            } 
          }  
        if (b != 0)
          return paramHsqlList; 
        for (i = paramArrayOfRangeGroup.length - 1; i >= 0; i--) {
          arrayOfRangeVariable = paramArrayOfRangeGroup[i].getRangeVariables();
          for (byte b1 = 0; b1 < arrayOfRangeVariable.length; b1++) {
            RangeVariable rangeVariable = arrayOfRangeVariable[b1];
            if (rangeVariable != null && resolveColumnReference(rangeVariable, true)) {
              if (this.opType == 2) {
                paramRangeGroup.setCorrelated();
                for (int j = paramArrayOfRangeGroup.length - 1; j > i; j--)
                  paramArrayOfRangeGroup[j].setCorrelated(); 
              } 
              return paramHsqlList;
            } 
          } 
        } 
        if (paramHsqlList == null)
          arrayListIdentity = new ArrayListIdentity(); 
        arrayListIdentity.add(this);
        break;
    } 
    return (HsqlList)arrayListIdentity;
  }
  
  private boolean resolveColumnReference(RangeVariable paramRangeVariable, boolean paramBoolean) {
    ColumnSchema columnSchema;
    if (this.tableName == null) {
      ExpressionColumn expressionColumn = paramRangeVariable.getColumnExpression(this.columnName);
      if (expressionColumn != null) {
        this.opType = expressionColumn.opType;
        this.nodes = expressionColumn.nodes;
        this.dataType = expressionColumn.dataType;
        return true;
      } 
    } 
    int i = paramRangeVariable.findColumn(this.schema, this.tableName, this.columnName);
    if (i == -1)
      return false; 
    switch (paramRangeVariable.rangeType) {
      case 3:
      case 4:
        if (this.tableName != null)
          return false; 
        columnSchema = paramRangeVariable.getColumn(i);
        if (columnSchema.getParameterMode() == 4)
          return false; 
        this.opType = (paramRangeVariable.rangeType == 4) ? 6 : 7;
        break;
      case 2:
        if (this.tableName == null)
          return false; 
        if (this.schema != null)
          return false; 
        this.opType = 9;
        break;
    } 
    setAttributesAsColumn(paramRangeVariable, i);
    return true;
  }
  
  boolean resolvesDuplicateColumnReference(RangeVariable paramRangeVariable) {
    if (this.tableName == null) {
      ExpressionColumn expressionColumn = paramRangeVariable.getColumnExpression(this.columnName);
      if (expressionColumn != null)
        return false; 
      switch (paramRangeVariable.rangeType) {
        case 2:
        case 3:
        case 4:
          return false;
      } 
      int i = paramRangeVariable.findColumn(this.schema, this.tableName, this.columnName);
      return (i != -1);
    } 
    return false;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    Type type;
    byte b;
    switch (this.opType) {
      case 4:
        if (paramExpression != null && paramExpression.opType != 25)
          throw Error.error(5544); 
        break;
      case 3:
        type = null;
        this.nullability = 0;
        for (b = 0; b < this.nodes.length; b++)
          type = Type.getAggregateType((this.nodes[b]).dataType, type); 
        this.dataType = type;
        break;
    } 
  }
  
  public Object getValue(Session paramSession) {
    Object object1;
    Object object2;
    byte b;
    switch (this.opType) {
      case 4:
        return null;
      case 10:
        return getDiagnosticsVariable(paramSession);
      case 6:
        return paramSession.sessionContext.routineVariables[this.columnIndex];
      case 7:
        return paramSession.sessionContext.routineArguments[this.columnIndex];
      case 9:
        return paramSession.sessionContext.triggerArguments[this.rangeVariable.rangePosition][this.columnIndex];
      case 2:
        object1 = paramSession.sessionContext.rangeIterators;
        object2 = object1[this.rangeVariable.rangePosition].getCurrent(this.columnIndex);
        if (this.dataType != this.column.dataType)
          object2 = this.dataType.convertToType(paramSession, object2, this.column.dataType); 
        return object2;
      case 5:
        return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      case 3:
        object1 = null;
        for (b = 0; b < this.nodes.length; b++) {
          object1 = this.nodes[b].getValue(paramSession, this.dataType);
          if (object1 != null)
            return object1; 
        } 
        return object1;
      case 8:
        return paramSession.sessionContext.dynamicArguments[this.parameterIndex];
      case 12:
        return paramSession.sessionData.getSequenceValue(this.sequence);
      case 13:
        return paramSession.sessionData.getSequenceCurrent(this.sequence);
      case 14:
        return ValuePool.getInt(paramSession.sessionContext.rownum);
    } 
    throw Error.runtimeError(201, "ExpressionColumn");
  }
  
  private Object getDiagnosticsVariable(Session paramSession) {
    return paramSession.sessionContext.diagnosticsVariables[this.columnIndex];
  }
  
  public String getSQL() {
    StringBuffer stringBuffer;
    byte b;
    switch (this.opType) {
      case 4:
        return "DEFAULT";
      case 8:
        return "?";
      case 11:
        return "*";
      case 3:
        return this.alias.getStatementName();
      case 6:
      case 7:
      case 10:
        return (this.column.getName()).statementName;
      case 14:
        stringBuffer = new StringBuffer("ROWNUM");
        stringBuffer.append('(').append(')');
        return stringBuffer.toString();
      case 2:
        if (this.column == null) {
          if (this.alias != null)
            return this.alias.getStatementName(); 
          if (this.tableName == null)
            return this.columnName; 
          stringBuffer = new StringBuffer();
          stringBuffer.append(this.tableName);
          stringBuffer.append('.');
          stringBuffer.append(this.columnName);
          return stringBuffer.toString();
        } 
        if (this.rangeVariable.tableAlias == null)
          return this.column.getName().getSchemaQualifiedStatementName(); 
        stringBuffer = new StringBuffer();
        stringBuffer.append(this.rangeVariable.tableAlias.getStatementName());
        stringBuffer.append('.');
        stringBuffer.append((this.column.getName()).statementName);
        return stringBuffer.toString();
      case 97:
        if (this.nodes.length == 0)
          return "*"; 
        stringBuffer = new StringBuffer();
        for (b = 0; b < this.nodes.length; b++) {
          Expression expression = this.nodes[b];
          if (b > 0)
            stringBuffer.append(','); 
          String str = expression.getSQL();
          stringBuffer.append(str);
        } 
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "ExpressionColumn");
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    switch (this.opType) {
      case 4:
        stringBuffer.append("DEFAULT");
        break;
      case 11:
        stringBuffer.append("OpTypes.ASTERISK ");
        break;
      case 6:
        stringBuffer.append("VARIABLE: ");
        stringBuffer.append((this.column.getName()).name);
        break;
      case 7:
        stringBuffer.append("PARAMETER").append(": ");
        stringBuffer.append((this.column.getName()).name);
        break;
      case 3:
        stringBuffer.append("COLUMN").append(": ");
        stringBuffer.append(this.columnName);
        if (this.alias != null)
          stringBuffer.append(" AS ").append(this.alias.name); 
        break;
      case 2:
        stringBuffer.append("COLUMN").append(": ");
        stringBuffer.append(this.column.getName().getSchemaQualifiedStatementName());
        if (this.alias != null)
          stringBuffer.append(" AS ").append(this.alias.name); 
        break;
      case 8:
        stringBuffer.append("DYNAMIC PARAM: ");
        stringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
        break;
      case 12:
        stringBuffer.append("SEQUENCE").append(": ");
        stringBuffer.append((this.sequence.getName()).name);
        break;
    } 
    stringBuffer.append('\n');
    return stringBuffer.toString();
  }
  
  String getTableName() {
    return (this.opType == 97) ? this.tableName : ((this.opType == 2) ? ((this.rangeVariable == null) ? this.tableName : (this.rangeVariable.getTable().getName()).name) : "");
  }
  
  static void checkColumnsResolved(HsqlList paramHsqlList) {
    if (paramHsqlList != null && !paramHsqlList.isEmpty()) {
      StringBuffer stringBuffer = new StringBuffer();
      Expression expression = (Expression)paramHsqlList.get(0);
      if (expression instanceof ExpressionColumn) {
        ExpressionColumn expressionColumn = (ExpressionColumn)expression;
        if (expressionColumn.schema != null)
          stringBuffer.append(expressionColumn.schema + '.'); 
        if (expressionColumn.tableName != null)
          stringBuffer.append(expressionColumn.tableName + '.'); 
        stringBuffer.append(expressionColumn.getColumnName());
        throw Error.error(5501, stringBuffer.toString());
      } 
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      expression.collectAllExpressions(orderedHashSet, Expression.columnExpressionSet, Expression.emptyExpressionSet);
      checkColumnsResolved((HsqlList)orderedHashSet);
      throw Error.error(5501);
    } 
  }
  
  public OrderedHashSet getUnkeyedColumns(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].getUnkeyedColumns(paramOrderedHashSet); 
    } 
    if (this.opType == 2 && !this.rangeVariable.hasKeyedColumnInGroupBy) {
      if (paramOrderedHashSet == null)
        paramOrderedHashSet = new OrderedHashSet(); 
      paramOrderedHashSet.add(this);
    } 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectRangeVariables(paramOrderedHashSet); 
    } 
    if (this.rangeVariable != null) {
      if (paramOrderedHashSet == null)
        paramOrderedHashSet = new OrderedHashSet(); 
      paramOrderedHashSet.add(this.rangeVariable);
    } 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet) {
    byte b;
    for (b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    } 
    if (this.rangeVariable != null)
      for (b = 0; b < paramArrayOfRangeVariable.length; b++) {
        if (paramArrayOfRangeVariable[b] == this.rangeVariable) {
          if (paramOrderedHashSet == null)
            paramOrderedHashSet = new OrderedHashSet(); 
          paramOrderedHashSet.add(this.rangeVariable);
          break;
        } 
      }  
    return paramOrderedHashSet;
  }
  
  Expression replaceAliasInOrderBy(Session paramSession, Expression[] paramArrayOfExpression, int paramInt) {
    byte b1;
    byte b;
    for (b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b] = this.nodes[b].replaceAliasInOrderBy(paramSession, paramArrayOfExpression, paramInt); 
    } 
    switch (this.opType) {
      case 2:
      case 3:
        b = -1;
        for (b1 = 0; b1 < paramInt; b1++) {
          HsqlNameManager.SimpleName simpleName = (paramArrayOfExpression[b1]).alias;
          String str = (simpleName == null) ? null : simpleName.name;
          if (this.schema == null && this.tableName == null && this.columnName.equals(str))
            if (b < 0) {
              b = b1;
            } else if (paramSession.database.sqlEnforceRefs) {
              String str1 = getColumnName();
              throw Error.error(5580, str1);
            }  
        } 
        if (b >= 0)
          return paramArrayOfExpression[b]; 
        for (b1 = 0; b1 < paramInt; b1++) {
          if (paramArrayOfExpression[b1] instanceof ExpressionColumn) {
            if (equals(paramArrayOfExpression[b1]))
              if (b < 0) {
                b = b1;
              } else if (paramSession.database.sqlEnforceRefs) {
                String str = getColumnName();
                throw Error.error(5580, str);
              }  
            if (this.tableName == null && this.schema == null && this.columnName.equals(((ExpressionColumn)paramArrayOfExpression[b1]).columnName))
              if (b < 0) {
                b = b1;
              } else if (paramSession.database.sqlEnforceRefs) {
                String str = getColumnName();
                throw Error.error(5580, str);
              }  
          } 
        } 
        if (b >= 0)
          return paramArrayOfExpression[b]; 
        break;
    } 
    return this;
  }
  
  Expression replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {
    if (this.opType == 2 && this.rangeVariable == paramRangeVariable)
      return paramArrayOfExpression[this.columnIndex]; 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b] = this.nodes[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    } 
    return this;
  }
  
  boolean hasReference(RangeVariable paramRangeVariable) {
    if (paramRangeVariable == this.rangeVariable)
      return true; 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null && this.nodes[b].hasReference(paramRangeVariable))
        return true; 
    } 
    return false;
  }
  
  public boolean equals(Expression paramExpression) {
    if (paramExpression == this)
      return true; 
    if (paramExpression == null)
      return false; 
    if (this.opType != paramExpression.opType)
      return false; 
    switch (this.opType) {
      case 5:
        return (this.columnIndex == paramExpression.columnIndex);
      case 3:
        return (this.nodes == paramExpression.nodes);
      case 2:
      case 6:
      case 7:
        return (this.column == paramExpression.getColumn() && this.rangeVariable == paramExpression.getRangeVariable());
    } 
    return false;
  }
  
  void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    byte b;
    for (b = 0; b < this.nodes.length; b++)
      this.nodes[b].replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
    for (b = 0; b < paramArrayOfRangeVariable1.length; b++) {
      if (this.rangeVariable == paramArrayOfRangeVariable1[b]) {
        this.rangeVariable = paramArrayOfRangeVariable2[b];
        break;
      } 
    } 
  }
  
  void resetColumnReferences() {
    this.rangeVariable = null;
    this.columnIndex = -1;
  }
  
  public boolean isIndexable(RangeVariable paramRangeVariable) {
    return (this.opType == 2) ? ((this.rangeVariable == paramRangeVariable)) : false;
  }
  
  public boolean isUnresolvedParam() {
    return (this.isParam && this.dataType == null);
  }
  
  boolean isDynamicParam() {
    return this.isParam;
  }
  
  RangeVariable[] getJoinRangeVariables(RangeVariable[] paramArrayOfRangeVariable) {
    return (this.opType == 2) ? new RangeVariable[] { this.rangeVariable } : RangeVariable.emptyArray;
  }
  
  double costFactor(Session paramSession, RangeVariable paramRangeVariable, int paramInt) {
    double d;
    if (paramRangeVariable.rangeTable instanceof TableDerived)
      return 1024.0D; 
    PersistentStore persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
    int i = paramRangeVariable.rangeTable.indexTypeForColumn(paramSession, this.columnIndex);
    switch (i) {
      case 2:
        if (paramInt == 40) {
          double d1 = 1.0D;
          break;
        } 
        d = (persistentStore.elementCount() / 2L);
        break;
      case 1:
        if (paramInt == 40) {
          d = (persistentStore.elementCount() / 8L);
          if (d > 1024.0D)
            d = 1024.0D; 
          break;
        } 
        d = (persistentStore.elementCount() / 2L);
        break;
      default:
        d = persistentStore.elementCount();
        break;
    } 
    return (d < 16.0D) ? 16.0D : d;
  }
  
  public Expression duplicate() {
    return (this.opType == 7) ? this : super.duplicate();
  }
  
  static {
    for (byte b = 0; b < diagnosticsVariableTokens.length; b++) {
      CharacterType characterType;
      HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newSystemObjectName(diagnosticsVariableTokens[b], 22);
      NumberType numberType = Type.SQL_INTEGER;
      if (diagnosticsVariableTokens[b] == "MORE")
        characterType = Type.SQL_CHAR; 
      ColumnSchema columnSchema = new ColumnSchema(hsqlName, (Type)characterType, false, false, null);
      diagnosticsList.add(diagnosticsVariableTokens[b], columnSchema);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */