package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataTable;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Type;

public class QuerySpecification extends QueryExpression {
  public int resultRangePosition;
  
  public boolean isDistinctSelect;
  
  public boolean isAggregated;
  
  public boolean isGrouped;
  
  public boolean isOrderSensitive;
  
  public boolean isSimpleDistinct;
  
  RangeVariable[] rangeVariables;
  
  private HsqlArrayList rangeVariableList;
  
  int startInnerRange = -1;
  
  int endInnerRange = -1;
  
  Expression queryCondition;
  
  Expression checkQueryCondition;
  
  private Expression havingCondition;
  
  Expression rowExpression;
  
  Expression[] exprColumns;
  
  HsqlArrayList exprColumnList;
  
  public int indexLimitVisible;
  
  private int indexLimitRowId;
  
  private int groupByColumnCount;
  
  private int havingColumnCount;
  
  private int indexStartHaving;
  
  public int indexStartOrderBy;
  
  public int indexStartAggregates;
  
  private int indexLimitExpressions;
  
  public int indexLimitData;
  
  private boolean hasRowID;
  
  private boolean isSimpleCount;
  
  private boolean isSingleMemoryTable;
  
  public boolean isUniqueResultRows;
  
  Type[] resultColumnTypes;
  
  private ArrayListIdentity aggregateSet;
  
  private ArrayListIdentity resolvedSubqueryExpressions = null;
  
  private boolean[] aggregateCheck;
  
  private OrderedHashSet tempSet = new OrderedHashSet();
  
  int[] columnMap;
  
  private Table baseTable;
  
  private OrderedHashSet conditionTables;
  
  public Index groupIndex;
  
  private RangeGroup[] outerRanges;
  
  QuerySpecification(Session paramSession, Table paramTable, ParserDQL.CompileContext paramCompileContext, boolean paramBoolean) {
    this(paramCompileContext);
    this.isValueList = paramBoolean;
    RangeVariable rangeVariable = new RangeVariable(paramTable, null, null, null, paramCompileContext);
    rangeVariable.addTableColumns(this.exprColumnList, 0, (HashSet)null);
    this.indexLimitVisible = this.exprColumnList.size();
    addRangeVariable(paramSession, rangeVariable);
    this.sortAndSlice = SortAndSlice.noSort;
    this.isBaseMergeable = true;
    this.isMergeable = true;
    this.isTable = true;
  }
  
  QuerySpecification(ParserDQL.CompileContext paramCompileContext) {
    super(paramCompileContext);
    this.resultRangePosition = paramCompileContext.getNextRangeVarIndex();
    this.rangeVariableList = new HsqlArrayList();
    this.exprColumnList = new HsqlArrayList();
    this.sortAndSlice = SortAndSlice.noSort;
    this.isBaseMergeable = true;
    this.isMergeable = true;
  }
  
  void addRangeVariable(Session paramSession, RangeVariable paramRangeVariable) {
    this.rangeVariableList.add(paramRangeVariable);
  }
  
  void addRangeSeparator() {
    RangeVariable rangeVariable = (RangeVariable)this.rangeVariableList.get(this.rangeVariableList.size() - 1);
    rangeVariable.isBoundary = true;
  }
  
  public TableDerived getValueListTable() {
    if (this.isValueList) {
      RangeVariable rangeVariable = null;
      if (this.rangeVariables == null) {
        if (this.rangeVariableList.size() == 1)
          rangeVariable = (RangeVariable)this.rangeVariableList.get(0); 
      } else if (this.rangeVariables.length == 1) {
        rangeVariable = this.rangeVariables[0];
      } 
      if (rangeVariable != null)
        return (TableDerived)rangeVariable.getTable(); 
    } 
    return null;
  }
  
  public RangeVariable[] getRangeVariables() {
    return this.rangeVariables;
  }
  
  private void resolveRangeVariables(Session paramSession, RangeGroup[] paramArrayOfRangeGroup) {
    if (this.rangeVariables == null || this.rangeVariables.length < this.rangeVariableList.size()) {
      this.rangeVariables = new RangeVariable[this.rangeVariableList.size()];
      this.rangeVariableList.toArray(this.rangeVariables);
    } 
    for (byte b = 0; b < this.rangeVariables.length; b++) {
      RangeGroup rangeGroup;
      if ((this.rangeVariables[b]).isLateral) {
        RangeVariable[] arrayOfRangeVariable = (RangeVariable[])ArrayUtil.resizeArray(this.rangeVariables, b);
        rangeGroup = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable, this);
      } else if (paramArrayOfRangeGroup == RangeGroup.emptyArray) {
        rangeGroup = RangeGroup.emptyGroup;
      } else {
        rangeGroup = new RangeGroup.RangeGroupSimple(RangeVariable.emptyArray, this);
      } 
      this.rangeVariables[b].resolveRangeTable(paramSession, rangeGroup, paramArrayOfRangeGroup);
    } 
  }
  
  void addSelectColumnExpression(Expression paramExpression) {
    if (paramExpression.getType() == 25)
      throw Error.error(5564); 
    if (this.indexLimitVisible > 0) {
      if (paramExpression.opType == 97 && ((ExpressionColumn)paramExpression).getTableName() == null)
        throw Error.error(5578); 
      Expression expression = (Expression)this.exprColumnList.get(0);
      if (expression.opType == 97 && ((ExpressionColumn)expression).getTableName() == null)
        throw Error.error(5578); 
    } 
    this.exprColumnList.add(paramExpression);
    this.indexLimitVisible++;
  }
  
  void addQueryCondition(Expression paramExpression) {
    this.queryCondition = paramExpression;
  }
  
  void addGroupByColumnExpression(Expression paramExpression) {
    if (paramExpression.getType() == 25)
      throw Error.error(5564); 
    this.exprColumnList.add(paramExpression);
    this.isGrouped = true;
    this.groupByColumnCount++;
  }
  
  void addHavingExpression(Expression paramExpression) {
    this.exprColumnList.add(paramExpression);
    this.havingCondition = paramExpression;
    this.havingColumnCount = 1;
  }
  
  void addSortAndSlice(SortAndSlice paramSortAndSlice) {
    this.sortAndSlice = paramSortAndSlice;
  }
  
  public void resolveReferences(Session paramSession, RangeGroup[] paramArrayOfRangeGroup) {
    if (this.isReferencesResolved)
      return; 
    this.outerRanges = paramArrayOfRangeGroup;
    resolveRangeVariables(paramSession, paramArrayOfRangeGroup);
    resolveColumnReferencesForAsterisk();
    finaliseColumns();
    resolveColumnReferences(paramSession, paramArrayOfRangeGroup);
    setReferenceableColumns();
    Expression.resolveColumnSet(paramSession, RangeVariable.emptyArray, paramArrayOfRangeGroup, this.unresolvedExpressions);
    this.unionColumnTypes = new Type[this.indexLimitVisible];
    this.isReferencesResolved = true;
  }
  
  public boolean hasReference(RangeVariable paramRangeVariable) {
    if (this.unresolvedExpressions == null)
      return false; 
    for (byte b = 0; b < this.unresolvedExpressions.size(); b++) {
      if (((Expression)this.unresolvedExpressions.get(b)).hasReference(paramRangeVariable))
        return true; 
    } 
    return false;
  }
  
  public boolean areColumnsResolved() {
    return super.areColumnsResolved();
  }
  
  public void resolveTypes(Session paramSession) {
    if (this.isResolved)
      return; 
    resolveTypesPartOne(paramSession);
    resolveTypesPartTwo(paramSession);
    resolveTypesPartThree(paramSession);
    ArrayUtil.copyArray(this.resultTable.colTypes, this.unionColumnTypes, this.unionColumnTypes.length);
  }
  
  void resolveTypesPartOne(Session paramSession) {
    if (this.isPartOneResolved)
      return; 
    resolveExpressionTypes(paramSession);
    resolveAggregates();
    for (byte b = 0; b < this.unionColumnTypes.length; b++)
      this.unionColumnTypes[b] = Type.getAggregateType(this.unionColumnTypes[b], this.exprColumns[b].getDataType()); 
    this.isPartOneResolved = true;
  }
  
  void resolveTypesPartTwo(Session paramSession) {
    if (this.isPartTwoResolved)
      return; 
    resolveGroups();
    int i;
    for (i = 0; i < this.unionColumnTypes.length; i++) {
      CharacterType characterType;
      Type type = this.unionColumnTypes[i];
      if (type == null) {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5567); 
        characterType = Type.SQL_VARCHAR_DEFAULT;
        this.unionColumnTypes[i] = (Type)characterType;
      } 
      this.exprColumns[i].setDataType(paramSession, (Type)characterType);
      if ((this.exprColumns[i]).dataType.isArrayType() && (this.exprColumns[i]).dataType.collectionBaseType() == null)
        throw Error.error(5567); 
    } 
    for (i = this.indexLimitVisible; i < this.indexStartHaving; i++) {
      if ((this.exprColumns[i]).dataType == null)
        throw Error.error(5567); 
    } 
    checkLobUsage();
    setMergeability();
    setUpdatability();
    setResultColumnTypes();
    createResultMetaData(paramSession);
    createTable(paramSession);
    mergeQuery();
    this.isPartTwoResolved = true;
  }
  
  void resolveTypesPartThree(Session paramSession) {
    if (this.isResolved)
      return; 
    this.sortAndSlice.setSortIndex(this);
    setRangeVariableConditions(paramSession);
    setDistinctConditions(paramSession);
    setAggregateConditions(paramSession);
    this.sortAndSlice.setSortRange(this);
    for (byte b = 0; b < this.rangeVariables.length; b++)
      this.rangeVariables[b].resolveRangeTableTypes(paramSession, this.rangeVariables); 
    setResultNullability();
    this.rangeVariableList = null;
    this.tempSet = null;
    this.compileContext = null;
    this.outerRanges = null;
    this.isResolved = true;
  }
  
  public void addExtraConditions(Expression paramExpression) {
    if (this.isAggregated || this.isGrouped)
      return; 
    this.queryCondition = ExpressionLogical.andExpressions(this.queryCondition, paramExpression);
  }
  
  private void resolveColumnReferences(Session paramSession, RangeGroup[] paramArrayOfRangeGroup) {
    if (this.isDistinctSelect || this.isGrouped)
      this.acceptsSequences = false; 
    int i;
    for (i = 0; i < this.rangeVariables.length; i++) {
      Expression expression = this.rangeVariables[i].getJoinCondition();
      if (expression != null)
        resolveColumnReferencesAndAllocate(paramSession, expression, i + 1, paramArrayOfRangeGroup, false); 
    } 
    resolveColumnReferencesAndAllocate(paramSession, this.queryCondition, this.rangeVariables.length, paramArrayOfRangeGroup, false);
    if (this.resolvedSubqueryExpressions != null)
      this.resolvedSubqueryExpressions.setSize(0); 
    for (i = 0; i < this.indexLimitVisible; i++) {
      resolveColumnReferencesAndAllocate(paramSession, this.exprColumns[i], this.rangeVariables.length, paramArrayOfRangeGroup, this.acceptsSequences);
      if (!this.isGrouped && !this.isDistinctSelect) {
        OrderedHashSet orderedHashSet = this.exprColumns[i].collectAllSubqueries(null);
        if (orderedHashSet != null)
          this.isMergeable = false; 
        orderedHashSet = this.exprColumns[i].collectAllExpressions(null, Expression.sequenceExpressionSet, Expression.subqueryAggregateExpressionSet);
        if (orderedHashSet != null) {
          this.isOrderSensitive = true;
          this.isMergeable = false;
          this.isBaseMergeable = false;
        } 
      } 
    } 
    for (i = this.indexLimitVisible; i < this.indexStartHaving; i++)
      this.exprColumns[i] = resolveColumnReferencesInGroupBy(paramSession, this.exprColumns[i]); 
    for (i = this.indexStartHaving; i < this.indexStartOrderBy; i++)
      resolveColumnReferencesAndAllocate(paramSession, this.exprColumns[i], this.rangeVariables.length, paramArrayOfRangeGroup, false); 
    resolveColumnRefernecesInOrderBy(paramSession, paramArrayOfRangeGroup, this.sortAndSlice);
  }
  
  void resolveColumnRefernecesInOrderBy(Session paramSession, RangeGroup[] paramArrayOfRangeGroup, SortAndSlice paramSortAndSlice) {
    int i = paramSortAndSlice.getOrderLength();
    for (byte b = 0; b < i; b++) {
      ExpressionOrderBy expressionOrderBy = (ExpressionOrderBy)paramSortAndSlice.exprList.get(b);
      replaceColumnIndexInOrderBy(expressionOrderBy);
      if ((expressionOrderBy.getLeftNode()).queryTableColumnIndex == -1) {
        if (paramSortAndSlice.sortUnion && expressionOrderBy.getLeftNode().getType() != 2)
          throw Error.error(5576); 
        expressionOrderBy.replaceAliasInOrderBy(paramSession, this.exprColumns, this.indexLimitVisible);
        resolveColumnReferencesAndAllocate(paramSession, expressionOrderBy, this.rangeVariables.length, RangeGroup.emptyArray, false);
        if (this.isAggregated || this.isGrouped) {
          boolean bool = expressionOrderBy.getLeftNode().isComposedOf(this.exprColumns, 0, this.indexLimitVisible + this.groupByColumnCount, Expression.aggregateFunctionSet);
          if (!bool)
            throw Error.error(5576); 
        } 
      } 
    } 
    if (paramSortAndSlice.limitCondition != null)
      this.unresolvedExpressions = paramSortAndSlice.limitCondition.resolveColumnReferences(paramSession, this, paramArrayOfRangeGroup, this.unresolvedExpressions); 
    paramSortAndSlice.prepare(this);
  }
  
  private boolean resolveColumnReferences(Session paramSession, Expression paramExpression, int paramInt, boolean paramBoolean) {
    if (paramExpression == null)
      return true; 
    boolean bool1 = (this.unresolvedExpressions == null) ? false : this.unresolvedExpressions.size();
    this.unresolvedExpressions = paramExpression.resolveColumnReferences(paramSession, this, paramInt, RangeGroup.emptyArray, this.unresolvedExpressions, paramBoolean);
    boolean bool2 = (this.unresolvedExpressions == null) ? false : this.unresolvedExpressions.size();
    return (bool1 == bool2);
  }
  
  private void resolveColumnReferencesForAsterisk() {
    for (byte b = 0; b < this.indexLimitVisible; b++) {
      Expression expression = (Expression)this.exprColumnList.get(b);
      if (expression.getType() == 97) {
        this.exprColumnList.remove(b);
        String str = ((ExpressionColumn)expression).getTableName();
        if (str == null) {
          addAllJoinedColumns(expression);
        } else {
          boolean bool = false;
          for (byte b2 = 0; b2 < this.rangeVariables.length; b2++) {
            RangeVariable rangeVariable = this.rangeVariables[b2].getRangeForTableName(str);
            if (rangeVariable != null) {
              HashSet hashSet = getAllNamedJoinColumns();
              this.rangeVariables[b2].addTableColumns(rangeVariable, expression, hashSet);
              bool = true;
              break;
            } 
          } 
          if (!bool)
            throw Error.error(5501, str); 
        } 
        for (byte b1 = 0; b1 < expression.nodes.length; b1++) {
          this.exprColumnList.add(b, expression.nodes[b1]);
          b++;
        } 
        this.indexLimitVisible += expression.nodes.length - 1;
        continue;
      } 
    } 
  }
  
  private void resolveColumnReferencesAndAllocate(Session paramSession, Expression paramExpression, int paramInt, RangeGroup[] paramArrayOfRangeGroup, boolean paramBoolean) {
    if (paramExpression == null)
      return; 
    HsqlList hsqlList = paramExpression.resolveColumnReferences(paramSession, this, paramInt, paramArrayOfRangeGroup, null, paramBoolean);
    if (hsqlList != null)
      for (byte b = 0; b < hsqlList.size(); b++) {
        boolean bool;
        Expression expression = (Expression)hsqlList.get(b);
        int i = 1;
        if (expression.isSelfAggregate()) {
          for (byte b1 = 0; b1 < expression.nodes.length; b1++) {
            HsqlList hsqlList1 = expression.nodes[b1].resolveColumnReferences(paramSession, this, paramInt, RangeGroup.emptyArray, null, false);
            for (byte b2 = 0; b2 < paramArrayOfRangeGroup.length; b2++) {
              if (paramArrayOfRangeGroup[b2].isVariable())
                hsqlList1 = Expression.resolveColumnSet(paramSession, paramArrayOfRangeGroup[b2].getRangeVariables(), RangeGroup.emptyArray, hsqlList1); 
            } 
            i &= (hsqlList1 == null) ? 1 : 0;
          } 
        } else {
          bool = resolveColumnReferences(paramSession, expression, paramInt, paramBoolean);
        } 
        if (bool) {
          if (expression.isSelfAggregate()) {
            if (this.aggregateSet == null)
              this.aggregateSet = new ArrayListIdentity(); 
            this.aggregateSet.add(expression);
            this.isAggregated = true;
            paramExpression.setAggregate();
          } 
          if (this.resolvedSubqueryExpressions == null)
            this.resolvedSubqueryExpressions = new ArrayListIdentity(); 
          this.resolvedSubqueryExpressions.add(expression);
        } else {
          if (this.unresolvedExpressions == null)
            this.unresolvedExpressions = (HsqlList)new ArrayListIdentity(); 
          this.unresolvedExpressions.add(expression);
        } 
      }  
  }
  
  private Expression resolveColumnReferencesInGroupBy(Session paramSession, Expression paramExpression) {
    if (paramExpression == null)
      return null; 
    HsqlList hsqlList = paramExpression.resolveColumnReferences(paramSession, this, this.rangeVariables.length, RangeGroup.emptyArray, null, false);
    if (hsqlList != null) {
      if (paramExpression.getType() == 2) {
        Expression expression = paramExpression.replaceAliasInOrderBy(paramSession, this.exprColumns, this.indexLimitVisible);
        if (expression != paramExpression)
          return expression; 
      } 
      resolveColumnReferencesAndAllocate(paramSession, paramExpression, this.rangeVariables.length, RangeGroup.emptyArray, false);
    } 
    return paramExpression;
  }
  
  private HashSet getAllNamedJoinColumns() {
    HashSet hashSet = null;
    for (byte b = 0; b < this.rangeVariableList.size(); b++) {
      RangeVariable rangeVariable = (RangeVariable)this.rangeVariableList.get(b);
      if (rangeVariable.namedJoinColumns != null) {
        if (hashSet == null)
          hashSet = new HashSet(); 
        hashSet.addAll((Collection)rangeVariable.namedJoinColumns);
      } 
    } 
    return hashSet;
  }
  
  public Expression getEquiJoinExpressions(OrderedHashSet paramOrderedHashSet, RangeVariable paramRangeVariable, boolean paramBoolean) {
    HashSet hashSet = new HashSet();
    Expression expression = null;
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < this.rangeVariableList.size(); b++) {
      RangeVariable rangeVariable = (RangeVariable)this.rangeVariableList.get(b);
      HashMappedList hashMappedList = rangeVariable.rangeTable.columnList;
      for (byte b1 = 0; b1 < hashMappedList.size(); b1++) {
        ColumnSchema columnSchema = (ColumnSchema)hashMappedList.get(b1);
        String str = (rangeVariable.getColumnAlias(b1)).name;
        boolean bool = paramOrderedHashSet.contains(str);
        boolean bool1 = (rangeVariable.namedJoinColumns != null && rangeVariable.namedJoinColumns.contains(str)) ? true : false;
        boolean bool2 = (!bool1 && !hashSet.add(str)) ? true : false;
        if (bool2 && (!paramBoolean || bool))
          throw Error.error(5578, str); 
        if (bool) {
          orderedHashSet.add(str);
          int i = rangeVariable.rangeTable.getColumnIndex(columnSchema.getNameString());
          int j = paramRangeVariable.rangeTable.getColumnIndex(str);
          ExpressionLogical expressionLogical = new ExpressionLogical(rangeVariable, i, paramRangeVariable, j);
          expression = ExpressionLogical.andExpressions(expression, expressionLogical);
          ExpressionColumn expressionColumn = rangeVariable.getColumnExpression(str);
          if (expressionColumn == null) {
            expressionColumn = new ExpressionColumn(new Expression[] { expressionLogical.getLeftNode(), expressionLogical.getRightNode() }, str);
            rangeVariable.addNamedJoinColumnExpression(str, expressionColumn);
          } else {
            expressionColumn.nodes = (Expression[])ArrayUtil.resizeArray(expressionColumn.nodes, expressionColumn.nodes.length + 1);
            expressionColumn.nodes[expressionColumn.nodes.length - 1] = expressionLogical.getRightNode();
          } 
          paramRangeVariable.addNamedJoinColumnExpression(str, expressionColumn);
        } 
      } 
    } 
    if (paramBoolean && !orderedHashSet.containsAll((Collection)paramOrderedHashSet))
      throw Error.error(5501); 
    paramRangeVariable.addNamedJoinColumns(orderedHashSet);
    return expression;
  }
  
  private void addAllJoinedColumns(Expression paramExpression) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    for (byte b = 0; b < this.rangeVariables.length; b++)
      this.rangeVariables[b].addTableColumns(hsqlArrayList); 
    Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression);
    paramExpression.nodes = arrayOfExpression;
  }
  
  private void finaliseColumns() {
    this.indexLimitRowId = this.indexLimitVisible;
    this.indexStartHaving = this.indexLimitRowId + this.groupByColumnCount;
    this.indexStartOrderBy = this.indexStartHaving + this.havingColumnCount;
    this.indexStartAggregates = this.indexStartOrderBy + this.sortAndSlice.getOrderLength();
    this.indexLimitData = this.indexLimitExpressions = this.indexStartAggregates;
    this.exprColumns = new Expression[this.indexLimitExpressions];
    this.exprColumnList.toArray(this.exprColumns);
    this.exprColumnList = null;
    byte b;
    for (b = 0; b < this.indexLimitVisible; b++)
      (this.exprColumns[b]).queryTableColumnIndex = b; 
    if (this.sortAndSlice.hasOrder())
      for (b = 0; b < this.sortAndSlice.getOrderLength(); b++)
        this.exprColumns[this.indexStartOrderBy + b] = (Expression)this.sortAndSlice.exprList.get(b);  
    this.rowExpression = new Expression(25, this.exprColumns);
  }
  
  private void replaceColumnIndexInOrderBy(Expression paramExpression) {
    Expression expression = paramExpression.getLeftNode();
    if (expression.getType() != 1)
      return; 
    Type type = expression.getDataType();
    if (type != null && type.typeCode == 4) {
      int i = ((Integer)expression.getValue(null)).intValue();
      if (0 < i && i <= this.indexLimitVisible) {
        paramExpression.setLeftNode(this.exprColumns[i - 1]);
        return;
      } 
    } 
    throw Error.error(5576);
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.indexStartAggregates; b++)
      paramOrderedHashSet = this.exprColumns[b].collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    if (this.queryCondition != null)
      paramOrderedHashSet = this.queryCondition.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    if (this.havingCondition != null)
      paramOrderedHashSet = this.havingCondition.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.indexStartAggregates; b++)
      paramOrderedHashSet = this.exprColumns[b].collectRangeVariables(paramOrderedHashSet); 
    if (this.queryCondition != null)
      paramOrderedHashSet = this.queryCondition.collectRangeVariables(paramOrderedHashSet); 
    if (this.havingCondition != null)
      paramOrderedHashSet = this.havingCondition.collectRangeVariables(paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  public void resolveExpressionTypes(Session paramSession) {
    byte b;
    for (b = 0; b < this.indexStartAggregates; b++) {
      Expression expression = this.exprColumns[b];
      expression.resolveTypes(paramSession, this.rowExpression);
      if (expression.getType() == 25)
        throw Error.error(5565); 
      if (expression.getType() == 22 && expression.getDegree() > 1)
        throw Error.error(5565); 
      if (expression.getDataType() != null && (expression.getDataType()).typeCode == 19)
        throw Error.error(5565); 
    } 
    for (b = 0; b < this.rangeVariables.length; b++) {
      Expression expression = this.rangeVariables[b].getJoinCondition();
      if (expression != null) {
        expression.resolveTypes(paramSession, null);
        if (expression.getDataType() != Type.SQL_BOOLEAN)
          throw Error.error(5568); 
      } 
    } 
    if (this.queryCondition != null) {
      this.queryCondition.resolveTypes(paramSession, null);
      if (this.queryCondition.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568); 
    } 
    if (this.havingCondition != null) {
      this.havingCondition.resolveTypes(paramSession, null);
      if (this.havingCondition.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568); 
    } 
    if (this.sortAndSlice.limitCondition != null)
      this.sortAndSlice.limitCondition.resolveTypes(paramSession, (Expression)null); 
  }
  
  private void resolveAggregates() {
    this.tempSet.clear();
    if (this.isAggregated) {
      this.aggregateCheck = new boolean[this.indexStartAggregates];
      this.tempSet.addAll((Collection)this.aggregateSet);
      this.indexLimitData = this.indexLimitExpressions = this.exprColumns.length + this.tempSet.size();
      this.exprColumns = (Expression[])ArrayUtil.resizeArray(this.exprColumns, this.indexLimitExpressions);
      int i = this.indexStartAggregates;
      for (byte b = 0; i < this.indexLimitExpressions; b++) {
        Expression expression = (Expression)this.tempSet.get(b);
        this.exprColumns[i] = expression.duplicate();
        (this.exprColumns[i]).nodes = expression.nodes;
        (this.exprColumns[i]).dataType = expression.dataType;
        i++;
      } 
      this.tempSet.clear();
    } 
  }
  
  private void setRangeVariableConditions(Session paramSession) {
    RangeVariableResolver rangeVariableResolver = new RangeVariableResolver(this);
    rangeVariableResolver.processConditions(paramSession);
    this.rangeVariables = rangeVariableResolver.rangeVariables;
  }
  
  private void setDistinctConditions(Session paramSession) {
    int[] arrayOfInt;
    if (!this.isDistinctSelect && !this.isGrouped)
      return; 
    if (this.isAggregated)
      return; 
    for (byte b1 = 0; b1 < this.rangeVariables.length; b1++) {
      if ((this.rangeVariables[b1]).isLateral || (this.rangeVariables[b1]).isLeftJoin || (this.rangeVariables[b1]).isRightJoin)
        return; 
    } 
    RangeVariable rangeVariable = null;
    if (this.isGrouped) {
      arrayOfInt = new int[this.groupByColumnCount];
      for (byte b = 0; b < this.groupByColumnCount; b++) {
        if (this.exprColumns[this.indexLimitRowId + b].getType() != 2)
          return; 
        if (rangeVariable == null) {
          rangeVariable = this.exprColumns[this.indexLimitRowId + b].getRangeVariable();
        } else if (rangeVariable != this.exprColumns[this.indexLimitRowId + b].getRangeVariable()) {
          return;
        } 
        arrayOfInt[b] = (this.exprColumns[b]).columnIndex;
      } 
    } else {
      arrayOfInt = new int[this.indexLimitVisible];
    } 
    for (byte b2 = 0; b2 < this.indexLimitVisible; b2++) {
      if (this.exprColumns[b2].getType() != 2)
        return; 
      if (rangeVariable == null) {
        rangeVariable = this.exprColumns[b2].getRangeVariable();
      } else if (rangeVariable != this.exprColumns[b2].getRangeVariable()) {
        return;
      } 
      if (!this.isGrouped)
        arrayOfInt[b2] = (this.exprColumns[b2]).columnIndex; 
    } 
    boolean bool = ArrayUtil.areAllIntIndexesAsBooleanArray(arrayOfInt, rangeVariable.usedColumns);
    if (!bool)
      return; 
    if (!rangeVariable.hasAnyIndexCondition()) {
      Index index = rangeVariable.rangeTable.getIndexForColumns(arrayOfInt);
      if (index != null)
        rangeVariable.setSortIndex(index, false); 
    } 
    this.isSimpleDistinct = rangeVariable.setDistinctColumnsOnIndex(arrayOfInt);
  }
  
  private void setAggregateConditions(Session paramSession) {
    if (!this.isAggregated)
      return; 
    if (this.isGrouped) {
      setGroupedAggregateConditions(paramSession);
    } else if (!this.sortAndSlice.hasOrder() && !this.sortAndSlice.hasLimit() && this.aggregateSet.size() == 1 && this.indexLimitVisible == 1) {
      SortAndSlice sortAndSlice;
      Expression expression = this.exprColumns[this.indexStartAggregates];
      int i = expression.getType();
      switch (i) {
        case 73:
        case 74:
          if (expression.hasCondition())
            break; 
          sortAndSlice = new SortAndSlice();
          sortAndSlice.isGenerated = true;
          sortAndSlice.addLimitCondition(ExpressionOp.limitOneExpression);
          if (sortAndSlice.prepareSpecial(paramSession, this))
            this.sortAndSlice = sortAndSlice; 
          break;
        case 71:
          if (!expression.hasCondition() && this.rangeVariables.length == 1 && this.queryCondition == null) {
            Expression expression1 = expression.getLeftNode();
            if (expression1.getType() == 11) {
              this.isSimpleCount = true;
              break;
            } 
            if (expression1.getNullability() == 0) {
              if (((ExpressionAggregate)expression).isDistinctAggregate) {
                if (expression1.opType == 2) {
                  Table table = expression1.getRangeVariable().getTable();
                  if ((table.getPrimaryKey()).length == 1 && table.getColumn(table.getPrimaryKey()[0]) == expression1.getColumn())
                    this.isSimpleCount = true; 
                } 
                break;
              } 
              this.isSimpleCount = true;
            } 
          } 
          break;
      } 
    } 
  }
  
  private void setGroupedAggregateConditions(Session paramSession) {}
  
  void checkLobUsage() {}
  
  private void resolveGroups() {
    this.tempSet.clear();
    if (this.isGrouped) {
      int k;
      for (k = this.indexLimitVisible; k < this.indexLimitVisible + this.groupByColumnCount; k++) {
        this.exprColumns[k].collectAllExpressions(this.tempSet, Expression.aggregateFunctionSet, Expression.subqueryExpressionSet);
        if (!this.tempSet.isEmpty())
          throw Error.error(5572, ((Expression)this.tempSet.get(0)).getSQL()); 
      } 
      for (k = 0; k < this.indexLimitVisible; k++) {
        if (!this.exprColumns[k].isComposedOf(this.exprColumns, this.indexLimitVisible, this.indexLimitVisible + this.groupByColumnCount, Expression.aggregateFunctionSet))
          this.tempSet.add(this.exprColumns[k]); 
      } 
      if (!this.tempSet.isEmpty() && !resolveForGroupBy((HsqlList)this.tempSet))
        throw Error.error(5574, ((Expression)this.tempSet.get(0)).getSQL()); 
    } else if (this.isAggregated) {
      for (byte b = 0; b < this.indexLimitVisible; b++) {
        this.exprColumns[b].collectAllExpressions(this.tempSet, Expression.columnExpressionSet, Expression.aggregateFunctionSet);
        if (!this.tempSet.isEmpty())
          throw Error.error(5574, ((Expression)this.tempSet.get(0)).getSQL()); 
      } 
    } 
    this.tempSet.clear();
    if (this.havingCondition != null) {
      if (this.unresolvedExpressions != null)
        this.tempSet.addAll((Collection)this.unresolvedExpressions); 
      for (int k = this.indexLimitVisible; k < this.indexLimitVisible + this.groupByColumnCount; k++)
        this.tempSet.add(this.exprColumns[k]); 
      if (!this.havingCondition.isComposedOf(this.tempSet, this.outerRanges, Expression.subqueryAggregateExpressionSet))
        throw Error.error(5573); 
      this.tempSet.clear();
    } 
    if (this.isDistinctSelect) {
      int k = this.sortAndSlice.getOrderLength();
      for (byte b = 0; b < k; b++) {
        Expression expression = (Expression)this.sortAndSlice.exprList.get(b);
        if (expression.queryTableColumnIndex == -1 && !expression.isComposedOf(this.exprColumns, 0, this.indexLimitVisible, Expression.emptyExpressionSet))
          throw Error.error(5576); 
      } 
    } 
    if (this.isGrouped) {
      int k = this.sortAndSlice.getOrderLength();
      for (byte b = 0; b < k; b++) {
        Expression expression = (Expression)this.sortAndSlice.exprList.get(b);
        if (expression.queryTableColumnIndex == -1 && !expression.isAggregate() && !expression.isComposedOf(this.exprColumns, 0, this.indexLimitVisible + this.groupByColumnCount, Expression.emptyExpressionSet))
          throw Error.error(5576); 
      } 
    } 
    if (!this.isAggregated)
      return; 
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    int i;
    for (i = this.indexStartAggregates; i < this.indexLimitExpressions; i++) {
      Expression expression = this.exprColumns[i];
      ExpressionColumn expressionColumn = new ExpressionColumn(expression, i, this.resultRangePosition);
      orderedHashSet1.add(expression);
      orderedHashSet2.add(expressionColumn);
    } 
    for (i = 0; i < this.indexStartHaving; i++) {
      if (!this.exprColumns[i].isAggregate()) {
        Expression expression = this.exprColumns[i];
        if (orderedHashSet1.add(expression)) {
          ExpressionColumn expressionColumn = new ExpressionColumn(expression, i, this.resultRangePosition);
          orderedHashSet2.add(expressionColumn);
        } 
      } 
    } 
    i = this.sortAndSlice.getOrderLength();
    int j;
    for (j = 0; j < i; j++) {
      Expression expression = (Expression)this.sortAndSlice.exprList.get(j);
      if (expression.getLeftNode().isAggregate())
        expression.setAggregate(); 
    } 
    for (j = this.indexStartOrderBy; j < this.indexStartAggregates; j++) {
      if (this.exprColumns[j].getLeftNode().isAggregate())
        this.exprColumns[j].setAggregate(); 
    } 
    for (j = 0; j < this.indexStartAggregates; j++) {
      Expression expression = this.exprColumns[j];
      if (expression.isAggregate()) {
        this.aggregateCheck[j] = true;
        if (expression.isAggregate())
          expression.convertToSimpleColumn(orderedHashSet1, orderedHashSet2); 
      } 
    } 
    for (j = 0; j < this.aggregateSet.size(); j++) {
      Expression expression = (Expression)this.aggregateSet.get(j);
      expression.convertToSimpleColumn(orderedHashSet1, orderedHashSet2);
    } 
    if (this.resolvedSubqueryExpressions != null)
      for (j = 0; j < this.resolvedSubqueryExpressions.size(); j++) {
        Expression expression = (Expression)this.resolvedSubqueryExpressions.get(j);
        expression.convertToSimpleColumn(orderedHashSet1, orderedHashSet2);
      }  
  }
  
  boolean resolveForGroupBy(HsqlList paramHsqlList) {
    int i;
    for (i = this.indexLimitVisible; i < this.indexLimitVisible + this.groupByColumnCount; i++) {
      Expression expression = this.exprColumns[i];
      if (expression.getType() == 2) {
        RangeVariable rangeVariable = expression.getRangeVariable();
        int j = expression.getColumnIndex();
        rangeVariable.columnsInGroupBy[j] = true;
      } 
    } 
    for (i = 0; i < this.rangeVariables.length; i++) {
      RangeVariable rangeVariable = this.rangeVariables[i];
      rangeVariable.hasKeyedColumnInGroupBy = (rangeVariable.rangeTable.getUniqueNotNullColumnGroup(rangeVariable.columnsInGroupBy) != null);
    } 
    OrderedHashSet orderedHashSet = null;
    for (byte b = 0; b < paramHsqlList.size(); b++) {
      Expression expression = (Expression)paramHsqlList.get(b);
      orderedHashSet = expression.getUnkeyedColumns(orderedHashSet);
    } 
    return (orderedHashSet == null);
  }
  
  Result getResult(Session paramSession, int paramInt) {
    Result result = getSingleResult(paramSession, paramInt);
    result.getNavigator().reset();
    return result;
  }
  
  private Result getSingleResult(Session paramSession, int paramInt) {
    int[] arrayOfInt = this.sortAndSlice.getLimits(paramSession, this, paramInt);
    Result result = buildResult(paramSession, arrayOfInt);
    RowSetNavigatorData rowSetNavigatorData = (RowSetNavigatorData)result.getNavigator();
    if (this.isDistinctSelect)
      rowSetNavigatorData.removeDuplicates(paramSession); 
    if (this.sortAndSlice.hasOrder())
      rowSetNavigatorData.sortOrder(paramSession); 
    if (arrayOfInt != SortAndSlice.defaultLimits && !this.sortAndSlice.skipFullResult)
      rowSetNavigatorData.trim(arrayOfInt[0], arrayOfInt[1]); 
    return result;
  }
  
  private Result buildResult(Session paramSession, int[] paramArrayOfint) {
    RowSetNavigatorDataTable rowSetNavigatorDataTable;
    RowSetNavigatorData rowSetNavigatorData = new RowSetNavigatorData(paramSession, this);
    Result result = Result.newResult((RowSetNavigator)rowSetNavigatorData);
    boolean bool = (this.isGrouped && !this.isSimpleDistinct) ? true : false;
    result.metaData = this.resultMetaData;
    if (this.isUpdatable)
      result.rsProperties = 8; 
    int i = 0;
    int j = paramArrayOfint[2];
    if (this.sortAndSlice.skipFullResult) {
      i = paramArrayOfint[0];
      j = paramArrayOfint[1];
    } 
    if (this.isSimpleCount) {
      Object[] arrayOfObject = new Object[this.indexLimitData];
      Table table = this.rangeVariables[0].getTable();
      table.materialise(paramSession);
      PersistentStore persistentStore = table.getRowStore(paramSession);
      long l = persistentStore.elementCount(paramSession);
      arrayOfObject[this.indexStartAggregates] = ValuePool.getLong(l);
      arrayOfObject[0] = ValuePool.getLong(l);
      rowSetNavigatorData.add(arrayOfObject);
      return result;
    } 
    int k = 0;
    RangeIterator[] arrayOfRangeIterator = new RangeIterator[this.rangeVariables.length];
    int m;
    for (m = 0; m < this.rangeVariables.length; m++)
      arrayOfRangeIterator[m] = this.rangeVariables[m].getIterator(paramSession); 
    paramSession.sessionContext.rownum = 1;
    m = 0;
    while (true) {
      if (m < k) {
        boolean bool1 = true;
        for (int i2 = k + 1; i2 < this.rangeVariables.length; i2++) {
          if ((this.rangeVariables[i2]).isRightJoin) {
            k = i2;
            m = i2;
            bool1 = false;
            ((RangeVariable.RangeIteratorRight)arrayOfRangeIterator[i2]).setOnOuterRows();
            break;
          } 
        } 
        if (bool1)
          break; 
      } 
      RangeIterator rangeIterator = arrayOfRangeIterator[m];
      if (rangeIterator.next()) {
        if (m < this.rangeVariables.length - 1) {
          m++;
          continue;
        } 
      } else {
        rangeIterator.reset();
        m--;
        continue;
      } 
      if (j == 0)
        break; 
      paramSession.sessionData.startRowProcessing();
      Object[] arrayOfObject1 = new Object[this.indexLimitData];
      int n;
      for (n = 0; n < this.indexStartAggregates; n++) {
        if (!this.isAggregated || !this.aggregateCheck[n])
          arrayOfObject1[n] = this.exprColumns[n].getValue(paramSession); 
      } 
      for (n = this.indexLimitVisible; n < this.indexLimitRowId; n++) {
        if (n == this.indexLimitVisible) {
          arrayOfObject1[n] = rangeIterator.getRowidObject();
        } else {
          arrayOfObject1[n] = rangeIterator.getCurrentRow();
        } 
      } 
      paramSession.sessionContext.rownum++;
      if (i > 0) {
        i--;
        continue;
      } 
      Object[] arrayOfObject2 = null;
      if (this.isAggregated || bool) {
        arrayOfObject2 = rowSetNavigatorData.getGroupData(arrayOfObject1);
        if (arrayOfObject2 != null)
          arrayOfObject1 = arrayOfObject2; 
      } 
      int i1;
      for (i1 = this.indexStartAggregates; i1 < this.indexLimitExpressions; i1++)
        arrayOfObject1[i1] = this.exprColumns[i1].updateAggregatingValue(paramSession, arrayOfObject1[i1]); 
      if (arrayOfObject2 == null) {
        rowSetNavigatorData.add(arrayOfObject1);
      } else if (this.isAggregated) {
        rowSetNavigatorData.update(arrayOfObject2, arrayOfObject1);
      } 
      i1 = rowSetNavigatorData.getSize();
      if (i1 == paramSession.resultMaxMemoryRows && !this.isAggregated && !this.isSingleMemoryTable) {
        rowSetNavigatorDataTable = new RowSetNavigatorDataTable(paramSession, this, rowSetNavigatorData);
        result.setNavigator((RowSetNavigator)rowSetNavigatorDataTable);
      } 
      if (((!this.isAggregated && !bool) || this.sortAndSlice.isGenerated) && i1 >= j)
        break; 
    } 
    rowSetNavigatorDataTable.reset();
    for (m = 0; m < this.rangeVariables.length; m++)
      arrayOfRangeIterator[m].reset(); 
    if (!bool && !this.isAggregated)
      return result; 
    if (this.isAggregated) {
      if (!bool && rowSetNavigatorDataTable.getSize() == 0) {
        Object[] arrayOfObject = new Object[this.exprColumns.length];
        for (byte b = 0; b < this.indexStartAggregates; b++) {
          if (!this.aggregateCheck[b])
            arrayOfObject[b] = this.exprColumns[b].getValue(paramSession); 
        } 
        rowSetNavigatorDataTable.add(arrayOfObject);
      } 
      rowSetNavigatorDataTable.reset();
      paramSession.sessionContext.setRangeIterator((RangeIterator)rowSetNavigatorDataTable);
      while (rowSetNavigatorDataTable.next()) {
        Object[] arrayOfObject = rowSetNavigatorDataTable.getCurrent();
        int n;
        for (n = this.indexStartAggregates; n < this.indexLimitExpressions; n++)
          arrayOfObject[n] = this.exprColumns[n].getAggregatedValue(paramSession, arrayOfObject[n]); 
        for (n = 0; n < this.indexStartAggregates; n++) {
          if (this.aggregateCheck[n])
            arrayOfObject[n] = this.exprColumns[n].getValue(paramSession); 
        } 
      } 
      paramSession.sessionContext.unsetRangeIterator((RangeIterator)rowSetNavigatorDataTable);
    } 
    rowSetNavigatorDataTable.reset();
    if (this.havingCondition != null) {
      while (rowSetNavigatorDataTable.hasNext()) {
        Object[] arrayOfObject = rowSetNavigatorDataTable.getNext();
        if (!Boolean.TRUE.equals(arrayOfObject[this.indexLimitVisible + this.groupByColumnCount]))
          rowSetNavigatorDataTable.removeCurrent(); 
      } 
      rowSetNavigatorDataTable.reset();
    } 
    return result;
  }
  
  void setReferenceableColumns() {
    this.accessibleColumns = new boolean[this.indexLimitVisible];
    IntValueHashMap intValueHashMap = new IntValueHashMap();
    for (byte b = 0; b < this.indexLimitVisible; b++) {
      Expression expression = this.exprColumns[b];
      String str = expression.getAlias();
      if (str.length() == 0) {
        HsqlNameManager.HsqlName hsqlName = HsqlNameManager.getAutoColumnName(b);
        expression.setAlias(hsqlName);
      } else {
        int i = intValueHashMap.get(str, -1);
        if (i == -1) {
          intValueHashMap.put(str, b);
          this.accessibleColumns[b] = true;
        } else {
          this.accessibleColumns[i] = false;
        } 
      } 
    } 
  }
  
  void setColumnAliases(HsqlNameManager.SimpleName[] paramArrayOfSimpleName) {
    if (paramArrayOfSimpleName.length != this.indexLimitVisible)
      throw Error.error(5593); 
    for (byte b = 0; b < this.indexLimitVisible; b++)
      this.exprColumns[b].setAlias(paramArrayOfSimpleName[b]); 
  }
  
  private void createResultMetaData(Session paramSession) {
    this.resultMetaData = ResultMetaData.newResultMetaData(this.resultColumnTypes, this.columnMap, this.indexLimitVisible, this.indexLimitRowId);
    for (byte b = 0; b < this.indexLimitVisible; b++) {
      ColumnBase columnBase;
      Expression expression = this.exprColumns[b];
      ColumnSchema columnSchema = null;
      columnSchema = expression.getColumn();
      this.resultMetaData.columnTypes[b] = expression.getDataType();
      if (columnSchema == null) {
        columnBase = new ColumnBase();
      } else {
        columnBase = new ColumnBase((paramSession.database.getCatalogName()).name, columnSchema);
      } 
      columnBase.setType(expression.getDataType());
      this.resultMetaData.columns[b] = columnBase;
      this.resultMetaData.columnLabels[b] = expression.getAlias();
    } 
  }
  
  private void setResultNullability() {
    for (byte b = 0; b < this.indexLimitVisible; b++) {
      Expression expression = this.exprColumns[b];
      byte b1 = expression.getNullability();
      if (expression.opType == 2) {
        RangeVariable rangeVariable = expression.getRangeVariable();
        if (rangeVariable != null && (rangeVariable.rangePositionInJoin < this.startInnerRange || rangeVariable.rangePositionInJoin >= this.endInnerRange))
          b1 = 1; 
      } 
      this.resultMetaData.columns[b].setNullability(b1);
    } 
  }
  
  void createTable(Session paramSession) {
    createResultTable(paramSession);
    this.mainIndex = this.resultTable.getPrimaryIndex();
    if (this.sortAndSlice.hasOrder() && !this.sortAndSlice.skipSort)
      this.orderIndex = this.sortAndSlice.getNewIndex(paramSession, this.resultTable); 
    if (this.isDistinctSelect || this.isFullOrder)
      createFullIndex(paramSession); 
    if (this.isGrouped) {
      int[] arrayOfInt = new int[this.groupByColumnCount];
      for (byte b = 0; b < this.groupByColumnCount; b++)
        arrayOfInt[b] = this.indexLimitRowId + b; 
      this.groupIndex = this.resultTable.createAndAddIndexStructure(paramSession, null, arrayOfInt, null, null, false, false, false);
    } else if (this.isAggregated) {
      this.groupIndex = this.mainIndex;
    } 
    if (this.isUpdatable && this.view == null) {
      int[] arrayOfInt = { this.indexLimitVisible };
      this.idIndex = this.resultTable.createAndAddIndexStructure(paramSession, null, arrayOfInt, null, null, false, false, false);
    } 
  }
  
  private void createFullIndex(Session paramSession) {
    int[] arrayOfInt = new int[this.indexLimitVisible];
    ArrayUtil.fillSequence(arrayOfInt);
    this.fullIndex = this.resultTable.createAndAddIndexStructure(paramSession, null, arrayOfInt, null, null, false, false, false);
    this.resultTable.fullIndex = this.fullIndex;
  }
  
  private void setResultColumnTypes() {
    this.resultColumnTypes = new Type[this.indexLimitData];
    int i;
    for (i = 0; i < this.indexLimitVisible; i++) {
      Expression expression = this.exprColumns[i];
      this.resultColumnTypes[i] = expression.getDataType();
    } 
    for (i = this.indexLimitVisible; i < this.indexLimitRowId; i++) {
      if (i == this.indexLimitVisible) {
        this.resultColumnTypes[i] = (Type)Type.SQL_BIGINT;
      } else {
        this.resultColumnTypes[i] = Type.SQL_ALL_TYPES;
      } 
    } 
    for (i = this.indexLimitRowId; i < this.indexLimitData; i++) {
      Expression expression = this.exprColumns[i];
      Type type = expression.getDataType();
      if (type.getCollation() != expression.collation && expression.collation != null)
        type = Type.getType(type, expression.collation); 
      this.resultColumnTypes[i] = type;
    } 
  }
  
  void createResultTable(Session paramSession) {
    HsqlNameManager.HsqlName hsqlName = paramSession.database.nameManager.getSubqueryTableName();
    byte b1 = (this.persistenceScope == 21) ? 2 : 9;
    HashMappedList hashMappedList = new HashMappedList();
    for (byte b2 = 0; b2 < this.indexLimitVisible; b2++) {
      Expression expression = this.exprColumns[b2];
      HsqlNameManager.SimpleName simpleName = expression.getSimpleName();
      String str = simpleName.name;
      HsqlNameManager.HsqlName hsqlName1 = paramSession.database.nameManager.newColumnSchemaHsqlName(hsqlName, simpleName);
      if (!this.accessibleColumns[b2])
        str = HsqlNameManager.getAutoNoNameColumnString(b2); 
      ColumnSchema columnSchema = new ColumnSchema(hsqlName1, expression.dataType, true, false, null);
      hashMappedList.add(str, columnSchema);
    } 
    try {
      this.resultTable = new TableDerived(paramSession.database, hsqlName, b1, this.resultColumnTypes, hashMappedList, ValuePool.emptyIntArray);
    } catch (Exception exception) {}
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SELECT").append(' ');
    int i = this.indexLimitVisible;
    int j;
    for (j = 0; j < i; j++) {
      if (j > 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.exprColumns[j].getSQL());
    } 
    stringBuffer.append("FROM");
    i = this.rangeVariables.length;
    for (j = 0; j < i; j++) {
      RangeVariable rangeVariable = this.rangeVariables[j];
      if (j > 0) {
        if (rangeVariable.isLeftJoin && rangeVariable.isRightJoin) {
          stringBuffer.append("FULL").append(' ');
        } else if (rangeVariable.isLeftJoin) {
          stringBuffer.append("LEFT").append(' ');
        } else if (rangeVariable.isRightJoin) {
          stringBuffer.append("RIGHT").append(' ');
        } 
        stringBuffer.append("JOIN").append(' ');
      } 
      stringBuffer.append((rangeVariable.getTable().getName()).statementName);
    } 
    if (this.isGrouped) {
      stringBuffer.append(' ').append("GROUP").append(' ').append("BY");
      i = this.indexLimitVisible + this.groupByColumnCount;
      for (j = this.indexLimitVisible; j < i; j++) {
        stringBuffer.append(this.exprColumns[j].getSQL());
        if (j < i - 1)
          stringBuffer.append(','); 
      } 
    } 
    if (this.havingCondition != null) {
      stringBuffer.append(' ').append("HAVING").append(' ');
      stringBuffer.append(this.havingCondition.getSQL());
    } 
    if (this.sortAndSlice.hasOrder()) {
      i = this.indexStartOrderBy + this.sortAndSlice.getOrderLength();
      stringBuffer.append(' ').append("ORDER").append("BY").append(' ');
      for (j = this.indexStartOrderBy; j < i; j++) {
        stringBuffer.append(this.exprColumns[j].getSQL());
        if (j < i - 1)
          stringBuffer.append(','); 
      } 
    } 
    if (this.sortAndSlice.hasLimit())
      stringBuffer.append(this.sortAndSlice.limitCondition.getLeftNode().getSQL()); 
    return stringBuffer.toString();
  }
  
  public ResultMetaData getMetaData() {
    return this.resultMetaData;
  }
  
  public String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer2 = new StringBuffer(paramInt);
    int i;
    for (i = 0; i < paramInt; i++)
      stringBuffer2.append(' '); 
    StringBuffer stringBuffer1 = new StringBuffer();
    stringBuffer1.append(stringBuffer2).append("isDistinctSelect=[").append(this.isDistinctSelect).append("]\n");
    stringBuffer1.append(stringBuffer2).append("isGrouped=[").append(this.isGrouped).append("]\n");
    stringBuffer1.append(stringBuffer2).append("isAggregated=[").append(this.isAggregated).append("]\n");
    stringBuffer1.append(stringBuffer2).append("columns=[");
    for (i = 0; i < this.indexLimitVisible; i++) {
      int j = i;
      if (this.exprColumns[i].getType() == 5)
        j = (this.exprColumns[i]).columnIndex; 
      stringBuffer1.append(stringBuffer2);
      String str1 = this.exprColumns[j].describe(paramSession, 2);
      stringBuffer1.append(str1.substring(0, str1.length() - 1));
      if (this.resultMetaData.columns[i].getNullability() == 0) {
        stringBuffer1.append(" not nullable\n");
      } else {
        stringBuffer1.append(" nullable\n");
      } 
    } 
    stringBuffer1.append("\n");
    stringBuffer1.append(stringBuffer2).append("]\n");
    for (i = 0; i < this.rangeVariables.length; i++) {
      stringBuffer1.append(stringBuffer2).append("[");
      stringBuffer1.append("range variable ").append(i + 1).append("\n");
      stringBuffer1.append(this.rangeVariables[i].describe(paramSession, paramInt + 2));
      stringBuffer1.append(stringBuffer2).append("]");
    } 
    stringBuffer1.append(stringBuffer2).append("]\n");
    String str = (this.queryCondition == null) ? "null" : this.queryCondition.describe(paramSession, paramInt);
    if (this.isGrouped) {
      stringBuffer1.append(stringBuffer2).append("groupColumns=[");
      for (i = this.indexLimitRowId; i < this.indexLimitRowId + this.groupByColumnCount; i++) {
        int j = i;
        if (this.exprColumns[i].getType() == 5)
          j = (this.exprColumns[i]).columnIndex; 
        stringBuffer1.append(this.exprColumns[j].describe(paramSession, paramInt));
      } 
      stringBuffer1.append(stringBuffer2).append("]\n");
    } 
    if (this.havingCondition != null) {
      str = this.havingCondition.describe(paramSession, paramInt);
      stringBuffer1.append(stringBuffer2).append("havingCondition=[").append(str).append("]\n");
    } 
    if (this.sortAndSlice.hasOrder()) {
      stringBuffer1.append(stringBuffer2).append("order by=[\n");
      for (i = 0; i < this.sortAndSlice.exprList.size(); i++)
        stringBuffer1.append(stringBuffer2).append(((Expression)this.sortAndSlice.exprList.get(i)).describe(paramSession, paramInt)); 
      if (this.sortAndSlice.primaryTableIndex != null)
        stringBuffer1.append(stringBuffer2).append("uses index"); 
      stringBuffer1.append(stringBuffer2).append("]\n");
    } 
    if (this.sortAndSlice.hasLimit()) {
      if (this.sortAndSlice.limitCondition.getLeftNode() != null)
        stringBuffer1.append(stringBuffer2).append("offset=[").append(this.sortAndSlice.limitCondition.getLeftNode().describe(paramSession, stringBuffer2.length())).append("]\n"); 
      if (this.sortAndSlice.limitCondition.getRightNode() != null)
        stringBuffer1.append(stringBuffer2).append("limit=[").append(this.sortAndSlice.limitCondition.getRightNode().describe(paramSession, stringBuffer2.length())).append("]\n"); 
    } 
    return stringBuffer1.toString();
  }
  
  void setMergeability() {
    this.isOrderSensitive |= this.sortAndSlice.hasLimit();
    if (this.isOrderSensitive)
      this.isMergeable = false; 
    if (this.isAggregated)
      this.isMergeable = false; 
    if (this.isGrouped || this.isDistinctSelect)
      this.isMergeable = false; 
    if (this.rangeVariables.length != 1) {
      this.isBaseMergeable = false;
      this.isMergeable = false;
      return;
    } 
  }
  
  void setUpdatability() {
    if (!this.isUpdatable)
      return; 
    this.isUpdatable = false;
    if (this.isGrouped || this.isDistinctSelect || this.isAggregated)
      return; 
    if (!this.isBaseMergeable)
      return; 
    if (!this.isTopLevel)
      return; 
    if (this.sortAndSlice.hasLimit() || this.sortAndSlice.hasOrder())
      return; 
    RangeVariable rangeVariable = this.rangeVariables[0];
    Table table1 = rangeVariable.getTable();
    Table table2 = table1.getBaseTable();
    if (table2 == null)
      return; 
    this.isInsertable = table1.isInsertable();
    this.isUpdatable = table1.isUpdatable();
    if (!this.isInsertable && !this.isUpdatable)
      return; 
    IntValueHashMap intValueHashMap = new IntValueHashMap();
    int[] arrayOfInt1 = table1.getBaseTableColumnMap();
    int[] arrayOfInt2 = new int[this.indexLimitVisible];
    if (this.queryCondition != null) {
      this.tempSet.clear();
      collectSubQueriesAndReferences(this.tempSet, this.queryCondition);
      if (this.tempSet.contains(table1.getName()) || this.tempSet.contains(table2.getName())) {
        this.isUpdatable = false;
        this.isInsertable = false;
        return;
      } 
    } 
    byte b;
    for (b = 0; b < this.indexLimitVisible; b++) {
      Expression expression = this.exprColumns[b];
      if (expression.getType() == 2) {
        String str = (expression.getColumn().getName()).name;
        if (intValueHashMap.containsKey(str)) {
          intValueHashMap.put(str, 1);
        } else {
          intValueHashMap.put(str, 0);
        } 
      } else {
        this.tempSet.clear();
        collectSubQueriesAndReferences(this.tempSet, expression);
        if (this.tempSet.contains(table1.getName())) {
          this.isUpdatable = false;
          this.isInsertable = false;
          return;
        } 
      } 
    } 
    this.isUpdatable = false;
    for (b = 0; b < this.indexLimitVisible; b++) {
      if (this.accessibleColumns[b]) {
        Expression expression = this.exprColumns[b];
        if (expression.getType() == 2) {
          String str = (expression.getColumn().getName()).name;
          if (intValueHashMap.get(str) == 0) {
            int i = table1.findColumn(str);
            arrayOfInt2[b] = arrayOfInt1[i];
            if (arrayOfInt2[b] != -1)
              this.isUpdatable = true; 
            continue;
          } 
        } 
      } 
      arrayOfInt2[b] = -1;
      this.isInsertable = false;
      continue;
    } 
    if (this.isInsertable) {
      boolean[] arrayOfBoolean = table2.getColumnCheckList(arrayOfInt2);
      for (b = 0; b < arrayOfBoolean.length; b++) {
        if (!arrayOfBoolean[b]) {
          ColumnSchema columnSchema = table2.getColumn(b);
          if (!columnSchema.isIdentity() && !columnSchema.isGenerated() && !columnSchema.hasDefault() && !columnSchema.isNullable()) {
            this.isInsertable = false;
            break;
          } 
        } 
      } 
    } 
    if (!this.isUpdatable)
      this.isInsertable = false; 
    if (this.isUpdatable) {
      this.columnMap = arrayOfInt2;
      this.baseTable = table2;
      if (this.view != null)
        return; 
      this.indexLimitRowId++;
      this.hasRowID = true;
      if (!table2.isFileBased()) {
        this.indexLimitRowId++;
        this.isSingleMemoryTable = true;
      } 
      this.indexLimitData = this.indexLimitRowId;
    } 
  }
  
  void mergeQuery() {
    RangeVariable rangeVariable = this.rangeVariables[0];
    Table table = rangeVariable.getTable();
    Expression expression = this.queryCondition;
    QueryExpression queryExpression = table.getQueryExpression();
    if (this.isBaseMergeable && queryExpression != null && queryExpression.isMergeable) {
      QuerySpecification querySpecification = queryExpression.getMainSelect();
      this.rangeVariables[0] = querySpecification.rangeVariables[0];
      this.rangeVariables[0].resetConditions();
      for (byte b = 0; b < this.indexLimitExpressions; b++) {
        Expression expression2 = this.exprColumns[b];
        this.exprColumns[b] = expression2.replaceColumnReferences(rangeVariable, querySpecification.exprColumns);
      } 
      if (expression != null)
        expression = expression.replaceColumnReferences(rangeVariable, querySpecification.exprColumns); 
      Expression expression1 = querySpecification.queryCondition;
      this.checkQueryCondition = querySpecification.checkQueryCondition;
      this.queryCondition = ExpressionLogical.andExpressions(expression1, expression);
    } 
    if (this.view != null)
      switch (this.view.getCheckOption()) {
        case 1:
          if (!this.isUpdatable)
            throw Error.error(5537); 
          this.checkQueryCondition = expression;
          break;
        case 2:
          if (!this.isUpdatable)
            throw Error.error(5537); 
          this.checkQueryCondition = this.queryCondition;
          break;
      }  
  }
  
  static void collectSubQueriesAndReferences(OrderedHashSet paramOrderedHashSet, Expression paramExpression) {
    paramExpression.collectAllExpressions(paramOrderedHashSet, Expression.subqueryExpressionSet, Expression.emptyExpressionSet);
    int i = paramOrderedHashSet.size();
    for (byte b = 0; b < i; b++) {
      Expression expression = (Expression)paramOrderedHashSet.get(b);
      expression.collectObjectNames((Set)paramOrderedHashSet);
    } 
  }
  
  public OrderedHashSet getSubqueries() {
    OrderedHashSet orderedHashSet = null;
    byte b;
    for (b = 0; b < this.indexLimitExpressions; b++)
      orderedHashSet = this.exprColumns[b].collectAllSubqueries(orderedHashSet); 
    if (this.queryCondition != null)
      orderedHashSet = this.queryCondition.collectAllSubqueries(orderedHashSet); 
    if (this.havingCondition != null)
      orderedHashSet = this.havingCondition.collectAllSubqueries(orderedHashSet); 
    for (b = 0; b < this.rangeVariables.length; b++) {
      OrderedHashSet orderedHashSet1 = this.rangeVariables[b].getSubqueries();
      orderedHashSet = OrderedHashSet.addAll(orderedHashSet, orderedHashSet1);
    } 
    return orderedHashSet;
  }
  
  public Table getBaseTable() {
    return this.baseTable;
  }
  
  public OrderedHashSet collectAllSubqueries(OrderedHashSet paramOrderedHashSet) {
    return paramOrderedHashSet;
  }
  
  public OrderedHashSet collectOuterColumnExpressions(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    paramOrderedHashSet1 = collectAllExpressions(paramOrderedHashSet1, Expression.columnExpressionSet, Expression.subqueryAggregateExpressionSet);
    if (paramOrderedHashSet1 == null)
      return null; 
    for (int i = paramOrderedHashSet1.size() - 1; i >= 0; i--) {
      Expression expression = (Expression)paramOrderedHashSet1.get(i);
      if (ArrayUtil.find((Object[])this.rangeVariables, expression.getRangeVariable()) >= 0)
        paramOrderedHashSet1.remove(i); 
      if (paramOrderedHashSet2.contains(expression))
        paramOrderedHashSet1.remove(i); 
    } 
    if (paramOrderedHashSet1.isEmpty())
      paramOrderedHashSet1 = null; 
    return paramOrderedHashSet1;
  }
  
  public OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2) {
    byte b;
    for (b = 0; b < this.indexStartAggregates; b++)
      paramOrderedHashSet = this.exprColumns[b].collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    if (this.queryCondition != null)
      paramOrderedHashSet = this.queryCondition.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    if (this.havingCondition != null)
      paramOrderedHashSet = this.havingCondition.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    for (b = 0; b < this.rangeVariables.length; b++)
      this.rangeVariables[b].collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    return paramOrderedHashSet;
  }
  
  public void collectObjectNames(Set paramSet) {
    byte b;
    for (b = 0; b < this.indexStartAggregates; b++)
      this.exprColumns[b].collectObjectNames(paramSet); 
    if (this.queryCondition != null)
      this.queryCondition.collectObjectNames(paramSet); 
    if (this.havingCondition != null)
      this.havingCondition.collectObjectNames(paramSet); 
    b = 0;
    int i = this.rangeVariables.length;
    while (b < i) {
      HsqlNameManager.HsqlName hsqlName = this.rangeVariables[b].getTable().getName();
      paramSet.add(hsqlName);
      b++;
    } 
  }
  
  public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {
    byte b;
    for (b = 0; b < this.indexStartAggregates; b++)
      this.exprColumns[b] = this.exprColumns[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    if (this.queryCondition != null)
      this.queryCondition = this.queryCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    if (this.havingCondition != null)
      this.havingCondition = this.havingCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    b = 0;
    int i = this.rangeVariables.length;
    while (b < i) {
      this.rangeVariables[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      b++;
    } 
  }
  
  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    byte b;
    for (b = 0; b < this.indexStartAggregates; b++)
      this.exprColumns[b].replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
    if (this.queryCondition != null)
      this.queryCondition.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
    if (this.havingCondition != null)
      this.havingCondition.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
    b = 0;
    int i = this.rangeVariables.length;
    while (b < i) {
      this.rangeVariables[b].getSubqueries();
      b++;
    } 
  }
  
  public void setReturningResult() {
    setReturningResultSet();
    this.acceptsSequences = true;
    this.isTopLevel = true;
  }
  
  void setReturningResultSet() {
    this.persistenceScope = 23;
  }
  
  public boolean isSingleColumn() {
    return (this.indexLimitVisible == 1);
  }
  
  public String[] getColumnNames() {
    String[] arrayOfString = new String[this.indexLimitVisible];
    for (byte b = 0; b < this.indexLimitVisible; b++)
      arrayOfString[b] = this.exprColumns[b].getAlias(); 
    return arrayOfString;
  }
  
  public Type[] getColumnTypes() {
    if (this.resultColumnTypes.length == this.indexLimitVisible)
      return this.resultColumnTypes; 
    Type[] arrayOfType = new Type[this.indexLimitVisible];
    ArrayUtil.copyArray(this.resultColumnTypes, arrayOfType, arrayOfType.length);
    return arrayOfType;
  }
  
  public int getColumnCount() {
    return this.indexLimitVisible;
  }
  
  public int[] getBaseTableColumnMap() {
    return this.columnMap;
  }
  
  public Expression getCheckCondition() {
    return this.queryCondition;
  }
  
  void getBaseTableNames(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.rangeVariables.length; b++) {
      Table table = (this.rangeVariables[b]).rangeTable;
      HsqlNameManager.HsqlName hsqlName = table.getName();
      if (!table.isView() && !table.isDataReadOnly() && !table.isTemp() && hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        paramOrderedHashSet.add(hsqlName); 
    } 
  }
  
  boolean isEquivalent(QueryExpression paramQueryExpression) {
    if (!(paramQueryExpression instanceof QuerySpecification))
      return false; 
    QuerySpecification querySpecification = (QuerySpecification)paramQueryExpression;
    if (!Expression.equals(this.exprColumns, querySpecification.exprColumns))
      return false; 
    if (!Expression.equals(this.queryCondition, querySpecification.queryCondition))
      return false; 
    if (this.rangeVariables.length != querySpecification.rangeVariables.length)
      return false; 
    for (byte b = 0; b < this.rangeVariables.length; b++) {
      if (this.rangeVariables[b].getTable() != querySpecification.rangeVariables[b].getTable())
        return false; 
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\QuerySpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */