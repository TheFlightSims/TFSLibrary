package org.hsqldb.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class HsqlSocketFactorySecure extends HsqlSocketFactory implements HandshakeCompletedListener {
  protected Object socketFactory;
  
  protected Object serverSocketFactory;
  
  protected final Object socket_factory_mutex = new Object();
  
  protected final Object server_socket_factory_mutex = new Object();
  
  public void configureSocket(Socket paramSocket) {
    super.configureSocket(paramSocket);
    SSLSocket sSLSocket = (SSLSocket)paramSocket;
    sSLSocket.addHandshakeCompletedListener(this);
  }
  
  public ServerSocket createServerSocket(int paramInt) throws Exception {
    SSLServerSocket sSLServerSocket = (SSLServerSocket)getServerSocketFactoryImpl().createServerSocket(paramInt);
    if (Error.TRACESYSTEMOUT) {
      Error.printSystemOut("[" + this + "]: createServerSocket()");
      Error.printSystemOut("capabilities for " + sSLServerSocket + ":");
      Error.printSystemOut("----------------------------");
      dump("supported cipher suites", sSLServerSocket.getSupportedCipherSuites());
      dump("enabled cipher suites", sSLServerSocket.getEnabledCipherSuites());
    } 
    return sSLServerSocket;
  }
  
  public ServerSocket createServerSocket(int paramInt, String paramString) throws Exception {
    InetAddress inetAddress = InetAddress.getByName(paramString);
    SSLServerSocket sSLServerSocket = (SSLServerSocket)getServerSocketFactoryImpl().createServerSocket(paramInt, 128, inetAddress);
    if (Error.TRACESYSTEMOUT) {
      Error.printSystemOut("[" + this + "]: createServerSocket()");
      Error.printSystemOut("capabilities for " + sSLServerSocket + ":");
      Error.printSystemOut("----------------------------");
      dump("supported cipher suites", sSLServerSocket.getSupportedCipherSuites());
      dump("enabled cipher suites", sSLServerSocket.getEnabledCipherSuites());
    } 
    return sSLServerSocket;
  }
  
  private static void dump(String paramString, String[] paramArrayOfString) {
    Error.printSystemOut(paramString);
    Error.printSystemOut("----------------------------");
    for (byte b = 0; b < paramArrayOfString.length; b++)
      Error.printSystemOut(String.valueOf(paramArrayOfString[b])); 
    Error.printSystemOut("----------------------------");
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt) throws Exception {
    if (paramSocket == null)
      return createSocket(paramString, paramInt); 
    SSLSocket sSLSocket = (SSLSocket)getSocketFactoryImpl().createSocket(paramSocket, paramString, paramInt, true);
    sSLSocket.addHandshakeCompletedListener(this);
    sSLSocket.startHandshake();
    verify(paramString, sSLSocket.getSession());
    return sSLSocket;
  }
  
  public Socket createSocket(String paramString, int paramInt) throws Exception {
    SSLSocket sSLSocket = (SSLSocket)getSocketFactoryImpl().createSocket(paramString, paramInt);
    sSLSocket.addHandshakeCompletedListener(this);
    sSLSocket.startHandshake();
    verify(paramString, sSLSocket.getSession());
    return sSLSocket;
  }
  
  public boolean isSecure() {
    return true;
  }
  
  protected SSLServerSocketFactory getServerSocketFactoryImpl() throws Exception {
    Object object;
    synchronized (this.server_socket_factory_mutex) {
      object = this.serverSocketFactory;
      if (object == null) {
        object = SSLServerSocketFactory.getDefault();
        this.serverSocketFactory = object;
      } 
    } 
    return (SSLServerSocketFactory)object;
  }
  
  protected SSLSocketFactory getSocketFactoryImpl() throws Exception {
    Object object;
    synchronized (this.socket_factory_mutex) {
      object = this.socketFactory;
      if (object == null) {
        object = SSLSocketFactory.getDefault();
        this.socketFactory = object;
      } 
    } 
    return (SSLSocketFactory)object;
  }
  
  protected void verify(String paramString, SSLSession paramSSLSession) throws Exception {
    X509Certificate[] arrayOfX509Certificate = paramSSLSession.getPeerCertificateChain();
    X509Certificate x509Certificate = arrayOfX509Certificate[0];
    Principal principal = x509Certificate.getSubjectDN();
    String str1 = String.valueOf(principal);
    int i = str1.indexOf("CN=");
    if (i < 0)
      throw new UnknownHostException(Error.getMessage(63)); 
    i += 3;
    int j = str1.indexOf(',', i);
    String str2 = str1.substring(i, (j > -1) ? j : str1.length());
    if (str2.length() < 1)
      throw new UnknownHostException(Error.getMessage(64)); 
    if (!str2.equalsIgnoreCase(paramString))
      throw new UnknownHostException(Error.getMessage(65, 0, new Object[] { str2, paramString })); 
  }
  
  public void handshakeCompleted(HandshakeCompletedEvent paramHandshakeCompletedEvent) {
    if (Error.TRACESYSTEMOUT) {
      SSLSocket sSLSocket = paramHandshakeCompletedEvent.getSocket();
      SSLSession sSLSession = paramHandshakeCompletedEvent.getSession();
      Error.printSystemOut("SSL handshake completed:");
      Error.printSystemOut("------------------------------------------------");
      Error.printSystemOut("socket:      : " + sSLSocket);
      Error.printSystemOut("cipher suite : " + sSLSession.getCipherSuite());
      String str = StringConverter.byteArrayToHexString(sSLSession.getId());
      Error.printSystemOut("session id   : " + str);
      Error.printSystemOut("------------------------------------------------");
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\HsqlSocketFactorySecure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */