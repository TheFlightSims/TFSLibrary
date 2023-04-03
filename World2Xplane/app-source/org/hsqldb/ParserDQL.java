package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntKeyHashMap;
import org.hsqldb.map.BitMap;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;
import org.hsqldb.types.Types;

public class ParserDQL extends ParserBase {
  protected Database database;
  
  protected Session session;
  
  protected final CompileContext compileContext;
  
  HsqlException lastError;
  
  ParserDQL(Session paramSession, Scanner paramScanner, CompileContext paramCompileContext) {
    super(paramScanner);
    this.session = paramSession;
    this.database = paramSession.getDatabase();
    this.compileContext = new CompileContext(paramSession, this, paramCompileContext);
  }
  
  void reset(String paramString) {
    super.reset(paramString);
    this.compileContext.reset();
    this.lastError = null;
  }
  
  void checkIsSchemaObjectName() {
    if (this.database.sqlEnforceNames) {
      checkIsNonReservedIdentifier();
    } else {
      checkIsNonCoreReservedIdentifier();
    } 
    if (this.database.sqlRegularNames)
      checkIsIrregularCharInIdentifier(); 
  }
  
  Type readTypeDefinition(boolean paramBoolean1, boolean paramBoolean2) {
    ArrayType arrayType;
    int i = Integer.MIN_VALUE;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    checkIsIdentifier();
    if (this.token.namePrefix == null)
      i = Type.getTypeNr(this.token.tokenString); 
    if (this.database.sqlSyntaxOra && !this.session.isProcessingScript() && i == 91) {
      read();
      return (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
    } 
    if (i == Integer.MIN_VALUE) {
      if (paramBoolean2) {
        checkIsSchemaObjectName();
        String str = this.session.getSchemaName(this.token.namePrefix);
        Type type1 = this.database.schemaManager.getDomainOrUDT(this.token.tokenString, str, false);
        if (type1 != null) {
          getRecordedToken().setExpression(type1);
          this.compileContext.addSchemaObject((SchemaObject)type1);
          read();
          return type1;
        } 
      } 
      if (this.token.namePrefix != null)
        throw Error.error(5509, this.token.tokenString); 
      if (this.database.sqlSyntaxOra)
        switch (this.token.tokenType) {
          case 563:
          case 564:
            read();
            return (Type)Type.SQL_DOUBLE;
          case 603:
            read();
            if (this.token.tokenType == 619) {
              read();
              return Type.getType(61, null, null, 1073741824L, 0);
            } 
            return Type.getType(12, null, this.database.collation, 1073741824L, 0);
          case 452:
            read();
            if (this.token.tokenType == 816) {
              read();
              int k = readInteger();
              int m = 0;
              if (this.token.tokenType == 804) {
                read();
                m = readInteger();
              } 
              readThis(802);
              return Type.getType(3, null, null, k, m);
            } 
            return (Type)Type.SQL_DECIMAL_DEFAULT;
          case 619:
            i = 61;
            break;
          case 635:
            bool5 = true;
            i = 12;
            break;
          case 612:
            i = 12;
            break;
          case 177:
            i = 1;
            break;
        }  
      if (this.database.sqlSyntaxMys || this.database.sqlSyntaxPgs)
        switch (this.token.tokenType) {
          case 797:
            if (this.database.sqlSyntaxMys) {
              i = 12;
              bool5 = true;
            } 
            break;
          case 632:
            i = -1;
            bool5 = true;
            break;
          case 793:
          case 795:
            if (this.database.sqlSyntaxMys) {
              i = -1;
              bool5 = true;
            } 
            break;
          case 573:
            if (this.database.sqlSyntaxPgs)
              i = 100; 
            break;
          case 796:
            if (this.database.sqlSyntaxMys)
              i = -3; 
            break;
          case 792:
          case 794:
            if (this.database.sqlSyntaxMys)
              i = -4; 
            break;
        }  
      if (i == Integer.MIN_VALUE)
        throw Error.error(5509, this.token.tokenString); 
    } 
    read();
    switch (i) {
      case 1:
        if (this.token.tokenType == 313) {
          read();
          i = 12;
          break;
        } 
        if (this.token.tokenType == 147) {
          read();
          readThis(453);
          i = 40;
          break;
        } 
        if (this.database.sqlSyntaxOra)
          bool5 = true; 
        break;
      case 8:
        if (this.token.tokenType == 212)
          read(); 
        break;
      case 60:
        if (this.token.tokenType == 313) {
          read();
          i = 61;
          break;
        } 
        if (this.token.tokenType == 147) {
          read();
          readThis(453);
          i = 30;
        } 
        break;
      case 14:
        if (this.token.tokenType == 313) {
          read();
          i = 15;
        } 
        break;
      case 10:
        return (Type)readIntervalType(false);
    } 
    long l = (i == 93) ? 6L : 0L;
    int j = 0;
    if (Types.requiresPrecision(i) && this.token.tokenType != 816 && this.database.sqlEnforceSize && !this.session.isProcessingScript())
      throw Error.error(5599, Type.getDefaultType(i).getNameString()); 
    if (Types.acceptsPrecision(i)) {
      if (this.token.tokenType == 816) {
        int k = 1;
        read();
        switch (this.token.tokenType) {
          case 869:
            if (this.token.dataType.typeCode != 4 && this.token.dataType.typeCode != 25)
              throw unexpectedToken(); 
            break;
          case 876:
            if (i == 30 || i == 40 || i == 61 || i == 12) {
              switch (this.token.lobMultiplierType) {
                case 426:
                  k = 1024;
                  break;
                case 435:
                  k = 1048576;
                  break;
                case 405:
                  k = 1073741824;
                  break;
              } 
              throw unexpectedToken();
            } 
            throw unexpectedToken(this.token.getFullString());
          default:
            throw unexpectedToken();
        } 
        bool1 = true;
        l = ((Number)this.token.tokenValue).longValue();
        if (l < 0L || (l == 0L && !Types.acceptsZeroPrecision(i)))
          throw Error.error(5592); 
        l *= k;
        read();
        if (i == 1 || i == 12 || i == 40)
          if (this.token.tokenType == 355) {
            read();
          } else if (this.token.tokenType == 454) {
            read();
          }  
        if (Types.acceptsScaleCreateParam(i) && this.token.tokenType == 804) {
          read();
          j = readInteger();
          if (j < 0)
            throw Error.error(5592); 
          bool2 = true;
        } 
        if (bool5 && !readIfThis(33))
          readIfThis(568); 
        readThis(802);
      } else if (i == 14) {
        l = 1L;
      } else if (i == 30 || i == 40) {
        l = 1073741824L;
      } else if (this.database.sqlEnforceSize && (i == 1 || i == 60)) {
        l = 1L;
      } 
      if (i == 93 || i == 92) {
        if (l > 9L)
          throw Error.error(5592); 
        j = (int)l;
        l = 0L;
        if (this.token.tokenType == 319) {
          read();
          readThis(281);
          readThis(549);
          if (i == 93) {
            i = 95;
          } else {
            i = 94;
          } 
        } else if (this.token.tokenType == 321) {
          read();
          readThis(281);
          readThis(549);
        } 
      } 
    } 
    switch (i) {
      case -1:
        if (this.database.sqlLongvarIsLob) {
          i = 40;
          l = 1073741824L;
          break;
        } 
        i = 12;
        if (!bool1)
          l = 16777216L; 
        break;
      case -4:
        if (this.database.sqlLongvarIsLob) {
          i = 30;
          l = 1073741824L;
          break;
        } 
        i = 61;
        if (!bool1)
          l = 16777216L; 
        break;
      case 1:
        if (this.database.sqlSyntaxDb2 && readIfThis(112)) {
          readThis(565);
          readThis(378);
          i = 60;
          break;
        } 
      case 40:
        bool3 = true;
        break;
      case 100:
        i = 12;
        bool4 = true;
      case 12:
        if (this.database.sqlSyntaxDb2 && readIfThis(112)) {
          readThis(565);
          readThis(378);
          i = 61;
          if (!bool1)
            l = 32768L; 
          break;
        } 
        bool3 = true;
        if (!bool1)
          l = 32768L; 
        if (this.session.isIgnorecase() && !this.session.isProcessingScript())
          bool4 = true; 
        if (l > 2147483647L)
          throw Error.error(5592); 
        break;
      case 61:
        if (!bool1)
          l = 32768L; 
        if (l > 2147483647L)
          throw Error.error(5592); 
        break;
      case 2:
      case 3:
        if (!bool1 && !bool2 && !this.database.sqlEnforceSize) {
          l = 128L;
          j = 32;
        } 
        break;
    } 
    Collation collation = this.database.collation;
    Charset charset = null;
    if (bool3 && paramBoolean1) {
      if (this.token.tokenType == 35) {
        read();
        readThis(254);
        checkIsSchemaObjectName();
        charset = (Charset)this.database.schemaManager.getCharacterSet(this.session, this.token.tokenString, this.token.namePrefix);
        read();
      } 
      if (this.token.tokenType == 41) {
        read();
        checkIsSimpleName();
        collation = this.database.schemaManager.getCollation(this.session, this.token.tokenString, this.token.namePrefix);
        read();
      } else if (bool4) {
        collation = Collation.getUpperCaseCompareCollation(collation);
      } 
    } 
    Type type = Type.getType(i, charset, collation, l, j);
    if (this.token.tokenType == 8) {
      if (type.isLobType())
        throw unexpectedToken(); 
      read();
      int k = 1024;
      if (this.token.tokenType == 811) {
        read();
        k = readInteger();
        if (j < 0)
          throw Error.error(5592); 
        readThis(820);
      } 
      arrayType = new ArrayType(type, k);
    } 
    return (Type)arrayType;
  }
  
  void readSimpleColumnNames(OrderedHashSet paramOrderedHashSet, RangeVariable paramRangeVariable, boolean paramBoolean) {
    while (true) {
      ColumnSchema columnSchema = readSimpleColumnName(paramRangeVariable, paramBoolean);
      if (!paramOrderedHashSet.add((columnSchema.getName()).name))
        throw Error.error(5579, (columnSchema.getName()).name); 
      if (readIfThis(804))
        continue; 
      if (this.token.tokenType == 802)
        return; 
      throw unexpectedToken();
    } 
  }
  
  void readTargetSpecificationList(OrderedHashSet paramOrderedHashSet, RangeVariable[] paramArrayOfRangeVariable, LongDeque paramLongDeque) {
    while (true) {
      Expression expression = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
      if (!paramOrderedHashSet.add(expression)) {
        ColumnSchema columnSchema = expression.getColumn();
        throw Error.error(5579, (columnSchema.getName()).name);
      } 
      if (readIfThis(804))
        continue; 
      if (this.token.tokenType == 802 || this.token.tokenType == 115)
        return; 
      throw unexpectedToken();
    } 
  }
  
  int[] readColumnList(Table paramTable, boolean paramBoolean) {
    OrderedHashSet orderedHashSet = readColumnNames(paramBoolean);
    return paramTable.getColumnIndexes(orderedHashSet);
  }
  
  void readSimpleColumnNames(OrderedHashSet paramOrderedHashSet, Table paramTable, boolean paramBoolean) {
    while (true) {
      ColumnSchema columnSchema = readSimpleColumnName(paramTable, paramBoolean);
      if (!paramOrderedHashSet.add((columnSchema.getName()).name))
        throw Error.error(5577, (columnSchema.getName()).name); 
      if (readIfThis(804))
        continue; 
      if (this.token.tokenType == 802)
        return; 
      throw unexpectedToken();
    } 
  }
  
  HsqlNameManager.HsqlName[] readColumnNames(HsqlNameManager.HsqlName paramHsqlName) {
    BitMap bitMap = new BitMap(32, true);
    OrderedHashSet orderedHashSet = readColumnNames(bitMap, false);
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[orderedHashSet.size()];
    for (byte b = 0; b < arrayOfHsqlName.length; b++) {
      String str = (String)orderedHashSet.get(b);
      boolean bool = bitMap.isSet(b);
      arrayOfHsqlName[b] = this.database.nameManager.newHsqlName(paramHsqlName.schema, str, bool, 9, paramHsqlName);
    } 
    return arrayOfHsqlName;
  }
  
  OrderedHashSet readColumnNames(boolean paramBoolean) {
    return readColumnNames((BitMap)null, paramBoolean);
  }
  
  OrderedHashSet readColumnNames(BitMap paramBitMap, boolean paramBoolean) {
    readThis(816);
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    readColumnNameList(orderedHashSet, paramBitMap, paramBoolean);
    readThis(802);
    return orderedHashSet;
  }
  
  void readColumnNameList(OrderedHashSet paramOrderedHashSet, BitMap paramBitMap, boolean paramBoolean) {
    byte b = 0;
    while (true) {
      if (this.session.isProcessingScript()) {
        if (!isSimpleName())
          this.token.isDelimitedIdentifier = true; 
      } else {
        checkIsSimpleName();
      } 
      if (!paramOrderedHashSet.add(this.token.tokenString))
        throw Error.error(5577, this.token.tokenString); 
      if (paramBitMap != null)
        paramBitMap.setValue(b, isDelimitedIdentifier()); 
      read();
      b++;
      if (paramBoolean && (this.token.tokenType == 338 || this.token.tokenType == 389))
        read(); 
      if (readIfThis(804))
        continue; 
      break;
    } 
  }
  
  HsqlNameManager.SimpleName[] readColumnNameList(OrderedHashSet paramOrderedHashSet) {
    BitMap bitMap = new BitMap(32, true);
    readThis(816);
    readColumnNameList(paramOrderedHashSet, bitMap, false);
    readThis(802);
    HsqlNameManager.SimpleName[] arrayOfSimpleName = new HsqlNameManager.SimpleName[paramOrderedHashSet.size()];
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      HsqlNameManager.SimpleName simpleName = HsqlNameManager.getSimpleName((String)paramOrderedHashSet.get(b), bitMap.isSet(b));
      arrayOfSimpleName[b] = simpleName;
    } 
    return arrayOfSimpleName;
  }
  
  int XreadUnionType() {
    byte b = 0;
    switch (this.token.tokenType) {
      case 298:
        read();
        b = 1;
        if (this.token.tokenType == 2) {
          b = 2;
          read();
          break;
        } 
        if (this.token.tokenType == 85)
          read(); 
        break;
      case 138:
        read();
        b = 3;
        if (this.token.tokenType == 2) {
          b = 4;
          read();
          break;
        } 
        if (this.token.tokenType == 85)
          read(); 
        break;
      case 98:
      case 609:
        read();
        b = 6;
        if (this.token.tokenType == 2) {
          b = 5;
          read();
          break;
        } 
        if (this.token.tokenType == 85)
          read(); 
        break;
    } 
    return b;
  }
  
  void XreadUnionCorrespondingClause(QueryExpression paramQueryExpression) {
    if (this.token.tokenType == 51) {
      read();
      paramQueryExpression.setUnionCorresoponding();
      if (this.token.tokenType == 24) {
        read();
        OrderedHashSet orderedHashSet = readColumnNames(false);
        paramQueryExpression.setUnionCorrespondingColumns(orderedHashSet);
      } 
    } 
  }
  
  QueryExpression XreadQueryExpression() {
    if (this.token.tokenType == 319) {
      read();
      boolean bool = readIfThis(220);
      this.compileContext.initSubqueryNames();
      while (true) {
        checkIsSimpleName();
        HsqlNameManager.HsqlName[] arrayOfHsqlName = null;
        HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, isDelimitedIdentifier(), 27);
        hsqlName.schema = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
        read();
        if (this.token.tokenType == 816) {
          arrayOfHsqlName = readColumnNames(hsqlName);
        } else if (bool) {
          unexpectedTokenRequire("(");
        } 
        readThis(10);
        readThis(816);
        TableDerived tableDerived = XreadTableNamedSubqueryBody(hsqlName, arrayOfHsqlName, bool ? 24 : 23);
        readThis(802);
        if (this.token.tokenType == 71)
          throw unsupportedFeature(); 
        if (bool && this.token.tokenType == 71) {
          TableDerived tableDerived1 = tableDerived;
          int[] arrayOfInt = readColumnList(tableDerived1, false);
          readThis(254);
          checkIsSimpleName();
          HsqlNameManager.HsqlName hsqlName1 = this.database.nameManager.newColumnHsqlName(tableDerived1.getName(), this.token.tokenString, this.token.isDelimitedIdentifier);
          ColumnSchema columnSchema1 = new ColumnSchema(hsqlName1, null, true, false, null);
          if (tableDerived1.getColumnIndex(hsqlName1.name) != -1)
            throw Error.error(5578, this.token.tokenString); 
          read();
          readThis(285);
          String str1 = readQuotedString();
          if (str1.length() != 1)
            throw unexpectedToken(str1); 
          readThis(78);
          String str2 = readQuotedString();
          if (str2.length() != 1)
            throw unexpectedToken(str2); 
          if (str1.equals(str2))
            throw unexpectedToken(str1); 
          readThis(306);
          checkIsSimpleName();
          checkIsSimpleName();
          hsqlName1 = this.database.nameManager.newColumnHsqlName(tableDerived1.getName(), this.token.tokenString, this.token.isDelimitedIdentifier);
          if (tableDerived1.getColumnIndex(hsqlName1.name) != -1)
            throw Error.error(5578, this.token.tokenString); 
          read();
          ColumnSchema columnSchema2 = new ColumnSchema(hsqlName1, null, true, false, null);
        } 
        this.compileContext.registerSubquery(hsqlName.name, tableDerived);
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        break;
      } 
    } 
    QueryExpression queryExpression = XreadQueryExpressionBody();
    SortAndSlice sortAndSlice = XreadOrderByExpression();
    if (queryExpression.sortAndSlice == null) {
      queryExpression.addSortAndSlice(sortAndSlice);
    } else if (queryExpression.sortAndSlice.hasLimit()) {
      if (sortAndSlice.hasLimit())
        throw Error.error(5549); 
      for (byte b = 0; b < sortAndSlice.exprList.size(); b++) {
        Expression expression = (Expression)sortAndSlice.exprList.get(b);
        queryExpression.sortAndSlice.addOrderExpression(expression);
      } 
    } else {
      queryExpression.addSortAndSlice(sortAndSlice);
    } 
    this.compileContext.unregisterSubqueries();
    return queryExpression;
  }
  
  QueryExpression XreadQueryExpressionBody() {
    QueryExpression queryExpression = XreadQueryTerm();
    while (true) {
      switch (this.token.tokenType) {
        case 98:
        case 298:
        case 609:
          queryExpression = XreadSetOperation(queryExpression);
          continue;
      } 
      return queryExpression;
    } 
  }
  
  QueryExpression XreadQueryTerm() {
    QueryExpression queryExpression;
    for (queryExpression = XreadQueryPrimary(); this.token.tokenType == 138; queryExpression = XreadSetOperation(queryExpression));
    return queryExpression;
  }
  
  private QueryExpression XreadSetOperation(QueryExpression paramQueryExpression) {
    paramQueryExpression = new QueryExpression(this.compileContext, paramQueryExpression);
    int i = XreadUnionType();
    XreadUnionCorrespondingClause(paramQueryExpression);
    QueryExpression queryExpression = XreadQueryTerm();
    paramQueryExpression.addUnion(queryExpression, i);
    return paramQueryExpression;
  }
  
  QueryExpression XreadQueryPrimary() {
    QueryExpression queryExpression;
    SortAndSlice sortAndSlice;
    switch (this.token.tokenType) {
      case 251:
      case 278:
      case 308:
        return XreadSimpleTable();
      case 816:
        read();
        queryExpression = XreadQueryExpressionBody();
        sortAndSlice = XreadOrderByExpression();
        readThis(802);
        if (queryExpression.sortAndSlice == null) {
          queryExpression.addSortAndSlice(sortAndSlice);
        } else if (queryExpression.sortAndSlice.hasLimit()) {
          if (sortAndSlice.hasLimit())
            throw Error.error(5549); 
          for (byte b = 0; b < sortAndSlice.exprList.size(); b++) {
            Expression expression = (Expression)sortAndSlice.exprList.get(b);
            queryExpression.sortAndSlice.addOrderExpression(expression);
          } 
        } else {
          queryExpression.addSortAndSlice(sortAndSlice);
        } 
        return queryExpression;
    } 
    throw unexpectedToken();
  }
  
  QuerySpecification XreadSimpleTable() {
    Table table;
    switch (this.token.tokenType) {
      case 278:
        read();
        table = readTableName();
        if (table.isView())
          table = ((View)table).newDerivedTable(this.session); 
        return new QuerySpecification(this.session, table, this.compileContext, false);
      case 308:
        read();
        table = XreadRowValueExpressionList();
        return new QuerySpecification(this.session, table, this.compileContext, true);
      case 251:
        return XreadQuerySpecification();
    } 
    throw unexpectedToken();
  }
  
  QuerySpecification XreadQuerySpecification() {
    QuerySpecification querySpecification = XreadSelect();
    if (!querySpecification.isValueList)
      XreadTableExpression(querySpecification); 
    return querySpecification;
  }
  
  void XreadTableExpression(QuerySpecification paramQuerySpecification) {
    XreadFromClause(paramQuerySpecification);
    readWhereGroupHaving(paramQuerySpecification);
  }
  
  QuerySpecification XreadSelect() {
    QuerySpecification querySpecification = new QuerySpecification(this.compileContext);
    readThis(251);
    if (this.token.tokenType == 773 || this.token.tokenType == 600) {
      SortAndSlice sortAndSlice = XreadTopOrLimit();
      if (sortAndSlice != null)
        querySpecification.addSortAndSlice(sortAndSlice); 
    } 
    if (this.token.tokenType == 85) {
      querySpecification.isDistinctSelect = true;
      read();
    } else if (this.token.tokenType == 2) {
      read();
    } 
    while (true) {
      Expression expression = XreadValueExpression();
      if (this.token.tokenType == 10) {
        read();
        checkIsNonCoreReservedIdentifier();
      } 
      if (isNonCoreReservedIdentifier()) {
        expression.setAlias(HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier()));
        read();
      } 
      querySpecification.addSelectColumnExpression(expression);
      if (this.token.tokenType == 115 || this.token.tokenType == 141)
        break; 
      if (readIfThis(804))
        continue; 
      if ((this.token.tokenType == 802 || this.token.tokenType == 872) && (this.database.sqlSyntaxMss || this.database.sqlSyntaxMys || this.database.sqlSyntaxPgs)) {
        Expression[] arrayOfExpression = new Expression[querySpecification.exprColumnList.size()];
        querySpecification.exprColumnList.toArray(arrayOfExpression);
        Expression expression1 = new Expression(25, arrayOfExpression);
        arrayOfExpression = new Expression[] { expression1 };
        Expression expression2 = new Expression(26, arrayOfExpression);
        this.compileContext.incrementDepth();
        HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[expression1.getDegree()];
        for (byte b = 0; b < arrayOfHsqlName.length; b++) {
          HsqlNameManager.SimpleName simpleName = expression1.nodes[b].getSimpleName();
          if (simpleName == null) {
            arrayOfHsqlName[b] = HsqlNameManager.getAutoColumnName(b);
          } else {
            arrayOfHsqlName[b] = HsqlNameManager.getColumnName(simpleName);
          } 
        } 
        TableDerived tableDerived = prepareSubqueryTable(expression2, arrayOfHsqlName);
        querySpecification = new QuerySpecification(this.session, tableDerived, this.compileContext, true);
        this.compileContext.decrementDepth();
        return querySpecification;
      } 
      throw unexpectedToken();
    } 
    return querySpecification;
  }
  
  void XreadFromClause(QuerySpecification paramQuerySpecification) {
    readThis(115);
    while (true) {
      XreadTableReference(paramQuerySpecification);
      if (readIfThis(804))
        continue; 
      break;
    } 
  }
  
  void XreadTableReference(QuerySpecification paramQuerySpecification) {
    boolean bool = false;
    RangeVariable rangeVariable = readTableOrSubquery();
    paramQuerySpecification.addRangeVariable(this.session, rangeVariable);
    while (true) {
      int j;
      boolean bool4;
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      int i = this.token.tokenType;
      switch (this.token.tokenType) {
        case 176:
          if (bool)
            throw unexpectedToken(); 
          read();
          bool = true;
          continue;
        case 132:
          read();
          readThis(144);
          break;
        case 56:
          if (bool)
            throw unexpectedToken(); 
          read();
          readThis(144);
          break;
        case 298:
          if (bool)
            throw unexpectedToken(); 
          j = getPosition();
          read();
          if (this.token.tokenType == 144) {
            read();
            break;
          } 
          rewind(j);
          bool3 = true;
          break;
        case 153:
          read();
          readIfThis(200);
          readThis(144);
          bool1 = true;
          break;
        case 240:
          read();
          readIfThis(200);
          readThis(144);
          bool2 = true;
          break;
        case 116:
          read();
          readIfThis(200);
          readThis(144);
          bool1 = true;
          bool2 = true;
          break;
        case 144:
          read();
          i = 132;
          break;
        default:
          if (bool)
            throw unexpectedToken(); 
          bool3 = true;
          break;
      } 
      if (bool3)
        break; 
      RangeVariable rangeVariable1 = readTableOrSubquery();
      Expression expression = null;
      switch (i) {
        case 56:
          paramQuerySpecification.addRangeVariable(this.session, rangeVariable1);
          break;
        case 298:
          expression = Expression.EXPR_FALSE;
          rangeVariable1.addJoinCondition(expression);
          rangeVariable1.setJoinType(true, true);
          paramQuerySpecification.addRangeVariable(this.session, rangeVariable1);
          break;
        case 116:
        case 132:
        case 153:
        case 240:
          bool4 = (this.token.tokenType == 306) ? true : false;
          rangeVariable1.setJoinType(bool1, bool2);
          if (bool || bool4) {
            rangeVariable.resolveRangeTable(this.session, RangeGroup.emptyGroup, this.compileContext.getOuterRanges());
            rangeVariable1.resolveRangeTable(this.session, RangeGroup.emptyGroup, this.compileContext.getOuterRanges());
          } 
          if (bool) {
            OrderedHashSet orderedHashSet = rangeVariable1.getUniqueColumnNameSet();
            expression = paramQuerySpecification.getEquiJoinExpressions(orderedHashSet, rangeVariable1, false);
            rangeVariable1.addJoinCondition(expression);
            paramQuerySpecification.addRangeVariable(this.session, rangeVariable1);
            break;
          } 
          if (bool4) {
            read();
            OrderedHashSet orderedHashSet = new OrderedHashSet();
            readThis(816);
            readSimpleColumnNames(orderedHashSet, rangeVariable1, false);
            readThis(802);
            expression = paramQuerySpecification.getEquiJoinExpressions(orderedHashSet, rangeVariable1, true);
            rangeVariable1.addJoinCondition(expression);
            paramQuerySpecification.addRangeVariable(this.session, rangeVariable1);
            break;
          } 
          if (this.token.tokenType == 194) {
            read();
            expression = XreadBooleanValueExpression();
            rangeVariable1.addJoinCondition(expression);
            paramQuerySpecification.addRangeVariable(this.session, rangeVariable1);
            break;
          } 
          throw unexpectedToken();
      } 
      bool = false;
    } 
  }
  
  Expression getRowExpression(OrderedHashSet paramOrderedHashSet) {
    Expression[] arrayOfExpression = new Expression[paramOrderedHashSet.size()];
    for (byte b = 0; b < arrayOfExpression.length; b++) {
      String str = (String)paramOrderedHashSet.get(b);
      arrayOfExpression[b] = new ExpressionColumn(null, null, str);
    } 
    return new Expression(25, arrayOfExpression);
  }
  
  void readWhereGroupHaving(QuerySpecification paramQuerySpecification) {
    if (this.token.tokenType == 316) {
      read();
      Expression expression = XreadBooleanValueExpression();
      paramQuerySpecification.addQueryCondition(expression);
    } 
    if (this.token.tokenType == 122) {
      read();
      readThis(24);
      while (true) {
        Expression expression = XreadValueExpression();
        paramQuerySpecification.addGroupByColumnExpression(expression);
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        break;
      } 
    } 
    if (this.token.tokenType == 125) {
      read();
      Expression expression = XreadBooleanValueExpression();
      paramQuerySpecification.addHavingExpression(expression);
    } 
  }
  
  SortAndSlice XreadOrderByExpression() {
    SortAndSlice sortAndSlice = null;
    if (this.token.tokenType == 198) {
      read();
      readThis(24);
      sortAndSlice = XreadOrderBy();
    } 
    if (this.token.tokenType == 600 || this.token.tokenType == 107 || this.token.tokenType == 192) {
      if (sortAndSlice == null)
        sortAndSlice = new SortAndSlice(); 
      XreadLimit(sortAndSlice);
    } 
    return (sortAndSlice == null) ? SortAndSlice.noSort : sortAndSlice;
  }
  
  private SortAndSlice XreadTopOrLimit() {
    Expression expression1 = null;
    Expression expression2 = null;
    if (this.token.tokenType == 600) {
      int j = getPosition();
      read();
      expression1 = XreadSimpleValueSpecificationOrNull();
      if (expression1 == null) {
        rewind(j);
        return null;
      } 
      readIfThis(804);
      expression2 = XreadSimpleValueSpecificationOrNull();
      if (expression2 == null)
        throw Error.error(5563, 81); 
    } else if (this.token.tokenType == 773) {
      int j = getPosition();
      read();
      expression2 = XreadSimpleValueSpecificationOrNull();
      if (expression2 == null) {
        rewind(j);
        return null;
      } 
      expression1 = new ExpressionValue(ValuePool.INTEGER_0, (Type)Type.SQL_INTEGER);
    } 
    int i = 1;
    if (expression1.isUnresolvedParam()) {
      expression1.setDataType(this.session, (Type)Type.SQL_INTEGER);
    } else if (expression1.opType == 1) {
      i = ((expression1.getDataType()).typeCode == 4 && ((Integer)expression1.getValue(null)).intValue() >= 0) ? 1 : 0;
    } else {
      throw Error.error(5563, 81);
    } 
    if (expression2.isUnresolvedParam()) {
      expression2.setDataType(this.session, (Type)Type.SQL_INTEGER);
    } else if (expression2.opType == 1) {
      i &= ((expression2.getDataType()).typeCode == 4 && ((Integer)expression2.getValue(null)).intValue() >= 0) ? 1 : 0;
    } else {
      throw Error.error(5563, 81);
    } 
    if (i != 0) {
      SortAndSlice sortAndSlice = new SortAndSlice();
      sortAndSlice.addLimitCondition(new ExpressionOp(95, expression1, expression2));
      return sortAndSlice;
    } 
    throw Error.error(5563, 81);
  }
  
  private void XreadLimit(SortAndSlice paramSortAndSlice) {
    Expression expression1 = null;
    Expression expression2 = null;
    if (this.token.tokenType == 192) {
      read();
      expression1 = XreadSimpleValueSpecificationOrNull();
      if (expression1 == null)
        throw Error.error(5563, 81); 
      if (this.token.tokenType == 243 || this.token.tokenType == 245)
        read(); 
    } 
    if (this.token.tokenType == 600) {
      read();
      expression2 = XreadSimpleValueSpecificationOrNull();
      if (expression2 == null)
        throw Error.error(5563, 81); 
      if (expression1 == null)
        if (this.token.tokenType == 804) {
          read();
          expression1 = expression2;
          expression2 = XreadSimpleValueSpecificationOrNull();
        } else if (this.token.tokenType == 192) {
          read();
          expression1 = XreadSimpleValueSpecificationOrNull();
        }  
      if (this.database.sqlSyntaxPgs || this.database.sqlSyntaxMys)
        paramSortAndSlice.setZeroLimit(); 
    } else if (this.token.tokenType == 107) {
      read();
      if (this.token.tokenType == 401 || this.token.tokenType == 448)
        read(); 
      expression2 = XreadSimpleValueSpecificationOrNull();
      if (expression2 == null)
        expression2 = new ExpressionValue(ValuePool.INTEGER_1, (Type)Type.SQL_INTEGER); 
      if (this.token.tokenType == 243 || this.token.tokenType == 245)
        read(); 
      readThis(195);
      paramSortAndSlice.setStrictLimit();
    } 
    if (paramSortAndSlice.hasOrder() && this.token.tokenType == 306) {
      read();
      readThis(597);
      paramSortAndSlice.setUsingIndex();
    } 
    if (expression1 == null)
      expression1 = new ExpressionValue(ValuePool.INTEGER_0, (Type)Type.SQL_INTEGER); 
    boolean bool = true;
    if (expression1.isUnresolvedParam())
      expression1.setDataType(this.session, (Type)Type.SQL_INTEGER); 
    if (expression2 != null && expression2.isUnresolvedParam())
      expression2.setDataType(this.session, (Type)Type.SQL_INTEGER); 
    if (bool) {
      paramSortAndSlice.addLimitCondition(new ExpressionOp(95, expression1, expression2));
      return;
    } 
    throw Error.error(5563, 81);
  }
  
  private SortAndSlice XreadOrderBy() {
    SortAndSlice sortAndSlice = new SortAndSlice();
    while (true) {
      boolean bool = false;
      boolean bool1 = false;
      Expression expression = XreadValueExpression();
      ExpressionOrderBy expressionOrderBy = new ExpressionOrderBy(expression);
      if (this.token.tokenType == 389) {
        expressionOrderBy.setDescending();
        bool = true;
        read();
      } else if (this.token.tokenType == 338) {
        read();
      } 
      if (this.database.sqlNullsOrder) {
        bool1 = !this.database.sqlNullsFirst ? true : false;
      } else {
        bool1 = !(this.database.sqlNullsFirst ^ bool) ? true : false;
      } 
      expressionOrderBy.setNullsLast(bool1);
      if (this.token.tokenType == 451) {
        read();
        if (this.token.tokenType == 401) {
          read();
          expressionOrderBy.setNullsLast(false);
        } else if (this.token.tokenType == 430) {
          read();
          expressionOrderBy.setNullsLast(true);
        } else {
          throw unexpectedToken();
        } 
      } 
      sortAndSlice.addOrderExpression(expressionOrderBy);
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      return sortAndSlice;
    } 
  }
  
  protected RangeVariable readRangeVariableForDataChange(int paramInt) {
    Table table = readTableName();
    HsqlNameManager.SimpleName simpleName = null;
    if (paramInt != 1215) {
      if (this.token.tokenType == 10) {
        read();
        checkIsNonCoreReservedIdentifier();
      } 
      if (isNonCoreReservedIdentifier()) {
        simpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
        read();
      } 
    } 
    if (table.isView) {
      switch (paramInt) {
        case 128:
          if ((table.isTriggerUpdatable() && table.isTriggerInsertable()) || (!table.isTriggerUpdatable() && !table.isTriggerInsertable() && table.isUpdatable() && table.isInsertable()))
            break; 
          throw Error.error(5545);
        case 82:
          if (table.isTriggerUpdatable() || table.isUpdatable())
            break; 
          throw Error.error(5545);
        case 19:
          if (table.isTriggerDeletable() || table.isUpdatable())
            break; 
          throw Error.error(5545);
        case 50:
          if (table.isTriggerInsertable() || table.isInsertable() || this.session.isProcessingScript())
            break; 
          throw Error.error(5545);
        case 1215:
          throw Error.error(5545);
      } 
      table = ((View)table).newDerivedTable(this.session);
    } 
    return new RangeVariable(table, simpleName, null, null, this.compileContext);
  }
  
  protected Table readNamedSubqueryOrNull() {
    if (!isSimpleName())
      return null; 
    TableDerived tableDerived = this.compileContext.getNamedSubQuery(this.token.tokenString);
    if (tableDerived == null)
      return null; 
    read();
    if (tableDerived.isRecompiled()) {
      tableDerived = tableDerived.newDerivedTable(this.session);
    } else {
      tableDerived.canRecompile = true;
    } 
    return tableDerived;
  }
  
  protected RangeVariable readTableOrSubquery() {
    Expression expression;
    RangeVariable rangeVariable;
    Table table = null;
    HsqlNameManager.SimpleName simpleName = null;
    HsqlNameManager.SimpleName[] arrayOfSimpleName = null;
    OrderedHashSet orderedHashSet = null;
    boolean bool1 = false;
    boolean bool2 = false;
    switch (this.token.tokenType) {
      case 816:
        table = XreadTableSubqueryOrNull(false);
        if (table == null) {
          table = XreadJoinedTableAsSubqueryOrNull();
          if (table == null) {
            table = XreadTableSubqueryOrNull(true);
            break;
          } 
          bool1 = true;
        } 
        break;
      case 301:
        expression = XreadCollectionDerivedTable(23);
        table = expression.getTable();
        bool2 = true;
        break;
      case 149:
        expression = XreadLateralDerivedTable();
        table = expression.getTable();
        bool2 = true;
        break;
      case 278:
        expression = XreadTableFunctionDerivedTable();
        table = expression.getTable();
        bool2 = true;
        break;
      default:
        table = readNamedSubqueryOrNull();
        if (table == null)
          table = readTableName(); 
        if (table.isView())
          table = ((View)table).newDerivedTable(this.session); 
        break;
    } 
    boolean bool3 = false;
    if (this.token.tokenType == 10) {
      read();
      checkIsNonCoreReservedIdentifier();
      bool3 = true;
    } 
    if (isNonCoreReservedIdentifier()) {
      boolean bool4 = (this.token.tokenType == 600 || this.token.tokenType == 192 || this.token.tokenType == 107) ? true : false;
      boolean bool5 = (this.token.tokenType == 609) ? true : false;
      int i = getPosition();
      simpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
      read();
      if (this.token.tokenType == 816) {
        orderedHashSet = new OrderedHashSet();
        arrayOfSimpleName = readColumnNameList(orderedHashSet);
      } else if (!bool3 && bool4) {
        if (this.token.tokenType == 803 || this.token.tokenType == 818 || this.token.tokenType == 869) {
          simpleName = null;
          rewind(i);
        } 
      } else if (!bool3 && bool5) {
        rewind(i);
      } 
    } 
    if (bool1) {
      rangeVariable = new RangeVariableJoined(table, simpleName, orderedHashSet, arrayOfSimpleName, this.compileContext);
    } else {
      rangeVariable = new RangeVariable(table, simpleName, orderedHashSet, arrayOfSimpleName, this.compileContext);
    } 
    if (bool2)
      rangeVariable.isLateral = true; 
    return rangeVariable;
  }
  
  private Expression readAggregate() {
    int i = this.token.tokenType;
    read();
    readThis(816);
    Expression expression = readAggregateExpression(i);
    readThis(802);
    readFilterClause(expression);
    return expression;
  }
  
  private void readFilterClause(Expression paramExpression) {
    int i = getPosition();
    if (this.token.tokenType == 108) {
      read();
      if (this.token.tokenType != 816) {
        rewind(i);
        return;
      } 
      readThis(816);
      readThis(316);
      Expression expression = XreadBooleanValueExpression();
      paramExpression.setCondition(expression);
      readThis(802);
    } 
  }
  
  private Expression readAggregateExpression(int paramInt) {
    int i = getExpressionType(paramInt);
    boolean bool1 = false;
    boolean bool2 = false;
    SortAndSlice sortAndSlice = null;
    String str = null;
    if (this.token.tokenType == 85) {
      bool1 = true;
      read();
    } else if (this.token.tokenType == 2) {
      bool2 = true;
      read();
    } 
    int j = getPosition();
    Expression expression = XreadValueExpression();
    switch (i) {
      case 71:
        if (expression.getType() == 97) {
          if (((ExpressionColumn)expression).tableName != null)
            throw unexpectedToken(); 
          if (bool2 || bool1)
            throw unexpectedToken(); 
          expression.opType = 11;
        } else if (this.token.tokenType == 804) {
          rewind(j);
          expression = XreadRowElementList(false);
        } 
        return new ExpressionAggregate(i, bool1, expression);
      case 78:
      case 79:
      case 80:
      case 81:
        if (bool2 || bool1)
          throw unexpectedToken(bool2 ? "ALL" : "DISTINCT"); 
        return new ExpressionAggregate(i, bool1, expression);
      case 82:
      case 83:
        if (this.token.tokenType == 198) {
          read();
          readThis(24);
          sortAndSlice = XreadOrderBy();
        } 
        if (i == 83 && this.token.tokenType == 627) {
          read();
          checkIsValue(1);
          str = (String)this.token.tokenValue;
          read();
        } 
        return new ExpressionArrayAggregate(i, bool1, expression, sortAndSlice, str);
      case 85:
        return new ExpressionArrayAggregate(i, bool1, expression, sortAndSlice, str);
    } 
    if (expression.getType() == 97 || expression.getType() == 11)
      throw unexpectedToken("*"); 
    return new ExpressionAggregate(i, bool1, expression);
  }
  
  Expression XreadValueSpecificationOrNull() {
    Expression expression = null;
    boolean bool = false;
    switch (this.token.tokenType) {
      case 817:
        read();
        break;
      case 814:
        read();
        bool = true;
        break;
    } 
    expression = XreadUnsignedValueSpecificationOrNull();
    if (expression == null)
      return null; 
    if (bool)
      expression = new ExpressionArithmetic(31, expression); 
    return expression;
  }
  
  Expression XreadUnsignedValueSpecificationOrNull() {
    ExpressionValue expressionValue;
    ExpressionColumn expressionColumn;
    FunctionSQL functionSQL;
    switch (this.token.tokenType) {
      case 294:
        read();
        return Expression.EXPR_TRUE;
      case 106:
        read();
        return Expression.EXPR_FALSE;
      case 78:
        if (this.compileContext.contextuallyTypedExpression) {
          read();
          return new ExpressionColumn(4);
        } 
        break;
      case 186:
        expressionValue = new ExpressionValue(null, null);
        read();
        return expressionValue;
      case 869:
        expressionValue = new ExpressionValue(this.token.tokenValue, this.token.dataType);
        read();
        return expressionValue;
      case 870:
      case 871:
        return (Expression)(!this.token.isHostParameter ? null : null);
      case 803:
        read();
        if (this.token.tokenType != 871 && this.token.tokenType != 870)
          throw unexpectedToken(":"); 
      case 818:
        expressionColumn = new ExpressionColumn(8);
        this.compileContext.addParameter(expressionColumn, getPosition());
        read();
        return expressionColumn;
      case 358:
        return XreadCurrentCollationSpec();
      case 60:
      case 62:
      case 63:
      case 64:
      case 65:
      case 68:
      case 69:
      case 253:
      case 277:
      case 305:
      case 307:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        return (functionSQL == null) ? null : readSQLFunction(functionSQL);
    } 
    return null;
  }
  
  Expression XreadSimpleValueSpecificationOrNull() {
    ExpressionValue expressionValue;
    ExpressionColumn expressionColumn1;
    ExpressionColumn expressionColumn2;
    switch (this.token.tokenType) {
      case 869:
        expressionValue = new ExpressionValue(this.token.tokenValue, this.token.dataType);
        read();
        return expressionValue;
      case 803:
        read();
        if (this.token.tokenType != 871 && this.token.tokenType != 870)
          throw unexpectedToken(":"); 
      case 818:
        expressionColumn2 = new ExpressionColumn(8);
        this.compileContext.addParameter(expressionColumn2, getPosition());
        read();
        return expressionColumn2;
      case 870:
      case 871:
        checkValidCatalogName(this.token.namePrePrePrefix);
        expressionColumn1 = new ExpressionColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
        read();
        return expressionColumn1;
    } 
    return null;
  }
  
  Expression XreadAllTypesValueExpressionPrimary(boolean paramBoolean) {
    Expression expression = null;
    switch (this.token.tokenType) {
      case 101:
      case 299:
        if (paramBoolean)
          return XreadPredicate(); 
        break;
      case 243:
        if (paramBoolean)
          break; 
        read();
        readThis(816);
        expression = XreadRowElementList(true);
        readThis(802);
        break;
      default:
        expression = XreadSimpleValueExpressionPrimary();
        if (expression != null)
          expression = XreadArrayElementReference(expression); 
        break;
    } 
    if (expression == null) {
      boolean bool = false;
      if (this.token.tokenType == 243) {
        read();
        checkIsThis(816);
        bool = true;
      } 
      if (this.token.tokenType == 816) {
        read();
        expression = XreadRowElementList(true);
        readThis(802);
      } 
    } 
    if (paramBoolean && expression != null)
      expression = XreadPredicateRightPart(expression); 
    return expression;
  }
  
  Expression XreadValueExpressionPrimary() {
    Expression expression = XreadSimpleValueExpressionPrimary();
    if (expression != null)
      return XreadArrayElementReference(expression); 
    if (this.token.tokenType == 816) {
      read();
      expression = XreadValueExpression();
      readThis(802);
    } else {
      return null;
    } 
    return expression;
  }
  
  Expression XreadSimpleValueExpressionPrimary() {
    int j;
    TableDerived tableDerived;
    Expression expression = XreadUnsignedValueSpecificationOrNull();
    if (expression != null)
      return expression; 
    int i = getPosition();
    switch (this.token.tokenType) {
      case 816:
        read();
        j = getPosition();
        readOpenBrackets();
        switch (this.token.tokenType) {
          case 251:
          case 278:
          case 308:
            tableDerived = null;
            rewind(j);
            try {
              tableDerived = XreadSubqueryTableBody(21);
              readThis(802);
            } catch (HsqlException hsqlException) {
              hsqlException.setLevel(this.compileContext.subqueryDepth);
              if (this.lastError == null || this.lastError.getLevel() < hsqlException.getLevel())
                this.lastError = hsqlException; 
              rewind(i);
              return null;
            } 
            if (tableDerived.queryExpression.isSingleColumn()) {
              expression = new Expression(21, tableDerived);
            } else {
              expression = new Expression(22, tableDerived);
            } 
            return expression;
        } 
        rewind(i);
        return null;
      case 801:
        expression = new ExpressionColumn(this.token.namePrePrefix, this.token.namePrefix);
        getRecordedToken().setExpression(expression);
        read();
        return expression;
      case 699:
        expression = readLeastExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 687:
        expression = readGreatestExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 681:
        expression = readDecodeExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 656:
        expression = readConcatExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 657:
        expression = readConcatSeparatorExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 571:
        expression = readCaseWhenExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 29:
        return readCaseExpression();
      case 187:
        return readNullIfExpression();
      case 40:
        return readCoalesceExpression();
      case 689:
      case 696:
        expression = readIfNullExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 613:
        expression = readIfNull2ExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 30:
      case 49:
        expression = readCastExpressionOrNull();
        if (expression != null)
          return expression; 
        break;
      case 72:
      case 140:
      case 281:
      case 282:
        expression = readDateTimeIntervalLiteral(this.session);
        if (expression != null)
          return expression; 
        break;
      case 8:
        return readCollection(19);
      case 6:
      case 9:
      case 16:
      case 52:
      case 97:
      case 163:
      case 168:
      case 258:
      case 269:
      case 270:
      case 274:
      case 309:
      case 310:
      case 606:
      case 688:
        return readAggregate();
      case 448:
        expression = readSequenceExpressionOrNull(12);
        if (expression != null)
          return expression; 
        break;
      case 59:
      case 617:
        expression = readSequenceExpressionOrNull(13);
        if (expression != null)
          return expression; 
        break;
      case 581:
        if (this.database.sqlSyntaxPgs) {
          read();
          readThis(816);
          String str1 = readQuotedString();
          Scanner scanner = this.session.getScanner();
          scanner.reset(str1);
          scanner.scanNext();
          String str2 = this.session.getSchemaName(scanner.token.namePrefix);
          NumberSequence numberSequence = this.database.schemaManager.getSequence(scanner.token.tokenString, str2, true);
          expression = new ExpressionColumn(numberSequence, 13);
          readThis(802);
          return expression;
        } 
        break;
      case 599:
        if (this.database.sqlSyntaxPgs) {
          read();
          readThis(816);
          readThis(802);
          return FunctionCustom.newCustomFunction("IDENTITY", 128);
        } 
        break;
      case 611:
        if (this.database.sqlSyntaxPgs)
          return readNextvalFunction(); 
        if (this.database.sqlSyntaxDb2) {
          expression = readSequenceExpressionOrNull(12);
          if (expression != null)
            return expression; 
        } 
        break;
      case 244:
        read();
        if (this.token.tokenType == 816) {
          read();
          readThis(802);
          readThis(201);
          readThis(816);
          readThis(802);
        } else {
          rewind(i);
          break;
        } 
        return new ExpressionColumn(14);
      case 625:
        read();
        if (this.token.tokenType == 816) {
          read();
          if (this.token.tokenType == 802) {
            read();
          } else {
            rewind(i);
            break;
          } 
        } else if (!this.database.sqlSyntaxOra && !this.database.sqlSyntaxDb2) {
          rewind(i);
          break;
        } 
        return new ExpressionColumn(14);
      case 153:
      case 240:
        break;
      case 278:
        read();
        readThis(816);
        tableDerived = XreadSubqueryTableBody(23);
        readThis(802);
        return new Expression(23, tableDerived);
      default:
        if (isCoreReservedKey())
          throw unexpectedToken(); 
        break;
    } 
    expression = readColumnOrFunctionExpression();
    if (expression.isAggregate())
      readFilterClause(expression); 
    return expression;
  }
  
  Expression readNextvalFunction() {
    read();
    readThis(816);
    String str1 = readQuotedString();
    Scanner scanner = this.session.getScanner();
    scanner.reset(str1);
    scanner.scanNext();
    String str2 = this.session.getSchemaName(scanner.token.namePrefix);
    NumberSequence numberSequence = this.database.schemaManager.getSequence(scanner.token.tokenString, str2, true);
    ExpressionColumn expressionColumn = new ExpressionColumn(numberSequence, 12);
    readThis(802);
    return expressionColumn;
  }
  
  Expression XreadAllTypesPrimary(boolean paramBoolean) {
    FunctionSQL functionSQL;
    null = null;
    switch (this.token.tokenType) {
      case 1:
      case 27:
      case 31:
      case 32:
      case 34:
      case 36:
      case 103:
      case 105:
      case 111:
      case 156:
      case 161:
      case 170:
      case 182:
      case 189:
      case 190:
      case 203:
      case 209:
      case 210:
      case 211:
      case 265:
      case 272:
      case 273:
      case 288:
      case 292:
      case 304:
      case 317:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        if (functionSQL == null)
          throw unsupportedFeature(); 
        null = readSQLFunction(functionSQL);
        if (null != null)
          return XreadModifier(null); 
        break;
    } 
    null = XreadAllTypesValueExpressionPrimary(paramBoolean);
    return XreadModifier(null);
  }
  
  Expression XreadModifier(Expression paramExpression) {
    Expression expression;
    IntervalType intervalType;
    Collation collation;
    switch (this.token.tokenType) {
      case 13:
        read();
        expression = null;
        if (this.token.tokenType == 157) {
          read();
        } else {
          IntervalType intervalType1;
          readThis(281);
          readThis(549);
          expression = XreadValueExpressionPrimary();
          switch (this.token.tokenType) {
            case 73:
            case 127:
            case 169:
            case 173:
            case 250:
            case 323:
              intervalType1 = readIntervalType(false);
              if (expression.getType() == 33) {
                expression.dataType = (Type)intervalType1;
                break;
              } 
              expression = new ExpressionOp(expression, (Type)intervalType1);
              break;
          } 
        } 
        paramExpression = new ExpressionOp(92, paramExpression, expression);
        break;
      case 73:
      case 127:
      case 169:
      case 173:
      case 250:
      case 323:
        intervalType = readIntervalType(true);
        if (paramExpression.getType() == 33) {
          paramExpression.dataType = (Type)intervalType;
          break;
        } 
        paramExpression = new ExpressionOp(paramExpression, (Type)intervalType);
        break;
      case 41:
        read();
        collation = this.database.schemaManager.getCollation(this.session, this.token.tokenString, this.token.namePrefix);
        paramExpression.setCollation(collation);
        read();
        break;
    } 
    return paramExpression;
  }
  
  Expression XreadValueExpressionWithContext() {
    this.compileContext.contextuallyTypedExpression = true;
    Expression expression = XreadValueExpressionOrNull();
    this.compileContext.contextuallyTypedExpression = false;
    return expression;
  }
  
  Expression XreadValueExpressionOrNull() {
    Expression expression = XreadAllTypesCommonValueExpression(true);
    return (expression == null) ? null : expression;
  }
  
  Expression XreadValueExpression() {
    Expression expression = XreadAllTypesCommonValueExpression(true);
    if (this.token.tokenType == 811) {
      read();
      Expression expression1 = XreadNumericValueExpression();
      readThis(820);
      expression = new ExpressionAccessor(expression, expression1);
    } 
    return expression;
  }
  
  Expression XreadRowOrCommonValueExpression() {
    return XreadAllTypesCommonValueExpression(false);
  }
  
  Expression XreadAllTypesCommonValueExpression(boolean paramBoolean) {
    Expression expression = XreadAllTypesTerm(paramBoolean);
    byte b = 0;
    boolean bool = false;
    while (true) {
      switch (this.token.tokenType) {
        case 817:
          b = 32;
          paramBoolean = false;
          break;
        case 814:
          b = 33;
          paramBoolean = false;
          break;
        case 805:
          b = 36;
          paramBoolean = false;
          break;
        case 197:
          if (paramBoolean) {
            b = 50;
            break;
          } 
        default:
          bool = true;
          break;
      } 
      if (bool)
        return expression; 
      read();
      Expression expression1 = expression;
      expression = XreadAllTypesTerm(paramBoolean);
      expression = paramBoolean ? new ExpressionLogical(b, expression1, expression) : new ExpressionArithmetic(b, expression1, expression);
    } 
  }
  
  Expression XreadAllTypesTerm(boolean paramBoolean) {
    Expression expression = XreadAllTypesFactor(paramBoolean);
    byte b = 0;
    boolean bool = false;
    while (true) {
      switch (this.token.tokenType) {
        case 801:
          b = 34;
          paramBoolean = false;
          break;
        case 806:
          b = 35;
          paramBoolean = false;
          break;
        case 5:
          if (paramBoolean) {
            b = 49;
            break;
          } 
        default:
          bool = true;
          break;
      } 
      if (bool)
        return expression; 
      read();
      Expression expression1 = expression;
      expression = XreadAllTypesFactor(paramBoolean);
      if (expression == null)
        throw unexpectedToken(); 
      expression = paramBoolean ? new ExpressionLogical(b, expression1, expression) : new ExpressionArithmetic(b, expression1, expression);
    } 
  }
  
  Expression XreadAllTypesFactor(boolean paramBoolean) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    switch (this.token.tokenType) {
      case 817:
        read();
        paramBoolean = false;
        break;
      case 814:
        read();
        paramBoolean = false;
        bool1 = true;
        break;
      case 183:
        if (paramBoolean) {
          read();
          bool2 = true;
        } 
        break;
    } 
    Expression expression = XreadAllTypesPrimary(paramBoolean);
    if (paramBoolean && this.token.tokenType == 142) {
      read();
      if (this.token.tokenType == 183) {
        read();
        bool2 = !bool2 ? true : false;
      } 
      if (this.token.tokenType == 294) {
        read();
      } else if (this.token.tokenType == 106) {
        read();
        bool2 = !bool2 ? true : false;
      } else if (this.token.tokenType == 300) {
        read();
        bool3 = true;
      } else {
        throw unexpectedToken();
      } 
    } 
    if (bool3) {
      expression = new ExpressionLogical(47, expression);
    } else if (bool1) {
      expression = new ExpressionArithmetic(31, expression);
    } else if (bool2) {
      expression = new ExpressionLogical(48, expression);
    } 
    return expression;
  }
  
  Expression XreadStringValueExpression() {
    return XreadCharacterValueExpression();
  }
  
  Expression XreadCharacterValueExpression() {
    Expression expression = XreadCharacterPrimary();
    Collation collation = readCollateClauseOrNull();
    while (this.token.tokenType == 805) {
      read();
      Expression expression1 = expression;
      expression = XreadCharacterPrimary();
      collation = readCollateClauseOrNull();
      expression = new ExpressionArithmetic(36, expression1, expression);
    } 
    return expression;
  }
  
  Expression XreadCharacterPrimary() {
    FunctionSQL functionSQL;
    Expression expression;
    switch (this.token.tokenType) {
      case 161:
      case 203:
      case 272:
      case 292:
      case 304:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        expression = readSQLFunction(functionSQL);
        if (expression != null)
          return expression; 
        break;
    } 
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadNumericPrimary() {
    FunctionSQL functionSQL;
    Expression expression;
    switch (this.token.tokenType) {
      case 1:
      case 27:
      case 31:
      case 32:
      case 34:
      case 36:
      case 103:
      case 105:
      case 111:
      case 156:
      case 170:
      case 190:
      case 209:
      case 211:
      case 265:
      case 317:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        if (functionSQL == null)
          throw unexpectedToken(); 
        expression = readSQLFunction(functionSQL);
        if (expression != null)
          return expression; 
        break;
    } 
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadNumericValueExpression() {
    for (Expression expression = XreadTerm();; expression = new ExpressionArithmetic(b, expression1, expression)) {
      byte b;
      if (this.token.tokenType == 817) {
        b = 32;
      } else if (this.token.tokenType == 814) {
        b = 33;
      } else {
        return expression;
      } 
      read();
      Expression expression1 = expression;
      expression = XreadTerm();
    } 
  }
  
  Expression XreadTerm() {
    for (Expression expression = XreadFactor();; expression = new ExpressionArithmetic(b, expression1, expression)) {
      byte b;
      if (this.token.tokenType == 801) {
        b = 34;
      } else if (this.token.tokenType == 806) {
        b = 35;
      } else {
        return expression;
      } 
      read();
      Expression expression1 = expression;
      expression = XreadFactor();
      if (expression == null)
        throw unexpectedToken(); 
    } 
  }
  
  Expression XreadFactor() {
    boolean bool = false;
    if (this.token.tokenType == 817) {
      read();
    } else if (this.token.tokenType == 814) {
      read();
      bool = true;
    } 
    Expression expression = XreadNumericPrimary();
    if (expression == null)
      return null; 
    if (bool)
      expression = new ExpressionArithmetic(31, expression); 
    return expression;
  }
  
  Expression XreadDatetimeValueExpression() {
    for (Expression expression = XreadDateTimeIntervalTerm();; expression = new ExpressionArithmetic(b, expression1, expression)) {
      byte b;
      if (this.token.tokenType == 817) {
        b = 32;
      } else if (this.token.tokenType == 814) {
        b = 33;
      } else {
        return expression;
      } 
      read();
      Expression expression1 = expression;
      expression = XreadDateTimeIntervalTerm();
    } 
  }
  
  Expression XreadIntervalValueExpression() {
    for (Expression expression = XreadDateTimeIntervalTerm();; expression = new ExpressionArithmetic(b, expression1, expression)) {
      byte b;
      if (this.token.tokenType == 817) {
        b = 32;
      } else if (this.token.tokenType == 814) {
        b = 33;
      } else {
        return expression;
      } 
      read();
      Expression expression1 = expression;
      expression = XreadDateTimeIntervalTerm();
    } 
  }
  
  Expression XreadDateTimeIntervalTerm() {
    FunctionSQL functionSQL;
    switch (this.token.tokenType) {
      case 1:
      case 61:
      case 66:
      case 67:
      case 158:
      case 159:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        if (functionSQL == null)
          throw unexpectedToken(); 
        return readSQLFunction(functionSQL);
    } 
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadDateTimeValueFunctionOrNull() {
    FunctionSQL functionSQL = null;
    switch (this.token.tokenType) {
      case 61:
      case 66:
      case 67:
      case 158:
      case 159:
        functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
        break;
      case 759:
        if (!this.database.sqlSyntaxOra)
          return null; 
      case 716:
      case 758:
      case 772:
        functionSQL = FunctionCustom.newCustomFunction(this.token.tokenString, this.token.tokenType);
        break;
      default:
        return null;
    } 
    if (functionSQL == null)
      throw unexpectedToken(); 
    return readSQLFunction(functionSQL);
  }
  
  Expression XreadBooleanValueExpression() {
    try {
      Expression expression = XreadBooleanTermOrNull();
      if (expression == null)
        throw Error.error(5568); 
      while (this.token.tokenType == 197) {
        byte b = 50;
        read();
        Expression expression1 = expression;
        expression = XreadBooleanTermOrNull();
        if (expression == null)
          throw Error.error(5568); 
        expression = new ExpressionLogical(b, expression1, expression);
      } 
      if (expression == null)
        throw Error.error(5568); 
      return expression;
    } catch (HsqlException hsqlException) {
      hsqlException.setLevel(this.compileContext.subqueryDepth);
      if (this.lastError != null && this.lastError.getLevel() >= hsqlException.getLevel()) {
        hsqlException = this.lastError;
        this.lastError = null;
      } 
      throw hsqlException;
    } 
  }
  
  Expression XreadBooleanTermOrNull() {
    Expression expression = XreadBooleanFactorOrNull();
    if (expression == null)
      return null; 
    while (this.token.tokenType == 5) {
      byte b = 49;
      read();
      Expression expression1 = expression;
      expression = XreadBooleanFactorOrNull();
      if (expression == null)
        throw unexpectedToken(); 
      expression = new ExpressionLogical(b, expression1, expression);
    } 
    return expression;
  }
  
  Expression XreadBooleanFactorOrNull() {
    boolean bool = false;
    if (this.token.tokenType == 183) {
      read();
      bool = true;
    } 
    Expression expression = XreadBooleanTestOrNull();
    if (expression == null)
      return null; 
    if (bool)
      expression = new ExpressionLogical(48, expression); 
    return expression;
  }
  
  Expression XreadBooleanTestOrNull() {
    boolean bool1 = false;
    boolean bool2 = false;
    Expression expression = XreadBooleanPrimaryOrNull();
    if (expression == null)
      return expression; 
    if (this.token.tokenType == 142) {
      read();
      if (this.token.tokenType == 183) {
        read();
        bool2 = true;
      } 
      if (this.token.tokenType == 294) {
        read();
      } else if (this.token.tokenType == 106) {
        read();
        bool2 = !bool2 ? true : false;
      } else if (this.token.tokenType == 300) {
        read();
        bool1 = true;
      } else {
        throw unexpectedToken();
      } 
    } 
    if (bool1)
      expression = new ExpressionLogical(47, expression); 
    if (bool2)
      expression = new ExpressionLogical(48, expression); 
    return expression;
  }
  
  Expression XreadBooleanPrimaryOrNull() {
    int i;
    Expression expression = null;
    switch (this.token.tokenType) {
      case 101:
      case 299:
        return XreadPredicate();
      case 243:
        read();
        readThis(816);
        expression = XreadRowElementList(true);
        readThis(802);
        break;
      default:
        i = getPosition();
        try {
          expression = XreadAllTypesCommonValueExpression(false);
        } catch (HsqlException hsqlException) {
          hsqlException.setLevel(this.compileContext.subqueryDepth);
          if (this.lastError == null || this.lastError.getLevel() < hsqlException.getLevel())
            this.lastError = hsqlException; 
          rewind(i);
        } 
        break;
    } 
    if (expression == null && this.token.tokenType == 816) {
      read();
      i = getPosition();
      try {
        expression = XreadRowElementList(true);
        readThis(802);
      } catch (HsqlException hsqlException) {
        hsqlException.setLevel(this.compileContext.subqueryDepth);
        if (this.lastError == null || this.lastError.getLevel() < hsqlException.getLevel())
          this.lastError = hsqlException; 
        rewind(i);
        expression = XreadBooleanValueExpression();
        readThis(802);
      } 
    } 
    if (expression != null)
      expression = XreadPredicateRightPart(expression); 
    return expression;
  }
  
  Expression XreadBooleanPredicand() {
    if (this.token.tokenType == 816) {
      read();
      Expression expression1 = XreadBooleanValueExpression();
      readThis(802);
      return expression1;
    } 
    Expression expression = XreadSimpleValueExpressionPrimary();
    if (expression != null)
      expression = XreadArrayElementReference(expression); 
    return expression;
  }
  
  Expression XreadPredicate() {
    switch (this.token.tokenType) {
      case 101:
        read();
        expression = XreadTableSubquery(55);
        return new ExpressionLogical(55, expression);
      case 299:
        read();
        expression = XreadTableSubquery(57);
        return new ExpressionLogical(57, expression);
    } 
    Expression expression = XreadRowValuePredicand();
    return XreadPredicateRightPart(expression);
  }
  
  Expression XreadPredicateRightPart(Expression paramExpression) {
    int j;
    Expression expression;
    boolean bool = false;
    ExpressionLogical expressionLogical = null;
    int i = getPosition();
    if (this.token.tokenType == 183) {
      read();
      bool = true;
    } 
    switch (this.token.tokenType) {
      case 142:
        if (bool)
          throw unexpectedToken(); 
        read();
        if (this.token.tokenType == 183) {
          bool = true;
          read();
        } 
        if (this.token.tokenType == 186) {
          read();
          if (bool) {
            expressionLogical = new ExpressionLogical(39, paramExpression);
            bool = false;
            break;
          } 
          expressionLogical = new ExpressionLogical(47, paramExpression);
          break;
        } 
        if (this.token.tokenType == 85) {
          read();
          readThis(115);
          Expression expression1 = XreadRowValuePredicand();
          expressionLogical = new ExpressionLogical(58, paramExpression, expression1);
          bool = !bool ? true : false;
          break;
        } 
        rewind(i);
        return paramExpression;
      case 154:
        expressionLogical = XreadLikePredicateRightPart(paramExpression);
        expressionLogical.noOptimisation = this.isCheckOrTriggerCondition;
        break;
      case 18:
        expressionLogical = XreadBetweenPredicateRightPart(paramExpression);
        break;
      case 130:
        expressionLogical = XreadInPredicateRightPart(paramExpression);
        expressionLogical.noOptimisation = this.isCheckOrTriggerCondition;
        break;
      case 202:
        if (bool)
          throw unexpectedToken(); 
        expressionLogical = XreadOverlapsPredicateRightPart(paramExpression);
        break;
      case 396:
      case 809:
      case 810:
      case 812:
      case 813:
      case 815:
        if (bool)
          throw unexpectedToken(); 
        j = getExpressionType(this.token.tokenType);
        read();
        switch (this.token.tokenType) {
          case 2:
          case 6:
          case 258:
            expressionLogical = XreadQuantifiedComparisonRightPart(j, paramExpression);
            break;
        } 
        expression = XreadRowValuePredicand();
        expressionLogical = new ExpressionLogical(j, paramExpression, expression);
        break;
      case 162:
        expressionLogical = XreadMatchPredicateRightPart(paramExpression);
        break;
      default:
        if (bool)
          throw unexpectedToken(); 
        return paramExpression;
    } 
    if (bool)
      expressionLogical = new ExpressionLogical(48, expressionLogical); 
    return expressionLogical;
  }
  
  private ExpressionLogical XreadBetweenPredicateRightPart(Expression paramExpression) {
    boolean bool = false;
    read();
    if (this.token.tokenType == 12) {
      read();
    } else if (this.token.tokenType == 275) {
      bool = true;
      read();
    } 
    Expression expression1 = XreadRowValuePredicand();
    readThis(5);
    Expression expression2 = XreadRowValuePredicand();
    ExpressionLogical expressionLogical1 = new ExpressionLogical(41, paramExpression, expression1);
    ExpressionLogical expressionLogical2 = new ExpressionLogical(45, paramExpression, expression2);
    ExpressionLogical expressionLogical3 = new ExpressionLogical(49, expressionLogical1, expressionLogical2);
    if (bool) {
      expressionLogical1 = new ExpressionLogical(45, paramExpression, expression1);
      expressionLogical2 = new ExpressionLogical(41, paramExpression, expression2);
      ExpressionLogical expressionLogical = new ExpressionLogical(49, expressionLogical1, expressionLogical2);
      return new ExpressionLogical(50, expressionLogical3, expressionLogical);
    } 
    return expressionLogical3;
  }
  
  private ExpressionLogical XreadQuantifiedComparisonRightPart(int paramInt, Expression paramExpression) {
    TableDerived tableDerived;
    int i = this.token.tokenType;
    byte b = 0;
    switch (this.token.tokenType) {
      case 6:
      case 258:
        b = 52;
        break;
      case 2:
        b = 51;
        break;
      default:
        throw Error.runtimeError(201, "ParserDQL");
    } 
    read();
    readThis(816);
    int j = getPosition();
    readOpenBrackets();
    switch (this.token.tokenType) {
      case 251:
      case 278:
      case 308:
      case 319:
        rewind(j);
        tableDerived = XreadSubqueryTableBody(54);
        expression = new Expression(23, tableDerived);
        readThis(802);
        expressionLogical = new ExpressionLogical(paramInt, paramExpression, expression);
        expressionLogical.setSubType(b);
        return expressionLogical;
    } 
    rewind(j);
    Expression expression = readAggregateExpression(i);
    readThis(802);
    readFilterClause(expression);
    ExpressionLogical expressionLogical = new ExpressionLogical(paramInt, paramExpression, expression);
    expressionLogical.setSubType(b);
    return expressionLogical;
  }
  
  private ExpressionLogical XreadInPredicateRightPart(Expression paramExpression) {
    TableDerived tableDerived;
    ExpressionLogical expressionLogical;
    int i = paramExpression.getDegree();
    Expression expression = null;
    read();
    readThis(816);
    int j = getPosition();
    int k = readOpenBrackets();
    switch (this.token.tokenType) {
      case 301:
        expression = XreadCollectionDerivedTable(54);
        readThis(802);
        readCloseBrackets(k);
        break;
      case 251:
      case 278:
      case 308:
      case 319:
        rewind(j);
        tableDerived = XreadSubqueryTableBody(54);
        expression = new Expression(23, tableDerived);
        readThis(802);
        break;
      default:
        rewind(j);
        expression = XreadInValueListConstructor(i);
        readThis(802);
        break;
    } 
    if (this.isCheckOrTriggerCondition) {
      expressionLogical = new ExpressionLogical(54, paramExpression, expression);
    } else {
      expressionLogical = new ExpressionLogical(40, paramExpression, expression);
      expressionLogical.setSubType(52);
    } 
    return expressionLogical;
  }
  
  Expression XreadInValueList(int paramInt) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    while (true) {
      Expression expression1 = XreadValueExpression();
      if (expression1.getType() != 25)
        expression1 = new Expression(25, new Expression[] { expression1 }); 
      hsqlArrayList.add(expression1);
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfExpression);
      Expression expression2 = new Expression(26, arrayOfExpression);
      for (byte b = 0; b < arrayOfExpression.length; b++) {
        if (arrayOfExpression[b].getType() != 25)
          arrayOfExpression[b] = new Expression(25, new Expression[] { arrayOfExpression[b] }); 
        Expression[] arrayOfExpression1 = (arrayOfExpression[b]).nodes;
        if (arrayOfExpression1.length != paramInt)
          throw unexpectedToken(); 
        for (byte b1 = 0; b1 < paramInt; b1++) {
          if (arrayOfExpression1[b1].getType() == 25)
            throw unexpectedToken(); 
        } 
      } 
      return expression2;
    } 
  }
  
  private ExpressionLogical XreadLikePredicateRightPart(Expression paramExpression) {
    read();
    Expression expression1 = XreadStringValueExpression();
    Expression expression2 = null;
    if (this.token.tokenString.equals("ESCAPE")) {
      read();
      expression2 = XreadStringValueExpression();
    } 
    return new ExpressionLike(paramExpression, expression1, expression2, this.isCheckOrTriggerCondition);
  }
  
  private ExpressionLogical XreadMatchPredicateRightPart(Expression paramExpression) {
    boolean bool = false;
    byte b1 = 59;
    read();
    if (this.token.tokenType == 299) {
      read();
      bool = true;
    } 
    if (this.token.tokenType == 511) {
      read();
      b1 = bool ? 62 : 59;
    } else if (this.token.tokenType == 470) {
      read();
      b1 = bool ? 63 : 60;
    } else if (this.token.tokenType == 116) {
      read();
      b1 = bool ? 64 : 61;
    } 
    byte b2 = bool ? 23 : 54;
    Expression expression = XreadTableSubquery(b2);
    return new ExpressionLogical(b1, paramExpression, expression);
  }
  
  private ExpressionLogical XreadOverlapsPredicateRightPart(Expression paramExpression) {
    if (paramExpression.getType() != 25)
      throw Error.error(5564); 
    if (paramExpression.nodes.length != 2)
      throw Error.error(5564); 
    read();
    if (this.token.tokenType != 816)
      throw unexpectedToken(); 
    Expression expression = XreadRowValuePredicand();
    if (expression.nodes.length != 2)
      throw Error.error(5564); 
    return new ExpressionLogical(56, paramExpression, expression);
  }
  
  Expression XreadRowValueExpression() {
    Expression expression = XreadExplicitRowValueConstructorOrNull();
    return (expression != null) ? expression : XreadRowValueSpecialCase();
  }
  
  Expression XreadTableRowValueConstructor() {
    Expression expression = XreadExplicitRowValueConstructorOrNull();
    return (expression != null) ? expression : XreadRowValueSpecialCase();
  }
  
  Expression XreadRowValuePredicand() {
    return XreadRowOrCommonValueExpression();
  }
  
  Expression XreadRowValueSpecialCase() {
    Expression expression = XreadSimpleValueExpressionPrimary();
    if (expression != null)
      expression = XreadArrayElementReference(expression); 
    return expression;
  }
  
  Expression XreadRowValueConstructor() {
    Expression expression = XreadExplicitRowValueConstructorOrNull();
    if (expression != null)
      return expression; 
    expression = XreadRowOrCommonValueExpression();
    return (expression != null) ? expression : XreadBooleanValueExpression();
  }
  
  Expression XreadExplicitRowValueConstructorOrNull() {
    Expression expression;
    int i;
    TableDerived tableDerived;
    switch (this.token.tokenType) {
      case 816:
        read();
        i = getPosition();
        readOpenBrackets();
        switch (this.token.tokenType) {
          case 251:
          case 278:
          case 308:
            rewind(i);
            tableDerived = XreadSubqueryTableBody(22);
            readThis(802);
            return new Expression(22, tableDerived);
        } 
        rewind(i);
        expression = XreadRowElementList(true);
        readThis(802);
        return expression;
      case 243:
        read();
        readThis(816);
        expression = XreadRowElementList(false);
        readThis(802);
        return expression;
    } 
    return null;
  }
  
  Expression XreadRowElementList(boolean paramBoolean) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    while (true) {
      Expression expression = XreadValueExpression();
      hsqlArrayList.add(expression);
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      if (paramBoolean && hsqlArrayList.size() == 1)
        return expression; 
      Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfExpression);
      return new Expression(25, arrayOfExpression);
    } 
  }
  
  Expression XreadCurrentCollationSpec() {
    throw Error.error(1500);
  }
  
  Expression XreadTableSubquery(int paramInt) {
    readThis(816);
    TableDerived tableDerived = XreadSubqueryTableBody(paramInt);
    readThis(802);
    return new Expression(23, tableDerived);
  }
  
  Table XreadTableSubqueryOrNull(boolean paramBoolean) {
    boolean bool = false;
    int i = getPosition();
    readThis(816);
    switch (this.token.tokenType) {
      case 251:
      case 278:
      case 308:
      case 319:
        break;
      case 816:
        if (paramBoolean)
          break; 
      default:
        bool = true;
        break;
    } 
    if (bool) {
      rewind(i);
      return null;
    } 
    TableDerived tableDerived = XreadSubqueryTableBody(23);
    readThis(802);
    return tableDerived;
  }
  
  TableDerived XreadJoinedTableAsSubqueryOrNull() {
    int i = getPosition();
    readThis(816);
    this.compileContext.incrementDepth();
    QuerySpecification querySpecification = XreadJoinedTableAsView();
    querySpecification.resolveReferences(this.session, this.compileContext.getOuterRanges());
    if (querySpecification.rangeVariables.length < 2) {
      this.compileContext.decrementDepth();
      rewind(i);
      return null;
    } 
    querySpecification.resolveTypesPartOne(this.session);
    querySpecification.resolveTypesPartTwo(this.session);
    TableDerived tableDerived = newSubQueryTable(querySpecification, 23);
    readThis(802);
    tableDerived.setSQL(getLastPart(i));
    tableDerived.prepareTable();
    this.compileContext.decrementDepth();
    return tableDerived;
  }
  
  QuerySpecification XreadJoinedTableAsView() {
    QuerySpecification querySpecification = new QuerySpecification(this.compileContext);
    ExpressionColumn expressionColumn = new ExpressionColumn(97);
    querySpecification.addSelectColumnExpression(expressionColumn);
    XreadTableReference(querySpecification);
    return querySpecification;
  }
  
  TableDerived XreadTableNamedSubqueryBody(HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, int paramInt) {
    TableDerived tableDerived;
    switch (paramInt) {
      case 24:
        return XreadRecursiveSubqueryBody(paramHsqlName, paramArrayOfHsqlName);
      case 23:
        tableDerived = XreadSubqueryTableBody(paramHsqlName, paramInt);
        if (tableDerived.queryExpression != null)
          tableDerived.queryExpression.resolve(this.session); 
        tableDerived.prepareTable(paramArrayOfHsqlName);
        return tableDerived;
    } 
    throw unexpectedToken();
  }
  
  TableDerived XreadRecursiveSubqueryBody(HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName) {
    int i = getPosition();
    this.compileContext.incrementDepth();
    this.compileContext.incrementDepth();
    QuerySpecification querySpecification1 = XreadSimpleTable();
    querySpecification1.resolveReferences(this.session, this.compileContext.getOuterRanges());
    querySpecification1.resolve(this.session);
    TableDerived tableDerived1 = newSubQueryTable(paramHsqlName, querySpecification1, 23);
    this.compileContext.decrementDepth();
    tableDerived1.prepareTable(paramArrayOfHsqlName);
    this.compileContext.initSubqueryNames();
    this.compileContext.registerSubquery(paramHsqlName.name, tableDerived1);
    checkIsThis(298);
    int j = XreadUnionType();
    QuerySpecification querySpecification2 = XreadSimpleTable();
    QueryExpression queryExpression = new QueryExpression(this.compileContext, querySpecification1);
    querySpecification2.resolveReferences(this.session, this.compileContext.getOuterRanges());
    queryExpression.addUnion(querySpecification2, j);
    queryExpression.isRecursive = true;
    queryExpression.recursiveTable = tableDerived1;
    queryExpression.resolve(this.session);
    TableDerived tableDerived2 = newSubQueryTable(paramHsqlName, queryExpression, 23);
    tableDerived2.prepareTable(paramArrayOfHsqlName);
    tableDerived2.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return tableDerived2;
  }
  
  TableDerived newSubQueryTable(Expression paramExpression, int paramInt) {
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.getSubqueryTableName();
    return new TableDerived(this.database, hsqlName, 2, null, paramExpression, paramInt, this.compileContext.getDepth());
  }
  
  TableDerived newSubQueryTable(QueryExpression paramQueryExpression, int paramInt) {
    return newSubQueryTable((HsqlNameManager.HsqlName)null, paramQueryExpression, paramInt);
  }
  
  TableDerived newSubQueryTable(HsqlNameManager.HsqlName paramHsqlName, QueryExpression paramQueryExpression, int paramInt) {
    if (paramHsqlName == null)
      paramHsqlName = this.database.nameManager.getSubqueryTableName(); 
    return new TableDerived(this.database, paramHsqlName, 2, paramQueryExpression, null, paramInt, this.compileContext.getDepth());
  }
  
  TableDerived XreadSubqueryTableBody(int paramInt) {
    return XreadSubqueryTableBody((HsqlNameManager.HsqlName)null, paramInt);
  }
  
  TableDerived XreadSubqueryTableBody(HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    int i = getPosition();
    this.compileContext.incrementDepth();
    QueryExpression queryExpression = XreadQueryExpression();
    TableDerived tableDerived = null;
    if (queryExpression.isValueList)
      tableDerived = ((QuerySpecification)queryExpression).getValueListTable(); 
    if (tableDerived == null)
      tableDerived = newSubQueryTable(paramHsqlName, queryExpression, paramInt); 
    tableDerived.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return tableDerived;
  }
  
  TableDerived XreadViewSubqueryTable(View paramView, boolean paramBoolean) {
    QueryExpression queryExpression;
    this.compileContext.incrementDepth();
    try {
      queryExpression = XreadQueryExpression();
    } catch (HsqlException hsqlException) {
      queryExpression = XreadJoinedTableAsView();
    } 
    queryExpression.setView(paramView);
    queryExpression.resolveReferences(this.session, RangeGroup.emptyArray);
    queryExpression.resolveTypesPartOne(this.session);
    queryExpression.resolveTypesPartTwo(this.session);
    if (paramBoolean)
      queryExpression.resolveTypesPartThree(this.session); 
    TableDerived tableDerived = new TableDerived(this.database, paramView.getName(), 8, queryExpression, null, 0, this.compileContext.getDepth());
    tableDerived.view = paramView;
    tableDerived.columnList = paramView.columnList;
    tableDerived.columnCount = paramView.columnList.size();
    tableDerived.createPrimaryKey();
    tableDerived.triggerList = paramView.triggerList;
    tableDerived.triggerLists = paramView.triggerLists;
    this.compileContext.decrementDepth();
    return tableDerived;
  }
  
  Expression XreadContextuallyTypedTable(int paramInt) {
    Expression expression = readRow();
    Expression[] arrayOfExpression = expression.nodes;
    boolean bool = false;
    if (paramInt == 1) {
      if (expression.getType() == 25) {
        expression.opType = 26;
        for (byte b1 = 0; b1 < arrayOfExpression.length; b1++) {
          if (arrayOfExpression[b1].getType() != 25) {
            arrayOfExpression[b1] = new Expression(25, new Expression[] { arrayOfExpression[b1] });
          } else if ((arrayOfExpression[b1]).nodes.length != paramInt) {
            throw Error.error(5564);
          } 
        } 
        return expression;
      } 
      expression = new Expression(25, new Expression[] { expression });
      return new Expression(26, new Expression[] { expression });
    } 
    if (expression.getType() != 25)
      throw Error.error(5564); 
    byte b;
    for (b = 0; b < arrayOfExpression.length; b++) {
      if (arrayOfExpression[b].getType() == 25) {
        bool = true;
        break;
      } 
    } 
    if (bool) {
      expression.opType = 26;
      for (b = 0; b < arrayOfExpression.length; b++) {
        if (arrayOfExpression[b].getType() != 25)
          throw Error.error(5564); 
        Expression[] arrayOfExpression1 = (arrayOfExpression[b]).nodes;
        if (arrayOfExpression1.length != paramInt)
          throw Error.error(5564); 
        for (byte b1 = 0; b1 < paramInt; b1++) {
          if (arrayOfExpression1[b1].getType() == 25)
            throw Error.error(5564); 
        } 
      } 
    } else {
      if (arrayOfExpression.length != paramInt)
        throw Error.error(5564); 
      expression = new Expression(26, new Expression[] { expression });
    } 
    return expression;
  }
  
  private Expression XreadInValueListConstructor(int paramInt) {
    int i = getPosition();
    this.compileContext.incrementDepth();
    Expression expression = XreadInValueList(paramInt);
    TableDerived tableDerived = newSubQueryTable(expression, 54);
    tableDerived.setSQL(getLastPart(i));
    expression.table = tableDerived;
    this.compileContext.decrementDepth();
    return expression;
  }
  
  private TableDerived XreadRowValueExpressionList() {
    this.compileContext.incrementDepth();
    Expression expression = XreadRowValueExpressionListBody();
    TableDerived tableDerived = prepareSubqueryTable(expression, (HsqlNameManager.HsqlName[])null);
    this.compileContext.decrementDepth();
    return tableDerived;
  }
  
  private TableDerived prepareSubqueryTable(Expression paramExpression, HsqlNameManager.HsqlName[] paramArrayOfHsqlName) {
    HsqlList hsqlList = paramExpression.resolveColumnReferences(this.session, RangeGroup.emptyGroup, this.compileContext.getOuterRanges(), null);
    ExpressionColumn.checkColumnsResolved(hsqlList);
    paramExpression.resolveTypes(this.session, null);
    paramExpression.prepareTable(this.session, null, (paramExpression.nodes[0]).nodes.length);
    TableDerived tableDerived = newSubQueryTable(paramExpression, 26);
    tableDerived.prepareTable(paramArrayOfHsqlName);
    return tableDerived;
  }
  
  Expression XreadRowValueExpressionListBody() {
    Expression expression = null;
    while (true) {
      int i = readOpenBrackets();
      Expression expression1 = readRow();
      readCloseBrackets(i);
      if (expression == null) {
        expression = new Expression(25, new Expression[] { expression1 });
      } else {
        expression.nodes = (Expression[])ArrayUtil.resizeArray(expression.nodes, expression.nodes.length + 1);
        expression.nodes[expression.nodes.length - 1] = expression1;
      } 
      if (this.token.tokenType != 804) {
        Expression[] arrayOfExpression = expression.nodes;
        int j = 1;
        if (arrayOfExpression[0].getType() == 25)
          j = (arrayOfExpression[0]).nodes.length; 
        expression.opType = 26;
        for (byte b = 0; b < arrayOfExpression.length; b++) {
          if (arrayOfExpression[b].getType() == 25) {
            if ((arrayOfExpression[b]).nodes.length != j)
              throw Error.error(5564); 
          } else {
            if (j != 1)
              throw Error.error(5564); 
            arrayOfExpression[b] = new Expression(25, new Expression[] { arrayOfExpression[b] });
          } 
        } 
        return expression;
      } 
      read();
    } 
  }
  
  Expression XreadTargetSpecification(RangeVariable[] paramArrayOfRangeVariable, LongDeque paramLongDeque) {
    ColumnSchema columnSchema = null;
    int i = -1;
    checkIsIdentifier();
    if (this.token.namePrePrePrefix != null)
      checkValidCatalogName(this.token.namePrePrePrefix); 
    for (byte b = 0; b < paramArrayOfRangeVariable.length; b++) {
      if (paramArrayOfRangeVariable[b] != null) {
        i = paramArrayOfRangeVariable[b].findColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
        if (i > -1) {
          columnSchema = paramArrayOfRangeVariable[b].getColumn(i);
          read();
          break;
        } 
      } 
    } 
    if (columnSchema == null)
      throw Error.error(5501, this.token.tokenString); 
    paramLongDeque.add(i);
    if (this.token.tokenType == 811) {
      if (!columnSchema.getDataType().isArrayType())
        throw unexpectedToken(); 
      read();
      Expression expression = XreadNumericValueExpression();
      if (expression == null)
        throw Error.error(5501, this.token.tokenString); 
      expression = new ExpressionAccessor(columnSchema.getAccessor(), expression);
      readThis(820);
      return expression;
    } 
    return columnSchema.getAccessor();
  }
  
  Expression XreadCollectionDerivedTable(int paramInt) {
    boolean bool = false;
    int i = getPosition();
    readThis(301);
    readThis(816);
    this.compileContext.incrementDepth();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    while (true) {
      Expression expression = XreadValueExpression();
      hsqlArrayList.add(expression);
      if (this.token.tokenType == 804) {
        read();
        continue;
      } 
      Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfExpression);
      readThis(802);
      if (this.token.tokenType == 319) {
        read();
        readThis(458);
        bool = true;
      } 
      ExpressionTable expressionTable = new ExpressionTable(arrayOfExpression, bool);
      TableDerived tableDerived = newSubQueryTable(expressionTable, paramInt);
      tableDerived.setSQL(getLastPart(i));
      this.compileContext.decrementDepth();
      return expressionTable;
    } 
  }
  
  Expression XreadTableFunctionDerivedTable() {
    int i = getPosition();
    readThis(278);
    readThis(816);
    this.compileContext.incrementDepth();
    Expression expression = XreadValueExpression();
    if (expression.getType() != 27 && expression.getType() != 28) {
      this.compileContext.decrementDepth();
      throw unexpectedToken("TABLE");
    } 
    readThis(802);
    expression = new ExpressionTable(new Expression[] { expression }, false);
    TableDerived tableDerived = newSubQueryTable(expression, 23);
    tableDerived.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return expression;
  }
  
  Expression XreadLateralDerivedTable() {
    readThis(149);
    readThis(816);
    TableDerived tableDerived = XreadSubqueryTableBody(23);
    readThis(802);
    return new Expression(23, tableDerived);
  }
  
  Expression XreadArrayConstructor() {
    readThis(816);
    TableDerived tableDerived = XreadSubqueryTableBody(23);
    readThis(802);
    return new Expression(100, tableDerived);
  }
  
  Collation readCollateClauseOrNull() {
    if (this.token.tokenType == 41) {
      read();
      return this.database.schemaManager.getCollation(this.session, this.token.tokenString, this.token.namePrefix);
    } 
    return null;
  }
  
  Expression XreadArrayElementReference(Expression paramExpression) {
    if (this.token.tokenType == 811) {
      read();
      Expression expression = XreadNumericValueExpression();
      readThis(820);
      paramExpression = new ExpressionAccessor(paramExpression, expression);
    } 
    return paramExpression;
  }
  
  Expression readRow() {
    Expression expression = null;
    while (true) {
      Expression expression1 = XreadValueExpressionWithContext();
      if (expression == null) {
        expression = expression1;
      } else if (expression.getType() == 25) {
        if (expression1.getType() == 25 && expression.nodes[0].getType() != 25) {
          expression = new Expression(25, new Expression[] { expression, expression1 });
        } else {
          expression.nodes = (Expression[])ArrayUtil.resizeArray(expression.nodes, expression.nodes.length + 1);
          expression.nodes[expression.nodes.length - 1] = expression1;
        } 
      } else {
        expression = new Expression(25, new Expression[] { expression, expression1 });
      } 
      if (this.token.tokenType != 804)
        return expression; 
      read();
    } 
  }
  
  Expression readCaseExpression() {
    Expression expression = null;
    read();
    if (this.token.tokenType != 314)
      expression = XreadRowValuePredicand(); 
    return readCaseWhen(expression);
  }
  
  private Expression readCaseWhen(Expression paramExpression) {
    readThis(314);
    Expression expression1 = null;
    if (paramExpression == null) {
      expression1 = XreadBooleanValueExpression();
    } else {
      while (true) {
        Expression expression = XreadPredicateRightPart(paramExpression);
        if (paramExpression == expression)
          expression = new ExpressionLogical(paramExpression, XreadRowValuePredicand()); 
        if (expression1 == null) {
          expression1 = expression;
        } else {
          expression1 = new ExpressionLogical(50, expression1, expression);
        } 
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        break;
      } 
    } 
    readThis(280);
    Expression expression2 = XreadValueExpression();
    Expression expression3 = null;
    if (this.token.tokenType == 314) {
      expression3 = readCaseWhen(paramExpression);
    } else if (this.token.tokenType == 92) {
      read();
      expression3 = XreadValueExpression();
      readThis(94);
      readIfThis(29);
    } else {
      expression3 = new ExpressionValue(null, Type.SQL_ALL_TYPES);
      readThis(94);
      readIfThis(29);
    } 
    ExpressionOp expressionOp = new ExpressionOp(96, expression2, expression3);
    return new ExpressionOp(93, expression1, expressionOp);
  }
  
  private Expression readCaseWhenExpressionOrNull() {
    Expression expression1 = null;
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    expression1 = XreadBooleanValueExpression();
    readThis(804);
    Expression expression2 = XreadValueExpression();
    readThis(804);
    ExpressionOp expressionOp = new ExpressionOp(96, expression2, XreadValueExpression());
    expression1 = new ExpressionOp(93, expression1, expressionOp);
    readThis(802);
    return expression1;
  }
  
  private Expression readCastExpressionOrNull() {
    Expression expression;
    Type type;
    boolean bool = (this.token.tokenType == 49) ? true : false;
    int i = getPosition();
    read();
    if (bool) {
      if (!readIfThis(816)) {
        rewind(i);
        return null;
      } 
      if (this.database.sqlSyntaxMss) {
        type = readTypeDefinition(false, true);
        readThis(804);
        expression = XreadValueExpressionOrNull();
      } else {
        expression = XreadValueExpressionOrNull();
        readThis(804);
        type = Type.getTypeForJDBCConvertToken(this.token.tokenType);
        if (type == null) {
          type = readTypeDefinition(false, true);
        } else {
          read();
        } 
      } 
    } else {
      readThis(816);
      expression = XreadValueExpressionOrNull();
      readThis(10);
      type = readTypeDefinition(false, true);
    } 
    if (expression.isUnresolvedParam()) {
      expression.setDataType(this.session, type);
    } else {
      expression = new ExpressionOp(expression, type);
    } 
    readThis(802);
    return expression;
  }
  
  private Expression readColumnOrFunctionExpression() {
    String str1 = this.token.tokenString;
    boolean bool = isDelimitedSimpleName();
    String str2 = this.token.namePrefix;
    String str3 = this.token.namePrePrefix;
    String str4 = this.token.namePrePrePrefix;
    Token token = getRecordedToken();
    checkIsIdentifier();
    if (isUndelimitedSimpleName()) {
      int i = this.token.tokenType;
      FunctionSQL functionSQL = FunctionCustom.newCustomFunction(this.token.tokenString, this.token.tokenType);
      if (functionSQL != null && i == 759 && !this.database.sqlSyntaxOra)
        functionSQL = null; 
      if (functionSQL != null) {
        int j = getPosition();
        try {
          Expression expression = readSQLFunction(functionSQL);
          if (expression != null)
            return expression; 
        } catch (HsqlException hsqlException) {
          hsqlException.setLevel(this.compileContext.subqueryDepth);
          if (this.lastError == null || this.lastError.getLevel() < hsqlException.getLevel())
            this.lastError = hsqlException; 
          rewind(j);
        } 
      } else if (isReservedKey()) {
        functionSQL = FunctionSQL.newSQLFunction(str1, this.compileContext);
        if (functionSQL != null) {
          Expression expression = readSQLFunction(functionSQL);
          if (expression != null)
            return expression; 
        } 
      } 
    } 
    read();
    if (this.token.tokenType != 816) {
      checkValidCatalogName(str4);
      return new ExpressionColumn(str3, str2, str1);
    } 
    if (str4 != null)
      throw Error.error(5551, str4); 
    checkValidCatalogName(str3);
    str2 = this.session.getSchemaName(str2);
    RoutineSchema routineSchema = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, str2, 16);
    if (routineSchema == null && bool) {
      HsqlNameManager.HsqlName hsqlName = this.database.schemaManager.getDefaultSchemaHsqlName();
      routineSchema = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, hsqlName.name, 16);
      if (routineSchema == null) {
        Routine.createRoutines(this.session, hsqlName, str1);
        routineSchema = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, hsqlName.name, 16);
      } 
    } 
    if (routineSchema == null) {
      if (this.lastError != null)
        throw this.lastError; 
      throw Error.error(5501, str1);
    } 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    readThis(816);
    if (this.token.tokenType == 802) {
      read();
    } else {
      while (true) {
        Expression expression = XreadValueExpression();
        hsqlArrayList.add(expression);
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        readThis(802);
        expression = new FunctionSQLInvoked(routineSchema);
        Expression[] arrayOfExpression1 = new Expression[hsqlArrayList.size()];
        hsqlArrayList.toArray(arrayOfExpression1);
        expression.setArguments(arrayOfExpression1);
        this.compileContext.addFunctionCall((FunctionSQLInvoked)expression);
        token.setExpression(routineSchema);
        return expression;
      } 
    } 
    FunctionSQLInvoked functionSQLInvoked = new FunctionSQLInvoked(routineSchema);
    Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression);
    functionSQLInvoked.setArguments(arrayOfExpression);
    this.compileContext.addFunctionCall(functionSQLInvoked);
    token.setExpression(routineSchema);
    return functionSQLInvoked;
  }
  
  Expression readCollection(int paramInt) {
    read();
    if (this.token.tokenType == 816)
      return XreadArrayConstructor(); 
    readThis(811);
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    byte b = 0;
    while (true) {
      if (this.token.tokenType == 820) {
        read();
      } else {
        if (b)
          readThis(804); 
        Expression expression = XreadValueExpressionOrNull();
        hsqlArrayList.add(expression);
        b++;
        continue;
      } 
      Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfExpression);
      return new Expression(19, arrayOfExpression);
    } 
  }
  
  private Expression readDecodeExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    ExpressionOp expressionOp = null;
    Expression expression1 = null;
    Expression expression2 = XreadValueExpression();
    readThis(804);
    while (true) {
      Expression expression3 = XreadValueExpression();
      if (this.token.tokenType == 804) {
        readThis(804);
      } else {
        if (expression1 == null)
          throw unexpectedToken(); 
        expression1.setRightNode(expression3);
        break;
      } 
      ExpressionLogical expressionLogical = new ExpressionLogical(58, expression2, expression3);
      Expression expression4 = XreadValueExpression();
      ExpressionOp expressionOp1 = new ExpressionOp(96, expression4, null);
      ExpressionOp expressionOp2 = new ExpressionOp(93, expressionLogical, expressionOp1);
      if (expressionOp == null) {
        expressionOp = expressionOp2;
      } else {
        expression1.setRightNode(expressionOp2);
      } 
      expression1 = expressionOp1;
      if (this.token.tokenType == 804) {
        readThis(804);
        continue;
      } 
      expression1.setRightNode(new ExpressionValue(null, null));
      break;
    } 
    readThis(802);
    return expressionOp;
  }
  
  private Expression readConcatExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression = XreadValueExpression();
    readThis(804);
    while (true) {
      Expression expression1 = XreadValueExpression();
      expression = new ExpressionArithmetic(36, expression, expression1);
      if (this.token.tokenType == 804) {
        readThis(804);
        continue;
      } 
      if (this.token.tokenType == 802) {
        readThis(802);
        return expression;
      } 
    } 
  }
  
  private Expression readConcatSeparatorExpressionOrNull() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression = XreadValueExpression();
    hsqlArrayList.add(expression);
    readThis(804);
    expression = XreadValueExpression();
    hsqlArrayList.add(expression);
    readThis(804);
    while (true) {
      expression = XreadValueExpression();
      hsqlArrayList.add(expression);
      if (this.token.tokenType == 804) {
        readThis(804);
        continue;
      } 
      if (this.token.tokenType == 802) {
        readThis(802);
        Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
        hsqlArrayList.toArray(arrayOfExpression);
        return new ExpressionOp(86, arrayOfExpression);
      } 
    } 
  }
  
  private Expression readLeastExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression = null;
    while (true) {
      expression = readValue(expression, 44);
      if (this.token.tokenType == 804) {
        readThis(804);
        continue;
      } 
      readThis(802);
      return expression;
    } 
  }
  
  private Expression readGreatestExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression = null;
    while (true) {
      expression = readValue(expression, 43);
      if (this.token.tokenType == 804) {
        readThis(804);
        continue;
      } 
      readThis(802);
      return expression;
    } 
  }
  
  private Expression readValue(Expression paramExpression, int paramInt) {
    Expression expression = XreadValueExpression();
    if (paramExpression == null)
      return expression; 
    ExpressionLogical expressionLogical = new ExpressionLogical(paramInt, paramExpression, expression);
    ExpressionOp expressionOp = new ExpressionOp(96, paramExpression, expression);
    return new ExpressionOp(93, expressionLogical, expressionOp);
  }
  
  private Expression readNullIfExpression() {
    read();
    readThis(816);
    Expression expression = XreadValueExpression();
    readThis(804);
    ExpressionOp expressionOp = new ExpressionOp(96, new ExpressionValue(null, (Type)null), expression);
    expression = new ExpressionLogical(expression, XreadValueExpression());
    expression = new ExpressionOp(93, expression, expressionOp);
    readThis(802);
    return expression;
  }
  
  private Expression readIfNullExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression1 = XreadValueExpression();
    readThis(804);
    Expression expression2 = XreadValueExpression();
    ExpressionLogical expressionLogical = new ExpressionLogical(47, expression1);
    ExpressionOp expressionOp = new ExpressionOp(96, expression2, expression1);
    expression1 = new ExpressionOp(93, expressionLogical, expressionOp);
    expression1.setSubType(91);
    readThis(802);
    return expression1;
  }
  
  private Expression readIfNull2ExpressionOrNull() {
    int i = getPosition();
    read();
    if (!readIfThis(816)) {
      rewind(i);
      return null;
    } 
    Expression expression1 = XreadValueExpression();
    readThis(804);
    Expression expression2 = XreadValueExpression();
    readThis(804);
    Expression expression3 = XreadValueExpression();
    ExpressionLogical expressionLogical = new ExpressionLogical(47, expression1);
    ExpressionOp expressionOp = new ExpressionOp(96, expression3, expression2);
    expression1 = new ExpressionOp(93, expressionLogical, expressionOp);
    expression1.setSubType(91);
    readThis(802);
    return expression1;
  }
  
  private Expression readCoalesceExpression() {
    ExpressionOp expressionOp = null;
    read();
    readThis(816);
    Expression expression = null;
    while (true) {
      Expression expression1 = XreadValueExpression();
      if (expression != null && this.token.tokenType == 802) {
        readThis(802);
        expression.setLeftNode(expression1);
      } else {
        ExpressionLogical expressionLogical = new ExpressionLogical(47, expression1);
        ExpressionOp expressionOp1 = new ExpressionOp(96, new ExpressionValue(null, (Type)null), expression1);
        ExpressionOp expressionOp2 = new ExpressionOp(93, expressionLogical, expressionOp1);
        if (this.session.database.sqlSyntaxMys)
          expressionOp2.setSubType(91); 
        if (expressionOp == null) {
          expressionOp = expressionOp2;
        } else {
          expression.setLeftNode(expressionOp2);
        } 
        expression = expressionOp1;
        readThis(804);
        continue;
      } 
      return expressionOp;
    } 
  }
  
  Expression readSQLFunction(FunctionSQL paramFunctionSQL) {
    int i = getPosition();
    read();
    short[] arrayOfShort = paramFunctionSQL.parseList;
    if (arrayOfShort.length == 0)
      return paramFunctionSQL; 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    boolean bool = (this.token.tokenType == 816) ? true : false;
    if (!bool) {
      if (arrayOfShort[0] == 864)
        return paramFunctionSQL; 
      rewind(i);
      return null;
    } 
    try {
      readExpression(hsqlArrayList, arrayOfShort, 0, arrayOfShort.length, false);
      this.lastError = null;
    } catch (HsqlException hsqlException) {
      if (!bool) {
        rewind(i);
        return null;
      } 
      if (paramFunctionSQL.parseListAlt == null)
        throw hsqlException; 
      rewind(i);
      read();
      arrayOfShort = paramFunctionSQL.parseListAlt;
      hsqlArrayList = new HsqlArrayList();
      readExpression(hsqlArrayList, arrayOfShort, 0, arrayOfShort.length, false);
      this.lastError = null;
    } 
    Expression[] arrayOfExpression = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression);
    paramFunctionSQL.setArguments(arrayOfExpression);
    return paramFunctionSQL.getFunctionExpression();
  }
  
  void readExpression(HsqlArrayList paramHsqlArrayList, short[] paramArrayOfshort, int paramInt1, int paramInt2, boolean paramBoolean) {
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
      Expression expression;
      int j;
      Integer integer;
      int k;
      ExpressionValue expressionValue;
      short s2;
      int m;
      short s1 = paramArrayOfshort[i];
      switch (s1) {
        case 818:
          expression = null;
          expression = XreadAllTypesCommonValueExpression(false);
          paramHsqlArrayList.add(expression);
          break;
        case 866:
          expression = null;
          integer = readIntegerObject();
          if (integer.intValue() < 0)
            throw Error.error(5592); 
          expression = new ExpressionValue(integer, (Type)Type.SQL_INTEGER);
          paramHsqlArrayList.add(expression);
          break;
        case 864:
          i++;
          j = paramHsqlArrayList.size();
          k = getPosition();
          s2 = paramArrayOfshort[i++];
          m = paramHsqlArrayList.size();
          try {
            readExpression(paramHsqlArrayList, paramArrayOfshort, i, s2, true);
          } catch (HsqlException hsqlException) {
            hsqlException.setLevel(this.compileContext.subqueryDepth);
            if (this.lastError == null || this.lastError.getLevel() < hsqlException.getLevel())
              this.lastError = hsqlException; 
            rewind(k);
            paramHsqlArrayList.setSize(j);
            for (int n = i; n < i + s2; n++) {
              if (paramArrayOfshort[n] == 818 || paramArrayOfshort[n] == 863 || paramArrayOfshort[n] == 866)
                paramHsqlArrayList.add(null); 
            } 
            i += s2 - 1;
            break;
          } 
          if (m == paramHsqlArrayList.size())
            paramHsqlArrayList.add(null); 
          i += s2 - 1;
          break;
        case 865:
          i++;
          j = paramArrayOfshort[++i];
          k = i;
          while (true) {
            int n = paramHsqlArrayList.size();
            readExpression(paramHsqlArrayList, paramArrayOfshort, k, j, true);
            if (paramHsqlArrayList.size() == n) {
              i += j - 1;
              break;
            } 
          } 
          break;
        case 863:
          j = paramArrayOfshort[++i];
          expressionValue = null;
          if (ArrayUtil.find(paramArrayOfshort, this.token.tokenType, i + 1, j) == -1) {
            if (!paramBoolean)
              throw unexpectedToken(); 
          } else {
            expressionValue = new ExpressionValue(ValuePool.getInt(this.token.tokenType), (Type)Type.SQL_INTEGER);
            read();
          } 
          paramHsqlArrayList.add(expressionValue);
          i += j;
          break;
        default:
          if (this.token.tokenType != s1)
            throw unexpectedToken(); 
          read();
          break;
      } 
    } 
  }
  
  private Expression readSequenceExpressionOrNull(int paramInt) {
    int i = getPosition();
    if (this.token.tokenType == 448) {
      read();
      if (this.token.tokenType != 307) {
        rewind(i);
        return null;
      } 
      readThis(307);
    } else if (this.database.sqlSyntaxDb2 && this.token.tokenType == 611) {
      read();
    } else if (this.database.sqlSyntaxDb2 && this.token.tokenType == 617) {
      read();
    } else {
      rewind(i);
      return null;
    } 
    readThis(112);
    checkIsSchemaObjectName();
    String str = this.session.getSchemaName(this.token.namePrefix);
    NumberSequence numberSequence = this.database.schemaManager.getSequence(this.token.tokenString, str, true);
    Token token = getRecordedToken();
    read();
    ExpressionColumn expressionColumn = new ExpressionColumn(numberSequence, paramInt);
    token.setExpression(numberSequence);
    this.compileContext.addSequence(numberSequence);
    return expressionColumn;
  }
  
  HsqlNameManager.SimpleName readSimpleName() {
    checkIsSimpleName();
    HsqlNameManager.SimpleName simpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
    read();
    return simpleName;
  }
  
  HsqlNameManager.HsqlName readNewSchemaName() {
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(2, false);
    SqlInvariants.checkSchemaNameNotSystem(hsqlName.name);
    return hsqlName;
  }
  
  HsqlNameManager.HsqlName readNewSchemaObjectName(int paramInt, boolean paramBoolean) {
    checkIsSchemaObjectName();
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, isDelimitedIdentifier(), paramInt);
    if (this.token.namePrefix != null) {
      HsqlNameManager.HsqlName hsqlName1;
      switch (paramInt) {
        case 1:
        case 11:
        case 21:
        case 22:
          throw unexpectedToken();
        case 19:
          if (this.token.namePrePrefix != null || this.token.isDelimitedPrefix || !"MODULE".equals(this.token.namePrefix))
            throw unexpectedTokenRequire("MODULE"); 
          read();
          return hsqlName;
        case 2:
          checkValidCatalogName(this.token.namePrefix);
          if (this.token.namePrePrefix != null)
            throw tooManyIdentifiers(); 
          read();
          return hsqlName;
        case 25:
        case 26:
          checkValidCatalogName(this.token.namePrefix);
          if (this.token.namePrePrefix != null)
            throw tooManyIdentifiers(); 
          read();
          return hsqlName;
        case 9:
          if (this.token.namePrefix != null)
            throw tooManyIdentifiers(); 
          read();
          return hsqlName;
      } 
      checkValidCatalogName(this.token.namePrePrefix);
      if (paramBoolean) {
        hsqlName1 = this.session.getSchemaHsqlName(this.token.namePrefix);
      } else {
        hsqlName1 = this.session.database.schemaManager.findSchemaHsqlName(this.token.namePrefix);
        if (hsqlName1 == null)
          hsqlName1 = this.database.nameManager.newHsqlName(this.token.namePrefix, isDelimitedIdentifier(), 2); 
      } 
      hsqlName.setSchemaIfNull(hsqlName1);
    } 
    read();
    return hsqlName;
  }
  
  HsqlNameManager.HsqlName readNewDependentSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(paramInt, true);
    hsqlName.parent = paramHsqlName;
    hsqlName.setSchemaIfNull(paramHsqlName.schema);
    if (hsqlName.schema != null && paramHsqlName.schema != null && hsqlName.schema != paramHsqlName.schema)
      throw Error.error(5505, this.token.namePrefix); 
    return hsqlName;
  }
  
  HsqlNameManager.HsqlName readSchemaName() {
    checkIsSchemaObjectName();
    checkValidCatalogName(this.token.namePrefix);
    HsqlNameManager.HsqlName hsqlName = this.session.getSchemaHsqlName(this.token.tokenString);
    read();
    return hsqlName;
  }
  
  SchemaObject readSchemaObjectName(int paramInt) {
    checkIsSchemaObjectName();
    checkValidCatalogName(this.token.namePrePrefix);
    String str = this.session.getSchemaName(this.token.namePrefix);
    SchemaObject schemaObject = this.database.schemaManager.getSchemaObject(this.token.tokenString, str, paramInt);
    read();
    return schemaObject;
  }
  
  SchemaObject readSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    checkIsSchemaObjectName();
    SchemaObject schemaObject = this.database.schemaManager.getSchemaObject(this.token.tokenString, paramHsqlName.name, paramInt);
    if (this.token.namePrefix != null) {
      if (!this.token.namePrefix.equals(paramHsqlName.name))
        throw Error.error(5505, this.token.namePrefix); 
      if (this.token.namePrePrefix != null && !this.token.namePrePrefix.equals((this.database.getCatalogName()).name))
        throw Error.error(5505, this.token.namePrefix); 
    } 
    read();
    return schemaObject;
  }
  
  Table readTableName() {
    checkIsIdentifier();
    if (this.token.namePrePrefix != null)
      checkValidCatalogName(this.token.namePrePrefix); 
    Table table = this.database.schemaManager.getTable(this.session, this.token.tokenString, this.token.namePrefix);
    getRecordedToken().setExpression(table);
    read();
    return table;
  }
  
  ColumnSchema readSimpleColumnName(RangeVariable paramRangeVariable, boolean paramBoolean) {
    ColumnSchema columnSchema = null;
    checkIsIdentifier();
    if (!paramBoolean && this.token.namePrefix != null)
      throw tooManyIdentifiers(); 
    int i = paramRangeVariable.findColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
    if (i == -1)
      throw Error.error(5501, this.token.tokenString); 
    columnSchema = paramRangeVariable.getTable().getColumn(i);
    read();
    return columnSchema;
  }
  
  ColumnSchema readSimpleColumnName(Table paramTable, boolean paramBoolean) {
    checkIsIdentifier();
    if (paramBoolean) {
      if (this.token.namePrefix != null && !(paramTable.getName()).name.equals(this.token.namePrefix))
        throw Error.error(5501, this.token.namePrefix); 
    } else if (this.token.namePrefix != null) {
      throw tooManyIdentifiers();
    } 
    int i = paramTable.findColumn(this.token.tokenString);
    if (i == -1)
      throw Error.error(5501, this.token.tokenString); 
    ColumnSchema columnSchema = paramTable.getColumn(i);
    read();
    return columnSchema;
  }
  
  StatementQuery compileDeclareCursor(RangeGroup[] paramArrayOfRangeGroup, boolean paramBoolean) {
    byte b = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    int i = getPosition();
    readThis(77);
    HsqlNameManager.SimpleName simpleName = readSimpleName();
    switch (this.token.tokenType) {
      case 252:
        read();
        b = 2;
        break;
      case 134:
        read();
        b = 1;
        break;
      case 11:
        read();
        break;
    } 
    if (this.token.tokenType == 180) {
      readThis(248);
    } else if (this.token.tokenType == 248) {
      read();
      bool1 = true;
    } 
    if (this.token.tokenType != 70) {
      rewind(i);
      return null;
    } 
    readThis(70);
    int j;
    for (j = 0; j < 2; j++) {
      if (this.token.tokenType == 319) {
        read();
        if (j == 0 && this.token.tokenType == 126) {
          read();
          bool2 = true;
        } else {
          readThis(237);
          j++;
          bool3 = true;
        } 
      } else if (this.token.tokenType == 321) {
        read();
        if (j == 0 && this.token.tokenType == 126) {
          read();
        } else {
          readThis(237);
          j++;
        } 
      } 
    } 
    readThis(112);
    j = ResultProperties.getProperties(b, 1, bool1, bool2, bool3);
    StatementQuery statementQuery = compileCursorSpecification(paramArrayOfRangeGroup, j, paramBoolean);
    statementQuery.setCursorName(simpleName);
    return statementQuery;
  }
  
  StatementQuery compileCursorSpecification(RangeGroup[] paramArrayOfRangeGroup, int paramInt, boolean paramBoolean) {
    OrderedHashSet orderedHashSet = null;
    QueryExpression queryExpression = XreadQueryExpression();
    if (this.token.tokenType == 112) {
      read();
      if (this.token.tokenType == 480 || this.token.tokenType == 107) {
        read();
        readThis(195);
        paramInt = ResultProperties.addUpdatable(paramInt, false);
      } else {
        readThis(303);
        paramInt = ResultProperties.addUpdatable(paramInt, true);
        if (this.token.tokenType == 191) {
          readThis(191);
          orderedHashSet = new OrderedHashSet();
          readColumnNameList(orderedHashSet, (BitMap)null, false);
        } 
      } 
    } 
    if (ResultProperties.isUpdatable(paramInt))
      queryExpression.isUpdatable = true; 
    queryExpression.setReturningResult();
    queryExpression.resolve(this.session, paramArrayOfRangeGroup, null);
    return paramBoolean ? new StatementCursor(this.session, queryExpression, this.compileContext) : new StatementQuery(this.session, queryExpression, this.compileContext);
  }
  
  StatementDMQL compileShortCursorSpecification(int paramInt) {
    QuerySpecification querySpecification = XreadSimpleTable();
    if (ResultProperties.isUpdatable(paramInt))
      querySpecification.isUpdatable = true; 
    querySpecification.setReturningResult();
    querySpecification.resolve(this.session);
    return new StatementQuery(this.session, querySpecification, this.compileContext);
  }
  
  int readCloseBrackets(int paramInt) {
    byte b;
    for (b = 0; b < paramInt && this.token.tokenType == 802; b++)
      read(); 
    return b;
  }
  
  int readOpenBrackets() {
    byte b = 0;
    while (this.token.tokenType == 816) {
      b++;
      read();
    } 
    return b;
  }
  
  void checkValidCatalogName(String paramString) {
    if (paramString != null && !paramString.equals((this.database.getCatalogName()).name))
      throw Error.error(5501, paramString); 
  }
  
  void rewind(int paramInt) {
    super.rewind(paramInt);
    this.compileContext.rewind(paramInt);
  }
  
  public static final class CompileContext {
    final Session session;
    
    final ParserBase parser;
    
    final CompileContext baseContext;
    
    private int subqueryDepth;
    
    private HsqlArrayList namedSubqueries;
    
    private OrderedIntKeyHashMap parameters = new OrderedIntKeyHashMap();
    
    private HsqlArrayList usedSequences = new HsqlArrayList(8, true);
    
    private HsqlArrayList usedRoutines = new HsqlArrayList(8, true);
    
    private HsqlArrayList rangeVariables = new HsqlArrayList(8, true);
    
    private HsqlArrayList usedObjects = new HsqlArrayList(8, true);
    
    Type currentDomain;
    
    boolean contextuallyTypedExpression;
    
    Routine callProcedure;
    
    private RangeGroup[] outerRangeGroups;
    
    private int rangeVarIndex = 0;
    
    public CompileContext(Session param1Session) {
      this(param1Session, null, null);
    }
    
    public CompileContext(Session param1Session, ParserBase param1ParserBase, CompileContext param1CompileContext) {
      this.session = param1Session;
      this.parser = param1ParserBase;
      this.baseContext = param1CompileContext;
      reset();
    }
    
    public void reset() {
      if (this.baseContext == null) {
        this.rangeVarIndex = 1;
        this.subqueryDepth = 0;
      } else {
        this.rangeVarIndex = this.baseContext.getRangeVarCount();
        this.subqueryDepth = this.baseContext.getDepth();
      } 
      this.rangeVariables.clear();
      this.parameters.clear();
      this.usedSequences.clear();
      this.usedRoutines.clear();
      this.callProcedure = null;
      this.usedObjects.clear();
      this.outerRangeGroups = RangeGroup.emptyArray;
      this.currentDomain = null;
      this.contextuallyTypedExpression = false;
    }
    
    public int getDepth() {
      return this.subqueryDepth;
    }
    
    public void incrementDepth() {
      this.subqueryDepth++;
      if (this.baseContext != null)
        this.baseContext.subqueryDepth++; 
    }
    
    public void decrementDepth() {
      this.subqueryDepth--;
      if (this.baseContext != null)
        this.baseContext.subqueryDepth--; 
    }
    
    public void rewind(int param1Int) {
      for (int i = this.rangeVariables.size() - 1; i >= 0; i--) {
        RangeVariable rangeVariable = (RangeVariable)this.rangeVariables.get(i);
        if (rangeVariable.parsePosition > param1Int)
          this.rangeVariables.remove(i); 
      } 
      Iterator iterator = this.parameters.keySet().iterator();
      while (iterator.hasNext()) {
        int j = iterator.nextInt();
        if (j >= param1Int)
          iterator.remove(); 
      } 
    }
    
    public void registerRangeVariable(RangeVariable param1RangeVariable) {
      param1RangeVariable.parsePosition = (this.parser == null) ? 0 : this.parser.getPosition();
      param1RangeVariable.rangePosition = getNextRangeVarIndex();
      param1RangeVariable.level = this.subqueryDepth;
      this.rangeVariables.add(param1RangeVariable);
    }
    
    public void setNextRangeVarIndex(int param1Int) {
      this.rangeVarIndex = param1Int;
    }
    
    public int getNextRangeVarIndex() {
      if (this.baseContext != null) {
        int i = this.baseContext.getNextRangeVarIndex();
        this.rangeVarIndex = i + 1;
        return i;
      } 
      return this.rangeVarIndex++;
    }
    
    public int getRangeVarCount() {
      return this.rangeVarIndex;
    }
    
    public RangeVariable[] getAllRangeVariables() {
      RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.size()];
      this.rangeVariables.toArray(arrayOfRangeVariable);
      return arrayOfRangeVariable;
    }
    
    public RangeGroup[] getOuterRanges() {
      return this.outerRangeGroups;
    }
    
    public void setOuterRanges(RangeGroup[] param1ArrayOfRangeGroup) {
      this.outerRangeGroups = param1ArrayOfRangeGroup;
    }
    
    public NumberSequence[] getSequences() {
      if (this.usedSequences.size() == 0)
        return NumberSequence.emptyArray; 
      NumberSequence[] arrayOfNumberSequence = new NumberSequence[this.usedSequences.size()];
      this.usedSequences.toArray(arrayOfNumberSequence);
      return arrayOfNumberSequence;
    }
    
    public Routine[] getRoutines() {
      if (this.callProcedure == null && this.usedRoutines.size() == 0)
        return Routine.emptyArray; 
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      for (byte b = 0; b < this.usedRoutines.size(); b++) {
        FunctionSQLInvoked functionSQLInvoked = (FunctionSQLInvoked)this.usedRoutines.get(b);
        orderedHashSet.add(functionSQLInvoked.routine);
      } 
      if (this.callProcedure != null)
        orderedHashSet.add(this.callProcedure); 
      Routine[] arrayOfRoutine = new Routine[orderedHashSet.size()];
      orderedHashSet.toArray((Object[])arrayOfRoutine);
      return arrayOfRoutine;
    }
    
    private void initSubqueryNames() {
      if (this.namedSubqueries == null)
        this.namedSubqueries = new HsqlArrayList(); 
      if (this.namedSubqueries.size() <= this.subqueryDepth)
        this.namedSubqueries.setSize(this.subqueryDepth + 1); 
      HashMappedList hashMappedList = (HashMappedList)this.namedSubqueries.get(this.subqueryDepth);
      if (hashMappedList == null) {
        hashMappedList = new HashMappedList();
        this.namedSubqueries.set(this.subqueryDepth, hashMappedList);
      } else {
        hashMappedList.clear();
      } 
    }
    
    private void registerSubquery(String param1String, TableDerived param1TableDerived) {
      HashMappedList hashMappedList = (HashMappedList)this.namedSubqueries.get(this.subqueryDepth);
      boolean bool = hashMappedList.add(param1String, param1TableDerived);
      if (!bool)
        throw Error.error(5504); 
    }
    
    private void unregisterSubqueries() {
      if (this.namedSubqueries == null)
        return; 
      for (int i = this.subqueryDepth; i < this.namedSubqueries.size(); i++)
        this.namedSubqueries.set(i, null); 
    }
    
    private TableDerived getNamedSubQuery(String param1String) {
      if (this.namedSubqueries == null)
        return null; 
      for (int i = this.subqueryDepth; i >= 0; i--) {
        if (this.namedSubqueries.size() > i) {
          HashMappedList hashMappedList = (HashMappedList)this.namedSubqueries.get(i);
          if (hashMappedList != null) {
            TableDerived tableDerived = (TableDerived)hashMappedList.get(param1String);
            if (tableDerived != null)
              return tableDerived; 
          } 
        } 
      } 
      return null;
    }
    
    private void addParameter(ExpressionColumn param1ExpressionColumn, int param1Int) {
      param1ExpressionColumn.parameterIndex = this.parameters.size();
      this.parameters.put(param1Int, param1ExpressionColumn);
    }
    
    private void addSchemaObject(SchemaObject param1SchemaObject) {
      this.usedObjects.add(param1SchemaObject);
    }
    
    private void addSequence(SchemaObject param1SchemaObject) {
      this.usedSequences.add(param1SchemaObject);
    }
    
    void addFunctionCall(FunctionSQLInvoked param1FunctionSQLInvoked) {
      this.usedRoutines.add(param1FunctionSQLInvoked);
    }
    
    void addProcedureCall(Routine param1Routine) {
      this.callProcedure = param1Routine;
    }
    
    ExpressionColumn[] getParameters() {
      if (this.parameters.size() == 0)
        return ExpressionColumn.emptyArray; 
      ExpressionColumn[] arrayOfExpressionColumn = new ExpressionColumn[this.parameters.size()];
      this.parameters.valuesToArray((Object[])arrayOfExpressionColumn);
      this.parameters.clear();
      return arrayOfExpressionColumn;
    }
    
    void clearParameters() {
      this.parameters.clear();
    }
    
    public OrderedHashSet getSchemaObjectNames() {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      byte b1;
      for (b1 = 0; b1 < this.usedSequences.size(); b1++) {
        SchemaObject schemaObject = (SchemaObject)this.usedSequences.get(b1);
        orderedHashSet.add(schemaObject.getName());
      } 
      for (b1 = 0; b1 < this.usedObjects.size(); b1++) {
        SchemaObject schemaObject = (SchemaObject)this.usedObjects.get(b1);
        orderedHashSet.add(schemaObject.getName());
      } 
      for (b1 = 0; b1 < this.rangeVariables.size(); b1++) {
        RangeVariable rangeVariable = (RangeVariable)this.rangeVariables.get(b1);
        HsqlNameManager.HsqlName hsqlName = rangeVariable.rangeTable.getName();
        if (hsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
          orderedHashSet.add(rangeVariable.rangeTable.getName());
          orderedHashSet.addAll((Collection)rangeVariable.getColumnNames());
        } else if (hsqlName.type == 10) {
          orderedHashSet.addAll((Collection)rangeVariable.getColumnNames());
        } 
      } 
      Routine[] arrayOfRoutine = getRoutines();
      for (byte b2 = 0; b2 < arrayOfRoutine.length; b2++)
        orderedHashSet.add(arrayOfRoutine[b2].getSpecificName()); 
      return orderedHashSet;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserDQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */