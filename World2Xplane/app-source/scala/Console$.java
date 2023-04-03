/*     */ package scala;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.EOFException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.text.MessageFormat;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.DynamicVariable;
/*     */ 
/*     */ public final class Console$ {
/*     */   public static final Console$ MODULE$;
/*     */   
/*     */   private final String BLACK;
/*     */   
/*     */   private final String RED;
/*     */   
/*     */   private final String GREEN;
/*     */   
/*     */   private final String YELLOW;
/*     */   
/*     */   private final String BLUE;
/*     */   
/*     */   private final String MAGENTA;
/*     */   
/*     */   private final String CYAN;
/*     */   
/*     */   private final String WHITE;
/*     */   
/*     */   private final String BLACK_B;
/*     */   
/*     */   private final String RED_B;
/*     */   
/*     */   private final String GREEN_B;
/*     */   
/*     */   private final String YELLOW_B;
/*     */   
/*     */   private final String BLUE_B;
/*     */   
/*     */   private final String MAGENTA_B;
/*     */   
/*     */   private final String CYAN_B;
/*     */   
/*     */   private final String WHITE_B;
/*     */   
/*     */   private final String RESET;
/*     */   
/*     */   private final String BOLD;
/*     */   
/*     */   private final String UNDERLINED;
/*     */   
/*     */   private final String BLINK;
/*     */   
/*     */   private final String REVERSED;
/*     */   
/*     */   private final String INVISIBLE;
/*     */   
/*     */   private final DynamicVariable<PrintStream> outVar;
/*     */   
/*     */   private final DynamicVariable<PrintStream> errVar;
/*     */   
/*     */   private final DynamicVariable<BufferedReader> inVar;
/*     */   
/*     */   private Console$() {
/*  26 */     MODULE$ = this;
/*  75 */     this.outVar = new DynamicVariable(System.out);
/*  76 */     this.errVar = new DynamicVariable(System.err);
/*  77 */     this.inVar = new DynamicVariable(
/*  78 */         new BufferedReader(new InputStreamReader(System.in)));
/*     */   }
/*     */   
/*     */   public final String BLACK() {
/*     */     return "\033[30m";
/*     */   }
/*     */   
/*     */   public final String RED() {
/*     */     return "\033[31m";
/*     */   }
/*     */   
/*     */   public final String GREEN() {
/*     */     return "\033[32m";
/*     */   }
/*     */   
/*     */   public final String YELLOW() {
/*     */     return "\033[33m";
/*     */   }
/*     */   
/*     */   public final String BLUE() {
/*     */     return "\033[34m";
/*     */   }
/*     */   
/*     */   public final String MAGENTA() {
/*     */     return "\033[35m";
/*     */   }
/*     */   
/*     */   public final String CYAN() {
/*     */     return "\033[36m";
/*     */   }
/*     */   
/*     */   public final String WHITE() {
/*     */     return "\033[37m";
/*     */   }
/*     */   
/*     */   public final String BLACK_B() {
/*     */     return "\033[40m";
/*     */   }
/*     */   
/*     */   public final String RED_B() {
/*     */     return "\033[41m";
/*     */   }
/*     */   
/*     */   public final String GREEN_B() {
/*     */     return "\033[42m";
/*     */   }
/*     */   
/*     */   public final String YELLOW_B() {
/*     */     return "\033[43m";
/*     */   }
/*     */   
/*     */   public final String BLUE_B() {
/*     */     return "\033[44m";
/*     */   }
/*     */   
/*     */   public final String MAGENTA_B() {
/*     */     return "\033[45m";
/*     */   }
/*     */   
/*     */   public final String CYAN_B() {
/*     */     return "\033[46m";
/*     */   }
/*     */   
/*     */   public final String WHITE_B() {
/*     */     return "\033[47m";
/*     */   }
/*     */   
/*     */   public final String RESET() {
/*     */     return "\033[0m";
/*     */   }
/*     */   
/*     */   public final String BOLD() {
/*     */     return "\033[1m";
/*     */   }
/*     */   
/*     */   public final String UNDERLINED() {
/*     */     return "\033[4m";
/*     */   }
/*     */   
/*     */   public final String BLINK() {
/*     */     return "\033[5m";
/*     */   }
/*     */   
/*     */   public final String REVERSED() {
/*     */     return "\033[7m";
/*     */   }
/*     */   
/*     */   public final String INVISIBLE() {
/*     */     return "\033[8m";
/*     */   }
/*     */   
/*     */   private DynamicVariable<PrintStream> outVar() {
/*     */     return this.outVar;
/*     */   }
/*     */   
/*     */   private DynamicVariable<PrintStream> errVar() {
/*     */     return this.errVar;
/*     */   }
/*     */   
/*     */   private DynamicVariable<BufferedReader> inVar() {
/*     */     return this.inVar;
/*     */   }
/*     */   
/*     */   public PrintStream out() {
/*  81 */     return (PrintStream)outVar().value();
/*     */   }
/*     */   
/*     */   public PrintStream err() {
/*  83 */     return (PrintStream)errVar().value();
/*     */   }
/*     */   
/*     */   public BufferedReader in() {
/*  85 */     return (BufferedReader)inVar().value();
/*     */   }
/*     */   
/*     */   public void setOut(PrintStream out) {
/*  91 */     outVar().value_$eq(out);
/*     */   }
/*     */   
/*     */   public <T> T withOut(PrintStream out, Function0 thunk) {
/* 107 */     return (T)outVar().withValue(out, thunk);
/*     */   }
/*     */   
/*     */   public void setOut(OutputStream out) {
/* 114 */     setOut(new PrintStream(out));
/*     */   }
/*     */   
/*     */   public <T> T withOut(OutputStream out, Function0 thunk) {
/* 126 */     PrintStream printStream = new PrintStream(out);
/* 126 */     return (T)outVar().withValue(printStream, thunk);
/*     */   }
/*     */   
/*     */   public void setErr(PrintStream err) {
/* 133 */     errVar().value_$eq(err);
/*     */   }
/*     */   
/*     */   public <T> T withErr(PrintStream err, Function0 thunk) {
/* 148 */     return (T)errVar().withValue(err, thunk);
/*     */   }
/*     */   
/*     */   public void setErr(OutputStream err) {
/* 155 */     setErr(new PrintStream(err));
/*     */   }
/*     */   
/*     */   public <T> T withErr(OutputStream err, Function0 thunk) {
/* 167 */     PrintStream printStream = new PrintStream(err);
/* 167 */     return (T)errVar().withValue(printStream, thunk);
/*     */   }
/*     */   
/*     */   public void setIn(Reader reader) {
/* 175 */     inVar().value_$eq(new BufferedReader(reader));
/*     */   }
/*     */   
/*     */   public <T> T withIn(Reader reader, Function0 thunk) {
/* 196 */     return (T)inVar().withValue(new BufferedReader(reader), thunk);
/*     */   }
/*     */   
/*     */   public void setIn(InputStream in) {
/* 203 */     setIn(new InputStreamReader(in));
/*     */   }
/*     */   
/*     */   public <T> T withIn(InputStream in, Function0 thunk) {
/* 216 */     InputStreamReader inputStreamReader = new InputStreamReader(in);
/* 216 */     return (T)inVar().withValue(new BufferedReader(inputStreamReader), thunk);
/*     */   }
/*     */   
/*     */   public void print(Object obj) {
/* 223 */     out().print((obj == null) ? "null" : obj.toString());
/*     */   }
/*     */   
/*     */   public void flush() {
/* 230 */     out().flush();
/*     */   }
/*     */   
/*     */   public void println() {
/* 234 */     out().println();
/*     */   }
/*     */   
/*     */   public void println(Object x) {
/* 240 */     out().println(x);
/*     */   }
/*     */   
/*     */   public void printf(String text, Seq args) {
/* 253 */     Predef$ predef$ = Predef$.MODULE$;
/* 253 */     out().print((new StringOps(text)).format(args));
/*     */   }
/*     */   
/*     */   public String readLine() {
/* 260 */     return in().readLine();
/*     */   }
/*     */   
/*     */   public String readLine(String text, Seq<Object> args) {
/* 270 */     printf(text, args);
/* 271 */     return readLine();
/*     */   }
/*     */   
/*     */   public boolean readBoolean() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual readLine : ()Ljava/lang/String;
/*     */     //   4: astore_1
/*     */     //   5: aload_1
/*     */     //   6: ifnonnull -> 19
/*     */     //   9: new java/io/EOFException
/*     */     //   12: dup
/*     */     //   13: ldc 'Console has reached end of input'
/*     */     //   15: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   18: athrow
/*     */     //   19: aload_1
/*     */     //   20: invokevirtual toLowerCase : ()Ljava/lang/String;
/*     */     //   23: astore_2
/*     */     //   24: ldc 'true'
/*     */     //   26: dup
/*     */     //   27: ifnonnull -> 38
/*     */     //   30: pop
/*     */     //   31: aload_2
/*     */     //   32: ifnull -> 45
/*     */     //   35: goto -> 50
/*     */     //   38: aload_2
/*     */     //   39: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   42: ifeq -> 50
/*     */     //   45: iconst_1
/*     */     //   46: istore_3
/*     */     //   47: goto -> 130
/*     */     //   50: ldc 't'
/*     */     //   52: dup
/*     */     //   53: ifnonnull -> 64
/*     */     //   56: pop
/*     */     //   57: aload_2
/*     */     //   58: ifnull -> 71
/*     */     //   61: goto -> 76
/*     */     //   64: aload_2
/*     */     //   65: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   68: ifeq -> 76
/*     */     //   71: iconst_1
/*     */     //   72: istore_3
/*     */     //   73: goto -> 130
/*     */     //   76: ldc 'yes'
/*     */     //   78: dup
/*     */     //   79: ifnonnull -> 90
/*     */     //   82: pop
/*     */     //   83: aload_2
/*     */     //   84: ifnull -> 97
/*     */     //   87: goto -> 102
/*     */     //   90: aload_2
/*     */     //   91: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   94: ifeq -> 102
/*     */     //   97: iconst_1
/*     */     //   98: istore_3
/*     */     //   99: goto -> 130
/*     */     //   102: ldc 'y'
/*     */     //   104: dup
/*     */     //   105: ifnonnull -> 116
/*     */     //   108: pop
/*     */     //   109: aload_2
/*     */     //   110: ifnull -> 123
/*     */     //   113: goto -> 128
/*     */     //   116: aload_2
/*     */     //   117: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   120: ifeq -> 128
/*     */     //   123: iconst_1
/*     */     //   124: istore_3
/*     */     //   125: goto -> 130
/*     */     //   128: iconst_0
/*     */     //   129: istore_3
/*     */     //   130: iload_3
/*     */     //   131: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #281	-> 0
/*     */     //   #282	-> 5
/*     */     //   #283	-> 9
/*     */     //   #285	-> 19
/*     */     //   #286	-> 24
/*     */     //   #287	-> 50
/*     */     //   #288	-> 76
/*     */     //   #289	-> 102
/*     */     //   #290	-> 128
/*     */     //   #285	-> 130
/*     */     //   #280	-> 131
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	132	0	this	Lscala/Console$;
/*     */     //   5	127	1	s	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public byte readByte() {
/* 302 */     String s = readLine();
/* 303 */     if (s == null)
/* 304 */       throw new EOFException("Console has reached end of input"); 
/* 306 */     Predef$ predef$ = Predef$.MODULE$;
/* 306 */     return (new StringOps(s)).toByte();
/*     */   }
/*     */   
/*     */   public short readShort() {
/* 317 */     String s = readLine();
/* 318 */     if (s == null)
/* 319 */       throw new EOFException("Console has reached end of input"); 
/* 321 */     Predef$ predef$ = Predef$.MODULE$;
/* 321 */     return (new StringOps(s)).toShort();
/*     */   }
/*     */   
/*     */   public char readChar() {
/* 332 */     String s = readLine();
/* 333 */     if (s == null)
/* 334 */       throw new EOFException("Console has reached end of input"); 
/* 336 */     return s.charAt(0);
/*     */   }
/*     */   
/*     */   public int readInt() {
/* 347 */     String s = readLine();
/* 348 */     if (s == null)
/* 349 */       throw new EOFException("Console has reached end of input"); 
/* 351 */     Predef$ predef$ = Predef$.MODULE$;
/* 351 */     return (new StringOps(s)).toInt();
/*     */   }
/*     */   
/*     */   public long readLong() {
/* 362 */     String s = readLine();
/* 363 */     if (s == null)
/* 364 */       throw new EOFException("Console has reached end of input"); 
/* 366 */     Predef$ predef$ = Predef$.MODULE$;
/* 366 */     return (new StringOps(s)).toLong();
/*     */   }
/*     */   
/*     */   public float readFloat() {
/* 377 */     String s = readLine();
/* 378 */     if (s == null)
/* 379 */       throw new EOFException("Console has reached end of input"); 
/* 381 */     Predef$ predef$ = Predef$.MODULE$;
/* 381 */     return (new StringOps(s)).toFloat();
/*     */   }
/*     */   
/*     */   public double readDouble() {
/* 392 */     String s = readLine();
/* 393 */     if (s == null)
/* 394 */       throw new EOFException("Console has reached end of input"); 
/* 396 */     Predef$ predef$ = Predef$.MODULE$;
/* 396 */     return (new StringOps(s)).toDouble();
/*     */   }
/*     */   
/*     */   public List<Object> readf(String format) {
/* 409 */     String s = readLine();
/* 410 */     if (s == null)
/* 411 */       throw new EOFException("Console has reached end of input"); 
/* 413 */     return textComponents((new MessageFormat(format)).parse(s));
/*     */   }
/*     */   
/*     */   public Object readf1(String format) {
/* 423 */     return readf(format).head();
/*     */   }
/*     */   
/*     */   public Tuple2<Object, Object> readf2(String format) {
/* 433 */     List<Object> res = readf(format);
/* 434 */     return new Tuple2<Object, Object>(res.head(), ((IterableLike)res.tail()).head());
/*     */   }
/*     */   
/*     */   public Tuple3<Object, Object, Object> readf3(String format) {
/* 445 */     List<Object> res = readf(format);
/* 446 */     return new Tuple3<Object, Object, Object>(res.head(), ((IterableLike)res.tail()).head(), ((IterableLike)((TraversableLike)res.tail()).tail()).head());
/*     */   }
/*     */   
/*     */   private List<Object> textComponents(Object[] a) {
/*     */     List<Object> list;
/* 450 */     int i = a.length - 1;
/* 451 */     Nil$ nil$ = Nil$.MODULE$;
/* 452 */     while (i >= 0) {
/* 453 */       Object object2, object1 = a[i];
/* 454 */       if (object1 instanceof java.lang.Boolean) {
/* 454 */         java.lang.Boolean bool = (java.lang.Boolean)object1;
/* 454 */         object2 = BoxesRunTime.boxToBoolean(bool.booleanValue());
/* 455 */       } else if (object1 instanceof java.lang.Byte) {
/* 455 */         java.lang.Byte byte_ = (java.lang.Byte)object1;
/* 455 */         object2 = BoxesRunTime.boxToByte(byte_.byteValue());
/* 456 */       } else if (object1 instanceof java.lang.Short) {
/* 456 */         java.lang.Short short_ = (java.lang.Short)object1;
/* 456 */         object2 = BoxesRunTime.boxToShort(short_.shortValue());
/* 457 */       } else if (object1 instanceof Character) {
/* 457 */         Character character = (Character)object1;
/* 457 */         object2 = BoxesRunTime.boxToCharacter(character.charValue());
/* 458 */       } else if (object1 instanceof Integer) {
/* 458 */         Integer integer = (Integer)object1;
/* 458 */         object2 = BoxesRunTime.boxToInteger(integer.intValue());
/* 459 */       } else if (object1 instanceof java.lang.Long) {
/* 459 */         java.lang.Long long_ = (java.lang.Long)object1;
/* 459 */         object2 = BoxesRunTime.boxToLong(long_.longValue());
/* 460 */       } else if (object1 instanceof java.lang.Float) {
/* 460 */         java.lang.Float float_ = (java.lang.Float)object1;
/* 460 */         object2 = BoxesRunTime.boxToFloat(float_.floatValue());
/* 461 */       } else if (object1 instanceof java.lang.Double) {
/* 461 */         java.lang.Double double_ = (java.lang.Double)object1;
/* 461 */         object2 = BoxesRunTime.boxToDouble(double_.doubleValue());
/*     */       } else {
/* 462 */         object2 = object1;
/*     */       } 
/*     */       list = nil$.$colon$colon(object2);
/* 464 */       i--;
/*     */     } 
/* 466 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Console$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */