/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ 
/*     */ public class TokenContentSpec extends ContentSpec {
/*  14 */   static final TokenContentSpec sDummy = new TokenContentSpec(' ', new PrefixedName("*", "*"));
/*     */   
/*     */   final PrefixedName mElemName;
/*     */   
/*     */   public TokenContentSpec(char arity, PrefixedName elemName) {
/*  27 */     super(arity);
/*  28 */     this.mElemName = elemName;
/*     */   }
/*     */   
/*     */   public static TokenContentSpec construct(char arity, PrefixedName elemName) {
/*  33 */     return new TokenContentSpec(arity, elemName);
/*     */   }
/*     */   
/*     */   public static TokenContentSpec getDummySpec() {
/*  37 */     return sDummy;
/*     */   }
/*     */   
/*     */   public boolean isLeaf() {
/*  47 */     return (this.mArity == ' ');
/*     */   }
/*     */   
/*     */   public PrefixedName getName() {
/*  51 */     return this.mElemName;
/*     */   }
/*     */   
/*     */   public StructValidator getSimpleValidator() {
/*  55 */     return new Validator(this.mArity, this.mElemName);
/*     */   }
/*     */   
/*     */   public ModelNode rewrite() {
/*  59 */     TokenModel model = new TokenModel(this.mElemName);
/*  60 */     if (this.mArity == '*')
/*  61 */       return new StarModel(model); 
/*  63 */     if (this.mArity == '?')
/*  64 */       return new OptionalModel(model); 
/*  66 */     if (this.mArity == '+')
/*  67 */       return new ConcatModel(model, new StarModel(new TokenModel(this.mElemName))); 
/*  70 */     return model;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  74 */     return (this.mArity == ' ') ? this.mElemName.toString() : (this.mElemName.toString() + this.mArity);
/*     */   }
/*     */   
/*     */   static final class Validator extends StructValidator {
/*     */     final char mArity;
/*     */     
/*     */     final PrefixedName mElemName;
/*     */     
/*  90 */     int mCount = 0;
/*     */     
/*     */     public Validator(char arity, PrefixedName elemName) {
/*  94 */       this.mArity = arity;
/*  95 */       this.mElemName = elemName;
/*     */     }
/*     */     
/*     */     public StructValidator newInstance() {
/* 106 */       return (this.mArity == '*') ? this : new Validator(this.mArity, this.mElemName);
/*     */     }
/*     */     
/*     */     public String tryToValidate(PrefixedName elemName) {
/* 111 */       if (!elemName.equals(this.mElemName))
/* 112 */         return "Expected element <" + this.mElemName + ">"; 
/* 114 */       if (++this.mCount > 1 && (this.mArity == '?' || this.mArity == ' '))
/* 115 */         return "More than one instance of element <" + this.mElemName + ">"; 
/* 117 */       return null;
/*     */     }
/*     */     
/*     */     public String fullyValid() {
/* 122 */       switch (this.mArity) {
/*     */         case '*':
/*     */         case '?':
/* 125 */           return null;
/*     */         case ' ':
/*     */         case '+':
/* 128 */           if (this.mCount > 0)
/* 129 */             return null; 
/* 131 */           return "Expected " + ((this.mArity == '+') ? "at least one" : "") + " element <" + this.mElemName + ">";
/*     */       } 
/* 135 */       throw new IllegalStateException(ErrorConsts.ERR_INTERNAL);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\TokenContentSpec.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */