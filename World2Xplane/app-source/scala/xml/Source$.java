/*    */ package scala.xml;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileDescriptor;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.Reader;
/*    */ import java.io.StringReader;
/*    */ import org.xml.sax.InputSource;
/*    */ 
/*    */ public final class Source$ {
/*    */   public static final Source$ MODULE$;
/*    */   
/*    */   private Source$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public InputSource fromFile(File file) {
/* 19 */     return new InputSource(new FileInputStream(file));
/*    */   }
/*    */   
/*    */   public InputSource fromFile(FileDescriptor fd) {
/* 20 */     return new InputSource(new FileInputStream(fd));
/*    */   }
/*    */   
/*    */   public InputSource fromFile(String name) {
/* 21 */     return new InputSource(new FileInputStream(name));
/*    */   }
/*    */   
/*    */   public InputSource fromInputStream(InputStream is) {
/* 23 */     return new InputSource(is);
/*    */   }
/*    */   
/*    */   public InputSource fromReader(Reader reader) {
/* 24 */     return new InputSource(reader);
/*    */   }
/*    */   
/*    */   public InputSource fromSysId(String sysID) {
/* 25 */     return new InputSource(sysID);
/*    */   }
/*    */   
/*    */   public InputSource fromString(String string) {
/* 26 */     return fromReader(new StringReader(string));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Source$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */