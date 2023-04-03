/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDNmTokensAttr extends DTDAttribute {
/*     */   public DTDNmTokensAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  28 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  33 */     return new DTDNmTokensAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  43 */     return 9;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  65 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  66 */       start++; 
/*  69 */     if (start >= end)
/*  70 */       return reportValidationProblem(v, "Empty NMTOKENS value"); 
/*  76 */     if (!normalize) {
/*  77 */       for (; start < end; start++) {
/*  78 */         char c = cbuf[start];
/*  79 */         if (!WstxInputData.isSpaceChar(c) && !WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  81 */           return reportInvalidChar(v, c, "not valid as NMTOKENS character"); 
/*     */       } 
/*  84 */       return null;
/*     */     } 
/*  90 */     end--;
/*  92 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  93 */       end--; 
/* 100 */     StringBuffer sb = null;
/* 102 */     while (start <= end) {
/* 103 */       int i = start;
/* 104 */       for (; i <= end; i++) {
/* 105 */         char c = cbuf[i];
/* 106 */         if (WstxInputData.isSpaceChar(c))
/*     */           break; 
/* 109 */         if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/* 110 */           return reportInvalidChar(v, c, "not valid as an NMTOKENS character"); 
/*     */       } 
/* 114 */       if (sb == null) {
/* 115 */         sb = new StringBuffer(end - start + 1);
/*     */       } else {
/* 117 */         sb.append(' ');
/*     */       } 
/* 119 */       sb.append(cbuf, start, i - start);
/* 121 */       start = i + 1;
/* 123 */       while (start <= end && WstxInputData.isSpaceChar(cbuf[start]))
/* 124 */         start++; 
/*     */     } 
/* 132 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 143 */     String defValue = this.mDefValue.getValue();
/* 144 */     int len = defValue.length();
/* 147 */     StringBuffer sb = null;
/* 148 */     int count = 0;
/* 149 */     int start = 0;
/* 152 */     label35: while (start < len) {
/* 153 */       char c = defValue.charAt(start);
/* 157 */       while (WstxInputData.isSpaceChar(c)) {
/* 160 */         if (++start >= len)
/*     */           break label35; 
/* 163 */         c = defValue.charAt(start);
/*     */       } 
/* 166 */       int i = start + 1;
/* 169 */       while (++i < len) {
/* 172 */         c = defValue.charAt(i);
/* 173 */         if (WstxInputData.isSpaceChar(c))
/*     */           break; 
/*     */       } 
/* 174 */       count++;
/* 175 */       String token = defValue.substring(start, i);
/* 176 */       int illegalIx = WstxInputData.findIllegalNmtokenChar(token, this.mCfgNsAware, this.mCfgXml11);
/* 177 */       if (illegalIx >= 0)
/* 178 */         reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character #" + illegalIx + " (" + WstxInputData.getCharDesc(defValue.charAt(illegalIx)) + ") not a valid NMTOKENS character"); 
/* 184 */       if (normalize) {
/* 185 */         if (sb == null) {
/* 186 */           sb = new StringBuffer(i - start + 32);
/*     */         } else {
/* 188 */           sb.append(' ');
/*     */         } 
/* 190 */         sb.append(token);
/*     */       } 
/* 192 */       start = i + 1;
/*     */     } 
/* 195 */     if (count == 0) {
/* 196 */       reportValidationProblem(rep, "Invalid default value '" + defValue + "'; empty String is not a valid NMTOKENS value");
/*     */       return;
/*     */     } 
/* 201 */     if (normalize)
/* 202 */       this.mDefValue.setValue(sb.toString()); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDNmTokensAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */