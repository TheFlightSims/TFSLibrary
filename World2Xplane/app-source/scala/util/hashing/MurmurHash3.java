/*     */ package scala.util.hashing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tUg!B\001\003\001\tA!aC've6,(\017S1tQNR!a\001\003\002\017!\f7\017[5oO*\021QAB\001\005kRLGNC\001\b\003\025\0318-\0317b'\t\001\021\002\005\002\013\0275\ta!\003\002\r\r\t1\021I\\=SK\032DQA\004\001\005\002A\ta\001P5oSRt4\001\001\013\002#A\021!\003A\007\002\005!)A\003\001C\003+\005\031Q.\033=\025\007YI2\004\005\002\013/%\021\001D\002\002\004\023:$\b\"\002\016\024\001\0041\022\001\0025bg\"DQ\001H\nA\002Y\tA\001Z1uC\")a\004\001C\003?\0059Q.\033=MCN$Hc\001\f!C!)!$\ba\001-!)A$\ba\001-!)1\005\001C\003I\005aa-\0338bY&TX\rS1tQR\031a#\n\024\t\013i\021\003\031\001\f\t\013\035\022\003\031\001\f\002\r1,gn\032;i\021\025I\003\001\"\004+\003%\tg/\0317b]\016DW\r\006\002\027W!)!\004\013a\001-!)Q\006\001C\003]\005Y\001O]8ek\016$\b*Y:i)\r1r\006\016\005\006a1\002\r!M\001\002qB\021!BM\005\003g\031\021q\001\025:pIV\034G\017C\0036Y\001\007a#\001\003tK\026$\007\"B\034\001\t\013A\024AC:ue&tw\rS1tQR\031a#\017\"\t\013i2\004\031A\036\002\007M$(\017\005\002=9\021!\"P\005\003}\031\ta\001\025:fI\0264\027B\001!B\005\031\031FO]5oO*\021aH\002\005\006kY\002\rA\006\005\006\t\002!)!R\001\016k:|'\017Z3sK\022D\025m\0355\025\007Y1u\013C\003H\007\002\007\001*\001\002ygB\031\021*\025+\017\005){eBA&O\033\005a%BA'\020\003\031a$o\\8u}%\tq!\003\002Q\r\0059\001/Y2lC\036,\027B\001*T\005=!&/\031<feN\f'\r\\3P]\016,'B\001)\007!\tQQ+\003\002W\r\t\031\021I\\=\t\013U\032\005\031\001\f\t\013e\003AQ\001.\002\027=\024H-\032:fI\"\0137\017\033\013\004-mc\006\"B$Y\001\004A\005\"B\033Y\001\0041\002\"\0020\001\t\013y\026!C1se\006L\b*Y:i+\t\001\007\016F\002\027CJDQAY/A\002\r\f\021!\031\t\004\025\0214\027BA3\007\005\025\t%O]1z!\t9\007\016\004\001\005\023%l\006\025!A\001\006\004Q'!\001+\022\005-$\006C\001\006m\023\tigAA\004O_RD\027N\\4)\005!|\007C\001\006q\023\t\thAA\006ta\026\034\027.\0317ju\026$\007\"B\033^\001\0041\002\"\002;\001\t\013)\030!\0032zi\026\034\b*Y:i)\r1bo\037\005\0069M\004\ra\036\t\004\025\021D\bC\001\006z\023\tQhA\001\003CsR,\007\"B\033t\001\0041\002\"B?\001\t\013q\030\001\0037jgRD\025m\0355\025\tYy\030\021\004\005\007\017r\004\r!!\0011\t\005\r\021Q\003\t\007\003\013\ty!a\005\016\005\005\035!\002BA\005\003\027\t\021\"[7nkR\f'\r\\3\013\007\0055a!\001\006d_2dWm\031;j_:LA!!\005\002\b\t!A*[:u!\r9\027Q\003\003\013\003/y\030\021!A\001\006\003Q'aA0%c!)Q\007 a\001-\0359\021Q\004\002\t\002\005}\021aC've6,(\017S1tQN\0022AEA\021\r\031\t!\001#\001\002$M\031\021\021E\t\t\0179\t\t\003\"\001\002(Q\021\021q\004\005\013\003W\t\tC1A\005\006\0055\022!C1se\006L8+Z3e+\t\tyc\004\002\0022u!Ah\002&b\021%\t)$!\t!\002\033\ty#\001\006beJ\f\027pU3fI\002B!\"!\017\002\"\t\007IQAA\036\003)\031HO]5oON+W\rZ\013\003\003{y!!a\020\036\t]X}P5\005\n\003\007\n\t\003)A\007\003{\t1b\035;sS:<7+Z3eA!Q\021qIA\021\005\004%)!!\023\002\027A\024x\016Z;diN+W\rZ\013\003\003\027z!!!\024\036\t)w0X0\005\n\003#\n\t\003)A\007\003\027\nA\002\035:pIV\034GoU3fI\002B!\"!\026\002\"\t\007IQAA,\0035\031\0300\\7fiJL7mU3fIV\021\021\021L\b\003\0037jB!.Jx^#I\021qLA\021A\0035\021\021L\001\017gflW.\032;sS\016\034V-\0323!\021)\t\031'!\tC\002\023\025\021QM\001\020iJ\fg/\032:tC\ndWmU3fIV\021\021qM\b\003\003SjBa:\036\f,!I\021QNA\021A\0035\021qM\001\021iJ\fg/\032:tC\ndWmU3fI\002B!\"!\035\002\"\t\007IQAA:\003\035\031X-]*fK\022,\022A\006\005\t\003o\n\t\003)A\007-\005A1/Z9TK\026$\007\005\003\006\002|\005\005\"\031!C\003\003g\nq!\\1q'\026,G\r\003\005\002\000\005\005\002\025!\004\027\003!i\027\r]*fK\022\004\003BCAB\003C\021\r\021\"\002\002t\00591/\032;TK\026$\007\002CAD\003C\001\013Q\002\f\002\021M,GoU3fI\002BqAXA\021\t\003\tY)\006\003\002\016\006UEc\001\f\002\020\"9!-!#A\002\005E\005\003\002\006e\003'\0032aZAK\t)I\027\021\022Q\001\002\003\025\rA\033\025\004\003+{\007b\002;\002\"\021\005\0211\024\013\004-\005u\005B\002\017\002\032\002\007q\017C\004Z\003C!\t!!)\025\007Y\t\031\013\003\004H\003?\003\r\001\023\005\b[\005\005B\021AAT)\r1\022\021\026\005\007a\005\025\006\031A\031\t\017]\n\t\003\"\001\002.R\031a#a,\t\rA\nY\0131\001<\021\035!\025\021\005C\001\003g#2AFA[\021\0319\025\021\027a\001\021\"A\021\021XA\021\t\003\tY,A\004tKFD\025m\0355\025\007Y\ti\fC\004H\003o\003\r!a01\t\005\005\0271\032\t\007\003\007\f)-!3\016\005\005-\021\002BAd\003\027\0211aU3r!\r9\0271\032\003\f\003\033\fi,!A\001\002\013\005!NA\002`IIB\001\"!5\002\"\021\005\0211[\001\b[\006\004\b*Y:i)\r1\022Q\033\005\b\017\006=\007\031AAla\031\tI.!9\002hBA\0211YAn\003?\f)/\003\003\002^\006-!aA'baB\031q-!9\005\027\005\r\030Q[A\001\002\003\025\tA\033\002\004?\022\032\004cA4\002h\022Y\021\021^Ak\003\003\005\tQ!\001k\005\ryF\005\016\005\t\003[\f\t\003\"\001\002p\00691/\032;ICNDGc\001\f\002r\"9q)a;A\002\005M\b\007BA{\003{\004b!a1\002x\006m\030\002BA}\003\027\0211aU3u!\r9\027Q \003\f\003\f\t0!A\001\002\013\005!NA\002`IU2qAa\001\002\"\001\021)A\001\007BeJ\f\027\020S1tQ&tw-\006\003\003\b\tM1#\002B\001\023\t%\001#\002\n\003\f\t=\021b\001B\007\005\t9\001*Y:iS:<\007\003\002\006e\005#\0012a\032B\n\t)I'\021\001Q\001\002\003\025\rA\033\025\004\005'y\007b\002\b\003\002\021\005!\021\004\013\003\0057\001bA!\b\003\002\tEQBAA\021\021\035Q\"\021\001C\001\005C!2A\006B\022\021\035\021'q\004a\001\005\037A\001Ba\n\002\"\021\005!\021F\001\rCJ\024\030-\037%bg\"LgnZ\013\005\005W\021\t$\006\002\003.A1!Q\004B\001\005_\0012a\032B\031\t)I'Q\005Q\001\002\003\025\rA\033\025\004\005cy\007\002\003B\034\003C!\tA!\017\002\031\tLH/Z:ICND\027N\\4\026\005\tm\"#\002B\037\023\t\005ca\002B \005k\001!1\b\002\ryI,g-\0338f[\026tGO\020\t\005%\t-q\017\003\005\003F\005\005B\021\001B$\0039y'\017Z3sK\022D\025m\0355j]\036,\"A!\023\023\013\t-\023B!\024\007\017\t}\"1\t\001\003JA!!Ca\003I\021!\021\t&!\t\005\002\tM\023A\0049s_\022,8\r\036%bg\"LgnZ\013\003\005+\022RAa\026\n\00532qAa\020\003P\001\021)\006\005\003\023\005\027\t\004\002\003B/\003C!\tAa\030\002\033M$(/\0338h\021\006\034\b.\0338h+\t\021\tGE\003\003d%\021)GB\004\003@\tm\003A!\031\021\tI\021Ya\017\005\t\005S\n\t\003\"\001\003l\005\001RO\\8sI\026\024X\r\032%bg\"LgnZ\013\003\005[\022RAa\034\n\005\0332qAa\020\003h\001\021i\007\003\005\003t\005\005BQ\001B;\0035\031\0300\\7fiJL7\rS1tQV!!q\017BB)\0251\"\021\020BC\021\0359%\021\017a\001\005w\002b!a1\003~\t\005\025\002\002B@\003\027\021!cR3o)J\fg/\032:tC\ndWm\0248dKB\031qMa!\005\r%\024\tH1\001k\021!)$\021\017I\001\002\0041\002\006\003B9\005\023\023yIa%\021\007)\021Y)C\002\003\016\032\021!\002Z3qe\026\034\027\r^3eC\t\021\t*A\tVg\026\004SO\\8sI\026\024X\r\032%bg\"\f#A!&\002\rIr\023\007\r\0301\021!\021I*!\t\005\006\tm\025a\004;sCZ,'o]1cY\026D\025m\0355\026\t\tu%Q\025\013\006-\t}%q\025\005\b\017\n]\005\031\001BQ!\031\t\031M! \003$B\031qM!*\005\r%\0249J1\001k\021!)$q\023I\001\002\0041\002\006\003BL\005\023\023YKa%\"\005\t5\026aD+tK\002z'\017Z3sK\022D\025m\0355\t\025\tE\026\021EI\001\n\013\021\031,A\fts6lW\r\036:jG\"\0137\017\033\023eK\032\fW\017\034;%eU!!Q\027Bf+\t\0219LK\002\027\005s[#Aa/\021\t\tu&qY\007\003\005SAA!1\003D\006IQO\\2iK\016\\W\r\032\006\004\005\0134\021AC1o]>$\030\r^5p]&!!\021\032B`\005E)hn\0315fG.,GMV1sS\006t7-\032\003\007S\n=&\031\0016\t\025\t=\027\021EI\001\n\013\021\t.A\rue\0064XM]:bE2,\007*Y:iI\021,g-Y;mi\022\022T\003\002B[\005'$a!\033Bg\005\004Q\007")
/*     */ public class MurmurHash3 {
/*     */   public final int mix(int hash, int data) {
/*  16 */     int h = mixLast(hash, data);
/*  18 */     return Integer.rotateLeft(h, 13) * 5 + -430675100;
/*     */   }
/*     */   
/*     */   public final int mixLast(int hash, int data) {
/*  29 */     k = Integer.rotateLeft(data * -862048943, 15) * 461845907;
/*  31 */     return hash ^ k;
/*     */   }
/*     */   
/*     */   public final int finalizeHash(int hash, int length) {
/*  35 */     return avalanche(hash ^ length);
/*     */   }
/*     */   
/*     */   private final int avalanche(int hash) {
/*  42 */     h = (hash ^ hash >>> 16) * -2048144789;
/*  44 */     h = (h ^ h >>> 13) * -1028477387;
/*  45 */     return h ^ h >>> 16;
/*     */   }
/*     */   
/*     */   public final int productHash(Product x, int seed) {
/*  52 */     int arr = x.productArity();
/*  60 */     int h = seed;
/*  61 */     int i = 0;
/*  62 */     while (i < arr) {
/*  63 */       h = mix(h, ScalaRunTime$.MODULE$.hash(x.productElement(i)));
/*  64 */       i++;
/*     */     } 
/*  66 */     return (arr == 0) ? x.productPrefix().hashCode() : finalizeHash(h, arr);
/*     */   }
/*     */   
/*     */   public final int stringHash(String str, int seed) {
/*  72 */     int h = seed;
/*  73 */     int i = 0;
/*  74 */     while (i + 1 < str.length()) {
/*  75 */       int data = (str.charAt(i) << 16) + str.charAt(i + 1);
/*  76 */       h = mix(h, data);
/*  77 */       i += 2;
/*     */     } 
/*  79 */     if (i < str.length())
/*  79 */       h = mixLast(h, str.charAt(i)); 
/*  80 */     return finalizeHash(h, str.length());
/*     */   }
/*     */   
/*     */   public final int unorderedHash(TraversableOnce xs, int seed) {
/*  88 */     IntRef a = new IntRef(0), b = new IntRef(0), n = new IntRef(0);
/*  89 */     IntRef c = new IntRef(1);
/*  90 */     xs.foreach((Function1)new MurmurHash3$$anonfun$unorderedHash$1(this, a, b, n, c));
/*  97 */     int h = mix(seed, a.elem);
/*  99 */     h = mix(h, b.elem);
/* 100 */     h = mixLast(h, c.elem);
/* 101 */     return finalizeHash(h, n.elem);
/*     */   }
/*     */   
/*     */   public class MurmurHash3$$anonfun$unorderedHash$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef a$1;
/*     */     
/*     */     private final IntRef b$1;
/*     */     
/*     */     private final IntRef n$1;
/*     */     
/*     */     private final IntRef c$1;
/*     */     
/*     */     public MurmurHash3$$anonfun$unorderedHash$1(MurmurHash3 $outer, IntRef a$1, IntRef b$1, IntRef n$1, IntRef c$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/*     */       int h = ScalaRunTime$.MODULE$.hash(x);
/*     */       this.a$1.elem += h;
/*     */       this.b$1.elem ^= h;
/*     */       if (h != 0)
/*     */         this.c$1.elem *= h; 
/*     */       this.n$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public final int orderedHash(TraversableOnce xs, int seed) {
/* 106 */     IntRef n = new IntRef(0);
/* 107 */     IntRef h = new IntRef(seed);
/* 108 */     xs.foreach((Function1)new MurmurHash3$$anonfun$orderedHash$1(this, n, h));
/* 112 */     return finalizeHash(h.elem, n.elem);
/*     */   }
/*     */   
/*     */   public class MurmurHash3$$anonfun$orderedHash$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef n$2;
/*     */     
/*     */     private final IntRef h$1;
/*     */     
/*     */     public MurmurHash3$$anonfun$orderedHash$1(MurmurHash3 $outer, IntRef n$2, IntRef h$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/*     */       this.h$1.elem = this.$outer.mix(this.h$1.elem, ScalaRunTime$.MODULE$.hash(x));
/*     */       this.n$2.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public final <T> int arrayHash(Object a, int seed) {
/* 118 */     int h = seed;
/* 119 */     int i = 0;
/* 120 */     while (i < ScalaRunTime$.MODULE$.array_length(a)) {
/* 121 */       h = mix(h, ScalaRunTime$.MODULE$.hash(ScalaRunTime$.MODULE$.array_apply(a, i)));
/* 122 */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, ScalaRunTime$.MODULE$.array_length(a));
/*     */   }
/*     */   
/*     */   public final int arrayHash$mZc$sp(boolean[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, a[i] ? 1231 : 1237);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mBc$sp(byte[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, a[i]);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mCc$sp(char[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, a[i]);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mDc$sp(double[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mFc$sp(float[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mIc$sp(int[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, a[i]);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mJc$sp(long[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mSc$sp(short[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, a[i]);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int arrayHash$mVc$sp(BoxedUnit[] a, int seed) {
/*     */     int h = seed;
/*     */     int i = 0;
/*     */     while (i < a.length) {
/*     */       h = mix(h, 0);
/*     */       i++;
/*     */     } 
/* 124 */     return finalizeHash(h, a.length);
/*     */   }
/*     */   
/*     */   public final int bytesHash(byte[] data, int seed) {
/* 131 */     int len = data.length;
/* 132 */     int h = seed;
/* 135 */     int i = 0;
/* 136 */     while (len >= 4) {
/* 137 */       int j = data[i + 0] & 0xFF;
/* 140 */       j = j | (data[i + 1] & 0xFF) << 8 | (data[i + 2] & 0xFF) << 16 | (data[i + 3] & 0xFF) << 24;
/* 142 */       h = mix(h, j);
/* 144 */       i += 4;
/* 145 */       len -= 4;
/*     */     } 
/* 149 */     int k = 0;
/* 150 */     if (len == 3)
/* 150 */       k = 0x0 ^ (data[i + 2] & 0xFF) << 16; 
/* 151 */     if (len >= 2)
/* 151 */       k ^= (data[i + 1] & 0xFF) << 8; 
/* 152 */     if (len >= 1) {
/* 153 */       k ^= data[i + 0] & 0xFF;
/* 154 */       h = mixLast(h, k);
/*     */     } 
/* 158 */     return finalizeHash(h, data.length);
/*     */   }
/*     */   
/*     */   public final int listHash(List xs, int seed) {
/* 162 */     int n = 0;
/* 163 */     int h = seed;
/* 164 */     List elems = xs;
/*     */     while (true) {
/* 165 */       if (elems.isEmpty())
/* 172 */         return finalizeHash(h, n); 
/*     */       Object head = elems.head();
/*     */       List tail = (List)elems.tail();
/*     */       h = mix(h, ScalaRunTime$.MODULE$.hash(head));
/*     */       n++;
/*     */       elems = tail;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T> int traversableHash$default$2() {
/*     */     return MurmurHash3$.MODULE$.traversableHash$default$2();
/*     */   }
/*     */   
/*     */   public static <T> int symmetricHash$default$2() {
/*     */     return MurmurHash3$.MODULE$.symmetricHash$default$2();
/*     */   }
/*     */   
/*     */   public static <T> int traversableHash(GenTraversableOnce<T> paramGenTraversableOnce, int paramInt) {
/*     */     return MurmurHash3$.MODULE$.traversableHash(paramGenTraversableOnce, paramInt);
/*     */   }
/*     */   
/*     */   public static <T> int symmetricHash(GenTraversableOnce<T> paramGenTraversableOnce, int paramInt) {
/*     */     return MurmurHash3$.MODULE$.symmetricHash(paramGenTraversableOnce, paramInt);
/*     */   }
/*     */   
/*     */   public static Object unorderedHashing() {
/*     */     return MurmurHash3$.MODULE$.unorderedHashing();
/*     */   }
/*     */   
/*     */   public static Object stringHashing() {
/*     */     return MurmurHash3$.MODULE$.stringHashing();
/*     */   }
/*     */   
/*     */   public static Object productHashing() {
/*     */     return MurmurHash3$.MODULE$.productHashing();
/*     */   }
/*     */   
/*     */   public static Object orderedHashing() {
/*     */     return MurmurHash3$.MODULE$.orderedHashing();
/*     */   }
/*     */   
/*     */   public static Object bytesHashing() {
/*     */     return MurmurHash3$.MODULE$.bytesHashing();
/*     */   }
/*     */   
/*     */   public static <T> ArrayHashing<T> arrayHashing() {
/*     */     return MurmurHash3$.MODULE$.arrayHashing();
/*     */   }
/*     */   
/*     */   public static int setHash(Set<?> paramSet) {
/*     */     return MurmurHash3$.MODULE$.setHash(paramSet);
/*     */   }
/*     */   
/*     */   public static int mapHash(Map<?, ?> paramMap) {
/*     */     return MurmurHash3$.MODULE$.mapHash(paramMap);
/*     */   }
/*     */   
/*     */   public static int seqHash(Seq<?> paramSeq) {
/*     */     return MurmurHash3$.MODULE$.seqHash(paramSeq);
/*     */   }
/*     */   
/*     */   public static int setSeed() {
/*     */     return MurmurHash3$.MODULE$.setSeed();
/*     */   }
/*     */   
/*     */   public static int mapSeed() {
/*     */     return MurmurHash3$.MODULE$.mapSeed();
/*     */   }
/*     */   
/*     */   public static int seqSeed() {
/*     */     return MurmurHash3$.MODULE$.seqSeed();
/*     */   }
/*     */   
/*     */   public static int traversableSeed() {
/*     */     return MurmurHash3$.MODULE$.traversableSeed();
/*     */   }
/*     */   
/*     */   public static int symmetricSeed() {
/*     */     return MurmurHash3$.MODULE$.symmetricSeed();
/*     */   }
/*     */   
/*     */   public static int productSeed() {
/*     */     return MurmurHash3$.MODULE$.productSeed();
/*     */   }
/*     */   
/*     */   public static int stringSeed() {
/*     */     return MurmurHash3$.MODULE$.stringSeed();
/*     */   }
/*     */   
/*     */   public static int arraySeed() {
/*     */     return MurmurHash3$.MODULE$.arraySeed();
/*     */   }
/*     */   
/*     */   public static class ArrayHashing<T> implements Hashing<Object> {
/*     */     public int hash(Object a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcZ$sp(boolean[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcB$sp(byte[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcC$sp(char[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcD$sp(double[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcF$sp(float[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcI$sp(int[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcJ$sp(long[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcS$sp(short[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */     
/*     */     public int hash$mcV$sp(BoxedUnit[] a) {
/* 225 */       return hash(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcZ$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcZ$sp(boolean[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mZc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(boolean[] a) {
/* 225 */       return hash$mcZ$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcB$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcB$sp(byte[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mBc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(byte[] a) {
/* 225 */       return hash$mcB$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcC$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcC$sp(char[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mCc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(char[] a) {
/* 225 */       return hash$mcC$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcD$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcD$sp(double[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mDc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(double[] a) {
/* 225 */       return hash$mcD$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcF$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcF$sp(float[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mFc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(float[] a) {
/* 225 */       return hash$mcF$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcI$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcI$sp(int[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mIc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(int[] a) {
/* 225 */       return hash$mcI$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcJ$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcJ$sp(long[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mJc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(long[] a) {
/* 225 */       return hash$mcJ$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcS$sp extends ArrayHashing<Object> {
/*     */     public int hash$mcS$sp(short[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mSc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(short[] a) {
/* 225 */       return hash$mcS$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArrayHashing$mcV$sp extends ArrayHashing<BoxedUnit> {
/*     */     public int hash$mcV$sp(BoxedUnit[] a) {
/* 225 */       return MurmurHash3$.MODULE$.arrayHash$mVc$sp(a);
/*     */     }
/*     */     
/*     */     public int hash(BoxedUnit[] a) {
/* 225 */       return hash$mcV$sp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$1 implements Hashing<byte[]> {
/*     */     public int hash(byte[] data) {
/* 231 */       return MurmurHash3$.MODULE$.bytesHash(data);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$2 implements Hashing<TraversableOnce<Object>> {
/*     */     public int hash(TraversableOnce<Object> xs) {
/* 235 */       return MurmurHash3$.MODULE$.orderedHash(xs);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$3 implements Hashing<Product> {
/*     */     public int hash(Product x) {
/* 239 */       return MurmurHash3$.MODULE$.productHash(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$4 implements Hashing<String> {
/*     */     public int hash(String x) {
/* 243 */       return MurmurHash3$.MODULE$.stringHash(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$5 implements Hashing<TraversableOnce<Object>> {
/*     */     public int hash(TraversableOnce<Object> xs) {
/* 247 */       return MurmurHash3$.MODULE$.unorderedHash(xs);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\MurmurHash3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */