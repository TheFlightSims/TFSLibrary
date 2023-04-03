/*    */ package com.ctc.wstx.evt;
/*    */ 
/*    */ import com.ctc.wstx.cfg.ErrorConsts;
/*    */ import com.ctc.wstx.exc.WstxParsingException;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import javax.xml.stream.util.XMLEventAllocator;
/*    */ import org.codehaus.stax2.XMLStreamReader2;
/*    */ import org.codehaus.stax2.ri.Stax2EventReaderImpl;
/*    */ 
/*    */ public class WstxEventReader extends Stax2EventReaderImpl {
/*    */   public WstxEventReader(XMLEventAllocator a, XMLStreamReader2 r) {
/* 36 */     super(a, r);
/*    */   }
/*    */   
/*    */   protected String getErrorDesc(int errorType, int currEvent) {
/* 48 */     switch (errorType) {
/*    */       case 1:
/* 50 */         return ErrorConsts.ERR_STATE_NOT_STELEM + ", got " + ErrorConsts.tokenTypeDesc(currEvent);
/*    */       case 2:
/* 52 */         return "Expected a text token, got " + ErrorConsts.tokenTypeDesc(currEvent);
/*    */       case 3:
/* 54 */         return "Only all-whitespace CHARACTERS/CDATA (or SPACE) allowed for nextTag(), got " + ErrorConsts.tokenTypeDesc(currEvent);
/*    */       case 4:
/* 56 */         return "Got " + ErrorConsts.tokenTypeDesc(currEvent) + ", instead of START_ELEMENT, END_ELEMENT or SPACE";
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public boolean isPropertySupported(String name) {
/* 63 */     return ((XMLStreamReader2)getStreamReader()).isPropertySupported(name);
/*    */   }
/*    */   
/*    */   public boolean setProperty(String name, Object value) {
/* 68 */     return ((XMLStreamReader2)getStreamReader()).setProperty(name, value);
/*    */   }
/*    */   
/*    */   protected void reportProblem(String msg, Location loc) throws XMLStreamException {
/* 82 */     throw new WstxParsingException(msg, loc);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\WstxEventReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */