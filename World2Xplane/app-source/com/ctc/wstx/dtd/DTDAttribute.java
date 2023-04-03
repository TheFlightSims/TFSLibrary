/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.ent.EntityDecl;
/*     */ import com.ctc.wstx.io.WstxInputData;
/*     */ import com.ctc.wstx.sr.InputProblemReporter;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import com.ctc.wstx.util.WordResolver;
/*     */ import java.util.Map;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public abstract class DTDAttribute {
/*     */   static final char CHAR_SPACE = ' ';
/*     */   
/*     */   public static final int TYPE_CDATA = 0;
/*     */   
/*     */   public static final int TYPE_ENUMERATED = 1;
/*     */   
/*     */   public static final int TYPE_ID = 2;
/*     */   
/*     */   public static final int TYPE_IDREF = 3;
/*     */   
/*     */   public static final int TYPE_IDREFS = 4;
/*     */   
/*     */   public static final int TYPE_ENTITY = 5;
/*     */   
/*     */   public static final int TYPE_ENTITIES = 6;
/*     */   
/*     */   public static final int TYPE_NOTATION = 7;
/*     */   
/*     */   public static final int TYPE_NMTOKEN = 8;
/*     */   
/*     */   public static final int TYPE_NMTOKENS = 9;
/*     */   
/*  68 */   static final String[] sTypes = new String[] { "CDATA", "ENUMERATED", "ID", "IDREF", "IDREFS", "ENTITY", "ENTITIES", "NOTATION", "NMTOKEN", "NMTOKENS" };
/*     */   
/*     */   protected final PrefixedName mName;
/*     */   
/*     */   protected final int mSpecialIndex;
/*     */   
/*     */   protected final DefaultAttrValue mDefValue;
/*     */   
/*     */   protected final boolean mCfgNsAware;
/*     */   
/*     */   protected final boolean mCfgXml11;
/*     */   
/*     */   public DTDAttribute(PrefixedName name, DefaultAttrValue defValue, int specIndex, boolean nsAware, boolean xml11) {
/* 118 */     this.mName = name;
/* 119 */     this.mDefValue = defValue;
/* 120 */     this.mSpecialIndex = specIndex;
/* 121 */     this.mCfgNsAware = nsAware;
/* 122 */     this.mCfgXml11 = xml11;
/*     */   }
/*     */   
/*     */   public abstract DTDAttribute cloneWith(int paramInt);
/*     */   
/*     */   public final PrefixedName getName() {
/* 133 */     return this.mName;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 136 */     return this.mName.toString();
/*     */   }
/*     */   
/*     */   public final String getDefaultValue(ValidationContext ctxt, XMLValidator dtd) throws XMLStreamException {
/* 142 */     String val = this.mDefValue.getValueIfOk();
/* 143 */     if (val == null) {
/* 144 */       this.mDefValue.reportUndeclared(ctxt, dtd);
/* 149 */       val = this.mDefValue.getValue();
/*     */     } 
/* 151 */     return val;
/*     */   }
/*     */   
/*     */   public final int getSpecialIndex() {
/* 155 */     return this.mSpecialIndex;
/*     */   }
/*     */   
/*     */   public final boolean needsValidation() {
/* 159 */     return (getValueType() != 0);
/*     */   }
/*     */   
/*     */   public final boolean isFixed() {
/* 163 */     return this.mDefValue.isFixed();
/*     */   }
/*     */   
/*     */   public final boolean isRequired() {
/* 167 */     return this.mDefValue.isRequired();
/*     */   }
/*     */   
/*     */   public final boolean isSpecial() {
/* 177 */     return this.mDefValue.isSpecial();
/*     */   }
/*     */   
/*     */   public final boolean hasDefaultValue() {
/* 181 */     return this.mDefValue.hasDefaultValue();
/*     */   }
/*     */   
/*     */   public int getValueType() {
/* 191 */     return 0;
/*     */   }
/*     */   
/*     */   public String getValueTypeString() {
/* 196 */     return sTypes[getValueType()];
/*     */   }
/*     */   
/*     */   public boolean typeIsId() {
/* 200 */     return false;
/*     */   }
/*     */   
/*     */   public boolean typeIsNotation() {
/* 204 */     return false;
/*     */   }
/*     */   
/*     */   public abstract String validate(DTDValidatorBase paramDTDValidatorBase, char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public String validate(DTDValidatorBase v, String value, boolean normalize) throws XMLStreamException {
/* 228 */     int len = value.length();
/* 232 */     char[] cbuf = v.getTempAttrValueBuffer(value.length());
/* 233 */     if (len > 0)
/* 234 */       value.getChars(0, len, cbuf, 0); 
/* 236 */     return validate(v, cbuf, 0, len, normalize);
/*     */   }
/*     */   
/*     */   public abstract void validateDefault(InputProblemReporter paramInputProblemReporter, boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public String normalize(DTDValidatorBase v, char[] cbuf, int start, int end) {
/* 263 */     return StringUtil.normalizeSpaces(cbuf, start, end);
/*     */   }
/*     */   
/*     */   public void normalizeDefault() {
/* 273 */     String val = this.mDefValue.getValue();
/* 274 */     if (val.length() > 0) {
/* 275 */       char[] cbuf = val.toCharArray();
/* 276 */       String str = StringUtil.normalizeSpaces(cbuf, 0, cbuf.length);
/* 277 */       if (str != null)
/* 278 */         this.mDefValue.setValue(str); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String validateDefaultName(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 292 */     String origDefValue = this.mDefValue.getValue();
/* 293 */     String defValue = origDefValue.trim();
/* 295 */     if (defValue.length() == 0)
/* 296 */       reportValidationProblem(rep, "Invalid default value '" + defValue + "'; empty String is not a valid name"); 
/* 301 */     int illegalIx = WstxInputData.findIllegalNameChar(defValue, this.mCfgNsAware, this.mCfgXml11);
/* 302 */     if (illegalIx >= 0)
/* 303 */       if (illegalIx == 0) {
/* 304 */         reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character " + WstxInputData.getCharDesc(defValue.charAt(0)) + ") not valid first character of a name");
/*     */       } else {
/* 308 */         reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character #" + illegalIx + " (" + WstxInputData.getCharDesc(defValue.charAt(illegalIx)) + ") not valid name character");
/*     */       }  
/* 315 */     return normalize ? defValue : origDefValue;
/*     */   }
/*     */   
/*     */   protected String validateDefaultNames(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 321 */     String defValue = this.mDefValue.getValue().trim();
/* 322 */     int len = defValue.length();
/* 325 */     StringBuffer sb = null;
/* 326 */     int count = 0;
/* 327 */     int start = 0;
/* 330 */     label40: while (start < len) {
/* 331 */       char c = defValue.charAt(start);
/* 335 */       while (WstxInputData.isSpaceChar(c)) {
/* 338 */         if (++start >= len)
/*     */           break label40; 
/* 341 */         c = defValue.charAt(start);
/*     */       } 
/* 345 */       int i = start + 1;
/* 347 */       for (; i < len && 
/* 348 */         !WstxInputData.isSpaceChar(defValue.charAt(i)); i++);
/* 352 */       String token = defValue.substring(start, i);
/* 353 */       int illegalIx = WstxInputData.findIllegalNameChar(token, this.mCfgNsAware, this.mCfgXml11);
/* 354 */       if (illegalIx >= 0)
/* 355 */         if (illegalIx == 0) {
/* 356 */           reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character " + WstxInputData.getCharDesc(defValue.charAt(start)) + ") not valid first character of a name token");
/*     */         } else {
/* 361 */           reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character " + WstxInputData.getCharDesc(c) + ") not a valid name character");
/*     */         }  
/* 367 */       count++;
/* 368 */       if (normalize) {
/* 369 */         if (sb == null) {
/* 370 */           sb = new StringBuffer(i - start + 32);
/*     */         } else {
/* 372 */           sb.append(' ');
/*     */         } 
/* 374 */         sb.append(token);
/*     */       } 
/* 376 */       start = i + 1;
/*     */     } 
/* 379 */     if (count == 0)
/* 380 */       reportValidationProblem(rep, "Invalid default value '" + defValue + "'; empty String is not a valid name value"); 
/* 384 */     return normalize ? sb.toString() : defValue;
/*     */   }
/*     */   
/*     */   protected String validateDefaultNmToken(InputProblemReporter rep, boolean normalize) throws XMLStreamException {
/* 390 */     String origDefValue = this.mDefValue.getValue();
/* 391 */     String defValue = origDefValue.trim();
/* 393 */     if (defValue.length() == 0)
/* 394 */       reportValidationProblem(rep, "Invalid default value '" + defValue + "'; empty String is not a valid NMTOKEN"); 
/* 396 */     int illegalIx = WstxInputData.findIllegalNmtokenChar(defValue, this.mCfgNsAware, this.mCfgXml11);
/* 397 */     if (illegalIx >= 0)
/* 398 */       reportValidationProblem(rep, "Invalid default value '" + defValue + "'; character #" + illegalIx + " (" + WstxInputData.getCharDesc(defValue.charAt(illegalIx)) + ") not valid NMTOKEN character"); 
/* 404 */     return normalize ? defValue : origDefValue;
/*     */   }
/*     */   
/*     */   public String validateEnumValue(char[] cbuf, int start, int end, boolean normalize, WordResolver res) {
/* 425 */     if (normalize) {
/* 426 */       while (start < end && cbuf[start] <= ' ')
/* 427 */         start++; 
/* 429 */       while (--end > start && cbuf[end] <= ' ');
/* 432 */       end++;
/*     */     } 
/* 436 */     if (start >= end)
/* 437 */       return null; 
/* 439 */     return res.find(cbuf, start, end);
/*     */   }
/*     */   
/*     */   protected EntityDecl findEntityDecl(DTDValidatorBase v, char[] ch, int start, int len, int hash) throws XMLStreamException {
/* 446 */     Map entMap = v.getEntityMap();
/* 450 */     String id = new String(ch, start, len);
/* 451 */     EntityDecl ent = (EntityDecl)entMap.get(id);
/* 453 */     if (ent == null) {
/* 454 */       reportValidationProblem(v, "Referenced entity '" + id + "' not defined");
/* 455 */     } else if (ent.isParsed()) {
/* 456 */       reportValidationProblem(v, "Referenced entity '" + id + "' is not an unparsed entity");
/*     */     } 
/* 458 */     return ent;
/*     */   }
/*     */   
/*     */   protected void checkEntity(InputProblemReporter rep, String id, EntityDecl ent) throws XMLStreamException {
/* 469 */     if (ent == null) {
/* 470 */       rep.reportValidationProblem("Referenced entity '" + id + "' not defined");
/* 471 */     } else if (ent.isParsed()) {
/* 472 */       rep.reportValidationProblem("Referenced entity '" + id + "' is not an unparsed entity");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String reportInvalidChar(DTDValidatorBase v, char c, String msg) throws XMLStreamException {
/* 485 */     reportValidationProblem(v, "Invalid character " + WstxInputData.getCharDesc(c) + ": " + msg);
/* 486 */     return null;
/*     */   }
/*     */   
/*     */   protected String reportValidationProblem(DTDValidatorBase v, String msg) throws XMLStreamException {
/* 492 */     v.reportValidationProblem("Attribute '" + this.mName + "': " + msg);
/* 493 */     return null;
/*     */   }
/*     */   
/*     */   protected String reportValidationProblem(InputProblemReporter rep, String msg) throws XMLStreamException {
/* 505 */     rep.reportValidationProblem("Attribute definition '" + this.mName + "': " + msg);
/* 506 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDAttribute.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */