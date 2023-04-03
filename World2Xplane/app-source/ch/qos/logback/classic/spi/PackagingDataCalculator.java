/*     */ package ch.qos.logback.classic.spi;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import sun.reflect.Reflection;
/*     */ 
/*     */ public class PackagingDataCalculator {
/*  29 */   static final StackTraceElementProxy[] STEP_ARRAY_TEMPLATE = new StackTraceElementProxy[0];
/*     */   
/*  31 */   HashMap<String, ClassPackagingData> cache = new HashMap<String, ClassPackagingData>();
/*     */   
/*     */   private static boolean GET_CALLER_CLASS_METHOD_AVAILABLE = false;
/*     */   
/*     */   static {
/*     */     try {
/*  42 */       Reflection.getCallerClass(2);
/*  43 */       GET_CALLER_CLASS_METHOD_AVAILABLE = true;
/*  44 */     } catch (NoClassDefFoundError e) {
/*     */     
/*  45 */     } catch (NoSuchMethodError e) {
/*     */     
/*  46 */     } catch (Throwable e) {
/*  47 */       System.err.println("Unexpected exception");
/*  48 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void calculate(IThrowableProxy tp) {
/*  54 */     while (tp != null) {
/*  55 */       populateFrames(tp.getStackTraceElementProxyArray());
/*  56 */       IThrowableProxy[] suppressed = tp.getSuppressed();
/*  57 */       if (suppressed != null)
/*  58 */         for (IThrowableProxy current : suppressed)
/*  59 */           populateFrames(current.getStackTraceElementProxyArray());  
/*  62 */       tp = tp.getCause();
/*     */     } 
/*     */   }
/*     */   
/*     */   void populateFrames(StackTraceElementProxy[] stepArray) {
/*  69 */     Throwable t = new Throwable("local stack reference");
/*  70 */     StackTraceElement[] localteSTEArray = t.getStackTrace();
/*  71 */     int commonFrames = STEUtil.findNumberOfCommonFrames(localteSTEArray, stepArray);
/*  73 */     int localFirstCommon = localteSTEArray.length - commonFrames;
/*  74 */     int stepFirstCommon = stepArray.length - commonFrames;
/*  76 */     ClassLoader lastExactClassLoader = null;
/*  77 */     ClassLoader firsExactClassLoader = null;
/*  79 */     int missfireCount = 0;
/*  80 */     for (int i = 0; i < commonFrames; i++) {
/*  81 */       Class callerClass = null;
/*  82 */       if (GET_CALLER_CLASS_METHOD_AVAILABLE)
/*  83 */         callerClass = Reflection.getCallerClass(localFirstCommon + i - missfireCount + 1); 
/*  86 */       StackTraceElementProxy step = stepArray[stepFirstCommon + i];
/*  87 */       String stepClassname = step.ste.getClassName();
/*  89 */       if (callerClass != null && stepClassname.equals(callerClass.getName())) {
/*  91 */         lastExactClassLoader = callerClass.getClassLoader();
/*  92 */         if (firsExactClassLoader == null)
/*  93 */           firsExactClassLoader = lastExactClassLoader; 
/*  95 */         ClassPackagingData pi = calculateByExactType(callerClass);
/*  96 */         step.setClassPackagingData(pi);
/*     */       } else {
/*  98 */         missfireCount++;
/*  99 */         ClassPackagingData pi = computeBySTEP(step, lastExactClassLoader);
/* 100 */         step.setClassPackagingData(pi);
/*     */       } 
/*     */     } 
/* 103 */     populateUncommonFrames(commonFrames, stepArray, firsExactClassLoader);
/*     */   }
/*     */   
/*     */   void populateUncommonFrames(int commonFrames, StackTraceElementProxy[] stepArray, ClassLoader firstExactClassLoader) {
/* 108 */     int uncommonFrames = stepArray.length - commonFrames;
/* 109 */     for (int i = 0; i < uncommonFrames; i++) {
/* 110 */       StackTraceElementProxy step = stepArray[i];
/* 111 */       ClassPackagingData pi = computeBySTEP(step, firstExactClassLoader);
/* 112 */       step.setClassPackagingData(pi);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ClassPackagingData calculateByExactType(Class type) {
/* 117 */     String className = type.getName();
/* 118 */     ClassPackagingData cpd = this.cache.get(className);
/* 119 */     if (cpd != null)
/* 120 */       return cpd; 
/* 122 */     String version = getImplementationVersion(type);
/* 123 */     String codeLocation = getCodeLocation(type);
/* 124 */     cpd = new ClassPackagingData(codeLocation, version);
/* 125 */     this.cache.put(className, cpd);
/* 126 */     return cpd;
/*     */   }
/*     */   
/*     */   private ClassPackagingData computeBySTEP(StackTraceElementProxy step, ClassLoader lastExactClassLoader) {
/* 131 */     String className = step.ste.getClassName();
/* 132 */     ClassPackagingData cpd = this.cache.get(className);
/* 133 */     if (cpd != null)
/* 134 */       return cpd; 
/* 136 */     Class type = bestEffortLoadClass(lastExactClassLoader, className);
/* 137 */     String version = getImplementationVersion(type);
/* 138 */     String codeLocation = getCodeLocation(type);
/* 139 */     cpd = new ClassPackagingData(codeLocation, version, false);
/* 140 */     this.cache.put(className, cpd);
/* 141 */     return cpd;
/*     */   }
/*     */   
/*     */   String getImplementationVersion(Class type) {
/* 145 */     if (type == null)
/* 146 */       return "na"; 
/* 148 */     Package aPackage = type.getPackage();
/* 149 */     if (aPackage != null) {
/* 150 */       String v = aPackage.getImplementationVersion();
/* 151 */       if (v == null)
/* 152 */         return "na"; 
/* 154 */       return v;
/*     */     } 
/* 157 */     return "na";
/*     */   }
/*     */   
/*     */   String getCodeLocation(Class type) {
/*     */     try {
/* 163 */       if (type != null) {
/* 165 */         URL resource = type.getProtectionDomain().getCodeSource().getLocation();
/* 166 */         if (resource != null) {
/* 167 */           String locationStr = resource.toString();
/* 169 */           String result = getCodeLocation(locationStr, '/');
/* 170 */           if (result != null)
/* 171 */             return result; 
/* 173 */           return getCodeLocation(locationStr, '\\');
/*     */         } 
/*     */       } 
/* 176 */     } catch (Exception e) {}
/* 179 */     return "na";
/*     */   }
/*     */   
/*     */   private String getCodeLocation(String locationStr, char separator) {
/* 183 */     int idx = locationStr.lastIndexOf(separator);
/* 184 */     if (isFolder(idx, locationStr)) {
/* 185 */       idx = locationStr.lastIndexOf(separator, idx - 1);
/* 186 */       return locationStr.substring(idx + 1);
/*     */     } 
/* 187 */     if (idx > 0)
/* 188 */       return locationStr.substring(idx + 1); 
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isFolder(int idx, String text) {
/* 194 */     return (idx != -1 && idx + 1 == text.length());
/*     */   }
/*     */   
/*     */   private Class loadClass(ClassLoader cl, String className) {
/* 198 */     if (cl == null)
/* 199 */       return null; 
/*     */     try {
/* 202 */       return cl.loadClass(className);
/* 203 */     } catch (ClassNotFoundException e1) {
/* 204 */       return null;
/* 205 */     } catch (NoClassDefFoundError e1) {
/* 206 */       return null;
/* 207 */     } catch (Exception e) {
/* 208 */       e.printStackTrace();
/* 209 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Class bestEffortLoadClass(ClassLoader lastGuaranteedClassLoader, String className) {
/* 221 */     Class result = loadClass(lastGuaranteedClassLoader, className);
/* 222 */     if (result != null)
/* 223 */       return result; 
/* 225 */     ClassLoader tccl = Thread.currentThread().getContextClassLoader();
/* 226 */     if (tccl != lastGuaranteedClassLoader)
/* 227 */       result = loadClass(tccl, className); 
/* 229 */     if (result != null)
/* 230 */       return result; 
/*     */     try {
/* 234 */       return Class.forName(className);
/* 235 */     } catch (ClassNotFoundException e1) {
/* 236 */       return null;
/* 237 */     } catch (NoClassDefFoundError e1) {
/* 238 */       return null;
/* 239 */     } catch (Exception e) {
/* 240 */       e.printStackTrace();
/* 241 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\PackagingDataCalculator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */