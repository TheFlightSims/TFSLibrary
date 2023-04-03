/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.DebugUtils$;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.util.hashing.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t}haB\001\003!\003\r\t!\003\002\n\021\006\034\b\016V1cY\026T!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U)!\"!\022\002lM\031\001aC\b\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021i\005\rcBA\t\023\033\005\021qAB\n\003\021\003!A#A\005ICNDG+\0312mKB\021\021#\006\004\007\003\tA\t\001\002\f\024\005UY\001\"\002\r\026\t\003I\022A\002\037j]&$h\bF\001\025\021\031YR\003\"\002\0059\005\tB-\0324bk2$Hj\\1e\r\006\034Go\034:\026\003u\001\"\001\004\020\n\005}1!aA%oi\"1\021%\006C\003\tq\tq\002\\8bI\032\0137\r^8s\t\026tW/\034\005\007GU!)\001\002\023\002\0319,w\017\0265sKNDw\016\0343\025\007u)s\005C\003'E\001\007Q$A\006`Y>\fGMR1di>\024\b\"\002\025#\001\004i\022\001B:ju\026DaAK\013\005\006\021Y\023\001E:ju\0264uN\035+ie\026\034\bn\0347e)\riB&\f\005\006M%\002\r!\b\005\006]%\002\r!H\001\004i\"\024\bB\002\031\026\t\013!\021'\001\005dCB\f7-\033;z)\ti\"\007C\0034_\001\007Q$\001\007fqB,7\r^3e'&TXMB\0046+A\005\031\021\001\034\003\023!\0137\017[+uS2\034XCA\034J'\t!4\002C\003:i\021\005!(\001\004%S:LG\017\n\013\002wA\021A\002P\005\003{\031\021A!\0268ji\")q\b\016C\0139\005!2/\033>f\033\006\004()^2lKR\024\025\016^*ju\026DQ!\021\033\005\026q\t\021c]5{K6\013\007OQ;dW\026$8+\033>f\021\025\031E\007\"\005E\0031)G.Z7ICND7i\0343f)\tiR\tC\003G\005\002\007q)A\002lKf\004\"\001S%\r\001\021)!\n\016b\001\027\n91*Z=UsB,\027C\001'P!\taQ*\003\002O\r\t9aj\034;iS:<\007C\001\007Q\023\t\tfAA\002B]fDQa\025\033\005\026Q\013q![7qe>4X\rF\002\036+^CQA\026*A\002u\tQ\001[2pI\026DQ\001\027*A\002u\tAa]3fI\"1!,\006C\001\tm\013!\002]8xKJ|e\rV<p)\tiB\fC\003^3\002\007Q$\001\004uCJ<W\r\036\004\005?V\001\001M\001\005D_:$XM\034;t+\r\t\007o]\n\003=.A\001b\0310\003\006\004%\t\001H\001\013Y>\fGMR1di>\024\b\002C3_\005\003\005\013\021B\017\002\0271|\027\r\032$bGR|'\017\t\005\tOz\023)\031!C\001Q\006)A/\0312mKV\t\021\016E\002\rU2L!a\033\004\003\013\005\023(/Y=\021\tEiwN]\005\003]\n\021\021\002S1tQ\026sGO]=\021\005!\003H!B9_\005\004Y%!A!\021\005!\033H!\002;_\005\004)(!B#oiJL\030C\001<m!\taq/\003\002y\r\t!a*\0367m\021!QhL!A!\002\023I\027A\002;bE2,\007\005\003\005}=\n\025\r\021\"\001\035\003%!\030M\0317f'&TX\r\003\005=\n\005\t\025!\003\036\003)!\030M\0317f'&TX\r\t\005\n\003\003q&Q1A\005\002q\t\021\002\0365sKNDw\016\0343\t\023\005\025aL!A!\002\023i\022A\003;ie\026\034\bn\0347eA!I\021\021\0020\003\006\004%\t\001H\001\ng\026,GM^1mk\026D\021\"!\004_\005\003\005\013\021B\017\002\025M,W\r\032<bYV,\007\005\003\006\002\022y\023)\031!C\001\003'\tqa]5{K6\f\007/\006\002\002\026A\031AB[\017\t\025\005eaL!A!\002\023\t)\"\001\005tSj,W.\0319!\021\031Ab\f\"\001\002\036Qq\021qDA\022\003K\t9#!\013\002,\0055\002#BA\021=>\024X\"A\013\t\r\r\fY\0021\001\036\021\0319\0271\004a\001S\"1A0a\007A\002uAq!!\001\002\034\001\007Q\004C\004\002\n\005m\001\031A\017\t\021\005E\0211\004a\001\003+A\001\"!\r_\t\003!\0211G\001\021I\026\024WoZ%oM>\024X.\031;j_:,\"!!\016\021\t\005]\022Q\b\b\004\031\005e\022bAA\036\r\0051\001K]3eK\032LA!a\020\002B\t11\013\036:j]\036T1!a\017\007!\rA\025Q\t\003\006c\002\021\ra\023\005\006s\001!\tA\017\005\bM\001\001\r\021\"\005\035\021%\ti\005\001a\001\n#\ty%A\b`Y>\fGMR1di>\024x\fJ3r)\rY\024\021\013\005\n\003'\nY%!AA\002u\t1\001\037\0232\021\035\t9\006\001Q!\nu\tAb\0307pC\0224\025m\031;pe\002BC!!\026\002\\A\031A\"!\030\n\007\005}cAA\005ue\006t7/[3oi\"Aq\r\001a\001\n#\t\031'\006\002\002fA!AB[A4!\031\tR.a\021\002jA\031\001*a\033\005\rQ\004!\031AA7#\r1\030q\r\005\n\003c\002\001\031!C\t\003g\n\021\002^1cY\026|F%Z9\025\007m\n)\b\003\006\002T\005=\024\021!a\001\003KBqA\037\001!B\023\t)\007\013\003\002x\005m\003b\002?\001\001\004%\t\002\b\005\n\003\002\001\031!C\t\003\003\013Q\002^1cY\026\034\026N_3`I\025\fHcA\036\002\004\"I\0211KA?\003\003\005\r!\b\005\007}\002\001\013\025B\017)\t\005\025\0251\f\005\t\003\003\001\001\031!C\t9!I\021Q\022\001A\002\023E\021qR\001\016i\"\024Xm\0355pY\022|F%Z9\025\007m\n\t\nC\005\002T\005-\025\021!a\001;!9\021Q\001\001!B\023i\002\006BAJ\0037B\021\"!\005\001\001\004%\t\"a\005\t\023\005m\005\0011A\005\022\005u\025aC:ju\026l\027\r]0%KF$2aOAP\021)\t\031&!'\002\002\003\007\021Q\003\005\t\0033\001\001\025)\003\002\026!\"\021\021UA.\021!\tI\001\001a\001\n#a\002\"CAU\001\001\007I\021CAV\0035\031X-\0323wC2,Xm\030\023fcR\0311(!,\t\023\005M\023qUA\001\002\004i\002bBA\007\001\001\006K!\b\025\005\003_\013Y\006\003\004\0026\002!\t\002H\001\016i\006\024G.Z*ju\026\034V-\0323\t\r\005e\006\001\"\005\035\003-Ig.\033;jC2\034\026N_3\t\017\005u\006\001\"\003\002@\006\001\022N\\5uS\006dG\013\033:fg\"|G\016\032\013\004;\005\005\007B\002\024\002<\002\007Q\004\003\004\002F\002!I\001H\001\020S:LG/[1m\007\006\004\030mY5us\"1\021\021\032\001\005\nq\t!\003\\1tiB{\007/\0367bi\026$\027J\0343fq\"A\021Q\032\001\005\002\021\ty-\001\003j]&$H#B\036\002R\006\025\b\002CAj\003\027\004\r!!6\002\005%t\007\003BAl\003Cl!!!7\013\t\005m\027Q\\\001\003S>T!!a8\002\t)\fg/Y\005\005\003G\fINA\tPE*,7\r^%oaV$8\013\036:fC6D\021\"a:\002L\022\005\r!!;\002\023I,\027\rZ#oiJL\b#\002\007\002l\006%\024bAAw\r\tAAHY=oC6,g\b\003\005\002r\002!\t\001BAz\003-\031XM]5bY&TX\rV8\025\013m\n)0a@\t\021\005]\030q\036a\001\003s\f1a\\;u!\021\t9.a?\n\t\005u\030\021\034\002\023\037\nTWm\031;PkR\004X\017^*ue\026\fW\016\003\005\003\002\005=\b\031\001B\002\003)9(/\033;f\013:$(/\037\t\007\031\t\025\021\021N\036\n\007\t\035aAA\005Gk:\034G/[8oc!9!1\002\001\005\022\t5\021!\0034j]\022,e\016\036:z)\021\tIGa\004\t\017\031\023I\0011\001\002D!A!1\003\001!\n\023\021)\"\001\006gS:$WI\034;ssB\"b!!\033\003\030\te\001b\002$\003\022\001\007\0211\t\005\b\0057\021\t\0021\001\036\003\005A\007b\002B\020\001\021E!\021E\001\tC\022$WI\034;ssR\0311Ha\t\t\021\t\025\"Q\004a\001\003S\n\021!\032\005\t\005S\001\001\025\"\003\003,\005I\021\r\0323F]R\024\030\020\r\013\006w\t5\"q\006\005\t\005K\0219\0031\001\002j!9!1\004B\024\001\004i\002b\002B\032\001\021E!QG\001\017M&tGm\024:BI\022,e\016\036:z+\021\0219D!\021\025\r\005%$\021\bB\036\021\0351%\021\007a\001\003\007B\001B!\020\0032\001\007!qH\001\006m\006dW/\032\t\004\021\n\005Ca\002B\"\005c\021\ra\023\002\002\005\"9!q\t\001\007\022\t%\023AD2sK\006$XMT3x\013:$(/_\013\005\005\027\022\031\006\006\004\002j\t5#q\n\005\b\r\n\025\003\031AA\"\021!\021iD!\022A\002\tE\003c\001%\003T\0219!1\tB#\005\004Y\005b\002B,\001\021E!\021L\001\fe\026lwN^3F]R\024\030\020\006\003\002j\tm\003b\002$\003V\001\007\0211\t\005\b\005?\002A\021\003B1\003=)g\016\036:jKNLE/\032:bi>\024XC\001B2!\031\021)Ga\032\002j5\tA!C\002\003j\021\021\001\"\023;fe\006$xN\035\005\b\005[\002A\021\003B8\00311wN]3bG\",e\016\036:z+\021\021\tHa\037\025\007m\022\031\b\003\005\003v\t-\004\031\001B<\003\0051\007c\002\007\003\006\005%$\021\020\t\004\021\nmDa\002B?\005W\022\ra\023\002\002+\"1!\021\021\001\005\022i\n!b\0317fCJ$\026M\0317f\021\035\021)\t\001C\005\005\017\013aA]3tSj,GcA\036\003\n\"9!1\022BB\001\004i\022a\0028foNK'0\032\005\b\005\037\003A\021\003BI\0031qgnU5{K6\013\007/\0213e)\rY$1\023\005\b\0057\021i\t1\001\036\021\035\0219\n\001C\t\0053\013qB\0348TSj,W*\0319SK6|g/\032\013\004w\tm\005b\002B\016\005+\003\r!\b\005\b\005?\003A\021\003BQ\0039qgnU5{K6\013\007OU3tKR$2a\017BR\021\035\021)K!(A\002u\t1\002^1cY\026dUM\\4uQ\"9!\021\026\001\005\006\021a\022a\005;pi\006d7+\033>f\033\006\004()^2lKR\034\bb\002BW\001\021E!qV\001\020G\006d7mU5{K6\013\007oU5{KR\031QD!-\t\017\t\025&1\026a\001;!9!Q\027\001\005\022\t]\026aC:ju\026l\025\r]%oSR$2a\017B]\021\035\021)Ka-A\002uAaA!0\001\t#Q\024!F:ju\026l\025\r]%oSR\fe\016\032*fEVLG\016\032\005\b\005\003\004A\021\001\003;\0031\001(/\0338u'&TX-T1q\021\031\021)\r\001C\tu\005q1/\033>f\033\006\004H)[:bE2,\007b\002Be\001\021E!1Z\001\021SN\034\026N_3NCB$UMZ5oK\022,\"A!4\021\0071\021y-C\002\003R\032\021qAQ8pY\026\fg\016C\004\003V\002!\tBa3\002#\005dw/Y=t\023:LGoU5{K6\013\007\017C\004\003Z\002!\tBa7\002\025\025dW-\\#rk\006d7\017\006\004\003N\nu'\021\035\005\t\005?\0249\0161\001\002D\005!1.Z=2\021!\021\031Oa6A\002\005\r\023\001B6fsJBqAa:\001\t+\021I/A\003j]\022,\007\020F\002\036\005WDaA\026Bs\001\004i\002b\002Bx\001\021E!\021_\001\021S:LGoV5uQ\016{g\016^3oiN$2a\017Bz\021!\021)P!<A\002\t]\030!A2\021\rAq\0261IA5\021!\021Y\020\001C\001\t\tu\030!\0055bg\"$\026M\0317f\007>tG/\0328ugV\021!q\037")
/*     */ public interface HashTable<A, Entry extends HashEntry<A, Entry>> extends HashTable.HashUtils<A> {
/*     */   int _loadFactor();
/*     */   
/*     */   @TraitSetter
/*     */   void _loadFactor_$eq(int paramInt);
/*     */   
/*     */   HashEntry<A, Entry>[] table();
/*     */   
/*     */   @TraitSetter
/*     */   void table_$eq(HashEntry<A, Entry>[] paramArrayOfHashEntry);
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
/*     */   int tableSizeSeed();
/*     */   
/*     */   int initialSize();
/*     */   
/*     */   void init(ObjectInputStream paramObjectInputStream, Function0<Entry> paramFunction0);
/*     */   
/*     */   void serializeTo(ObjectOutputStream paramObjectOutputStream, Function1<Entry, BoxedUnit> paramFunction1);
/*     */   
/*     */   Entry findEntry(A paramA);
/*     */   
/*     */   void addEntry(Entry paramEntry);
/*     */   
/*     */   <B> Entry findOrAddEntry(A paramA, B paramB);
/*     */   
/*     */   <B> Entry createNewEntry(A paramA, B paramB);
/*     */   
/*     */   Entry removeEntry(A paramA);
/*     */   
/*     */   Iterator<Entry> entriesIterator();
/*     */   
/*     */   <U> void foreachEntry(Function1<Entry, U> paramFunction1);
/*     */   
/*     */   void clearTable();
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
/*     */   void sizeMapDisable();
/*     */   
/*     */   boolean isSizeMapDefined();
/*     */   
/*     */   boolean alwaysInitSizeMap();
/*     */   
/*     */   boolean elemEquals(A paramA1, A paramA2);
/*     */   
/*     */   int index(int paramInt);
/*     */   
/*     */   void initWithContents(Contents<A, Entry> paramContents);
/*     */   
/*     */   Contents<A, Entry> hashTableContents();
/*     */   
/*     */   public class HashTable$$anon$1 extends AbstractIterator<Entry> {
/*     */     private final HashEntry<A, Entry>[] iterTable;
/*     */     
/*     */     private int idx;
/*     */     
/*     */     private HashEntry<A, Entry> es;
/*     */     
/*     */     public HashTable$$anon$1(HashTable<A, Entry> $outer) {
/* 203 */       this.iterTable = $outer.table();
/* 204 */       this.idx = HashTable$class.scala$collection$mutable$HashTable$$lastPopulatedIndex($outer);
/* 205 */       this.es = iterTable()[idx()];
/*     */     }
/*     */     
/*     */     private HashEntry<A, Entry>[] iterTable() {
/*     */       return this.iterTable;
/*     */     }
/*     */     
/*     */     private int idx() {
/*     */       return this.idx;
/*     */     }
/*     */     
/*     */     private void idx_$eq(int x$1) {
/*     */       this.idx = x$1;
/*     */     }
/*     */     
/*     */     private HashEntry<A, Entry> es() {
/* 205 */       return this.es;
/*     */     }
/*     */     
/*     */     private void es_$eq(HashEntry<A, Entry> x$1) {
/* 205 */       this.es = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 207 */       return !(es() == null);
/*     */     }
/*     */     
/*     */     public Entry next() {
/* 209 */       HashEntry<A, Entry> res = es();
/* 210 */       es_$eq((HashEntry<A, Entry>)es().next());
/* 211 */       while (es() == null && idx() > 0) {
/* 212 */         idx_$eq(idx() - 1);
/* 213 */         es_$eq(iterTable()[idx()]);
/*     */       } 
/* 215 */       return (Entry)res;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class HashUtils$class {
/*     */     public static void $init$(HashTable.HashUtils $this) {}
/*     */     
/*     */     public static final int sizeMapBucketBitSize(HashTable.HashUtils $this) {
/* 394 */       return 5;
/*     */     }
/*     */     
/*     */     public static final int sizeMapBucketSize(HashTable.HashUtils $this) {
/* 396 */       return 1 << $this.sizeMapBucketBitSize();
/*     */     }
/*     */     
/*     */     public static int elemHashCode(HashTable.HashUtils $this, Object key) {
/* 398 */       return ScalaRunTime$.MODULE$.hash(key);
/*     */     }
/*     */     
/*     */     public static final int improve(HashTable.HashUtils $this, int hcode, int seed) {
/* 426 */       int i = package$.MODULE$.byteswap32(hcode);
/* 449 */       int rotation = seed % 32;
/* 450 */       int rotated = i >>> rotation | i << 32 - rotation;
/* 451 */       return rotated;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Contents<A, Entry extends HashEntry<A, Entry>> {
/*     */     private final int loadFactor;
/*     */     
/*     */     private final HashEntry<A, Entry>[] table;
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
/* 470 */       return this.loadFactor;
/*     */     }
/*     */     
/*     */     public Contents(int loadFactor, HashEntry[] table, int tableSize, int threshold, int seedvalue, int[] sizemap) {}
/*     */     
/*     */     public HashEntry<A, Entry>[] table() {
/* 471 */       return this.table;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/* 472 */       return this.tableSize;
/*     */     }
/*     */     
/*     */     public int threshold() {
/* 473 */       return this.threshold;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/* 474 */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/* 475 */       return this.sizemap;
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 478 */       return DebugUtils$.MODULE$.buildString(
/* 479 */           (Function1)new HashTable$Contents$$anonfun$debugInformation$1(this));
/*     */     }
/*     */     
/*     */     public class HashTable$Contents$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<Object, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public HashTable$Contents$$anonfun$debugInformation$1(HashTable.Contents $outer) {}
/*     */       
/*     */       public final void apply(Function1 append) {
/* 480 */         append.apply("Hash table contents");
/* 481 */         append.apply("-------------------");
/* 482 */         append.apply((new StringBuilder()).append("Table: [").append(DebugUtils$.MODULE$.arrayString(this.$outer.table(), 0, (this.$outer.table()).length)).append("]").toString());
/* 483 */         append.apply((new StringBuilder()).append("Table size: ").append(BoxesRunTime.boxToInteger(this.$outer.tableSize())).toString());
/* 484 */         append.apply((new StringBuilder()).append("Load factor: ").append(BoxesRunTime.boxToInteger(this.$outer.loadFactor())).toString());
/* 485 */         append.apply((new StringBuilder()).append("Seedvalue: ").append(BoxesRunTime.boxToInteger(this.$outer.seedvalue())).toString());
/* 486 */         append.apply((new StringBuilder()).append("Threshold: ").append(BoxesRunTime.boxToInteger(this.$outer.threshold())).toString());
/* 487 */         append.apply((new StringBuilder()).append("Sizemap: [").append(DebugUtils$.MODULE$.arrayString(this.$outer.sizemap(), 0, (this.$outer.sizemap()).length)).append("]").toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface HashUtils<KeyType> {
/*     */     int sizeMapBucketBitSize();
/*     */     
/*     */     int sizeMapBucketSize();
/*     */     
/*     */     int elemHashCode(KeyType param1KeyType);
/*     */     
/*     */     int improve(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */