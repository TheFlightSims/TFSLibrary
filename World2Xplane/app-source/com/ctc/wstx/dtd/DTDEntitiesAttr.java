/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.ent.EntityDecl;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class DTDEntitiesAttr extends DTDAttribute {
/*     */   public DTDEntitiesAttr(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/*  33 */     super(name, defValue, specIndex, nsAware, xml11);
/*     */   }
/*     */   
/*     */   public DTDAttribute cloneWith(int specIndex) {
/*  38 */     return new DTDEntitiesAttr(this.mName, this.mDefValue, specIndex, this.mCfgNsAware, this.mCfgXml11);
/*     */   }
/*     */   
/*     */   public int getValueType() {
/*  48 */     return 6;
/*     */   }
/*     */   
/*     */   public String validate(DTDValidatorBase v, char[] cbuf, int start, int end, boolean normalize) throws XMLStreamException {
/*  71 */     while (start < end && WstxInputData.isSpaceChar(cbuf[start]))
/*  72 */       start++; 
/*  76 */     if (start >= end)
/*  77 */       return reportValidationProblem(v, "Empty ENTITIES value"); 
/*  79 */     end--;
/*  80 */     while (end > start && WstxInputData.isSpaceChar(cbuf[end]))
/*  81 */       end--; 
/*  85 */     String idStr = null;
/*  86 */     StringBuffer sb = null;
/*  88 */     while (start <= end) {
/*  90 */       char c = cbuf[start];
/*  91 */       if (!WstxInputData.isNameStartChar(c, this.mCfgNsAware, this.mCfgXml11))
/*  92 */         return reportInvalidChar(v, c, "not valid as the first ENTITIES character"); 
/*  94 */       int hash = c;
/*  95 */       int i = start + 1;
/*  96 */       for (; i <= end; i++) {
/*  97 */         c = cbuf[i];
/*  98 */         if (WstxInputData.isSpaceChar(c))
/*     */           break; 
/* 101 */         if (!WstxInputData.isNameChar(c, this.mCfgNsAware, this.mCfgXml11))
/* 102 */           return reportInvalidChar(v, c, "not valid as an ENTITIES character"); 
/* 104 */         hash = hash * 31 + c;
/*     */       } 
/* 107 */       EntityDecl ent = findEntityDecl(v, cbuf, start, i - start, hash);
/* 111 */       start = i + 1;
/* 116 */       if (normalize)
/* 117 */         if (idStr == null) {
/* 118 */           idStr = ent.getName();
/*     */         } else {
/* 120 */           if (sb == null)
/* 121 */             sb = new StringBuffer(idStr); 
/* 123 */           idStr = ent.getName();
/* 124 */           sb.append(' ');
/* 125 */           sb.append(idStr);
/*     */         }  
/* 130 */       while (start <= end && WstxInputData.isSpaceChar(cbuf[start]))
/* 131 */         start++; 
/*     */     } 
/* 135 */     if (normalize) {
/* 136 */       if (sb != null)
/* 137 */         idStr = sb.toString(); 
/* 139 */       return idStr;
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public void validateDefault(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 153 */     String normStr = validateDefaultNames(rep, true);
/* 154 */     if (normalize)
/* 155 */       this.mDefValue.setValue(normStr); 
/* 164 */     StringTokenizer st = new StringTokenizer(normStr);
/* 169 */     MinimalDTDReader dtdr = (MinimalDTDReader)rep;
/* 170 */     while (st.hasMoreTokens()) {
/* 171 */       String str = st.nextToken();
/* 172 */       EntityDecl ent = dtdr.findEntity(str);
/* 174 */       checkEntity(rep, normStr, ent);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDEntitiesAttr.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */