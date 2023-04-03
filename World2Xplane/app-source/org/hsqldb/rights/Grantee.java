package org.hsqldb.rights;

import org.hsqldb.HsqlNameManager;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.types.Type;

public class Grantee implements SchemaObject {
  boolean isRole;
  
  private boolean isAdminDirect = false;
  
  private boolean isAdmin = false;
  
  boolean isSchemaCreator = false;
  
  boolean isPublic = false;
  
  boolean isSystem = false;
  
  protected HsqlNameManager.HsqlName granteeName;
  
  private MultiValueHashMap directRightsMap = new MultiValueHashMap();
  
  HashMap fullRightsMap = new HashMap();
  
  OrderedHashSet roles;
  
  private MultiValueHashMap grantedRightsMap = new MultiValueHashMap();
  
  protected GranteeManager granteeManager;
  
  protected Right ownerRights;
  
  Grantee(HsqlNameManager.HsqlName paramHsqlName, GranteeManager paramGranteeManager) {
    this.granteeName = paramHsqlName;
    this.granteeManager = paramGranteeManager;
    this.roles = new OrderedHashSet();
    this.ownerRights = new Right();
    this.ownerRights.isFull = true;
    this.ownerRights.grantor = GranteeManager.systemAuthorisation;
    this.ownerRights.grantee = this;
  }
  
  public int getType() {
    return 11;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.granteeName;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return null;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return null;
  }
  
  public Grantee getOwner() {
    return null;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ').append("ROLE");
    stringBuffer.append(' ').append(this.granteeName.statementName);
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public boolean isRole() {
    return this.isRole;
  }
  
  public boolean isSystem() {
    return this.isSystem;
  }
  
  public OrderedHashSet getDirectRoles() {
    return this.roles;
  }
  
  public OrderedHashSet getAllRoles() {
    OrderedHashSet orderedHashSet = getGranteeAndAllRoles();
    orderedHashSet.remove(this);
    return orderedHashSet;
  }
  
  public OrderedHashSet getGranteeAndAllRoles() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    addGranteeAndRoles(orderedHashSet);
    return orderedHashSet;
  }
  
  public OrderedHashSet getGranteeAndAllRolesWithPublic() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    addGranteeAndRoles(orderedHashSet);
    orderedHashSet.add(this.granteeManager.publicRole);
    return orderedHashSet;
  }
  
  public boolean isAccessible(HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    if (isFullyAccessibleByRole(paramHsqlName))
      return true; 
    Right right = (Right)this.fullRightsMap.get(paramHsqlName);
    return (right == null) ? false : right.canAccess(paramInt);
  }
  
  public boolean isAccessible(SchemaObject paramSchemaObject) {
    return isAccessible(paramSchemaObject.getName());
  }
  
  public boolean isAccessible(HsqlNameManager.HsqlName paramHsqlName) {
    if (isFullyAccessibleByRole(paramHsqlName))
      return true; 
    Right right = (Right)this.fullRightsMap.get(paramHsqlName);
    return (right != null && !right.isEmpty()) ? true : (!this.isPublic ? this.granteeManager.publicRole.isAccessible(paramHsqlName) : false);
  }
  
  private OrderedHashSet addGranteeAndRoles(OrderedHashSet paramOrderedHashSet) {
    paramOrderedHashSet.add(this);
    for (byte b = 0; b < this.roles.size(); b++) {
      Grantee grantee = (Grantee)this.roles.get(b);
      if (!paramOrderedHashSet.contains(grantee))
        grantee.addGranteeAndRoles(paramOrderedHashSet); 
    } 
    return paramOrderedHashSet;
  }
  
  private boolean hasRoleDirect(Grantee paramGrantee) {
    return this.roles.contains(paramGrantee);
  }
  
  public boolean hasRole(Grantee paramGrantee) {
    return getAllRoles().contains(paramGrantee);
  }
  
  void grant(HsqlNameManager.HsqlName paramHsqlName, Right paramRight, Grantee paramGrantee, boolean paramBoolean) {
    Right right1 = paramGrantee.getAllGrantableRights(paramHsqlName);
    Right right2 = null;
    if (paramRight == Right.fullRights) {
      if (right1.isEmpty())
        return; 
      paramRight = right1;
    } else if (!right1.contains(paramRight)) {
      throw Error.error(2000);
    } 
    Iterator iterator = this.directRightsMap.get(paramHsqlName);
    while (iterator.hasNext()) {
      Right right = (Right)iterator.next();
      if (right.grantor == paramGrantee) {
        right2 = right;
        right2.add(paramRight);
        break;
      } 
    } 
    if (right2 == null) {
      right2 = paramRight.duplicate();
      right2.grantor = paramGrantee;
      right2.grantee = this;
      this.directRightsMap.put(paramHsqlName, right2);
    } 
    if (paramBoolean)
      if (right2.grantableRights == null) {
        right2.grantableRights = paramRight.duplicate();
      } else {
        right2.grantableRights.add(paramRight);
      }  
    if (!paramGrantee.isSystem())
      paramGrantee.grantedRightsMap.put(paramHsqlName, right2); 
    updateAllRights();
  }
  
  void revoke(SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean) {
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    Iterator iterator = this.directRightsMap.get(hsqlName);
    Right right = null;
    while (iterator.hasNext()) {
      right = (Right)iterator.next();
      if (right.grantor == paramGrantee)
        break; 
    } 
    if (right == null)
      return; 
    if (right.grantableRights != null)
      right.grantableRights.remove(paramSchemaObject, paramRight); 
    if (paramBoolean)
      return; 
    if (paramRight.isFull) {
      this.directRightsMap.remove(hsqlName, right);
      paramGrantee.grantedRightsMap.remove(hsqlName, right);
      updateAllRights();
      return;
    } 
    right.remove(paramSchemaObject, paramRight);
    if (right.isEmpty()) {
      this.directRightsMap.remove(hsqlName, right);
      paramGrantee.grantedRightsMap.remove(hsqlName, right);
    } 
    updateAllRights();
  }
  
  void revokeDbObject(HsqlNameManager.HsqlName paramHsqlName) {
    this.directRightsMap.remove(paramHsqlName);
    this.grantedRightsMap.remove(paramHsqlName);
    this.fullRightsMap.remove(paramHsqlName);
  }
  
  void clearPrivileges() {
    this.roles.clear();
    this.directRightsMap.clear();
    this.grantedRightsMap.clear();
    this.fullRightsMap.clear();
    this.isAdmin = false;
  }
  
  public OrderedHashSet getColumnsForAllPrivileges(SchemaObject paramSchemaObject) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return table.getColumnNameSet(); 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      return (right == null) ? Right.emptySet : right.getColumnsForAllRights(table);
    } 
    return Right.emptySet;
  }
  
  public OrderedHashSet getAllDirectPrivileges(SchemaObject paramSchemaObject) {
    if (paramSchemaObject.getOwner() == this) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      orderedHashSet.add(this.ownerRights);
      return orderedHashSet;
    } 
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    Iterator iterator = this.directRightsMap.get(hsqlName);
    if (iterator.hasNext()) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      while (iterator.hasNext())
        orderedHashSet.add(iterator.next()); 
      return orderedHashSet;
    } 
    return Right.emptySet;
  }
  
  public OrderedHashSet getAllGrantedPrivileges(SchemaObject paramSchemaObject) {
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    Iterator iterator = this.grantedRightsMap.get(hsqlName);
    if (iterator.hasNext()) {
      OrderedHashSet orderedHashSet = new OrderedHashSet();
      while (iterator.hasNext())
        orderedHashSet.add(iterator.next()); 
      return orderedHashSet;
    } 
    return Right.emptySet;
  }
  
  public void checkSelect(SchemaObject paramSchemaObject, boolean[] paramArrayOfboolean) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canSelect(table, paramArrayOfboolean))
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkInsert(SchemaObject paramSchemaObject, boolean[] paramArrayOfboolean) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canInsert(table, paramArrayOfboolean))
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkUpdate(SchemaObject paramSchemaObject, boolean[] paramArrayOfboolean) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canUpdate(table, paramArrayOfboolean))
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkReferences(SchemaObject paramSchemaObject, boolean[] paramArrayOfboolean) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canReference(table, paramArrayOfboolean))
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkTrigger(SchemaObject paramSchemaObject, boolean[] paramArrayOfboolean) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canReference(table, paramArrayOfboolean))
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkDelete(SchemaObject paramSchemaObject) {
    if (paramSchemaObject instanceof Table) {
      Table table = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(table.getName()))
        return; 
      Right right = (Right)this.fullRightsMap.get(table.getName());
      if (right != null && right.canDelete())
        return; 
    } 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkAccess(SchemaObject paramSchemaObject) {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return; 
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    Right right = (Right)this.fullRightsMap.get(hsqlName);
    if (right != null && !right.isEmpty())
      return; 
    throw Error.error(5501, (paramSchemaObject.getName()).name);
  }
  
  public void checkSchemaUpdateOrGrantRights(String paramString) {
    if (!hasSchemaUpdateOrGrantRights(paramString))
      throw Error.error(5501, paramString); 
  }
  
  public boolean hasSchemaUpdateOrGrantRights(String paramString) {
    if (isAdmin())
      return true; 
    Grantee grantee = this.granteeManager.database.schemaManager.toSchemaOwner(paramString);
    return (grantee == this) ? true : (hasRole(grantee));
  }
  
  public boolean isGrantable(SchemaObject paramSchemaObject, Right paramRight) {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true; 
    Right right = getAllGrantableRights(paramSchemaObject.getName());
    return right.contains(paramRight);
  }
  
  public boolean isGrantable(Grantee paramGrantee) {
    return this.isAdmin;
  }
  
  public boolean isFullyAccessibleByRole(HsqlNameManager.HsqlName paramHsqlName) {
    Grantee grantee;
    if (this.isAdmin)
      return true; 
    if (paramHsqlName.type == 2) {
      grantee = paramHsqlName.owner;
    } else {
      if (paramHsqlName.schema == null)
        return false; 
      grantee = paramHsqlName.schema.owner;
    } 
    return (grantee == this) ? true : (hasRole(grantee));
  }
  
  public void checkAdmin() {
    if (!isAdmin())
      throw Error.error(5507); 
  }
  
  public boolean isAdmin() {
    return this.isAdmin;
  }
  
  public boolean isSchemaCreator() {
    return (this.isAdmin || hasRole(this.granteeManager.schemaRole));
  }
  
  public boolean canChangeAuthorisation() {
    return (this.isAdmin || hasRole(this.granteeManager.changeAuthRole));
  }
  
  public boolean isPublic() {
    return this.isPublic;
  }
  
  public OrderedHashSet visibleGrantees() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    GranteeManager granteeManager = this.granteeManager;
    if (isAdmin()) {
      orderedHashSet.addAll(granteeManager.getGrantees());
    } else {
      orderedHashSet.add(this);
      Iterator iterator = getAllRoles().iterator();
      while (iterator.hasNext())
        orderedHashSet.add(iterator.next()); 
    } 
    return orderedHashSet;
  }
  
  public boolean hasNonSelectTableRight(SchemaObject paramSchemaObject) {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true; 
    Right right = (Right)this.fullRightsMap.get(paramSchemaObject.getName());
    return (right == null) ? false : right.canAcesssNonSelect();
  }
  
  public boolean hasColumnRights(SchemaObject paramSchemaObject, int[] paramArrayOfint) {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true; 
    Right right = (Right)this.fullRightsMap.get(paramSchemaObject.getName());
    return (right == null) ? false : right.canAccess((Table)paramSchemaObject, paramArrayOfint);
  }
  
  void setAdminDirect() {
    this.isAdmin = this.isAdminDirect = true;
  }
  
  boolean updateNestedRoles(Grantee paramGrantee) {
    boolean bool = false;
    if (paramGrantee != this)
      for (byte b = 0; b < this.roles.size(); b++) {
        Grantee grantee = (Grantee)this.roles.get(b);
        bool |= grantee.updateNestedRoles(paramGrantee);
      }  
    if (bool)
      updateAllRights(); 
    return (bool || paramGrantee == this);
  }
  
  void updateAllRights() {
    this.fullRightsMap.clear();
    this.isAdmin = this.isAdminDirect;
    for (byte b = 0; b < this.roles.size(); b++) {
      Grantee grantee = (Grantee)this.roles.get(b);
      addToFullRights(grantee.fullRightsMap);
      this.isAdmin |= grantee.isAdmin();
    } 
    addToFullRights(this.directRightsMap);
    if (!this.isRole && !this.isPublic && !this.isSystem)
      addToFullRights(this.granteeManager.publicRole.fullRightsMap); 
  }
  
  void addToFullRights(HashMap paramHashMap) {
    Iterator iterator = paramHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      Right right1 = (Right)paramHashMap.get(object);
      Right right2 = (Right)this.fullRightsMap.get(object);
      if (right2 == null) {
        right2 = right1.duplicate();
        this.fullRightsMap.put(object, right2);
      } else {
        right2.add(right1);
      } 
      if (right1.grantableRights == null)
        continue; 
      if (right2.grantableRights == null) {
        right2.grantableRights = right1.grantableRights.duplicate();
        continue;
      } 
      right2.grantableRights.add(right1.grantableRights);
    } 
  }
  
  private void addToFullRights(MultiValueHashMap paramMultiValueHashMap) {
    Iterator iterator = paramMultiValueHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      Iterator iterator1 = paramMultiValueHashMap.get(object);
      Right right = (Right)this.fullRightsMap.get(object);
      while (iterator1.hasNext()) {
        Right right1 = (Right)iterator1.next();
        if (right == null) {
          right = right1.duplicate();
          this.fullRightsMap.put(object, right);
        } else {
          right.add(right1);
        } 
        if (right1.grantableRights == null)
          continue; 
        if (right.grantableRights == null) {
          right.grantableRights = right1.grantableRights.duplicate();
          continue;
        } 
        right.grantableRights.add(right1.grantableRights);
      } 
    } 
  }
  
  Right getAllGrantableRights(HsqlNameManager.HsqlName paramHsqlName) {
    if (this.isAdmin)
      return paramHsqlName.schema.owner.ownerRights; 
    if (paramHsqlName.schema.owner == this)
      return this.ownerRights; 
    if (this.roles.contains(paramHsqlName.schema.owner))
      return paramHsqlName.schema.owner.ownerRights; 
    OrderedHashSet orderedHashSet = getAllRoles();
    for (byte b = 0; b < orderedHashSet.size(); b++) {
      Grantee grantee = (Grantee)orderedHashSet.get(b);
      if (paramHsqlName.schema.owner == grantee)
        return grantee.ownerRights; 
    } 
    Right right = (Right)this.fullRightsMap.get(paramHsqlName);
    return (right == null || right.grantableRights == null) ? Right.noRights : right.grantableRights;
  }
  
  private MultiValueHashMap getRights() {
    return this.directRightsMap;
  }
  
  void grant(Grantee paramGrantee) {
    this.roles.add(paramGrantee);
  }
  
  void revoke(Grantee paramGrantee) {
    if (!hasRoleDirect(paramGrantee))
      throw Error.error(2253, paramGrantee.getName().getNameString()); 
    this.roles.remove(paramGrantee);
  }
  
  private String roleMapToString(OrderedHashSet paramOrderedHashSet) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      if (stringBuffer.length() > 0)
        stringBuffer.append(','); 
      Grantee grantee = (Grantee)paramOrderedHashSet.get(b);
      stringBuffer.append(grantee.getName().getStatementName());
    } 
    return stringBuffer.toString();
  }
  
  HsqlArrayList getRightsSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    String str = roleMapToString(this.roles);
    if (str.length() != 0) {
      StringBuffer stringBuffer = new StringBuffer(128);
      stringBuffer.append("GRANT").append(' ').append(str);
      stringBuffer.append(' ').append("TO").append(' ');
      stringBuffer.append(getName().getStatementName());
      hsqlArrayList.add(stringBuffer.toString());
    } 
    MultiValueHashMap multiValueHashMap = getRights();
    Iterator iterator = multiValueHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      Iterator iterator1 = multiValueHashMap.get(object);
      while (iterator1.hasNext()) {
        Table table;
        NumberSequence numberSequence;
        Type type1;
        Type type2;
        SchemaObject schemaObject;
        Right right = (Right)iterator1.next();
        StringBuffer stringBuffer = new StringBuffer(128);
        HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)object;
        switch (hsqlName.type) {
          case 3:
          case 4:
            table = this.granteeManager.database.schemaManager.findUserTable(null, hsqlName.name, hsqlName.schema.name);
            if (table != null) {
              stringBuffer.append("GRANT").append(' ');
              stringBuffer.append(right.getTableRightsSQL(table));
              stringBuffer.append(' ').append("ON").append(' ');
              stringBuffer.append("TABLE").append(' ');
              stringBuffer.append(hsqlName.getSchemaQualifiedStatementName());
            } 
            break;
          case 7:
            numberSequence = (NumberSequence)this.granteeManager.database.schemaManager.findSchemaObject(hsqlName.name, hsqlName.schema.name, 7);
            if (numberSequence != null) {
              stringBuffer.append("GRANT").append(' ');
              stringBuffer.append("USAGE");
              stringBuffer.append(' ').append("ON").append(' ');
              stringBuffer.append("SEQUENCE").append(' ');
              stringBuffer.append(hsqlName.getSchemaQualifiedStatementName());
            } 
            break;
          case 13:
            type1 = (Type)this.granteeManager.database.schemaManager.findSchemaObject(hsqlName.name, hsqlName.schema.name, 13);
            if (type1 != null) {
              stringBuffer.append("GRANT").append(' ');
              stringBuffer.append("USAGE");
              stringBuffer.append(' ').append("ON").append(' ');
              stringBuffer.append("DOMAIN").append(' ');
              stringBuffer.append(hsqlName.getSchemaQualifiedStatementName());
            } 
            break;
          case 12:
            type2 = (Type)this.granteeManager.database.schemaManager.findSchemaObject(hsqlName.name, hsqlName.schema.name, 13);
            if (type2 != null) {
              stringBuffer.append("GRANT").append(' ');
              stringBuffer.append("USAGE");
              stringBuffer.append(' ').append("ON").append(' ');
              stringBuffer.append("TYPE").append(' ');
              stringBuffer.append(hsqlName.getSchemaQualifiedStatementName());
            } 
            break;
          case 16:
          case 17:
          case 24:
            schemaObject = this.granteeManager.database.schemaManager.findSchemaObject(hsqlName.name, hsqlName.schema.name, hsqlName.type);
            if (schemaObject != null) {
              stringBuffer.append("GRANT").append(' ');
              stringBuffer.append("EXECUTE").append(' ');
              stringBuffer.append("ON").append(' ');
              stringBuffer.append("SPECIFIC").append(' ');
              if (schemaObject.getType() == 17) {
                stringBuffer.append("PROCEDURE");
              } else {
                stringBuffer.append("FUNCTION");
              } 
              stringBuffer.append(' ');
              stringBuffer.append(hsqlName.getSchemaQualifiedStatementName());
            } 
            break;
        } 
        if (stringBuffer.length() == 0)
          continue; 
        stringBuffer.append(' ').append("TO").append(' ');
        stringBuffer.append(getName().getStatementName());
        hsqlArrayList.add(stringBuffer.toString());
      } 
    } 
    return hsqlArrayList;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rights\Grantee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */