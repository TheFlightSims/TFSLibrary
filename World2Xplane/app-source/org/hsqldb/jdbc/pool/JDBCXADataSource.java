package org.hsqldb.jdbc.pool;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.CommonDataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.Xid;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCDriver;
import org.hsqldb.jdbc.JDBCUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Iterator;

public class JDBCXADataSource extends JDBCCommonDataSource implements XADataSource, Serializable, Referenceable, CommonDataSource {
  private HashMap resources = new HashMap();
  
  public XAConnection getXAConnection() throws SQLException {
    JDBCConnection jDBCConnection = (JDBCConnection)JDBCDriver.getConnection(this.url, this.connectionProps);
    return new JDBCXAConnection(this, jDBCConnection);
  }
  
  public XAConnection getXAConnection(String paramString1, String paramString2) throws SQLException {
    if (paramString1 == null || paramString2 == null)
      throw JDBCUtil.nullArgument(); 
    if (paramString1.equals(this.user) && paramString2.equals(this.password))
      return getXAConnection(); 
    throw JDBCUtil.sqlException(Error.error(4000));
  }
  
  public Reference getReference() throws NamingException {
    String str = "org.hsqldb.jdbc.JDBCDataSourceFactory";
    Reference reference = new Reference(getClass().getName(), str, null);
    reference.add(new StringRefAddr("database", getDatabase()));
    reference.add(new StringRefAddr("user", getUser()));
    reference.add(new StringRefAddr("password", this.password));
    reference.add(new StringRefAddr("loginTimeout", Integer.toString(this.loginTimeout)));
    return reference;
  }
  
  public void addResource(Xid paramXid, JDBCXAResource paramJDBCXAResource) {
    this.resources.put(paramXid, paramJDBCXAResource);
  }
  
  public JDBCXAResource removeResource(Xid paramXid) {
    return (JDBCXAResource)this.resources.remove(paramXid);
  }
  
  Xid[] getPreparedXids() {
    Iterator iterator = this.resources.keySet().iterator();
    HashSet hashSet = new HashSet();
    while (iterator.hasNext()) {
      Xid xid = (Xid)iterator.next();
      if (((JDBCXAResource)this.resources.get(xid)).state == JDBCXAResource.XA_STATE_PREPARED)
        hashSet.add(xid); 
    } 
    Xid[] arrayOfXid = new Xid[hashSet.size()];
    hashSet.toArray((Object[])arrayOfXid);
    return arrayOfXid;
  }
  
  JDBCXAResource getResource(Xid paramXid) {
    return (JDBCXAResource)this.resources.get(paramXid);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\pool\JDBCXADataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */