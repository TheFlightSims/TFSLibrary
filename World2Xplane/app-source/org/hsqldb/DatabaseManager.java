package org.hsqldb;

import java.util.Vector;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;

public class DatabaseManager {
  private static int dbIDCounter;
  
  static final HashMap memDatabaseMap = new HashMap();
  
  static final HashMap fileDatabaseMap = new HashMap();
  
  static final HashMap resDatabaseMap = new HashMap();
  
  static final IntKeyHashMap databaseIDMap = new IntKeyHashMap();
  
  static HashMap serverMap = new HashMap();
  
  private static final HsqlTimer timer = new HsqlTimer();
  
  public static Vector getDatabaseURIs() {
    Vector<String> vector = new Vector();
    synchronized (databaseIDMap) {
      Iterator iterator = databaseIDMap.values().iterator();
      while (iterator.hasNext()) {
        Database database = (Database)iterator.next();
        vector.addElement(database.getURI());
      } 
    } 
    return vector;
  }
  
  public static void closeDatabases(int paramInt) {
    synchronized (databaseIDMap) {
      Iterator iterator = databaseIDMap.values().iterator();
      while (iterator.hasNext()) {
        Database database = (Database)iterator.next();
        try {
          database.close(paramInt);
        } catch (HsqlException hsqlException) {}
      } 
    } 
  }
  
  public static Session newSession(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2) {
    Database database = null;
    synchronized (databaseIDMap) {
      database = (Database)databaseIDMap.get(paramInt1);
    } 
    if (database == null)
      return null; 
    Session session = database.connect(paramString1, paramString2, paramString3, paramInt2);
    session.isNetwork = true;
    return session;
  }
  
  public static Session newSession(String paramString1, String paramString2, String paramString3, String paramString4, HsqlProperties paramHsqlProperties, String paramString5, int paramInt) {
    Database database = getDatabase(paramString1, paramString2, paramHsqlProperties);
    return (database == null) ? null : database.connect(paramString3, paramString4, paramString5, paramInt);
  }
  
  public static Session getSession(int paramInt, long paramLong) {
    Database database = null;
    synchronized (databaseIDMap) {
      database = (Database)databaseIDMap.get(paramInt);
    } 
    return (database == null) ? null : database.sessionManager.getSession(paramLong);
  }
  
  public static int getDatabase(String paramString1, String paramString2, Server paramServer, HsqlProperties paramHsqlProperties) {
    Database database = getDatabase(paramString1, paramString2, paramHsqlProperties);
    registerServer(paramServer, database);
    return database.databaseID;
  }
  
  public static Database getDatabase(int paramInt) {
    return (Database)databaseIDMap.get(paramInt);
  }
  
  public static void shutdownDatabases(Server paramServer, int paramInt) {
    Database[] arrayOfDatabase;
    synchronized (serverMap) {
      HashSet hashSet = (HashSet)serverMap.get(paramServer);
      if (hashSet == null) {
        arrayOfDatabase = new Database[0];
      } else {
        arrayOfDatabase = new Database[hashSet.size()];
        hashSet.toArray((Object[])arrayOfDatabase);
      } 
    } 
    for (byte b = 0; b < arrayOfDatabase.length; b++)
      arrayOfDatabase[b].close(paramInt); 
  }
  
  public static Database getDatabase(String paramString1, String paramString2, HsqlProperties paramHsqlProperties) {
    Database database = getDatabaseObject(paramString1, paramString2, paramHsqlProperties);
    synchronized (database) {
      switch (database.getState()) {
        case 4:
          if (lookupDatabaseObject(paramString1, paramString2) == null)
            addDatabaseObject(paramString1, paramString2, database); 
          database.open();
          break;
        case 2:
        case 3:
          throw Error.error(451, 23);
      } 
    } 
    return database;
  }
  
  private static synchronized Database getDatabaseObject(String paramString1, String paramString2, HsqlProperties paramHsqlProperties) {
    HashMap hashMap;
    String str = paramString2;
    if (paramString1 == "file:") {
      hashMap = fileDatabaseMap;
      str = filePathToKey(paramString2);
      Database database1 = (Database)hashMap.get(str);
      if (database1 == null && hashMap.size() > 0) {
        Iterator iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
          String str1 = (String)iterator.next();
          if (str.equalsIgnoreCase(str1)) {
            str = str1;
            break;
          } 
        } 
      } 
    } else if (paramString1 == "res:") {
      hashMap = resDatabaseMap;
    } else if (paramString1 == "mem:") {
      hashMap = memDatabaseMap;
    } else {
      throw Error.runtimeError(201, "DatabaseManager");
    } 
    Database database = (Database)hashMap.get(str);
    if (database == null) {
      database = new Database(paramString1, paramString2, str, paramHsqlProperties);
      database.databaseID = dbIDCounter;
      synchronized (databaseIDMap) {
        databaseIDMap.put(dbIDCounter, database);
        dbIDCounter++;
      } 
      hashMap.put(str, database);
    } 
    return database;
  }
  
  public static synchronized Database lookupDatabaseObject(String paramString1, String paramString2) {
    HashMap hashMap;
    String str = paramString2;
    if (paramString1 == "file:") {
      hashMap = fileDatabaseMap;
      str = filePathToKey(paramString2);
    } else if (paramString1 == "res:") {
      hashMap = resDatabaseMap;
    } else if (paramString1 == "mem:") {
      hashMap = memDatabaseMap;
    } else {
      throw Error.runtimeError(201, "DatabaseManager");
    } 
    return (Database)hashMap.get(str);
  }
  
  private static synchronized void addDatabaseObject(String paramString1, String paramString2, Database paramDatabase) {
    HashMap hashMap;
    String str = paramString2;
    if (paramString1 == "file:") {
      hashMap = fileDatabaseMap;
      str = filePathToKey(paramString2);
    } else if (paramString1 == "res:") {
      hashMap = resDatabaseMap;
    } else if (paramString1 == "mem:") {
      hashMap = memDatabaseMap;
    } else {
      throw Error.runtimeError(201, "DatabaseManager");
    } 
    synchronized (databaseIDMap) {
      databaseIDMap.put(paramDatabase.databaseID, paramDatabase);
    } 
    hashMap.put(str, paramDatabase);
  }
  
  static void removeDatabase(Database paramDatabase) {
    HashMap hashMap;
    int i = paramDatabase.databaseID;
    String str1 = paramDatabase.getType();
    String str2 = paramDatabase.getPath();
    String str3 = str2;
    notifyServers(paramDatabase);
    if (str1 == "file:") {
      hashMap = fileDatabaseMap;
      str3 = filePathToKey(str2);
    } else if (str1 == "res:") {
      hashMap = resDatabaseMap;
    } else if (str1 == "mem:") {
      hashMap = memDatabaseMap;
    } else {
      throw Error.runtimeError(201, "DatabaseManager");
    } 
    boolean bool = false;
    synchronized (databaseIDMap) {
      databaseIDMap.remove(i);
      bool = databaseIDMap.isEmpty();
    } 
    synchronized (hashMap) {
      hashMap.remove(str3);
    } 
    if (bool)
      ValuePool.resetPool(); 
  }
  
  public static void deRegisterServer(Server paramServer) {
    synchronized (serverMap) {
      serverMap.remove(paramServer);
    } 
  }
  
  private static void registerServer(Server paramServer, Database paramDatabase) {
    synchronized (serverMap) {
      if (!serverMap.containsKey(paramServer))
        serverMap.put(paramServer, new HashSet()); 
      HashSet hashSet = (HashSet)serverMap.get(paramServer);
      hashSet.add(paramDatabase);
    } 
  }
  
  private static void notifyServers(Database paramDatabase) {
    Server[] arrayOfServer;
    synchronized (serverMap) {
      arrayOfServer = new Server[serverMap.size()];
      serverMap.keysToArray((Object[])arrayOfServer);
    } 
    for (byte b = 0; b < arrayOfServer.length; b++) {
      HashSet hashSet;
      Server server = arrayOfServer[b];
      boolean bool = false;
      synchronized (serverMap) {
        hashSet = (HashSet)serverMap.get(server);
      } 
      if (hashSet != null)
        synchronized (hashSet) {
          bool = hashSet.remove(paramDatabase);
        }  
      if (bool)
        server.notify(0, paramDatabase.databaseID); 
    } 
  }
  
  static boolean isServerDB(Database paramDatabase) {
    Iterator iterator = serverMap.keySet().iterator();
    while (iterator.hasNext()) {
      Server server = (Server)iterator.next();
      HashSet hashSet = (HashSet)serverMap.get(server);
      if (hashSet.contains(paramDatabase))
        return true; 
    } 
    return false;
  }
  
  public static HsqlTimer getTimer() {
    return timer;
  }
  
  private static String filePathToKey(String paramString) {
    try {
      return FileUtil.getFileUtil().canonicalPath(paramString);
    } catch (Exception exception) {
      return paramString;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\DatabaseManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */