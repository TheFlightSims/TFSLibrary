/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.RichLong;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rtAB\001\003\021\003!\001\"A\007CSR|\005/\032:bi&|gn\035\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\004\"!\003\006\016\003\t1aa\003\002\t\002\021a!!\004\"ji>\003XM]1uS>t7o\005\002\013\033A\021abD\007\002\r%\021\001C\002\002\007\003:L(+\0324\t\013IQA\021\001\013\002\rqJg.\033;?\007\001!\022\001\003\004\b-)\001\n1!\001\030\005\rIe\016^\n\003+5AQ!G\013\005\002i\ta\001J5oSR$C#A\016\021\0059a\022BA\017\007\005\021)f.\033;\006\tY)\002a\b\t\003\035\001J!A\006\004\t\013\t*B\021A\022\002\ti,'o\034\013\004I\035Z\003C\001\b&\023\t1cAA\004C_>dW-\0318\t\013!\n\003\031A\025\002\003%\004\"A\013\020\016\003UAQ\001L\021A\002%\nA!\\1tW\")A&\006C\001]Q\031qd\f\031\t\013!j\003\031A\025\t\0131j\003\031A\025\t\013I*B\021A\032\002\021!\f7/T1uG\"$B\001\n\0337q!)Q'\ra\001S\005\0311.Z=\t\013]\n\004\031A\025\002\rA\024XMZ5y\021\025I\024\0071\001*\003\005i\007\"B\036\026\t\003a\024aD;og&<g.\0323D_6\004\030M]3\025\007\021jd\bC\003)u\001\007\021\006C\003@u\001\007\021&A\001k\021\025\tU\003\"\001C\003\035\031\bn\034:uKJ$2\001J\"F\021\025!\005\t1\001*\003\ti\027\007C\003G\001\002\007\021&\001\002ne!)\001*\006C\001\023\006Q1m\\7qY\026lWM\034;\025\005}Q\005\"\002\025H\001\004I\003\"\002'\026\t\003i\025\001\0022jiN$\"A\024+\021\007=\023F%D\001Q\025\t\tF!A\005j[6,H/\0312mK&\0211\013\025\002\013\023:$W\r_3e'\026\f\b\"B+L\001\004I\023a\0018v[\")q+\006C\0011\006I!-\033;TiJLgn\032\013\0043\002\f\007C\001.^\035\tq1,\003\002]\r\0051\001K]3eK\032L!AX0\003\rM#(/\0338h\025\taf\001C\003V-\002\007\021\006C\004c-B\005\t\031A-\002\007M,\007\017C\003e+\021\005Q-A\007iS\036DWm\035;P]\026\024\025\016\036\013\003?\031DQaP2A\002%Bq\001[\013\022\002\023\005\021.A\ncSR\034FO]5oO\022\"WMZ1vYR$#'F\001kU\tI6nK\001m!\ti'/D\001o\025\ty\007/A\005v]\016DWmY6fI*\021\021OB\001\013C:tw\016^1uS>t\027BA:o\005E)hn\0315fG.,GMV1sS\006t7-Z\004\006k*A\tA^\001\004\023:$\bCA<y\033\005Qa!\002\f\013\021\003I8c\001=\016uB\021q/\006\005\006%a$\t\001 \013\002m\0329aP\003I\001\004\003y(\001\002'p]\036\034\"!`\007\t\013eiH\021\001\016\006\013yl\b!!\002\021\0079\t9!\003\002\r!1!% C\001\003\027!R\001JA\007\003'Aq\001KA\005\001\004\ty\001\005\003\002\022\005\rQ\"A?\t\0171\nI\0011\001\002\020!1A& C\001\003/!b!!\002\002\032\005m\001b\002\025\002\026\001\007\021q\002\005\bY\005U\001\031AA\b\021\031\021T\020\"\001\002 Q9A%!\t\002$\005\025\002bB\033\002\036\001\007\021q\002\005\bo\005u\001\031AA\b\021\035I\024Q\004a\001\003\037AaaO?\005\002\005%B#\002\023\002,\0055\002b\002\025\002(\001\007\021q\002\005\b\005\035\002\031AA\b\021\031\tU\020\"\001\0022Q)A%a\r\0026!9A)a\fA\002\005=\001b\002$\0020\001\007\021q\002\005\007\021v$\t!!\017\025\t\005\025\0211\b\005\bQ\005]\002\031AA\b\021\031aU\020\"\001\002@Q\031a*!\021\t\017U\013i\0041\001\002\020!1q+ C\001\003\013\"R!WA$\003\023Bq!VA\"\001\004\ty\001\003\005c\003\007\002\n\0211\001Z\021\031!W\020\"\001\002NQ!\021QAA(\021\035y\0241\na\001\003\037Aq\001[?\022\002\023\005\021nB\004\002V)A\t!a\026\002\t1{gn\032\t\004o\006ecA\002@\013\021\003\tYfE\003\002Z5\ti\006\005\002x{\"9!#!\027\005\002\005\005DCAA,\001")
/*    */ public final class BitOperations {
/*    */   public static abstract class Int$class {
/*    */     public static void $init$(BitOperations.Int $this) {}
/*    */     
/*    */     public static boolean zero(BitOperations.Int $this, int i, int mask) {
/* 20 */       return ((i & mask) == 0);
/*    */     }
/*    */     
/*    */     public static int mask(BitOperations.Int $this, int i, int mask) {
/* 21 */       return i & ($this.complement(mask - 1) ^ mask);
/*    */     }
/*    */     
/*    */     public static boolean hasMatch(BitOperations.Int $this, int key, int prefix, int m) {
/* 22 */       return ($this.mask(key, m) == prefix);
/*    */     }
/*    */     
/*    */     public static boolean unsignedCompare(BitOperations.Int $this, int i, int j) {
/* 23 */       return ((i < j)) ^ ((i < 0)) ^ ((j < 0) ? 1 : 0);
/*    */     }
/*    */     
/*    */     public static boolean shorter(BitOperations.Int $this, int m1, int m2) {
/* 24 */       return $this.unsignedCompare(m2, m1);
/*    */     }
/*    */     
/*    */     public static int complement(BitOperations.Int $this, int i) {
/* 25 */       return 0xFFFFFFFF ^ i;
/*    */     }
/*    */     
/*    */     public static IndexedSeq bits(BitOperations.Int $this, int num) {
/* 26 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 26 */       return (IndexedSeq)scala.runtime.RichInt$.MODULE$.to$extension0(31, 0).by(-1).map((Function1)new BitOperations$Int$$anonfun$bits$1($this, num), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom());
/*    */     }
/*    */     
/*    */     public static String bitString(BitOperations.Int $this, int num, String sep) {
/* 27 */       return ((TraversableOnce)$this.bits(num).map((Function1)new BitOperations$Int$$anonfun$bitString$1($this), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())).mkString(sep);
/*    */     }
/*    */     
/*    */     public static String bitString$default$2(BitOperations.Int $this) {
/* 27 */       return "";
/*    */     }
/*    */     
/*    */     public static int highestOneBit(BitOperations.Int $this, int j) {
/* 30 */       i = j | j >> 1;
/* 32 */       i |= i >> 2;
/* 33 */       i |= i >> 4;
/* 34 */       i |= i >> 8;
/* 35 */       i |= i >> 16;
/* 36 */       return i - (i >>> 1);
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface Int {
/*    */     boolean zero(int param1Int1, int param1Int2);
/*    */     
/*    */     int mask(int param1Int1, int param1Int2);
/*    */     
/*    */     boolean hasMatch(int param1Int1, int param1Int2, int param1Int3);
/*    */     
/*    */     boolean unsignedCompare(int param1Int1, int param1Int2);
/*    */     
/*    */     boolean shorter(int param1Int1, int param1Int2);
/*    */     
/*    */     int complement(int param1Int);
/*    */     
/*    */     IndexedSeq<Object> bits(int param1Int);
/*    */     
/*    */     String bitString(int param1Int, String param1String);
/*    */     
/*    */     String bitString$default$2();
/*    */     
/*    */     int highestOneBit(int param1Int);
/*    */     
/*    */     public class BitOperations$Int$$anonfun$bits$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final int num$1;
/*    */       
/*    */       public final boolean apply(int i) {
/*    */         return apply$mcZI$sp(i);
/*    */       }
/*    */       
/*    */       public boolean apply$mcZI$sp(int i) {
/*    */         return ((this.num$1 >>> i & 0x1) != 0);
/*    */       }
/*    */       
/*    */       public BitOperations$Int$$anonfun$bits$1(BitOperations.Int $outer, int num$1) {}
/*    */     }
/*    */     
/*    */     public class BitOperations$Int$$anonfun$bitString$1 extends AbstractFunction1<Object, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(boolean b) {
/*    */         return b ? "1" : "0";
/*    */       }
/*    */       
/*    */       public BitOperations$Int$$anonfun$bitString$1(BitOperations.Int $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Int$ implements Int {
/*    */     public static final Int$ MODULE$;
/*    */     
/*    */     public class BitOperations$Int$$anonfun$bits$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final int num$1;
/*    */       
/*    */       public final boolean apply(int i) {
/*    */         return apply$mcZI$sp(i);
/*    */       }
/*    */       
/*    */       public boolean apply$mcZI$sp(int i) {
/*    */         return ((this.num$1 >>> i & 0x1) != 0);
/*    */       }
/*    */       
/*    */       public BitOperations$Int$$anonfun$bits$1(BitOperations.Int $outer, int num$1) {}
/*    */     }
/*    */     
/*    */     public class BitOperations$Int$$anonfun$bitString$1 extends AbstractFunction1<Object, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(boolean b) {
/*    */         return b ? "1" : "0";
/*    */       }
/*    */       
/*    */       public BitOperations$Int$$anonfun$bitString$1(BitOperations.Int $outer) {}
/*    */     }
/*    */     
/*    */     public boolean zero(int i, int mask) {
/* 39 */       return BitOperations.Int$class.zero(this, i, mask);
/*    */     }
/*    */     
/*    */     public int mask(int i, int mask) {
/* 39 */       return BitOperations.Int$class.mask(this, i, mask);
/*    */     }
/*    */     
/*    */     public boolean hasMatch(int key, int prefix, int m) {
/* 39 */       return BitOperations.Int$class.hasMatch(this, key, prefix, m);
/*    */     }
/*    */     
/*    */     public boolean unsignedCompare(int i, int j) {
/* 39 */       return BitOperations.Int$class.unsignedCompare(this, i, j);
/*    */     }
/*    */     
/*    */     public boolean shorter(int m1, int m2) {
/* 39 */       return BitOperations.Int$class.shorter(this, m1, m2);
/*    */     }
/*    */     
/*    */     public int complement(int i) {
/* 39 */       return BitOperations.Int$class.complement(this, i);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> bits(int num) {
/* 39 */       return BitOperations.Int$class.bits(this, num);
/*    */     }
/*    */     
/*    */     public String bitString(int num, String sep) {
/* 39 */       return BitOperations.Int$class.bitString(this, num, sep);
/*    */     }
/*    */     
/*    */     public int highestOneBit(int j) {
/* 39 */       return BitOperations.Int$class.highestOneBit(this, j);
/*    */     }
/*    */     
/*    */     public String bitString$default$2() {
/* 39 */       return BitOperations.Int$class.bitString$default$2(this);
/*    */     }
/*    */     
/*    */     public Int$() {
/* 39 */       MODULE$ = this;
/* 39 */       BitOperations.Int$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract class Long$class {
/*    */     public static void $init$(BitOperations.Long $this) {}
/*    */     
/*    */     public static boolean zero(BitOperations.Long $this, long i, long mask) {
/* 43 */       return ((i & mask) == 0L);
/*    */     }
/*    */     
/*    */     public static long mask(BitOperations.Long $this, long i, long mask) {
/* 44 */       return i & ($this.complement(mask - 1L) ^ mask);
/*    */     }
/*    */     
/*    */     public static boolean hasMatch(BitOperations.Long $this, long key, long prefix, long m) {
/* 45 */       return ($this.mask(key, m) == prefix);
/*    */     }
/*    */     
/*    */     public static boolean unsignedCompare(BitOperations.Long $this, long i, long j) {
/* 46 */       return ((i < j)) ^ ((i < 0L)) ^ ((j < 0L) ? 1 : 0);
/*    */     }
/*    */     
/*    */     public static boolean shorter(BitOperations.Long $this, long m1, long m2) {
/* 47 */       return $this.unsignedCompare(m2, m1);
/*    */     }
/*    */     
/*    */     public static long complement(BitOperations.Long $this, long i) {
/* 48 */       return 0xFFFFFFFFFFFFFFFFL ^ i;
/*    */     }
/*    */     
/*    */     public static IndexedSeq bits(BitOperations.Long $this, long num) {
/* 49 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 49 */       return (IndexedSeq)(new RichLong(63L)).to(BoxesRunTime.boxToLong(0L)).by(BoxesRunTime.boxToLong(-1L)).map((Function1)new BitOperations$Long$$anonfun$bits$2($this, num), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom());
/*    */     }
/*    */     
/*    */     public static String bitString(BitOperations.Long $this, long num, String sep) {
/* 50 */       return ((TraversableOnce)$this.bits(num).map((Function1)new BitOperations$Long$$anonfun$bitString$2($this), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())).mkString(sep);
/*    */     }
/*    */     
/*    */     public static String bitString$default$2(BitOperations.Long $this) {
/* 50 */       return "";
/*    */     }
/*    */     
/*    */     public static long highestOneBit(BitOperations.Long $this, long j) {
/* 53 */       i = j | j >> 1L;
/* 55 */       i |= i >> 2L;
/* 56 */       i |= i >> 4L;
/* 57 */       i |= i >> 8L;
/* 58 */       i |= i >> 16L;
/* 59 */       i |= i >> 32L;
/* 60 */       return i - (i >>> 1L);
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface Long {
/*    */     boolean zero(long param1Long1, long param1Long2);
/*    */     
/*    */     long mask(long param1Long1, long param1Long2);
/*    */     
/*    */     boolean hasMatch(long param1Long1, long param1Long2, long param1Long3);
/*    */     
/*    */     boolean unsignedCompare(long param1Long1, long param1Long2);
/*    */     
/*    */     boolean shorter(long param1Long1, long param1Long2);
/*    */     
/*    */     long complement(long param1Long);
/*    */     
/*    */     IndexedSeq<Object> bits(long param1Long);
/*    */     
/*    */     String bitString(long param1Long, String param1String);
/*    */     
/*    */     String bitString$default$2();
/*    */     
/*    */     long highestOneBit(long param1Long);
/*    */     
/*    */     public class BitOperations$Long$$anonfun$bits$2 extends AbstractFunction1.mcZJ.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final long num$2;
/*    */       
/*    */       public final boolean apply(long i) {
/*    */         return apply$mcZJ$sp(i);
/*    */       }
/*    */       
/*    */       public boolean apply$mcZJ$sp(long i) {
/*    */         return ((this.num$2 >>> (int)i & 0x1L) != 0L);
/*    */       }
/*    */       
/*    */       public BitOperations$Long$$anonfun$bits$2(BitOperations.Long $outer, long num$2) {}
/*    */     }
/*    */     
/*    */     public class BitOperations$Long$$anonfun$bitString$2 extends AbstractFunction1<Object, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(boolean b) {
/*    */         return b ? "1" : "0";
/*    */       }
/*    */       
/*    */       public BitOperations$Long$$anonfun$bitString$2(BitOperations.Long $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Long$ implements Long {
/*    */     public static final Long$ MODULE$;
/*    */     
/*    */     public class BitOperations$Long$$anonfun$bits$2 extends AbstractFunction1.mcZJ.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final long num$2;
/*    */       
/*    */       public final boolean apply(long i) {
/*    */         return apply$mcZJ$sp(i);
/*    */       }
/*    */       
/*    */       public boolean apply$mcZJ$sp(long i) {
/*    */         return ((this.num$2 >>> (int)i & 0x1L) != 0L);
/*    */       }
/*    */       
/*    */       public BitOperations$Long$$anonfun$bits$2(BitOperations.Long $outer, long num$2) {}
/*    */     }
/*    */     
/*    */     public class BitOperations$Long$$anonfun$bitString$2 extends AbstractFunction1<Object, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(boolean b) {
/*    */         return b ? "1" : "0";
/*    */       }
/*    */       
/*    */       public BitOperations$Long$$anonfun$bitString$2(BitOperations.Long $outer) {}
/*    */     }
/*    */     
/*    */     public boolean zero(long i, long mask) {
/* 63 */       return BitOperations.Long$class.zero(this, i, mask);
/*    */     }
/*    */     
/*    */     public long mask(long i, long mask) {
/* 63 */       return BitOperations.Long$class.mask(this, i, mask);
/*    */     }
/*    */     
/*    */     public boolean hasMatch(long key, long prefix, long m) {
/* 63 */       return BitOperations.Long$class.hasMatch(this, key, prefix, m);
/*    */     }
/*    */     
/*    */     public boolean unsignedCompare(long i, long j) {
/* 63 */       return BitOperations.Long$class.unsignedCompare(this, i, j);
/*    */     }
/*    */     
/*    */     public boolean shorter(long m1, long m2) {
/* 63 */       return BitOperations.Long$class.shorter(this, m1, m2);
/*    */     }
/*    */     
/*    */     public long complement(long i) {
/* 63 */       return BitOperations.Long$class.complement(this, i);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Object> bits(long num) {
/* 63 */       return BitOperations.Long$class.bits(this, num);
/*    */     }
/*    */     
/*    */     public String bitString(long num, String sep) {
/* 63 */       return BitOperations.Long$class.bitString(this, num, sep);
/*    */     }
/*    */     
/*    */     public long highestOneBit(long j) {
/* 63 */       return BitOperations.Long$class.highestOneBit(this, j);
/*    */     }
/*    */     
/*    */     public String bitString$default$2() {
/* 63 */       return BitOperations.Long$class.bitString$default$2(this);
/*    */     }
/*    */     
/*    */     public Long$() {
/* 63 */       MODULE$ = this;
/* 63 */       BitOperations.Long$class.$init$(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\BitOperations.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */