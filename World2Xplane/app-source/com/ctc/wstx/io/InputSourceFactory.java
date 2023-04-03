/*    */ package com.ctc.wstx.io;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import java.io.Reader;
/*    */ import java.net.URL;
/*    */ import javax.xml.stream.Location;
/*    */ 
/*    */ public final class InputSourceFactory {
/*    */   public static ReaderSource constructEntitySource(ReaderConfig cfg, WstxInputSource parent, String entityName, InputBootstrapper bs, String pubId, String sysId, int xmlVersion, URL src, Reader r) {
/* 34 */     ReaderSource rs = new ReaderSource(cfg, parent, entityName, pubId, sysId, src, r, true);
/* 36 */     if (bs != null)
/* 37 */       rs.setInputOffsets(bs.getInputTotal(), bs.getInputRow(), -bs.getInputColumn()); 
/* 40 */     return rs;
/*    */   }
/*    */   
/*    */   public static BranchingReaderSource constructDocumentSource(ReaderConfig cfg, InputBootstrapper bs, String pubId, String sysId, URL src, Reader r, boolean realClose) {
/* 54 */     URL url = cfg.getBaseURL();
/* 55 */     if (url != null)
/* 56 */       src = url; 
/* 58 */     BranchingReaderSource rs = new BranchingReaderSource(cfg, pubId, sysId, src, r, realClose);
/* 59 */     if (bs != null)
/* 60 */       rs.setInputOffsets(bs.getInputTotal(), bs.getInputRow(), -bs.getInputColumn()); 
/* 63 */     return rs;
/*    */   }
/*    */   
/*    */   public static WstxInputSource constructCharArraySource(WstxInputSource parent, String fromEntity, char[] text, int offset, int len, Location loc, URL src) {
/* 74 */     return new CharArraySource(parent, fromEntity, text, offset, len, loc, src);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\InputSourceFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */