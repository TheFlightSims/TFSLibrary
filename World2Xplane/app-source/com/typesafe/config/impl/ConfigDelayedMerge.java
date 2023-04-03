/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ConfigDelayedMerge extends AbstractConfigValue implements Unmergeable, ReplaceableMergeStack {
/*     */   private final List<AbstractConfigValue> stack;
/*     */   
/*     */   ConfigDelayedMerge(ConfigOrigin origin, List<AbstractConfigValue> stack) {
/*  31 */     super(origin);
/*  32 */     this.stack = stack;
/*  33 */     if (stack.isEmpty())
/*  34 */       throw new ConfigException.BugOrBroken("creating empty delayed merge value"); 
/*  37 */     for (AbstractConfigValue v : stack) {
/*  38 */       if (v instanceof ConfigDelayedMerge || v instanceof ConfigDelayedMergeObject)
/*  39 */         throw new ConfigException.BugOrBroken("placed nested DelayedMerge in a ConfigDelayedMerge, should have consolidated stack"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/*  46 */     throw new ConfigException.NotResolved("called valueType() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
/*     */   }
/*     */   
/*     */   public Object unwrapped() {
/*  52 */     throw new ConfigException.NotResolved("called unwrapped() on value with unresolved substitutions, need to Config#resolve() first, see API docs");
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolveSubstitutions(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*  59 */     return resolveSubstitutions(this, this.stack, context);
/*     */   }
/*     */   
/*     */   static AbstractConfigValue resolveSubstitutions(ReplaceableMergeStack replaceable, List<AbstractConfigValue> stack, ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/*  70 */     int count = 0;
/*  71 */     AbstractConfigValue merged = null;
/*  72 */     for (AbstractConfigValue v : stack) {
/*     */       AbstractConfigValue resolved;
/*  73 */       if (v instanceof ReplaceableMergeStack)
/*  74 */         throw new ConfigException.BugOrBroken("A delayed merge should not contain another one: " + replaceable); 
/*  77 */       boolean replaced = false;
/*  82 */       if (v instanceof Unmergeable) {
/*  89 */         context.source().replace((AbstractConfigValue)replaceable, replaceable.makeReplacer(count + 1));
/*  91 */         replaced = true;
/*     */       } 
/*     */       try {
/*  96 */         resolved = context.resolve(v);
/*     */       } finally {
/*  98 */         if (replaced)
/*  99 */           context.source().unreplace((AbstractConfigValue)replaceable); 
/*     */       } 
/* 102 */       if (resolved != null)
/* 103 */         if (merged == null) {
/* 104 */           merged = resolved;
/*     */         } else {
/* 106 */           merged = merged.withFallback(resolved);
/*     */         }  
/* 108 */       count++;
/*     */     } 
/* 111 */     return merged;
/*     */   }
/*     */   
/*     */   public ResolveReplacer makeReplacer(final int skipping) {
/* 116 */     return new ResolveReplacer() {
/*     */         protected AbstractConfigValue makeReplacement(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 120 */           return ConfigDelayedMerge.makeReplacement(context, ConfigDelayedMerge.this.stack, skipping);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static AbstractConfigValue makeReplacement(ResolveContext context, List<AbstractConfigValue> stack, int skipping) throws AbstractConfigValue.NotPossibleToResolve {
/* 129 */     List<AbstractConfigValue> subStack = stack.subList(skipping, stack.size());
/* 131 */     if (subStack.isEmpty())
/* 132 */       throw new AbstractConfigValue.NotPossibleToResolve(context); 
/* 135 */     AbstractConfigValue merged = null;
/* 136 */     for (AbstractConfigValue v : subStack) {
/* 137 */       if (merged == null) {
/* 138 */         merged = v;
/*     */         continue;
/*     */       } 
/* 140 */       merged = merged.withFallback(v);
/*     */     } 
/* 142 */     return merged;
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/* 148 */     return ResolveStatus.UNRESOLVED;
/*     */   }
/*     */   
/*     */   ConfigDelayedMerge relativized(Path prefix) {
/* 153 */     List<AbstractConfigValue> newStack = new ArrayList<AbstractConfigValue>();
/* 154 */     for (AbstractConfigValue o : this.stack)
/* 155 */       newStack.add(o.relativized(prefix)); 
/* 157 */     return new ConfigDelayedMerge(origin(), newStack);
/*     */   }
/*     */   
/*     */   static boolean stackIgnoresFallbacks(List<AbstractConfigValue> stack) {
/* 162 */     AbstractConfigValue last = stack.get(stack.size() - 1);
/* 163 */     return last.ignoresFallbacks();
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/* 168 */     return stackIgnoresFallbacks(this.stack);
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue newCopy(ConfigOrigin newOrigin) {
/* 173 */     return new ConfigDelayedMerge(newOrigin, this.stack);
/*     */   }
/*     */   
/*     */   protected final ConfigDelayedMerge mergedWithTheUnmergeable(Unmergeable fallback) {
/* 178 */     return (ConfigDelayedMerge)mergedWithTheUnmergeable(this.stack, fallback);
/*     */   }
/*     */   
/*     */   protected final ConfigDelayedMerge mergedWithObject(AbstractConfigObject fallback) {
/* 183 */     return (ConfigDelayedMerge)mergedWithObject(this.stack, fallback);
/*     */   }
/*     */   
/*     */   protected ConfigDelayedMerge mergedWithNonObject(AbstractConfigValue fallback) {
/* 188 */     return (ConfigDelayedMerge)mergedWithNonObject(this.stack, fallback);
/*     */   }
/*     */   
/*     */   public Collection<AbstractConfigValue> unmergedValues() {
/* 193 */     return this.stack;
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 198 */     return other instanceof ConfigDelayedMerge;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 204 */     if (other instanceof ConfigDelayedMerge)
/* 205 */       return (canEqual(other) && this.stack.equals(((ConfigDelayedMerge)other).stack)); 
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 215 */     return this.stack.hashCode();
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, String atKey, ConfigRenderOptions options) {
/* 220 */     render(this.stack, sb, indent, atRoot, atKey, options);
/*     */   }
/*     */   
/*     */   static void render(List<AbstractConfigValue> stack, StringBuilder sb, int indent, boolean atRoot, String atKey, ConfigRenderOptions options) {
/* 226 */     boolean commentMerge = options.getComments();
/* 227 */     if (commentMerge) {
/* 228 */       sb.append("# unresolved merge of " + stack.size() + " values follows (\n");
/* 229 */       if (atKey == null) {
/* 230 */         indent(sb, indent, options);
/* 231 */         sb.append("# this unresolved merge will not be parseable because it's at the root of the object\n");
/* 232 */         indent(sb, indent, options);
/* 233 */         sb.append("# the HOCON format has no way to list multiple root objects in a single file\n");
/*     */       } 
/*     */     } 
/* 237 */     List<AbstractConfigValue> reversed = new ArrayList<AbstractConfigValue>();
/* 238 */     reversed.addAll(stack);
/* 239 */     Collections.reverse(reversed);
/* 241 */     int i = 0;
/* 242 */     for (AbstractConfigValue v : reversed) {
/* 243 */       if (commentMerge) {
/* 244 */         indent(sb, indent, options);
/* 245 */         if (atKey != null) {
/* 246 */           sb.append("#     unmerged value " + i + " for key " + ConfigImplUtil.renderJsonString(atKey) + " from ");
/*     */         } else {
/* 249 */           sb.append("#     unmerged value " + i + " from ");
/*     */         } 
/* 251 */         i++;
/* 252 */         sb.append(v.origin().description());
/* 253 */         sb.append("\n");
/* 255 */         for (String comment : v.origin().comments()) {
/* 256 */           indent(sb, indent, options);
/* 257 */           sb.append("# ");
/* 258 */           sb.append(comment);
/* 259 */           sb.append("\n");
/*     */         } 
/*     */       } 
/* 262 */       indent(sb, indent, options);
/* 264 */       if (atKey != null) {
/* 265 */         sb.append(ConfigImplUtil.renderJsonString(atKey));
/* 266 */         if (options.getFormatted()) {
/* 267 */           sb.append(" : ");
/*     */         } else {
/* 269 */           sb.append(":");
/*     */         } 
/*     */       } 
/* 271 */       v.render(sb, indent, atRoot, options);
/* 272 */       sb.append(",");
/* 273 */       if (options.getFormatted())
/* 274 */         sb.append('\n'); 
/*     */     } 
/* 277 */     sb.setLength(sb.length() - 1);
/* 278 */     if (options.getFormatted()) {
/* 279 */       sb.setLength(sb.length() - 1);
/* 280 */       sb.append("\n");
/*     */     } 
/* 282 */     if (commentMerge) {
/* 283 */       indent(sb, indent, options);
/* 284 */       sb.append("# ) end of unresolved merge\n");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigDelayedMerge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */