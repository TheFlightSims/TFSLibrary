/*    */ package org.postgresql.ssl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ 
/*    */ public abstract class WrappedFactory extends SSLSocketFactory {
/*    */   protected SSLSocketFactory _factory;
/*    */   
/*    */   public Socket createSocket(InetAddress host, int port) throws IOException {
/* 28 */     return this._factory.createSocket(host, port);
/*    */   }
/*    */   
/*    */   public Socket createSocket(String host, int port) throws IOException {
/* 32 */     return this._factory.createSocket(host, port);
/*    */   }
/*    */   
/*    */   public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
/* 36 */     return this._factory.createSocket(host, port, localHost, localPort);
/*    */   }
/*    */   
/*    */   public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
/* 40 */     return this._factory.createSocket(address, port, localAddress, localPort);
/*    */   }
/*    */   
/*    */   public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
/* 44 */     return this._factory.createSocket(socket, host, port, autoClose);
/*    */   }
/*    */   
/*    */   public String[] getDefaultCipherSuites() {
/* 48 */     return this._factory.getDefaultCipherSuites();
/*    */   }
/*    */   
/*    */   public String[] getSupportedCipherSuites() {
/* 52 */     return this._factory.getSupportedCipherSuites();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ssl\WrappedFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */