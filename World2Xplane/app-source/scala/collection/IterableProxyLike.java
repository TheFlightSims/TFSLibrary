package scala.collection;

import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005EcaB\001\003!\003\r\ta\002\002\022\023R,'/\0312mKB\023x\016_=MS.,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001Qc\001\005\024;M!\001!C\007'!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\005\035=\tB$D\001\003\023\t\001\"A\001\007Ji\026\024\030M\0317f\031&\\W\r\005\002\023'1\001AA\002\013\001\t\013\007QCA\001B#\t1\022\004\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h!\tQ!$\003\002\034\t\t\031\021I\\=\021\005IiBA\002\020\001\t\013\007qD\001\003SKB\024\030C\001\f!%\r\tSb\t\004\005E\001\001\001E\001\007=e\0264\027N\\3nK:$h\bE\002\017IEI!!\n\002\003\021%#XM]1cY\026\004BAD\024\0229%\021\001F\001\002\025)J\fg/\032:tC\ndW\r\025:pqfd\025n[3\t\013)\002A\021A\026\002\r\021Jg.\033;%)\005a\003C\001\006.\023\tqCA\001\003V]&$\b\"\002\031\001\t\003\n\024\001C5uKJ\fGo\034:\026\003I\0022AD\032\022\023\t!$A\001\005Ji\026\024\030\r^8s\021\0251\004\001\"\0218\003\0359'o\\;qK\022$\"\001O\035\021\0079\031D\004C\003;k\001\0071(\001\003tSj,\007C\001\006=\023\tiDAA\002J]RDQa\020\001\005B\001\013qa\0357jI&tw\r\006\0029\003\")!H\020a\001w!)q\b\001C!\007R\031\001\bR#\t\013i\022\005\031A\036\t\013\031\023\005\031A\036\002\tM$X\r\035\005\006\021\002!\t%S\001\ni\006\\WMU5hQR$\"\001\b&\t\013-;\005\031A\036\002\0039DQ!\024\001\005B9\013\021\002\032:paJKw\r\033;\025\005qy\005\"B&M\001\004Y\004\"B)\001\t\003\022\026a\001>jaV!1\013\0325W)\t!&\016\006\002V1B\021!C\026\003\006/B\023\r!\006\002\005)\"\fG\017C\003Z!\002\017!,\001\002cMB)1L\030\017a+6\tAL\003\002^\005\0059q-\0328fe&\034\027BA0]\0051\031\025M\034\"vS2$gI]8n!\021Q\021mY4\n\005\t$!A\002+va2,'\007\005\002\023I\022)Q\r\025b\001M\n\021\021)M\t\003#e\001\"A\0055\005\013%\004&\031A\013\003\003\tCQa\033)A\0021\fA\001\0365biB\031a\"\\4\n\0059\024!aC$f]&#XM]1cY\026DQ\001\035\001\005BE\faA_5q\0032dW\003\002:}uV$Ra]?\000\003\007!\"\001\036<\021\005I)H!B,p\005\004)\002\"B-p\001\b9\b#B._9a$\b\003\002\006bsn\004\"A\005>\005\013\025|'\031\0014\021\005IaH!B5p\005\004)\002\"B6p\001\004q\bc\001\bnw\"1\021\021A8A\002e\f\001\002\0365jg\026cW-\034\005\007\003\013y\007\031A>\002\021QD\027\r^#mK6Dq!!\003\001\t\003\nY!\001\007{SB<\026\016\0365J]\022,\0070\006\004\002\016\005m\021\021\003\013\005\003\037\t\031\002E\002\023\003#!aaVA\004\005\004)\002bB-\002\b\001\017\021Q\003\t\b7zc\022qCA\b!\025Q\021-!\007<!\r\021\0221\004\003\007K\006\035!\031\0014\t\017\005}\001\001\"\021\002\"\005a1/Y7f\0132,W.\0328ugV!\0211EA\031)\021\t)#a\013\021\007)\t9#C\002\002*\021\021qAQ8pY\026\fg\016C\004l\003;\001\r!!\f\021\t9i\027q\006\t\004%\005EBAB5\002\036\t\007a\rC\004\0026\001!\t%a\016\002\tYLWm^\013\003\003s\021R!a\017\n\0031QA\t\001\001\003sI1!!\016\020!\025q\021\021I\t\035\023\r\t\031E\001\002\r\023R,'/\0312mKZKWm\036\005\b\003k\001A\021IA$)\031\ty$!\023\002N!9\0211JA#\001\004Y\024\001\0024s_6Dq!a\024\002F\001\0071(A\003v]RLG\016")
public interface IterableProxyLike<A, Repr extends IterableLike<A, Repr> & Iterable<A>> extends IterableLike<A, Repr>, TraversableProxyLike<A, Repr> {
  Iterator<A> iterator();
  
  Iterator<Repr> grouped(int paramInt);
  
  Iterator<Repr> sliding(int paramInt);
  
  Iterator<Repr> sliding(int paramInt1, int paramInt2);
  
  Repr takeRight(int paramInt);
  
  Repr dropRight(int paramInt);
  
  <A1, B, That> That zip(GenIterable<B> paramGenIterable, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
  
  <B, A1, That> That zipAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
  
  <A1, That> That zipWithIndex(CanBuildFrom<Repr, Tuple2<A1, Object>, That> paramCanBuildFrom);
  
  <B> boolean sameElements(GenIterable<B> paramGenIterable);
  
  Object view();
  
  IterableView<A, Repr> view(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableProxyLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */