package scala.collection.parallel;

import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\rmb\001C\001\003!\003\r\t\001\002\005\0033\005+x-\\3oi\026$\027\n^3sC\ndW-\023;fe\006$xN\035\006\003\007\021\t\001\002]1sC2dW\r\034\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\fWCA\005\025'\r\001!B\004\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007cA\b\021%5\t!!\003\002\022\005\ty!+Z7bS:\034\030\n^3sCR|'\017\005\002\024)1\001AAB\013\001\t\013\007qCA\001U\007\001\t\"\001G\016\021\005-I\022B\001\016\007\005\035qu\016\0365j]\036\004\"a\003\017\n\005u1!aA!os\")q\004\001C\001A\0051A%\0338ji\022\"\022!\t\t\003\027\tJ!a\t\004\003\tUs\027\016\036\005\006K\001!\tEJ\001\006G>,h\016\036\013\003O)\002\"a\003\025\n\005%2!aA%oi\")1\006\na\001Y\005\t\001\017\005\003\f[Iy\023B\001\030\007\005%1UO\\2uS>t\027\007\005\002\fa%\021\021G\002\002\b\005>|G.Z1o\021\025\031\004\001\"\0215\003\031\021X\rZ;dKV\021Qg\016\013\003mi\002\"aE\034\005\013a\022$\031A\035\003\003U\013\"AE\016\t\013m\022\004\031\001\037\002\005=\004\b#B\006>mY2\024B\001 \007\005%1UO\\2uS>t'\007C\003A\001\021\005\023)\001\003g_2$WC\001\"F)\t\031\005\n\006\002E\rB\0211#\022\003\006q}\022\r!\017\005\006w}\002\ra\022\t\006\027u\"E\t\022\005\006\023~\002\r\001R\001\002u\")1\n\001C!\031\006\0311/^7\026\0055{EC\001(Q!\t\031r\nB\0039\025\n\007\021\bC\003R\025\002\017!+A\002ok6\0042aU.O\035\t!\026L\004\002V16\taK\003\002X-\0051AH]8pizJ\021aB\005\0035\032\tq\001]1dW\006<W-\003\002];\n9a*^7fe&\034'B\001.\007\021\025y\006\001\"\021a\003\035\001(o\0343vGR,\"!Y2\025\005\t$\007CA\nd\t\025AdL1\001:\021\025\tf\fq\001f!\r\0316L\031\005\006O\002!\t\005[\001\004[&tWCA5q)\t\021\"\016C\003lM\002\017A.A\002pe\022\0042aU7p\023\tqWL\001\005Pe\022,'/\0338h!\t\031\002\017B\0039M\n\007\021\bC\003s\001\021\0053/A\002nCb,\"\001\036=\025\005I)\b\"B6r\001\b1\bcA*noB\0211\003\037\003\006qE\024\r!\017\005\006u\002!\te_\001\fG>\004\030\020V8BeJ\f\0270F\002}\003\017!b!I?\002\n\0055\001\"\002@z\001\004y\030!B1se\006L\b#B\006\002\002\005\025\021bAA\002\r\t)\021I\035:bsB\0311#a\002\005\013aJ(\031A\035\t\r\005-\021\0201\001(\003\0211'o\\7\t\r\005=\021\0201\001(\003\raWM\034\005\b\003'\001A\021AA\013\003)\021X\rZ;dK2+g\r^\013\005\003/\tY\002\006\004\002\032\005u\021\021\005\t\004'\005mAA\002\035\002\022\t\007\021\bC\004\002 \005E\001\031A\024\002\017!|w/\\1os\"91(!\005A\002\005\r\002\003C\006>\0033\tI\"!\007\t\017\005\035\002\001\"\001\002*\005aQ.\03193G>l'-\0338feV1\0211FA\033\003w!b!!\f\002@\005\025\003cB\b\0020\005M\022\021H\005\004\003c\021!\001C\"p[\nLg.\032:\021\007M\t)\004B\004\0028\005\025\"\031A\f\003\003M\0032aEA\036\t\035\ti$!\nC\002]\021A\001\0265bi\"A\021\021IA\023\001\004\t\031%A\001g!\025YQFEA\032\021!\t9%!\nA\002\0055\022AA2c\021\035\tY\005\001C\001\003\033\n\001cY8mY\026\034GOM2p[\nLg.\032:\026\r\005=\023QKA-)\031\t\t&a\027\002fA9q\"a\f\002T\005]\003cA\n\002V\0219\021qGA%\005\0049\002cA\n\002Z\0219\021QHA%\005\0049\002\002CA/\003\023\002\r!a\030\002\005A4\007CB\006\002bI\t\031&C\002\002d\031\021q\002U1si&\fGNR;oGRLwN\034\005\t\003\017\nI\0051\001\002R!9\021\021\016\001\005\002\005-\024\001\0054mCRl\027\r\035\032d_6\024\027N\\3s+\031\ti'a\035\002xQ1\021qNA=\003\013\003raDA\030\003c\n)\bE\002\024\003g\"q!a\016\002h\t\007q\003E\002\024\003o\"q!!\020\002h\t\007q\003\003\005\002B\005\035\004\031AA>!\025YQFEA?!\031\ty(!!\002r5\tA!C\002\002\004\022\021!cR3o)J\fg/\032:tC\ndWm\0248dK\"A\021qIA4\001\004\ty\007C\004\002\n\002!\t!a#\002\031\r|\007/\037\032ck&dG-\032:\026\021\0055\025QUAU\003##B!a$\002.B\0311#!%\005\021\005M\025q\021b\001\003+\0231A\0217e#\rA\022q\023\t\t\0033\013y*a)\002(6\021\0211\024\006\004\003;#\021aB7vi\006\024G.Z\005\005\003C\013YJA\004Ck&dG-\032:\021\007M\t)\013\002\0049\003\017\023\r!\017\t\004'\005%FaBAV\003\017\023\ra\006\002\005\007>dG\016\003\005\0020\006\035\005\031AAH\003\005\021\007bBAZ\001\021\005\021QW\001\020M&dG/\032:3G>l'-\0338feV1\021qWA_\003\003$b!!/\002F\006%\007cB\b\0020\005m\026q\030\t\004'\005uFA\002\035\0022\n\007\021\bE\002\024\003\003$q!a1\0022\n\007qC\001\003UQ&\034\bbBAd\003c\003\r\001L\001\005aJ,G\r\003\005\002H\005E\006\031AA]\021\035\ti\r\001C\001\003\037\f!CZ5mi\026\024hj\034;3G>l'-\0338feV1\021\021[Al\0037$b!a5\002^\006}\007cB\b\0020\005U\027\021\034\t\004'\005]GA\002\035\002L\n\007\021\bE\002\024\0037$q!a1\002L\n\007q\003C\004\002H\006-\007\031\001\027\t\021\005\035\0231\032a\001\003'Dq!a9\001\t\003\t)/A\nqCJ$\030\016^5p]J\032w.\0342j]\026\0248/\006\004\002h\006M\030q\037\013\t\003S\fI0a?\002\000B91\"a;\002p\006=\030bAAw\r\t1A+\0369mKJ\002raDA\030\003c\f)\020E\002\024\003g$a\001OAq\005\004I\004cA\n\002x\0229\0211YAq\005\0049\002bBAd\003C\004\r\001\f\005\t\003{\f\t\0171\001\002p\006)!\r\036:vK\"A!\021AAq\001\004\ty/\001\004cM\006d7/\032\005\b\005\013\001A\021\001B\004\0035!\030m[33G>l'-\0338feV1!\021\002B\b\005'!bAa\003\003\026\te\001cB\b\0020\t5!\021\003\t\004'\t=AA\002\035\003\004\t\007\021\bE\002\024\005'!q!a1\003\004\t\007q\003C\004\003\030\t\r\001\031A\024\002\0039D\001\"a\022\003\004\001\007!1\002\005\b\005;\001A\021\001B\020\0035!'o\03493G>l'-\0338feV1!\021\005B\024\005W!bAa\t\003.\t=\002cB\b\0020\t\025\"\021\006\t\004'\t\035BA\002\035\003\034\t\007\021\bE\002\024\005W!q!a1\003\034\t\007q\003C\004\003\030\tm\001\031A\024\t\021\005\035#1\004a\001\005GAqAa\r\001\t\003\021)$\001\btY&\034WMM2p[\nLg.\032:\026\r\t]\"Q\bB!)!\021IDa\021\003F\t%\003cB\b\0020\tm\"q\b\t\004'\tuBA\002\035\0032\t\007\021\bE\002\024\005\003\"q!a1\0032\t\007q\003C\004\002\f\tE\002\031A\024\t\017\t\035#\021\007a\001O\005)QO\034;jY\"A\021q\tB\031\001\004\021I\004C\004\003N\001!\tAa\024\002#M\004H.\033;BiJ\032w.\0342j]\026\0248/\006\004\003R\te#Q\f\013\t\005'\022yFa\031\003hA91\"a;\003V\tU\003cB\b\0020\t]#1\f\t\004'\teCA\002\035\003L\t\007\021\bE\002\024\005;\"q!a1\003L\t\007q\003C\004\003b\t-\003\031A\024\002\005\005$\b\002\003B3\005\027\002\rA!\026\002\r\t,gm\034:f\021!\021IGa\023A\002\tU\023!B1gi\026\024\bb\002B7\001\021\005!qN\001\023i\006\\Wm\0265jY\026\0244m\\7cS:,'/\006\004\003r\te$Q\020\013\007\005g\022yH!!\021\r-\tYO!\0360!\035y\021q\006B<\005w\0022a\005B=\t\031A$1\016b\001sA\0311C! \005\017\005\r'1\016b\001/!11Fa\033A\0021B\001\"a\022\003l\001\007!Q\017\005\b\005\013\003A\021\001BD\0039\031\b/\03183G>l'-\0338feN,bA!#\003\022\nUE\003\003BF\005/\023IJa'\021\017-\tYO!$\003\016B9q\"a\f\003\020\nM\005cA\n\003\022\0221\001Ha!C\002e\0022a\005BK\t\035\t\031Ma!C\002]Aaa\013BB\001\004a\003\002\003B3\005\007\003\rA!$\t\021\t%$1\021a\001\005\033CqAa(\001\t\003\021\t+A\006tG\006tGk\\!se\006LXC\002BR\005S\023)\fF\005\"\005K\023YKa,\003<\"9\021J!(A\002\t\035\006cA\n\003*\0221\001H!(C\002eBqa\017BO\001\004\021i\013\005\005\f{\t\035&q\025BT\021\035q(Q\024a\001\005c\003RaCA\001\005g\0032a\005B[\t!\0219L!(C\002\te&!A!\022\007\t\0356\004C\004\002\f\tu\005\031A\024\t\017\t}\006\001\"\001\003B\006q1oY1o)>\034u.\0342j]\026\024XC\002Bb\005\023\024i\r\006\005\003F\n='1\033Bl!\035y\021q\006Bd\005\027\0042a\005Be\t\031A$Q\030b\001sA\0311C!4\005\017\005u\"Q\030b\001/!A!\021\033B_\001\004\0219-\001\006ti\006\024HOV1mk\026Dqa\017B_\001\004\021)\016\005\005\f{\t\035'q\031Bd\021!\t9E!0A\002\t\025\007b\002B`\001\021\005!1\\\013\007\005;\024\031Oa:\025\025\t}'\021\036Bv\005[\024\t\020E\004\020\003_\021\tO!:\021\007M\021\031\017\002\0049\0053\024\r!\017\t\004'\t\035HaBA\037\0053\024\ra\006\005\b\003?\021I\0161\001(\021!\021\tN!7A\002\t\005\bbB\036\003Z\002\007!q\036\t\t\027u\022\tO!9\003b\"A\021q\tBm\001\004\021y\016C\004\003v\002!\tAa>\002\031iL\007OM2p[\nLg.\032:\026\021\te8\021AB\003\007\023!bAa?\004\f\rE\001cB\b\0020\tu8q\001\t\b\027\005-(q`B\002!\r\0312\021\001\003\007q\tM(\031A\035\021\007M\031)\001B\004\0028\tM(\031A\f\021\007M\031I\001B\004\002>\tM(\031A\f\t\021\r5!1\037a\001\007\037\t\001b\034;iKJ\004\030\016\036\t\005\037A\031\031\001\003\005\002H\tM\b\031\001B~\021\035\031)\002\001C\001\007/\tqB_5q\0032d'gY8nE&tWM]\013\t\0073\031\tc!\n\004*QQ11DB\026\007c\031)d!\017\021\017=\tyc!\b\004(A91\"a;\004 \r\r\002cA\n\004\"\0211\001ha\005C\002e\0022aEB\023\t\035\t9da\005C\002]\0012aEB\025\t\035\tida\005C\002]A\001b!\f\004\024\001\0071qF\001\005i\"\fG\017\005\003\020!\r\r\002\002CB\032\007'\001\raa\b\002\021QD\027n]3mK6D\001ba\016\004\024\001\00711E\001\ti\"\fG/\0327f[\"A\021qIB\n\001\004\031Y\002")
public interface AugmentedIterableIterator<T> extends RemainsIterator<T> {
  int count(Function1<T, Object> paramFunction1);
  
  <U> U reduce(Function2<U, U, U> paramFunction2);
  
  <U> U fold(U paramU, Function2<U, U, U> paramFunction2);
  
  <U> U sum(Numeric<U> paramNumeric);
  
  <U> U product(Numeric<U> paramNumeric);
  
  <U> T min(Ordering<U> paramOrdering);
  
  <U> T max(Ordering<U> paramOrdering);
  
  <U> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
  
  <U> U reduceLeft(int paramInt, Function2<U, U, U> paramFunction2);
  
  <S, That> Combiner<S, That> map2combiner(Function1<T, S> paramFunction1, Combiner<S, That> paramCombiner);
  
  <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, S> paramPartialFunction, Combiner<S, That> paramCombiner);
  
  <S, That> Combiner<S, That> flatmap2combiner(Function1<T, GenTraversableOnce<S>> paramFunction1, Combiner<S, That> paramCombiner);
  
  <U, Coll, Bld extends scala.collection.mutable.Builder<U, Coll>> Bld copy2builder(Bld paramBld);
  
  <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> paramFunction1, Combiner<U, This> paramCombiner);
  
  <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> paramFunction1, Combiner<U, This> paramCombiner);
  
  <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> paramFunction1, Combiner<U, This> paramCombiner1, Combiner<U, This> paramCombiner2);
  
  <U, This> Combiner<U, This> take2combiner(int paramInt, Combiner<U, This> paramCombiner);
  
  <U, This> Combiner<U, This> drop2combiner(int paramInt, Combiner<U, This> paramCombiner);
  
  <U, This> Combiner<U, This> slice2combiner(int paramInt1, int paramInt2, Combiner<U, This> paramCombiner);
  
  <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int paramInt, Combiner<U, This> paramCombiner1, Combiner<U, This> paramCombiner2);
  
  <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1<T, Object> paramFunction1, Combiner<U, This> paramCombiner);
  
  <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1<T, Object> paramFunction1, Combiner<U, This> paramCombiner1, Combiner<U, This> paramCombiner2);
  
  <U, A> void scanToArray(U paramU, Function2<U, U, U> paramFunction2, Object paramObject, int paramInt);
  
  <U, That> Combiner<U, That> scanToCombiner(U paramU, Function2<U, U, U> paramFunction2, Combiner<U, That> paramCombiner);
  
  <U, That> Combiner<U, That> scanToCombiner(int paramInt, U paramU, Function2<U, U, U> paramFunction2, Combiner<U, That> paramCombiner);
  
  <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator<S> paramRemainsIterator, Combiner<Tuple2<U, S>, That> paramCombiner);
  
  <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator<S> paramRemainsIterator, U paramU, S paramS, Combiner<Tuple2<U, S>, That> paramCombiner);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AugmentedIterableIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */