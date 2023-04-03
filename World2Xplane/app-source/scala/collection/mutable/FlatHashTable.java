/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.Random;
/*     */ import scala.util.hashing.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t%eaB\001\003!\003\r\t!\003\002\016\r2\fG\017S1tQR\013'\r\\3\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025\005\0352c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\tA1\030Q\005\b\003#Ii\021AA\004\007'\tA\t\001\002\013\002\033\031c\027\r\036%bg\"$\026M\0317f!\t\tRC\002\004\002\005!\005AAF\n\003+-AQ\001G\013\005\002e\ta\001P5oSRtD#\001\013\t\013m)BQ\001\017\002\033M,W\rZ$f]\026\024\030\r^8s+\005i\002c\001\020$K5\tqD\003\002!C\005!A.\0318h\025\005\021\023\001\0026bm\006L!\001J\020\003\027QC'/Z1e\031>\034\027\r\034\t\003M%j\021a\n\006\003Q\031\tA!\036;jY&\021!f\n\002\007%\006tGm\\7\t\0131*B\021A\027\002#\021,g-Y;mi2{\027\r\032$bGR|'/F\001/!\taq&\003\0021\r\t\031\021J\034;\t\013I*BQA\027\002\0371|\027\r\032$bGR|'\017R3ok6DQ\001N\013\005\002U\n\001c]5{K\032{'\017\0265sKNDw\016\0343\025\00792\004\bC\0038g\001\007a&\001\003tSj,\007\"B\0354\001\004q\023aC0m_\006$g)Y2u_JDQaO\013\005\002q\nAB\\3x)\"\024Xm\0355pY\022$2AL\037?\021\025I$\b1\001/\021\0259$\b1\001/\r\021\001U\003A!\003\021\r{g\016^3oiN,\"AQ4\024\005}Z\001\002\003#@\005\013\007I\021A\027\002\0251|\027\r\032$bGR|'\017\003\005G\t\005\t\025!\003/\003-aw.\0313GC\016$xN\035\021\t\021!{$Q1A\005\002%\013Q\001^1cY\026,\022A\023\t\004\031-[\021B\001'\007\005\025\t%O]1z\021!quH!A!\002\023Q\025A\002;bE2,\007\005\003\005Q\t\025\r\021\"\001.\003%!\030M\0317f'&TX\r\003\005S\t\005\t\025!\003/\003)!\030M\0317f'&TX\r\t\005\t)~\022)\031!C\001[\005IA\017\033:fg\"|G\016\032\005\t-~\022\t\021)A\005]\005QA\017\033:fg\"|G\016\032\021\t\021a{$Q1A\005\0025\n\021b]3fIZ\fG.^3\t\021i{$\021!Q\001\n9\n!b]3fIZ\fG.^3!\021!avH!b\001\n\003i\026aB:ju\026l\027\r]\013\002=B\031Ab\023\030\t\021\001|$\021!Q\001\ny\013\001b]5{K6\f\007\017\t\005\0061}\"\tA\031\013\bGB\f(o\035;v!\r!w(Z\007\002+A\021am\032\007\001\t\025AwH1\001j\005\005\t\025C\0016n!\ta1.\003\002m\r\t9aj\034;iS:<\007C\001\007o\023\tygAA\002B]fDQ\001R1A\0029BQ\001S1A\002)CQ\001U1A\0029BQ\001V1A\0029BQ\001W1A\0029BQ\001X1A\002y3qa^\013\021\002\007\005\001PA\005ICNDW\013^5mgV\031\0210!\006\024\005Y\\\001\"B>w\t\003a\030A\002\023j]&$H\005F\001~!\taa0\003\002\000\r\t!QK\\5u\021\031\t\031A\036C\013[\005!2/\033>f\033\006\004()^2lKR\024\025\016^*ju\026Da!a\002w\t+i\023!E:ju\026l\025\r\035\"vG.,GoU5{K\"9\0211\002<\005\022\0055\021\001D3mK6D\025m\0355D_\022,Gc\001\030\002\020!A\021\021CA\005\001\004\t\031\"\001\003fY\026l\007c\0014\002\026\021)\001N\036b\001S\"9\021\021\004<\005\026\005m\021aB5naJ|g/\032\013\006]\005u\021\021\005\005\b\003?\t9\0021\001/\003\025A7m\0343f\021\035\t\031#a\006A\0029\nAa]3fIB\031a-a\n\005\013!\004!\031A5\t\013m\004A\021\001?\t\017\0055\002\001\"\004\0020\005QA/\0312mK\022+'-^4\026\005\005E\002c\001\007\0024%\031\021Q\007\004\003\017\t{w\016\\3b]\"A\021\b\001a\001\n\003!Q\006\003\006\002<\001\001\r\021\"\001\005\003{\tqb\0307pC\0224\025m\031;pe~#S-\035\013\004{\006}\002\"CA!\003s\t\t\0211\001/\003\rAH%\r\005\b\003\013\002\001\025)\003/\0031yFn\\1e\r\006\034Go\034:!Q\021\t\031%!\023\021\0071\tY%C\002\002N\031\021\021\002\036:b]NLWM\034;\t\017!\003\001\031!C\t\023\"I\0211\013\001A\002\023E\021QK\001\ni\006\024G.Z0%KF$2!`A,\021%\t\t%!\025\002\002\003\007!\n\003\004O\001\001\006KA\023\025\005\0033\nI\005C\004Q\001\001\007I\021C\027\t\023\005\005\004\0011A\005\022\005\r\024!\004;bE2,7+\033>f?\022*\027\017F\002~\003KB\021\"!\021\002`\005\005\t\031\001\030\t\rI\003\001\025)\003/Q\021\t9'!\023\t\017Q\003\001\031!C\t[!I\021q\016\001A\002\023E\021\021O\001\016i\"\024Xm\0355pY\022|F%Z9\025\007u\f\031\bC\005\002B\0055\024\021!a\001]!1a\013\001Q!\n9BC!!\036\002J!9A\f\001a\001\n#i\006\"CA?\001\001\007I\021CA@\003-\031\030N_3nCB|F%Z9\025\007u\f\t\tC\005\002B\005m\024\021!a\001=\"1\001\r\001Q!\nyCC!a!\002J!9\001\f\001a\001\n#i\003\"CAF\001\001\007I\021CAG\0035\031X-\0323wC2,Xm\030\023fcR\031Q0a$\t\023\005\005\023\021RA\001\002\004q\003B\002.\001A\003&a\006\013\003\002\022\006%\003bBAL\001\021E\021\021T\001\tG\006\004\030mY5usR\031a&a'\t\017\005u\025Q\023a\001]\005aQ\r\0379fGR,GmU5{K\"1\021\021\025\001\005\0025\n1\"\0338ji&\fGnU5{K\"1\021Q\025\001\005\n5\nq\"\0338ji&\fGnQ1qC\016LG/\037\005\007\003S\003A\021C\027\002\025I\fg\016Z8n'\026,G\r\003\004\002.\002!\t\"L\001\016i\006\024G.Z*ju\026\034V-\0323\t\021\005E\006\001\"\001\005\003g\013A!\0338jiR)Q0!.\002F\"A\021qWAX\001\004\tI,\001\002j]B!\0211XAa\033\t\tiLC\002\002@\006\n!![8\n\t\005\r\027Q\030\002\022\037\nTWm\031;J]B,Ho\025;sK\006l\007\002CAd\003_\003\r!!3\002\003\031\004b\001DAf\003Ki\030bAAg\r\tIa)\0368di&|g.\r\005\t\003#\004A\021\001\003\002T\006Y1/\032:jC2L'0\032+p)\ri\030Q\033\005\t\003/\fy\r1\001\002Z\006\031q.\036;\021\t\005m\0261\\\005\005\003;\fiL\001\nPE*,7\r^(viB,Ho\025;sK\006l\007bBAq\001\021E\0211]\001\nM&tG-\0228uef$B!!:\002lB)A\"a:\002&%\031\021\021\036\004\003\r=\003H/[8o\021!\t\t\"a8A\002\005\025\002bBAx\001\021E\021\021_\001\016G>tG/Y5og\026sGO]=\025\t\005E\0221\037\005\t\003#\ti\0171\001\002&!9\021q\037\001\005\n\005e\030!\0044j]\022,e\016\036:z\0236\004H\016F\002\f\003wD\001\"!\005\002v\002\007\021Q\005\005\b\003\004A\021\003B\001\003!\tG\rZ#oiJLH\003BA\031\005\007A\001\"!\005\002~\002\007\021Q\005\005\b\005\017\001A\021\003B\005\003-\021X-\\8wK\026sGO]=\025\t\005\025(1\002\005\t\003#\021)\0011\001\002&!9!q\002\001\005\022\tE\021\001C5uKJ\fGo\034:\026\005\tM\001C\002B\013\005/\t)#D\001\005\023\r\021I\002\002\002\t\023R,'/\031;pe\"1!Q\004\001\005\nq\f\021b\032:poR\013'\r\\3\t\r\t\005\002\001\"\003}\003=\031\007.Z2l\007>t7/[:uK:$\bb\002B\023\001\021E!qE\001\r]:\034\026N_3NCB\fE\r\032\013\004{\n%\002b\002B\026\005G\001\rAL\001\002Q\"9!q\006\001\005\022\tE\022a\0048o'&TX-T1q%\026lwN^3\025\007u\024\031\004C\004\003,\t5\002\031\001\030\t\017\t]\002\001\"\005\003:\005qaN\\*ju\026l\025\r\035*fg\026$HcA?\003<!9!Q\bB\033\001\004q\023a\003;bE2,G*\0328hi\"DqA!\021\001\t\013!Q&A\nu_R\fGnU5{K6\013\007OQ;dW\026$8\017C\004\003F\001!\tBa\022\002\037\r\fGnY*ju\026l\025\r]*ju\026$2A\fB%\021\035\021iDa\021A\0029BqA!\024\001\t#\021y%A\006tSj,W*\0319J]&$HcA?\003R!9!Q\bB&\001\004q\003B\002B+\001\021EA0A\013tSj,W*\0319J]&$\030I\0343SK\n,\030\016\0343\t\017\te\003\001\"\001\005y\006a\001O]5oiNK'0Z'ba\"9!Q\f\001\005\002\021a\030!\0049sS:$8i\0348uK:$8\017\003\004\003b\001!\t\002`\001\017g&TX-T1q\t&\034\030M\0317f\021\035\021)\007\001C\t\003_\t\001#[:TSj,W*\0319EK\032Lg.\0323\t\017\t%\004\001\"\005\0020\005\t\022\r\\<bsNLe.\033;TSj,W*\0319\t\017\t5\004\001\"\006\003p\005)\021N\0343fqR\031aF!\035\t\017\005}!1\016a\001]!1!Q\017\001\005\022q\f!b\0317fCJ$\026M\0317f\021!\021I\b\001C\001\t\tm\024!\0055bg\"$\026M\0317f\007>tG/\0328ugV\021!Q\020\t\005!}\n)\003C\004\003\002\002!\tBa!\002!%t\027\016^,ji\"\034uN\034;f]R\034HcA?\003\006\"A!q\021B@\001\004\021i(A\001d\001")
/*     */ public interface FlatHashTable<A> extends FlatHashTable.HashUtils<A> {
/*     */   int _loadFactor();
/*     */   
/*     */   @TraitSetter
/*     */   void _loadFactor_$eq(int paramInt);
/*     */   
/*     */   Object[] table();
/*     */   
/*     */   @TraitSetter
/*     */   void table_$eq(Object[] paramArrayOfObject);
/*     */   
/*     */   int tableSize();
/*     */   
/*     */   @TraitSetter
/*     */   void tableSize_$eq(int paramInt);
/*     */   
/*     */   int threshold();
/*     */   
/*     */   @TraitSetter
/*     */   void threshold_$eq(int paramInt);
/*     */   
/*     */   int[] sizemap();
/*     */   
/*     */   @TraitSetter
/*     */   void sizemap_$eq(int[] paramArrayOfint);
/*     */   
/*     */   int seedvalue();
/*     */   
/*     */   @TraitSetter
/*     */   void seedvalue_$eq(int paramInt);
/*     */   
/*     */   int capacity(int paramInt);
/*     */   
/*     */   int initialSize();
/*     */   
/*     */   int randomSeed();
/*     */   
/*     */   int tableSizeSeed();
/*     */   
/*     */   void init(ObjectInputStream paramObjectInputStream, Function1<A, BoxedUnit> paramFunction1);
/*     */   
/*     */   void serializeTo(ObjectOutputStream paramObjectOutputStream);
/*     */   
/*     */   Option<A> findEntry(A paramA);
/*     */   
/*     */   boolean containsEntry(A paramA);
/*     */   
/*     */   boolean addEntry(A paramA);
/*     */   
/*     */   Option<A> removeEntry(A paramA);
/*     */   
/*     */   Iterator<A> iterator();
/*     */   
/*     */   void nnSizeMapAdd(int paramInt);
/*     */   
/*     */   void nnSizeMapRemove(int paramInt);
/*     */   
/*     */   void nnSizeMapReset(int paramInt);
/*     */   
/*     */   int totalSizeMapBuckets();
/*     */   
/*     */   int calcSizeMapSize(int paramInt);
/*     */   
/*     */   void sizeMapInit(int paramInt);
/*     */   
/*     */   void sizeMapInitAndRebuild();
/*     */   
/*     */   void printSizeMap();
/*     */   
/*     */   void printContents();
/*     */   
/*     */   void sizeMapDisable();
/*     */   
/*     */   boolean isSizeMapDefined();
/*     */   
/*     */   boolean alwaysInitSizeMap();
/*     */   
/*     */   int index(int paramInt);
/*     */   
/*     */   void clearTable();
/*     */   
/*     */   Contents<A> hashTableContents();
/*     */   
/*     */   void initWithContents(Contents<A> paramContents);
/*     */   
/*     */   public class FlatHashTable$$anonfun$serializeTo$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/* 108 */       this.out$1.writeObject(x$1);
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anonfun$serializeTo$1(FlatHashTable $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   public class FlatHashTable$$anon$1 extends AbstractIterator<A> {
/* 188 */     private int i = 0;
/*     */     
/*     */     private int i() {
/* 188 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 188 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 190 */       for (; i() < (this.$outer.table()).length && this.$outer.table()[i()] == null; i_$eq(i() + 1));
/* 191 */       return (i() < (this.$outer.table()).length);
/*     */     }
/*     */     
/*     */     public A next() {
/* 194 */       i_$eq(i() + 1);
/* 194 */       return hasNext() ? (A)this.$outer.table()[i() - 1] : 
/* 195 */         (A)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anon$1(FlatHashTable $outer) {}
/*     */   }
/*     */   
/*     */   public class FlatHashTable$$anonfun$checkConsistent$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(int i) {
/* 215 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anonfun$checkConsistent$1(FlatHashTable $outer) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 216 */       if (this.$outer.table()[i] != null && !this.$outer.containsEntry(this.$outer.table()[i])) {
/* 217 */         FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 = new FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1(this, i);
/* 217 */         Predef$ predef$ = Predef$.MODULE$;
/* 217 */         if (!false)
/* 217 */           throw new AssertionError((new StringBuilder()).append("assertion failed: ").append(flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1.apply()).toString()); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int i$1;
/*     */       
/*     */       public final String apply() {
/* 217 */         return (new StringBuilder()).append(this.i$1).append(" ").append(this.$outer.$outer.table()[this.i$1]).append(" ").append(Predef$.MODULE$.refArrayOps(this.$outer.$outer.table()).mkString()).toString();
/*     */       }
/*     */       
/*     */       public FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1(FlatHashTable$$anonfun$checkConsistent$1 $outer, int i$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FlatHashTable$$anon$2 extends ThreadLocal<Random> {
/*     */     public Random initialValue() {
/* 359 */       return new Random();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Contents<A> {
/*     */     private final int loadFactor;
/*     */     
/*     */     private final Object[] table;
/*     */     
/*     */     private final int tableSize;
/*     */     
/*     */     private final int threshold;
/*     */     
/*     */     private final int seedvalue;
/*     */     
/*     */     private final int[] sizemap;
/*     */     
/*     */     public int loadFactor() {
/* 376 */       return this.loadFactor;
/*     */     }
/*     */     
/*     */     public Contents(int loadFactor, Object[] table, int tableSize, int threshold, int seedvalue, int[] sizemap) {}
/*     */     
/*     */     public Object[] table() {
/* 377 */       return this.table;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/* 378 */       return this.tableSize;
/*     */     }
/*     */     
/*     */     public int threshold() {
/* 379 */       return this.threshold;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/* 380 */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/* 381 */       return this.sizemap;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class HashUtils$class {
/*     */     public static void $init$(FlatHashTable.HashUtils $this) {}
/*     */     
/*     */     public static final int sizeMapBucketBitSize(FlatHashTable.HashUtils $this) {
/* 385 */       return 5;
/*     */     }
/*     */     
/*     */     public static final int sizeMapBucketSize(FlatHashTable.HashUtils $this) {
/* 387 */       return 1 << $this.sizeMapBucketBitSize();
/*     */     }
/*     */     
/*     */     public static int elemHashCode(FlatHashTable.HashUtils $this, Object elem) {
/* 390 */       if (elem == null)
/* 390 */         throw new IllegalArgumentException("Flat hash tables cannot contain null elements."); 
/* 390 */       return 
/* 391 */         elem.hashCode();
/*     */     }
/*     */     
/*     */     public static final int improve(FlatHashTable.HashUtils $this, int hcode, int seed) {
/* 399 */       int improved = package$.MODULE$.byteswap32(hcode);
/* 403 */       int rotation = seed % 32;
/* 404 */       int rotated = improved >>> rotation | improved << 32 - rotation;
/* 405 */       return rotated;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface HashUtils<A> {
/*     */     int sizeMapBucketBitSize();
/*     */     
/*     */     int sizeMapBucketSize();
/*     */     
/*     */     int elemHashCode(A param1A);
/*     */     
/*     */     int improve(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\FlatHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */