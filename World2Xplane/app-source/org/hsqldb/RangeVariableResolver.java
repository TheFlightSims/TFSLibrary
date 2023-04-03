package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.persist.PersistentStore;

public class RangeVariableResolver {
  Session session;
  
  QuerySpecification select;
  
  RangeVariable[] rangeVariables;
  
  Expression conditions;
  
  OrderedHashSet rangeVarSet = new OrderedHashSet();
  
  ParserDQL.CompileContext compileContext;
  
  SortAndSlice sortAndSlice = SortAndSlice.noSort;
  
  boolean reorder;
  
  HsqlArrayList[] tempJoinExpressions;
  
  HsqlArrayList[] joinExpressions;
  
  HsqlArrayList[] whereExpressions;
  
  HsqlArrayList queryConditions = new HsqlArrayList();
  
  Expression[] inExpressions;
  
  boolean[] inInJoin;
  
  int inExpressionCount = 0;
  
  boolean expandInExpression = true;
  
  int firstLeftJoinIndex;
  
  int firstRightJoinIndex;
  
  int lastRightJoinIndex;
  
  int firstLateralJoinIndex;
  
  int firstOuterJoinIndex;
  
  int lastOuterJoinIndex;
  
  OrderedIntHashSet colIndexSetEqual = new OrderedIntHashSet();
  
  IntKeyIntValueHashMap colIndexSetOther = new IntKeyIntValueHashMap();
  
  OrderedHashSet tempSet = new OrderedHashSet();
  
  HashMap tempMap = new HashMap();
  
  MultiValueHashMap tempMultiMap = new MultiValueHashMap();
  
  RangeVariableResolver(QuerySpecification paramQuerySpecification) {
    this.select = paramQuerySpecification;
    this.rangeVariables = paramQuerySpecification.rangeVariables;
    this.conditions = paramQuerySpecification.queryCondition;
    this.compileContext = paramQuerySpecification.compileContext;
    this.sortAndSlice = paramQuerySpecification.sortAndSlice;
    this.reorder = true;
    initialise();
  }
  
  RangeVariableResolver(RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, ParserDQL.CompileContext paramCompileContext, boolean paramBoolean) {
    this.rangeVariables = paramArrayOfRangeVariable;
    this.conditions = paramExpression;
    this.compileContext = paramCompileContext;
    this.reorder = paramBoolean;
    initialise();
  }
  
  private void initialise() {
    this.firstLeftJoinIndex = this.rangeVariables.length;
    this.firstRightJoinIndex = this.rangeVariables.length;
    this.firstLateralJoinIndex = this.rangeVariables.length;
    this.firstOuterJoinIndex = this.rangeVariables.length;
    this.inExpressions = new Expression[this.rangeVariables.length];
    this.inInJoin = new boolean[this.rangeVariables.length];
    this.tempJoinExpressions = new HsqlArrayList[this.rangeVariables.length];
    byte b;
    for (b = 0; b < this.rangeVariables.length; b++)
      this.tempJoinExpressions[b] = new HsqlArrayList(); 
    this.joinExpressions = new HsqlArrayList[this.rangeVariables.length];
    for (b = 0; b < this.rangeVariables.length; b++)
      this.joinExpressions[b] = new HsqlArrayList(); 
    this.whereExpressions = new HsqlArrayList[this.rangeVariables.length];
    for (b = 0; b < this.rangeVariables.length; b++)
      this.whereExpressions[b] = new HsqlArrayList(); 
  }
  
  void processConditions(Session paramSession) {
    this.session = paramSession;
    decomposeAndConditions(paramSession, this.conditions, (HsqlList)this.queryConditions);
    byte b;
    for (b = 0; b < this.rangeVariables.length; b++) {
      this.rangeVarSet.add(this.rangeVariables[b]);
      if ((this.rangeVariables[b]).joinCondition != null)
        decomposeAndConditions(paramSession, (this.rangeVariables[b]).joinCondition, (HsqlList)this.tempJoinExpressions[b]); 
    } 
    for (b = 0; b < this.queryConditions.size(); b++) {
      Expression expression = (Expression)this.queryConditions.get(b);
      if (!expression.isTrue() && (expression.isSingleColumnEqual || expression.isColumnCondition)) {
        RangeVariable rangeVariable = expression.getLeftNode().getRangeVariable();
        if ((expression.getLeftNode()).opType == 2 && rangeVariable != null) {
          int i = this.rangeVarSet.getIndex(rangeVariable);
          if (i > 0) {
            (this.rangeVariables[i]).isLeftJoin = false;
            (this.rangeVariables[i - 1]).isRightJoin = false;
          } 
        } 
        rangeVariable = expression.getRightNode().getRangeVariable();
        if ((expression.getRightNode()).opType == 2 && rangeVariable != null) {
          int i = this.rangeVarSet.getIndex(rangeVariable);
          if (i > 0) {
            (this.rangeVariables[i]).isLeftJoin = false;
            (this.rangeVariables[i - 1]).isRightJoin = false;
          } 
        } 
      } 
    } 
    for (b = 0; b < this.rangeVariables.length; b++) {
      RangeVariable rangeVariable = this.rangeVariables[b];
      boolean bool = false;
      if (rangeVariable.isLeftJoin) {
        if (this.firstLeftJoinIndex == this.rangeVariables.length)
          this.firstLeftJoinIndex = b; 
        bool = true;
      } 
      if (rangeVariable.isRightJoin) {
        if (this.firstRightJoinIndex == this.rangeVariables.length)
          this.firstRightJoinIndex = b; 
        this.lastRightJoinIndex = b;
        bool = true;
      } 
      if (rangeVariable.isLateral) {
        if (this.firstLateralJoinIndex == this.rangeVariables.length)
          this.firstLateralJoinIndex = b; 
        bool = true;
      } 
      if (bool) {
        if (this.firstOuterJoinIndex == this.rangeVariables.length)
          this.firstOuterJoinIndex = b; 
        this.lastOuterJoinIndex = b;
      } 
    } 
    expandConditions();
    this.conditions = null;
    reorder();
    assignToLists();
    assignToRangeVariables();
    if (this.select != null) {
      this.select.startInnerRange = 0;
      this.select.endInnerRange = this.rangeVariables.length;
      if (this.firstRightJoinIndex < this.rangeVariables.length)
        this.select.startInnerRange = this.firstRightJoinIndex; 
      if (this.firstLeftJoinIndex < this.rangeVariables.length)
        this.select.endInnerRange = this.firstLeftJoinIndex; 
    } 
    for (b = 0; b < this.rangeVariables.length; b++)
      (this.rangeVariables[b]).rangePositionInJoin = b; 
    if (this.expandInExpression && this.inExpressionCount != 0)
      setInConditionsAsTables(); 
  }
  
  static Expression decomposeAndConditions(Session paramSession, Expression paramExpression, HsqlList paramHsqlList) {
    if (paramExpression == null)
      return Expression.EXPR_TRUE; 
    Expression expression1 = paramExpression.getLeftNode();
    Expression expression2 = paramExpression.getRightNode();
    int i = paramExpression.getType();
    if (i == 49) {
      expression1 = decomposeAndConditions(paramSession, expression1, paramHsqlList);
      expression2 = decomposeAndConditions(paramSession, expression2, paramHsqlList);
      if (expression1.isTrue())
        return expression2; 
      if (expression2.isTrue())
        return expression1; 
      paramExpression.setLeftNode(expression1);
      paramExpression.setRightNode(expression2);
      return paramExpression;
    } 
    if (i == 40 && expression1.getType() == 25 && expression2.getType() == 25) {
      for (byte b = 0; b < expression1.nodes.length; b++) {
        ExpressionLogical expressionLogical = new ExpressionLogical(expression1.nodes[b], expression2.nodes[b]);
        expressionLogical.resolveTypes(paramSession, null);
        paramHsqlList.add(expressionLogical);
      } 
      return Expression.EXPR_TRUE;
    } 
    if (!paramExpression.isTrue())
      paramHsqlList.add(paramExpression); 
    return Expression.EXPR_TRUE;
  }
  
  static Expression decomposeOrConditions(Expression paramExpression, HsqlList paramHsqlList) {
    if (paramExpression == null)
      return Expression.EXPR_FALSE; 
    Expression expression1 = paramExpression.getLeftNode();
    Expression expression2 = paramExpression.getRightNode();
    int i = paramExpression.getType();
    if (i == 50) {
      expression1 = decomposeOrConditions(expression1, paramHsqlList);
      expression2 = decomposeOrConditions(expression2, paramHsqlList);
      return expression1.isFalse() ? expression2 : (expression2.isFalse() ? expression1 : new ExpressionLogical(50, expression1, expression2));
    } 
    if (!paramExpression.isFalse())
      paramHsqlList.add(paramExpression); 
    return Expression.EXPR_FALSE;
  }
  
  void expandConditions() {
    HsqlArrayList[] arrayOfHsqlArrayList = this.tempJoinExpressions;
    if (this.firstRightJoinIndex == this.rangeVariables.length)
      moveConditions((HsqlList[])this.tempJoinExpressions, 0, this.firstOuterJoinIndex, (HsqlList)this.queryConditions, -1); 
    if (this.firstOuterJoinIndex < 2)
      return; 
    byte b;
    for (b = 0; b < this.firstOuterJoinIndex; b++)
      moveConditions((HsqlList[])this.tempJoinExpressions, 0, this.firstOuterJoinIndex, (HsqlList)this.tempJoinExpressions[b], b); 
    if (this.firstOuterJoinIndex < 3)
      return; 
    for (b = 0; b < this.firstOuterJoinIndex; b++) {
      HsqlArrayList hsqlArrayList = arrayOfHsqlArrayList[b];
      this.tempMultiMap.clear();
      this.tempSet.clear();
      this.tempMap.clear();
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      for (byte b1 = 0; b1 < hsqlArrayList.size(); b1++) {
        Expression expression = (Expression)hsqlArrayList.get(b1);
        if (!expression.isTrue())
          if (expression.isSingleColumnEqual) {
            bool1 = true;
            if ((expression.getLeftNode()).opType == 2) {
              this.tempMap.put(expression.getLeftNode().getColumn(), expression.getRightNode());
            } else if ((expression.getRightNode()).opType == 2) {
              this.tempMap.put(expression.getRightNode().getColumn(), expression.getLeftNode());
            } 
          } else if (expression.isColumnEqual && expression.getLeftNode().getRangeVariable() != expression.getRightNode().getRangeVariable() && expression.getLeftNode().getRangeVariable() != null && expression.getRightNode().getRangeVariable() != null) {
            int i = this.rangeVarSet.getIndex(expression.getLeftNode().getRangeVariable());
            if (i < 0) {
              expression.isSingleColumnEqual = true;
              expression.isSingleColumnCondition = true;
              this.tempMap.put(expression.getRightNode().getColumn(), expression.getLeftNode());
            } else if (i < this.firstOuterJoinIndex) {
              i = this.rangeVarSet.getIndex(expression.getRightNode().getRangeVariable());
              if (i < 0) {
                expression.isSingleColumnEqual = true;
                expression.isSingleColumnCondition = true;
                this.tempMap.put(expression.getRightNode().getColumn(), expression.getLeftNode());
              } else if (i < this.firstOuterJoinIndex) {
                bool2 = true;
                if (expression.getLeftNode().getRangeVariable() == this.rangeVariables[b]) {
                  ColumnSchema columnSchema = expression.getLeftNode().getColumn();
                  this.tempMultiMap.put(columnSchema, expression.getRightNode());
                  if (this.tempMultiMap.valueCount(columnSchema) > 1)
                    bool3 = true; 
                } else if (expression.getRightNode().getRangeVariable() == this.rangeVariables[b]) {
                  ColumnSchema columnSchema = expression.getRightNode().getColumn();
                  this.tempMultiMap.put(columnSchema, expression.getLeftNode());
                  if (this.tempMultiMap.valueCount(columnSchema) > 1)
                    bool3 = true; 
                } 
              } 
            } 
          }  
      } 
      if (bool3) {
        Iterator iterator = this.tempMultiMap.keySet().iterator();
        while (iterator.hasNext()) {
          Object object = iterator.next();
          Iterator iterator1 = this.tempMultiMap.get(object);
          this.tempSet.clear();
          while (iterator1.hasNext())
            this.tempSet.add(iterator1.next()); 
          while (this.tempSet.size() > 1) {
            Expression expression = (Expression)this.tempSet.remove(this.tempSet.size() - 1);
            for (byte b2 = 0; b2 < this.tempSet.size(); b2++) {
              Expression expression1 = (Expression)this.tempSet.get(b2);
              closeJoinChain((HsqlList[])arrayOfHsqlArrayList, expression, expression1);
            } 
          } 
        } 
      } 
      if (bool2 && bool1) {
        Iterator iterator = this.tempMultiMap.keySet().iterator();
        while (iterator.hasNext()) {
          Object object = iterator.next();
          Expression expression = (Expression)this.tempMap.get(object);
          if (expression != null) {
            Iterator iterator1 = this.tempMultiMap.get(object);
            while (iterator1.hasNext()) {
              Expression expression1 = (Expression)iterator1.next();
              ExpressionLogical expressionLogical = new ExpressionLogical(expression, expression1);
              int i = this.rangeVarSet.getIndex(expression1.getRangeVariable());
              arrayOfHsqlArrayList[i].add(expressionLogical);
            } 
          } 
        } 
      } 
    } 
  }
  
  void moveConditions(HsqlList[] paramArrayOfHsqlList, int paramInt1, int paramInt2, HsqlList paramHsqlList, int paramInt3) {
    for (byte b = 0; b < paramHsqlList.size(); b++) {
      Expression expression = (Expression)paramHsqlList.get(b);
      this.tempSet.clear();
      expression.collectRangeVariables(this.rangeVariables, this.tempSet);
      int i = this.rangeVarSet.getSmallestIndex(this.tempSet);
      if (i >= paramInt1) {
        i = this.rangeVarSet.getLargestIndex(this.tempSet);
        if (i < paramInt2 && i != paramInt3) {
          paramHsqlList.remove(b);
          paramArrayOfHsqlList[i].add(expression);
          b--;
        } 
      } 
    } 
  }
  
  void closeJoinChain(HsqlList[] paramArrayOfHsqlList, Expression paramExpression1, Expression paramExpression2) {
    int i = this.rangeVarSet.getIndex(paramExpression1.getRangeVariable());
    int j = this.rangeVarSet.getIndex(paramExpression2.getRangeVariable());
    int k = (i > j) ? i : j;
    if (i == -1 || j == -1)
      return; 
    ExpressionLogical expressionLogical = new ExpressionLogical(paramExpression1, paramExpression2);
    for (byte b = 0; b < paramArrayOfHsqlList[k].size(); b++) {
      if (expressionLogical.equals(paramArrayOfHsqlList[k].get(b)))
        return; 
    } 
    paramArrayOfHsqlList[k].add(expressionLogical);
  }
  
  void reorder() {
    if (!this.reorder)
      return; 
    if (this.rangeVariables.length == 1 || this.firstRightJoinIndex != this.rangeVariables.length)
      return; 
    if (this.firstLeftJoinIndex == 1)
      return; 
    if (this.firstLateralJoinIndex != this.rangeVariables.length)
      return; 
    if (this.sortAndSlice.usingIndex && this.sortAndSlice.primaryTableIndex != null)
      return; 
    HsqlArrayList hsqlArrayList1 = new HsqlArrayList();
    HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
    for (byte b = 0; b < this.firstLeftJoinIndex; b++) {
      HsqlArrayList hsqlArrayList = this.tempJoinExpressions[b];
      for (byte b1 = 0; b1 < hsqlArrayList.size(); b1++) {
        Expression expression = (Expression)hsqlArrayList.get(b1);
        if (expression.isColumnEqual) {
          hsqlArrayList1.add(expression);
        } else if (expression.isSingleColumnCondition) {
          hsqlArrayList2.add(expression);
        } 
      } 
    } 
    reorderRanges(hsqlArrayList2, hsqlArrayList1);
  }
  
  void reorderRanges(HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2) {
    if (paramHsqlArrayList1.size() == 0)
      return; 
    byte b = -1;
    RangeVariable rangeVariable = null;
    double d = 1024.0D;
    for (byte b1 = 0; b1 < this.firstLeftJoinIndex; b1++) {
      Table table = (this.rangeVariables[b1]).rangeTable;
      if (!(table instanceof TableDerived)) {
        collectIndexableColumns(this.rangeVariables[b1], (HsqlList)paramHsqlArrayList1);
        Index.IndexUse[] arrayOfIndexUse = table.getIndexForColumns(this.session, this.colIndexSetEqual, 40, false);
        Index index = null;
        for (byte b3 = 0; b3 < arrayOfIndexUse.length; b3++) {
          index = (arrayOfIndexUse[b3]).index;
          PersistentStore persistentStore = table.getRowStore(this.session);
          double d1 = persistentStore.searchCost(this.session, index, (arrayOfIndexUse[b3]).columnCount, 40);
          if (d1 < d) {
            d = d1;
            b = b1;
          } 
        } 
        if (index == null) {
          Iterator iterator = this.colIndexSetOther.keySet().iterator();
          while (iterator.hasNext()) {
            int i = iterator.nextInt();
            index = table.getIndexForColumn(this.session, i);
            if (index != null) {
              d = (table.getRowStore(this.session).elementCount() / 2L);
              if (this.colIndexSetOther.get(i, 0) > 1)
                d /= 2.0D; 
              break;
            } 
          } 
        } 
        if (index != null && b1 == 0) {
          b = 0;
          break;
        } 
      } 
    } 
    if (b < 0)
      return; 
    if (b == 0 && this.firstLeftJoinIndex == 2)
      return; 
    RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.length];
    ArrayUtil.copyArray(this.rangeVariables, arrayOfRangeVariable, this.rangeVariables.length);
    rangeVariable = arrayOfRangeVariable[b];
    arrayOfRangeVariable[b] = arrayOfRangeVariable[0];
    arrayOfRangeVariable[0] = rangeVariable;
    for (b = 1; b < this.firstLeftJoinIndex; b++) {
      boolean bool = false;
      byte b3;
      for (b3 = 0; b3 < paramHsqlArrayList2.size(); b3++) {
        Expression expression = (Expression)paramHsqlArrayList2.get(b3);
        if (expression != null) {
          int i = getJoinedRangePosition(expression, b, arrayOfRangeVariable);
          if (i >= b) {
            rangeVariable = arrayOfRangeVariable[b];
            arrayOfRangeVariable[b] = arrayOfRangeVariable[i];
            arrayOfRangeVariable[i] = rangeVariable;
            paramHsqlArrayList2.set(b3, null);
            bool = true;
            break;
          } 
        } 
      } 
      if (!bool) {
        for (b3 = 0; b3 < paramHsqlArrayList1.size(); b3++) {
          Table table = (arrayOfRangeVariable[b3]).rangeTable;
          collectIndexableColumns(arrayOfRangeVariable[b3], (HsqlList)paramHsqlArrayList1);
          Index.IndexUse[] arrayOfIndexUse = table.getIndexForColumns(this.session, this.colIndexSetEqual, 40, false);
          if (arrayOfIndexUse.length > 0) {
            bool = true;
            break;
          } 
        } 
        if (!bool)
          break; 
      } 
    } 
    if (b != this.firstLeftJoinIndex)
      return; 
    ArrayUtil.copyArray(arrayOfRangeVariable, this.rangeVariables, this.rangeVariables.length);
    paramHsqlArrayList2.clear();
    byte b2;
    for (b2 = 0; b2 < this.firstLeftJoinIndex; b2++) {
      HsqlArrayList hsqlArrayList = this.tempJoinExpressions[b2];
      paramHsqlArrayList2.addAll((Collection)hsqlArrayList);
      hsqlArrayList.clear();
    } 
    this.tempJoinExpressions[this.firstLeftJoinIndex - 1].addAll((Collection)paramHsqlArrayList2);
    this.rangeVarSet.clear();
    for (b2 = 0; b2 < this.rangeVariables.length; b2++)
      this.rangeVarSet.add(this.rangeVariables[b2]); 
  }
  
  int getJoinedRangePosition(Expression paramExpression, int paramInt, RangeVariable[] paramArrayOfRangeVariable) {
    byte b = -1;
    RangeVariable[] arrayOfRangeVariable = paramExpression.getJoinRangeVariables(paramArrayOfRangeVariable);
    for (byte b1 = 0; b1 < arrayOfRangeVariable.length; b1++) {
      for (byte b2 = 0; b2 < paramArrayOfRangeVariable.length; b2++) {
        if (arrayOfRangeVariable[b1] == paramArrayOfRangeVariable[b2] && b2 >= paramInt) {
          if (b > 0)
            return -1; 
          b = b2;
        } 
      } 
    } 
    return b;
  }
  
  void assignToLists() {
    byte b = -1;
    byte b1;
    for (b1 = 0; b1 < this.rangeVariables.length; b1++) {
      if ((this.rangeVariables[b1]).isLeftJoin)
        b = b1; 
      if ((this.rangeVariables[b1]).isRightJoin)
        b = b1; 
      if (b == b1) {
        this.joinExpressions[b1].addAll((Collection)this.tempJoinExpressions[b1]);
      } else {
        int i = b + 1;
        for (byte b2 = 0; b2 < this.tempJoinExpressions[b1].size(); b2++) {
          Expression expression = (Expression)this.tempJoinExpressions[b1].get(b2);
          assignToJoinLists(expression, (HsqlList[])this.joinExpressions, i);
        } 
      } 
    } 
    for (b1 = 0; b1 < this.queryConditions.size(); b1++)
      assignToJoinLists((Expression)this.queryConditions.get(b1), (HsqlList[])this.whereExpressions, this.lastRightJoinIndex); 
  }
  
  void assignToJoinLists(Expression paramExpression, HsqlList[] paramArrayOfHsqlList, int paramInt) {
    if (paramExpression == null)
      return; 
    this.tempSet.clear();
    paramExpression.collectRangeVariables(this.rangeVariables, this.tempSet);
    int i = this.rangeVarSet.getLargestIndex(this.tempSet);
    if (i == -1)
      i = 0; 
    if (i < paramInt)
      i = paramInt; 
    if (paramExpression instanceof ExpressionLogical && ((ExpressionLogical)paramExpression).isTerminal)
      i = paramArrayOfHsqlList.length - 1; 
    paramArrayOfHsqlList[i].add(paramExpression);
  }
  
  void assignToRangeVariables() {
    for (byte b = 0; b < this.rangeVariables.length; b++) {
      boolean bool = false;
      if (b < this.firstLeftJoinIndex && this.firstRightJoinIndex == this.rangeVariables.length) {
        RangeVariable.RangeVariableConditions rangeVariableConditions = (this.rangeVariables[b]).joinConditions[0];
        this.joinExpressions[b].addAll((Collection)this.whereExpressions[b]);
        assignToRangeVariable(this.rangeVariables[b], rangeVariableConditions, b, (HsqlList)this.joinExpressions[b]);
        assignToRangeVariable(rangeVariableConditions, (HsqlList)this.joinExpressions[b]);
      } else {
        RangeVariable.RangeVariableConditions rangeVariableConditions = (this.rangeVariables[b]).joinConditions[0];
        assignToRangeVariable(this.rangeVariables[b], rangeVariableConditions, b, (HsqlList)this.joinExpressions[b]);
        rangeVariableConditions = (this.rangeVariables[b]).joinConditions[0];
        if (rangeVariableConditions.hasIndex())
          bool = true; 
        assignToRangeVariable(rangeVariableConditions, (HsqlList)this.joinExpressions[b]);
        rangeVariableConditions = (this.rangeVariables[b]).whereConditions[0];
        for (int i = b + 1; i < this.rangeVariables.length; i++) {
          if ((this.rangeVariables[i]).isRightJoin)
            assignToRangeVariable((this.rangeVariables[i]).whereConditions[0], (HsqlList)this.whereExpressions[b]); 
        } 
        if (!bool)
          assignToRangeVariable(this.rangeVariables[b], rangeVariableConditions, b, (HsqlList)this.whereExpressions[b]); 
        assignToRangeVariable(rangeVariableConditions, (HsqlList)this.whereExpressions[b]);
      } 
    } 
  }
  
  void assignToRangeVariable(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList) {
    byte b = 0;
    int i = paramHsqlList.size();
    while (b < i) {
      Expression expression = (Expression)paramHsqlList.get(b);
      paramRangeVariableConditions.addCondition(expression);
      b++;
    } 
  }
  
  private void collectIndexableColumns(RangeVariable paramRangeVariable, HsqlList paramHsqlList) {
    this.colIndexSetEqual.clear();
    this.colIndexSetOther.clear();
    byte b = 0;
    int i = paramHsqlList.size();
    while (b < i) {
      int j;
      Expression expression = (Expression)paramHsqlList.get(b);
      if (!expression.isSingleColumnCondition)
        continue; 
      if (expression.getLeftNode().getRangeVariable() == paramRangeVariable) {
        j = expression.getLeftNode().getColumnIndex();
      } else if (expression.getRightNode().getRangeVariable() == paramRangeVariable) {
        j = expression.getRightNode().getColumnIndex();
      } else {
        continue;
      } 
      if (expression.isSingleColumnEqual) {
        this.colIndexSetEqual.add(j);
      } else {
        int k = this.colIndexSetOther.get(j, 0);
        this.colIndexSetOther.put(j, k + 1);
      } 
      continue;
      b++;
    } 
  }
  
  void assignToRangeVariable(RangeVariable paramRangeVariable, RangeVariable.RangeVariableConditions paramRangeVariableConditions, int paramInt, HsqlList paramHsqlList) {
    if (paramHsqlList.isEmpty())
      return; 
    setIndexConditions(paramRangeVariableConditions, paramHsqlList, paramInt, true);
  }
  
  private void setIndexConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt, boolean paramBoolean) {
    this.colIndexSetEqual.clear();
    this.colIndexSetOther.clear();
    byte b = 0;
    int i = paramHsqlList.size();
    while (b < i) {
      Expression expression = (Expression)paramHsqlList.get(b);
      if (expression != null && expression.isIndexable(paramRangeVariableConditions.rangeVar)) {
        int m;
        int n;
        int k = expression.getType();
        switch (k) {
          case 50:
          case 2:
            break;
          case 40:
            if (expression.exprSubType == 52 || expression.exprSubType == 51 || expression.getLeftNode().getRangeVariable() != paramRangeVariableConditions.rangeVar)
              break; 
            m = expression.getLeftNode().getColumnIndex();
            this.colIndexSetEqual.add(m);
            break;
          case 47:
            if (expression.getLeftNode().getRangeVariable() != paramRangeVariableConditions.rangeVar || paramRangeVariableConditions.rangeVar.isLeftJoin)
              break; 
            m = expression.getLeftNode().getColumnIndex();
            this.colIndexSetEqual.add(m);
            break;
          case 48:
            if (expression.getLeftNode().getLeftNode().getRangeVariable() != paramRangeVariableConditions.rangeVar || paramRangeVariableConditions.rangeVar.isLeftJoin)
              break; 
            m = expression.getLeftNode().getLeftNode().getColumnIndex();
            n = this.colIndexSetOther.get(m, 0);
            this.colIndexSetOther.put(m, n + 1);
            break;
          case 41:
          case 42:
          case 43:
          case 44:
          case 45:
            if (expression.getLeftNode().getRangeVariable() != paramRangeVariableConditions.rangeVar)
              break; 
            m = expression.getLeftNode().getColumnIndex();
            n = this.colIndexSetOther.get(m, 0);
            this.colIndexSetOther.put(m, n + 1);
            break;
          default:
            Error.runtimeError(201, "RangeVariableResolver");
            break;
        } 
      } 
      b++;
    } 
    setEqualityConditions(paramRangeVariableConditions, paramHsqlList, paramInt);
    boolean bool = paramRangeVariableConditions.hasIndex();
    if (!bool) {
      setNonEqualityConditions(paramRangeVariableConditions, paramHsqlList, paramInt);
      bool = paramRangeVariableConditions.hasIndex();
    } 
    if (paramInt == 0 && this.sortAndSlice.usingIndex)
      bool = true; 
    b = 0;
    if (!bool && paramBoolean) {
      i = 0;
      int k = paramHsqlList.size();
      while (i < k) {
        Expression expression = (Expression)paramHsqlList.get(i);
        if (expression != null)
          if (expression.getType() == 50) {
            bool = setOrConditions(paramRangeVariableConditions, (ExpressionLogical)expression, paramInt);
            if (bool) {
              paramHsqlList.set(i, null);
              b = 1;
              break;
            } 
          } else if (expression.getType() == 40 && expression.exprSubType == 52 && paramInt < this.firstLeftJoinIndex && this.firstRightJoinIndex == this.rangeVariables.length && !expression.getRightNode().isCorrelated()) {
            OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
            ((ExpressionLogical)expression).addLeftColumnsForAllAny(paramRangeVariableConditions.rangeVar, orderedIntHashSet);
            Index.IndexUse[] arrayOfIndexUse = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumns(this.session, orderedIntHashSet, 40, false);
            if (arrayOfIndexUse.length != 0 && this.inExpressions[paramInt] == null) {
              this.inExpressions[paramInt] = expression;
              this.inInJoin[paramInt] = paramRangeVariableConditions.isJoin;
              this.inExpressionCount++;
              paramHsqlList.set(i, null);
              break;
            } 
          }  
        i++;
      } 
    } 
    i = 0;
    int j = paramHsqlList.size();
    while (i < j) {
      Expression expression = (Expression)paramHsqlList.get(i);
      if (expression != null)
        if (b != 0) {
          for (byte b1 = 0; b1 < paramRangeVariableConditions.rangeVar.joinConditions.length; b1++) {
            if (paramRangeVariableConditions.isJoin) {
              (paramRangeVariableConditions.rangeVar.joinConditions[b1]).nonIndexCondition = ExpressionLogical.andExpressions(expression, (paramRangeVariableConditions.rangeVar.joinConditions[b1]).nonIndexCondition);
            } else {
              (paramRangeVariableConditions.rangeVar.whereConditions[b1]).nonIndexCondition = ExpressionLogical.andExpressions(expression, (paramRangeVariableConditions.rangeVar.whereConditions[b1]).nonIndexCondition);
            } 
          } 
        } else {
          paramRangeVariableConditions.addCondition(expression);
        }  
      i++;
    } 
  }
  
  private boolean setOrConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, ExpressionLogical paramExpressionLogical, int paramInt) {
    // Byte code:
    //   0: new org/hsqldb/lib/HsqlArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload_2
    //   10: aload #4
    //   12: invokestatic decomposeOrConditions : (Lorg/hsqldb/Expression;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/Expression;
    //   15: pop
    //   16: aload #4
    //   18: invokevirtual size : ()I
    //   21: anewarray org/hsqldb/RangeVariable$RangeVariableConditions
    //   24: astore #5
    //   26: iconst_0
    //   27: istore #6
    //   29: iload #6
    //   31: aload #4
    //   33: invokevirtual size : ()I
    //   36: if_icmpge -> 115
    //   39: new org/hsqldb/lib/HsqlArrayList
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: astore #7
    //   48: aload #4
    //   50: iload #6
    //   52: invokevirtual get : (I)Ljava/lang/Object;
    //   55: checkcast org/hsqldb/Expression
    //   58: astore #8
    //   60: aload_0
    //   61: getfield session : Lorg/hsqldb/Session;
    //   64: aload #8
    //   66: aload #7
    //   68: invokestatic decomposeAndConditions : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/Expression;
    //   71: pop
    //   72: new org/hsqldb/RangeVariable$RangeVariableConditions
    //   75: dup
    //   76: aload_1
    //   77: invokespecial <init> : (Lorg/hsqldb/RangeVariable$RangeVariableConditions;)V
    //   80: astore #9
    //   82: aload_0
    //   83: aload #9
    //   85: aload #7
    //   87: iload_3
    //   88: iconst_0
    //   89: invokespecial setIndexConditions : (Lorg/hsqldb/RangeVariable$RangeVariableConditions;Lorg/hsqldb/lib/HsqlList;IZ)V
    //   92: aload #5
    //   94: iload #6
    //   96: aload #9
    //   98: aastore
    //   99: aload #9
    //   101: invokevirtual hasIndex : ()Z
    //   104: ifne -> 109
    //   107: iconst_0
    //   108: ireturn
    //   109: iinc #6, 1
    //   112: goto -> 29
    //   115: aconst_null
    //   116: astore #6
    //   118: iconst_0
    //   119: istore #7
    //   121: iload #7
    //   123: aload #5
    //   125: arraylength
    //   126: if_icmpge -> 243
    //   129: aload #5
    //   131: iload #7
    //   133: aaload
    //   134: astore #8
    //   136: aload #5
    //   138: iload #7
    //   140: aaload
    //   141: aload #6
    //   143: putfield excludeConditions : Lorg/hsqldb/Expression;
    //   146: iload #7
    //   148: aload #5
    //   150: arraylength
    //   151: iconst_1
    //   152: isub
    //   153: if_icmpne -> 159
    //   156: goto -> 243
    //   159: aconst_null
    //   160: astore #9
    //   162: aload #8
    //   164: getfield indexCond : [Lorg/hsqldb/Expression;
    //   167: ifnull -> 204
    //   170: iconst_0
    //   171: istore #10
    //   173: iload #10
    //   175: aload #8
    //   177: getfield indexedColumnCount : I
    //   180: if_icmpge -> 204
    //   183: aload #9
    //   185: aload #8
    //   187: getfield indexCond : [Lorg/hsqldb/Expression;
    //   190: iload #10
    //   192: aaload
    //   193: invokestatic andExpressions : (Lorg/hsqldb/Expression;Lorg/hsqldb/Expression;)Lorg/hsqldb/Expression;
    //   196: astore #9
    //   198: iinc #10, 1
    //   201: goto -> 173
    //   204: aload #9
    //   206: aload #8
    //   208: getfield indexEndCondition : Lorg/hsqldb/Expression;
    //   211: invokestatic andExpressions : (Lorg/hsqldb/Expression;Lorg/hsqldb/Expression;)Lorg/hsqldb/Expression;
    //   214: astore #9
    //   216: aload #9
    //   218: aload #8
    //   220: getfield nonIndexCondition : Lorg/hsqldb/Expression;
    //   223: invokestatic andExpressions : (Lorg/hsqldb/Expression;Lorg/hsqldb/Expression;)Lorg/hsqldb/Expression;
    //   226: astore #9
    //   228: aload #9
    //   230: aload #6
    //   232: invokestatic orExpressions : (Lorg/hsqldb/Expression;Lorg/hsqldb/Expression;)Lorg/hsqldb/Expression;
    //   235: astore #6
    //   237: iinc #7, 1
    //   240: goto -> 121
    //   243: aload #6
    //   245: ifnull -> 248
    //   248: aload_1
    //   249: getfield isJoin : Z
    //   252: ifeq -> 300
    //   255: aload_1
    //   256: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   259: aload #5
    //   261: putfield joinConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   264: aload #4
    //   266: invokevirtual size : ()I
    //   269: anewarray org/hsqldb/RangeVariable$RangeVariableConditions
    //   272: astore #5
    //   274: aload #5
    //   276: aload_1
    //   277: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   280: getfield whereConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   283: iconst_0
    //   284: aaload
    //   285: invokestatic fillArray : ([Ljava/lang/Object;Ljava/lang/Object;)V
    //   288: aload_1
    //   289: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   292: aload #5
    //   294: putfield whereConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   297: goto -> 342
    //   300: aload_1
    //   301: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   304: aload #5
    //   306: putfield whereConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   309: aload #4
    //   311: invokevirtual size : ()I
    //   314: anewarray org/hsqldb/RangeVariable$RangeVariableConditions
    //   317: astore #5
    //   319: aload #5
    //   321: aload_1
    //   322: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   325: getfield joinConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   328: iconst_0
    //   329: aaload
    //   330: invokestatic fillArray : ([Ljava/lang/Object;Ljava/lang/Object;)V
    //   333: aload_1
    //   334: getfield rangeVar : Lorg/hsqldb/RangeVariable;
    //   337: aload #5
    //   339: putfield joinConditions : [Lorg/hsqldb/RangeVariable$RangeVariableConditions;
    //   342: iconst_1
    //   343: ireturn
  }
  
  private void setEqualityConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt) {
    Index index = null;
    if (paramInt == 0 && this.sortAndSlice.usingIndex) {
      index = this.sortAndSlice.primaryTableIndex;
      if (index != null)
        paramRangeVariableConditions.rangeIndex = index; 
    } 
    if (index == null) {
      Index.IndexUse[] arrayOfIndexUse = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumns(this.session, this.colIndexSetEqual, 40, false);
      if (arrayOfIndexUse.length == 0)
        return; 
      index = (arrayOfIndexUse[0]).index;
      double d = Double.MAX_VALUE;
      if (arrayOfIndexUse.length > 1)
        for (byte b = 0; b < arrayOfIndexUse.length; b++) {
          PersistentStore persistentStore = paramRangeVariableConditions.rangeVar.rangeTable.getRowStore(this.session);
          double d1 = persistentStore.searchCost(this.session, (arrayOfIndexUse[b]).index, (arrayOfIndexUse[b]).columnCount, 40);
          if (d1 < d) {
            d = d1;
            index = (arrayOfIndexUse[b]).index;
          } 
        }  
    } 
    int[] arrayOfInt = index.getColumns();
    int i = arrayOfInt.length;
    Expression[] arrayOfExpression = new Expression[arrayOfInt.length];
    byte b1;
    for (b1 = 0; b1 < paramHsqlList.size(); b1++) {
      Expression expression = (Expression)paramHsqlList.get(b1);
      if (expression != null) {
        int j = expression.getType();
        if ((j == 40 || j == 47) && expression.getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar && expression.isIndexable(paramRangeVariableConditions.rangeVar)) {
          int k = ArrayUtil.find(arrayOfInt, expression.getLeftNode().getColumnIndex());
          if (k != -1 && arrayOfExpression[k] == null) {
            arrayOfExpression[k] = expression;
            paramHsqlList.set(b1, null);
          } 
        } 
      } 
    } 
    b1 = 0;
    for (byte b2 = 0; b2 < arrayOfExpression.length; b2++) {
      Expression expression = arrayOfExpression[b2];
      if (expression == null) {
        if (i == arrayOfInt.length)
          i = b2; 
        b1 = 1;
      } else if (b1 != 0) {
        paramHsqlList.add(expression);
        arrayOfExpression[b2] = null;
      } 
    } 
    if (i > 0)
      paramRangeVariableConditions.addIndexCondition(arrayOfExpression, index, i); 
  }
  
  private void setNonEqualityConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt) {
    if (this.colIndexSetOther.isEmpty())
      return; 
    int i = 0;
    Index index = null;
    if (paramInt == 0 && this.sortAndSlice.usingIndex)
      index = this.sortAndSlice.primaryTableIndex; 
    if (index == null) {
      Iterator iterator = this.colIndexSetOther.keySet().iterator();
      while (iterator.hasNext()) {
        int j = iterator.nextInt();
        int k = this.colIndexSetOther.get(j, 0);
        if (k > i) {
          Index index1 = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumn(this.session, j);
          if (index1 != null) {
            index = index1;
            i = k;
          } 
        } 
      } 
    } 
    if (index == null)
      return; 
    int[] arrayOfInt = index.getColumns();
    for (byte b = 0; b < paramHsqlList.size(); b++) {
      Expression expression = (Expression)paramHsqlList.get(b);
      if (expression != null) {
        boolean bool = false;
        switch (expression.getType()) {
          case 48:
            if (expression.getLeftNode().getType() == 47 && arrayOfInt[0] == expression.getLeftNode().getLeftNode().getColumnIndex())
              bool = true; 
            break;
          case 41:
          case 42:
          case 43:
          case 44:
          case 45:
            if (arrayOfInt[0] == expression.getLeftNode().getColumnIndex() && expression.getRightNode() != null && !expression.getRightNode().isCorrelated())
              bool = true; 
            break;
        } 
        if (bool) {
          Expression[] arrayOfExpression = new Expression[index.getColumnCount()];
          arrayOfExpression[0] = expression;
          paramRangeVariableConditions.addIndexCondition(arrayOfExpression, index, 1);
          paramHsqlList.set(b, null);
          break;
        } 
      } 
    } 
  }
  
  void setInConditionsAsTables() {
    for (int i = this.rangeVariables.length - 1; i >= 0; i--) {
      RangeVariable rangeVariable = this.rangeVariables[i];
      ExpressionLogical expressionLogical = (ExpressionLogical)this.inExpressions[i];
      if (expressionLogical != null) {
        OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
        expressionLogical.addLeftColumnsForAllAny(rangeVariable, orderedIntHashSet);
        Index.IndexUse[] arrayOfIndexUse = rangeVariable.rangeTable.getIndexForColumns(this.session, orderedIntHashSet, 40, false);
        Index index = (arrayOfIndexUse[0]).index;
        byte b1 = 0;
        for (byte b2 = 0; b2 < index.getColumnCount() && orderedIntHashSet.contains(index.getColumns()[b2]); b2++)
          b1++; 
        RangeVariable rangeVariable1 = new RangeVariable(expressionLogical.getRightNode().getTable(), null, null, null, this.compileContext);
        rangeVariable1.isGenerated = true;
        RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.length + 1];
        ArrayUtil.copyAdjustArray(this.rangeVariables, arrayOfRangeVariable, rangeVariable1, i, 1);
        this.rangeVariables = arrayOfRangeVariable;
        Expression[] arrayOfExpression = new Expression[index.getColumnCount()];
        byte b3;
        for (b3 = 0; b3 < b1; b3++) {
          int j = index.getColumns()[b3];
          int k = orderedIntHashSet.getIndex(j);
          ExpressionLogical expressionLogical1 = new ExpressionLogical(rangeVariable, j, rangeVariable1, k);
          arrayOfExpression[b3] = expressionLogical1;
        } 
        b3 = ((this.rangeVariables[i]).isLeftJoin || (this.rangeVariables[i]).isRightJoin) ? 1 : 0;
        RangeVariable.RangeVariableConditions rangeVariableConditions = (!this.inInJoin[i] && b3 != 0) ? rangeVariable.whereConditions[0] : rangeVariable.joinConditions[0];
        rangeVariableConditions.addIndexCondition(arrayOfExpression, index, b1);
        for (byte b4 = 0; b4 < orderedIntHashSet.size(); b4++) {
          int j = orderedIntHashSet.get(b4);
          byte b = b4;
          ExpressionLogical expressionLogical1 = new ExpressionLogical(rangeVariable, j, rangeVariable1, b);
          rangeVariableConditions.addCondition(expressionLogical1);
        } 
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RangeVariableResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */