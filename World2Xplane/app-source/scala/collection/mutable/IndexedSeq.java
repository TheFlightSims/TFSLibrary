package scala.collection.mutable;

import scala.collection.IndexedSeq;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005\021B\001\006J]\022,\0070\0323TKFT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\007\001-ya$\t\025\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\0211aU3r!\t!R\003\004\001\005\013Y\001!\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\031q\004I\n\016\003\021I!!\001\003\021\t\t*3cJ\007\002G)\021A\005B\001\bO\026tWM]5d\023\t13E\001\016HK:,'/[2Ue\0064XM]:bE2,G+Z7qY\006$X\r\005\002\021\001A!\001#K\n,\023\tQ#A\001\bJ]\022,\0070\0323TKFd\025n[3\021\007A\0011\003C\003.\001\021\005a&\001\004%S:LG\017\n\013\002_A\021A\002M\005\003c\031\021A!\0268ji\")1\007\001C!i\005I1m\\7qC:LwN\\\013\002kA\031!EN\024\n\005]\032#\001E$f]\026\024\030nY\"p[B\fg.[8o\021\025I\004\001\"\021;\003\r\031X-]\013\002W\035)AH\001E\001{\005Q\021J\0343fq\026$7+Z9\021\005Aqd!B\001\003\021\003y4C\001 A!\r\021\023iJ\005\003\005\016\022!bU3r\r\006\034Go\034:z\021\025!e\b\"\001F\003\031a\024N\\5u}Q\tQ\bC\003H}\021\r\001*\001\007dC:\024U/\0337e\rJ|W.\006\002J%V\t!\nE\003#\0276\0136+\003\002MG\ta1)\0318Ck&dGM\022:p[B\021ajT\007\002}%\021\001K\016\002\005\007>dG\016\005\002\025%\022)aC\022b\001/A\031\001\003A)\t\013UsD\021\001,\002\0259,wOQ;jY\022,'/\006\002X9V\t\001\f\005\003\0213nk\026B\001.\003\005\035\021U/\0337eKJ\004\"\001\006/\005\013Y!&\031A\f\021\007A\0011\f")
public interface IndexedSeq<A> extends Seq<A>, IndexedSeq<A>, GenericTraversableTemplate<A, IndexedSeq>, IndexedSeqLike<A, IndexedSeq<A>> {
  GenericCompanion<IndexedSeq> companion();
  
  IndexedSeq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */