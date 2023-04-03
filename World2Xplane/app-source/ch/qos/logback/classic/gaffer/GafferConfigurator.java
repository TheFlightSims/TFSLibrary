/*    */ package ch.qos.logback.classic.gaffer;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
/*    */ import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
/*    */ import ch.qos.logback.core.util.ContextUtil;
/*    */ import groovy.lang.Binding;
/*    */ import groovy.lang.Closure;
/*    */ import groovy.lang.GroovyObject;
/*    */ import groovy.lang.GroovyShell;
/*    */ import groovy.lang.MetaClass;
/*    */ import groovy.lang.Reference;
/*    */ import groovy.lang.Script;
/*    */ import java.io.File;
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.net.URL;
/*    */ import org.codehaus.groovy.control.CompilerConfiguration;
/*    */ import org.codehaus.groovy.control.customizers.ImportCustomizer;
/*    */ import org.codehaus.groovy.reflection.ClassInfo;
/*    */ import org.codehaus.groovy.runtime.ArrayUtil;
/*    */ import org.codehaus.groovy.runtime.BytecodeInterface8;
/*    */ import org.codehaus.groovy.runtime.GStringImpl;
/*    */ import org.codehaus.groovy.runtime.GeneratedClosure;
/*    */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*    */ 
/*    */ public class GafferConfigurator implements GroovyObject {
/*    */   private LoggerContext context;
/*    */   
/*    */   public GafferConfigurator(LoggerContext context) {
/* 31 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 31 */     MetaClass metaClass = $getStaticMetaClass();
/* 31 */     Object object = SYNTHETIC_LOCAL_VARIABLE_1;
/* 31 */     this.context = (LoggerContext)ScriptBytecodeAdapter.castToType(object, LoggerContext.class);
/*    */   }
/*    */   
/*    */   protected void informContextOfURLUsedForConfiguration(URL url) {
/* 35 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 35 */     arrayOfCallSite[0].call(ConfigurationWatchListUtil.class, this.context, url);
/*    */   }
/*    */   
/*    */   public void run(URL url) {
/* 39 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 39 */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 39 */       informContextOfURLUsedForConfiguration(url);
/* 39 */       null;
/*    */     } else {
/* 39 */       arrayOfCallSite[1].callCurrent(this, url);
/*    */     } 
/* 40 */     arrayOfCallSite[2].callCurrent(this, arrayOfCallSite[3].callGetProperty(url));
/*    */   }
/*    */   
/*    */   public void run(File file) {
/* 44 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 44 */     arrayOfCallSite[4].callCurrent(this, arrayOfCallSite[5].call(arrayOfCallSite[6].call(file)));
/* 45 */     arrayOfCallSite[7].callCurrent(this, arrayOfCallSite[8].callGetProperty(file));
/*    */   }
/*    */   
/*    */   public void run(String dslText) {
/* 49 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 49 */     Binding binding = (Binding)ScriptBytecodeAdapter.castToType(arrayOfCallSite[9].callConstructor(Binding.class), Binding.class);
/* 50 */     arrayOfCallSite[10].call(binding, "hostname", arrayOfCallSite[11].callGetProperty(ContextUtil.class));
/* 52 */     Object configuration = arrayOfCallSite[12].callConstructor(CompilerConfiguration.class);
/* 52 */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 53 */       arrayOfCallSite[15].call(configuration, importCustomizer());
/*    */     } else {
/* 53 */       arrayOfCallSite[13].call(configuration, arrayOfCallSite[14].callCurrent(this));
/*    */     } 
/* 56 */     arrayOfCallSite[16].call(arrayOfCallSite[17].callConstructor(ContextUtil.class, this.context), arrayOfCallSite[18].call(this.context));
/* 58 */     Reference dslScript = new Reference(ScriptBytecodeAdapter.castToType(arrayOfCallSite[19].call(arrayOfCallSite[20].callConstructor(GroovyShell.class, binding, configuration), dslText), Script.class));
/* 60 */     arrayOfCallSite[21].call(arrayOfCallSite[22].callGroovyObjectGetProperty(dslScript.get()), ConfigurationDelegate.class);
/* 61 */     arrayOfCallSite[23].call(dslScript.get(), this.context);
/* 62 */     _run_closure1 _run_closure1 = new _run_closure1(this, this, dslScript);
/* 62 */     ScriptBytecodeAdapter.setProperty(_run_closure1, null, arrayOfCallSite[24].callGroovyObjectGetProperty(dslScript.get()), "getDeclaredOrigin");
/* 64 */     arrayOfCallSite[25].call(dslScript.get());
/*    */   }
/*    */   
/*    */   class _run_closure1 extends Closure implements GeneratedClosure {
/*    */     public _run_closure1(Object _outerInstance, Object _thisObject, Reference dslScript) {
/*    */       super(_outerInstance, _thisObject);
/*    */       Reference reference = dslScript;
/*    */       this.dslScript = reference;
/*    */     }
/*    */     
/*    */     public Object doCall(Object it) {
/*    */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */       return this.dslScript.get();
/*    */     }
/*    */     
/*    */     public Script getDslScript() {
/*    */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */       return (Script)ScriptBytecodeAdapter.castToType(this.dslScript.get(), Script.class);
/*    */     }
/*    */     
/*    */     public Object doCall() {
/*    */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */       return doCall(null);
/*    */     }
/*    */     
/*    */     static {
/*    */       __$swapInit();
/*    */     }
/*    */   }
/*    */   
/*    */   protected ImportCustomizer importCustomizer() {
/* 68 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 68 */     Object customizer = arrayOfCallSite[26].callConstructor(ImportCustomizer.class);
/* 71 */     Object core = "ch.qos.logback.core";
/* 72 */     arrayOfCallSite[27].call(customizer, ArrayUtil.createArray(core, new GStringImpl(new Object[] { core }, new String[] { "", ".encoder" }), new GStringImpl(new Object[] { core }, new String[] { "", ".read" }), new GStringImpl(new Object[] { core }, new String[] { "", ".rolling" }), new GStringImpl(new Object[] { core }, new String[] { "", ".status" }), "ch.qos.logback.classic.net"));
/* 75 */     arrayOfCallSite[28].call(customizer, arrayOfCallSite[29].callGetProperty(PatternLayoutEncoder.class));
/* 77 */     arrayOfCallSite[30].call(customizer, arrayOfCallSite[31].callGetProperty(Level.class));
/* 79 */     arrayOfCallSite[32].call(customizer, "off", arrayOfCallSite[33].callGetProperty(Level.class), "OFF");
/* 80 */     arrayOfCallSite[34].call(customizer, "error", arrayOfCallSite[35].callGetProperty(Level.class), "ERROR");
/* 81 */     arrayOfCallSite[36].call(customizer, "warn", arrayOfCallSite[37].callGetProperty(Level.class), "WARN");
/* 82 */     arrayOfCallSite[38].call(customizer, "info", arrayOfCallSite[39].callGetProperty(Level.class), "INFO");
/* 83 */     arrayOfCallSite[40].call(customizer, "debug", arrayOfCallSite[41].callGetProperty(Level.class), "DEBUG");
/* 84 */     arrayOfCallSite[42].call(customizer, "trace", arrayOfCallSite[43].callGetProperty(Level.class), "TRACE");
/* 85 */     arrayOfCallSite[44].call(customizer, "all", arrayOfCallSite[45].callGetProperty(Level.class), "ALL");
/* 87 */     return (ImportCustomizer)ScriptBytecodeAdapter.castToType(customizer, ImportCustomizer.class);
/*    */   }
/*    */   
/*    */   static {
/*    */     __$swapInit();
/*    */     long l1 = 0L;
/*    */     __timeStamp__239_neverHappen1368189247308 = l1;
/*    */     long l2 = 1368189247308L;
/*    */     __timeStamp = l2;
/*    */   }
/*    */   
/*    */   public LoggerContext getContext() {
/*    */     return this.context;
/*    */   }
/*    */   
/*    */   public void setContext(LoggerContext paramLoggerContext) {
/*    */     this.context = paramLoggerContext;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\GafferConfigurator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */