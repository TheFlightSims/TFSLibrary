package scala.collection.mutable;

import scala.Function1;
import scala.collection.GenSet;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.script.Message;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t]aaB\001\003!\003\r\t!\003\002\020'ft7\r\033:p]&TX\rZ*fi*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQcE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\004'\026$\bC\001\013\026\031\001!QA\006\001C\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020C\003 \001\021\005\001%\001\004%S:LG\017\n\013\002CA\021ABI\005\003G\031\021A!\0268ji\"1Q\005\001I\005\002\031\nAa]5{KV\tq\005\005\002\rQ%\021\021F\002\002\004\023:$\b\"B\026\001\t\003b\023aB5t\0136\004H/_\013\002[A\021ABL\005\003_\031\021qAQ8pY\026\fg\016\003\0042\001A%\tAM\001\tG>tG/Y5ogR\021Qf\r\005\006iA\002\raE\001\005K2,W\016\003\0047\001A%\taN\001\tIAdWo\035\023fcR\021\001(O\007\002\001!)A'\016a\001'!)1\b\001C!y\005iA\005\0357vg\022\002H.^:%KF$\"\001O\037\t\013yR\004\031A \002\005a\034\bc\001!B'5\tA!\003\002C\t\tyAK]1wKJ\034\030M\0317f\037:\034W\r\003\004E\001A%\t!R\001\nI5Lg.^:%KF$\"\001\017$\t\013Q\032\005\031A\n\t\013!\003A\021I%\002\037\021j\027N\\;tI5Lg.^:%KF$\"\001\017&\t\013y:\005\031A \t\0131\003A\021I'\002\rU\004H-\031;f)\r\tcj\024\005\006i-\003\ra\005\005\006!.\003\r!L\001\tS:\034G.\0363fI\")!\013\001C!'\006\031\021\r\0323\025\0055\"\006\"\002\033R\001\004\031\002\"\002,\001\t\003:\026A\002:f[>4X\r\006\002.1\")A'\026a\001'!)!\f\001C!7\006I\021N\034;feN,7\r\036\013\003\037qCQ!X-A\002y\013A\001\0365biB\031\001iX\n\n\005\001$!AB$f]N+G\017\003\004c\001A%\t\001I\001\006G2,\027M\035\005\006I\002!\t%Z\001\tgV\0247/\032;PMR\021QF\032\005\006;\016\004\rA\030\005\006Q\002!\t%[\001\bM>\024X-Y2i+\tQ\027\017\006\002\"W\")An\032a\001[\006\ta\r\005\003\r]N\001\030BA8\007\005%1UO\\2uS>t\027\007\005\002\025c\022)!o\032b\001/\t\tQ\013C\003u\001\021\005S/\001\004sKR\f\027N\034\013\003CYDQa^:A\002a\f\021\001\035\t\005\0319\034R\006C\003{\001\021\00530\001\004u_2K7\017^\013\002yB!Q0a\003\024\035\rq\030q\001\b\004\006\025QBAA\001\025\r\t\031\001C\001\007yI|w\016\036 \n\003\035I1!!\003\007\003\035\001\030mY6bO\026LA!!\004\002\020\t!A*[:u\025\r\tIA\002\005\b\003'\001A\021IA\013\003!!xn\025;sS:<GCAA\f!\021\tI\"a\b\017\0071\tY\"C\002\002\036\031\ta\001\025:fI\0264\027\002BA\021\003G\021aa\025;sS:<'bAA\017\r!9\021q\005\001\005B\005%\022A\003\023mKN\034H\005\\3tgR\031\021%a\013\t\021\0055\022Q\005a\001\003_\t1aY7e!\025\t\t$a\016\024\033\t\t\031DC\002\0026\021\taa]2sSB$\030\002BA\035\003g\021q!T3tg\006<W\rC\004\002>\001!\t%a\020\002\013\rdwN\\3\025\005\005\005\003c\001\035\002D%!\021QIA$\005\021\031V\r\0344\n\007\005%CAA\bUe\0064XM]:bE2,G*[6f\021-\ti\005AA\001\002\023%a%a\024\002\025M,\b/\032:%g&TX-\003\002&\003\"Y\0211\013\001\002\002\003%I\001LA+\0035\031X\017]3sI%\034X)\0349us&\0311&a\026\n\007\005eCAA\004TKRd\025n[3\t\031\005u\003!!A\001\n\023\ty&a\031\002\035M,\b/\032:%G>tG/Y5ogR\031Q&!\031\t\rQ\nY\0061\001\024\023\r\t\024q\013\005\r\003O\002\021\021!A\005\n\005%\024QN\001\017gV\004XM\035\023%a2,8\017J3r)\rA\0241\016\005\007i\005\025\004\031A\n\n\007Y\ny'C\002\002Z\tAA\"a\035\001\003\003\005I\021BA;\003s\n1c];qKJ$C\005\0357vg\022\002H.^:%KF$2\001OA<\021\031q\024\021\017a\001%\0311(a\037\n\t\005u\024q\020\002\t\017J|w/\0312mK*\031\021\021\021\003\002\017\035,g.\032:jG\"a\021Q\021\001\002\002\003%I!a\"\002\f\006y1/\0369fe\022\"S.\0338vg\022*\027\017F\0029\003\023Ca\001NAB\001\004\031\022b\001#\002p!a\021q\022\001\002\002\003%I!!%\002\026\006)2/\0369fe\022\"S.\0338vg\022j\027N\\;tI\025\fHc\001\035\002\024\"1a(!$A\002}J1\001SAL\023\021\tI*a \003\025MC'/\0338lC\ndW\r\003\007\002\036\002\t\t\021!C\005\003?\013)+\001\007tkB,'\017J;qI\006$X\rF\003\"\003C\013\031\013\003\0045\0037\003\ra\005\005\007!\006m\005\031A\027\n\0071\013y\007\003\007\002*\002\t\t\021!C\005\003W\013y+A\005tkB,'\017J1eIR\031Q&!,\t\rQ\n9\0131\001\024\023\r\021\026q\016\005\r\003g\003\021\021!A\005\n\005U\026\021X\001\rgV\004XM\035\023sK6|g/\032\013\004[\005]\006B\002\033\0022\002\0071#C\002W\003_BA\"!0\001\003\003\005I\021BA`\003\007\fqb];qKJ$\023N\034;feN,7\r\036\013\004\037\005\005\007BB/\002<\002\007a,C\002[\003\013L1!a2\005\005)9UM\\*fi2K7.\032\005\f\003\027\004\021\021!A\005\n\001\ni-A\006tkB,'\017J2mK\006\024\030b\0012\002p!a\021\021\033\001\002\002\003%I!a5\002X\006q1/\0369fe\022\032XOY:fi>3GcA\027\002V\"1Q,a4A\002yK1\001ZAc\0211\tY\016AA\001\002\023%\021Q\\Au\0035\031X\017]3sI\031|'/Z1dQV!\021q\\At)\r\t\023\021\035\005\bY\006e\007\031AAr!\025aanEAs!\r!\022q\035\003\007e\006e'\031A\f\n\007!\fY/C\002\002n\022\021A\"\023;fe\006\024G.\032'jW\026DA\"!=\001\003\003\005I\021BAz\003o\fAb];qKJ$#/\032;bS:$2!IA{\021\0319\030q\036a\001q&\031A/a\034\t\027\005m\b!!A\001\n\023Y\030Q`\001\rgV\004XM\035\023u_2K7\017^\005\003u\006CAB!\001\001\003\003\005I\021BA\013\005\007\tab];qKJ$Co\\*ue&tw-\003\003\002\024\005]\003\002\004B\004\001\005\005\t\021\"\003\003\n\t5\021\001E:va\026\024H\005\n7fgN$C.Z:t)\r\t#1\002\005\t\003[\021)\0011\001\0020%!\021qEA8\0211\021\t\002AA\001\002\023%!1\003B\013\003-\031X\017]3sI\rdwN\\3\025\003=IA!!\020\002p\001")
public interface SynchronizedSet<A> extends Set<A> {
  int scala$collection$mutable$SynchronizedSet$$super$size();
  
  boolean scala$collection$mutable$SynchronizedSet$$super$isEmpty();
  
  boolean scala$collection$mutable$SynchronizedSet$$super$contains(A paramA);
  
  SynchronizedSet<A> scala$collection$mutable$SynchronizedSet$$super$$plus$eq(A paramA);
  
  SynchronizedSet<A> scala$collection$mutable$SynchronizedSet$$super$$plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
  
  SynchronizedSet<A> scala$collection$mutable$SynchronizedSet$$super$$minus$eq(A paramA);
  
  SynchronizedSet<A> scala$collection$mutable$SynchronizedSet$$super$$minus$minus$eq(TraversableOnce<A> paramTraversableOnce);
  
  void scala$collection$mutable$SynchronizedSet$$super$update(A paramA, boolean paramBoolean);
  
  boolean scala$collection$mutable$SynchronizedSet$$super$add(A paramA);
  
  boolean scala$collection$mutable$SynchronizedSet$$super$remove(A paramA);
  
  Set<A> scala$collection$mutable$SynchronizedSet$$super$intersect(GenSet<A> paramGenSet);
  
  void scala$collection$mutable$SynchronizedSet$$super$clear();
  
  boolean scala$collection$mutable$SynchronizedSet$$super$subsetOf(GenSet<A> paramGenSet);
  
  <U> void scala$collection$mutable$SynchronizedSet$$super$foreach(Function1<A, U> paramFunction1);
  
  void scala$collection$mutable$SynchronizedSet$$super$retain(Function1<A, Object> paramFunction1);
  
  List<A> scala$collection$mutable$SynchronizedSet$$super$toList();
  
  String scala$collection$mutable$SynchronizedSet$$super$toString();
  
  void scala$collection$mutable$SynchronizedSet$$super$$less$less(Message<A> paramMessage);
  
  Set<A> scala$collection$mutable$SynchronizedSet$$super$clone();
  
  int size();
  
  boolean isEmpty();
  
  boolean contains(A paramA);
  
  SynchronizedSet<A> $plus$eq(A paramA);
  
  SynchronizedSet<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
  
  SynchronizedSet<A> $minus$eq(A paramA);
  
  SynchronizedSet<A> $minus$minus$eq(TraversableOnce<A> paramTraversableOnce);
  
  void update(A paramA, boolean paramBoolean);
  
  boolean add(A paramA);
  
  boolean remove(A paramA);
  
  Set<A> intersect(GenSet<A> paramGenSet);
  
  void clear();
  
  boolean subsetOf(GenSet<A> paramGenSet);
  
  <U> void foreach(Function1<A, U> paramFunction1);
  
  void retain(Function1<A, Object> paramFunction1);
  
  List<A> toList();
  
  String toString();
  
  void $less$less(Message<A> paramMessage);
  
  Set<A> clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */