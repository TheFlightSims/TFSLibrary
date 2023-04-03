/*     */ package ch.qos.logback.classic.gaffer;
/*     */ 
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.joran.spi.NoAutoStartUtil;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import groovy.lang.Closure;
/*     */ import groovy.lang.GroovyObject;
/*     */ import groovy.lang.MetaClass;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.codehaus.groovy.reflection.ClassInfo;
/*     */ import org.codehaus.groovy.runtime.BytecodeInterface8;
/*     */ import org.codehaus.groovy.runtime.GStringImpl;
/*     */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*     */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*     */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*     */ import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
/*     */ 
/*     */ public class ComponentDelegate extends ContextAwareBase implements GroovyObject {
/*     */   private final Object component;
/*     */   
/*     */   private final List fieldsToCascade;
/*     */   
/*     */   public ComponentDelegate(Object component) {
/*  28 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  28 */     List list = ScriptBytecodeAdapter.createList(new Object[0]);
/*  28 */     MetaClass metaClass = $getStaticMetaClass();
/*  31 */     Object object = SYNTHETIC_LOCAL_VARIABLE_1;
/*     */   }
/*     */   
/*     */   public String getLabel() {
/*  34 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  34 */     return "component";
/*     */   }
/*     */   
/*     */   public String getLabelFistLetterInUpperCase() {
/*  36 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  36 */     return (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) ? (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[6].call(arrayOfCallSite[7].call(arrayOfCallSite[8].call(getLabel(), Integer.valueOf(0))), arrayOfCallSite[9].call(getLabel(), Integer.valueOf(1))), String.class) : (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[0].call(arrayOfCallSite[1].call(arrayOfCallSite[2].call(arrayOfCallSite[3].callCurrent(this), Integer.valueOf(0))), arrayOfCallSite[4].call(arrayOfCallSite[5].callCurrent(this), Integer.valueOf(1))), String.class);
/*     */   }
/*     */   
/*     */   public void methodMissing(String name, Object args) {
/*  39 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  39 */     NestingType nestingType = (NestingType)ScriptBytecodeAdapter.castToType(arrayOfCallSite[10].call(PropertyUtil.class, this.component, name), NestingType.class);
/*  39 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*  40 */       if (ScriptBytecodeAdapter.compareEqual(nestingType, arrayOfCallSite[17].callGetProperty(NestingType.class))) {
/*  41 */         arrayOfCallSite[18].callCurrent(this, new GStringImpl(new Object[] { getLabelFistLetterInUpperCase(), getComponentName(), arrayOfCallSite[19].callGetProperty(arrayOfCallSite[20].call(this.component)), name }, new String[] { "", " ", " of type [", "] has no appplicable [", "] property " }));
/*     */         return;
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareEqual(nestingType, arrayOfCallSite[11].callGetProperty(NestingType.class))) {
/*  41 */       arrayOfCallSite[12].callCurrent(this, new GStringImpl(new Object[] { arrayOfCallSite[13].callCurrent(this), arrayOfCallSite[14].callCurrent(this), arrayOfCallSite[15].callGetProperty(arrayOfCallSite[16].call(this.component)), name }, new String[] { "", " ", " of type [", "] has no appplicable [", "] property " }));
/*     */       return;
/*     */     } 
/*  45 */     String subComponentName = null;
/*  46 */     Class clazz = null;
/*  47 */     Closure closure = null;
/*  48 */     Object object = arrayOfCallSite[21].callCurrent(this, args);
/*  48 */     subComponentName = (String)ScriptBytecodeAdapter.castToType(arrayOfCallSite[22].call(object, Integer.valueOf(0)), String.class);
/*  48 */     clazz = (Class)ScriptBytecodeAdapter.castToType(arrayOfCallSite[23].call(object, Integer.valueOf(1)), Class.class);
/*  48 */     closure = (Closure)ScriptBytecodeAdapter.castToType(arrayOfCallSite[24].call(object, Integer.valueOf(2)), Closure.class);
/*  48 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*  49 */       if (ScriptBytecodeAdapter.compareNotEqual(clazz, null)) {
/*  50 */         Object subComponent = arrayOfCallSite[42].call(clazz);
/*  51 */         if ((DefaultTypeTransformation.booleanUnbox(subComponentName) && DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[43].call(subComponent, name)))) {
/*  52 */           String str = subComponentName;
/*  52 */           ScriptBytecodeAdapter.setProperty(str, null, subComponent, "name");
/*     */         } 
/*  54 */         if (subComponent instanceof ch.qos.logback.core.spi.ContextAware) {
/*  55 */           Object object1 = arrayOfCallSite[44].callGroovyObjectGetProperty(this);
/*  55 */           ScriptBytecodeAdapter.setProperty(object1, null, subComponent, "context");
/*     */         } 
/*  57 */         if (DefaultTypeTransformation.booleanUnbox(closure)) {
/*  58 */           ComponentDelegate subDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[45].callConstructor(ComponentDelegate.class, subComponent), ComponentDelegate.class);
/*  60 */           arrayOfCallSite[46].callCurrent(this, subDelegate);
/*  61 */           Object object1 = arrayOfCallSite[47].callGroovyObjectGetProperty(this);
/*  61 */           ScriptBytecodeAdapter.setGroovyObjectProperty(object1, ComponentDelegate.class, subDelegate, "context");
/*  62 */           arrayOfCallSite[48].callCurrent(this, subComponent);
/*  63 */           ComponentDelegate componentDelegate1 = subDelegate;
/*  63 */           ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ComponentDelegate.class, (GroovyObject)closure, "delegate");
/*  64 */           Object object2 = arrayOfCallSite[49].callGetProperty(Closure.class);
/*  64 */           ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ComponentDelegate.class, (GroovyObject)closure, "resolveStrategy");
/*  65 */           arrayOfCallSite[50].call(closure);
/*     */         } 
/*  67 */         if ((subComponent instanceof ch.qos.logback.core.spi.LifeCycle && DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[51].call(NoAutoStartUtil.class, subComponent))))
/*  68 */           arrayOfCallSite[52].call(subComponent); 
/*  70 */         arrayOfCallSite[53].call(PropertyUtil.class, nestingType, this.component, subComponent, name);
/*     */       } else {
/*  72 */         arrayOfCallSite[54].callCurrent(this, new GStringImpl(new Object[] { name, getLabel(), getComponentName(), arrayOfCallSite[55].callGetProperty(arrayOfCallSite[56].call(this.component)) }, new String[] { "No 'class' argument specified for [", "] in ", " ", " of type [", "]" }));
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     if (ScriptBytecodeAdapter.compareNotEqual(clazz, null)) {
/*     */       Object subComponent = arrayOfCallSite[25].call(clazz);
/*     */       if ((DefaultTypeTransformation.booleanUnbox(subComponentName) && DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[26].call(subComponent, name)))) {
/*     */         String str = subComponentName;
/*     */         ScriptBytecodeAdapter.setProperty(str, null, subComponent, "name");
/*     */       } 
/*     */       if (subComponent instanceof ch.qos.logback.core.spi.ContextAware) {
/*     */         Object object1 = arrayOfCallSite[27].callGroovyObjectGetProperty(this);
/*     */         ScriptBytecodeAdapter.setProperty(object1, null, subComponent, "context");
/*     */       } 
/*     */       if (DefaultTypeTransformation.booleanUnbox(closure)) {
/*     */         ComponentDelegate subDelegate = (ComponentDelegate)ScriptBytecodeAdapter.castToType(arrayOfCallSite[28].callConstructor(ComponentDelegate.class, subComponent), ComponentDelegate.class);
/*     */         arrayOfCallSite[29].callCurrent(this, subDelegate);
/*     */         Object object1 = arrayOfCallSite[30].callGroovyObjectGetProperty(this);
/*     */         ScriptBytecodeAdapter.setGroovyObjectProperty(object1, ComponentDelegate.class, subDelegate, "context");
/*     */         arrayOfCallSite[31].callCurrent(this, subComponent);
/*     */         ComponentDelegate componentDelegate1 = subDelegate;
/*     */         ScriptBytecodeAdapter.setGroovyObjectProperty(componentDelegate1, ComponentDelegate.class, (GroovyObject)closure, "delegate");
/*     */         Object object2 = arrayOfCallSite[32].callGetProperty(Closure.class);
/*     */         ScriptBytecodeAdapter.setGroovyObjectProperty(object2, ComponentDelegate.class, (GroovyObject)closure, "resolveStrategy");
/*     */         arrayOfCallSite[33].call(closure);
/*     */       } 
/*     */       if ((subComponent instanceof ch.qos.logback.core.spi.LifeCycle && DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[34].call(NoAutoStartUtil.class, subComponent))))
/*     */         arrayOfCallSite[35].call(subComponent); 
/*     */       arrayOfCallSite[36].call(PropertyUtil.class, nestingType, this.component, subComponent, name);
/*     */     } else {
/*  72 */       arrayOfCallSite[37].callCurrent(this, new GStringImpl(new Object[] { name, arrayOfCallSite[38].callCurrent(this), arrayOfCallSite[39].callCurrent(this), arrayOfCallSite[40].callGetProperty(arrayOfCallSite[41].call(this.component)) }, new String[] { "No 'class' argument specified for [", "] in ", " ", " of type [", "]" }));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cascadeFields(ComponentDelegate subDelegate) {
/*     */     CallSite[] arrayOfCallSite;
/*     */     String k;
/*     */     Iterator iterator;
/*  77 */     for (arrayOfCallSite = $getCallSiteArray(), k = null, iterator = (Iterator)ScriptBytecodeAdapter.castToType(arrayOfCallSite[57].call(this.fieldsToCascade), Iterator.class); iterator.hasNext(); ) {
/*  77 */       k = (String)ScriptBytecodeAdapter.castToType(iterator.next(), String.class);
/*  78 */       Object object = ScriptBytecodeAdapter.getGroovyObjectProperty(ComponentDelegate.class, this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { k }, new String[] { "", "" }), String.class));
/*  78 */       ScriptBytecodeAdapter.setProperty(object, null, arrayOfCallSite[58].callGroovyObjectGetProperty(subDelegate), (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { k }, new String[] { "", "" }), String.class));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void injectParent(Object subComponent) {
/*  83 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  83 */     if (DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[59].call(subComponent, "parent"))) {
/*  84 */       Object object = this.component;
/*  84 */       ScriptBytecodeAdapter.setProperty(object, null, subComponent, "parent");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void propertyMissing(String name, Object value) {
/*  89 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  89 */     NestingType nestingType = (NestingType)ScriptBytecodeAdapter.castToType(arrayOfCallSite[60].call(PropertyUtil.class, this.component, name), NestingType.class);
/*  89 */     if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/*  90 */       if (ScriptBytecodeAdapter.compareEqual(nestingType, arrayOfCallSite[67].callGetProperty(NestingType.class))) {
/*  91 */         arrayOfCallSite[68].callCurrent(this, new GStringImpl(new Object[] { getLabelFistLetterInUpperCase(), getComponentName(), arrayOfCallSite[69].callGetProperty(arrayOfCallSite[70].call(this.component)), name }, new String[] { "", " ", " of type [", "] has no appplicable [", "] property " }));
/*     */         return;
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareEqual(nestingType, arrayOfCallSite[61].callGetProperty(NestingType.class))) {
/*  91 */       arrayOfCallSite[62].callCurrent(this, new GStringImpl(new Object[] { arrayOfCallSite[63].callCurrent(this), arrayOfCallSite[64].callCurrent(this), arrayOfCallSite[65].callGetProperty(arrayOfCallSite[66].call(this.component)), name }, new String[] { "", " ", " of type [", "] has no appplicable [", "] property " }));
/*     */       return;
/*     */     } 
/*  94 */     arrayOfCallSite[71].call(PropertyUtil.class, nestingType, this.component, value, name);
/*     */   }
/*     */   
/*     */   public Object analyzeArgs(Object... args) {
/*  99 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*  99 */     String name = null;
/* 100 */     Class clazz = null;
/* 101 */     Closure closure = null;
/* 101 */     if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 103 */       if (ScriptBytecodeAdapter.compareGreaterThan(arrayOfCallSite[74].call(args), Integer.valueOf(3))) {
/* 104 */         arrayOfCallSite[75].callCurrent(this, new GStringImpl(new Object[] { args }, new String[] { "At most 3 arguments allowed but you passed ", "" }));
/* 105 */         return ScriptBytecodeAdapter.createList(new Object[] { name, clazz, closure });
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareGreaterThan(arrayOfCallSite[72].call(args), Integer.valueOf(3))) {
/*     */       arrayOfCallSite[73].callCurrent(this, new GStringImpl(new Object[] { args }, new String[] { "At most 3 arguments allowed but you passed ", "" }));
/* 105 */       return ScriptBytecodeAdapter.createList(new Object[] { name, clazz, closure });
/*     */     } 
/* 105 */     if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 108 */       if (BytecodeInterface8.objectArrayGet(args, -1) instanceof Closure) {
/* 109 */         Object object = BytecodeInterface8.objectArrayGet(args, -1);
/* 109 */         closure = (Closure)ScriptBytecodeAdapter.castToType(object, Closure.class);
/* 110 */         args = (Object[])ScriptBytecodeAdapter.castToType(arrayOfCallSite[80].call(args, BytecodeInterface8.objectArrayGet(args, -1)), Object[].class);
/* 110 */         arrayOfCallSite[80].call(args, BytecodeInterface8.objectArrayGet(args, -1));
/*     */       } 
/*     */     } else if (arrayOfCallSite[76].call(args, Integer.valueOf(-1)) instanceof Closure) {
/*     */       Object object = arrayOfCallSite[77].call(args, Integer.valueOf(-1));
/*     */       closure = (Closure)ScriptBytecodeAdapter.castToType(object, Closure.class);
/* 110 */       args = (Object[])ScriptBytecodeAdapter.castToType(arrayOfCallSite[78].call(args, arrayOfCallSite[79].call(args, Integer.valueOf(-1))), Object[].class);
/* 110 */       arrayOfCallSite[78].call(args, arrayOfCallSite[79].call(args, Integer.valueOf(-1)));
/*     */     } 
/* 110 */     if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 113 */       if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[84].call(args), Integer.valueOf(1))) {
/* 114 */         Object object = arrayOfCallSite[85].callCurrent(this, BytecodeInterface8.objectArrayGet(args, 0));
/* 114 */         clazz = (Class)ScriptBytecodeAdapter.castToType(object, Class.class);
/*     */       } 
/*     */     } else if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[81].call(args), Integer.valueOf(1))) {
/* 114 */       Object object = arrayOfCallSite[82].callCurrent(this, arrayOfCallSite[83].call(args, Integer.valueOf(0)));
/* 114 */       clazz = (Class)ScriptBytecodeAdapter.castToType(object, Class.class);
/*     */     } 
/* 114 */     if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
/* 117 */       if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[91].call(args), Integer.valueOf(2))) {
/* 118 */         Object object1 = arrayOfCallSite[92].callCurrent(this, BytecodeInterface8.objectArrayGet(args, 0));
/* 118 */         name = (String)ScriptBytecodeAdapter.castToType(object1, String.class);
/* 119 */         Object object2 = arrayOfCallSite[93].callCurrent(this, BytecodeInterface8.objectArrayGet(args, 1));
/* 119 */         clazz = (Class)ScriptBytecodeAdapter.castToType(object2, Class.class);
/*     */       } 
/* 122 */       return ScriptBytecodeAdapter.createList(new Object[] { name, clazz, closure });
/*     */     } 
/*     */     if (ScriptBytecodeAdapter.compareEqual(arrayOfCallSite[86].call(args), Integer.valueOf(2))) {
/*     */       Object object1 = arrayOfCallSite[87].callCurrent(this, arrayOfCallSite[88].call(args, Integer.valueOf(0)));
/*     */       name = (String)ScriptBytecodeAdapter.castToType(object1, String.class);
/*     */       Object object2 = arrayOfCallSite[89].callCurrent(this, arrayOfCallSite[90].call(args, Integer.valueOf(1)));
/*     */       clazz = (Class)ScriptBytecodeAdapter.castToType(object2, Class.class);
/*     */     } 
/* 122 */     return ScriptBytecodeAdapter.createList(new Object[] { name, clazz, closure });
/*     */   }
/*     */   
/*     */   public Class parseClassArgument(Object arg) {
/* 126 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 126 */     if (arg instanceof Class)
/* 127 */       return (Class)ScriptBytecodeAdapter.castToType(arg, Class.class); 
/* 128 */     if (arg instanceof String)
/* 129 */       return (Class)ScriptBytecodeAdapter.castToType(arrayOfCallSite[94].call(Class.class, arg), Class.class); 
/* 131 */     arrayOfCallSite[95].callCurrent(this, new GStringImpl(new Object[] { arrayOfCallSite[96].callGetProperty(arrayOfCallSite[97].call(arg)) }, new String[] { "Unexpected argument type ", "" }));
/* 132 */     return (Class)ScriptBytecodeAdapter.castToType(null, Class.class);
/*     */   }
/*     */   
/*     */   public String parseNameArgument(Object arg) {
/* 137 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 137 */     if (arg instanceof String)
/* 138 */       return (String)ScriptBytecodeAdapter.castToType(arg, String.class); 
/* 140 */     arrayOfCallSite[98].callCurrent(this, "With 2 or 3 arguments, the first argument must be the component name, i.e of type string");
/* 141 */     return (String)ScriptBytecodeAdapter.castToType(null, String.class);
/*     */   }
/*     */   
/*     */   public String getComponentName() {
/* 146 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 146 */     if (DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[99].call(this.component, "name")))
/* 147 */       return (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { arrayOfCallSite[100].callGetProperty(this.component) }, new String[] { "[", "]" }), String.class); 
/* 149 */     return "";
/*     */   }
/*     */   
/*     */   static {
/*     */     __$swapInit();
/*     */     long l1 = 0L;
/*     */     __timeStamp__239_neverHappen1368189247431 = l1;
/*     */     long l2 = 1368189247431L;
/*     */     __timeStamp = l2;
/*     */   }
/*     */   
/*     */   public final Object getComponent() {
/*     */     return this.component;
/*     */   }
/*     */   
/*     */   public final List getFieldsToCascade() {
/*     */     return this.fieldsToCascade;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\ComponentDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */