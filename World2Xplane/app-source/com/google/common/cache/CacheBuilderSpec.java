/*     */ package com.google.common.cache;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @Beta
/*     */ public final class CacheBuilderSpec {
/*  89 */   private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
/*     */   
/*  92 */   private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
/*     */   
/*  95 */   private static final ImmutableMap<String, ValueParser> VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
/*     */   
/*     */   @VisibleForTesting
/*     */   Integer initialCapacity;
/*     */   
/*     */   @VisibleForTesting
/*     */   Long maximumSize;
/*     */   
/*     */   @VisibleForTesting
/*     */   Long maximumWeight;
/*     */   
/*     */   @VisibleForTesting
/*     */   Integer concurrencyLevel;
/*     */   
/*     */   @VisibleForTesting
/*     */   LocalCache.Strength keyStrength;
/*     */   
/*     */   @VisibleForTesting
/*     */   LocalCache.Strength valueStrength;
/*     */   
/*     */   @VisibleForTesting
/*     */   Boolean recordStats;
/*     */   
/*     */   @VisibleForTesting
/*     */   long writeExpirationDuration;
/*     */   
/*     */   @VisibleForTesting
/*     */   TimeUnit writeExpirationTimeUnit;
/*     */   
/*     */   @VisibleForTesting
/*     */   long accessExpirationDuration;
/*     */   
/*     */   @VisibleForTesting
/*     */   TimeUnit accessExpirationTimeUnit;
/*     */   
/*     */   @VisibleForTesting
/*     */   long refreshDuration;
/*     */   
/*     */   @VisibleForTesting
/*     */   TimeUnit refreshTimeUnit;
/*     */   
/*     */   private final String specification;
/*     */   
/*     */   private CacheBuilderSpec(String specification) {
/* 128 */     this.specification = specification;
/*     */   }
/*     */   
/*     */   public static CacheBuilderSpec parse(String cacheBuilderSpecification) {
/* 137 */     CacheBuilderSpec spec = new CacheBuilderSpec(cacheBuilderSpecification);
/* 138 */     if (!cacheBuilderSpecification.isEmpty())
/* 139 */       for (String keyValuePair : KEYS_SPLITTER.split(cacheBuilderSpecification)) {
/* 140 */         ImmutableList<String> immutableList = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
/* 141 */         Preconditions.checkArgument(!immutableList.isEmpty(), "blank key-value pair");
/* 142 */         Preconditions.checkArgument((immutableList.size() <= 2), "key-value pair %s with more than one equals sign", new Object[] { keyValuePair });
/* 146 */         String key = immutableList.get(0);
/* 147 */         ValueParser valueParser = (ValueParser)VALUE_PARSERS.get(key);
/* 148 */         Preconditions.checkArgument((valueParser != null), "unknown key %s", new Object[] { key });
/* 150 */         String value = (immutableList.size() == 1) ? null : immutableList.get(1);
/* 151 */         valueParser.parse(spec, key, value);
/*     */       }  
/* 155 */     return spec;
/*     */   }
/*     */   
/*     */   public static CacheBuilderSpec disableCaching() {
/* 163 */     return parse("maximumSize=0");
/*     */   }
/*     */   
/*     */   CacheBuilder<Object, Object> toCacheBuilder() {
/* 170 */     CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
/* 171 */     if (this.initialCapacity != null)
/* 172 */       builder.initialCapacity(this.initialCapacity.intValue()); 
/* 174 */     if (this.maximumSize != null)
/* 175 */       builder.maximumSize(this.maximumSize.longValue()); 
/* 177 */     if (this.maximumWeight != null)
/* 178 */       builder.maximumWeight(this.maximumWeight.longValue()); 
/* 180 */     if (this.concurrencyLevel != null)
/* 181 */       builder.concurrencyLevel(this.concurrencyLevel.intValue()); 
/* 183 */     if (this.keyStrength != null)
/* 184 */       switch (this.keyStrength) {
/*     */         case WEAK:
/* 186 */           builder.weakKeys();
/*     */           break;
/*     */         default:
/* 189 */           throw new AssertionError();
/*     */       }  
/* 192 */     if (this.valueStrength != null)
/* 193 */       switch (this.valueStrength) {
/*     */         case SOFT:
/* 195 */           builder.softValues();
/*     */           break;
/*     */         case WEAK:
/* 198 */           builder.weakValues();
/*     */           break;
/*     */         default:
/* 201 */           throw new AssertionError();
/*     */       }  
/* 204 */     if (this.recordStats != null && this.recordStats.booleanValue())
/* 205 */       builder.recordStats(); 
/* 207 */     if (this.writeExpirationTimeUnit != null)
/* 208 */       builder.expireAfterWrite(this.writeExpirationDuration, this.writeExpirationTimeUnit); 
/* 210 */     if (this.accessExpirationTimeUnit != null)
/* 211 */       builder.expireAfterAccess(this.accessExpirationDuration, this.accessExpirationTimeUnit); 
/* 213 */     if (this.refreshTimeUnit != null)
/* 214 */       builder.refreshAfterWrite(this.refreshDuration, this.refreshTimeUnit); 
/* 217 */     return builder;
/*     */   }
/*     */   
/*     */   public String toParsableString() {
/* 227 */     return this.specification;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 236 */     return Objects.toStringHelper(this).addValue(toParsableString()).toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 241 */     return Objects.hashCode(new Object[] { this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit) });
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object obj) {
/* 256 */     if (this == obj)
/* 257 */       return true; 
/* 259 */     if (!(obj instanceof CacheBuilderSpec))
/* 260 */       return false; 
/* 262 */     CacheBuilderSpec that = (CacheBuilderSpec)obj;
/* 263 */     return (Objects.equal(this.initialCapacity, that.initialCapacity) && Objects.equal(this.maximumSize, that.maximumSize) && Objects.equal(this.maximumWeight, that.maximumWeight) && Objects.equal(this.concurrencyLevel, that.concurrencyLevel) && Objects.equal(this.keyStrength, that.keyStrength) && Objects.equal(this.valueStrength, that.valueStrength) && Objects.equal(this.recordStats, that.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(that.writeExpirationDuration, that.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(that.accessExpirationDuration, that.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(that.refreshDuration, that.refreshTimeUnit)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Long durationInNanos(long duration, @Nullable TimeUnit unit) {
/* 283 */     return (unit == null) ? null : Long.valueOf(unit.toNanos(duration));
/*     */   }
/*     */   
/*     */   static abstract class IntegerParser implements ValueParser {
/*     */     protected abstract void parseInteger(CacheBuilderSpec param1CacheBuilderSpec, int param1Int);
/*     */     
/*     */     public void parse(CacheBuilderSpec spec, String key, String value) {
/* 292 */       Preconditions.checkArgument((value != null && !value.isEmpty()), "value of key %s omitted", new Object[] { key });
/*     */       try {
/* 294 */         parseInteger(spec, Integer.parseInt(value));
/* 295 */       } catch (NumberFormatException e) {
/* 296 */         throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] { key, value }), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class LongParser implements ValueParser {
/*     */     protected abstract void parseLong(CacheBuilderSpec param1CacheBuilderSpec, long param1Long);
/*     */     
/*     */     public void parse(CacheBuilderSpec spec, String key, String value) {
/* 308 */       Preconditions.checkArgument((value != null && !value.isEmpty()), "value of key %s omitted", new Object[] { key });
/*     */       try {
/* 310 */         parseLong(spec, Long.parseLong(value));
/* 311 */       } catch (NumberFormatException e) {
/* 312 */         throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] { key, value }), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class InitialCapacityParser extends IntegerParser {
/*     */     protected void parseInteger(CacheBuilderSpec spec, int value) {
/* 322 */       Preconditions.checkArgument((spec.initialCapacity == null), "initial capacity was already set to ", new Object[] { spec.initialCapacity });
/* 324 */       spec.initialCapacity = Integer.valueOf(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class MaximumSizeParser extends LongParser {
/*     */     protected void parseLong(CacheBuilderSpec spec, long value) {
/* 332 */       Preconditions.checkArgument((spec.maximumSize == null), "maximum size was already set to ", new Object[] { spec.maximumSize });
/* 334 */       Preconditions.checkArgument((spec.maximumWeight == null), "maximum weight was already set to ", new Object[] { spec.maximumWeight });
/* 336 */       spec.maximumSize = Long.valueOf(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class MaximumWeightParser extends LongParser {
/*     */     protected void parseLong(CacheBuilderSpec spec, long value) {
/* 344 */       Preconditions.checkArgument((spec.maximumWeight == null), "maximum weight was already set to ", new Object[] { spec.maximumWeight });
/* 346 */       Preconditions.checkArgument((spec.maximumSize == null), "maximum size was already set to ", new Object[] { spec.maximumSize });
/* 348 */       spec.maximumWeight = Long.valueOf(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ConcurrencyLevelParser extends IntegerParser {
/*     */     protected void parseInteger(CacheBuilderSpec spec, int value) {
/* 356 */       Preconditions.checkArgument((spec.concurrencyLevel == null), "concurrency level was already set to ", new Object[] { spec.concurrencyLevel });
/* 358 */       spec.concurrencyLevel = Integer.valueOf(value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class KeyStrengthParser implements ValueParser {
/*     */     private final LocalCache.Strength strength;
/*     */     
/*     */     public KeyStrengthParser(LocalCache.Strength strength) {
/* 367 */       this.strength = strength;
/*     */     }
/*     */     
/*     */     public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
/* 372 */       Preconditions.checkArgument((value == null), "key %s does not take values", new Object[] { key });
/* 373 */       Preconditions.checkArgument((spec.keyStrength == null), "%s was already set to %s", new Object[] { key, spec.keyStrength });
/* 374 */       spec.keyStrength = this.strength;
/*     */     }
/*     */   }
/*     */   
/*     */   static class ValueStrengthParser implements ValueParser {
/*     */     private final LocalCache.Strength strength;
/*     */     
/*     */     public ValueStrengthParser(LocalCache.Strength strength) {
/* 383 */       this.strength = strength;
/*     */     }
/*     */     
/*     */     public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
/* 388 */       Preconditions.checkArgument((value == null), "key %s does not take values", new Object[] { key });
/* 389 */       Preconditions.checkArgument((spec.valueStrength == null), "%s was already set to %s", new Object[] { key, spec.valueStrength });
/* 392 */       spec.valueStrength = this.strength;
/*     */     }
/*     */   }
/*     */   
/*     */   static class RecordStatsParser implements ValueParser {
/*     */     public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
/* 401 */       Preconditions.checkArgument((value == null), "recordStats does not take values");
/* 402 */       Preconditions.checkArgument((spec.recordStats == null), "recordStats already set");
/* 403 */       spec.recordStats = Boolean.valueOf(true);
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class DurationParser implements ValueParser {
/*     */     protected abstract void parseDuration(CacheBuilderSpec param1CacheBuilderSpec, long param1Long, TimeUnit param1TimeUnit);
/*     */     
/*     */     public void parse(CacheBuilderSpec spec, String key, String value) {
/* 416 */       Preconditions.checkArgument((value != null && !value.isEmpty()), "value of key %s omitted", new Object[] { key });
/*     */       try {
/*     */         TimeUnit timeUnit;
/* 418 */         char lastChar = value.charAt(value.length() - 1);
/* 420 */         switch (lastChar) {
/*     */           case 'd':
/* 422 */             timeUnit = TimeUnit.DAYS;
/*     */             break;
/*     */           case 'h':
/* 425 */             timeUnit = TimeUnit.HOURS;
/*     */             break;
/*     */           case 'm':
/* 428 */             timeUnit = TimeUnit.MINUTES;
/*     */             break;
/*     */           case 's':
/* 431 */             timeUnit = TimeUnit.SECONDS;
/*     */             break;
/*     */           default:
/* 434 */             throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[] { key, value }));
/*     */         } 
/* 439 */         long duration = Long.parseLong(value.substring(0, value.length() - 1));
/* 440 */         parseDuration(spec, duration, timeUnit);
/* 441 */       } catch (NumberFormatException e) {
/* 442 */         throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] { key, value }));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class AccessDurationParser extends DurationParser {
/*     */     protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
/* 451 */       Preconditions.checkArgument((spec.accessExpirationTimeUnit == null), "expireAfterAccess already set");
/* 452 */       spec.accessExpirationDuration = duration;
/* 453 */       spec.accessExpirationTimeUnit = unit;
/*     */     }
/*     */   }
/*     */   
/*     */   static class WriteDurationParser extends DurationParser {
/*     */     protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
/* 460 */       Preconditions.checkArgument((spec.writeExpirationTimeUnit == null), "expireAfterWrite already set");
/* 461 */       spec.writeExpirationDuration = duration;
/* 462 */       spec.writeExpirationTimeUnit = unit;
/*     */     }
/*     */   }
/*     */   
/*     */   static class RefreshDurationParser extends DurationParser {
/*     */     protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
/* 469 */       Preconditions.checkArgument((spec.refreshTimeUnit == null), "refreshAfterWrite already set");
/* 470 */       spec.refreshDuration = duration;
/* 471 */       spec.refreshTimeUnit = unit;
/*     */     }
/*     */   }
/*     */   
/*     */   private static interface ValueParser {
/*     */     void parse(CacheBuilderSpec param1CacheBuilderSpec, String param1String1, @Nullable String param1String2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\cache\CacheBuilderSpec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */