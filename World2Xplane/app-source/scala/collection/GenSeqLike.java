/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Equals;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\035daB\001\003!\003\r\ta\002\002\013\017\026t7+Z9MS.,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001Qc\001\005\0245M)\001!C\007\035?A\021!bC\007\002\t%\021A\002\002\002\004\003:L\b\003\002\b\020#ei\021AA\005\003!\t\021qbR3o\023R,'/\0312mK2K7.\032\t\003%Ma\001\001\002\004\025\001\021\025\r!\006\002\002\003F\021a#\003\t\003\025]I!\001\007\003\003\0179{G\017[5oOB\021!C\007\003\0077\001!)\031A\013\003\tI+\007O\035\t\003\025uI!A\b\003\003\r\025\013X/\0317t!\021q\001%\005\022\n\005\005\022!A\004)be\006dG.\0327ju\006\024G.\032\t\004G\031\nR\"\001\023\013\005\025\022\021\001\0039be\006dG.\0327\n\005\035\"#A\002)beN+\027\017C\003*\001\021\005!&\001\004%S:LG\017\n\013\002WA\021!\002L\005\003[\021\021A!\0268ji\")q\006\001D\001a\005\0311/Z9\026\003E\0022A\004\032\022\023\t\031$AA\002TKFDQ!\016\001\007\002Y\nQ!\0319qYf$\"!E\034\t\013a\"\004\031A\035\002\007%$\007\020\005\002\013u%\0211\b\002\002\004\023:$\b\"B\037\001\r\003q\024A\0027f]\036$\b.F\001:\021\025\001\005\001\"\001B\003-I7\017R3gS:,G-\021;\025\005\t+\005C\001\006D\023\t!EAA\004C_>dW-\0318\t\013az\004\031A\035\t\013\035\003a\021\001%\002\033M,w-\\3oi2+gn\032;i)\rI\024J\024\005\006\025\032\003\raS\001\002aB!!\002T\tC\023\tiEAA\005Gk:\034G/[8oc!)qJ\022a\001s\005!aM]8n\021\025\t\006\001\"\001S\0031\001(/\0324jq2+gn\032;i)\tI4\013C\003K!\002\0071\nC\003V\001\031\005a+\001\006j]\022,\007p\0265fe\026$2!O,Y\021\025QE\0131\001L\021\025yE\0131\001:\021\025)\006\001\"\001[)\tI4\fC\003K3\002\0071\nC\003^\001\021\005a,A\004j]\022,\007p\0244\026\005}\033GCA\035a\021\025\tG\f1\001c\003\021)G.Z7\021\005I\031G!\0023]\005\004)'!\001\"\022\005EI\001\"B/\001\t\0039WC\0015l)\rI\024\016\034\005\006C\032\004\rA\033\t\003%-$Q\001\0324C\002\025DQa\0244A\002eBQA\034\001\005\002=\f1\002\\1ti&sG-\032=PMV\021\001o\035\013\003sEDQ!Y7A\002I\004\"AE:\005\013\021l'\031A3\t\0139\004A\021A;\026\005YLHcA\035xu\")\021\r\036a\001qB\021!#\037\003\006IR\024\r!\032\005\006wR\004\r!O\001\004K:$\007\"B?\001\t\003q\030A\0047bgRLe\016Z3y/\",'/\032\013\003s}DQA\023?A\002-Ca! \001\007\002\005\rA#B\035\002\006\005\035\001B\002&\002\002\001\0071\n\003\004|\003\003\001\r!\017\005\b\003\027\001a\021AA\007\003\035\021XM^3sg\026,\022!\007\005\b\003#\001a\021AA\n\003)\021XM^3sg\026l\025\r]\013\007\003+\t\t$a\007\025\t\005]\0211\007\013\005\0033\ty\002E\002\023\0037!q!!\b\002\020\t\007QC\001\003UQ\006$\b\002CA\021\003\037\001\035!a\t\002\005\t4\007#CA\023\003WI\022qFA\r\033\t\t9CC\002\002*\t\tqaZ3oKJL7-\003\003\002.\005\035\"\001D\"b]\n+\030\016\0343Ge>l\007c\001\n\0022\0211A-a\004C\002UA\001\"!\016\002\020\001\007\021qG\001\002MB)!\002T\t\0020!9\0211\b\001\005\002\005u\022AC:uCJ$8oV5uQV!\021qHA')\r\021\025\021\t\005\t\003\007\nI\0041\001\002F\005!A\017[1u!\025q\021qIA&\023\r\tIE\001\002\007\017\026t7+Z9\021\007I\ti\005\002\004e\003s\021\r!\006\005\b\003w\001a\021AA)+\021\t\031&a\027\025\013\t\013)&!\030\t\021\005\r\023q\na\001\003/\002RADA$\0033\0022AEA.\t\031!\027q\nb\001+!9\021qLA(\001\004I\024AB8gMN,G\017C\004\002d\0011\t!!\032\002\021\025tGm],ji\",B!a\032\002pQ\031!)!\033\t\021\005\r\023\021\ra\001\003W\002RADA$\003[\0022AEA8\t\031!\027\021\rb\001+!9\0211\017\001\007\002\005U\024!\0029bi\016DWCBA<\003\013\013i\b\006\005\002z\005\035\025\021RAG)\021\tY(a \021\007I\ti\bB\004\002\036\005E$\031A\013\t\021\005\005\022\021\017a\002\003\003\003\022\"!\n\002,e\t\031)a\037\021\007I\t)\t\002\004e\003c\022\r!\032\005\007\037\006E\004\031A\035\t\021\005M\024\021\017a\001\003\027\003RADA$\003\007Cq!a$\002r\001\007\021(\001\005sKBd\027mY3e\021\035\t\031\n\001D\001\003+\013q!\0369eCR,G-\006\004\002\030\006\025\026Q\024\013\007\0033\0139+a+\025\t\005m\025q\024\t\004%\005uEaBA\017\003#\023\r!\006\005\t\003C\t\t\nq\001\002\"BI\021QEA\0263\005\r\0261\024\t\004%\005\025FA\0023\002\022\n\007Q\rC\004\002*\006E\005\031A\035\002\013%tG-\032=\t\017\005\f\t\n1\001\002$\"9\021q\026\001\007\002\005E\026a\003\023qYV\034HeY8m_:,b!a-\002B\006eF\003BA[\003\007$B!a.\002<B\031!#!/\005\017\005u\021Q\026b\001+!A\021\021EAW\001\b\ti\fE\005\002&\005-\022$a0\0028B\031!#!1\005\r\021\fiK1\001f\021\035\t\027Q\026a\001\003Cq!a2\001\r\003\tI-A\006%G>dwN\034\023qYV\034XCBAf\0033\f\t\016\006\003\002N\006mG\003BAh\003'\0042AEAi\t\035\ti\"!2C\002UA\001\"!\t\002F\002\017\021Q\033\t\n\003K\tY#GAl\003\037\0042AEAm\t\031!\027Q\031b\001K\"9\021-!2A\002\005]\007bBAp\001\031\005\021\021]\001\006a\006$Gk\\\013\007\003G\f\t0!;\025\r\005\025\0301_A|)\021\t9/a;\021\007I\tI\017B\004\002\036\005u'\031A\013\t\021\005\005\022Q\034a\002\003[\004\022\"!\n\002,e\ty/a:\021\007I\t\t\020\002\004e\003;\024\r!\032\005\b\003k\fi\0161\001:\003\raWM\034\005\bC\006u\007\031AAx\021\035\tY\020\001D\001\003{\f1bY8se\026\034\bo\0348egV!\021q B\007)\021\021\tAa\004\025\007\t\023\031\001C\004K\003s\004\rA!\002\021\017)\0219!\005B\006\005&\031!\021\002\003\003\023\031+hn\031;j_:\024\004c\001\n\003\016\0211A-!?C\002UA\001\"a\021\002z\002\007!\021\003\t\006\035\005\035#1\002\005\b\005+\001a\021\001B\f\003\025!xnU3r+\t\021I\002\005\003\017\003\017\n\002b\002B\017\001\021\005!qD\001\006k:LwN\\\013\007\005C\021yCa\n\025\t\t\r\"\021\007\013\005\005K\021I\003E\002\023\005O!q!!\b\003\034\t\007Q\003\003\005\002\"\tm\0019\001B\026!%\t)#a\013\032\005[\021)\003E\002\023\005_!a\001\032B\016\005\004)\007\002CA\"\0057\001\rAa\r\021\0139\t9E!\f\t\017\t]\002A\"\001\003:\005!A-\0334g+\021\021YDa\021\025\007e\021i\004\003\005\002D\tU\002\031\001B !\025q\021q\tB!!\r\021\"1\t\003\007I\nU\"\031A3\t\017\t\035\003A\"\001\003J\005I\021N\034;feN,7\r^\013\005\005\027\022\031\006F\002\032\005\033B\001\"a\021\003F\001\007!q\n\t\006\035\005\035#\021\013\t\004%\tMCA\0023\003F\t\007Q\rC\004\003X\0011\t!!\004\002\021\021L7\017^5oGRDqAa\027\001\t\003\022i&\001\005iCND7i\0343f)\005I\004b\002B1\001\021\005#1M\001\007KF,\030\r\\:\025\007\t\023)\007C\004\002D\t}\003\031A\005")
/*     */ public interface GenSeqLike<A, Repr> extends GenIterableLike<A, Repr>, Equals, Parallelizable<A, ParSeq<A>> {
/*     */   Seq<A> seq();
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   int length();
/*     */   
/*     */   boolean isDefinedAt(int paramInt);
/*     */   
/*     */   int segmentLength(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   int prefixLength(Function1<A, Object> paramFunction1);
/*     */   
/*     */   int indexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   int indexWhere(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <B> int indexOf(B paramB);
/*     */   
/*     */   <B> int indexOf(B paramB, int paramInt);
/*     */   
/*     */   <B> int lastIndexOf(B paramB);
/*     */   
/*     */   <B> int lastIndexOf(B paramB, int paramInt);
/*     */   
/*     */   int lastIndexWhere(Function1<A, Object> paramFunction1);
/*     */   
/*     */   int lastIndexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   Repr reverse();
/*     */   
/*     */   <B, That> That reverseMap(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> boolean startsWith(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> boolean startsWith(GenSeq<B> paramGenSeq, int paramInt);
/*     */   
/*     */   <B> boolean endsWith(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B, That> That patch(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That updated(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$colon(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $colon$plus(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That padTo(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<A, B, Object> paramFunction2);
/*     */   
/*     */   GenSeq<A> toSeq();
/*     */   
/*     */   <B, That> That union(GenSeq<B> paramGenSeq, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> Repr diff(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> Repr intersect(GenSeq<B> paramGenSeq);
/*     */   
/*     */   Repr distinct();
/*     */   
/*     */   int hashCode();
/*     */   
/*     */   boolean equals(Object paramObject);
/*     */   
/*     */   public class GenSeqLike$$anonfun$indexOf$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$1;
/*     */     
/*     */     public final boolean apply(Object x$1) {
/*     */       Object object;
/* 144 */       return (((object = this.elem$1) == x$1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, x$1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, x$1) : object.equals(x$1)))));
/*     */     }
/*     */     
/*     */     public GenSeqLike$$anonfun$indexOf$1(GenSeqLike $outer, Object elem$1) {}
/*     */   }
/*     */   
/*     */   public class GenSeqLike$$anonfun$lastIndexOf$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$2;
/*     */     
/*     */     public final boolean apply(Object x$2) {
/*     */       Object object;
/* 159 */       return (((object = this.elem$2) == x$2) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, x$2) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, x$2) : object.equals(x$2)))));
/*     */     }
/*     */     
/*     */     public GenSeqLike$$anonfun$lastIndexOf$1(GenSeqLike $outer, Object elem$2) {}
/*     */   }
/*     */   
/*     */   public class GenSeqLike$$anonfun$lastIndexOf$2 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$3;
/*     */     
/*     */     public final boolean apply(Object x$3) {
/*     */       Object object;
/* 172 */       return (((object = this.elem$3) == x$3) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, x$3) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, x$3) : object.equals(x$3)))));
/*     */     }
/*     */     
/*     */     public GenSeqLike$$anonfun$lastIndexOf$2(GenSeqLike $outer, Object elem$3) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */