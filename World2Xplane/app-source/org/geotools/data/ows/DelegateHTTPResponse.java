/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class DelegateHTTPResponse implements HTTPResponse {
/*    */   protected HTTPResponse delegate;
/*    */   
/*    */   public DelegateHTTPResponse(HTTPResponse delegate) {
/* 28 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 34 */     this.delegate.dispose();
/*    */   }
/*    */   
/*    */   public String getContentType() {
/* 39 */     return this.delegate.getContentType();
/*    */   }
/*    */   
/*    */   public String getResponseHeader(String headerName) {
/* 44 */     return this.delegate.getResponseHeader(headerName);
/*    */   }
/*    */   
/*    */   public InputStream getResponseStream() throws IOException {
/* 49 */     return this.delegate.getResponseStream();
/*    */   }
/*    */   
/*    */   public String getResponseCharset() {
/* 54 */     return this.delegate.getResponseCharset();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\DelegateHTTPResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */