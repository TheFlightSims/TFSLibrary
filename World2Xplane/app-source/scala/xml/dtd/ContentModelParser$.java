/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.regexp.Base;
/*     */ import scala.util.regexp.WordExp;
/*     */ 
/*     */ public final class ContentModelParser$ extends Scanner {
/*     */   public static final ContentModelParser$ MODULE$;
/*     */   
/*     */   private ContentModelParser$() {
/*  14 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public ContentModel parse(String s) {
/*  18 */     initScanner(s);
/*  18 */     return contentspec();
/*     */   }
/*     */   
/*     */   public void accept(int tok) {
/*  21 */     if (token() != tok)
/*  22 */       throw (tok == 6 && token() == 10) ? 
/*  23 */         scala.sys.package$.MODULE$.error("in DTDs, \nmixed content models must be like (#PCDATA|Name|Name|...)*") : 
/*     */         
/*  26 */         scala.sys.package$.MODULE$.error((
/*  27 */           new StringBuilder()).append("expected ").append(token2string(tok)).append(", got unexpected token:").append(token2string(token())).toString()); 
/*  29 */     nextToken();
/*     */   }
/*     */   
/*     */   public Base.RegExp maybeSuffix(Base.RegExp s) {
/*  33 */     int i = token();
/*  33 */     switch (i) {
/*     */       default:
/*     */       
/*     */       case 8:
/*  36 */         nextToken();
/*  36 */         (new Base.RegExp[2])[0] = (Base.RegExp)ContentModel$.MODULE$.Eps();
/*  36 */         (new Base.RegExp[2])[1] = s;
/*     */       case 7:
/*     */         nextToken();
/*     */         (new Base.RegExp[2])[0] = s;
/*     */         (new Base.RegExp[2])[1] = (Base.RegExp)new Base.Star((Base)ContentModel$.MODULE$, s);
/*     */       case 6:
/*     */         break;
/*     */     } 
/*     */     nextToken();
/*     */     return (Base.RegExp)new Base.Star((Base)ContentModel$.MODULE$, s);
/*     */   }
/*     */   
/*     */   public ContentModel contentspec() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual token : ()I
/*     */     //   4: istore_1
/*     */     //   5: iload_1
/*     */     //   6: lookupswitch default -> 32, 1 -> 208, 3 -> 65
/*     */     //   32: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*     */     //   35: new scala/collection/mutable/StringBuilder
/*     */     //   38: dup
/*     */     //   39: invokespecial <init> : ()V
/*     */     //   42: ldc 'unexpected token:'
/*     */     //   44: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   47: aload_0
/*     */     //   48: aload_0
/*     */     //   49: invokevirtual token : ()I
/*     */     //   52: invokevirtual token2string : (I)Ljava/lang/String;
/*     */     //   55: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   58: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   61: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   64: athrow
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual nextToken : ()V
/*     */     //   69: aload_0
/*     */     //   70: invokevirtual sOpt : ()V
/*     */     //   73: aload_0
/*     */     //   74: invokevirtual token : ()I
/*     */     //   77: iconst_0
/*     */     //   78: if_icmpeq -> 95
/*     */     //   81: new scala/xml/dtd/ELEMENTS
/*     */     //   84: dup
/*     */     //   85: aload_0
/*     */     //   86: invokevirtual regexp : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   89: invokespecial <init> : (Lscala/util/regexp/Base$RegExp;)V
/*     */     //   92: goto -> 275
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual nextToken : ()V
/*     */     //   99: aload_0
/*     */     //   100: invokevirtual token : ()I
/*     */     //   103: istore_2
/*     */     //   104: iload_2
/*     */     //   105: lookupswitch default -> 132, 4 -> 202, 9 -> 165
/*     */     //   132: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*     */     //   135: new scala/collection/mutable/StringBuilder
/*     */     //   138: dup
/*     */     //   139: invokespecial <init> : ()V
/*     */     //   142: ldc 'unexpected token:'
/*     */     //   144: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   147: aload_0
/*     */     //   148: aload_0
/*     */     //   149: invokevirtual token : ()I
/*     */     //   152: invokevirtual token2string : (I)Ljava/lang/String;
/*     */     //   155: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   158: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   161: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   164: athrow
/*     */     //   165: new scala/xml/dtd/MIXED
/*     */     //   168: dup
/*     */     //   169: aload_0
/*     */     //   170: getstatic scala/xml/dtd/ContentModel$.MODULE$ : Lscala/xml/dtd/ContentModel$;
/*     */     //   173: invokevirtual Eps : ()Lscala/util/regexp/Base$Eps$;
/*     */     //   176: invokevirtual choiceRest : (Lscala/util/regexp/Base$RegExp;)Lscala/util/regexp/Base$Alt;
/*     */     //   179: invokespecial <init> : (Lscala/util/regexp/Base$RegExp;)V
/*     */     //   182: astore_3
/*     */     //   183: aload_0
/*     */     //   184: invokevirtual sOpt : ()V
/*     */     //   187: aload_0
/*     */     //   188: iconst_4
/*     */     //   189: invokevirtual accept : (I)V
/*     */     //   192: aload_0
/*     */     //   193: bipush #6
/*     */     //   195: invokevirtual accept : (I)V
/*     */     //   198: aload_3
/*     */     //   199: goto -> 275
/*     */     //   202: getstatic scala/xml/dtd/PCDATA$.MODULE$ : Lscala/xml/dtd/PCDATA$;
/*     */     //   205: goto -> 275
/*     */     //   208: aload_0
/*     */     //   209: invokevirtual value : ()Ljava/lang/String;
/*     */     //   212: astore #4
/*     */     //   214: ldc 'ANY'
/*     */     //   216: dup
/*     */     //   217: ifnonnull -> 229
/*     */     //   220: pop
/*     */     //   221: aload #4
/*     */     //   223: ifnull -> 237
/*     */     //   226: goto -> 245
/*     */     //   229: aload #4
/*     */     //   231: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   234: ifeq -> 245
/*     */     //   237: getstatic scala/xml/dtd/ANY$.MODULE$ : Lscala/xml/dtd/ANY$;
/*     */     //   240: astore #5
/*     */     //   242: goto -> 273
/*     */     //   245: ldc 'EMPTY'
/*     */     //   247: dup
/*     */     //   248: ifnonnull -> 260
/*     */     //   251: pop
/*     */     //   252: aload #4
/*     */     //   254: ifnull -> 268
/*     */     //   257: goto -> 276
/*     */     //   260: aload #4
/*     */     //   262: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   265: ifeq -> 276
/*     */     //   268: getstatic scala/xml/dtd/EMPTY$.MODULE$ : Lscala/xml/dtd/EMPTY$;
/*     */     //   271: astore #5
/*     */     //   273: aload #5
/*     */     //   275: areturn
/*     */     //   276: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*     */     //   279: new scala/collection/mutable/StringBuilder
/*     */     //   282: dup
/*     */     //   283: invokespecial <init> : ()V
/*     */     //   286: ldc 'expected ANY, EMPTY or '(' instead of '
/*     */     //   288: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   291: aload_0
/*     */     //   292: invokevirtual value : ()Ljava/lang/String;
/*     */     //   295: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   298: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   301: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */     //   304: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #42	-> 0
/*     */     //   #72	-> 32
/*     */     //   #51	-> 65
/*     */     //   #52	-> 69
/*     */     //   #53	-> 73
/*     */     //   #54	-> 81
/*     */     //   #56	-> 95
/*     */     //   #57	-> 99
/*     */     //   #67	-> 132
/*     */     //   #61	-> 165
/*     */     //   #62	-> 183
/*     */     //   #63	-> 187
/*     */     //   #64	-> 192
/*     */     //   #65	-> 198
/*     */     //   #59	-> 202
/*     */     //   #44	-> 208
/*     */     //   #45	-> 214
/*     */     //   #46	-> 245
/*     */     //   #44	-> 273
/*     */     //   #42	-> 275
/*     */     //   #47	-> 276
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	305	0	this	Lscala/xml/dtd/ContentModelParser$;
/*     */     //   183	16	3	res	Lscala/xml/dtd/MIXED;
/*     */   }
/*     */   
/*     */   public void sOpt() {
/*  75 */     if (token() == 13)
/*  75 */       nextToken(); 
/*     */   }
/*     */   
/*     */   public Base.RegExp regexp() {
/*     */     Base.RegExp q;
/*     */     Base.Alt alt;
/*  82 */     Base.RegExp p = particle();
/*  83 */     sOpt();
/*  84 */     int i = token();
/*  84 */     switch (i) {
/*     */       default:
/*  84 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*     */       case 5:
/*  87 */         q = seqRest(p);
/*  87 */         accept(4);
/*     */       case 9:
/*     */         alt = choiceRest(p);
/*     */         accept(4);
/*     */       case 4:
/*     */         break;
/*     */     } 
/*     */     nextToken();
/*     */     return maybeSuffix(p);
/*     */   }
/*     */   
/*     */   public Base.RegExp seqRest(Base.RegExp p) {
/*  93 */     (new Base.RegExp[1])[0] = p;
/*  93 */     List k = scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Base.RegExp[1]));
/*  94 */     while (token() == 5) {
/*  95 */       nextToken();
/*  96 */       sOpt();
/*  97 */       Base.RegExp regExp = particle();
/*  97 */       k = k.$colon$colon(regExp);
/*  98 */       sOpt();
/*     */     } 
/* 100 */     return ContentModel$.MODULE$.Sequ().apply((Seq)k.reverse());
/*     */   }
/*     */   
/*     */   public Base.Alt choiceRest(Base.RegExp p) {
/* 105 */     (new Base.RegExp[1])[0] = p;
/* 105 */     List k = scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Base.RegExp[1]));
/* 106 */     while (token() == 9) {
/* 107 */       nextToken();
/* 108 */       sOpt();
/* 109 */       Base.RegExp regExp = particle();
/* 109 */       k = k.$colon$colon(regExp);
/* 110 */       sOpt();
/*     */     } 
/* 112 */     return ContentModel$.MODULE$.Alt().apply((Seq)k.reverse());
/*     */   }
/*     */   
/*     */   public Base.RegExp particle() {
/*     */     WordExp.Letter a;
/* 117 */     int i = token();
/* 117 */     switch (i) {
/*     */       default:
/* 120 */         throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("expected '(' or Name, got:").append(token2string(token())).toString());
/*     */       case 1:
/*     */         a = new WordExp.Letter(ContentModel$.MODULE$, new ContentModel.ElemName(value()));
/*     */         nextToken();
/*     */       case 3:
/*     */         break;
/*     */     } 
/*     */     nextToken();
/*     */     sOpt();
/*     */     return regexp();
/*     */   }
/*     */   
/*     */   public WordExp.Letter atom() {
/* 124 */     int i = token();
/* 124 */     switch (i) {
/*     */       default:
/* 126 */         throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("expected Name, got:").append(token2string(token())).toString());
/*     */       case 1:
/*     */         break;
/*     */     } 
/*     */     WordExp.Letter a = new WordExp.Letter(ContentModel$.MODULE$, new ContentModel.ElemName(value()));
/*     */     nextToken();
/*     */     return a;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ContentModelParser$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */