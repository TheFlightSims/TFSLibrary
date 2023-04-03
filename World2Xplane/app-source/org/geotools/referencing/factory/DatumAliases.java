/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.LocalName;
/*     */ import org.geotools.util.NameFactory;
/*     */ import org.geotools.util.ScopedName;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.PixelInCell;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatumType;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public class DatumAliases extends ReferencingFactory implements DatumFactory {
/*     */   private static final String ALIAS_TABLE = "DatumAliasesTable.txt";
/*     */   
/*     */   private static final String SEPARATORS = ";";
/*     */   
/*  99 */   private static final Object[] NEED_LOADING = new Object[0];
/*     */   
/*     */   private final URL aliasURL;
/*     */   
/* 112 */   private final Map<String, Object[]> aliasMap = (Map)new HashMap<String, Object>();
/*     */   
/*     */   private LocalName[] authorities;
/*     */   
/*     */   private DatumFactory factory;
/*     */   
/*     */   public DatumAliases() {
/* 133 */     super(60);
/* 134 */     this.aliasURL = DatumAliases.class.getResource("DatumAliasesTable.txt");
/* 135 */     if (this.aliasURL == null)
/* 136 */       throw new NoSuchElementException("DatumAliasesTable.txt"); 
/*     */   }
/*     */   
/*     */   public DatumAliases(DatumFactory factory) {
/* 146 */     this();
/* 147 */     this.factory = factory;
/* 148 */     ensureNonNull("factory", factory);
/*     */   }
/*     */   
/*     */   public DatumAliases(DatumFactory factory, URL aliasURL) {
/* 160 */     super(60);
/* 161 */     this.factory = factory;
/* 162 */     this.aliasURL = aliasURL;
/* 163 */     ensureNonNull("factory", factory);
/* 164 */     ensureNonNull("aliasURL", aliasURL);
/*     */   }
/*     */   
/*     */   private DatumFactory getDatumFactory() throws NoSuchElementException {
/* 183 */     assert Thread.holdsLock(this);
/* 184 */     if (this.factory == null) {
/* 186 */       Iterator<DatumFactory> it = ReferencingFactoryFinder.getDatumFactories(null).iterator();
/*     */       while (true) {
/* 187 */         DatumFactory candidate = it.next();
/* 188 */         if (candidate != this) {
/* 189 */           this.factory = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 191 */     return this.factory;
/*     */   }
/*     */   
/*     */   private static String toCaseless(String key) {
/* 198 */     return key.replace('_', ' ').trim().toLowerCase();
/*     */   }
/*     */   
/*     */   private static String readLine(BufferedReader in) throws IOException {
/*     */     String line;
/*     */     do {
/* 207 */       line = in.readLine();
/* 208 */     } while (line != null && ((line = line.trim()).length() == 0 || line.charAt(0) == '#'));
/* 209 */     return line;
/*     */   }
/*     */   
/*     */   private void reload() throws IOException {
/* 221 */     assert Thread.holdsLock(this);
/* 222 */     LogRecord record = Loggings.format(Level.FINE, 27, this.aliasURL);
/* 224 */     record.setLoggerName(LOGGER.getName());
/* 225 */     LOGGER.log(record);
/* 226 */     BufferedReader in = new BufferedReader(new InputStreamReader(this.aliasURL.openStream()));
/* 232 */     String line = readLine(in);
/* 233 */     if (line != null) {
/* 234 */       List<Object> elements = new ArrayList();
/* 235 */       StringTokenizer st = new StringTokenizer(line, ";");
/* 236 */       while (st.hasMoreTokens()) {
/* 237 */         String name = st.nextToken().trim();
/* 238 */         elements.add((name.length() != 0) ? new LocalName(name) : null);
/*     */       } 
/* 240 */       this.authorities = elements.<LocalName>toArray(new LocalName[elements.size()]);
/* 241 */       Map<String, String> canonical = new HashMap<String, String>();
/* 248 */       while ((line = readLine(in)) != null) {
/* 249 */         elements.clear();
/* 250 */         canonical.clear();
/* 251 */         st = new StringTokenizer(line, ";");
/* 252 */         while (st.hasMoreTokens()) {
/* 253 */           String alias = st.nextToken().trim();
/* 254 */           if (alias.length() != 0) {
/* 255 */             String previous = canonical.put(alias, alias);
/* 256 */             if (previous != null) {
/* 257 */               canonical.put(previous, previous);
/* 258 */               alias = previous;
/*     */             } 
/*     */           } else {
/* 261 */             alias = null;
/*     */           } 
/* 263 */           elements.add(alias);
/*     */         } 
/* 266 */         for (int i = elements.size(); --i >= 0 && 
/* 267 */           elements.get(i) == null;)
/* 268 */           elements.remove(i); 
/* 270 */         if (!elements.isEmpty()) {
/* 277 */           String[] names = elements.<String>toArray(new String[elements.size()]);
/* 278 */           for (int j = 0; j < names.length; j++) {
/* 279 */             String name = names[j];
/* 280 */             String key = toCaseless(name);
/* 281 */             Object[] previous = (Object[])this.aliasMap.put(key, names);
/* 282 */             if (previous != null && previous != NEED_LOADING)
/* 283 */               if (previous instanceof GenericName[]) {
/* 284 */                 this.aliasMap.put(key, previous);
/* 285 */               } else if (!Arrays.equals(previous, (Object[])names)) {
/* 287 */                 LOGGER.warning("Inconsistent aliases for datum \"" + name + "\".");
/*     */               }  
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     in.close();
/*     */   }
/*     */   
/*     */   private void log(IOException exception) {
/* 301 */     LogRecord record = Loggings.format(Level.WARNING, 9, this.aliasURL);
/* 302 */     record.setSourceClassName(DatumAliases.class.getName());
/* 303 */     record.setSourceMethodName("reload");
/* 304 */     record.setThrown(exception);
/* 305 */     record.setLoggerName(LOGGER.getName());
/* 306 */     LOGGER.log(record);
/*     */   }
/*     */   
/*     */   private GenericName[] getAliases(String name) {
/* 321 */     assert Thread.holdsLock(this);
/* 322 */     if (this.aliasMap.isEmpty())
/*     */       try {
/* 323 */         reload();
/* 324 */       } catch (IOException exception) {
/* 325 */         log(exception);
/*     */       }  
/* 333 */     name = toCaseless(name);
/* 334 */     Object[] aliases = this.aliasMap.get(name);
/* 335 */     if (aliases == null)
/* 337 */       return null; 
/* 339 */     if (aliases == NEED_LOADING) {
/*     */       try {
/* 343 */         reload();
/* 344 */       } catch (IOException exception) {
/* 345 */         log(exception);
/*     */       } 
/* 348 */       aliases = this.aliasMap.get(name);
/* 349 */       if (aliases == NEED_LOADING)
/* 352 */         return null; 
/*     */     } 
/* 355 */     if (aliases instanceof GenericName[])
/* 356 */       return (GenericName[])aliases; 
/* 364 */     int count = 0;
/* 365 */     GenericName[] names = new GenericName[aliases.length];
/*     */     int i;
/* 366 */     for (i = 0; i < aliases.length; i++) {
/* 367 */       CharSequence alias = (CharSequence)aliases[i];
/* 368 */       if (alias != null) {
/* 369 */         if (count < this.authorities.length) {
/* 370 */           LocalName authority = this.authorities[count];
/* 371 */           if (authority != null) {
/* 372 */             names[count++] = (GenericName)new ScopedName((GenericName)authority, alias);
/*     */             continue;
/*     */           } 
/*     */         } 
/* 376 */         names[count++] = (GenericName)new LocalName(alias);
/*     */       } 
/*     */       continue;
/*     */     } 
/* 379 */     names = (GenericName[])XArray.resize((Object[])names, count);
/* 380 */     for (i = 0; i < names.length; i++) {
/* 381 */       String alias = names[i].tip().toString();
/* 382 */       Object[] previous = (Object[])this.aliasMap.put(toCaseless(alias), names);
/* 383 */       assert previous == names || Arrays.equals(aliases, previous) : alias;
/*     */     } 
/* 385 */     return names;
/*     */   }
/*     */   
/*     */   private Map<String, ?> addAliases(Map<String, ?> properties) {
/*     */     String name;
/* 401 */     ensureNonNull("properties", properties);
/* 402 */     Object value = properties.get("name");
/* 403 */     ensureNonNull("name", value);
/* 405 */     if (value instanceof Identifier) {
/* 406 */       name = ((Identifier)value).getCode();
/*     */     } else {
/* 408 */       name = value.toString();
/*     */     } 
/* 410 */     GenericName[] aliases = getAliases(name);
/* 411 */     if (aliases != null) {
/* 419 */       int count = aliases.length;
/* 420 */       value = properties.get("alias");
/* 421 */       if (value != null) {
/* 422 */         Map<String, GenericName> merged = new LinkedHashMap<String, GenericName>();
/* 423 */         putAll(NameFactory.toArray(value), merged);
/* 424 */         count -= putAll(aliases, merged);
/* 425 */         Collection<GenericName> c = merged.values();
/* 426 */         aliases = c.<GenericName>toArray(new GenericName[c.size()]);
/*     */       } 
/* 432 */       if (count > 0) {
/* 433 */         Map<String, Object> copy = new HashMap<String, Object>(properties);
/* 434 */         copy.put("alias", aliases);
/* 435 */         properties = copy;
/*     */       } 
/*     */     } 
/* 438 */     return properties;
/*     */   }
/*     */   
/*     */   private static final int putAll(GenericName[] names, Map<String, GenericName> map) {
/* 448 */     int ignored = 0;
/* 449 */     for (int i = 0; i < names.length; i++) {
/* 450 */       GenericName name = names[i];
/* 451 */       GenericName scoped = name.toFullyQualifiedName();
/* 452 */       String key = toCaseless(scoped.toString());
/* 453 */       GenericName old = map.put(key, name);
/* 454 */       if (old instanceof org.opengis.util.ScopedName) {
/* 455 */         map.put(key, old);
/* 456 */         ignored++;
/*     */       } 
/*     */     } 
/* 459 */     return ignored;
/*     */   }
/*     */   
/*     */   public synchronized EngineeringDatum createEngineeringDatum(Map<String, ?> properties) throws FactoryException {
/* 471 */     return getDatumFactory().createEngineeringDatum(addAliases(properties));
/*     */   }
/*     */   
/*     */   public synchronized GeodeticDatum createGeodeticDatum(Map<String, ?> properties, Ellipsoid ellipsoid, PrimeMeridian primeMeridian) throws FactoryException {
/* 485 */     return getDatumFactory().createGeodeticDatum(addAliases(properties), ellipsoid, primeMeridian);
/*     */   }
/*     */   
/*     */   public synchronized ImageDatum createImageDatum(Map<String, ?> properties, PixelInCell pixelInCell) throws FactoryException {
/* 500 */     return getDatumFactory().createImageDatum(addAliases(properties), pixelInCell);
/*     */   }
/*     */   
/*     */   public synchronized TemporalDatum createTemporalDatum(Map<String, ?> properties, Date origin) throws FactoryException {
/* 513 */     return getDatumFactory().createTemporalDatum(addAliases(properties), origin);
/*     */   }
/*     */   
/*     */   public synchronized VerticalDatum createVerticalDatum(Map<String, ?> properties, VerticalDatumType type) throws FactoryException {
/* 526 */     return getDatumFactory().createVerticalDatum(addAliases(properties), type);
/*     */   }
/*     */   
/*     */   public synchronized Ellipsoid createEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) throws FactoryException {
/* 542 */     return getDatumFactory().createEllipsoid(addAliases(properties), semiMajorAxis, semiMinorAxis, unit);
/*     */   }
/*     */   
/*     */   public synchronized Ellipsoid createFlattenedSphere(Map<String, ?> properties, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) throws FactoryException {
/* 559 */     return getDatumFactory().createFlattenedSphere(addAliases(properties), semiMajorAxis, inverseFlattening, unit);
/*     */   }
/*     */   
/*     */   public synchronized PrimeMeridian createPrimeMeridian(Map<String, ?> properties, double longitude, Unit<Angle> angularUnit) throws FactoryException {
/* 574 */     return getDatumFactory().createPrimeMeridian(addAliases(properties), longitude, angularUnit);
/*     */   }
/*     */   
/*     */   public synchronized void freeUnused() {
/* 583 */     if (this.aliasMap != null)
/* 584 */       for (Map.Entry<String, Object[]> entry : this.aliasMap.entrySet()) {
/* 585 */         Object[] value = entry.getValue();
/* 586 */         if (!(value instanceof GenericName[]))
/* 587 */           entry.setValue(NEED_LOADING); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\DatumAliases.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */