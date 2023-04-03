/*     */ package org.apache.commons.lang.builder;
/*     */ 
/*     */ public class StandardToStringStyle extends ToStringStyle {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public boolean isUseClassName() {
/*  58 */     return super.isUseClassName();
/*     */   }
/*     */   
/*     */   public void setUseClassName(boolean useClassName) {
/*  67 */     super.setUseClassName(useClassName);
/*     */   }
/*     */   
/*     */   public boolean isUseShortClassName() {
/*  79 */     return super.isUseShortClassName();
/*     */   }
/*     */   
/*     */   public boolean isShortClassName() {
/*  90 */     return super.isUseShortClassName();
/*     */   }
/*     */   
/*     */   public void setUseShortClassName(boolean useShortClassName) {
/* 100 */     super.setUseShortClassName(useShortClassName);
/*     */   }
/*     */   
/*     */   public void setShortClassName(boolean shortClassName) {
/* 111 */     super.setUseShortClassName(shortClassName);
/*     */   }
/*     */   
/*     */   public boolean isUseIdentityHashCode() {
/* 121 */     return super.isUseIdentityHashCode();
/*     */   }
/*     */   
/*     */   public void setUseIdentityHashCode(boolean useIdentityHashCode) {
/* 130 */     super.setUseIdentityHashCode(useIdentityHashCode);
/*     */   }
/*     */   
/*     */   public boolean isUseFieldNames() {
/* 141 */     return super.isUseFieldNames();
/*     */   }
/*     */   
/*     */   public void setUseFieldNames(boolean useFieldNames) {
/* 150 */     super.setUseFieldNames(useFieldNames);
/*     */   }
/*     */   
/*     */   public boolean isDefaultFullDetail() {
/* 162 */     return super.isDefaultFullDetail();
/*     */   }
/*     */   
/*     */   public void setDefaultFullDetail(boolean defaultFullDetail) {
/* 172 */     super.setDefaultFullDetail(defaultFullDetail);
/*     */   }
/*     */   
/*     */   public boolean isArrayContentDetail() {
/* 183 */     return super.isArrayContentDetail();
/*     */   }
/*     */   
/*     */   public void setArrayContentDetail(boolean arrayContentDetail) {
/* 192 */     super.setArrayContentDetail(arrayContentDetail);
/*     */   }
/*     */   
/*     */   public String getArrayStart() {
/* 203 */     return super.getArrayStart();
/*     */   }
/*     */   
/*     */   public void setArrayStart(String arrayStart) {
/* 215 */     super.setArrayStart(arrayStart);
/*     */   }
/*     */   
/*     */   public String getArrayEnd() {
/* 226 */     return super.getArrayEnd();
/*     */   }
/*     */   
/*     */   public void setArrayEnd(String arrayEnd) {
/* 238 */     super.setArrayEnd(arrayEnd);
/*     */   }
/*     */   
/*     */   public String getArraySeparator() {
/* 249 */     return super.getArraySeparator();
/*     */   }
/*     */   
/*     */   public void setArraySeparator(String arraySeparator) {
/* 261 */     super.setArraySeparator(arraySeparator);
/*     */   }
/*     */   
/*     */   public String getContentStart() {
/* 272 */     return super.getContentStart();
/*     */   }
/*     */   
/*     */   public void setContentStart(String contentStart) {
/* 284 */     super.setContentStart(contentStart);
/*     */   }
/*     */   
/*     */   public String getContentEnd() {
/* 295 */     return super.getContentEnd();
/*     */   }
/*     */   
/*     */   public void setContentEnd(String contentEnd) {
/* 307 */     super.setContentEnd(contentEnd);
/*     */   }
/*     */   
/*     */   public String getFieldNameValueSeparator() {
/* 318 */     return super.getFieldNameValueSeparator();
/*     */   }
/*     */   
/*     */   public void setFieldNameValueSeparator(String fieldNameValueSeparator) {
/* 330 */     super.setFieldNameValueSeparator(fieldNameValueSeparator);
/*     */   }
/*     */   
/*     */   public String getFieldSeparator() {
/* 341 */     return super.getFieldSeparator();
/*     */   }
/*     */   
/*     */   public void setFieldSeparator(String fieldSeparator) {
/* 353 */     super.setFieldSeparator(fieldSeparator);
/*     */   }
/*     */   
/*     */   public boolean isFieldSeparatorAtStart() {
/* 366 */     return super.isFieldSeparatorAtStart();
/*     */   }
/*     */   
/*     */   public void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart) {
/* 377 */     super.setFieldSeparatorAtStart(fieldSeparatorAtStart);
/*     */   }
/*     */   
/*     */   public boolean isFieldSeparatorAtEnd() {
/* 390 */     return super.isFieldSeparatorAtEnd();
/*     */   }
/*     */   
/*     */   public void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd) {
/* 401 */     super.setFieldSeparatorAtEnd(fieldSeparatorAtEnd);
/*     */   }
/*     */   
/*     */   public String getNullText() {
/* 412 */     return super.getNullText();
/*     */   }
/*     */   
/*     */   public void setNullText(String nullText) {
/* 424 */     super.setNullText(nullText);
/*     */   }
/*     */   
/*     */   public String getSizeStartText() {
/* 438 */     return super.getSizeStartText();
/*     */   }
/*     */   
/*     */   public void setSizeStartText(String sizeStartText) {
/* 453 */     super.setSizeStartText(sizeStartText);
/*     */   }
/*     */   
/*     */   public String getSizeEndText() {
/* 467 */     return super.getSizeEndText();
/*     */   }
/*     */   
/*     */   public void setSizeEndText(String sizeEndText) {
/* 482 */     super.setSizeEndText(sizeEndText);
/*     */   }
/*     */   
/*     */   public String getSummaryObjectStartText() {
/* 496 */     return super.getSummaryObjectStartText();
/*     */   }
/*     */   
/*     */   public void setSummaryObjectStartText(String summaryObjectStartText) {
/* 511 */     super.setSummaryObjectStartText(summaryObjectStartText);
/*     */   }
/*     */   
/*     */   public String getSummaryObjectEndText() {
/* 525 */     return super.getSummaryObjectEndText();
/*     */   }
/*     */   
/*     */   public void setSummaryObjectEndText(String summaryObjectEndText) {
/* 540 */     super.setSummaryObjectEndText(summaryObjectEndText);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\StandardToStringStyle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */