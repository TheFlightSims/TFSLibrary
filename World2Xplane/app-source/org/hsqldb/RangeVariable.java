package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.OrderedLongHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public class RangeVariable implements Cloneable {
  static final RangeVariable[] emptyArray = new RangeVariable[0];
  
  public static final int TABLE_RANGE = 1;
  
  public static final int TRANSITION_RANGE = 2;
  
  public static final int PARAMETER_RANGE = 3;
  
  public static final int VARIALBE_RANGE = 4;
  
  Table rangeTable;
  
  final HsqlNameManager.SimpleName tableAlias;
  
  private OrderedHashSet columnAliases;
  
  private HsqlNameManager.SimpleName[] columnAliasNames;
  
  private OrderedHashSet columnNames;
  
  OrderedHashSet namedJoinColumns;
  
  HashMap namedJoinColumnExpressions;
  
  private Object[] emptyData;
  
  boolean[] columnsInGroupBy;
  
  boolean hasKeyedColumnInGroupBy;
  
  boolean[] usedColumns;
  
  boolean[] updatedColumns;
  
  RangeVariableConditions[] joinConditions;
  
  RangeVariableConditions[] whereConditions;
  
  int subRangeCount;
  
  Expression joinCondition;
  
  boolean isLateral;
  
  boolean isLeftJoin;
  
  boolean isRightJoin;
  
  boolean isBoundary;
  
  boolean hasLateral;
  
  boolean hasLeftJoin;
  
  boolean hasRightJoin;
  
  int level;
  
  int indexDistinctCount;
  
  int rangePositionInJoin;
  
  int rangePosition;
  
  int parsePosition;
  
  HashMappedList variables;
  
  int rangeType;
  
  boolean isGenerated;
  
  public RangeVariable(HashMappedList paramHashMappedList, HsqlNameManager.SimpleName paramSimpleName, boolean paramBoolean, int paramInt) {
    this.variables = paramHashMappedList;
    this.rangeType = paramInt;
    this.rangeTable = null;
    this.tableAlias = paramSimpleName;
    this.emptyData = null;
    this.columnsInGroupBy = null;
    this.usedColumns = null;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
    switch (paramInt) {
      case 2:
      case 3:
      case 4:
        return;
    } 
    throw Error.runtimeError(201, "RangeVariable");
  }
  
  public RangeVariable(Table paramTable, HsqlNameManager.SimpleName paramSimpleName, OrderedHashSet paramOrderedHashSet, HsqlNameManager.SimpleName[] paramArrayOfSimpleName, ParserDQL.CompileContext paramCompileContext) {
    this.rangeType = 1;
    this.rangeTable = paramTable;
    this.tableAlias = paramSimpleName;
    this.columnAliases = paramOrderedHashSet;
    this.columnAliasNames = paramArrayOfSimpleName;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
    paramCompileContext.registerRangeVariable(this);
    if (this.rangeTable.getColumnCount() != 0)
      setRangeTableVariables(); 
  }
  
  public RangeVariable(Table paramTable, int paramInt) {
    this.rangeType = 1;
    this.rangeTable = paramTable;
    this.tableAlias = null;
    this.emptyData = this.rangeTable.getEmptyRowData();
    this.columnsInGroupBy = this.rangeTable.getNewColumnCheckList();
    this.usedColumns = this.rangeTable.getNewColumnCheckList();
    this.rangePosition = paramInt;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
  }
  
  public void setRangeTableVariables() {
    if (this.columnAliasNames != null && this.rangeTable.getColumnCount() != this.columnAliasNames.length)
      throw Error.error(5593); 
    this.emptyData = this.rangeTable.getEmptyRowData();
    this.columnsInGroupBy = this.rangeTable.getNewColumnCheckList();
    this.usedColumns = this.rangeTable.getNewColumnCheckList();
    (this.joinConditions[0]).rangeIndex = this.rangeTable.getPrimaryIndex();
    (this.whereConditions[0]).rangeIndex = this.rangeTable.getPrimaryIndex();
  }
  
  public void setJoinType(boolean paramBoolean1, boolean paramBoolean2) {
    this.isLeftJoin = paramBoolean1;
    this.isRightJoin = paramBoolean2;
    if (this.isRightJoin)
      (this.whereConditions[0]).rangeIndex = this.rangeTable.getPrimaryIndex(); 
  }
  
  public void addNamedJoinColumns(OrderedHashSet paramOrderedHashSet) {
    this.namedJoinColumns = paramOrderedHashSet;
  }
  
  public void addColumn(int paramInt) {
    if (this.usedColumns != null)
      this.usedColumns[paramInt] = true; 
  }
  
  public void addAllColumns() {
    if (this.usedColumns != null)
      ArrayUtil.fillArray(this.usedColumns, true); 
  }
  
  public void addNamedJoinColumnExpression(String paramString, Expression paramExpression) {
    if (this.namedJoinColumnExpressions == null)
      this.namedJoinColumnExpressions = new HashMap(); 
    this.namedJoinColumnExpressions.put(paramString, paramExpression);
  }
  
  public ExpressionColumn getColumnExpression(String paramString) {
    return (this.namedJoinColumnExpressions == null) ? null : (ExpressionColumn)this.namedJoinColumnExpressions.get(paramString);
  }
  
  public Table getTable() {
    return this.rangeTable;
  }
  
  public boolean hasAnyIndexCondition() {
    byte b;
    for (b = 0; b < this.joinConditions.length; b++) {
      if ((this.joinConditions[0]).indexedColumnCount > 0)
        return true; 
    } 
    for (b = 0; b < this.whereConditions.length; b++) {
      if ((this.whereConditions[0]).indexedColumnCount > 0)
        return true; 
    } 
    return false;
  }
  
  public boolean hasSingleIndexCondition() {
    return (this.joinConditions.length == 1 && (this.joinConditions[0]).indexedColumnCount > 0);
  }
  
  public boolean setDistinctColumnsOnIndex(int[] paramArrayOfint) {
    if (this.joinConditions.length != 1)
      return false; 
    int[] arrayOfInt = (this.joinConditions[0]).rangeIndex.getColumns();
    if (paramArrayOfint.length > arrayOfInt.length)
      return false; 
    if (paramArrayOfint.length == arrayOfInt.length && ArrayUtil.haveEqualSets(paramArrayOfint, arrayOfInt, paramArrayOfint.length)) {
      this.indexDistinctCount = paramArrayOfint.length;
      return true;
    } 
    if (ArrayUtil.haveEqualArrays(paramArrayOfint, arrayOfInt, paramArrayOfint.length)) {
      this.indexDistinctCount = paramArrayOfint.length;
      return true;
    } 
    return false;
  }
  
  public Index getSortIndex() {
    return (this.joinConditions.length == 1) ? (this.joinConditions[0]).rangeIndex : null;
  }
  
  public boolean setSortIndex(Index paramIndex, boolean paramBoolean) {
    if (this.joinConditions.length == 1 && (this.joinConditions[0]).indexedColumnCount == 0) {
      (this.joinConditions[0]).rangeIndex = paramIndex;
      (this.joinConditions[0]).reversed = paramBoolean;
      return true;
    } 
    return false;
  }
  
  public boolean reverseOrder() {
    if (this.joinConditions.length == 1 && (this.joinConditions[0]).indexedColumnCount > 0) {
      this.joinConditions[0].reverseIndexCondition();
      return true;
    } 
    return false;
  }
  
  public OrderedHashSet getColumnNames() {
    if (this.columnNames == null) {
      this.columnNames = new OrderedHashSet();
      this.rangeTable.getColumnNames(this.usedColumns, (Set)this.columnNames);
    } 
    return this.columnNames;
  }
  
  public OrderedHashSet getUniqueColumnNameSet() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    if (this.columnAliases != null) {
      orderedHashSet.addAll((Collection)this.columnAliases);
      return orderedHashSet;
    } 
    for (byte b = 0; b < this.rangeTable.columnList.size(); b++) {
      String str = (this.rangeTable.getColumn(b).getName()).name;
      boolean bool = orderedHashSet.add(str);
      if (!bool)
        throw Error.error(5578, str); 
    } 
    return orderedHashSet;
  }
  
  public int findColumn(String paramString1, String paramString2, String paramString3) {
    return (this.namedJoinColumnExpressions != null && this.namedJoinColumnExpressions.containsKey(paramString3) && paramString2 != null) ? -1 : (resolvesSchemaAndTableName(paramString1, paramString2) ? findColumn(paramString3) : -1);
  }
  
  private int findColumn(String paramString) {
    return (this.variables != null) ? this.variables.getIndex(paramString) : ((this.columnAliases != null) ? this.columnAliases.getIndex(paramString) : this.rangeTable.findColumn(paramString));
  }
  
  public ColumnSchema getColumn(int paramInt) {
    return (this.variables == null) ? this.rangeTable.getColumn(paramInt) : (ColumnSchema)this.variables.get(paramInt);
  }
  
  public HsqlNameManager.SimpleName getColumnAlias(int paramInt) {
    return (this.columnAliases == null) ? this.rangeTable.getColumn(paramInt).getName() : this.columnAliasNames[paramInt];
  }
  
  public boolean hasColumnAlias() {
    return (this.columnAliases != null);
  }
  
  public boolean hasTableAlias() {
    return (this.tableAlias != null);
  }
  
  public boolean isVariable() {
    return (this.variables != null);
  }
  
  public HsqlNameManager.SimpleName getTableAlias() {
    return (this.tableAlias == null) ? this.rangeTable.getName() : this.tableAlias;
  }
  
  public RangeVariable getRangeForTableName(String paramString) {
    return resolvesTableName(paramString) ? this : null;
  }
  
  private boolean resolvesSchemaAndTableName(String paramString1, String paramString2) {
    return (resolvesSchemaName(paramString1) && resolvesTableName(paramString2));
  }
  
  private boolean resolvesTableName(String paramString) {
    if (paramString == null)
      return true; 
    if (this.variables != null)
      return (this.tableAlias != null) ? paramString.equals(this.tableAlias.name) : false; 
    if (this.tableAlias == null) {
      if (paramString.equals((this.rangeTable.getName()).name))
        return true; 
    } else if (paramString.equals(this.tableAlias.name)) {
      return true;
    } 
    return false;
  }
  
  private boolean resolvesSchemaName(String paramString) {
    return (paramString == null) ? true : ((this.variables != null) ? false : ((this.tableAlias != null) ? false : paramString.equals((this.rangeTable.getSchemaName()).name)));
  }
  
  public void addTableColumns(HsqlArrayList paramHsqlArrayList) {
    if (this.namedJoinColumns != null) {
      int i = paramHsqlArrayList.size();
      byte b1 = 0;
      for (byte b2 = 0; b2 < i; b2++) {
        Expression expression = (Expression)paramHsqlArrayList.get(b2);
        String str = expression.getColumnName();
        if (this.namedJoinColumns.contains(str)) {
          if (b1 != b2) {
            paramHsqlArrayList.remove(b2);
            paramHsqlArrayList.add(b1, expression);
          } 
          expression = getColumnExpression(str);
          paramHsqlArrayList.set(b1, expression);
          b1++;
        } 
      } 
    } 
    addTableColumns(paramHsqlArrayList, paramHsqlArrayList.size(), (HashSet)this.namedJoinColumns);
  }
  
  public int addTableColumns(HsqlArrayList paramHsqlArrayList, int paramInt, HashSet paramHashSet) {
    Table table = getTable();
    int i = table.getColumnCount();
    for (byte b = 0; b < i; b++) {
      ColumnSchema columnSchema = table.getColumn(b);
      String str = (this.columnAliases == null) ? (columnSchema.getName()).name : (String)this.columnAliases.get(b);
      if (paramHashSet == null || !paramHashSet.contains(str)) {
        ExpressionColumn expressionColumn = new ExpressionColumn(this, b);
        paramHsqlArrayList.add(paramInt++, expressionColumn);
      } 
    } 
    return paramInt;
  }
  
  public void addTableColumns(RangeVariable paramRangeVariable, Expression paramExpression, HashSet paramHashSet) {
    if (paramRangeVariable == this) {
      Table table = getTable();
      int i = table.getColumnCount();
      addTableColumns(paramExpression, 0, i, paramHashSet);
    } 
  }
  
  protected int getFirstColumnIndex(RangeVariable paramRangeVariable) {
    return (paramRangeVariable == this) ? 0 : -1;
  }
  
  protected void addTableColumns(Expression paramExpression, int paramInt1, int paramInt2, HashSet paramHashSet) {
    Table table = getTable();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
      ColumnSchema columnSchema = table.getColumn(i);
      String str = (this.columnAliases == null) ? (columnSchema.getName()).name : (String)this.columnAliases.get(i);
      if (paramHashSet == null || !paramHashSet.contains(str)) {
        ExpressionColumn expressionColumn = new ExpressionColumn(this, i);
        hsqlArrayList.add(expressionColumn);
      } 
    } 
    Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression);
    paramExpression.nodes = arrayOfExpression;
  }
  
  public void setForCheckConstraint() {
    (this.joinConditions[0]).rangeIndex = null;
    (this.whereConditions[0]).rangeIndex = null;
    this.rangePosition = 0;
  }
  
  public Expression getJoinCondition() {
    return this.joinCondition;
  }
  
  public void addJoinCondition(Expression paramExpression) {
    this.joinCondition = ExpressionLogical.andExpressions(this.joinCondition, paramExpression);
  }
  
  public void resetConditions() {
    Index index = (this.joinConditions[0]).rangeIndex;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    (this.joinConditions[0]).rangeIndex = index;
    index = (this.whereConditions[0]).rangeIndex;
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
    (this.whereConditions[0]).rangeIndex = index;
  }
  
  public OrderedHashSet getSubqueries() {
    OrderedHashSet orderedHashSet = null;
    if (this.joinCondition != null)
      orderedHashSet = this.joinCondition.collectAllSubqueries(orderedHashSet); 
    if (this.rangeTable instanceof TableDerived) {
      QueryExpression queryExpression = ((TableDerived)this.rangeTable).getQueryExpression();
      if (queryExpression == null) {
        Expression expression = ((TableDerived)this.rangeTable).getDataExpression();
        if (expression != null) {
          if (orderedHashSet == null)
            orderedHashSet = new OrderedHashSet(); 
          OrderedHashSet.addAll(orderedHashSet, expression.getSubqueries());
        } 
      } else {
        OrderedHashSet orderedHashSet1 = queryExpression.getSubqueries();
        orderedHashSet = OrderedHashSet.addAll(orderedHashSet, orderedHashSet1);
        orderedHashSet = OrderedHashSet.add(orderedHashSet, this.rangeTable);
      } 
    } 
    return orderedHashSet;
  }
  
  public OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2) {
    if (this.joinCondition != null)
      paramOrderedHashSet = this.joinCondition.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    QueryExpression queryExpression = this.rangeTable.getQueryExpression();
    Expression expression = this.rangeTable.getDataExpression();
    if (queryExpression != null)
      paramOrderedHashSet = queryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    if (expression != null)
      paramOrderedHashSet = expression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    return paramOrderedHashSet;
  }
  
  public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {
    QueryExpression queryExpression = this.rangeTable.getQueryExpression();
    Expression expression = this.rangeTable.getDataExpression();
    if (expression != null)
      expression = expression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    if (queryExpression != null)
      queryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    if (this.joinCondition != null)
      this.joinCondition = this.joinCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    byte b;
    for (b = 0; b < this.joinConditions.length; b++)
      this.joinConditions[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    for (b = 0; b < this.whereConditions.length; b++)
      this.whereConditions[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
  }
  
  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    if (this.joinCondition != null)
      this.joinCondition.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
  }
  
  public void resolveRangeTable(Session paramSession, RangeGroup paramRangeGroup, RangeGroup[] paramArrayOfRangeGroup) {
    QueryExpression queryExpression = this.rangeTable.getQueryExpression();
    Expression expression = this.rangeTable.getDataExpression();
    if (queryExpression == null && expression == null)
      return; 
    paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
    if (expression != null) {
      HsqlList hsqlList = expression.resolveColumnReferences(paramSession, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
      hsqlList = Expression.resolveColumnSet(paramSession, emptyArray, RangeGroup.emptyArray, hsqlList);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      expression.resolveTypes(paramSession, null);
      setRangeTableVariables();
    } 
    if (queryExpression != null) {
      queryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
      HsqlList hsqlList = queryExpression.getUnresolvedExpressions();
      hsqlList = Expression.resolveColumnSet(paramSession, emptyArray, RangeGroup.emptyArray, hsqlList);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      queryExpression.resolveTypesPartOne(paramSession);
      queryExpression.resolveTypesPartTwo(paramSession);
      this.rangeTable.prepareTable();
      setRangeTableVariables();
    } 
  }
  
  void resolveRangeTableTypes(Session paramSession, RangeVariable[] paramArrayOfRangeVariable) {
    QueryExpression queryExpression = this.rangeTable.getQueryExpression();
    if (queryExpression != null) {
      if (queryExpression instanceof QuerySpecification) {
        QuerySpecification querySpecification = (QuerySpecification)queryExpression;
        if (!querySpecification.isGrouped && !querySpecification.isAggregated && !querySpecification.isOrderSensitive)
          moveConditionsToInner(paramSession, paramArrayOfRangeVariable); 
      } 
      queryExpression.resolveTypesPartThree(paramSession);
    } 
  }
  
  void moveConditionsToInner(Session paramSession, RangeVariable[] paramArrayOfRangeVariable) {
    Expression expression = null;
    if (this.whereConditions.length > 1)
      return; 
    if (this.joinConditions.length > 1)
      return; 
    int j;
    for (j = 0; j < paramArrayOfRangeVariable.length; j++) {
      if ((paramArrayOfRangeVariable[j]).isLeftJoin || (paramArrayOfRangeVariable[j]).isRightJoin)
        return; 
    } 
    int i = ArrayUtil.find((Object[])paramArrayOfRangeVariable, this);
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    addConditionsToList(hsqlArrayList, (this.joinConditions[0]).indexCond);
    if ((this.joinConditions[0]).indexCond != null && (this.joinConditions[0]).indexCond[0] != (this.joinConditions[0]).indexEndCond[0])
      addConditionsToList(hsqlArrayList, (this.joinConditions[0]).indexEndCond); 
    addConditionsToList(hsqlArrayList, (this.whereConditions[0]).indexCond);
    addConditionsToList(hsqlArrayList, (this.whereConditions[0]).indexEndCond);
    RangeVariableResolver.decomposeAndConditions(paramSession, (this.joinConditions[0]).nonIndexCondition, (HsqlList)hsqlArrayList);
    RangeVariableResolver.decomposeAndConditions(paramSession, (this.whereConditions[0]).nonIndexCondition, (HsqlList)hsqlArrayList);
    for (j = hsqlArrayList.size() - 1; j >= 0; j--) {
      Expression expression1 = (Expression)hsqlArrayList.get(j);
      if (expression1 == null || expression1.isTrue() || expression1.hasReference(paramArrayOfRangeVariable, i))
        hsqlArrayList.remove(j); 
    } 
    if (hsqlArrayList.size() == 0) {
      if (this.rangeTable.isView())
        ((TableDerived)this.rangeTable).resetToView(); 
      return;
    } 
    QueryExpression queryExpression = this.rangeTable.getQueryExpression();
    Expression[] arrayOfExpression = ((QuerySpecification)queryExpression).exprColumns;
    for (byte b = 0; b < hsqlArrayList.size(); b++) {
      Expression expression1 = (Expression)hsqlArrayList.get(b);
      OrderedHashSet orderedHashSet = expression1.collectRangeVariables(null);
      expression1 = expression1.duplicate();
      expression1 = expression1.replaceColumnReferences(this, arrayOfExpression);
      if (expression1.collectAllSubqueries(null) != null)
        return; 
      if (orderedHashSet != null)
        for (byte b1 = 0; b1 < orderedHashSet.size(); b1++) {
          RangeVariable rangeVariable = (RangeVariable)orderedHashSet.get(b1);
          if (this != rangeVariable && rangeVariable.rangeType == 1)
            queryExpression.setCorrelated(); 
        }  
      expression = ExpressionLogical.andExpressions(expression, expression1);
    } 
    queryExpression.addExtraConditions(expression);
  }
  
  private static void addConditionsToList(HsqlArrayList paramHsqlArrayList, Expression[] paramArrayOfExpression) {
    if (paramArrayOfExpression == null)
      return; 
    for (byte b = 0; b < paramArrayOfExpression.length; b++) {
      if (paramArrayOfExpression[b] != null && ((paramArrayOfExpression[b]).isSingleColumnCondition || (paramArrayOfExpression[b]).isSingleColumnNull || (paramArrayOfExpression[b]).isSingleColumnNotNull))
        paramHsqlArrayList.add(paramArrayOfExpression[b]); 
    } 
  }
  
  public String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer2 = new StringBuffer(paramInt);
    for (byte b1 = 0; b1 < paramInt; b1++)
      stringBuffer2.append(' '); 
    StringBuffer stringBuffer1 = new StringBuffer();
    String str = "INNER";
    if (this.isLeftJoin) {
      str = "LEFT OUTER";
      if (this.isRightJoin)
        str = "FULL"; 
    } else if (this.isRightJoin) {
      str = "RIGHT OUTER";
    } 
    stringBuffer1.append(stringBuffer2).append("join type=").append(str).append("\n");
    stringBuffer1.append(stringBuffer2).append("table=").append((this.rangeTable.getName()).name).append("\n");
    if (this.tableAlias != null)
      stringBuffer1.append(stringBuffer2).append("alias=").append(this.tableAlias.name).append("\n"); 
    RangeVariableConditions[] arrayOfRangeVariableConditions = this.joinConditions;
    if (this.whereConditions[0].hasIndexCondition())
      arrayOfRangeVariableConditions = this.whereConditions; 
    stringBuffer1.append(stringBuffer2).append("cardinality=");
    stringBuffer1.append((arrayOfRangeVariableConditions[0]).rangeIndex.size(paramSession, this.rangeTable.getRowStore(paramSession))).append("\n");
    boolean bool = !arrayOfRangeVariableConditions[0].hasIndexCondition() ? true : false;
    stringBuffer1.append(stringBuffer2);
    if (arrayOfRangeVariableConditions == this.whereConditions && (this.joinConditions[0]).nonIndexCondition != null) {
      stringBuffer1.append("join condition = [");
      stringBuffer1.append((this.joinConditions[0]).nonIndexCondition.describe(paramSession, paramInt));
      stringBuffer1.append(stringBuffer2).append("]\n");
      stringBuffer1.append(stringBuffer2);
    } 
    stringBuffer1.append("access=").append(bool ? "FULL SCAN" : "INDEX PRED").append("\n");
    for (byte b2 = 0; b2 < arrayOfRangeVariableConditions.length; b2++) {
      if (b2 > 0) {
        stringBuffer1.append(stringBuffer2).append("OR condition = [");
      } else {
        stringBuffer1.append(stringBuffer2);
        if (arrayOfRangeVariableConditions == this.whereConditions) {
          stringBuffer1.append("where condition = [");
        } else {
          stringBuffer1.append("join condition = [");
        } 
      } 
      stringBuffer1.append(arrayOfRangeVariableConditions[b2].describe(paramSession, paramInt + 2));
      stringBuffer1.append(stringBuffer2).append("]\n");
    } 
    if (arrayOfRangeVariableConditions == this.joinConditions) {
      stringBuffer1.append(stringBuffer2);
      if ((this.whereConditions[0]).nonIndexCondition != null) {
        stringBuffer1.append("where condition = [");
        stringBuffer1.append((this.whereConditions[0]).nonIndexCondition.describe(paramSession, paramInt));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2);
      } 
    } 
    return stringBuffer1.toString();
  }
  
  public RangeIteratorMain getIterator(Session paramSession) {
    RangeIteratorMain rangeIteratorMain;
    if (this.isRightJoin) {
      rangeIteratorMain = new RangeIteratorRight(paramSession, this, null);
    } else {
      rangeIteratorMain = new RangeIteratorMain(paramSession, this);
    } 
    paramSession.sessionContext.setRangeIterator(rangeIteratorMain);
    return rangeIteratorMain;
  }
  
  public static RangeIterator getIterator(Session paramSession, RangeVariable[] paramArrayOfRangeVariable) {
    if (paramArrayOfRangeVariable.length == 1)
      return paramArrayOfRangeVariable[0].getIterator(paramSession); 
    RangeIteratorMain[] arrayOfRangeIteratorMain = new RangeIteratorMain[paramArrayOfRangeVariable.length];
    for (byte b = 0; b < paramArrayOfRangeVariable.length; b++)
      arrayOfRangeIteratorMain[b] = paramArrayOfRangeVariable[b].getIterator(paramSession); 
    return new RangeIteratorJoined(arrayOfRangeIteratorMain);
  }
  
  public static class RangeVariableConditions {
    final RangeVariable rangeVar;
    
    Expression[] indexCond;
    
    Expression[] indexEndCond;
    
    int[] opTypes;
    
    int[] opTypesEnd;
    
    Expression indexEndCondition;
    
    int indexedColumnCount;
    
    Index rangeIndex;
    
    final boolean isJoin;
    
    Expression excludeConditions;
    
    Expression nonIndexCondition;
    
    Expression terminalCondition;
    
    int opType;
    
    int opTypeEnd;
    
    boolean isFalse;
    
    boolean reversed;
    
    boolean hasIndex;
    
    RangeVariableConditions(RangeVariable param1RangeVariable, boolean param1Boolean) {
      this.rangeVar = param1RangeVariable;
      this.isJoin = param1Boolean;
    }
    
    RangeVariableConditions(RangeVariableConditions param1RangeVariableConditions) {
      this.rangeVar = param1RangeVariableConditions.rangeVar;
      this.isJoin = param1RangeVariableConditions.isJoin;
      this.nonIndexCondition = param1RangeVariableConditions.nonIndexCondition;
    }
    
    boolean hasIndexCondition() {
      return (this.indexedColumnCount > 0);
    }
    
    boolean hasIndex() {
      return this.hasIndex;
    }
    
    void addCondition(Expression param1Expression) {
      if (param1Expression == null)
        return; 
      if (param1Expression instanceof ExpressionLogical && ((ExpressionLogical)param1Expression).isTerminal)
        this.terminalCondition = param1Expression; 
      this.nonIndexCondition = ExpressionLogical.andExpressions(this.nonIndexCondition, param1Expression);
      if (Expression.EXPR_FALSE.equals(this.nonIndexCondition))
        this.isFalse = true; 
      if (this.rangeIndex == null || this.rangeIndex.getColumnCount() == 0)
        return; 
      if (this.indexedColumnCount == 0)
        return; 
      if (param1Expression.getIndexableExpression(this.rangeVar) == null)
        return; 
      int i = param1Expression.getLeftNode().getColumnIndex();
      int[] arrayOfInt = this.rangeIndex.getColumns();
      switch (param1Expression.getType()) {
        case 41:
        case 42:
        case 43:
          if (this.opType == 48) {
            if (arrayOfInt[this.indexedColumnCount - 1] == i) {
              this.nonIndexCondition = ExpressionLogical.andExpressions(this.nonIndexCondition, this.indexCond[this.indexedColumnCount - 1]);
              this.indexCond[this.indexedColumnCount - 1] = param1Expression;
              this.opType = param1Expression.opType;
              this.opTypes[this.indexedColumnCount - 1] = param1Expression.opType;
              if (param1Expression.getType() == 42 && this.indexedColumnCount == 1)
                this.indexEndCond[this.indexedColumnCount - 1] = ExpressionLogical.andExpressions(this.indexEndCond[this.indexedColumnCount - 1], param1Expression.nodes[2]); 
            } 
            break;
          } 
          addToIndexConditions(param1Expression);
          break;
        case 44:
        case 45:
          if (this.opType == 43 || this.opType == 41 || this.opType == 42 || this.opType == 48) {
            if (this.opTypeEnd == 74 && arrayOfInt[this.indexedColumnCount - 1] == i) {
              this.indexEndCond[this.indexedColumnCount - 1] = param1Expression;
              this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, param1Expression);
              this.opTypeEnd = param1Expression.opType;
              this.opTypesEnd[this.indexedColumnCount - 1] = param1Expression.opType;
            } 
            break;
          } 
          addToIndexEndConditions(param1Expression);
          break;
      } 
    }
    
    private boolean addToIndexConditions(Expression param1Expression) {
      if ((this.opType == 40 || this.opType == 47) && this.indexedColumnCount < this.rangeIndex.getColumnCount() && this.rangeIndex.getColumns()[this.indexedColumnCount] == param1Expression.getLeftNode().getColumnIndex()) {
        this.indexCond[this.indexedColumnCount] = param1Expression;
        this.opType = param1Expression.opType;
        this.opTypes[this.indexedColumnCount] = param1Expression.opType;
        this.opTypeEnd = 74;
        this.opTypesEnd[this.indexedColumnCount] = 74;
        this.indexedColumnCount++;
        return true;
      } 
      return false;
    }
    
    private boolean addToIndexEndConditions(Expression param1Expression) {
      if ((this.opType == 40 || this.opType == 47) && this.indexedColumnCount < this.rangeIndex.getColumnCount() && this.rangeIndex.getColumns()[this.indexedColumnCount] == param1Expression.getLeftNode().getColumnIndex()) {
        ExpressionLogical expressionLogical = ExpressionLogical.newNotNullCondition(param1Expression.getLeftNode());
        this.indexCond[this.indexedColumnCount] = expressionLogical;
        this.indexEndCond[this.indexedColumnCount] = param1Expression;
        this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, param1Expression);
        this.opType = 48;
        this.opTypes[this.indexedColumnCount] = 48;
        this.opTypeEnd = param1Expression.opType;
        this.opTypesEnd[this.indexedColumnCount] = param1Expression.opType;
        this.indexedColumnCount++;
        return true;
      } 
      return false;
    }
    
    void addIndexCondition(Expression[] param1ArrayOfExpression, Index param1Index, int param1Int) {
      Expression expression;
      byte b;
      int i = param1Index.getColumnCount();
      this.rangeIndex = param1Index;
      this.indexCond = new Expression[i];
      this.indexEndCond = new Expression[i];
      this.opTypes = new int[i];
      this.opTypesEnd = new int[i];
      this.opType = (param1ArrayOfExpression[0]).opType;
      this.opTypes[0] = (param1ArrayOfExpression[0]).opType;
      switch (this.opType) {
        case 48:
          this.indexCond = param1ArrayOfExpression;
          this.opTypeEnd = 74;
          this.opTypesEnd[0] = 74;
          break;
        case 41:
        case 42:
        case 43:
          this.indexCond = param1ArrayOfExpression;
          if (param1ArrayOfExpression[0].getType() == 42)
            this.indexEndCond[0] = this.indexEndCondition = (param1ArrayOfExpression[0]).nodes[2]; 
          this.opTypeEnd = 74;
          this.opTypesEnd[0] = 74;
          break;
        case 44:
        case 45:
          expression = param1ArrayOfExpression[0].getLeftNode();
          expression = new ExpressionLogical(47, expression);
          expression = new ExpressionLogical(48, expression);
          this.indexCond[0] = expression;
          this.indexEndCond[0] = this.indexEndCondition = param1ArrayOfExpression[0];
          this.opTypeEnd = this.opType;
          this.opTypesEnd[0] = this.opType;
          this.opType = 48;
          this.opTypes[0] = 48;
          break;
        case 40:
        case 47:
          this.indexCond = param1ArrayOfExpression;
          for (b = 0; b < param1Int; b++) {
            Expression expression1 = param1ArrayOfExpression[b];
            this.indexEndCond[b] = expression1;
            this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, expression1);
            this.opType = expression1.opType;
            this.opTypes[0] = expression1.opType;
            this.opTypesEnd[0] = expression1.opType;
          } 
          this.opTypeEnd = this.opType;
          break;
        default:
          Error.runtimeError(201, "RangeVariable");
          break;
      } 
      this.indexedColumnCount = param1Int;
      this.hasIndex = true;
    }
    
    private void reverseIndexCondition() {
      if (this.opType != 40 && this.opType != 47) {
        this.indexEndCondition = null;
        for (byte b = 0; b < this.indexedColumnCount; b++) {
          Expression expression = this.indexCond[b];
          int i = this.opTypes[b];
          this.indexCond[b] = this.indexEndCond[b];
          this.indexEndCond[b] = expression;
          this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, expression);
          this.opTypes[b] = this.opTypesEnd[b];
          this.opTypesEnd[b] = i;
        } 
        this.opType = this.opTypes[this.indexedColumnCount - 1];
        this.opTypeEnd = this.opTypesEnd[this.indexedColumnCount - 1];
      } 
      this.reversed = true;
    }
    
    String describe(Session param1Session, int param1Int) {
      StringBuffer stringBuffer1 = new StringBuffer();
      StringBuffer stringBuffer2 = new StringBuffer(param1Int);
      byte b;
      for (b = 0; b < param1Int; b++)
        stringBuffer2.append(' '); 
      stringBuffer1.append("index=").append((this.rangeIndex.getName()).name).append("\n");
      if (hasIndexCondition()) {
        if (this.indexedColumnCount > 0) {
          stringBuffer1.append(stringBuffer2).append("start conditions=[");
          for (b = 0; b < this.indexedColumnCount; b++) {
            if (this.indexCond != null && this.indexCond[b] != null)
              stringBuffer1.append(this.indexCond[b].describe(param1Session, param1Int)); 
          } 
          stringBuffer1.append("]\n");
        } 
        if (this.indexEndCondition != null) {
          String str = this.indexEndCondition.describe(param1Session, param1Int);
          stringBuffer1.append(stringBuffer2).append("end condition=[").append(str).append("]\n");
        } 
      } 
      if (this.nonIndexCondition != null) {
        String str = this.nonIndexCondition.describe(param1Session, param1Int);
        stringBuffer1.append(stringBuffer2).append("other condition=[").append(str).append("]\n");
      } 
      return stringBuffer1.toString();
    }
    
    private void replaceColumnReferences(RangeVariable param1RangeVariable, Expression[] param1ArrayOfExpression) {
      if (this.indexCond != null)
        for (byte b = 0; b < this.indexCond.length; b++) {
          if (this.indexCond[b] != null)
            this.indexCond[b] = this.indexCond[b].replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
        }  
      if (this.indexEndCond != null)
        for (byte b = 0; b < this.indexEndCond.length; b++) {
          if (this.indexEndCond[b] != null)
            this.indexEndCond[b] = this.indexEndCond[b].replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
        }  
      if (this.indexEndCondition != null)
        this.indexEndCondition = this.indexEndCondition.replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
      if (this.excludeConditions != null)
        this.excludeConditions = this.excludeConditions.replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
      if (this.nonIndexCondition != null)
        this.nonIndexCondition = this.nonIndexCondition.replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
      if (this.terminalCondition != null)
        this.terminalCondition = this.terminalCondition.replaceColumnReferences(param1RangeVariable, param1ArrayOfExpression); 
    }
  }
  
  public static class RangeIteratorJoined extends RangeIteratorBase {
    RangeVariable.RangeIteratorMain[] rangeIterators;
    
    int currentIndex = 0;
    
    public RangeIteratorJoined(RangeVariable.RangeIteratorMain[] param1ArrayOfRangeIteratorMain) {
      this.rangeIterators = param1ArrayOfRangeIteratorMain;
      this.isBeforeFirst = true;
    }
    
    public boolean isBeforeFirst() {
      return this.isBeforeFirst;
    }
    
    public boolean next() {
      while (this.currentIndex >= 0) {
        RangeVariable.RangeIteratorMain rangeIteratorMain = this.rangeIterators[this.currentIndex];
        if (rangeIteratorMain.next()) {
          if (this.currentIndex < this.rangeIterators.length - 1) {
            this.currentIndex++;
            continue;
          } 
          this.currentRow = (this.rangeIterators[this.currentIndex]).currentRow;
          this.currentData = this.currentRow.getData();
          return true;
        } 
        rangeIteratorMain.reset();
        this.currentIndex--;
      } 
      this.currentData = (this.rangeIterators[this.rangeIterators.length - 1]).rangeVar.emptyData;
      this.currentRow = null;
      for (byte b = 0; b < this.rangeIterators.length; b++)
        this.rangeIterators[b].reset(); 
      return false;
    }
    
    public void removeCurrent() {}
    
    public void release() {
      if (this.it != null)
        this.it.release(); 
      for (byte b = 0; b < this.rangeIterators.length; b++)
        this.rangeIterators[b].reset(); 
    }
    
    public void reset() {
      super.reset();
      for (byte b = 0; b < this.rangeIterators.length; b++)
        this.rangeIterators[b].reset(); 
    }
    
    public int getRangePosition() {
      return 0;
    }
  }
  
  public static class RangeIteratorRight extends RangeIteratorMain {
    boolean isOnRightOuterRows;
    
    private RangeIteratorRight(Session param1Session, RangeVariable param1RangeVariable, RangeVariable.RangeIteratorMain param1RangeIteratorMain) {
      super(param1Session, param1RangeVariable);
    }
    
    public void setOnOuterRows() {
      this.conditions = this.rangeVar.whereConditions;
      this.isOnRightOuterRows = true;
      this.hasLeftOuterRow = false;
      this.condIndex = 0;
      initialiseIterator();
    }
    
    public boolean next() {
      return this.isOnRightOuterRows ? ((this.it == null) ? false : findNextRight()) : super.next();
    }
    
    private boolean findNextRight() {
      boolean bool = false;
      while (true) {
        this.currentRow = this.it.getNextRow();
        if (this.currentRow == null)
          break; 
        this.currentData = this.currentRow.getData();
        if ((this.conditions[this.condIndex]).indexEndCondition != null && !(this.conditions[this.condIndex]).indexEndCondition.testCondition(this.session))
          break; 
        if (((this.conditions[this.condIndex]).nonIndexCondition != null && !(this.conditions[this.condIndex]).nonIndexCondition.testCondition(this.session)) || !lookupAndTest())
          continue; 
        bool = true;
        break;
      } 
      if (bool)
        return true; 
      this.it.release();
      this.currentRow = null;
      this.currentData = this.rangeVar.emptyData;
      return bool;
    }
    
    private boolean lookupAndTest() {
      boolean bool = !this.lookup.contains(this.currentRow.getPos()) ? true : false;
      if (bool) {
        this.currentData = this.currentRow.getData();
        if ((this.conditions[this.condIndex]).nonIndexCondition != null && !(this.conditions[this.condIndex]).nonIndexCondition.testCondition(this.session))
          bool = false; 
      } 
      return bool;
    }
  }
  
  public static class RangeIteratorMain extends RangeIteratorBase {
    boolean hasLeftOuterRow;
    
    boolean isFullIterator;
    
    RangeVariable.RangeVariableConditions[] conditions;
    
    RangeVariable.RangeVariableConditions[] whereConditions;
    
    RangeVariable.RangeVariableConditions[] joinConditions;
    
    int condIndex = 0;
    
    OrderedLongHashSet lookup;
    
    Object[] currentJoinData = null;
    
    RangeIteratorMain() {}
    
    private RangeIteratorMain(Session param1Session, RangeVariable param1RangeVariable) {
      this.rangePosition = param1RangeVariable.rangePosition;
      this.store = param1RangeVariable.rangeTable.getRowStore(param1Session);
      this.session = param1Session;
      this.rangeVar = param1RangeVariable;
      this.currentData = param1RangeVariable.emptyData;
      this.isBeforeFirst = true;
      this.whereConditions = param1RangeVariable.whereConditions;
      this.joinConditions = param1RangeVariable.joinConditions;
      if (param1RangeVariable.isRightJoin)
        this.lookup = new OrderedLongHashSet(); 
      this.conditions = param1RangeVariable.joinConditions;
      if (param1RangeVariable.whereConditions[0].hasIndexCondition())
        this.conditions = param1RangeVariable.whereConditions; 
    }
    
    public boolean isBeforeFirst() {
      return this.isBeforeFirst;
    }
    
    public boolean next() {
      while (this.condIndex < this.conditions.length) {
        if (this.isBeforeFirst) {
          this.isBeforeFirst = false;
          initialiseIterator();
        } 
        boolean bool = findNext();
        if (bool)
          return true; 
        reset();
        this.condIndex++;
      } 
      this.condIndex = 0;
      return false;
    }
    
    public void removeCurrent() {}
    
    public void reset() {
      if (this.it != null)
        this.it.release(); 
      this.it = null;
      this.currentData = this.rangeVar.emptyData;
      this.currentRow = null;
      this.isBeforeFirst = true;
    }
    
    public int getRangePosition() {
      return this.rangeVar.rangePosition;
    }
    
    protected void initialiseIterator() {
      if (this.condIndex == 0)
        this.hasLeftOuterRow = this.rangeVar.isLeftJoin; 
      if ((this.conditions[this.condIndex]).isFalse) {
        this.it = (this.conditions[this.condIndex]).rangeIndex.emptyIterator();
        return;
      } 
      this.rangeVar.rangeTable.materialiseCorrelated(this.session);
      if ((this.conditions[this.condIndex]).indexCond == null) {
        if ((this.conditions[this.condIndex]).reversed) {
          this.it = (this.conditions[this.condIndex]).rangeIndex.lastRow(this.session, this.store, this.rangeVar.indexDistinctCount);
        } else {
          this.it = (this.conditions[this.condIndex]).rangeIndex.firstRow(this.session, this.store, this.rangeVar.indexDistinctCount);
        } 
      } else {
        getFirstRow();
        if (!(this.conditions[this.condIndex]).isJoin)
          this.hasLeftOuterRow = false; 
      } 
    }
    
    private void getFirstRow() {
      if (this.currentJoinData == null || this.currentJoinData.length < (this.conditions[this.condIndex]).indexedColumnCount)
        this.currentJoinData = new Object[(this.conditions[this.condIndex]).indexedColumnCount]; 
      for (byte b = 0; b < (this.conditions[this.condIndex]).indexedColumnCount; b++) {
        int i = 0;
        int j = (b == (this.conditions[this.condIndex]).indexedColumnCount - 1) ? (this.conditions[this.condIndex]).opType : (this.conditions[this.condIndex]).indexCond[b].getType();
        if (j == 47 || j == 48 || j == 74) {
          this.currentJoinData[b] = null;
        } else {
          Type type1 = (this.conditions[this.condIndex]).indexCond[b].getRightNode().getDataType();
          Object object = (this.conditions[this.condIndex]).indexCond[b].getRightNode().getValue(this.session);
          Type type2 = (this.conditions[this.condIndex]).indexCond[b].getLeftNode().getDataType();
          if (type2 != type1) {
            i = type2.compareToTypeRange(object);
            if (i == 0 && type2.typeComparisonGroup != type1.typeComparisonGroup)
              object = type2.convertToType(this.session, object, type1); 
          } 
          if (b == 0) {
            int k = (this.conditions[this.condIndex]).indexCond[0].getType();
            if (i < 0) {
              switch (k) {
                case 41:
                case 42:
                case 43:
                  object = null;
                  break;
                default:
                  this.it = (this.conditions[this.condIndex]).rangeIndex.emptyIterator();
                  return;
              } 
            } else if (i > 0) {
              switch (k) {
                case 48:
                  object = null;
                  break;
                default:
                  this.it = (this.conditions[this.condIndex]).rangeIndex.emptyIterator();
                  return;
              } 
            } 
          } 
          this.currentJoinData[b] = object;
        } 
      } 
      this.it = (this.conditions[this.condIndex]).rangeIndex.findFirstRow(this.session, this.store, this.currentJoinData, (this.conditions[this.condIndex]).indexedColumnCount, this.rangeVar.indexDistinctCount, (this.conditions[this.condIndex]).opType, (this.conditions[this.condIndex]).reversed, null);
    }
    
    private boolean findNext() {
      boolean bool = false;
      while (true) {
        this.currentRow = this.it.getNextRow();
        if (this.currentRow == null)
          break; 
        this.currentData = this.currentRow.getData();
        if ((this.conditions[this.condIndex]).terminalCondition != null && !(this.conditions[this.condIndex]).terminalCondition.testCondition(this.session))
          break; 
        if ((this.conditions[this.condIndex]).indexEndCondition != null && !(this.conditions[this.condIndex]).indexEndCondition.testCondition(this.session)) {
          if (!(this.conditions[this.condIndex]).isJoin)
            this.hasLeftOuterRow = false; 
          break;
        } 
        if ((this.joinConditions[this.condIndex]).nonIndexCondition != null && !(this.joinConditions[this.condIndex]).nonIndexCondition.testCondition(this.session))
          continue; 
        if ((this.whereConditions[this.condIndex]).nonIndexCondition != null && !(this.whereConditions[this.condIndex]).nonIndexCondition.testCondition(this.session)) {
          this.hasLeftOuterRow = false;
          addFoundRow();
          continue;
        } 
        Expression expression = (this.conditions[this.condIndex]).excludeConditions;
        if (expression != null && expression.testCondition(this.session))
          continue; 
        addFoundRow();
        this.hasLeftOuterRow = false;
        return true;
      } 
      this.it.release();
      this.currentRow = null;
      this.currentData = this.rangeVar.emptyData;
      if (this.hasLeftOuterRow && this.condIndex == this.conditions.length - 1) {
        bool = ((this.whereConditions[this.condIndex]).nonIndexCondition == null || (this.whereConditions[this.condIndex]).nonIndexCondition.testCondition(this.session)) ? true : false;
        this.hasLeftOuterRow = false;
      } 
      return bool;
    }
    
    private void addFoundRow() {
      if (this.rangeVar.isRightJoin)
        this.lookup.add(this.currentRow.getPos()); 
    }
  }
  
  public static class RangeIteratorBase implements RangeIterator {
    Session session;
    
    int rangePosition;
    
    RowIterator it;
    
    PersistentStore store;
    
    Object[] currentData;
    
    Row currentRow;
    
    boolean isBeforeFirst;
    
    RangeVariable rangeVar;
    
    private RangeIteratorBase() {}
    
    public boolean isBeforeFirst() {
      return this.isBeforeFirst;
    }
    
    public boolean next() {
      if (this.isBeforeFirst) {
        this.isBeforeFirst = false;
      } else if (this.it == null) {
        return false;
      } 
      this.currentRow = this.it.getNextRow();
      if (this.currentRow == null)
        return false; 
      this.currentData = this.currentRow.getData();
      return true;
    }
    
    public Row getCurrentRow() {
      return this.currentRow;
    }
    
    public Object[] getCurrent() {
      return this.currentData;
    }
    
    public Object getCurrent(int param1Int) {
      return (this.currentData == null) ? null : this.currentData[param1Int];
    }
    
    public void setCurrent(Object[] param1ArrayOfObject) {
      this.currentData = param1ArrayOfObject;
    }
    
    public long getRowId() {
      return (this.currentRow == null) ? 0L : ((this.rangeVar.rangeTable.getId() << 32L) + this.currentRow.getPos());
    }
    
    public Object getRowidObject() {
      return (this.currentRow == null) ? null : ValuePool.getLong(getRowId());
    }
    
    public void removeCurrent() {}
    
    public void reset() {
      if (this.it != null)
        this.it.release(); 
      this.it = null;
      this.currentRow = null;
      this.isBeforeFirst = true;
    }
    
    public int getRangePosition() {
      return this.rangePosition;
    }
    
    public Row getNextRow() {
      throw Error.runtimeError(201, "RangeVariable");
    }
    
    public boolean hasNext() {
      throw Error.runtimeError(201, "RangeVariable");
    }
    
    public Object[] getNext() {
      throw Error.runtimeError(201, "RangeVariable");
    }
    
    public boolean setRowColumns(boolean[] param1ArrayOfboolean) {
      throw Error.runtimeError(201, "RangeVariable");
    }
    
    public void release() {
      if (this.it != null)
        this.it.release(); 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RangeVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */