/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.sr.InputProblemReporter;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.DTDValidationSchema;
/*    */ import org.codehaus.stax2.validation.ValidationContext;
/*    */ import org.codehaus.stax2.validation.XMLValidator;
/*    */ 
/*    */ public abstract class DTDSubset implements DTDValidationSchema {
/*    */   public abstract DTDSubset combineWithExternalSubset(InputProblemReporter paramInputProblemReporter, DTDSubset paramDTDSubset) throws XMLStreamException;
/*    */   
/*    */   public abstract XMLValidator createValidator(ValidationContext paramValidationContext) throws XMLStreamException;
/*    */   
/*    */   public String getSchemaType() {
/* 66 */     return "http://www.w3.org/XML/1998/namespace";
/*    */   }
/*    */   
/*    */   public abstract int getEntityCount();
/*    */   
/*    */   public abstract int getNotationCount();
/*    */   
/*    */   public abstract boolean isCachable();
/*    */   
/*    */   public abstract boolean isReusableWith(DTDSubset paramDTDSubset);
/*    */   
/*    */   public abstract HashMap getGeneralEntityMap();
/*    */   
/*    */   public abstract List getGeneralEntityList();
/*    */   
/*    */   public abstract HashMap getParameterEntityMap();
/*    */   
/*    */   public abstract HashMap getNotationMap();
/*    */   
/*    */   public abstract List getNotationList();
/*    */   
/*    */   public abstract HashMap getElementMap();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDSubset.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */