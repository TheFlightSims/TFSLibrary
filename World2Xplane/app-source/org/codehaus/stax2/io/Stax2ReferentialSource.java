/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ 
/*    */ public abstract class Stax2ReferentialSource extends Stax2Source {
/*    */   public abstract URL getReference();
/*    */   
/*    */   public abstract Reader constructReader() throws IOException;
/*    */   
/*    */   public abstract InputStream constructInputStream() throws IOException;
/*    */   
/*    */   public String getSystemId() {
/* 47 */     String sysId = super.getSystemId();
/* 48 */     if (sysId == null)
/* 49 */       sysId = getReference().toExternalForm(); 
/* 51 */     return sysId;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2ReferentialSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */