/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.ent.EntityDecl;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDEntityAttr extends DTDAttribute {
/*     */   public DTDEntityAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  30 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  35 */     return new DTDEntityAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  45 */     return 5;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  62 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  63 */       start++; 
/*  67 */     if (start >= end)
/*  68 */       return reportValidationProblem(v, "Empty ENTITY value"); 
/*  70 */     end--;
/*  71 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  72 */       end--; 
/*  76 */     char c = cbuf[start];
/*  77 */     if (!WstxInputData.isNameStartChar(c, this.mCfgNsAware, this.mCfgXml11) && c != ':')
/*  78 */       return reportInvalidChar(v, c, "not valid as the first ID character"); 
/*  80 */     int hash = c;
/*  82 */     for (int i = start + 1; i <= end; i++) {
/*  83 */       c = cbuf[i];
/*  84 */       if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  85 */         return reportInvalidChar(v, c, "not valid as an ID character"); 
/*  87 */       hash = hash * 31 + c;
/*     */     } 
/*  90 */     EntityDecl ent = findEntityDecl(v, cbuf, start, end - start + 1, hash);
/*  93 */     return normalize ? ent.getName() : null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 104 */     String normStr = validateDefaultName(rep, normalize);
/* 105 */     if (normalize)
/* 106 */       this.mDefValue.setValue(normStr); 
/* 115 */     EntityDecl ent = ((MinimalDTDReader)rep).findEntity(normStr);
/* 116 */     checkEntity(rep, normStr, ent);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDEntityAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */