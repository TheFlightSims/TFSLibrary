/*    */ package com.ctc.wstx.msv;
/*    */ 
/*    */ import com.sun.msv.grammar.xmlschema.XMLSchemaGrammar;
/*    */ import com.sun.msv.verifier.DocumentDeclaration;
/*    */ import com.sun.msv.verifier.regexp.xmlschema.XSREDocDecl;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.ValidationContext;
/*    */ import org.codehaus.stax2.validation.XMLValidationSchema;
/*    */ import org.codehaus.stax2.validation.XMLValidator;
/*    */ 
/*    */ public class W3CSchema implements XMLValidationSchema {
/*    */   protected final XMLSchemaGrammar mGrammar;
/*    */   
/*    */   public W3CSchema(XMLSchemaGrammar grammar) {
/* 36 */     this.mGrammar = grammar;
/*    */   }
/*    */   
/*    */   public String getSchemaType() {
/* 40 */     return "http://www.w3.org/2001/XMLSchema";
/*    */   }
/*    */   
/*    */   public XMLValidator createValidator(ValidationContext ctxt) throws XMLStreamException {
/* 46 */     XSREDocDecl dd = new XSREDocDecl(this.mGrammar);
/* 47 */     return new GenericMsvValidator(this, ctxt, (DocumentDeclaration)dd);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\msv\W3CSchema.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */