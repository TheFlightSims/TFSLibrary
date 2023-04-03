package org.hsqldb;

import org.hsqldb.index.Index;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;

public class RangeVariableJoined extends RangeVariable {
  RangeVariable[] rangeArray;
  
  public RangeVariableJoined(Table paramTable, HsqlNameManager.SimpleName paramSimpleName, OrderedHashSet paramOrderedHashSet, HsqlNameManager.SimpleName[] paramArrayOfSimpleName, ParserDQL.CompileContext paramCompileContext) {
    super(paramTable, paramSimpleName, paramOrderedHashSet, paramArrayOfSimpleName, paramCompileContext);
    setParameters();
  }
  
  private void setParameters() {
    QuerySpecification querySpecification = (QuerySpecification)this.rangeTable.getQueryExpression();
    this.rangeArray = querySpecification.rangeVariables;
    byte b = 0;
    if (b < this.rangeArray.length) {
      if ((this.rangeArray[b]).isLeftJoin)
        this.hasLeftJoin = true; 
      if ((this.rangeArray[b]).isRightJoin)
        this.hasRightJoin = true; 
      if ((this.rangeArray[b]).isLateral)
        this.hasLateral = true; 
    } 
  }
  
  public RangeVariable[] getBaseRangeVariables() {
    return this.rangeArray;
  }
  
  public void setRangeTableVariables() {
    super.setRangeTableVariables();
  }
  
  public void setJoinType(boolean paramBoolean1, boolean paramBoolean2) {
    super.setJoinType(paramBoolean1, paramBoolean2);
  }
  
  public void addNamedJoinColumns(OrderedHashSet paramOrderedHashSet) {
    super.addNamedJoinColumns(paramOrderedHashSet);
  }
  
  public void addColumn(int paramInt) {
    super.addColumn(paramInt);
  }
  
  public void addAllColumns() {
    super.addAllColumns();
  }
  
  public void addNamedJoinColumnExpression(String paramString, Expression paramExpression) {
    super.addNamedJoinColumnExpression(paramString, paramExpression);
  }
  
  public ExpressionColumn getColumnExpression(String paramString) {
    ExpressionColumn expressionColumn = super.getColumnExpression(paramString);
    if (expressionColumn == null)
      expressionColumn = this.rangeArray[0].getColumnExpression(paramString); 
    return expressionColumn;
  }
  
  public Table getTable() {
    return super.getTable();
  }
  
  public boolean hasSingleIndexCondition() {
    return super.hasSingleIndexCondition();
  }
  
  public boolean setDistinctColumnsOnIndex(int[] paramArrayOfint) {
    return super.setDistinctColumnsOnIndex(paramArrayOfint);
  }
  
  public Index getSortIndex() {
    return super.getSortIndex();
  }
  
  public boolean setSortIndex(Index paramIndex, boolean paramBoolean) {
    return super.setSortIndex(paramIndex, paramBoolean);
  }
  
  public boolean reverseOrder() {
    return super.reverseOrder();
  }
  
  public OrderedHashSet getColumnNames() {
    return super.getColumnNames();
  }
  
  public OrderedHashSet getUniqueColumnNameSet() {
    return super.getUniqueColumnNameSet();
  }
  
  public int findColumn(String paramString1, String paramString2, String paramString3) {
    if (this.tableAlias != null)
      return super.findColumn(paramString1, paramString2, paramString3); 
    boolean bool = ((this.rangeArray[0]).namedJoinColumnExpressions != null) ? true : false;
    int i = 0;
    if (bool) {
      i = (this.rangeArray[0]).namedJoinColumnExpressions.size();
      if ((this.rangeArray[0]).namedJoinColumnExpressions.containsKey(paramString3))
        return (paramString2 != null) ? -1 : super.findColumn(paramString1, paramString2, paramString3); 
    } 
    for (byte b = 0; b < this.rangeArray.length; b++) {
      RangeVariable rangeVariable = this.rangeArray[b];
      int j = rangeVariable.findColumn(paramString1, paramString2, paramString3);
      if (j > -1) {
        if (!bool)
          return i + j; 
        for (byte b1 = 0; b1 < j; b1++) {
          ColumnSchema columnSchema = rangeVariable.rangeTable.getColumn(b1);
          if (!rangeVariable.namedJoinColumnExpressions.containsKey(columnSchema.getNameString()))
            i++; 
        } 
        return i;
      } 
      i += rangeVariable.rangeTable.getColumnCount();
      if (bool)
        i -= rangeVariable.namedJoinColumnExpressions.size(); 
    } 
    return -1;
  }
  
  public HsqlNameManager.SimpleName getColumnAlias(int paramInt) {
    return super.getColumnAlias(paramInt);
  }
  
  public boolean hasColumnAlias() {
    return super.hasColumnAlias();
  }
  
  public HsqlNameManager.SimpleName getTableAlias() {
    return super.getTableAlias();
  }
  
  public RangeVariable getRangeForTableName(String paramString) {
    if (this.tableAlias != null)
      return super.getRangeForTableName(paramString); 
    for (byte b = 0; b < this.rangeArray.length; b++) {
      RangeVariable rangeVariable = this.rangeArray[b].getRangeForTableName(paramString);
      if (rangeVariable != null)
        return rangeVariable; 
    } 
    return null;
  }
  
  public void addTableColumns(HsqlArrayList paramHsqlArrayList) {
    super.addTableColumns(paramHsqlArrayList);
  }
  
  public int addTableColumns(HsqlArrayList paramHsqlArrayList, int paramInt, HashSet paramHashSet) {
    return super.addTableColumns(paramHsqlArrayList, paramInt, paramHashSet);
  }
  
  public void addTableColumns(RangeVariable paramRangeVariable, Expression paramExpression, HashSet paramHashSet) {
    int i = getFirstColumnIndex(paramRangeVariable);
    addTableColumns(paramExpression, i, paramRangeVariable.rangeTable.getColumnCount(), paramHashSet);
  }
  
  protected int getFirstColumnIndex(RangeVariable paramRangeVariable) {
    if (paramRangeVariable == this)
      return 0; 
    int i = 0;
    for (byte b = 0; b < this.rangeArray.length; b++) {
      int j = this.rangeArray[b].getFirstColumnIndex(paramRangeVariable);
      if (j == -1) {
        i += (this.rangeArray[b]).rangeTable.getColumnCount();
      } else {
        return i + j;
      } 
    } 
    return -1;
  }
  
  public void setForCheckConstraint() {
    super.setForCheckConstraint();
  }
  
  public Expression getJoinCondition() {
    return super.getJoinCondition();
  }
  
  public void addJoinCondition(Expression paramExpression) {
    super.addJoinCondition(paramExpression);
  }
  
  public void resetConditions() {
    super.resetConditions();
  }
  
  public void replaceColumnReference(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {}
  
  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    super.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
  }
  
  public void resolveRangeTable(Session paramSession, RangeGroup paramRangeGroup, RangeGroup[] paramArrayOfRangeGroup) {
    super.resolveRangeTable(paramSession, paramRangeGroup, paramArrayOfRangeGroup);
  }
  
  public String describe(Session paramSession, int paramInt) {
    RangeVariable.RangeVariableConditions[] arrayOfRangeVariableConditions = this.joinConditions;
    String str1 = ValuePool.spaceString.substring(0, paramInt);
    StringBuffer stringBuffer = new StringBuffer();
    String str2 = "INNER";
    if (this.isLeftJoin) {
      str2 = "LEFT OUTER";
      if (this.isRightJoin)
        str2 = "FULL"; 
    } else if (this.isRightJoin) {
      str2 = "RIGHT OUTER";
    } 
    stringBuffer.append(str1).append("join type=").append(str2).append("\n");
    stringBuffer.append(str1).append("table=").append((this.rangeTable.getName()).name).append("\n");
    if (this.tableAlias != null)
      stringBuffer.append(str1).append("alias=").append(this.tableAlias.name).append("\n"); 
    boolean bool = !arrayOfRangeVariableConditions[0].hasIndexCondition() ? true : false;
    stringBuffer.append(str1).append("access=").append(bool ? "FULL SCAN" : "INDEX PRED").append("\n");
    for (byte b = 0; b < arrayOfRangeVariableConditions.length; b++) {
      RangeVariable.RangeVariableConditions rangeVariableConditions = this.joinConditions[b];
      if (b > 0) {
        stringBuffer.append(str1).append("OR condition = [");
      } else {
        stringBuffer.append(str1).append("condition = [");
      } 
      stringBuffer.append(rangeVariableConditions.describe(paramSession, paramInt + 2));
      stringBuffer.append(str1).append("]\n");
    } 
    return stringBuffer.toString();
  }
  
  public RangeVariable.RangeIteratorMain getIterator(Session paramSession) {
    return super.getIterator(paramSession);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RangeVariableJoined.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */