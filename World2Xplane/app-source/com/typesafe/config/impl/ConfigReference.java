/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ 
/*     */ final class ConfigReference extends AbstractConfigValue implements Unmergeable {
/*     */   private final SubstitutionExpression expr;
/*     */   
/*     */   private final int prefixLength;
/*     */   
/*     */   ConfigReference(ConfigOrigin origin, SubstitutionExpression expr) {
/*  23 */     this(origin, expr, 0);
/*     */   }
/*     */   
/*     */   private ConfigReference(ConfigOrigin origin, SubstitutionExpression expr, int prefixLength) {
/*  27 */     super(origin);
/*  28 */     this.expr = expr;
/*  29 */     this.prefixLength = prefixLength;
/*     */   }
/*     */   
/*     */   private ConfigException.NotResolved notResolved() {
/*  33 */     return new ConfigException.NotResolved("need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/*  40 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Object unwrapped() {
/*  45 */     throw notResolved();
/*     */   }
/*     */   
/*     */   protected ConfigReference newCopy(ConfigOrigin newOrigin) {
/*  50 */     return new ConfigReference(newOrigin, this.expr, this.prefixLength);
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<ConfigReference> unmergedValues() {
/*  60 */     return Collections.singleton(this);
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolveSubstitutions(ResolveContext context) {
/*  69 */     context.source().replace(this, ResolveReplacer.cycleResolveReplacer);
/*     */     try {
/*     */       AbstractConfigValue abstractConfigValue;
/*     */       try {
/*  73 */         abstractConfigValue = context.source().lookupSubst(context, this.expr, this.prefixLength);
/*  74 */       } catch (NotPossibleToResolve e) {
/*  75 */         if (this.expr.optional()) {
/*  76 */           abstractConfigValue = null;
/*     */         } else {
/*  78 */           throw new ConfigException.UnresolvedSubstitution(origin(), this.expr + " was part of a cycle of substitutions involving " + e.traceString(), e);
/*     */         } 
/*     */       } 
/*  83 */       if (abstractConfigValue == null && !this.expr.optional()) {
/*  84 */         if (context.options().getAllowUnresolved())
/*  85 */           return this; 
/*  87 */         throw new ConfigException.UnresolvedSubstitution(origin(), this.expr.toString());
/*     */       } 
/*  89 */       return abstractConfigValue;
/*     */     } finally {
/*  92 */       context.source().unreplace(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/*  98 */     return ResolveStatus.UNRESOLVED;
/*     */   }
/*     */   
/*     */   ConfigReference relativized(Path prefix) {
/* 108 */     SubstitutionExpression newExpr = this.expr.changePath(this.expr.path().prepend(prefix));
/* 109 */     return new ConfigReference(origin(), newExpr, this.prefixLength + prefix.length());
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 114 */     return other instanceof ConfigReference;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 120 */     if (other instanceof ConfigReference)
/* 121 */       return (canEqual(other) && this.expr.equals(((ConfigReference)other).expr)); 
/* 123 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 130 */     return this.expr.hashCode();
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 135 */     sb.append(this.expr.toString());
/*     */   }
/*     */   
/*     */   SubstitutionExpression expression() {
/* 139 */     return this.expr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */