/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.util.ExceptionUtil;
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ChoiceContentSpec extends ContentSpec {
/*     */   final boolean mNsAware;
/*     */   
/*     */   final boolean mHasMixed;
/*     */   
/*     */   final ContentSpec[] mContentSpecs;
/*     */   
/*     */   private ChoiceContentSpec(boolean nsAware, char arity, boolean mixed, ContentSpec[] specs) {
/*  34 */     super(arity);
/*  35 */     this.mNsAware = nsAware;
/*  36 */     this.mHasMixed = mixed;
/*  37 */     this.mContentSpecs = specs;
/*     */   }
/*     */   
/*     */   private ChoiceContentSpec(boolean nsAware, char arity, boolean mixed, Collection specs) {
/*  42 */     super(arity);
/*  43 */     this.mNsAware = nsAware;
/*  44 */     this.mHasMixed = mixed;
/*  45 */     this.mContentSpecs = new ContentSpec[specs.size()];
/*  46 */     specs.toArray((Object[])this.mContentSpecs);
/*     */   }
/*     */   
/*     */   public static ChoiceContentSpec constructChoice(boolean nsAware, char arity, Collection specs) {
/*  52 */     return new ChoiceContentSpec(nsAware, arity, false, specs);
/*     */   }
/*     */   
/*     */   public static ChoiceContentSpec constructMixed(boolean nsAware, Collection specs) {
/*  57 */     return new ChoiceContentSpec(nsAware, '*', true, specs);
/*     */   }
/*     */   
/*     */   public StructValidator getSimpleValidator() {
/*     */     int i;
/*  72 */     ContentSpec[] specs = this.mContentSpecs;
/*  73 */     int len = specs.length;
/*  76 */     if (this.mHasMixed) {
/*  77 */       i = len;
/*     */     } else {
/*  79 */       i = 0;
/*  80 */       for (; i < len && 
/*  81 */         specs[i].isLeaf(); i++);
/*     */     } 
/*  87 */     if (i == len) {
/*  88 */       PrefixedNameSet keyset = namesetFromSpecs(this.mNsAware, specs);
/*  89 */       return new Validator(this.mArity, keyset);
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public ModelNode rewrite() {
/*  99 */     ContentSpec[] specs = this.mContentSpecs;
/* 100 */     int len = specs.length;
/* 101 */     ModelNode[] models = new ModelNode[len];
/* 102 */     for (int i = 0; i < len; i++)
/* 103 */       models[i] = specs[i].rewrite(); 
/* 105 */     ChoiceModel model = new ChoiceModel(models);
/* 108 */     if (this.mArity == '*')
/* 109 */       return new StarModel(model); 
/* 111 */     if (this.mArity == '?')
/* 112 */       return new OptionalModel(model); 
/* 114 */     if (this.mArity == '+')
/* 115 */       return new ConcatModel(model, new StarModel(model.cloneModel())); 
/* 118 */     return model;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     StringBuffer sb = new StringBuffer();
/* 125 */     if (this.mHasMixed) {
/* 126 */       sb.append("(#PCDATA | ");
/*     */     } else {
/* 128 */       sb.append('(');
/*     */     } 
/* 130 */     for (int i = 0; i < this.mContentSpecs.length; i++) {
/* 131 */       if (i > 0)
/* 132 */         sb.append(" | "); 
/* 134 */       sb.append(this.mContentSpecs[i].toString());
/*     */     } 
/* 136 */     sb.append(')');
/* 138 */     if (this.mArity != ' ')
/* 139 */       sb.append(this.mArity); 
/* 141 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected static PrefixedNameSet namesetFromSpecs(boolean nsAware, ContentSpec[] specs) {
/* 158 */     int len = specs.length;
/* 159 */     PrefixedName[] nameArray = new PrefixedName[len];
/* 160 */     for (int i = 0; i < len; i++)
/* 161 */       nameArray[i] = ((TokenContentSpec)specs[i]).getName(); 
/* 164 */     if (len < 5)
/* 165 */       return new SmallPrefixedNameSet(nsAware, nameArray); 
/* 167 */     return new LargePrefixedNameSet(nsAware, nameArray);
/*     */   }
/*     */   
/*     */   static final class Validator extends StructValidator {
/*     */     final char mArity;
/*     */     
/*     */     final PrefixedNameSet mNames;
/*     */     
/* 183 */     int mCount = 0;
/*     */     
/*     */     public Validator(char arity, PrefixedNameSet names) {
/* 187 */       this.mArity = arity;
/* 188 */       this.mNames = names;
/*     */     }
/*     */     
/*     */     public StructValidator newInstance() {
/* 199 */       return (this.mArity == '*') ? this : new Validator(this.mArity, this.mNames);
/*     */     }
/*     */     
/*     */     public String tryToValidate(PrefixedName elemName) {
/* 204 */       if (!this.mNames.contains(elemName)) {
/* 205 */         if (this.mNames.hasMultiple())
/* 206 */           return "Expected one of (" + this.mNames.toString(" | ") + ")"; 
/* 208 */         return "Expected <" + this.mNames.toString("") + ">";
/*     */       } 
/* 210 */       if (++this.mCount > 1 && (this.mArity == '?' || this.mArity == ' ')) {
/* 211 */         if (this.mNames.hasMultiple())
/* 212 */           return "Expected $END (already had one of [" + this.mNames.toString(" | ") + "]"; 
/* 215 */         return "Expected $END (already had one <" + this.mNames.toString("") + ">]";
/*     */       } 
/* 218 */       return null;
/*     */     }
/*     */     
/*     */     public String fullyValid() {
/* 223 */       switch (this.mArity) {
/*     */         case '*':
/*     */         case '?':
/* 226 */           return null;
/*     */         case ' ':
/*     */         case '+':
/* 229 */           if (this.mCount > 0)
/* 230 */             return null; 
/* 232 */           return "Expected " + ((this.mArity == '+') ? "at least" : "") + " one of elements (" + this.mNames + ")";
/*     */       } 
/* 236 */       ExceptionUtil.throwGenericInternal();
/* 237 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\ChoiceContentSpec.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */