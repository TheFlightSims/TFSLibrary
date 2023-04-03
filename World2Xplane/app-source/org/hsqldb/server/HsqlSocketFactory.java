package org.hsqldb.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HsqlSocketFactory {
  private static HsqlSocketFactory plainImpl;
  
  private static HsqlSocketFactory sslImpl;
  
  public static HsqlSocketFactory getInstance(boolean paramBoolean) throws Exception {
    return paramBoolean ? getSSLImpl() : getPlainImpl();
  }
  
  public void configureSocket(Socket paramSocket) {}
  
  public ServerSocket createServerSocket(int paramInt) throws Exception {
    return new ServerSocket(paramInt);
  }
  
  public ServerSocket createServerSocket(int paramInt, String paramString) throws Exception {
    return new ServerSocket(paramInt, 128, InetAddress.getByName(paramString));
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt) throws Exception {
    return (paramSocket == null) ? new Socket(paramString, paramInt) : paramSocket;
  }
  
  public Socket createSocket(String paramString, int paramInt) throws Exception {
    return new Socket(paramString, paramInt);
  }
  
  public boolean isSecure() {
    return false;
  }
  
  private static HsqlSocketFactory getPlainImpl() throws Exception {
    synchronized (HsqlSocketFactory.class) {
      if (plainImpl == null)
        plainImpl = new HsqlSocketFactory(); 
    } 
    return plainImpl;
  }
  
  private static HsqlSocketFactory getSSLImpl() throws Exception {
    synchronized (HsqlSocketFactory.class) {
      if (sslImpl == null)
        sslImpl = newFactory("org.hsqldb.server.HsqlSocketFactorySecure"); 
    } 
    return sslImpl;
  }
  
  private static HsqlSocketFactory newFactory(String paramString) throws Exception {
    Object object;
    Class<?> clazz = Class.forName(paramString);
    Class[] arrayOfClass = new Class[0];
    Constructor<?> constructor = clazz.getDeclaredConstructor(arrayOfClass);
    Object[] arrayOfObject = new Object[0];
    try {
      object = constructor.newInstance(arrayOfObject);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getTargetException();
      throw (throwable instanceof Exception) ? (Exception)throwable : new RuntimeException(throwable.toString());
    } 
    return (HsqlSocketFactory)object;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\HsqlSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */