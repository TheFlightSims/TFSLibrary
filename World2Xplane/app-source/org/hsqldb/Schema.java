package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.rights.Grantee;

public final class Schema implements SchemaObject {
  private HsqlNameManager.HsqlName name;
  
  SchemaObjectSet triggerLookup;
  
  SchemaObjectSet constraintLookup;
  
  SchemaObjectSet indexLookup;
  
  SchemaObjectSet tableLookup;
  
  SchemaObjectSet sequenceLookup;
  
  SchemaObjectSet typeLookup;
  
  SchemaObjectSet charsetLookup;
  
  SchemaObjectSet collationLookup;
  
  SchemaObjectSet procedureLookup;
  
  SchemaObjectSet functionLookup;
  
  SchemaObjectSet specificRoutineLookup;
  
  SchemaObjectSet assertionLookup;
  
  HashMappedList tableList;
  
  HashMappedList sequenceList;
  
  long changeTimestamp;
  
  public Schema(HsqlNameManager.HsqlName paramHsqlName, Grantee paramGrantee) {
    this.name = paramHsqlName;
    this.triggerLookup = new SchemaObjectSet(8);
    this.indexLookup = new SchemaObjectSet(20);
    this.constraintLookup = new SchemaObjectSet(5);
    this.tableLookup = new SchemaObjectSet(3);
    this.sequenceLookup = new SchemaObjectSet(7);
    this.typeLookup = new SchemaObjectSet(12);
    this.charsetLookup = new SchemaObjectSet(14);
    this.collationLookup = new SchemaObjectSet(15);
    this.procedureLookup = new SchemaObjectSet(17);
    this.functionLookup = new SchemaObjectSet(16);
    this.specificRoutineLookup = new SchemaObjectSet(24);
    this.assertionLookup = new SchemaObjectSet(6);
    this.tableList = (HashMappedList)this.tableLookup.map;
    this.sequenceList = (HashMappedList)this.sequenceLookup.map;
    paramHsqlName.owner = paramGrantee;
  }
  
  public int getType() {
    return 2;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return null;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return null;
  }
  
  public Grantee getOwner() {
    return this.name.owner;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public long getChangeTimestamp() {
    return this.changeTimestamp;
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("CREATE").append(' ');
    stringBuffer.append("SCHEMA").append(' ');
    stringBuffer.append((getName()).statementName).append(' ');
    stringBuffer.append("AUTHORIZATION").append(' ');
    stringBuffer.append(getOwner().getName().getStatementName());
    return stringBuffer.toString();
  }
  
  static String getSetSchemaSQL(HsqlNameManager.HsqlName paramHsqlName) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("SCHEMA").append(' ');
    stringBuffer.append(paramHsqlName.statementName);
    return stringBuffer.toString();
  }
  
  public String[] getSQLArray(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    String str = getSetSchemaSQL(this.name);
    hsqlArrayList.add(str);
    String[] arrayOfString1 = this.sequenceLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    hsqlArrayList.addAll((Object[])arrayOfString1);
    arrayOfString1 = this.tableLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    hsqlArrayList.addAll((Object[])arrayOfString1);
    arrayOfString1 = this.functionLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    hsqlArrayList.addAll((Object[])arrayOfString1);
    arrayOfString1 = this.procedureLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    hsqlArrayList.addAll((Object[])arrayOfString1);
    arrayOfString1 = this.assertionLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    hsqlArrayList.addAll((Object[])arrayOfString1);
    if (hsqlArrayList.size() == 1)
      return new String[0]; 
    String[] arrayOfString2 = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString2);
    return arrayOfString2;
  }
  
  public String[] getSequenceRestartSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Iterator iterator = this.sequenceLookup.map.values().iterator();
    while (iterator.hasNext()) {
      NumberSequence numberSequence = (NumberSequence)iterator.next();
      String str = numberSequence.getRestartSQL();
      hsqlArrayList.add(str);
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String[] getTriggerSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Iterator iterator = this.tableLookup.map.values().iterator();
    while (iterator.hasNext()) {
      Table table = (Table)iterator.next();
      String[] arrayOfString1 = table.getTriggerSQL();
      hsqlArrayList.addAll((Object[])arrayOfString1);
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public void addSimpleObjects(OrderedHashSet paramOrderedHashSet) {
    Iterator iterator = this.specificRoutineLookup.map.values().iterator();
    while (iterator.hasNext()) {
      Routine routine = (Routine)iterator.next();
      if (routine.dataImpact == 1 || routine.dataImpact == 2)
        paramOrderedHashSet.add(routine); 
    } 
    paramOrderedHashSet.addAll(this.typeLookup.map.values());
    paramOrderedHashSet.addAll(this.charsetLookup.map.values());
    paramOrderedHashSet.addAll(this.collationLookup.map.values());
  }
  
  boolean isEmpty() {
    return (this.sequenceLookup.isEmpty() && this.tableLookup.isEmpty() && this.typeLookup.isEmpty() && this.charsetLookup.isEmpty() && this.collationLookup.isEmpty() && this.specificRoutineLookup.isEmpty());
  }
  
  public SchemaObjectSet getObjectSet(int paramInt) {
    switch (paramInt) {
      case 7:
        return this.sequenceLookup;
      case 3:
      case 4:
        return this.tableLookup;
      case 14:
        return this.charsetLookup;
      case 15:
        return this.collationLookup;
      case 17:
        return this.procedureLookup;
      case 16:
        return this.functionLookup;
      case 18:
        return this.functionLookup;
      case 24:
        return this.specificRoutineLookup;
      case 12:
      case 13:
        return this.typeLookup;
      case 6:
        return this.assertionLookup;
      case 8:
        return this.triggerLookup;
      case 20:
        return this.indexLookup;
      case 5:
        return this.constraintLookup;
    } 
    throw Error.runtimeError(201, "Schema");
  }
  
  Iterator schemaObjectIterator(int paramInt) {
    Iterator iterator;
    switch (paramInt) {
      case 7:
        return this.sequenceLookup.map.values().iterator();
      case 3:
      case 4:
        return this.tableLookup.map.values().iterator();
      case 14:
        return this.charsetLookup.map.values().iterator();
      case 15:
        return this.collationLookup.map.values().iterator();
      case 17:
        return this.procedureLookup.map.values().iterator();
      case 16:
        return this.functionLookup.map.values().iterator();
      case 18:
        iterator = this.functionLookup.map.values().iterator();
        return (Iterator)new WrapperIterator(iterator, this.procedureLookup.map.values().iterator());
      case 24:
        return this.specificRoutineLookup.map.values().iterator();
      case 12:
      case 13:
        return this.typeLookup.map.values().iterator();
      case 6:
        return this.assertionLookup.map.values().iterator();
      case 8:
        return this.triggerLookup.map.values().iterator();
      case 20:
        return this.indexLookup.map.values().iterator();
      case 5:
        return this.constraintLookup.map.values().iterator();
    } 
    throw Error.runtimeError(201, "Schema");
  }
  
  void release() {
    for (byte b = 0; b < this.tableList.size(); b++) {
      Table table = (Table)this.tableList.get(b);
      table.terminateTriggers();
    } 
    this.tableList.clear();
    this.sequenceList.clear();
    this.triggerLookup = null;
    this.indexLookup = null;
    this.constraintLookup = null;
    this.procedureLookup = null;
    this.functionLookup = null;
    this.sequenceLookup = null;
    this.tableLookup = null;
    this.typeLookup = null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */