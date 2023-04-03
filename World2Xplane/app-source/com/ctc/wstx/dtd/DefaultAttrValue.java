/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import java.text.MessageFormat;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.validation.ValidationContext;
/*     */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*     */ import org.codehaus.stax2.validation.XMLValidator;
/*     */ 
/*     */ public final class DefaultAttrValue {
/*     */   public static final int DEF_DEFAULT = 1;
/*     */   
/*     */   public static final int DEF_IMPLIED = 2;
/*     */   
/*     */   public static final int DEF_REQUIRED = 3;
/*     */   
/*     */   public static final int DEF_FIXED = 4;
/*     */   
/*  57 */   static final DefaultAttrValue sImplied = new DefaultAttrValue(2);
/*     */   
/*  59 */   static final DefaultAttrValue sRequired = new DefaultAttrValue(3);
/*     */   
/*     */   final int mDefValueType;
/*     */   
/*  77 */   private String mValue = null;
/*     */   
/*  83 */   private UndeclaredEntity mUndeclaredEntity = null;
/*     */   
/*     */   private DefaultAttrValue(int defValueType) {
/*  93 */     this.mDefValueType = defValueType;
/*     */   }
/*     */   
/*     */   public static DefaultAttrValue constructImplied() {
/*  96 */     return sImplied;
/*     */   }
/*     */   
/*     */   public static DefaultAttrValue constructRequired() {
/*  97 */     return sRequired;
/*     */   }
/*     */   
/*     */   public static DefaultAttrValue constructFixed() {
/* 100 */     return new DefaultAttrValue(4);
/*     */   }
/*     */   
/*     */   public static DefaultAttrValue constructOptional() {
/* 104 */     return new DefaultAttrValue(1);
/*     */   }
/*     */   
/*     */   public void setValue(String v) {
/* 108 */     this.mValue = v;
/*     */   }
/*     */   
/*     */   public void addUndeclaredPE(String name, Location loc) {
/* 113 */     addUndeclaredEntity(name, loc, true);
/*     */   }
/*     */   
/*     */   public void addUndeclaredGE(String name, Location loc) {
/* 118 */     addUndeclaredEntity(name, loc, false);
/*     */   }
/*     */   
/*     */   public void reportUndeclared(ValidationContext ctxt, XMLValidator dtd) throws XMLStreamException {
/* 124 */     this.mUndeclaredEntity.reportUndeclared(ctxt, dtd);
/*     */   }
/*     */   
/*     */   public boolean hasUndeclaredEntities() {
/* 134 */     return (this.mUndeclaredEntity != null);
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 138 */     return this.mValue;
/*     */   }
/*     */   
/*     */   public String getValueIfOk() {
/* 149 */     return (this.mUndeclaredEntity == null) ? this.mValue : null;
/*     */   }
/*     */   
/*     */   public boolean isRequired() {
/* 153 */     return (this == sRequired);
/*     */   }
/*     */   
/*     */   public boolean isFixed() {
/* 157 */     return (this.mDefValueType == 4);
/*     */   }
/*     */   
/*     */   public boolean hasDefaultValue() {
/* 161 */     return (this.mDefValueType == 1 || this.mDefValueType == 4);
/*     */   }
/*     */   
/*     */   public boolean isSpecial() {
/* 173 */     return (this != sImplied);
/*     */   }
/*     */   
/*     */   private void addUndeclaredEntity(String name, Location loc, boolean isPe) {
/* 184 */     if (this.mUndeclaredEntity == null)
/* 185 */       this.mUndeclaredEntity = new UndeclaredEntity(name, loc, isPe); 
/*     */   }
/*     */   
/*     */   static final class UndeclaredEntity {
/*     */     final String mName;
/*     */     
/*     */     final boolean mIsPe;
/*     */     
/*     */     final Location mLocation;
/*     */     
/*     */     UndeclaredEntity(String name, Location loc, boolean isPe) {
/* 203 */       this.mName = name;
/* 204 */       this.mIsPe = isPe;
/* 205 */       this.mLocation = loc;
/*     */     }
/*     */     
/*     */     public void reportUndeclared(ValidationContext ctxt, XMLValidator dtd) throws XMLStreamException {
/* 211 */       String msg = MessageFormat.format(ErrorConsts.ERR_DTD_UNDECLARED_ENTITY, new Object[] { this.mIsPe ? "parsed" : "general", this.mName });
/* 212 */       XMLValidationProblem prob = new XMLValidationProblem(this.mLocation, msg, 3);
/* 214 */       prob.setReporter(dtd);
/* 215 */       ctxt.reportProblem(prob);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DefaultAttrValue.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */