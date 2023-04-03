/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class ResolveSource {
/*     */   private final AbstractConfigObject root;
/*     */   
/*     */   private final Map<AbstractConfigValue, ResolveReplacer> replacements;
/*     */   
/*     */   ResolveSource(AbstractConfigObject root) {
/*  22 */     this.root = root;
/*  23 */     this.replacements = new IdentityHashMap<AbstractConfigValue, ResolveReplacer>();
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue findInObject(AbstractConfigObject obj, ResolveContext context, SubstitutionExpression subst) throws AbstractConfigValue.NotPossibleToResolve {
/*  29 */     return obj.peekPath(subst.path(), context);
/*     */   }
/*     */   
/*     */   AbstractConfigValue lookupSubst(ResolveContext context, SubstitutionExpression subst, int prefixLength) throws AbstractConfigValue.NotPossibleToResolve {
/*  34 */     context.trace(subst);
/*     */     try {
/*  38 */       AbstractConfigValue result = findInObject(this.root, context, subst);
/*  40 */       if (result == null) {
/*  44 */         SubstitutionExpression unprefixed = subst.changePath(subst.path().subPath(prefixLength));
/*  48 */         context.untrace();
/*  49 */         context.trace(unprefixed);
/*  51 */         if (prefixLength > 0)
/*  52 */           result = findInObject(this.root, context, unprefixed); 
/*  55 */         if (result == null && context.options().getUseSystemEnvironment())
/*  56 */           result = findInObject(ConfigImpl.envVariablesAsConfigObject(), context, unprefixed); 
/*     */       } 
/*  61 */       if (result != null)
/*  62 */         result = context.resolve(result); 
/*  65 */       return result;
/*     */     } finally {
/*  67 */       context.untrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   void replace(AbstractConfigValue value, ResolveReplacer replacer) {
/*  72 */     ResolveReplacer old = this.replacements.put(value, replacer);
/*  73 */     if (old != null)
/*  74 */       throw new ConfigException.BugOrBroken("should not have replaced the same value twice: " + value); 
/*     */   }
/*     */   
/*     */   void unreplace(AbstractConfigValue value) {
/*  79 */     ResolveReplacer replacer = this.replacements.remove(value);
/*  80 */     if (replacer == null)
/*  81 */       throw new ConfigException.BugOrBroken("unreplace() without replace(): " + value); 
/*     */   }
/*     */   
/*     */   private AbstractConfigValue replacement(ResolveContext context, AbstractConfigValue value) throws AbstractConfigValue.NotPossibleToResolve {
/*  86 */     ResolveReplacer replacer = this.replacements.get(value);
/*  87 */     if (replacer == null)
/*  88 */       return value; 
/*  90 */     return replacer.replace(context);
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolveCheckingReplacement(ResolveContext context, AbstractConfigValue original) throws AbstractConfigValue.NotPossibleToResolve {
/* 102 */     AbstractConfigValue replacement = replacement(context, original);
/* 104 */     if (replacement != original)
/* 106 */       return context.resolve(replacement); 
/* 110 */     AbstractConfigValue resolved = original.resolveSubstitutions(context);
/* 112 */     return resolved;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ResolveSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */