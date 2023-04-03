package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public class TableWorks {
  OrderedHashSet emptySet = new OrderedHashSet();
  
  private Database database;
  
  private Table table;
  
  private Session session;
  
  public TableWorks(Session paramSession, Table paramTable) {
    this.database = paramTable.database;
    this.table = paramTable;
    this.session = paramSession;
  }
  
  public Table getTable() {
    return this.table;
  }
  
  void checkCreateForeignKey(Constraint paramConstraint) {
    boolean bool = (paramConstraint.core.updateAction == 4 || paramConstraint.core.updateAction == 2 || paramConstraint.core.updateAction == 0 || paramConstraint.core.deleteAction == 4 || paramConstraint.core.deleteAction == 2) ? true : false;
    if (bool)
      for (byte b = 0; b < paramConstraint.core.refCols.length; b++) {
        ColumnSchema columnSchema = this.table.getColumn(paramConstraint.core.refCols[b]);
        if (columnSchema.isGenerated())
          throw Error.error(5524, columnSchema.getNameString()); 
      }  
    if (paramConstraint.core.mainName == this.table.getName() && ArrayUtil.haveCommonElement(paramConstraint.core.refCols, paramConstraint.core.mainCols))
      throw Error.error(5527); 
    bool = (paramConstraint.core.updateAction == 4 || paramConstraint.core.deleteAction == 4) ? true : false;
    if (bool)
      for (byte b = 0; b < paramConstraint.core.refCols.length; b++) {
        ColumnSchema columnSchema = this.table.getColumn(paramConstraint.core.refCols[b]);
        Expression expression = columnSchema.getDefaultExpression();
        if (expression == null) {
          String str = (columnSchema.getName()).statementName;
          throw Error.error(5521, str);
        } 
      }  
    bool = (paramConstraint.core.updateAction == 2 || paramConstraint.core.deleteAction == 2) ? true : false;
    if (bool && !this.session.isProcessingScript())
      for (byte b = 0; b < paramConstraint.core.refCols.length; b++) {
        ColumnSchema columnSchema = this.table.getColumn(paramConstraint.core.refCols[b]);
        if (!columnSchema.isNullable()) {
          String str = (columnSchema.getName()).statementName;
          throw Error.error(5520, str);
        } 
      }  
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    if (this.table.getConstraint((paramConstraint.getName()).name) != null)
      throw Error.error(5504, (paramConstraint.getName()).statementName); 
    if (this.table.getFKConstraintForColumns(paramConstraint.core.mainTable, paramConstraint.core.mainCols, paramConstraint.core.refCols) != null)
      throw Error.error(5528, (paramConstraint.getName()).statementName); 
    if (paramConstraint.core.mainTable.isTemp() != this.table.isTemp())
      throw Error.error(5524, (paramConstraint.getName()).statementName); 
    Constraint constraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    if (constraint == null)
      throw Error.error(5529, (paramConstraint.getMain().getName()).statementName); 
    paramConstraint.core.mainTable.checkColumnsMatch(paramConstraint.core.mainCols, this.table, paramConstraint.core.refCols);
    ArrayUtil.reorderMaps(constraint.getMainColumns(), paramConstraint.getMainColumns(), paramConstraint.getRefColumns());
    boolean[] arrayOfBoolean = paramConstraint.core.mainTable.getColumnCheckList(paramConstraint.core.mainCols);
    Grantee grantee = this.session.getGrantee();
    grantee.checkReferences(paramConstraint.core.mainTable, arrayOfBoolean);
  }
  
  void addForeignKey(Constraint paramConstraint) {
    checkModifyTable();
    checkCreateForeignKey(paramConstraint);
    Constraint constraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    Index index1 = constraint.getMainIndex();
    constraint.checkReferencedRows(this.session, this.table);
    boolean bool = false;
    if (paramConstraint.core.mainTable.getSchemaName() == this.table.getSchemaName()) {
      int i = this.database.schemaManager.getTableIndex(this.table);
      if (i != -1 && i < this.database.schemaManager.getTableIndex(paramConstraint.core.mainTable))
        bool = true; 
    } else {
      bool = true;
    } 
    HsqlNameManager.HsqlName hsqlName1 = this.database.nameManager.newAutoName("IDX", this.table.getSchemaName(), this.table.getName(), 20);
    Index index2 = this.table.createIndexStructure(hsqlName1, paramConstraint.core.refCols, null, null, false, true, bool);
    HsqlNameManager.HsqlName hsqlName2 = this.database.nameManager.newAutoName("REF", (paramConstraint.getName()).name, this.table.getSchemaName(), this.table.getName(), 20);
    paramConstraint.core.uniqueName = constraint.getName();
    paramConstraint.core.mainName = hsqlName2;
    paramConstraint.core.mainIndex = index1;
    paramConstraint.core.refTable = this.table;
    paramConstraint.core.refName = paramConstraint.getName();
    paramConstraint.core.refIndex = index2;
    paramConstraint.isForward = bool;
    Table table1 = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, paramConstraint, index2, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, table1, -1, 0);
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(table1);
    Table table2 = this.database.schemaManager.getTable(this.session, (paramConstraint.core.mainTable.getName()).name, (paramConstraint.core.mainTable.getSchemaName()).name);
    table2.addConstraint(new Constraint(hsqlName2, paramConstraint));
    updateConstraints(table1, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(table1);
    this.table = table1;
  }
  
  void checkAddColumn(ColumnSchema paramColumnSchema) {
    checkModifyTable();
    if (this.table.isText() && !this.table.isEmpty(this.session))
      throw Error.error(320); 
    if (this.table.findColumn((paramColumnSchema.getName()).name) != -1)
      throw Error.error(5504); 
    if (paramColumnSchema.isPrimaryKey() && this.table.hasPrimaryKey())
      throw Error.error(5530); 
    if (paramColumnSchema.isIdentity() && this.table.hasIdentityColumn())
      throw Error.error(5525); 
    if (!this.table.isEmpty(this.session) && !paramColumnSchema.hasDefault() && (!paramColumnSchema.isNullable() || paramColumnSchema.isPrimaryKey()) && !paramColumnSchema.isIdentity())
      throw Error.error(5531); 
  }
  
  void addColumn(ColumnSchema paramColumnSchema, int paramInt, HsqlArrayList paramHsqlArrayList) {
    Index index = null;
    Constraint constraint1 = null;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    checkAddColumn(paramColumnSchema);
    Constraint constraint2 = (Constraint)paramHsqlArrayList.get(0);
    if (constraint2.getConstraintType() == 4) {
      if (paramColumnSchema.getDataType().isLobType())
        throw Error.error(5534); 
      constraint2.core.mainCols = new int[] { paramInt };
      this.database.schemaManager.checkSchemaObjectNotExists(constraint2.getName());
      if (this.table.hasPrimaryKey())
        throw Error.error(5530); 
      bool2 = true;
    } else {
      constraint2 = null;
    } 
    Table table = this.table.moveDefinition(this.session, this.table.tableType, paramColumnSchema, constraint2, (Index)null, paramInt, 1, this.emptySet, this.emptySet);
    for (byte b = 1; b < paramHsqlArrayList.size(); b++) {
      HsqlNameManager.HsqlName hsqlName1;
      boolean bool4;
      Constraint constraint;
      boolean bool5;
      int i;
      HsqlNameManager.HsqlName hsqlName2;
      constraint2 = (Constraint)paramHsqlArrayList.get(b);
      switch (constraint2.constType) {
        case 2:
          if (bool2)
            throw Error.error(5522); 
          if (paramColumnSchema.getDataType().isLobType())
            throw Error.error(5534); 
          bool2 = true;
          constraint2.core.mainCols = new int[] { paramInt };
          this.database.schemaManager.checkSchemaObjectNotExists(constraint2.getName());
          hsqlName1 = this.database.nameManager.newAutoName("IDX", (constraint2.getName()).name, this.table.getSchemaName(), this.table.getName(), 20);
          index = table.createAndAddIndexStructure(this.session, hsqlName1, constraint2.getMainColumns(), null, null, true, true, false);
          constraint2.core.mainTable = table;
          constraint2.core.mainIndex = index;
          table.addConstraint(constraint2);
          break;
        case 0:
          if (bool1)
            throw Error.error(5528); 
          bool1 = true;
          constraint2.core.refCols = new int[] { paramInt };
          constraint2.core.mainTable = this.database.schemaManager.getUserTable(this.session, constraint2.getMainTableName());
          constraint2.core.refTable = table;
          constraint2.core.refName = constraint2.getName();
          bool4 = (this.table == constraint2.core.mainTable) ? true : false;
          if (bool4)
            constraint2.core.mainTable = table; 
          constraint2.setColumnsIndexes(table);
          checkCreateForeignKey(constraint2);
          constraint = constraint2.core.mainTable.getUniqueConstraintForColumns(constraint2.core.mainCols);
          bool5 = (constraint2.core.mainTable.getSchemaName() != this.table.getSchemaName()) ? true : false;
          i = this.database.schemaManager.getTableIndex(this.table);
          if (!bool4 && i < this.database.schemaManager.getTableIndex(constraint2.core.mainTable))
            bool5 = true; 
          hsqlName2 = this.database.nameManager.newAutoName("IDX", (constraint2.getName()).name, this.table.getSchemaName(), this.table.getName(), 20);
          index = table.createAndAddIndexStructure(this.session, hsqlName2, constraint2.getRefColumns(), null, null, false, true, bool5);
          constraint2.core.uniqueName = constraint.getName();
          constraint2.core.mainName = this.database.nameManager.newAutoName("REF", constraint2.core.refName.name, this.table.getSchemaName(), this.table.getName(), 20);
          constraint2.core.mainIndex = constraint.getMainIndex();
          constraint2.core.refIndex = index;
          constraint2.isForward = bool5;
          table.addConstraint(constraint2);
          constraint1 = new Constraint(constraint2.core.mainName, constraint2);
          break;
        case 3:
          if (bool3)
            throw Error.error(5528); 
          bool3 = true;
          constraint2.prepareCheckConstraint(this.session, table);
          table.addConstraint(constraint2);
          if (constraint2.isNotNull()) {
            paramColumnSchema.setNullable(false);
            table.setColumnTypeVars(paramInt);
            if (!this.table.isEmpty(this.session) && !paramColumnSchema.hasDefault())
              throw Error.error(5531); 
          } 
          break;
      } 
    } 
    paramColumnSchema.compile(this.session, table);
    moveData(this.table, table, paramInt, 1);
    if (constraint1 != null)
      constraint1.getMain().addConstraint(constraint1); 
    registerConstraintNames(paramHsqlArrayList);
    setNewTableInSchema(table);
    updateConstraints(table, this.emptySet);
    this.database.schemaManager.addSchemaObject(paramColumnSchema);
    this.database.schemaManager.recompileDependentObjects(table);
    table.compile(this.session, (SchemaObject)null);
    this.table = table;
  }
  
  void updateConstraints(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    for (byte b = 0; b < paramOrderedHashSet1.size(); b++) {
      Table table = (Table)paramOrderedHashSet1.get(b);
      updateConstraints(table, paramOrderedHashSet2);
    } 
  }
  
  void updateConstraints(Table paramTable, OrderedHashSet paramOrderedHashSet) {
    for (int i = paramTable.constraintList.length - 1; i >= 0; i--) {
      Constraint constraint = paramTable.constraintList[i];
      if (paramOrderedHashSet.contains(constraint.getName())) {
        paramTable.removeConstraint(i);
      } else if (constraint.getConstraintType() == 0) {
        Table table1 = this.database.schemaManager.getUserTable(this.session, constraint.core.refTable.getName());
        constraint.core.refTable = table1;
        Table table2 = this.database.schemaManager.getUserTable(this.session, constraint.core.mainTable.getName());
        Constraint constraint1 = table2.getConstraint((constraint.getMainName()).name);
        constraint1.core = constraint.core;
      } else if (constraint.getConstraintType() == 1) {
        Table table1 = this.database.schemaManager.getUserTable(this.session, constraint.core.mainTable.getName());
        constraint.core.mainTable = table1;
        Table table2 = this.database.schemaManager.getUserTable(this.session, constraint.core.refTable.getName());
        Constraint constraint1 = table2.getConstraint((constraint.getRefName()).name);
        constraint1.core = constraint.core;
      } 
    } 
  }
  
  OrderedHashSet makeNewTables(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, OrderedHashSet paramOrderedHashSet3) {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < paramOrderedHashSet1.size(); b++) {
      Table table = (Table)paramOrderedHashSet1.get(b);
      TableWorks tableWorks = new TableWorks(this.session, table);
      tableWorks.makeNewTable(paramOrderedHashSet2, paramOrderedHashSet3);
      orderedHashSet.add(tableWorks.getTable());
    } 
    return orderedHashSet;
  }
  
  void makeNewTable(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, (Index)null, -1, 0, paramOrderedHashSet1, paramOrderedHashSet2);
    if (table.indexList.length == this.table.indexList.length) {
      this.database.persistentStoreCollection.releaseStore(table);
      return;
    } 
    moveData(this.table, table, -1, 0);
    this.table = table;
  }
  
  void alterIndex(Index paramIndex, int[] paramArrayOfint) {
    Index index = this.database.logger.newIndex(this.table, paramIndex, paramArrayOfint);
    int i = paramIndex.getPosition();
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this.table);
    Index[] arrayOfIndex = persistentStore.getAccessorKeys();
    index.setPosition(i);
    this.table.getIndexList()[i] = index;
    this.table.setBestRowIdentifiers();
    arrayOfIndex[i] = index;
    persistentStore.reindex(this.session, index);
  }
  
  Index addIndex(int[] paramArrayOfint, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean) {
    Index index;
    checkModifyTable();
    if (this.table.isEmpty(this.session) || this.table.isIndexingMutable()) {
      index = this.table.createIndex(this.session, paramHsqlName, paramArrayOfint, null, null, paramBoolean, false, false);
    } else {
      index = this.table.createIndexStructure(paramHsqlName, paramArrayOfint, null, null, paramBoolean, false, false);
      Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, index, -1, 0, this.emptySet, this.emptySet);
      moveData(this.table, table, -1, 0);
      this.table = table;
      setNewTableInSchema(this.table);
      updateConstraints(this.table, this.emptySet);
    } 
    this.database.schemaManager.addSchemaObject((SchemaObject)index);
    this.database.schemaManager.recompileDependentObjects(this.table);
    return index;
  }
  
  void addPrimaryKey(Constraint paramConstraint) {
    checkModifyTable();
    if (this.table.hasPrimaryKey())
      throw Error.error(5532); 
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, paramConstraint, (Index)null, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, table, -1, 0);
    this.table = table;
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }
  
  void addUniqueConstraint(int[] paramArrayOfint, HsqlNameManager.HsqlName paramHsqlName) {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramHsqlName);
    if (this.table.getUniqueConstraintForColumns(paramArrayOfint) != null)
      throw Error.error(5522); 
    HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newAutoName("IDX", paramHsqlName.name, this.table.getSchemaName(), this.table.getName(), 20);
    Index index = this.table.createIndexStructure(hsqlName, paramArrayOfint, null, null, true, true, false);
    Constraint constraint = new Constraint(paramHsqlName, this.table, index, 2);
    Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, constraint, index, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, table, -1, 0);
    this.table = table;
    this.database.schemaManager.addSchemaObject(constraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }
  
  void addUniqueConstraint(Constraint paramConstraint) {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    if (this.table.getUniqueConstraintForColumns(paramConstraint.getMainColumns()) != null)
      throw Error.error(5522); 
    Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, paramConstraint, paramConstraint.getMainIndex(), -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, table, -1, 0);
    this.table = table;
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }
  
  void addCheckConstraint(Constraint paramConstraint) {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    paramConstraint.prepareCheckConstraint(this.session, this.table);
    paramConstraint.checkCheckConstraint(this.session, this.table);
    this.table.addConstraint(paramConstraint);
    if (paramConstraint.isNotNull()) {
      ColumnSchema columnSchema = this.table.getColumn(paramConstraint.notNullColumnIndex);
      columnSchema.setNullable(false);
      this.table.setColumnTypeVars(paramConstraint.notNullColumnIndex);
    } 
    this.database.schemaManager.addSchemaObject(paramConstraint);
  }
  
  void dropIndex(String paramString) {
    checkModifyTable();
    Index index = this.table.getIndex(paramString);
    if (this.table.isIndexingMutable()) {
      this.table.dropIndex(this.session, index.getPosition());
    } else {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      orderedHashSet.add(this.table.getIndex(paramString).getName());
      Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, (Index)null, -1, 0, this.emptySet, orderedHashSet);
      moveData(this.table, table, -1, 0);
      setNewTableInSchema(table);
      updateConstraints(table, this.emptySet);
      this.table = table;
    } 
    if (!index.isConstraint())
      this.database.schemaManager.removeSchemaObject(index.getName()); 
    this.database.schemaManager.recompileDependentObjects(this.table);
  }
  
  void dropColumn(int paramInt, boolean paramBoolean) {
    OrderedHashSet orderedHashSet1 = new OrderedHashSet();
    OrderedHashSet orderedHashSet2 = this.table.getDependentConstraints(paramInt);
    OrderedHashSet orderedHashSet3 = this.table.getContainingConstraints(paramInt);
    OrderedHashSet orderedHashSet4 = this.table.getContainingIndexNames(paramInt);
    ColumnSchema columnSchema = this.table.getColumn(paramInt);
    HsqlNameManager.HsqlName hsqlName = columnSchema.getName();
    OrderedHashSet orderedHashSet5 = this.database.schemaManager.getReferencesTo(this.table.getName(), hsqlName);
    checkModifyTable();
    if (!paramBoolean) {
      if (!orderedHashSet3.isEmpty()) {
        Constraint constraint = (Constraint)orderedHashSet3.get(0);
        HsqlNameManager.HsqlName hsqlName1 = constraint.getName();
        throw Error.error(5536, hsqlName1.getSchemaQualifiedStatementName());
      } 
      if (!orderedHashSet5.isEmpty())
        for (byte b1 = 0; b1 < orderedHashSet5.size(); b1++) {
          HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet5.get(b1);
          if (hsqlName1 != hsqlName) {
            byte b2 = 0;
            while (true) {
              if (b2 < orderedHashSet2.size()) {
                Constraint constraint = (Constraint)orderedHashSet2.get(b2);
                if (constraint.getName() == hsqlName1)
                  break; 
                b2++;
                continue;
              } 
              throw Error.error(5536, hsqlName1.getSchemaQualifiedStatementName());
            } 
          } 
        }  
    } 
    orderedHashSet2.addAll((Collection)orderedHashSet3);
    orderedHashSet3.clear();
    OrderedHashSet orderedHashSet6 = new OrderedHashSet();
    for (byte b = 0; b < orderedHashSet2.size(); b++) {
      Constraint constraint = (Constraint)orderedHashSet2.get(b);
      if (constraint.constType == 0) {
        orderedHashSet6.add(constraint.getMain());
        orderedHashSet1.add(constraint.getMainName());
        orderedHashSet1.add(constraint.getRefName());
        orderedHashSet4.add(constraint.getRefIndex().getName());
      } 
      if (constraint.constType == 1) {
        orderedHashSet6.add(constraint.getRef());
        orderedHashSet1.add(constraint.getMainName());
        orderedHashSet1.add(constraint.getRefName());
        orderedHashSet4.add(constraint.getRefIndex().getName());
      } 
      orderedHashSet1.add(constraint.getName());
    } 
    orderedHashSet6 = makeNewTables(orderedHashSet6, orderedHashSet1, orderedHashSet4);
    Table table = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, (Index)null, paramInt, -1, orderedHashSet1, orderedHashSet4);
    moveData(this.table, table, paramInt, -1);
    this.database.schemaManager.removeSchemaObjects(orderedHashSet5);
    this.database.schemaManager.removeSchemaObjects(orderedHashSet1);
    this.database.schemaManager.removeSchemaObject(hsqlName);
    setNewTableInSchema(table);
    setNewTablesInSchema(orderedHashSet6);
    updateConstraints(table, this.emptySet);
    updateConstraints(orderedHashSet6, orderedHashSet1);
    this.database.schemaManager.recompileDependentObjects(orderedHashSet6);
    this.database.schemaManager.recompileDependentObjects(table);
    table.compile(this.session, (SchemaObject)null);
    this.table = table;
  }
  
  void registerConstraintNames(HsqlArrayList paramHsqlArrayList) {
    for (byte b = 0; b < paramHsqlArrayList.size(); b++) {
      Constraint constraint = (Constraint)paramHsqlArrayList.get(b);
      switch (constraint.constType) {
        case 2:
        case 3:
        case 4:
          this.database.schemaManager.addSchemaObject(constraint);
          break;
      } 
    } 
  }
  
  void dropConstraint(String paramString, boolean paramBoolean) {
    OrderedHashSet orderedHashSet1;
    OrderedHashSet orderedHashSet2;
    Table table1;
    OrderedHashSet orderedHashSet3;
    HsqlNameManager.HsqlName hsqlName;
    OrderedHashSet orderedHashSet4;
    byte b;
    Table table2;
    Constraint constraint = this.table.getConstraint(paramString);
    if (constraint == null)
      throw Error.error(5501, paramString); 
    switch (constraint.getConstraintType()) {
      case 1:
        throw Error.error(4002);
      case 2:
      case 4:
        checkModifyTable();
        orderedHashSet1 = this.table.getDependentConstraints(constraint);
        if (!paramBoolean && !orderedHashSet1.isEmpty()) {
          Constraint constraint1 = (Constraint)orderedHashSet1.get(0);
          throw Error.error(5533, constraint1.getName().getSchemaQualifiedStatementName());
        } 
        orderedHashSet2 = new OrderedHashSet();
        orderedHashSet3 = new OrderedHashSet();
        orderedHashSet4 = new OrderedHashSet();
        for (b = 0; b < orderedHashSet1.size(); b++) {
          Constraint constraint1 = (Constraint)orderedHashSet1.get(b);
          Table table = constraint1.getMain();
          if (table != this.table)
            orderedHashSet2.add(table); 
          table = constraint1.getRef();
          if (table != this.table)
            orderedHashSet2.add(table); 
          orderedHashSet3.add(constraint1.getMainName());
          orderedHashSet3.add(constraint1.getRefName());
          orderedHashSet4.add(constraint1.getRefIndex().getName());
        } 
        orderedHashSet3.add(constraint.getName());
        if (constraint.getConstraintType() == 2)
          orderedHashSet4.add(constraint.getMainIndex().getName()); 
        table2 = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, (Index)null, -1, 0, orderedHashSet3, orderedHashSet4);
        moveData(this.table, table2, -1, 0);
        orderedHashSet2 = makeNewTables(orderedHashSet2, orderedHashSet3, orderedHashSet4);
        if (constraint.getConstraintType() == 4) {
          int[] arrayOfInt = constraint.getMainColumns();
          for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
            table2.getColumn(arrayOfInt[b1]).setPrimaryKey(false);
            table2.setColumnTypeVars(arrayOfInt[b1]);
          } 
        } 
        this.database.schemaManager.removeSchemaObjects(orderedHashSet3);
        setNewTableInSchema(table2);
        setNewTablesInSchema(orderedHashSet2);
        updateConstraints(table2, this.emptySet);
        updateConstraints(orderedHashSet2, orderedHashSet3);
        this.database.schemaManager.recompileDependentObjects(orderedHashSet2);
        this.database.schemaManager.recompileDependentObjects(table2);
        this.table = table2;
        break;
      case 0:
        checkModifyTable();
        orderedHashSet1 = new OrderedHashSet();
        table1 = constraint.getMain();
        hsqlName = constraint.getMainName();
        orderedHashSet1.add(hsqlName);
        orderedHashSet1.add(constraint.getRefName());
        orderedHashSet4 = new OrderedHashSet();
        orderedHashSet4.add(constraint.getRefIndex().getName());
        table2 = this.table.moveDefinition(this.session, this.table.tableType, (ColumnSchema)null, (Constraint)null, (Index)null, -1, 0, orderedHashSet1, orderedHashSet4);
        moveData(this.table, table2, -1, 0);
        this.database.schemaManager.removeSchemaObject(constraint.getName());
        setNewTableInSchema(table2);
        table1.removeConstraint(hsqlName.name);
        updateConstraints(table2, this.emptySet);
        this.database.schemaManager.recompileDependentObjects(this.table);
        this.table = table2;
        break;
      case 3:
        this.database.schemaManager.removeSchemaObject(constraint.getName());
        if (constraint.isNotNull()) {
          ColumnSchema columnSchema = this.table.getColumn(constraint.notNullColumnIndex);
          columnSchema.setNullable(false);
          this.table.setColumnTypeVars(constraint.notNullColumnIndex);
        } 
        break;
    } 
  }
  
  void retypeColumn(ColumnSchema paramColumnSchema1, ColumnSchema paramColumnSchema2) {
    Type type1 = paramColumnSchema1.getDataType();
    Type type2 = paramColumnSchema2.getDataType();
    checkModifyTable();
    if (type1.equals(type2) && paramColumnSchema1.getIdentitySequence() == paramColumnSchema2.getIdentitySequence())
      return; 
    if (!this.table.isEmpty(this.session) && type1.typeCode != type2.typeCode) {
      boolean bool = paramColumnSchema2.getDataType().canConvertFrom(paramColumnSchema1.getDataType());
      switch (type1.typeCode) {
        case 1111:
        case 2000:
          bool = false;
          break;
      } 
      if (!bool)
        throw Error.error(5561); 
    } 
    int i = this.table.getColumnIndex((paramColumnSchema1.getName()).name);
    int j = type2.canMoveFrom(type1);
    if (j == 0 && paramColumnSchema2.isIdentity() && !paramColumnSchema1.isIdentity() && paramColumnSchema1.isNullable() && !paramColumnSchema1.isPrimaryKey())
      j = 1; 
    if (j == 1) {
      checkConvertColDataType(paramColumnSchema1, paramColumnSchema2);
      j = 0;
    } 
    if (j == 0) {
      paramColumnSchema1.setType(paramColumnSchema2);
      paramColumnSchema1.setDefaultExpression(paramColumnSchema2.getDefaultExpression());
      paramColumnSchema1.setIdentity(paramColumnSchema2.getIdentitySequence());
      this.table.setColumnTypeVars(i);
      this.table.resetDefaultsFlag();
      return;
    } 
    this.database.schemaManager.checkColumnIsReferenced(this.table.getName(), this.table.getColumn(i).getName());
    this.table.checkColumnInCheckConstraint(i);
    this.table.checkColumnInFKConstraint(i);
    checkConvertColDataType(paramColumnSchema1, paramColumnSchema2);
    retypeColumn(paramColumnSchema2, i);
  }
  
  void checkConvertColDataType(ColumnSchema paramColumnSchema1, ColumnSchema paramColumnSchema2) {
    int i = this.table.getColumnIndex((paramColumnSchema1.getName()).name);
    RowIterator rowIterator = this.table.rowIterator(this.session);
    while (rowIterator.hasNext()) {
      Row row = rowIterator.getNextRow();
      Object object = row.getData()[i];
      if (!paramColumnSchema2.isNullable() && object == null)
        throw Error.error(10); 
      paramColumnSchema2.getDataType().convertToType(this.session, object, paramColumnSchema1.getDataType());
    } 
  }
  
  private void retypeColumn(ColumnSchema paramColumnSchema, int paramInt) {
    Table table = this.table.moveDefinition(this.session, this.table.tableType, paramColumnSchema, (Constraint)null, (Index)null, paramInt, 0, this.emptySet, this.emptySet);
    moveData(this.table, table, paramInt, 0);
    setNewTableInSchema(table);
    updateConstraints(table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
    this.table = table;
  }
  
  void setColNullability(ColumnSchema paramColumnSchema, boolean paramBoolean) {
    Constraint constraint = null;
    int i = this.table.getColumnIndex((paramColumnSchema.getName()).name);
    if (paramColumnSchema.isNullable() == paramBoolean)
      return; 
    if (paramBoolean) {
      if (paramColumnSchema.isPrimaryKey())
        throw Error.error(5526); 
      this.table.checkColumnInFKConstraint(i, 2);
      removeColumnNotNullConstraints(i);
    } else {
      HsqlNameManager.HsqlName hsqlName = this.database.nameManager.newAutoName("CT", this.table.getSchemaName(), this.table.getName(), 5);
      constraint = new Constraint(hsqlName, null, 3);
      constraint.check = new ExpressionLogical(paramColumnSchema);
      constraint.prepareCheckConstraint(this.session, this.table);
      constraint.checkCheckConstraint(this.session, this.table);
      paramColumnSchema.setNullable(false);
      this.table.addConstraint(constraint);
      this.table.setColumnTypeVars(i);
      this.database.schemaManager.addSchemaObject(constraint);
    } 
  }
  
  void setColDefaultExpression(int paramInt, Expression paramExpression) {
    if (paramExpression == null)
      this.table.checkColumnInFKConstraint(paramInt, 4); 
    ColumnSchema columnSchema = this.table.getColumn(paramInt);
    columnSchema.setDefaultExpression(paramExpression);
    this.table.setColumnTypeVars(paramInt);
  }
  
  public boolean setTableType(Session paramSession, int paramInt) {
    Table table;
    int i = this.table.getTableType();
    if (i == paramInt)
      return false; 
    switch (paramInt) {
      case 5:
      case 4:
        break;
      default:
        return false;
    } 
    try {
      table = this.table.moveDefinition(paramSession, paramInt, (ColumnSchema)null, (Constraint)null, (Index)null, -1, 0, this.emptySet, this.emptySet);
      moveData(this.table, table, -1, 0);
    } catch (HsqlException hsqlException) {
      return false;
    } 
    setNewTableInSchema(table);
    updateConstraints(table, this.emptySet);
    this.table = table;
    this.database.schemaManager.recompileDependentObjects(this.table);
    return true;
  }
  
  void setNewTablesInSchema(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      Table table = (Table)paramOrderedHashSet.get(b);
      setNewTableInSchema(table);
    } 
  }
  
  void setNewTableInSchema(Table paramTable) {
    int i = this.database.schemaManager.getTableIndex(paramTable);
    if (i != -1)
      this.database.schemaManager.setTable(i, paramTable); 
  }
  
  void removeColumnNotNullConstraints(int paramInt) {
    for (int i = this.table.constraintList.length - 1; i >= 0; i--) {
      Constraint constraint = this.table.constraintList[i];
      if (constraint.isNotNull() && constraint.notNullColumnIndex == paramInt)
        this.database.schemaManager.removeSchemaObject(constraint.getName()); 
    } 
    ColumnSchema columnSchema = this.table.getColumn(paramInt);
    columnSchema.setNullable(true);
    this.table.setColumnTypeVars(paramInt);
  }
  
  private void checkModifyTable() {
    if (this.session.getUser().isSystem())
      return; 
    if (this.session.isProcessingScript())
      return; 
    if (this.database.isFilesReadOnly() || this.table.isReadOnly())
      throw Error.error(456); 
    if (this.table.isText() && this.table.isConnected())
      throw Error.error(320); 
  }
  
  void moveData(Table paramTable1, Table paramTable2, int paramInt1, int paramInt2) {
    int i = paramTable1.getTableType();
    if (i == 3) {
      Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
      for (byte b = 0; b < arrayOfSession.length; b++)
        (arrayOfSession[b]).sessionData.persistentStoreCollection.moveData(paramTable1, paramTable2, paramInt1, paramInt2); 
    } else {
      boolean bool = false;
      if (paramTable2.isCached())
        bool = (paramTable1.getSpaceID() != 7) ? true : false; 
      if (bool) {
        int j = (this.database.logger.getCache()).spaceManager.getNewTableSpaceID();
        paramTable2.setSpaceID(j);
      } 
      PersistentStore persistentStore1 = this.database.persistentStoreCollection.getStore(paramTable1);
      PersistentStore persistentStore2 = this.database.persistentStoreCollection.getStore(paramTable2);
      try {
        persistentStore2.moveData(this.session, persistentStore1, paramInt1, paramInt2);
      } catch (HsqlException hsqlException) {
        persistentStore2.release();
        this.database.persistentStoreCollection.setStore(paramTable2, null);
        throw hsqlException;
      } 
      this.database.persistentStoreCollection.releaseStore(paramTable1);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TableWorks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */