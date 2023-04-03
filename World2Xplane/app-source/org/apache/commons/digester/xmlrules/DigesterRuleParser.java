/*     */ package org.apache.commons.digester.xmlrules;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.beanutils.ConvertUtils;
/*     */ import org.apache.commons.collections.ArrayStack;
/*     */ import org.apache.commons.digester.AbstractObjectCreationFactory;
/*     */ import org.apache.commons.digester.BeanPropertySetterRule;
/*     */ import org.apache.commons.digester.CallMethodRule;
/*     */ import org.apache.commons.digester.CallParamRule;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.FactoryCreateRule;
/*     */ import org.apache.commons.digester.NodeCreateRule;
/*     */ import org.apache.commons.digester.ObjectCreateRule;
/*     */ import org.apache.commons.digester.ObjectCreationFactory;
/*     */ import org.apache.commons.digester.ObjectParamRule;
/*     */ import org.apache.commons.digester.Rule;
/*     */ import org.apache.commons.digester.RuleSet;
/*     */ import org.apache.commons.digester.RuleSetBase;
/*     */ import org.apache.commons.digester.Rules;
/*     */ import org.apache.commons.digester.SetNestedPropertiesRule;
/*     */ import org.apache.commons.digester.SetNextRule;
/*     */ import org.apache.commons.digester.SetPropertiesRule;
/*     */ import org.apache.commons.digester.SetPropertyRule;
/*     */ import org.apache.commons.digester.SetRootRule;
/*     */ import org.apache.commons.digester.SetTopRule;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class DigesterRuleParser extends RuleSetBase {
/*     */   public static final String DIGESTER_PUBLIC_ID = "-//Jakarta Apache //DTD digester-rules XML V1.0//EN";
/*     */   
/*     */   private String digesterDtdUrl;
/*     */   
/*     */   protected Digester targetDigester;
/*     */   
/*  83 */   protected String basePath = "";
/*     */   
/*     */   protected PatternStack patternStack;
/*     */   
/*     */   protected class PatternStack extends ArrayStack {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected PatternStack(DigesterRuleParser this$0) {
/*  89 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  91 */       StringBuffer str = new StringBuffer();
/*  92 */       for (int i = 0; i < size(); i++) {
/*  93 */         String elem = get(i).toString();
/*  94 */         if (elem.length() > 0) {
/*  95 */           if (str.length() > 0)
/*  96 */             str.append('/'); 
/*  98 */           str.append(elem);
/*     */         } 
/*     */       } 
/* 101 */       return str.toString();
/*     */     }
/*     */   }
/*     */   
/* 116 */   private Set includedFiles = new HashSet();
/*     */   
/*     */   public DigesterRuleParser() {
/* 123 */     this.patternStack = new PatternStack(this);
/*     */   }
/*     */   
/*     */   public DigesterRuleParser(Digester targetDigester) {
/* 132 */     this.targetDigester = targetDigester;
/* 133 */     this.patternStack = new PatternStack(this);
/*     */   }
/*     */   
/*     */   private DigesterRuleParser(Digester targetDigester, PatternStack stack, Set includedFiles) {
/* 148 */     this.targetDigester = targetDigester;
/* 149 */     this.patternStack = stack;
/* 150 */     this.includedFiles = includedFiles;
/*     */   }
/*     */   
/*     */   public void setTarget(Digester d) {
/* 158 */     this.targetDigester = d;
/*     */   }
/*     */   
/*     */   public void setBasePath(String path) {
/* 169 */     if (path == null) {
/* 170 */       this.basePath = "";
/* 172 */     } else if (path.length() > 0 && !path.endsWith("/")) {
/* 173 */       this.basePath = path + "/";
/*     */     } else {
/* 175 */       this.basePath = path;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDigesterRulesDTD(String dtdURL) {
/* 184 */     this.digesterDtdUrl = dtdURL;
/*     */   }
/*     */   
/*     */   protected String getDigesterRulesDTD() {
/* 195 */     return this.digesterDtdUrl;
/*     */   }
/*     */   
/*     */   public void add(Rule rule) {
/* 206 */     this.targetDigester.addRule(this.basePath + this.patternStack.toString(), rule);
/*     */   }
/*     */   
/*     */   public void addRuleInstances(Digester digester) {
/* 221 */     String ruleClassName = Rule.class.getName();
/* 222 */     digester.register("-//Jakarta Apache //DTD digester-rules XML V1.0//EN", getDigesterRulesDTD());
/* 224 */     digester.addRule("*/pattern", new PatternRule(this, "value"));
/* 226 */     digester.addRule("*/include", new IncludeRule(this));
/* 228 */     digester.addFactoryCreate("*/bean-property-setter-rule", (ObjectCreationFactory)new BeanPropertySetterRuleFactory());
/* 229 */     digester.addRule("*/bean-property-setter-rule", new PatternRule(this, "pattern"));
/* 230 */     digester.addSetNext("*/bean-property-setter-rule", "add", ruleClassName);
/* 232 */     digester.addFactoryCreate("*/call-method-rule", (ObjectCreationFactory)new CallMethodRuleFactory(this));
/* 233 */     digester.addRule("*/call-method-rule", new PatternRule(this, "pattern"));
/* 234 */     digester.addSetNext("*/call-method-rule", "add", ruleClassName);
/* 236 */     digester.addFactoryCreate("*/object-param-rule", (ObjectCreationFactory)new ObjectParamRuleFactory(this));
/* 237 */     digester.addRule("*/object-param-rule", new PatternRule(this, "pattern"));
/* 238 */     digester.addSetNext("*/object-param-rule", "add", ruleClassName);
/* 240 */     digester.addFactoryCreate("*/call-param-rule", (ObjectCreationFactory)new CallParamRuleFactory(this));
/* 241 */     digester.addRule("*/call-param-rule", new PatternRule(this, "pattern"));
/* 242 */     digester.addSetNext("*/call-param-rule", "add", ruleClassName);
/* 244 */     digester.addFactoryCreate("*/factory-create-rule", (ObjectCreationFactory)new FactoryCreateRuleFactory(this));
/* 245 */     digester.addRule("*/factory-create-rule", new PatternRule(this, "pattern"));
/* 246 */     digester.addSetNext("*/factory-create-rule", "add", ruleClassName);
/* 248 */     digester.addFactoryCreate("*/object-create-rule", (ObjectCreationFactory)new ObjectCreateRuleFactory(this));
/* 249 */     digester.addRule("*/object-create-rule", new PatternRule(this, "pattern"));
/* 250 */     digester.addSetNext("*/object-create-rule", "add", ruleClassName);
/* 252 */     digester.addFactoryCreate("*/node-create-rule", (ObjectCreationFactory)new NodeCreateRuleFactory(this));
/* 253 */     digester.addRule("*/node-create-rule", new PatternRule(this, "pattern"));
/* 254 */     digester.addSetNext("*/node-create-rule", "add", ruleClassName);
/* 256 */     digester.addFactoryCreate("*/set-properties-rule", (ObjectCreationFactory)new SetPropertiesRuleFactory(this));
/* 257 */     digester.addRule("*/set-properties-rule", new PatternRule(this, "pattern"));
/* 258 */     digester.addSetNext("*/set-properties-rule", "add", ruleClassName);
/* 260 */     digester.addRule("*/set-properties-rule/alias", new SetPropertiesAliasRule(this));
/* 262 */     digester.addFactoryCreate("*/set-property-rule", (ObjectCreationFactory)new SetPropertyRuleFactory(this));
/* 263 */     digester.addRule("*/set-property-rule", new PatternRule(this, "pattern"));
/* 264 */     digester.addSetNext("*/set-property-rule", "add", ruleClassName);
/* 266 */     digester.addFactoryCreate("*/set-nested-properties-rule", (ObjectCreationFactory)new SetNestedPropertiesRuleFactory(this));
/* 267 */     digester.addRule("*/set-nested-properties-rule", new PatternRule(this, "pattern"));
/* 268 */     digester.addSetNext("*/set-nested-properties-rule", "add", ruleClassName);
/* 270 */     digester.addRule("*/set-nested-properties-rule/alias", new SetNestedPropertiesAliasRule(this));
/* 272 */     digester.addFactoryCreate("*/set-top-rule", (ObjectCreationFactory)new SetTopRuleFactory(this));
/* 273 */     digester.addRule("*/set-top-rule", new PatternRule(this, "pattern"));
/* 274 */     digester.addSetNext("*/set-top-rule", "add", ruleClassName);
/* 276 */     digester.addFactoryCreate("*/set-next-rule", (ObjectCreationFactory)new SetNextRuleFactory(this));
/* 277 */     digester.addRule("*/set-next-rule", new PatternRule(this, "pattern"));
/* 278 */     digester.addSetNext("*/set-next-rule", "add", ruleClassName);
/* 279 */     digester.addFactoryCreate("*/set-root-rule", (ObjectCreationFactory)new SetRootRuleFactory(this));
/* 280 */     digester.addRule("*/set-root-rule", new PatternRule(this, "pattern"));
/* 281 */     digester.addSetNext("*/set-root-rule", "add", ruleClassName);
/*     */   }
/*     */   
/*     */   private class PatternRule extends Rule {
/*     */     private String attrName;
/*     */     
/*     */     private String pattern;
/*     */     
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     public PatternRule(DigesterRuleParser this$0, String attrName) {
/* 301 */       this.this$0 = this$0;
/*     */       this.pattern = null;
/* 303 */       this.attrName = attrName;
/*     */     }
/*     */     
/*     */     public void begin(Attributes attributes) {
/* 311 */       this.pattern = attributes.getValue(this.attrName);
/* 312 */       if (this.pattern != null)
/* 313 */         this.this$0.patternStack.push(this.pattern); 
/*     */     }
/*     */     
/*     */     public void end() {
/* 322 */       if (this.pattern != null)
/* 323 */         this.this$0.patternStack.pop(); 
/*     */     }
/*     */   }
/*     */   
/*     */   private class IncludeRule extends Rule {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     public IncludeRule(DigesterRuleParser this$0) {
/* 338 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void begin(Attributes attributes) throws Exception {
/* 350 */       String fileName = attributes.getValue("path");
/* 351 */       if (fileName != null && fileName.length() > 0)
/* 352 */         includeXMLRules(fileName); 
/* 357 */       String className = attributes.getValue("class");
/* 358 */       if (className != null && className.length() > 0)
/* 359 */         includeProgrammaticRules(className); 
/*     */     }
/*     */     
/*     */     private void includeXMLRules(String fileName) throws IOException, SAXException, CircularIncludeException {
/* 370 */       ClassLoader cl = Thread.currentThread().getContextClassLoader();
/* 371 */       if (cl == null)
/* 372 */         cl = this.this$0.getClass().getClassLoader(); 
/* 374 */       URL fileURL = cl.getResource(fileName);
/* 375 */       if (fileURL == null)
/* 376 */         throw new FileNotFoundException("File \"" + fileName + "\" not found."); 
/* 378 */       fileName = fileURL.toExternalForm();
/* 379 */       if (!this.this$0.includedFiles.add(fileName))
/* 381 */         throw new CircularIncludeException(fileName); 
/* 384 */       DigesterRuleParser includedSet = new DigesterRuleParser(this.this$0.targetDigester, this.this$0.patternStack, this.this$0.includedFiles);
/* 386 */       includedSet.setDigesterRulesDTD(this.this$0.getDigesterRulesDTD());
/* 387 */       Digester digester = new Digester();
/* 388 */       digester.addRuleSet((RuleSet)includedSet);
/* 389 */       digester.push(this.this$0);
/* 390 */       digester.parse(fileName);
/* 391 */       this.this$0.includedFiles.remove(fileName);
/*     */     }
/*     */     
/*     */     private void includeProgrammaticRules(String className) throws ClassNotFoundException, ClassCastException, InstantiationException, IllegalAccessException {
/* 406 */       Class cls = Class.forName(className);
/* 407 */       DigesterRulesSource rulesSource = (DigesterRulesSource)cls.newInstance();
/* 410 */       Rules digesterRules = this.this$0.targetDigester.getRules();
/* 411 */       Rules prefixWrapper = new DigesterRuleParser.RulesPrefixAdapter(this.this$0, this.this$0.patternStack.toString(), digesterRules);
/* 414 */       this.this$0.targetDigester.setRules(prefixWrapper);
/*     */       try {
/* 416 */         rulesSource.getRules(this.this$0.targetDigester);
/*     */       } finally {
/* 419 */         this.this$0.targetDigester.setRules(digesterRules);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private class RulesPrefixAdapter implements Rules {
/*     */     private Rules delegate;
/*     */     
/*     */     private String prefix;
/*     */     
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     public RulesPrefixAdapter(DigesterRuleParser this$0, String patternPrefix, Rules rules) {
/* 441 */       this.this$0 = this$0;
/* 442 */       this.prefix = patternPrefix;
/* 443 */       this.delegate = rules;
/*     */     }
/*     */     
/*     */     public void add(String pattern, Rule rule) {
/* 451 */       StringBuffer buffer = new StringBuffer();
/* 452 */       buffer.append(this.prefix);
/* 453 */       if (!pattern.startsWith("/"))
/* 454 */         buffer.append('/'); 
/* 456 */       buffer.append(pattern);
/* 457 */       this.delegate.add(buffer.toString(), rule);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 464 */       this.delegate.clear();
/*     */     }
/*     */     
/*     */     public Digester getDigester() {
/* 471 */       return this.delegate.getDigester();
/*     */     }
/*     */     
/*     */     public String getNamespaceURI() {
/* 478 */       return this.delegate.getNamespaceURI();
/*     */     }
/*     */     
/*     */     public List match(String pattern) {
/* 485 */       return this.delegate.match(pattern);
/*     */     }
/*     */     
/*     */     public List match(String namespaceURI, String pattern) {
/* 492 */       return this.delegate.match(namespaceURI, pattern);
/*     */     }
/*     */     
/*     */     public List rules() {
/* 499 */       return this.delegate.rules();
/*     */     }
/*     */     
/*     */     public void setDigester(Digester digester) {
/* 506 */       this.delegate.setDigester(digester);
/*     */     }
/*     */     
/*     */     public void setNamespaceURI(String namespaceURI) {
/* 513 */       this.delegate.setNamespaceURI(namespaceURI);
/*     */     }
/*     */   }
/*     */   
/*     */   private class BeanPropertySetterRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     private BeanPropertySetterRuleFactory(DigesterRuleParser this$0) {
/* 526 */       DigesterRuleParser.this = DigesterRuleParser.this;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) throws Exception {
/*     */       BeanPropertySetterRule beanPropertySetterRule1;
/* 528 */       Rule beanPropertySetterRule = null;
/* 529 */       String propertyname = attributes.getValue("propertyname");
/* 531 */       if (propertyname == null) {
/* 533 */         beanPropertySetterRule1 = new BeanPropertySetterRule();
/*     */       } else {
/* 535 */         beanPropertySetterRule1 = new BeanPropertySetterRule(propertyname);
/*     */       } 
/* 538 */       return beanPropertySetterRule1;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class CallMethodRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected CallMethodRuleFactory(DigesterRuleParser this$0) {
/* 546 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/*     */       CallMethodRule callMethodRule1;
/* 548 */       Rule callMethodRule = null;
/* 549 */       String methodName = attributes.getValue("methodname");
/* 553 */       int targetOffset = 0;
/* 554 */       String targetOffsetStr = attributes.getValue("targetoffset");
/* 555 */       if (targetOffsetStr != null)
/* 556 */         targetOffset = Integer.parseInt(targetOffsetStr); 
/* 559 */       if (attributes.getValue("paramcount") == null) {
/* 561 */         callMethodRule1 = new CallMethodRule(targetOffset, methodName);
/*     */       } else {
/* 564 */         int paramCount = Integer.parseInt(attributes.getValue("paramcount"));
/* 566 */         String paramTypesAttr = attributes.getValue("paramtypes");
/* 567 */         if (paramTypesAttr == null || paramTypesAttr.length() == 0) {
/* 568 */           callMethodRule1 = new CallMethodRule(targetOffset, methodName, paramCount);
/*     */         } else {
/* 570 */           String[] paramTypes = getParamTypes(paramTypesAttr);
/* 571 */           callMethodRule1 = new CallMethodRule(targetOffset, methodName, paramCount, paramTypes);
/*     */         } 
/*     */       } 
/* 575 */       return callMethodRule1;
/*     */     }
/*     */     
/*     */     private String[] getParamTypes(String paramTypes) {
/*     */       String[] paramTypesArray;
/* 584 */       if (paramTypes != null) {
/* 585 */         ArrayList paramTypesList = new ArrayList();
/* 586 */         StringTokenizer tokens = new StringTokenizer(paramTypes, " \t\n\r,");
/* 588 */         while (tokens.hasMoreTokens())
/* 589 */           paramTypesList.add(tokens.nextToken()); 
/* 591 */         paramTypesArray = paramTypesList.<String>toArray(new String[0]);
/*     */       } else {
/* 593 */         paramTypesArray = new String[0];
/*     */       } 
/* 595 */       return paramTypesArray;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class CallParamRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected CallParamRuleFactory(DigesterRuleParser this$0) {
/* 602 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/*     */       CallParamRule callParamRule1;
/* 606 */       int paramIndex = Integer.parseInt(attributes.getValue("paramnumber"));
/* 607 */       String attributeName = attributes.getValue("attrname");
/* 608 */       String fromStack = attributes.getValue("from-stack");
/* 609 */       String stackIndex = attributes.getValue("stack-index");
/* 610 */       Rule callParamRule = null;
/* 612 */       if (attributeName == null) {
/* 613 */         if (stackIndex != null) {
/* 614 */           callParamRule1 = new CallParamRule(paramIndex, Integer.parseInt(stackIndex));
/* 616 */         } else if (fromStack != null) {
/* 617 */           callParamRule1 = new CallParamRule(paramIndex, Boolean.valueOf(fromStack).booleanValue());
/*     */         } else {
/* 620 */           callParamRule1 = new CallParamRule(paramIndex);
/*     */         } 
/* 623 */       } else if (fromStack == null) {
/* 624 */         callParamRule1 = new CallParamRule(paramIndex, attributeName);
/*     */       } else {
/* 627 */         throw new RuntimeException("Attributes from-stack and attrname cannot both be present.");
/*     */       } 
/* 631 */       return callParamRule1;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class ObjectParamRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected ObjectParamRuleFactory(DigesterRuleParser this$0) {
/* 638 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) throws Exception {
/*     */       ObjectParamRule objectParamRule1;
/* 641 */       int paramIndex = Integer.parseInt(attributes.getValue("paramnumber"));
/* 642 */       String attributeName = attributes.getValue("attrname");
/* 643 */       String type = attributes.getValue("type");
/* 644 */       String value = attributes.getValue("value");
/* 646 */       Rule objectParamRule = null;
/* 649 */       if (type == null)
/* 650 */         throw new RuntimeException("Attribute 'type' is required."); 
/* 654 */       Object param = null;
/* 655 */       Class clazz = Class.forName(type);
/* 656 */       if (value == null) {
/* 657 */         param = clazz.newInstance();
/*     */       } else {
/* 659 */         param = ConvertUtils.convert(value, clazz);
/*     */       } 
/* 662 */       if (attributeName == null) {
/* 663 */         objectParamRule1 = new ObjectParamRule(paramIndex, param);
/*     */       } else {
/* 665 */         objectParamRule1 = new ObjectParamRule(paramIndex, attributeName, param);
/*     */       } 
/* 667 */       return objectParamRule1;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class NodeCreateRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected NodeCreateRuleFactory(DigesterRuleParser this$0) {
/* 674 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) throws Exception {
/* 678 */       String nodeType = attributes.getValue("type");
/* 679 */       if (nodeType == null || "".equals(nodeType))
/* 682 */         return new NodeCreateRule(); 
/* 683 */       if ("element".equals(nodeType))
/* 685 */         return new NodeCreateRule(1); 
/* 686 */       if ("fragment".equals(nodeType))
/* 688 */         return new NodeCreateRule(11); 
/* 691 */       throw new RuntimeException("Unrecognized node type: " + nodeType + ".  This attribute is optional or can have a value of element|fragment.");
/*     */     }
/*     */   }
/*     */   
/*     */   protected class FactoryCreateRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected FactoryCreateRuleFactory(DigesterRuleParser this$0) {
/* 702 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 704 */       String className = attributes.getValue("classname");
/* 705 */       String attrName = attributes.getValue("attrname");
/* 706 */       boolean ignoreExceptions = "true".equalsIgnoreCase(attributes.getValue("ignore-exceptions"));
/* 708 */       return (attrName == null || attrName.length() == 0) ? new FactoryCreateRule(className, ignoreExceptions) : new FactoryCreateRule(className, attrName, ignoreExceptions);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class ObjectCreateRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected ObjectCreateRuleFactory(DigesterRuleParser this$0) {
/* 717 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 719 */       String className = attributes.getValue("classname");
/* 720 */       String attrName = attributes.getValue("attrname");
/* 721 */       return (attrName == null || attrName.length() == 0) ? new ObjectCreateRule(className) : new ObjectCreateRule(className, attrName);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetPropertiesRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetPropertiesRuleFactory(DigesterRuleParser this$0) {
/* 730 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 732 */       return new SetPropertiesRule();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetPropertyRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetPropertyRuleFactory(DigesterRuleParser this$0) {
/* 739 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 741 */       String name = attributes.getValue("name");
/* 742 */       String value = attributes.getValue("value");
/* 743 */       return new SetPropertyRule(name, value);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetNestedPropertiesRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetNestedPropertiesRuleFactory(DigesterRuleParser this$0) {
/* 750 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 752 */       boolean allowUnknownChildElements = "true".equalsIgnoreCase(attributes.getValue("allow-unknown-child-elements"));
/* 754 */       SetNestedPropertiesRule snpr = new SetNestedPropertiesRule();
/* 755 */       snpr.setAllowUnknownChildElements(allowUnknownChildElements);
/* 756 */       return snpr;
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetTopRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetTopRuleFactory(DigesterRuleParser this$0) {
/* 763 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 765 */       String methodName = attributes.getValue("methodname");
/* 766 */       String paramType = attributes.getValue("paramtype");
/* 767 */       return (paramType == null || paramType.length() == 0) ? new SetTopRule(methodName) : new SetTopRule(methodName, paramType);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetNextRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetNextRuleFactory(DigesterRuleParser this$0) {
/* 776 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 778 */       String methodName = attributes.getValue("methodname");
/* 779 */       String paramType = attributes.getValue("paramtype");
/* 780 */       return (paramType == null || paramType.length() == 0) ? new SetNextRule(methodName) : new SetNextRule(methodName, paramType);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetRootRuleFactory extends AbstractObjectCreationFactory {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     protected SetRootRuleFactory(DigesterRuleParser this$0) {
/* 789 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public Object createObject(Attributes attributes) {
/* 791 */       String methodName = attributes.getValue("methodname");
/* 792 */       String paramType = attributes.getValue("paramtype");
/* 793 */       return (paramType == null || paramType.length() == 0) ? new SetRootRule(methodName) : new SetRootRule(methodName, paramType);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetPropertiesAliasRule extends Rule {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     public SetPropertiesAliasRule(DigesterRuleParser this$0) {
/* 808 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void begin(Attributes attributes) {
/* 817 */       String attrName = attributes.getValue("attr-name");
/* 818 */       String propName = attributes.getValue("prop-name");
/* 820 */       SetPropertiesRule rule = (SetPropertiesRule)this.digester.peek();
/* 821 */       rule.addAlias(attrName, propName);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SetNestedPropertiesAliasRule extends Rule {
/*     */     private final DigesterRuleParser this$0;
/*     */     
/*     */     public SetNestedPropertiesAliasRule(DigesterRuleParser this$0) {
/* 834 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void begin(Attributes attributes) {
/* 843 */       String attrName = attributes.getValue("attr-name");
/* 844 */       String propName = attributes.getValue("prop-name");
/* 846 */       SetNestedPropertiesRule rule = (SetNestedPropertiesRule)this.digester.peek();
/* 847 */       rule.addAlias(attrName, propName);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\DigesterRuleParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */