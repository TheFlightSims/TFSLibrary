/*    */ package com.ctc.wstx.msv;
/*    */ 
/*    */ import com.sun.msv.grammar.trex.TREXGrammar;
/*    */ import com.sun.msv.reader.GrammarReaderController;
/*    */ import com.sun.msv.reader.trex.ng.RELAXNGReader;
/*    */ import com.sun.msv.reader.util.IgnoreController;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*    */ import org.xml.sax.InputSource;
/*    */ 
/*    */ public class RelaxNGSchemaFactory extends BaseSchemaFactory {
/* 45 */   protected final GrammarReaderController mDummyController = (GrammarReaderController)new IgnoreController();
/*    */   
/*    */   public RelaxNGSchemaFactory() {
/* 50 */     super("http://relaxng.org/ns/structure/0.9");
/*    */   }
/*    */   
/*    */   protected XMLValidationSchema loadSchema(InputSource src, Object sysRef) throws XMLStreamException {
/* 69 */     SAXParserFactory saxFactory = getSaxFactory();
/* 70 */     BaseSchemaFactory.MyGrammarController ctrl = new BaseSchemaFactory.MyGrammarController();
/* 71 */     TREXGrammar grammar = RELAXNGReader.parse(src, saxFactory, (GrammarReaderController)ctrl);
/* 72 */     if (grammar == null) {
/* 73 */       String msg = "Failed to load RelaxNG schema from '" + sysRef + "'";
/* 74 */       String emsg = ctrl.mErrorMsg;
/* 75 */       if (emsg != null)
/* 76 */         msg = msg + ": " + emsg; 
/* 78 */       throw new XMLStreamException(msg);
/*    */     } 
/* 80 */     return new RelaxNGSchema(grammar);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\RelaxNGSchemaFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */