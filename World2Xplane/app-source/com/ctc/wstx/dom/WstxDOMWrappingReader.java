/*    */ package com.ctc.wstx.dom;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.exc.WstxParsingException;
/*    */ import java.util.Collections;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import org.codehaus.stax2.ri.dom.DOMWrappingReader;
/*    */ 
/*    */ public class WstxDOMWrappingReader extends DOMWrappingReader {
/*    */   protected final ReaderConfig mConfig;
/*    */   
/*    */   protected WstxDOMWrappingReader(DOMSource src, ReaderConfig cfg) throws XMLStreamException {
/* 28 */     super(src, cfg.willSupportNamespaces(), cfg.willCoalesceText());
/* 29 */     this.mConfig = cfg;
/* 31 */     if (cfg.hasInternNamesBeenEnabled())
/* 32 */       setInternNames(true); 
/* 34 */     if (cfg.hasInternNsURIsBeenEnabled())
/* 35 */       setInternNsURIs(true); 
/*    */   }
/*    */   
/*    */   public static WstxDOMWrappingReader createFrom(DOMSource src, ReaderConfig cfg) throws XMLStreamException {
/* 42 */     return new WstxDOMWrappingReader(src, cfg);
/*    */   }
/*    */   
/*    */   public boolean isPropertySupported(String name) {
/* 54 */     return this.mConfig.isPropertySupported(name);
/*    */   }
/*    */   
/*    */   public Object getProperty(String name) {
/* 59 */     if (name.equals("javax.xml.stream.entities"))
/* 61 */       return Collections.EMPTY_LIST; 
/* 63 */     if (name.equals("javax.xml.stream.notations"))
/* 65 */       return Collections.EMPTY_LIST; 
/* 67 */     return this.mConfig.getProperty(name);
/*    */   }
/*    */   
/*    */   public boolean setProperty(String name, Object value) {
/* 73 */     return this.mConfig.setProperty(name, value);
/*    */   }
/*    */   
/*    */   protected void throwStreamException(String msg, Location loc) throws XMLStreamException {
/* 86 */     if (loc == null)
/* 87 */       throw new WstxParsingException(msg); 
/* 89 */     throw new WstxParsingException(msg, loc);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dom\WstxDOMWrappingReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */