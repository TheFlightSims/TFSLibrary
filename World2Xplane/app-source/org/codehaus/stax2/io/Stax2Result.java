/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.Writer;
/*    */ import javax.xml.transform.Result;
/*    */ 
/*    */ public abstract class Stax2Result implements Result {
/*    */   protected String mSystemId;
/*    */   
/*    */   protected String mPublicId;
/*    */   
/*    */   protected String mEncoding;
/*    */   
/*    */   public String getSystemId() {
/* 54 */     return this.mSystemId;
/*    */   }
/*    */   
/*    */   public void setSystemId(String id) {
/* 58 */     this.mSystemId = id;
/*    */   }
/*    */   
/*    */   public String getPublicId() {
/* 62 */     return this.mPublicId;
/*    */   }
/*    */   
/*    */   public void setPublicId(String id) {
/* 66 */     this.mPublicId = id;
/*    */   }
/*    */   
/*    */   public String getEncoding() {
/* 70 */     return this.mEncoding;
/*    */   }
/*    */   
/*    */   public void setEncoding(String enc) {
/* 74 */     this.mEncoding = enc;
/*    */   }
/*    */   
/*    */   public abstract Writer constructWriter() throws IOException;
/*    */   
/*    */   public abstract OutputStream constructOutputStream() throws IOException;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2Result.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */