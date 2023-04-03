/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.imageio.spi.RegisterableService;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ 
/*     */ public class AbstractFactory implements Factory, RegisterableService {
/*     */   public static final int MINIMUM_PRIORITY = 1;
/*     */   
/*     */   public static final int NORMAL_PRIORITY = 50;
/*     */   
/*     */   public static final int MAXIMUM_PRIORITY = 100;
/*     */   
/*     */   protected final int priority;
/*     */   
/* 179 */   protected final Map<RenderingHints.Key, Object> hints = new LinkedHashMap<RenderingHints.Key, Object>();
/*     */   
/* 187 */   private final Map<RenderingHints.Key, Object> unmodifiableHints = Collections.unmodifiableMap(this.hints);
/*     */   
/*     */   protected AbstractFactory() {
/* 194 */     this(50);
/*     */   }
/*     */   
/*     */   protected AbstractFactory(int priority) {
/* 204 */     this.priority = priority;
/* 205 */     if (priority < 1 || priority > 100)
/* 206 */       throw new IllegalArgumentException(Errors.format(58, "priority", Integer.valueOf(priority))); 
/*     */   }
/*     */   
/*     */   public int getPriority() {
/* 221 */     return this.priority;
/*     */   }
/*     */   
/*     */   protected boolean addImplementationHints(RenderingHints map) {
/* 249 */     boolean changed = false;
/* 250 */     if (map != null)
/* 251 */       for (Map.Entry<?, ?> entry : map.entrySet()) {
/* 252 */         Object key = entry.getKey();
/* 253 */         if (key instanceof RenderingHints.Key) {
/* 254 */           Object value = entry.getValue();
/* 255 */           Object old = this.hints.put((RenderingHints.Key)key, value);
/* 256 */           if (!changed && !Utilities.equals(value, old))
/* 257 */             changed = true; 
/*     */         } 
/*     */       }  
/* 262 */     return changed;
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 272 */     return this.unmodifiableHints;
/*     */   }
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class<?> category) {
/* 292 */     for (Iterator<?> it = registry.getServiceProviders(category, false); it.hasNext(); ) {
/* 293 */       Object provider = it.next();
/* 294 */       if (provider != this && provider instanceof AbstractFactory) {
/*     */         Object first, second;
/* 295 */         AbstractFactory factory = (AbstractFactory)provider;
/* 296 */         int priority = getPriority();
/* 297 */         int compare = factory.getPriority();
/* 299 */         if (priority > compare) {
/* 300 */           first = this;
/* 301 */           second = factory;
/* 302 */         } else if (priority < compare) {
/* 303 */           first = factory;
/* 304 */           second = this;
/*     */         } else {
/*     */           continue;
/*     */         } 
/* 308 */         registry.setOrdering(category, first, second);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onDeregistration(ServiceRegistry registry, Class category) {}
/*     */   
/*     */   public final int hashCode() {
/* 338 */     return getClass().hashCode() + 37 * this.priority;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/* 363 */     if (object == this)
/* 364 */       return true; 
/* 366 */     if (object != null && object.getClass().equals(getClass())) {
/* 367 */       AbstractFactory that = (AbstractFactory)object;
/* 368 */       if (this.priority == that.priority) {
/* 369 */         Set<FactoryComparator> comparators = new HashSet<FactoryComparator>();
/* 370 */         return (new FactoryComparator(this, that)).compare(comparators);
/*     */       } 
/*     */     } 
/* 373 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 387 */     String name = format(this);
/* 388 */     Map<Factory, String> done = new IdentityHashMap<Factory, String>();
/* 390 */     done.put(this, name);
/* 391 */     String tree = format(getImplementationHints(), done);
/* 392 */     return name + System.getProperty("line.separator", "\n") + tree;
/*     */   }
/*     */   
/*     */   static String toString(Map<?, ?> hints) {
/* 400 */     return format(hints, new IdentityHashMap<Factory, String>());
/*     */   }
/*     */   
/*     */   private static String format(Factory factory) {
/* 407 */     String name = Classes.getShortClassName(factory);
/* 408 */     if (factory instanceof AuthorityFactory)
/* 409 */       name = name + "[\"" + ((AuthorityFactory)factory).getAuthority().getTitle() + "\"]"; 
/* 411 */     return name;
/*     */   }
/*     */   
/*     */   private static String format(Map<?, ?> hints, Map<Factory, String> done) {
/*     */     TableWriter tableWriter;
/*     */     try {
/* 421 */       tableWriter = new TableWriter(null, " ");
/* 422 */       format((Writer)tableWriter, hints, "  ", done);
/* 423 */     } catch (IOException e) {
/* 425 */       throw new AssertionError(e);
/*     */     } 
/* 427 */     return tableWriter.toString();
/*     */   }
/*     */   
/*     */   private static void format(Writer table, Map<?, ?> hints, String indent, Map<Factory, String> done) throws IOException {
/* 439 */     for (Map.Entry<?, ?> entry : hints.entrySet()) {
/* 440 */       Object k = entry.getKey();
/* 441 */       String key = (k instanceof RenderingHints.Key) ? Hints.nameOf((RenderingHints.Key)k) : String.valueOf(k);
/* 443 */       Object value = entry.getValue();
/* 444 */       table.write(indent);
/* 445 */       table.write(key);
/* 446 */       table.write("\t= ");
/* 447 */       Factory recursive = null;
/* 448 */       if (value instanceof Factory) {
/* 449 */         recursive = (Factory)value;
/* 450 */         value = format(recursive);
/* 451 */         String previous = done.put(recursive, key);
/* 452 */         if (previous != null) {
/* 453 */           done.put(recursive, previous);
/* 454 */           table.write("(same as ");
/* 455 */           table.write(previous);
/* 456 */           value = ")";
/* 457 */           recursive = null;
/*     */         } 
/*     */       } 
/* 460 */       table.write(String.valueOf(value));
/* 461 */       table.write(10);
/* 462 */       if (recursive != null) {
/* 463 */         String nextIndent = Utilities.spaces(indent.length() + 2);
/* 464 */         format(table, recursive.getImplementationHints(), nextIndent, done);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\AbstractFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */