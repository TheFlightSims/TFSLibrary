/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.collection.immutable.Range$;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ClassTag$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ObjectRef;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.runtime.Statics;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035d\001B\001\003\001.\021ab\0244gg\026$\bk\\:ji&|gN\003\002\004\t\005)\021N\0349vi*\021QAB\001\ba\006\0248/\0338h\025\t9\001\"\001\003vi&d'\"A\005\002\013M\034\027\r\\1\004\001M)\001\001\004\t\025/A\021QBD\007\002\021%\021q\002\003\002\007\003:L(+\0324\021\005E\021R\"\001\002\n\005M\021!\001\003)pg&$\030n\0348\021\0055)\022B\001\f\t\005\035\001&o\0343vGR\004\"!\004\r\n\005eA!\001D*fe&\fG.\033>bE2,\007\002C\016\001\005+\007I\021\001\017\002\rM|WO]2f+\005i\002C\001\020$\033\005y\"B\001\021\"\003\021a\027M\\4\013\003\t\nAA[1wC&\021Ae\b\002\r\007\"\f'oU3rk\026t7-\032\005\tM\001\021\t\022)A\005;\00591o\\;sG\026\004\003\002\003\025\001\005+\007I\021A\025\002\r=4gm]3u+\005Q\003CA\007,\023\ta\003BA\002J]RD\001B\f\001\003\022\003\006IAK\001\b_\03247/\032;!\021\025\001\004\001\"\0012\003\031a\024N\\5u}Q\031!g\r\033\021\005E\001\001\"B\0160\001\004i\002\"\002\0250\001\004Q\003\002\003\034\001\021\013\007I\021B\034\002\013%tG-\032=\026\003a\0022!D\035+\023\tQ\004BA\003BeJ\f\027\020\003\005=\001!\005\t\025)\0039\003\031Ig\016Z3yA!)a\b\001C\001S\005!A.\0338f\021\025\001\005\001\"\001*\003\031\031w\016\\;n]\")!\t\001C\001\007\006aA.\0338f\007>tG/\0328ugV\tA\t\005\002F\021:\021QBR\005\003\017\"\ta\001\025:fI\0264\027BA%K\005\031\031FO]5oO*\021q\t\003\005\006\031\002!\t%T\001\ti>\034FO]5oOR\ta\n\005\002\037\037&\021\021j\b\005\006#\002!\tEU\001\006I1,7o\035\013\003'Z\003\"!\004+\n\005UC!a\002\"p_2,\027M\034\005\006/B\003\r\001E\001\005i\"\fG\017C\004Z\001\005\005I\021\001.\002\t\r|\007/\037\013\004emc\006bB\016Y!\003\005\r!\b\005\bQa\003\n\0211\001+\021\035q\006!%A\005\002}\013abY8qs\022\"WMZ1vYR$\023'F\001aU\ti\022mK\001c!\t\031\007.D\001e\025\t)g-A\005v]\016DWmY6fI*\021q\rC\001\013C:tw\016^1uS>t\027BA5e\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bW\002\t\n\021\"\001m\0039\031w\016]=%I\0264\027-\0367uII*\022!\034\026\003U\005Dqa\034\001\002\002\023\005\003/A\007qe>$Wo\031;Qe\0264\027\016_\013\002\035\"9!\017AA\001\n\003I\023\001\0049s_\022,8\r^!sSRL\bb\002;\001\003\003%\t!^\001\017aJ|G-^2u\0132,W.\0328u)\t1\030\020\005\002\016o&\021\001\020\003\002\004\003:L\bb\002>t\003\003\005\rAK\001\004q\022\n\004b\002?\001\003\003%\t%`\001\020aJ|G-^2u\023R,'/\031;peV\ta\020\005\003\000\003\0131XBAA\001\025\r\t\031\001C\001\013G>dG.Z2uS>t\027\002BA\004\003\003\021\001\"\023;fe\006$xN\035\005\n\003\027\001\021\021!C\001\003\033\t\001bY1o\013F,\030\r\034\013\004'\006=\001\002\003>\002\n\005\005\t\031\001<\t\023\005M\001!!A\005B\005U\021\001\0035bg\"\034u\016Z3\025\003)B\021\"!\007\001\003\003%\t%a\007\002\r\025\fX/\0317t)\r\031\026Q\004\005\tu\006]\021\021!a\001m\036I\021\021\005\002\002\002#\005\0211E\001\017\037\03247/\032;Q_NLG/[8o!\r\t\022Q\005\004\t\003\t\t\t\021#\001\002(M)\021QEA\025/A9\0211FA\031;)\022TBAA\027\025\r\ty\003C\001\beVtG/[7f\023\021\t\031$!\f\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\0041\003K!\t!a\016\025\005\005\r\002\002\003'\002&\005\005IQI'\t\025\005u\022QEA\001\n\003\013y$A\003baBd\027\020F\0033\003\003\n\031\005\003\004\034\003w\001\r!\b\005\007Q\005m\002\031\001\026\t\025\005\035\023QEA\001\n\003\013I%A\004v]\006\004\b\017\\=\025\t\005-\023q\013\t\006\033\0055\023\021K\005\004\003\037B!AB(qi&|g\016E\003\016\003'j\"&C\002\002V!\021a\001V;qY\026\024\004\"CA-\003\013\n\t\0211\0013\003\rAH\005\r\005\013\003;\n)#!A\005\n\005}\023a\003:fC\022\024Vm]8mm\026$\"!!\031\021\007y\t\031'C\002\002f}\021aa\0242kK\016$\b")
/*    */ public class OffsetPosition implements Position, Product, Serializable {
/*    */   private final CharSequence source;
/*    */   
/*    */   private final int offset;
/*    */   
/*    */   private int[] index;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   public String longString() {
/* 21 */     return Position$class.longString(this);
/*    */   }
/*    */   
/*    */   public CharSequence source() {
/* 21 */     return this.source;
/*    */   }
/*    */   
/*    */   public int offset() {
/* 21 */     return this.offset;
/*    */   }
/*    */   
/*    */   public OffsetPosition copy(CharSequence source, int offset) {
/* 21 */     return new OffsetPosition(source, offset);
/*    */   }
/*    */   
/*    */   public CharSequence copy$default$1() {
/* 21 */     return source();
/*    */   }
/*    */   
/*    */   public int copy$default$2() {
/* 21 */     return offset();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "OffsetPosition";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 21 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 21 */     switch (x$1) {
/*    */       default:
/* 21 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 21 */     return source();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 21 */     return x$1 instanceof OffsetPosition;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 21 */     return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, Statics.anyHash(source())), offset()), 2);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 87
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/util/parsing/input/OffsetPosition
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 91
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/util/parsing/input/OffsetPosition
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual source : ()Ljava/lang/CharSequence;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual source : ()Ljava/lang/CharSequence;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 83
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 83
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual offset : ()I
/*    */     //   62: aload #4
/*    */     //   64: invokevirtual offset : ()I
/*    */     //   67: if_icmpne -> 83
/*    */     //   70: aload #4
/*    */     //   72: aload_0
/*    */     //   73: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   76: ifeq -> 83
/*    */     //   79: iconst_1
/*    */     //   80: goto -> 84
/*    */     //   83: iconst_0
/*    */     //   84: ifeq -> 91
/*    */     //   87: iconst_1
/*    */     //   88: goto -> 92
/*    */     //   91: iconst_0
/*    */     //   92: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     //   #236	-> 12
/*    */     //   #21	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	93	0	this	Lscala/util/parsing/input/OffsetPosition;
/*    */     //   0	93	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public OffsetPosition(CharSequence source, int offset) {
/* 21 */     Position$class.$init$(this);
/* 21 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   private int[] index$lzycompute() {
/* 24 */     synchronized (this) {
/* 24 */       if (!this.bitmap$0) {
/* 25 */         ObjectRef lineStarts = new ObjectRef(new ArrayBuffer());
/* 26 */         ((ArrayBuffer)lineStarts.elem).$plus$eq(BoxesRunTime.boxToInteger(0));
/* 27 */         Predef$ predef$ = Predef$.MODULE$;
/* 27 */         int i = source().length();
/* 27 */         Range$ range$ = Range$.MODULE$;
/* 27 */         OffsetPosition$$anonfun$index$1 offsetPosition$$anonfun$index$1 = new OffsetPosition$$anonfun$index$1(this, lineStarts);
/* 27 */         Range range = new Range(0, i, 1);
/* 27 */         if (range.validateRangeBoundaries((Function1)offsetPosition$$anonfun$index$1)) {
/*    */           int terminal1;
/*    */           int step1;
/*    */           int i1;
/* 27 */           for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 27 */             int j = i1, k = j;
/* 27 */             (source().charAt(k) == '\n') ? ((ArrayBuffer)lineStarts.elem).$plus$eq(BoxesRunTime.boxToInteger(k + 1)) : BoxedUnit.UNIT;
/* 27 */             i1 += step1;
/*    */           } 
/*    */         } 
/* 29 */         ((ArrayBuffer)lineStarts.elem).$plus$eq(BoxesRunTime.boxToInteger(source().length()));
/* 30 */         this.index = (int[])((ArrayBuffer)lineStarts.elem).toArray(ClassTag$.MODULE$.Int());
/*    */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/input/OffsetPosition}} */
/*    */       return this.index;
/*    */     } 
/*    */   }
/*    */   
/*    */   private int[] index() {
/*    */     return this.bitmap$0 ? this.index : index$lzycompute();
/*    */   }
/*    */   
/*    */   public class OffsetPosition$$anonfun$index$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ObjectRef lineStarts$1;
/*    */     
/*    */     public OffsetPosition$$anonfun$index$1(OffsetPosition $outer, ObjectRef lineStarts$1) {}
/*    */     
/*    */     public final Object apply(int i) {
/*    */       return (this.$outer.source().charAt(i) == '\n') ? ((ArrayBuffer)this.lineStarts$1.elem).$plus$eq(BoxesRunTime.boxToInteger(i + 1)) : BoxedUnit.UNIT;
/*    */     }
/*    */   }
/*    */   
/*    */   public int line() {
/* 35 */     int lo = 0;
/* 36 */     int hi = (index()).length - 1;
/* 37 */     while (lo + 1 < hi) {
/* 38 */       int mid = (hi + lo) / 2;
/* 39 */       if (offset() < index()[mid]) {
/* 39 */         hi = mid;
/*    */         continue;
/*    */       } 
/* 40 */       lo = mid;
/*    */     } 
/* 42 */     return lo + 1;
/*    */   }
/*    */   
/*    */   public int column() {
/* 46 */     return offset() - index()[line() - 1] + 1;
/*    */   }
/*    */   
/*    */   public String lineContents() {
/* 53 */     return source().subSequence(index()[line() - 1], index()[line()]).toString();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     return (new StringBuilder()).append(line()).append(".").append(BoxesRunTime.boxToInteger(column())).toString();
/*    */   }
/*    */   
/*    */   public boolean $less(Position that) {
/*    */     boolean bool;
/* 65 */     if (that instanceof OffsetPosition) {
/* 65 */       OffsetPosition offsetPosition = (OffsetPosition)that;
/* 67 */       bool = (offset() < offsetPosition.offset()) ? true : false;
/*    */     } else {
/* 69 */       bool = (line() < that.line() || (
/* 70 */         line() == that.line() && column() < that.column())) ? true : false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<CharSequence, Object>, OffsetPosition> tupled() {
/*    */     return OffsetPosition$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<CharSequence, Function1<Object, OffsetPosition>> curried() {
/*    */     return OffsetPosition$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\OffsetPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */