/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class DelegateHTTPClient implements HTTPClient {
/*    */   protected HTTPClient delegate;
/*    */   
/*    */   public DelegateHTTPClient(HTTPClient delegate) {
/* 30 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
/* 36 */     return this.delegate.post(url, postContent, postContentType);
/*    */   }
/*    */   
/*    */   public HTTPResponse get(URL url) throws IOException {
/* 41 */     return this.delegate.get(url);
/*    */   }
/*    */   
/*    */   public String getUser() {
/* 46 */     return this.delegate.getUser();
/*    */   }
/*    */   
/*    */   public void setUser(String user) {
/* 51 */     this.delegate.setUser(user);
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 56 */     return this.delegate.getPassword();
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 61 */     this.delegate.setPassword(password);
/*    */   }
/*    */   
/*    */   public int getConnectTimeout() {
/* 66 */     return this.delegate.getConnectTimeout();
/*    */   }
/*    */   
/*    */   public void setConnectTimeout(int connectTimeout) {
/* 71 */     this.delegate.setConnectTimeout(connectTimeout);
/*    */   }
/*    */   
/*    */   public int getReadTimeout() {
/* 76 */     return this.delegate.getReadTimeout();
/*    */   }
/*    */   
/*    */   public void setReadTimeout(int readTimeout) {
/* 81 */     this.delegate.setReadTimeout(readTimeout);
/*    */   }
/*    */   
/*    */   public void setTryGzip(boolean tryGZIP) {
/* 86 */     this.delegate.setTryGzip(tryGZIP);
/*    */   }
/*    */   
/*    */   public boolean isTryGzip() {
/* 91 */     return this.delegate.isTryGzip();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\DelegateHTTPClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */