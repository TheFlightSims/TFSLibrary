/*    */ package ch.qos.logback.core.joran.conditional;
/*    */ 
/*    */ import ch.qos.logback.core.spi.ContextAwareBase;
/*    */ import ch.qos.logback.core.spi.PropertyContainer;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.codehaus.commons.compiler.CompileException;
/*    */ import org.codehaus.janino.ClassBodyEvaluator;
/*    */ 
/*    */ public class PropertyEvalScriptBuilder extends ContextAwareBase {
/* 29 */   private static String SCRIPT_PREFIX = "public boolean evaluate() { return ";
/*    */   
/* 31 */   private static String SCRIPT_SUFFIX = "; }";
/*    */   
/*    */   final PropertyContainer localPropContainer;
/*    */   
/*    */   Map<String, String> map;
/*    */   
/*    */   PropertyEvalScriptBuilder(PropertyContainer localPropContainer) {
/* 39 */     this.map = new HashMap<String, String>();
/*    */     this.localPropContainer = localPropContainer;
/*    */   }
/*    */   
/*    */   public Condition build(String script) throws IllegalAccessException, CompileException, InstantiationException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
/* 47 */     ClassBodyEvaluator cbe = new ClassBodyEvaluator();
/* 48 */     cbe.setImplementedInterfaces(new Class[] { Condition.class });
/* 49 */     cbe.setExtendedClass(PropertyWrapperForScripts.class);
/* 50 */     cbe.cook(SCRIPT_PREFIX + script + SCRIPT_SUFFIX);
/* 52 */     Class<?> clazz = cbe.getClazz();
/* 53 */     Condition instance = (Condition)clazz.newInstance();
/* 54 */     Method setMapMethod = clazz.getMethod("setPropertyContainers", new Class[] { PropertyContainer.class, PropertyContainer.class });
/* 55 */     setMapMethod.invoke(instance, new Object[] { this.localPropContainer, this.context });
/* 57 */     return instance;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\conditional\PropertyEvalScriptBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */