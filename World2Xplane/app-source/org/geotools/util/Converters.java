/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.resources.LazySet;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class Converters {
/*  45 */   private static final Logger LOGGER = Logging.getLogger(Converters.class);
/*     */   
/*     */   static ConverterFactory[] factories;
/*     */   
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/*  63 */     assert Thread.holdsLock(Converters.class);
/*  64 */     if (registry == null)
/*  65 */       registry = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { ConverterFactory.class })); 
/*  68 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized Set<ConverterFactory> getConverterFactories(Hints hints) {
/*  87 */     hints = GeoTools.addDefaultHints(hints);
/*  88 */     return (Set<ConverterFactory>)new LazySet(getServiceRegistry().getServiceProviders(ConverterFactory.class, null, hints));
/*     */   }
/*     */   
/*     */   public static Set<ConverterFactory> getConverterFactories(Class<?> source, Class<?> target) {
/* 106 */     HashSet<ConverterFactory> factories = new HashSet<ConverterFactory>();
/* 107 */     for (ConverterFactory factory : factories()) {
/* 108 */       if (factory.createConverter(source, target, null) != null)
/* 109 */         factories.add(factory); 
/*     */     } 
/* 112 */     return factories;
/*     */   }
/*     */   
/*     */   public static <T> T convert(Object source, Class<T> target) {
/* 129 */     return convert(source, target, null);
/*     */   }
/*     */   
/*     */   public static <T> T convert(Object source, Class<T> target, Hints hints) {
/* 154 */     if (source == null)
/* 155 */       return null; 
/* 158 */     Class<?> sourceClass = source.getClass();
/* 159 */     if (sourceClass == target || sourceClass.equals(target) || target.isAssignableFrom(sourceClass))
/* 161 */       return target.cast(source); 
/* 164 */     for (ConverterFactory factory : factories()) {
/* 165 */       Converter converter = factory.createConverter(sourceClass, target, hints);
/* 166 */       if (converter != null)
/*     */         try {
/* 168 */           T converted = converter.convert(source, target);
/* 169 */           if (converted != null)
/* 170 */             return converted; 
/* 172 */         } catch (Exception e) {
/* 173 */           if (LOGGER.isLoggable(Level.FINER))
/* 174 */             LOGGER.log(Level.FINER, "Error applying the converter " + converter.getClass() + " on (" + source + "," + target + ")", e); 
/*     */         }  
/*     */     } 
/* 182 */     if (String.class.equals(target))
/* 183 */       return target.cast(source.toString()); 
/* 185 */     return null;
/*     */   }
/*     */   
/*     */   static ConverterFactory[] factories() {
/* 195 */     if (factories == null) {
/* 196 */       Collection<ConverterFactory> factoryCollection = getConverterFactories(GeoTools.getDefaultHints());
/* 198 */       factories = factoryCollection.<ConverterFactory>toArray(new ConverterFactory[factoryCollection.size()]);
/*     */     } 
/* 201 */     return factories;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Converters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */