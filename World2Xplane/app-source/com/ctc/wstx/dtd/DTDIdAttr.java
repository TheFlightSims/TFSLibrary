/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.ElementId;
/*     */ import com.ctc.wstx.util.ElementIdMap;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDIdAttr extends DTDAttribute {
/*     */   public DTDIdAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  38 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  43 */     return new DTDIdAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  53 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean typeIsId() {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  75 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  76 */       start++; 
/*  80 */     if (start >= end)
/*  81 */       return reportValidationProblem(v, "Empty ID value"); 
/*  83 */     end--;
/*  84 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  85 */       end--; 
/*  89 */     char c = cbuf[start];
/*  90 */     if (!WstxInputData.isNameStartChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  91 */       return reportInvalidChar(v, c, "not valid as the first ID character"); 
/*  93 */     int hash = c;
/*  94 */     for (int i = start + 1; i <= end; i++) {
/*  95 */       c = cbuf[i];
/*  96 */       if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  97 */         return reportInvalidChar(v, c, "not valid as an ID character"); 
/*  99 */       hash = hash * 31 + c;
/*     */     } 
/* 103 */     ElementIdMap m = v.getIdMap();
/* 104 */     PrefixedName elemName = v.getElemName();
/* 105 */     Location loc = v.getLocation();
/* 106 */     ElementId id = m.addDefined(cbuf, start, end - start + 1, hash, loc, elemName, this.mName);
/* 110 */     if (id.getLocation() != loc)
/* 111 */       return reportValidationProblem(v, "Duplicate id '" + id.getId() + "', first declared at " + id.getLocation()); 
/* 115 */     if (normalize)
/* 116 */       return id.getId(); 
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) {
/* 129 */     throw new IllegalStateException(ErrorConsts.ERR_INTERNAL);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDIdAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */