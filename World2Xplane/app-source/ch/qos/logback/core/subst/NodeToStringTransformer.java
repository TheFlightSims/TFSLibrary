/*     */ package ch.qos.logback.core.subst;
/*     */ 
/*     */ import ch.qos.logback.core.spi.PropertyContainer;
/*     */ import ch.qos.logback.core.spi.ScanException;
/*     */ import ch.qos.logback.core.util.OptionHelper;
/*     */ 
/*     */ public class NodeToStringTransformer {
/*     */   final Node node;
/*     */   
/*     */   final PropertyContainer propertyContainer0;
/*     */   
/*     */   final PropertyContainer propertyContainer1;
/*     */   
/*     */   public NodeToStringTransformer(Node node, PropertyContainer propertyContainer0, PropertyContainer propertyContainer1) {
/*  33 */     this.node = node;
/*  34 */     this.propertyContainer0 = propertyContainer0;
/*  35 */     this.propertyContainer1 = propertyContainer1;
/*     */   }
/*     */   
/*     */   public NodeToStringTransformer(Node node, PropertyContainer propertyContainer0) {
/*  39 */     this(node, propertyContainer0, null);
/*     */   }
/*     */   
/*     */   public static String substituteVariable(String input, PropertyContainer pc0, PropertyContainer pc1) throws ScanException {
/*  43 */     Tokenizer tokenizer = new Tokenizer(input);
/*  44 */     Parser parser = new Parser(tokenizer.tokenize());
/*  45 */     Node node = parser.parse();
/*  46 */     NodeToStringTransformer nodeToStringTransformer = new NodeToStringTransformer(node, pc0, pc1);
/*  47 */     return nodeToStringTransformer.transform();
/*     */   }
/*     */   
/*     */   public String transform() {
/*  51 */     StringBuilder stringBuilder = new StringBuilder();
/*  52 */     compileNode(this.node, stringBuilder);
/*  53 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private void compileNode(Node inputNode, StringBuilder stringBuilder) {
/*  57 */     Node n = inputNode;
/*  58 */     while (n != null) {
/*  59 */       switch (n.type) {
/*     */         case LITERAL:
/*  61 */           handleLiteral(n, stringBuilder);
/*     */           break;
/*     */         case VARIABLE:
/*  64 */           handleVariable(n, stringBuilder);
/*     */           break;
/*     */       } 
/*  67 */       n = n.next;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleVariable(Node n, StringBuilder stringBuilder) {
/*  72 */     StringBuilder keyBuffer = new StringBuilder();
/*  73 */     Node payload = (Node)n.payload;
/*  74 */     compileNode(payload, keyBuffer);
/*  75 */     String key = keyBuffer.toString();
/*  76 */     String value = lookupKey(key);
/*  77 */     if (value != null) {
/*  78 */       stringBuilder.append(value);
/*     */       return;
/*     */     } 
/*  82 */     if (n.defaultPart == null) {
/*  83 */       stringBuilder.append(key + "_IS_UNDEFINED");
/*     */       return;
/*     */     } 
/*  87 */     Node defaultPart = (Node)n.defaultPart;
/*  88 */     StringBuilder defaultPartBuffer = new StringBuilder();
/*  89 */     compileNode(defaultPart, defaultPartBuffer);
/*  90 */     String defaultVal = defaultPartBuffer.toString();
/*  91 */     stringBuilder.append(defaultVal);
/*     */   }
/*     */   
/*     */   private String lookupKey(String key) {
/*  95 */     String value = this.propertyContainer0.getProperty(key);
/*  96 */     if (value != null)
/*  97 */       return value; 
/*  99 */     if (this.propertyContainer1 != null) {
/* 100 */       value = this.propertyContainer1.getProperty(key);
/* 101 */       if (value != null)
/* 102 */         return value; 
/*     */     } 
/* 105 */     value = OptionHelper.getSystemProperty(key, null);
/* 106 */     if (value != null)
/* 107 */       return value; 
/* 109 */     value = OptionHelper.getEnv(key);
/* 110 */     if (value != null)
/* 111 */       return value; 
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   private void handleLiteral(Node n, StringBuilder stringBuilder) {
/* 119 */     stringBuilder.append((String)n.payload);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\subst\NodeToStringTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */