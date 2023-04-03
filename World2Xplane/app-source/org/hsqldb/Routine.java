package org.hsqldb;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCResultSet;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.BitMap;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;
import org.hsqldb.types.Types;

public class Routine implements SchemaObject, RangeGroup, Cloneable {
  public static final int NO_SQL = 1;
  
  public static final int CONTAINS_SQL = 2;
  
  public static final int READS_SQL = 3;
  
  public static final int MODIFIES_SQL = 4;
  
  public static final int LANGUAGE_JAVA = 1;
  
  public static final int LANGUAGE_SQL = 2;
  
  public static final int PARAM_STYLE_JAVA = 1;
  
  public static final int PARAM_STYLE_SQL = 2;
  
  static final Routine[] emptyArray = new Routine[0];
  
  RoutineSchema routineSchema;
  
  private HsqlNameManager.HsqlName name;
  
  private HsqlNameManager.HsqlName specificName;
  
  Type[] parameterTypes;
  
  int typeGroups;
  
  Type returnType;
  
  Type[] tableType;
  
  Table returnTable;
  
  final int routineType;
  
  int language = 2;
  
  int dataImpact = 2;
  
  int parameterStyle;
  
  boolean isDeterministic;
  
  boolean isNullInputOutput;
  
  boolean isNewSavepointLevel = true;
  
  int maxDynamicResults = 0;
  
  boolean isRecursive;
  
  boolean returnsTable;
  
  Statement statement;
  
  boolean isAggregate;
  
  boolean isIndex;
  
  boolean isSearch;
  
  private String methodName;
  
  Method javaMethod;
  
  boolean javaMethodWithConnection;
  
  private boolean isLibraryRoutine;
  
  HashMappedList parameterList = new HashMappedList();
  
  RangeVariable[] ranges = RangeVariable.emptyArray;
  
  int variableCount;
  
  OrderedHashSet references;
  
  Table triggerTable;
  
  int triggerType;
  
  int triggerOperation;
  
  public Routine(int paramInt) {
    this.routineType = paramInt;
    this.returnType = Type.SQL_ALL_TYPES;
    this.ranges = new RangeVariable[] { new RangeVariable(this.parameterList, null, false, 3) };
  }
  
  public Routine(Table paramTable, RangeVariable[] paramArrayOfRangeVariable, int paramInt1, int paramInt2, int paramInt3) {
    this.routineType = 8;
    this.returnType = Type.SQL_ALL_TYPES;
    this.dataImpact = paramInt1;
    this.ranges = paramArrayOfRangeVariable;
    this.triggerTable = paramTable;
    this.triggerType = paramInt2;
    this.triggerOperation = paramInt3;
  }
  
  public int getType() {
    return this.routineType;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return (this.routineType == 8) ? this.triggerTable.getSchemaName() : this.name.schema;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return this.references;
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {
    ParserRoutine parserRoutine = new ParserRoutine(paramSession, new Scanner(this.statement.getSQL()));
    parserRoutine.read();
    parserRoutine.startRecording();
    Statement statement = parserRoutine.compileSQLProcedureStatementOrNull(this, (StatementCompound)null);
    Token[] arrayOfToken = parserRoutine.getRecordedStatement();
    String str = Token.getSQL(arrayOfToken);
    statement.setSQL(str);
    setProcedure(statement);
    statement.resolve(paramSession);
    setReferences();
  }
  
  public String getSQL() {
    return getDefinitionSQL(true);
  }
  
  public String getSQLAlter() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ALTER").append(' ').append("SPECIFIC");
    stringBuffer.append(' ').append("ROUTINE").append(' ');
    stringBuffer.append(this.specificName.getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("BODY");
    stringBuffer.append(' ').append(this.statement.getSQL());
    return stringBuffer.toString();
  }
  
  public String getSQLDeclaration() {
    return getDefinitionSQL(false);
  }
  
  private String getDefinitionSQL(boolean paramBoolean) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ');
    if (this.isAggregate)
      stringBuffer.append("AGGREGATE").append(' '); 
    if (this.routineType == 17) {
      stringBuffer.append("PROCEDURE");
    } else {
      stringBuffer.append("FUNCTION");
    } 
    stringBuffer.append(' ');
    stringBuffer.append(this.name.getSchemaQualifiedStatementName());
    stringBuffer.append('(');
    for (byte b = 0; b < this.parameterList.size(); b++) {
      if (b > 0)
        stringBuffer.append(','); 
      ColumnSchema columnSchema = (ColumnSchema)this.parameterList.get(b);
      stringBuffer.append(columnSchema.getSQL());
    } 
    stringBuffer.append(')');
    stringBuffer.append(' ');
    if (this.routineType == 16) {
      stringBuffer.append("RETURNS");
      stringBuffer.append(' ');
      if (this.returnsTable) {
        stringBuffer.append("TABLE");
        stringBuffer.append(this.returnTable.getColumnListWithTypeSQL());
      } else {
        stringBuffer.append(this.returnType.getTypeDefinition());
      } 
      stringBuffer.append(' ');
    } 
    if (this.specificName != null) {
      stringBuffer.append("SPECIFIC");
      stringBuffer.append(' ');
      stringBuffer.append(this.specificName.getStatementName());
      stringBuffer.append(' ');
    } 
    stringBuffer.append("LANGUAGE");
    stringBuffer.append(' ');
    if (this.language == 1) {
      stringBuffer.append("JAVA");
    } else {
      stringBuffer.append("SQL");
    } 
    stringBuffer.append(' ');
    if (!this.isDeterministic) {
      stringBuffer.append("NOT");
      stringBuffer.append(' ');
    } 
    stringBuffer.append("DETERMINISTIC");
    stringBuffer.append(' ');
    stringBuffer.append(getDataImpactString());
    stringBuffer.append(' ');
    if (this.routineType == 16) {
      if (this.isNullInputOutput) {
        stringBuffer.append("RETURNS").append(' ').append("NULL");
      } else {
        stringBuffer.append("CALLED");
      } 
      stringBuffer.append(' ').append("ON").append(' ');
      stringBuffer.append("NULL").append(' ').append("INPUT");
      stringBuffer.append(' ');
    } else {
      if (this.isNewSavepointLevel) {
        stringBuffer.append("NEW");
      } else {
        stringBuffer.append("OLD");
      } 
      stringBuffer.append(' ').append("SAVEPOINT").append(' ');
      stringBuffer.append("LEVEL").append(' ');
      if (this.maxDynamicResults != 0) {
        stringBuffer.append(' ').append("DYNAMIC").append(' ');
        stringBuffer.append("RESULT").append(' ').append("SETS");
        stringBuffer.append(' ').append(this.maxDynamicResults).append(' ');
      } 
    } 
    if (this.language == 1) {
      stringBuffer.append("EXTERNAL").append(' ').append("NAME");
      stringBuffer.append(' ').append('\'').append(this.methodName).append('\'');
    } else if (paramBoolean) {
      stringBuffer.append(this.statement.getSQL());
    } else {
      stringBuffer.append("SIGNAL").append(' ');
      stringBuffer.append("SQLSTATE").append(' ');
      stringBuffer.append('\'').append("45000").append('\'');
    } 
    return stringBuffer.toString();
  }
  
  public String getSQLBodyDefinition() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.language == 1) {
      stringBuffer.append("EXTERNAL").append(' ').append("NAME");
      stringBuffer.append(' ').append('\'').append(this.methodName).append('\'');
    } else {
      stringBuffer.append(this.statement.getSQL());
    } 
    return stringBuffer.toString();
  }
  
  public String getExternalName() {
    return (this.language == 1) ? this.methodName : null;
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public void addParameter(ColumnSchema paramColumnSchema) {
    HsqlNameManager.HsqlName hsqlName = paramColumnSchema.getName();
    String str = (hsqlName == null) ? HsqlNameManager.getAutoNoNameColumnString(this.parameterList.size()) : hsqlName.name;
    this.parameterList.add(str, paramColumnSchema);
  }
  
  public void setLanguage(int paramInt) {
    this.language = paramInt;
  }
  
  public int getLanguage() {
    return this.language;
  }
  
  boolean isPSM() {
    return (this.language == 2);
  }
  
  public void setDataImpact(int paramInt) {
    this.dataImpact = paramInt;
  }
  
  public int getDataImpact() {
    return this.dataImpact;
  }
  
  public String getDataImpactString() {
    StringBuffer stringBuffer = new StringBuffer();
    switch (this.dataImpact) {
      case 1:
        stringBuffer.append("NO").append(' ').append("SQL");
        break;
      case 2:
        stringBuffer.append("CONTAINS").append(' ').append("SQL");
        break;
      case 3:
        stringBuffer.append("READS").append(' ').append("SQL").append(' ').append("DATA");
        break;
      case 4:
        stringBuffer.append("MODIFIES").append(' ').append("SQL").append(' ').append("DATA");
        break;
    } 
    return stringBuffer.toString();
  }
  
  public void setReturnType(Type paramType) {
    this.returnType = paramType;
  }
  
  public Type getReturnType() {
    return this.returnType;
  }
  
  public void setTableType(Type[] paramArrayOfType) {
    this.tableType = paramArrayOfType;
  }
  
  public Type[] getTableType() {
    return this.tableType;
  }
  
  public Table getTable() {
    return this.returnTable;
  }
  
  public void setProcedure(Statement paramStatement) {
    this.statement = paramStatement;
  }
  
  public Statement getProcedure() {
    return this.statement;
  }
  
  public void setSpecificName(HsqlNameManager.HsqlName paramHsqlName) {
    this.specificName = paramHsqlName;
  }
  
  public int getMaxDynamicResults() {
    return this.maxDynamicResults;
  }
  
  public void setName(HsqlNameManager.HsqlName paramHsqlName) {
    this.name = paramHsqlName;
  }
  
  public HsqlNameManager.HsqlName getSpecificName() {
    return this.specificName;
  }
  
  public void setDeterministic(boolean paramBoolean) {
    this.isDeterministic = paramBoolean;
  }
  
  public boolean isDeterministic() {
    return this.isDeterministic;
  }
  
  public void setNullInputOutput(boolean paramBoolean) {
    this.isNullInputOutput = paramBoolean;
  }
  
  public boolean isNullInputOutput() {
    return this.isNullInputOutput;
  }
  
  public void setNewSavepointLevel(boolean paramBoolean) {
    this.isNewSavepointLevel = paramBoolean;
  }
  
  public void setMaxDynamicResults(int paramInt) {
    this.maxDynamicResults = paramInt;
  }
  
  public void setParameterStyle(int paramInt) {
    this.parameterStyle = paramInt;
  }
  
  public void setMethodURL(String paramString) {
    this.methodName = paramString;
  }
  
  public Method getMethod() {
    return this.javaMethod;
  }
  
  public void setMethod(Method paramMethod) {
    this.javaMethod = paramMethod;
  }
  
  public void setReturnTable(TableDerived paramTableDerived) {
    this.returnTable = paramTableDerived;
    this.returnsTable = true;
    HsqlNameManager.SimpleName[] arrayOfSimpleName = new HsqlNameManager.SimpleName[paramTableDerived.getColumnCount()];
    Type[] arrayOfType = paramTableDerived.getColumnTypes();
    this.returnType = (Type)new RowType(arrayOfType);
  }
  
  public boolean returnsTable() {
    return this.returnsTable;
  }
  
  public void setAggregate(boolean paramBoolean) {
    this.isAggregate = paramBoolean;
  }
  
  public boolean isAggregate() {
    return this.isAggregate;
  }
  
  public void resolve(Session paramSession) {
    setLanguage(this.language);
    if (this.language == 2) {
      if (this.dataImpact == 1)
        throw Error.error(5604, "CONTAINS SQL"); 
      if (this.parameterStyle == 1)
        throw Error.error(5604, "PARAMETER STYLE"); 
    } 
    if (this.language == 2 && this.parameterStyle != 0 && this.parameterStyle != 2)
      throw Error.error(5604, "PARAMETER STYLE"); 
    this.parameterTypes = new Type[this.parameterList.size()];
    this.typeGroups = 0;
    int i;
    for (i = 0; i < this.parameterTypes.length; i++) {
      ColumnSchema columnSchema = (ColumnSchema)this.parameterList.get(i);
      this.parameterTypes[i] = columnSchema.dataType;
      if (i < 4)
        BitMap.setByte(this.typeGroups, (byte)columnSchema.dataType.typeComparisonGroup, i * 8); 
    } 
    if (this.isAggregate) {
      if (this.parameterTypes.length != 4)
        throw Error.error(5610); 
      i = ((this.parameterTypes[1]).typeCode == 16) ? 1 : 0;
      ColumnSchema columnSchema = (ColumnSchema)this.parameterList.get(0);
      i &= (columnSchema.getParameterMode() == 1) ? 1 : 0;
      columnSchema = (ColumnSchema)this.parameterList.get(1);
      i &= (columnSchema.getParameterMode() == 1) ? 1 : 0;
      columnSchema = (ColumnSchema)this.parameterList.get(2);
      i &= (columnSchema.getParameterMode() == 2) ? 1 : 0;
      columnSchema = (ColumnSchema)this.parameterList.get(3);
      i &= (columnSchema.getParameterMode() == 2) ? 1 : 0;
      if (i == 0)
        throw Error.error(5610); 
    } 
    resolveReferences(paramSession);
  }
  
  void resolveReferences(Session paramSession) {
    if (this.statement != null) {
      this.statement.resolve(paramSession);
      checkSQLData(paramSession);
    } 
    if (this.methodName != null && this.javaMethod == null) {
      boolean[] arrayOfBoolean = new boolean[1];
      this.javaMethod = getMethod(this.methodName, this, arrayOfBoolean, this.returnsTable);
      if (this.javaMethod == null)
        throw Error.error(6013); 
      this.javaMethodWithConnection = arrayOfBoolean[0];
      String str = this.javaMethod.getDeclaringClass().getName();
      if (str.equals("java.lang.Math"))
        this.isLibraryRoutine = true; 
    } 
    setReferences();
  }
  
  private void setReferences() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < this.parameterTypes.length; b++) {
      ColumnSchema columnSchema = (ColumnSchema)this.parameterList.get(b);
      OrderedHashSet orderedHashSet1 = columnSchema.getReferences();
      if (orderedHashSet1 != null)
        orderedHashSet.addAll((Collection)orderedHashSet1); 
    } 
    if (this.statement != null)
      orderedHashSet.addAll((Collection)this.statement.getReferences()); 
    this.isRecursive = false;
    if (orderedHashSet.contains(getSpecificName())) {
      orderedHashSet.remove(getSpecificName());
      this.isRecursive = true;
    } 
    this.references = orderedHashSet;
  }
  
  void checkSQLData(Session paramSession) {
    OrderedHashSet orderedHashSet = this.statement.getReferences();
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      if (hsqlName.type == 24) {
        Routine routine = (Routine)paramSession.database.schemaManager.getSchemaObject(hsqlName);
        if (routine.dataImpact == 3) {
          if (this.dataImpact == 2)
            throw Error.error(5608, "READS SQL"); 
        } else if (routine.dataImpact == 4 && (this.dataImpact == 2 || this.dataImpact == 3)) {
          throw Error.error(5608, "MODIFIES SQL");
        } 
      } 
    } 
    if (this.dataImpact == 2 || this.dataImpact == 3) {
      HsqlNameManager.HsqlName[] arrayOfHsqlName = this.statement.getTableNamesForWrite();
      for (byte b1 = 0; b1 < arrayOfHsqlName.length; b1++) {
        if ((arrayOfHsqlName[b1]).schema != SqlInvariants.MODULE_HSQLNAME)
          throw Error.error(5608, "MODIFIES SQL"); 
      } 
    } 
    if (this.dataImpact == 2) {
      HsqlNameManager.HsqlName[] arrayOfHsqlName = this.statement.getTableNamesForRead();
      for (byte b1 = 0; b1 < arrayOfHsqlName.length; b1++) {
        if ((arrayOfHsqlName[b1]).schema != SqlInvariants.MODULE_HSQLNAME)
          throw Error.error(5608, "READS SQL"); 
      } 
    } 
  }
  
  public boolean isTrigger() {
    return (this.routineType == 8);
  }
  
  public boolean isProcedure() {
    return (this.routineType == 17);
  }
  
  public boolean isFunction() {
    return (this.routineType == 16);
  }
  
  public ColumnSchema getParameter(int paramInt) {
    return (ColumnSchema)this.parameterList.get(paramInt);
  }
  
  Type[] getParameterTypes() {
    return this.parameterTypes;
  }
  
  int getParameterSignature() {
    return this.typeGroups;
  }
  
  public int getParameterCount() {
    return this.parameterTypes.length;
  }
  
  public int getParameterCount(int paramInt) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < this.parameterList.size(); b2++) {
      ColumnSchema columnSchema = (ColumnSchema)this.parameterList.get(b2);
      if (columnSchema.getParameterMode() == paramInt)
        b1++; 
    } 
    return b1;
  }
  
  public int getParameterIndex(String paramString) {
    return this.parameterList.getIndex(paramString);
  }
  
  public RangeVariable[] getRangeVariables() {
    return this.ranges;
  }
  
  public void setCorrelated() {}
  
  public boolean isVariable() {
    return true;
  }
  
  public int getVariableCount() {
    return this.variableCount;
  }
  
  public boolean isLibraryRoutine() {
    return this.isLibraryRoutine;
  }
  
  public HsqlNameManager.HsqlName[] getTableNamesForRead() {
    return (this.statement == null) ? HsqlNameManager.HsqlName.emptyArray : this.statement.getTableNamesForRead();
  }
  
  public HsqlNameManager.HsqlName[] getTableNamesForWrite() {
    return (this.statement == null) ? HsqlNameManager.HsqlName.emptyArray : this.statement.getTableNamesForWrite();
  }
  
  public void resetAlteredRoutineSettings() {
    if (isPSM()) {
      this.methodName = null;
      this.javaMethod = null;
      this.javaMethodWithConnection = false;
      this.parameterStyle = 2;
      if (this.dataImpact == 1)
        this.dataImpact = 2; 
    } else {
      this.statement = null;
      this.references = null;
      this.variableCount = 0;
      this.ranges = RangeVariable.emptyArray;
    } 
  }
  
  public void setAsAlteredRoutine(Routine paramRoutine) {
    this.language = paramRoutine.language;
    this.dataImpact = paramRoutine.dataImpact;
    this.parameterStyle = paramRoutine.parameterStyle;
    this.isDeterministic = paramRoutine.isDeterministic;
    this.isNullInputOutput = paramRoutine.isNullInputOutput;
    this.maxDynamicResults = paramRoutine.maxDynamicResults;
    this.isRecursive = paramRoutine.isRecursive;
    this.javaMethod = paramRoutine.javaMethod;
    this.isRecursive = paramRoutine.isRecursive;
    this.javaMethodWithConnection = paramRoutine.javaMethodWithConnection;
    this.methodName = paramRoutine.methodName;
    this.statement = paramRoutine.statement;
    this.references = paramRoutine.references;
    this.variableCount = paramRoutine.variableCount;
    this.ranges = paramRoutine.ranges;
  }
  
  Object[] convertArgsToJava(Session paramSession, Object[] paramArrayOfObject) {
    byte b1 = this.javaMethodWithConnection ? 1 : 0;
    Object[] arrayOfObject = new Object[(this.javaMethod.getParameterTypes()).length];
    Type[] arrayOfType = getParameterTypes();
    byte b2;
    for (b2 = 0; b2 < arrayOfType.length; b2++) {
      Object object = paramArrayOfObject[b2];
      ColumnSchema columnSchema = getParameter(b2);
      if (columnSchema.parameterMode == 1) {
        arrayOfObject[b2 + b1] = arrayOfType[b2].convertSQLToJava(paramSession, object);
      } else {
        Object object1 = arrayOfType[b2].convertSQLToJava(paramSession, object);
        Class<?> clazz = arrayOfType[b2].getJDBCClass();
        Object object2 = Array.newInstance(clazz, 1);
        Array.set(object2, 0, object1);
        arrayOfObject[b2 + b1] = object2;
      } 
    } 
    while (b2 + b1 < arrayOfObject.length) {
      arrayOfObject[b2 + b1] = new ResultSet[1];
      b2++;
    } 
    return arrayOfObject;
  }
  
  void convertArgsToSQL(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    byte b1 = this.javaMethodWithConnection ? 1 : 0;
    Type[] arrayOfType = getParameterTypes();
    byte b2;
    for (b2 = 0; b2 < arrayOfType.length; b2++) {
      Object object = paramArrayOfObject2[b2 + b1];
      ColumnSchema columnSchema = getParameter(b2);
      if (columnSchema.parameterMode != 1)
        object = Array.get(object, 0); 
      paramArrayOfObject1[b2] = arrayOfType[b2].convertJavaToSQL(paramSession, object);
    } 
    Result result = null;
    while (b2 + b1 < paramArrayOfObject2.length) {
      ResultSet resultSet = ((ResultSet[])paramArrayOfObject2[b2 + b1])[0];
      if (resultSet != null)
        if (resultSet instanceof JDBCResultSet) {
          Result result1 = ((JDBCResultSet)resultSet).result;
          if (result == null) {
            paramArrayOfObject1[b2] = result1;
            result = result1;
          } else {
            result.addChainedResult(result1);
          } 
        } else {
          Error.error(6000, "ResultSet not native");
        }  
      b2++;
    } 
  }
  
  public Result invokeJavaMethodDirect(Object[] paramArrayOfObject) {
    Result result;
    try {
      Object object = this.javaMethod.invoke(null, paramArrayOfObject);
      object = this.returnType.convertJavaToSQL(null, object);
      result = Result.newPSMResult(object);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(Error.error(throwable, 6000, (getName()).name), null);
    } 
    return result;
  }
  
  Result invokeJavaMethod(Session paramSession, Object[] paramArrayOfObject) {
    Result result;
    HsqlNameManager.HsqlName hsqlName = paramSession.getCurrentSchemaHsqlName();
    try {
      if (this.dataImpact == 1) {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
        paramSession.setNoSQL();
      } else if (this.dataImpact == 2) {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
      } else if (this.dataImpact == 3) {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
      } 
      paramSession.setCurrentSchemaHsqlName(getSchemaName());
      Object object = this.javaMethod.invoke(null, paramArrayOfObject);
      if (returnsTable()) {
        if (object instanceof JDBCResultSet) {
          result = ((JDBCResultSet)object).result;
        } else {
          throw Error.runtimeError(201, "FunctionSQLInvoked");
        } 
      } else {
        object = this.returnType.convertJavaToSQL(paramSession, object);
        result = Result.newPSMResult(object);
      } 
    } catch (InvocationTargetException invocationTargetException) {
      result = Result.newErrorResult(Error.error(invocationTargetException, 6000, (getName()).name), null);
    } catch (IllegalAccessException illegalAccessException) {
      result = Result.newErrorResult(Error.error(illegalAccessException, 6000, (getName()).name), null);
    } catch (Throwable throwable) {
      result = Result.newErrorResult(Error.error(throwable, 6000, (getName()).name), null);
    } 
    paramSession.setCurrentSchemaHsqlName(hsqlName);
    return result;
  }
  
  public Result invoke(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, boolean paramBoolean) {
    Result result;
    if (paramBoolean)
      paramSession.sessionContext.pushRoutineInvocation(); 
    if (isPSM()) {
      try {
        paramSession.sessionContext.routineArguments = paramArrayOfObject1;
        paramSession.sessionContext.routineVariables = ValuePool.emptyObjectArray;
        if (this.variableCount > 0)
          paramSession.sessionContext.routineVariables = new Object[this.variableCount]; 
        result = this.statement.execute(paramSession);
        if (paramArrayOfObject2 != null)
          for (byte b = 0; b < paramArrayOfObject2.length; b++)
            paramArrayOfObject2[b] = paramArrayOfObject1[b + 1];  
      } catch (Throwable throwable) {
        result = Result.newErrorResult(throwable);
      } 
    } else {
      if (this.isAggregate)
        paramArrayOfObject1 = convertArgsToJava(paramSession, paramArrayOfObject1); 
      result = invokeJavaMethod(paramSession, paramArrayOfObject1);
      if (this.isAggregate) {
        Object[] arrayOfObject = new Object[paramArrayOfObject1.length];
        convertArgsToSQL(paramSession, arrayOfObject, paramArrayOfObject1);
        for (byte b = 0; b < paramArrayOfObject2.length; b++)
          paramArrayOfObject2[b] = arrayOfObject[b + 1]; 
      } 
    } 
    if (paramBoolean)
      paramSession.sessionContext.popRoutineInvocation(); 
    return result;
  }
  
  public Routine duplicate() {
    try {
      return (Routine)clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw Error.runtimeError(201, "Type");
    } 
  }
  
  static Method getMethod(String paramString, Routine paramRoutine, boolean[] paramArrayOfboolean, boolean paramBoolean) {
    int i = paramString.indexOf(':');
    if (i != -1) {
      if (!paramString.substring(0, i).equals("CLASSPATH"))
        throw Error.error(6012, paramString); 
      paramString = paramString.substring(i + 1);
    } 
    Method[] arrayOfMethod = getMethods(paramString);
    int j = -1;
    for (i = 0; i < arrayOfMethod.length; i++) {
      byte b1 = 0;
      paramArrayOfboolean[0] = false;
      Method method = arrayOfMethod[i];
      Class[] arrayOfClass = method.getParameterTypes();
      if (arrayOfClass.length > 0 && arrayOfClass[0].equals(Connection.class)) {
        b1 = 1;
        paramArrayOfboolean[0] = true;
      } 
      int k = arrayOfClass.length - b1;
      if (paramRoutine.isProcedure())
        for (byte b = b1; b < arrayOfClass.length; b++) {
          if (arrayOfClass[b].isArray() && ResultSet.class.isAssignableFrom(arrayOfClass[b].getComponentType())) {
            k = b - b1;
            break;
          } 
        }  
      if (k != paramRoutine.parameterTypes.length)
        continue; 
      if (paramBoolean) {
        if (!ResultSet.class.isAssignableFrom(method.getReturnType()))
          continue; 
      } else {
        Type type = Types.getParameterSQLType(method.getReturnType());
        if (type == null || !paramRoutine.returnType.canBeAssignedFrom(type))
          continue; 
        if (type.isLobType() || (!type.isBinaryType() && !type.isCharacterType())) {
          int m = paramRoutine.returnType.typeCode;
          if (m == 2)
            m = 3; 
          if (type.typeCode != m)
            continue; 
        } 
      } 
      byte b2;
      for (b2 = 0; b2 < paramRoutine.parameterTypes.length; b2++) {
        boolean bool1 = false;
        Class<?> clazz = arrayOfClass[b2 + b1];
        if (clazz.isArray() && !byte[].class.equals(clazz)) {
          clazz = clazz.getComponentType();
          if (clazz.isPrimitive()) {
            method = null;
            break;
          } 
          bool1 = true;
        } 
        Type type = Types.getParameterSQLType(clazz);
        if (type == null) {
          method = null;
          break;
        } 
        boolean bool2 = ((paramRoutine.parameterTypes[b2]).typeComparisonGroup == type.typeComparisonGroup) ? true : false;
        if (bool2 && paramRoutine.parameterTypes[b2].isNumberType()) {
          int m = (paramRoutine.parameterTypes[b2]).typeCode;
          if (m == 2)
            m = 3; 
          bool2 = (m == type.typeCode) ? true : false;
        } 
        if (bool1 && (paramRoutine.getParameter(b2)).parameterMode == 1)
          bool2 = false; 
        if (!bool2) {
          method = null;
          if (b2 + b1 > j)
            j = b2 + b1; 
          break;
        } 
      } 
      if (method != null) {
        for (b2 = 0; b2 < paramRoutine.parameterTypes.length; b2++)
          paramRoutine.getParameter(b2).setNullable(!arrayOfClass[b2 + b1].isPrimitive()); 
        return method;
      } 
      continue;
    } 
    if (j >= 0) {
      ColumnSchema columnSchema = paramRoutine.getParameter(j);
      throw Error.error(6021, columnSchema.getNameString());
    } 
    return null;
  }
  
  static Method[] getMethods(String paramString) {
    Class<?> clazz;
    int i = paramString.lastIndexOf('.');
    if (i == -1)
      throw Error.error(5501, paramString); 
    String str1 = paramString.substring(0, i);
    String str2 = paramString.substring(i + 1);
    Method[] arrayOfMethod = null;
    if (!HsqlDatabaseProperties.supportsJavaMethod(paramString))
      throw Error.error(5501, str1); 
    try {
      clazz = Class.forName(str1, true, Thread.currentThread().getContextClassLoader());
    } catch (Throwable throwable) {
      try {
        clazz = Class.forName(str1);
      } catch (Throwable throwable1) {
        throw Error.error(throwable1, 5501, 26, new Object[] { throwable1.toString(), str1 });
      } 
    } 
    try {
      arrayOfMethod = clazz.getMethods();
    } catch (Throwable throwable) {
      throw Error.error(throwable, 5501, 26, new Object[] { throwable.toString(), str1 });
    } 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    for (i = 0; i < arrayOfMethod.length; i++) {
      byte b = 0;
      int j = Integer.MAX_VALUE;
      Method method = arrayOfMethod[i];
      int k = method.getModifiers();
      if (method.getName().equals(str2) && Modifier.isStatic(k) && Modifier.isPublic(k)) {
        Class[] arrayOfClass = arrayOfMethod[i].getParameterTypes();
        if (arrayOfClass.length > 0 && arrayOfClass[0].equals(Connection.class))
          b = 1; 
        for (byte b1 = b; b1 < arrayOfClass.length; b1++) {
          Class<?> clazz1 = arrayOfClass[b1];
          if (clazz1.isArray()) {
            if (!byte[].class.equals(clazz1)) {
              clazz1 = clazz1.getComponentType();
              if (clazz1.isPrimitive()) {
                method = null;
                break;
              } 
              if (ResultSet.class.isAssignableFrom(clazz1) && j > b1)
                j = b1; 
            } 
            if (b1 >= j) {
              if (!ResultSet.class.isAssignableFrom(clazz1)) {
                method = null;
                break;
              } 
              continue;
            } 
          } else if (b1 > j) {
            method = null;
            break;
          } 
          Type type = Types.getParameterSQLType(clazz1);
          if (type == null) {
            method = null;
            break;
          } 
          continue;
        } 
        if (method != null)
          if (ResultSet.class.isAssignableFrom(method.getReturnType())) {
            hsqlArrayList.add(arrayOfMethod[i]);
          } else {
            Type type = Types.getParameterSQLType(method.getReturnType());
            if (type != null)
              hsqlArrayList.add(arrayOfMethod[i]); 
          }  
      } 
    } 
    arrayOfMethod = new Method[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfMethod);
    return arrayOfMethod;
  }
  
  public static Routine[] newRoutines(Session paramSession, Method[] paramArrayOfMethod) {
    Routine[] arrayOfRoutine = new Routine[paramArrayOfMethod.length];
    for (byte b = 0; b < paramArrayOfMethod.length; b++) {
      Method method = paramArrayOfMethod[b];
      arrayOfRoutine[b] = newRoutine(paramSession, method);
    } 
    return arrayOfRoutine;
  }
  
  public static Routine newRoutine(Session paramSession, Method paramMethod) {
    Routine routine = new Routine(16);
    byte b1 = 0;
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    String str1 = paramMethod.getDeclaringClass().getName();
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CLASSPATH:");
    stringBuffer.append(paramMethod.getDeclaringClass().getName()).append('.');
    stringBuffer.append(paramMethod.getName());
    if (arrayOfClass.length > 0 && arrayOfClass[0].equals(Connection.class))
      b1 = 1; 
    String str2 = stringBuffer.toString();
    if (str1.equals("java.lang.Math"))
      routine.isLibraryRoutine = true; 
    for (byte b2 = b1; b2 < arrayOfClass.length; b2++) {
      Type type1 = Types.getParameterSQLType(arrayOfClass[b2]);
      HsqlNameManager.HsqlName hsqlName = paramSession.database.nameManager.newHsqlName("C" + (b2 - b1 + 1), false, 23);
      ColumnSchema columnSchema = new ColumnSchema(hsqlName, type1, !arrayOfClass[b2].isPrimitive(), false, null);
      routine.addParameter(columnSchema);
    } 
    routine.setLanguage(1);
    routine.setMethod(paramMethod);
    routine.setMethodURL(str2);
    routine.setDataImpact(1);
    Type type = Types.getParameterSQLType(paramMethod.getReturnType());
    routine.javaMethodWithConnection = (b1 == 1);
    routine.setReturnType(type);
    routine.resolve(paramSession);
    return routine;
  }
  
  public static void createRoutines(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, String paramString) {
    Method[] arrayOfMethod = getMethods(paramString);
    Routine[] arrayOfRoutine = newRoutines(paramSession, arrayOfMethod);
    HsqlNameManager.HsqlName hsqlName = paramSession.database.nameManager.newHsqlName(paramHsqlName, paramString, true, 16);
    for (byte b = 0; b < arrayOfRoutine.length; b++) {
      arrayOfRoutine[b].setName(hsqlName);
      paramSession.database.schemaManager.addSchemaObject(arrayOfRoutine[b]);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Routine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */