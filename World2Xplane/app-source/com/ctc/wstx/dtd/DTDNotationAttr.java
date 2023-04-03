/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.sr.InputProblemReporter;
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ import com.ctc.wstx.util.WordResolver;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public final class DTDNotationAttr extends DTDAttribute {
/*    */   final WordResolver mEnumValues;
/*    */   
/*    */   public DTDNotationAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11, WordResolver enumValues) {
/* 28 */     super(name, defValue, specIndex, nsAware, xml11);
/* 29 */     this.mEnumValues = enumValues;
/*    */   }
/*    */   
/*    */   public DTDAttribute cloneWith(int specIndex) {
/* 34 */     return new DTDNotationAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11, this.mEnumValues);
/*    */   }
/*    */   
/*    */   public int getValueType() {
/* 45 */     return 7;
/*    */   }
/*    */   
/*    */   public boolean typeIsNotation() {
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/* 68 */     String ok = validateEnumValue(cbuf, start, end, normalize, this.mEnumValues);
/* 69 */     if (ok == null) {
/* 70 */       String val = new String(cbuf, start, end - start);
/* 71 */       return reportValidationProblem(v, "Invalid notation value '" + val + "': has to be one of (" + this.mEnumValues + ")");
/*    */     } 
/* 74 */     return ok;
/*    */   }
/*    */   
/*    */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 86 */     String def = validateDefaultName(rep, normalize);
/* 89 */     String shared = this.mEnumValues.find(def);
/* 90 */     if (shared == null)
/* 91 */       reportValidationProblem(rep, "Invalid default value '" + def + "': has to be one of (" + this.mEnumValues + ")"); 
/* 96 */     if (normalize)
/* 97 */       this.mDefValue.setValue(shared); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDNotationAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */