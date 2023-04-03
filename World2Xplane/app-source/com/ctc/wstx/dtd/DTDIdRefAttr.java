/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.ElementId;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDIdRefAttr extends DTDAttribute {
/*     */   public DTDIdRefAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  31 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  36 */     return new DTDIdRefAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  46 */     return 3;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  67 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  68 */       start++; 
/*  71 */     if (start >= end)
/*  72 */       return reportValidationProblem(v, "Empty IDREF value"); 
/*  75 */     end--;
/*  76 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  77 */       end--; 
/*  81 */     char c = cbuf[start];
/*  82 */     if (!WstxInputData.isNameStartChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  83 */       return reportInvalidChar(v, c, "not valid as the first IDREF character"); 
/*  85 */     int hash = c;
/*  86 */     for (int i = start + 1; i <= end; i++) {
/*  87 */       c = cbuf[i];
/*  88 */       if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  89 */         return reportInvalidChar(v, c, "not valid as an IDREF character"); 
/*  91 */       hash = hash * 31 + c;
/*     */     } 
/*  95 */     ElementIdMap m = v.getIdMap();
/*  96 */     Location loc = v.getLocation();
/*  97 */     ElementId id = m.addReferenced(cbuf, start, end - start + 1, hash, loc, v.getElemName(), this.mName);
/* 100 */     return normalize ? id.getId() : null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 111 */     String def = validateDefaultName(rep, normalize);
/* 112 */     if (normalize)
/* 113 */       this.mDefValue.setValue(def); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDIdRefAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */