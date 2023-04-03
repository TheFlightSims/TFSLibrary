/*    */ package org.codehaus.stax2.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ 
/*    */ public abstract class Stax2BlockSource extends Stax2Source {
/*    */   public URL getReference() {
/* 33 */     return null;
/*    */   }
/*    */   
/*    */   public abstract Reader constructReader() throws IOException;
/*    */   
/*    */   public abstract InputStream constructInputStream() throws IOException;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2BlockSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */