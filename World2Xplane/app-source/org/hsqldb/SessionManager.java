package org.hsqldb;

import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.rights.User;

public class SessionManager {
  long sessionIdCount = 0L;
  
  private LongKeyHashMap sessionMap = new LongKeyHashMap();
  
  private Session sysSession;
  
  private Session sysLobSession;
  
  public SessionManager(Database paramDatabase) {
    User user = paramDatabase.getUserManager().getSysUser();
    this.sysSession = new Session(paramDatabase, user, false, false, this.sessionIdCount++, null, 0);
    this.sysLobSession = new Session(paramDatabase, user, true, false, this.sessionIdCount++, null, 0);
  }
  
  public synchronized Session newSession(Database paramDatabase, User paramUser, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt) {
    Session session = new Session(paramDatabase, paramUser, paramBoolean2, paramBoolean1, this.sessionIdCount, paramString, paramInt);
    this.sessionMap.put(this.sessionIdCount, session);
    this.sessionIdCount++;
    return session;
  }
  
  public synchronized Session newSessionForLog(Database paramDatabase) {
    boolean bool = paramDatabase.databaseProperties.isVersion18();
    Session session = new Session(paramDatabase, paramDatabase.getUserManager().getSysUser(), bool, false, this.sessionIdCount, null, 0);
    session.isProcessingLog = true;
    this.sessionMap.put(this.sessionIdCount, session);
    this.sessionIdCount++;
    return session;
  }
  
  public Session getSysSessionForScript(Database paramDatabase) {
    Session session = new Session(paramDatabase, paramDatabase.getUserManager().getSysUser(), false, false, 0L, null, 0);
    session.setCurrentSchemaHsqlName(paramDatabase.schemaManager.defaultSchemaHsqlName);
    session.isProcessingScript = true;
    return session;
  }
  
  public Session getSysLobSession() {
    return this.sysLobSession;
  }
  
  public Session getSysSession() {
    this.sysSession.currentSchema = this.sysSession.database.schemaManager.getDefaultSchemaHsqlName();
    this.sysSession.isProcessingScript = false;
    this.sysSession.isProcessingLog = false;
    this.sysSession.setUser(this.sysSession.database.getUserManager().getSysUser());
    return this.sysSession;
  }
  
  public Session newSysSession() {
    Session session = new Session(this.sysSession.database, this.sysSession.getUser(), false, false, this.sessionIdCount, null, 0);
    session.currentSchema = this.sysSession.database.schemaManager.getDefaultSchemaHsqlName();
    this.sessionMap.put(this.sessionIdCount, session);
    this.sessionIdCount++;
    return session;
  }
  
  public Session newSysSession(HsqlNameManager.HsqlName paramHsqlName, User paramUser) {
    Session session = new Session(this.sysSession.database, paramUser, false, false, 0L, null, 0);
    session.currentSchema = paramHsqlName;
    return session;
  }
  
  public void closeAllSessions() {
    Session[] arrayOfSession = getAllSessions();
    for (byte b = 0; b < arrayOfSession.length; b++)
      arrayOfSession[b].close(); 
  }
  
  synchronized void removeSession(Session paramSession) {
    this.sessionMap.remove(paramSession.getId());
  }
  
  synchronized void close() {
    closeAllSessions();
    this.sysSession.close();
    this.sysLobSession.close();
  }
  
  synchronized boolean isEmpty() {
    return this.sessionMap.isEmpty();
  }
  
  public synchronized Session[] getVisibleSessions(Session paramSession) {
    (new Session[1])[0] = paramSession;
    return paramSession.isAdmin() ? getAllSessions() : new Session[1];
  }
  
  synchronized Session getSession(long paramLong) {
    return (Session)this.sessionMap.get(paramLong);
  }
  
  public synchronized Session[] getAllSessions() {
    Session[] arrayOfSession = new Session[this.sessionMap.size()];
    Iterator iterator = this.sessionMap.values().iterator();
    for (byte b = 0; iterator.hasNext(); b++)
      arrayOfSession[b] = (Session)iterator.next(); 
    return arrayOfSession;
  }
  
  public synchronized boolean isUserActive(String paramString) {
    Iterator iterator = this.sessionMap.values().iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Session session = (Session)iterator.next();
      if (paramString.equals(session.getUser().getName().getNameString()))
        return true; 
    } 
    return false;
  }
  
  public synchronized void removeSchemaReference(Schema paramSchema) {
    Iterator iterator = this.sessionMap.values().iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Session session = (Session)iterator.next();
      if (session.getCurrentSchemaHsqlName() == paramSchema.getName())
        session.resetSchema(); 
    } 
  }
  
  public synchronized void resetLoggedSchemas() {
    Iterator iterator = this.sessionMap.values().iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Session session = (Session)iterator.next();
      session.loggedSchema = null;
    } 
    this.sysLobSession.loggedSchema = null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SessionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */