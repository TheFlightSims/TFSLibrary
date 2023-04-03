/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.sys.package$;
/*    */ import scala.xml.parsing.TokenTests;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025a\001B\001\003\001%\021qaU2b]:,'O\003\002\004\t\005\031A\r\0363\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\007\001Qa\002\005\002\f\0315\t!!\003\002\016\005\t1Ak\\6f]N\004\"a\004\n\016\003AQ!!\005\003\002\017A\f'o]5oO&\0211\003\005\002\013)>\\WM\034+fgR\034\b\"B\013\001\t\0031\022A\002\037j]&$h\bF\001\030!\tY\001\001C\004\032\001\t\007IQ\001\016\002\013\025sEi\021%\026\003my\021\001\b\017\002\001!1a\004\001Q\001\016m\ta!\022(E\007\"\003\003b\002\021\001\001\004%\t!I\001\006i>\\WM\\\013\002EA\0211\005J\007\002\r%\021QE\002\002\004\023:$\bbB\024\001\001\004%\t\001K\001\ni>\\WM\\0%KF$\"!\013\027\021\005\rR\023BA\026\007\005\021)f.\033;\t\01752\023\021!a\001E\005\031\001\020J\031\t\r=\002\001\025)\003#\003\031!xn[3oA!I\021\007\001a\001\002\004%\tAM\001\006m\006dW/Z\013\002gA\021Ag\016\b\003GUJ!A\016\004\002\rA\023X\rZ3g\023\tA\024H\001\004TiJLgn\032\006\003m\031A\021b\017\001A\002\003\007I\021\001\037\002\023Y\fG.^3`I\025\fHCA\025>\021\035i#(!AA\002MBaa\020\001!B\023\031\024A\002<bYV,\007\005C\004B\001\001\007I\021\002\"\002\005%$X#A\"\021\007\021cuJ\004\002F\025:\021a)S\007\002\017*\021\001\nC\001\007yI|w\016\036 \n\003\035I!a\023\004\002\017A\f7m[1hK&\021QJ\024\002\t\023R,'/\031;pe*\0211J\002\t\003GAK!!\025\004\003\t\rC\027M\035\005\b'\002\001\r\021\"\003U\003\031IGo\030\023fcR\021\021&\026\005\b[I\013\t\0211\001D\021\0319\006\001)Q\005\007\006\031\021\016\036\021\t\017e\003\001\031!C\0055\006\t1-F\001P\021\035a\006\0011A\005\nu\013QaY0%KF$\"!\0130\t\0175Z\026\021!a\001\037\"1\001\r\001Q!\n=\013!a\031\021\t\013\t\004AQA2\002\027%t\027\016^*dC:tWM\035\013\003S\021DQ!Z1A\002M\n\021a\035\005\006O\002!)\001[\001\n]\026DH\017V8lK:$\022!\013\005\006U\002!)a[\001\fSNLE-\0328u\007\"\f'/F\001m!\t\031S.\003\002o\r\t9!i\\8mK\006t\007\"\0029\001\t\013A\027\001\0028fqRDQA\035\001\005\006M\f1!Y2d)\tIC\017C\003vc\002\007q*A\001e\021\0259\b\001\"\002y\003\021\t7mY*\025\005%J\b\"\002>w\001\004Y\030A\0013t!\r!EpT\005\003{:\0231aU3r\021\025y\b\001\"\002\"\003%\021X-\0313U_.,g\016\003\004\002\004\001!)!I\001\005]\006lW\r")
/*    */ public class Scanner extends Tokens implements TokenTests {
/*    */   private final char ENDCH;
/*    */   
/*    */   private int token;
/*    */   
/*    */   private String value;
/*    */   
/*    */   private Iterator<Object> it;
/*    */   
/*    */   private char c;
/*    */   
/*    */   public final boolean isSpace(char ch) {
/* 16 */     return TokenTests.class.isSpace(this, ch);
/*    */   }
/*    */   
/*    */   public final boolean isSpace(Seq cs) {
/* 16 */     return TokenTests.class.isSpace(this, cs);
/*    */   }
/*    */   
/*    */   public boolean isAlpha(char c) {
/* 16 */     return TokenTests.class.isAlpha(this, c);
/*    */   }
/*    */   
/*    */   public boolean isAlphaDigit(char c) {
/* 16 */     return TokenTests.class.isAlphaDigit(this, c);
/*    */   }
/*    */   
/*    */   public boolean isNameChar(char ch) {
/* 16 */     return TokenTests.class.isNameChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isNameStart(char ch) {
/* 16 */     return TokenTests.class.isNameStart(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isName(String s) {
/* 16 */     return TokenTests.class.isName(this, s);
/*    */   }
/*    */   
/*    */   public boolean isPubIDChar(char ch) {
/* 16 */     return TokenTests.class.isPubIDChar(this, ch);
/*    */   }
/*    */   
/*    */   public boolean isValidIANAEncoding(Seq ianaEncoding) {
/* 16 */     return TokenTests.class.isValidIANAEncoding(this, ianaEncoding);
/*    */   }
/*    */   
/*    */   public boolean checkSysID(String s) {
/* 16 */     return TokenTests.class.checkSysID(this, s);
/*    */   }
/*    */   
/*    */   public boolean checkPubID(String s) {
/* 16 */     return TokenTests.class.checkPubID(this, s);
/*    */   }
/*    */   
/*    */   public Scanner() {
/* 16 */     TokenTests.class.$init$(this);
/* 20 */     this.token = 10;
/* 23 */     this.it = null;
/* 24 */     this.c = 'z';
/*    */   }
/*    */   
/*    */   public final char ENDCH() {
/*    */     return Character.MIN_VALUE;
/*    */   }
/*    */   
/*    */   public int token() {
/*    */     return this.token;
/*    */   }
/*    */   
/*    */   public void token_$eq(int x$1) {
/*    */     this.token = x$1;
/*    */   }
/*    */   
/*    */   public String value() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   public void value_$eq(String x$1) {
/*    */     this.value = x$1;
/*    */   }
/*    */   
/*    */   private Iterator<Object> it() {
/*    */     return this.it;
/*    */   }
/*    */   
/*    */   private void it_$eq(Iterator<Object> x$1) {
/*    */     this.it = x$1;
/*    */   }
/*    */   
/*    */   private char c() {
/* 24 */     return this.c;
/*    */   }
/*    */   
/*    */   private void c_$eq(char x$1) {
/* 24 */     this.c = x$1;
/*    */   }
/*    */   
/*    */   public final void initScanner(String s) {
/* 28 */     value_$eq("");
/* 29 */     Predef$ predef$ = Predef$.MODULE$;
/* 29 */     it_$eq((new StringOps(s)).iterator());
/* 30 */     token_$eq(11);
/* 31 */     next();
/* 32 */     nextToken();
/*    */   }
/*    */   
/*    */   public final void nextToken() {
/* 37 */     if (token() != 10)
/* 37 */       token_$eq(readToken()); 
/*    */   }
/*    */   
/*    */   public final boolean isIdentChar() {
/* 41 */     return (('a' <= c() && c() <= 'z') || (
/* 42 */       'A' <= c() && c() <= 'Z'));
/*    */   }
/*    */   
/*    */   public final void next() {
/* 44 */     if (it().hasNext()) {
/* 44 */       c_$eq(BoxesRunTime.unboxToChar(it().next()));
/*    */     } else {
/* 44 */       c_$eq(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void acc(char d) {
/* 47 */     if (c() == d) {
/* 47 */       next();
/*    */       return;
/*    */     } 
/* 47 */     throw package$.MODULE$.error((new StringBuilder()).append("expected '").append(BoxesRunTime.boxToCharacter(d)).append("' found '").append(BoxesRunTime.boxToCharacter(c())).append("' !").toString());
/*    */   }
/*    */   
/*    */   public final void accS(Seq ds) {
/* 50 */     ds.foreach((Function1)new Scanner$$anonfun$accS$1(this));
/*    */   }
/*    */   
/*    */   public class Scanner$$anonfun$accS$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(char d) {
/* 50 */       this.$outer.acc(d);
/*    */     }
/*    */     
/*    */     public Scanner$$anonfun$accS$1(Scanner $outer) {}
/*    */   }
/*    */   
/*    */   public final int readToken() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_0
/*    */     //   2: invokespecial c : ()C
/*    */     //   5: invokevirtual isSpace : (C)Z
/*    */     //   8: ifeq -> 46
/*    */     //   11: aload_0
/*    */     //   12: aload_0
/*    */     //   13: invokespecial c : ()C
/*    */     //   16: invokevirtual isSpace : (C)Z
/*    */     //   19: ifeq -> 41
/*    */     //   22: aload_0
/*    */     //   23: aload_0
/*    */     //   24: invokespecial it : ()Lscala/collection/Iterator;
/*    */     //   27: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   32: invokestatic unboxToChar : (Ljava/lang/Object;)C
/*    */     //   35: invokespecial c_$eq : (C)V
/*    */     //   38: goto -> 11
/*    */     //   41: bipush #13
/*    */     //   43: goto -> 268
/*    */     //   46: aload_0
/*    */     //   47: invokespecial c : ()C
/*    */     //   50: istore_1
/*    */     //   51: iload_1
/*    */     //   52: lookupswitch default -> 136, 0 -> 186, 35 -> 191, 40 -> 263, 41 -> 255, 42 -> 238, 43 -> 229, 44 -> 247, 63 -> 220, 124 -> 211
/*    */     //   136: aload_0
/*    */     //   137: aload_0
/*    */     //   138: invokespecial c : ()C
/*    */     //   141: invokevirtual isNameStart : (C)Z
/*    */     //   144: ifeq -> 154
/*    */     //   147: aload_0
/*    */     //   148: invokevirtual name : ()I
/*    */     //   151: goto -> 268
/*    */     //   154: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*    */     //   157: new scala/collection/mutable/StringBuilder
/*    */     //   160: dup
/*    */     //   161: invokespecial <init> : ()V
/*    */     //   164: ldc 'unexpected character:'
/*    */     //   166: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   169: aload_0
/*    */     //   170: invokespecial c : ()C
/*    */     //   173: invokestatic boxToCharacter : (C)Ljava/lang/Character;
/*    */     //   176: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   179: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   182: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*    */     //   185: athrow
/*    */     //   186: bipush #10
/*    */     //   188: goto -> 268
/*    */     //   191: aload_0
/*    */     //   192: invokevirtual next : ()V
/*    */     //   195: aload_0
/*    */     //   196: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*    */     //   199: ldc 'PCDATA'
/*    */     //   201: invokevirtual wrapString : (Ljava/lang/String;)Lscala/collection/immutable/WrappedString;
/*    */     //   204: invokevirtual accS : (Lscala/collection/Seq;)V
/*    */     //   207: iconst_0
/*    */     //   208: goto -> 268
/*    */     //   211: aload_0
/*    */     //   212: invokevirtual next : ()V
/*    */     //   215: bipush #9
/*    */     //   217: goto -> 268
/*    */     //   220: aload_0
/*    */     //   221: invokevirtual next : ()V
/*    */     //   224: bipush #8
/*    */     //   226: goto -> 268
/*    */     //   229: aload_0
/*    */     //   230: invokevirtual next : ()V
/*    */     //   233: bipush #7
/*    */     //   235: goto -> 268
/*    */     //   238: aload_0
/*    */     //   239: invokevirtual next : ()V
/*    */     //   242: bipush #6
/*    */     //   244: goto -> 268
/*    */     //   247: aload_0
/*    */     //   248: invokevirtual next : ()V
/*    */     //   251: iconst_5
/*    */     //   252: goto -> 268
/*    */     //   255: aload_0
/*    */     //   256: invokevirtual next : ()V
/*    */     //   259: iconst_4
/*    */     //   260: goto -> 268
/*    */     //   263: aload_0
/*    */     //   264: invokevirtual next : ()V
/*    */     //   267: iconst_3
/*    */     //   268: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #53	-> 0
/*    */     //   #54	-> 11
/*    */     //   #55	-> 41
/*    */     //   #56	-> 46
/*    */     //   #67	-> 136
/*    */     //   #68	-> 154
/*    */     //   #65	-> 186
/*    */     //   #64	-> 191
/*    */     //   #63	-> 211
/*    */     //   #62	-> 220
/*    */     //   #61	-> 229
/*    */     //   #60	-> 238
/*    */     //   #59	-> 247
/*    */     //   #58	-> 255
/*    */     //   #57	-> 263
/*    */     //   #53	-> 268
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	269	0	this	Lscala/xml/dtd/Scanner;
/*    */   }
/*    */   
/*    */   public final int name() {
/* 72 */     StringBuilder sb = new StringBuilder();
/*    */     while (true) {
/* 73 */       sb.append(c());
/* 73 */       next();
/* 73 */       if (!isNameChar(c())) {
/* 74 */         value_$eq(sb.toString());
/* 75 */         return 1;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\Scanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */