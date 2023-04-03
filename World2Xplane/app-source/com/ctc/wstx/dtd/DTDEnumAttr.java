/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.sr.InputProblemReporter;
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ import com.ctc.wstx.util.WordResolver;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public final class DTDEnumAttr extends DTDAttribute {
/*    */   final WordResolver mEnumValues;
/*    */   
/*    */   public DTDEnumAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11, WordResolver enumValues) {
/* 27 */     super(name, defValue, specIndex, nsAware, xml11);
/* 28 */     this.mEnumValues = enumValues;
/*    */   }
/*    */   
/*    */   public DTDAttribute cloneWith(int specIndex) {
/* 33 */     return new DTDEnumAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11, this.mEnumValues);
/*    */   }
/*    */   
/*    */   public int getValueType() {
/* 44 */     return 1;
/*    */   }
/*    */   
/*    */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/* 61 */     String ok = validateEnumValue(cbuf, start, end, normalize, this.mEnumValues);
/* 62 */     if (ok == null) {
/* 63 */       String val = new String(cbuf, start, end - start);
/* 64 */       return reportValidationProblem(v, "Invalid enumerated value '" + val + "': has to be one of (" + this.mEnumValues + ")");
/*    */     } 
/* 67 */     return ok;
/*    */   }
/*    */   
/*    */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 78 */     String def = validateDefaultNmToken(rep, normalize);
/* 81 */     String shared = this.mEnumValues.find(def);
/* 82 */     if (shared == null) {
/* 83 */       reportValidationProblem(rep, "Invalid default value '" + def + "': has to be one of (" + this.mEnumValues + ")");
/*    */       return;
/*    */     } 
/* 89 */     if (normalize)
/* 90 */       this.mDefValue.setValue(shared); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDEnumAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */