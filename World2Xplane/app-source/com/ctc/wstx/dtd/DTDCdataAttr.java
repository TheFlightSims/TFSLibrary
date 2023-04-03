/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.sr.InputProblemReporter;
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import org.codehaus.stax2.validation.XMLValidationException;
/*    */ 
/*    */ public final class DTDCdataAttr extends DTDAttribute {
/*    */   public DTDCdataAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/* 20 */     super(name, defValue, specIndex, nsAware, xml11);
/*    */   }
/*    */   
/*    */   public DTDAttribute cloneWith(int specIndex) {
/* 25 */     return new DTDCdataAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*    */   }
/*    */   
/*    */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLValidationException {
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {}
/*    */   
/*    */   public String normalize(DTDValidatorBase v, char[] cbuf, int start, int end) {
/* 53 */     return null;
/*    */   }
/*    */   
/*    */   public void normalizeDefault() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDCdataAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */