/*    */ package com.ctc.wstx.ent;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.io.WstxInputSource;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.net.URL;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLResolver;
/*    */ 
/*    */ public class UnparsedExtEntity extends ExtEntity {
/*    */   final String mNotationId;
/*    */   
/*    */   public UnparsedExtEntity(Location loc, String name, URL ctxt, String pubId, String sysId, String notationId) {
/* 22 */     super(loc, name, ctxt, pubId, sysId);
/* 23 */     this.mNotationId = notationId;
/*    */   }
/*    */   
/*    */   public String getNotationName() {
/* 27 */     return this.mNotationId;
/*    */   }
/*    */   
/*    */   public void writeEnc(Writer w) throws IOException {
/* 38 */     w.write("<!ENTITY ");
/* 39 */     w.write(this.mName);
/* 40 */     String pubId = getPublicId();
/* 41 */     if (pubId != null) {
/* 42 */       w.write("PUBLIC \"");
/* 43 */       w.write(pubId);
/* 44 */       w.write("\" ");
/*    */     } else {
/* 46 */       w.write("SYSTEM ");
/*    */     } 
/* 48 */     w.write(34);
/* 49 */     w.write(getSystemId());
/* 50 */     w.write("\" NDATA ");
/* 51 */     w.write(this.mNotationId);
/* 52 */     w.write(62);
/*    */   }
/*    */   
/*    */   public boolean isParsed() {
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   public WstxInputSource expand(WstxInputSource parent, XMLResolver res, ReaderConfig cfg, int xmlVersion) {
/* 70 */     throw new IllegalStateException("Internal error: createInputSource() called for unparsed (external) entity.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\ent\UnparsedExtEntity.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */