/*    */ package ch.qos.logback.classic.gaffer;
/*    */ 
/*    */ import groovy.lang.Closure;
/*    */ import groovy.lang.GroovyObject;
/*    */ import groovy.lang.MetaClass;
/*    */ import java.beans.Introspector;
/*    */ import java.lang.ref.SoftReference;
/*    */ import org.codehaus.groovy.reflection.ClassInfo;
/*    */ import org.codehaus.groovy.runtime.BytecodeInterface8;
/*    */ import org.codehaus.groovy.runtime.GStringImpl;
/*    */ import org.codehaus.groovy.runtime.GeneratedClosure;
/*    */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*    */ import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
/*    */ 
/*    */ public class PropertyUtil implements GroovyObject {
/*    */   public PropertyUtil() {
/*    */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */     MetaClass metaClass = $getStaticMetaClass();
/*    */     this.metaClass = metaClass;
/*    */   }
/*    */   
/*    */   public static boolean hasAdderMethod(Object obj, String name) {
/* 25 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 25 */     String addMethod = null;
/* 25 */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 25 */       GStringImpl gStringImpl1 = new GStringImpl(new Object[] { upperCaseFirstLetter(name) }, new String[] { "add", "" });
/* 25 */       addMethod = (String)ScriptBytecodeAdapter.castToType(gStringImpl1, String.class);
/* 26 */       return DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[1].call(arrayOfCallSite[2].callGetProperty(obj), obj, addMethod));
/*    */     } 
/*    */     GStringImpl gStringImpl = new GStringImpl(new Object[] { arrayOfCallSite[0].callStatic(PropertyUtil.class, name) }new String[] { "add", "" });
/*    */     addMethod = (String)ScriptBytecodeAdapter.castToType(gStringImpl, String.class);
/* 26 */     return DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[1].call(arrayOfCallSite[2].callGetProperty(obj), obj, addMethod));
/*    */   }
/*    */   
/*    */   public static NestingType nestingType(Object obj, String name) {
/* 30 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 30 */     Object decapitalizedName = arrayOfCallSite[3].call(Introspector.class, name);
/* 31 */     if (DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[4].call(obj, decapitalizedName)))
/* 32 */       return (NestingType)ScriptBytecodeAdapter.castToType(arrayOfCallSite[5].callGetProperty(NestingType.class), NestingType.class); 
/* 34 */     if (DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[6].callStatic(PropertyUtil.class, obj, name)))
/* 35 */       return (NestingType)ScriptBytecodeAdapter.castToType(arrayOfCallSite[7].callGetProperty(NestingType.class), NestingType.class); 
/* 37 */     return (NestingType)ScriptBytecodeAdapter.castToType(arrayOfCallSite[8].callGetProperty(NestingType.class), NestingType.class);
/*    */   }
/*    */   
/*    */   public static void attach(NestingType nestingType, Object component, Object subComponent, String name) {
/* 41 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 41 */     NestingType nestingType1 = nestingType;
/* 42 */     if (ScriptBytecodeAdapter.isCase(nestingType1, arrayOfCallSite[9].callGetProperty(NestingType.class))) {
/* 43 */       Object object1 = arrayOfCallSite[10].call(Introspector.class, name);
/* 43 */       name = (String)ScriptBytecodeAdapter.castToType(object1, String.class);
/* 44 */       Object object2 = subComponent;
/* 44 */       ScriptBytecodeAdapter.setProperty(object2, null, component, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { name }, new String[] { "", "" }), String.class));
/* 46 */     } else if (ScriptBytecodeAdapter.isCase(nestingType1, arrayOfCallSite[11].callGetProperty(NestingType.class))) {
/* 47 */       String firstUpperName = (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[12].call(PropertyUtil.class, name), String.class);
/* 48 */       ScriptBytecodeAdapter.invokeMethodN(PropertyUtil.class, component, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { firstUpperName }, new String[] { "add", "" }), String.class), new Object[] { subComponent });
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String transformFirstLetter(String s, Closure closure) {
/* 54 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 54 */     if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 54 */       if ((ScriptBytecodeAdapter.compareEqual(s, null) || ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[14].call(s), Integer.valueOf(0))))
/* 55 */         return s; 
/*    */     } else if ((ScriptBytecodeAdapter.compareEqual(s, null) || ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[13].call(s), Integer.valueOf(0)))) {
/* 55 */       return s;
/*    */     } 
/* 57 */     String firstLetter = (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[15].callConstructor(String.class, arrayOfCallSite[16].call(s, Integer.valueOf(0))), String.class);
/* 59 */     String modifiedFistLetter = (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[17].call(closure, firstLetter), String.class);
/* 59 */     if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 61 */       if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[21].call(s), Integer.valueOf(1)))
/* 62 */         return modifiedFistLetter; 
/* 64 */       return (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[22].call(modifiedFistLetter, arrayOfCallSite[23].call(s, Integer.valueOf(1))), String.class);
/*    */     } 
/*    */     if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[18].call(s), Integer.valueOf(1)))
/*    */       return modifiedFistLetter; 
/* 64 */     return (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[19].call(modifiedFistLetter, arrayOfCallSite[20].call(s, Integer.valueOf(1))), String.class);
/*    */   }
/*    */   
/*    */   public static String upperCaseFirstLetter(String s) {
/* 69 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 69 */     return (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[24].callStatic(PropertyUtil.class, s, new _upperCaseFirstLetter_closure1(PropertyUtil.class, PropertyUtil.class)), String.class);
/*    */   }
/*    */   
/*    */   static {
/*    */     __$swapInit();
/*    */     long l1 = 0L;
/*    */     __timeStamp__239_neverHappen1368189247360 = l1;
/*    */     long l2 = 1368189247360L;
/*    */     __timeStamp = l2;
/*    */   }
/*    */   
/*    */   class _upperCaseFirstLetter_closure1 extends Closure implements GeneratedClosure {
/*    */     public _upperCaseFirstLetter_closure1(Object _outerInstance, Object _thisObject) {
/*    */       super(_outerInstance, _thisObject);
/*    */     }
/*    */     
/*    */     public Object doCall(String it) {
/* 69 */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 69 */       return arrayOfCallSite[0].call(it);
/*    */     }
/*    */     
/*    */     public Object call(String it) {
/*    */       CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */       return arrayOfCallSite[1].callCurrent((GroovyObject)this, it);
/*    */     }
/*    */     
/*    */     static {
/*    */       __$swapInit();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\PropertyUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */