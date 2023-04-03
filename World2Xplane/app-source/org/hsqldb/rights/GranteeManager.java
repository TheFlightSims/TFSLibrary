package org.hsqldb.rights;

import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.SchemaObject;
import org.hsqldb.SqlInvariants;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;

public class GranteeManager {
  static User systemAuthorisation;
  
  private HashMappedList map = new HashMappedList();
  
  private HashMappedList roleMap = new HashMappedList();
  
  Database database;
  
  Grantee publicRole;
  
  Grantee dbaRole;
  
  Grantee schemaRole;
  
  Grantee changeAuthRole;
  
  static final IntValueHashMap rightsStringLookup = new IntValueHashMap(7);
  
  public GranteeManager(Database paramDatabase) {
    this.database = paramDatabase;
    addRole(this.database.nameManager.newHsqlName("PUBLIC", false, 11));
    this.publicRole = getRole("PUBLIC");
    this.publicRole.isPublic = true;
    addRole(this.database.nameManager.newHsqlName("DBA", false, 11));
    this.dbaRole = getRole("DBA");
    this.dbaRole.setAdminDirect();
    addRole(this.database.nameManager.newHsqlName("CREATE_SCHEMA", false, 11));
    this.schemaRole = getRole("CREATE_SCHEMA");
    addRole(this.database.nameManager.newHsqlName("CHANGE_AUTHORIZATION", false, 11));
    this.changeAuthRole = getRole("CHANGE_AUTHORIZATION");
  }
  
  public Grantee getDBARole() {
    return this.dbaRole;
  }
  
  public static Grantee getSystemRole() {
    return systemAuthorisation;
  }
  
  public void grant(OrderedHashSet paramOrderedHashSet, SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean) {
    if (paramSchemaObject instanceof RoutineSchema) {
      Routine[] arrayOfRoutine = ((RoutineSchema)paramSchemaObject).getSpecificRoutines();
      grant(paramOrderedHashSet, (SchemaObject[])arrayOfRoutine, paramRight, paramGrantee, paramBoolean);
      return;
    } 
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    if (!paramGrantee.isGrantable(paramSchemaObject, paramRight))
      throw Error.error(2000, paramGrantee.getName().getNameString()); 
    if (paramGrantee.isAdmin())
      paramGrantee = paramSchemaObject.getOwner(); 
    checkGranteeList(paramOrderedHashSet);
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      Grantee grantee = get((String)paramOrderedHashSet.get(b));
      grantee.grant(hsqlName, paramRight, paramGrantee, paramBoolean);
      if (grantee.isRole)
        updateAllRights(grantee); 
    } 
  }
  
  public void grant(OrderedHashSet paramOrderedHashSet, SchemaObject[] paramArrayOfSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean) {
    boolean bool = false;
    for (byte b = 0; b < paramArrayOfSchemaObject.length; b++) {
      if (paramGrantee.isGrantable(paramArrayOfSchemaObject[b], paramRight)) {
        grant(paramOrderedHashSet, paramArrayOfSchemaObject[b], paramRight, paramGrantee, paramBoolean);
        bool = true;
      } 
    } 
    if (!bool)
      throw Error.error(2000, paramGrantee.getName().getNameString()); 
  }
  
  public void checkGranteeList(OrderedHashSet paramOrderedHashSet) {
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      String str = (String)paramOrderedHashSet.get(b);
      Grantee grantee = get(str);
      if (grantee == null)
        throw Error.error(4001, str); 
      if (isImmutable(str))
        throw Error.error(4002, str); 
      if (grantee instanceof User && ((User)grantee).isExternalOnly)
        throw Error.error(4000, str); 
    } 
  }
  
  public void grant(String paramString1, String paramString2, Grantee paramGrantee) {
    Grantee grantee1 = get(paramString1);
    if (grantee1 == null)
      throw Error.error(4001, paramString1); 
    if (isImmutable(paramString1))
      throw Error.error(4002, paramString1); 
    Grantee grantee2 = getRole(paramString2);
    if (grantee2 == null)
      throw Error.error(2200, paramString2); 
    if (grantee2 == grantee1)
      throw Error.error(2251, paramString1); 
    if (grantee2.hasRole(grantee1))
      throw Error.error(2251, paramString2); 
    if (!paramGrantee.isGrantable(grantee2))
      throw Error.error(2000, paramGrantee.getName().getNameString()); 
    grantee1.grant(grantee2);
    grantee1.updateAllRights();
    if (grantee1.isRole)
      updateAllRights(grantee1); 
  }
  
  public void checkRoleList(String paramString, OrderedHashSet paramOrderedHashSet, Grantee paramGrantee, boolean paramBoolean) {
    Grantee grantee = get(paramString);
    for (byte b = 0; b < paramOrderedHashSet.size(); b++) {
      String str = (String)paramOrderedHashSet.get(b);
      Grantee grantee1 = getRole(str);
      if (grantee1 == null)
        throw Error.error(2200, str); 
      if (str.equals("_SYSTEM") || str.equals("PUBLIC"))
        throw Error.error(4002, str); 
      if (paramBoolean) {
        if (grantee.getDirectRoles().contains(grantee1))
          throw Error.error(2200, paramString); 
      } else if (!grantee.getDirectRoles().contains(grantee1)) {
        throw Error.error(2200, str);
      } 
      if (!paramGrantee.isAdmin())
        throw Error.error(2000, paramGrantee.getName().getNameString()); 
    } 
  }
  
  public void grantSystemToPublic(SchemaObject paramSchemaObject, Right paramRight) {
    this.publicRole.grant(paramSchemaObject.getName(), paramRight, systemAuthorisation, true);
  }
  
  public void revoke(String paramString1, String paramString2, Grantee paramGrantee) {
    if (!paramGrantee.isAdmin())
      throw Error.error(5507); 
    Grantee grantee1 = get(paramString1);
    if (grantee1 == null)
      throw Error.error(4000, paramString1); 
    Grantee grantee2 = (Grantee)this.roleMap.get(paramString2);
    grantee1.revoke(grantee2);
    grantee1.updateAllRights();
    if (grantee1.isRole)
      updateAllRights(grantee1); 
  }
  
  public void revoke(OrderedHashSet paramOrderedHashSet, SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramSchemaObject instanceof RoutineSchema) {
      Routine[] arrayOfRoutine = ((RoutineSchema)paramSchemaObject).getSpecificRoutines();
      revoke(paramOrderedHashSet, (SchemaObject[])arrayOfRoutine, paramRight, paramGrantee, paramBoolean1, paramBoolean2);
      return;
    } 
    HsqlNameManager.HsqlName hsqlName = paramSchemaObject.getName();
    if (paramSchemaObject instanceof Routine)
      hsqlName = ((Routine)paramSchemaObject).getSpecificName(); 
    if (!paramGrantee.isFullyAccessibleByRole(hsqlName))
      throw Error.error(5501, (paramSchemaObject.getName()).name); 
    if (paramGrantee.isAdmin())
      paramGrantee = paramSchemaObject.getOwner(); 
    byte b;
    for (b = 0; b < paramOrderedHashSet.size(); b++) {
      String str = (String)paramOrderedHashSet.get(b);
      Grantee grantee = get(str);
      if (grantee == null)
        throw Error.error(4001, str); 
      if (isImmutable(str))
        throw Error.error(4002, str); 
    } 
    for (b = 0; b < paramOrderedHashSet.size(); b++) {
      String str = (String)paramOrderedHashSet.get(b);
      Grantee grantee = get(str);
      grantee.revoke(paramSchemaObject, paramRight, paramGrantee, paramBoolean1);
      grantee.updateAllRights();
      if (grantee.isRole)
        updateAllRights(grantee); 
    } 
  }
  
  public void revoke(OrderedHashSet paramOrderedHashSet, SchemaObject[] paramArrayOfSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean1, boolean paramBoolean2) {
    for (byte b = 0; b < paramArrayOfSchemaObject.length; b++)
      revoke(paramOrderedHashSet, paramArrayOfSchemaObject[b], paramRight, paramGrantee, paramBoolean1, paramBoolean2); 
  }
  
  void removeEmptyRole(Grantee paramGrantee) {
    for (byte b = 0; b < this.map.size(); b++) {
      Grantee grantee = (Grantee)this.map.get(b);
      grantee.roles.remove(paramGrantee);
    } 
  }
  
  public void removeDbObject(HsqlNameManager.HsqlName paramHsqlName) {
    for (byte b = 0; b < this.map.size(); b++) {
      Grantee grantee = (Grantee)this.map.get(b);
      grantee.revokeDbObject(paramHsqlName);
    } 
  }
  
  public void removeDbObjects(OrderedHashSet paramOrderedHashSet) {
    Iterator iterator = paramOrderedHashSet.iterator();
    while (iterator.hasNext()) {
      HsqlNameManager.HsqlName hsqlName = (HsqlNameManager.HsqlName)iterator.next();
      for (byte b = 0; b < this.map.size(); b++) {
        Grantee grantee = (Grantee)this.map.get(b);
        grantee.revokeDbObject(hsqlName);
      } 
    } 
  }
  
  void updateAllRights(Grantee paramGrantee) {
    byte b;
    for (b = 0; b < this.map.size(); b++) {
      Grantee grantee = (Grantee)this.map.get(b);
      if (grantee.isRole)
        grantee.updateNestedRoles(paramGrantee); 
    } 
    for (b = 0; b < this.map.size(); b++) {
      Grantee grantee = (Grantee)this.map.get(b);
      if (!grantee.isRole)
        grantee.updateAllRights(); 
    } 
  }
  
  public boolean removeGrantee(String paramString) {
    if (isReserved(paramString))
      return false; 
    Grantee grantee = (Grantee)this.map.remove(paramString);
    if (grantee == null)
      return false; 
    grantee.clearPrivileges();
    updateAllRights(grantee);
    if (grantee.isRole) {
      this.roleMap.remove(paramString);
      removeEmptyRole(grantee);
    } 
    return true;
  }
  
  public Grantee addRole(HsqlNameManager.HsqlName paramHsqlName) {
    if (this.map.containsKey(paramHsqlName.name))
      throw Error.error(4003, paramHsqlName.name); 
    if (SqlInvariants.isLobsSchemaName(paramHsqlName.name) || SqlInvariants.isSystemSchemaName(paramHsqlName.name))
      throw Error.error(4002, paramHsqlName.name); 
    Grantee grantee = new Grantee(paramHsqlName, this);
    grantee.isRole = true;
    this.map.put(paramHsqlName.name, grantee);
    this.roleMap.add(paramHsqlName.name, grantee);
    return grantee;
  }
  
  public User addUser(HsqlNameManager.HsqlName paramHsqlName) {
    if (this.map.containsKey(paramHsqlName.name))
      throw Error.error(4003, paramHsqlName.name); 
    if (SqlInvariants.isLobsSchemaName(paramHsqlName.name) || SqlInvariants.isSystemSchemaName(paramHsqlName.name))
      throw Error.error(4002, paramHsqlName.name); 
    User user = new User(paramHsqlName, this);
    this.map.put(paramHsqlName.name, user);
    return user;
  }
  
  public void removeNewUser(HsqlNameManager.HsqlName paramHsqlName) {
    this.map.remove(paramHsqlName.name);
  }
  
  boolean isGrantee(String paramString) {
    return this.map.containsKey(paramString);
  }
  
  public static int getCheckSingleRight(String paramString) {
    int i = getRight(paramString);
    if (i != 0)
      return i; 
    throw Error.error(5581, paramString);
  }
  
  public static int getRight(String paramString) {
    return rightsStringLookup.get(paramString, 0);
  }
  
  public Grantee get(String paramString) {
    return (Grantee)this.map.get(paramString);
  }
  
  public Collection getGrantees() {
    return this.map.values();
  }
  
  public static boolean validRightString(String paramString) {
    return (getRight(paramString) != 0);
  }
  
  public static boolean isImmutable(String paramString) {
    return (paramString.equals("_SYSTEM") || paramString.equals("DBA") || paramString.equals("CREATE_SCHEMA") || paramString.equals("CHANGE_AUTHORIZATION"));
  }
  
  public static boolean isReserved(String paramString) {
    return (paramString.equals("_SYSTEM") || paramString.equals("DBA") || paramString.equals("CREATE_SCHEMA") || paramString.equals("CHANGE_AUTHORIZATION") || paramString.equals("PUBLIC"));
  }
  
  public void dropRole(String paramString) {
    if (!isRole(paramString))
      throw Error.error(2200, paramString); 
    if (isReserved(paramString))
      throw Error.error(5507); 
    removeGrantee(paramString);
  }
  
  public Set getRoleNames() {
    return this.roleMap.keySet();
  }
  
  public Collection getRoles() {
    return this.roleMap.values();
  }
  
  public Grantee getRole(String paramString) {
    Grantee grantee = (Grantee)this.roleMap.get(paramString);
    if (grantee == null)
      throw Error.error(2200, paramString); 
    return grantee;
  }
  
  public boolean isRole(String paramString) {
    return this.roleMap.containsKey(paramString);
  }
  
  public String[] getSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Iterator iterator = getRoles().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      if (!isReserved(grantee.getName().getNameString()))
        hsqlArrayList.add(grantee.getSQL()); 
    } 
    iterator = getGrantees().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      if (!(grantee instanceof User) || ((User)grantee).isExternalOnly)
        continue; 
      hsqlArrayList.add(grantee.getSQL());
      if (((User)grantee).isLocalOnly)
        hsqlArrayList.add(((User)grantee).getLocalUserSQL()); 
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String[] getRightstSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    Iterator iterator = getGrantees().iterator();
    while (iterator.hasNext()) {
      Grantee grantee = (Grantee)iterator.next();
      String str = grantee.getName().getNameString();
      if (isImmutable(str) || (grantee instanceof User && ((User)grantee).isExternalOnly))
        continue; 
      HsqlArrayList hsqlArrayList1 = grantee.getRightsSQL();
      hsqlArrayList.addAll((Collection)hsqlArrayList1);
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  static {
    rightsStringLookup.put("ALL", 63);
    rightsStringLookup.put("SELECT", 1);
    rightsStringLookup.put("UPDATE", 8);
    rightsStringLookup.put("DELETE", 2);
    rightsStringLookup.put("INSERT", 4);
    rightsStringLookup.put("EXECUTE", 32);
    rightsStringLookup.put("USAGE", 16);
    rightsStringLookup.put("REFERENCES", 64);
    rightsStringLookup.put("TRIGGER", 128);
  }
  
  static {
    HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newSystemObjectName("_SYSTEM", 11);
    systemAuthorisation = new User(hsqlName, null);
    systemAuthorisation.isSystem = true;
    systemAuthorisation.setAdminDirect();
    systemAuthorisation.setInitialSchema(SqlInvariants.SYSTEM_SCHEMA_HSQLNAME);
    SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.SYSTEM_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.LOBS_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.SQLJ_SCHEMA_HSQLNAME.owner = systemAuthorisation;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rights\GranteeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */