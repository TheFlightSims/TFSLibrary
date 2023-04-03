package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Collation;
import org.hsqldb.types.NullType;
import org.hsqldb.types.Type;

public class Expression implements Cloneable {
  public static final int LEFT = 0;
  
  public static final int RIGHT = 1;
  
  public static final int UNARY = 1;
  
  public static final int BINARY = 2;
  
  public static final int TERNARY = 3;
  
  static final Expression[] emptyArray = new Expression[0];
  
  static final Expression EXPR_TRUE = new ExpressionLogical(true);
  
  static final Expression EXPR_FALSE = new ExpressionLogical(false);
  
  static final OrderedIntHashSet aggregateFunctionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet columnExpressionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet subqueryExpressionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet subqueryAggregateExpressionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet functionExpressionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet sequenceExpressionSet = new OrderedIntHashSet();
  
  static final OrderedIntHashSet emptyExpressionSet = new OrderedIntHashSet();
  
  protected int opType;
  
  protected int exprSubType;
  
  HsqlNameManager.SimpleName alias;
  
  private boolean isAggregate;
  
  protected Object valueData;
  
  protected Expression[] nodes;
  
  Type[] nodeDataTypes;
  
  TableDerived table;
  
  boolean isCorrelated;
  
  int columnIndex = -1;
  
  protected Type dataType;
  
  int queryTableColumnIndex = -1;
  
  int parameterIndex = -1;
  
  int rangePosition = -1;
  
  boolean isColumnCondition;
  
  boolean isColumnEqual;
  
  boolean isSingleColumnCondition;
  
  boolean isSingleColumnEqual;
  
  boolean isSingleColumnNull;
  
  boolean isSingleColumnNotNull;
  
  byte nullability = 2;
  
  Collation collation;
  
  Expression(int paramInt) {
    this.opType = paramInt;
    this.nodes = emptyArray;
  }
  
  Expression(int paramInt, TableDerived paramTableDerived) {
    switch (paramInt) {
      case 19:
        this.opType = 19;
        break;
      case 100:
        this.opType = 100;
        break;
      case 23:
        this.opType = 23;
        break;
      case 21:
      case 22:
        this.opType = 22;
        break;
      default:
        throw Error.runtimeError(201, "Expression");
    } 
    this.nodes = emptyArray;
    this.table = paramTableDerived;
  }
  
  Expression(int paramInt, Expression[] paramArrayOfExpression) {
    this(paramInt);
    this.nodes = paramArrayOfExpression;
  }
  
  static String getContextSQL(Expression paramExpression) {
    if (paramExpression == null)
      return null; 
    null = paramExpression.getSQL();
    switch (paramExpression.opType) {
      case 1:
      case 2:
      case 25:
      case 27:
      case 28:
      case 91:
      case 93:
      case 96:
        return null;
    } 
    StringBuffer stringBuffer = new StringBuffer();
    return stringBuffer.append('(').append(null).append(')').toString();
  }
  
  public String getSQL() {
    byte b;
    StringBuffer stringBuffer = new StringBuffer(64);
    switch (this.opType) {
      case 1:
        return (this.valueData == null) ? "NULL" : this.dataType.convertToSQLString(this.valueData);
      case 25:
        stringBuffer.append('(');
        for (b = 0; b < this.nodes.length; b++) {
          if (b > 0)
            stringBuffer.append(','); 
          stringBuffer.append(this.nodes[b].getSQL());
        } 
        stringBuffer.append(')');
        return stringBuffer.toString();
      case 26:
        for (b = 0; b < this.nodes.length; b++) {
          if (b > 0)
            stringBuffer.append(','); 
          stringBuffer.append(this.nodes[b].getSQL());
        } 
        return stringBuffer.toString();
    } 
    switch (this.opType) {
      case 19:
        stringBuffer.append("ARRAY").append('[');
        for (b = 0; b < this.nodes.length; b++) {
          if (b > 0)
            stringBuffer.append(','); 
          stringBuffer.append(this.nodes[b].getSQL());
        } 
        stringBuffer.append(']');
        return stringBuffer.toString();
      case 22:
      case 23:
      case 100:
        stringBuffer.append('(');
        stringBuffer.append(')');
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    byte b;
    for (b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    switch (this.opType) {
      case 1:
        stringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
        stringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
        return stringBuffer.toString();
      case 19:
        stringBuffer.append("ARRAY ");
        return stringBuffer.toString();
      case 100:
        stringBuffer.append("ARRAY SUBQUERY");
        return stringBuffer.toString();
      case 22:
      case 23:
        stringBuffer.append("QUERY ");
        stringBuffer.append(this.table.queryExpression.describe(paramSession, paramInt));
        return stringBuffer.toString();
      case 25:
        stringBuffer.append("ROW = ");
        for (b = 0; b < this.nodes.length; b++) {
          stringBuffer.append(this.nodes[b].describe(paramSession, paramInt + 1));
          stringBuffer.append(' ');
        } 
        break;
      case 26:
        stringBuffer.append("VALUELIST ");
        for (b = 0; b < this.nodes.length; b++) {
          stringBuffer.append(this.nodes[b].describe(paramSession, paramInt + 1));
          stringBuffer.append(' ');
        } 
        break;
    } 
    return stringBuffer.toString();
  }
  
  void setDataType(Session paramSession, Type paramType) {
    if (this.opType == 1)
      this.valueData = paramType.convertToType(paramSession, this.valueData, this.dataType); 
    this.dataType = paramType;
  }
  
  public boolean equals(Expression paramExpression) {
    if (paramExpression == this)
      return true; 
    if (paramExpression == null)
      return false; 
    if (this.opType != paramExpression.opType || this.exprSubType != paramExpression.exprSubType || !equals(this.dataType, paramExpression.dataType))
      return false; 
    switch (this.opType) {
      case 5:
        return (this.columnIndex == paramExpression.columnIndex);
      case 1:
        return equals(this.valueData, paramExpression.valueData);
      case 19:
      case 22:
      case 23:
      case 100:
        return this.table.queryExpression.isEquivalent(paramExpression.table.queryExpression);
    } 
    return equals(this.nodes, paramExpression.nodes);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this) ? true : ((paramObject instanceof Expression) ? equals((Expression)paramObject) : false);
  }
  
  public int hashCode() {
    int i = this.opType + this.exprSubType;
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        i += this.nodes[b].hashCode(); 
    } 
    return i;
  }
  
  static boolean equals(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2) ? true : ((paramObject1 == null) ? false : paramObject1.equals(paramObject2));
  }
  
  static boolean equals(Expression[] paramArrayOfExpression1, Expression[] paramArrayOfExpression2) {
    if (paramArrayOfExpression1 == paramArrayOfExpression2)
      return true; 
    if (paramArrayOfExpression1.length != paramArrayOfExpression2.length)
      return false; 
    int i = paramArrayOfExpression1.length;
    for (byte b = 0; b < i; b++) {
      Expression expression1 = paramArrayOfExpression1[b];
      Expression expression2 = paramArrayOfExpression2[b];
      boolean bool = (expression1 == null) ? ((expression2 == null) ? true : false) : expression1.equals(expression2);
      if (!bool)
        return false; 
    } 
    return true;
  }
  
  boolean isComposedOf(Expression[] paramArrayOfExpression, int paramInt1, int paramInt2, OrderedIntHashSet paramOrderedIntHashSet) {
    QuerySpecification querySpecification;
    OrderedHashSet orderedHashSet;
    int k;
    switch (this.opType) {
      case 1:
      case 6:
      case 7:
      case 8:
        return true;
    } 
    if (paramOrderedIntHashSet.contains(this.opType))
      return true; 
    for (int j = paramInt1; j < paramInt2; j++) {
      if (equals(paramArrayOfExpression[j]))
        return true; 
    } 
    switch (this.opType) {
      case 19:
      case 23:
      case 53:
      case 55:
      case 57:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 100:
        return false;
      case 22:
        if (this.table == null)
          break; 
        if (!(this.table.getQueryExpression() instanceof QuerySpecification))
          return false; 
        querySpecification = (QuerySpecification)this.table.getQueryExpression();
        orderedHashSet = new OrderedHashSet();
        for (k = paramInt1; k < paramInt2; k++) {
          if ((paramArrayOfExpression[k]).opType == 2)
            orderedHashSet.add(paramArrayOfExpression[k]); 
        } 
        return (querySpecification.collectOuterColumnExpressions((OrderedHashSet)null, orderedHashSet) == null);
    } 
    if (this.nodes.length == 0)
      return false; 
    int i = 1;
    for (byte b = 0; b < this.nodes.length; b++)
      i &= (this.nodes[b] == null || this.nodes[b].isComposedOf(paramArrayOfExpression, paramInt1, paramInt2, paramOrderedIntHashSet)) ? 1 : 0; 
    return i;
  }
  
  boolean isComposedOf(OrderedHashSet paramOrderedHashSet, RangeGroup[] paramArrayOfRangeGroup, OrderedIntHashSet paramOrderedIntHashSet) {
    if (this.opType == 1 || this.opType == 8 || this.opType == 7 || this.opType == 6)
      return true; 
    if (paramOrderedIntHashSet.contains(this.opType))
      return true; 
    int i;
    for (i = 0; i < paramOrderedHashSet.size(); i++) {
      if (equals(paramOrderedHashSet.get(i)))
        return true; 
    } 
    if (this.opType == 2)
      for (i = 0; i < paramArrayOfRangeGroup.length; i++) {
        RangeVariable[] arrayOfRangeVariable = paramArrayOfRangeGroup[i].getRangeVariables();
        for (byte b1 = 0; b1 < arrayOfRangeVariable.length; b1++) {
          if (arrayOfRangeVariable[b1] == getRangeVariable())
            return true; 
        } 
      }  
    switch (this.opType) {
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
        return false;
    } 
    if (this.nodes.length == 0)
      return false; 
    i = 1;
    for (byte b = 0; b < this.nodes.length; b++)
      i &= (this.nodes[b] == null || this.nodes[b].isComposedOf(paramOrderedHashSet, paramArrayOfRangeGroup, paramOrderedIntHashSet)) ? 1 : 0; 
    return i;
  }
  
  Expression replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b] = this.nodes[b].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    } 
    if (this.table != null && this.table.queryExpression != null)
      this.table.queryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression); 
    return this;
  }
  
  void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
    } 
    if (this.table != null && this.table.queryExpression != null)
      this.table.queryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2); 
  }
  
  void resetColumnReferences() {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].resetColumnReferences(); 
    } 
  }
  
  void convertToSimpleColumn(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    if (this.opType == 1)
      return; 
    if (this.opType == 5)
      return; 
    int i = paramOrderedHashSet1.getIndex(this);
    if (i != -1) {
      Expression expression = (Expression)paramOrderedHashSet2.get(i);
      this.nodes = emptyArray;
      this.opType = 5;
      this.columnIndex = expression.columnIndex;
      this.rangePosition = expression.rangePosition;
      return;
    } 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].convertToSimpleColumn(paramOrderedHashSet1, paramOrderedHashSet2); 
    } 
    if (this.table != null && this.table.queryExpression != null) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      this.table.queryExpression.collectAllExpressions(orderedHashSet, columnExpressionSet, emptyExpressionSet);
      for (byte b1 = 0; b1 < orderedHashSet.size(); b1++) {
        Expression expression = (Expression)orderedHashSet.get(b1);
        expression.convertToSimpleColumn(paramOrderedHashSet1, paramOrderedHashSet2);
      } 
    } 
  }
  
  boolean isAggregate() {
    return this.isAggregate;
  }
  
  void setAggregate() {
    this.isAggregate = true;
  }
  
  boolean isSelfAggregate() {
    return false;
  }
  
  void setAlias(HsqlNameManager.SimpleName paramSimpleName) {
    this.alias = paramSimpleName;
  }
  
  String getAlias() {
    return (this.alias != null) ? this.alias.name : "";
  }
  
  HsqlNameManager.SimpleName getSimpleName() {
    return this.alias;
  }
  
  public int getType() {
    return this.opType;
  }
  
  Expression getLeftNode() {
    return (this.nodes.length > 0) ? this.nodes[0] : null;
  }
  
  Expression getRightNode() {
    return (this.nodes.length > 1) ? this.nodes[1] : null;
  }
  
  void setLeftNode(Expression paramExpression) {
    this.nodes[0] = paramExpression;
  }
  
  void setRightNode(Expression paramExpression) {
    this.nodes[1] = paramExpression;
  }
  
  void setSubType(int paramInt) {
    this.exprSubType = paramInt;
  }
  
  RangeVariable getRangeVariable() {
    return null;
  }
  
  Expression replaceAliasInOrderBy(Session paramSession, Expression[] paramArrayOfExpression, int paramInt) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b] = this.nodes[b].replaceAliasInOrderBy(paramSession, paramArrayOfExpression, paramInt); 
    } 
    return this;
  }
  
  OrderedHashSet collectRangeVariables(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectRangeVariables(paramOrderedHashSet); 
    } 
    if (this.table != null && this.table.queryExpression != null)
      paramOrderedHashSet = this.table.queryExpression.collectRangeVariables(paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    } 
    if (this.table != null && this.table.queryExpression != null)
      paramOrderedHashSet = this.table.queryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet); 
    return paramOrderedHashSet;
  }
  
  void collectObjectNames(Set paramSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].collectObjectNames(paramSet); 
    } 
    if (this.table != null && this.table.queryExpression != null)
      this.table.queryExpression.collectObjectNames(paramSet); 
  }
  
  boolean hasReference(RangeVariable paramRangeVariable) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null && this.nodes[b].hasReference(paramRangeVariable))
        return true; 
    } 
    return (this.table != null && this.table.queryExpression != null && this.table.queryExpression.hasReference(paramRangeVariable));
  }
  
  boolean hasReference(RangeVariable[] paramArrayOfRangeVariable, int paramInt) {
    OrderedHashSet orderedHashSet = collectRangeVariables(paramArrayOfRangeVariable, null);
    if (orderedHashSet == null)
      return false; 
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      if (orderedHashSet.get(b) != paramArrayOfRangeVariable[paramInt])
        return true; 
    } 
    return false;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList) {
    return resolveColumnReferences(paramSession, paramRangeGroup, (paramRangeGroup.getRangeVariables()).length, paramArrayOfRangeGroup, paramHsqlList, true);
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    ArrayListIdentity arrayListIdentity;
    HsqlList hsqlList;
    RangeVariable[] arrayOfRangeVariable;
    QueryExpression queryExpression;
    Expression expression;
    if (this.opType == 1)
      return paramHsqlList; 
    switch (this.opType) {
      case 26:
      case 30:
        if (this.table != null) {
          if ((paramRangeGroup.getRangeVariables()).length > paramInt) {
            RangeVariable[] arrayOfRangeVariable1 = (RangeVariable[])ArrayUtil.resizeArray(paramRangeGroup.getRangeVariables(), paramInt);
            paramRangeGroup = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable1, paramRangeGroup);
          } 
          paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
          paramRangeGroup = new RangeGroup.RangeGroupSimple(this.table);
          paramInt = 0;
        } 
        for (b = 0; b < this.nodes.length; b++) {
          if (this.nodes[b] != null)
            paramHsqlList = this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean); 
        } 
        return paramHsqlList;
    } 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramHsqlList = this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean); 
    } 
    switch (this.opType) {
      case 22:
      case 23:
      case 100:
        arrayOfRangeVariable = paramRangeGroup.getRangeVariables();
        if (arrayOfRangeVariable.length > paramInt) {
          arrayOfRangeVariable = (RangeVariable[])ArrayUtil.resizeArray(arrayOfRangeVariable, paramInt);
          paramRangeGroup = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable, paramRangeGroup);
        } 
        paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
        queryExpression = this.table.queryExpression;
        if (queryExpression != null) {
          queryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
          if (!queryExpression.areColumnsResolved()) {
            if (paramHsqlList == null)
              arrayListIdentity = new ArrayListIdentity(); 
            arrayListIdentity.addAll((Collection)queryExpression.getUnresolvedExpressions());
          } 
        } 
        expression = this.table.dataExpression;
        if (expression != null)
          hsqlList = expression.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, (HsqlList)arrayListIdentity, paramBoolean); 
        break;
    } 
    return hsqlList;
  }
  
  public OrderedHashSet getUnkeyedColumns(OrderedHashSet paramOrderedHashSet) {
    if (this.opType == 1)
      return paramOrderedHashSet; 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].getUnkeyedColumns(paramOrderedHashSet); 
    } 
    switch (this.opType) {
      case 19:
      case 22:
      case 23:
      case 100:
        if (this.table != null) {
          if (paramOrderedHashSet == null)
            paramOrderedHashSet = new OrderedHashSet(); 
          paramOrderedHashSet.add(this);
        } 
        break;
    } 
    return paramOrderedHashSet;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    Type type;
    QueryExpression queryExpression;
    byte b2;
    Expression expression;
    byte b1;
    for (b1 = 0; b1 < this.nodes.length; b1++) {
      if (this.nodes[b1] != null)
        this.nodes[b1].resolveTypes(paramSession, this); 
    } 
    switch (this.opType) {
      case 1:
      case 26:
        return;
      case 25:
        this.nodeDataTypes = new Type[this.nodes.length];
        for (b1 = 0; b1 < this.nodes.length; b1++) {
          if (this.nodes[b1] != null)
            this.nodeDataTypes[b1] = (this.nodes[b1]).dataType; 
        } 
      case 19:
        type = null;
        for (b2 = 0; b2 < this.nodes.length; b2++)
          type = Type.getAggregateType(type, (this.nodes[b2]).dataType); 
        for (b2 = 0; b2 < this.nodes.length; b2++)
          (this.nodes[b2]).dataType = type; 
        if (type != null)
          for (b2 = 0; b2 < this.nodes.length; b2++) {
            if ((this.nodes[b2]).valueData != null)
              (this.nodes[b2]).valueData = type.convertToDefaultType(paramSession, (this.nodes[b2]).valueData); 
          }  
        this.dataType = (Type)new ArrayType(type, this.nodes.length);
        return;
      case 100:
        queryExpression = this.table.queryExpression;
        queryExpression.resolveTypes(paramSession);
        this.table.prepareTable();
        this.nodeDataTypes = queryExpression.getColumnTypes();
        this.dataType = this.nodeDataTypes[0];
        if (this.nodeDataTypes.length > 1)
          throw Error.error(5564); 
        this.dataType = (Type)new ArrayType(this.dataType, this.nodes.length);
      case 22:
      case 23:
        queryExpression = this.table.queryExpression;
        if (queryExpression != null)
          queryExpression.resolveTypes(paramSession); 
        expression = this.table.dataExpression;
        if (expression != null)
          expression.resolveTypes(paramSession, null); 
        this.table.prepareTable();
        this.nodeDataTypes = this.table.getColumnTypes();
        this.dataType = this.nodeDataTypes[0];
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  void setAsConstantValue(Session paramSession, Expression paramExpression) {
    this.valueData = getValue(paramSession);
    this.opType = 1;
    this.nodes = emptyArray;
  }
  
  void setAsConstantValue(Object paramObject, Expression paramExpression) {
    this.valueData = paramObject;
    this.opType = 1;
    this.nodes = emptyArray;
  }
  
  void prepareTable(Session paramSession, Expression paramExpression, int paramInt) {
    if (this.nodeDataTypes != null)
      return; 
    byte b;
    for (b = 0; b < this.nodes.length; b++) {
      Expression expression = this.nodes[b];
      if (expression.opType == 25) {
        if (paramInt != expression.nodes.length)
          throw Error.error(5564); 
      } else if (paramInt == 1) {
        this.nodes[b] = new Expression(25);
        (this.nodes[b]).nodes = new Expression[] { expression };
      } else {
        throw Error.error(5564);
      } 
    } 
    this.nodeDataTypes = new Type[paramInt];
    for (b = 0; b < paramInt; b++) {
      CharacterType characterType;
      Type type = (paramExpression == null) ? null : (paramExpression.nodes[b]).dataType;
      boolean bool = (paramExpression == null) ? false : paramExpression.nodes[b].isUnresolvedParam();
      int i;
      for (i = 0; i < this.nodes.length; i++) {
        type = Type.getAggregateType(((this.nodes[i]).nodes[b]).dataType, type);
        bool |= (this.nodes[i]).nodes[b].isUnresolvedParam();
      } 
      if (type == null)
        characterType = Type.SQL_VARCHAR_DEFAULT; 
      i = ((Type)characterType).typeCode;
      if (bool && characterType.isCharacterType() && (i == 1 || ((Type)characterType).precision < Type.SQL_VARCHAR_DEFAULT.precision)) {
        if (i == 1)
          i = 12; 
        long l = Math.max(Type.SQL_VARCHAR_DEFAULT.precision, ((Type)characterType).precision);
        characterType = CharacterType.getCharacterType(i, l, characterType.getCollation());
      } 
      this.nodeDataTypes[b] = (Type)characterType;
      if (paramExpression != null && paramExpression.nodes[b].isUnresolvedParam())
        (paramExpression.nodes[b]).dataType = (Type)characterType; 
      for (byte b1 = 0; b1 < this.nodes.length; b1++) {
        if ((this.nodes[b1]).nodes[b].isUnresolvedParam()) {
          ((this.nodes[b1]).nodes[b]).dataType = this.nodeDataTypes[b];
        } else if (((this.nodes[b1]).nodes[b]).opType == 1 && ((this.nodes[b1]).nodes[b]).valueData == null) {
          ((this.nodes[b1]).nodes[b]).dataType = this.nodeDataTypes[b];
        } 
      } 
    } 
  }
  
  void insertValuesIntoSubqueryTable(Session paramSession, PersistentStore paramPersistentStore) {
    for (byte b = 0; b < this.nodes.length; b++) {
      Object[] arrayOfObject1 = this.nodes[b].getRowValue(paramSession);
      Object[] arrayOfObject2 = paramPersistentStore.getTable().getEmptyRowData();
      for (byte b1 = 0; b1 < this.nodeDataTypes.length; b1++)
        arrayOfObject2[b1] = this.nodeDataTypes[b1].convertToType(paramSession, arrayOfObject1[b1], ((this.nodes[b]).nodes[b1]).dataType); 
      Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject2, false);
      try {
        paramPersistentStore.indexRow(paramSession, row);
      } catch (HsqlException hsqlException) {}
    } 
  }
  
  String getColumnName() {
    return getAlias();
  }
  
  public ColumnSchema getColumn() {
    return null;
  }
  
  int getColumnIndex() {
    return this.columnIndex;
  }
  
  Type getDataType() {
    return this.dataType;
  }
  
  byte getNullability() {
    return this.nullability;
  }
  
  Type getNodeDataType(int paramInt) {
    if (this.nodeDataTypes == null) {
      if (paramInt > 0)
        throw Error.runtimeError(201, "Expression"); 
      return this.dataType;
    } 
    return this.nodeDataTypes[paramInt];
  }
  
  Type[] getNodeDataTypes() {
    return (this.nodeDataTypes == null) ? new Type[] { this.dataType } : this.nodeDataTypes;
  }
  
  int getDegree() {
    switch (this.opType) {
      case 25:
        return this.nodes.length;
      case 22:
      case 23:
      case 30:
        return (this.table == null) ? this.nodeDataTypes.length : this.table.queryExpression.getColumnCount();
    } 
    return 1;
  }
  
  public Table getTable() {
    return this.table;
  }
  
  public void materialise(Session paramSession) {
    if (this.table == null)
      return; 
    if (this.table.isCorrelated()) {
      this.table.materialiseCorrelated(paramSession);
    } else {
      this.table.materialise(paramSession);
    } 
  }
  
  Object getValue(Session paramSession, Type paramType) {
    Object object = getValue(paramSession);
    return (object == null || this.dataType == paramType) ? object : paramType.convertToType(paramSession, object, this.dataType);
  }
  
  public Object getConstantValueNoCheck(Session paramSession) {
    try {
      return getValue(paramSession);
    } catch (HsqlException hsqlException) {
      return null;
    } 
  }
  
  public Object[] getRowValue(Session paramSession) {
    Object[] arrayOfObject;
    byte b;
    switch (this.opType) {
      case 25:
        arrayOfObject = new Object[this.nodes.length];
        for (b = 0; b < this.nodes.length; b++)
          arrayOfObject[b] = this.nodes[b].getValue(paramSession); 
        return arrayOfObject;
      case 22:
      case 23:
        return this.table.queryExpression.getValues(paramSession);
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject2;
    RowSetNavigatorData rowSetNavigatorData;
    Object[] arrayOfObject1;
    int i;
    Object[] arrayOfObject3;
    byte b;
    switch (this.opType) {
      case 1:
        return this.valueData;
      case 5:
        return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      case 25:
        if (this.nodes.length == 1)
          return this.nodes[0].getValue(paramSession); 
        arrayOfObject2 = new Object[this.nodes.length];
        for (i = 0; i < this.nodes.length; i++)
          arrayOfObject2[i] = this.nodes[i].getValue(paramSession); 
        return arrayOfObject2;
      case 19:
        arrayOfObject2 = new Object[this.nodes.length];
        for (i = 0; i < this.nodes.length; i++)
          arrayOfObject2[i] = this.nodes[i].getValue(paramSession); 
        return arrayOfObject2;
      case 100:
        this.table.materialiseCorrelated(paramSession);
        rowSetNavigatorData = this.table.getNavigator(paramSession);
        i = rowSetNavigatorData.getSize();
        arrayOfObject3 = new Object[i];
        rowSetNavigatorData.beforeFirst();
        for (b = 0; rowSetNavigatorData.hasNext(); b++) {
          Object[] arrayOfObject = rowSetNavigatorData.getNextRowData();
          arrayOfObject3[b] = arrayOfObject[0];
        } 
        return arrayOfObject3;
      case 22:
      case 23:
        this.table.materialiseCorrelated(paramSession);
        arrayOfObject1 = this.table.getValues(paramSession);
        return (arrayOfObject1.length == 1) ? arrayOfObject1[0] : arrayOfObject1;
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  public Result getResult(Session paramSession) {
    RowSetNavigatorData rowSetNavigatorData;
    Object[] arrayOfObject;
    Result result;
    byte b;
    switch (this.opType) {
      case 19:
        rowSetNavigatorData = this.table.getNavigator(paramSession);
        arrayOfObject = new Object[rowSetNavigatorData.getSize()];
        rowSetNavigatorData.beforeFirst();
        for (b = 0; rowSetNavigatorData.hasNext(); b++) {
          Object[] arrayOfObject1 = rowSetNavigatorData.getNext();
          arrayOfObject[b] = arrayOfObject1[0];
        } 
        return Result.newPSMResult(arrayOfObject);
      case 23:
        this.table.materialiseCorrelated(paramSession);
        rowSetNavigatorData = this.table.getNavigator(paramSession);
        result = Result.newResult((RowSetNavigator)rowSetNavigatorData);
        result.metaData = this.table.queryExpression.getMetaData();
        return result;
    } 
    Object object = getValue(paramSession);
    return Result.newPSMResult(object);
  }
  
  public boolean testCondition(Session paramSession) {
    return Boolean.TRUE.equals(getValue(paramSession));
  }
  
  static int countNulls(Object[] paramArrayOfObject) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfObject.length; b2++) {
      if (paramArrayOfObject[b2] == null)
        b1++; 
    } 
    return b1;
  }
  
  public boolean isTrue() {
    return (this.opType == 1 && this.valueData instanceof Boolean && ((Boolean)this.valueData).booleanValue());
  }
  
  public boolean isFalse() {
    return (this.opType == 1 && this.valueData instanceof Boolean && !((Boolean)this.valueData).booleanValue());
  }
  
  public boolean isIndexable(RangeVariable paramRangeVariable) {
    return false;
  }
  
  static void convertToType(Session paramSession, Object[] paramArrayOfObject, Type[] paramArrayOfType1, Type[] paramArrayOfType2) {
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      if ((paramArrayOfType1[b]).typeComparisonGroup != (paramArrayOfType2[b]).typeComparisonGroup)
        paramArrayOfObject[b] = paramArrayOfType2[b].convertToType(paramSession, paramArrayOfObject[b], paramArrayOfType1[b]); 
    } 
  }
  
  static QuerySpecification getCheckSelect(Session paramSession, Table paramTable, Expression paramExpression) {
    ParserDQL.CompileContext compileContext = new ParserDQL.CompileContext(paramSession, null, null);
    compileContext.setNextRangeVarIndex(0);
    QuerySpecification querySpecification = new QuerySpecification(compileContext);
    RangeVariable rangeVariable = new RangeVariable(paramTable, null, null, null, compileContext);
    RangeVariable[] arrayOfRangeVariable = { rangeVariable };
    RangeGroup.RangeGroupSimple rangeGroupSimple = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable, false);
    paramExpression.resolveCheckOrGenExpression(paramSession, rangeGroupSimple, true);
    if (Type.SQL_BOOLEAN != paramExpression.getDataType())
      throw Error.error(5568); 
    ExpressionLogical expressionLogical = new ExpressionLogical(48, paramExpression);
    querySpecification.addSelectColumnExpression(EXPR_TRUE);
    querySpecification.addRangeVariable(paramSession, rangeVariable);
    querySpecification.addQueryCondition(expressionLogical);
    querySpecification.resolve(paramSession);
    return querySpecification;
  }
  
  public void resolveCheckOrGenExpression(Session paramSession, RangeGroup paramRangeGroup, boolean paramBoolean) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: new org/hsqldb/lib/OrderedHashSet
    //   6: dup
    //   7: invokespecial <init> : ()V
    //   10: astore #5
    //   12: aload_0
    //   13: aload_1
    //   14: aload_2
    //   15: getstatic org/hsqldb/RangeGroup.emptyArray : [Lorg/hsqldb/RangeGroup;
    //   18: aconst_null
    //   19: invokevirtual resolveColumnReferences : (Lorg/hsqldb/Session;Lorg/hsqldb/RangeGroup;[Lorg/hsqldb/RangeGroup;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/lib/HsqlList;
    //   22: astore #6
    //   24: aload #6
    //   26: invokestatic checkColumnsResolved : (Lorg/hsqldb/lib/HsqlList;)V
    //   29: aload_0
    //   30: aload_1
    //   31: aconst_null
    //   32: invokevirtual resolveTypes : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;)V
    //   35: aload_0
    //   36: aload #5
    //   38: getstatic org/hsqldb/Expression.subqueryAggregateExpressionSet : Lorg/hsqldb/lib/OrderedIntHashSet;
    //   41: getstatic org/hsqldb/Expression.emptyExpressionSet : Lorg/hsqldb/lib/OrderedIntHashSet;
    //   44: invokevirtual collectAllExpressions : (Lorg/hsqldb/lib/OrderedHashSet;Lorg/hsqldb/lib/OrderedIntHashSet;Lorg/hsqldb/lib/OrderedIntHashSet;)Lorg/hsqldb/lib/OrderedHashSet;
    //   47: pop
    //   48: aload #5
    //   50: invokevirtual isEmpty : ()Z
    //   53: ifne -> 63
    //   56: sipush #5512
    //   59: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   62: athrow
    //   63: aload_0
    //   64: aload #5
    //   66: getstatic org/hsqldb/Expression.functionExpressionSet : Lorg/hsqldb/lib/OrderedIntHashSet;
    //   69: getstatic org/hsqldb/Expression.emptyExpressionSet : Lorg/hsqldb/lib/OrderedIntHashSet;
    //   72: invokevirtual collectAllExpressions : (Lorg/hsqldb/lib/OrderedHashSet;Lorg/hsqldb/lib/OrderedIntHashSet;Lorg/hsqldb/lib/OrderedIntHashSet;)Lorg/hsqldb/lib/OrderedHashSet;
    //   75: pop
    //   76: iconst_0
    //   77: istore #7
    //   79: iload #7
    //   81: aload #5
    //   83: invokevirtual size : ()I
    //   86: if_icmpge -> 173
    //   89: aload #5
    //   91: iload #7
    //   93: invokevirtual get : (I)Ljava/lang/Object;
    //   96: checkcast org/hsqldb/Expression
    //   99: astore #8
    //   101: aload #8
    //   103: getfield opType : I
    //   106: bipush #27
    //   108: if_icmpne -> 129
    //   111: aload #8
    //   113: checkcast org/hsqldb/FunctionSQLInvoked
    //   116: invokevirtual isDeterministic : ()Z
    //   119: ifne -> 129
    //   122: sipush #5512
    //   125: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   128: athrow
    //   129: aload #8
    //   131: getfield opType : I
    //   134: bipush #28
    //   136: if_icmpne -> 167
    //   139: aload #8
    //   141: checkcast org/hsqldb/FunctionSQL
    //   144: invokevirtual isDeterministic : ()Z
    //   147: ifne -> 167
    //   150: iload_3
    //   151: ifeq -> 160
    //   154: iconst_1
    //   155: istore #4
    //   157: goto -> 167
    //   160: sipush #5512
    //   163: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   166: athrow
    //   167: iinc #7, 1
    //   170: goto -> 79
    //   173: iload_3
    //   174: ifeq -> 447
    //   177: iload #4
    //   179: ifeq -> 447
    //   182: new org/hsqldb/lib/HsqlArrayList
    //   185: dup
    //   186: invokespecial <init> : ()V
    //   189: astore #7
    //   191: aload_1
    //   192: aload_0
    //   193: aload #7
    //   195: invokestatic decomposeAndConditions : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/Expression;
    //   198: pop
    //   199: iconst_0
    //   200: istore #8
    //   202: iload #8
    //   204: aload #7
    //   206: invokevirtual size : ()I
    //   209: if_icmpge -> 435
    //   212: iconst_1
    //   213: istore #4
    //   215: aload #7
    //   217: iload #8
    //   219: invokevirtual get : (I)Ljava/lang/Object;
    //   222: checkcast org/hsqldb/Expression
    //   225: astore #9
    //   227: aload #9
    //   229: instanceof org/hsqldb/ExpressionLogical
    //   232: ifeq -> 435
    //   235: aload #9
    //   237: checkcast org/hsqldb/ExpressionLogical
    //   240: invokevirtual convertToSmaller : ()Z
    //   243: istore #11
    //   245: iload #11
    //   247: ifne -> 253
    //   250: goto -> 435
    //   253: aload #9
    //   255: invokevirtual getRightNode : ()Lorg/hsqldb/Expression;
    //   258: astore #10
    //   260: aload #9
    //   262: invokevirtual getLeftNode : ()Lorg/hsqldb/Expression;
    //   265: astore #9
    //   267: aload #9
    //   269: getfield dataType : Lorg/hsqldb/types/Type;
    //   272: invokevirtual isDateTimeType : ()Z
    //   275: ifne -> 284
    //   278: iconst_1
    //   279: istore #4
    //   281: goto -> 435
    //   284: aload #9
    //   286: invokevirtual hasNonDeterministicFunction : ()Z
    //   289: ifeq -> 298
    //   292: iconst_1
    //   293: istore #4
    //   295: goto -> 435
    //   298: aload #10
    //   300: instanceof org/hsqldb/ExpressionArithmetic
    //   303: ifeq -> 364
    //   306: aload_0
    //   307: getfield opType : I
    //   310: bipush #32
    //   312: if_icmpne -> 334
    //   315: aload #10
    //   317: invokevirtual getRightNode : ()Lorg/hsqldb/Expression;
    //   320: invokevirtual hasNonDeterministicFunction : ()Z
    //   323: ifeq -> 343
    //   326: aload #10
    //   328: invokevirtual swapLeftAndRightNodes : ()V
    //   331: goto -> 343
    //   334: aload_0
    //   335: getfield opType : I
    //   338: bipush #33
    //   340: if_icmpne -> 435
    //   343: aload #10
    //   345: invokevirtual getRightNode : ()Lorg/hsqldb/Expression;
    //   348: invokevirtual hasNonDeterministicFunction : ()Z
    //   351: ifeq -> 357
    //   354: goto -> 435
    //   357: aload #10
    //   359: invokevirtual getLeftNode : ()Lorg/hsqldb/Expression;
    //   362: astore #10
    //   364: aload #10
    //   366: getfield opType : I
    //   369: bipush #28
    //   371: if_icmpne -> 435
    //   374: aload #10
    //   376: checkcast org/hsqldb/FunctionSQL
    //   379: astore #12
    //   381: aload #12
    //   383: getfield funcType : I
    //   386: lookupswitch default -> 426, 43 -> 420, 50 -> 420, 52 -> 420
    //   420: iconst_0
    //   421: istore #4
    //   423: goto -> 429
    //   426: goto -> 435
    //   429: iinc #8, 1
    //   432: goto -> 202
    //   435: iload #4
    //   437: ifeq -> 447
    //   440: sipush #5512
    //   443: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   446: athrow
    //   447: aload #5
    //   449: invokevirtual clear : ()V
    //   452: aload_0
    //   453: aload #5
    //   455: invokevirtual collectObjectNames : (Lorg/hsqldb/lib/Set;)V
    //   458: aload_2
    //   459: invokeinterface getRangeVariables : ()[Lorg/hsqldb/RangeVariable;
    //   464: astore #7
    //   466: iconst_0
    //   467: istore #8
    //   469: iload #8
    //   471: aload #5
    //   473: invokevirtual size : ()I
    //   476: if_icmpge -> 656
    //   479: aload #5
    //   481: iload #8
    //   483: invokevirtual get : (I)Ljava/lang/Object;
    //   486: checkcast org/hsqldb/HsqlNameManager$HsqlName
    //   489: astore #9
    //   491: aload #9
    //   493: getfield type : I
    //   496: lookupswitch default -> 650, 7 -> 585, 9 -> 532, 24 -> 592
    //   532: iload_3
    //   533: ifeq -> 539
    //   536: goto -> 650
    //   539: aload #7
    //   541: iconst_0
    //   542: aaload
    //   543: getfield rangeTable : Lorg/hsqldb/Table;
    //   546: aload #9
    //   548: getfield name : Ljava/lang/String;
    //   551: invokevirtual findColumn : (Ljava/lang/String;)I
    //   554: istore #10
    //   556: aload #7
    //   558: iconst_0
    //   559: aaload
    //   560: getfield rangeTable : Lorg/hsqldb/Table;
    //   563: iload #10
    //   565: invokevirtual getColumn : (I)Lorg/hsqldb/ColumnSchema;
    //   568: astore #11
    //   570: aload #11
    //   572: invokevirtual isGenerated : ()Z
    //   575: ifeq -> 650
    //   578: sipush #5512
    //   581: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   584: athrow
    //   585: sipush #5512
    //   588: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   591: athrow
    //   592: aload_1
    //   593: getfield database : Lorg/hsqldb/Database;
    //   596: getfield schemaManager : Lorg/hsqldb/SchemaManager;
    //   599: aload #9
    //   601: invokevirtual getSchemaObject : (Lorg/hsqldb/HsqlNameManager$HsqlName;)Lorg/hsqldb/SchemaObject;
    //   604: checkcast org/hsqldb/Routine
    //   607: astore #10
    //   609: aload #10
    //   611: invokevirtual isDeterministic : ()Z
    //   614: ifne -> 624
    //   617: sipush #5512
    //   620: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   623: athrow
    //   624: aload #10
    //   626: invokevirtual getDataImpact : ()I
    //   629: istore #11
    //   631: iload #11
    //   633: iconst_3
    //   634: if_icmpeq -> 643
    //   637: iload #11
    //   639: iconst_4
    //   640: if_icmpne -> 650
    //   643: sipush #5512
    //   646: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   649: athrow
    //   650: iinc #8, 1
    //   653: goto -> 469
    //   656: aload #5
    //   658: invokevirtual clear : ()V
    //   661: return
  }
  
  boolean isUnresolvedParam() {
    return false;
  }
  
  boolean isDynamicParam() {
    return false;
  }
  
  boolean hasNonDeterministicFunction() {
    OrderedHashSet orderedHashSet = null;
    orderedHashSet = collectAllExpressions(orderedHashSet, functionExpressionSet, emptyExpressionSet);
    if (orderedHashSet == null)
      return false; 
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      Expression expression = (Expression)orderedHashSet.get(b);
      if (expression.opType == 27) {
        if (!((FunctionSQLInvoked)expression).isDeterministic())
          return true; 
      } else if (expression.opType == 28 && !((FunctionSQL)expression).isDeterministic()) {
        return true;
      } 
    } 
    return false;
  }
  
  void swapLeftAndRightNodes() {
    Expression expression = this.nodes[0];
    this.nodes[0] = this.nodes[1];
    this.nodes[1] = expression;
  }
  
  void setAttributesAsColumn(ColumnSchema paramColumnSchema, boolean paramBoolean) {
    throw Error.runtimeError(201, "Expression");
  }
  
  String getValueClassName() {
    Type type = (this.dataType == null) ? NullType.getNullType() : this.dataType;
    return type.getJDBCClassName();
  }
  
  OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2) {
    if (paramOrderedIntHashSet2.contains(this.opType))
      return paramOrderedHashSet; 
    byte b;
    for (b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    } 
    b = 0;
    if (paramOrderedIntHashSet1.contains(this.opType)) {
      if (paramOrderedHashSet == null)
        paramOrderedHashSet = new OrderedHashSet(); 
      paramOrderedHashSet.add(this);
      b = 1;
    } 
    if (b == 0 && this.table != null && this.table.queryExpression != null)
      paramOrderedHashSet = this.table.queryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2); 
    return paramOrderedHashSet;
  }
  
  public OrderedHashSet getSubqueries() {
    return collectAllSubqueries(null);
  }
  
  OrderedHashSet collectAllSubqueries(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramOrderedHashSet = this.nodes[b].collectAllSubqueries(paramOrderedHashSet); 
    } 
    if (this.table != null) {
      OrderedHashSet orderedHashSet = null;
      if (this.table.queryExpression != null) {
        orderedHashSet = this.table.queryExpression.getSubqueries();
        paramOrderedHashSet = OrderedHashSet.addAll(paramOrderedHashSet, orderedHashSet);
      } 
      if (paramOrderedHashSet == null)
        paramOrderedHashSet = new OrderedHashSet(); 
      paramOrderedHashSet.add(this.table);
    } 
    return paramOrderedHashSet;
  }
  
  public boolean isCorrelated() {
    return (this.table == null) ? false : this.table.isCorrelated();
  }
  
  public void checkValidCheckConstraint() {
    OrderedHashSet orderedHashSet = null;
    orderedHashSet = collectAllExpressions(orderedHashSet, subqueryAggregateExpressionSet, emptyExpressionSet);
    if (orderedHashSet != null && !orderedHashSet.isEmpty())
      throw Error.error(1500, "subquery in check constraint"); 
  }
  
  static HsqlList resolveColumnSet(Session paramSession, RangeVariable[] paramArrayOfRangeVariable, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList) {
    return resolveColumnSet(paramSession, paramArrayOfRangeVariable, paramArrayOfRangeVariable.length, paramArrayOfRangeGroup, paramHsqlList, null);
  }
  
  static HsqlList resolveColumnSet(Session paramSession, RangeVariable[] paramArrayOfRangeVariable, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList1, HsqlList paramHsqlList2) {
    if (paramHsqlList1 == null)
      return paramHsqlList2; 
    RangeGroup.RangeGroupSimple rangeGroupSimple = new RangeGroup.RangeGroupSimple(paramArrayOfRangeVariable, false);
    for (byte b = 0; b < paramHsqlList1.size(); b++) {
      Expression expression = (Expression)paramHsqlList1.get(b);
      paramHsqlList2 = expression.resolveColumnReferences(paramSession, rangeGroupSimple, paramInt, paramArrayOfRangeGroup, paramHsqlList2, false);
    } 
    return paramHsqlList2;
  }
  
  boolean isConditionRangeVariable(RangeVariable paramRangeVariable) {
    return false;
  }
  
  RangeVariable[] getJoinRangeVariables(RangeVariable[] paramArrayOfRangeVariable) {
    return RangeVariable.emptyArray;
  }
  
  double costFactor(Session paramSession, RangeVariable paramRangeVariable, int paramInt) {
    return 16.0D;
  }
  
  Expression getIndexableExpression(RangeVariable paramRangeVariable) {
    return null;
  }
  
  public Expression duplicate() {
    Expression expression = null;
    try {
      expression = (Expression)clone();
      expression.nodes = (Expression[])this.nodes.clone();
      for (byte b = 0; b < this.nodes.length; b++) {
        if (this.nodes[b] != null)
          expression.nodes[b] = this.nodes[b].duplicate(); 
      } 
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw Error.runtimeError(201, "Expression");
    } 
    return expression;
  }
  
  void replaceNode(Expression paramExpression1, Expression paramExpression2) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] == paramExpression1) {
        paramExpression2.alias = (this.nodes[b]).alias;
        this.nodes[b] = paramExpression2;
        return;
      } 
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject) {
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject) {
    throw Error.runtimeError(201, "Expression");
  }
  
  public Expression getCondition() {
    return null;
  }
  
  public boolean hasCondition() {
    return false;
  }
  
  public void setCondition(Expression paramExpression) {
    throw Error.runtimeError(201, "Expression");
  }
  
  public void setCollation(Collation paramCollation) {
    this.collation = paramCollation;
  }
  
  static {
    aggregateFunctionSet.add(71);
    aggregateFunctionSet.add(72);
    aggregateFunctionSet.add(73);
    aggregateFunctionSet.add(74);
    aggregateFunctionSet.add(75);
    aggregateFunctionSet.add(76);
    aggregateFunctionSet.add(77);
    aggregateFunctionSet.add(78);
    aggregateFunctionSet.add(79);
    aggregateFunctionSet.add(80);
    aggregateFunctionSet.add(81);
    aggregateFunctionSet.add(83);
    aggregateFunctionSet.add(82);
    aggregateFunctionSet.add(85);
    aggregateFunctionSet.add(98);
  }
  
  static {
    columnExpressionSet.add(2);
  }
  
  static {
    subqueryExpressionSet.add(22);
    subqueryExpressionSet.add(23);
  }
  
  static {
    subqueryAggregateExpressionSet.add(71);
    subqueryAggregateExpressionSet.add(72);
    subqueryAggregateExpressionSet.add(73);
    subqueryAggregateExpressionSet.add(74);
    subqueryAggregateExpressionSet.add(75);
    subqueryAggregateExpressionSet.add(76);
    subqueryAggregateExpressionSet.add(77);
    subqueryAggregateExpressionSet.add(78);
    subqueryAggregateExpressionSet.add(79);
    subqueryAggregateExpressionSet.add(80);
    subqueryAggregateExpressionSet.add(81);
    subqueryAggregateExpressionSet.add(83);
    subqueryAggregateExpressionSet.add(82);
    subqueryAggregateExpressionSet.add(85);
    subqueryAggregateExpressionSet.add(98);
    subqueryAggregateExpressionSet.add(23);
    subqueryAggregateExpressionSet.add(22);
  }
  
  static {
    functionExpressionSet.add(28);
    functionExpressionSet.add(27);
  }
  
  static {
    sequenceExpressionSet.add(14);
    sequenceExpressionSet.add(12);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */