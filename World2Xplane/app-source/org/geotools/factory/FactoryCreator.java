/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class FactoryCreator extends FactoryRegistry {
/*  50 */   private static final Class[] HINTS_ARGUMENT = new Class[] { Hints.class };
/*     */   
/*  55 */   private final Map<Class<?>, List<Reference<?>>> cache = new HashMap<Class<?>, List<Reference<?>>>();
/*     */   
/*  61 */   private final Set<Class<?>> underConstruction = new HashSet<Class<?>>();
/*     */   
/*     */   public FactoryCreator(Class<?> category) {
/*  71 */     super(category);
/*     */   }
/*     */   
/*     */   public FactoryCreator(Class<?>[] categories) {
/*  82 */     super(categories);
/*     */   }
/*     */   
/*     */   public FactoryCreator(Collection<Class<?>> categories) {
/*  91 */     super(categories);
/*     */   }
/*     */   
/*     */   final <T> List<Reference<T>> getCachedProviders(Class<T> category) {
/*  99 */     List<Reference<?>> c = this.cache.get(category);
/* 100 */     if (c == null) {
/* 101 */       c = new LinkedList<Reference<?>>();
/* 102 */       this.cache.put(category, c);
/*     */     } 
/* 105 */     return (List)c;
/*     */   }
/*     */   
/*     */   private <T> void cache(Class<T> category, T factory) {
/* 117 */     getCachedProviders(category).add(new WeakReference<T>(factory));
/*     */   }
/*     */   
/*     */   public <T> T getServiceProvider(Class<T> category, ServiceRegistry.Filter filter, Hints hints, Hints.Key key) throws FactoryRegistryException {
/*     */     try {
/* 145 */       return super.getServiceProvider(category, filter, hints, key);
/* 146 */     } catch (FactoryNotFoundException exception) {
/*     */       Class<?>[] types;
/* 148 */       FactoryNotFoundException notFound = exception;
/* 155 */       if (hints == null || key == null) {
/* 156 */         types = null;
/*     */       } else {
/* 158 */         Object hint = hints.get(key);
/* 159 */         if (hint == null) {
/* 160 */           types = null;
/*     */         } else {
/* 162 */           if (hint instanceof Class[]) {
/* 163 */             types = (Class[])hint;
/*     */           } else {
/* 165 */             types = new Class[] { (Class)hint };
/*     */           } 
/* 169 */           int length = types.length;
/* 170 */           for (int i = 0; i < length; i++) {
/* 171 */             Class<?> type = types[i];
/* 172 */             if (type != null && category.isAssignableFrom(type)) {
/* 173 */               int modifiers = type.getModifiers();
/* 174 */               if (!Modifier.isAbstract(modifiers)) {
/* 175 */                 T candidate = createSafe(category, type, hints);
/* 176 */                 if (candidate != null) {
/* 179 */                   if (isAcceptable(candidate, category, hints, filter)) {
/* 180 */                     cache(category, candidate);
/* 181 */                     return candidate;
/*     */                   } 
/* 183 */                   dispose(candidate);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 195 */       for (Iterator<T> it = getUnfilteredProviders(category); it.hasNext(); ) {
/* 196 */         T candidate, factory = it.next();
/* 197 */         Class<?> implementation = factory.getClass();
/* 198 */         if (types != null && !isTypeOf(types, implementation))
/*     */           continue; 
/* 201 */         if (filter != null && !filter.filter(factory))
/*     */           continue; 
/*     */         try {
/* 206 */           candidate = createSafe(category, implementation, hints);
/* 207 */         } catch (FactoryNotFoundException factoryNotFoundException) {
/* 210 */           Logging.recoverableException(LOGGER, FactoryCreator.class, "getServiceProvider", factoryNotFoundException);
/*     */           continue;
/* 212 */         } catch (FactoryRegistryException factoryRegistryException) {
/* 213 */           if (factoryRegistryException.getCause() instanceof NoSuchMethodException)
/*     */             continue; 
/* 220 */           throw factoryRegistryException;
/*     */         } 
/* 223 */         if (candidate == null)
/*     */           continue; 
/* 226 */         if (isAcceptable(candidate, category, hints, filter)) {
/* 227 */           cache(category, candidate);
/* 228 */           return candidate;
/*     */         } 
/* 230 */         dispose(candidate);
/*     */       } 
/* 232 */       throw notFound;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isTypeOf(Class<?>[] types, Class<?> implementation) {
/* 239 */     for (int i = 0; i < types.length; i++) {
/* 240 */       if (types[i].isAssignableFrom(implementation))
/* 241 */         return true; 
/*     */     } 
/* 244 */     return false;
/*     */   }
/*     */   
/*     */   private <T> T createSafe(Class<T> category, Class<?> implementation, Hints hints) {
/* 256 */     if (!this.underConstruction.add(implementation))
/* 257 */       return null; 
/*     */     try {
/* 260 */       return (T)createServiceProvider((Class)category, implementation, hints);
/*     */     } finally {
/* 262 */       if (!this.underConstruction.remove(implementation))
/* 263 */         throw new AssertionError(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected <T> T createServiceProvider(Class<T> category, Class<?> implementation, Hints hints) throws FactoryRegistryException {
/*     */     Throwable throwable;
/*     */     try {
/* 292 */       return category.cast(implementation.getConstructor(HINTS_ARGUMENT).newInstance(new Object[] { hints }));
/* 294 */     } catch (NoSuchMethodException exception) {
/* 296 */       throwable = exception;
/*     */       try {
/* 299 */         return category.cast(implementation.getConstructor((Class[])null).newInstance((Object[])null));
/* 301 */       } catch (NoSuchMethodException noSuchMethodException) {}
/* 304 */     } catch (IllegalAccessException exception) {
/* 305 */       throwable = exception;
/* 306 */     } catch (InstantiationException exception) {
/* 307 */       throwable = exception;
/* 308 */     } catch (InvocationTargetException exception) {
/* 309 */       throwable = exception.getCause();
/* 310 */       if (throwable instanceof FactoryRegistryException)
/* 311 */         throw (FactoryRegistryException)throwable; 
/*     */     } 
/* 314 */     throw new FactoryRegistryException(Errors.format(23, implementation), throwable);
/*     */   }
/*     */   
/*     */   private static void dispose(Object factory) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */