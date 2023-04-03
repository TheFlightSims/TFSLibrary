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
/*     */ public final class DTDIdRefsAttr extends DTDAttribute {
/*     */   public DTDIdRefsAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  31 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  36 */     return new DTDIdRefsAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  46 */     return 4;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  63 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  64 */       start++; 
/*  68 */     if (start >= end)
/*  69 */       return reportValidationProblem(v, "Empty IDREFS value"); 
/*  72 */     end--;
/*  74 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  75 */       end--; 
/*  79 */     ElementIdMap m = v.getIdMap();
/*  80 */     Location loc = v.getLocation();
/*  82 */     String idStr = null;
/*  83 */     StringBuffer sb = null;
/*  84 */     while (start <= end) {
/*  86 */       char c = cbuf[start];
/*  87 */       if (!WstxInputData.isNameStartChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  88 */         return reportInvalidChar(v, c, "not valid as the first IDREFS character"); 
/*  90 */       int hash = c;
/*  91 */       int i = start + 1;
/*  92 */       for (; i <= end; i++) {
/*  93 */         c = cbuf[i];
/*  94 */         if (WstxInputData.isSpaceChar(c))
/*     */           break; 
/*  97 */         if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  98 */           return reportInvalidChar(v, c, "not valid as an IDREFS character"); 
/* 100 */         hash = hash * 31 + c;
/*     */       } 
/* 104 */       ElementId id = m.addReferenced(cbuf, start, i - start, hash, loc, v.getElemName(), this.mName);
/* 108 */       start = i + 1;
/* 113 */       if (normalize)
/* 114 */         if (idStr == null) {
/* 115 */           idStr = id.getId();
/*     */         } else {
/* 117 */           if (sb == null)
/* 118 */             sb = new StringBuffer(idStr); 
/* 120 */           idStr = id.getId();
/* 121 */           sb.append(' ');
/* 122 */           sb.append(idStr);
/*     */         }  
/* 127 */       while (start <= end && WstxInputData.isSpaceChar(cbuf[start]))
/* 128 */         start++; 
/*     */     } 
/* 132 */     if (normalize) {
/* 133 */       if (sb != null)
/* 134 */         idStr = sb.toString(); 
/* 136 */       return idStr;
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 153 */     String def = validateDefaultNames(rep, normalize);
/* 154 */     if (normalize)
/* 155 */       this.mDefValue.setValue(def); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDIdRefsAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */