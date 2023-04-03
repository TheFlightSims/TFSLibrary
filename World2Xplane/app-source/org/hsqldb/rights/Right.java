package org.hsqldb.rights;

import org.hsqldb.HsqlNameManager;
import org.hsqldb.SchemaObject;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;

public final class Right {
  boolean isFull = false;
  
  boolean isFullSelect;
  
  boolean isFullInsert;
  
  boolean isFullUpdate;
  
  boolean isFullReferences;
  
  boolean isFullTrigger;
  
  boolean isFullDelete;
  
  OrderedHashSet selectColumnSet;
  
  OrderedHashSet insertColumnSet;
  
  OrderedHashSet updateColumnSet;
  
  OrderedHashSet referencesColumnSet;
  
  OrderedHashSet triggerColumnSet;
  
  Right grantableRights;
  
  Grantee grantor;
  
  Grantee grantee;
  
  public static final OrderedHashSet emptySet = new OrderedHashSet();
  
  public static final Right fullRights = new Right();
  
  public static final Right noRights = new Right();
  
  static final OrderedHashSet fullRightsSet = new OrderedHashSet();
  
  public static final String[] privilegeNames;
  
  public static final int[] privilegeTypes;
  
  public Right() {}
  
  Right(Table paramTable) {
    this.isFullDelete = true;
    this.selectColumnSet = paramTable.getColumnNameSet();
    this.insertColumnSet = paramTable.getColumnNameSet();
    this.updateColumnSet = paramTable.getColumnNameSet();
    this.referencesColumnSet = paramTable.getColumnNameSet();
    this.triggerColumnSet = paramTable.getColumnNameSet();
  }
  
  public boolean isFull() {
    return this.isFull;
  }
  
  public Grantee getGrantor() {
    return this.grantor;
  }
  
  public Grantee getGrantee() {
    return this.grantee;
  }
  
  public Right getGrantableRights() {
    return (this.grantableRights == null) ? noRights : this.grantableRights;
  }
  
  public Right duplicate() {
    Right right = new Right();
    right.add(this);
    return right;
  }
  
  public void add(Right paramRight) {
    if (this.isFull)
      return; 
    if (paramRight.isFull) {
      clear();
      this.isFull = true;
      return;
    } 
    this.isFullSelect |= paramRight.isFullSelect;
    this.isFullInsert |= paramRight.isFullInsert;
    this.isFullUpdate |= paramRight.isFullUpdate;
    this.isFullReferences |= paramRight.isFullReferences;
    this.isFullDelete |= paramRight.isFullDelete;
    if (this.isFullSelect) {
      this.selectColumnSet = null;
    } else if (paramRight.selectColumnSet != null) {
      if (this.selectColumnSet == null)
        this.selectColumnSet = new OrderedHashSet(); 
      this.selectColumnSet.addAll((Collection)paramRight.selectColumnSet);
    } 
    if (this.isFullInsert) {
      this.insertColumnSet = null;
    } else if (paramRight.insertColumnSet != null) {
      if (this.insertColumnSet == null)
        this.insertColumnSet = new OrderedHashSet(); 
      this.insertColumnSet.addAll((Collection)paramRight.insertColumnSet);
    } 
    if (this.isFullUpdate) {
      this.updateColumnSet = null;
    } else if (paramRight.updateColumnSet != null) {
      if (this.updateColumnSet == null)
        this.updateColumnSet = new OrderedHashSet(); 
      this.updateColumnSet.addAll((Collection)paramRight.updateColumnSet);
    } 
    if (this.isFullReferences) {
      this.referencesColumnSet = null;
    } else if (paramRight.referencesColumnSet != null) {
      if (this.referencesColumnSet == null)
        this.referencesColumnSet = new OrderedHashSet(); 
      this.referencesColumnSet.addAll((Collection)paramRight.referencesColumnSet);
    } 
    if (this.isFullTrigger) {
      this.triggerColumnSet = null;
    } else if (paramRight.triggerColumnSet != null) {
      if (this.triggerColumnSet == null)
        this.triggerColumnSet = new OrderedHashSet(); 
      this.triggerColumnSet.addAll((Collection)paramRight.triggerColumnSet);
    } 
  }
  
  public void remove(SchemaObject paramSchemaObject, Right paramRight) {
    if (paramRight.isFull) {
      clear();
      return;
    } 
    if (this.isFull) {
      this.isFull = false;
      this.isFullSelect = this.isFullInsert = this.isFullUpdate = this.isFullReferences = this.isFullDelete = true;
    } 
    if (paramRight.isFullDelete)
      this.isFullDelete = false; 
    if (this.isFullSelect || this.selectColumnSet != null)
      if (paramRight.isFullSelect) {
        this.isFullSelect = false;
        this.selectColumnSet = null;
      } else if (paramRight.selectColumnSet != null) {
        if (this.isFullSelect) {
          this.isFullSelect = false;
          this.selectColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        } 
        this.selectColumnSet.removeAll((Collection)paramRight.selectColumnSet);
        if (this.selectColumnSet.isEmpty())
          this.selectColumnSet = null; 
      }  
    if (this.isFullInsert || this.insertColumnSet != null)
      if (paramRight.isFullInsert) {
        this.isFullInsert = false;
        this.insertColumnSet = null;
      } else if (paramRight.insertColumnSet != null) {
        if (this.isFullInsert) {
          this.isFullInsert = false;
          this.insertColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        } 
        this.insertColumnSet.removeAll((Collection)paramRight.insertColumnSet);
        if (this.insertColumnSet.isEmpty())
          this.insertColumnSet = null; 
      }  
    if (this.isFullUpdate || this.updateColumnSet != null)
      if (paramRight.isFullUpdate) {
        this.isFullUpdate = false;
        this.updateColumnSet = null;
      } else if (paramRight.updateColumnSet != null) {
        if (this.isFullUpdate) {
          this.isFullUpdate = false;
          this.updateColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        } 
        this.updateColumnSet.removeAll((Collection)paramRight.updateColumnSet);
        if (this.updateColumnSet.isEmpty())
          this.updateColumnSet = null; 
      }  
    if (this.isFullReferences || this.referencesColumnSet != null)
      if (paramRight.isFullReferences) {
        this.isFullReferences = false;
        this.referencesColumnSet = null;
      } else if (paramRight.referencesColumnSet != null) {
        if (this.isFullReferences) {
          this.isFullReferences = false;
          this.referencesColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        } 
        this.referencesColumnSet.removeAll((Collection)paramRight.referencesColumnSet);
        if (this.referencesColumnSet.isEmpty())
          this.referencesColumnSet = null; 
      }  
    if (this.isFullTrigger || this.triggerColumnSet != null)
      if (paramRight.isFullTrigger) {
        this.isFullTrigger = false;
        this.triggerColumnSet = null;
      } else if (paramRight.triggerColumnSet != null) {
        if (this.isFullTrigger) {
          this.isFullTrigger = false;
          this.triggerColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        } 
        this.triggerColumnSet.removeAll((Collection)paramRight.triggerColumnSet);
        if (this.triggerColumnSet.isEmpty())
          this.triggerColumnSet = null; 
      }  
  }
  
  void clear() {
    this.isFull = this.isFullSelect = this.isFullInsert = this.isFullUpdate = this.isFullReferences = this.isFullDelete = false;
    this.selectColumnSet = this.insertColumnSet = this.updateColumnSet = this.referencesColumnSet = this.triggerColumnSet = null;
  }
  
  public boolean isEmpty() {
    return (this.isFull || this.isFullSelect || this.isFullInsert || this.isFullUpdate || this.isFullReferences || this.isFullDelete) ? false : ((this.selectColumnSet != null && !this.selectColumnSet.isEmpty()) ? false : ((this.insertColumnSet != null && !this.insertColumnSet.isEmpty()) ? false : ((this.updateColumnSet != null && !this.updateColumnSet.isEmpty()) ? false : ((this.referencesColumnSet != null && !this.referencesColumnSet.isEmpty()) ? false : (!(this.triggerColumnSet != null && !this.triggerColumnSet.isEmpty()))))));
  }
  
  OrderedHashSet getColumnsForAllRights(Table paramTable) {
    if (this.isFull)
      return paramTable.getColumnNameSet(); 
    if (this.isFullSelect || this.isFullInsert || this.isFullUpdate || this.isFullReferences)
      return paramTable.getColumnNameSet(); 
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    if (this.selectColumnSet != null)
      orderedHashSet.addAll((Collection)this.selectColumnSet); 
    if (this.insertColumnSet != null)
      orderedHashSet.addAll((Collection)this.insertColumnSet); 
    if (this.updateColumnSet != null)
      orderedHashSet.addAll((Collection)this.updateColumnSet); 
    if (this.referencesColumnSet != null)
      orderedHashSet.addAll((Collection)this.referencesColumnSet); 
    return orderedHashSet;
  }
  
  public boolean contains(Right paramRight) {
    return this.isFull ? true : (paramRight.isFull ? false : (!containsRights(this.isFullSelect, this.selectColumnSet, paramRight.selectColumnSet, paramRight.isFullSelect) ? false : (!containsRights(this.isFullInsert, this.insertColumnSet, paramRight.insertColumnSet, paramRight.isFullInsert) ? false : (!containsRights(this.isFullUpdate, this.updateColumnSet, paramRight.updateColumnSet, paramRight.isFullUpdate) ? false : (!containsRights(this.isFullReferences, this.referencesColumnSet, paramRight.referencesColumnSet, paramRight.isFullReferences) ? false : (!containsRights(this.isFullTrigger, this.triggerColumnSet, paramRight.triggerColumnSet, paramRight.isFullTrigger) ? false : (!(!this.isFullDelete && paramRight.isFullDelete))))))));
  }
  
  void removeDroppedColumns(OrderedHashSet paramOrderedHashSet, Table paramTable) {
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(b);
      if (paramTable.findColumn(hsqlName.name) >= 0) {
        paramOrderedHashSet.remove(b);
        b--;
      } 
    } 
  }
  
  public OrderedHashSet getColumnsForPrivilege(Table paramTable, int paramInt) {
    if (this.isFull)
      return paramTable.getColumnNameSet(); 
    switch (paramInt) {
      case 1:
        return this.isFullSelect ? paramTable.getColumnNameSet() : ((this.selectColumnSet == null) ? emptySet : this.selectColumnSet);
      case 4:
        return this.isFullInsert ? paramTable.getColumnNameSet() : ((this.insertColumnSet == null) ? emptySet : this.insertColumnSet);
      case 8:
        return this.isFullUpdate ? paramTable.getColumnNameSet() : ((this.updateColumnSet == null) ? emptySet : this.updateColumnSet);
      case 64:
        return this.isFullReferences ? paramTable.getColumnNameSet() : ((this.referencesColumnSet == null) ? emptySet : this.referencesColumnSet);
      case 128:
        return this.isFullTrigger ? paramTable.getColumnNameSet() : ((this.triggerColumnSet == null) ? emptySet : this.triggerColumnSet);
    } 
    return emptySet;
  }
  
  static boolean containsAllColumns(OrderedHashSet paramOrderedHashSet, Table paramTable, boolean[] paramArrayOfboolean) {
    for (byte b = 0; b < paramArrayOfboolean.length; b++) {
      if (paramArrayOfboolean[b]) {
        if (paramOrderedHashSet == null)
          return false; 
        if (!paramOrderedHashSet.contains(paramTable.getColumn(b).getName()))
          return false; 
      } 
    } 
    return true;
  }
  
  static boolean containsRights(boolean paramBoolean1, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, boolean paramBoolean2) {
    return paramBoolean1 ? true : (paramBoolean2 ? false : (!(paramOrderedHashSet2 != null && (paramOrderedHashSet1 == null || !paramOrderedHashSet1.containsAll((Collection)paramOrderedHashSet2)))));
  }
  
  boolean canSelect(Table paramTable, boolean[] paramArrayOfboolean) {
    return (this.isFull || this.isFullSelect) ? true : containsAllColumns(this.selectColumnSet, paramTable, paramArrayOfboolean);
  }
  
  boolean canInsert(Table paramTable, boolean[] paramArrayOfboolean) {
    return (this.isFull || this.isFullInsert) ? true : containsAllColumns(this.insertColumnSet, paramTable, paramArrayOfboolean);
  }
  
  boolean canUpdate(Table paramTable, boolean[] paramArrayOfboolean) {
    return (this.isFull || this.isFullUpdate) ? true : containsAllColumns(this.updateColumnSet, paramTable, paramArrayOfboolean);
  }
  
  boolean canReference(Table paramTable, boolean[] paramArrayOfboolean) {
    return (this.isFull || this.isFullReferences) ? true : containsAllColumns(this.referencesColumnSet, paramTable, paramArrayOfboolean);
  }
  
  boolean canTrigger(Table paramTable, boolean[] paramArrayOfboolean) {
    return (this.isFull || this.isFullTrigger) ? true : containsAllColumns(this.triggerColumnSet, paramTable, paramArrayOfboolean);
  }
  
  boolean canDelete() {
    return (this.isFull || this.isFullDelete);
  }
  
  public boolean canAccessFully(int paramInt) {
    if (this.isFull)
      return true; 
    switch (paramInt) {
      case 2:
        return this.isFullDelete;
      case 1:
        return this.isFullSelect;
      case 4:
        return this.isFullInsert;
      case 8:
        return this.isFullUpdate;
      case 64:
        return this.isFullReferences;
      case 128:
        return this.isFullTrigger;
      case 32:
        return this.isFull;
    } 
    throw Error.runtimeError(201, "Right");
  }
  
  public boolean canAcesssNonSelect() {
    if (this.isFull)
      return true; 
    if (this.isFullInsert || this.isFullUpdate || this.isFullDelete || this.isFullReferences || this.isFullTrigger)
      return true; 
    int i = 0;
    i |= (this.insertColumnSet != null && !this.insertColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.updateColumnSet != null && !this.updateColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.referencesColumnSet != null && !this.referencesColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.triggerColumnSet != null && !this.triggerColumnSet.isEmpty()) ? 1 : 0;
    return i;
  }
  
  public boolean canAccess(int paramInt) {
    if (this.isFull)
      return true; 
    switch (paramInt) {
      case 2:
        return this.isFullDelete;
      case 1:
        return this.isFullSelect ? true : ((this.selectColumnSet != null && !this.selectColumnSet.isEmpty()));
      case 4:
        return this.isFullInsert ? true : ((this.insertColumnSet != null && !this.insertColumnSet.isEmpty()));
      case 8:
        return this.isFullUpdate ? true : ((this.updateColumnSet != null && !this.updateColumnSet.isEmpty()));
      case 64:
        return this.isFullReferences ? true : ((this.referencesColumnSet != null && !this.referencesColumnSet.isEmpty()));
      case 128:
        return this.isFullTrigger ? true : ((this.triggerColumnSet != null && !this.triggerColumnSet.isEmpty()));
      case 32:
        return this.isFull;
    } 
    throw Error.runtimeError(201, "Right");
  }
  
  public boolean canAccess(Table paramTable, int[] paramArrayOfint) {
    if (this.isFull)
      return true; 
    if (this.isFullSelect || this.isFullInsert || this.isFullUpdate || this.isFullDelete || this.isFullReferences || this.isFullTrigger)
      return true; 
    int i = 0;
    i |= (this.selectColumnSet != null && this.insertColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.insertColumnSet != null && this.insertColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.updateColumnSet != null && !this.updateColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.referencesColumnSet != null && !this.referencesColumnSet.isEmpty()) ? 1 : 0;
    i |= (this.triggerColumnSet != null && !this.triggerColumnSet.isEmpty()) ? 1 : 0;
    if (i == 0)
      return false; 
    HashSet hashSet = new HashSet();
    hashSet.addAll((Collection)this.selectColumnSet);
    hashSet.addAll((Collection)this.insertColumnSet);
    hashSet.addAll((Collection)this.updateColumnSet);
    hashSet.addAll((Collection)this.referencesColumnSet);
    hashSet.addAll((Collection)this.triggerColumnSet);
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (!hashSet.contains(paramTable.getColumn(b).getName()))
        return false; 
    } 
    return i;
  }
  
  String getTableRightsSQL(Table paramTable) {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.isFull)
      return "ALL"; 
    if (this.isFullSelect) {
      stringBuffer.append("SELECT");
      stringBuffer.append(',');
    } else if (this.selectColumnSet != null) {
      stringBuffer.append("SELECT");
      getColumnList(paramTable, this.selectColumnSet, stringBuffer);
      stringBuffer.append(',');
    } 
    if (this.isFullInsert) {
      stringBuffer.append("INSERT");
      stringBuffer.append(',');
    } else if (this.insertColumnSet != null) {
      stringBuffer.append("INSERT");
      getColumnList(paramTable, this.insertColumnSet, stringBuffer);
      stringBuffer.append(',');
    } 
    if (this.isFullUpdate) {
      stringBuffer.append("UPDATE");
      stringBuffer.append(',');
    } else if (this.updateColumnSet != null) {
      stringBuffer.append("UPDATE");
      getColumnList(paramTable, this.updateColumnSet, stringBuffer);
      stringBuffer.append(',');
    } 
    if (this.isFullDelete) {
      stringBuffer.append("DELETE");
      stringBuffer.append(',');
    } 
    if (this.isFullReferences) {
      stringBuffer.append("REFERENCES");
      stringBuffer.append(',');
    } else if (this.referencesColumnSet != null) {
      stringBuffer.append("REFERENCES");
      stringBuffer.append(',');
    } 
    if (this.isFullTrigger) {
      stringBuffer.append("TRIGGER");
      stringBuffer.append(',');
    } else if (this.triggerColumnSet != null) {
      stringBuffer.append("TRIGGER");
      stringBuffer.append(',');
    } 
    return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
  }
  
  private static void getColumnList(Table paramTable, OrderedHashSet paramOrderedHashSet, StringBuffer paramStringBuffer) {
    byte b1 = 0;
    boolean[] arrayOfBoolean = paramTable.getNewColumnCheckList();
    byte b2;
    for (b2 = 0; b2 < paramOrderedHashSet.size(); b2++) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(b2);
      int i = paramTable.findColumn(hsqlName.name);
      if (i != -1) {
        arrayOfBoolean[i] = true;
        b1++;
      } 
    } 
    if (b1 == 0)
      throw Error.runtimeError(201, "Right"); 
    paramStringBuffer.append('(');
    b2 = 0;
    byte b3 = 0;
    while (b2 < arrayOfBoolean.length) {
      if (arrayOfBoolean[b2]) {
        b3++;
        paramStringBuffer.append((paramTable.getColumn(b2).getName()).statementName);
        if (b3 < b1)
          paramStringBuffer.append(','); 
      } 
      b2++;
    } 
    paramStringBuffer.append(')');
  }
  
  public void setColumns(Table paramTable) {
    if (this.selectColumnSet != null)
      setColumns(paramTable, this.selectColumnSet); 
    if (this.insertColumnSet != null)
      setColumns(paramTable, this.insertColumnSet); 
    if (this.updateColumnSet != null)
      setColumns(paramTable, this.updateColumnSet); 
    if (this.referencesColumnSet != null)
      setColumns(paramTable, this.referencesColumnSet); 
    if (this.triggerColumnSet != null)
      setColumns(paramTable, this.triggerColumnSet); 
  }
  
  private static void setColumns(Table paramTable, OrderedHashSet paramOrderedHashSet) {
    byte b1 = 0;
    boolean[] arrayOfBoolean = paramTable.getNewColumnCheckList();
    byte b2;
    for (b2 = 0; b2 < paramOrderedHashSet.size(); b2++) {
      String str = (String)paramOrderedHashSet.get(b2);
      int i = paramTable.findColumn(str);
      if (i == -1)
        throw Error.error(5501, str); 
      arrayOfBoolean[i] = true;
      b1++;
    } 
    if (b1 == 0)
      throw Error.error(5501); 
    paramOrderedHashSet.clear();
    for (b2 = 0; b2 < arrayOfBoolean.length; b2++) {
      if (arrayOfBoolean[b2])
        paramOrderedHashSet.add(paramTable.getColumn(b2).getName()); 
    } 
  }
  
  public void set(int paramInt, OrderedHashSet paramOrderedHashSet) {
    switch (paramInt) {
      case 1:
        if (paramOrderedHashSet == null)
          this.isFullSelect = true; 
        this.selectColumnSet = paramOrderedHashSet;
        break;
      case 2:
        if (paramOrderedHashSet == null)
          this.isFullDelete = true; 
        break;
      case 4:
        if (paramOrderedHashSet == null)
          this.isFullInsert = true; 
        this.insertColumnSet = paramOrderedHashSet;
        break;
      case 8:
        if (paramOrderedHashSet == null)
          this.isFullUpdate = true; 
        this.updateColumnSet = paramOrderedHashSet;
        break;
      case 64:
        if (paramOrderedHashSet == null)
          this.isFullReferences = true; 
        this.referencesColumnSet = paramOrderedHashSet;
        break;
      case 128:
        if (paramOrderedHashSet == null)
          this.isFullTrigger = true; 
        this.triggerColumnSet = paramOrderedHashSet;
        break;
    } 
  }
  
  String[] getTableRightsArray() {
    if (this.isFull)
      return new String[] { "SELECT", "INSERT", "UPDATE", "DELETE", "REFERENCES" }; 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    String[] arrayOfString = new String[hsqlArrayList.size()];
    if (this.isFullSelect)
      hsqlArrayList.add("SELECT"); 
    if (this.isFullInsert)
      hsqlArrayList.add("INSERT"); 
    if (this.isFullUpdate)
      hsqlArrayList.add("UPDATE"); 
    if (this.isFullDelete)
      hsqlArrayList.add("DELETE"); 
    if (this.isFullReferences)
      hsqlArrayList.add("REFERENCES"); 
    if (this.isFullTrigger)
      hsqlArrayList.add("TRIGGER"); 
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  static {
    fullRights.grantor = GranteeManager.systemAuthorisation;
    fullRights.isFull = true;
    fullRightsSet.add(fullRights);
    privilegeNames = new String[] { "SELECT", "INSERT", "UPDATE", "DELETE", "REFERENCES", "TRIGGER" };
    privilegeTypes = new int[] { 1, 4, 8, 2, 64, 128 };
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rights\Right.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */