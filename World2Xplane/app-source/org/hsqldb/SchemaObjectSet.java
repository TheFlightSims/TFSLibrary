package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;

public class SchemaObjectSet {
  HashMap map;
  
  int type;
  
  SchemaObjectSet(int paramInt) {
    this.type = paramInt;
    switch (paramInt) {
      case 3:
      case 4:
      case 6:
      case 7:
      case 8:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 24:
        this.map = (HashMap)new HashMappedList();
        break;
      case 5:
      case 9:
      case 20:
        this.map = new HashMap();
        break;
    } 
  }
  
  HsqlNameManager.HsqlName getName(String paramString) {
    SchemaObject schemaObject;
    switch (this.type) {
      case 3:
      case 4:
      case 6:
      case 7:
      case 8:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 24:
        schemaObject = (SchemaObject)this.map.get(paramString);
        return (schemaObject == null) ? null : schemaObject.getName();
      case 5:
      case 9:
      case 20:
        return (HsqlNameManager.HsqlName)this.map.get(paramString);
    } 
    return (HsqlNameManager.HsqlName)this.map.get(paramString);
  }
  
  public SchemaObject getObject(String paramString) {
    switch (this.type) {
      case 3:
      case 4:
      case 6:
      case 7:
      case 8:
      case 9:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 24:
        return (SchemaObject)this.map.get(paramString);
    } 
    throw Error.runtimeError(201, "SchemaObjectSet");
  }
  
  public boolean contains(String paramString) {
    return this.map.containsKey(paramString);
  }
  
  void checkAdd(HsqlNameManager.HsqlName paramHsqlName) {
    if (this.map.containsKey(paramHsqlName.name)) {
      int i = getAddErrorCode(paramHsqlName.type);
      throw Error.error(i, paramHsqlName.name);
    } 
  }
  
  boolean isEmpty() {
    return this.map.isEmpty();
  }
  
  void checkExists(String paramString) {
    if (!this.map.containsKey(paramString)) {
      int i = getGetErrorCode(this.type);
      throw Error.error(i, paramString);
    } 
  }
  
  public void add(SchemaObject paramSchemaObject) {
    HsqlNameManager.HsqlName hsqlName2;
    HsqlNameManager.HsqlName hsqlName1 = paramSchemaObject.getName();
    if (this.type == 24)
      hsqlName1 = ((Routine)paramSchemaObject).getSpecificName(); 
    if (this.map.containsKey(hsqlName1.name)) {
      int i = getAddErrorCode(hsqlName1.type);
      throw Error.error(i, hsqlName1.name);
    } 
    SchemaObject schemaObject = paramSchemaObject;
    switch (hsqlName1.type) {
      case 5:
      case 20:
        hsqlName2 = hsqlName1;
        break;
    } 
    this.map.put(hsqlName1.name, hsqlName2);
  }
  
  void remove(String paramString) {
    this.map.remove(paramString);
  }
  
  void removeParent(HsqlNameManager.HsqlName paramHsqlName) {
    Iterator iterator = this.map.values().iterator();
    while (iterator.hasNext()) {
      if (this.type == 8 || this.type == 24) {
        SchemaObject schemaObject = (SchemaObject)iterator.next();
        if ((schemaObject.getName()).parent == paramHsqlName)
          iterator.remove(); 
        continue;
      } 
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
      if (hsqlName.parent == paramHsqlName)
        iterator.remove(); 
    } 
  }
  
  void rename(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2) {
    int i;
    SchemaObject schemaObject;
    if (this.map.containsKey(paramHsqlName2.name)) {
      int j = getAddErrorCode(paramHsqlName1.type);
      throw Error.error(j, paramHsqlName2.name);
    } 
    switch (paramHsqlName2.type) {
      case 3:
      case 4:
      case 6:
      case 7:
      case 8:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
        i = ((HashMappedList)this.map).getIndex(paramHsqlName1.name);
        if (i == -1) {
          int j = getGetErrorCode(paramHsqlName1.type);
          throw Error.error(j, paramHsqlName1.name);
        } 
        schemaObject = (SchemaObject)((HashMappedList)this.map).get(i);
        schemaObject.getName().rename(paramHsqlName2);
        ((HashMappedList)this.map).setKey(i, paramHsqlName1.name);
        break;
      case 5:
      case 9:
      case 20:
        this.map.remove(paramHsqlName1.name);
        paramHsqlName1.rename(paramHsqlName2);
        this.map.put(paramHsqlName1.name, paramHsqlName1);
        break;
    } 
  }
  
  static int getAddErrorCode(int paramInt) {
    switch (paramInt) {
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 20:
      case 24:
        return 5504;
    } 
    throw Error.runtimeError(201, "SchemaObjectSet");
  }
  
  static int getGetErrorCode(int paramInt) {
    switch (paramInt) {
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 20:
      case 24:
        return 5501;
    } 
    throw Error.runtimeError(201, "SchemaObjectSet");
  }
  
  public static String getName(int paramInt) {
    switch (paramInt) {
      case 4:
        return "VIEW";
      case 9:
        return "COLUMN";
      case 3:
        return "TABLE";
      case 7:
        return "SEQUENCE";
      case 14:
        return "CHARACTER SET";
      case 13:
        return "DOMAIN";
      case 12:
        return "TYPE";
      case 5:
        return "CONSTRAINT";
      case 15:
        return "COLLATION";
      case 17:
        return "PROCEDURE";
      case 16:
        return "FUNCTION";
      case 6:
        return "ASSERTION";
      case 20:
        return "INDEX";
      case 8:
        return "TRIGGER";
    } 
    throw Error.runtimeError(201, "SchemaObjectSet");
  }
  
  String[] getSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    if (!(this.map instanceof HashMappedList))
      return null; 
    if (this.map.isEmpty())
      return ValuePool.emptyStringArray; 
    Iterator iterator = this.map.values().iterator();
    if (this.type == 16 || this.type == 17) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      while (iterator.hasNext()) {
        RoutineSchema routineSchema = (RoutineSchema)iterator.next();
        for (byte b = 0; b < routineSchema.routines.length; b++) {
          Routine routine = routineSchema.routines[b];
          if (routine.dataImpact != 1 && routine.dataImpact != 2)
            orderedHashSet.add(routine); 
        } 
      } 
      iterator = orderedHashSet.iterator();
    } 
    addAllSQL(paramOrderedHashSet1, paramOrderedHashSet2, hsqlArrayList, iterator, null);
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  static void addAllSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, HsqlArrayList paramHsqlArrayList, Iterator paramIterator, OrderedHashSet paramOrderedHashSet3) {
    while (paramIterator.hasNext()) {
      HsqlNameManager.HsqlName hsqlName;
      SchemaObject schemaObject = (SchemaObject)paramIterator.next();
      OrderedHashSet orderedHashSet = schemaObject.getReferences();
      boolean bool = true;
      for (byte b = 0; b < orderedHashSet.size(); b++) {
        HsqlNameManager.HsqlName hsqlName1 = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
        if (!SqlInvariants.isSchemaNameSystem(hsqlName1))
          switch (hsqlName1.type) {
            case 3:
              if (!paramOrderedHashSet1.contains(hsqlName1))
                bool = false; 
              break;
            case 9:
              if (schemaObject.getType() == 3) {
                int i = ((Table)schemaObject).findColumn(hsqlName1.name);
                ColumnSchema columnSchema = ((Table)schemaObject).getColumn(i);
                if (!isChildObjectResolved(columnSchema, paramOrderedHashSet1))
                  bool = false; 
                break;
              } 
              if (!paramOrderedHashSet1.contains(hsqlName1.parent))
                bool = false; 
              break;
            case 5:
              if (hsqlName1.parent == schemaObject.getName()) {
                Constraint constraint = ((Table)schemaObject).getConstraint(hsqlName1.name);
                if (constraint.getConstraintType() == 3 && !isChildObjectResolved(constraint, paramOrderedHashSet1))
                  bool = false; 
              } 
              break;
            case 14:
              if (hsqlName1.schema == null)
                break; 
            case 12:
            case 13:
            case 16:
            case 17:
            case 24:
              if (!paramOrderedHashSet1.contains(hsqlName1))
                bool = false; 
              break;
          }  
      } 
      if (!bool) {
        paramOrderedHashSet2.add(schemaObject);
        continue;
      } 
      if (schemaObject.getType() == 16 || schemaObject.getType() == 17) {
        hsqlName = ((Routine)schemaObject).getSpecificName();
      } else {
        hsqlName = schemaObject.getName();
      } 
      paramOrderedHashSet1.add(hsqlName);
      if (paramOrderedHashSet3 != null)
        paramOrderedHashSet3.add(schemaObject); 
      if (schemaObject.getType() == 3) {
        paramHsqlArrayList.addAll((Object[])((Table)schemaObject).getSQL(paramOrderedHashSet1, paramOrderedHashSet2));
        continue;
      } 
      switch (schemaObject.getType()) {
        case 16:
        case 17:
          if (((Routine)schemaObject).isRecursive) {
            paramHsqlArrayList.add(((Routine)schemaObject).getSQLDeclaration());
            paramHsqlArrayList.add(((Routine)schemaObject).getSQLAlter());
            continue;
          } 
          break;
      } 
      paramHsqlArrayList.add(schemaObject.getSQL());
    } 
  }
  
  static boolean isChildObjectResolved(SchemaObject paramSchemaObject, OrderedHashSet paramOrderedHashSet) {
    OrderedHashSet orderedHashSet = paramSchemaObject.getReferences();
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)orderedHashSet.get(b);
      if (!SqlInvariants.isSchemaNameSystem(hsqlName) && !paramOrderedHashSet.contains(hsqlName))
        return false; 
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SchemaObjectSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */