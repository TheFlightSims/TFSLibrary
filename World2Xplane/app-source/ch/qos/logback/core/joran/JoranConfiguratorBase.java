/*     */ package ch.qos.logback.core.joran;
/*     */ 
/*     */ import ch.qos.logback.core.joran.action.Action;
/*     */ import ch.qos.logback.core.joran.action.AppenderAction;
/*     */ import ch.qos.logback.core.joran.action.AppenderRefAction;
/*     */ import ch.qos.logback.core.joran.action.ContextPropertyAction;
/*     */ import ch.qos.logback.core.joran.action.ConversionRuleAction;
/*     */ import ch.qos.logback.core.joran.action.DefinePropertyAction;
/*     */ import ch.qos.logback.core.joran.action.ImplicitAction;
/*     */ import ch.qos.logback.core.joran.action.NestedBasicPropertyIA;
/*     */ import ch.qos.logback.core.joran.action.NestedComplexPropertyIA;
/*     */ import ch.qos.logback.core.joran.action.NewRuleAction;
/*     */ import ch.qos.logback.core.joran.action.ParamAction;
/*     */ import ch.qos.logback.core.joran.action.PropertyAction;
/*     */ import ch.qos.logback.core.joran.action.StatusListenerAction;
/*     */ import ch.qos.logback.core.joran.action.TimestampAction;
/*     */ import ch.qos.logback.core.joran.spi.ElementSelector;
/*     */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*     */ import ch.qos.logback.core.joran.spi.Interpreter;
/*     */ import ch.qos.logback.core.joran.spi.RuleStore;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class JoranConfiguratorBase extends GenericConfigurator {
/*     */   public List getErrorList() {
/*  54 */     return null;
/*     */   }
/*     */   
/*     */   protected void addInstanceRules(RuleStore rs) {
/*  61 */     rs.addRule(new ElementSelector("configuration/variable"), (Action)new PropertyAction());
/*  62 */     rs.addRule(new ElementSelector("configuration/property"), (Action)new PropertyAction());
/*  64 */     rs.addRule(new ElementSelector("configuration/substitutionProperty"), (Action)new PropertyAction());
/*  67 */     rs.addRule(new ElementSelector("configuration/timestamp"), (Action)new TimestampAction());
/*  69 */     rs.addRule(new ElementSelector("configuration/define"), (Action)new DefinePropertyAction());
/*  73 */     rs.addRule(new ElementSelector("configuration/contextProperty"), (Action)new ContextPropertyAction());
/*  76 */     rs.addRule(new ElementSelector("configuration/conversionRule"), (Action)new ConversionRuleAction());
/*  79 */     rs.addRule(new ElementSelector("configuration/statusListener"), (Action)new StatusListenerAction());
/*  82 */     rs.addRule(new ElementSelector("configuration/appender"), (Action)new AppenderAction());
/*  83 */     rs.addRule(new ElementSelector("configuration/appender/appender-ref"), (Action)new AppenderRefAction());
/*  85 */     rs.addRule(new ElementSelector("configuration/newRule"), (Action)new NewRuleAction());
/*  86 */     rs.addRule(new ElementSelector("*/param"), (Action)new ParamAction());
/*     */   }
/*     */   
/*     */   protected void addImplicitRules(Interpreter interpreter) {
/*  92 */     NestedComplexPropertyIA nestedComplexPropertyIA = new NestedComplexPropertyIA();
/*  93 */     nestedComplexPropertyIA.setContext(this.context);
/*  94 */     interpreter.addImplicitAction((ImplicitAction)nestedComplexPropertyIA);
/*  96 */     NestedBasicPropertyIA nestedBasicIA = new NestedBasicPropertyIA();
/*  97 */     nestedBasicIA.setContext(this.context);
/*  98 */     interpreter.addImplicitAction((ImplicitAction)nestedBasicIA);
/*     */   }
/*     */   
/*     */   protected void buildInterpreter() {
/* 103 */     super.buildInterpreter();
/* 104 */     Map<String, Object> omap = this.interpreter.getInterpretationContext().getObjectMap();
/* 106 */     omap.put("APPENDER_BAG", new HashMap<Object, Object>());
/* 107 */     omap.put("FILTER_CHAIN_BAG", new HashMap<Object, Object>());
/*     */   }
/*     */   
/*     */   public InterpretationContext getInterpretationContext() {
/* 111 */     return this.interpreter.getInterpretationContext();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\JoranConfiguratorBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */