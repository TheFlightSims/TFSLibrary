/*      */ package org.geotools.factory;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.lang.ref.Reference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import java.util.logging.Logger;
/*      */ import javax.imageio.spi.ServiceRegistry;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.geotools.util.logging.Logging;
/*      */ 
/*      */ public class FactoryRegistry extends ServiceRegistry {
/*   83 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.factory");
/*      */   
/*   88 */   private static final Level DEBUG_LEVEL = Level.FINEST;
/*      */   
/*   98 */   private final FactoryIteratorProviders globalConfiguration = new FactoryIteratorProviders();
/*      */   
/*      */   private Set<Class<?>> needScanForPlugins;
/*      */   
/*  112 */   private final RecursionCheckingHelper scanningCategories = new RecursionCheckingHelper();
/*      */   
/*  118 */   private final RecursionCheckingHelper testingAvailability = new RecursionCheckingHelper();
/*      */   
/*  124 */   private final RecursionCheckingHelper testingHints = new RecursionCheckingHelper();
/*      */   
/*      */   public FactoryRegistry(Class<?> category) {
/*  135 */     this(Collections.singleton(category));
/*      */   }
/*      */   
/*      */   public FactoryRegistry(Class<?>[] categories) {
/*  146 */     this(Arrays.asList(categories));
/*      */   }
/*      */   
/*      */   public FactoryRegistry(Collection<Class<?>> categories) {
/*  155 */     super(categories.iterator());
/*  156 */     for (Iterator<Class<?>> it = getCategories(); it.hasNext(); ) {
/*  157 */       if (this.needScanForPlugins == null)
/*  158 */         this.needScanForPlugins = new HashSet<Class<?>>(); 
/*  160 */       this.needScanForPlugins.add(it.next());
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized <T> Iterator<T> getServiceProviders(final Class<T> category, final ServiceRegistry.Filter filter, final Hints hints) {
/*  185 */     if (this.scanningCategories.contains(category))
/*  188 */       throw new RecursiveSearchException(category); 
/*  190 */     ServiceRegistry.Filter hintsFilter = new ServiceRegistry.Filter() {
/*      */         public boolean filter(Object provider) {
/*  192 */           return FactoryRegistry.this.isAcceptable(category.cast(provider), category, hints, filter);
/*      */         }
/*      */       };
/*  195 */     synchronizeIteratorProviders();
/*  196 */     scanForPluginsIfNeeded(category);
/*  197 */     return getServiceProviders(category, hintsFilter, true);
/*      */   }
/*      */   
/*      */   final <T> Iterator<T> getUnfilteredProviders(Class<T> category) {
/*  227 */     if (this.scanningCategories.contains(category))
/*  228 */       throw new RecursiveSearchException(category); 
/*  230 */     scanForPluginsIfNeeded(category);
/*  231 */     return getServiceProviders(category, true);
/*      */   }
/*      */   
/*      */   public <T> T getServiceProvider(Class<T> category, ServiceRegistry.Filter filter, Hints hints, Hints.Key key) throws FactoryRegistryException {
/*  265 */     synchronizeIteratorProviders();
/*  266 */     boolean debug = LOGGER.isLoggable(DEBUG_LEVEL);
/*  267 */     if (debug)
/*  279 */       debug("ENTRY", category, key, (String)null, (Class)null); 
/*  281 */     Class<?> implementation = null;
/*  282 */     if (key != null) {
/*  286 */       Class<?> valueClass = key.getValueClass();
/*  287 */       if (!category.isAssignableFrom(valueClass)) {
/*  288 */         if (debug)
/*  289 */           debug("THROW", category, key, "unexpected type:", valueClass); 
/*  291 */         throw new IllegalArgumentException(Errors.format(69, key));
/*      */       } 
/*  293 */       if (hints != null) {
/*  294 */         Object hint = hints.get(key);
/*  295 */         if (hint != null) {
/*  296 */           if (debug)
/*  297 */             debug("CHECK", category, key, "user provided a", hint.getClass()); 
/*  299 */           if (category.isInstance(hint)) {
/*  304 */             if (debug)
/*  305 */               debug("RETURN", category, key, "return hint as provided.", (Class)null); 
/*  307 */             return category.cast(hint);
/*      */           } 
/*  326 */           hints = new Hints(hints);
/*  327 */           if (hints.remove(key) != hint)
/*  329 */             throw new AssertionError(key); 
/*  337 */           if (hint instanceof Class[]) {
/*  338 */             Class<?>[] types = (Class[])hint;
/*  339 */             int length = types.length;
/*  340 */             for (int i = 0; i < length - 1; i++) {
/*  341 */               Class<?> type = types[i];
/*  342 */               if (debug)
/*  343 */                 debug("CHECK", category, key, "consider hint[" + i + ']', type); 
/*  345 */               T t = getServiceImplementation(category, type, filter, hints);
/*  346 */               if (t != null) {
/*  347 */                 if (debug)
/*  348 */                   debug("RETURN", category, key, "found implementation", t.getClass()); 
/*  350 */                 return t;
/*      */               } 
/*      */             } 
/*  353 */             if (length != 0)
/*  354 */               implementation = types[length - 1]; 
/*      */           } else {
/*  357 */             implementation = (Class)hint;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  362 */     if (debug && implementation != null)
/*  363 */       debug("CHECK", category, key, "consider hint[last]", implementation); 
/*  365 */     T candidate = getServiceImplementation(category, implementation, filter, hints);
/*  366 */     if (candidate != null) {
/*  367 */       if (debug)
/*  368 */         debug("RETURN", category, key, "found implementation", candidate.getClass()); 
/*  370 */       return candidate;
/*      */     } 
/*  372 */     if (debug)
/*  373 */       debug("THROW", category, key, "could not find implementation.", (Class)null); 
/*  375 */     throw new FactoryNotFoundException(Errors.format(49, (implementation != null) ? implementation : category));
/*      */   }
/*      */   
/*      */   private static void debug(String status, Class<?> category, Hints.Key key, String message, Class type) {
/*  395 */     StringBuilder buffer = new StringBuilder(status);
/*  396 */     buffer.append(Utilities.spaces(Math.max(1, 7 - status.length()))).append('(').append(Classes.getShortName(category));
/*  398 */     if (key != null)
/*  399 */       buffer.append(", ").append(key); 
/*  401 */     buffer.append(')');
/*  402 */     if (message != null)
/*  403 */       buffer.append(": ").append(message); 
/*  405 */     if (type != null)
/*  406 */       buffer.append(' ').append(Classes.getShortName(type)).append('.'); 
/*  408 */     LogRecord record = new LogRecord(DEBUG_LEVEL, buffer.toString());
/*  409 */     record.setSourceClassName(FactoryRegistry.class.getName());
/*  410 */     record.setSourceMethodName("getServiceProvider");
/*  411 */     record.setLoggerName(LOGGER.getName());
/*  412 */     LOGGER.log(record);
/*      */   }
/*      */   
/*      */   private <T> T getServiceImplementation(Class<T> category, Class<?> implementation, ServiceRegistry.Filter filter, Hints hints) {
/*  430 */     for (Iterator<T> it = getUnfilteredProviders(category); it.hasNext(); ) {
/*  431 */       T candidate = it.next();
/*  434 */       if (implementation != null && !implementation.isInstance(candidate))
/*      */         continue; 
/*  437 */       if (!isAcceptable(candidate, category, hints, filter))
/*      */         continue; 
/*  440 */       return candidate;
/*      */     } 
/*  442 */     List<Reference<T>> cached = getCachedProviders(category);
/*  443 */     if (cached != null)
/*  448 */       for (Iterator<Reference<T>> iterator = cached.iterator(); iterator.hasNext(); ) {
/*  449 */         T candidate = ((Reference<T>)iterator.next()).get();
/*  450 */         if (candidate == null) {
/*  451 */           iterator.remove();
/*      */           continue;
/*      */         } 
/*  454 */         if (implementation != null && !implementation.isInstance(candidate))
/*      */           continue; 
/*  457 */         if (!isAcceptable(candidate, category, hints, filter))
/*      */           continue; 
/*  460 */         return candidate;
/*      */       }  
/*  463 */     return null;
/*      */   }
/*      */   
/*      */   <T> List<Reference<T>> getCachedProviders(Class<T> category) {
/*  471 */     return null;
/*      */   }
/*      */   
/*      */   final <T> boolean isAcceptable(T candidate, Class<T> category, Hints hints, ServiceRegistry.Filter filter) {
/*  492 */     if (filter != null && !filter.filter(candidate))
/*  493 */       return false; 
/*  501 */     if (!isAvailable(candidate))
/*  502 */       return false; 
/*  504 */     if (hints != null && 
/*  505 */       candidate instanceof Factory && 
/*  506 */       !usesAcceptableHints((Factory)candidate, category, hints, (Set<Factory>)null))
/*  507 */       return false; 
/*  514 */     return isAcceptable(candidate, category, hints);
/*      */   }
/*      */   
/*      */   private boolean usesAcceptableHints(Factory factory, Class<?> category, Hints hints, Set<Factory> alreadyDone) {
/*      */     Map<RenderingHints.Key, ?> implementationHints;
/*  547 */     if (!this.testingHints.addAndCheck(factory))
/*  548 */       return false; 
/*      */     try {
/*  552 */       implementationHints = Hints.stripNonKeys(factory.getImplementationHints());
/*      */     } finally {
/*  554 */       this.testingHints.removeAndCheck(factory);
/*      */     } 
/*  556 */     if (implementationHints == null)
/*  558 */       return true; 
/*  563 */     Hints remaining = null;
/*  564 */     for (Map.Entry<?, ?> entry : implementationHints.entrySet()) {
/*  565 */       Object key = entry.getKey();
/*  566 */       Object value = entry.getValue();
/*  567 */       Object expected = hints.get(key);
/*  568 */       if (expected != null)
/*  573 */         if (expected instanceof Class) {
/*  574 */           if (!((Class)expected).isInstance(value))
/*  575 */             return false; 
/*  577 */         } else if (expected instanceof Class[]) {
/*  578 */           Class<?>[] types = (Class[])expected;
/*  579 */           int i = 0;
/*      */           do {
/*  580 */             if (i >= types.length)
/*  580 */               return false; 
/*  581 */           } while (!types[i++].isInstance(value));
/*  582 */         } else if (!expected.equals(value)) {
/*  583 */           return false;
/*      */         }  
/*  601 */       if (value instanceof Factory) {
/*  602 */         Factory dependency = (Factory)value;
/*  603 */         if (alreadyDone == null)
/*  604 */           alreadyDone = new HashSet<Factory>(); 
/*  606 */         if (!alreadyDone.contains(dependency)) {
/*      */           Class<?> type;
/*  607 */           alreadyDone.add(factory);
/*  608 */           if (remaining == null) {
/*  609 */             remaining = new Hints(hints);
/*  610 */             remaining.keySet().removeAll(implementationHints.keySet());
/*      */           } 
/*  613 */           if (key instanceof Hints.Key) {
/*  614 */             type = ((Hints.Key)key).getValueClass();
/*      */           } else {
/*  616 */             type = Factory.class;
/*      */           } 
/*  619 */           if (!usesAcceptableHints(dependency, type, remaining, alreadyDone))
/*  620 */             return false; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  625 */     return true;
/*      */   }
/*      */   
/*      */   protected <T> boolean isAcceptable(T provider, Class<T> category, Hints hints) {
/*  651 */     return true;
/*      */   }
/*      */   
/*      */   private boolean isAvailable(Object provider) {
/*  658 */     if (!(provider instanceof OptionalFactory))
/*  659 */       return true; 
/*  661 */     OptionalFactory factory = (OptionalFactory)provider;
/*  662 */     Class<? extends OptionalFactory> type = (Class)factory.getClass();
/*  663 */     if (!this.testingAvailability.addAndCheck(type))
/*  664 */       throw new RecursiveSearchException(type); 
/*      */     try {
/*  667 */       return factory.isAvailable();
/*      */     } finally {
/*  669 */       this.testingAvailability.removeAndCheck(type);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final Set<ClassLoader> getClassLoaders() {
/*  690 */     Set<ClassLoader> loaders = new HashSet<ClassLoader>();
/*  691 */     for (int i = 0; i < 4; i++) {
/*      */       ClassLoader loader;
/*      */       try {
/*  694 */         switch (i) {
/*      */           case 0:
/*  695 */             loader = getClass().getClassLoader();
/*      */             break;
/*      */           case 1:
/*  696 */             loader = FactoryRegistry.class.getClassLoader();
/*      */             break;
/*      */           case 2:
/*  697 */             loader = Thread.currentThread().getContextClassLoader();
/*      */             break;
/*      */           case 3:
/*  698 */             loader = ClassLoader.getSystemClassLoader();
/*      */             break;
/*      */           default:
/*  700 */             throw new AssertionError(i);
/*      */         } 
/*  702 */       } catch (SecurityException exception) {}
/*  707 */       loaders.add(loader);
/*      */     } 
/*  709 */     loaders.remove(null);
/*  710 */     loaders.addAll(GeoTools.getClassLoaders());
/*  718 */     ClassLoader[] asArray = loaders.<ClassLoader>toArray(new ClassLoader[loaders.size()]);
/*  719 */     for (int j = 0; j < asArray.length; j++) {
/*  720 */       ClassLoader loader = asArray[j];
/*      */       try {
/*  722 */         while ((loader = loader.getParent()) != null)
/*  723 */           loaders.remove(loader); 
/*  725 */       } catch (SecurityException exception) {}
/*      */     } 
/*  730 */     if (loaders.isEmpty())
/*  731 */       LOGGER.warning("No class loaders available."); 
/*  733 */     return loaders;
/*      */   }
/*      */   
/*      */   public void scanForPlugins() {
/*  747 */     Set<ClassLoader> loaders = getClassLoaders();
/*  748 */     for (Iterator<Class<?>> categories = getCategories(); categories.hasNext(); ) {
/*  749 */       Class<?> category = categories.next();
/*  750 */       scanForPlugins(loaders, category);
/*      */     } 
/*      */   }
/*      */   
/*      */   private <T> void scanForPlugins(Collection<ClassLoader> loaders, Class<T> category) {
/*  762 */     if (!this.scanningCategories.addAndCheck(category))
/*  763 */       throw new RecursiveSearchException(category); 
/*      */     try {
/*  766 */       StringBuilder message = getLogHeader(category);
/*  767 */       boolean newServices = false;
/*  771 */       for (ClassLoader loader : loaders) {
/*  772 */         newServices |= register(lookupProviders(category, loader), category, message);
/*  773 */         newServices |= registerFromSystemProperty(loader, category, message);
/*      */       } 
/*  778 */       FactoryIteratorProvider[] fip = FactoryIteratorProviders.getIteratorProviders();
/*  779 */       for (int i = 0; i < fip.length; i++) {
/*  780 */         Iterator<T> it = fip[i].iterator(category);
/*  781 */         if (it != null)
/*  782 */           newServices |= register(it, category, message); 
/*      */       } 
/*  788 */       if (newServices)
/*  789 */         log("scanForPlugins", message); 
/*      */     } finally {
/*  792 */       this.scanningCategories.removeAndCheck(category);
/*      */     } 
/*      */   }
/*      */   
/*      */   private <T> void scanForPluginsIfNeeded(Class<?> category) {
/*  801 */     if (this.needScanForPlugins != null && this.needScanForPlugins.remove(category)) {
/*  802 */       if (this.needScanForPlugins.isEmpty())
/*  803 */         this.needScanForPlugins = null; 
/*  805 */       scanForPlugins(getClassLoaders(), category);
/*      */     } 
/*      */   }
/*      */   
/*      */   private <T> boolean register(Iterator<T> factories, Class<T> category, StringBuilder message) {
/*  821 */     boolean newServices = false;
/*  822 */     String lineSeparator = System.getProperty("line.separator", "\n");
/*  823 */     while (factories.hasNext()) {
/*      */       T factory;
/*      */       try {
/*  826 */         factory = factories.next();
/*  827 */       } catch (OutOfMemoryError error) {
/*  829 */         throw error;
/*  830 */       } catch (NoClassDefFoundError error) {
/*  837 */         loadingFailure(category, error, false);
/*      */         continue;
/*  839 */       } catch (ExceptionInInitializerError error) {
/*  844 */         Throwable cause = error.getCause();
/*  845 */         if (cause != null)
/*  846 */           loadingFailure(category, cause, true); 
/*  848 */         throw error;
/*  849 */       } catch (Error error) {
/*  850 */         if (!Classes.getShortClassName(error).equals("ServiceConfigurationError"))
/*  854 */           throw error; 
/*  860 */         loadingFailure(category, error, true);
/*      */         continue;
/*      */       } 
/*  863 */       if (category.isAssignableFrom(factory.getClass())) {
/*  864 */         Class<? extends T> factoryClass = factory.getClass().asSubclass(category);
/*  870 */         T replacement = getServiceProviderByClass((Class)factoryClass);
/*  871 */         if (replacement != null)
/*  872 */           factory = replacement; 
/*  876 */         if (registerServiceProvider(factory, category)) {
/*  883 */           message.append(lineSeparator);
/*  884 */           message.append("  ");
/*  885 */           message.append(factoryClass.getName());
/*  886 */           newServices = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*  890 */     return newServices;
/*      */   }
/*      */   
/*      */   private <T> boolean registerFromSystemProperty(ClassLoader loader, Class<T> category, StringBuilder message) {
/*  906 */     boolean newServices = false;
/*      */     try {
/*  908 */       String classname = System.getProperty(category.getName());
/*  909 */       if (classname != null)
/*      */         try {
/*  910 */           Class<?> candidate = loader.loadClass(classname);
/*  911 */           if (category.isAssignableFrom(candidate)) {
/*  912 */             Class<? extends T> factoryClass = candidate.asSubclass(category);
/*  913 */             T factory = getServiceProviderByClass((Class)factoryClass);
/*  914 */             if (factory == null)
/*      */               try {
/*  915 */                 factory = factoryClass.newInstance();
/*  916 */                 if (registerServiceProvider(factory, category)) {
/*  917 */                   message.append(System.getProperty("line.separator", "\n"));
/*  918 */                   message.append("  ");
/*  919 */                   message.append(factoryClass.getName());
/*  920 */                   newServices = true;
/*      */                 } 
/*  922 */               } catch (IllegalAccessException exception) {
/*  923 */                 throw new FactoryRegistryException(Errors.format(23, classname), exception);
/*  925 */               } catch (InstantiationException exception) {
/*  926 */                 throw new FactoryRegistryException(Errors.format(23, classname), exception);
/*      */               }  
/*  936 */             for (Iterator<T> it = getServiceProviders(category, false); it.hasNext(); ) {
/*  937 */               T other = it.next();
/*  938 */               if (other != factory)
/*  939 */                 setOrdering(category, factory, other); 
/*      */             } 
/*      */           } 
/*  943 */         } catch (ClassNotFoundException exception) {} 
/*  948 */     } catch (SecurityException exception) {}
/*  952 */     return newServices;
/*      */   }
/*      */   
/*      */   private static void loadingFailure(Class<?> category, Throwable error, boolean showStackTrace) {
/*  961 */     String name = Classes.getShortName(category);
/*  962 */     StringBuilder cause = new StringBuilder(Classes.getShortClassName(error));
/*  963 */     String message = error.getLocalizedMessage();
/*  964 */     if (message != null) {
/*  965 */       cause.append(": ");
/*  966 */       cause.append(message);
/*      */     } 
/*  968 */     LogRecord record = Loggings.format(Level.WARNING, 8, name, cause.toString());
/*  970 */     if (showStackTrace)
/*  971 */       record.setThrown(error); 
/*  973 */     record.setSourceClassName(FactoryRegistry.class.getName());
/*  974 */     record.setSourceMethodName("scanForPlugins");
/*  975 */     record.setLoggerName(LOGGER.getName());
/*  976 */     LOGGER.log(record);
/*      */   }
/*      */   
/*      */   private static StringBuilder getLogHeader(Class<?> category) {
/*  983 */     return new StringBuilder(Loggings.getResources(null).getString(21, category));
/*      */   }
/*      */   
/*      */   private static void log(String method, StringBuilder message) {
/*  991 */     LogRecord record = new LogRecord(Level.CONFIG, message.toString());
/*  992 */     record.setSourceClassName(FactoryRegistry.class.getName());
/*  993 */     record.setSourceMethodName(method);
/*  994 */     record.setLoggerName(LOGGER.getName());
/*  995 */     LOGGER.log(record);
/*      */   }
/*      */   
/*      */   private void synchronizeIteratorProviders() {
/* 1007 */     FactoryIteratorProvider[] newProviders = this.globalConfiguration.synchronizeIteratorProviders();
/* 1009 */     if (newProviders == null)
/*      */       return; 
/* 1012 */     for (Iterator<Class<?>> categories = getCategories(); categories.hasNext(); ) {
/* 1013 */       Class<?> category = categories.next();
/* 1014 */       if (this.needScanForPlugins == null || !this.needScanForPlugins.contains(category))
/* 1024 */         for (int i = 0; i < newProviders.length; i++)
/* 1025 */           register(newProviders[i], category);  
/*      */     } 
/*      */   }
/*      */   
/*      */   private <T> void register(FactoryIteratorProvider provider, Class<T> category) {
/* 1035 */     Iterator<T> it = provider.iterator(category);
/* 1036 */     if (it != null) {
/* 1037 */       StringBuilder message = getLogHeader(category);
/* 1038 */       if (register(it, category, message))
/* 1039 */         log("synchronizeIteratorProviders", message); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public <T> boolean setOrdering(Class<T> category, Comparator<T> comparator) {
/* 1061 */     boolean set = false;
/* 1062 */     List<T> previous = new ArrayList<T>();
/* 1063 */     for (Iterator<T> it = getServiceProviders(category, false); it.hasNext(); ) {
/* 1064 */       T f1 = it.next();
/* 1065 */       for (int i = previous.size(); --i >= 0; ) {
/*      */         int c;
/* 1066 */         T f2 = previous.get(i);
/*      */         try {
/* 1069 */           c = comparator.compare(f1, f2);
/* 1070 */         } catch (ClassCastException exception) {
/*      */           continue;
/*      */         } 
/* 1079 */         if (c > 0) {
/* 1080 */           set |= setOrdering(category, f1, f2);
/*      */           continue;
/*      */         } 
/* 1081 */         if (c < 0)
/* 1082 */           set |= setOrdering(category, f2, f1); 
/*      */       } 
/* 1085 */       previous.add(f1);
/*      */     } 
/* 1087 */     return set;
/*      */   }
/*      */   
/*      */   public <T> boolean setOrdering(Class<T> base, boolean set, ServiceRegistry.Filter service1, ServiceRegistry.Filter service2) {
/* 1108 */     boolean done = false;
/* 1109 */     for (Iterator<Class<?>> categories = getCategories(); categories.hasNext(); ) {
/* 1110 */       Class<?> candidate = categories.next();
/* 1111 */       if (base.isAssignableFrom(candidate)) {
/* 1112 */         Class<? extends T> category = candidate.asSubclass(base);
/* 1113 */         done |= setOrUnsetOrdering(category, set, service1, service2);
/*      */       } 
/*      */     } 
/* 1116 */     return done;
/*      */   }
/*      */   
/*      */   private <T> boolean setOrUnsetOrdering(Class<T> category, boolean set, ServiceRegistry.Filter service1, ServiceRegistry.Filter service2) {
/* 1125 */     boolean done = false;
/* 1126 */     T impl1 = null;
/* 1127 */     T impl2 = null;
/* 1128 */     for (Iterator<? extends T> it = getServiceProviders(category, false); it.hasNext(); ) {
/* 1129 */       T factory = it.next();
/* 1130 */       if (service1.filter(factory))
/* 1130 */         impl1 = factory; 
/* 1131 */       if (service2.filter(factory))
/* 1131 */         impl2 = factory; 
/* 1132 */       if (impl1 != null && impl2 != null && impl1 != impl2) {
/* 1133 */         if (set) {
/* 1133 */           done |= setOrdering(category, impl1, impl2);
/*      */           continue;
/*      */         } 
/* 1134 */         done |= unsetOrdering(category, impl1, impl2);
/*      */       } 
/*      */     } 
/* 1137 */     return done;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */