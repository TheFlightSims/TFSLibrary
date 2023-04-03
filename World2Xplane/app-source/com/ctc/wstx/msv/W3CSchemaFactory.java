/*    */ package com.ctc.wstx.msv;
/*    */ 
/*    */ import com.sun.msv.grammar.xmlschema.XMLSchemaGrammar;
/*    */ import com.sun.msv.reader.GrammarReaderController;
/*    */ import com.sun.msv.reader.util.IgnoreController;
/*    */ import com.sun.msv.reader.xmlschema.XMLSchemaReader;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*    */ import org.xml.sax.InputSource;
/*    */ 
/*    */ public class W3CSchemaFactory extends BaseSchemaFactory {
/* 45 */   protected final GrammarReaderController mDummyController = (GrammarReaderController)new IgnoreController();
/*    */   
/*    */   public W3CSchemaFactory() {
/* 50 */     super("http://relaxng.org/ns/structure/0.9");
/*    */   }
/*    */   
/*    */   protected XMLValidationSchema loadSchema(InputSource src, Object sysRef) throws XMLStreamException {
/* 66 */     SAXParserFactory saxFactory = getSaxFactory();
/* 68 */     BaseSchemaFactory.MyGrammarController ctrl = new BaseSchemaFactory.MyGrammarController();
/* 69 */     XMLSchemaGrammar grammar = XMLSchemaReader.parse(src, saxFactory, (GrammarReaderController)ctrl);
/* 70 */     if (grammar == null) {
/* 71 */       String msg = "Failed to load W3C Schema from '" + sysRef + "'";
/* 72 */       String emsg = ctrl.mErrorMsg;
/* 73 */       if (emsg != null)
/* 74 */         msg = msg + ": " + emsg; 
/* 76 */       throw new XMLStreamException(msg);
/*    */     } 
/* 78 */     return new W3CSchema(grammar);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\W3CSchemaFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */