/*    */ package com.ctc.wstx.ent;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.io.WstxInputSource;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.net.URL;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLResolver;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public abstract class ExtEntity extends EntityDecl {
/*    */   final String mPublicId;
/*    */   
/*    */   final String mSystemId;
/*    */   
/*    */   public ExtEntity(Location loc, String name, URL ctxt, String pubId, String sysId) {
/* 23 */     super(loc, name, ctxt);
/* 24 */     this.mPublicId = pubId;
/* 25 */     this.mSystemId = sysId;
/*    */   }
/*    */   
/*    */   public abstract String getNotationName();
/*    */   
/*    */   public String getPublicId() {
/* 31 */     return this.mPublicId;
/*    */   }
/*    */   
/*    */   public String getReplacementText() {
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public int getReplacementText(Writer w) {
/* 41 */     return 0;
/*    */   }
/*    */   
/*    */   public String getSystemId() {
/* 45 */     return this.mSystemId;
/*    */   }
/*    */   
/*    */   public abstract void writeEnc(Writer paramWriter) throws IOException;
/*    */   
/*    */   public char[] getReplacementChars() {
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isExternal() {
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public abstract boolean isParsed();
/*    */   
/*    */   public abstract WstxInputSource expand(WstxInputSource paramWstxInputSource, XMLResolver paramXMLResolver, ReaderConfig paramReaderConfig, int paramInt) throws IOException, XMLStreamException;
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\ent\ExtEntity.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */