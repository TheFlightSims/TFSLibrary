/*     */ package org.codehaus.stax2.validation;
/*     */ 
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public class ValidatorPair extends XMLValidator {
/*     */   public static final String ATTR_TYPE_DEFAULT = "CDATA";
/*     */   
/*     */   protected XMLValidator mFirst;
/*     */   
/*     */   protected XMLValidator mSecond;
/*     */   
/*     */   public ValidatorPair(XMLValidator first, XMLValidator second) {
/*  33 */     this.mFirst = first;
/*  34 */     this.mSecond = second;
/*     */   }
/*     */   
/*     */   public XMLValidationSchema getSchema() {
/*  49 */     return null;
/*     */   }
/*     */   
/*     */   public void validateElementStart(String localName, String uri, String prefix) throws XMLStreamException {
/*  56 */     this.mFirst.validateElementStart(localName, uri, prefix);
/*  57 */     this.mSecond.validateElementStart(localName, uri, prefix);
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, String value) throws XMLStreamException {
/*  64 */     String retVal = this.mFirst.validateAttribute(localName, uri, prefix, value);
/*  66 */     if (retVal != null)
/*  67 */       value = retVal; 
/*  69 */     return this.mSecond.validateAttribute(localName, uri, prefix, value);
/*     */   }
/*     */   
/*     */   public String validateAttribute(String localName, String uri, String prefix, char[] valueChars, int valueStart, int valueEnd) throws XMLStreamException {
/*  78 */     String retVal = this.mFirst.validateAttribute(localName, uri, prefix, valueChars, valueStart, valueEnd);
/*  83 */     if (retVal != null)
/*  84 */       return this.mSecond.validateAttribute(localName, uri, prefix, retVal); 
/*  87 */     return this.mSecond.validateAttribute(localName, uri, prefix, valueChars, valueStart, valueEnd);
/*     */   }
/*     */   
/*     */   public int validateElementAndAttributes() throws XMLStreamException {
/*  94 */     int textType1 = this.mFirst.validateElementAndAttributes();
/*  95 */     int textType2 = this.mSecond.validateElementAndAttributes();
/* 101 */     return (textType1 < textType2) ? textType1 : textType2;
/*     */   }
/*     */   
/*     */   public int validateElementEnd(String localName, String uri, String prefix) throws XMLStreamException {
/* 107 */     int textType1 = this.mFirst.validateElementEnd(localName, uri, prefix);
/* 108 */     int textType2 = this.mSecond.validateElementEnd(localName, uri, prefix);
/* 111 */     return (textType1 < textType2) ? textType1 : textType2;
/*     */   }
/*     */   
/*     */   public void validateText(String text, boolean lastTextSegment) throws XMLStreamException {
/* 117 */     this.mFirst.validateText(text, lastTextSegment);
/* 118 */     this.mSecond.validateText(text, lastTextSegment);
/*     */   }
/*     */   
/*     */   public void validateText(char[] cbuf, int textStart, int textEnd, boolean lastTextSegment) throws XMLStreamException {
/* 125 */     this.mFirst.validateText(cbuf, textStart, textEnd, lastTextSegment);
/* 126 */     this.mSecond.validateText(cbuf, textStart, textEnd, lastTextSegment);
/*     */   }
/*     */   
/*     */   public void validationCompleted(boolean eod) throws XMLStreamException {
/* 132 */     this.mFirst.validationCompleted(eod);
/* 133 */     this.mSecond.validationCompleted(eod);
/*     */   }
/*     */   
/*     */   public String getAttributeType(int index) {
/* 144 */     String type = this.mFirst.getAttributeType(index);
/* 149 */     if (type == null || type.length() == 0 || type.equals("CDATA")) {
/* 150 */       String type2 = this.mSecond.getAttributeType(index);
/* 151 */       if (type2 != null && type2.length() > 0)
/* 152 */         return type2; 
/*     */     } 
/* 156 */     return type;
/*     */   }
/*     */   
/*     */   public int getIdAttrIndex() {
/* 161 */     int index = this.mFirst.getIdAttrIndex();
/* 162 */     if (index < 0)
/* 163 */       return this.mSecond.getIdAttrIndex(); 
/* 165 */     return index;
/*     */   }
/*     */   
/*     */   public int getNotationAttrIndex() {
/* 170 */     int index = this.mFirst.getNotationAttrIndex();
/* 171 */     if (index < 0)
/* 172 */       return this.mSecond.getNotationAttrIndex(); 
/* 174 */     return index;
/*     */   }
/*     */   
/*     */   public static boolean removeValidator(XMLValidator root, XMLValidationSchema schema, XMLValidator[] results) {
/* 185 */     if (root instanceof ValidatorPair)
/* 186 */       return ((ValidatorPair)root).doRemoveValidator(schema, results); 
/* 188 */     if (root.getSchema() == schema) {
/* 189 */       results[0] = root;
/* 190 */       results[1] = null;
/* 191 */       return true;
/*     */     } 
/* 194 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean removeValidator(XMLValidator root, XMLValidator vld, XMLValidator[] results) {
/* 199 */     if (root == vld) {
/* 200 */       results[0] = root;
/* 201 */       results[1] = null;
/* 202 */       return true;
/*     */     } 
/* 203 */     if (root instanceof ValidatorPair)
/* 204 */       return ((ValidatorPair)root).doRemoveValidator(vld, results); 
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   private boolean doRemoveValidator(XMLValidationSchema schema, XMLValidator[] results) {
/* 211 */     if (removeValidator(this.mFirst, schema, results)) {
/* 212 */       XMLValidator newFirst = results[1];
/* 213 */       if (newFirst == null) {
/* 214 */         results[1] = this.mSecond;
/*     */       } else {
/* 216 */         this.mFirst = newFirst;
/* 217 */         results[1] = this;
/*     */       } 
/* 219 */       return true;
/*     */     } 
/* 221 */     if (removeValidator(this.mSecond, schema, results)) {
/* 222 */       XMLValidator newSecond = results[1];
/* 223 */       if (newSecond == null) {
/* 224 */         results[1] = this.mFirst;
/*     */       } else {
/* 226 */         this.mSecond = newSecond;
/* 227 */         results[1] = this;
/*     */       } 
/* 229 */       return true;
/*     */     } 
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   private boolean doRemoveValidator(XMLValidator vld, XMLValidator[] results) {
/* 236 */     if (removeValidator(this.mFirst, vld, results)) {
/* 237 */       XMLValidator newFirst = results[1];
/* 238 */       if (newFirst == null) {
/* 239 */         results[1] = this.mSecond;
/*     */       } else {
/* 241 */         this.mFirst = newFirst;
/* 242 */         results[1] = this;
/*     */       } 
/* 244 */       return true;
/*     */     } 
/* 246 */     if (removeValidator(this.mSecond, vld, results)) {
/* 247 */       XMLValidator newSecond = results[1];
/* 248 */       if (newSecond == null) {
/* 249 */         results[1] = this.mFirst;
/*     */       } else {
/* 251 */         this.mSecond = newSecond;
/* 252 */         results[1] = this;
/*     */       } 
/* 254 */       return true;
/*     */     } 
/* 256 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\ValidatorPair.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */