/*     */ package ch.qos.logback.core.util;
/*     */ 
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.spi.ContextAware;
/*     */ import ch.qos.logback.core.spi.PropertyContainer;
/*     */ import ch.qos.logback.core.spi.ScanException;
/*     */ import ch.qos.logback.core.subst.NodeToStringTransformer;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class OptionHelper {
/*     */   static final String DELIM_START = "${";
/*     */   
/*     */   static final char DELIM_STOP = '}';
/*     */   
/*     */   static final String DELIM_DEFAULT = ":-";
/*     */   
/*     */   static final int DELIM_START_LEN = 2;
/*     */   
/*     */   static final int DELIM_STOP_LEN = 1;
/*     */   
/*     */   static final int DELIM_DEFAULT_LEN = 2;
/*     */   
/*     */   static final String _IS_UNDEFINED = "_IS_UNDEFINED";
/*     */   
/*     */   public static Object instantiateByClassName(String className, Class<?> superClass, Context context) throws IncompatibleClassException, DynamicClassLoadingException {
/*  34 */     ClassLoader classLoader = Loader.getClassLoaderOfObject(context);
/*  35 */     return instantiateByClassName(className, superClass, classLoader);
/*     */   }
/*     */   
/*     */   public static Object instantiateByClassNameAndParameter(String className, Class<?> superClass, Context context, Class<?> type, Object param) throws IncompatibleClassException, DynamicClassLoadingException {
/*  41 */     ClassLoader classLoader = Loader.getClassLoaderOfObject(context);
/*  42 */     return instantiateByClassNameAndParameter(className, superClass, classLoader, type, param);
/*     */   }
/*     */   
/*     */   public static Object instantiateByClassName(String className, Class<?> superClass, ClassLoader classLoader) throws IncompatibleClassException, DynamicClassLoadingException {
/*  48 */     return instantiateByClassNameAndParameter(className, superClass, classLoader, (Class<?>)null, (Object)null);
/*     */   }
/*     */   
/*     */   public static Object instantiateByClassNameAndParameter(String className, Class<?> superClass, ClassLoader classLoader, Class<?> type, Object parameter) throws IncompatibleClassException, DynamicClassLoadingException {
/*  55 */     if (className == null)
/*  56 */       throw new NullPointerException(); 
/*     */     try {
/*  59 */       Class<?> classObj = null;
/*  60 */       classObj = classLoader.loadClass(className);
/*  61 */       if (!superClass.isAssignableFrom(classObj))
/*  62 */         throw new IncompatibleClassException(superClass, classObj); 
/*  64 */       if (type == null)
/*  65 */         return classObj.newInstance(); 
/*  67 */       Constructor<?> constructor = classObj.getConstructor(new Class[] { type });
/*  68 */       return constructor.newInstance(new Object[] { parameter });
/*  70 */     } catch (IncompatibleClassException ice) {
/*  71 */       throw ice;
/*  72 */     } catch (Throwable t) {
/*  73 */       throw new DynamicClassLoadingException("Failed to instantiate type " + className, t);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String substVars(String val, PropertyContainer pc1) {
/* 109 */     return substVars(val, pc1, null);
/*     */   }
/*     */   
/*     */   public static String substVars(String input, PropertyContainer pc0, PropertyContainer pc1) {
/*     */     try {
/* 117 */       String replacement = NodeToStringTransformer.substituteVariable(input, pc0, pc1);
/* 119 */       if (replacement.contains("${"))
/* 120 */         replacement = NodeToStringTransformer.substituteVariable(replacement, pc0, pc1); 
/* 122 */       return replacement;
/* 123 */     } catch (ScanException e) {
/* 124 */       throw new IllegalArgumentException("Failed to parse input [" + input + "]", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String propertyLookup(String key, PropertyContainer pc1, PropertyContainer pc2) {
/* 130 */     String value = null;
/* 132 */     value = pc1.getProperty(key);
/* 135 */     if (value == null && pc2 != null)
/* 136 */       value = pc2.getProperty(key); 
/* 139 */     if (value == null)
/* 140 */       value = getSystemProperty(key, null); 
/* 142 */     if (value == null)
/* 143 */       value = getEnv(key); 
/* 145 */     return value;
/*     */   }
/*     */   
/*     */   public static String getSystemProperty(String key, String def) {
/*     */     try {
/* 159 */       return System.getProperty(key, def);
/* 160 */     } catch (SecurityException e) {
/* 161 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getEnv(String key) {
/*     */     try {
/* 173 */       return System.getenv(key);
/* 174 */     } catch (SecurityException e) {
/* 175 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getSystemProperty(String key) {
/*     */     try {
/* 189 */       return System.getProperty(key);
/* 190 */     } catch (SecurityException e) {
/* 191 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setSystemProperties(ContextAware contextAware, Properties props) {
/* 196 */     for (Object o : props.keySet()) {
/* 197 */       String key = (String)o;
/* 198 */       String value = props.getProperty(key);
/* 199 */       setSystemProperty(contextAware, key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setSystemProperty(ContextAware contextAware, String key, String value) {
/*     */     try {
/* 205 */       System.setProperty(key, value);
/* 206 */     } catch (SecurityException e) {
/* 207 */       contextAware.addError("Failed to set system property [" + key + "]", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Properties getSystemProperties() {
/*     */     try {
/* 219 */       return System.getProperties();
/* 220 */     } catch (SecurityException e) {
/* 221 */       return new Properties();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String[] extractDefaultReplacement(String key) {
/* 234 */     String[] result = new String[2];
/* 235 */     if (key == null)
/* 236 */       return result; 
/* 238 */     result[0] = key;
/* 239 */     int d = key.indexOf(":-");
/* 240 */     if (d != -1) {
/* 241 */       result[0] = key.substring(0, d);
/* 242 */       result[1] = key.substring(d + 2);
/*     */     } 
/* 244 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(String value, boolean dEfault) {
/* 255 */     if (value == null)
/* 256 */       return dEfault; 
/* 259 */     String trimmedVal = value.trim();
/* 261 */     if ("true".equalsIgnoreCase(trimmedVal))
/* 262 */       return true; 
/* 265 */     if ("false".equalsIgnoreCase(trimmedVal))
/* 266 */       return false; 
/* 269 */     return dEfault;
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(String str) {
/* 273 */     return (str == null || "".equals(str));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\OptionHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */