/*     */ package org.geotools.data.ows;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import org.geotools.data.Base64;
/*     */ 
/*     */ public class SimpleHttpClient implements HTTPClient {
/*     */   private static final int DEFAULT_TIMEOUT = 30;
/*     */   
/*     */   private String user;
/*     */   
/*     */   private String password;
/*     */   
/*  44 */   private int connectTimeout = 30;
/*     */   
/*  46 */   private int readTimeout = 30;
/*     */   
/*     */   private boolean tryGzip = true;
/*     */   
/*     */   public String getUser() {
/*  52 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(String user) {
/*  57 */     this.user = user;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  62 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/*  67 */     this.password = password;
/*     */   }
/*     */   
/*     */   public int getConnectTimeout() {
/*  72 */     return this.connectTimeout;
/*     */   }
/*     */   
/*     */   public void setConnectTimeout(int connectTimeout) {
/*  77 */     this.connectTimeout = connectTimeout;
/*     */   }
/*     */   
/*     */   public int getReadTimeout() {
/*  82 */     return this.readTimeout;
/*     */   }
/*     */   
/*     */   public void setReadTimeout(int readTimeout) {
/*  87 */     this.readTimeout = readTimeout;
/*     */   }
/*     */   
/*     */   public HTTPResponse get(URL url) throws IOException {
/*  95 */     URLConnection connection = openConnection(url);
/*  96 */     if (connection instanceof HttpURLConnection)
/*  97 */       ((HttpURLConnection)connection).setRequestMethod("GET"); 
/* 100 */     connection.connect();
/* 102 */     return new SimpleHTTPResponse(connection);
/*     */   }
/*     */   
/*     */   public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
/* 112 */     URLConnection connection = openConnection(url);
/* 113 */     if (connection instanceof HttpURLConnection)
/* 114 */       ((HttpURLConnection)connection).setRequestMethod("POST"); 
/* 116 */     connection.setDoOutput(true);
/* 117 */     if (postContentType != null)
/* 118 */       connection.setRequestProperty("Content-type", postContentType); 
/* 121 */     connection.connect();
/* 123 */     OutputStream outputStream = connection.getOutputStream();
/*     */     try {
/* 125 */       byte[] buff = new byte[512];
/*     */       int count;
/* 127 */       while ((count = postContent.read(buff)) > -1)
/* 128 */         outputStream.write(buff, 0, count); 
/*     */     } finally {
/* 131 */       outputStream.flush();
/* 132 */       outputStream.close();
/*     */     } 
/* 135 */     return new SimpleHTTPResponse(connection);
/*     */   }
/*     */   
/*     */   private HttpURLConnection openConnection(URL finalURL) throws IOException {
/* 139 */     HttpURLConnection connection = (HttpURLConnection)finalURL.openConnection();
/* 140 */     if (this.tryGzip)
/* 141 */       connection.addRequestProperty("Accept-Encoding", "gzip"); 
/* 144 */     if (getConnectTimeout() > 0)
/* 145 */       connection.setConnectTimeout(1000 * getConnectTimeout()); 
/* 147 */     if (getReadTimeout() > 0)
/* 148 */       connection.setReadTimeout(1000 * getReadTimeout()); 
/* 151 */     String username = getUser();
/* 152 */     String password = getPassword();
/* 154 */     if (username != null && password != null) {
/* 155 */       String userpassword = username + ":" + password;
/* 156 */       String encodedAuthorization = Base64.encodeBytes(userpassword.getBytes("UTF-8"));
/* 157 */       connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
/*     */     } 
/* 159 */     return connection;
/*     */   }
/*     */   
/*     */   public static class SimpleHTTPResponse implements HTTPResponse {
/*     */     private URLConnection connection;
/*     */     
/*     */     private InputStream responseStream;
/*     */     
/*     */     public SimpleHTTPResponse(URLConnection connection) throws IOException {
/* 169 */       this.connection = connection;
/* 170 */       InputStream inputStream = connection.getInputStream();
/* 172 */       String contentEncoding = connection.getContentEncoding();
/* 174 */       if (contentEncoding != null && connection.getContentEncoding().indexOf("gzip") != -1)
/* 175 */         inputStream = new GZIPInputStream(inputStream); 
/* 177 */       this.responseStream = inputStream;
/*     */     }
/*     */     
/*     */     public void dispose() {
/* 184 */       if (this.responseStream != null) {
/*     */         try {
/* 186 */           this.responseStream.close();
/* 187 */         } catch (IOException e) {}
/* 190 */         this.responseStream = null;
/*     */       } 
/* 192 */       if (this.connection != null) {
/* 193 */         if (this.connection instanceof HttpURLConnection)
/* 194 */           ((HttpURLConnection)this.connection).disconnect(); 
/* 196 */         this.connection = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getContentType() {
/* 205 */       return this.connection.getContentType();
/*     */     }
/*     */     
/*     */     public String getResponseHeader(String headerName) {
/* 211 */       return this.connection.getHeaderField(headerName);
/*     */     }
/*     */     
/*     */     public InputStream getResponseStream() throws IOException {
/* 218 */       return this.responseStream;
/*     */     }
/*     */     
/*     */     public String getResponseCharset() {
/* 226 */       String contentType = getContentType();
/* 227 */       if (null == contentType)
/* 228 */         return null; 
/* 230 */       String[] split = contentType.split(";");
/* 232 */       for (int i = 1; i < split.length; i++) {
/* 233 */         String[] mimeParam = split[i].split("=");
/* 234 */         if (mimeParam.length == 2 && "charset".equalsIgnoreCase(mimeParam[0])) {
/* 235 */           String charset = mimeParam[1];
/* 236 */           return charset.trim();
/*     */         } 
/*     */       } 
/* 239 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTryGzip(boolean tryGZIP) {
/* 249 */     this.tryGzip = tryGZIP;
/*     */   }
/*     */   
/*     */   public boolean isTryGzip() {
/* 258 */     return this.tryGzip;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\SimpleHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */