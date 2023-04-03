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
/*     */ final class ConfigConcatenation extends AbstractConfigValue implements Unmergeable {
/*     */   private final List<AbstractConfigValue> pieces;
/*     */   
/*     */   ConfigConcatenation(ConfigOrigin origin, List<AbstractConfigValue> pieces) {
/*  30 */     super(origin);
/*  31 */     this.pieces = pieces;
/*  33 */     if (pieces.size() < 2)
/*  34 */       throw new ConfigException.BugOrBroken("Created concatenation with less than 2 items: " + this); 
/*  37 */     boolean hadUnmergeable = false;
/*  38 */     for (AbstractConfigValue p : pieces) {
/*  39 */       if (p instanceof ConfigConcatenation)
/*  40 */         throw new ConfigException.BugOrBroken("ConfigConcatenation should never be nested: " + this); 
/*  42 */       if (p instanceof Unmergeable)
/*  43 */         hadUnmergeable = true; 
/*     */     } 
/*  45 */     if (!hadUnmergeable)
/*  46 */       throw new ConfigException.BugOrBroken("Created concatenation without an unmergeable in it: " + this); 
/*     */   }
/*     */   
/*     */   private ConfigException.NotResolved notResolved() {
/*  51 */     return new ConfigException.NotResolved("need to Config#resolve(), see the API docs for Config#resolve(); substitution not resolved: " + this);
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/*  58 */     throw notResolved();
/*     */   }
/*     */   
/*     */   public Object unwrapped() {
/*  63 */     throw notResolved();
/*     */   }
/*     */   
/*     */   protected ConfigConcatenation newCopy(ConfigOrigin newOrigin) {
/*  68 */     return new ConfigConcatenation(newOrigin, this.pieces);
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<ConfigConcatenation> unmergedValues() {
/*  81 */     return Collections.singleton(this);
/*     */   }
/*     */   
/*     */   private static void join(ArrayList<AbstractConfigValue> builder, AbstractConfigValue origRight) {
/*  88 */     AbstractConfigValue left = builder.get(builder.size() - 1);
/*  89 */     AbstractConfigValue right = origRight;
/*  93 */     if (left instanceof com.typesafe.config.ConfigObject && right instanceof SimpleConfigList) {
/*  94 */       left = DefaultTransformer.transform(left, ConfigValueType.LIST);
/*  95 */     } else if (left instanceof SimpleConfigList && right instanceof com.typesafe.config.ConfigObject) {
/*  96 */       right = DefaultTransformer.transform(right, ConfigValueType.LIST);
/*     */     } 
/* 102 */     AbstractConfigValue joined = null;
/* 103 */     if (left instanceof com.typesafe.config.ConfigObject && right instanceof com.typesafe.config.ConfigObject) {
/* 104 */       joined = right.withFallback(left);
/* 105 */     } else if (left instanceof SimpleConfigList && right instanceof SimpleConfigList) {
/* 106 */       joined = ((SimpleConfigList)left).concatenate((SimpleConfigList)right);
/*     */     } else {
/* 107 */       if (left instanceof ConfigConcatenation || right instanceof ConfigConcatenation)
/* 108 */         throw new ConfigException.BugOrBroken("unflattened ConfigConcatenation"); 
/* 109 */       if (!(left instanceof Unmergeable) && !(right instanceof Unmergeable)) {
/* 113 */         String s1 = left.transformToString();
/* 114 */         String s2 = right.transformToString();
/* 115 */         if (s1 == null || s2 == null)
/* 116 */           throw new ConfigException.WrongType(left.origin(), "Cannot concatenate object or list with a non-object-or-list, " + left + " and " + right + " are not compatible"); 
/* 120 */         ConfigOrigin joinedOrigin = SimpleConfigOrigin.mergeOrigins(left.origin(), right.origin());
/* 122 */         joined = new ConfigString(joinedOrigin, s1 + s2);
/*     */       } 
/*     */     } 
/* 126 */     if (joined == null) {
/* 127 */       builder.add(right);
/*     */     } else {
/* 129 */       builder.remove(builder.size() - 1);
/* 130 */       builder.add(joined);
/*     */     } 
/*     */   }
/*     */   
/*     */   static List<AbstractConfigValue> consolidate(List<AbstractConfigValue> pieces) {
/* 135 */     if (pieces.size() < 2)
/* 136 */       return pieces; 
/* 138 */     List<AbstractConfigValue> flattened = new ArrayList<AbstractConfigValue>(pieces.size());
/* 139 */     for (AbstractConfigValue v : pieces) {
/* 140 */       if (v instanceof ConfigConcatenation) {
/* 141 */         flattened.addAll(((ConfigConcatenation)v).pieces);
/*     */         continue;
/*     */       } 
/* 143 */       flattened.add(v);
/*     */     } 
/* 147 */     ArrayList<AbstractConfigValue> consolidated = new ArrayList<AbstractConfigValue>(flattened.size());
/* 149 */     for (AbstractConfigValue v : flattened) {
/* 150 */       if (consolidated.isEmpty()) {
/* 151 */         consolidated.add(v);
/*     */         continue;
/*     */       } 
/* 153 */       join(consolidated, v);
/*     */     } 
/* 156 */     return consolidated;
/*     */   }
/*     */   
/*     */   static AbstractConfigValue concatenate(List<AbstractConfigValue> pieces) {
/* 161 */     List<AbstractConfigValue> consolidated = consolidate(pieces);
/* 162 */     if (consolidated.isEmpty())
/* 163 */       return null; 
/* 164 */     if (consolidated.size() == 1)
/* 165 */       return consolidated.get(0); 
/* 167 */     ConfigOrigin mergedOrigin = SimpleConfigOrigin.mergeOrigins(consolidated);
/* 168 */     return new ConfigConcatenation(mergedOrigin, consolidated);
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolveSubstitutions(ResolveContext context) throws AbstractConfigValue.NotPossibleToResolve {
/* 174 */     List<AbstractConfigValue> resolved = new ArrayList<AbstractConfigValue>(this.pieces.size());
/* 175 */     for (AbstractConfigValue p : this.pieces) {
/* 178 */       AbstractConfigValue r = context.unrestricted().resolve(p);
/* 179 */       if (r == null)
/*     */         continue; 
/* 182 */       resolved.add(r);
/*     */     } 
/* 187 */     List<AbstractConfigValue> joined = consolidate(resolved);
/* 190 */     if (joined.size() > 1 && context.options().getAllowUnresolved())
/* 191 */       return new ConfigConcatenation(origin(), joined); 
/* 192 */     if (joined.size() != 1)
/* 193 */       throw new ConfigException.BugOrBroken("Resolved list should always join to exactly one value, not " + joined); 
/* 196 */     return joined.get(0);
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/* 201 */     return ResolveStatus.UNRESOLVED;
/*     */   }
/*     */   
/*     */   ConfigConcatenation relativized(Path prefix) {
/* 211 */     List<AbstractConfigValue> newPieces = new ArrayList<AbstractConfigValue>();
/* 212 */     for (AbstractConfigValue p : this.pieces)
/* 213 */       newPieces.add(p.relativized(prefix)); 
/* 215 */     return new ConfigConcatenation(origin(), newPieces);
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 220 */     return other instanceof ConfigConcatenation;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 226 */     if (other instanceof ConfigConcatenation)
/* 227 */       return (canEqual(other) && this.pieces.equals(((ConfigConcatenation)other).pieces)); 
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 236 */     return this.pieces.hashCode();
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 241 */     for (AbstractConfigValue p : this.pieces)
/* 242 */       p.render(sb, indent, atRoot, options); 
/*     */   }
/*     */   
/*     */   static List<AbstractConfigValue> valuesFromPieces(ConfigOrigin origin, List<Object> pieces) {
/* 247 */     List<AbstractConfigValue> values = new ArrayList<AbstractConfigValue>(pieces.size());
/* 248 */     for (Object p : pieces) {
/* 249 */       if (p instanceof SubstitutionExpression) {
/* 250 */         values.add(new ConfigReference(origin, (SubstitutionExpression)p));
/*     */         continue;
/*     */       } 
/* 251 */       if (p instanceof String) {
/* 252 */         values.add(new ConfigString(origin, (String)p));
/*     */         continue;
/*     */       } 
/* 254 */       throw new ConfigException.BugOrBroken("Unexpected piece " + p);
/*     */     } 
/* 258 */     return values;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigConcatenation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */