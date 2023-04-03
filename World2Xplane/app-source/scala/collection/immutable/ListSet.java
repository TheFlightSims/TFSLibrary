/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.AbstractSet;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.generic.TraversableForwarder;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005r!B\001\003\021\003I\021a\002'jgR\034V\r\036\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\021\005)YQ\"\001\002\007\0131\021\001\022A\007\003\0171K7\017^*fiN\0311B\004\032\021\007=\021B#D\001\021\025\t\tB!A\004hK:,'/[2\n\005M\001\"aE%n[V$\030M\0317f'\026$h)Y2u_JL\bC\001\006\026\r\021a!\001\001\f\026\005]q2CB\013\031Q-r#\007E\002\0325qi\021\001B\005\0037\021\0211\"\0212tiJ\f7\r^*fiB\021QD\b\007\001\t\025yRC1\001!\005\005\t\025CA\021&!\t\0213%D\001\007\023\t!cAA\004O_RD\027N\\4\021\005\t2\023BA\024\007\005\r\te.\037\t\004\025%b\022B\001\026\003\005\r\031V\r\036\t\005\0371bB#\003\002.!\t\021r)\0328fe&\0347+\032;UK6\004H.\031;f!\021Ir\006H\031\n\005A\"!aB*fi2K7.\032\t\004\025Ua\002C\001\0224\023\t!dA\001\007TKJL\027\r\\5{C\ndW\rC\0037+\021\005q'\001\004=S:LGO\020\013\002c!)\021(\006C!u\005I1m\\7qC:LwN\\\013\002wA\031q\002\020\013\n\005u\002\"\001E$f]\026\024\030nY\"p[B\fg.[8o\021\025yT\003\"\021A\003\021\031\030N_3\026\003\005\003\"A\t\"\n\005\r3!aA%oi\")Q)\006C!\r\0069\021n]#naRLX#A$\021\005\tB\025BA%\007\005\035\021un\0347fC:DQaS\013\005\0021\013\001bY8oi\006Lgn\035\013\003\0176CQA\024&A\002q\tA!\0327f[\")\001+\006C\001#\006)A\005\0357vgR\021\021G\025\005\006\035>\003\r\001\b\005\006)V!\t!V\001\007I5Lg.^:\025\005E2\006\"\002(T\001\004a\002\"\002-\026\t\003J\026A\003\023qYV\034H\005\0357vgR\021\021G\027\005\0067^\003\r\001X\001\003qN\0042!G/\035\023\tqFA\001\nHK:$&/\031<feN\f'\r\\3P]\016,\007\"\0021\026\t\003\t\027\001C5uKJ\fGo\034:\026\003\t\0042!G2\035\023\t!GA\001\005Ji\026\024\030\r^8s\021\0251W\003\"\021h\003\021AW-\0313\026\003qAQ![\013\005B)\fA\001^1jYV\t\021\007C\003m+\021\005S.\001\007tiJLgn\032)sK\032L\0070F\001o!\tyG/D\001q\025\t\t(/\001\003mC:<'\"A:\002\t)\fg/Y\005\003kB\024aa\025;sS:<g\001B<\026\021a\024AAT8eKN\031a/\r\032\t\021\0314(Q1A\005B\035D\001b\037<\003\002\003\006I\001H\001\006Q\026\fG\r\t\005\006mY$\t! \013\004}\006\005\001CA@w\033\005)\002\"\0024}\001\004a\002\"B w\t\003\002\005bBA\004m\022%\021\021B\001\rg&TX-\0238uKJt\027\r\034\013\006\003\006-\021q\002\005\b\003\033\t)\0011\0012\003\005q\007bBA\t\003\013\001\r!Q\001\004C\016\034\007\006BA\003\003+\001B!a\006\002\0365\021\021\021\004\006\004\00371\021AC1o]>$\030\r^5p]&!\021qDA\r\005\035!\030-\0337sK\016DQ!\022<\005B\031Caa\023<\005B\005\025BcA$\002(!9\021\021FA\022\001\004a\022!A3\t\017\0055b\017\"\003\0020\005\0012m\0348uC&t7/\0238uKJt\027\r\034\013\006\017\006E\0221\007\005\b\003\033\tY\0031\0012\021\035\tI#a\013A\002qAC!a\013\002\026!1\001K\036C!\003s!2!MA\036\021\035\tI#a\016A\002qAa\001\026<\005B\005}BcA\031\002B!9\021\021FA\037\001\004a\002\"B5w\t\003R\007bCA$m\n\005\t\021!C!+)\f1g]2bY\006$3m\0347mK\016$\030n\0348%S6lW\017^1cY\026$C*[:u'\026$H\005J;oG\",7m[3e?>,H/\032:\t\031\005-SC!A\001\002\023\005Q#!\024\002gM\034\027\r\\1%G>dG.Z2uS>tG%[7nkR\f'\r\\3%\031&\034HoU3uI\021*hn\0315fG.,Gm\030\023qYV\034HcA\031\002P!9\021\021FA%\001\004a\002bCA$+\t\005\t\021!C\001+)DaAN\006\005\002\005UC#A\005\t\017\005e3\002b\001\002\\\005a1-\0318Ck&dGM\022:p[V!\021QLA8+\t\ty\006E\005\020\003C\n)'!\034\002r%\031\0211\r\t\003\031\r\013gNQ;jY\0224%o\\7\021\t\005\035\024\021N\007\002\027%\031\0211\016\037\003\t\r{G\016\034\t\004;\005=DAB\020\002X\t\007\001\005\005\003\013+\0055\004bBA;\027\021\005\023qO\001\006K6\004H/_\013\005\003s\ny(\006\002\002|A!!\"FA?!\ri\022q\020\003\007?\005M$\031\001\021\t\017\005\r5\002\"\021\002\006\006Qa.Z<Ck&dG-\032:\026\t\005\035\025qS\013\003\003\023\003\002\"a#\002\022\006U\025\021T\007\003\003\033S1!a$\005\003\035iW\017^1cY\026LA!a%\002\016\n9!)^5mI\026\024\bcA\017\002\030\0221q$!!C\002\001\002BAC\013\002\026\0369\021QT\006\t\n\005}\025\001D#naRLH*[:u'\026$\b\003BA4\003C3q!a)\f\021\023\t)K\001\007F[B$\030\020T5tiN+Go\005\003\002\"\006\035\006c\001\006\026K!9a'!)\005\002\005-FCAAP\021)\ty+!)\002\002\023%\021\021W\001\fe\026\fGMU3t_24X\r\006\002\0024B\031q.!.\n\007\005]\006O\001\004PE*,7\r\036\004\007\003w[\001!!0\003\0351K7\017^*fi\n+\030\016\0343feV!\021qXAf'\031\tI,!1\002HB\031!%a1\n\007\005\025gA\001\004B]f\024VM\032\t\t\003\027\013\t*!3\002PB\031Q$a3\005\017\0055\027\021\030b\001A\t!Q\t\\3n!\021QQ#!3\t\027\005M\027\021\030B\001B\003%\021qZ\001\bS:LG/[1m\021\0351\024\021\030C\001\003/$B!!7\002\\B1\021qMA]\003\023D\001\"a5\002V\002\007\021q\032\005\bm\005eF\021AAp)\t\tI\016\003\006\002d\006e&\031!C\t\003K\fQ!\0327f[N,\"!a:\021\r\005-\025\021^Ae\023\021\tY/!$\003\0251K7\017\036\"vM\032,'\017C\005\002p\006e\006\025!\003\002h\0061Q\r\\3ng\002B!\"a=\002:\n\007I\021CA{\003\021\031X-\0328\026\005\005]\bCBAF\003s\fI-\003\003\002|\0065%a\002%bg\"\034V\r\036\005\n\003\fI\f)A\005\003o\fQa]3f]\002B\001Ba\001\002:\022\005!QA\001\tIAdWo\035\023fcR!!q\001B\005\033\t\tI\f\003\005\003\f\t\005\001\031AAe\003\005A\b\002\003B\b\003s#\tA!\005\002\013\rdW-\031:\025\005\tM\001c\001\022\003\026%\031!q\003\004\003\tUs\027\016\036\005\t\0057\tI\f\"\001\003\036\0051!/Z:vYR$\"!a4\t\023\005=6\"!A\005\n\005E\006")
/*     */ public class ListSet<A> extends AbstractSet<A> implements Set<A>, GenericSetTemplate<A, ListSet>, SetLike<A, ListSet<A>>, Serializable {
/*     */   public static class EmptyListSet$ extends ListSet<Object> {
/*     */     public static final EmptyListSet$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/*  27 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyListSet$() {
/*  27 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ListSetBuilder<Elem> implements Builder<Elem, ListSet<Elem>> {
/*     */     private final ListBuffer<Elem> elems;
/*     */     
/*     */     private final HashSet<Elem> seen;
/*     */     
/*     */     public void sizeHint(int size) {
/*  34 */       Builder.class.sizeHint(this, size);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll) {
/*  34 */       Builder.class.sizeHint(this, coll);
/*     */     }
/*     */     
/*     */     public void sizeHint(TraversableLike coll, int delta) {
/*  34 */       Builder.class.sizeHint(this, coll, delta);
/*     */     }
/*     */     
/*     */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  34 */       Builder.class.sizeHintBounded(this, size, boundingColl);
/*     */     }
/*     */     
/*     */     public <NewTo> Builder<Elem, NewTo> mapResult(Function1 f) {
/*  34 */       return Builder.class.mapResult(this, f);
/*     */     }
/*     */     
/*     */     public Growable<Elem> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  34 */       return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public Growable<Elem> $plus$plus$eq(TraversableOnce xs) {
/*  34 */       return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */     }
/*     */     
/*     */     public ListSetBuilder(ListSet initial) {
/*  34 */       Growable.class.$init$((Growable)this);
/*  34 */       Builder.class.$init$(this);
/*  36 */       this.elems = (ListBuffer<Elem>)(new ListBuffer()).$plus$plus$eq((TraversableOnce)initial).reverse();
/*  37 */       this.seen = (HashSet<Elem>)(new HashSet()).$plus$plus$eq((TraversableOnce)initial);
/*     */     }
/*     */     
/*     */     public ListSetBuilder() {
/*     */       this(ListSet$.MODULE$.empty());
/*     */     }
/*     */     
/*     */     public ListBuffer<Elem> elems() {
/*     */       return this.elems;
/*     */     }
/*     */     
/*     */     public HashSet<Elem> seen() {
/*  37 */       return this.seen;
/*     */     }
/*     */     
/*     */     public ListSetBuilder<Elem> $plus$eq(Object x) {
/*  41 */       elems().$plus$eq(x);
/*  42 */       seen().apply(x) ? BoxedUnit.UNIT : seen().$plus$eq(x);
/*  44 */       return this;
/*     */     }
/*     */     
/*     */     public void clear() {
/*  46 */       elems().clear();
/*  46 */       seen().clear();
/*     */     }
/*     */     
/*     */     public ListSet<Elem> result() {
/*  47 */       ListSet$ listSet$ = ListSet$.MODULE$;
/*  47 */       return (ListSet<Elem>)TraversableForwarder.class.foldLeft((TraversableForwarder)elems(), ListSet.EmptyListSet$.MODULE$, (Function2)new ListSet$ListSetBuilder$$anonfun$result$1(this));
/*     */     }
/*     */     
/*     */     public class ListSet$ListSetBuilder$$anonfun$result$1 extends AbstractFunction2<ListSet<Elem>, Elem, ListSet<Elem>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ListSet<Elem> apply(ListSet<Elem> x$1, Object x$2) {
/*  47 */         return x$1.scala$collection$immutable$ListSet$$unchecked_$plus((Elem)x$2);
/*     */       }
/*     */       
/*     */       public ListSet$ListSetBuilder$$anonfun$result$1(ListSet.ListSetBuilder $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  66 */     return Set$class.toSet(this);
/*     */   }
/*     */   
/*     */   public Set<A> seq() {
/*  66 */     return Set$class.seq(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSet<A>> parCombiner() {
/*  66 */     return Set$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public ListSet() {
/*  66 */     Traversable$class.$init$(this);
/*  66 */     Iterable$class.$init$(this);
/*  66 */     Set$class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<ListSet> companion() {
/*  71 */     return (GenericCompanion<ListSet>)ListSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public int size() {
/*  77 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public ListSet<A> $plus(Object elem) {
/*  89 */     return new Node(this, (A)elem);
/*     */   }
/*     */   
/*     */   public ListSet<A> $minus(Object elem) {
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public ListSet<A> $plus$plus(GenTraversableOnce xs) {
/* 102 */     return xs.isEmpty() ? this : (
/* 103 */       (ListSetBuilder<A>)(new ListSetBuilder(this)).$plus$plus$eq(xs.seq())).result();
/*     */   }
/*     */   
/*     */   public ListSet<A> scala$collection$immutable$ListSet$$unchecked_$plus(Object e) {
/* 105 */     return new Node(this, (A)e);
/*     */   }
/*     */   
/*     */   public ListSet<A> scala$collection$immutable$ListSet$$unchecked_outer() {
/* 107 */     throw new NoSuchElementException("Empty ListSet has no outer pointer");
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 114 */     return (Iterator<A>)new ListSet$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class ListSet$$anon$1 extends AbstractIterator<A> {
/*     */     private ListSet<A> that;
/*     */     
/*     */     public ListSet$$anon$1(ListSet<A> $outer) {
/* 115 */       this.that = $outer;
/*     */     }
/*     */     
/*     */     private ListSet<A> that() {
/* 115 */       return this.that;
/*     */     }
/*     */     
/*     */     private void that_$eq(ListSet<A> x$1) {
/* 115 */       this.that = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 116 */       return that().nonEmpty();
/*     */     }
/*     */     
/*     */     public A next() {
/* 119 */       Object res = that().head();
/* 120 */       that_$eq(that().tail());
/* 121 */       return hasNext() ? (A)res : 
/*     */         
/* 123 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public A head() {
/* 129 */     throw new NoSuchElementException("Set has no elements");
/*     */   }
/*     */   
/*     */   public ListSet<A> tail() {
/* 134 */     throw new NoSuchElementException("Next of an empty set");
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 136 */     return "ListSet";
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<ListSet<?>, A, ListSet<A>> canBuildFrom() {
/*     */     return ListSet$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Object setCanBuildFrom() {
/*     */     return ListSet$.MODULE$.setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public class Node extends ListSet<A> implements Serializable {
/*     */     private final A head;
/*     */     
/*     */     public A head() {
/* 140 */       return this.head;
/*     */     }
/*     */     
/*     */     public Node(ListSet $outer, Object head) {}
/*     */     
/*     */     public ListSet<A> scala$collection$immutable$ListSet$$unchecked_outer() {
/* 141 */       return scala$collection$immutable$ListSet$Node$$$outer();
/*     */     }
/*     */     
/*     */     public int size() {
/* 147 */       return sizeInternal(this, 0);
/*     */     }
/*     */     
/*     */     private int sizeInternal(ListSet n, int acc) {
/*     */       while (true) {
/* 149 */         if (n.isEmpty())
/* 149 */           return acc; 
/* 150 */         acc++;
/* 150 */         n = n.scala$collection$immutable$ListSet$$unchecked_outer();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 156 */       return false;
/*     */     }
/*     */     
/*     */     public boolean contains(Object e) {
/* 163 */       return containsInternal(this, (A)e);
/*     */     }
/*     */     
/*     */     private boolean containsInternal(ListSet<Object> n, Object e) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: invokevirtual isEmpty : ()Z
/*     */       //   4: ifeq -> 11
/*     */       //   7: iconst_0
/*     */       //   8: goto -> 78
/*     */       //   11: aload_1
/*     */       //   12: invokevirtual head : ()Ljava/lang/Object;
/*     */       //   15: dup
/*     */       //   16: astore_3
/*     */       //   17: aload_2
/*     */       //   18: if_acmpne -> 25
/*     */       //   21: iconst_1
/*     */       //   22: goto -> 74
/*     */       //   25: aload_3
/*     */       //   26: ifnonnull -> 33
/*     */       //   29: iconst_0
/*     */       //   30: goto -> 74
/*     */       //   33: aload_3
/*     */       //   34: instanceof java/lang/Number
/*     */       //   37: ifeq -> 51
/*     */       //   40: aload_3
/*     */       //   41: checkcast java/lang/Number
/*     */       //   44: aload_2
/*     */       //   45: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */       //   48: goto -> 74
/*     */       //   51: aload_3
/*     */       //   52: instanceof java/lang/Character
/*     */       //   55: ifeq -> 69
/*     */       //   58: aload_3
/*     */       //   59: checkcast java/lang/Character
/*     */       //   62: aload_2
/*     */       //   63: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */       //   66: goto -> 74
/*     */       //   69: aload_3
/*     */       //   70: aload_2
/*     */       //   71: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   74: ifeq -> 79
/*     */       //   77: iconst_1
/*     */       //   78: ireturn
/*     */       //   79: aload_1
/*     */       //   80: invokevirtual scala$collection$immutable$ListSet$$unchecked_outer : ()Lscala/collection/immutable/ListSet;
/*     */       //   83: astore_1
/*     */       //   84: goto -> 0
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #165	-> 0
/*     */       //   #164	-> 78
/*     */       //   #165	-> 79
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	87	0	this	Lscala/collection/immutable/ListSet$Node;
/*     */       //   0	87	1	n	Lscala/collection/immutable/ListSet;
/*     */       //   0	87	2	e	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public ListSet<A> $plus(Object e) {
/* 169 */       return contains((A)e) ? this : new Node(this, (A)e);
/*     */     }
/*     */     
/*     */     public ListSet<A> $minus(Object e) {
/* 173 */       A a = head();
/* 174 */       ListSet<A> tail = scala$collection$immutable$ListSet$Node$$$outer().$minus(e);
/* 174 */       return ((e == a) ? true : ((e == null) ? false : ((e instanceof Number) ? BoxesRunTime.equalsNumObject((Number)e, a) : ((e instanceof Character) ? BoxesRunTime.equalsCharObject((Character)e, a) : e.equals(a))))) ? scala$collection$immutable$ListSet$Node$$$outer() : new Node(tail, head());
/*     */     }
/*     */     
/*     */     public ListSet<A> tail() {
/* 177 */       return scala$collection$immutable$ListSet$Node$$$outer();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\ListSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */