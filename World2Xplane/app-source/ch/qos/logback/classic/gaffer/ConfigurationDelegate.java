/*     */ package ch.qos.logback.classic.gaffer;
/*     */ 
/*     */ import ch.qos.logback.classic.Level;
/*     */ import ch.qos.logback.classic.Logger;
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.jmx.JMXConfigurator;
/*     */ import ch.qos.logback.classic.jmx.MBeanUtil;
/*     */ import ch.qos.logback.classic.net.ReceiverBase;
/*     */ import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
/*     */ import ch.qos.logback.classic.turbo.TurboFilter;
/*     */ import ch.qos.logback.core.Appender;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.CoreConstants;
/*     */ import ch.qos.logback.core.spi.ContextAware;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.spi.LifeCycle;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.StatusListener;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import ch.qos.logback.core.util.CachingDateFormatter;
/*     */ import ch.qos.logback.core.util.Duration;
/*     */ import groovy.lang.Closure;
/*     */ import groovy.lang.GroovyObject;
/*     */ import groovy.lang.MetaClass;
/*     */ import groovy.lang.Reference;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.management.MalformedObjectNameException;
/*     */ import javax.management.ObjectName;
/*     */ import org.codehaus.groovy.reflection.ClassInfo;
/*     */ import org.codehaus.groovy.runtime.BytecodeInterface8;
/*     */ import org.codehaus.groovy.runtime.GStringImpl;
/*     */ import org.codehaus.groovy.runtime.GeneratedClosure;
/*     */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*     */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*     */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*     */ import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
/*     */ import org.slf4j.Logger;
/*     */ 
/*     */ public class ConfigurationDelegate extends ContextAwareBase implements GroovyObject {
/*     */   private List<Appender> appenderList;
/*     */   
/*     */   public ConfigurationDelegate() {
/*  44 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  44 */     List<Appender> list = ScriptBytecodeAdapter.createList(new Object[0]);
/*  44 */     MetaClass metaClass = $getStaticMetaClass();
/*     */   }
/*     */   
/*     */   public Object getDeclaredOrigin() {
/*  47 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public void scan(String scanPeriodStr) {
/*  54 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  54 */     ReconfigureOnChangeFilter rocf = (ReconfigureOnChangeFilter)ScriptBytecodeAdapter.castToType(arrayOfCallSite[0].callConstructor(ReconfigureOnChangeFilter.class), ReconfigureOnChangeFilter.class);
/*  55 */     arrayOfCallSite[1].call(rocf, arrayOfCallSite[2].callGroovyObjectGetProperty(this));
/*  56 */     if (DefaultTypeTransformation.booleanUnbox(scanPeriodStr))
/*     */       try {
/*  58 */         Duration duration = (Duration)ScriptBytecodeAdapter.castToType(arrayOfCallSite[3].call(Duration.class, scanPeriodStr), Duration.class);
/*  59 */         arrayOfCallSite[4].call(rocf, arrayOfCallSite[5].call(duration));
/*  60 */         arrayOfCallSite[6].callCurrent(this, arrayOfCallSite[7].call("Setting ReconfigureOnChangeFilter scanning period to ", duration));
/*  60 */       } catch (NumberFormatException nfe) {
/*  63 */         arrayOfCallSite[8].callCurrent(this, arrayOfCallSite[9].call(arrayOfCallSite[10].call("Error while converting [", arrayOfCallSite[11].callGroovyObjectGetProperty(this)), "] to long"), nfe);
/*     */       } finally {} 
/*  66 */     arrayOfCallSite[12].call(rocf);
/*  67 */     arrayOfCallSite[13].callCurrent(this, "Adding ReconfigureOnChangeFilter as a turbo filter");
/*  68 */     arrayOfCallSite[14].call(arrayOfCallSite[15].callGroovyObjectGetProperty(this), rocf);
/*     */   }
/*     */   
/*     */   public void statusListener(Class listenerClass) {
/*  72 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  72 */     StatusListener statusListener = (StatusListener)ScriptBytecodeAdapter.castToType(arrayOfCallSite[16].call(listenerClass), StatusListener.class);
/*  73 */     arrayOfCallSite[17].call(arrayOfCallSite[18].callGetProperty(arrayOfCallSite[19].callGroovyObjectGetProperty(this)), statusListener);
/*  74 */     if (statusListener instanceof ContextAware)
/*  75 */       arrayOfCallSite[20].call(ScriptBytecodeAdapter.castToType(statusListener, ContextAware.class), arrayOfCallSite[21].callGroovyObjectGetProperty(this)); 
/*  77 */     if (statusListener instanceof LifeCycle)
/*  78 */       arrayOfCallSite[22].call(ScriptBytecodeAdapter.castToType(statusListener, LifeCycle.class)); 
/*  80 */     arrayOfCallSite[23].callCurrent(this, new GStringImpl(new Object[] { arrayOfCallSite[24].callGetProperty(listenerClass) }, new String[] { "Added status listener of type [", "]" }));
/*     */   }
/*     */   
/*     */   public void conversionRule(String conversionWord, Class converterClass) {
/*  84 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  84 */     String converterClassName = (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[25].call(converterClass), String.class);
/*  86 */     Map ruleRegistry = (Map)ScriptBytecodeAdapter.castToType(arrayOfCallSite[26].call(arrayOfCallSite[27].callGroovyObjectGetProperty(this), arrayOfCallSite[28].callGetProperty(CoreConstants.class)), Map.class);
/*  86 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*  87 */       if (ScriptBytecodeAdapter.compareEqual(ruleRegistry, null)) {
/*  88 */         Object object = arrayOfCallSite[33].callConstructor(HashMap.class);
/*  88 */         ruleRegistry = (Map)ScriptBytecodeAdapter.castToType(object, Map.class);
/*  89 */         arrayOfCallSite[34].call(arrayOfCallSite[35].callGroovyObjectGetProperty(this), arrayOfCallSite[36].callGetProperty(CoreConstants.class), ruleRegistry);
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareEqual(ruleRegistry, null)) {
/*     */       Object object = arrayOfCallSite[29].callConstructor(HashMap.class);
/*     */       ruleRegistry = (Map)ScriptBytecodeAdapter.castToType(object, Map.class);
/*  89 */       arrayOfCallSite[30].call(arrayOfCallSite[31].callGroovyObjectGetProperty(this), arrayOfCallSite[32].callGetProperty(CoreConstants.class), ruleRegistry);
/*     */     } 
/*  92 */     arrayOfCallSite[37].callCurrent(this, arrayOfCallSite[38].call(arrayOfCallSite[39].call(arrayOfCallSite[40].call(arrayOfCallSite[41].call("registering conversion word ", conversionWord), " with class ["), converterClassName), "]"));
/*  93 */     arrayOfCallSite[42].call(ruleRegistry, conversionWord, converterClassName);
/*     */   }
/*     */   
/*     */   public void root(Level level) {
/*  96 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  96 */     root(level, ScriptBytecodeAdapter.createList(new Object[0]));
/*  96 */     null;
/*     */   }
/*     */   
/*     */   public void root(Level level, List appenderNames) {
/*  97 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  97 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*  97 */       if (ScriptBytecodeAdapter.compareEqual(level, null)) {
/*  98 */         arrayOfCallSite[46].callCurrent(this, "Root logger cannot be set to level null");
/*     */       } else {
/* 100 */         arrayOfCallSite[47].callCurrent(this, arrayOfCallSite[48].callGetProperty(Logger.class), level, appenderNames);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     if (ScriptBytecodeAdapter.compareEqual(level, null)) {
/*     */       arrayOfCallSite[43].callCurrent(this, "Root logger cannot be set to level null");
/*     */     } else {
/* 100 */       arrayOfCallSite[44].callCurrent(this, arrayOfCallSite[45].callGetProperty(Logger.class), level, appenderNames);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void logger(String name, Level level) {
/* 104 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 104 */     logger(name, level, ScriptBytecodeAdapter.createList(new Object[0]), null);
/* 104 */     null;
/*     */   }
/*     */   
/*     */   public void logger(String name, Level level, List appenderNames, Boolean additivity) {
/* 105 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 105 */     if (DefaultTypeTransformation.booleanUnbox(name)) {
/* 106 */       Reference logger = new Reference(ScriptBytecodeAdapter.castToType(arrayOfCallSite[49].call(ScriptBytecodeAdapter.castToType(arrayOfCallSite[50].callGroovyObjectGetProperty(this), LoggerContext.class), name), Logger.class));
/* 107 */       Level level1 = level;
/* 107 */       ScriptBytecodeAdapter.setProperty(level1, null, logger.get(), "level");
/* 109 */       if (DefaultTypeTransformation.booleanUnbox(appenderNames))
/* 110 */         arrayOfCallSite[51].call(appenderNames, new _logger_closure1(this, this, logger)); 
/* 120 */       if (ScriptBytecodeAdapter.compareNotEqual(additivity, null)) {
/* 121 */         Boolean bool = additivity;
/* 121 */         ScriptBytecodeAdapter.setProperty(bool, null, logger.get(), "additive");
/*     */       } 
/*     */     } else {
/* 124 */       arrayOfCallSite[52].callCurrent(this, "No name attribute for logger");
/*     */     } 
/*     */   }
/*     */   
/*     */   class _logger_closure1 extends Closure implements GeneratedClosure {
/*     */     public _logger_closure1(Object _outerInstance, Object _thisObject, Reference logger) {
/*     */       super(_outerInstance, _thisObject);
/*     */       Reference reference = logger;
/*     */       this.logger = reference;
/*     */     }
/*     */     
/*     */     public Object doCall(Object aName) {
/*     */       Reference reference = new Reference(aName);
/*     */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */       Appender appender = (Appender)ScriptBytecodeAdapter.castToType(arrayOfCallSite[0].call(arrayOfCallSite[1].callGroovyObjectGetProperty(this), new ConfigurationDelegate._logger_closure1_closure3(this, getThisObject(), reference)), Appender.class);
/*     */       if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*     */         if (ScriptBytecodeAdapter.compareNotEqual(appender, null))
/*     */           return arrayOfCallSite[4].call(this.logger.get(), appender); 
/*     */         return arrayOfCallSite[5].callCurrent((GroovyObject)this, new GStringImpl(new Object[] { reference.get() }, new String[] { "Failed to find appender named [", "]" }));
/*     */       } 
/*     */       if (ScriptBytecodeAdapter.compareNotEqual(appender, null))
/*     */         return arrayOfCallSite[2].call(this.logger.get(), appender); 
/*     */       return arrayOfCallSite[3].callCurrent((GroovyObject)this, new GStringImpl(new Object[] { reference.get() }, new String[] { "Failed to find appender named [", "]" }));
/*     */     }
/*     */     
/*     */     public Logger getLogger() {
/*     */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */       return (Logger)ScriptBytecodeAdapter.castToType(this.logger.get(), Logger.class);
/*     */     }
/*     */     
/*     */     static {
/*     */       __$swapInit();
/*     */     }
/*     */   }
/*     */   
/*     */   public void appender(String name, Class clazz, Closure closure) {
/* 129 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 129 */     arrayOfCallSite[53].callCurrent(this, arrayOfCallSite[54].call(arrayOfCallSite[55].call("About to instantiate appender of type [", arrayOfCallSite[56].callGetProperty(clazz)), "]"));
/* 130 */     Appender appender = (Appender)ScriptBytecodeAdapter.castToType(arrayOfCallSite[57].call(clazz), Appender.class);
/* 131 */     arrayOfCallSite[58].callCurrent(this, arrayOfCallSite[59].call(arrayOfCallSite[60].call("Naming appender as [", name), "]"));
/* 132 */     String str = name;
/* 132 */     ScriptBytecodeAdapter.setProperty(str, null, appender, "name");
/* 133 */     Object object = arrayOfCallSite[61].callGroovyObjectGetProperty(this);
/* 133 */     ScriptBytecodeAdapter.setProperty(object, null, appender, "context");
/* 134 */     arrayOfCallSite[62].call(this.appenderList, appender);
/* 134 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 135 */       if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/* 136 */         AppenderDelegate ad = (AppenderDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[68].callConstructor(AppenderDelegate.class, appender), AppenderDelegate.class);
/* 137 */         arrayOfCallSite[69].callCurrent(this, ad, appender);
/* 138 */         Object object1 = arrayOfCallSite[70].callGroovyObjectGetProperty(this);
/* 138 */         ScriptBytecodeAdapter.setProperty(object1, null, ad, "context");
/* 139 */         AppenderDelegate appenderDelegate1 = ad;
/* 139 */         ScriptBytecodeAdapter.setGroovyObjectProperty(appenderDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/* 140 */         Object object2 = arrayOfCallSite[71].callGetProperty(Closure.class);
/* 140 */         ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 141 */         arrayOfCallSite[72].call(closure);
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/*     */       AppenderDelegate ad = (AppenderDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[63].callConstructor(AppenderDelegate.class, appender), AppenderDelegate.class);
/*     */       arrayOfCallSite[64].callCurrent(this, ad, appender);
/*     */       Object object1 = arrayOfCallSite[65].callGroovyObjectGetProperty(this);
/*     */       ScriptBytecodeAdapter.setProperty(object1, null, ad, "context");
/*     */       AppenderDelegate appenderDelegate1 = ad;
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(appenderDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/*     */       Object object2 = arrayOfCallSite[66].callGetProperty(Closure.class);
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 141 */       arrayOfCallSite[67].call(closure);
/*     */     } 
/*     */     try {
/* 144 */       arrayOfCallSite[73].call(appender);
/* 144 */     } catch (RuntimeException e) {
/* 146 */       arrayOfCallSite[74].callCurrent(this, arrayOfCallSite[75].call(arrayOfCallSite[76].call("Failed to start apppender named [", name), "]"), e);
/*     */     } finally {}
/*     */   }
/*     */   
/*     */   public void receiver(String name, Class aClass, Closure closure) {
/* 151 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 151 */     arrayOfCallSite[77].callCurrent(this, arrayOfCallSite[78].call(arrayOfCallSite[79].call("About to instantiate receiver of type [", arrayOfCallSite[80].callGetProperty(arrayOfCallSite[81].callGroovyObjectGetProperty(this))), "]"));
/* 152 */     ReceiverBase receiver = (ReceiverBase)ScriptBytecodeAdapter.castToType(arrayOfCallSite[82].call(aClass), ReceiverBase.class);
/* 153 */     Object object = arrayOfCallSite[83].callGroovyObjectGetProperty(this);
/* 153 */     ScriptBytecodeAdapter.setProperty(object, null, receiver, "context");
/* 153 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 154 */       if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/* 155 */         ComponentDelegate componentDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[88].callConstructor(ComponentDelegate.class, receiver), ComponentDelegate.class);
/* 156 */         Object object1 = arrayOfCallSite[89].callGroovyObjectGetProperty(this);
/* 156 */         ScriptBytecodeAdapter.setProperty(object1, null, componentDelegate, "context");
/* 157 */         ComponentDelegate componentDelegate1 = componentDelegate;
/* 157 */         ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/* 158 */         Object object2 = arrayOfCallSite[90].callGetProperty(Closure.class);
/* 158 */         ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 159 */         arrayOfCallSite[91].call(closure);
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/*     */       ComponentDelegate componentDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[84].callConstructor(ComponentDelegate.class, receiver), ComponentDelegate.class);
/*     */       Object object1 = arrayOfCallSite[85].callGroovyObjectGetProperty(this);
/*     */       ScriptBytecodeAdapter.setProperty(object1, null, componentDelegate, "context");
/*     */       ComponentDelegate componentDelegate1 = componentDelegate;
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/*     */       Object object2 = arrayOfCallSite[86].callGetProperty(Closure.class);
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 159 */       arrayOfCallSite[87].call(closure);
/*     */     } 
/*     */     try {
/* 162 */       arrayOfCallSite[92].call(receiver);
/* 162 */     } catch (RuntimeException e) {
/* 164 */       arrayOfCallSite[93].callCurrent(this, arrayOfCallSite[94].call(arrayOfCallSite[95].call("Failed to start receiver of type [", arrayOfCallSite[96].call(aClass)), "]"), e);
/*     */     } finally {}
/*     */   }
/*     */   
/*     */   private void copyContributions(AppenderDelegate appenderDelegate, Appender appender) {
/* 169 */     Reference reference1 = new Reference(appenderDelegate), reference2 = new Reference(appender);
/* 169 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 169 */     if ((Appender)reference2.get() instanceof ConfigurationContributor) {
/* 170 */       ConfigurationContributor cc = (ConfigurationContributor)ScriptBytecodeAdapter.castToType(reference2.get(), ConfigurationContributor.class);
/* 171 */       arrayOfCallSite[97].call(arrayOfCallSite[98].call(cc), new _copyContributions_closure2(this, this, reference1, reference2));
/*     */     } 
/*     */   }
/*     */   
/*     */   class _copyContributions_closure2 extends Closure implements GeneratedClosure {
/*     */     public _copyContributions_closure2(Object _outerInstance, Object _thisObject, Reference appenderDelegate, Reference appender) {
/*     */       super(_outerInstance, _thisObject);
/*     */       Reference reference1 = appenderDelegate;
/*     */       this.appenderDelegate = reference1;
/*     */       Reference reference2 = appender;
/*     */       this.appender = reference2;
/*     */     }
/*     */     
/*     */     public Object doCall(Object oldName, Object newName) {
/* 172 */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 172 */       Closure closure = ScriptBytecodeAdapter.getMethodPointer(this.appender.get(), (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { oldName }, new String[] { "", "" }), String.class));
/* 172 */       ScriptBytecodeAdapter.setProperty(closure, null, arrayOfCallSite[0].callGetProperty(this.appenderDelegate.get()), (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { newName }, new String[] { "", "" }), String.class));
/* 172 */       return closure;
/*     */     }
/*     */     
/*     */     public Object call(Object oldName, Object newName) {
/*     */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */       return arrayOfCallSite[1].callCurrent((GroovyObject)this, oldName, newName);
/*     */     }
/*     */     
/*     */     public AppenderDelegate getAppenderDelegate() {
/*     */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */       return (AppenderDelegate)ScriptBytecodeAdapter.castToType(this.appenderDelegate.get(), AppenderDelegate.class);
/*     */     }
/*     */     
/*     */     public Appender getAppender() {
/*     */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */       return (Appender)ScriptBytecodeAdapter.castToType(this.appender.get(), Appender.class);
/*     */     }
/*     */     
/*     */     static {
/*     */       __$swapInit();
/*     */     }
/*     */   }
/*     */   
/*     */   public void turboFilter(Class clazz, Closure closure) {
/* 178 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 178 */     arrayOfCallSite[99].callCurrent(this, arrayOfCallSite[100].call(arrayOfCallSite[101].call("About to instantiate turboFilter of type [", arrayOfCallSite[102].callGetProperty(clazz)), "]"));
/* 179 */     TurboFilter turboFilter = (TurboFilter)ScriptBytecodeAdapter.castToType(arrayOfCallSite[103].call(clazz), TurboFilter.class);
/* 180 */     Object object = arrayOfCallSite[104].callGroovyObjectGetProperty(this);
/* 180 */     ScriptBytecodeAdapter.setProperty(object, null, turboFilter, "context");
/* 180 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 182 */       if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/* 183 */         ComponentDelegate componentDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[109].callConstructor(ComponentDelegate.class, turboFilter), ComponentDelegate.class);
/* 184 */         Object object1 = arrayOfCallSite[110].callGroovyObjectGetProperty(this);
/* 184 */         ScriptBytecodeAdapter.setProperty(object1, null, componentDelegate, "context");
/* 185 */         ComponentDelegate componentDelegate1 = componentDelegate;
/* 185 */         ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/* 186 */         Object object2 = arrayOfCallSite[111].callGetProperty(Closure.class);
/* 186 */         ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 187 */         arrayOfCallSite[112].call(closure);
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareNotEqual(closure, null)) {
/*     */       ComponentDelegate componentDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[105].callConstructor(ComponentDelegate.class, turboFilter), ComponentDelegate.class);
/*     */       Object object1 = arrayOfCallSite[106].callGroovyObjectGetProperty(this);
/*     */       ScriptBytecodeAdapter.setProperty(object1, null, componentDelegate, "context");
/*     */       ComponentDelegate componentDelegate1 = componentDelegate;
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ConfigurationDelegate.class, (GroovyObject)closure, "delegate");
/*     */       Object object2 = arrayOfCallSite[107].callGetProperty(Closure.class);
/*     */       ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ConfigurationDelegate.class, (GroovyObject)closure, "resolveStrategy");
/* 187 */       arrayOfCallSite[108].call(closure);
/*     */     } 
/* 189 */     arrayOfCallSite[113].call(turboFilter);
/* 190 */     arrayOfCallSite[114].callCurrent(this, "Adding aforementioned turbo filter to context");
/* 191 */     arrayOfCallSite[115].call(arrayOfCallSite[116].callGroovyObjectGetProperty(this), turboFilter);
/*     */   }
/*     */   
/*     */   public String timestamp(String datePattern, long timeReference) {
/* 195 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 195 */     long now = -1L;
/* 195 */     if (BytecodeInterface8.isOrigL() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 197 */       if (ScriptBytecodeAdapter.compareEqual(Long.valueOf(timeReference), Integer.valueOf(-1))) {
/* 198 */         arrayOfCallSite[122].callCurrent(this, "Using current interpretation time, i.e. now, as time reference.");
/* 199 */         Object object = arrayOfCallSite[123].call(System.class);
/* 199 */         now = DefaultTypeTransformation.longUnbox(object);
/*     */       } else {
/* 201 */         long l = timeReference;
/* 202 */         arrayOfCallSite[124].callCurrent(this, arrayOfCallSite[125].call(arrayOfCallSite[126].call("Using ", Long.valueOf(now)), " as time reference."));
/*     */       } 
/* 204 */       CachingDateFormatter sdf = (CachingDateFormatter)ScriptBytecodeAdapter.castToType(arrayOfCallSite[127].callConstructor(CachingDateFormatter.class, datePattern), CachingDateFormatter.class);
/* 205 */       return (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[128].call(sdf, Long.valueOf(now)), String.class);
/*     */     } 
/*     */     if (ScriptBytecodeAdapter.compareEqual(Long.valueOf(timeReference), Integer.valueOf(-1))) {
/*     */       arrayOfCallSite[117].callCurrent(this, "Using current interpretation time, i.e. now, as time reference.");
/*     */       Object object = arrayOfCallSite[118].call(System.class);
/*     */       now = DefaultTypeTransformation.longUnbox(object);
/*     */     } else {
/*     */       long l = timeReference;
/*     */       arrayOfCallSite[119].callCurrent(this, arrayOfCallSite[120].call(arrayOfCallSite[121].call("Using ", Long.valueOf(now)), " as time reference."));
/*     */     } 
/*     */     CachingDateFormatter cachingDateFormatter = (CachingDateFormatter)ScriptBytecodeAdapter.castToType(arrayOfCallSite[127].callConstructor(CachingDateFormatter.class, datePattern), CachingDateFormatter.class);
/* 205 */     return (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[128].call(cachingDateFormatter, Long.valueOf(now)), String.class);
/*     */   }
/*     */   
/*     */   public void jmxConfigurator(String name) {
/* 216 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 216 */     Object objectName = null;
/* 217 */     Object contextName = arrayOfCallSite[129].callGetProperty(arrayOfCallSite[130].callGroovyObjectGetProperty(this));
/* 217 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 218 */       if (ScriptBytecodeAdapter.compareNotEqual(name, null))
/*     */         try {
/* 221 */           Object object = arrayOfCallSite[132].callConstructor(ObjectName.class, name);
/* 221 */         } catch (MalformedObjectNameException e) {
/* 223 */           String str = name;
/*     */         } finally {} 
/*     */     } else if (ScriptBytecodeAdapter.compareNotEqual(name, null)) {
/*     */       try {
/*     */         Object object = arrayOfCallSite[131].callConstructor(ObjectName.class, name);
/*     */       } catch (MalformedObjectNameException e) {
/* 223 */         String str = name;
/*     */       } finally {}
/*     */     } 
/* 223 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 226 */       if (ScriptBytecodeAdapter.compareEqual(objectName, null)) {
/* 227 */         Object objectNameAsStr = arrayOfCallSite[137].call(MBeanUtil.class, contextName, JMXConfigurator.class);
/* 228 */         Object object1 = arrayOfCallSite[138].call(MBeanUtil.class, arrayOfCallSite[139].callGroovyObjectGetProperty(this), this, objectNameAsStr);
/* 229 */         if (ScriptBytecodeAdapter.compareEqual(objectName, null)) {
/* 230 */           arrayOfCallSite[140].callCurrent(this, new GStringImpl(new Object[] { objectNameAsStr }, new String[] { "Failed to construct ObjectName for [", "]" }));
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareEqual(objectName, null)) {
/*     */       Object objectNameAsStr = arrayOfCallSite[133].call(MBeanUtil.class, contextName, JMXConfigurator.class);
/*     */       Object object1 = arrayOfCallSite[134].call(MBeanUtil.class, arrayOfCallSite[135].callGroovyObjectGetProperty(this), this, objectNameAsStr);
/*     */       if (ScriptBytecodeAdapter.compareEqual(objectName, null)) {
/* 230 */         arrayOfCallSite[136].callCurrent(this, new GStringImpl(new Object[] { objectNameAsStr }, new String[] { "Failed to construct ObjectName for [", "]" }));
/*     */         return;
/*     */       } 
/*     */     } 
/* 235 */     Object platformMBeanServer = arrayOfCallSite[141].callGetProperty(ManagementFactory.class);
/* 236 */     if (!DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[142].call(MBeanUtil.class, platformMBeanServer, objectName))) {
/* 237 */       JMXConfigurator jmxConfigurator = (JMXConfigurator)ScriptBytecodeAdapter.castToType(arrayOfCallSite[143].callConstructor(JMXConfigurator.class, ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(arrayOfCallSite[144].callGroovyObjectGetProperty(this), LoggerContext.class), LoggerContext.class), platformMBeanServer, objectName), JMXConfigurator.class);
/*     */       try {
/* 239 */         arrayOfCallSite[145].call(platformMBeanServer, jmxConfigurator, objectName);
/* 239 */       } catch (Exception all) {
/* 241 */         arrayOfCallSite[146].callCurrent(this, "Failed to create mbean", all);
/*     */       } finally {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scan() {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*     */       scan(null);
/*     */       null;
/*     */       return;
/*     */     } 
/*     */     scan(null);
/*     */     null;
/*     */   }
/*     */   
/*     */   public void logger(String name, Level level, List<String> appenderNames) {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     logger(name, level, appenderNames, null);
/*     */     null;
/*     */   }
/*     */   
/*     */   public void appender(String name, Class clazz) {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     appender(name, clazz, null);
/*     */     null;
/*     */   }
/*     */   
/*     */   public void receiver(String name, Class aClass) {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     receiver(name, aClass, null);
/*     */     null;
/*     */   }
/*     */   
/*     */   public void turboFilter(Class clazz) {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     turboFilter(clazz, null);
/*     */     null;
/*     */   }
/*     */   
/*     */   public String timestamp(String datePattern) {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     return (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) ? timestamp(datePattern, -1L) : timestamp(datePattern, -1L);
/*     */   }
/*     */   
/*     */   public void jmxConfigurator() {
/*     */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*     */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*     */       jmxConfigurator(null);
/*     */       null;
/*     */       return;
/*     */     } 
/*     */     jmxConfigurator(null);
/*     */     null;
/*     */   }
/*     */   
/*     */   static {
/*     */     __$swapInit();
/*     */     long l1 = 0L;
/*     */     __timeStamp__239_neverHappen1368189247380 = l1;
/*     */     long l2 = 1368189247380L;
/*     */     __timeStamp = l2;
/*     */   }
/*     */   
/*     */   public List<Appender> getAppenderList() {
/*     */     return this.appenderList;
/*     */   }
/*     */   
/*     */   public void setAppenderList(List<Appender> paramList) {
/*     */     this.appenderList = paramList;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\ConfigurationDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */