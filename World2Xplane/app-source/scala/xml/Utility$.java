/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Enumeration;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.Set;
/*     */ import scala.collection.mutable.SetLike;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.CharRef;
/*     */ import scala.xml.parsing.TokenTests;
/*     */ 
/*     */ public final class Utility$ implements TokenTests {
/*     */   public static final Utility$ MODULE$;
/*     */   
/*     */   private final char SU;
/*     */   
/*     */   public final boolean isSpace(char ch) {
/*  21 */     return TokenTests.class.isSpace(this, ch);
/*     */   }
/*     */   
/*     */   public final boolean isSpace(Seq cs) {
/*  21 */     return TokenTests.class.isSpace(this, cs);
/*     */   }
/*     */   
/*     */   public boolean isAlpha(char c) {
/*  21 */     return TokenTests.class.isAlpha(this, c);
/*     */   }
/*     */   
/*     */   public boolean isAlphaDigit(char c) {
/*  21 */     return TokenTests.class.isAlphaDigit(this, c);
/*     */   }
/*     */   
/*     */   public boolean isNameChar(char ch) {
/*  21 */     return TokenTests.class.isNameChar(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isNameStart(char ch) {
/*  21 */     return TokenTests.class.isNameStart(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isName(String s) {
/*  21 */     return TokenTests.class.isName(this, s);
/*     */   }
/*     */   
/*     */   public boolean isPubIDChar(char ch) {
/*  21 */     return TokenTests.class.isPubIDChar(this, ch);
/*     */   }
/*     */   
/*     */   public boolean isValidIANAEncoding(Seq ianaEncoding) {
/*  21 */     return TokenTests.class.isValidIANAEncoding(this, ianaEncoding);
/*     */   }
/*     */   
/*     */   public boolean checkSysID(String s) {
/*  21 */     return TokenTests.class.checkSysID(this, s);
/*     */   }
/*     */   
/*     */   public boolean checkPubID(String s) {
/*  21 */     return TokenTests.class.checkPubID(this, s);
/*     */   }
/*     */   
/*     */   private Utility$() {
/*  21 */     MODULE$ = this;
/*  21 */     TokenTests.class.$init$(this);
/*     */   }
/*     */   
/*     */   public final char SU() {
/*  22 */     return '\032';
/*     */   }
/*     */   
/*     */   public String implicitSbToString(StringBuilder sb) {
/*  26 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String sbToString(Function1 f) {
/*  31 */     StringBuilder sb = new StringBuilder();
/*  32 */     f.apply(sb);
/*  33 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean isAtomAndNotText(Node x) {
/*  35 */     return (x.isAtom() && !(x instanceof Text));
/*     */   }
/*     */   
/*     */   public Node trim(Node x) {
/*  45 */     Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>> option = Elem$.MODULE$.unapplySeq(x);
/*  45 */     if (option.isEmpty())
/*  45 */       throw new MatchError(x); 
/*  45 */     return 
/*     */       
/*  47 */       Elem$.MODULE$.apply((String)((Tuple5)option.get())._1(), (String)((Tuple5)option.get())._2(), (MetaData)((Tuple5)option.get())._3(), (NamespaceBinding)((Tuple5)option.get())._4(), (Seq<Node>)((TraversableLike)((Tuple5)option.get())._5()).flatMap((Function1)new Utility$$anonfun$trim$1(), scala.collection.Seq$.MODULE$.canBuildFrom()));
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$trim$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x) {
/*  47 */       return Utility$.MODULE$.trimProper(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public Seq<Node> trimProper(Node x) {
/*     */     Node node;
/*  53 */     Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>> option = Elem$.MODULE$.unapplySeq(x);
/*  53 */     if (option.isEmpty()) {
/*  56 */       Option<String> option1 = Text$.MODULE$.unapply(x);
/*  56 */       if (option1.isEmpty()) {
/*  59 */         node = x;
/*     */       } else {
/*     */         Seq<Text> seq = (new TextBuffer()).append((Seq<Object>)scala.Predef$.MODULE$.wrapString((String)option1.get())).toText();
/*     */       } 
/*     */     } else {
/*     */       node = Elem$.MODULE$.apply((String)((Tuple5)option.get())._1(), (String)((Tuple5)option.get())._2(), (MetaData)((Tuple5)option.get())._3(), (NamespaceBinding)((Tuple5)option.get())._4(), (Seq<Node>)((TraversableLike)((Tuple5)option.get())._5()).flatMap((Function1)new Utility$$anonfun$trimProper$1(), scala.collection.Seq$.MODULE$.canBuildFrom()));
/*     */     } 
/*     */     return (Seq<Node>)node;
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$trimProper$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x) {
/*     */       return Utility$.MODULE$.trimProper(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public MetaData sort(MetaData md) {
/*  64 */     String key = md.key();
/*  65 */     MetaData smaller = sort(md.filter((Function1<MetaData, Object>)new Utility$$anonfun$3(key)));
/*  66 */     MetaData greater = sort(md.filter((Function1<MetaData, Object>)new Utility$$anonfun$4(key)));
/*  67 */     return (md == Null$.MODULE$ || md.next() == Null$.MODULE$) ? md : (MetaData)smaller.foldRight(md.copy(greater), (Function2)new Utility$$anonfun$sort$1());
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$3 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String key$1;
/*     */     
/*     */     public final boolean apply(MetaData m) {
/*     */       String str = m.key();
/*     */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */       return (new StringOps(str)).$less(this.key$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$3(String key$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$4 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String key$1;
/*     */     
/*     */     public final boolean apply(MetaData m) {
/*     */       String str = m.key();
/*     */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */       return (new StringOps(str)).$greater(this.key$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$4(String key$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sort$1 extends AbstractFunction2<MetaData, MetaData, MetaData> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final MetaData apply(MetaData x, MetaData xs) {
/*  67 */       return x.copy(xs);
/*     */     }
/*     */   }
/*     */   
/*     */   public Node sort(Node n) {
/*     */     Node node;
/*  72 */     Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>> option = Elem$.MODULE$.unapplySeq(n);
/*  72 */     if (option.isEmpty()) {
/*  75 */       node = n;
/*     */     } else {
/*     */       node = Elem$.MODULE$.apply((String)((Tuple5)option.get())._1(), (String)((Tuple5)option.get())._2(), sort((MetaData)((Tuple5)option.get())._3()), (NamespaceBinding)((Tuple5)option.get())._4(), (Seq<Node>)((TraversableLike)((Tuple5)option.get())._5()).map((Function1)new Utility$$anonfun$sort$2(), scala.collection.Seq$.MODULE$.canBuildFrom()));
/*     */     } 
/*     */     return node;
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sort$2 extends AbstractFunction1<Node, Node> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Node apply(Node n) {
/*     */       return Utility$.MODULE$.sort(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public final String escape(String text) {
/*  81 */     return sbToString((Function1<StringBuilder, BoxedUnit>)new Utility$$anonfun$escape$1(text));
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$escape$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String text$1;
/*     */     
/*     */     public final void apply(StringBuilder x$1) {
/*  81 */       Utility$.MODULE$.escape(this.text$1, x$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$escape$1(String text$1) {}
/*     */   }
/*     */   
/*     */   public final StringBuilder escape(String text, StringBuilder s) {
/* 108 */     int len = text.length();
/* 109 */     int pos = 0;
/* 110 */     while (pos < len) {
/* 111 */       char c = text.charAt(pos);
/* 111 */       switch (c) {
/*     */         default:
/* 119 */           if (c >= ' ');
/*     */         case '\t':
/*     */         
/*     */         case '\r':
/*     */         
/*     */         case '\n':
/*     */         
/*     */         case '"':
/*     */         
/*     */         case '&':
/*     */         
/*     */         case '>':
/*     */         
/*     */         case '<':
/*     */           break;
/*     */       } 
/*     */       s.append("&lt;");
/* 122 */       pos++;
/*     */     } 
/* 124 */     return s;
/*     */   }
/*     */   
/*     */   public final StringBuilder unescape(String ref, StringBuilder s) {
/* 134 */     char c = BoxesRunTime.unboxToChar(option1.get());
/* 134 */     scala.Predef$.less.colon.less less = scala.Predef$.MODULE$.conforms();
/*     */     Option option1, option2;
/* 134 */     return (option2 = (Option)((option1 = Utility.Escapes$.MODULE$.unescMap().get(ref)).isEmpty() ? scala.None$.MODULE$ : new Some(s.append(c)))).isEmpty() ? (StringBuilder)less.apply(null) : (StringBuilder)option2.get();
/*     */   }
/*     */   
/*     */   public Set<String> collectNamespaces(Seq nodes) {
/* 141 */     return (Set<String>)nodes.foldLeft(new HashSet(), (Function2)new Utility$$anonfun$collectNamespaces$1());
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$1 extends AbstractFunction2<HashSet<String>, Node, HashSet<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final HashSet<String> apply(HashSet<String> set, Node x) {
/* 141 */       Utility$.MODULE$.collectNamespaces(x, (Set<String>)set);
/* 141 */       return set;
/*     */     }
/*     */   }
/*     */   
/*     */   public void collectNamespaces(Node n, Set set) {
/* 147 */     if (n.doCollectNamespaces()) {
/* 148 */       set.$plus$eq(n.namespace());
/* 149 */       n.attributes().foreach((Function1)new Utility$$anonfun$collectNamespaces$2(n, set));
/* 154 */       n.child().foreach((Function1)new Utility$$anonfun$collectNamespaces$3(set));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$2 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$1;
/*     */     
/*     */     private final Set set$1;
/*     */     
/*     */     public Utility$$anonfun$collectNamespaces$2(Node n$1, Set set$1) {}
/*     */     
/*     */     public final Object apply(MetaData a) {
/*     */       BoxedUnit boxedUnit;
/*     */       if (a instanceof PrefixedAttribute) {
/*     */         SetLike setLike = this.set$1.$plus$eq(a.getNamespace(this.n$1));
/*     */       } else {
/*     */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */       return boxedUnit;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$3 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set set$1;
/*     */     
/*     */     public Utility$$anonfun$collectNamespaces$3(Set set$1) {}
/*     */     
/*     */     public final void apply(Node i) {
/* 155 */       Utility$.MODULE$.collectNamespaces(i, this.set$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public NamespaceBinding toXML$default$2() {
/* 181 */     return TopScope$.MODULE$;
/*     */   }
/*     */   
/*     */   public StringBuilder toXML$default$3() {
/* 182 */     return new StringBuilder();
/*     */   }
/*     */   
/*     */   public boolean toXML$default$4() {
/* 183 */     return false;
/*     */   }
/*     */   
/*     */   public boolean toXML$default$5() {
/* 184 */     return true;
/*     */   }
/*     */   
/*     */   public boolean toXML$default$6() {
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   public boolean toXML$default$7() {
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public StringBuilder toXML(Node x, NamespaceBinding pscope, StringBuilder sb, boolean stripComments, boolean decodeEntities, boolean preserveWhitespace, boolean minimizeTags) {
/* 188 */     return serialize(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags ? MinimizeMode$.MODULE$.Always() : MinimizeMode$.MODULE$.Never());
/*     */   }
/*     */   
/*     */   public NamespaceBinding serialize$default$2() {
/* 201 */     return TopScope$.MODULE$;
/*     */   }
/*     */   
/*     */   public StringBuilder serialize$default$3() {
/* 202 */     return new StringBuilder();
/*     */   }
/*     */   
/*     */   public boolean serialize$default$4() {
/* 203 */     return false;
/*     */   }
/*     */   
/*     */   public boolean serialize$default$5() {
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   public boolean serialize$default$6() {
/* 205 */     return false;
/*     */   }
/*     */   
/*     */   public Enumeration.Value serialize$default$7() {
/* 206 */     return MinimizeMode$.MODULE$.Default();
/*     */   }
/*     */   
/*     */   public StringBuilder serialize(Node x, NamespaceBinding pscope, StringBuilder sb, boolean stripComments, boolean decodeEntities, boolean preserveWhitespace, Enumeration.Value minimizeTags) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/xml/Comment
/*     */     //   4: ifeq -> 29
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/xml/Comment
/*     */     //   11: astore #8
/*     */     //   13: iload #4
/*     */     //   15: ifne -> 29
/*     */     //   18: aload #8
/*     */     //   20: aload_3
/*     */     //   21: invokevirtual buildString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*     */     //   24: astore #14
/*     */     //   26: goto -> 307
/*     */     //   29: aload_1
/*     */     //   30: instanceof scala/xml/SpecialNode
/*     */     //   33: ifeq -> 53
/*     */     //   36: aload_1
/*     */     //   37: checkcast scala/xml/SpecialNode
/*     */     //   40: astore #9
/*     */     //   42: aload #9
/*     */     //   44: aload_3
/*     */     //   45: invokevirtual buildString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*     */     //   48: astore #14
/*     */     //   50: goto -> 307
/*     */     //   53: aload_1
/*     */     //   54: instanceof scala/xml/Group
/*     */     //   57: ifeq -> 94
/*     */     //   60: aload_1
/*     */     //   61: checkcast scala/xml/Group
/*     */     //   64: astore #10
/*     */     //   66: aload #10
/*     */     //   68: invokevirtual nodes : ()Lscala/collection/Seq;
/*     */     //   71: new scala/xml/Utility$$anonfun$serialize$1
/*     */     //   74: dup
/*     */     //   75: aload_3
/*     */     //   76: aload #7
/*     */     //   78: aload #10
/*     */     //   80: invokespecial <init> : (Lscala/collection/mutable/StringBuilder;Lscala/Enumeration$Value;Lscala/xml/Group;)V
/*     */     //   83: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   88: aload_3
/*     */     //   89: astore #14
/*     */     //   91: goto -> 307
/*     */     //   94: aload_1
/*     */     //   95: instanceof scala/xml/Elem
/*     */     //   98: ifeq -> 310
/*     */     //   101: aload_1
/*     */     //   102: checkcast scala/xml/Elem
/*     */     //   105: astore #13
/*     */     //   107: aload_3
/*     */     //   108: bipush #60
/*     */     //   110: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*     */     //   113: pop
/*     */     //   114: aload #13
/*     */     //   116: aload_3
/*     */     //   117: invokevirtual nameToString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*     */     //   120: pop
/*     */     //   121: aload #13
/*     */     //   123: invokevirtual attributes : ()Lscala/xml/MetaData;
/*     */     //   126: ifnull -> 141
/*     */     //   129: aload #13
/*     */     //   131: invokevirtual attributes : ()Lscala/xml/MetaData;
/*     */     //   134: aload_3
/*     */     //   135: invokevirtual buildString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*     */     //   138: goto -> 144
/*     */     //   141: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   144: pop
/*     */     //   145: aload #13
/*     */     //   147: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*     */     //   150: aload_3
/*     */     //   151: aload_2
/*     */     //   152: invokevirtual buildString : (Lscala/collection/mutable/StringBuilder;Lscala/xml/NamespaceBinding;)V
/*     */     //   155: aload #13
/*     */     //   157: invokevirtual child : ()Lscala/collection/Seq;
/*     */     //   160: invokeinterface isEmpty : ()Z
/*     */     //   165: ifeq -> 248
/*     */     //   168: aload #7
/*     */     //   170: getstatic scala/xml/MinimizeMode$.MODULE$ : Lscala/xml/MinimizeMode$;
/*     */     //   173: invokevirtual Always : ()Lscala/Enumeration$Value;
/*     */     //   176: astore #11
/*     */     //   178: dup
/*     */     //   179: ifnonnull -> 191
/*     */     //   182: pop
/*     */     //   183: aload #11
/*     */     //   185: ifnull -> 238
/*     */     //   188: goto -> 199
/*     */     //   191: aload #11
/*     */     //   193: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   196: ifne -> 238
/*     */     //   199: aload #7
/*     */     //   201: getstatic scala/xml/MinimizeMode$.MODULE$ : Lscala/xml/MinimizeMode$;
/*     */     //   204: invokevirtual Default : ()Lscala/Enumeration$Value;
/*     */     //   207: astore #12
/*     */     //   209: dup
/*     */     //   210: ifnonnull -> 222
/*     */     //   213: pop
/*     */     //   214: aload #12
/*     */     //   216: ifnull -> 230
/*     */     //   219: goto -> 248
/*     */     //   222: aload #12
/*     */     //   224: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   227: ifeq -> 248
/*     */     //   230: aload #13
/*     */     //   232: invokevirtual minimizeEmpty : ()Z
/*     */     //   235: ifeq -> 248
/*     */     //   238: aload_3
/*     */     //   239: ldc_w '/>'
/*     */     //   242: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*     */     //   245: goto -> 305
/*     */     //   248: aload_3
/*     */     //   249: bipush #62
/*     */     //   251: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*     */     //   254: pop
/*     */     //   255: aload_0
/*     */     //   256: aload #13
/*     */     //   258: invokevirtual child : ()Lscala/collection/Seq;
/*     */     //   261: aload #13
/*     */     //   263: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*     */     //   266: aload_3
/*     */     //   267: iload #4
/*     */     //   269: aload_0
/*     */     //   270: invokevirtual sequenceToXML$default$5 : ()Z
/*     */     //   273: aload_0
/*     */     //   274: invokevirtual sequenceToXML$default$6 : ()Z
/*     */     //   277: aload_0
/*     */     //   278: invokevirtual sequenceToXML$default$7 : ()Lscala/Enumeration$Value;
/*     */     //   281: invokevirtual sequenceToXML : (Lscala/collection/Seq;Lscala/xml/NamespaceBinding;Lscala/collection/mutable/StringBuilder;ZZZLscala/Enumeration$Value;)V
/*     */     //   284: aload_3
/*     */     //   285: ldc_w '</'
/*     */     //   288: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*     */     //   291: pop
/*     */     //   292: aload #13
/*     */     //   294: aload_3
/*     */     //   295: invokevirtual nameToString : (Lscala/collection/mutable/StringBuilder;)Lscala/collection/mutable/StringBuilder;
/*     */     //   298: pop
/*     */     //   299: aload_3
/*     */     //   300: bipush #62
/*     */     //   302: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*     */     //   305: astore #14
/*     */     //   307: aload #14
/*     */     //   309: areturn
/*     */     //   310: new java/lang/IllegalArgumentException
/*     */     //   313: dup
/*     */     //   314: new scala/collection/mutable/StringBuilder
/*     */     //   317: dup
/*     */     //   318: invokespecial <init> : ()V
/*     */     //   321: ldc_w 'Don't know how to serialize a '
/*     */     //   324: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   327: aload_1
/*     */     //   328: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   331: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   334: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   337: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   340: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   343: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #209	-> 0
/*     */     //   #208	-> 0
/*     */     //   #210	-> 29
/*     */     //   #211	-> 53
/*     */     //   #212	-> 94
/*     */     //   #214	-> 107
/*     */     //   #215	-> 114
/*     */     //   #216	-> 121
/*     */     //   #217	-> 145
/*     */     //   #218	-> 155
/*     */     //   #219	-> 168
/*     */     //   #220	-> 199
/*     */     //   #223	-> 238
/*     */     //   #226	-> 248
/*     */     //   #227	-> 255
/*     */     //   #228	-> 284
/*     */     //   #229	-> 292
/*     */     //   #230	-> 299
/*     */     //   #212	-> 305
/*     */     //   #208	-> 307
/*     */     //   #232	-> 310
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	344	0	this	Lscala/xml/Utility$;
/*     */     //   0	344	1	x	Lscala/xml/Node;
/*     */     //   0	344	2	pscope	Lscala/xml/NamespaceBinding;
/*     */     //   0	344	3	sb	Lscala/collection/mutable/StringBuilder;
/*     */     //   0	344	4	stripComments	Z
/*     */     //   0	344	5	decodeEntities	Z
/*     */     //   0	344	6	preserveWhitespace	Z
/*     */     //   0	344	7	minimizeTags	Lscala/Enumeration$Value;
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$serialize$1 extends AbstractFunction1<Node, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder sb$2;
/*     */     
/*     */     private final Enumeration.Value minimizeTags$2;
/*     */     
/*     */     private final Group x4$1;
/*     */     
/*     */     public final StringBuilder apply(Node c) {
/* 211 */       NamespaceBinding x$9 = this.x4$1.scope();
/* 211 */       StringBuilder x$10 = this.sb$2;
/* 211 */       Enumeration.Value x$11 = this.minimizeTags$2;
/* 211 */       boolean x$12 = Utility$.MODULE$.serialize$default$4(), x$13 = Utility$.MODULE$.serialize$default$5(), x$14 = Utility$.MODULE$.serialize$default$6();
/* 211 */       return Utility$.MODULE$.serialize(c, x$9, x$10, x$12, x$13, x$14, x$11);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$serialize$1(StringBuilder sb$2, Enumeration.Value minimizeTags$2, Group x4$1) {}
/*     */   }
/*     */   
/*     */   public NamespaceBinding sequenceToXML$default$2() {
/* 238 */     return TopScope$.MODULE$;
/*     */   }
/*     */   
/*     */   public StringBuilder sequenceToXML$default$3() {
/* 239 */     return new StringBuilder();
/*     */   }
/*     */   
/*     */   public boolean sequenceToXML$default$4() {
/* 240 */     return false;
/*     */   }
/*     */   
/*     */   public boolean sequenceToXML$default$5() {
/* 241 */     return true;
/*     */   }
/*     */   
/*     */   public boolean sequenceToXML$default$6() {
/* 242 */     return false;
/*     */   }
/*     */   
/*     */   public Enumeration.Value sequenceToXML$default$7() {
/* 243 */     return MinimizeMode$.MODULE$.Default();
/*     */   }
/*     */   
/*     */   public void sequenceToXML(Seq children, NamespaceBinding pscope, StringBuilder sb, boolean stripComments, boolean decodeEntities, boolean preserveWhitespace, Enumeration.Value minimizeTags) {
/* 245 */     if (children.isEmpty())
/*     */       return; 
/* 246 */     if (children.forall((Function1)new Utility$$anonfun$sequenceToXML$1())) {
/* 247 */       Iterator it = children.iterator();
/* 248 */       Node f = (Node)it.next();
/* 249 */       serialize(f, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
/* 250 */       while (it.hasNext()) {
/* 251 */         Node x = (Node)it.next();
/* 252 */         sb.append(' ');
/* 253 */         serialize(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
/*     */       } 
/*     */     } else {
/* 256 */       children.foreach((Function1)new Utility$$anonfun$sequenceToXML$2(pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sequenceToXML$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x) {
/*     */       return Utility$.MODULE$.isAtomAndNotText(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sequenceToXML$2 extends AbstractFunction1<Node, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final NamespaceBinding pscope$1;
/*     */     
/*     */     private final StringBuilder sb$1;
/*     */     
/*     */     private final boolean stripComments$1;
/*     */     
/*     */     private final boolean decodeEntities$1;
/*     */     
/*     */     private final boolean preserveWhitespace$1;
/*     */     
/*     */     private final Enumeration.Value minimizeTags$1;
/*     */     
/*     */     public final StringBuilder apply(Node x$3) {
/* 256 */       return Utility$.MODULE$.serialize(x$3, this.pscope$1, this.sb$1, this.stripComments$1, this.decodeEntities$1, this.preserveWhitespace$1, this.minimizeTags$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$sequenceToXML$2(NamespaceBinding pscope$1, StringBuilder sb$1, boolean stripComments$1, boolean decodeEntities$1, boolean preserveWhitespace$1, Enumeration.Value minimizeTags$1) {}
/*     */   }
/*     */   
/*     */   public final Option<String> prefix(String name) {
/* 262 */     int i = name.indexOf(':');
/* 262 */     switch (i) {
/*     */       default:
/*     */       
/*     */       case -1:
/*     */         break;
/*     */     } 
/* 262 */     return 
/* 263 */       (Option<String>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public int hashCode(String pre, String label, int attribHashCode, int scpeHash, Seq children) {
/* 271 */     return scala.util.hashing.MurmurHash3$.MODULE$.orderedHash((TraversableOnce)((SeqLike)((SeqLike)children.$plus$colon(BoxesRunTime.boxToInteger(scpeHash), scala.collection.Seq$.MODULE$.canBuildFrom())).$plus$colon(BoxesRunTime.boxToInteger(attribHashCode), scala.collection.Seq$.MODULE$.canBuildFrom())).$plus$colon(label, scala.collection.Seq$.MODULE$.canBuildFrom()), scala.runtime.ScalaRunTime$.MODULE$.hash(pre));
/*     */   }
/*     */   
/*     */   public String appendQuoted(String s) {
/* 273 */     return sbToString((Function1<StringBuilder, BoxedUnit>)new Utility$$anonfun$appendQuoted$1(s));
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$appendQuoted$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String s$1;
/*     */     
/*     */     public final void apply(StringBuilder x$7) {
/* 273 */       Utility$.MODULE$.appendQuoted(this.s$1, x$7);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$appendQuoted$1(String s$1) {}
/*     */   }
/*     */   
/*     */   public StringBuilder appendQuoted(String s, StringBuilder sb) {
/* 280 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 280 */     char ch = (new StringOps(s)).contains(BoxesRunTime.boxToCharacter('"')) ? '\'' : '"';
/* 281 */     return sb.append(ch).append(s).append(ch);
/*     */   }
/*     */   
/*     */   public StringBuilder appendEscapedQuoted(String s, StringBuilder sb) {
/* 288 */     sb.append('"');
/* 289 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 289 */     IndexedSeqOptimized.class.foreach((IndexedSeqOptimized)new StringOps(s), (Function1)new Utility$$anonfun$appendEscapedQuoted$1(sb));
/* 293 */     return sb.append('"');
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$appendEscapedQuoted$1 extends AbstractFunction1<Object, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder sb$3;
/*     */     
/*     */     public final StringBuilder apply(char c) {
/*     */       switch (c) {
/*     */         default:
/*     */         
/*     */         case '"':
/*     */           break;
/*     */       } 
/*     */       this.sb$3.append('\\');
/*     */       return this.sb$3.append('"');
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$appendEscapedQuoted$1(StringBuilder sb$3) {}
/*     */   }
/*     */   
/*     */   public String getName(String s, int index) {
/* 299 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 299 */     String xs = (String)IndexedSeqOptimized.class.drop((IndexedSeqOptimized)new StringOps(s), index);
/* 300 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 300 */     if (TraversableOnce.class.nonEmpty((TraversableOnce)new StringOps(xs))) {
/* 300 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 300 */       char c = BoxesRunTime.unboxToChar(IndexedSeqOptimized.class.head((IndexedSeqOptimized)new StringOps(xs)));
/* 300 */       if (TokenTests.class.isNameStart(this, c))
/* 300 */         scala.Predef$ predef$3 = scala.Predef$.MODULE$; 
/*     */     } 
/* 301 */     return (index >= s.length()) ? null : "";
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$getName$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char ch) {
/*     */       return Utility$.MODULE$.isNameChar(ch);
/*     */     }
/*     */   }
/*     */   
/*     */   public String checkAttributeValue(String value) {
/* 310 */     int i = 0;
/* 311 */     while (i < value.length()) {
/*     */       String n;
/* 312 */       char c = value.charAt(i);
/* 312 */       switch (c) {
/*     */         case '&':
/* 316 */           n = getName(value, i + 1);
/* 317 */           if (n == null)
/* 318 */             return (new StringBuilder()).append("malformed entity reference in attribute value [").append(value).append("]").toString(); 
/* 319 */           if ((i = i + n.length() + 1) >= 
/* 320 */             value.length() || value.charAt(i) != ';')
/* 321 */             return (new StringBuilder()).append("malformed entity reference in attribute value [").append(value).append("]").toString(); 
/* 324 */           i++;
/*     */           break;
/*     */         case '<':
/*     */           return "< not allowed in attribute value";
/*     */       } 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public Seq<Node> parseAttributeValue(String value) {
/* 330 */     StringBuilder sb = new StringBuilder();
/* 331 */     StringBuilder rfb = null;
/* 332 */     NodeBuffer nb = new NodeBuffer();
/* 334 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 334 */     Iterator it = (new StringOps(value)).iterator();
/* 335 */     while (it.hasNext()) {
/*     */       BoxedUnit boxedUnit;
/* 336 */       CharRef c = new CharRef(BoxesRunTime.unboxToChar(it.next()));
/* 339 */       c.elem = BoxesRunTime.unboxToChar(it.next());
/* 341 */       c.elem = BoxesRunTime.unboxToChar(it.next());
/* 342 */       String theChar = parseCharRef((Function0<Object>)new Utility$$anonfun$1(c), (Function0<BoxedUnit>)new Utility$$anonfun$2(it, c), (Function1<String, BoxedUnit>)new Utility$$anonfun$6(), (Function1<String, BoxedUnit>)new Utility$$anonfun$7());
/* 346 */       if (rfb == null)
/* 346 */         rfb = new StringBuilder(); 
/* 347 */       rfb.append(c.elem);
/* 348 */       c.elem = BoxesRunTime.unboxToChar(it.next());
/* 349 */       while (c.elem != ';') {
/* 350 */         rfb.append(c.elem);
/* 351 */         c.elem = BoxesRunTime.unboxToChar(it.next());
/*     */       } 
/* 353 */       String ref = rfb.toString();
/* 354 */       rfb.clear();
/* 355 */       StringBuilder stringBuilder = unescape(ref, sb);
/* 356 */       if (stringBuilder == null) {
/* 357 */         if (sb.length() > 0) {
/* 358 */           nb.$plus$eq(Text$.MODULE$.apply(sb.toString()));
/* 359 */           sb.clear();
/*     */         } 
/* 361 */         ArrayBuffer arrayBuffer = nb.$plus$eq(new EntityRef(ref));
/*     */       } else {
/* 362 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/* 366 */       (c.elem == '&') ? ((c.elem == '#') ? sb.append(theChar) : boxedUnit) : sb.append(c.elem);
/*     */     } 
/* 369 */     Text x = Text$.MODULE$.apply(sb.toString());
/* 370 */     if (nb.length() == 0)
/* 371 */       return (Seq<Node>)x; 
/* 373 */     (sb.length() > 0) ? nb.$plus$eq(x) : BoxedUnit.UNIT;
/* 375 */     return (Seq<Node>)nb;
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final char apply() {
/*     */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/*     */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$1(CharRef c$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Iterator it$1;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final void apply() {
/*     */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$2(Iterator it$1, CharRef c$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$6 extends AbstractFunction1<String, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(String s) {
/*     */       throw new RuntimeException(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$7 extends AbstractFunction1<String, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(String s) {
/*     */       throw new RuntimeException(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String parseCharRef(Function0 ch, Function0 nextch, Function1 reportSyntaxError, Function1 reportTruncatedError) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface apply$mcC$sp : ()C
/*     */     //   6: bipush #120
/*     */     //   8: if_icmpne -> 25
/*     */     //   11: aload_2
/*     */     //   12: invokeinterface apply$mcV$sp : ()V
/*     */     //   17: iconst_1
/*     */     //   18: ifeq -> 25
/*     */     //   21: iconst_1
/*     */     //   22: goto -> 26
/*     */     //   25: iconst_0
/*     */     //   26: istore #6
/*     */     //   28: iload #6
/*     */     //   30: ifeq -> 38
/*     */     //   33: bipush #16
/*     */     //   35: goto -> 40
/*     */     //   38: bipush #10
/*     */     //   40: istore #9
/*     */     //   42: iconst_0
/*     */     //   43: istore #12
/*     */     //   45: aload_1
/*     */     //   46: invokeinterface apply$mcC$sp : ()C
/*     */     //   51: bipush #59
/*     */     //   53: if_icmpeq -> 410
/*     */     //   56: aload_1
/*     */     //   57: invokeinterface apply$mcC$sp : ()C
/*     */     //   62: istore #5
/*     */     //   64: iload #5
/*     */     //   66: lookupswitch default -> 260, 26 -> 303, 48 -> 368, 49 -> 368, 50 -> 368, 51 -> 368, 52 -> 368, 53 -> 368, 54 -> 368, 55 -> 368, 56 -> 368, 57 -> 368, 65 -> 316, 66 -> 316, 67 -> 316, 68 -> 316, 69 -> 316, 70 -> 316, 97 -> 316, 98 -> 316, 99 -> 316, 100 -> 316, 101 -> 316, 102 -> 316
/*     */     //   260: aload_3
/*     */     //   261: new scala/collection/mutable/StringBuilder
/*     */     //   264: dup
/*     */     //   265: invokespecial <init> : ()V
/*     */     //   268: ldc_w 'character ''
/*     */     //   271: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   274: aload_1
/*     */     //   275: invokeinterface apply$mcC$sp : ()C
/*     */     //   280: invokestatic boxToCharacter : (C)Ljava/lang/Character;
/*     */     //   283: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   286: ldc_w '' not allowed in char ref\\n'
/*     */     //   289: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   292: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   295: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   300: goto -> 400
/*     */     //   303: aload #4
/*     */     //   305: ldc_w ''
/*     */     //   308: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   313: goto -> 400
/*     */     //   316: iload #6
/*     */     //   318: ifeq -> 356
/*     */     //   321: iload #12
/*     */     //   323: iload #9
/*     */     //   325: imul
/*     */     //   326: getstatic scala/runtime/RichChar$.MODULE$ : Lscala/runtime/RichChar$;
/*     */     //   329: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   332: aload_1
/*     */     //   333: invokeinterface apply$mcC$sp : ()C
/*     */     //   338: istore #8
/*     */     //   340: astore #7
/*     */     //   342: iload #8
/*     */     //   344: invokevirtual asDigit$extension : (C)I
/*     */     //   347: iadd
/*     */     //   348: istore #12
/*     */     //   350: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   353: goto -> 400
/*     */     //   356: aload_3
/*     */     //   357: ldc_w 'hex char not allowed in decimal char ref\\nDid you mean to write &#x ?'
/*     */     //   360: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   365: goto -> 400
/*     */     //   368: iload #12
/*     */     //   370: iload #9
/*     */     //   372: imul
/*     */     //   373: getstatic scala/runtime/RichChar$.MODULE$ : Lscala/runtime/RichChar$;
/*     */     //   376: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   379: aload_1
/*     */     //   380: invokeinterface apply$mcC$sp : ()C
/*     */     //   385: istore #11
/*     */     //   387: astore #10
/*     */     //   389: iload #11
/*     */     //   391: invokevirtual asDigit$extension : (C)I
/*     */     //   394: iadd
/*     */     //   395: istore #12
/*     */     //   397: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   400: pop
/*     */     //   401: aload_2
/*     */     //   402: invokeinterface apply$mcV$sp : ()V
/*     */     //   407: goto -> 45
/*     */     //   410: new java/lang/String
/*     */     //   413: dup
/*     */     //   414: iconst_1
/*     */     //   415: newarray int
/*     */     //   417: dup
/*     */     //   418: iconst_0
/*     */     //   419: iload #12
/*     */     //   421: iastore
/*     */     //   422: iconst_0
/*     */     //   423: iconst_1
/*     */     //   424: invokespecial <init> : ([III)V
/*     */     //   427: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #386	-> 0
/*     */     //   #387	-> 28
/*     */     //   #388	-> 42
/*     */     //   #389	-> 45
/*     */     //   #390	-> 56
/*     */     //   #403	-> 260
/*     */     //   #401	-> 303
/*     */     //   #395	-> 316
/*     */     //   #399	-> 321
/*     */     //   #396	-> 356
/*     */     //   #392	-> 368
/*     */     //   #405	-> 401
/*     */     //   #407	-> 410
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	428	0	this	Lscala/xml/Utility$;
/*     */     //   0	428	1	ch	Lscala/Function0;
/*     */     //   0	428	2	nextch	Lscala/Function0;
/*     */     //   0	428	3	reportSyntaxError	Lscala/Function1;
/*     */     //   0	428	4	reportTruncatedError	Lscala/Function1;
/*     */     //   28	399	6	hex	Z
/*     */     //   42	385	9	base	I
/*     */     //   45	382	12	i	I
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Utility$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */