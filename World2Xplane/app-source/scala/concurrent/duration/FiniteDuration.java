/*     */ package scala.concurrent.duration;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.PartialOrdering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichLong;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t}s!B\001\003\021\003I\021A\004$j]&$X\rR;sCRLwN\034\006\003\007\021\t\001\002Z;sCRLwN\034\006\003\013\031\t!bY8oGV\024(/\0328u\025\0059\021!B:dC2\f7\001\001\t\003\025-i\021A\001\004\006\031\tA\t!\004\002\017\r&t\027\016^3EkJ\fG/[8o'\rYaB\005\t\003\037Ai\021AB\005\003#\031\021a!\0218z%\0264\007CA\b\024\023\t!bA\001\007TKJL\027\r\\5{C\ndW\rC\003\027\027\021\005q#\001\004=S:LGO\020\013\002\023\035)\021d\003E\0025\0059b)\0338ji\026$UO]1uS>t\027j](sI\026\024X\r\032\t\0037qi\021a\003\004\006;-A\tA\b\002\030\r&t\027\016^3EkJ\fG/[8o\023N|%\017Z3sK\022\0342\001H\020(!\t\001S%D\001\"\025\t\0213%\001\003mC:<'\"\001\023\002\t)\fg/Y\005\003M\005\022aa\0242kK\016$\bc\001\0251g9\021\021F\f\b\003U5j\021a\013\006\003Y!\ta\001\020:p_Rt\024\"A\004\n\005=2\021a\0029bG.\fw-Z\005\003cI\022\001b\024:eKJLgn\032\006\003_\031\001\"A\003\033\007\t1\021!!N\n\003iY\002\"AC\034\n\005a\022!\001\003#ve\006$\030n\0348\t\021i\"$Q1A\005\002m\na\001\\3oORDW#\001\037\021\005=i\024B\001 \007\005\021auN\\4\t\021\001#$\021!Q\001\nq\nq\001\\3oORD\007\005\003\005Ci\t\025\r\021\"\001D\003\021)h.\033;\026\003\021\003\"!R$\017\005)1\025BA\030\003\023\tA\025J\001\005US6,WK\\5u\025\ty#\001\003\005Li\t\005\t\025!\003E\003\025)h.\033;!\021\0251B\007\"\001N)\r\031dj\024\005\006u1\003\r\001\020\005\006\0052\003\r\001\022\005\007#R\002K\021\002*\002\017\t|WO\0343fIR\0211K\026\t\003\037QK!!\026\004\003\017\t{w\016\\3b]\")q\013\025a\001y\005\031Q.\031=\t\013e#D\021A\036\002\017Q|g*\0318pg\")1\f\016C\001w\005AAo\\'jGJ|7\017C\003^i\021\0051(\001\005u_6KG\016\\5t\021\025yF\007\"\001<\003%!xnU3d_:$7\017C\003bi\021\0051(A\005u_6Kg.\036;fg\")1\r\016C\001w\0059Ao\034%pkJ\034\b\"B35\t\003Y\024A\002;p\t\006L8\017C\003hi\021\005\001.\001\004u_Vs\027\016\036\013\003S2\004\"a\0046\n\005-4!A\002#pk\ndW\rC\003nM\002\007A)A\001v\021\025yG\007\"\001q\003\0351'o\\7O_^,\022!\035\t\003\025IL!a\035\002\003\021\021+\027\r\0327j]\026Da!\036\033!\n\0231\030AC;oSR\034FO]5oOV\tq\017\005\002!q&\021\0210\t\002\007'R\024\030N\\4\t\013m$D\021\t?\002\021Q|7\013\036:j]\036$\022a\036\005\006}R\"\ta`\001\bG>l\007/\031:f)\021\t\t!a\002\021\007=\t\031!C\002\002\006\031\0211!\0238u\021\031\tI! a\001m\005)q\016\0365fe\"A\021Q\002\033!\n\023\ty!A\004tC\032,\027\t\0323\025\013q\n\t\"!\006\t\017\005M\0211\002a\001y\005\t\021\rC\004\002\030\005-\001\031\001\037\002\003\tD\001\"a\0075A\023%\021QD\001\004C\022$G#B\032\002 \005\r\002bBA\021\0033\001\r\001P\001\f_RDWM\035'f]\036$\b\016C\004\002&\005e\001\031\001#\002\023=$\b.\032:V]&$\bbBA\025i\021\005\0211F\001\006IAdWo\035\013\004m\0055\002bBA\005\003O\001\rA\016\005\b\003c!D\021AA\032\003\031!S.\0338vgR\031a'!\016\t\017\005%\021q\006a\001m!9\021\021\b\033\005\002\005m\022A\002\023uS6,7\017F\0027\003{Aq!a\020\0028\001\007\021.\001\004gC\016$xN\035\005\b\003\007\"D\021AA#\003\021!C-\033<\025\007Y\n9\005C\004\002J\005\005\003\031A5\002\017\021Lg/[:pe\"A\021Q\n\033!\n\023\ty%A\005nS:,8OW3s_V\t\021\016C\004\002DQ\"\t!a\025\025\007%\f)\006C\004\002J\005E\003\031\001\034\t\017\005%B\007\"\001\002ZQ\0311'a\027\t\017\005%\021q\013a\001g!9\021\021\007\033\005\002\005}CcA\032\002b!9\021\021BA/\001\004\031\004bBA3i\021\005\021qM\001\005a2,8\017F\0024\003SBq!!\003\002d\001\0071\007C\004\002nQ\"\t!a\034\002\0135Lg.^:\025\007M\n\t\bC\004\002\n\005-\004\031A\032\t\017\005UD\007\"\001\002x\005\031Q.\0338\025\007M\nI\bC\004\002\n\005M\004\031A\032\t\r]#D\021AA?)\r\031\024q\020\005\b\003\023\tY\b1\0014\021\035\t\031\005\016C\001\003\007#2aMAC\021\035\tI%!!A\002qBq!!\0175\t\003\tI\tF\0024\003\027Cq!a\020\002\b\002\007A\bC\004\002\020R\"I!!%\002\017M\fg-Z'vYR)A(a%\002\030\"9\021QSAG\001\004a\024AA0b\021\035\tI*!$A\002q\n!a\0302\t\017\005uE\007\"\001\002 \006\031A-\033<\025\007M\n\t\013C\004\002J\005m\005\031\001\037\t\017\005\025F\007\"\001\002(\006\031Q.\0367\025\007M\nI\013C\004\002@\005\r\006\031\001\037\t\017\0055F\007\"\001\0020\006aQO\\1ss~#S.\0338vgV\t1\007C\004\0024R\")!!.\002\021%\034h)\0338ji\026$\022a\025\005\b\003s#D\021IA^\003\031)\027/^1mgR\0311+!0\t\021\005%\021q\027a\001\003\0032aDAa\023\r\t\031M\002\002\004\003:L\bbBAdi\021\005\023\021Z\001\tQ\006\034\bnQ8eKR\021\021\021\001\005\007-q!\t!!4\025\003iAaA \017\005\002\005EGCBA\001\003'\f)\016C\004\002\024\005=\007\031A\032\t\017\005]\021q\032a\001g!I\021\021\034\017\002\002\023%\0211\\\001\fe\026\fGMU3t_24X\rF\001 \021\035\tyn\003C\001\003C\fQ!\0319qYf$RaMAr\003KDaAOAo\001\004a\004B\002\"\002^\002\007A\tC\004\002`.!\t!!;\025\013M\nY/!<\t\ri\n9\0171\001=\021\035\021\025q\035a\001\003_\004B!!=\002x:\031q\"a=\n\007\005Uh!\001\004Qe\026$WMZ\005\004s\006e(bAA{\r!I\021Q`\006C\002\0235\021q`\001\007[\006DxL\\:\026\005\t\005qB\001B\002=!yx\000\000\000\000\000\000\000\020\002\003B\004\027\001\006iA!\001\002\0175\f\007p\0308tA!I!1B\006C\002\0235!QB\001\b[\006DxLq[t+\t\021ya\004\002\003\022y9\001\005rN&HP;\020\002\003B\013\027\001\006iAa\004\002\0215\f\007p\030b6h\002B\021B!\007\f\005\004%iAa\007\002\r5\f\007pX7t+\t\021ib\004\002\003 y1\001bY>Q6ZH\001Ba\t\fA\0035!QD\001\b[\006Dx,\\:!\021%\0219c\003b\001\n\033\021I#A\003nCb|6/\006\002\003,=\021!Q\006\020\006\005\025\n]\020\002\005\t\005cY\001\025!\004\003,\0051Q.\031=`g\002B\021B!\016\f\005\004%iAa\016\002\0175\f\007pX7j]V\021!\021H\b\003\005wqB!C\025 h$A!qH\006!\002\033\021I$\001\005nCb|V.\0338!\021%\021\031e\003b\001\n\033\021)%A\003nCb|\006.\006\002\003H=\021!\021\n\020\004O]y\020\002\003B'\027\001\006iAa\022\002\r5\f\007p\0305!\021%\021\tf\003b\001\n\033\021\031&A\003nCb|F-\006\002\003V=\021!q\013\020\004\003\001~\020\002\003B.\027\001\006iA!\026\002\r5\f\007p\0303!\021%\tInCA\001\n\023\tY\016")
/*     */ public final class FiniteDuration extends Duration {
/*     */   private final long length;
/*     */   
/*     */   private final TimeUnit unit;
/*     */   
/*     */   public static class FiniteDurationIsOrdered$ implements Ordering<FiniteDuration> {
/*     */     public static final FiniteDurationIsOrdered$ MODULE$;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 526 */       return Ordering.class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 526 */       return Ordering.class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 526 */       return Ordering.class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 526 */       return Ordering.class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 526 */       return Ordering.class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 526 */       return Ordering.class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 526 */       return Ordering.class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 526 */       return Ordering.class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<FiniteDuration> reverse() {
/* 526 */       return Ordering.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 526 */       return Ordering.class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<FiniteDuration>.Ops mkOrderingOps(Object lhs) {
/* 526 */       return Ordering.class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 526 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public FiniteDurationIsOrdered$() {
/* 526 */       MODULE$ = this;
/* 526 */       PartialOrdering.class.$init$((PartialOrdering)this);
/* 526 */       Ordering.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(FiniteDuration a, FiniteDuration b) {
/* 527 */       return a.compare(b);
/*     */     }
/*     */   }
/*     */   
/*     */   public long length() {
/* 547 */     return this.length;
/*     */   }
/*     */   
/*     */   public TimeUnit unit() {
/* 547 */     return this.unit;
/*     */   }
/*     */   
/*     */   public FiniteDuration(long length, TimeUnit unit) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: lload_1
/*     */     //   2: putfield length : J
/*     */     //   5: aload_0
/*     */     //   6: aload_3
/*     */     //   7: putfield unit : Ljava/util/concurrent/TimeUnit;
/*     */     //   10: aload_0
/*     */     //   11: invokespecial <init> : ()V
/*     */     //   14: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   17: getstatic java/util/concurrent/TimeUnit.NANOSECONDS : Ljava/util/concurrent/TimeUnit;
/*     */     //   20: dup
/*     */     //   21: ifnonnull -> 32
/*     */     //   24: pop
/*     */     //   25: aload_3
/*     */     //   26: ifnull -> 39
/*     */     //   29: goto -> 51
/*     */     //   32: aload_3
/*     */     //   33: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   36: ifeq -> 51
/*     */     //   39: aload_0
/*     */     //   40: ldc2_w 9223372036854775807
/*     */     //   43: invokespecial bounded : (J)Z
/*     */     //   46: istore #7
/*     */     //   48: goto -> 291
/*     */     //   51: getstatic java/util/concurrent/TimeUnit.MICROSECONDS : Ljava/util/concurrent/TimeUnit;
/*     */     //   54: dup
/*     */     //   55: ifnonnull -> 66
/*     */     //   58: pop
/*     */     //   59: aload_3
/*     */     //   60: ifnull -> 73
/*     */     //   63: goto -> 85
/*     */     //   66: aload_3
/*     */     //   67: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   70: ifeq -> 85
/*     */     //   73: aload_0
/*     */     //   74: ldc2_w 9223372036854775
/*     */     //   77: invokespecial bounded : (J)Z
/*     */     //   80: istore #7
/*     */     //   82: goto -> 291
/*     */     //   85: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
/*     */     //   88: dup
/*     */     //   89: ifnonnull -> 100
/*     */     //   92: pop
/*     */     //   93: aload_3
/*     */     //   94: ifnull -> 107
/*     */     //   97: goto -> 119
/*     */     //   100: aload_3
/*     */     //   101: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   104: ifeq -> 119
/*     */     //   107: aload_0
/*     */     //   108: ldc2_w 9223372036854
/*     */     //   111: invokespecial bounded : (J)Z
/*     */     //   114: istore #7
/*     */     //   116: goto -> 291
/*     */     //   119: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
/*     */     //   122: dup
/*     */     //   123: ifnonnull -> 134
/*     */     //   126: pop
/*     */     //   127: aload_3
/*     */     //   128: ifnull -> 141
/*     */     //   131: goto -> 153
/*     */     //   134: aload_3
/*     */     //   135: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   138: ifeq -> 153
/*     */     //   141: aload_0
/*     */     //   142: ldc2_w 9223372036
/*     */     //   145: invokespecial bounded : (J)Z
/*     */     //   148: istore #7
/*     */     //   150: goto -> 291
/*     */     //   153: getstatic java/util/concurrent/TimeUnit.MINUTES : Ljava/util/concurrent/TimeUnit;
/*     */     //   156: dup
/*     */     //   157: ifnonnull -> 168
/*     */     //   160: pop
/*     */     //   161: aload_3
/*     */     //   162: ifnull -> 175
/*     */     //   165: goto -> 187
/*     */     //   168: aload_3
/*     */     //   169: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   172: ifeq -> 187
/*     */     //   175: aload_0
/*     */     //   176: ldc2_w 153722867
/*     */     //   179: invokespecial bounded : (J)Z
/*     */     //   182: istore #7
/*     */     //   184: goto -> 291
/*     */     //   187: getstatic java/util/concurrent/TimeUnit.HOURS : Ljava/util/concurrent/TimeUnit;
/*     */     //   190: dup
/*     */     //   191: ifnonnull -> 202
/*     */     //   194: pop
/*     */     //   195: aload_3
/*     */     //   196: ifnull -> 209
/*     */     //   199: goto -> 221
/*     */     //   202: aload_3
/*     */     //   203: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   206: ifeq -> 221
/*     */     //   209: aload_0
/*     */     //   210: ldc2_w 2562047
/*     */     //   213: invokespecial bounded : (J)Z
/*     */     //   216: istore #7
/*     */     //   218: goto -> 291
/*     */     //   221: getstatic java/util/concurrent/TimeUnit.DAYS : Ljava/util/concurrent/TimeUnit;
/*     */     //   224: dup
/*     */     //   225: ifnonnull -> 236
/*     */     //   228: pop
/*     */     //   229: aload_3
/*     */     //   230: ifnull -> 243
/*     */     //   233: goto -> 255
/*     */     //   236: aload_3
/*     */     //   237: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   240: ifeq -> 255
/*     */     //   243: aload_0
/*     */     //   244: ldc2_w 106751
/*     */     //   247: invokespecial bounded : (J)Z
/*     */     //   250: istore #7
/*     */     //   252: goto -> 291
/*     */     //   255: getstatic java/util/concurrent/TimeUnit.DAYS : Ljava/util/concurrent/TimeUnit;
/*     */     //   258: lload_1
/*     */     //   259: aload_3
/*     */     //   260: invokevirtual convert : (JLjava/util/concurrent/TimeUnit;)J
/*     */     //   263: lstore #4
/*     */     //   265: ldc2_w 106751
/*     */     //   268: lneg
/*     */     //   269: lload #4
/*     */     //   271: lcmp
/*     */     //   272: ifgt -> 288
/*     */     //   275: lload #4
/*     */     //   277: ldc2_w 106751
/*     */     //   280: lcmp
/*     */     //   281: ifgt -> 288
/*     */     //   284: iconst_1
/*     */     //   285: goto -> 289
/*     */     //   288: iconst_0
/*     */     //   289: istore #7
/*     */     //   291: astore #6
/*     */     //   293: iload #7
/*     */     //   295: ifeq -> 299
/*     */     //   298: return
/*     */     //   299: new java/lang/IllegalArgumentException
/*     */     //   302: dup
/*     */     //   303: new scala/collection/mutable/StringBuilder
/*     */     //   306: dup
/*     */     //   307: invokespecial <init> : ()V
/*     */     //   310: ldc_w 'requirement failed: '
/*     */     //   313: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   316: ldc_w 'Duration is limited to +-(2^63-1)ns (ca. 292 years)'
/*     */     //   319: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   322: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   325: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   328: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #547	-> 0
/*     */     //   #553	-> 14
/*     */     //   #557	-> 17
/*     */     //   #558	-> 51
/*     */     //   #559	-> 85
/*     */     //   #560	-> 119
/*     */     //   #561	-> 153
/*     */     //   #562	-> 187
/*     */     //   #563	-> 221
/*     */     //   #565	-> 255
/*     */     //   #566	-> 265
/*     */     //   #564	-> 289
/*     */     //   #553	-> 291
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	329	0	this	Lscala/concurrent/duration/FiniteDuration;
/*     */     //   0	329	1	length	J
/*     */     //   0	329	3	unit	Ljava/util/concurrent/TimeUnit;
/*     */     //   265	64	4	v	J
/*     */   }
/*     */   
/*     */   private boolean bounded(long max) {
/* 551 */     return (-max <= length() && length() <= max);
/*     */   }
/*     */   
/*     */   public long toNanos() {
/* 569 */     return unit().toNanos(length());
/*     */   }
/*     */   
/*     */   public long toMicros() {
/* 570 */     return unit().toMicros(length());
/*     */   }
/*     */   
/*     */   public long toMillis() {
/* 571 */     return unit().toMillis(length());
/*     */   }
/*     */   
/*     */   public long toSeconds() {
/* 572 */     return unit().toSeconds(length());
/*     */   }
/*     */   
/*     */   public long toMinutes() {
/* 573 */     return unit().toMinutes(length());
/*     */   }
/*     */   
/*     */   public long toHours() {
/* 574 */     return unit().toHours(length());
/*     */   }
/*     */   
/*     */   public long toDays() {
/* 575 */     return unit().toDays(length());
/*     */   }
/*     */   
/*     */   public double toUnit(TimeUnit u) {
/* 576 */     return toNanos() / TimeUnit.NANOSECONDS.convert(1L, u);
/*     */   }
/*     */   
/*     */   public Deadline fromNow() {
/* 581 */     return Deadline$.MODULE$.now().$plus(this);
/*     */   }
/*     */   
/*     */   private String unitString() {
/* 583 */     return (new StringBuilder()).append(Duration$.MODULE$.timeUnitName().apply(unit())).append((length() == 1L) ? "" : "s").toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 584 */     return (new StringBuilder()).append("").append(BoxesRunTime.boxToLong(length())).append(" ").append(unitString()).toString();
/*     */   }
/*     */   
/*     */   public int compare(Duration other) {
/*     */     int i;
/* 586 */     if (other instanceof FiniteDuration) {
/* 586 */       FiniteDuration finiteDuration = (FiniteDuration)other;
/* 586 */       long l = toNanos();
/* 586 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 586 */       i = (new RichLong(l)).compare(BoxesRunTime.boxToLong(finiteDuration.toNanos()));
/*     */     } else {
/* 588 */       i = -other.compare(this);
/*     */     } 
/*     */     return i;
/*     */   }
/*     */   
/*     */   private long safeAdd(long a, long b) {
/* 593 */     if ((b > 0L && a > Long.MAX_VALUE - b) || (
/* 594 */       b < 0L && a < Long.MIN_VALUE - b))
/* 594 */       throw new IllegalArgumentException("integer overflow"); 
/* 595 */     return a + b;
/*     */   }
/*     */   
/*     */   private FiniteDuration add(long otherLength, TimeUnit otherUnit) {
/* 598 */     TimeUnit commonUnit = (otherUnit.convert(1L, unit()) == 0L) ? unit() : otherUnit;
/* 599 */     long totalLength = safeAdd(commonUnit.convert(length(), unit()), commonUnit.convert(otherLength, otherUnit));
/* 600 */     return new FiniteDuration(totalLength, commonUnit);
/*     */   }
/*     */   
/*     */   public Duration $plus(Duration other) {
/*     */     Duration duration;
/* 603 */     if (other instanceof FiniteDuration) {
/* 603 */       FiniteDuration finiteDuration = (FiniteDuration)other;
/* 603 */       duration = add(finiteDuration.length(), finiteDuration.unit());
/*     */     } else {
/* 605 */       duration = other;
/*     */     } 
/*     */     return duration;
/*     */   }
/*     */   
/*     */   public Duration $minus(Duration other) {
/*     */     Duration duration;
/* 607 */     if (other instanceof FiniteDuration) {
/* 607 */       FiniteDuration finiteDuration = (FiniteDuration)other;
/* 607 */       duration = add(-finiteDuration.length(), finiteDuration.unit());
/*     */     } else {
/* 609 */       duration = other;
/*     */     } 
/*     */     return duration;
/*     */   }
/*     */   
/*     */   public Duration $times(double factor) {
/* 613 */     return scala.Predef$.MODULE$.double2Double(factor).isInfinite() ? (
/* 614 */       scala.Predef$.MODULE$.double2Double(factor).isNaN() ? Duration$.MODULE$.Undefined() : (
/* 615 */       ((((factor > false) ? 1 : 0) ^ $less(Duration$.MODULE$.Zero())) != 0) ? Duration$.MODULE$.Inf() : 
/* 616 */       Duration$.MODULE$.MinusInf())) : Duration$.MODULE$.fromNanos(toNanos() * factor);
/*     */   }
/*     */   
/*     */   public Duration $div(double divisor) {
/* 619 */     return scala.Predef$.MODULE$.double2Double(divisor).isInfinite() ? (
/* 620 */       scala.Predef$.MODULE$.double2Double(divisor).isNaN() ? Duration$.MODULE$.Undefined() : 
/* 621 */       Duration$.MODULE$.Zero()) : Duration$.MODULE$.fromNanos(toNanos() / divisor);
/*     */   }
/*     */   
/*     */   private double minusZero() {
/* 624 */     return -0.0D;
/*     */   }
/*     */   
/*     */   public double $div(Duration divisor) {
/* 626 */     return divisor.isFinite() ? (toNanos() / divisor.toNanos()) : (
/* 627 */       (divisor == Duration$.MODULE$.Undefined()) ? Double.NaN : (
/* 628 */       ((((length() < 0L) ? 1 : 0) ^ divisor.$greater(Duration$.MODULE$.Zero())) != 0) ? 0.0D : 
/* 629 */       minusZero()));
/*     */   }
/*     */   
/*     */   public FiniteDuration $plus(FiniteDuration other) {
/* 632 */     return add(other.length(), other.unit());
/*     */   }
/*     */   
/*     */   public FiniteDuration $minus(FiniteDuration other) {
/* 633 */     return add(-other.length(), other.unit());
/*     */   }
/*     */   
/*     */   public FiniteDuration plus(FiniteDuration other) {
/* 634 */     return $plus(other);
/*     */   }
/*     */   
/*     */   public FiniteDuration minus(FiniteDuration other) {
/* 635 */     return $minus(other);
/*     */   }
/*     */   
/*     */   public FiniteDuration min(FiniteDuration other) {
/* 636 */     return $less(other) ? this : other;
/*     */   }
/*     */   
/*     */   public FiniteDuration max(FiniteDuration other) {
/* 637 */     return $greater(other) ? this : other;
/*     */   }
/*     */   
/*     */   public FiniteDuration $div(long divisor) {
/* 646 */     return Duration$.MODULE$.fromNanos(toNanos() / divisor);
/*     */   }
/*     */   
/*     */   public FiniteDuration $times(long factor) {
/* 653 */     return new FiniteDuration(safeMul(length(), factor), unit());
/*     */   }
/*     */   
/*     */   private long safeMul(long _a, long _b) {
/* 666 */     long a = scala.math.package$.MODULE$.abs(_a);
/* 667 */     long b = scala.math.package$.MODULE$.abs(_b);
/* 669 */     if (Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(b) < 64)
/* 669 */       throw new IllegalArgumentException("multiplication overflow"); 
/* 670 */     long product = a * b;
/* 671 */     if (product < 0L)
/* 671 */       throw new IllegalArgumentException("multiplication overflow"); 
/* 672 */     return ((((a == _a) ? 1 : 0) ^ ((b == _b) ? 1 : 0)) != 0) ? -product : product;
/*     */   }
/*     */   
/*     */   public FiniteDuration div(long divisor) {
/* 680 */     return $div(divisor);
/*     */   }
/*     */   
/*     */   public FiniteDuration mul(long factor) {
/* 687 */     return $times(factor);
/*     */   }
/*     */   
/*     */   public FiniteDuration unary_$minus() {
/* 689 */     return Duration$.MODULE$.apply(-length(), unit());
/*     */   }
/*     */   
/*     */   public final boolean isFinite() {
/* 691 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*     */     boolean bool;
/* 693 */     if (other instanceof FiniteDuration) {
/* 693 */       FiniteDuration finiteDuration = (FiniteDuration)other;
/* 693 */       bool = (toNanos() == finiteDuration.toNanos());
/*     */     } else {
/* 695 */       bool = super.equals(other);
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 697 */     return (int)toNanos();
/*     */   }
/*     */   
/*     */   public static FiniteDuration apply(long paramLong, String paramString) {
/*     */     return FiniteDuration$.MODULE$.apply(paramLong, paramString);
/*     */   }
/*     */   
/*     */   public static FiniteDuration apply(long paramLong, TimeUnit paramTimeUnit) {
/*     */     return FiniteDuration$.MODULE$.apply(paramLong, paramTimeUnit);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\FiniteDuration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */