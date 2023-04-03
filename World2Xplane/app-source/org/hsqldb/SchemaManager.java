package org.hsqldb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public class SchemaManager {
  Database database;
  
  HsqlNameManager.HsqlName defaultSchemaHsqlName;
  
  HashMappedList schemaMap = new HashMappedList();
  
  MultiValueHashMap referenceMap = new MultiValueHashMap();
  
  int defaultTableType = 4;
  
  long schemaChangeTimestamp;
  
  HsqlNameManager.HsqlName[] catalogNameArray;
  
  ReadWriteLock lock = new ReentrantReadWriteLock();
  
  Lock readLock = this.lock.readLock();
  
  Lock writeLock = this.lock.writeLock();
  
  Table dualTable;
  
  public Table dataChangeTable;
  
  long[][] tempIndexRoots;
  
  public SchemaManager(Database paramDatabase) {
    this.database = paramDatabase;
    this.defaultSchemaHsqlName = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    this.catalogNameArray = new HsqlNameManager.HsqlName[] { paramDatabase.getCatalogName() };
    Schema schema = new Schema(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.owner);
    this.schemaMap.put((schema.getName()).name, schema);
    try {
      schema.charsetLookup.add((SchemaObject)Charset.SQL_TEXT);
      schema.charsetLookup.add((SchemaObject)Charset.SQL_IDENTIFIER_CHARSET);
      schema.charsetLookup.add((SchemaObject)Charset.SQL_CHARACTER);
      schema.collationLookup.add((SchemaObject)Collation.getDefaultInstance());
      schema.collationLookup.add((SchemaObject)Collation.getDefaultIgnoreCaseInstance());
      schema.typeLookup.add((SchemaObject)TypeInvariants.CARDINAL_NUMBER);
      schema.typeLookup.add((SchemaObject)TypeInvariants.YES_OR_NO);
      schema.typeLookup.add((SchemaObject)TypeInvariants.CHARACTER_DATA);
      schema.typeLookup.add((SchemaObject)TypeInvariants.SQL_IDENTIFIER);
      schema.typeLookup.add((SchemaObject)TypeInvariants.TIME_STAMP);
    } catch (HsqlException hsqlException) {}
  }
  
  public void setSchemaChangeTimestamp() {
    this.schemaChangeTimestamp = this.database.txManager.getGlobalChangeTimestamp();
  }
  
  public long getSchemaChangeTimestamp() {
    return this.schemaChangeTimestamp;
  }
  
  public HsqlNameManager.HsqlName getSQLJSchemaHsqlName() {
    return SqlInvariants.SQLJ_SCHEMA_HSQLNAME;
  }
  
  public void createPublicSchema() {
    this.writeLock.lock();
    try {
      HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName((HsqlNameManager.HsqlName)null, "PUBLIC", 2);
      Schema schema = new Schema(hsqlName, this.database.getGranteeManager().getDBARole());
      this.defaultSchemaHsqlName = schema.getName();
      this.schemaMap.put((schema.getName()).name, schema);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void createSchema(HsqlNameManager.HsqlName paramHsqlName, Grantee paramGrantee) {
    this.writeLock.lock();
    try {
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName.name);
      Schema schema = new Schema(paramHsqlName, paramGrantee);
      this.schemaMap.add(paramHsqlName.name, schema);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void dropSchema(Session paramSession, String paramString, boolean paramBoolean) {
    this.writeLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      if (schema == null)
        throw Error.error(5501, paramString); 
      if (SqlInvariants.isLobsSchemaName(paramString))
        throw Error.error(5503, paramString); 
      if (!paramBoolean && !schema.isEmpty())
        throw Error.error(4200); 
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      getCascadingReferencesToSchema(schema.getName(), orderedHashSet);
      removeSchemaObjects(orderedHashSet);
      Iterator iterator1 = schema.schemaObjectIterator(3);
      while (iterator1.hasNext()) {
        Table table = (Table)iterator1.next();
        Constraint[] arrayOfConstraint = table.getFKConstraints();
        for (byte b = 0; b < arrayOfConstraint.length; b++) {
          Constraint constraint = arrayOfConstraint[b];
          if (constraint.getMain().getSchemaName() != schema.getName()) {
            constraint.getMain().removeConstraint((constraint.getMainName()).name);
            removeReferencesFrom(constraint);
          } 
        } 
        removeTable(paramSession, table);
      } 
      Iterator iterator2 = schema.schemaObjectIterator(7);
      while (iterator2.hasNext()) {
        NumberSequence numberSequence = (NumberSequence)iterator2.next();
        this.database.getGranteeManager().removeDbObject(numberSequence.getName());
      } 
      schema.release();
      this.schemaMap.remove(paramString);
      if (this.defaultSchemaHsqlName.name.equals(paramString)) {
        HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newHsqlName(paramString, false, 2);
        schema = new Schema(hsqlName, this.database.getGranteeManager().getDBARole());
        this.defaultSchemaHsqlName = schema.getName();
        this.schemaMap.put((schema.getName()).name, schema);
      } 
      this.database.getUserManager().removeSchemaReference(paramString);
      this.database.getSessionManager().removeSchemaReference(schema);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void renameSchema(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    this.writeLock.lock();
    try {
      Schema schema1 = (Schema)this.schemaMap.get(paramHsqlName1.name);
      Schema schema2 = (Schema)this.schemaMap.get(paramHsqlName2.name);
      if (schema1 == null)
        throw Error.error(5501, paramHsqlName1.name); 
      if (schema2 != null)
        throw Error.error(5504, paramHsqlName2.name); 
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName1.name);
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName2.name);
      int i = this.schemaMap.getIndex(paramHsqlName1.name);
      schema1.getName().rename(paramHsqlName2);
      this.schemaMap.set(i, paramHsqlName2.name, schema1);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void release() {
    this.writeLock.lock();
    try {
      Iterator iterator = this.schemaMap.values().iterator();
      while (iterator.hasNext()) {
        Schema schema = (Schema)iterator.next();
        schema.release();
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public String[] getSchemaNamesArray() {
    this.readLock.lock();
    try {
      String[] arrayOfString = new String[this.schemaMap.size()];
      this.schemaMap.toKeysArray((Object[])arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Schema[] getAllSchemas() {
    this.readLock.lock();
    try {
      Schema[] arrayOfSchema = new Schema[this.schemaMap.size()];
      this.schemaMap.toValuesArray((Object[])arrayOfSchema);
      return arrayOfSchema;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName getUserSchemaHsqlName(String paramString) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      if (schema == null)
        throw Error.error(4850, paramString); 
      if (schema.getName() == SqlInvariants.INFORMATION_SCHEMA_HSQLNAME)
        throw Error.error(4850, paramString); 
      return schema.getName();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Grantee toSchemaOwner(String paramString) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      return (schema == null) ? null : schema.getOwner();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName getDefaultSchemaHsqlName() {
    return this.defaultSchemaHsqlName;
  }
  
  public void setDefaultSchemaHsqlName(HsqlNameManager.HsqlName paramHsqlName) {
    this.defaultSchemaHsqlName = paramHsqlName;
  }
  
  public boolean schemaExists(String paramString) {
    this.readLock.lock();
    try {
      return this.schemaMap.containsKey(paramString);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName findSchemaHsqlName(String paramString) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      if (schema == null)
        return null; 
      return schema.getName();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName getSchemaHsqlName(String paramString) {
    if (paramString == null)
      return this.defaultSchemaHsqlName; 
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      if (schema == null)
        throw Error.error(4850, paramString); 
      return schema.getName();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public String getSchemaName(String paramString) {
    return (getSchemaHsqlName(paramString)).name;
  }
  
  public Schema findSchema(String paramString) {
    this.readLock.lock();
    try {
      return (Schema)this.schemaMap.get(paramString);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void dropSchemas(Session paramSession, Grantee paramGrantee, boolean paramBoolean) {
    this.writeLock.lock();
    try {
      HsqlArrayList hsqlArrayList = getSchemas(paramGrantee);
      Iterator iterator = hsqlArrayList.iterator();
      while (iterator.hasNext()) {
        Schema schema = (Schema)iterator.next();
        dropSchema(paramSession, (schema.getName()).name, paramBoolean);
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public HsqlArrayList getSchemas(Grantee paramGrantee) {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      Iterator iterator = this.schemaMap.values().iterator();
      while (iterator.hasNext()) {
        Schema schema = (Schema)iterator.next();
        if (paramGrantee.equals(schema.getOwner()))
          hsqlArrayList.add(schema); 
      } 
      return hsqlArrayList;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public boolean hasSchemas(Grantee paramGrantee) {
    this.readLock.lock();
    try {
      Iterator iterator = this.schemaMap.values().iterator();
      while (iterator.hasNext()) {
        Schema schema = (Schema)iterator.next();
        if (paramGrantee.equals(schema.getOwner()))
          return true; 
      } 
      return false;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlArrayList getAllTables(boolean paramBoolean) {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      String[] arrayOfString = getSchemaNamesArray();
      for (byte b = 0; b < arrayOfString.length; b++) {
        String str = arrayOfString[b];
        if ((paramBoolean || !SqlInvariants.isLobsSchemaName(str)) && !SqlInvariants.isSystemSchemaName(str)) {
          HashMappedList hashMappedList = getTables(str);
          hsqlArrayList.addAll(hashMappedList.values());
        } 
      } 
      return hsqlArrayList;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HashMappedList getTables(String paramString) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      return schema.tableList;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName[] getCatalogNameArray() {
    return this.catalogNameArray;
  }
  
  public HsqlNameManager.HsqlName[] getCatalogAndBaseTableNames() {
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      HsqlArrayList hsqlArrayList = getAllTables(false);
      for (byte b = 0; b < hsqlArrayList.size(); b++) {
        Table table = (Table)hsqlArrayList.get(b);
        if (!table.isTemp())
          orderedHashSet.add(table.getName()); 
      } 
      orderedHashSet.add(this.database.getCatalogName());
      HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[orderedHashSet.size()];
      orderedHashSet.toArray((Object[])arrayOfHsqlName);
      return arrayOfHsqlName;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName[] getCatalogAndBaseTableNames(HsqlNameManager.HsqlName paramHsqlName) {
    if (paramHsqlName == null)
      return this.catalogNameArray; 
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet1;
      HsqlNameManager.HsqlName[] arrayOfHsqlName1;
      HashMappedList hashMappedList;
      byte b1;
      HsqlNameManager.HsqlName[] arrayOfHsqlName2;
      HsqlNameManager.HsqlName[] arrayOfHsqlName3;
      switch (paramHsqlName.type) {
        case 2:
          if (findSchemaHsqlName(paramHsqlName.name) == null)
            return this.catalogNameArray; 
          orderedHashSet1 = new OrderedHashSet();
          orderedHashSet1.add(this.database.getCatalogName());
          hashMappedList = getTables(paramHsqlName.name);
          for (b1 = 0; b1 < hashMappedList.size(); b1++)
            orderedHashSet1.add(((SchemaObject)hashMappedList.get(b1)).getName()); 
          arrayOfHsqlName2 = new HsqlNameManager.HsqlName[orderedHashSet1.size()];
          orderedHashSet1.toArray((Object[])arrayOfHsqlName2);
          return arrayOfHsqlName2;
        case 11:
          return this.catalogNameArray;
        case 5:
        case 20:
          findSchemaObject(paramHsqlName.name, paramHsqlName.schema.name, paramHsqlName.type);
          break;
      } 
      SchemaObject schemaObject = findSchemaObject(paramHsqlName.name, paramHsqlName.schema.name, paramHsqlName.type);
      if (schemaObject == null)
        return this.catalogNameArray; 
      HsqlNameManager.HsqlName hsqlName = (schemaObject.getName()).parent;
      OrderedHashSet orderedHashSet2 = getReferencesTo(schemaObject.getName());
      OrderedHashSet orderedHashSet3 = new OrderedHashSet();
      orderedHashSet3.add(this.database.getCatalogName());
      if (hsqlName != null) {
        SchemaObject schemaObject1 = findSchemaObject(hsqlName.name, hsqlName.schema.name, hsqlName.type);
        if (schemaObject1 != null && (schemaObject1.getName()).type == 3)
          orderedHashSet3.add(schemaObject1.getName()); 
      } 
      if ((schemaObject.getName()).type == 3)
        orderedHashSet3.add(schemaObject.getName()); 
      for (byte b2 = 0; b2 < orderedHashSet2.size(); b2++) {
        HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet2.get(b2);
        if (hsqlName1.type == 3) {
          Table table = findUserTable(null, hsqlName1.name, hsqlName1.schema.name);
          if (table != null && !table.isTemp())
            orderedHashSet3.add(hsqlName1); 
        } 
      } 
      HsqlNameManager.HsqlName[] arrayOfHsqlName4 = new HsqlNameManager.HsqlName[orderedHashSet3.size()];
      orderedHashSet3.toArray((Object[])arrayOfHsqlName4);
      return arrayOfHsqlName4;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  private SchemaObjectSet getSchemaObjectSet(Schema paramSchema, int paramInt) {
    this.readLock.lock();
    try {
      SchemaObjectSet schemaObjectSet = null;
      switch (paramInt) {
        case 7:
          schemaObjectSet = paramSchema.sequenceLookup;
          break;
        case 3:
        case 4:
          schemaObjectSet = paramSchema.tableLookup;
          break;
        case 14:
          schemaObjectSet = paramSchema.charsetLookup;
          break;
        case 15:
          schemaObjectSet = paramSchema.collationLookup;
          break;
        case 17:
          schemaObjectSet = paramSchema.procedureLookup;
          break;
        case 16:
          schemaObjectSet = paramSchema.functionLookup;
          break;
        case 12:
        case 13:
          schemaObjectSet = paramSchema.typeLookup;
          break;
        case 20:
          schemaObjectSet = paramSchema.indexLookup;
          break;
        case 5:
          schemaObjectSet = paramSchema.constraintLookup;
          break;
        case 8:
          schemaObjectSet = paramSchema.triggerLookup;
          break;
        case 24:
          schemaObjectSet = paramSchema.specificRoutineLookup;
          break;
      } 
      return schemaObjectSet;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void checkSchemaObjectNotExists(HsqlNameManager.HsqlName paramHsqlName) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      SchemaObjectSet schemaObjectSet = getSchemaObjectSet(schema, paramHsqlName.type);
      schemaObjectSet.checkAdd(paramHsqlName);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Table getTable(Session paramSession, String paramString1, String paramString2) {
    this.readLock.lock();
    try {
      Table table = null;
      if ("MODULE".equals(paramString2) || "SESSION".equals(paramString2)) {
        table = findSessionTable(paramSession, paramString1);
        if (table == null)
          throw Error.error(5501, paramString1); 
        return table;
      } 
      if (paramString2 == null) {
        if ((paramSession.database.sqlSyntaxOra || paramSession.database.sqlSyntaxDb2) && "DUAL".equals(paramString1))
          return this.dualTable; 
        table = findSessionTable(paramSession, paramString1);
      } 
      if (table == null) {
        paramString2 = paramSession.getSchemaName(paramString2);
        table = findUserTable(paramSession, paramString1, paramString2);
      } 
      if (table == null && "INFORMATION_SCHEMA".equals(paramString2) && this.database.dbInfo != null)
        table = this.database.dbInfo.getSystemTable(paramSession, paramString1); 
      if (table == null)
        throw Error.error(5501, paramString1); 
      return table;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Table getUserTable(Session paramSession, HsqlNameManager.HsqlName paramHsqlName) {
    return getUserTable(paramSession, paramHsqlName.name, paramHsqlName.schema.name);
  }
  
  public Table getUserTable(Session paramSession, String paramString1, String paramString2) {
    Table table = findUserTable(paramSession, paramString1, paramString2);
    if (table == null) {
      String str = (paramString2 == null) ? paramString1 : (paramString2 + '.' + paramString1);
      throw Error.error(5501, str);
    } 
    return table;
  }
  
  public Table findUserTable(Session paramSession, String paramString1, String paramString2) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema == null)
        return null; 
      int i = schema.tableList.getIndex(paramString1);
      if (i == -1)
        return null; 
      return (Table)schema.tableList.get(i);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Table findSessionTable(Session paramSession, String paramString) {
    return paramSession.sessionContext.findSessionTable(paramString);
  }
  
  public void dropTableOrView(Session paramSession, Table paramTable, boolean paramBoolean) {
    this.writeLock.lock();
    try {
      if (paramTable.isView()) {
        dropView(paramTable, paramBoolean);
      } else {
        dropTable(paramSession, paramTable, paramBoolean);
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private void dropView(Table paramTable, boolean paramBoolean) {
    Schema schema = (Schema)this.schemaMap.get((paramTable.getSchemaName()).name);
    removeSchemaObject(paramTable.getName(), paramBoolean);
    schema.triggerLookup.removeParent(paramTable.getName());
  }
  
  private void dropTable(Session paramSession, Table paramTable, boolean paramBoolean) {
    Schema schema = (Schema)this.schemaMap.get((paramTable.getSchemaName()).name);
    int i = schema.tableList.getIndex((paramTable.getName()).name);
    OrderedHashSet orderedHashSet1 = paramTable.getDependentExternalConstraints();
    OrderedHashSet orderedHashSet2 = new OrderedHashSet();
    getCascadingReferencesTo(paramTable.getName(), orderedHashSet2);
    if (!paramBoolean) {
      byte b1;
      for (b1 = 0; b1 < orderedHashSet1.size(); b1++) {
        Constraint constraint = (Constraint)orderedHashSet1.get(b1);
        HsqlNameManager.HsqlName hsqlName = constraint.getRefName();
        if (constraint.getConstraintType() == 1)
          throw Error.error(5533, hsqlName.getSchemaQualifiedStatementName()); 
      } 
      if (!orderedHashSet2.isEmpty()) {
        b1 = 0;
        while (b1 < orderedHashSet2.size()) {
          HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet2.get(b1);
          if (hsqlName.parent == paramTable.getName()) {
            b1++;
            continue;
          } 
          throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
        } 
      } 
    } 
    OrderedHashSet orderedHashSet3 = new OrderedHashSet();
    OrderedHashSet orderedHashSet4 = new OrderedHashSet();
    OrderedHashSet orderedHashSet5 = new OrderedHashSet();
    for (byte b = 0; b < orderedHashSet1.size(); b++) {
      Constraint constraint = (Constraint)orderedHashSet1.get(b);
      Table table = constraint.getMain();
      if (table != paramTable)
        orderedHashSet3.add(table); 
      table = constraint.getRef();
      if (table != paramTable)
        orderedHashSet3.add(table); 
      orderedHashSet4.add(constraint.getMainName());
      orderedHashSet4.add(constraint.getRefName());
      orderedHashSet5.add(constraint.getRefIndex().getName());
    } 
    OrderedHashSet orderedHashSet6 = paramTable.getUniquePKConstraintNames();
    TableWorks tableWorks = new TableWorks(paramSession, paramTable);
    orderedHashSet3 = tableWorks.makeNewTables(orderedHashSet3, orderedHashSet4, orderedHashSet5);
    tableWorks.setNewTablesInSchema(orderedHashSet3);
    tableWorks.updateConstraints(orderedHashSet3, orderedHashSet4);
    removeSchemaObjects(orderedHashSet2);
    removeTableDependentReferences(paramTable);
    removeReferencesTo(orderedHashSet6);
    removeReferencesTo(paramTable.getName());
    removeReferencesFrom(paramTable);
    schema.tableList.remove(i);
    schema.indexLookup.removeParent(paramTable.getName());
    schema.constraintLookup.removeParent(paramTable.getName());
    schema.triggerLookup.removeParent(paramTable.getName());
    removeTable(paramSession, paramTable);
    recompileDependentObjects(orderedHashSet3);
  }
  
  private void removeTable(Session paramSession, Table paramTable) {
    this.database.getGranteeManager().removeDbObject(paramTable.getName());
    paramTable.releaseTriggers();
    if (!paramTable.isView() && paramTable.hasLobColumn()) {
      RowIterator rowIterator = paramTable.rowIterator(paramSession);
      while (rowIterator.hasNext()) {
        Row row = rowIterator.getNextRow();
        Object[] arrayOfObject = row.getData();
        paramSession.sessionData.adjustLobUsageCount(paramTable, arrayOfObject, -1);
      } 
    } 
    if (paramTable.tableType == 3) {
      Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
      for (byte b = 0; b < arrayOfSession.length; b++)
        (arrayOfSession[b]).sessionData.persistentStoreCollection.setStore(paramTable, null); 
    } else {
      this.database.persistentStoreCollection.releaseStore(paramTable);
    } 
  }
  
  public void setTable(int paramInt, Table paramTable) {
    this.writeLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get((paramTable.getSchemaName()).name);
      schema.tableList.set(paramInt, (paramTable.getName()).name, paramTable);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int getTableIndex(Table paramTable) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get((paramTable.getSchemaName()).name);
      if (schema == null)
        return -1; 
      HsqlNameManager.HsqlName hsqlName = paramTable.getName();
      return schema.tableList.getIndex(hsqlName.name);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void recompileDependentObjects(OrderedHashSet paramOrderedHashSet) {
    this.writeLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      for (byte b1 = 0; b1 < paramOrderedHashSet.size(); b1++) {
        Table table = (Table)paramOrderedHashSet.get(b1);
        orderedHashSet.addAll((Collection)getReferencesTo(table.getName()));
      } 
      Session session = this.database.sessionManager.getSysSession();
      for (byte b2 = 0; b2 < orderedHashSet.size(); b2++) {
        SchemaObject schemaObject;
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b2);
        switch (hsqlName.type) {
          case 4:
          case 5:
          case 6:
          case 16:
          case 17:
          case 18:
          case 24:
            schemaObject = getSchemaObject(hsqlName);
            schemaObject.compile(session, null);
            break;
        } 
      } 
      if (Error.TRACE) {
        HsqlArrayList hsqlArrayList = getAllTables(false);
        for (byte b = 0; b < hsqlArrayList.size(); b++) {
          Table table = (Table)hsqlArrayList.get(b);
          table.verifyConstraintsIntegrity();
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void recompileDependentObjects(Table paramTable) {
    this.writeLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      getCascadingReferencesTo(paramTable.getName(), orderedHashSet);
      Session session = this.database.sessionManager.getSysSession();
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        SchemaObject schemaObject;
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        switch (hsqlName.type) {
          case 4:
          case 5:
          case 6:
          case 16:
          case 17:
          case 18:
          case 24:
            schemaObject = getSchemaObject(hsqlName);
            schemaObject.compile(session, null);
            break;
        } 
      } 
      if (Error.TRACE) {
        HsqlArrayList hsqlArrayList = getAllTables(false);
        for (byte b1 = 0; b1 < hsqlArrayList.size(); b1++) {
          Table table = (Table)hsqlArrayList.get(b1);
          table.verifyConstraintsIntegrity();
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Collation getCollation(Session paramSession, String paramString1, String paramString2) {
    Collation collation = null;
    if (paramString2 == null || "INFORMATION_SCHEMA".equals(paramString2))
      try {
        collation = Collation.getCollation(paramString1);
      } catch (HsqlException hsqlException) {} 
    if (collation == null) {
      paramString2 = paramSession.getSchemaName(paramString2);
      collation = (Collation)getSchemaObject(paramString1, paramString2, 15);
    } 
    return collation;
  }
  
  public NumberSequence getSequence(String paramString1, String paramString2, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema != null) {
        NumberSequence numberSequence = (NumberSequence)schema.sequenceList.get(paramString1);
        if (numberSequence != null)
          return numberSequence; 
      } 
      if (paramBoolean)
        throw Error.error(5501, paramString1); 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Type getUserDefinedType(String paramString1, String paramString2, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema != null) {
        SchemaObject schemaObject = schema.typeLookup.getObject(paramString1);
        if (schemaObject != null)
          return (Type)schemaObject; 
      } 
      if (paramBoolean)
        throw Error.error(5501, paramString1); 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Type getDomainOrUDT(String paramString1, String paramString2, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema != null) {
        SchemaObject schemaObject = schema.typeLookup.getObject(paramString1);
        if (schemaObject != null)
          return (Type)schemaObject; 
      } 
      if (paramBoolean)
        throw Error.error(5501, paramString1); 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Type getDomain(String paramString1, String paramString2, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema != null) {
        SchemaObject schemaObject = schema.typeLookup.getObject(paramString1);
        if (schemaObject != null && ((Type)schemaObject).isDomainType())
          return (Type)schemaObject; 
      } 
      if (paramBoolean)
        throw Error.error(5501, paramString1); 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Type getDistinctType(String paramString1, String paramString2, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema != null) {
        SchemaObject schemaObject = schema.typeLookup.getObject(paramString1);
        if (schemaObject != null && ((Type)schemaObject).isDistinctType())
          return (Type)schemaObject; 
      } 
      if (paramBoolean)
        throw Error.error(5501, paramString1); 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public SchemaObject getSchemaObject(String paramString1, String paramString2, int paramInt) {
    this.readLock.lock();
    try {
      SchemaObject schemaObject = findSchemaObject(paramString1, paramString2, paramInt);
      if (schemaObject == null)
        throw Error.error(SchemaObjectSet.getGetErrorCode(paramInt), paramString1); 
      return schemaObject;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public SchemaObject getCharacterSet(Session paramSession, String paramString1, String paramString2) {
    if (paramString2 == null || "INFORMATION_SCHEMA".equals(paramString2)) {
      if (paramString1.equals("SQL_IDENTIFIER"))
        return (SchemaObject)Charset.SQL_IDENTIFIER_CHARSET; 
      if (paramString1.equals("SQL_TEXT"))
        return (SchemaObject)Charset.SQL_TEXT; 
      if (paramString1.equals("LATIN1"))
        return (SchemaObject)Charset.LATIN1; 
      if (paramString1.equals("ASCII_GRAPHIC"))
        return (SchemaObject)Charset.ASCII_GRAPHIC; 
    } 
    if (paramString2 == null)
      paramString2 = paramSession.getSchemaName(paramString2); 
    return getSchemaObject(paramString1, paramString2, 14);
  }
  
  public SchemaObject findSchemaObject(String paramString1, String paramString2, int paramInt) {
    this.readLock.lock();
    try {
      HsqlNameManager.HsqlName hsqlName;
      Table table;
      SchemaObject schemaObject;
      Index index;
      Constraint constraint;
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      if (schema == null)
        return null; 
      SchemaObjectSet schemaObjectSet = null;
      switch (paramInt) {
        case 7:
          schemaObject = schema.sequenceLookup.getObject(paramString1);
          return schemaObject;
        case 3:
        case 4:
          schemaObject = schema.tableLookup.getObject(paramString1);
          return schemaObject;
        case 14:
          schemaObject = schema.charsetLookup.getObject(paramString1);
          return schemaObject;
        case 15:
          schemaObject = schema.collationLookup.getObject(paramString1);
          return schemaObject;
        case 17:
          schemaObject = schema.procedureLookup.getObject(paramString1);
          return schemaObject;
        case 16:
          schemaObject = schema.functionLookup.getObject(paramString1);
          return schemaObject;
        case 18:
          schemaObject = schema.procedureLookup.getObject(paramString1);
          if (schemaObject == null)
            schemaObject = schema.functionLookup.getObject(paramString1); 
          return schemaObject;
        case 24:
          schemaObject = schema.specificRoutineLookup.getObject(paramString1);
          return schemaObject;
        case 12:
        case 13:
          schemaObject = schema.typeLookup.getObject(paramString1);
          return schemaObject;
        case 20:
          schemaObjectSet = schema.indexLookup;
          hsqlName = schemaObjectSet.getName(paramString1);
          if (hsqlName == null) {
            schemaObject = null;
            return schemaObject;
          } 
          table = (Table)schema.tableList.get(hsqlName.parent.name);
          index = table.getIndex(paramString1);
          return (SchemaObject)index;
        case 5:
          schemaObjectSet = schema.constraintLookup;
          hsqlName = schemaObjectSet.getName(paramString1);
          if (hsqlName == null) {
            index = null;
            return (SchemaObject)index;
          } 
          table = (Table)schema.tableList.get(hsqlName.parent.name);
          if (table == null) {
            index = null;
            return (SchemaObject)index;
          } 
          constraint = table.getConstraint(paramString1);
          return constraint;
        case 8:
          schemaObjectSet = schema.indexLookup;
          hsqlName = schemaObjectSet.getName(paramString1);
          if (hsqlName == null) {
            constraint = null;
            return constraint;
          } 
          table = (Table)schema.tableList.get(hsqlName.parent.name);
          return table.getTrigger(paramString1);
      } 
      throw Error.runtimeError(201, "SchemaManager");
    } finally {
      this.readLock.unlock();
    } 
  }
  
  Table findUserTableForIndex(Session paramSession, String paramString1, String paramString2) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString2);
      HsqlNameManager.HsqlName hsqlName = schema.indexLookup.getName(paramString1);
      if (hsqlName == null)
        return null; 
      return findUserTable(paramSession, hsqlName.parent.name, paramString2);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  void dropIndex(Session paramSession, HsqlNameManager.HsqlName paramHsqlName) {
    this.writeLock.lock();
    try {
      Table table = getTable(paramSession, paramHsqlName.parent.name, paramHsqlName.parent.schema.name);
      TableWorks tableWorks = new TableWorks(paramSession, table);
      tableWorks.dropIndex(paramHsqlName.name);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void dropConstraint(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    this.writeLock.lock();
    try {
      Table table = getTable(paramSession, paramHsqlName.parent.name, paramHsqlName.parent.schema.name);
      TableWorks tableWorks = new TableWorks(paramSession, table);
      tableWorks.dropConstraint(paramHsqlName.name, paramBoolean);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void removeDependentObjects(HsqlNameManager.HsqlName paramHsqlName) {
    this.writeLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      schema.indexLookup.removeParent(paramHsqlName);
      schema.constraintLookup.removeParent(paramHsqlName);
      schema.triggerLookup.removeParent(paramHsqlName);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  void removeExportedKeys(Table paramTable) {
    this.writeLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get((paramTable.getSchemaName()).name);
      for (byte b = 0; b < schema.tableList.size(); b++) {
        Table table = (Table)schema.tableList.get(b);
        Constraint[] arrayOfConstraint = table.getConstraints();
        for (int i = arrayOfConstraint.length - 1; i >= 0; i--) {
          Table table1 = arrayOfConstraint[i].getRef();
          if (paramTable == table1)
            table.removeConstraint(i); 
        } 
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Iterator databaseObjectIterator(String paramString, int paramInt) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramString);
      return schema.schemaObjectIterator(paramInt);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Iterator databaseObjectIterator(int paramInt) {
    this.readLock.lock();
    try {
      Iterator iterator = this.schemaMap.values().iterator();
      WrapperIterator wrapperIterator = new WrapperIterator();
      while (iterator.hasNext()) {
        int i = paramInt;
        if (paramInt == 18)
          i = 16; 
        Schema schema = (Schema)iterator.next();
        SchemaObjectSet schemaObjectSet = schema.getObjectSet(i);
        if (schemaObjectSet.map.size() != 0) {
          Object[] arrayOfObject = new Object[schemaObjectSet.map.size()];
          schemaObjectSet.map.valuesToArray(arrayOfObject);
          wrapperIterator = new WrapperIterator((Iterator)wrapperIterator, (Iterator)new WrapperIterator(arrayOfObject));
        } 
        if (paramInt == 18) {
          schemaObjectSet = schema.getObjectSet(17);
          if (schemaObjectSet.map.size() != 0) {
            Object[] arrayOfObject = new Object[schemaObjectSet.map.size()];
            schemaObjectSet.map.valuesToArray(arrayOfObject);
            wrapperIterator = new WrapperIterator((Iterator)wrapperIterator, (Iterator)new WrapperIterator(arrayOfObject));
          } 
        } 
      } 
      return (Iterator)wrapperIterator;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  private void addReferencesFrom(SchemaObject paramSchemaObject) {
    OrderedHashSet orderedHashSet = paramSchemaObject.getReferences();
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (orderedHashSet == null)
      return; 
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      if (paramSchemaObject instanceof Routine)
        hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
      this.referenceMap.put(hsqlName1, hsqlName);
    } 
  }
  
  private void removeReferencesTo(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(b);
      this.referenceMap.remove(hsqlName);
    } 
  }
  
  private void removeReferencesTo(HsqlNameManager.HsqlName paramHsqlName) {
    this.referenceMap.remove(paramHsqlName);
  }
  
  private void removeReferencesFrom(SchemaObject paramSchemaObject) {
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    OrderedHashSet orderedHashSet = paramSchemaObject.getReferences();
    if (orderedHashSet == null)
      return; 
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      if (paramSchemaObject instanceof Routine)
        hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
      this.referenceMap.remove(hsqlName1, hsqlName);
    } 
  }
  
  private void removeTableDependentReferences(Table paramTable) {
    OrderedHashSet orderedHashSet = paramTable.getReferencesForDependents();
    if (orderedHashSet == null)
      return; 
    byte b = 0;
    while (b < orderedHashSet.size()) {
      TriggerDef triggerDef;
      ColumnSchema columnSchema;
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      Constraint constraint = null;
      switch (hsqlName.type) {
        case 5:
          constraint = paramTable.getConstraint(hsqlName.name);
          break;
        case 8:
          triggerDef = paramTable.getTrigger(hsqlName.name);
          break;
        case 9:
          columnSchema = paramTable.getColumn(paramTable.getColumnIndex(hsqlName.name));
          break;
        default:
          b++;
          continue;
      } 
      removeReferencesFrom(columnSchema);
    } 
  }
  
  public OrderedHashSet getReferencesTo(HsqlNameManager.HsqlName paramHsqlName) {
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      Iterator iterator = this.referenceMap.get(paramHsqlName);
      while (iterator.hasNext()) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
        orderedHashSet.add(hsqlName);
      } 
      return orderedHashSet;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public OrderedHashSet getReferencesTo(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      Iterator iterator = this.referenceMap.get(paramHsqlName1);
      while (iterator.hasNext()) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
        SchemaObject schemaObject = getSchemaObject(hsqlName);
        OrderedHashSet orderedHashSet1 = schemaObject.getReferences();
        if (orderedHashSet1.contains(paramHsqlName2))
          orderedHashSet.add(hsqlName); 
      } 
      return orderedHashSet;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  private boolean isReferenced(HsqlNameManager.HsqlName paramHsqlName) {
    this.writeLock.lock();
    try {
      return this.referenceMap.containsKey(paramHsqlName);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void getCascadingReferencesTo(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet) {
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      Iterator iterator = this.referenceMap.get(paramHsqlName);
      while (iterator.hasNext()) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
        boolean bool = paramOrderedHashSet.add(hsqlName);
        if (bool)
          orderedHashSet.add(hsqlName); 
      } 
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        getCascadingReferencesTo(hsqlName, paramOrderedHashSet);
      } 
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void getCascadingReferencesToSchema(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet) {
    Iterator iterator = this.referenceMap.keySet().iterator();
    while (iterator.hasNext()) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
      if (hsqlName.schema != paramHsqlName)
        continue; 
      getCascadingReferencesTo(hsqlName, paramOrderedHashSet);
    } 
    for (int i = paramOrderedHashSet.size() - 1; i >= 0; i--) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(i);
      if (hsqlName.schema == paramHsqlName)
        paramOrderedHashSet.remove(i); 
    } 
  }
  
  public MultiValueHashMap getReferencesToSchema(String paramString) {
    MultiValueHashMap multiValueHashMap = new MultiValueHashMap();
    Iterator iterator = this.referenceMap.keySet().iterator();
    while (iterator.hasNext()) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
      if (!hsqlName.schema.name.equals(paramString))
        continue; 
      Iterator iterator1 = this.referenceMap.get(hsqlName);
      while (iterator1.hasNext())
        multiValueHashMap.put(hsqlName, iterator1.next()); 
    } 
    return multiValueHashMap;
  }
  
  public HsqlNameManager.HsqlName getSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, String paramString, int paramInt, boolean paramBoolean) {
    this.readLock.lock();
    try {
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName.name);
      SchemaObjectSet schemaObjectSet = null;
      if (schema == null) {
        if (paramBoolean)
          throw Error.error(SchemaObjectSet.getGetErrorCode(paramInt)); 
        return null;
      } 
      if (paramInt == 18) {
        schemaObjectSet = schema.functionLookup;
        SchemaObject schemaObject = schema.functionLookup.getObject(paramString);
        if (schemaObject == null) {
          schemaObjectSet = schema.procedureLookup;
          schemaObject = schema.procedureLookup.getObject(paramString);
        } 
      } else {
        schemaObjectSet = getSchemaObjectSet(schema, paramInt);
      } 
      if (paramBoolean)
        schemaObjectSet.checkExists(paramString); 
      return schemaObjectSet.getName(paramString);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public SchemaObject getSchemaObject(HsqlNameManager.HsqlName paramHsqlName) {
    this.readLock.lock();
    try {
      SchemaObject schemaObject2;
      HsqlNameManager.HsqlName hsqlName;
      Table table;
      TriggerDef triggerDef;
      Constraint constraint;
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      if (schema == null)
        return null; 
      switch (paramHsqlName.type) {
        case 7:
          schemaObject1 = (SchemaObject)schema.sequenceList.get(paramHsqlName.name);
          return schemaObject1;
        case 3:
        case 4:
          schemaObject1 = (SchemaObject)schema.tableList.get(paramHsqlName.name);
          return schemaObject1;
        case 14:
          schemaObject1 = schema.charsetLookup.getObject(paramHsqlName.name);
          return schemaObject1;
        case 15:
          schemaObject1 = schema.collationLookup.getObject(paramHsqlName.name);
          return schemaObject1;
        case 17:
          schemaObject1 = schema.procedureLookup.getObject(paramHsqlName.name);
          return schemaObject1;
        case 16:
          schemaObject1 = schema.functionLookup.getObject(paramHsqlName.name);
          return schemaObject1;
        case 24:
          schemaObject1 = schema.specificRoutineLookup.getObject(paramHsqlName.name);
          return schemaObject1;
        case 18:
          schemaObject1 = schema.functionLookup.getObject(paramHsqlName.name);
          if (schemaObject1 == null)
            schemaObject1 = schema.procedureLookup.getObject(paramHsqlName.name); 
          schemaObject2 = schemaObject1;
          return schemaObject2;
        case 12:
        case 13:
          schemaObject2 = schema.typeLookup.getObject(paramHsqlName.name);
          return schemaObject2;
        case 8:
          paramHsqlName = schema.triggerLookup.getName(paramHsqlName.name);
          if (paramHsqlName == null) {
            schemaObject2 = null;
            return schemaObject2;
          } 
          hsqlName = paramHsqlName.parent;
          table = (Table)schema.tableList.get(hsqlName.name);
          return table.getTrigger(paramHsqlName.name);
        case 5:
          paramHsqlName = schema.constraintLookup.getName(paramHsqlName.name);
          if (paramHsqlName == null) {
            hsqlName = null;
            return (SchemaObject)hsqlName;
          } 
          hsqlName = paramHsqlName.parent;
          table = (Table)schema.tableList.get(hsqlName.name);
          return table.getConstraint(paramHsqlName.name);
        case 6:
          hsqlName = null;
          return (SchemaObject)hsqlName;
        case 20:
          paramHsqlName = schema.indexLookup.getName(paramHsqlName.name);
          if (paramHsqlName == null) {
            hsqlName = null;
            return (SchemaObject)hsqlName;
          } 
          hsqlName = paramHsqlName.parent;
          table = (Table)schema.tableList.get(hsqlName.name);
          return (SchemaObject)table.getIndex(paramHsqlName.name);
      } 
      SchemaObject schemaObject1 = null;
      return schemaObject1;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void checkColumnIsReferenced(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    OrderedHashSet orderedHashSet = getReferencesTo(paramHsqlName1, paramHsqlName2);
    if (!orderedHashSet.isEmpty()) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(0);
      throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
    } 
  }
  
  public void checkObjectIsReferenced(HsqlNameManager.HsqlName paramHsqlName) {
    OrderedHashSet orderedHashSet = getReferencesTo(paramHsqlName);
    HsqlNameManager.HsqlName hsqlName = null;
    char c;
    for (c = Character.MIN_VALUE; c < orderedHashSet.size(); c++) {
      hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(c);
      if (hsqlName.parent != paramHsqlName)
        break; 
      hsqlName = null;
    } 
    if (hsqlName == null)
      return; 
    c = 'ᕾ';
    if (hsqlName.type == 0)
      c = 'ᖝ'; 
    throw Error.error(c, hsqlName.getSchemaQualifiedStatementName());
  }
  
  public void checkSchemaNameCanChange(HsqlNameManager.HsqlName paramHsqlName) {
    this.readLock.lock();
    try {
      Iterator iterator = this.referenceMap.values().iterator();
      HsqlNameManager.HsqlName hsqlName;
      for (hsqlName = null; iterator.hasNext(); hsqlName = null) {
        hsqlName = (HsqlNameManager.HsqlName)iterator.next();
        switch (hsqlName.type) {
          case 4:
          case 8:
          case 16:
          case 17:
          case 18:
          case 24:
            if (hsqlName.schema == paramHsqlName)
              break; 
            break;
        } 
      } 
      if (hsqlName == null)
        return; 
      throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void addSchemaObject(SchemaObject paramSchemaObject) {
    this.writeLock.lock();
    try {
      RoutineSchema routineSchema;
      OrderedHashSet orderedHashSet;
      byte b;
      HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
      Schema schema = (Schema)this.schemaMap.get(hsqlName.schema.name);
      SchemaObjectSet schemaObjectSet = getSchemaObjectSet(schema, hsqlName.type);
      switch (hsqlName.type) {
        case 16:
        case 17:
          routineSchema = (RoutineSchema)schemaObjectSet.getObject(hsqlName.name);
          if (routineSchema == null) {
            routineSchema = new RoutineSchema(hsqlName.type, hsqlName);
            routineSchema.addSpecificRoutine(this.database, (Routine)paramSchemaObject);
            schemaObjectSet.checkAdd(hsqlName);
            SchemaObjectSet schemaObjectSet1 = getSchemaObjectSet(schema, 24);
            schemaObjectSet1.checkAdd(((Routine)paramSchemaObject).getSpecificName());
            schemaObjectSet.add(routineSchema);
            schemaObjectSet1.add(paramSchemaObject);
          } else {
            SchemaObjectSet schemaObjectSet1 = getSchemaObjectSet(schema, 24);
            HsqlNameManager.HsqlName hsqlName1 = ((Routine)paramSchemaObject).getSpecificName();
            if (hsqlName1 != null)
              schemaObjectSet1.checkAdd(hsqlName1); 
            routineSchema.addSpecificRoutine(this.database, (Routine)paramSchemaObject);
            schemaObjectSet1.add(paramSchemaObject);
          } 
          addReferencesFrom(paramSchemaObject);
          return;
        case 3:
          orderedHashSet = ((Table)paramSchemaObject).getReferencesForDependents();
          for (b = 0; b < orderedHashSet.size(); b++) {
            int i;
            ColumnSchema columnSchema;
            HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
            switch (hsqlName1.type) {
              case 9:
                i = ((Table)paramSchemaObject).findColumn(hsqlName1.name);
                columnSchema = ((Table)paramSchemaObject).getColumn(i);
                addSchemaObject(columnSchema);
                break;
            } 
          } 
          break;
        case 9:
          orderedHashSet = paramSchemaObject.getReferences();
          if (orderedHashSet == null || orderedHashSet.isEmpty())
            return; 
          break;
      } 
      if (schemaObjectSet != null)
        schemaObjectSet.add(paramSchemaObject); 
      addReferencesFrom(paramSchemaObject);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void removeSchemaObject(HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    this.writeLock.lock();
    try {
      RoutineSchema routineSchema;
      OrderedHashSet orderedHashSet2;
      Iterator iterator;
      OrderedHashSet orderedHashSet1 = new OrderedHashSet();
      switch (paramHsqlName.type) {
        case 16:
        case 17:
        case 18:
          routineSchema = (RoutineSchema)getSchemaObject(paramHsqlName);
          if (routineSchema != null) {
            Routine[] arrayOfRoutine = routineSchema.getSpecificRoutines();
            for (byte b = 0; b < arrayOfRoutine.length; b++)
              getCascadingReferencesTo(arrayOfRoutine[b].getSpecificName(), orderedHashSet1); 
          } 
          break;
        case 3:
        case 4:
        case 7:
        case 12:
        case 14:
        case 15:
        case 24:
          getCascadingReferencesTo(paramHsqlName, orderedHashSet1);
          break;
        case 13:
          orderedHashSet2 = getReferencesTo(paramHsqlName);
          iterator = orderedHashSet2.iterator();
          while (iterator.hasNext()) {
            HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
            if (hsqlName.type == 9)
              iterator.remove(); 
          } 
          if (!orderedHashSet2.isEmpty()) {
            HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet2.get(0);
            throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
          } 
          break;
      } 
      if (orderedHashSet1.isEmpty()) {
        removeSchemaObject(paramHsqlName);
        return;
      } 
      if (!paramBoolean) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet1.get(0);
        throw Error.error(5502, hsqlName.getSchemaQualifiedStatementName());
      } 
      orderedHashSet1.add(paramHsqlName);
      removeSchemaObjects(orderedHashSet1);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void removeSchemaObjects(OrderedHashSet paramOrderedHashSet) {
    this.writeLock.lock();
    try {
      for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(b);
        removeSchemaObject(hsqlName);
      } 
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void removeSchemaObject(HsqlNameManager.HsqlName paramHsqlName) {
    this.writeLock.lock();
    try {
      Table table2;
      RoutineSchema routineSchema;
      Routine routine;
      Table table1;
      Routine[] arrayOfRoutine;
      byte b;
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      SchemaObject schemaObject = null;
      SchemaObjectSet schemaObjectSet = null;
      switch (paramHsqlName.type) {
        case 7:
          schemaObjectSet = schema.sequenceLookup;
          schemaObject = schemaObjectSet.getObject(paramHsqlName.name);
          break;
        case 3:
        case 4:
          schemaObjectSet = schema.tableLookup;
          schemaObject = schemaObjectSet.getObject(paramHsqlName.name);
          break;
        case 9:
          table2 = (Table)getSchemaObject(paramHsqlName.parent);
          if (table2 != null)
            schemaObject = table2.getColumn(table2.getColumnIndex(paramHsqlName.name)); 
          break;
        case 14:
          schemaObjectSet = schema.charsetLookup;
          schemaObject = schemaObjectSet.getObject(paramHsqlName.name);
          break;
        case 15:
          schemaObjectSet = schema.collationLookup;
          schemaObject = schemaObjectSet.getObject(paramHsqlName.name);
          break;
        case 17:
          schemaObjectSet = schema.procedureLookup;
          routineSchema = (RoutineSchema)schemaObjectSet.getObject(paramHsqlName.name);
          schemaObject = routineSchema;
          arrayOfRoutine = routineSchema.getSpecificRoutines();
          for (b = 0; b < arrayOfRoutine.length; b++)
            removeSchemaObject(arrayOfRoutine[b].getSpecificName()); 
          break;
        case 16:
          schemaObjectSet = schema.functionLookup;
          routineSchema = (RoutineSchema)schemaObjectSet.getObject(paramHsqlName.name);
          schemaObject = routineSchema;
          arrayOfRoutine = routineSchema.getSpecificRoutines();
          for (b = 0; b < arrayOfRoutine.length; b++)
            removeSchemaObject(arrayOfRoutine[b].getSpecificName()); 
          break;
        case 24:
          schemaObjectSet = schema.specificRoutineLookup;
          routine = (Routine)schemaObjectSet.getObject(paramHsqlName.name);
          schemaObject = routine;
          routine.routineSchema.removeSpecificRoutine(routine);
          if ((routine.routineSchema.getSpecificRoutines()).length == 0)
            removeSchemaObject(routine.getName()); 
          break;
        case 12:
        case 13:
          schemaObjectSet = schema.typeLookup;
          schemaObject = schemaObjectSet.getObject(paramHsqlName.name);
          break;
        case 20:
          schemaObjectSet = schema.indexLookup;
          break;
        case 5:
          schemaObjectSet = schema.constraintLookup;
          if (paramHsqlName.parent.type == 3) {
            Table table = (Table)schema.tableList.get(paramHsqlName.parent.name);
            schemaObject = table.getConstraint(paramHsqlName.name);
            table.removeConstraint(paramHsqlName.name);
            break;
          } 
          if (paramHsqlName.parent.type == 13) {
            Type type = (Type)schema.typeLookup.getObject(paramHsqlName.parent.name);
            schemaObject = type.userTypeModifier.getConstraint(paramHsqlName.name);
            type.userTypeModifier.removeConstraint(paramHsqlName.name);
          } 
          break;
        case 8:
          schemaObjectSet = schema.triggerLookup;
          table1 = (Table)schema.tableList.get(paramHsqlName.parent.name);
          schemaObject = table1.getTrigger(paramHsqlName.name);
          if (schemaObject != null)
            table1.removeTrigger((TriggerDef)schemaObject); 
          break;
        default:
          throw Error.runtimeError(201, "SchemaManager");
      } 
      if (schemaObject != null) {
        this.database.getGranteeManager().removeDbObject(paramHsqlName);
        removeReferencesFrom(schemaObject);
      } 
      if (schemaObjectSet != null)
        schemaObjectSet.remove(paramHsqlName.name); 
      removeReferencesTo(paramHsqlName);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void renameSchemaObject(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    this.writeLock.lock();
    try {
      if (paramHsqlName1.schema != paramHsqlName2.schema)
        throw Error.error(5505, paramHsqlName2.schema.name); 
      checkObjectIsReferenced(paramHsqlName1);
      Schema schema = (Schema)this.schemaMap.get(paramHsqlName1.schema.name);
      SchemaObjectSet schemaObjectSet = getSchemaObjectSet(schema, paramHsqlName1.type);
      schemaObjectSet.rename(paramHsqlName1, paramHsqlName2);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void replaceReferences(SchemaObject paramSchemaObject1, SchemaObject paramSchemaObject2) {
    this.writeLock.lock();
    try {
      removeReferencesFrom(paramSchemaObject1);
      addReferencesFrom(paramSchemaObject2);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public HsqlNameManager.HsqlName getObjectName(String paramString1, String paramString2, int paramInt) {
    SchemaObject schemaObject = getSchemaObject(paramString1, paramString2, paramInt);
    return (schemaObject == null) ? null : schemaObject.getName();
  }
  
  public String[] getSQLArray() {
    this.readLock.lock();
    try {
      OrderedHashSet orderedHashSet3;
      OrderedHashSet orderedHashSet1 = new OrderedHashSet();
      OrderedHashSet orderedHashSet2 = new OrderedHashSet();
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      Iterator iterator1 = this.schemaMap.values().iterator();
      iterator1 = this.schemaMap.values().iterator();
      while (iterator1.hasNext()) {
        Schema schema = (Schema)iterator1.next();
        if (SqlInvariants.isSystemSchemaName((schema.getName()).name) || SqlInvariants.isLobsSchemaName((schema.getName()).name))
          continue; 
        hsqlArrayList.add(schema.getSQL());
        schema.addSimpleObjects(orderedHashSet2);
      } 
      do {
        Iterator iterator = orderedHashSet2.iterator();
        if (!iterator.hasNext())
          break; 
        orderedHashSet3 = new OrderedHashSet();
        SchemaObjectSet.addAllSQL(orderedHashSet1, orderedHashSet2, hsqlArrayList, iterator, orderedHashSet3);
        orderedHashSet2.removeAll((Collection)orderedHashSet3);
      } while (orderedHashSet3.size() != 0);
      iterator1 = this.schemaMap.values().iterator();
      while (iterator1.hasNext()) {
        Schema schema = (Schema)iterator1.next();
        if (SqlInvariants.isLobsSchemaName((schema.getName()).name) || SqlInvariants.isSystemSchemaName((schema.getName()).name))
          continue; 
        hsqlArrayList.addAll((Object[])schema.getSQLArray(orderedHashSet1, orderedHashSet2));
      } 
      do {
        Iterator iterator = orderedHashSet2.iterator();
        if (!iterator.hasNext())
          break; 
        orderedHashSet3 = new OrderedHashSet();
        SchemaObjectSet.addAllSQL(orderedHashSet1, orderedHashSet2, hsqlArrayList, iterator, orderedHashSet3);
        orderedHashSet2.removeAll((Collection)orderedHashSet3);
      } while (orderedHashSet3.size() != 0);
      Iterator iterator2 = orderedHashSet2.iterator();
      while (iterator2.hasNext()) {
        SchemaObject schemaObject = (SchemaObject)iterator2.next();
        if (schemaObject instanceof Routine)
          hsqlArrayList.add(((Routine)schemaObject).getSQLDeclaration()); 
      } 
      iterator2 = orderedHashSet2.iterator();
      while (iterator2.hasNext()) {
        SchemaObject schemaObject = (SchemaObject)iterator2.next();
        if (schemaObject instanceof Routine) {
          hsqlArrayList.add(((Routine)schemaObject).getSQLAlter());
          continue;
        } 
        hsqlArrayList.add(schemaObject.getSQL());
      } 
      iterator1 = this.schemaMap.values().iterator();
      while (iterator1.hasNext()) {
        Schema schema = (Schema)iterator1.next();
        if (SqlInvariants.isLobsSchemaName((schema.getName()).name) || SqlInvariants.isSystemSchemaName((schema.getName()).name))
          continue; 
        String[] arrayOfString1 = schema.getTriggerSQL();
        if (arrayOfString1.length > 0) {
          hsqlArrayList.add(Schema.getSetSchemaSQL(schema.getName()));
          hsqlArrayList.addAll((Object[])arrayOfString1);
        } 
      } 
      iterator1 = this.schemaMap.values().iterator();
      while (iterator1.hasNext()) {
        Schema schema = (Schema)iterator1.next();
        hsqlArrayList.addAll((Object[])schema.getSequenceRestartSQL());
      } 
      if (this.defaultSchemaHsqlName != null) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SET").append(' ').append("DATABASE");
        stringBuffer.append(' ').append("DEFAULT").append(' ');
        stringBuffer.append("INITIAL").append(' ').append("SCHEMA");
        stringBuffer.append(' ').append(this.defaultSchemaHsqlName.statementName);
        hsqlArrayList.add(stringBuffer.toString());
      } 
      String[] arrayOfString = new String[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public String[] getTablePropsSQL(boolean paramBoolean) {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList1 = getAllTables(false);
      HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
      for (byte b = 0; b < hsqlArrayList1.size(); b++) {
        Table table = (Table)hsqlArrayList1.get(b);
        if (table.isText()) {
          String[] arrayOfString1 = table.getSQLForTextSource(paramBoolean);
          hsqlArrayList2.addAll((Object[])arrayOfString1);
        } 
        String str = table.getSQLForReadOnly();
        if (str != null)
          hsqlArrayList2.add(str); 
        if (table.isCached()) {
          str = table.getSQLForClustered();
          if (str != null)
            hsqlArrayList2.add(str); 
        } 
      } 
      String[] arrayOfString = new String[hsqlArrayList2.size()];
      hsqlArrayList2.toArray(arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public String[] getTableSpaceSQL() {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList1 = getAllTables(false);
      HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
      for (byte b = 0; b < hsqlArrayList1.size(); b++) {
        Table table = (Table)hsqlArrayList1.get(b);
        if (table.isCached()) {
          String str = table.getSQLForTableSpace();
          if (str != null)
            hsqlArrayList2.add(str); 
        } 
      } 
      String[] arrayOfString = new String[hsqlArrayList2.size()];
      hsqlArrayList2.toArray(arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public String[] getIndexRootsSQL() {
    this.readLock.lock();
    try {
      Session session = this.database.sessionManager.getSysSession();
      long[][] arrayOfLong = getIndexRoots(session);
      HsqlArrayList hsqlArrayList1 = getAllTables(true);
      HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
      for (byte b = 0; b < arrayOfLong.length; b++) {
        Table table = (Table)hsqlArrayList1.get(b);
        if (arrayOfLong[b] != null && (arrayOfLong[b]).length > 0 && arrayOfLong[b][0] != -1L) {
          String str = table.getIndexRootsSQL(arrayOfLong[b]);
          hsqlArrayList2.add(str);
        } 
      } 
      String[] arrayOfString = new String[hsqlArrayList2.size()];
      hsqlArrayList2.toArray(arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public String[] getCommentsArray() {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList1 = getAllTables(false);
      HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b = 0; b < hsqlArrayList1.size(); b++) {
        Table table = (Table)hsqlArrayList1.get(b);
        if (table.getTableType() != 1) {
          int i = table.getColumnCount();
          for (byte b1 = 0; b1 < i; b1++) {
            ColumnSchema columnSchema = table.getColumn(b1);
            if ((columnSchema.getName()).comment != null) {
              stringBuffer.setLength(0);
              stringBuffer.append("COMMENT").append(' ').append("ON");
              stringBuffer.append(' ').append("COLUMN").append(' ');
              stringBuffer.append(table.getName().getSchemaQualifiedStatementName());
              stringBuffer.append('.').append((columnSchema.getName()).statementName);
              stringBuffer.append(' ').append("IS").append(' ');
              stringBuffer.append(StringConverter.toQuotedString((columnSchema.getName()).comment, '\'', true));
              hsqlArrayList2.add(stringBuffer.toString());
            } 
          } 
          if ((table.getName()).comment != null) {
            stringBuffer.setLength(0);
            stringBuffer.append("COMMENT").append(' ').append("ON");
            stringBuffer.append(' ').append("TABLE").append(' ');
            stringBuffer.append(table.getName().getSchemaQualifiedStatementName());
            stringBuffer.append(' ').append("IS").append(' ');
            stringBuffer.append(StringConverter.toQuotedString((table.getName()).comment, '\'', true));
            hsqlArrayList2.add(stringBuffer.toString());
          } 
        } 
      } 
      Iterator iterator = databaseObjectIterator(18);
      while (iterator.hasNext()) {
        SchemaObject schemaObject = (SchemaObject)iterator.next();
        if ((schemaObject.getName()).comment == null)
          continue; 
        stringBuffer.setLength(0);
        stringBuffer.append("COMMENT").append(' ').append("ON");
        stringBuffer.append(' ').append("ROUTINE").append(' ');
        stringBuffer.append(schemaObject.getName().getSchemaQualifiedStatementName());
        stringBuffer.append(' ').append("IS").append(' ');
        stringBuffer.append(StringConverter.toQuotedString((schemaObject.getName()).comment, '\'', true));
        hsqlArrayList2.add(stringBuffer.toString());
      } 
      String[] arrayOfString = new String[hsqlArrayList2.size()];
      hsqlArrayList2.toArray(arrayOfString);
      return arrayOfString;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void setTempIndexRoots(long[][] paramArrayOflong) {
    this.tempIndexRoots = paramArrayOflong;
  }
  
  public long[][] getIndexRoots(Session paramSession) {
    this.readLock.lock();
    try {
      if (this.tempIndexRoots != null) {
        long[][] arrayOfLong1 = this.tempIndexRoots;
        this.tempIndexRoots = (long[][])null;
        return arrayOfLong1;
      } 
      HsqlArrayList hsqlArrayList1 = getAllTables(true);
      HsqlArrayList hsqlArrayList2 = new HsqlArrayList();
      byte b = 0;
      int i = hsqlArrayList1.size();
      while (b < i) {
        Table table = (Table)hsqlArrayList1.get(b);
        if (table.getTableType() == 5) {
          long[] arrayOfLong1 = table.getIndexRootsArray();
          hsqlArrayList2.add(arrayOfLong1);
        } else {
          hsqlArrayList2.add(null);
        } 
        b++;
      } 
      long[][] arrayOfLong = new long[hsqlArrayList2.size()][];
      hsqlArrayList2.toArray(arrayOfLong);
      return arrayOfLong;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void setIndexRoots(long[][] paramArrayOflong) {
    this.readLock.lock();
    try {
      HsqlArrayList hsqlArrayList = this.database.schemaManager.getAllTables(true);
      byte b = 0;
      int i = hsqlArrayList.size();
      while (b < i) {
        Table table = (Table)hsqlArrayList.get(b);
        if (table.getTableType() == 5) {
          long[] arrayOfLong = paramArrayOflong[b];
          if (arrayOfLong != null)
            table.setIndexRoots(arrayOfLong); 
        } 
        b++;
      } 
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void setDefaultTableType(int paramInt) {
    this.defaultTableType = paramInt;
  }
  
  public int getDefaultTableType() {
    return this.defaultTableType;
  }
  
  public void createSystemTables() {
    this.dualTable = TableUtil.newSingleColumnTable(this.database, SqlInvariants.DUAL_TABLE_HSQLNAME, 12, SqlInvariants.DUAL_COLUMN_HSQLNAME, (Type)Type.SQL_VARCHAR);
    this.dualTable.insertSys(this.database.sessionManager.getSysSession(), this.dualTable.getRowStore((Session)null), new Object[] { "X" });
    this.dualTable.setDataReadOnly(true);
    Type[] arrayOfType = { (Type)Type.SQL_BIGINT, (Type)Type.SQL_BIGINT, (Type)Type.SQL_BIGINT, TypeInvariants.SQL_IDENTIFIER, TypeInvariants.SQL_IDENTIFIER, (Type)Type.SQL_BOOLEAN };
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.getSubqueryTableName();
    HashMappedList hashMappedList = new HashMappedList();
    for (byte b = 0; b < arrayOfType.length; b++) {
      HsqlNameManager.HsqlName hsqlName1 = HsqlNameManager.getAutoColumnName(b + 1);
      ColumnSchema columnSchema = new ColumnSchema(hsqlName1, arrayOfType[b], true, false, null);
      hashMappedList.add(hsqlName1.name, columnSchema);
    } 
    this.dataChangeTable = new TableDerived(this.database, hsqlName, 13, arrayOfType, hashMappedList, new int[] { 0 });
    this.dataChangeTable.createIndexForColumns((Session)null, new int[] { 1 });
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SchemaManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */