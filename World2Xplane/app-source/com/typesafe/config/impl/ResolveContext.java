/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigResolveOptions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ResolveContext {
/*     */   private final ResolveSource source;
/*     */   
/*     */   private final ResolveMemos memos;
/*     */   
/*     */   private final ConfigResolveOptions options;
/*     */   
/*     */   private final Path restrictToChild;
/*     */   
/*     */   private final List<SubstitutionExpression> expressionTrace;
/*     */   
/*     */   ResolveContext(ResolveSource source, ResolveMemos memos, ConfigResolveOptions options, Path restrictToChild, List<SubstitutionExpression> expressionTrace) {
/*  34 */     this.source = source;
/*  35 */     this.memos = memos;
/*  36 */     this.options = options;
/*  37 */     this.restrictToChild = restrictToChild;
/*  38 */     this.expressionTrace = expressionTrace;
/*     */   }
/*     */   
/*     */   ResolveContext(AbstractConfigObject root, ConfigResolveOptions options, Path restrictToChild) {
/*  44 */     this(new ResolveSource(root), new ResolveMemos(), options, restrictToChild, new ArrayList<SubstitutionExpression>());
/*     */   }
/*     */   
/*     */   ResolveSource source() {
/*  49 */     return this.source;
/*     */   }
/*     */   
/*     */   ConfigResolveOptions options() {
/*  53 */     return this.options;
/*     */   }
/*     */   
/*     */   boolean isRestrictedToChild() {
/*  57 */     return (this.restrictToChild != null);
/*     */   }
/*     */   
/*     */   Path restrictToChild() {
/*  61 */     return this.restrictToChild;
/*     */   }
/*     */   
/*     */   ResolveContext restrict(Path restrictTo) {
/*  65 */     if (restrictTo == this.restrictToChild)
/*  66 */       return this; 
/*  68 */     return new ResolveContext(this.source, this.memos, this.options, restrictTo, this.expressionTrace);
/*     */   }
/*     */   
/*     */   ResolveContext unrestricted() {
/*  72 */     return restrict(null);
/*     */   }
/*     */   
/*     */   void trace(SubstitutionExpression expr) {
/*  76 */     this.expressionTrace.add(expr);
/*     */   }
/*     */   
/*     */   void untrace() {
/*  80 */     this.expressionTrace.remove(this.expressionTrace.size() - 1);
/*     */   }
/*     */   
/*     */   String traceString() {
/*  84 */     String separator = ", ";
/*  85 */     StringBuilder sb = new StringBuilder();
/*  86 */     for (SubstitutionExpression expr : this.expressionTrace) {
/*  87 */       sb.append(expr.toString());
/*  88 */       sb.append(separator);
/*     */     } 
/*  90 */     if (sb.length() > 0)
/*  91 */       sb.setLength(sb.length() - separator.length()); 
/*  92 */     return sb.toString();
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolve(AbstractConfigValue original) throws AbstractConfigValue.NotPossibleToResolve {
/*  98 */     MemoKey fullKey = new MemoKey(original, null);
/*  99 */     MemoKey restrictedKey = null;
/* 101 */     AbstractConfigValue cached = this.memos.get(fullKey);
/* 106 */     if (cached == null && isRestrictedToChild()) {
/* 107 */       restrictedKey = new MemoKey(original, restrictToChild());
/* 108 */       cached = this.memos.get(restrictedKey);
/*     */     } 
/* 111 */     if (cached != null)
/* 112 */       return cached; 
/* 114 */     AbstractConfigValue resolved = this.source.resolveCheckingReplacement(this, original);
/* 116 */     if (resolved == null || resolved.resolveStatus() == ResolveStatus.RESOLVED) {
/* 121 */       this.memos.put(fullKey, resolved);
/* 126 */     } else if (isRestrictedToChild()) {
/* 127 */       if (restrictedKey == null)
/* 128 */         throw new ConfigException.BugOrBroken("restrictedKey should not be null here"); 
/* 131 */       this.memos.put(restrictedKey, resolved);
/* 132 */     } else if (options().getAllowUnresolved()) {
/* 133 */       this.memos.put(fullKey, resolved);
/*     */     } else {
/* 135 */       throw new ConfigException.BugOrBroken("resolveSubstitutions() did not give us a resolved object");
/*     */     } 
/* 140 */     return resolved;
/*     */   }
/*     */   
/*     */   static AbstractConfigValue resolve(AbstractConfigValue value, AbstractConfigObject root, ConfigResolveOptions options) {
/* 146 */     ResolveContext context = new ResolveContext(root, options, null);
/*     */     try {
/* 149 */       return context.resolve(value);
/* 150 */     } catch (NotPossibleToResolve e) {
/* 152 */       throw new ConfigException.BugOrBroken("NotPossibleToResolve was thrown from an outermost resolve", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ResolveContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */