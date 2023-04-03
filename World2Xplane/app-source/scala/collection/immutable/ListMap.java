/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tEr!B\001\003\021\003I\021a\002'jgRl\025\r\035\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\021\005)YQ\"\001\002\007\0131\021\001\022A\007\003\0171K7\017^'baN\0311BD\031\021\007=\021B#D\001\021\025\t\tB!A\004hK:,'/[2\n\005M\001\"aE%n[V$\030M\0317f\033\006\004h)Y2u_JL\bC\001\006\026\r\021a!\001\001\f\026\007]i\002fE\003\0261)j\023\007\005\003\0133m9\023B\001\016\003\005-\t%m\035;sC\016$X*\0319\021\005qiB\002\001\003\006=U\021\ra\b\002\002\003F\021\001\005\n\t\003C\tj\021AB\005\003G\031\021qAT8uQ&tw\r\005\002\"K%\021aE\002\002\004\003:L\bC\001\017)\t\031IS\003\"b\001?\t\t!\t\005\003\013Wm9\023B\001\027\003\005\ri\025\r\035\t\006\0259Zr\005M\005\003_\t\021q!T1q\031&\\W\r\005\003\013+m9\003CA\0213\023\t\031dA\001\007TKJL\027\r\\5{C\ndW\rC\0036+\021\005a'\001\004=S:LGO\020\013\002a!)\001(\006C!s\005)Q-\0349usV\t!\b\005\003\013+m\001\003\"\002\037\026\t\003j\024\001B:ju\026,\022A\020\t\003C}J!\001\021\004\003\007%sG\017C\003C+\021\0051)A\002hKR$\"\001R$\021\007\005*u%\003\002G\r\t1q\n\035;j_:DQ\001S!A\002m\t1a[3z\021\025QU\003\"\021L\003\035)\b\017Z1uK\022,\"\001T(\025\0075\0236\013\005\003\013+mq\005C\001\017P\t\025\001\026J1\001R\005\t\021\025'\005\002(I!)\001*\023a\0017!)A+\023a\001\035\006)a/\0317vK\")a+\006C\001/\006)A\005\0357vgV\021\001l\027\013\0033r\003BAC\013\0345B\021Ad\027\003\006!V\023\r!\025\005\006;V\003\rAX\001\003WZ\004B!I0\0345&\021\001M\002\002\007)V\004H.\032\032\t\013Y+B\021\t2\026\005\r4G\003\0023hU2\004BAC\013\034KB\021AD\032\003\006!\006\024\r!\025\005\006Q\006\004\r![\001\006K2,W.\r\t\005C}[R\rC\003lC\002\007\021.A\003fY\026l'\007C\003nC\002\007a.A\003fY\026l7\017E\002\"_&L!\001\035\004\003\025q\022X\r]3bi\026$g\bC\003s+\021\0053/\001\006%a2,8\017\n9mkN,\"\001^<\025\005UD\b\003\002\006\0267Y\004\"\001H<\005\013A\013(\031A)\t\013e\f\b\031\001>\002\005a\034\bcA>}}6\tA!\003\002~\t\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\021\tsl\007<\t\017\005\005Q\003\"\001\002\004\0051A%\\5okN$2\001MA\003\021\025Au\0201\001\034\021\035\tI!\006C\001\003\027\t\001\"\033;fe\006$xN]\013\003\003\033\001Ra_A\b\003'I1!!\005\005\005!IE/\032:bi>\024\b\003B\021`7\035Ba\001S\013\005\022\005]Q#A\016\t\rQ+B\021CA\016+\0059\003bBA\020+\021\005\023\021E\001\005i\006LG.F\0011\r\031\t)#\006\005\002(\t!aj\0343f+\021\tI#a\f\024\013\005\r\0221F\031\021\013))2$!\f\021\007q\ty\003\002\004Q\003G\021\r!\025\005\013\021\006\r\"Q1A\005R\005]\001BCA\033\003G\021\t\021)A\0057\005!1.Z=!\021)!\0261\005BC\002\023E\023\021H\013\003\003[A1\"!\020\002$\t\005\t\025!\003\002.\0051a/\0317vK\002Bq!NA\022\t\003\t\t\005\006\004\002D\005\035\023\021\n\t\007\003\013\n\031#!\f\016\003UAa\001SA \001\004Y\002b\002+\002@\001\007\021Q\006\005\007y\005\rB\021I\037\t\021\005=\0231\005C\005\003#\nQa]5{KB\"RAPA*\003/B\001\"!\026\002N\001\007\0211F\001\004GV\024\bbBA-\003\033\002\rAP\001\004C\016\034\007\006BA'\003;\002B!a\030\002f5\021\021\021\r\006\004\003G2\021AC1o]>$\030\r^5p]&!\021qMA1\005\035!\030-\0337sK\016D\001\"a\033\002$\021\005\023QN\001\bSN,U\016\035;z+\t\ty\007E\002\"\003cJ1!a\035\007\005\035\021un\0347fC:D\001\"a\036\002$\021\005\023\021P\001\006CB\004H.\037\013\005\003[\tY\bC\004\002~\005U\004\031A\016\002\003-D\001\"!!\002$\021%\0211Q\001\007CB\004H.\037\031\025\r\0055\022QQAD\021!\t)&a A\002\005-\002bBA?\003\002\ra\007\025\005\003\ni\006C\004C\003G!\t%!$\025\t\005=\025\021\023\t\005C\025\013i\003C\004\002~\005-\005\031A\016\t\021\005U\0251\005C\005\003/\013AaZ3uaQ1\021qRAM\0037C\001\"!\026\002\024\002\007\0211\006\005\b\003{\n\031\n1\001\034Q\021\t\031*!\030\t\017)\013\031\003\"\021\002\"V!\0211UAU)\031\t)+a,\0022B)!\"F\016\002(B\031A$!+\005\021\005-\026q\024b\001\003[\023!A\021\032\022\007\0055B\005C\004\002~\005}\005\031A\016\t\021\005M\026q\024a\001\003O\013\021A\036\005\t\003\003\t\031\003\"\021\0028R!\0211FA]\021\035\ti(!.A\002mA\001\"a\b\002$\021\005\023QX\013\003\003WAc!a\t\002B\006\035\007cA\021\002D&\031\021Q\031\004\003!M+'/[1m-\026\0248/[8o+&#e\004\003TsGU\n7V@z)\013U\t\t-a3\037\021\021i\003\r`E\025\021.Fa!N\006\005\002\005=G#A\005\t\017\005M7\002b\001\002V\006a1-\0318Ck&dGM\022:p[V1\021q[Ax\003g,\"!!7\021\023=\tY.a8\002l\006U\030bAAo!\ta1)\0318Ck&dGM\022:p[B!\021\021]Ar\033\005Y\021\002BAs\003O\024AaQ8mY&\031\021\021\036\t\003\033\035+g.T1q\r\006\034Go\034:z!\031\ts,!<\002rB\031A$a<\005\ry\t\tN1\001 !\ra\0221\037\003\007S\005E'\031A\020\021\r))\022Q^Ay\021\031A4\002\"\001\002zV1\0211 B\001\005\013)\"!!@\021\r))\022q B\002!\ra\"\021\001\003\007=\005](\031A\020\021\007q\021)\001\002\004*\003o\024\raH\004\b\005\023Y\001\022\002B\006\0031)U\016\035;z\031&\034H/T1q!\021\t\tO!\004\007\017\t=1\002#\003\003\022\taQ)\0349us2K7\017^'baN!!Q\002B\n!\021QQ\003\n\021\t\017U\022i\001\"\001\003\030Q\021!1\002\005\013\0057\021i!!A\005\n\tu\021a\003:fC\022\024Vm]8mm\026$\"Aa\b\021\t\t\005\"1F\007\003\005GQAA!\n\003(\005!A.\0318h\025\t\021I#\001\003kCZ\f\027\002\002B\027\005G\021aa\0242kK\016$\b\"\003B\016\027\005\005I\021\002B\017\001")
/*     */ public class ListMap<A, B> extends AbstractMap<A, B> implements Map<A, B>, MapLike<A, B, ListMap<A, B>>, Serializable {
/*     */   public static final long serialVersionUID = 301002838095710379L;
/*     */   
/*     */   public static class EmptyListMap$ extends ListMap<Object, scala.runtime.Nothing$> {
/*     */     public static final EmptyListMap$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/*  31 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyListMap$() {
/*  31 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public ListMap<A, scala.runtime.Nothing$> empty() {
/*  57 */     return ListMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public int size() {
/*  63 */     return 0;
/*     */   }
/*     */   
/*     */   public Option<B> get(Object key) {
/*  71 */     return (Option<B>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public <B1> ListMap<A, B1> updated(Object key, Object value) {
/*  81 */     return new Node(this, (A)key, value);
/*     */   }
/*     */   
/*     */   public <B1> ListMap<A, B1> $plus(Tuple2 kv) {
/*  87 */     return updated((A)kv._1(), (B1)kv._2());
/*     */   }
/*     */   
/*     */   public <B1> ListMap<A, B1> $plus(Tuple2<?, ?> elem1, Tuple2 elem2, Seq elems) {
/*  98 */     return $plus(elem1).$plus(elem2).$plus$plus((GenTraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public <B1> ListMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 106 */     ListMap listMap = (ListMap)repr();
/* 106 */     return (ListMap<A, B1>)xs.seq().$div$colon(listMap, (Function2)new ListMap$$anonfun$$plus$plus$1(this));
/*     */   }
/*     */   
/*     */   public class ListMap$$anonfun$$plus$plus$1 extends AbstractFunction2<ListMap<A, B1>, Tuple2<A, B1>, ListMap<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ListMap<A, B1> apply(ListMap x$2, Tuple2 x$3) {
/* 106 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public ListMap$$anonfun$$plus$plus$1(ListMap $outer) {}
/*     */   }
/*     */   
/*     */   public ListMap<A, B> $minus(Object key) {
/* 114 */     return this;
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> iterator() {
/* 125 */     return (new ListMap$$anon$1(this)).toList().reverseIterator();
/*     */   }
/*     */   
/*     */   public class ListMap$$anon$1 extends AbstractIterator<Tuple2<A, B>> {
/*     */     private ListMap<A, B> self;
/*     */     
/*     */     public ListMap$$anon$1(ListMap<A, B> $outer) {
/*     */       this.self = $outer;
/*     */     }
/*     */     
/*     */     public ListMap<A, B> self() {
/*     */       return this.self;
/*     */     }
/*     */     
/*     */     public void self_$eq(ListMap<A, B> x$1) {
/*     */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       return !self().isEmpty();
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> next() {
/*     */       if (hasNext()) {
/*     */         Tuple2<A, B> res = new Tuple2(self().key(), self().value());
/*     */         self_$eq(self().tail());
/*     */         return res;
/*     */       } 
/*     */       throw new NoSuchElementException("next on empty iterator");
/*     */     }
/*     */   }
/*     */   
/*     */   public A key() {
/* 127 */     throw new NoSuchElementException("empty map");
/*     */   }
/*     */   
/*     */   public B value() {
/* 128 */     throw new NoSuchElementException("empty map");
/*     */   }
/*     */   
/*     */   public ListMap<A, B> tail() {
/* 129 */     throw new NoSuchElementException("empty map");
/*     */   }
/*     */   
/*     */   public static <A, B> CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>> canBuildFrom() {
/*     */     return ListMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public class Node<B1> extends ListMap<A, B1> implements Serializable {
/*     */     public static final long serialVersionUID = -6453056603889598734L;
/*     */     
/*     */     private final A key;
/*     */     
/*     */     private final B1 value;
/*     */     
/*     */     public A key() {
/* 134 */       return this.key;
/*     */     }
/*     */     
/*     */     public Node(ListMap $outer, Object key, Object value) {}
/*     */     
/*     */     public B1 value() {
/* 135 */       return this.value;
/*     */     }
/*     */     
/*     */     public int size() {
/* 140 */       return size0(this, 0);
/*     */     }
/*     */     
/*     */     private int size0(ListMap cur, int acc) {
/*     */       while (true) {
/* 143 */         if (cur.isEmpty())
/* 143 */           return acc; 
/* 143 */         acc++;
/* 143 */         cur = cur.tail();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 149 */       return false;
/*     */     }
/*     */     
/*     */     public B1 apply(Object k) {
/* 158 */       return apply0(this, (A)k);
/*     */     }
/*     */     
/*     */     private B1 apply0(ListMap cur, Object k) {
/*     */       while (true) {
/* 162 */         if (cur.isEmpty())
/* 162 */           throw new NoSuchElementException((new StringBuilder()).append("key not found: ").append(k).toString()); 
/* 163 */         Object object = cur.key();
/* 163 */         if ((k == object) ? true : ((k == null) ? false : ((k instanceof Number) ? BoxesRunTime.equalsNumObject((Number)k, object) : ((k instanceof Character) ? BoxesRunTime.equalsCharObject((Character)k, object) : k.equals(object)))))
/* 163 */           return (B1)cur.value(); 
/* 164 */         cur = cur.tail();
/*     */       } 
/*     */     }
/*     */     
/*     */     public Option<B1> get(Object k) {
/* 172 */       return get0(this, (A)k);
/*     */     }
/*     */     
/*     */     private Option<B1> get0(ListMap cur, Object k) {
/*     */       while (true) {
/* 175 */         Object object = cur.key();
/* 176 */         if (cur.tail().nonEmpty()) {
/* 176 */           cur = cur.tail();
/*     */           continue;
/*     */         } 
/* 176 */         return ((k == object) ? true : ((k == null) ? false : ((k instanceof Number) ? BoxesRunTime.equalsNumObject((Number)k, object) : ((k instanceof Character) ? BoxesRunTime.equalsCharObject((Character)k, object) : k.equals(object))))) ? (Option<B1>)new Some(cur.value()) : (Option<B1>)scala.None$.MODULE$;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <B2> ListMap<A, B2> updated(Object k, Object v) {
/* 183 */       ListMap<A, B> m = contains(k) ? $minus((A)k) : this;
/* 184 */       return new Node(m, (A)k, (B1)v);
/*     */     }
/*     */     
/*     */     public ListMap<A, B1> $minus(Object k) {
/* 201 */       ListMap cur = this;
/* 202 */       List<Tuple2> lst = Nil$.MODULE$;
/* 203 */       while (cur.nonEmpty()) {
/* 204 */         Object object = cur.key();
/* 204 */         if (!((k == object) ? 1 : ((k == null) ? 0 : ((k instanceof Number) ? BoxesRunTime.equalsNumObject((Number)k, object) : ((k instanceof Character) ? BoxesRunTime.equalsCharObject((Character)k, object) : k.equals(object))))))
/* 204 */           lst = lst.$colon$colon(new Tuple2(cur.key(), cur.value())); 
/* 205 */         cur = cur.tail();
/*     */       } 
/* 207 */       ListMap<A, B1> acc = (ListMap)ListMap$.MODULE$.apply(Nil$.MODULE$);
/*     */       while (true) {
/* 208 */         Nil$ nil$ = Nil$.MODULE$;
/* 208 */         if (lst == null) {
/* 208 */           if (nil$ == null)
/* 214 */             return acc; 
/*     */         } else if (lst.equals(nil$)) {
/* 214 */           return acc;
/*     */         } 
/*     */         Tuple2 elem = lst.head();
/*     */         acc = new Node(acc, (A)elem._1(), (B1)elem._2());
/*     */         lst = (List<Tuple2>)lst.tail();
/*     */       } 
/*     */     }
/*     */     
/*     */     public ListMap<A, B1> tail() {
/* 218 */       return scala$collection$immutable$ListMap$Node$$$outer();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */