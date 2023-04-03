/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.geotools.ows.ServiceException;
/*    */ import org.jdom.JDOMException;
/*    */ 
/*    */ public abstract class Response {
/*    */   protected HTTPResponse httpResponse;
/*    */   
/*    */   public Response(HTTPResponse httpResponse) throws ServiceException, IOException {
/* 39 */     if (httpResponse.getResponseStream() == null)
/* 40 */       throw new NullPointerException("An inputStream is required for " + getClass().getName()); 
/* 42 */     if (httpResponse.getContentType() == null)
/* 44 */       throw new NullPointerException("Content type is required for " + getClass().getName()); 
/* 46 */     this.httpResponse = httpResponse;
/* 50 */     if (httpResponse.getContentType().toLowerCase().equals("application/vnd.ogc.se_xml"))
/*    */       try {
/* 52 */         throw parseException(httpResponse.getResponseStream());
/*    */       } finally {
/* 54 */         dispose();
/*    */       }  
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 60 */     this.httpResponse.dispose();
/*    */   }
/*    */   
/*    */   public String getContentType() {
/* 64 */     return this.httpResponse.getContentType();
/*    */   }
/*    */   
/*    */   public InputStream getInputStream() {
/*    */     try {
/* 80 */       return this.httpResponse.getResponseStream();
/* 81 */     } catch (IOException e) {
/* 82 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected ServiceException parseException(InputStream inputStream) throws IOException {
/*    */     try {
/* 88 */       return ServiceExceptionParser.parse(inputStream);
/* 89 */     } catch (JDOMException e) {
/* 90 */       throw (IOException)(new IOException()).initCause(e);
/*    */     } finally {
/* 92 */       inputStream.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\Response.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */