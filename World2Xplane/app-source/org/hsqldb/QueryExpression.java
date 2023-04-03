package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataTable;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class QueryExpression implements RangeGroup {
  public static final int NOUNION = 0;
  
  public static final int UNION = 1;
  
  public static final int UNION_ALL = 2;
  
  public static final int INTERSECT = 3;
  
  public static final int INTERSECT_ALL = 4;
  
  public static final int EXCEPT_ALL = 5;
  
  public static final int EXCEPT = 6;
  
  public static final int UNION_TERM = 7;
  
  int columnCount;
  
  private QueryExpression leftQueryExpression;
  
  private QueryExpression rightQueryExpression;
  
  SortAndSlice sortAndSlice;
  
  private int unionType;
  
  private boolean unionCorresponding;
  
  private OrderedHashSet unionCorrespondingColumns;
  
  int[] unionColumnMap;
  
  Type[] unionColumnTypes;
  
  boolean isFullOrder;
  
  HsqlList unresolvedExpressions;
  
  boolean isReferencesResolved;
  
  boolean isPartOneResolved;
  
  boolean isPartTwoResolved;
  
  boolean isResolved;
  
  int persistenceScope = 21;
  
  ResultMetaData resultMetaData;
  
  boolean[] accessibleColumns;
  
  View view;
  
  boolean isBaseMergeable;
  
  boolean isMergeable;
  
  boolean isUpdatable;
  
  boolean isInsertable;
  
  boolean isCheckable;
  
  boolean isTopLevel;
  
  boolean isRecursive;
  
  boolean isSingleRow;
  
  boolean acceptsSequences;
  
  boolean isCorrelated;
  
  boolean isTable;
  
  boolean isValueList;
  
  TableDerived recursiveTable;
  
  public TableBase resultTable;
  
  public Index mainIndex;
  
  public Index fullIndex;
  
  public Index orderIndex;
  
  public Index idIndex;
  
  ParserDQL.CompileContext compileContext;
  
  QueryExpression(ParserDQL.CompileContext paramCompileContext) {
    this.compileContext = paramCompileContext;
    this.sortAndSlice = SortAndSlice.noSort;
  }
  
  public QueryExpression(ParserDQL.CompileContext paramCompileContext, QueryExpression paramQueryExpression) {
    this(paramCompileContext);
    this.sortAndSlice = SortAndSlice.noSort;
    this.leftQueryExpression = paramQueryExpression;
  }
  
  public RangeVariable[] getRangeVariables() {
    return RangeVariable.emptyArray;
  }
  
  public void setCorrelated() {
    this.isCorrelated = true;
  }
  
  public boolean isVariable() {
    return false;
  }
  
  public void setSingleRow() {
    this.isSingleRow = true;
  }
  
  public boolean isRecursive() {
    return this.isRecursive;
  }
  
  void addUnion(QueryExpression paramQueryExpression, int paramInt) {
    this.sortAndSlice = SortAndSlice.noSort;
    this.rightQueryExpression = paramQueryExpression;
    this.unionType = paramInt;
    setFullOrder();
  }
  
  void addSortAndSlice(SortAndSlice paramSortAndSlice) {
    this.sortAndSlice = paramSortAndSlice;
    paramSortAndSlice.sortUnion = true;
  }
  
  public void setUnionCorresoponding() {
    this.unionCorresponding = true;
  }
  
  public void setUnionCorrespondingColumns(OrderedHashSet paramOrderedHashSet) {
    this.unionCorrespondingColumns = paramOrderedHashSet;
  }
  
  public void setFullOrder() {
    this.isFullOrder = true;
    if (this.leftQueryExpression != null)
      this.leftQueryExpression.setFullOrder(); 
    if (this.rightQueryExpression != null)
      this.rightQueryExpression.setFullOrder(); 
  }
  
  public void resolve(Session paramSession) {
    resolveReferences(paramSession, RangeGroup.emptyArray);
    ExpressionColumn.checkColumnsResolved(this.unresolvedExpressions);
    resolveTypes(paramSession);
  }
  
  public void resolve(Session paramSession, RangeGroup[] paramArrayOfRangeGroup, Type[] paramArrayOfType) {
    resolveReferences(paramSession, paramArrayOfRangeGroup);
    if (this.unresolvedExpressions != null)
      for (byte b = 0; b < this.unresolvedExpressions.size(); b++) {
        Expression expression = (Expression)this.unresolvedExpressions.get(b);
        HsqlList hsqlList = expression.resolveColumnReferences(paramSession, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
        ExpressionColumn.checkColumnsResolved(hsqlList);
      }  
    resolveTypesPartOne(paramSession);
    if (paramArrayOfType != null)
      for (byte b = 0; b < this.unionColumnTypes.length && b < paramArrayOfType.length; b++) {
        if (this.unionColumnTypes[b] == null)
          this.unionColumnTypes[b] = paramArrayOfType[b]; 
      }  
    resolveTypesPartTwo(paramSession);
    resolveTypesPartThree(paramSession);
  }
  
  public void resolveReferences(Session paramSession, RangeGroup[] paramArrayOfRangeGroup) {
    if (this.isReferencesResolved)
      return; 
    this.leftQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
    this.rightQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
    addUnresolvedExpressions(this.leftQueryExpression.unresolvedExpressions);
    addUnresolvedExpressions(this.rightQueryExpression.unresolvedExpressions);
    if (this.leftQueryExpression.isCorrelated || this.rightQueryExpression.isCorrelated)
      setCorrelated(); 
    if (!this.unionCorresponding) {
      this.columnCount = this.leftQueryExpression.getColumnCount();
      int i = this.rightQueryExpression.getColumnCount();
      if (this.columnCount != i)
        throw Error.error(5594); 
      this.unionColumnTypes = new Type[this.columnCount];
      this.rightQueryExpression.unionColumnMap = new int[this.columnCount];
      ArrayUtil.fillSequence(this.leftQueryExpression.unionColumnMap);
      resolveColumnRefernecesInUnionOrderBy();
      this.accessibleColumns = this.leftQueryExpression.accessibleColumns;
      this.isReferencesResolved = true;
      return;
    } 
    String[] arrayOfString1 = this.leftQueryExpression.getColumnNames();
    String[] arrayOfString2 = this.rightQueryExpression.getColumnNames();
    if (this.unionCorrespondingColumns == null) {
      this.unionCorrespondingColumns = new OrderedHashSet();
      OrderedIntHashSet orderedIntHashSet1 = new OrderedIntHashSet();
      OrderedIntHashSet orderedIntHashSet2 = new OrderedIntHashSet();
      for (byte b = 0; b < arrayOfString1.length; b++) {
        String str = arrayOfString1[b];
        int i = ArrayUtil.find((Object[])arrayOfString2, str);
        if (str.length() > 0 && i != -1) {
          if (!this.leftQueryExpression.accessibleColumns[b])
            throw Error.error(5578); 
          if (!this.rightQueryExpression.accessibleColumns[i])
            throw Error.error(5578); 
          orderedIntHashSet1.add(b);
          orderedIntHashSet2.add(i);
          this.unionCorrespondingColumns.add(str);
        } 
      } 
      if (this.unionCorrespondingColumns.isEmpty())
        throw Error.error(5578); 
      this.leftQueryExpression.unionColumnMap = orderedIntHashSet1.toArray();
      this.rightQueryExpression.unionColumnMap = orderedIntHashSet2.toArray();
    } else {
      this.leftQueryExpression.unionColumnMap = new int[this.unionCorrespondingColumns.size()];
      this.rightQueryExpression.unionColumnMap = new int[this.unionCorrespondingColumns.size()];
      for (byte b = 0; b < this.unionCorrespondingColumns.size(); b++) {
        String str = (String)this.unionCorrespondingColumns.get(b);
        int i = ArrayUtil.find((Object[])arrayOfString1, str);
        if (i == -1)
          throw Error.error(5501); 
        if (!this.leftQueryExpression.accessibleColumns[i])
          throw Error.error(5578); 
        this.leftQueryExpression.unionColumnMap[b] = i;
        i = ArrayUtil.find((Object[])arrayOfString2, str);
        if (i == -1)
          throw Error.error(5501); 
        if (!this.rightQueryExpression.accessibleColumns[i])
          throw Error.error(5578); 
        this.rightQueryExpression.unionColumnMap[b] = i;
      } 
    } 
    this.columnCount = this.unionCorrespondingColumns.size();
    this.unionColumnTypes = new Type[this.columnCount];
    resolveColumnRefernecesInUnionOrderBy();
    this.accessibleColumns = new boolean[this.columnCount];
    ArrayUtil.fillArray(this.accessibleColumns, true);
    this.isReferencesResolved = true;
  }
  
  void resolveColumnRefernecesInUnionOrderBy() {
    int i = this.sortAndSlice.getOrderLength();
    if (i == 0)
      return; 
    String[] arrayOfString = getColumnNames();
    for (byte b = 0; b < i; b++) {
      Expression expression1 = (Expression)this.sortAndSlice.exprList.get(b);
      Expression expression2 = expression1.getLeftNode();
      if (expression2.getType() == 1) {
        if ((expression2.getDataType()).typeCode == 4) {
          int j = ((Integer)expression2.getValue(null)).intValue();
          if (0 < j && j <= arrayOfString.length) {
            (expression1.getLeftNode()).queryTableColumnIndex = j - 1;
          } else {
            throw Error.error(5576);
          } 
        } else {
          throw Error.error(5576);
        } 
      } else if (expression2.getType() == 2) {
        int j = ArrayUtil.find((Object[])arrayOfString, expression2.getColumnName());
        if (j >= 0) {
          (expression1.getLeftNode()).queryTableColumnIndex = j;
        } else {
          throw Error.error(5576);
        } 
      } else {
        throw Error.error(5576);
      } 
    } 
    this.sortAndSlice.prepare((QuerySpecification)null);
  }
  
  private void addUnresolvedExpressions(HsqlList paramHsqlList) {
    if (paramHsqlList == null)
      return; 
    if (this.unresolvedExpressions == null)
      this.unresolvedExpressions = (HsqlList)new ArrayListIdentity(); 
    this.unresolvedExpressions.addAll((Collection)paramHsqlList);
  }
  
  public void resolveTypes(Session paramSession) {
    if (this.isResolved)
      return; 
    resolveTypesPartOne(paramSession);
    resolveTypesPartTwo(paramSession);
    resolveTypesPartThree(paramSession);
  }
  
  void resolveTypesPartOne(Session paramSession) {
    if (this.isPartOneResolved)
      return; 
    ArrayUtil.projectRowReverse((Object[])this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    this.leftQueryExpression.resolveTypesPartOne(paramSession);
    ArrayUtil.projectRow((Object[])this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    ArrayUtil.projectRowReverse((Object[])this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    this.rightQueryExpression.resolveTypesPartOne(paramSession);
    ArrayUtil.projectRow((Object[])this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    this.isPartOneResolved = true;
  }
  
  void resolveTypesPartTwo(Session paramSession) {
    if (this.isPartTwoResolved)
      return; 
    ArrayUtil.projectRowReverse((Object[])this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    this.leftQueryExpression.resolveTypesPartTwo(paramSession);
    this.leftQueryExpression.resolveTypesPartThree(paramSession);
    ArrayUtil.projectRowReverse((Object[])this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, (Object[])this.unionColumnTypes);
    this.rightQueryExpression.resolveTypesPartTwo(paramSession);
    this.rightQueryExpression.resolveTypesPartThree(paramSession);
    ResultMetaData resultMetaData1 = this.leftQueryExpression.getMetaData();
    ResultMetaData resultMetaData2 = this.rightQueryExpression.getMetaData();
    for (byte b = 0; b < this.leftQueryExpression.unionColumnMap.length; b++) {
      int i = this.leftQueryExpression.unionColumnMap[b];
      int j = this.rightQueryExpression.unionColumnMap[b];
      ColumnBase columnBase = resultMetaData1.columns[i];
      byte b1 = resultMetaData1.columns[i].getNullability();
      byte b2 = resultMetaData2.columns[j].getNullability();
      if (columnBase instanceof ColumnSchema && resultMetaData2.columns[j] instanceof ColumnBase) {
        columnBase = new ColumnBase();
        columnBase.setType(this.leftQueryExpression.unionColumnTypes[b]);
        columnBase.setNullability(b1);
        resultMetaData1.columns[i] = columnBase;
      } 
      if (b2 == 1 || (b2 == 2 && b1 == 0)) {
        if (columnBase instanceof ColumnSchema) {
          columnBase = new ColumnBase();
          columnBase.setType(this.leftQueryExpression.unionColumnTypes[b]);
          resultMetaData1.columns[i] = columnBase;
        } 
        columnBase.setNullability(b2);
      } 
    } 
    if (this.unionCorresponding || this.isRecursive) {
      this.resultMetaData = this.leftQueryExpression.getMetaData().getNewMetaData(this.leftQueryExpression.unionColumnMap);
      createTable(paramSession);
    } 
    if (this.sortAndSlice.hasOrder())
      for (QueryExpression queryExpression = this;; queryExpression = queryExpression.leftQueryExpression) {
        if (queryExpression.leftQueryExpression == null || queryExpression.unionCorresponding) {
          this.sortAndSlice.setIndex(paramSession, queryExpression.resultTable);
          break;
        } 
      }  
    this.isPartTwoResolved = true;
  }
  
  void resolveTypesPartThree(Session paramSession) {
    this.compileContext = null;
    this.isResolved = true;
  }
  
  public Object[] getValues(Session paramSession) {
    Result result = getResult(paramSession, 2);
    int i = result.getNavigator().getSize();
    if (i == 0)
      return new Object[result.metaData.getColumnCount()]; 
    if (i == 1)
      return result.getSingleRowData(); 
    throw Error.error(3201);
  }
  
  public void addExtraConditions(Expression paramExpression) {}
  
  public Object[] getSingleRowValues(Session paramSession) {
    Result result = getResult(paramSession, 2);
    int i = result.getNavigator().getSize();
    if (i == 0)
      return null; 
    if (i == 1)
      return result.getSingleRowData(); 
    throw Error.error(3201);
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject = getValues(paramSession);
    return arrayOfObject[0];
  }
  
  Result getResult(Session paramSession, int paramInt) {
    RowSetNavigatorDataTable rowSetNavigatorDataTable1;
    RowSetNavigatorDataTable rowSetNavigatorDataTable2;
    if (this.isRecursive)
      return getResultRecursive(paramSession); 
    boolean bool = (this.unionType == 2) ? paramInt : false;
    Result result1 = this.leftQueryExpression.getResult(paramSession, bool);
    RowSetNavigatorData rowSetNavigatorData1 = (RowSetNavigatorData)result1.getNavigator();
    Result result2 = this.rightQueryExpression.getResult(paramSession, bool);
    RowSetNavigatorData rowSetNavigatorData2 = (RowSetNavigatorData)result2.getNavigator();
    if (this.unionCorresponding) {
      RowSetNavigatorDataTable rowSetNavigatorDataTable;
      boolean bool1 = (paramSession.resultMaxMemoryRows == 0 || (rowSetNavigatorData1.getSize() < paramSession.resultMaxMemoryRows && rowSetNavigatorData2.getSize() < paramSession.resultMaxMemoryRows)) ? true : false;
      if (bool1) {
        RowSetNavigatorData rowSetNavigatorData = new RowSetNavigatorData(paramSession, this);
      } else {
        rowSetNavigatorDataTable = new RowSetNavigatorDataTable(paramSession, this);
      } 
      rowSetNavigatorDataTable.copy((RowIterator)rowSetNavigatorData1, this.leftQueryExpression.unionColumnMap);
      rowSetNavigatorData1.release();
      rowSetNavigatorDataTable1 = rowSetNavigatorDataTable;
      result1.setNavigator((RowSetNavigator)rowSetNavigatorDataTable1);
      result1.metaData = getMetaData();
      if (bool1) {
        RowSetNavigatorData rowSetNavigatorData = new RowSetNavigatorData(paramSession, this);
      } else {
        rowSetNavigatorDataTable = new RowSetNavigatorDataTable(paramSession, this);
      } 
      rowSetNavigatorDataTable.copy((RowIterator)rowSetNavigatorData2, this.rightQueryExpression.unionColumnMap);
      rowSetNavigatorData2.release();
      rowSetNavigatorDataTable2 = rowSetNavigatorDataTable;
    } 
    switch (this.unionType) {
      case 1:
        rowSetNavigatorDataTable1.union(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      case 2:
        rowSetNavigatorDataTable1.unionAll(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      case 3:
        rowSetNavigatorDataTable1.intersect(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      case 4:
        rowSetNavigatorDataTable1.intersectAll(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      case 6:
        rowSetNavigatorDataTable1.except(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      case 5:
        rowSetNavigatorDataTable1.exceptAll(paramSession, (RowSetNavigatorData)rowSetNavigatorDataTable2);
        break;
      default:
        throw Error.runtimeError(201, "QueryExpression");
    } 
    if (this.sortAndSlice.hasOrder())
      rowSetNavigatorDataTable1.sortOrderUnion(paramSession, this.sortAndSlice); 
    if (this.sortAndSlice.hasLimit()) {
      int[] arrayOfInt = this.sortAndSlice.getLimits(paramSession, this, paramInt);
      rowSetNavigatorDataTable1.trim(arrayOfInt[0], arrayOfInt[1]);
    } 
    rowSetNavigatorDataTable1.reset();
    return result1;
  }
  
  Result getResultRecursive(Session paramSession) {
    RowSetNavigatorData rowSetNavigatorData = new RowSetNavigatorData(paramSession, this);
    Result result = Result.newResult((RowSetNavigator)rowSetNavigatorData);
    this.recursiveTable.materialise(paramSession);
    RowIterator rowIterator = this.recursiveTable.rowIterator(paramSession);
    rowSetNavigatorData.copy(rowIterator, this.unionColumnMap);
    result.metaData = this.resultMetaData;
    for (byte b = 0;; b++) {
      Result result1 = this.rightQueryExpression.getResult(paramSession, 0);
      RowSetNavigatorData rowSetNavigatorData1 = (RowSetNavigatorData)result1.getNavigator();
      if (rowSetNavigatorData1.isEmpty()) {
        this.recursiveTable.clearAllData(paramSession);
        rowSetNavigatorData.reset();
        return result;
      } 
      switch (this.unionType) {
        case 1:
          rowSetNavigatorData.union(paramSession, rowSetNavigatorData1);
          break;
        case 2:
          rowSetNavigatorData.unionAll(paramSession, rowSetNavigatorData1);
          break;
        default:
          throw Error.runtimeError(201, "QueryExpression");
      } 
      this.recursiveTable.clearAllData(paramSession);
      rowSetNavigatorData1.reset();
      this.recursiveTable.insertIntoTable(paramSession, result1);
      if (b > 'Ā')
        throw Error.error(458); 
    } 
  }
  
  public OrderedHashSet getSubqueries() {
    null = this.leftQueryExpression.getSubqueries();
    return OrderedHashSet.addAll(null, this.rightQueryExpression.getSubqueries());
  }
  
  public boolean isSingleColumn() {
    return this.leftQueryExpression.isSingleColumn();
  }
  
  public ResultMetaData getMetaData() {
    return (this.resultMetaData != null) ? this.resultMetaData : this.leftQueryExpression.getMetaData();
  }
  
  public QuerySpecification getMainSelect() {
    return (this.leftQueryExpression == null) ? (QuerySpecification)this : this.leftQueryExpression.getMainSelect();
  }
  
  public String describe(Session paramSession, int paramInt) {
    String str;
    StringBuffer stringBuffer2 = new StringBuffer(paramInt);
    for (byte b = 0; b < paramInt; b++)
      stringBuffer2.append(' '); 
    StringBuffer stringBuffer1 = new StringBuffer();
    switch (this.unionType) {
      case 1:
        str = "UNION";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
      case 2:
        str = "UNION ALL";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
      case 3:
        str = "INTERSECT";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
      case 4:
        str = "INTERSECT ALL";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
      case 6:
        str = "EXCEPT";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
      case 5:
        str = "EXCEPT ALL";
        stringBuffer1.append(stringBuffer2).append(str).append("\n");
        stringBuffer1.append(stringBuffer2).append("Left Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        stringBuffer1.append(stringBuffer2).append("Right Query=[\n");
        stringBuffer1.append(stringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
        stringBuffer1.append(stringBuffer2).append("]\n");
        return stringBuffer1.toString();
    } 
    throw Error.runtimeError(201, "QueryExpression");
  }
  
  public HsqlList getUnresolvedExpressions() {
    return this.unresolvedExpressions;
  }
  
  public boolean areColumnsResolved() {
    if (this.unresolvedExpressions == null || this.unresolvedExpressions.isEmpty())
      return true; 
    for (byte b = 0; b < this.unresolvedExpressions.size(); b++) {
      Expression expression = (Expression)this.unresolvedExpressions.get(b);
      if (expression.getRangeVariable() == null)
        return false; 
      if ((expression.getRangeVariable()).rangeType == 1)
        return false; 
    } 
    return true;
  }
  
  String[] getColumnNames() {
    if (this.unionCorrespondingColumns == null)
      return this.leftQueryExpression.getColumnNames(); 
    String[] arrayOfString = new String[this.unionCorrespondingColumns.size()];
    this.unionCorrespondingColumns.toArray((Object[])arrayOfString);
    return arrayOfString;
  }
  
  public Type[] getColumnTypes() {
    return this.unionColumnTypes;
  }
  
  public int getColumnCount() {
    if (this.unionCorrespondingColumns == null) {
      int i = this.leftQueryExpression.getColumnCount();
      int j = this.rightQueryExpression.getColumnCount();
      if (i != j)
        throw Error.error(5594); 
      return i;
    } 
    return this.unionCorrespondingColumns.size();
  }
  
  public OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2) {
    paramOrderedHashSet = this.leftQueryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    if (this.rightQueryExpression != null)
      paramOrderedHashSet = this.rightQueryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet) {
    paramOrderedHashSet = this.leftQueryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet);
    if (this.rightQueryExpression != null)
      paramOrderedHashSet = this.rightQueryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(OrderedHashSet paramOrderedHashSet) {
    paramOrderedHashSet = this.leftQueryExpression.collectRangeVariables(paramOrderedHashSet);
    if (this.rightQueryExpression != null)
      paramOrderedHashSet = this.rightQueryExpression.collectRangeVariables(paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  public void collectObjectNames(Set paramSet) {
    this.leftQueryExpression.collectObjectNames(paramSet);
    if (this.rightQueryExpression != null)
      this.rightQueryExpression.collectObjectNames(paramSet); 
  }
  
  public HashMappedList getColumns() {
    getResultTable();
    return ((TableDerived)getResultTable()).columnList;
  }
  
  public void setView(View paramView) {
    this.view = paramView;
    this.isUpdatable = true;
    this.acceptsSequences = true;
    this.isTopLevel = true;
  }
  
  public void setTableColumnNames(HashMappedList paramHashMappedList) {
    if (this.resultTable != null) {
      ((TableDerived)this.resultTable).columnList = paramHashMappedList;
      return;
    } 
    this.leftQueryExpression.setTableColumnNames(paramHashMappedList);
  }
  
  void createTable(Session paramSession) {
    createResultTable(paramSession);
    this.mainIndex = this.resultTable.getPrimaryIndex();
    if (this.sortAndSlice.hasOrder())
      this.orderIndex = this.sortAndSlice.getNewIndex(paramSession, this.resultTable); 
    int[] arrayOfInt = new int[this.columnCount];
    ArrayUtil.fillSequence(arrayOfInt);
    this.fullIndex = this.resultTable.createAndAddIndexStructure(paramSession, null, arrayOfInt, null, null, false, false, false);
    this.resultTable.fullIndex = this.fullIndex;
  }
  
  void createResultTable(Session paramSession) {
    HsqlNameManager.HsqlName hsqlName = paramSession.database.nameManager.getSubqueryTableName();
    byte b = (this.persistenceScope == 21) ? 2 : 9;
    HashMappedList hashMappedList = this.leftQueryExpression.getUnionColumns();
    try {
      this.resultTable = new TableDerived(paramSession.database, hsqlName, b, this.unionColumnTypes, hashMappedList, ValuePool.emptyIntArray);
    } catch (Exception exception) {}
  }
  
  public void setColumnsDefined() {
    if (this.leftQueryExpression != null)
      this.leftQueryExpression.setColumnsDefined(); 
  }
  
  public void setReturningResult() {
    if ((this.compileContext.getSequences()).length > 0)
      throw Error.error(5598); 
    this.isTopLevel = true;
    setReturningResultSet();
  }
  
  void setReturningResultSet() {
    if (this.unionCorresponding) {
      this.persistenceScope = 23;
      return;
    } 
    this.leftQueryExpression.setReturningResultSet();
  }
  
  private HashMappedList getUnionColumns() {
    if (this.unionCorresponding || this.leftQueryExpression == null) {
      HashMappedList hashMappedList1 = ((TableDerived)this.resultTable).columnList;
      HashMappedList hashMappedList2 = new HashMappedList();
      for (byte b = 0; b < this.unionColumnMap.length; b++) {
        ColumnSchema columnSchema = (ColumnSchema)hashMappedList1.get(this.unionColumnMap[b]);
        hashMappedList2.add((columnSchema.getName()).name, columnSchema);
      } 
      return hashMappedList2;
    } 
    return this.leftQueryExpression.getUnionColumns();
  }
  
  public HsqlNameManager.HsqlName[] getResultColumnNames() {
    if (this.resultTable == null)
      return this.leftQueryExpression.getResultColumnNames(); 
    HashMappedList hashMappedList = ((TableDerived)this.resultTable).columnList;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[hashMappedList.size()];
    for (byte b = 0; b < arrayOfHsqlName.length; b++)
      arrayOfHsqlName[b] = ((ColumnSchema)hashMappedList.get(b)).getName(); 
    return arrayOfHsqlName;
  }
  
  public TableBase getResultTable() {
    return (this.resultTable != null) ? this.resultTable : ((this.leftQueryExpression != null) ? this.leftQueryExpression.getResultTable() : null);
  }
  
  public Table getBaseTable() {
    return null;
  }
  
  public boolean isUpdatable() {
    return this.isUpdatable;
  }
  
  public boolean isInsertable() {
    return this.isInsertable;
  }
  
  public int[] getBaseTableColumnMap() {
    return null;
  }
  
  public Expression getCheckCondition() {
    return null;
  }
  
  public boolean hasReference(RangeVariable paramRangeVariable) {
    return this.leftQueryExpression.hasReference(paramRangeVariable) ? true : (this.rightQueryExpression.hasReference(paramRangeVariable));
  }
  
  void getBaseTableNames(OrderedHashSet paramOrderedHashSet) {
    this.leftQueryExpression.getBaseTableNames(paramOrderedHashSet);
    this.rightQueryExpression.getBaseTableNames(paramOrderedHashSet);
  }
  
  boolean isEquivalent(QueryExpression paramQueryExpression) {
    return (this.leftQueryExpression.isEquivalent(paramQueryExpression.leftQueryExpression) && this.unionType == paramQueryExpression.unionType && ((this.rightQueryExpression == null) ? (paramQueryExpression.rightQueryExpression == null) : this.rightQueryExpression.isEquivalent(paramQueryExpression.rightQueryExpression)));
  }
  
  public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {
    this.leftQueryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    this.rightQueryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
  }
  
  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    this.leftQueryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
    this.rightQueryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\QueryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */