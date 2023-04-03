/*    */ package com.ctc.wstx.msv;
/*    */ 
/*    */ import com.sun.msv.grammar.Grammar;
/*    */ import com.sun.msv.grammar.trex.TREXGrammar;
/*    */ import com.sun.msv.verifier.DocumentDeclaration;
/*    */ import com.sun.msv.verifier.regexp.REDocumentDeclaration;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.ValidationContext;
/*    */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*    */ import org.codehaus.stax2.validation.XMLValidator;
/*    */ 
/*    */ public class RelaxNGSchema implements XMLValidationSchema {
/*    */   protected final TREXGrammar mGrammar;
/*    */   
/*    */   public RelaxNGSchema(TREXGrammar grammar) {
/* 41 */     this.mGrammar = grammar;
/*    */   }
/*    */   
/*    */   public String getSchemaType() {
/* 45 */     return "http://relaxng.org/ns/structure/0.9";
/*    */   }
/*    */   
/*    */   public XMLValidator createValidator(ValidationContext ctxt) throws XMLStreamException {
/* 51 */     REDocumentDeclaration dd = new REDocumentDeclaration((Grammar)this.mGrammar);
/* 52 */     return new GenericMsvValidator(this, ctxt, (DocumentDeclaration)dd);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\RelaxNGSchema.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */