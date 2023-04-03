/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigRenderOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ abstract class AbstractConfigValue implements ConfigValue, MergeableValue {
/*     */   private final SimpleConfigOrigin origin;
/*     */   
/*     */   AbstractConfigValue(ConfigOrigin origin) {
/*  31 */     this.origin = (SimpleConfigOrigin)origin;
/*     */   }
/*     */   
/*     */   public SimpleConfigOrigin origin() {
/*  36 */     return this.origin;
/*     */   }
/*     */   
/*     */   static class NotPossibleToResolve extends Exception {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private final String traceString;
/*     */     
/*     */     NotPossibleToResolve(ResolveContext context) {
/*  57 */       super("was not possible to resolve");
/*  58 */       this.traceString = context.traceString();
/*     */     }
/*     */     
/*     */     String traceString() {
/*  62 */       return this.traceString;
/*     */     }
/*     */   }
/*     */   
/*     */   AbstractConfigValue resolveSubstitutions(ResolveContext context) throws NotPossibleToResolve {
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   ResolveStatus resolveStatus() {
/*  79 */     return ResolveStatus.RESOLVED;
/*     */   }
/*     */   
/*     */   AbstractConfigValue relativized(Path prefix) {
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   protected static interface Modifier {
/*     */     AbstractConfigValue modifyChildMayThrow(String param1String, AbstractConfigValue param1AbstractConfigValue) throws Exception;
/*     */   }
/*     */   
/*     */   protected abstract class NoExceptionsModifier implements Modifier {
/*     */     public final AbstractConfigValue modifyChildMayThrow(String keyOrNull, AbstractConfigValue v) throws Exception {
/*     */       try {
/* 109 */         return modifyChild(keyOrNull, v);
/* 110 */       } catch (RuntimeException e) {
/* 111 */         throw e;
/* 112 */       } catch (Exception e) {
/* 113 */         throw new ConfigException.BugOrBroken("Unexpected exception", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     abstract AbstractConfigValue modifyChild(String param1String, AbstractConfigValue param1AbstractConfigValue);
/*     */   }
/*     */   
/*     */   public AbstractConfigValue toFallbackValue() {
/* 122 */     return this;
/*     */   }
/*     */   
/*     */   protected boolean ignoresFallbacks() {
/* 133 */     return (resolveStatus() == ResolveStatus.RESOLVED);
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue withFallbacksIgnored() {
/* 137 */     if (ignoresFallbacks())
/* 138 */       return this; 
/* 140 */     throw new ConfigException.BugOrBroken("value class doesn't implement forced fallback-ignoring " + this);
/*     */   }
/*     */   
/*     */   protected final void requireNotIgnoringFallbacks() {
/* 147 */     if (ignoresFallbacks())
/* 148 */       throw new ConfigException.BugOrBroken("method should not have been called with ignoresFallbacks=true " + getClass().getSimpleName()); 
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue constructDelayedMerge(ConfigOrigin origin, List<AbstractConfigValue> stack) {
/* 155 */     return new ConfigDelayedMerge(origin, stack);
/*     */   }
/*     */   
/*     */   protected final AbstractConfigValue mergedWithTheUnmergeable(Collection<AbstractConfigValue> stack, Unmergeable fallback) {
/* 160 */     requireNotIgnoringFallbacks();
/* 164 */     List<AbstractConfigValue> newStack = new ArrayList<AbstractConfigValue>();
/* 165 */     newStack.addAll(stack);
/* 166 */     newStack.addAll(fallback.unmergedValues());
/* 167 */     return constructDelayedMerge(AbstractConfigObject.mergeOrigins(newStack), newStack);
/*     */   }
/*     */   
/*     */   private final AbstractConfigValue delayMerge(Collection<AbstractConfigValue> stack, AbstractConfigValue fallback) {
/* 176 */     List<AbstractConfigValue> newStack = new ArrayList<AbstractConfigValue>();
/* 177 */     newStack.addAll(stack);
/* 178 */     newStack.add(fallback);
/* 179 */     return constructDelayedMerge(AbstractConfigObject.mergeOrigins(newStack), newStack);
/*     */   }
/*     */   
/*     */   protected final AbstractConfigValue mergedWithObject(Collection<AbstractConfigValue> stack, AbstractConfigObject fallback) {
/* 184 */     requireNotIgnoringFallbacks();
/* 186 */     if (this instanceof AbstractConfigObject)
/* 187 */       throw new ConfigException.BugOrBroken("Objects must reimplement mergedWithObject"); 
/* 189 */     return mergedWithNonObject(stack, fallback);
/*     */   }
/*     */   
/*     */   protected final AbstractConfigValue mergedWithNonObject(Collection<AbstractConfigValue> stack, AbstractConfigValue fallback) {
/* 194 */     requireNotIgnoringFallbacks();
/* 196 */     if (resolveStatus() == ResolveStatus.RESOLVED)
/* 200 */       return withFallbacksIgnored(); 
/* 204 */     return delayMerge(stack, fallback);
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue mergedWithTheUnmergeable(Unmergeable fallback) {
/* 209 */     requireNotIgnoringFallbacks();
/* 211 */     return mergedWithTheUnmergeable(Collections.singletonList(this), fallback);
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue mergedWithObject(AbstractConfigObject fallback) {
/* 215 */     requireNotIgnoringFallbacks();
/* 217 */     return mergedWithObject(Collections.singletonList(this), fallback);
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue mergedWithNonObject(AbstractConfigValue fallback) {
/* 221 */     requireNotIgnoringFallbacks();
/* 223 */     return mergedWithNonObject(Collections.singletonList(this), fallback);
/*     */   }
/*     */   
/*     */   public AbstractConfigValue withOrigin(ConfigOrigin origin) {
/* 227 */     if (this.origin == origin)
/* 228 */       return this; 
/* 230 */     return newCopy(origin);
/*     */   }
/*     */   
/*     */   public AbstractConfigValue withFallback(ConfigMergeable mergeable) {
/* 236 */     if (ignoresFallbacks())
/* 237 */       return this; 
/* 239 */     ConfigValue other = ((MergeableValue)mergeable).toFallbackValue();
/* 241 */     if (other instanceof Unmergeable)
/* 242 */       return mergedWithTheUnmergeable((Unmergeable)other); 
/* 243 */     if (other instanceof AbstractConfigObject)
/* 244 */       return mergedWithObject((AbstractConfigObject)other); 
/* 246 */     return mergedWithNonObject((AbstractConfigValue)other);
/*     */   }
/*     */   
/*     */   protected boolean canEqual(Object other) {
/* 252 */     return other instanceof ConfigValue;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 258 */     if (other instanceof ConfigValue)
/* 259 */       return (canEqual(other) && valueType() == ((ConfigValue)other).valueType() && ConfigImplUtil.equalsHandlingNull(unwrapped(), ((ConfigValue)other).unwrapped())); 
/* 265 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 272 */     Object o = unwrapped();
/* 273 */     if (o == null)
/* 274 */       return 0; 
/* 276 */     return o.hashCode();
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 281 */     StringBuilder sb = new StringBuilder();
/* 282 */     render(sb, 0, true, (String)null, ConfigRenderOptions.concise());
/* 283 */     return getClass().getSimpleName() + "(" + sb.toString() + ")";
/*     */   }
/*     */   
/*     */   protected static void indent(StringBuilder sb, int indent, ConfigRenderOptions options) {
/* 287 */     if (options.getFormatted()) {
/* 288 */       int remaining = indent;
/* 289 */       while (remaining > 0) {
/* 290 */         sb.append("    ");
/* 291 */         remaining--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, String atKey, ConfigRenderOptions options) {
/* 297 */     if (atKey != null) {
/*     */       String renderedKey;
/* 299 */       if (options.getJson()) {
/* 300 */         renderedKey = ConfigImplUtil.renderJsonString(atKey);
/*     */       } else {
/* 302 */         renderedKey = ConfigImplUtil.renderStringUnquotedIfPossible(atKey);
/*     */       } 
/* 304 */       sb.append(renderedKey);
/* 306 */       if (options.getJson()) {
/* 307 */         if (options.getFormatted()) {
/* 308 */           sb.append(" : ");
/*     */         } else {
/* 310 */           sb.append(":");
/*     */         } 
/* 313 */       } else if (this instanceof com.typesafe.config.ConfigObject) {
/* 314 */         if (options.getFormatted())
/* 315 */           sb.append(' '); 
/*     */       } else {
/* 317 */         sb.append("=");
/*     */       } 
/*     */     } 
/* 321 */     render(sb, indent, atRoot, options);
/*     */   }
/*     */   
/*     */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 325 */     Object u = unwrapped();
/* 326 */     sb.append(u.toString());
/*     */   }
/*     */   
/*     */   public final String render() {
/* 331 */     return render(ConfigRenderOptions.defaults());
/*     */   }
/*     */   
/*     */   public final String render(ConfigRenderOptions options) {
/* 336 */     StringBuilder sb = new StringBuilder();
/* 337 */     render(sb, 0, true, (String)null, options);
/* 338 */     return sb.toString();
/*     */   }
/*     */   
/*     */   String transformToString() {
/* 347 */     return null;
/*     */   }
/*     */   
/*     */   SimpleConfig atKey(ConfigOrigin origin, String key) {
/* 351 */     Map<String, AbstractConfigValue> m = Collections.singletonMap(key, this);
/* 352 */     return (new SimpleConfigObject(origin, m)).toConfig();
/*     */   }
/*     */   
/*     */   public SimpleConfig atKey(String key) {
/* 357 */     return atKey(SimpleConfigOrigin.newSimple("atKey(" + key + ")"), key);
/*     */   }
/*     */   
/*     */   SimpleConfig atPath(ConfigOrigin origin, Path path) {
/* 361 */     Path parent = path.parent();
/* 362 */     SimpleConfig result = atKey(origin, path.last());
/* 363 */     while (parent != null) {
/* 364 */       String key = parent.last();
/* 365 */       result = result.atKey(origin, key);
/* 366 */       parent = parent.parent();
/*     */     } 
/* 368 */     return result;
/*     */   }
/*     */   
/*     */   public SimpleConfig atPath(String pathExpression) {
/* 373 */     SimpleConfigOrigin origin = SimpleConfigOrigin.newSimple("atPath(" + pathExpression + ")");
/* 374 */     return atPath(origin, Path.newPath(pathExpression));
/*     */   }
/*     */   
/*     */   protected abstract AbstractConfigValue newCopy(ConfigOrigin paramConfigOrigin);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\AbstractConfigValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */