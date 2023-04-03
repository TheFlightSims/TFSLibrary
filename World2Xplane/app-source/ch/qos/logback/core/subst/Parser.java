/*     */ package ch.qos.logback.core.subst;
/*     */ 
/*     */ import ch.qos.logback.core.CoreConstants;
/*     */ import ch.qos.logback.core.spi.ScanException;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Parser {
/*     */   final List<Token> tokenList;
/*     */   
/*  39 */   int pointer = 0;
/*     */   
/*     */   public Parser(List<Token> tokenList) {
/*  42 */     this.tokenList = tokenList;
/*     */   }
/*     */   
/*     */   public Node parse() throws ScanException {
/*  46 */     return E();
/*     */   }
/*     */   
/*     */   private Node E() throws ScanException {
/*  50 */     Node t = T();
/*  51 */     if (t == null)
/*  52 */       return null; 
/*  54 */     Node eOpt = Eopt();
/*  55 */     if (eOpt != null)
/*  56 */       t.append(eOpt); 
/*  58 */     return t;
/*     */   }
/*     */   
/*     */   private Node Eopt() throws ScanException {
/*  63 */     Token next = peekAtCurentToken();
/*  64 */     if (next == null)
/*  65 */       return null; 
/*  67 */     return E();
/*     */   }
/*     */   
/*     */   private Node T() throws ScanException {
/*     */     Node innerNode;
/*     */     Token right;
/*     */     Node curlyLeft, v;
/*  73 */     Token w, t = peekAtCurentToken();
/*  75 */     switch (t.type) {
/*     */       case LITERAL:
/*  77 */         advanceTokenPointer();
/*  78 */         return makeNewLiteralNode(t.payload);
/*     */       case CURLY_LEFT:
/*  80 */         advanceTokenPointer();
/*  81 */         innerNode = C();
/*  82 */         right = peekAtCurentToken();
/*  83 */         expectCurlyRight(right);
/*  84 */         advanceTokenPointer();
/*  85 */         curlyLeft = makeNewLiteralNode(CoreConstants.LEFT_ACCOLADE);
/*  86 */         curlyLeft.append(innerNode);
/*  87 */         curlyLeft.append(makeNewLiteralNode(CoreConstants.RIGHT_ACCOLADE));
/*  88 */         return curlyLeft;
/*     */       case START:
/*  90 */         advanceTokenPointer();
/*  91 */         v = V();
/*  92 */         w = peekAtCurentToken();
/*  93 */         expectCurlyRight(w);
/*  94 */         advanceTokenPointer();
/*  95 */         return v;
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   private Node makeNewLiteralNode(String s) {
/* 102 */     return new Node(Node.Type.LITERAL, s);
/*     */   }
/*     */   
/*     */   private Node V() throws ScanException {
/* 107 */     Node e = E();
/* 108 */     Node variable = new Node(Node.Type.VARIABLE, e);
/* 109 */     Token t = peekAtCurentToken();
/* 110 */     if (isDefaultToken(t)) {
/* 111 */       advanceTokenPointer();
/* 112 */       Node def = E();
/* 113 */       variable.defaultPart = def;
/*     */     } 
/* 115 */     return variable;
/*     */   }
/*     */   
/*     */   private Node C() throws ScanException {
/* 120 */     Node e0 = E();
/* 121 */     Token t = peekAtCurentToken();
/* 122 */     if (isDefaultToken(t)) {
/* 123 */       advanceTokenPointer();
/* 124 */       Node literal = makeNewLiteralNode(":-");
/* 125 */       e0.append(literal);
/* 126 */       Node e1 = E();
/* 127 */       e0.append(e1);
/*     */     } 
/* 129 */     return e0;
/*     */   }
/*     */   
/*     */   private boolean isDefaultToken(Token t) {
/* 133 */     return (t != null && t.type == Token.Type.DEFAULT);
/*     */   }
/*     */   
/*     */   void advanceTokenPointer() {
/* 137 */     this.pointer++;
/*     */   }
/*     */   
/*     */   void expectNotNull(Token t, String expected) {
/* 141 */     if (t == null)
/* 142 */       throw new IllegalArgumentException("All tokens consumed but was expecting \"" + expected + "\""); 
/*     */   }
/*     */   
/*     */   void expectCurlyRight(Token t) throws ScanException {
/* 148 */     expectNotNull(t, "}");
/* 149 */     if (t.type != Token.Type.CURLY_RIGHT)
/* 150 */       throw new ScanException("Expecting }"); 
/*     */   }
/*     */   
/*     */   Token peekAtCurentToken() {
/* 155 */     if (this.pointer < this.tokenList.size())
/* 156 */       return this.tokenList.get(this.pointer); 
/* 158 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\subst\Parser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */