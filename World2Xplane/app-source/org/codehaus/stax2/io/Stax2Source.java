/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ import javax.xml.transform.Source;
/*    */ 
/*    */ public abstract class Stax2Source implements Source {
/*    */   protected String mSystemId;
/*    */   
/*    */   protected String mPublicId;
/*    */   
/*    */   protected String mEncoding;
/*    */   
/*    */   public String getSystemId() {
/* 55 */     return this.mSystemId;
/*    */   }
/*    */   
/*    */   public void setSystemId(String id) {
/* 59 */     this.mSystemId = id;
/*    */   }
/*    */   
/*    */   public String getPublicId() {
/* 63 */     return this.mPublicId;
/*    */   }
/*    */   
/*    */   public void setPublicId(String id) {
/* 67 */     this.mPublicId = id;
/*    */   }
/*    */   
/*    */   public String getEncoding() {
/* 71 */     return this.mEncoding;
/*    */   }
/*    */   
/*    */   public void setEncoding(String enc) {
/* 75 */     this.mEncoding = enc;
/*    */   }
/*    */   
/*    */   public abstract URL getReference();
/*    */   
/*    */   public abstract Reader constructReader() throws IOException;
/*    */   
/*    */   public abstract InputStream constructInputStream() throws IOException;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2Source.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */