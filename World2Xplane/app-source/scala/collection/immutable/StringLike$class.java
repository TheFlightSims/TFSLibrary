/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.ScalaNumber;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichChar$;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.util.matching.Regex;
/*     */ 
/*     */ public abstract class StringLike$class {
/*     */   public static void $init$(StringLike $this) {}
/*     */   
/*     */   public static char apply(StringLike $this, int n) {
/*  53 */     return $this.toString().charAt(n);
/*     */   }
/*     */   
/*     */   public static int length(StringLike $this) {
/*  55 */     return $this.toString().length();
/*     */   }
/*     */   
/*     */   public static String mkString(StringLike $this) {
/*  57 */     return $this.toString();
/*     */   }
/*     */   
/*     */   public static Object slice(StringLike<Repr> $this, int from, int until) {
/*  60 */     Predef$ predef$1 = Predef$.MODULE$;
/*  60 */     int start = RichInt$.MODULE$.max$extension(from, 0);
/*  61 */     Predef$ predef$2 = Predef$.MODULE$;
/*  61 */     int end = RichInt$.MODULE$.min$extension(until, $this.length());
/*  64 */     String str = $this.toString().substring(start, end);
/*  64 */     Predef$ predef$3 = Predef$.MODULE$;
/*  64 */     return (start >= end) ? $this.newBuilder().result() : ((Builder)$this.newBuilder().$plus$plus$eq((TraversableOnce)new StringOps(str))).result();
/*     */   }
/*     */   
/*     */   public static String $times(StringLike $this, int n) {
/*  70 */     StringBuilder buf = new StringBuilder();
/*  71 */     Predef$ predef$ = Predef$.MODULE$;
/*  71 */     Range$ range$ = Range$.MODULE$;
/*  71 */     StringLike$$anonfun$$times$1 stringLike$$anonfun$$times$1 = new StringLike$$anonfun$$times$1($this, (StringLike<Repr>)buf);
/*     */     Range range;
/*  71 */     if ((range = new Range(0, n, 1)).validateRangeBoundaries((Function1<Object, Object>)stringLike$$anonfun$$times$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/*  71 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/*  71 */         buf.append($this.toString());
/*  71 */         i1 += step1;
/*     */       } 
/*     */     } 
/*  72 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static int compare(StringLike $this, String other) {
/*  75 */     return $this.toString().compareTo(other);
/*     */   }
/*     */   
/*     */   public static boolean scala$collection$immutable$StringLike$$isLineBreak(StringLike $this, char c) {
/*  77 */     return (c == StringLike$.MODULE$.scala$collection$immutable$StringLike$$LF() || c == StringLike$.MODULE$.scala$collection$immutable$StringLike$$FF());
/*     */   }
/*     */   
/*     */   public static String stripLineEnd(StringLike $this) {
/*  90 */     int len = $this.toString().length();
/*  93 */     char last = $this.apply(len - 1);
/*  94 */     return (len == 0) ? $this.toString() : (scala$collection$immutable$StringLike$$isLineBreak($this, last) ? 
/*  95 */       $this.toString().substring(0, (last == StringLike$.MODULE$.scala$collection$immutable$StringLike$$LF() && len >= 2 && $this.apply(len - 2) == StringLike$.MODULE$.scala$collection$immutable$StringLike$$CR()) ? (len - 2) : (len - 1)) : 
/*     */       
/*  97 */       $this.toString());
/*     */   }
/*     */   
/*     */   public static Iterator linesWithSeparators(StringLike<Repr> $this) {
/* 110 */     return (Iterator)new StringLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static Iterator lines(StringLike<Repr> $this) {
/* 129 */     return $this.linesWithSeparators().map((Function1)new StringLike$$anonfun$lines$1($this));
/*     */   }
/*     */   
/*     */   public static Iterator linesIterator(StringLike<Repr> $this) {
/* 136 */     return $this.linesWithSeparators().map((Function1)new StringLike$$anonfun$linesIterator$1($this));
/*     */   }
/*     */   
/*     */   public static String capitalize(StringLike $this) {
/* 143 */     char[] chars = $this.toString().toCharArray();
/* 144 */     char c = chars[0];
/* 144 */     Predef$ predef$ = Predef$.MODULE$;
/* 144 */     chars[0] = RichChar$.MODULE$.toUpper$extension(c);
/* 145 */     return ($this.toString() == null) ? null : (($this.toString().length() == 0) ? "" : new String(chars));
/*     */   }
/*     */   
/*     */   public static String stripPrefix(StringLike $this, String prefix) {
/* 150 */     return $this.toString().startsWith(prefix) ? $this.toString().substring(prefix.length()) : 
/* 151 */       $this.toString();
/*     */   }
/*     */   
/*     */   public static String stripSuffix(StringLike $this, String suffix) {
/* 156 */     return $this.toString().endsWith(suffix) ? $this.toString().substring(0, $this.toString().length() - suffix.length()) : 
/* 157 */       $this.toString();
/*     */   }
/*     */   
/*     */   public static String replaceAllLiterally(StringLike $this, String literal, String replacement) {
/* 168 */     String arg1 = Pattern.quote(literal);
/* 169 */     String arg2 = Matcher.quoteReplacement(replacement);
/* 171 */     return $this.toString().replaceAll(arg1, arg2);
/*     */   }
/*     */   
/*     */   public static String stripMargin(StringLike $this, char marginChar) {
/* 180 */     StringBuilder buf = new StringBuilder();
/* 181 */     $this.linesWithSeparators().foreach((Function1)new StringLike$$anonfun$stripMargin$1($this, buf, marginChar));
/* 188 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static String stripMargin(StringLike $this) {
/* 196 */     return $this.stripMargin('|');
/*     */   }
/*     */   
/*     */   public static String scala$collection$immutable$StringLike$$escape(StringLike $this, char ch) {
/* 198 */     return (new StringBuilder()).append("\\Q").append(BoxesRunTime.boxToCharacter(ch)).append("\\E").toString();
/*     */   }
/*     */   
/*     */   public static String[] split(StringLike $this, char separator) throws PatternSyntaxException {
/* 201 */     return $this.toString().split(scala$collection$immutable$StringLike$$escape($this, separator));
/*     */   }
/*     */   
/*     */   public static String[] split(StringLike<Repr> $this, char[] separators) throws PatternSyntaxException {
/* 205 */     String re = (new StringBuilder()).append(Predef$.MODULE$.charArrayOps(separators).foldLeft("[", (Function2)new StringLike$$anonfun$1($this))).append("]").toString();
/* 206 */     return $this.toString().split(re);
/*     */   }
/*     */   
/*     */   public static Regex r(StringLike $this) {
/* 213 */     return $this.r(Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public static Regex r(StringLike $this, Seq groupNames) {
/* 224 */     return new Regex($this.toString(), groupNames);
/*     */   }
/*     */   
/*     */   public static boolean toBoolean(StringLike $this) {
/* 226 */     return parseBoolean($this, $this.toString());
/*     */   }
/*     */   
/*     */   public static byte toByte(StringLike $this) {
/* 227 */     return Byte.parseByte($this.toString());
/*     */   }
/*     */   
/*     */   public static short toShort(StringLike $this) {
/* 228 */     return Short.parseShort($this.toString());
/*     */   }
/*     */   
/*     */   public static int toInt(StringLike $this) {
/* 229 */     return Integer.parseInt($this.toString());
/*     */   }
/*     */   
/*     */   public static long toLong(StringLike $this) {
/* 230 */     return Long.parseLong($this.toString());
/*     */   }
/*     */   
/*     */   public static float toFloat(StringLike $this) {
/* 231 */     return Float.parseFloat($this.toString());
/*     */   }
/*     */   
/*     */   public static double toDouble(StringLike $this) {
/* 232 */     return Double.parseDouble($this.toString());
/*     */   }
/*     */   
/*     */   private static boolean parseBoolean(StringLike $this, String s) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ifnonnull -> 15
/*     */     //   4: new java/lang/IllegalArgumentException
/*     */     //   7: dup
/*     */     //   8: ldc_w 'For input string: "null"'
/*     */     //   11: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   14: athrow
/*     */     //   15: aload_1
/*     */     //   16: invokevirtual toLowerCase : ()Ljava/lang/String;
/*     */     //   19: astore_2
/*     */     //   20: ldc_w 'true'
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 35
/*     */     //   27: pop
/*     */     //   28: aload_2
/*     */     //   29: ifnull -> 42
/*     */     //   32: goto -> 47
/*     */     //   35: aload_2
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifeq -> 47
/*     */     //   42: iconst_1
/*     */     //   43: istore_3
/*     */     //   44: goto -> 71
/*     */     //   47: ldc_w 'false'
/*     */     //   50: dup
/*     */     //   51: ifnonnull -> 62
/*     */     //   54: pop
/*     */     //   55: aload_2
/*     */     //   56: ifnull -> 69
/*     */     //   59: goto -> 73
/*     */     //   62: aload_2
/*     */     //   63: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   66: ifeq -> 73
/*     */     //   69: iconst_0
/*     */     //   70: istore_3
/*     */     //   71: iload_3
/*     */     //   72: ireturn
/*     */     //   73: new java/lang/IllegalArgumentException
/*     */     //   76: dup
/*     */     //   77: new scala/collection/mutable/StringBuilder
/*     */     //   80: dup
/*     */     //   81: invokespecial <init> : ()V
/*     */     //   84: ldc_w 'For input string: "'
/*     */     //   87: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   90: aload_1
/*     */     //   91: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   94: ldc_w '"'
/*     */     //   97: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   100: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   103: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   106: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #235	-> 0
/*     */     //   #241	-> 4
/*     */     //   #235	-> 15
/*     */     //   #236	-> 20
/*     */     //   #237	-> 47
/*     */     //   #235	-> 71
/*     */     //   #238	-> 73
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	107	0	$this	Lscala/collection/immutable/StringLike;
/*     */     //   0	107	1	s	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public static Object toArray(StringLike $this, ClassTag evidence$1) {
/* 244 */     return $this.toString().toCharArray();
/*     */   }
/*     */   
/*     */   public static Object scala$collection$immutable$StringLike$$unwrapArg(StringLike $this, Object arg) {
/*     */     Object object;
/* 246 */     if (arg instanceof ScalaNumber) {
/* 246 */       ScalaNumber scalaNumber = (ScalaNumber)arg;
/* 246 */       object = scalaNumber.underlying();
/*     */     } else {
/* 248 */       object = arg;
/*     */     } 
/*     */     return object;
/*     */   }
/*     */   
/*     */   public static String format(StringLike<Repr> $this, Seq args) {
/* 266 */     return String.format($this.toString(), (Object[])((TraversableOnce)args.map((Function1)new StringLike$$anonfun$format$1($this), Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.AnyRef()));
/*     */   }
/*     */   
/*     */   public static String formatLocal(StringLike<Repr> $this, Locale l, Seq args) {
/* 283 */     return String.format(l, $this.toString(), (Object[])((TraversableOnce)args.map((Function1)new StringLike$$anonfun$formatLocal$1($this), Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.AnyRef()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StringLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */