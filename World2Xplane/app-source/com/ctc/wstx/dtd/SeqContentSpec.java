/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.util.PrefixedName;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class SeqContentSpec extends ContentSpec {
/*     */   final boolean mNsAware;
/*     */   
/*     */   final ContentSpec[] mContentSpecs;
/*     */   
/*     */   public SeqContentSpec(boolean nsAware, char arity, ContentSpec[] subSpecs) {
/*  26 */     super(arity);
/*  27 */     this.mNsAware = nsAware;
/*  28 */     this.mContentSpecs = subSpecs;
/*     */   }
/*     */   
/*     */   public static SeqContentSpec construct(boolean nsAware, char arity, Collection subSpecs) {
/*  33 */     ContentSpec[] specs = new ContentSpec[subSpecs.size()];
/*  34 */     subSpecs.toArray((Object[])specs);
/*  35 */     return new SeqContentSpec(nsAware, arity, specs);
/*     */   }
/*     */   
/*     */   public StructValidator getSimpleValidator() {
/*  49 */     ContentSpec[] specs = this.mContentSpecs;
/*  50 */     int i = 0;
/*  51 */     int len = specs.length;
/*  53 */     for (; i < len && 
/*  54 */       specs[i].isLeaf(); i++);
/*  59 */     if (i == len) {
/*  60 */       PrefixedName[] set = new PrefixedName[len];
/*  61 */       for (i = 0; i < len; i++) {
/*  62 */         TokenContentSpec ss = (TokenContentSpec)specs[i];
/*  63 */         set[i] = ss.getName();
/*     */       } 
/*  65 */       return new Validator(this.mArity, set);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */   
/*     */   public ModelNode rewrite() {
/*  79 */     ModelNode model = rewrite(this.mContentSpecs, 0, this.mContentSpecs.length);
/*  82 */     if (this.mArity == '*')
/*  83 */       return new StarModel(model); 
/*  85 */     if (this.mArity == '?')
/*  86 */       return new OptionalModel(model); 
/*  88 */     if (this.mArity == '+')
/*  89 */       return new ConcatModel(model, new StarModel(model.cloneModel())); 
/*  92 */     return model;
/*     */   }
/*     */   
/*     */   private ModelNode rewrite(ContentSpec[] specs, int first, int last) {
/*  98 */     int count = last - first;
/*  99 */     if (count > 3) {
/* 100 */       int mid = last + first + 1 >> 1;
/* 101 */       return new ConcatModel(rewrite(specs, first, mid), rewrite(specs, mid, last));
/*     */     } 
/* 104 */     ConcatModel model = new ConcatModel(specs[first].rewrite(), specs[first + 1].rewrite());
/* 106 */     if (count == 3)
/* 107 */       model = new ConcatModel(model, specs[first + 2].rewrite()); 
/* 109 */     return model;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     StringBuffer sb = new StringBuffer();
/* 115 */     sb.append('(');
/* 117 */     for (int i = 0; i < this.mContentSpecs.length; i++) {
/* 118 */       if (i > 0)
/* 119 */         sb.append(", "); 
/* 121 */       sb.append(this.mContentSpecs[i].toString());
/*     */     } 
/* 123 */     sb.append(')');
/* 125 */     if (this.mArity != ' ')
/* 126 */       sb.append(this.mArity); 
/* 128 */     return sb.toString();
/*     */   }
/*     */   
/*     */   static final class Validator extends StructValidator {
/*     */     final char mArity;
/*     */     
/*     */     final PrefixedName[] mNames;
/*     */     
/* 151 */     int mRounds = 0;
/*     */     
/* 156 */     int mStep = 0;
/*     */     
/*     */     public Validator(char arity, PrefixedName[] names) {
/* 160 */       this.mArity = arity;
/* 161 */       this.mNames = names;
/*     */     }
/*     */     
/*     */     public StructValidator newInstance() {
/* 170 */       return new Validator(this.mArity, this.mNames);
/*     */     }
/*     */     
/*     */     public String tryToValidate(PrefixedName elemName) {
/* 176 */       if (this.mStep == 0 && this.mRounds == 1 && (
/* 177 */         this.mArity == '?' || this.mArity == ' '))
/* 178 */         return "was not expecting any more elements in the sequence (" + concatNames(this.mNames) + ")"; 
/* 183 */       PrefixedName next = this.mNames[this.mStep];
/* 184 */       if (!elemName.equals(next))
/* 185 */         return expElem(this.mStep); 
/* 187 */       if (++this.mStep == this.mNames.length) {
/* 188 */         this.mRounds++;
/* 189 */         this.mStep = 0;
/*     */       } 
/* 191 */       return null;
/*     */     }
/*     */     
/*     */     public String fullyValid() {
/* 196 */       if (this.mStep != 0)
/* 197 */         return expElem(this.mStep) + "; got end element"; 
/* 200 */       switch (this.mArity) {
/*     */         case '*':
/*     */         case '?':
/* 203 */           return null;
/*     */         case ' ':
/*     */         case '+':
/* 206 */           if (this.mRounds > 0)
/* 207 */             return null; 
/* 209 */           return "Expected sequence (" + concatNames(this.mNames) + "); got end element";
/*     */       } 
/* 212 */       throw new IllegalStateException("Internal error");
/*     */     }
/*     */     
/*     */     private String expElem(int step) {
/* 217 */       return "expected element <" + this.mNames[step] + "> in sequence (" + concatNames(this.mNames) + ")";
/*     */     }
/*     */     
/*     */     static final String concatNames(PrefixedName[] names) {
/* 223 */       StringBuffer sb = new StringBuffer();
/* 224 */       for (int i = 0, len = names.length; i < len; i++) {
/* 225 */         if (i > 0)
/* 226 */           sb.append(", "); 
/* 228 */         sb.append(names[i].toString());
/*     */       } 
/* 230 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\SeqContentSpec.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */