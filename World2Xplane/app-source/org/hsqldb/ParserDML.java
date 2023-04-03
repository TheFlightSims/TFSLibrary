package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.types.Type;

public class ParserDML extends ParserDQL {
  ParserDML(Session paramSession, Scanner paramScanner) {
    super(paramSession, paramScanner, (ParserDQL.CompileContext)null);
  }
  
  StatementDMQL compileInsertStatement(RangeGroup[] paramArrayOfRangeGroup) {
    Expression expression2;
    int m;
    Expression expression1;
    byte b1;
    HsqlList hsqlList;
    Expression[] arrayOfExpression;
    byte b2;
    read();
    readThis(141);
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    RangeVariable rangeVariable = readRangeVariableForDataChange(50);
    rangeVariable.resolveRangeTableTypes(this.session, RangeVariable.emptyArray);
    Table table1 = rangeVariable.getTable();
    boolean[] arrayOfBoolean = null;
    int[] arrayOfInt = table1.getColumnMap();
    int i = table1.getColumnCount();
    int j = getPosition();
    Table table2 = table1.isTriggerInsertable() ? table1 : table1.getBaseTable();
    switch (this.token.tokenType) {
      case 78:
        read();
        readThis(308);
        expression2 = new Expression(25, new Expression[0]);
        expression2 = new Expression(26, new Expression[] { expression2 });
        arrayOfBoolean = table1.getNewColumnCheckList();
        for (b1 = 0; b1 < table1.colDefaults.length; b1++) {
          if (table1.colDefaults[b1] == null && table1.identityColumn != b1 && !table1.getColumn(b1).isGenerated())
            throw Error.error(5544); 
        } 
        return new StatementInsert(this.session, table1, arrayOfInt, expression2, arrayOfBoolean, this.compileContext);
      case 816:
        m = readOpenBrackets();
        if (m == 1) {
          b1 = 0;
          switch (this.token.tokenType) {
            case 251:
            case 278:
            case 319:
              rewind(j);
              b1 = 1;
              break;
          } 
          if (b1 != 0)
            break; 
          OrderedHashSet orderedHashSet = new OrderedHashSet();
          boolean bool = this.database.sqlSyntaxOra;
          readSimpleColumnNames(orderedHashSet, rangeVariable, bool);
          readThis(802);
          i = orderedHashSet.size();
          arrayOfInt = table1.getColumnIndexes(orderedHashSet);
          if (this.token.tokenType != 308 && this.token.tokenType != 461)
            break; 
        } else {
          rewind(j);
          break;
        } 
      case 461:
        if (this.token.tokenType == 461) {
          read();
          if (this.token.tokenType == 305) {
            read();
            bool1 = true;
          } else if (this.token.tokenType == 276) {
            read();
            bool2 = true;
          } else {
            unexpectedToken();
          } 
          readThis(307);
          if (this.token.tokenType != 308)
            break; 
        } 
      case 308:
        read();
        arrayOfBoolean = table1.getColumnCheckList(arrayOfInt);
        expression1 = XreadContextuallyTypedTable(i);
        hsqlList = expression1.resolveColumnReferences(this.session, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
        ExpressionColumn.checkColumnsResolved(hsqlList);
        expression1.resolveTypes(this.session, null);
        setParameterTypes(expression1, table1, arrayOfInt);
        if (table1 != table2) {
          int[] arrayOfInt1 = table1.getBaseTableColumnMap();
          int[] arrayOfInt2 = new int[arrayOfInt.length];
          ArrayUtil.projectRow(arrayOfInt1, arrayOfInt, arrayOfInt2);
          arrayOfInt = arrayOfInt2;
        } 
        arrayOfExpression = expression1.nodes;
        for (b2 = 0; b2 < arrayOfExpression.length; b2++) {
          Expression[] arrayOfExpression1 = (arrayOfExpression[b2]).nodes;
          for (byte b = 0; b < arrayOfExpression1.length; b++) {
            Expression expression = arrayOfExpression1[b];
            ColumnSchema columnSchema = table2.getColumn(arrayOfInt[b]);
            if (columnSchema.isIdentity()) {
              bool3 = true;
              if (expression.getType() != 4) {
                if (table2.identitySequence.isAlways() && !bool1 && !bool2)
                  throw Error.error(5543); 
                if (bool1)
                  arrayOfExpression1[b] = new ExpressionColumn(4); 
              } 
            } else if (!columnSchema.hasDefault()) {
              if (columnSchema.isGenerated()) {
                if (expression.getType() != 4)
                  throw Error.error(5541); 
              } else if (expression.getType() == 4) {
                throw Error.error(5544);
              } 
            } 
            if (expression.isUnresolvedParam())
              expression.setAttributesAsColumn(columnSchema, true); 
          } 
        } 
        if (!bool3 && (bool1 || bool2))
          unexpectedTokenRequire("OVERRIDING"); 
        return new StatementInsert(this.session, table1, arrayOfInt, expression1, arrayOfBoolean, this.compileContext);
      case 251:
      case 278:
      case 319:
        break;
      default:
        throw unexpectedToken();
    } 
    arrayOfBoolean = table1.getColumnCheckList(arrayOfInt);
    if (table1 != table2) {
      int[] arrayOfInt1 = table1.getBaseTableColumnMap();
      int[] arrayOfInt2 = new int[arrayOfInt.length];
      ArrayUtil.projectRow(arrayOfInt1, arrayOfInt, arrayOfInt2);
      arrayOfInt = arrayOfInt2;
    } 
    int k = table2.getIdentityColumnIndex();
    int n = -1;
    if (k != -1 && ArrayUtil.find(arrayOfInt, k) > -1) {
      if (table2.identitySequence.isAlways() && !bool1 && !bool2)
        throw Error.error(5543); 
      if (bool1)
        n = k; 
    } else if (bool1 || bool2) {
      unexpectedTokenRequire("OVERRIDING");
    } 
    Type[] arrayOfType = new Type[arrayOfInt.length];
    ArrayUtil.projectRow((Object[])table2.getColumnTypes(), arrayOfInt, (Object[])arrayOfType);
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    QueryExpression queryExpression = XreadQueryExpression();
    queryExpression.setReturningResult();
    queryExpression.resolve(this.session, paramArrayOfRangeGroup, arrayOfType);
    if (i != queryExpression.getColumnCount())
      throw Error.error(5546); 
    return new StatementInsert(this.session, table1, arrayOfInt, arrayOfBoolean, queryExpression, this.compileContext, n);
  }
  
  private static void setParameterTypes(Expression paramExpression, Table paramTable, int[] paramArrayOfint) {
    for (byte b = 0; b < paramExpression.nodes.length; b++) {
      Expression[] arrayOfExpression = (paramExpression.nodes[b]).nodes;
      for (byte b1 = 0; b1 < arrayOfExpression.length; b1++) {
        if (arrayOfExpression[b1].isUnresolvedParam())
          arrayOfExpression[b1].setAttributesAsColumn(paramTable.getColumn(paramArrayOfint[b1]), true); 
      } 
    } 
  }
  
  Statement compileTruncateStatement() {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    HsqlNameManager.HsqlName hsqlName = null;
    RangeVariable[] arrayOfRangeVariable = null;
    Table table = null;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = null;
    RangeVariable rangeVariable = null;
    readThis(295);
    if (this.token.tokenType == 278) {
      readThis(278);
      rangeVariable = readRangeVariableForDataChange(1215);
      arrayOfRangeVariable = new RangeVariable[] { rangeVariable };
      table = arrayOfRangeVariable[0].getTable();
      hsqlName = table.getName();
      bool1 = true;
    } else {
      readThis(497);
      hsqlName = readSchemaName();
    } 
    switch (this.token.tokenType) {
      case 376:
        read();
        readThis(128);
        break;
      case 484:
        read();
        readThis(128);
        bool4 = true;
        break;
    } 
    if (!bool1)
      checkIsThis(5); 
    if (readIfThis(5)) {
      readThis(44);
      bool2 = true;
      if (readIfThis(180)) {
        readThis(37);
        bool3 = true;
      } 
    } 
    if (bool1) {
      arrayOfHsqlName = new HsqlNameManager.HsqlName[] { table.getName() };
    } else {
      arrayOfHsqlName = this.session.database.schemaManager.getCatalogAndBaseTableNames();
    } 
    if (bool2) {
      Object[] arrayOfObject = { hsqlName, Boolean.valueOf(bool4), Boolean.valueOf(bool3) };
      return new StatementCommand(1215, arrayOfObject, null, arrayOfHsqlName);
    } 
    return new StatementDML(this.session, table, rangeVariable, arrayOfRangeVariable, this.compileContext, bool4, 1215);
  }
  
  Statement compileDeleteStatement(RangeGroup[] paramArrayOfRangeGroup) {
    Expression expression = null;
    boolean bool = false;
    readThis(79);
    readThis(115);
    RangeVariable rangeVariable = readRangeVariableForDataChange(19);
    RangeVariable[] arrayOfRangeVariable = { rangeVariable };
    Table table1 = arrayOfRangeVariable[0].getTable();
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    if (this.token.tokenType == 316) {
      read();
      expression = XreadBooleanValueExpression();
      RangeGroup.RangeGroupSimple rangeGroupSimple = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable, false);
      HsqlList hsqlList = expression.resolveColumnReferences(this.session, rangeGroupSimple, paramArrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      expression.resolveTypes(this.session, null);
      if (expression.isUnresolvedParam())
        expression.dataType = (Type)Type.SQL_BOOLEAN; 
      if (expression.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568); 
    } 
    Table table2 = table1.isTriggerDeletable() ? table1 : table1.getBaseTable();
    if (table1 != table2) {
      QuerySpecification querySpecification = table1.getQueryExpression().getMainSelect();
      if (expression != null)
        expression = expression.replaceColumnReferences(arrayOfRangeVariable[0], querySpecification.exprColumns); 
      expression = ExpressionLogical.andExpressions(querySpecification.queryCondition, expression);
      arrayOfRangeVariable = querySpecification.rangeVariables;
    } 
    if (expression != null) {
      arrayOfRangeVariable[0].addJoinCondition(expression);
      RangeVariableResolver rangeVariableResolver = new RangeVariableResolver(arrayOfRangeVariable, null, this.compileContext, false);
      rangeVariableResolver.processConditions(this.session);
      arrayOfRangeVariable = rangeVariableResolver.rangeVariables;
    } 
    for (byte b = 0; b < arrayOfRangeVariable.length; b++)
      arrayOfRangeVariable[b].resolveRangeTableTypes(this.session, RangeVariable.emptyArray); 
    return new StatementDML(this.session, table1, rangeVariable, arrayOfRangeVariable, this.compileContext, bool, 19);
  }
  
  StatementDMQL compileUpdateStatement(RangeGroup[] paramArrayOfRangeGroup) {
    read();
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    LongDeque longDeque = new LongDeque();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    RangeVariable rangeVariable = readRangeVariableForDataChange(82);
    RangeVariable[] arrayOfRangeVariable = { rangeVariable };
    RangeGroup.RangeGroupSimple rangeGroupSimple = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable, false);
    Table table1 = (arrayOfRangeVariable[0]).rangeTable;
    Table table2 = table1.isTriggerUpdatable() ? table1 : table1.getBaseTable();
    readThis(254);
    readSetClauseList(arrayOfRangeVariable, orderedHashSet, longDeque, hsqlArrayList);
    int[] arrayOfInt = new int[longDeque.size()];
    longDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression2 = new Expression[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfExpression2);
    for (byte b1 = 0; b1 < arrayOfExpression2.length; b1++)
      resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression2[b1]); 
    boolean[] arrayOfBoolean = table1.getColumnCheckList(arrayOfInt);
    Expression[] arrayOfExpression1 = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression1);
    Expression expression = null;
    if (this.token.tokenType == 316) {
      read();
      expression = XreadBooleanValueExpression();
      HsqlList hsqlList = expression.resolveColumnReferences(this.session, rangeGroupSimple, paramArrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      expression.resolveTypes(this.session, null);
      if (expression.isUnresolvedParam())
        expression.dataType = (Type)Type.SQL_BOOLEAN; 
      if (expression.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568); 
    } 
    resolveUpdateExpressions(table1, rangeGroupSimple, arrayOfInt, arrayOfExpression1, paramArrayOfRangeGroup);
    if (table1 != table2) {
      QuerySpecification querySpecification = ((TableDerived)table1).getQueryExpression().getMainSelect();
      if (expression != null)
        expression = expression.replaceColumnReferences(arrayOfRangeVariable[0], querySpecification.exprColumns); 
      for (byte b = 0; b < arrayOfExpression1.length; b++)
        arrayOfExpression1[b] = arrayOfExpression1[b].replaceColumnReferences(arrayOfRangeVariable[0], querySpecification.exprColumns); 
      expression = ExpressionLogical.andExpressions(querySpecification.queryCondition, expression);
      arrayOfRangeVariable = querySpecification.rangeVariables;
    } 
    if (expression != null) {
      arrayOfRangeVariable[0].addJoinCondition(expression);
      RangeVariableResolver rangeVariableResolver = new RangeVariableResolver(arrayOfRangeVariable, null, this.compileContext, false);
      rangeVariableResolver.processConditions(this.session);
      arrayOfRangeVariable = rangeVariableResolver.rangeVariables;
    } 
    for (byte b2 = 0; b2 < arrayOfRangeVariable.length; b2++)
      arrayOfRangeVariable[b2].resolveRangeTableTypes(this.session, RangeVariable.emptyArray); 
    if (table1 != table2) {
      int[] arrayOfInt1 = table1.getBaseTableColumnMap();
      int[] arrayOfInt2 = new int[arrayOfInt.length];
      ArrayUtil.projectRow(arrayOfInt1, arrayOfInt, arrayOfInt2);
      arrayOfInt = arrayOfInt2;
      for (byte b = 0; b < arrayOfInt.length; b++) {
        if (table2.colGenerated[arrayOfInt[b]])
          throw Error.error(5513); 
      } 
    } 
    return new StatementDML(this.session, arrayOfExpression2, table1, rangeVariable, arrayOfRangeVariable, arrayOfInt, arrayOfExpression1, arrayOfBoolean, this.compileContext);
  }
  
  void resolveUpdateExpressions(Table paramTable, RangeGroup paramRangeGroup, int[] paramArrayOfint, Expression[] paramArrayOfExpression, RangeGroup[] paramArrayOfRangeGroup) {
    HsqlList hsqlList = null;
    int i = -1;
    if (paramTable.hasIdentityColumn() && paramTable.identitySequence.isAlways())
      i = paramTable.getIdentityColumnIndex(); 
    byte b1 = 0;
    for (byte b2 = 0; b1 < paramArrayOfint.length; b2++) {
      Expression expression = paramArrayOfExpression[b2];
      if (paramTable.colGenerated[paramArrayOfint[b1]])
        throw Error.error(5513); 
      if (expression.getType() == 25) {
        Expression[] arrayOfExpression = expression.nodes;
        byte b = 0;
        while (b < arrayOfExpression.length) {
          Expression expression1 = arrayOfExpression[b];
          if (i == paramArrayOfint[b1] && expression1.getType() != 4)
            throw Error.error(5541); 
          if (expression1.isUnresolvedParam()) {
            expression1.setAttributesAsColumn(paramTable.getColumn(paramArrayOfint[b1]), true);
          } else if (expression1.getType() == 4) {
            if (paramTable.colDefaults[paramArrayOfint[b1]] == null && paramTable.identityColumn != paramArrayOfint[b1])
              throw Error.error(5544); 
          } else {
            hsqlList = expression.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
            ExpressionColumn.checkColumnsResolved(hsqlList);
            hsqlList = null;
            expression1.resolveTypes(this.session, null);
          } 
          b++;
          b1++;
        } 
      } else if (expression.getType() == 22) {
        hsqlList = expression.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
        ExpressionColumn.checkColumnsResolved(hsqlList);
        expression.resolveTypes(this.session, null);
        int j = expression.table.queryExpression.getColumnCount();
        byte b = 0;
        while (b < j) {
          if (i == paramArrayOfint[b1])
            throw Error.error(5541); 
          b++;
          b1++;
        } 
      } else {
        Expression expression1 = expression;
        if (i == paramArrayOfint[b1] && expression1.getType() != 4)
          throw Error.error(5541); 
        if (expression1.isUnresolvedParam()) {
          expression1.setAttributesAsColumn(paramTable.getColumn(paramArrayOfint[b1]), true);
        } else if (expression1.getType() == 4) {
          if (paramTable.colDefaults[paramArrayOfint[b1]] == null && paramTable.identityColumn != paramArrayOfint[b1])
            throw Error.error(5544); 
        } else {
          hsqlList = expression.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
          ExpressionColumn.checkColumnsResolved(hsqlList);
          expression1.resolveTypes(this.session, null);
        } 
        b1++;
      } 
    } 
  }
  
  void readSetClauseList(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet, LongDeque paramLongDeque, HsqlArrayList paramHsqlArrayList) {
    while (true) {
      byte b;
      if (this.token.tokenType == 816) {
        read();
        int k = paramOrderedHashSet.size();
        readTargetSpecificationList(paramOrderedHashSet, paramArrayOfRangeVariable, paramLongDeque);
        b = paramOrderedHashSet.size() - k;
        readThis(802);
      } else {
        Expression expression = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
        if (!paramOrderedHashSet.add(expression)) {
          ColumnSchema columnSchema = expression.getColumn();
          throw Error.error(5579, (columnSchema.getName()).name);
        } 
        b = 1;
      } 
      readThis(396);
      int i = getPosition();
      int j = readOpenBrackets();
      if (this.token.tokenType == 251) {
        rewind(i);
        TableDerived tableDerived = XreadSubqueryTableBody(22);
        if (b != tableDerived.queryExpression.getColumnCount())
          throw Error.error(5546); 
        Expression expression = new Expression(22, tableDerived);
        paramHsqlArrayList.add(expression);
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        break;
      } 
      if (j > 0)
        rewind(i); 
      if (b > 1) {
        readThis(816);
        Expression expression = readRow();
        readThis(802);
        byte b1 = (expression.getType() == 25) ? expression.nodes.length : 1;
        if (b != b1)
          throw Error.error(5546); 
        paramHsqlArrayList.add(expression);
      } else {
        Expression expression = XreadValueExpressionWithContext();
        paramHsqlArrayList.add(expression);
      } 
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      break;
    } 
  }
  
  void readGetClauseList(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet, LongDeque paramLongDeque, HsqlArrayList paramHsqlArrayList) {
    while (true) {
      int i;
      ExpressionColumn expressionColumn;
      Expression expression = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
      if (!paramOrderedHashSet.add(expression)) {
        ColumnSchema columnSchema = expression.getColumn();
        throw Error.error(5579, (columnSchema.getName()).name);
      } 
      readThis(396);
      switch (this.token.tokenType) {
        case 443:
        case 495:
          i = ExpressionColumn.diagnosticsList.getIndex(this.token.tokenString);
          expressionColumn = new ExpressionColumn(10, i);
          paramHsqlArrayList.add(expressionColumn);
          read();
          break;
      } 
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      break;
    } 
  }
  
  StatementDMQL compileMergeStatement(RangeGroup[] paramArrayOfRangeGroup) {
    int[] arrayOfInt1 = null;
    int[] arrayOfInt2 = null;
    Expression[] arrayOfExpression1 = null;
    HsqlArrayList hsqlArrayList1 = new HsqlArrayList();
    Expression[] arrayOfExpression2 = Expression.emptyArray;
    HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
    Expression expression2 = null;
    read();
    readThis(141);
    RangeVariable rangeVariable1 = readRangeVariableForDataChange(128);
    Table table = rangeVariable1.rangeTable;
    readThis(306);
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    RangeVariable rangeVariable2 = readTableOrSubquery();
    RangeVariable[] arrayOfRangeVariable1 = { rangeVariable1 };
    rangeVariable2.resolveRangeTable(this.session, new RangeGroup.RangeGroupSimple(arrayOfRangeVariable1, false), paramArrayOfRangeGroup);
    rangeVariable2.resolveRangeTableTypes(this.session, arrayOfRangeVariable1);
    this.compileContext.setOuterRanges(RangeGroup.emptyArray);
    readThis(194);
    Expression expression1 = XreadBooleanValueExpression();
    RangeVariable[] arrayOfRangeVariable2 = { rangeVariable2, rangeVariable1 };
    RangeVariable[] arrayOfRangeVariable3 = { rangeVariable2 };
    RangeVariable[] arrayOfRangeVariable4 = { rangeVariable1 };
    RangeGroup.RangeGroupSimple rangeGroupSimple1 = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable2, false);
    RangeGroup.RangeGroupSimple rangeGroupSimple2 = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable3, false);
    arrayOfInt1 = table.getColumnMap();
    boolean[] arrayOfBoolean = table.getNewColumnCheckList();
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    LongDeque longDeque = new LongDeque();
    readMergeWhen(longDeque, orderedHashSet2, orderedHashSet1, hsqlArrayList2, hsqlArrayList1, arrayOfRangeVariable4, rangeVariable2);
    if (hsqlArrayList2.size() > 0) {
      int i = orderedHashSet2.size();
      if (i != 0) {
        arrayOfInt1 = table.getColumnIndexes(orderedHashSet2);
        arrayOfBoolean = table.getColumnCheckList(arrayOfInt1);
      } 
      expression2 = (Expression)hsqlArrayList2.get(0);
      setParameterTypes(expression2, table, arrayOfInt1);
    } 
    if (hsqlArrayList1.size() > 0) {
      arrayOfExpression1 = new Expression[orderedHashSet1.size()];
      orderedHashSet1.toArray((Object[])arrayOfExpression1);
      for (byte b1 = 0; b1 < arrayOfExpression1.length; b1++)
        resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression1[b1]); 
      arrayOfExpression2 = new Expression[hsqlArrayList1.size()];
      hsqlArrayList1.toArray(arrayOfExpression2);
      arrayOfInt2 = new int[longDeque.size()];
      longDeque.toArray(arrayOfInt2);
    } 
    if (arrayOfExpression2.length != 0) {
      Table table1 = table.isTriggerUpdatable() ? table : table.getBaseTable();
      int[] arrayOfInt = arrayOfInt2;
      if (table != table1) {
        arrayOfInt = new int[arrayOfInt2.length];
        ArrayUtil.projectRow(table.getBaseTableColumnMap(), arrayOfInt2, arrayOfInt);
      } 
      resolveUpdateExpressions(table, rangeGroupSimple1, arrayOfInt2, arrayOfExpression2, paramArrayOfRangeGroup);
    } 
    HsqlList hsqlList = null;
    hsqlList = expression1.resolveColumnReferences(this.session, rangeGroupSimple1, paramArrayOfRangeGroup, null);
    ExpressionColumn.checkColumnsResolved(hsqlList);
    expression1.resolveTypes(this.session, null);
    if (expression1.isUnresolvedParam())
      expression1.dataType = (Type)Type.SQL_BOOLEAN; 
    if (expression1.getDataType() != Type.SQL_BOOLEAN)
      throw Error.error(5568); 
    arrayOfRangeVariable2[1].addJoinCondition(expression1);
    RangeVariableResolver rangeVariableResolver = new RangeVariableResolver(arrayOfRangeVariable2, null, this.compileContext, false);
    rangeVariableResolver.processConditions(this.session);
    arrayOfRangeVariable2 = rangeVariableResolver.rangeVariables;
    for (byte b = 0; b < arrayOfRangeVariable2.length; b++)
      arrayOfRangeVariable2[b].resolveRangeTableTypes(this.session, RangeVariable.emptyArray); 
    if (expression2 != null) {
      hsqlList = expression2.resolveColumnReferences(this.session, rangeGroupSimple2, RangeGroup.emptyArray, null);
      hsqlList = Expression.resolveColumnSet(this.session, RangeVariable.emptyArray, paramArrayOfRangeGroup, hsqlList);
      ExpressionColumn.checkColumnsResolved(hsqlList);
      expression2.resolveTypes(this.session, null);
    } 
    return new StatementDML(this.session, arrayOfExpression1, rangeVariable2, rangeVariable1, arrayOfRangeVariable2, arrayOfInt1, arrayOfInt2, arrayOfBoolean, expression1, expression2, arrayOfExpression2, this.compileContext);
  }
  
  private void readMergeWhen(LongDeque paramLongDeque, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2, RangeVariable[] paramArrayOfRangeVariable, RangeVariable paramRangeVariable) {
    Table table = (paramArrayOfRangeVariable[0]).rangeTable;
    int i = table.getColumnCount();
    readThis(314);
    if (this.token.tokenType == 437) {
      if (paramHsqlArrayList2.size() != 0)
        throw Error.error(5547); 
      read();
      readThis(280);
      readThis(303);
      readThis(254);
      readSetClauseList(paramArrayOfRangeVariable, paramOrderedHashSet2, paramLongDeque, paramHsqlArrayList2);
    } else if (this.token.tokenType == 183) {
      if (paramHsqlArrayList1.size() != 0)
        throw Error.error(5548); 
      read();
      readThis(437);
      readThis(280);
      readThis(135);
      int j = readOpenBrackets();
      if (j == 1) {
        boolean bool = this.database.sqlSyntaxOra;
        readSimpleColumnNames(paramOrderedHashSet1, paramArrayOfRangeVariable[0], bool);
        i = paramOrderedHashSet1.size();
        readThis(802);
        j = 0;
      } 
      readThis(308);
      Expression expression = XreadContextuallyTypedTable(i);
      if (expression.nodes.length != 1)
        throw Error.error(3201); 
      paramHsqlArrayList1.add(expression);
    } else {
      throw unexpectedToken();
    } 
    if (this.token.tokenType == 314)
      readMergeWhen(paramLongDeque, paramOrderedHashSet1, paramOrderedHashSet2, paramHsqlArrayList1, paramHsqlArrayList2, paramArrayOfRangeVariable, paramRangeVariable); 
  }
  
  StatementDMQL compileCallStatement(RangeGroup[] paramArrayOfRangeGroup, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual read : ()V
    //   4: aload_0
    //   5: invokevirtual isIdentifier : ()Z
    //   8: ifeq -> 391
    //   11: aload_0
    //   12: aload_0
    //   13: getfield token : Lorg/hsqldb/Token;
    //   16: getfield namePrePrefix : Ljava/lang/String;
    //   19: invokevirtual checkValidCatalogName : (Ljava/lang/String;)V
    //   22: aload_0
    //   23: getfield database : Lorg/hsqldb/Database;
    //   26: getfield schemaManager : Lorg/hsqldb/SchemaManager;
    //   29: aload_0
    //   30: getfield token : Lorg/hsqldb/Token;
    //   33: getfield tokenString : Ljava/lang/String;
    //   36: aload_0
    //   37: getfield session : Lorg/hsqldb/Session;
    //   40: aload_0
    //   41: getfield token : Lorg/hsqldb/Token;
    //   44: getfield namePrefix : Ljava/lang/String;
    //   47: invokevirtual getSchemaName : (Ljava/lang/String;)Ljava/lang/String;
    //   50: bipush #17
    //   52: invokevirtual findSchemaObject : (Ljava/lang/String;Ljava/lang/String;I)Lorg/hsqldb/SchemaObject;
    //   55: checkcast org/hsqldb/RoutineSchema
    //   58: astore_3
    //   59: aload_3
    //   60: ifnull -> 391
    //   63: aload_0
    //   64: invokevirtual read : ()V
    //   67: new org/hsqldb/lib/HsqlArrayList
    //   70: dup
    //   71: invokespecial <init> : ()V
    //   74: astore #4
    //   76: aload_0
    //   77: sipush #816
    //   80: invokevirtual readThis : (I)V
    //   83: aload_0
    //   84: getfield token : Lorg/hsqldb/Token;
    //   87: getfield tokenType : I
    //   90: sipush #802
    //   93: if_icmpne -> 103
    //   96: aload_0
    //   97: invokevirtual read : ()V
    //   100: goto -> 150
    //   103: aload_0
    //   104: invokevirtual XreadValueExpression : ()Lorg/hsqldb/Expression;
    //   107: astore #5
    //   109: aload #4
    //   111: aload #5
    //   113: invokevirtual add : (Ljava/lang/Object;)Z
    //   116: pop
    //   117: aload_0
    //   118: getfield token : Lorg/hsqldb/Token;
    //   121: getfield tokenType : I
    //   124: sipush #804
    //   127: if_icmpne -> 137
    //   130: aload_0
    //   131: invokevirtual read : ()V
    //   134: goto -> 147
    //   137: aload_0
    //   138: sipush #802
    //   141: invokevirtual readThis : (I)V
    //   144: goto -> 150
    //   147: goto -> 103
    //   150: aload #4
    //   152: invokevirtual size : ()I
    //   155: anewarray org/hsqldb/Expression
    //   158: astore #5
    //   160: aload #4
    //   162: aload #5
    //   164: invokevirtual toArray : (Ljava/lang/Object;)Ljava/lang/Object;
    //   167: pop
    //   168: aload_3
    //   169: aload #5
    //   171: arraylength
    //   172: invokevirtual getSpecificRoutine : (I)Lorg/hsqldb/Routine;
    //   175: astore #6
    //   177: aload_0
    //   178: getfield compileContext : Lorg/hsqldb/ParserDQL$CompileContext;
    //   181: aload #6
    //   183: invokevirtual addProcedureCall : (Lorg/hsqldb/Routine;)V
    //   186: aconst_null
    //   187: astore #7
    //   189: iconst_0
    //   190: istore #8
    //   192: iload #8
    //   194: aload #5
    //   196: arraylength
    //   197: if_icmpge -> 301
    //   200: aload #5
    //   202: iload #8
    //   204: aaload
    //   205: astore #9
    //   207: aload #9
    //   209: invokevirtual isUnresolvedParam : ()Z
    //   212: ifeq -> 240
    //   215: aload #9
    //   217: aload #6
    //   219: iload #8
    //   221: invokevirtual getParameter : (I)Lorg/hsqldb/ColumnSchema;
    //   224: aload #6
    //   226: iload #8
    //   228: invokevirtual getParameter : (I)Lorg/hsqldb/ColumnSchema;
    //   231: invokevirtual isWriteable : ()Z
    //   234: invokevirtual setAttributesAsColumn : (Lorg/hsqldb/ColumnSchema;Z)V
    //   237: goto -> 295
    //   240: aload #6
    //   242: iload #8
    //   244: invokevirtual getParameter : (I)Lorg/hsqldb/ColumnSchema;
    //   247: invokevirtual getParameterMode : ()B
    //   250: istore #10
    //   252: aload #5
    //   254: iload #8
    //   256: aaload
    //   257: aload_0
    //   258: getfield session : Lorg/hsqldb/Session;
    //   261: getstatic org/hsqldb/RangeGroup.emptyGroup : Lorg/hsqldb/RangeGroup;
    //   264: aload_1
    //   265: aload #7
    //   267: invokevirtual resolveColumnReferences : (Lorg/hsqldb/Session;Lorg/hsqldb/RangeGroup;[Lorg/hsqldb/RangeGroup;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/lib/HsqlList;
    //   270: astore #7
    //   272: iload #10
    //   274: iconst_1
    //   275: if_icmpeq -> 295
    //   278: aload #9
    //   280: invokevirtual getType : ()I
    //   283: bipush #6
    //   285: if_icmpeq -> 295
    //   288: sipush #5603
    //   291: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   294: athrow
    //   295: iinc #8, 1
    //   298: goto -> 192
    //   301: aload #7
    //   303: invokestatic checkColumnsResolved : (Lorg/hsqldb/lib/HsqlList;)V
    //   306: iconst_0
    //   307: istore #8
    //   309: iload #8
    //   311: aload #5
    //   313: arraylength
    //   314: if_icmpge -> 367
    //   317: aload #5
    //   319: iload #8
    //   321: aaload
    //   322: aload_0
    //   323: getfield session : Lorg/hsqldb/Session;
    //   326: aconst_null
    //   327: invokevirtual resolveTypes : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;)V
    //   330: aload #6
    //   332: iload #8
    //   334: invokevirtual getParameter : (I)Lorg/hsqldb/ColumnSchema;
    //   337: invokevirtual getDataType : ()Lorg/hsqldb/types/Type;
    //   340: aload #5
    //   342: iload #8
    //   344: aaload
    //   345: invokevirtual getDataType : ()Lorg/hsqldb/types/Type;
    //   348: invokevirtual canBeAssignedFrom : (Lorg/hsqldb/types/Type;)Z
    //   351: ifne -> 361
    //   354: sipush #5561
    //   357: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   360: athrow
    //   361: iinc #8, 1
    //   364: goto -> 309
    //   367: new org/hsqldb/StatementProcedure
    //   370: dup
    //   371: aload_0
    //   372: getfield session : Lorg/hsqldb/Session;
    //   375: aload #6
    //   377: aload #5
    //   379: aload_0
    //   380: getfield compileContext : Lorg/hsqldb/ParserDQL$CompileContext;
    //   383: invokespecial <init> : (Lorg/hsqldb/Session;Lorg/hsqldb/Routine;[Lorg/hsqldb/Expression;Lorg/hsqldb/ParserDQL$CompileContext;)V
    //   386: astore #8
    //   388: aload #8
    //   390: areturn
    //   391: iload_2
    //   392: ifeq -> 409
    //   395: sipush #5501
    //   398: aload_0
    //   399: getfield token : Lorg/hsqldb/Token;
    //   402: getfield tokenString : Ljava/lang/String;
    //   405: invokestatic error : (ILjava/lang/String;)Lorg/hsqldb/HsqlException;
    //   408: athrow
    //   409: aload_0
    //   410: invokevirtual XreadValueExpression : ()Lorg/hsqldb/Expression;
    //   413: astore_3
    //   414: aload_3
    //   415: aload_0
    //   416: getfield session : Lorg/hsqldb/Session;
    //   419: getstatic org/hsqldb/RangeGroup.emptyGroup : Lorg/hsqldb/RangeGroup;
    //   422: aload_1
    //   423: aconst_null
    //   424: invokevirtual resolveColumnReferences : (Lorg/hsqldb/Session;Lorg/hsqldb/RangeGroup;[Lorg/hsqldb/RangeGroup;Lorg/hsqldb/lib/HsqlList;)Lorg/hsqldb/lib/HsqlList;
    //   427: astore #4
    //   429: aload #4
    //   431: invokestatic checkColumnsResolved : (Lorg/hsqldb/lib/HsqlList;)V
    //   434: aload_3
    //   435: aload_0
    //   436: getfield session : Lorg/hsqldb/Session;
    //   439: aconst_null
    //   440: invokevirtual resolveTypes : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;)V
    //   443: new org/hsqldb/StatementProcedure
    //   446: dup
    //   447: aload_0
    //   448: getfield session : Lorg/hsqldb/Session;
    //   451: aload_3
    //   452: aload_0
    //   453: getfield compileContext : Lorg/hsqldb/ParserDQL$CompileContext;
    //   456: invokespecial <init> : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;Lorg/hsqldb/ParserDQL$CompileContext;)V
    //   459: astore #5
    //   461: aload #5
    //   463: areturn
  }
  
  void resolveOuterReferencesAndTypes(RangeGroup[] paramArrayOfRangeGroup, Expression paramExpression) {
    HsqlList hsqlList = paramExpression.resolveColumnReferences(this.session, RangeGroup.emptyGroup, 0, paramArrayOfRangeGroup, null, false);
    ExpressionColumn.checkColumnsResolved(hsqlList);
    paramExpression.resolveTypes(this.session, null);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserDML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */