/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDNmTokenAttr extends DTDAttribute {
/*     */   public DTDNmTokenAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  28 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  33 */     return new DTDNmTokenAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  43 */     return 8;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  60 */     int origLen = end - start;
/*  63 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  64 */       start++; 
/*  68 */     if (start >= end)
/*  69 */       return reportValidationProblem(v, "Empty NMTOKEN value"); 
/*  72 */     end--;
/*  73 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  74 */       end--; 
/*  78 */     for (int i = start; i <= end; i++) {
/*  79 */       char c = cbuf[i];
/*  80 */       if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  81 */         return reportInvalidChar(v, c, "not valid NMTOKEN character"); 
/*     */     } 
/*  85 */     if (normalize) {
/*  87 */       int len = end - start + 1;
/*  88 */       if (len != origLen)
/*  89 */         return new String(cbuf, start, len); 
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 103 */     String def = validateDefaultNmToken(rep, normalize);
/* 104 */     if (normalize)
/* 105 */       this.mDefValue.setValue(def); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDNmTokenAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */