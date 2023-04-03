/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.net.URL;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class LoggingHTTPClient extends DelegateHTTPClient {
/*    */   private String charsetName;
/*    */   
/*    */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*    */   
/* 33 */   private static final Logger LOGGER = Logger.getLogger("org.geotools.data.ows.httpclient");
/*    */   
/*    */   public LoggingHTTPClient(HTTPClient delegate) {
/* 37 */     this(delegate, "UTF-8");
/*    */   }
/*    */   
/*    */   public LoggingHTTPClient(HTTPClient delegate, String charsetName) {
/* 41 */     super(delegate);
/* 42 */     this.charsetName = charsetName;
/*    */   }
/*    */   
/*    */   public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
/* 48 */     if (LOGGER.isLoggable(Level.FINEST)) {
/* 49 */       ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 50 */       copy(postContent, out);
/* 51 */       LOGGER.finest("POST Request URL: " + url);
/* 52 */       LOGGER.finest("POST Request Body: \n" + out.toString(this.charsetName));
/* 54 */       return new LoggingHTTPResponse(this.delegate.post(url, new ByteArrayInputStream(out.toByteArray()), postContentType), this.charsetName);
/*    */     } 
/* 57 */     return this.delegate.post(url, postContent, postContentType);
/*    */   }
/*    */   
/*    */   public HTTPResponse get(URL url) throws IOException {
/* 63 */     if (LOGGER.isLoggable(Level.FINEST)) {
/* 64 */       LOGGER.finest("GET Request URL: " + url);
/* 65 */       return new LoggingHTTPResponse(this.delegate.get(url), this.charsetName);
/*    */     } 
/* 67 */     return this.delegate.get(url);
/*    */   }
/*    */   
/*    */   public static void copy(InputStream input, OutputStream output) throws IOException {
/* 72 */     byte[] buffer = new byte[4096];
/* 73 */     int n = 0;
/* 74 */     while (-1 != (n = input.read(buffer)))
/* 75 */       output.write(buffer, 0, n); 
/*    */   }
/*    */   
/*    */   class LoggingHTTPResponse extends DelegateHTTPResponse {
/*    */     private InputStream input;
/*    */     
/*    */     public LoggingHTTPResponse(HTTPResponse delegate, String charsetName) throws IOException {
/* 84 */       super(delegate);
/* 86 */       ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 87 */       LoggingHTTPClient.copy(delegate.getResponseStream(), output);
/* 88 */       LoggingHTTPClient.LOGGER.finest("Response: \n" + output.toString(charsetName));
/* 90 */       this.input = new ByteArrayInputStream(output.toByteArray());
/*    */     }
/*    */     
/*    */     public InputStream getResponseStream() throws IOException {
/* 95 */       return this.input;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\LoggingHTTPClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */