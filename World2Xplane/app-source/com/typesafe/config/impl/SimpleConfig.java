/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigList;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigResolveOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ final class SimpleConfig implements Config, MergeableValue, Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private final AbstractConfigObject object;
/*     */   
/*     */   SimpleConfig(AbstractConfigObject object) {
/*  40 */     this.object = object;
/*     */   }
/*     */   
/*     */   public AbstractConfigObject root() {
/*  45 */     return this.object;
/*     */   }
/*     */   
/*     */   public ConfigOrigin origin() {
/*  50 */     return this.object.origin();
/*     */   }
/*     */   
/*     */   public SimpleConfig resolve() {
/*  55 */     return resolve(ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public SimpleConfig resolve(ConfigResolveOptions options) {
/*  60 */     return resolveWith(this, options);
/*     */   }
/*     */   
/*     */   public SimpleConfig resolveWith(Config source) {
/*  65 */     return resolveWith(source, ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public SimpleConfig resolveWith(Config source, ConfigResolveOptions options) {
/*  70 */     AbstractConfigValue resolved = ResolveContext.resolve(this.object, ((SimpleConfig)source).object, options);
/*  72 */     if (resolved == this.object)
/*  73 */       return this; 
/*  75 */     return new SimpleConfig((AbstractConfigObject)resolved);
/*     */   }
/*     */   
/*     */   public boolean hasPath(String pathExpression) {
/*     */     ConfigValue peeked;
/*  80 */     Path path = Path.newPath(pathExpression);
/*     */     try {
/*  83 */       peeked = this.object.peekPath(path);
/*  84 */     } catch (com.typesafe.config.ConfigException.NotResolved e) {
/*  85 */       throw ConfigImpl.improveNotResolved(path, e);
/*     */     } 
/*  87 */     return (peeked != null && peeked.valueType() != ConfigValueType.NULL);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  92 */     return this.object.isEmpty();
/*     */   }
/*     */   
/*     */   private static void findPaths(Set<Map.Entry<String, ConfigValue>> entries, Path parent, AbstractConfigObject obj) {
/*  97 */     for (Map.Entry<String, ConfigValue> entry : (Iterable<Map.Entry<String, ConfigValue>>)obj.entrySet()) {
/*  98 */       String elem = entry.getKey();
/*  99 */       ConfigValue v = entry.getValue();
/* 100 */       Path path = Path.newKey(elem);
/* 101 */       if (parent != null)
/* 102 */         path = path.prepend(parent); 
/* 103 */       if (v instanceof AbstractConfigObject) {
/* 104 */         findPaths(entries, path, (AbstractConfigObject)v);
/*     */         continue;
/*     */       } 
/* 105 */       if (v instanceof ConfigNull)
/*     */         continue; 
/* 108 */       entries.add(new AbstractMap.SimpleImmutableEntry<String, ConfigValue>(path.render(), v));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, ConfigValue>> entrySet() {
/* 115 */     Set<Map.Entry<String, ConfigValue>> entries = new HashSet<Map.Entry<String, ConfigValue>>();
/* 116 */     findPaths(entries, null, this.object);
/* 117 */     return entries;
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue findKey(AbstractConfigObject self, String key, ConfigValueType expected, Path originalPath) {
/* 122 */     AbstractConfigValue v = self.peekAssumingResolved(key, originalPath);
/* 123 */     if (v == null)
/* 124 */       throw new ConfigException.Missing(originalPath.render()); 
/* 126 */     if (expected != null)
/* 127 */       v = DefaultTransformer.transform(v, expected); 
/* 129 */     if (v.valueType() == ConfigValueType.NULL)
/* 130 */       throw new ConfigException.Null(v.origin(), originalPath.render(), (expected != null) ? expected.name() : null); 
/* 132 */     if (expected != null && v.valueType() != expected)
/* 133 */       throw new ConfigException.WrongType(v.origin(), originalPath.render(), expected.name(), v.valueType().name()); 
/* 136 */     return v;
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue find(AbstractConfigObject self, Path path, ConfigValueType expected, Path originalPath) {
/*     */     try {
/* 142 */       String key = path.first();
/* 143 */       Path next = path.remainder();
/* 144 */       if (next == null)
/* 145 */         return findKey(self, key, expected, originalPath); 
/* 147 */       AbstractConfigObject o = (AbstractConfigObject)findKey(self, key, ConfigValueType.OBJECT, originalPath.subPath(0, originalPath.length() - next.length()));
/* 150 */       assert o != null;
/* 151 */       return find(o, next, expected, originalPath);
/* 153 */     } catch (com.typesafe.config.ConfigException.NotResolved e) {
/* 154 */       throw ConfigImpl.improveNotResolved(path, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   AbstractConfigValue find(Path pathExpression, ConfigValueType expected, Path originalPath) {
/* 159 */     return find(this.object, pathExpression, expected, originalPath);
/*     */   }
/*     */   
/*     */   AbstractConfigValue find(String pathExpression, ConfigValueType expected) {
/* 163 */     Path path = Path.newPath(pathExpression);
/* 164 */     return find(path, expected, path);
/*     */   }
/*     */   
/*     */   public AbstractConfigValue getValue(String path) {
/* 169 */     return find(path, null);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String path) {
/* 174 */     ConfigValue v = find(path, ConfigValueType.BOOLEAN);
/* 175 */     return ((Boolean)v.unwrapped()).booleanValue();
/*     */   }
/*     */   
/*     */   private ConfigNumber getConfigNumber(String path) {
/* 179 */     ConfigValue v = find(path, ConfigValueType.NUMBER);
/* 180 */     return (ConfigNumber)v;
/*     */   }
/*     */   
/*     */   public Number getNumber(String path) {
/* 185 */     return getConfigNumber(path).unwrapped();
/*     */   }
/*     */   
/*     */   public int getInt(String path) {
/* 190 */     ConfigNumber n = getConfigNumber(path);
/* 191 */     return n.intValueRangeChecked(path);
/*     */   }
/*     */   
/*     */   public long getLong(String path) {
/* 196 */     return getNumber(path).longValue();
/*     */   }
/*     */   
/*     */   public double getDouble(String path) {
/* 201 */     return getNumber(path).doubleValue();
/*     */   }
/*     */   
/*     */   public String getString(String path) {
/* 206 */     ConfigValue v = find(path, ConfigValueType.STRING);
/* 207 */     return (String)v.unwrapped();
/*     */   }
/*     */   
/*     */   public ConfigList getList(String path) {
/* 212 */     AbstractConfigValue v = find(path, ConfigValueType.LIST);
/* 213 */     return (ConfigList)v;
/*     */   }
/*     */   
/*     */   public AbstractConfigObject getObject(String path) {
/* 218 */     AbstractConfigObject obj = (AbstractConfigObject)find(path, ConfigValueType.OBJECT);
/* 219 */     return obj;
/*     */   }
/*     */   
/*     */   public SimpleConfig getConfig(String path) {
/* 224 */     return getObject(path).toConfig();
/*     */   }
/*     */   
/*     */   public Object getAnyRef(String path) {
/* 229 */     ConfigValue v = find(path, null);
/* 230 */     return v.unwrapped();
/*     */   }
/*     */   
/*     */   public Long getBytes(String path) {
/* 235 */     Long size = null;
/*     */     try {
/* 237 */       size = Long.valueOf(getLong(path));
/* 238 */     } catch (com.typesafe.config.ConfigException.WrongType e) {
/* 239 */       ConfigValue v = find(path, ConfigValueType.STRING);
/* 240 */       size = Long.valueOf(parseBytes((String)v.unwrapped(), v.origin(), path));
/*     */     } 
/* 243 */     return size;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Long getMilliseconds(String path) {
/* 249 */     return Long.valueOf(getDuration(path, TimeUnit.MILLISECONDS));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Long getNanoseconds(String path) {
/* 255 */     return Long.valueOf(getDuration(path, TimeUnit.NANOSECONDS));
/*     */   }
/*     */   
/*     */   public long getDuration(String path, TimeUnit unit) {
/* 260 */     ConfigValue v = find(path, ConfigValueType.STRING);
/* 261 */     long result = unit.convert(parseDuration((String)v.unwrapped(), v.origin(), path), TimeUnit.NANOSECONDS);
/* 264 */     return result;
/*     */   }
/*     */   
/*     */   private <T> List<T> getHomogeneousUnwrappedList(String path, ConfigValueType expected) {
/* 270 */     List<T> l = new ArrayList<T>();
/* 271 */     ConfigList configList = getList(path);
/* 272 */     for (ConfigValue cv : configList) {
/* 274 */       AbstractConfigValue v = (AbstractConfigValue)cv;
/* 275 */       if (expected != null)
/* 276 */         v = DefaultTransformer.transform(v, expected); 
/* 278 */       if (v.valueType() != expected)
/* 279 */         throw new ConfigException.WrongType(v.origin(), path, "list of " + expected.name(), "list of " + v.valueType().name()); 
/* 282 */       l.add((T)v.unwrapped());
/*     */     } 
/* 284 */     return l;
/*     */   }
/*     */   
/*     */   public List<Boolean> getBooleanList(String path) {
/* 289 */     return getHomogeneousUnwrappedList(path, ConfigValueType.BOOLEAN);
/*     */   }
/*     */   
/*     */   public List<Number> getNumberList(String path) {
/* 294 */     return getHomogeneousUnwrappedList(path, ConfigValueType.NUMBER);
/*     */   }
/*     */   
/*     */   public List<Integer> getIntList(String path) {
/* 299 */     List<Integer> l = new ArrayList<Integer>();
/* 300 */     List<AbstractConfigValue> numbers = getHomogeneousWrappedList(path, ConfigValueType.NUMBER);
/* 301 */     for (AbstractConfigValue v : numbers)
/* 302 */       l.add(Integer.valueOf(((ConfigNumber)v).intValueRangeChecked(path))); 
/* 304 */     return l;
/*     */   }
/*     */   
/*     */   public List<Long> getLongList(String path) {
/* 309 */     List<Long> l = new ArrayList<Long>();
/* 310 */     List<Number> numbers = getNumberList(path);
/* 311 */     for (Number n : numbers)
/* 312 */       l.add(Long.valueOf(n.longValue())); 
/* 314 */     return l;
/*     */   }
/*     */   
/*     */   public List<Double> getDoubleList(String path) {
/* 319 */     List<Double> l = new ArrayList<Double>();
/* 320 */     List<Number> numbers = getNumberList(path);
/* 321 */     for (Number n : numbers)
/* 322 */       l.add(Double.valueOf(n.doubleValue())); 
/* 324 */     return l;
/*     */   }
/*     */   
/*     */   public List<String> getStringList(String path) {
/* 329 */     return getHomogeneousUnwrappedList(path, ConfigValueType.STRING);
/*     */   }
/*     */   
/*     */   private <T extends ConfigValue> List<T> getHomogeneousWrappedList(String path, ConfigValueType expected) {
/* 335 */     List<T> l = new ArrayList<T>();
/* 336 */     ConfigList configList = getList(path);
/* 337 */     for (ConfigValue cv : configList) {
/* 339 */       AbstractConfigValue v = (AbstractConfigValue)cv;
/* 340 */       if (expected != null)
/* 341 */         v = DefaultTransformer.transform(v, expected); 
/* 343 */       if (v.valueType() != expected)
/* 344 */         throw new ConfigException.WrongType(v.origin(), path, "list of " + expected.name(), "list of " + v.valueType().name()); 
/* 347 */       l.add((T)v);
/*     */     } 
/* 349 */     return l;
/*     */   }
/*     */   
/*     */   public List<ConfigObject> getObjectList(String path) {
/* 354 */     return getHomogeneousWrappedList(path, ConfigValueType.OBJECT);
/*     */   }
/*     */   
/*     */   public List<? extends Config> getConfigList(String path) {
/* 359 */     List<ConfigObject> objects = getObjectList(path);
/* 360 */     List<Config> l = new ArrayList<Config>();
/* 361 */     for (ConfigObject o : objects)
/* 362 */       l.add(o.toConfig()); 
/* 364 */     return l;
/*     */   }
/*     */   
/*     */   public List<? extends Object> getAnyRefList(String path) {
/* 369 */     List<Object> l = new ArrayList();
/* 370 */     ConfigList configList = getList(path);
/* 371 */     for (ConfigValue v : configList)
/* 372 */       l.add(v.unwrapped()); 
/* 374 */     return l;
/*     */   }
/*     */   
/*     */   public List<Long> getBytesList(String path) {
/* 379 */     List<Long> l = new ArrayList<Long>();
/* 380 */     ConfigList configList = getList(path);
/* 381 */     for (ConfigValue v : configList) {
/* 382 */       if (v.valueType() == ConfigValueType.NUMBER) {
/* 383 */         l.add(Long.valueOf(((Number)v.unwrapped()).longValue()));
/*     */         continue;
/*     */       } 
/* 384 */       if (v.valueType() == ConfigValueType.STRING) {
/* 385 */         String s = (String)v.unwrapped();
/* 386 */         Long n = Long.valueOf(parseBytes(s, v.origin(), path));
/* 387 */         l.add(n);
/*     */         continue;
/*     */       } 
/* 389 */       throw new ConfigException.WrongType(v.origin(), path, "memory size string or number of bytes", v.valueType().name());
/*     */     } 
/* 394 */     return l;
/*     */   }
/*     */   
/*     */   public List<Long> getDurationList(String path, TimeUnit unit) {
/* 399 */     List<Long> l = new ArrayList<Long>();
/* 400 */     ConfigList configList = getList(path);
/* 401 */     for (ConfigValue v : configList) {
/* 402 */       if (v.valueType() == ConfigValueType.NUMBER) {
/* 403 */         Long n = Long.valueOf(unit.convert(((Number)v.unwrapped()).longValue(), TimeUnit.MILLISECONDS));
/* 406 */         l.add(n);
/*     */         continue;
/*     */       } 
/* 407 */       if (v.valueType() == ConfigValueType.STRING) {
/* 408 */         String s = (String)v.unwrapped();
/* 409 */         Long n = Long.valueOf(unit.convert(parseDuration(s, v.origin(), path), TimeUnit.NANOSECONDS));
/* 412 */         l.add(n);
/*     */         continue;
/*     */       } 
/* 414 */       throw new ConfigException.WrongType(v.origin(), path, "duration string or number of milliseconds", v.valueType().name());
/*     */     } 
/* 419 */     return l;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public List<Long> getMillisecondsList(String path) {
/* 425 */     return getDurationList(path, TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public List<Long> getNanosecondsList(String path) {
/* 431 */     return getDurationList(path, TimeUnit.NANOSECONDS);
/*     */   }
/*     */   
/*     */   public AbstractConfigObject toFallbackValue() {
/* 436 */     return this.object;
/*     */   }
/*     */   
/*     */   public SimpleConfig withFallback(ConfigMergeable other) {
/* 443 */     return this.object.withFallback(other).toConfig();
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 448 */     if (other instanceof SimpleConfig)
/* 449 */       return this.object.equals(((SimpleConfig)other).object); 
/* 451 */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 460 */     return 41 * this.object.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 465 */     return "Config(" + this.object.toString() + ")";
/*     */   }
/*     */   
/*     */   private static String getUnits(String s) {
/* 469 */     int i = s.length() - 1;
/* 470 */     while (i >= 0) {
/* 471 */       char c = s.charAt(i);
/* 472 */       if (!Character.isLetter(c))
/*     */         break; 
/* 474 */       i--;
/*     */     } 
/* 476 */     return s.substring(i + 1);
/*     */   }
/*     */   
/*     */   public static long parseDuration(String input, ConfigOrigin originForException, String pathForException) {
/* 497 */     String s = ConfigImplUtil.unicodeTrim(input);
/* 498 */     String originalUnitString = getUnits(s);
/* 499 */     String unitString = originalUnitString;
/* 500 */     String numberString = ConfigImplUtil.unicodeTrim(s.substring(0, s.length() - unitString.length()));
/* 502 */     TimeUnit units = null;
/* 506 */     if (numberString.length() == 0)
/* 507 */       throw new ConfigException.BadValue(originForException, pathForException, "No number in duration value '" + input + "'"); 
/* 511 */     if (unitString.length() > 2 && !unitString.endsWith("s"))
/* 512 */       unitString = unitString + "s"; 
/* 515 */     if (unitString.equals("") || unitString.equals("ms") || unitString.equals("milliseconds")) {
/* 517 */       units = TimeUnit.MILLISECONDS;
/* 518 */     } else if (unitString.equals("us") || unitString.equals("microseconds")) {
/* 519 */       units = TimeUnit.MICROSECONDS;
/* 520 */     } else if (unitString.equals("ns") || unitString.equals("nanoseconds")) {
/* 521 */       units = TimeUnit.NANOSECONDS;
/* 522 */     } else if (unitString.equals("d") || unitString.equals("days")) {
/* 523 */       units = TimeUnit.DAYS;
/* 524 */     } else if (unitString.equals("h") || unitString.equals("hours")) {
/* 525 */       units = TimeUnit.HOURS;
/* 526 */     } else if (unitString.equals("s") || unitString.equals("seconds")) {
/* 527 */       units = TimeUnit.SECONDS;
/* 528 */     } else if (unitString.equals("m") || unitString.equals("minutes")) {
/* 529 */       units = TimeUnit.MINUTES;
/*     */     } else {
/* 531 */       throw new ConfigException.BadValue(originForException, pathForException, "Could not parse time unit '" + originalUnitString + "' (try ns, us, ms, s, m, d)");
/*     */     } 
/*     */     try {
/* 541 */       if (numberString.matches("[0-9]+"))
/* 542 */         return units.toNanos(Long.parseLong(numberString)); 
/* 544 */       long nanosInUnit = units.toNanos(1L);
/* 545 */       return (long)(Double.parseDouble(numberString) * nanosInUnit);
/* 547 */     } catch (NumberFormatException e) {
/* 548 */       throw new ConfigException.BadValue(originForException, pathForException, "Could not parse duration number '" + numberString + "'");
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum MemoryUnit {
/* 555 */     BYTES("", 1024, 0),
/* 557 */     KILOBYTES("kilo", 1000, 1),
/* 558 */     MEGABYTES("mega", 1000, 2),
/* 559 */     GIGABYTES("giga", 1000, 3),
/* 560 */     TERABYTES("tera", 1000, 4),
/* 561 */     PETABYTES("peta", 1000, 5),
/* 562 */     EXABYTES("exa", 1000, 6),
/* 563 */     ZETTABYTES("zetta", 1000, 7),
/* 564 */     YOTTABYTES("yotta", 1000, 8),
/* 566 */     KIBIBYTES("kibi", 1024, 1),
/* 567 */     MEBIBYTES("mebi", 1024, 2),
/* 568 */     GIBIBYTES("gibi", 1024, 3),
/* 569 */     TEBIBYTES("tebi", 1024, 4),
/* 570 */     PEBIBYTES("pebi", 1024, 5),
/* 571 */     EXBIBYTES("exbi", 1024, 6),
/* 572 */     ZEBIBYTES("zebi", 1024, 7),
/* 573 */     YOBIBYTES("yobi", 1024, 8);
/*     */     
/*     */     final String prefix;
/*     */     
/*     */     final int powerOf;
/*     */     
/*     */     final int power;
/*     */     
/*     */     final long bytes;
/*     */     
/* 624 */     private static Map<String, MemoryUnit> unitsMap = makeUnitsMap();
/*     */     
/*     */     MemoryUnit(String prefix, int powerOf, int power) {
/*     */       this.prefix = prefix;
/*     */       this.powerOf = powerOf;
/*     */       this.power = power;
/*     */       int i = power;
/*     */       long bytes = 1L;
/*     */       while (i > 0) {
/*     */         bytes *= powerOf;
/*     */         i--;
/*     */       } 
/*     */       this.bytes = bytes;
/*     */     }
/*     */     
/*     */     private static Map<String, MemoryUnit> makeUnitsMap() {
/*     */       Map<String, MemoryUnit> map = new HashMap<String, MemoryUnit>();
/*     */       for (MemoryUnit unit : values()) {
/*     */         map.put(unit.prefix + "byte", unit);
/*     */         map.put(unit.prefix + "bytes", unit);
/*     */         if (unit.prefix.length() == 0) {
/*     */           map.put("b", unit);
/*     */           map.put("B", unit);
/*     */           map.put("", unit);
/*     */         } else {
/*     */           String first = unit.prefix.substring(0, 1);
/*     */           String firstUpper = first.toUpperCase();
/*     */           if (unit.powerOf == 1024) {
/*     */             map.put(first, unit);
/*     */             map.put(firstUpper, unit);
/*     */             map.put(firstUpper + "i", unit);
/*     */             map.put(firstUpper + "iB", unit);
/*     */           } else if (unit.powerOf == 1000) {
/*     */             if (unit.power == 1) {
/*     */               map.put(first + "B", unit);
/*     */             } else {
/*     */               map.put(firstUpper + "B", unit);
/*     */             } 
/*     */           } else {
/*     */             throw new RuntimeException("broken MemoryUnit enum");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return map;
/*     */     }
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     static MemoryUnit parseUnit(String unit) {
/* 627 */       return unitsMap.get(unit);
/*     */     }
/*     */   }
/*     */   
/*     */   public static long parseBytes(String input, ConfigOrigin originForException, String pathForException) {
/* 649 */     String s = ConfigImplUtil.unicodeTrim(input);
/* 650 */     String unitString = getUnits(s);
/* 651 */     String numberString = ConfigImplUtil.unicodeTrim(s.substring(0, s.length() - unitString.length()));
/* 656 */     if (numberString.length() == 0)
/* 657 */       throw new ConfigException.BadValue(originForException, pathForException, "No number in size-in-bytes value '" + input + "'"); 
/* 661 */     MemoryUnit units = MemoryUnit.parseUnit(unitString);
/* 663 */     if (units == null)
/* 664 */       throw new ConfigException.BadValue(originForException, pathForException, "Could not parse size-in-bytes unit '" + unitString + "' (try k, K, kB, KiB, kilobytes, kibibytes)"); 
/*     */     try {
/* 672 */       if (numberString.matches("[0-9]+"))
/* 673 */         return Long.parseLong(numberString) * units.bytes; 
/* 675 */       return (long)(Double.parseDouble(numberString) * units.bytes);
/* 677 */     } catch (NumberFormatException e) {
/* 678 */       throw new ConfigException.BadValue(originForException, pathForException, "Could not parse size-in-bytes number '" + numberString + "'");
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbstractConfigValue peekPath(Path path) {
/* 684 */     return root().peekPath(path);
/*     */   }
/*     */   
/*     */   private static void addProblem(List<ConfigException.ValidationProblem> accumulator, Path path, ConfigOrigin origin, String problem) {
/* 689 */     accumulator.add(new ConfigException.ValidationProblem(path.render(), origin, problem));
/*     */   }
/*     */   
/*     */   private static String getDesc(ConfigValue refValue) {
/* 693 */     if (refValue instanceof AbstractConfigObject) {
/* 694 */       AbstractConfigObject obj = (AbstractConfigObject)refValue;
/* 695 */       if (obj.isEmpty())
/* 696 */         return "object"; 
/* 698 */       return "object with keys " + obj.keySet();
/*     */     } 
/* 699 */     if (refValue instanceof SimpleConfigList)
/* 700 */       return "list"; 
/* 702 */     return refValue.valueType().name().toLowerCase();
/*     */   }
/*     */   
/*     */   private static void addMissing(List<ConfigException.ValidationProblem> accumulator, ConfigValue refValue, Path path, ConfigOrigin origin) {
/* 708 */     addProblem(accumulator, path, origin, "No setting at '" + path.render() + "', expecting: " + getDesc(refValue));
/*     */   }
/*     */   
/*     */   private static void addWrongType(List<ConfigException.ValidationProblem> accumulator, ConfigValue refValue, AbstractConfigValue actual, Path path) {
/* 714 */     addProblem(accumulator, path, actual.origin(), "Wrong value type at '" + path.render() + "', expecting: " + getDesc(refValue) + " but got: " + getDesc(actual));
/*     */   }
/*     */   
/*     */   private static boolean couldBeNull(AbstractConfigValue v) {
/* 720 */     return (DefaultTransformer.transform(v, ConfigValueType.NULL).valueType() == ConfigValueType.NULL);
/*     */   }
/*     */   
/*     */   private static boolean haveCompatibleTypes(ConfigValue reference, AbstractConfigValue value) {
/* 725 */     if (couldBeNull((AbstractConfigValue)reference) || couldBeNull(value))
/* 727 */       return true; 
/* 728 */     if (reference instanceof AbstractConfigObject) {
/* 729 */       if (value instanceof AbstractConfigObject)
/* 730 */         return true; 
/* 732 */       return false;
/*     */     } 
/* 734 */     if (reference instanceof SimpleConfigList) {
/* 736 */       if (value instanceof SimpleConfigList || value instanceof SimpleConfigObject)
/* 737 */         return true; 
/* 739 */       return false;
/*     */     } 
/* 741 */     if (reference instanceof ConfigString)
/* 745 */       return true; 
/* 746 */     if (value instanceof ConfigString)
/* 748 */       return true; 
/* 750 */     if (reference.valueType() == value.valueType())
/* 751 */       return true; 
/* 753 */     return false;
/*     */   }
/*     */   
/*     */   private static void checkValidObject(Path path, AbstractConfigObject reference, AbstractConfigObject value, List<ConfigException.ValidationProblem> accumulator) {
/* 762 */     for (Map.Entry<String, ConfigValue> entry : (Iterable<Map.Entry<String, ConfigValue>>)reference.entrySet()) {
/*     */       Path childPath;
/* 763 */       String key = entry.getKey();
/* 766 */       if (path != null) {
/* 767 */         childPath = Path.newKey(key).prepend(path);
/*     */       } else {
/* 769 */         childPath = Path.newKey(key);
/*     */       } 
/* 771 */       AbstractConfigValue v = value.get(key);
/* 772 */       if (v == null) {
/* 773 */         addMissing(accumulator, entry.getValue(), childPath, value.origin());
/*     */         continue;
/*     */       } 
/* 775 */       checkValid(childPath, entry.getValue(), v, accumulator);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkListCompatibility(Path path, SimpleConfigList listRef, SimpleConfigList listValue, List<ConfigException.ValidationProblem> accumulator) {
/* 782 */     if (!listRef.isEmpty() && !listValue.isEmpty()) {
/* 785 */       AbstractConfigValue refElement = listRef.get(0);
/* 786 */       for (ConfigValue elem : listValue) {
/* 787 */         AbstractConfigValue e = (AbstractConfigValue)elem;
/* 788 */         if (!haveCompatibleTypes(refElement, e)) {
/* 789 */           addProblem(accumulator, path, e.origin(), "List at '" + path.render() + "' contains wrong value type, expecting list of " + getDesc(refElement) + " but got element of type " + getDesc(e));
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void checkValid(Path path, ConfigValue reference, AbstractConfigValue value, List<ConfigException.ValidationProblem> accumulator) {
/* 804 */     if (haveCompatibleTypes(reference, value)) {
/* 805 */       if (reference instanceof AbstractConfigObject && value instanceof AbstractConfigObject) {
/* 806 */         checkValidObject(path, (AbstractConfigObject)reference, (AbstractConfigObject)value, accumulator);
/* 808 */       } else if (reference instanceof SimpleConfigList && value instanceof SimpleConfigList) {
/* 809 */         SimpleConfigList listRef = (SimpleConfigList)reference;
/* 810 */         SimpleConfigList listValue = (SimpleConfigList)value;
/* 811 */         checkListCompatibility(path, listRef, listValue, accumulator);
/* 812 */       } else if (reference instanceof SimpleConfigList && value instanceof SimpleConfigObject) {
/* 814 */         SimpleConfigList listRef = (SimpleConfigList)reference;
/* 815 */         AbstractConfigValue listValue = DefaultTransformer.transform(value, ConfigValueType.LIST);
/* 817 */         if (listValue instanceof SimpleConfigList) {
/* 818 */           checkListCompatibility(path, listRef, (SimpleConfigList)listValue, accumulator);
/*     */         } else {
/* 820 */           addWrongType(accumulator, reference, value, path);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 823 */       addWrongType(accumulator, reference, value, path);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isResolved() {
/* 829 */     return (root().resolveStatus() == ResolveStatus.RESOLVED);
/*     */   }
/*     */   
/*     */   public void checkValid(Config reference, String... restrictToPaths) {
/* 834 */     SimpleConfig ref = (SimpleConfig)reference;
/* 837 */     if (ref.root().resolveStatus() != ResolveStatus.RESOLVED)
/* 838 */       throw new ConfigException.BugOrBroken("do not call checkValid() with an unresolved reference config, call Config#resolve(), see Config#resolve() API docs"); 
/* 843 */     if (root().resolveStatus() != ResolveStatus.RESOLVED)
/* 844 */       throw new ConfigException.NotResolved("need to Config#resolve() each config before using it, see the API docs for Config#resolve()"); 
/* 849 */     List<ConfigException.ValidationProblem> problems = new ArrayList<ConfigException.ValidationProblem>();
/* 851 */     if (restrictToPaths.length == 0) {
/* 852 */       checkValidObject(null, ref.root(), root(), problems);
/*     */     } else {
/* 854 */       for (String p : restrictToPaths) {
/* 855 */         Path path = Path.newPath(p);
/* 856 */         AbstractConfigValue refValue = ref.peekPath(path);
/* 857 */         if (refValue != null) {
/* 858 */           AbstractConfigValue child = peekPath(path);
/* 859 */           if (child != null) {
/* 860 */             checkValid(path, refValue, child, problems);
/*     */           } else {
/* 862 */             addMissing(problems, refValue, path, origin());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 868 */     if (!problems.isEmpty())
/* 869 */       throw new ConfigException.ValidationFailed(problems); 
/*     */   }
/*     */   
/*     */   public SimpleConfig withOnlyPath(String pathExpression) {
/* 875 */     Path path = Path.newPath(pathExpression);
/* 876 */     return new SimpleConfig(root().withOnlyPath(path));
/*     */   }
/*     */   
/*     */   public SimpleConfig withoutPath(String pathExpression) {
/* 881 */     Path path = Path.newPath(pathExpression);
/* 882 */     return new SimpleConfig(root().withoutPath(path));
/*     */   }
/*     */   
/*     */   public SimpleConfig withValue(String pathExpression, ConfigValue v) {
/* 887 */     Path path = Path.newPath(pathExpression);
/* 888 */     return new SimpleConfig(root().withValue(path, v));
/*     */   }
/*     */   
/*     */   SimpleConfig atKey(ConfigOrigin origin, String key) {
/* 892 */     return root().atKey(origin, key);
/*     */   }
/*     */   
/*     */   public SimpleConfig atKey(String key) {
/* 897 */     return root().atKey(key);
/*     */   }
/*     */   
/*     */   public Config atPath(String path) {
/* 902 */     return root().atPath(path);
/*     */   }
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 907 */     return new SerializedConfigValue(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SimpleConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */