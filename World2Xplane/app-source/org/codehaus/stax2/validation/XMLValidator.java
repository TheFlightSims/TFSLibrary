/*     */ package org.codehaus.stax2.validation;
/*     */ 
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class XMLValidator {
/*     */   public static final int CONTENT_ALLOW_NONE = 0;
/*     */   
/*     */   public static final int CONTENT_ALLOW_WS = 1;
/*     */   
/*     */   public static final int CONTENT_ALLOW_WS_NONSTRICT = 2;
/*     */   
/*     */   public static final int CONTENT_ALLOW_VALIDATABLE_TEXT = 3;
/*     */   
/*     */   public static final int CONTENT_ALLOW_ANY_TEXT = 4;
/*     */   
/*     */   public static final int CONTENT_ALLOW_UNDEFINED = 5;
/*     */   
/*     */   public String getSchemaType() {
/* 106 */     XMLValidationSchema sch = getSchema();
/* 107 */     return (sch == null) ? null : sch.getSchemaType();
/*     */   }
/*     */   
/*     */   public abstract XMLValidationSchema getSchema();
/*     */   
/*     */   public abstract void validateElementStart(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract String validateAttribute(String paramString1, String paramString2, String paramString3, String paramString4) throws XMLStreamException;
/*     */   
/*     */   public abstract String validateAttribute(String paramString1, String paramString2, String paramString3, char[] paramArrayOfchar, int paramInt1, int paramInt2) throws XMLStreamException;
/*     */   
/*     */   public abstract int validateElementAndAttributes() throws XMLStreamException;
/*     */   
/*     */   public abstract int validateElementEnd(String paramString1, String paramString2, String paramString3) throws XMLStreamException;
/*     */   
/*     */   public abstract void validateText(String paramString, boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public abstract void validateText(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public abstract void validationCompleted(boolean paramBoolean) throws XMLStreamException;
/*     */   
/*     */   public abstract String getAttributeType(int paramInt);
/*     */   
/*     */   public abstract int getIdAttrIndex();
/*     */   
/*     */   public abstract int getNotationAttrIndex();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\XMLValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */