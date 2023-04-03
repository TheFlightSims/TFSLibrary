package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorDataChange;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public class Table extends TableBase implements SchemaObject {
  public static final Table[] emptyArray = new Table[0];
  
  protected HsqlNameManager.HsqlName tableName;
  
  protected long changeTimestamp;
  
  public HashMappedList columnList;
  
  int identityColumn;
  
  NumberSequence identitySequence;
  
  Constraint[] constraintList;
  
  Constraint[] fkConstraints;
  
  Constraint[] fkMainConstraints;
  
  Constraint[] checkConstraints;
  
  TriggerDef[] triggerList;
  
  TriggerDef[][] triggerLists;
  
  Expression[] colDefaults;
  
  private boolean hasDefaultValues;
  
  boolean[] colGenerated;
  
  private boolean hasGeneratedValues;
  
  boolean[] colRefFK;
  
  boolean[] colMainFK;
  
  boolean hasReferentialAction;
  
  boolean isDropped;
  
  private boolean hasDomainColumns;
  
  private boolean hasNotNullColumns;
  
  protected int[] defaultColumnMap;
  
  RangeVariable[] defaultRanges;
  
  public Table(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    this.database = paramDatabase;
    this.tableName = paramHsqlName;
    this.persistenceId = paramDatabase.persistentStoreCollection.getNextId();
    switch (paramInt) {
      case 13:
        this.persistenceScope = 21;
        this.isSessionBased = true;
        break;
      case 2:
        this.persistenceScope = 21;
        this.isSessionBased = true;
        break;
      case 1:
        this.isSessionBased = true;
      case 12:
        this.persistenceScope = 24;
        this.isSchemaBased = true;
        break;
      case 5:
        if (paramDatabase.logger.isFileDatabase()) {
          this.persistenceScope = 24;
          this.isSchemaBased = true;
          this.isCached = true;
          this.isLogged = !paramDatabase.isFilesReadOnly();
          break;
        } 
        paramInt = 4;
      case 4:
        this.persistenceScope = 24;
        this.isSchemaBased = true;
        this.isLogged = !paramDatabase.isFilesReadOnly();
        break;
      case 3:
        this.persistenceScope = 22;
        this.isTemp = true;
        this.isSchemaBased = true;
        this.isSessionBased = true;
        break;
      case 6:
        this.persistenceScope = 23;
        if (!paramDatabase.logger.isFileDatabase())
          throw Error.error(459); 
        this.isSchemaBased = true;
        this.isSessionBased = true;
        this.isTemp = true;
        this.isText = true;
        this.isReadOnly = true;
        break;
      case 7:
        this.persistenceScope = 24;
        if (!paramDatabase.logger.isFileDatabase()) {
          if (!paramDatabase.logger.isAllowedFullPath())
            throw Error.error(459); 
          this.isReadOnly = true;
        } 
        this.isSchemaBased = true;
        this.isText = true;
        break;
      case 8:
        this.persistenceScope = 21;
        this.isSchemaBased = true;
        this.isSessionBased = true;
        this.isView = true;
        break;
      case 9:
        this.persistenceScope = 23;
        this.isSessionBased = true;
        break;
      case 11:
        this.persistenceScope = 21;
        this.isSessionBased = true;
        break;
      default:
        throw Error.runtimeError(201, "Table");
    } 
    this.tableType = paramInt;
    this.primaryKeyCols = null;
    this.primaryKeyTypes = null;
    this.identityColumn = -1;
    this.columnList = new HashMappedList();
    this.indexList = Index.emptyArray;
    this.constraintList = Constraint.emptyArray;
    this.fkConstraints = Constraint.emptyArray;
    this.fkMainConstraints = Constraint.emptyArray;
    this.checkConstraints = Constraint.emptyArray;
    this.triggerList = TriggerDef.emptyArray;
    this.triggerLists = new TriggerDef[9][];
    for (byte b = 0; b < 9; b++)
      this.triggerLists[b] = TriggerDef.emptyArray; 
    if (paramDatabase.isFilesReadOnly() && isFileBased())
      this.isReadOnly = true; 
  }
  
  public Table(Table paramTable, HsqlNameManager.HsqlName paramHsqlName) {
    this.persistenceScope = 21;
    paramHsqlName.schema = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
    this.tableName = paramHsqlName;
    this.database = paramTable.database;
    this.tableType = 9;
    this.columnList = paramTable.columnList;
    this.columnCount = paramTable.columnCount;
    this.indexList = Index.emptyArray;
    this.constraintList = Constraint.emptyArray;
    createPrimaryKey();
  }
  
  public int getType() {
    return 3;
  }
  
  public final HsqlNameManager.HsqlName getName() {
    return this.tableName;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.database.getCatalogName();
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.tableName.schema;
  }
  
  public Grantee getOwner() {
    return this.tableName.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    if (this.identitySequence != null && this.identitySequence.getName() != null)
      orderedHashSet.add(this.identitySequence.getName()); 
    return orderedHashSet;
  }
  
  public RangeVariable[] getDefaultRanges() {
    if (this.defaultRanges == null)
      this.defaultRanges = new RangeVariable[] { new RangeVariable(this, 0) }; 
    return this.defaultRanges;
  }
  
  public OrderedHashSet getReferencesForDependents() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b;
    for (b = 0; b < this.colTypes.length; b++) {
      ColumnSchema columnSchema = getColumn(b);
      OrderedHashSet orderedHashSet1 = columnSchema.getReferences();
      if (orderedHashSet1 != null && !orderedHashSet1.isEmpty())
        orderedHashSet.add(columnSchema.getName()); 
    } 
    for (b = 0; b < this.fkConstraints.length; b++) {
      if (this.fkConstraints[b].getMainTableName() != getName())
        orderedHashSet.add(this.fkConstraints[b].getName()); 
    } 
    for (b = 0; b < this.triggerList.length; b++)
      orderedHashSet.add(this.triggerList[b].getName()); 
    return orderedHashSet;
  }
  
  public OrderedHashSet getComponents() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    orderedHashSet.addAll((Object[])this.constraintList);
    orderedHashSet.addAll((Object[])this.triggerList);
    for (byte b = 0; b < this.indexList.length; b++) {
      if (!this.indexList[b].isConstraint())
        orderedHashSet.add(this.indexList[b]); 
    } 
    return orderedHashSet;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {
    for (byte b = 0; b < this.columnCount; b++) {
      ColumnSchema columnSchema = getColumn(b);
      columnSchema.compile(paramSession, this);
    } 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ');
    if (isTemp()) {
      stringBuffer.append("GLOBAL").append(' ');
      stringBuffer.append("TEMPORARY").append(' ');
    } else if (isText()) {
      stringBuffer.append("TEXT").append(' ');
    } else if (isCached()) {
      stringBuffer.append("CACHED").append(' ');
    } else {
      stringBuffer.append("MEMORY").append(' ');
    } 
    stringBuffer.append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append('(');
    int[] arrayOfInt = getPrimaryKey();
    Constraint constraint = getPrimaryConstraint();
    for (byte b1 = 0; b1 < this.columnCount; b1++) {
      ColumnSchema columnSchema = getColumn(b1);
      String str1 = (columnSchema.getName()).statementName;
      Type type = columnSchema.getDataType();
      if (b1 > 0)
        stringBuffer.append(','); 
      stringBuffer.append(str1);
      stringBuffer.append(' ');
      stringBuffer.append(type.getTypeDefinition());
      if (!type.isDistinctType() && !type.isDomainType() && type.isCharacterType()) {
        Collation collation = ((CharacterType)type).getCollation();
        if (collation.isObjectCollation())
          stringBuffer.append(' ').append(collation.getCollateSQL()); 
      } 
      String str2 = columnSchema.getDefaultSQL();
      if (str2 != null) {
        stringBuffer.append(' ').append("DEFAULT").append(' ');
        stringBuffer.append(str2);
      } 
      if (columnSchema.isIdentity())
        stringBuffer.append(' ').append(columnSchema.getIdentitySequence().getSQLColumnDefinition()); 
      if (columnSchema.isGenerated()) {
        stringBuffer.append(' ').append("GENERATED").append(' ');
        stringBuffer.append("ALWAYS").append(' ').append("AS").append("(");
        stringBuffer.append(columnSchema.getGeneratingExpression().getSQL());
        stringBuffer.append(")");
      } 
      if (!columnSchema.isNullable()) {
        Constraint constraint1 = getNotNullConstraintForColumn(b1);
        if (constraint1 != null && !constraint1.getName().isReservedName())
          stringBuffer.append(' ').append("CONSTRAINT").append(' ').append((constraint1.getName()).statementName); 
        stringBuffer.append(' ').append("NOT").append(' ').append("NULL");
      } 
      if (arrayOfInt.length == 1 && b1 == arrayOfInt[0] && constraint.getName().isReservedName())
        stringBuffer.append(' ').append("PRIMARY").append(' ').append("KEY"); 
    } 
    Constraint[] arrayOfConstraint = getConstraints();
    byte b2 = 0;
    int i = arrayOfConstraint.length;
    while (b2 < i) {
      Constraint constraint1 = arrayOfConstraint[b2];
      if (!constraint1.isForward) {
        String str = constraint1.getSQL();
        if (str.length() > 0) {
          stringBuffer.append(',');
          stringBuffer.append(str);
        } 
      } 
      b2++;
    } 
    stringBuffer.append(')');
    if (onCommitPreserve()) {
      stringBuffer.append(' ').append("ON").append(' ');
      stringBuffer.append("COMMIT").append(' ').append("PRESERVE");
      stringBuffer.append(' ').append("ROWS");
    } 
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return this.changeTimestamp;
  }
  
  public final void setName(HsqlNameManager.HsqlName paramHsqlName) {
    this.tableName = paramHsqlName;
  }
  
  String[] getSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    for (byte b1 = 0; b1 < this.constraintList.length; b1++) {
      Constraint constraint = this.constraintList[b1];
      if (constraint.isForward) {
        paramOrderedHashSet2.add(constraint);
      } else if (constraint.getConstraintType() == 2 || constraint.getConstraintType() == 4) {
        paramOrderedHashSet1.add(constraint.getName());
      } 
    } 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    hsqlArrayList.add(getSQL());
    if (!this.isTemp && !this.isText && this.identitySequence != null && this.identitySequence.getName() == null)
      hsqlArrayList.add(NumberSequence.getRestartSQL(this)); 
    for (byte b2 = 0; b2 < this.indexList.length; b2++) {
      if (!this.indexList[b2].isConstraint() && this.indexList[b2].getColumnCount() > 0)
        hsqlArrayList.add(this.indexList[b2].getSQL()); 
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String getSQLForReadOnly() {
    if (this.isReadOnly) {
      StringBuffer stringBuffer = new StringBuffer(64);
      stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
      stringBuffer.append(getName().getSchemaQualifiedStatementName());
      stringBuffer.append(' ').append("READ").append(' ');
      stringBuffer.append("ONLY");
      return stringBuffer.toString();
    } 
    return null;
  }
  
  public String[] getSQLForTextSource(boolean paramBoolean) {
    if (isText()) {
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      if (this.isReadOnly)
        hsqlArrayList.add(getSQLForReadOnly()); 
      String str1 = ((TextTable)this).getDataSourceDDL();
      if (str1 != null)
        hsqlArrayList.add(str1); 
      String str2 = ((TextTable)this).getDataSourceHeader();
      if (paramBoolean && str2 != null && !this.isReadOnly)
        hsqlArrayList.add(str2); 
      String[] arrayOfString = new String[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfString);
      return arrayOfString;
    } 
    return null;
  }
  
  public String getSQLForClustered() {
    if (!isCached() && !isText())
      return null; 
    Index index = getClusteredIndex();
    if (index == null)
      return null; 
    String str = getColumnListSQL(index.getColumns(), index.getColumnCount());
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("CLUSTERED").append(' ');
    stringBuffer.append("ON").append(' ').append(str);
    return stringBuffer.toString();
  }
  
  public String getSQLForTableSpace() {
    if (!isCached() || this.tableSpace == 7)
      return null; 
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("SPACE").append(' ').append(this.tableSpace);
    return stringBuffer.toString();
  }
  
  public String[] getTriggerSQL() {
    String[] arrayOfString = new String[this.triggerList.length];
    for (byte b = 0; b < this.triggerList.length; b++) {
      if (!this.triggerList[b].isSystem())
        arrayOfString[b] = this.triggerList[b].getSQL(); 
    } 
    return arrayOfString;
  }
  
  public String getIndexRootsSQL(long[] paramArrayOflong) {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("INDEX").append(' ').append('\'');
    stringBuffer.append(StringUtil.getList(paramArrayOflong, " ", ""));
    stringBuffer.append('\'');
    return stringBuffer.toString();
  }
  
  public String getColumnListSQL(int[] paramArrayOfint, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('(');
    for (byte b = 0; b < paramInt; b++) {
      stringBuffer.append((getColumn(paramArrayOfint[b]).getName()).statementName);
      if (b < paramInt - 1)
        stringBuffer.append(','); 
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public String getColumnListWithTypeSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('(');
    for (byte b = 0; b < this.columnCount; b++) {
      ColumnSchema columnSchema = getColumn(b);
      String str = (columnSchema.getName()).statementName;
      Type type = columnSchema.getDataType();
      if (b > 0)
        stringBuffer.append(','); 
      stringBuffer.append(str);
      stringBuffer.append(' ');
      stringBuffer.append(type.getTypeDefinition());
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public boolean isConnected() {
    return true;
  }
  
  public static int compareRows(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint, Type[] paramArrayOfType) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      int j = paramArrayOfType[paramArrayOfint[b]].compare(paramSession, paramArrayOfObject1[paramArrayOfint[b]], paramArrayOfObject2[paramArrayOfint[b]]);
      if (j != 0)
        return j; 
    } 
    return 0;
  }
  
  public int getId() {
    return this.tableName.hashCode();
  }
  
  public final boolean isSchemaBaseTable() {
    switch (this.tableType) {
      case 4:
      case 5:
      case 7:
        return true;
    } 
    return false;
  }
  
  public final boolean isWithDataSource() {
    return this.isWithDataSource;
  }
  
  public final boolean isText() {
    return this.isText;
  }
  
  public final boolean isTemp() {
    return this.isTemp;
  }
  
  public final boolean isReadOnly() {
    return this.isReadOnly;
  }
  
  public final boolean isView() {
    return this.isView;
  }
  
  public boolean isQueryBased() {
    return false;
  }
  
  public boolean isCached() {
    return this.isCached;
  }
  
  public boolean isDataReadOnly() {
    return this.isReadOnly;
  }
  
  public boolean isDropped() {
    return this.isDropped;
  }
  
  final boolean isIndexingMutable() {
    return !this.isCached;
  }
  
  public void checkDataReadOnly() {
    if (isDataReadOnly())
      throw Error.error(456); 
  }
  
  public void setDataReadOnly(boolean paramBoolean) {
    if (!paramBoolean) {
      if (this.database.isFilesReadOnly() && isFileBased())
        throw Error.error(456); 
      if (this.database.getType() == "mem:" && this.isText)
        throw Error.error(456); 
    } 
    this.isReadOnly = paramBoolean;
  }
  
  public boolean isFileBased() {
    return (this.isCached || this.isText);
  }
  
  public void addConstraint(Constraint paramConstraint) {
    boolean bool = (paramConstraint.getConstraintType() == 4) ? false : this.constraintList.length;
    this.constraintList = (Constraint[])ArrayUtil.toAdjustedArray(this.constraintList, paramConstraint, bool, 1);
    updateConstraintLists();
  }
  
  void updateConstraintLists() {
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    this.hasReferentialAction = false;
    byte b4;
    for (b4 = 0; b4 < this.constraintList.length; b4++) {
      switch (this.constraintList[b4].getConstraintType()) {
        case 0:
          b1++;
          break;
        case 1:
          b2++;
          break;
        case 3:
          if (this.constraintList[b4].isNotNull())
            break; 
          b3++;
          break;
      } 
    } 
    this.fkConstraints = (b1 == 0) ? Constraint.emptyArray : new Constraint[b1];
    b1 = 0;
    this.fkMainConstraints = (b2 == 0) ? Constraint.emptyArray : new Constraint[b2];
    b2 = 0;
    this.checkConstraints = (b3 == 0) ? Constraint.emptyArray : new Constraint[b3];
    b3 = 0;
    this.colRefFK = new boolean[this.columnCount];
    this.colMainFK = new boolean[this.columnCount];
    for (b4 = 0; b4 < this.constraintList.length; b4++) {
      switch (this.constraintList[b4].getConstraintType()) {
        case 0:
          this.fkConstraints[b1] = this.constraintList[b4];
          ArrayUtil.intIndexesToBooleanArray(this.constraintList[b4].getRefColumns(), this.colRefFK);
          b1++;
          break;
        case 1:
          this.fkMainConstraints[b2] = this.constraintList[b4];
          ArrayUtil.intIndexesToBooleanArray(this.constraintList[b4].getMainColumns(), this.colMainFK);
          if (this.constraintList[b4].hasTriggeredAction())
            this.hasReferentialAction = true; 
          b2++;
          break;
        case 3:
          if (this.constraintList[b4].isNotNull())
            break; 
          this.checkConstraints[b3] = this.constraintList[b4];
          b3++;
          break;
      } 
    } 
  }
  
  void verifyConstraintsIntegrity() {
    for (byte b = 0; b < this.constraintList.length; b++) {
      Constraint constraint = this.constraintList[b];
      if (constraint.getConstraintType() == 0 || constraint.getConstraintType() == 1) {
        if (constraint.getMain() != this.database.schemaManager.findUserTable(null, (constraint.getMain().getName()).name, (constraint.getMain().getName()).schema.name))
          throw Error.runtimeError(201, "FK mismatch : " + (constraint.getName()).name); 
        if (constraint.getRef() != this.database.schemaManager.findUserTable(null, (constraint.getRef().getName()).name, (constraint.getRef().getName()).schema.name))
          throw Error.runtimeError(201, "FK mismatch : " + (constraint.getName()).name); 
      } 
    } 
  }
  
  public Constraint[] getConstraints() {
    return this.constraintList;
  }
  
  public Constraint[] getFKConstraints() {
    return this.fkConstraints;
  }
  
  public Constraint getPrimaryConstraint() {
    return (this.primaryKeyCols.length == 0) ? null : this.constraintList[0];
  }
  
  void collectFKReadLocks(int[] paramArrayOfint, OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.fkMainConstraints.length; b++) {
      Constraint constraint = this.fkMainConstraints[b];
      Table table = constraint.getRef();
      int[] arrayOfInt = constraint.getMainColumns();
      if (table != this)
        if (paramArrayOfint == null) {
          if (constraint.core.hasDeleteAction) {
            int[] arrayOfInt1 = (constraint.core.deleteAction == 0) ? null : constraint.getRefColumns();
            if (paramOrderedHashSet.add(table.getName()))
              table.collectFKReadLocks(arrayOfInt1, paramOrderedHashSet); 
          } 
        } else if (ArrayUtil.haveCommonElement(paramArrayOfint, arrayOfInt) && paramOrderedHashSet.add(table.getName())) {
          table.collectFKReadLocks(constraint.getRefColumns(), paramOrderedHashSet);
        }  
    } 
  }
  
  void collectFKWriteLocks(int[] paramArrayOfint, OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < this.fkMainConstraints.length; b++) {
      Constraint constraint = this.fkMainConstraints[b];
      Table table = constraint.getRef();
      int[] arrayOfInt = constraint.getMainColumns();
      if (table != this)
        if (paramArrayOfint == null) {
          if (constraint.core.hasDeleteAction) {
            int[] arrayOfInt1 = (constraint.core.deleteAction == 0) ? null : constraint.getRefColumns();
            if (paramOrderedHashSet.add(table.getName()))
              table.collectFKWriteLocks(arrayOfInt1, paramOrderedHashSet); 
          } 
        } else if (ArrayUtil.haveCommonElement(paramArrayOfint, arrayOfInt) && constraint.core.hasUpdateAction && paramOrderedHashSet.add(table.getName())) {
          table.collectFKWriteLocks(constraint.getRefColumns(), paramOrderedHashSet);
        }  
    } 
  }
  
  Constraint getNotNullConstraintForColumn(int paramInt) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.isNotNull() && constraint.notNullColumnIndex == paramInt)
        return constraint; 
      b++;
    } 
    return null;
  }
  
  Constraint getUniqueConstraintForColumns(int[] paramArrayOfint) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.isUniqueWithColumns(paramArrayOfint))
        return constraint; 
      b++;
    } 
    return null;
  }
  
  Constraint getFKConstraintForColumns(Table paramTable, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.isEquivalent(paramTable, paramArrayOfint1, this, paramArrayOfint2))
        return constraint; 
      b++;
    } 
    return null;
  }
  
  public Constraint getUniqueOrPKConstraintForIndex(Index paramIndex) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.getMainIndex() == paramIndex && (constraint.getConstraintType() == 2 || constraint.getConstraintType() == 4))
        return constraint; 
      b++;
    } 
    return null;
  }
  
  int getNextConstraintIndex(int paramInt1, int paramInt2) {
    int i = paramInt1;
    int j = this.constraintList.length;
    while (i < j) {
      Constraint constraint = this.constraintList[i];
      if (constraint.getConstraintType() == paramInt2)
        return i; 
      i++;
    } 
    return -1;
  }
  
  public void addColumn(ColumnSchema paramColumnSchema) {
    String str = (paramColumnSchema.getName()).name;
    if (findColumn(str) >= 0)
      throw Error.error(5504, str); 
    if (paramColumnSchema.isIdentity()) {
      if (this.identityColumn != -1)
        throw Error.error(5525, str); 
      this.identityColumn = this.columnCount;
      this.identitySequence = paramColumnSchema.getIdentitySequence();
    } 
    addColumnNoCheck(paramColumnSchema);
  }
  
  public void addColumnNoCheck(ColumnSchema paramColumnSchema) {
    if (this.primaryKeyCols != null)
      throw Error.runtimeError(201, "Table"); 
    this.columnList.add((paramColumnSchema.getName()).name, paramColumnSchema);
    this.columnCount++;
  }
  
  public boolean hasGeneratedColumn() {
    return this.hasGeneratedValues;
  }
  
  public boolean hasLobColumn() {
    return this.hasLobColumn;
  }
  
  public boolean hasIdentityColumn() {
    return (this.identityColumn != -1);
  }
  
  public long getNextIdentity() {
    return this.identitySequence.peek();
  }
  
  void checkColumnsMatch(int[] paramArrayOfint1, Table paramTable, int[] paramArrayOfint2) {
    for (byte b = 0; b < paramArrayOfint1.length; b++) {
      Type type1 = this.colTypes[paramArrayOfint1[b]];
      Type type2 = paramTable.colTypes[paramArrayOfint2[b]];
      if (type1.typeComparisonGroup != type2.typeComparisonGroup)
        throw Error.error(5562); 
    } 
  }
  
  void checkColumnsMatch(ColumnSchema paramColumnSchema, int paramInt) {
    Type type1 = this.colTypes[paramInt];
    Type type2 = paramColumnSchema.getDataType();
    if (type1.typeComparisonGroup != type2.typeComparisonGroup)
      throw Error.error(5562); 
  }
  
  Table moveDefinition(Session paramSession, int paramInt1, ColumnSchema paramColumnSchema, Constraint paramConstraint, Index paramIndex, int paramInt2, int paramInt3, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    Table table;
    boolean bool = false;
    if (paramConstraint != null && paramConstraint.constType == 4)
      bool = true; 
    if (this.isText) {
      table = new TextTable(this.database, this.tableName, paramInt1);
      ((TextTable)table).dataSource = ((TextTable)this).dataSource;
      ((TextTable)table).isReversed = ((TextTable)this).isReversed;
      ((TextTable)table).isConnected = ((TextTable)this).isConnected;
    } else {
      table = new Table(this.database, this.tableName, paramInt1);
    } 
    if (this.tableType == 3)
      table.persistenceScope = this.persistenceScope; 
    for (byte b1 = 0; b1 < this.columnCount; b1++) {
      ColumnSchema columnSchema = (ColumnSchema)this.columnList.get(b1);
      if (b1 == paramInt2) {
        if (paramColumnSchema != null)
          table.addColumn(paramColumnSchema); 
        if (paramInt3 <= 0)
          continue; 
      } 
      table.addColumn(columnSchema);
      continue;
    } 
    if (this.columnCount == paramInt2)
      table.addColumn(paramColumnSchema); 
    int[] arrayOfInt = null;
    if (hasPrimaryKey() && !paramOrderedHashSet1.contains(getPrimaryConstraint().getName())) {
      arrayOfInt = this.primaryKeyCols;
      arrayOfInt = ArrayUtil.toAdjustedColumnArray(arrayOfInt, paramInt2, paramInt3);
    } else if (bool) {
      arrayOfInt = paramConstraint.getMainColumns();
    } 
    table.createPrimaryKey(getIndex(0).getName(), arrayOfInt, false);
    for (byte b2 = 1; b2 < this.indexList.length; b2++) {
      Index index = this.indexList[b2];
      if (!paramOrderedHashSet2.contains(index.getName())) {
        int[] arrayOfInt1 = ArrayUtil.toAdjustedColumnArray(index.getColumns(), paramInt2, paramInt3);
        Index index1 = table.createIndexStructure(index.getName(), arrayOfInt1, index.getColumnDesc(), null, index.isUnique(), index.isConstraint(), index.isForward());
        index1.setClustered(index.isClustered());
        table.addIndex(paramSession, index1);
      } 
    } 
    if (paramIndex != null)
      table.addIndex(paramSession, paramIndex); 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    if (bool) {
      paramConstraint.core.mainIndex = table.indexList[0];
      paramConstraint.core.mainTable = table;
      paramConstraint.core.mainTableName = table.tableName;
      hsqlArrayList.add(paramConstraint);
    } 
    byte b3;
    for (b3 = 0; b3 < this.constraintList.length; b3++) {
      Constraint constraint = this.constraintList[b3];
      if (!paramOrderedHashSet1.contains(constraint.getName())) {
        constraint = constraint.duplicate();
        constraint.updateTable(paramSession, this, table, paramInt2, paramInt3);
        hsqlArrayList.add(constraint);
      } 
    } 
    if (!bool && paramConstraint != null) {
      paramConstraint.updateTable(paramSession, this, table, -1, 0);
      hsqlArrayList.add(paramConstraint);
    } 
    table.constraintList = new Constraint[hsqlArrayList.size()];
    hsqlArrayList.toArray(table.constraintList);
    table.updateConstraintLists();
    table.setBestRowIdentifiers();
    table.triggerList = this.triggerList;
    table.triggerLists = this.triggerLists;
    for (b3 = 0; b3 < table.constraintList.length; b3++)
      table.constraintList[b3].compile(paramSession, table); 
    for (b3 = 0; b3 < table.columnCount; b3++)
      table.getColumn(b3).compile(paramSession, table); 
    return table;
  }
  
  void checkColumnInCheckConstraint(int paramInt) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.constType == 3 && !constraint.isNotNull() && constraint.hasColumn(paramInt)) {
        HsqlNameManager.HsqlName hsqlName = constraint.getName();
        throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
      } 
      b++;
    } 
  }
  
  void checkColumnInFKConstraint(int paramInt) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.hasColumn(paramInt) && (constraint.getConstraintType() == 1 || constraint.getConstraintType() == 0)) {
        HsqlNameManager.HsqlName hsqlName = constraint.getName();
        throw Error.error(5533, hsqlName.getSchemaQualifiedStatementName());
      } 
      b++;
    } 
  }
  
  OrderedHashSet getDependentConstraints(int paramInt) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.hasColumnOnly(paramInt))
        orderedHashSet.add(constraint); 
      b++;
    } 
    return orderedHashSet;
  }
  
  OrderedHashSet getContainingConstraints(int paramInt) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.hasColumnPlus(paramInt))
        orderedHashSet.add(constraint); 
      b++;
    } 
    return orderedHashSet;
  }
  
  OrderedHashSet getContainingIndexNames(int paramInt) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.indexList.length;
    while (b < i) {
      Index index = this.indexList[b];
      if (ArrayUtil.find(index.getColumns(), paramInt) != -1)
        orderedHashSet.add(index.getName()); 
      b++;
    } 
    return orderedHashSet;
  }
  
  OrderedHashSet getDependentConstraints(Constraint paramConstraint) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.fkMainConstraints.length;
    while (b < i) {
      Constraint constraint = this.fkMainConstraints[b];
      if (constraint.core.uniqueName == paramConstraint.getName())
        orderedHashSet.add(constraint); 
      b++;
    } 
    return orderedHashSet;
  }
  
  public OrderedHashSet getDependentExternalConstraints() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if ((constraint.getConstraintType() == 1 || constraint.getConstraintType() == 0) && constraint.core.mainTable != constraint.core.refTable)
        orderedHashSet.add(constraint); 
      b++;
    } 
    return orderedHashSet;
  }
  
  public OrderedHashSet getUniquePKConstraintNames() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.constType == 2 || constraint.constType == 4)
        orderedHashSet.add(constraint.getName()); 
      b++;
    } 
    return orderedHashSet;
  }
  
  void checkColumnInFKConstraint(int paramInt1, int paramInt2) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.getConstraintType() == 0 && constraint.hasColumn(paramInt1) && (paramInt2 == constraint.getUpdateAction() || paramInt2 == constraint.getDeleteAction())) {
        HsqlNameManager.HsqlName hsqlName = constraint.getName();
        throw Error.error(5533, hsqlName.getSchemaQualifiedStatementName());
      } 
      b++;
    } 
  }
  
  int getIdentityColumnIndex() {
    return this.identityColumn;
  }
  
  public int getColumnIndex(String paramString) {
    int i = findColumn(paramString);
    if (i == -1)
      throw Error.error(5501, paramString); 
    return i;
  }
  
  public int findColumn(String paramString) {
    return this.columnList.getIndex(paramString);
  }
  
  void resetDefaultsFlag() {
    this.hasDefaultValues = false;
    byte b;
    for (b = 0; b < this.colDefaults.length; b++)
      this.hasDefaultValues |= (this.colDefaults[b] != null) ? 1 : 0; 
    this.hasGeneratedValues = false;
    for (b = 0; b < this.colGenerated.length; b++)
      this.hasGeneratedValues |= this.colGenerated[b]; 
    this.hasNotNullColumns = false;
    for (b = 0; b < this.colNotNull.length; b++)
      this.hasNotNullColumns |= this.colNotNull[b]; 
  }
  
  public int[] getBestRowIdentifiers() {
    return this.bestRowIdentifierCols;
  }
  
  public boolean isBestRowIdentifiersStrict() {
    return this.bestRowIdentifierStrict;
  }
  
  public Index getClusteredIndex() {
    for (byte b = 0; b < this.indexList.length; b++) {
      if (this.indexList[b].isClustered())
        return this.indexList[b]; 
    } 
    return null;
  }
  
  synchronized Index getIndexForColumn(Session paramSession, int paramInt) {
    int i = this.bestIndexForColumn[paramInt];
    if (i > -1)
      return this.indexList[i]; 
    switch (this.tableType) {
      case 1:
      case 2:
      case 3:
      case 8:
      case 11:
        return createIndexForColumns(paramSession, new int[] { paramInt });
    } 
    return null;
  }
  
  boolean isIndexed(int paramInt) {
    return (this.bestIndexForColumn[paramInt] != -1);
  }
  
  int[] getUniqueNotNullColumnGroup(boolean[] paramArrayOfboolean) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.constType == 2) {
        int[] arrayOfInt = constraint.getMainColumns();
        if (ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, this.colNotNull) && ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, paramArrayOfboolean))
          return arrayOfInt; 
      } else if (constraint.constType == 4) {
        int[] arrayOfInt = constraint.getMainColumns();
        if (ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, paramArrayOfboolean))
          return arrayOfInt; 
      } 
      b++;
    } 
    return null;
  }
  
  boolean areColumnsNotNull(int[] paramArrayOfint) {
    return ArrayUtil.areAllIntIndexesInBooleanArray(paramArrayOfint, this.colNotNull);
  }
  
  public void createPrimaryKey() {
    createPrimaryKey((HsqlNameManager.HsqlName)null, ValuePool.emptyIntArray, false);
  }
  
  public void createPrimaryKey(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfint, boolean paramBoolean) {
    if (this.primaryKeyCols != null)
      throw Error.runtimeError(201, "Table"); 
    if (paramArrayOfint == null)
      paramArrayOfint = ValuePool.emptyIntArray; 
    for (byte b = 0; b < paramArrayOfint.length; b++)
      getColumn(paramArrayOfint[b]).setPrimaryKey(true); 
    this.primaryKeyCols = paramArrayOfint;
    setColumnStructures();
    this.primaryKeyTypes = new Type[this.primaryKeyCols.length];
    ArrayUtil.projectRow((Object[])this.colTypes, this.primaryKeyCols, (Object[])this.primaryKeyTypes);
    this.primaryKeyColsSequence = new int[this.primaryKeyCols.length];
    ArrayUtil.fillSequence(this.primaryKeyColsSequence);
    HsqlNameManager.HsqlName hsqlName = paramHsqlName;
    if (hsqlName == null)
      hsqlName = this.database.nameManager.newAutoName("IDX", getSchemaName(), getName(), 20); 
    createPrimaryIndex(this.primaryKeyCols, this.primaryKeyTypes, hsqlName);
    setBestRowIdentifiers();
  }
  
  public void createPrimaryKeyConstraint(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfint, boolean paramBoolean) {
    createPrimaryKey(paramHsqlName, paramArrayOfint, paramBoolean);
    Constraint constraint = new Constraint(paramHsqlName, this, getPrimaryIndex(), 4);
    addConstraint(constraint);
  }
  
  void setColumnStructures() {
    if (this.colTypes == null)
      this.colTypes = new Type[this.columnCount]; 
    this.colDefaults = new Expression[this.columnCount];
    this.colNotNull = new boolean[this.columnCount];
    this.colGenerated = new boolean[this.columnCount];
    this.defaultColumnMap = new int[this.columnCount];
    this.hasDomainColumns = false;
    for (byte b = 0; b < this.columnCount; b++)
      setColumnTypeVars(b); 
    resetDefaultsFlag();
  }
  
  void setColumnTypeVars(int paramInt) {
    ColumnSchema columnSchema = getColumn(paramInt);
    Type type = columnSchema.getDataType();
    if (type.isDomainType())
      this.hasDomainColumns = true; 
    if (type.isLobType())
      this.hasLobColumn = true; 
    this.colTypes[paramInt] = type;
    this.colNotNull[paramInt] = (columnSchema.isPrimaryKey() || !columnSchema.isNullable());
    this.defaultColumnMap[paramInt] = paramInt;
    if (columnSchema.isIdentity()) {
      this.identitySequence = columnSchema.getIdentitySequence();
      this.identityColumn = paramInt;
    } else if (this.identityColumn == paramInt) {
      this.identityColumn = -1;
    } 
    this.colDefaults[paramInt] = columnSchema.getDefaultExpression();
    this.colGenerated[paramInt] = columnSchema.isGenerated();
    resetDefaultsFlag();
  }
  
  int[] getColumnMap() {
    return this.defaultColumnMap;
  }
  
  int[] getNewColumnMap() {
    return new int[this.columnCount];
  }
  
  boolean[] getColumnCheckList(int[] paramArrayOfint) {
    boolean[] arrayOfBoolean = new boolean[this.columnCount];
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      int i = paramArrayOfint[b];
      if (i > -1)
        arrayOfBoolean[i] = true; 
    } 
    return arrayOfBoolean;
  }
  
  int[] getColumnIndexes(String[] paramArrayOfString) {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = getColumnIndex(paramArrayOfString[b]); 
    return arrayOfInt;
  }
  
  int[] getColumnIndexes(OrderedHashSet paramOrderedHashSet) {
    int[] arrayOfInt = new int[paramOrderedHashSet.size()];
    for (byte b = 0; b < arrayOfInt.length; b++) {
      arrayOfInt[b] = getColumnIndex((String)paramOrderedHashSet.get(b));
      if (arrayOfInt[b] == -1)
        throw Error.error(5501, (String)paramOrderedHashSet.get(b)); 
    } 
    return arrayOfInt;
  }
  
  int[] getColumnIndexes(HashMappedList paramHashMappedList) {
    int[] arrayOfInt = new int[paramHashMappedList.size()];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = ((Integer)paramHashMappedList.get(b)).intValue(); 
    return arrayOfInt;
  }
  
  public ColumnSchema getColumn(int paramInt) {
    return (ColumnSchema)this.columnList.get(paramInt);
  }
  
  public OrderedHashSet getColumnNameSet(int[] paramArrayOfint) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < paramArrayOfint.length; b++)
      orderedHashSet.add(((ColumnSchema)this.columnList.get(b)).getName()); 
    return orderedHashSet;
  }
  
  public OrderedHashSet getColumnNameSet(boolean[] paramArrayOfboolean) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < paramArrayOfboolean.length; b++) {
      if (paramArrayOfboolean[b])
        orderedHashSet.add(this.columnList.get(b)); 
    } 
    return orderedHashSet;
  }
  
  public void getColumnNames(boolean[] paramArrayOfboolean, Set paramSet) {
    for (byte b = 0; b < paramArrayOfboolean.length; b++) {
      if (paramArrayOfboolean[b])
        paramSet.add(((ColumnSchema)this.columnList.get(b)).getName()); 
    } 
  }
  
  public OrderedHashSet getColumnNameSet() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < this.columnCount; b++)
      orderedHashSet.add(((ColumnSchema)this.columnList.get(b)).getName()); 
    return orderedHashSet;
  }
  
  Object[] getNewRowData(Session paramSession) {
    Object[] arrayOfObject = new Object[this.columnCount];
    if (this.hasDefaultValues)
      for (byte b = 0; b < this.columnCount; b++) {
        Expression expression = this.colDefaults[b];
        if (expression != null)
          arrayOfObject[b] = expression.getValue(paramSession, this.colTypes[b]); 
      }  
    return arrayOfObject;
  }
  
  boolean hasTrigger(int paramInt) {
    return ((this.triggerLists[paramInt]).length != 0);
  }
  
  void addTrigger(TriggerDef paramTriggerDef, HsqlNameManager.HsqlName paramHsqlName) {
    int i = this.triggerList.length;
    if (paramHsqlName != null) {
      int j = getTriggerIndex(paramHsqlName.name);
      if (j != -1)
        i = j + 1; 
    } 
    this.triggerList = (TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerList, paramTriggerDef, i, 1);
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramTriggerDef.triggerType];
    i = arrayOfTriggerDef.length;
    if (paramHsqlName != null)
      for (byte b = 0; b < arrayOfTriggerDef.length; b++) {
        TriggerDef triggerDef = arrayOfTriggerDef[b];
        if ((triggerDef.getName()).name.equals(paramHsqlName.name)) {
          i = b + 1;
          break;
        } 
      }  
    arrayOfTriggerDef = (TriggerDef[])ArrayUtil.toAdjustedArray(arrayOfTriggerDef, paramTriggerDef, i, 1);
    this.triggerLists[paramTriggerDef.triggerType] = arrayOfTriggerDef;
  }
  
  TriggerDef getTrigger(String paramString) {
    for (int i = this.triggerList.length - 1; i >= 0; i--) {
      if ((this.triggerList[i].getName()).name.equals(paramString))
        return this.triggerList[i]; 
    } 
    return null;
  }
  
  public int getTriggerIndex(String paramString) {
    for (byte b = 0; b < this.triggerList.length; b++) {
      if ((this.triggerList[b].getName()).name.equals(paramString))
        return b; 
    } 
    return -1;
  }
  
  void removeTrigger(TriggerDef paramTriggerDef) {
    TriggerDef triggerDef = null;
    int i;
    for (i = 0; i < this.triggerList.length; i++) {
      triggerDef = this.triggerList[i];
      if ((triggerDef.getName()).name.equals((paramTriggerDef.getName()).name)) {
        triggerDef.terminate();
        this.triggerList = (TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerList, null, i, -1);
        break;
      } 
    } 
    if (triggerDef == null)
      return; 
    i = triggerDef.triggerType;
    for (byte b = 0; b < (this.triggerLists[i]).length; b++) {
      triggerDef = this.triggerLists[i][b];
      if ((triggerDef.getName()).name.equals((paramTriggerDef.getName()).name)) {
        this.triggerLists[i] = (TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerLists[i], null, b, -1);
        break;
      } 
    } 
  }
  
  void releaseTriggers() {
    for (byte b = 0; b < 9; b++) {
      for (byte b1 = 0; b1 < (this.triggerLists[b]).length; b1++)
        this.triggerLists[b][b1].terminate(); 
      this.triggerLists[b] = TriggerDef.emptyArray;
    } 
    this.triggerList = TriggerDef.emptyArray;
  }
  
  void terminateTriggers() {
    for (byte b = 0; b < 9; b++) {
      for (byte b1 = 0; b1 < (this.triggerLists[b]).length; b1++)
        this.triggerLists[b][b1].terminate(); 
    } 
  }
  
  int getIndexIndex(String paramString) {
    Index[] arrayOfIndex = this.indexList;
    for (byte b = 0; b < arrayOfIndex.length; b++) {
      if (paramString.equals((arrayOfIndex[b].getName()).name))
        return b; 
    } 
    return -1;
  }
  
  Index getIndex(String paramString) {
    Index[] arrayOfIndex = this.indexList;
    int i = getIndexIndex(paramString);
    return (i == -1) ? null : arrayOfIndex[i];
  }
  
  int getConstraintIndex(String paramString) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      if ((this.constraintList[b].getName()).name.equals(paramString))
        return b; 
      b++;
    } 
    return -1;
  }
  
  public Constraint getConstraint(String paramString) {
    int i = getConstraintIndex(paramString);
    return (i < 0) ? null : this.constraintList[i];
  }
  
  public Constraint getUniqueConstraintForIndex(Index paramIndex) {
    byte b = 0;
    int i = this.constraintList.length;
    while (b < i) {
      Constraint constraint = this.constraintList[b];
      if (constraint.getMainIndex() == paramIndex && (constraint.getConstraintType() == 4 || constraint.getConstraintType() == 2))
        return constraint; 
      b++;
    } 
    return null;
  }
  
  void removeConstraint(String paramString) {
    int i = getConstraintIndex(paramString);
    if (i != -1)
      removeConstraint(i); 
  }
  
  void removeConstraint(int paramInt) {
    this.constraintList = (Constraint[])ArrayUtil.toAdjustedArray(this.constraintList, null, paramInt, -1);
    updateConstraintLists();
  }
  
  void renameColumn(ColumnSchema paramColumnSchema, String paramString, boolean paramBoolean) {
    String str = (paramColumnSchema.getName()).name;
    int i = getColumnIndex(str);
    this.columnList.setKey(i, paramString);
    paramColumnSchema.getName().rename(paramString, paramBoolean);
  }
  
  void renameColumn(ColumnSchema paramColumnSchema, HsqlNameManager.HsqlName paramHsqlName) {
    String str = (paramColumnSchema.getName()).name;
    int i = getColumnIndex(str);
    if (findColumn(paramHsqlName.name) != -1)
      throw Error.error(5504); 
    this.columnList.setKey(i, paramHsqlName.name);
    paramColumnSchema.getName().rename(paramHsqlName);
  }
  
  public TriggerDef[] getTriggers() {
    return this.triggerList;
  }
  
  public boolean isWritable() {
    return (!this.isReadOnly && !this.database.databaseReadOnly && (!this.database.isFilesReadOnly() || (!this.isCached && !this.isText)));
  }
  
  public boolean isInsertable() {
    return isWritable();
  }
  
  public boolean isUpdatable() {
    return isWritable();
  }
  
  public boolean isTriggerInsertable() {
    return false;
  }
  
  public boolean isTriggerUpdatable() {
    return false;
  }
  
  public boolean isTriggerDeletable() {
    return false;
  }
  
  public int[] getUpdatableColumns() {
    return this.defaultColumnMap;
  }
  
  public Table getBaseTable() {
    return this;
  }
  
  public int[] getBaseTableColumnMap() {
    return this.defaultColumnMap;
  }
  
  Index createIndexForColumns(Session paramSession, int[] paramArrayOfint) {
    Index index = null;
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newAutoName("IDX_T", getSchemaName(), getName(), 20);
    try {
      index = createAndAddIndexStructure(paramSession, hsqlName, paramArrayOfint, null, null, false, false, false);
    } catch (Throwable throwable) {
      return null;
    } 
    switch (this.tableType) {
      case 1:
      case 3:
        paramSession.sessionData.persistentStoreCollection.registerIndex(paramSession, this);
        break;
    } 
    return index;
  }
  
  void fireTriggers(Session paramSession, int paramInt, RowSetNavigatorDataChange paramRowSetNavigatorDataChange) {
    if (!this.database.isReferentialIntegrity())
      return; 
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    byte b = 0;
    int i = arrayOfTriggerDef.length;
    while (b < i) {
      TriggerDef triggerDef = arrayOfTriggerDef[b];
      boolean bool = triggerDef instanceof TriggerDefSQL;
      if (triggerDef.hasOldTable());
      triggerDef.pushPair(paramSession, null, null);
      b++;
    } 
  }
  
  void fireTriggers(Session paramSession, int paramInt, RowSetNavigator paramRowSetNavigator) {
    if (!this.database.isReferentialIntegrity())
      return; 
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    byte b = 0;
    int i = arrayOfTriggerDef.length;
    while (b < i) {
      TriggerDef triggerDef = arrayOfTriggerDef[b];
      boolean bool = triggerDef instanceof TriggerDefSQL;
      if (triggerDef.hasOldTable());
      triggerDef.pushPair(paramSession, null, null);
      b++;
    } 
  }
  
  void fireTriggers(Session paramSession, int paramInt, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfint) {
    if (!this.database.isReferentialIntegrity())
      return; 
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    byte b = 0;
    int i = arrayOfTriggerDef.length;
    while (b < i) {
      TriggerDef triggerDef = arrayOfTriggerDef[b];
      boolean bool = triggerDef instanceof TriggerDefSQL;
      if (paramArrayOfint == null || triggerDef.getUpdateColumnIndexes() == null || ArrayUtil.haveCommonElement(triggerDef.getUpdateColumnIndexes(), paramArrayOfint))
        if (triggerDef.isForEachRow()) {
          switch (triggerDef.triggerType) {
            case 3:
              if (!bool)
                paramArrayOfObject2 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject2); 
              break;
            case 5:
              if (!bool) {
                paramArrayOfObject1 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject1);
                paramArrayOfObject2 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject2);
              } 
              break;
            case 4:
            case 7:
            case 8:
              if (!bool)
                paramArrayOfObject1 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject1); 
              break;
          } 
          triggerDef.pushPair(paramSession, paramArrayOfObject1, paramArrayOfObject2);
        } else {
          triggerDef.pushPair(paramSession, null, null);
        }  
      b++;
    } 
  }
  
  public void enforceRowConstraints(Session paramSession, Object[] paramArrayOfObject) {
    for (byte b = 0; b < this.columnCount; b++) {
      Type type = this.colTypes[b];
      if (this.hasDomainColumns && type.isDomainType()) {
        Constraint[] arrayOfConstraint = type.userTypeModifier.getConstraints();
        ColumnSchema columnSchema = getColumn(b);
        for (byte b1 = 0; b1 < arrayOfConstraint.length; b1++)
          arrayOfConstraint[b1].checkCheckConstraint(paramSession, this, columnSchema, paramArrayOfObject[b]); 
      } 
      if (this.colNotNull[b] && paramArrayOfObject[b] == null) {
        Constraint constraint = getNotNullConstraintForColumn(b);
        if (constraint == null && ArrayUtil.find(this.primaryKeyCols, b) > -1)
          constraint = getPrimaryConstraint(); 
        String str = (constraint == null) ? "" : (constraint.getName()).name;
        ColumnSchema columnSchema = getColumn(b);
        String[] arrayOfString = { str, this.tableName.statementName, (columnSchema.getName()).statementName };
        throw Error.error(null, 10, 3, arrayOfString);
      } 
    } 
  }
  
  public void enforceTypeLimits(Session paramSession, Object[] paramArrayOfObject) {
    byte b = 0;
    try {
      while (b < this.columnCount) {
        paramArrayOfObject[b] = this.colTypes[b].convertToTypeLimits(paramSession, paramArrayOfObject[b]);
        b++;
      } 
    } catch (HsqlException hsqlException) {
      int i = hsqlException.getErrorCode();
      if (i == -3401 || i == -3403 || i == -3408) {
        ColumnSchema columnSchema = getColumn(b);
        String[] arrayOfString = { "", this.tableName.statementName, (columnSchema.getName()).statementName };
        throw Error.error(hsqlException, i, 3, arrayOfString);
      } 
      throw hsqlException;
    } 
  }
  
  int indexTypeForColumn(Session paramSession, int paramInt) {
    int i = this.bestIndexForColumn[paramInt];
    if (i > -1)
      return (this.indexList[i].isUnique() && this.indexList[i].getColumnCount() == 1) ? 2 : 1; 
    switch (this.tableType) {
      case 1:
      case 2:
      case 3:
      case 8:
      case 11:
        return 1;
    } 
    return 0;
  }
  
  synchronized Index getIndexForColumns(Session paramSession, int[] paramArrayOfint) {
    int i = this.bestIndexForColumn[paramArrayOfint[0]];
    if (i > -1)
      return this.indexList[i]; 
    switch (this.tableType) {
      case 1:
      case 2:
      case 3:
      case 8:
      case 11:
        return createIndexForColumns(paramSession, paramArrayOfint);
    } 
    return null;
  }
  
  Index getFullIndexForColumns(int[] paramArrayOfint) {
    for (byte b = 0; b < this.indexList.length; b++) {
      if (ArrayUtil.haveEqualArrays(this.indexList[b].getColumns(), paramArrayOfint, paramArrayOfint.length))
        return this.indexList[b]; 
    } 
    return null;
  }
  
  Index getIndexForColumns(int[] paramArrayOfint) {
    for (byte b = 0; b < this.indexList.length; b++) {
      if (ArrayUtil.haveEqualSets(this.indexList[b].getColumns(), paramArrayOfint, paramArrayOfint.length))
        return this.indexList[b]; 
    } 
    return null;
  }
  
  synchronized Index.IndexUse[] getIndexForColumns(Session paramSession, OrderedIntHashSet paramOrderedIntHashSet, int paramInt, boolean paramBoolean) {
    Index index;
    Index.IndexUse[] arrayOfIndexUse = Index.emptyUseArray;
    if (paramOrderedIntHashSet.isEmpty())
      return Index.emptyUseArray; 
    byte b = 0;
    int i = this.indexList.length;
    while (b < i) {
      Index index1 = getIndex(b);
      int[] arrayOfInt = index1.getColumns();
      int j = paramBoolean ? paramOrderedIntHashSet.getOrderedStartMatchCount(arrayOfInt) : paramOrderedIntHashSet.getStartMatchCount(arrayOfInt);
      if (j != 0) {
        if (j == paramOrderedIntHashSet.size())
          return index1.asArray(); 
        if (j == index1.getColumnCount() && index1.isUnique())
          return index1.asArray(); 
        if (arrayOfIndexUse.length == 0 && j == index1.getColumnCount()) {
          arrayOfIndexUse = index1.asArray();
        } else {
          Index.IndexUse[] arrayOfIndexUse1 = new Index.IndexUse[arrayOfIndexUse.length + 1];
          ArrayUtil.copyArray(arrayOfIndexUse, arrayOfIndexUse1, arrayOfIndexUse.length);
          arrayOfIndexUse1[arrayOfIndexUse1.length - 1] = new Index.IndexUse(index1, j);
          arrayOfIndexUse = arrayOfIndexUse1;
        } 
      } 
      b++;
    } 
    switch (this.tableType) {
      case 1:
      case 2:
      case 3:
      case 8:
      case 11:
        index = createIndexForColumns(paramSession, paramOrderedIntHashSet.toArray());
        if (index != null)
          arrayOfIndexUse = index.asArray(); 
        break;
    } 
    return arrayOfIndexUse;
  }
  
  public final long[] getIndexRootsArray() {
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    long[] arrayOfLong = new long[this.indexList.length * 2 + 1];
    byte b1 = 0;
    byte b2;
    for (b2 = 0; b2 < this.indexList.length; b2++) {
      CachedObject cachedObject = persistentStore.getAccessor(this.indexList[b2]);
      arrayOfLong[b1++] = (cachedObject == null) ? -1L : cachedObject.getPos();
    } 
    for (b2 = 0; b2 < this.indexList.length; b2++)
      arrayOfLong[b1++] = this.indexList[b2].sizeUnique(persistentStore); 
    arrayOfLong[b1] = this.indexList[0].size(null, persistentStore);
    return arrayOfLong;
  }
  
  public void setIndexRoots(long[] paramArrayOflong) {
    if (!this.isCached)
      throw Error.error(5501, this.tableName.name); 
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    byte b1 = 0;
    for (byte b2 = 0; b2 < this.indexList.length; b2++)
      persistentStore.setAccessor(this.indexList[b2], paramArrayOflong[b1++]); 
    long l = paramArrayOflong[this.indexList.length * 2];
    for (byte b3 = 0; b3 < this.indexList.length; b3++)
      persistentStore.setElementCount(this.indexList[b3], l, paramArrayOflong[b1++]); 
  }
  
  void setIndexRoots(Session paramSession, String paramString) {
    if (!this.isCached)
      throw Error.error(5501, this.tableName.name); 
    ParserDQL parserDQL = new ParserDQL(paramSession, new Scanner(paramString), null);
    long[] arrayOfLong = new long[getIndexCount() * 2 + 1];
    parserDQL.read();
    int i = 0;
    byte b;
    for (b = 0; b < getIndexCount(); b++) {
      long l = parserDQL.readBigint();
      arrayOfLong[i++] = l;
    } 
    try {
      for (b = 0; b < getIndexCount() + 1; b++) {
        long l = parserDQL.readBigint();
        arrayOfLong[i++] = l;
      } 
    } catch (Exception exception) {
      for (i = getIndexCount(); i < arrayOfLong.length; i++)
        arrayOfLong[i] = -1L; 
    } 
    setIndexRoots(arrayOfLong);
  }
  
  Row insertSingleRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfint) {
    if (this.identityColumn != -1)
      setIdentityColumn(paramSession, paramArrayOfObject); 
    if (this.hasGeneratedValues)
      setGeneratedColumns(paramSession, paramArrayOfObject); 
    enforceTypeLimits(paramSession, paramArrayOfObject);
    if (this.hasDomainColumns || this.hasNotNullColumns)
      enforceRowConstraints(paramSession, paramArrayOfObject); 
    if (this.isView)
      return null; 
    Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, true);
    paramSession.addInsertAction(this, paramPersistentStore, row, paramArrayOfint);
    return row;
  }
  
  void insertIntoTable(Session paramSession, Result paramResult) {
    PersistentStore persistentStore = getRowStore(paramSession);
    RowSetNavigator rowSetNavigator = paramResult.initialiseNavigator();
    while (rowSetNavigator.hasNext()) {
      Object[] arrayOfObject1 = rowSetNavigator.getNext();
      Object[] arrayOfObject2 = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject1, this.columnCount);
      insertData(paramSession, persistentStore, arrayOfObject2);
    } 
  }
  
  public void insertNoCheckFromLog(Session paramSession, Object[] paramArrayOfObject) {
    systemUpdateIdentityValue(paramArrayOfObject);
    PersistentStore persistentStore = getRowStore(paramSession);
    Row row = (Row)persistentStore.getNewCachedObject(paramSession, paramArrayOfObject, true);
    paramSession.addInsertAction(this, persistentStore, row, null);
  }
  
  public int insertSys(Session paramSession, PersistentStore paramPersistentStore, Result paramResult) {
    RowSetNavigator rowSetNavigator = paramResult.getNavigator();
    byte b;
    for (b = 0; rowSetNavigator.hasNext(); b++)
      insertSys(paramSession, paramPersistentStore, rowSetNavigator.getNext()); 
    return b;
  }
  
  void insertResult(Session paramSession, PersistentStore paramPersistentStore, Result paramResult) {
    RowSetNavigator rowSetNavigator = paramResult.initialiseNavigator();
    while (rowSetNavigator.hasNext()) {
      Object[] arrayOfObject1 = rowSetNavigator.getNext();
      Object[] arrayOfObject2 = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject1, this.columnCount);
      insertData(paramSession, paramPersistentStore, arrayOfObject2);
    } 
  }
  
  public void insertFromScript(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    systemUpdateIdentityValue(paramArrayOfObject);
    if (paramSession.database.getProperties().isVersion18())
      for (byte b = 0; b < this.columnCount; b++) {
        if (paramArrayOfObject[b] != null && (this.colTypes[b].isCharacterType() || this.colTypes[b].isBinaryType())) {
          int i;
          if (paramArrayOfObject[b] instanceof String) {
            i = ((String)paramArrayOfObject[b]).length();
          } else if (paramArrayOfObject[b] instanceof BinaryData) {
            i = (int)((BinaryData)paramArrayOfObject[b]).length(paramSession);
          } else {
            throw Error.runtimeError(1200, "Table");
          } 
          if (i > (this.colTypes[b]).precision) {
            i = (i / 10 + 1) * 10;
            this.colTypes[b] = Type.getType((this.colTypes[b]).typeCode, this.colTypes[b].getCharacterSet(), this.colTypes[b].getCollation(), i, 0);
            ColumnSchema columnSchema = getColumn(b);
            columnSchema.setType(this.colTypes[b]);
          } 
        } 
      }  
    insertData(paramSession, paramPersistentStore, paramArrayOfObject);
  }
  
  public void insertData(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, false);
    paramPersistentStore.indexRow(paramSession, row);
  }
  
  public void insertSys(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, false);
    paramPersistentStore.indexRow(paramSession, row);
  }
  
  protected void setIdentityColumn(Session paramSession, Object[] paramArrayOfObject) {
    if (this.identityColumn != -1) {
      Number number = (Number)paramArrayOfObject[this.identityColumn];
      if (this.identitySequence.getName() == null) {
        if (number == null) {
          number = (Number)this.identitySequence.getValueObject();
          paramArrayOfObject[this.identityColumn] = number;
        } else {
          this.identitySequence.userUpdate(number.longValue());
        } 
      } else if (number == null) {
        number = (Number)paramSession.sessionData.getSequenceValue(this.identitySequence);
        paramArrayOfObject[this.identityColumn] = number;
      } 
      if (paramSession != null)
        paramSession.setLastIdentity(number); 
    } 
  }
  
  public void setGeneratedColumns(Session paramSession, Object[] paramArrayOfObject) {
    if (this.hasGeneratedValues)
      for (byte b = 0; b < this.colGenerated.length; b++) {
        if (this.colGenerated[b]) {
          Expression expression = getColumn(b).getGeneratingExpression();
          RangeVariable.RangeIteratorBase rangeIteratorBase = paramSession.sessionContext.getCheckIterator(getDefaultRanges()[0]);
          rangeIteratorBase.currentData = paramArrayOfObject;
          paramArrayOfObject[b] = expression.getValue(paramSession, this.colTypes[b]);
        } 
      }  
  }
  
  public void systemSetIdentityColumn(Session paramSession, Object[] paramArrayOfObject) {
    if (this.identityColumn != -1) {
      Number number = (Number)paramArrayOfObject[this.identityColumn];
      if (number == null) {
        number = (Number)this.identitySequence.getValueObject();
        paramArrayOfObject[this.identityColumn] = number;
      } else {
        this.identitySequence.userUpdate(number.longValue());
      } 
    } 
  }
  
  protected void systemUpdateIdentityValue(Object[] paramArrayOfObject) {
    if (this.identityColumn != -1) {
      Number number = (Number)paramArrayOfObject[this.identityColumn];
      if (number != null)
        this.identitySequence.systemUpdate(number.longValue()); 
    } 
  }
  
  public Row getDeleteRowFromLog(Session paramSession, Object[] paramArrayOfObject) {
    Row row = null;
    PersistentStore persistentStore = getRowStore(paramSession);
    if (hasPrimaryKey()) {
      RowIterator rowIterator = getPrimaryIndex().findFirstRow(paramSession, persistentStore, paramArrayOfObject, this.primaryKeyColsSequence);
      row = rowIterator.getNextRow();
      rowIterator.release();
    } else if (this.bestIndex == null) {
      RowIterator rowIterator = rowIterator(paramSession);
      do {
        row = rowIterator.getNextRow();
      } while (row != null && compareRows(paramSession, row.getData(), paramArrayOfObject, this.defaultColumnMap, this.colTypes) != 0);
      rowIterator.release();
    } else {
      Object[] arrayOfObject;
      RowIterator rowIterator = this.bestIndex.findFirstRow(paramSession, persistentStore, paramArrayOfObject);
      do {
        row = rowIterator.getNextRow();
        if (row == null)
          break; 
        arrayOfObject = row.getData();
        if (this.bestIndex.compareRowNonUnique(paramSession, arrayOfObject, paramArrayOfObject, this.bestIndex.getColumns()) != 0) {
          row = null;
          break;
        } 
      } while (compareRows(paramSession, arrayOfObject, paramArrayOfObject, this.defaultColumnMap, this.colTypes) != 0);
      rowIterator.release();
    } 
    return row;
  }
  
  public RowIterator rowIteratorClustered(Session paramSession) {
    PersistentStore persistentStore = getRowStore(paramSession);
    Index index = getClusteredIndex();
    if (index == null)
      index = getPrimaryIndex(); 
    return index.firstRow(paramSession, persistentStore, 0);
  }
  
  public RowIterator rowIteratorClustered(PersistentStore paramPersistentStore) {
    Index index = getClusteredIndex();
    if (index == null)
      index = getPrimaryIndex(); 
    return index.firstRow(paramPersistentStore);
  }
  
  public void clearAllData(Session paramSession) {
    super.clearAllData(paramSession);
    if (this.identitySequence != null)
      this.identitySequence.reset(); 
  }
  
  public void clearAllData(PersistentStore paramPersistentStore) {
    super.clearAllData(paramPersistentStore);
    if (this.identitySequence != null)
      this.identitySequence.reset(); 
  }
  
  public PersistentStore getRowStore(Session paramSession) {
    return (this.store != null) ? this.store : (this.isSessionBased ? paramSession.sessionData.persistentStoreCollection.getStore(this) : this.database.persistentStoreCollection.getStore(this));
  }
  
  public QueryExpression getQueryExpression() {
    return null;
  }
  
  public Expression getDataExpression() {
    return null;
  }
  
  public void prepareTable() {}
  
  public void materialise(Session paramSession) {}
  
  public void materialiseCorrelated(Session paramSession) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Table.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */