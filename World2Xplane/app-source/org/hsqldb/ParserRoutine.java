package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.Type;

public class ParserRoutine extends ParserDML {
  ParserRoutine(Session paramSession, Scanner paramScanner) {
    super(paramSession, paramScanner);
  }
  
  Expression readDefaultClause(Type paramType) {
    Expression expression = null;
    boolean bool1 = false;
    if (this.token.tokenType == 186) {
      read();
      return new ExpressionValue(null, paramType);
    } 
    if (paramType.isDateTimeType() || paramType.isIntervalType()) {
      Object object;
      switch (this.token.tokenType) {
        case 72:
        case 140:
        case 281:
        case 282:
          expression = readDateTimeIntervalLiteral(this.session);
          if (expression.dataType.typeCode != paramType.typeCode)
            throw unexpectedToken(); 
          object = expression.getValue(this.session, paramType);
          return new ExpressionValue(object, paramType);
        case 869:
          break;
        default:
          expression = XreadDateTimeValueFunctionOrNull();
          if (expression == null)
            break; 
          expression = XreadModifier(expression);
          break;
      } 
    } else if (paramType.isNumberType()) {
      if (this.token.tokenType == 814) {
        read();
        bool1 = true;
      } else if (this.database.sqlSyntaxPgs && this.token.tokenType == 611) {
        return readNextvalFunction();
      } 
    } else if (paramType.isCharacterType()) {
      FunctionSQL functionSQL;
      switch (this.token.tokenType) {
        case 60:
        case 63:
        case 64:
        case 65:
        case 69:
        case 253:
        case 277:
        case 305:
          functionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
          expression = readSQLFunction(functionSQL);
          break;
      } 
    } else if (paramType.isBooleanType()) {
      switch (this.token.tokenType) {
        case 294:
          read();
          return Expression.EXPR_TRUE;
        case 106:
          read();
          return Expression.EXPR_FALSE;
      } 
    } else if (paramType.isBitType()) {
      switch (this.token.tokenType) {
        case 294:
          read();
          return new ExpressionValue(BinaryData.singleBitOne, paramType);
        case 106:
          read();
          return new ExpressionValue(BinaryData.singleBitZero, paramType);
      } 
    } else if (paramType.isArrayType()) {
      expression = readCollection(19);
      if (expression.nodes.length > 0)
        throw Error.parseError(5562, null, this.scanner.getLineNumber()); 
      expression.dataType = paramType;
      return expression;
    } 
    if (expression != null) {
      expression.resolveTypes(this.session, null);
      if (paramType.typeComparisonGroup != (expression.getDataType()).typeComparisonGroup)
        throw Error.parseError(5562, null, this.scanner.getLineNumber()); 
      return expression;
    } 
    boolean bool2 = false;
    if (this.database.sqlSyntaxMss && this.token.tokenType == 816) {
      read();
      bool2 = true;
    } 
    if (this.token.tokenType == 869) {
      Object object = this.token.tokenValue;
      Type type1 = this.token.dataType;
      Type type2 = paramType;
      if (paramType.typeCode == 40) {
        type2 = Type.getType(12, null, this.database.collation, paramType.precision, 0);
      } else if (paramType.typeCode == 30) {
        type2 = Type.getType(61, null, null, paramType.precision, 0);
      } 
      object = type2.convertToType(this.session, object, type1);
      read();
      if (bool1)
        object = paramType.negate(object); 
      if (bool2)
        readThis(802); 
      return new ExpressionValue(object, type2);
    } 
    if (this.database.sqlSyntaxDb2) {
      BinaryData binaryData2;
      Integer integer;
      Boolean bool;
      String str1;
      BinaryData binaryData1;
      FunctionSQL functionSQL;
      String str2 = null;
      switch (paramType.typeComparisonGroup) {
        case 12:
          str2 = "";
          break;
        case 61:
          binaryData2 = BinaryData.zeroLengthBinary;
          break;
        case 2:
          integer = Integer.valueOf(0);
          break;
        case 16:
          bool = Boolean.FALSE;
          break;
        case 40:
          str1 = "";
          return new ExpressionValue(str1, (Type)Type.SQL_VARCHAR_DEFAULT);
        case 30:
          binaryData1 = BinaryData.zeroLengthBinary;
          return new ExpressionValue(binaryData1, (Type)Type.SQL_VARBINARY_DEFAULT);
        case 92:
          functionSQL = FunctionSQL.newSQLFunction("CURRENT_TIME", this.compileContext);
          functionSQL.resolveTypes(this.session, null);
          return functionSQL;
        case 91:
          functionSQL = FunctionSQL.newSQLFunction("CURRENT_DATE", this.compileContext);
          functionSQL.resolveTypes(this.session, null);
          return functionSQL;
        case 93:
          functionSQL = FunctionSQL.newSQLFunction("CURRENT_TIMESTAMP", this.compileContext);
          functionSQL.resolveTypes(this.session, null);
          return functionSQL;
      } 
      Object object = paramType.convertToDefaultType(this.session, binaryData1);
      return new ExpressionValue(object, paramType);
    } 
    throw unexpectedToken();
  }
  
  Statement compileOpenCursorStatement(StatementCompound paramStatementCompound) {
    readThis(196);
    checkIsSimpleName();
    String str = this.token.tokenString;
    read();
    for (byte b = 0; b < paramStatementCompound.cursors.length; b++) {
      if ((paramStatementCompound.cursors[b].getCursorName()).name.equals(str))
        return paramStatementCompound.cursors[b]; 
    } 
    throw Error.parseError(4680, null, this.scanner.getLineNumber());
  }
  
  Statement compileSelectSingleRowStatement(RangeGroup[] paramArrayOfRangeGroup) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    LongDeque longDeque = new LongDeque();
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    QuerySpecification querySpecification = XreadSelect();
    readThis(141);
    RangeVariable[] arrayOfRangeVariable = paramArrayOfRangeGroup[0].getRangeVariables();
    readTargetSpecificationList(orderedHashSet, arrayOfRangeVariable, longDeque);
    XreadTableExpression(querySpecification);
    querySpecification.setReturningResult();
    int[] arrayOfInt = new int[longDeque.size()];
    longDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfExpression);
    Type[] arrayOfType = new Type[arrayOfExpression.length];
    for (byte b = 0; b < arrayOfExpression.length; b++) {
      if (arrayOfExpression[b].getColumn().getParameterMode() == 1)
        throw Error.parseError(2500, null, this.scanner.getLineNumber()); 
      arrayOfType[b] = arrayOfExpression[b].getDataType();
    } 
    querySpecification.setReturningResult();
    querySpecification.resolve(this.session, paramArrayOfRangeGroup, arrayOfType);
    if (querySpecification.getColumnCount() != arrayOfExpression.length)
      throw Error.error(5564, "INTO"); 
    return new StatementSet(this.session, arrayOfExpression, querySpecification, arrayOfInt, this.compileContext);
  }
  
  Statement compileGetStatement(RangeGroup[] paramArrayOfRangeGroup) {
    read();
    readThis(391);
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    LongDeque longDeque = new LongDeque();
    RangeVariable[] arrayOfRangeVariable = paramArrayOfRangeGroup[0].getRangeVariables();
    readGetClauseList(arrayOfRangeVariable, orderedHashSet, longDeque, hsqlArrayList);
    if (hsqlArrayList.size() > 1)
      throw Error.parseError(5602, null, this.scanner.getLineNumber()); 
    Expression expression = (Expression)hsqlArrayList.get(0);
    if (expression.getDegree() != orderedHashSet.size())
      throw Error.error(5546, "SET"); 
    int[] arrayOfInt = new int[longDeque.size()];
    longDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfExpression);
    byte b;
    for (b = 0; b < arrayOfExpression.length; b++)
      resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression[b]); 
    resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, expression);
    for (b = 0; b < arrayOfExpression.length; b++) {
      if (arrayOfExpression[b].getColumn().getParameterMode() == 1)
        throw Error.parseError(2500, null, this.scanner.getLineNumber()); 
      if (!arrayOfExpression[b].getDataType().canBeAssignedFrom(expression.getNodeDataType(b)))
        throw Error.parseError(5561, null, this.scanner.getLineNumber()); 
    } 
    return new StatementSet(this.session, arrayOfExpression, expression, arrayOfInt, this.compileContext);
  }
  
  StatementSet compileSetStatement(RangeGroup[] paramArrayOfRangeGroup, RangeVariable[] paramArrayOfRangeVariable) {
    read();
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    LongDeque longDeque = new LongDeque();
    readSetClauseList(paramArrayOfRangeVariable, orderedHashSet, longDeque, hsqlArrayList);
    if (hsqlArrayList.size() > 1)
      throw Error.parseError(5602, null, this.scanner.getLineNumber()); 
    Expression expression = (Expression)hsqlArrayList.get(0);
    if (expression.getDegree() != orderedHashSet.size())
      throw Error.error(5546, "SET"); 
    int[] arrayOfInt = new int[longDeque.size()];
    longDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfExpression);
    byte b;
    for (b = 0; b < arrayOfExpression.length; b++)
      resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression[b]); 
    resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, expression);
    for (b = 0; b < arrayOfExpression.length; b++) {
      ColumnSchema columnSchema = arrayOfExpression[b].getColumn();
      if (columnSchema.getParameterMode() == 1)
        throw Error.error(2500, (columnSchema.getName()).statementName); 
      if (!arrayOfExpression[b].getDataType().canBeAssignedFrom(expression.getNodeDataType(b)))
        throw Error.parseError(5561, null, this.scanner.getLineNumber()); 
    } 
    return new StatementSet(this.session, arrayOfExpression, expression, arrayOfInt, this.compileContext);
  }
  
  StatementDMQL compileTriggerSetStatement(Table paramTable, RangeGroup[] paramArrayOfRangeGroup) {
    read();
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    RangeVariable[] arrayOfRangeVariable = { paramArrayOfRangeGroup[0].getRangeVariables()[1] };
    LongDeque longDeque = new LongDeque();
    readSetClauseList(arrayOfRangeVariable, orderedHashSet, longDeque, hsqlArrayList);
    int[] arrayOfInt = new int[longDeque.size()];
    longDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression2 = new Expression[orderedHashSet.size()];
    orderedHashSet.toArray((Object[])arrayOfExpression2);
    for (byte b = 0; b < arrayOfExpression2.length; b++)
      resolveOuterReferencesAndTypes(RangeGroup.emptyArray, arrayOfExpression2[b]); 
    Expression[] arrayOfExpression1 = new Expression[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfExpression1);
    resolveUpdateExpressions(paramTable, RangeGroup.emptyGroup, arrayOfInt, arrayOfExpression1, paramArrayOfRangeGroup);
    return new StatementSet(this.session, arrayOfExpression2, paramTable, paramArrayOfRangeGroup[0].getRangeVariables(), arrayOfInt, arrayOfExpression1, this.compileContext);
  }
  
  StatementSchema compileAlterSpecificRoutine() {
    boolean bool = false;
    readThis(259);
    readThis(491);
    Routine routine = (Routine)readSchemaObjectName(24);
    routine = routine.duplicate();
    readRoutineCharacteristics(routine);
    bool = readIfThis(485);
    if (bool) {
      OrderedHashSet orderedHashSet = this.database.schemaManager.getReferencesTo(routine.getSpecificName());
      if (!orderedHashSet.isEmpty())
        throw Error.parseError(5502, null, this.scanner.getLineNumber()); 
    } 
    if (this.token.tokenType == 567) {
      read();
    } else if (this.token.tokenType == 445) {
      read();
    } 
    readRoutineBody(routine);
    routine.resetAlteredRoutineSettings();
    routine.resolve(this.session);
    Object[] arrayOfObject = { routine };
    String str = getLastPart();
    return new StatementSchema(str, 17, arrayOfObject, null, this.database.schemaManager.getCatalogNameArray());
  }
  
  StatementSchema compileCreateProcedureOrFunction(boolean paramBoolean) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: getfield token : Lorg/hsqldb/Token;
    //   6: getfield tokenType : I
    //   9: sipush #559
    //   12: if_icmpne -> 39
    //   15: iconst_1
    //   16: istore_3
    //   17: aload_0
    //   18: invokevirtual read : ()V
    //   21: aload_0
    //   22: getfield token : Lorg/hsqldb/Token;
    //   25: getfield tokenType : I
    //   28: sipush #215
    //   31: if_icmpne -> 39
    //   34: aload_0
    //   35: invokespecial unexpectedToken : ()Lorg/hsqldb/HsqlException;
    //   38: athrow
    //   39: aload_0
    //   40: getfield token : Lorg/hsqldb/Token;
    //   43: getfield tokenType : I
    //   46: sipush #215
    //   49: if_icmpne -> 57
    //   52: bipush #17
    //   54: goto -> 59
    //   57: bipush #16
    //   59: istore_2
    //   60: aload_0
    //   61: invokevirtual read : ()V
    //   64: aload_0
    //   65: iload_2
    //   66: iconst_1
    //   67: invokevirtual readNewSchemaObjectName : (IZ)Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   70: astore #4
    //   72: aload #4
    //   74: aload_0
    //   75: getfield session : Lorg/hsqldb/Session;
    //   78: invokevirtual getCurrentSchemaHsqlName : ()Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   81: invokevirtual setSchemaIfNull : (Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   84: new org/hsqldb/Routine
    //   87: dup
    //   88: iload_2
    //   89: invokespecial <init> : (I)V
    //   92: astore #5
    //   94: aload #5
    //   96: aload #4
    //   98: invokevirtual setName : (Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   101: aload #5
    //   103: iload_3
    //   104: invokevirtual setAggregate : (Z)V
    //   107: aload_0
    //   108: sipush #816
    //   111: invokevirtual readThis : (I)V
    //   114: aload_0
    //   115: getfield token : Lorg/hsqldb/Token;
    //   118: getfield tokenType : I
    //   121: sipush #802
    //   124: if_icmpne -> 134
    //   127: aload_0
    //   128: invokevirtual read : ()V
    //   131: goto -> 183
    //   134: aload_0
    //   135: aload #5
    //   137: iconst_1
    //   138: invokespecial readRoutineParameter : (Lorg/hsqldb/Routine;Z)Lorg/hsqldb/ColumnSchema;
    //   141: astore #6
    //   143: aload #5
    //   145: aload #6
    //   147: invokevirtual addParameter : (Lorg/hsqldb/ColumnSchema;)V
    //   150: aload_0
    //   151: getfield token : Lorg/hsqldb/Token;
    //   154: getfield tokenType : I
    //   157: sipush #804
    //   160: if_icmpne -> 170
    //   163: aload_0
    //   164: invokevirtual read : ()V
    //   167: goto -> 180
    //   170: aload_0
    //   171: sipush #802
    //   174: invokevirtual readThis : (I)V
    //   177: goto -> 183
    //   180: goto -> 134
    //   183: iload_2
    //   184: bipush #17
    //   186: if_icmpeq -> 264
    //   189: aload_0
    //   190: sipush #238
    //   193: invokevirtual readThis : (I)V
    //   196: aload_0
    //   197: getfield token : Lorg/hsqldb/Token;
    //   200: getfield tokenType : I
    //   203: sipush #278
    //   206: if_icmpne -> 249
    //   209: aload_0
    //   210: invokevirtual read : ()V
    //   213: new org/hsqldb/TableDerived
    //   216: dup
    //   217: aload_0
    //   218: getfield database : Lorg/hsqldb/Database;
    //   221: getstatic org/hsqldb/SqlInvariants.MODULE_HSQLNAME : Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   224: bipush #11
    //   226: invokespecial <init> : (Lorg/hsqldb/Database;Lorg/hsqldb/HsqlNameManager$HsqlName;I)V
    //   229: astore #6
    //   231: aload_0
    //   232: aload #5
    //   234: aload #6
    //   236: invokespecial readTableDefinition : (Lorg/hsqldb/Routine;Lorg/hsqldb/Table;)V
    //   239: aload #5
    //   241: aload #6
    //   243: invokevirtual setReturnTable : (Lorg/hsqldb/TableDerived;)V
    //   246: goto -> 264
    //   249: aload_0
    //   250: iconst_0
    //   251: iconst_1
    //   252: invokevirtual readTypeDefinition : (ZZ)Lorg/hsqldb/types/Type;
    //   255: astore #6
    //   257: aload #5
    //   259: aload #6
    //   261: invokevirtual setReturnType : (Lorg/hsqldb/types/Type;)V
    //   264: aload_0
    //   265: aload #5
    //   267: invokespecial readRoutineCharacteristics : (Lorg/hsqldb/Routine;)V
    //   270: aload_0
    //   271: aload #5
    //   273: invokevirtual readRoutineBody : (Lorg/hsqldb/Routine;)V
    //   276: iconst_1
    //   277: anewarray java/lang/Object
    //   280: dup
    //   281: iconst_0
    //   282: aload #5
    //   284: aastore
    //   285: astore #6
    //   287: aload_0
    //   288: invokevirtual getLastPart : ()Ljava/lang/String;
    //   291: astore #7
    //   293: new org/hsqldb/StatementSchema
    //   296: dup
    //   297: aload #7
    //   299: bipush #14
    //   301: aload #6
    //   303: aconst_null
    //   304: aload_0
    //   305: getfield database : Lorg/hsqldb/Database;
    //   308: getfield schemaManager : Lorg/hsqldb/SchemaManager;
    //   311: invokevirtual getCatalogNameArray : ()[Lorg/hsqldb/HsqlNameManager$HsqlName;
    //   314: invokespecial <init> : (Ljava/lang/String;I[Ljava/lang/Object;[Lorg/hsqldb/HsqlNameManager$HsqlName;[Lorg/hsqldb/HsqlNameManager$HsqlName;)V
    //   317: astore #8
    //   319: aload #8
    //   321: areturn
  }
  
  Routine readCreatePasswordCheckFunction() {
    Routine routine = new Routine(16);
    if (this.token.tokenType == 181) {
      read();
      return null;
    } 
    if (this.token.tokenType == 104) {
      routine.setLanguage(1);
      routine.setDataImpact(1);
    } else {
      routine.setLanguage(2);
      routine.setDataImpact(2);
    } 
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName("PASSWORD", false, 16);
    hsqlName.setSchemaIfNull(SqlInvariants.SYSTEM_SCHEMA_HSQLNAME);
    routine.setName(hsqlName);
    hsqlName = this.database.nameManager.newHsqlName("PASSWORD", false, 23);
    ColumnSchema columnSchema = new ColumnSchema(hsqlName, (Type)Type.SQL_VARCHAR, false, false, null);
    routine.addParameter(columnSchema);
    routine.setReturnType((Type)Type.SQL_BOOLEAN);
    readRoutineBody(routine);
    routine.resolve(this.session);
    return routine;
  }
  
  Routine readCreateDatabaseAuthenticationFunction() {
    Routine routine = new Routine(16);
    if (this.token.tokenType == 181) {
      read();
      return null;
    } 
    checkIsThis(104);
    routine.setLanguage(1);
    routine.setDataImpact(1);
    routine.setName(this.database.nameManager.newHsqlName("AUTHENTICATION", false, 16));
    for (byte b = 0; b < 3; b++) {
      ColumnSchema columnSchema = new ColumnSchema(null, (Type)Type.SQL_VARCHAR, false, false, null);
      routine.addParameter(columnSchema);
    } 
    routine.setReturnType((Type)new ArrayType((Type)Type.SQL_VARCHAR_DEFAULT, 1024));
    readRoutineBody(routine);
    routine.resolve(this.session);
    return routine;
  }
  
  private void readTableDefinition(Routine paramRoutine, Table paramTable) throws HsqlException {
    readThis(816);
    for (byte b = 0;; b++) {
      ColumnSchema columnSchema = readRoutineParameter(paramRoutine, false);
      if (columnSchema.getName() == null)
        throw unexpectedToken(); 
      paramTable.addColumn(columnSchema);
      if (this.token.tokenType == 804) {
        read();
      } else {
        readThis(802);
        paramTable.createPrimaryKey();
        return;
      } 
    } 
  }
  
  private void readRoutineCharacteristics(Routine paramRoutine) {
    OrderedIntHashSet orderedIntHashSet = new OrderedIntHashSet();
    for (boolean bool = false; !bool; bool = true) {
      HsqlNameManager.HsqlName hsqlName;
      int i;
      switch (this.token.tokenType) {
        case 146:
          if (!orderedIntHashSet.add(146))
            throw unexpectedToken(); 
          read();
          if (this.token.tokenType == 425) {
            read();
            paramRoutine.setLanguage(1);
            continue;
          } 
          if (this.token.tokenType == 261) {
            read();
            paramRoutine.setLanguage(2);
            continue;
          } 
          throw unexpectedToken();
        case 204:
          if (!orderedIntHashSet.add(204))
            throw unexpectedToken(); 
          read();
          readThis(519);
          if (this.token.tokenType == 425) {
            read();
            paramRoutine.setParameterStyle(1);
            continue;
          } 
          readThis(261);
          paramRoutine.setParameterStyle(2);
          continue;
        case 259:
          if (!orderedIntHashSet.add(259))
            throw unexpectedToken(); 
          read();
          hsqlName = readNewSchemaObjectName(24, false);
          paramRoutine.setSpecificName(hsqlName);
          continue;
        case 83:
          if (!orderedIntHashSet.add(83))
            throw unexpectedToken(); 
          read();
          paramRoutine.setDeterministic(true);
          continue;
        case 183:
          if (!orderedIntHashSet.add(83))
            throw unexpectedToken(); 
          read();
          readThis(83);
          paramRoutine.setDeterministic(false);
          continue;
        case 171:
          if (!orderedIntHashSet.add(261))
            throw unexpectedToken(); 
          if (paramRoutine.getType() == 16)
            throw unexpectedToken(); 
          read();
          readThis(261);
          readThis(378);
          paramRoutine.setDataImpact(4);
          continue;
        case 180:
          if (!orderedIntHashSet.add(261))
            throw unexpectedToken(); 
          read();
          readThis(261);
          paramRoutine.setDataImpact(1);
          continue;
        case 218:
          if (!orderedIntHashSet.add(261))
            throw unexpectedToken(); 
          read();
          readThis(261);
          readThis(378);
          paramRoutine.setDataImpact(3);
          continue;
        case 375:
          if (!orderedIntHashSet.add(261))
            throw unexpectedToken(); 
          read();
          readThis(261);
          paramRoutine.setDataImpact(2);
          continue;
        case 238:
          if (!orderedIntHashSet.add(186) || paramRoutine.isProcedure())
            throw unexpectedToken(); 
          if (paramRoutine.isAggregate())
            throw Error.error(5604, this.token.tokenString); 
          read();
          readThis(186);
          readThis(194);
          readThis(186);
          readThis(419);
          paramRoutine.setNullInputOutput(true);
          continue;
        case 26:
          if (!orderedIntHashSet.add(186) || paramRoutine.isProcedure())
            throw unexpectedToken(); 
          read();
          readThis(194);
          readThis(186);
          readThis(419);
          paramRoutine.setNullInputOutput(false);
          continue;
        case 89:
          if (!orderedIntHashSet.add(236) || paramRoutine.isFunction())
            throw unexpectedToken(); 
          read();
          readThis(236);
          readThis(510);
          i = readInteger();
          if (i < 0 || i > 16)
            throw Error.error(5604, String.valueOf(i)); 
          paramRoutine.setMaxDynamicResults(i);
          continue;
        case 179:
          if (paramRoutine.getType() == 16 || !orderedIntHashSet.add(246))
            throw unexpectedToken(); 
          read();
          readThis(246);
          readThis(432);
          paramRoutine.setNewSavepointLevel(true);
          continue;
        case 193:
          if (paramRoutine.getType() == 16 || !orderedIntHashSet.add(246))
            throw unexpectedToken(); 
          read();
          readThis(246);
          readThis(432);
          paramRoutine.setNewSavepointLevel(false);
          throw unsupportedFeature("OLD");
      } 
    } 
  }
  
  void readRoutineBody(Routine paramRoutine) {
    if (this.token.tokenType == 104) {
      if (paramRoutine.getLanguage() != 1)
        throw unexpectedToken(); 
      read();
      readThis(445);
      checkIsValue(1);
      paramRoutine.setMethodURL((String)this.token.tokenValue);
      read();
      if (this.token.tokenType == 204) {
        read();
        readThis(519);
        readThis(425);
      } 
    } else {
      startRecording();
      Statement statement = compileSQLProcedureStatementOrNull(paramRoutine, (StatementCompound)null);
      if (statement == null)
        throw unexpectedToken(); 
      Token[] arrayOfToken = getRecordedStatement();
      String str = Token.getSQL(arrayOfToken);
      statement.setSQL(str);
      paramRoutine.setProcedure(statement);
    } 
  }
  
  private Object[] readLocalDeclarationList(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    byte b = 0;
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null) ? paramRoutine : paramStatementCompound;
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    while (this.token.tokenType == 77) {
      Table table = null;
      if (!b) {
        table = readLocalTableVariableDeclarationOrNull(paramRoutine);
        if (table == null) {
          b = 1;
          continue;
        } 
        hsqlArrayList.add(table);
        readThis(821);
        continue;
      } 
      if (b == 1) {
        ColumnSchema[] arrayOfColumnSchema = readLocalVariableDeclarationOrNull();
        if (arrayOfColumnSchema == null) {
          b = 2;
          continue;
        } 
        hsqlArrayList.addAll((Object[])arrayOfColumnSchema);
        continue;
      } 
      if (b == 2) {
        StatementQuery statementQuery = compileDeclareCursor(arrayOfRangeGroup, true);
        if (statementQuery == null) {
          b = 3;
          continue;
        } 
        hsqlArrayList.add(statementQuery);
        readThis(821);
        continue;
      } 
      if (b == 3) {
        StatementHandler statementHandler = compileLocalHandlerDeclaration(paramRoutine, paramStatementCompound);
        hsqlArrayList.add(statementHandler);
      } 
    } 
    Object[] arrayOfObject = new Object[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfObject);
    return arrayOfObject;
  }
  
  Table readLocalTableVariableDeclarationOrNull(Routine paramRoutine) {
    int i = getPosition();
    readThis(77);
    if (this.token.tokenType == 278) {
      read();
      HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(3, false);
      hsqlName.schema = SqlInvariants.MODULE_HSQLNAME;
      Table table = new Table(this.database, hsqlName, 3);
      table.persistenceScope = 20;
      readTableDefinition(paramRoutine, table);
      return table;
    } 
    rewind(i);
    return null;
  }
  
  ColumnSchema[] readLocalVariableDeclarationOrNull() {
    int i = getPosition();
    HsqlNameManager.HsqlName[] arrayOfHsqlName = HsqlNameManager.HsqlName.emptyArray;
    try {
      readThis(77);
      if (isReservedKey()) {
        rewind(i);
        return null;
      } 
      while (true) {
        arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.resizeArray(arrayOfHsqlName, arrayOfHsqlName.length + 1);
        arrayOfHsqlName[arrayOfHsqlName.length - 1] = readNewSchemaObjectName(22, false);
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        Type type = readTypeDefinition(false, true);
        Expression expression = null;
        if (this.token.tokenType == 78) {
          read();
          expression = readDefaultClause(type);
        } 
        ColumnSchema[] arrayOfColumnSchema = new ColumnSchema[arrayOfHsqlName.length];
        for (byte b = 0; b < arrayOfHsqlName.length; b++) {
          arrayOfColumnSchema[b] = new ColumnSchema(arrayOfHsqlName[b], type, true, false, expression);
          arrayOfColumnSchema[b].setParameterMode((byte)2);
        } 
        readThis(821);
        return arrayOfColumnSchema;
      } 
    } catch (HsqlException hsqlException) {
      rewind(i);
      return null;
    } 
  }
  
  private StatementHandler compileLocalHandlerDeclaration(Routine paramRoutine, StatementCompound paramStatementCompound) {
    byte b;
    readThis(77);
    switch (this.token.tokenType) {
      case 376:
        read();
        b = 5;
        break;
      case 102:
        read();
        b = 6;
        break;
      case 297:
        read();
        b = 7;
        break;
      default:
        throw unexpectedToken();
    } 
    readThis(124);
    readThis(112);
    StatementHandler statementHandler = new StatementHandler(b);
    boolean bool1 = false;
    boolean bool2 = true;
    while (!bool1) {
      byte b1 = 0;
      switch (this.token.tokenType) {
        case 804:
          if (bool2)
            throw unexpectedToken(); 
          read();
          bool2 = true;
          continue;
        case 263:
          b1 = 4;
        case 262:
          if (b1 == 0)
            b1 = 1; 
        case 264:
          if (b1 == 0)
            b1 = 2; 
        case 183:
          if (b1 == 0)
            b1 = 3; 
          if (!bool2)
            throw unexpectedToken(); 
          bool2 = false;
          read();
          if (b1 == 3) {
            readThis(404);
          } else if (b1 == 4) {
            String str = parseSQLStateValue();
            statementHandler.addConditionState(str);
            continue;
          } 
          statementHandler.addConditionType(b1);
          continue;
      } 
      if (bool2)
        throw unexpectedToken(); 
      bool1 = true;
    } 
    if (this.token.tokenType == 821) {
      read();
    } else {
      Statement statement = compileSQLProcedureStatementOrNull(paramRoutine, paramStatementCompound);
      if (statement == null)
        throw unexpectedToken(); 
      readThis(821);
      statementHandler.addStatement(statement);
    } 
    return statementHandler;
  }
  
  String parseSQLStateValue() {
    readIfThis(307);
    checkIsValue(1);
    String str = this.token.tokenString;
    if (this.token.tokenString.length() != 5)
      throw Error.parseError(5607, null, this.scanner.getLineNumber()); 
    read();
    return str;
  }
  
  private Statement compileCompoundStatement(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    readThis(17);
    readThis(14);
    if (paramHsqlName == null) {
      StatementCompound statementCompound1 = paramStatementCompound;
      byte b = 0;
      while (statementCompound1 != null) {
        b++;
        statementCompound1 = statementCompound1.parent;
      } 
      String str = "_" + b;
      paramHsqlName = this.session.database.nameManager.newHsqlName(str, false, 21);
    } 
    StatementCompound statementCompound = new StatementCompound(12, paramHsqlName);
    statementCompound.setAtomic(true);
    statementCompound.setRoot(paramRoutine);
    statementCompound.setParent(paramStatementCompound);
    Object[] arrayOfObject = readLocalDeclarationList(paramRoutine, paramStatementCompound);
    statementCompound.setLocalDeclarations(arrayOfObject);
    this.session.sessionContext.pushRoutineTables(statementCompound.scopeTables);
    try {
      Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, statementCompound);
      statementCompound.setStatements(arrayOfStatement);
    } finally {
      this.session.sessionContext.popRoutineTables();
    } 
    readThis(94);
    if (isSimpleName() && !isReservedKey()) {
      if (paramHsqlName == null)
        throw unexpectedToken(); 
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString); 
      read();
    } 
    return statementCompound;
  }
  
  private Statement[] compileSQLProcedureStatementList(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    while (true) {
      Statement statement = compileSQLProcedureStatementOrNull(paramRoutine, paramStatementCompound);
      if (statement == null) {
        if (hsqlArrayList.size() == 0)
          throw unexpectedToken(); 
        Statement[] arrayOfStatement = new Statement[hsqlArrayList.size()];
        hsqlArrayList.toArray(arrayOfStatement);
        return arrayOfStatement;
      } 
      readThis(821);
      hsqlArrayList.add(statement);
    } 
  }
  
  Statement compileSQLProcedureStatementOrNull(Routine paramRoutine, StatementCompound paramStatementCompound) {
    Statement statement = null;
    HsqlNameManager.HsqlName hsqlName1 = null;
    RangeGroup rangeGroup = (RangeGroup)((paramStatementCompound == null) ? paramRoutine : paramStatementCompound);
    RangeGroup[] arrayOfRangeGroup = { rangeGroup };
    if (!paramRoutine.isTrigger() && isSimpleName() && !isReservedKey()) {
      hsqlName1 = readNewSchemaObjectName(21, false);
      if (this.token.tokenType != 803)
        throw unexpectedToken(hsqlName1.getNameString()); 
      readThis(803);
    } 
    this.compileContext.reset();
    HsqlNameManager.HsqlName hsqlName2 = this.session.getCurrentSchemaHsqlName();
    this.session.setCurrentSchemaHsqlName(paramRoutine.getSchemaName());
    try {
      Statement statement2;
      Routine routine;
      switch (this.token.tokenType) {
        case 196:
          if (paramRoutine.dataImpact == 2)
            throw Error.error(5602, paramRoutine.getDataImpactString()); 
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileOpenCursorStatement(paramStatementCompound);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 251:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileSelectSingleRowStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 135:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileInsertStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 303:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileUpdateStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 79:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileDeleteStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 295:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileTruncateStatement();
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 166:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileMergeStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 254:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          if (paramRoutine.isTrigger()) {
            if (paramRoutine.triggerType == 4 && paramRoutine.triggerOperation != 19) {
              int i = getPosition();
              try {
                statement = compileTriggerSetStatement(paramRoutine.triggerTable, arrayOfRangeGroup);
              } catch (HsqlException hsqlException) {
                rewind(i);
                statement = compileSetStatement(arrayOfRangeGroup, rangeGroup.getRangeVariables());
                ((StatementSet)statement).checkIsNotColumnTarget();
              } 
            } else {
              statement = compileSetStatement(arrayOfRangeGroup, rangeGroup.getRangeVariables());
              ((StatementSet)statement).checkIsNotColumnTarget();
            } 
          } else {
            statement = compileSetStatement(arrayOfRangeGroup, rangeGroup.getRangeVariables());
          } 
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 119:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileGetStatement(arrayOfRangeGroup);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement2 = statement;
          return statement2;
        case 25:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileCallStatement(arrayOfRangeGroup, true);
          routine = ((StatementProcedure)statement).procedure;
          if (routine != null)
            switch (paramRoutine.dataImpact) {
              case 2:
                if (routine.dataImpact == 3 || routine.dataImpact == 4)
                  throw Error.error(5602, paramRoutine.getDataImpactString()); 
                break;
              case 3:
                if (routine.dataImpact == 4)
                  throw Error.error(5602, paramRoutine.getDataImpactString()); 
                break;
            }  
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 237:
          if (paramRoutine.isTrigger() || hsqlName1 != null)
            throw unexpectedToken(); 
          read();
          statement = compileReturnValue(paramRoutine, paramStatementCompound);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 17:
          statement = compileCompoundStatement(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 322:
          if (paramRoutine.isTrigger())
            throw unexpectedToken(); 
          statement = compileWhile(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 234:
          statement = compileRepeat(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 160:
          statement = compileLoop(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 112:
          statement = compileFor(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 143:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileIterate();
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 152:
          if (hsqlName1 != null)
            throw unexpectedToken(); 
          statement = compileLeave(paramRoutine, paramStatementCompound);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 412:
          statement = compileIf(paramRoutine, paramStatementCompound);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 29:
          statement = compileCase(paramRoutine, paramStatementCompound);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 255:
          statement = compileSignal(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
        case 235:
          statement = compileResignal(paramRoutine, paramStatementCompound, hsqlName1);
          statement.setRoot(paramRoutine);
          statement.setParent(paramStatementCompound);
          statement1 = statement;
          return statement1;
      } 
      Statement statement1 = null;
      return statement1;
    } finally {
      this.session.setCurrentSchemaHsqlName(hsqlName2);
    } 
  }
  
  private Statement compileReturnValue(Routine paramRoutine, StatementCompound paramStatementCompound) {
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null) ? paramRoutine : paramStatementCompound;
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    Expression expression = XreadValueExpressionOrNull();
    if (expression == null) {
      checkIsValue();
      if (this.token.tokenValue == null)
        expression = new ExpressionValue(null, null); 
    } 
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
    if (paramRoutine.isProcedure())
      throw Error.parseError(5602, null, this.scanner.getLineNumber()); 
    return new StatementExpression(this.session, this.compileContext, 58, expression);
  }
  
  private Statement compileIterate() {
    readThis(143);
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(21, false);
    return new StatementSimple(102, hsqlName);
  }
  
  private Statement compileLeave(Routine paramRoutine, StatementCompound paramStatementCompound) {
    readThis(152);
    HsqlNameManager.HsqlName hsqlName = readNewSchemaObjectName(21, false);
    return new StatementSimple(89, hsqlName);
  }
  
  private Statement compileWhile(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    readThis(322);
    Expression expression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
    StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
    readThis(86);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(94);
    readThis(322);
    if (isSimpleName() && !isReservedKey()) {
      if (paramHsqlName == null)
        throw unexpectedToken(); 
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString); 
      read();
    } 
    StatementCompound statementCompound = new StatementCompound(97, paramHsqlName);
    statementCompound.setStatements(arrayOfStatement);
    statementCompound.setCondition(statementExpression);
    return statementCompound;
  }
  
  private Statement compileRepeat(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    readThis(234);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(302);
    Expression expression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
    StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
    readThis(94);
    readThis(234);
    if (isSimpleName() && !isReservedKey()) {
      if (paramHsqlName == null)
        throw unexpectedToken(); 
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString); 
      read();
    } 
    StatementCompound statementCompound = new StatementCompound(95, paramHsqlName);
    statementCompound.setStatements(arrayOfStatement);
    statementCompound.setCondition(statementExpression);
    return statementCompound;
  }
  
  private Statement compileLoop(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    readThis(160);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(94);
    readThis(160);
    if (isSimpleName() && !isReservedKey()) {
      if (paramHsqlName == null)
        throw unexpectedToken(); 
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString); 
      read();
    } 
    StatementCompound statementCompound = new StatementCompound(90, paramHsqlName);
    statementCompound.setStatements(arrayOfStatement);
    return statementCompound;
  }
  
  private Statement compileFor(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null) ? paramRoutine : paramStatementCompound;
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    readThis(112);
    StatementQuery statementQuery = compileCursorSpecification(arrayOfRangeGroup, 0, false);
    readThis(86);
    StatementCompound statementCompound = new StatementCompound(46, paramHsqlName);
    statementCompound.setAtomic(true);
    statementCompound.setRoot(paramRoutine);
    statementCompound.setParent(paramStatementCompound);
    statementCompound.setLoopStatement(statementQuery);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, statementCompound);
    readThis(94);
    readThis(112);
    if (isSimpleName() && !isReservedKey()) {
      if (paramHsqlName == null)
        throw unexpectedToken(); 
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString); 
      read();
    } 
    statementCompound.setStatements(arrayOfStatement);
    return statementCompound;
  }
  
  private Statement compileIf(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    readThis(412);
    Expression expression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
    StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
    hsqlArrayList.add(statementExpression);
    readThis(280);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    byte b;
    for (b = 0; b < arrayOfStatement.length; b++)
      hsqlArrayList.add(arrayOfStatement[b]); 
    while (this.token.tokenType == 93) {
      read();
      expression = XreadBooleanValueExpression();
      resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
      statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
      hsqlArrayList.add(statementExpression);
      readThis(280);
      arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (b = 0; b < arrayOfStatement.length; b++)
        hsqlArrayList.add(arrayOfStatement[b]); 
    } 
    if (this.token.tokenType == 92) {
      read();
      expression = Expression.EXPR_TRUE;
      statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
      hsqlArrayList.add(statementExpression);
      arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (b = 0; b < arrayOfStatement.length; b++)
        hsqlArrayList.add(arrayOfStatement[b]); 
    } 
    readThis(94);
    readThis(412);
    arrayOfStatement = new Statement[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfStatement);
    StatementCompound statementCompound = new StatementCompound(88, null);
    statementCompound.setStatements(arrayOfStatement);
    return statementCompound;
  }
  
  private Statement compileCase(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Expression expression = null;
    readThis(29);
    if (this.token.tokenType == 314) {
      hsqlArrayList = readCaseWhen(paramRoutine, paramStatementCompound);
    } else {
      hsqlArrayList = readSimpleCaseWhen(paramRoutine, paramStatementCompound);
    } 
    if (this.token.tokenType == 92) {
      read();
      expression = Expression.EXPR_TRUE;
      StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
      hsqlArrayList.add(statementExpression);
      Statement[] arrayOfStatement1 = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (byte b = 0; b < arrayOfStatement1.length; b++)
        hsqlArrayList.add(arrayOfStatement1[b]); 
    } 
    readThis(94);
    readThis(29);
    Statement[] arrayOfStatement = new Statement[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfStatement);
    StatementCompound statementCompound = new StatementCompound(88, null);
    statementCompound.setStatements(arrayOfStatement);
    return statementCompound;
  }
  
  private HsqlArrayList readSimpleCaseWhen(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Expression expression1 = null;
    Expression expression2 = XreadRowValuePredicand();
    while (true) {
      readThis(314);
      while (true) {
        Expression expression = XreadPredicateRightPart(expression2);
        if (expression2 == expression)
          expression = new ExpressionLogical(expression2, XreadRowValuePredicand()); 
        resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
        if (expression1 == null) {
          expression1 = expression;
        } else {
          expression1 = new ExpressionLogical(50, expression1, expression);
        } 
        if (this.token.tokenType == 804) {
          read();
          continue;
        } 
        StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression1);
        hsqlArrayList.add(statementExpression);
        readThis(280);
        Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
        for (byte b = 0; b < arrayOfStatement.length; b++)
          hsqlArrayList.add(arrayOfStatement[b]); 
        break;
      } 
      if (this.token.tokenType != 314)
        return hsqlArrayList; 
    } 
  }
  
  private HsqlArrayList readCaseWhen(Routine paramRoutine, StatementCompound paramStatementCompound) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Expression expression = null;
    while (true) {
      readThis(314);
      expression = XreadBooleanValueExpression();
      resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
      StatementExpression statementExpression = new StatementExpression(this.session, this.compileContext, 1211, expression);
      hsqlArrayList.add(statementExpression);
      readThis(280);
      Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (byte b = 0; b < arrayOfStatement.length; b++)
        hsqlArrayList.add(arrayOfStatement[b]); 
      if (this.token.tokenType != 314)
        return hsqlArrayList; 
    } 
  }
  
  private Statement compileSignal(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    Expression expression = null;
    readThis(255);
    readThis(263);
    String str = parseSQLStateValue();
    if (readIfThis(254)) {
      readThis(441);
      readThis(396);
      expression = XreadSimpleValueSpecificationOrNull();
      if (expression == null)
        throw unexpectedToken(); 
      resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
    } 
    return new StatementSimple(92, str, expression);
  }
  
  private Statement compileResignal(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName) {
    String str = null;
    Expression expression = null;
    readThis(235);
    if (readIfThis(263)) {
      str = parseSQLStateValue();
      if (readIfThis(254)) {
        readThis(441);
        readThis(396);
        expression = XreadSimpleValueSpecificationOrNull();
        if (expression == null)
          throw unexpectedToken(); 
        resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, expression);
      } 
    } 
    return new StatementSimple(91, str, expression);
  }
  
  private ColumnSchema readRoutineParameter(Routine paramRoutine, boolean paramBoolean) {
    HsqlNameManager.HsqlName hsqlName = null;
    byte b = 1;
    if (paramBoolean)
      switch (this.token.tokenType) {
        case 130:
          read();
          break;
        case 199:
          if (paramRoutine.getType() != 17)
            throw unexpectedToken(); 
          read();
          b = 4;
          break;
        case 133:
          if (paramRoutine.getType() != 17 && !paramRoutine.isAggregate())
            throw unexpectedToken(); 
          read();
          b = 2;
          break;
      }  
    if (!isReservedKey())
      hsqlName = readNewDependentSchemaObjectName(paramRoutine.getName(), 23); 
    Type type = readTypeDefinition(false, true);
    ColumnSchema columnSchema = new ColumnSchema(hsqlName, type, true, false, null);
    if (paramBoolean)
      columnSchema.setParameterMode(b); 
    return columnSchema;
  }
  
  void resolveOuterReferencesAndTypes(Routine paramRoutine, StatementCompound paramStatementCompound, Expression paramExpression) {
    RangeGroup rangeGroup = (RangeGroup)((paramStatementCompound == null) ? paramRoutine : paramStatementCompound);
    resolveOuterReferencesAndTypes(new RangeGroup[] { rangeGroup }, paramExpression);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserRoutine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */