/*    */ package com.ctc.wstx.ent;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.io.DefaultInputResolver;
/*    */ import com.ctc.wstx.io.WstxInputSource;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.net.URL;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLResolver;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public class ParsedExtEntity extends ExtEntity {
/*    */   public ParsedExtEntity(Location loc, String name, URL ctxt, String pubId, String sysId) {
/* 22 */     super(loc, name, ctxt, pubId, sysId);
/*    */   }
/*    */   
/*    */   public String getNotationName() {
/* 26 */     return null;
/*    */   }
/*    */   
/*    */   public void writeEnc(Writer w) throws IOException {
/* 37 */     w.write("<!ENTITY ");
/* 38 */     w.write(this.mName);
/* 39 */     String pubId = getPublicId();
/* 40 */     if (pubId != null) {
/* 41 */       w.write("PUBLIC \"");
/* 42 */       w.write(pubId);
/* 43 */       w.write("\" ");
/*    */     } else {
/* 45 */       w.write("SYSTEM ");
/*    */     } 
/* 47 */     w.write(34);
/* 48 */     w.write(getSystemId());
/* 49 */     w.write("\">");
/*    */   }
/*    */   
/*    */   public boolean isParsed() {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public WstxInputSource expand(WstxInputSource parent, XMLResolver res, ReaderConfig cfg, int xmlVersion) throws IOException, XMLStreamException {
/* 70 */     if (xmlVersion == 0)
/* 71 */       xmlVersion = 256; 
/* 73 */     return DefaultInputResolver.resolveEntity(parent, this.mContext, this.mName, getPublicId(), getSystemId(), res, cfg, xmlVersion);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\ent\ParsedExtEntity.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */