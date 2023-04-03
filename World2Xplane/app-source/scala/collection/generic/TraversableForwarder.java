package scala.collection.generic;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r=aaB\001\003!\003\r\t!\003\002\025)J\fg/\032:tC\ndWMR8so\006\024H-\032:\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025U\0312\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\003\n\005I!!a\003+sCZ,'o]1cY\026\004\"\001F\013\r\001\0211a\003\001CC\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020C\003 \001\021\005\001%\001\004%S:LG\017\n\013\002CA\021ABI\005\003G\031\021A!\0268ji\")Q\005\001D\tM\005QQO\0343fe2L\030N\\4\026\003=AQ\001\013\001\005B%\nqAZ8sK\006\034\007.\006\002+cQ\021\021e\013\005\006Y\035\002\r!L\001\002MB!ABL\n1\023\tycAA\005Gk:\034G/[8ocA\021A#\r\003\006e\035\022\ra\006\002\002\005\")A\007\001C!k\0059\021n]#naRLX#\001\034\021\00519\024B\001\035\007\005\035\021un\0347fC:DQA\017\001\005BU\n\001B\\8o\0136\004H/\037\005\006y\001!\t%P\001\005g&TX-F\001?!\taq(\003\002A\r\t\031\021J\034;\t\013\t\003A\021I\033\002\037!\f7\017R3gS:LG/Z*ju\026DQ\001\022\001\005B\025\013aAZ8sC2dGC\001\034G\021\02595\t1\001I\003\005\001\b\003\002\007/'YBQA\023\001\005B-\013a!\032=jgR\034HC\001\034M\021\0259\025\n1\001I\021\025q\005\001\"\021P\003\025\031w.\0368u)\tq\004\013C\003H\033\002\007\001\nC\003S\001\021\0053+\001\003gS:$GC\001+X!\raQkE\005\003-\032\021aa\0249uS>t\007\"B$R\001\004A\005\"B-\001\t\003R\026\001\0034pY\022dUM\032;\026\005msFC\001/e)\tiv\f\005\002\025=\022)!\007\027b\001/!)\001\r\027a\001C\006\021q\016\035\t\006\031\tl6#X\005\003G\032\021\021BR;oGRLwN\034\032\t\013\025D\006\031A/\002\003iDQa\032\001\005B!\f!\002\n3jm\022\032w\016\\8o+\tIG\016\006\002k_R\0211.\034\t\003)1$QA\r4C\002]AQ\001\0314A\0029\004R\001\0042l'-DQ!\0324A\002-DQ!\035\001\005BI\f\021BZ8mIJKw\r\033;\026\005M4HC\001;z)\t)x\017\005\002\025m\022)!\007\035b\001/!)\001\r\035a\001qB)ABY\nvk\")Q\r\035a\001k\")1\020\001C!y\006iAeY8m_:$#m\0357bg\",2!`A\001)\rq\030q\001\013\004\006\r\001c\001\013\002\002\021)!G\037b\001/!1\001M\037a\001\003\013\001R\001\0042\024~DQ!\032>A\002}Dq!a\003\001\t\003\ni!\001\006sK\022,8-\032'fMR,B!a\004\002\024Q!\021\021CA\f!\r!\0221\003\003\be\005%!\031AA\013#\t\0312\004C\004a\003\023\001\r!!\007\021\0171\021\027\021C\n\002\022!9\021Q\004\001\005B\005}\021\001\005:fIV\034W\rT3gi>\003H/[8o+\021\t\t#a\n\025\t\005\r\022\021\006\t\005\031U\013)\003E\002\025\003O!qAMA\016\005\004\t)\002C\004a\0037\001\r!a\013\021\0171\021\027QE\n\002&!9\021q\006\001\005B\005E\022a\003:fIV\034WMU5hQR,B!a\r\0028Q!\021QGA\035!\r!\022q\007\003\be\0055\"\031AA\013\021\035\001\027Q\006a\001\003w\001r\001\0042\024\003k\t)\004C\004\002@\001!\t%!\021\002#I,G-^2f%&<\007\016^(qi&|g.\006\003\002D\005%C\003BA#\003\027\002B\001D+\002HA\031A#!\023\005\017I\niD1\001\002\026!9\001-!\020A\002\0055\003c\002\007c'\005\035\023q\t\005\b\003#\002A\021IA*\003\r\031X/\\\013\005\003+\nI\006\006\003\002X\005m\003c\001\013\002Z\0219!'a\024C\002\005U\001\002CA/\003\037\002\035!a\030\002\0079,X\016\005\004\002b\005E\024q\013\b\005\003G\niG\004\003\002f\005-TBAA4\025\r\tI\007C\001\007yI|w\016\036 \n\003\035I1!a\034\007\003\035\001\030mY6bO\026LA!a\035\002v\t9a*^7fe&\034'bAA8\r!9\021\021\020\001\005B\005m\024a\0029s_\022,8\r^\013\005\003{\n\t\t\006\003\002\000\005\r\005c\001\013\002\002\0229!'a\036C\002\005U\001\002CA/\003o\002\035!!\"\021\r\005\005\024\021OA@\021\035\tI\t\001C!\003\027\0131!\\5o+\021\ti)a'\025\007M\ty\t\003\005\002\022\006\035\0059AAJ\003\r\031W\016\035\t\007\003C\n)*!'\n\t\005]\025Q\017\002\t\037J$WM]5oOB\031A#a'\005\017I\n9I1\001\002\026!9\021q\024\001\005B\005\005\026aA7bqV!\0211UAV)\r\031\022Q\025\005\t\003#\013i\nq\001\002(B1\021\021MAK\003S\0032\001FAV\t\035\021\024Q\024b\001\003+Aq!a,\001\t\003\n\t,\001\003iK\006$W#A\n\t\017\005U\006\001\"\021\0028\006Q\001.Z1e\037B$\030n\0348\026\003QCq!a/\001\t\003\n\t,\001\003mCN$\bbBA`\001\021\005\023qW\001\013Y\006\034Ho\0249uS>t\007bBAb\001\021\005\023QY\001\rG>\004\030\020V8Ck\0324WM]\013\005\003\017\fY\016F\002\"\003\023D\001\"a3\002B\002\007\021QZ\001\005I\026\034H\017\005\004\002P\006U\027\021\\\007\003\003#T1!a5\005\003\035iW\017^1cY\026LA!a6\002R\n1!)\0364gKJ\0042\001FAn\t\035\021\024\021\031b\001\003+Aq!a8\001\t\003\n\t/A\006d_BLHk\\!se\006LX\003BAr\003c$r!IAs\003g\f9\020\003\005\002h\006u\007\031AAu\003\tA8\017E\003\r\003W\fy/C\002\002n\032\021Q!\021:sCf\0042\001FAy\t\035\021\024Q\034b\001\003+Aq!!>\002^\002\007a(A\003ti\006\024H\017C\004\002z\006u\007\031\001 \002\0071,g\016C\004\002`\002!\t%!@\026\t\005}(q\001\013\006C\t\005!\021\002\005\t\003O\fY\0201\001\003\004A)A\"a;\003\006A\031ACa\002\005\017I\nYP1\001\002\026!9\021Q_A~\001\004q\004bBAp\001\021\005#QB\013\005\005\037\0219\002F\002\"\005#A\001\"a:\003\f\001\007!1\003\t\006\031\005-(Q\003\t\004)\t]Aa\002\032\003\f\t\007\021Q\003\005\b\0057\001A\021\tB\017\003\035!x.\021:sCf,BAa\b\003&Q!!\021\005B\024!\025a\0211\036B\022!\r!\"Q\005\003\be\te!\031AA\013\021)\021IC!\007\002\002\003\017!1F\001\013KZLG-\0328dK\022\n\004C\002B\027\005g\021\031#\004\002\0030)\031!\021\007\004\002\017I,g\r\\3di&!!Q\007B\030\005!\031E.Y:t)\006<\007b\002B\035\001\021\005#1H\001\007i>d\025n\035;\026\005\tu\002#\002B \005\013\032RB\001B!\025\r\021\031\005B\001\nS6lW\017^1cY\026LAAa\022\003B\t!A*[:u\021\035\021Y\005\001C!\005\033\n!\002^8Ji\026\024\030M\0317f+\t\021y\005\005\003\021\005#\032\022b\001B*\t\tA\021\n^3sC\ndW\rC\004\003X\001!\tE!\027\002\013Q|7+Z9\026\005\tm\003\003\002\t\003^MI1Aa\030\005\005\r\031V-\035\005\b\005G\002A\021\tB3\0031!x.\0238eKb,GmU3r+\t\0219\007E\003\003@\t%4#\003\003\003l\t\005#AC%oI\026DX\rZ*fc\"9!q\016\001\005B\tE\024\001\003;p\005V4g-\032:\026\t\tM$\021P\013\003\005k\002b!a4\002V\n]\004c\001\013\003z\0219!G!\034C\002\005U\001b\002B?\001\021\005#qP\001\ti>\034FO]3b[V\021!\021\021\t\006\005\021\031iE\005\005\005\013\023\tE\001\004TiJ,\027-\034\005\b\005\023\003A\021\tBF\003\025!xnU3u+\021\021iIa&\026\005\t=\005C\002B \005#\023)*\003\003\003\024\n\005#aA*fiB\031ACa&\005\017I\0229I1\001\002\026!9!1\024\001\005B\tu\025!\002;p\033\006\004XC\002BP\005S\023y\013\006\003\003\"\nM\006\003\003B \005G\0239K!,\n\t\t\025&\021\t\002\004\033\006\004\bc\001\013\003*\0229!1\026BM\005\0049\"!\001+\021\007Q\021y\013B\004\0032\ne%\031A\f\003\003UC\001B!.\003\032\002\017!qW\001\003KZ\004rA!/\003@N\021)MD\002\r\005wK1A!0\007\003\031\001&/\0323fM&!!\021\031Bb\005A!C.Z:tI\r|Gn\0348%Y\026\0348OC\002\003>\032\001r\001\004Bd\005O\023i+C\002\003J\032\021a\001V;qY\026\024\004b\002Bg\001\021\005#qZ\001\t[.\034FO]5oORA!\021\033Bl\0053\024i\016\005\003\003:\nM\027\002\002Bk\005\007\024aa\025;sS:<\007\002CA{\005\027\004\rA!5\t\021\tm'1\032a\001\005#\f1a]3q\021!\021yNa3A\002\tE\027aA3oI\"9!Q\032\001\005B\t\rH\003\002Bi\005KD\001Ba7\003b\002\007!\021\033\005\b\005\033\004A\021\tBu+\t\021\t\016C\004\003n\002!\tEa<\002\023\005$Gm\025;sS:<GC\003By\005o\024YP!@\003\000B!\021q\032Bz\023\021\021)0!5\003\033M#(/\0338h\005VLG\016Z3s\021!\021IPa;A\002\tE\030!\0012\t\021\005U(1\036a\001\005#D\001Ba7\003l\002\007!\021\033\005\t\005?\024Y\0171\001\003R\"9!Q\036\001\005B\r\rAC\002By\007\013\0319\001\003\005\003z\016\005\001\031\001By\021!\021Yn!\001A\002\tE\007b\002Bw\001\021\00531\002\013\005\005c\034i\001\003\005\003z\016%\001\031\001By\001")
public interface TraversableForwarder<A> extends Traversable<A> {
  Traversable<A> underlying();
  
  <B> void foreach(Function1<A, B> paramFunction1);
  
  boolean isEmpty();
  
  boolean nonEmpty();
  
  int size();
  
  boolean hasDefiniteSize();
  
  boolean forall(Function1<A, Object> paramFunction1);
  
  boolean exists(Function1<A, Object> paramFunction1);
  
  int count(Function1<A, Object> paramFunction1);
  
  Option<A> find(Function1<A, Object> paramFunction1);
  
  <B> B foldLeft(B paramB, Function2<B, A, B> paramFunction2);
  
  <B> B $div$colon(B paramB, Function2<B, A, B> paramFunction2);
  
  <B> B foldRight(B paramB, Function2<A, B, B> paramFunction2);
  
  <B> B $colon$bslash(B paramB, Function2<A, B, B> paramFunction2);
  
  <B> B reduceLeft(Function2<B, A, B> paramFunction2);
  
  <B> Option<B> reduceLeftOption(Function2<B, A, B> paramFunction2);
  
  <B> B reduceRight(Function2<A, B, B> paramFunction2);
  
  <B> Option<B> reduceRightOption(Function2<A, B, B> paramFunction2);
  
  <B> B sum(Numeric<B> paramNumeric);
  
  <B> B product(Numeric<B> paramNumeric);
  
  <B> A min(Ordering<B> paramOrdering);
  
  <B> A max(Ordering<B> paramOrdering);
  
  A head();
  
  Option<A> headOption();
  
  A last();
  
  Option<A> lastOption();
  
  <B> void copyToBuffer(Buffer<B> paramBuffer);
  
  <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
  
  <B> void copyToArray(Object paramObject, int paramInt);
  
  <B> void copyToArray(Object paramObject);
  
  <B> Object toArray(ClassTag<B> paramClassTag);
  
  List<A> toList();
  
  Iterable<A> toIterable();
  
  Seq<A> toSeq();
  
  IndexedSeq<A> toIndexedSeq();
  
  <B> Buffer<B> toBuffer();
  
  Stream<A> toStream();
  
  <B> Set<B> toSet();
  
  <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> paramless);
  
  String mkString(String paramString1, String paramString2, String paramString3);
  
  String mkString(String paramString);
  
  String mkString();
  
  StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
  
  StringBuilder addString(StringBuilder paramStringBuilder, String paramString);
  
  StringBuilder addString(StringBuilder paramStringBuilder);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\TraversableForwarder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */