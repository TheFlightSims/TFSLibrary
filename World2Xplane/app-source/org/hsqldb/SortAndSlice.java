package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public final class SortAndSlice {
  static final SortAndSlice noSort = new SortAndSlice();
  
  static final int[] defaultLimits = new int[] { 0, Integer.MAX_VALUE, Integer.MAX_VALUE };
  
  public int[] sortOrder;
  
  public boolean[] sortDescending;
  
  public boolean[] sortNullsLast;
  
  public Collation[] collations;
  
  boolean sortUnion;
  
  HsqlArrayList exprList = new HsqlArrayList();
  
  ExpressionOp limitCondition;
  
  int columnCount;
  
  boolean hasNullsLast;
  
  boolean strictLimit;
  
  boolean zeroLimit;
  
  boolean usingIndex;
  
  boolean allDescending;
  
  public boolean skipSort = false;
  
  public boolean skipFullResult = false;
  
  public Index index;
  
  public Table primaryTable;
  
  public Index primaryTableIndex;
  
  public int[] colIndexes;
  
  public boolean isGenerated;
  
  public HsqlArrayList getExpressionList() {
    return this.exprList;
  }
  
  public boolean hasOrder() {
    return (this.exprList.size() != 0);
  }
  
  public boolean hasLimit() {
    return (this.limitCondition != null);
  }
  
  public int getOrderLength() {
    return this.exprList.size();
  }
  
  public void addOrderExpression(Expression paramExpression) {
    this.exprList.add(paramExpression);
  }
  
  public void addLimitCondition(ExpressionOp paramExpressionOp) {
    this.limitCondition = paramExpressionOp;
  }
  
  public void setStrictLimit() {
    this.strictLimit = true;
  }
  
  public void setZeroLimit() {
    this.zeroLimit = true;
  }
  
  public void setUsingIndex() {
    this.usingIndex = true;
  }
  
  public void prepareSingleColumn(int paramInt) {
    this.sortOrder = new int[1];
    this.sortDescending = new boolean[1];
    this.sortNullsLast = new boolean[1];
    this.sortOrder[0] = paramInt;
  }
  
  public void prepareMultiColumn(int paramInt) {
    this.sortOrder = new int[paramInt];
    this.sortDescending = new boolean[paramInt];
    this.sortNullsLast = new boolean[paramInt];
    for (byte b = 0; b < paramInt; b++)
      this.sortOrder[b] = b; 
  }
  
  public void prepare(int paramInt) {
    this.columnCount = this.exprList.size();
    if (this.columnCount == 0)
      return; 
    this.sortOrder = new int[this.columnCount + paramInt];
    this.sortDescending = new boolean[this.columnCount + paramInt];
    this.sortNullsLast = new boolean[this.columnCount + paramInt];
    ArrayUtil.fillSequence(this.sortOrder);
    for (byte b = 0; b < this.columnCount; b++) {
      ExpressionOrderBy expressionOrderBy = (ExpressionOrderBy)this.exprList.get(b);
      this.sortDescending[b] = expressionOrderBy.isDescending();
      this.sortNullsLast[b] = expressionOrderBy.isNullsLast();
      this.hasNullsLast |= this.sortNullsLast[b];
    } 
  }
  
  public void prepare(QuerySpecification paramQuerySpecification) {
    this.columnCount = this.exprList.size();
    if (this.columnCount == 0)
      return; 
    this.sortOrder = new int[this.columnCount];
    this.sortDescending = new boolean[this.columnCount];
    this.sortNullsLast = new boolean[this.columnCount];
    for (byte b = 0; b < this.columnCount; b++) {
      ExpressionOrderBy expressionOrderBy = (ExpressionOrderBy)this.exprList.get(b);
      if ((expressionOrderBy.getLeftNode()).queryTableColumnIndex == -1) {
        this.sortOrder[b] = paramQuerySpecification.indexStartOrderBy + b;
      } else {
        this.sortOrder[b] = (expressionOrderBy.getLeftNode()).queryTableColumnIndex;
      } 
      this.sortDescending[b] = expressionOrderBy.isDescending();
      this.sortNullsLast[b] = expressionOrderBy.isNullsLast();
      this.hasNullsLast |= this.sortNullsLast[b];
      if (expressionOrderBy.collation != null) {
        if (this.collations == null)
          this.collations = new Collation[this.columnCount]; 
        this.collations[b] = expressionOrderBy.collation;
      } 
    } 
  }
  
  void setSortIndex(QuerySpecification paramQuerySpecification) {
    if (this == noSort)
      return; 
    if (this.isGenerated)
      return; 
    byte b;
    for (b = 0; b < this.columnCount; b++) {
      ExpressionOrderBy expressionOrderBy = (ExpressionOrderBy)this.exprList.get(b);
      Type type = expressionOrderBy.getLeftNode().getDataType();
      if (type.isArrayType() || type.isLobType())
        throw Error.error(5534); 
    } 
    if (paramQuerySpecification == null)
      return; 
    if ((paramQuerySpecification.isDistinctSelect || paramQuerySpecification.isGrouped || paramQuerySpecification.isAggregated) && !paramQuerySpecification.isSimpleDistinct)
      return; 
    if (this.columnCount == 0) {
      if (this.limitCondition == null)
        return; 
      this.skipFullResult = true;
      return;
    } 
    if (this.collations != null)
      return; 
    this.colIndexes = new int[this.columnCount];
    b = 0;
    int i;
    for (i = 0; i < this.columnCount; i++) {
      Expression expression = ((Expression)this.exprList.get(i)).getLeftNode();
      if (expression.getType() != 2)
        return; 
      if (((ExpressionColumn)expression).getRangeVariable() != paramQuerySpecification.rangeVariables[0])
        return; 
      this.colIndexes[i] = expression.columnIndex;
      if (expression.getColumn().getNullability() != 0)
        b = 1; 
    } 
    if (this.hasNullsLast && b != 0)
      return; 
    i = ArrayUtil.countTrueElements(this.sortDescending);
    this.allDescending = (i == this.columnCount);
    if (!this.allDescending && i > 0)
      return; 
    this.primaryTable = paramQuerySpecification.rangeVariables[0].getTable();
    this.primaryTableIndex = this.primaryTable.getFullIndexForColumns(this.colIndexes);
  }
  
  void setSortRange(QuerySpecification paramQuerySpecification) {
    if (this == noSort)
      return; 
    if (this.primaryTableIndex == null) {
      if (paramQuerySpecification.isSimpleDistinct)
        setSortIndex(paramQuerySpecification); 
      if (this.primaryTableIndex == null)
        return; 
    } 
    Index index = paramQuerySpecification.rangeVariables[0].getSortIndex();
    if (index == null)
      return; 
    if (this.primaryTable != (paramQuerySpecification.rangeVariables[0]).rangeTable)
      return; 
    if (index == this.primaryTableIndex) {
      if (this.allDescending) {
        boolean bool = paramQuerySpecification.rangeVariables[0].reverseOrder();
        if (!bool)
          return; 
      } 
      this.skipSort = true;
      this.skipFullResult = true;
    } else if (!(paramQuerySpecification.rangeVariables[0]).joinConditions[0].hasIndexCondition() && paramQuerySpecification.rangeVariables[0].setSortIndex(this.primaryTableIndex, this.allDescending)) {
      this.skipSort = true;
      this.skipFullResult = true;
    } 
  }
  
  public boolean prepareSpecial(Session paramSession, QuerySpecification paramQuerySpecification) {
    Expression expression = paramQuerySpecification.exprColumns[paramQuerySpecification.indexStartAggregates];
    int i = expression.getType();
    expression = expression.getLeftNode();
    if (expression.getType() != 2)
      return false; 
    if (((ExpressionColumn)expression).getRangeVariable() != paramQuerySpecification.rangeVariables[0])
      return false; 
    Index index = paramQuerySpecification.rangeVariables[0].getSortIndex();
    if (index == null)
      return false; 
    if (paramQuerySpecification.rangeVariables[0].hasSingleIndexCondition()) {
      int[] arrayOfInt = index.getColumns();
      if (arrayOfInt[0] != ((ExpressionColumn)expression).getColumnIndex())
        return false; 
      if (i == 74)
        paramQuerySpecification.rangeVariables[0].reverseOrder(); 
    } else {
      if (paramQuerySpecification.rangeVariables[0].hasAnyIndexCondition())
        return false; 
      Table table = paramQuerySpecification.rangeVariables[0].getTable();
      Index index1 = table.getIndexForColumn(paramSession, ((ExpressionColumn)expression).getColumnIndex());
      if (index1 == null)
        return false; 
      Expression[] arrayOfExpression = { ExpressionLogical.newNotNullCondition(expression) };
      (paramQuerySpecification.rangeVariables[0]).joinConditions[0].addIndexCondition(arrayOfExpression, index1, 1);
      if (i == 74)
        paramQuerySpecification.rangeVariables[0].reverseOrder(); 
    } 
    this.columnCount = 1;
    this.sortOrder = new int[this.columnCount];
    this.sortDescending = new boolean[this.columnCount];
    this.sortNullsLast = new boolean[this.columnCount];
    this.skipSort = true;
    this.skipFullResult = true;
    return true;
  }
  
  int[] getLimits(Session paramSession, QueryExpression paramQueryExpression, int paramInt) {
    if (this == noSort && paramInt == 0)
      return defaultLimits; 
    int i = 0;
    int j = Integer.MAX_VALUE;
    int k = Integer.MAX_VALUE;
    boolean bool1 = false;
    if (hasLimit()) {
      Integer integer = (Integer)this.limitCondition.getLeftNode().getValue(paramSession);
      if (integer == null || integer.intValue() < 0)
        throw Error.error(3453); 
      i = integer.intValue();
      bool1 = (i != 0) ? true : false;
      if (this.limitCondition.getRightNode() != null) {
        integer = (Integer)this.limitCondition.getRightNode().getValue(paramSession);
        if (integer == null || integer.intValue() < 0 || (this.strictLimit && integer.intValue() == 0))
          throw Error.error(3452); 
        if (integer.intValue() == 0 && !this.zeroLimit) {
          j = Integer.MAX_VALUE;
        } else {
          j = integer.intValue();
          bool1 = true;
        } 
      } 
    } 
    if (paramInt != 0) {
      if (paramInt < j)
        j = paramInt; 
      bool1 = true;
    } 
    boolean bool2 = false;
    if (paramQueryExpression instanceof QuerySpecification) {
      QuerySpecification querySpecification = (QuerySpecification)paramQueryExpression;
      if (!querySpecification.isDistinctSelect && !querySpecification.isGrouped)
        bool2 = true; 
      if (querySpecification.isSimpleDistinct)
        bool2 = true; 
    } 
    if (bool1) {
      if (bool2 && (!hasOrder() || this.skipSort) && (!hasLimit() || this.skipFullResult) && k - i > j)
        k = i + j; 
      return new int[] { i, j, k };
    } 
    return defaultLimits;
  }
  
  public void setIndex(Session paramSession, TableBase paramTableBase) {
    this.index = getNewIndex(paramSession, paramTableBase);
  }
  
  public Index getNewIndex(Session paramSession, TableBase paramTableBase) {
    if (hasOrder()) {
      Index index = paramTableBase.createAndAddIndexStructure(paramSession, null, this.sortOrder, this.sortDescending, this.sortNullsLast, false, false, false);
      if (this.collations != null)
        for (byte b = 0; b < this.columnCount; b++) {
          if (this.collations[b] != null) {
            Type type = index.getColumnTypes()[b];
            type = Type.getType(type.typeCode, type.getCharacterSet(), this.collations[b], type.precision, type.scale);
            index.getColumnTypes()[b] = type;
          } 
        }  
      return index;
    } 
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SortAndSlice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */