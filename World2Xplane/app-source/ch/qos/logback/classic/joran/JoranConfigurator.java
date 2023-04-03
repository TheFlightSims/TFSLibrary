/*    */ package ch.qos.logback.classic.joran;
/*    */ 
/*    */ import ch.qos.logback.classic.joran.action.ConfigurationAction;
/*    */ import ch.qos.logback.classic.joran.action.ConsolePluginAction;
/*    */ import ch.qos.logback.classic.joran.action.ContextNameAction;
/*    */ import ch.qos.logback.classic.joran.action.EvaluatorAction;
/*    */ import ch.qos.logback.classic.joran.action.InsertFromJNDIAction;
/*    */ import ch.qos.logback.classic.joran.action.JMXConfiguratorAction;
/*    */ import ch.qos.logback.classic.joran.action.LevelAction;
/*    */ import ch.qos.logback.classic.joran.action.LoggerAction;
/*    */ import ch.qos.logback.classic.joran.action.LoggerContextListenerAction;
/*    */ import ch.qos.logback.classic.joran.action.ReceiverAction;
/*    */ import ch.qos.logback.classic.joran.action.RootLoggerAction;
/*    */ import ch.qos.logback.classic.sift.SiftAction;
/*    */ import ch.qos.logback.classic.spi.PlatformInfo;
/*    */ import ch.qos.logback.classic.util.DefaultNestedComponentRules;
/*    */ import ch.qos.logback.core.joran.JoranConfiguratorBase;
/*    */ import ch.qos.logback.core.joran.action.Action;
/*    */ import ch.qos.logback.core.joran.action.AppenderRefAction;
/*    */ import ch.qos.logback.core.joran.action.IncludeAction;
/*    */ import ch.qos.logback.core.joran.action.NOPAction;
/*    */ import ch.qos.logback.core.joran.conditional.ElseAction;
/*    */ import ch.qos.logback.core.joran.conditional.IfAction;
/*    */ import ch.qos.logback.core.joran.conditional.ThenAction;
/*    */ import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
/*    */ import ch.qos.logback.core.joran.spi.ElementSelector;
/*    */ import ch.qos.logback.core.joran.spi.RuleStore;
/*    */ 
/*    */ public class JoranConfigurator extends JoranConfiguratorBase {
/*    */   public void addInstanceRules(RuleStore rs) {
/* 41 */     super.addInstanceRules(rs);
/* 43 */     rs.addRule(new ElementSelector("configuration"), (Action)new ConfigurationAction());
/* 45 */     rs.addRule(new ElementSelector("configuration/contextName"), (Action)new ContextNameAction());
/* 47 */     rs.addRule(new ElementSelector("configuration/contextListener"), (Action)new LoggerContextListenerAction());
/* 49 */     rs.addRule(new ElementSelector("configuration/insertFromJNDI"), (Action)new InsertFromJNDIAction());
/* 51 */     rs.addRule(new ElementSelector("configuration/evaluator"), (Action)new EvaluatorAction());
/* 53 */     rs.addRule(new ElementSelector("configuration/appender/sift"), (Action)new SiftAction());
/* 54 */     rs.addRule(new ElementSelector("configuration/appender/sift/*"), (Action)new NOPAction());
/* 56 */     rs.addRule(new ElementSelector("configuration/logger"), (Action)new LoggerAction());
/* 57 */     rs.addRule(new ElementSelector("configuration/logger/level"), (Action)new LevelAction());
/* 59 */     rs.addRule(new ElementSelector("configuration/root"), (Action)new RootLoggerAction());
/* 60 */     rs.addRule(new ElementSelector("configuration/root/level"), (Action)new LevelAction());
/* 61 */     rs.addRule(new ElementSelector("configuration/logger/appender-ref"), (Action)new AppenderRefAction());
/* 63 */     rs.addRule(new ElementSelector("configuration/root/appender-ref"), (Action)new AppenderRefAction());
/* 67 */     rs.addRule(new ElementSelector("*/if"), (Action)new IfAction());
/* 68 */     rs.addRule(new ElementSelector("*/if/then"), (Action)new ThenAction());
/* 69 */     rs.addRule(new ElementSelector("*/if/then/*"), (Action)new NOPAction());
/* 70 */     rs.addRule(new ElementSelector("*/if/else"), (Action)new ElseAction());
/* 71 */     rs.addRule(new ElementSelector("*/if/else/*"), (Action)new NOPAction());
/* 76 */     if (PlatformInfo.hasJMXObjectName())
/* 77 */       rs.addRule(new ElementSelector("configuration/jmxConfigurator"), (Action)new JMXConfiguratorAction()); 
/* 80 */     rs.addRule(new ElementSelector("configuration/include"), (Action)new IncludeAction());
/* 82 */     rs.addRule(new ElementSelector("configuration/consolePlugin"), (Action)new ConsolePluginAction());
/* 85 */     rs.addRule(new ElementSelector("configuration/receiver"), (Action)new ReceiverAction());
/*    */   }
/*    */   
/*    */   protected void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry registry) {
/* 93 */     DefaultNestedComponentRules.addDefaultNestedComponentRegistryRules(registry);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\joran\JoranConfigurator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */