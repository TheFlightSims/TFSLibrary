package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public class RoutineSchema implements SchemaObject {
  static RoutineSchema[] emptyArray = new RoutineSchema[0];
  
  Routine[] routines = Routine.emptyArray;
  
  int routineType;
  
  private HsqlNameManager.HsqlName name;
  
  public RoutineSchema(int paramInt, HsqlNameManager.HsqlName paramHsqlName) {
    this.routineType = paramInt;
    this.name = paramHsqlName;
  }
  
  public int getType() {
    return this.routineType;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    for (byte b = 0; b < this.routines.length; b++)
      orderedHashSet.addAll((Collection)this.routines[b].getReferences()); 
    return orderedHashSet;
  }
  
  public OrderedHashSet getComponents() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    orderedHashSet.addAll((Object[])this.routines);
    return orderedHashSet;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    return null;
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public String[] getSQLArray() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    for (byte b = 0; b < this.routines.length; b++)
      hsqlArrayList.add(this.routines[b].getSQL()); 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public void addSpecificRoutine(Database paramDatabase, Routine paramRoutine) {
    int i = paramRoutine.getParameterSignature();
    Type[] arrayOfType = paramRoutine.getParameterTypes();
    for (byte b = 0; b < this.routines.length; b++) {
      if ((this.routines[b]).parameterTypes.length == arrayOfType.length) {
        if (this.routineType == 17)
          throw Error.error(5605); 
        if (this.routines[b].isAggregate() != paramRoutine.isAggregate())
          throw Error.error(5605); 
        boolean bool = true;
        for (byte b1 = 0; b1 < arrayOfType.length; b1++) {
          if (!(this.routines[b]).parameterTypes[b1].equals(arrayOfType[b1])) {
            bool = false;
            break;
          } 
        } 
        if (bool)
          throw Error.error(5605); 
      } 
    } 
    if (paramRoutine.getSpecificName() == null) {
      HsqlNameManager.HsqlName hsqlName = paramDatabase.nameManager.newSpecificRoutineName(this.name);
      paramRoutine.setSpecificName(hsqlName);
    } else {
      (paramRoutine.getSpecificName()).parent = this.name;
      (paramRoutine.getSpecificName()).schema = this.name.schema;
    } 
    paramRoutine.setName(this.name);
    paramRoutine.routineSchema = this;
    this.routines = (Routine[])ArrayUtil.resizeArray(this.routines, this.routines.length + 1);
    this.routines[this.routines.length - 1] = paramRoutine;
  }
  
  public void removeSpecificRoutine(Routine paramRoutine) {
    for (byte b = 0; b < this.routines.length; b++) {
      if (this.routines[b] == paramRoutine) {
        this.routines = (Routine[])ArrayUtil.toAdjustedArray(this.routines, null, b, -1);
        break;
      } 
    } 
  }
  
  public Routine[] getSpecificRoutines() {
    return this.routines;
  }
  
  public Routine getSpecificRoutine(Type[] paramArrayOfType) {
    Routine routine = findSpecificRoutine(paramArrayOfType);
    if (routine == null) {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(this.name.getSchemaQualifiedStatementName());
      stringBuffer.append("(");
      for (byte b = 0; b < paramArrayOfType.length; b++) {
        if (b != 0)
          stringBuffer.append(","); 
        stringBuffer.append(paramArrayOfType[b].getNameString());
      } 
      stringBuffer.append(")");
      throw Error.error(5609, stringBuffer.toString());
    } 
    return routine;
  }
  
  public Routine findSpecificRoutine(Type[] paramArrayOfType) {
    byte b = -1;
    byte b1;
    label64: for (b1 = 0; b1 < this.routines.length; b1++) {
      int i = 0;
      if (this.routines[b1].isAggregate() && paramArrayOfType.length == 1) {
        if (paramArrayOfType[0] == null)
          return this.routines[b1]; 
        int j = paramArrayOfType[0].precedenceDegree((this.routines[b1]).parameterTypes[0]);
        if (j < -128) {
          if (b != -1) {
            int k = paramArrayOfType[0].precedenceDegree((this.routines[b]).parameterTypes[0]);
            int m = paramArrayOfType[0].precedenceDegree((this.routines[b1]).parameterTypes[0]);
            if (k != m && m < k)
              b = b1; 
          } 
        } else {
          if (j == 0)
            return this.routines[b1]; 
          b = b1;
        } 
      } else if ((this.routines[b1]).parameterTypes.length == paramArrayOfType.length) {
        if (paramArrayOfType.length == 0)
          return this.routines[b1]; 
        byte b2;
        for (b2 = 0; b2 < paramArrayOfType.length; b2++) {
          if (paramArrayOfType[b2] != null) {
            int j = paramArrayOfType[b2].precedenceDegree((this.routines[b1]).parameterTypes[b2]);
            if (j < -128)
              continue label64; 
            if (j == 0 && i == b2)
              i = b2 + 1; 
          } 
        } 
        if (i == paramArrayOfType.length)
          return this.routines[b1]; 
        if (b == -1) {
          b = b1;
        } else {
          for (b2 = 0; b2 < paramArrayOfType.length; b2++) {
            if (paramArrayOfType[b2] != null) {
              int j = paramArrayOfType[b2].precedenceDegree((this.routines[b]).parameterTypes[b2]);
              int k = paramArrayOfType[b2].precedenceDegree((this.routines[b1]).parameterTypes[b2]);
              if (j != k) {
                if (k < j)
                  b = b1; 
                break;
              } 
            } 
          } 
        } 
      } 
    } 
    return (b < 0) ? null : this.routines[b];
  }
  
  public Routine getSpecificRoutine(int paramInt) {
    for (byte b = 0; b < this.routines.length; b++) {
      if ((this.routines[b]).parameterTypes.length == paramInt)
        return this.routines[b]; 
    } 
    throw Error.error(5501);
  }
  
  public boolean isAggregate() {
    return (this.routines[0]).isAggregate;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RoutineSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */