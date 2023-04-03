package scala.collection.parallel;

import scala.collection.GenSeq;
import scala.collection.Seq;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005\025baB\001\003!\003\r\t!\003\002\007!\006\0248+Z9\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2C\002\001\f\037y\021\023\006\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\t\024\033\005!\021B\001\n\005\005\0319UM\\*fcB\021A#\006\007\001\t\0311\002\001\"b\001/\t\tA+\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bcA\020!'5\t!!\003\002\"\005\tY\001+\031:Ji\026\024\030M\0317f!\021\031ce\005\025\016\003\021R!!\n\003\002\017\035,g.\032:jG&\021q\005\n\002\023\017\026tWM]5d!\006\024H+Z7qY\006$X\r\005\002 \001A)qDK\n-[%\0211F\001\002\013!\006\0248+Z9MS.,\007cA\020\001'A\031aFN\n\017\005=\"dB\001\0314\033\005\t$B\001\032\t\003\031a$o\\8u}%\tq!\003\0026\r\0059\001/Y2lC\036,\027BA\0349\005\r\031V-\035\006\003k\031AQA\017\001\005\002m\na\001J5oSR$C#\001\037\021\0051i\024B\001 \007\005\021)f.\033;\t\013\001\003A\021I!\002\023\r|W\016]1oS>tW#\001\"\023\007\r+\005J\002\003E\001\001\021%\001\004\037sK\032Lg.Z7f]Rt\004cA\022GQ%\021q\t\n\002\021\017\026tWM]5d\007>l\007/\0318j_:\0042aI%)\023\tQEEA\nHK:,'/[2QCJ\034u.\0349b]&|g\016C\003M\001\031\005Q*A\003baBd\027\020\006\002\024\035\")qj\023a\001!\006\t\021\016\005\002\r#&\021!K\002\002\004\023:$\b\"\002+\001\t\003*\026\001\003;p'R\024\030N\\4\025\003Y\003\"a\026.\017\0051A\026BA-\007\003\031\001&/\0323fM&\0211\f\030\002\007'R\024\030N\\4\013\005e3\001\"\0020\001\t\003z\026\001D:ue&tw\r\025:fM&DX#\0011\021\005\0054W\"\0012\013\005\r$\027\001\0027b]\036T\021!Z\001\005U\0064\030-\003\002\\E\036)\001N\001E\001S\0061\001+\031:TKF\004\"a\b6\007\013\005\021\001\022A6\024\005)d\007cA\022nQ%\021a\016\n\002\013!\006\024h)Y2u_JL\b\"\0029k\t\003\t\030A\002\037j]&$h\bF\001j\021\025\031(\016b\001u\0031\031\027M\034\"vS2$gI]8n+\t)h0F\001w!\025\031s/_?\000\023\tAHE\001\bDC:\034u.\0342j]\0264%o\\7\021\005i\\X\"\0016\n\005q4%\001B\"pY2\004\"\001\006@\005\013Y\021(\031A\f\021\007}\001Q\020C\004\002\004)$\t!!\002\002\0259,wOQ;jY\022,'/\006\003\002\b\005EQCAA\005!\035y\0221BA\b\003'I1!!\004\003\005!\031u.\0342j]\026\024\bc\001\013\002\022\0211a#!\001C\002]\001Ba\b\001\002\020!9\021q\0036\005\002\005e\021a\0038fo\016{WNY5oKJ,B!a\007\002\"U\021\021Q\004\t\b?\005-\021qDA\022!\r!\022\021\005\003\007-\005U!\031A\f\021\t}\001\021q\004")
public interface ParSeq<T> extends GenSeq<T>, ParIterable<T>, GenericParTemplate<T, ParSeq>, ParSeqLike<T, ParSeq<T>, Seq<T>> {
  GenericCompanion<ParSeq> companion();
  
  T apply(int paramInt);
  
  String toString();
  
  String stringPrefix();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */