package scala.collection.parallel.immutable;

import scala.collection.GenSeq;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.immutable.Seq;
import scala.collection.parallel.ParSeq;
import scala.collection.parallel.ParSeqLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]4q!\001\002\021\002\007\0051B\001\004QCJ\034V-\035\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021\001\0039be\006dG.\0327\013\005\035A\021AC2pY2,7\r^5p]*\t\021\"A\003tG\006d\027m\001\001\026\005192c\002\001\016#\001\032sE\f\t\003\035=i\021\001C\005\003!!\021a!\0218z%\0264\007c\001\n\024+5\ta!\003\002\025\r\t1q)\0328TKF\004\"AF\f\r\001\0211\001\004\001CC\002e\021\021\001V\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020E\002\"EUi\021\001B\005\003\003\021\0012\001J\023\026\033\005\021\021B\001\024\003\005-\001\026M]%uKJ\f'\r\\3\021\t!ZS#L\007\002S)\021!FB\001\bO\026tWM]5d\023\ta\023F\001\nHK:,'/[2QCJ$V-\0349mCR,\007C\001\023\001!\025\ts&F\0313\023\t\001DA\001\006QCJ\034V-\035'jW\026\0042\001\n\001\026!\r\031T'F\007\002i)\0211AB\005\003mQ\0221aU3r\021\025A\004\001\"\001:\003\031!\023N\\5uIQ\t!\b\005\002\017w%\021A\b\003\002\005+:LG\017C\003?\001\021\005s(A\005d_6\004\030M\\5p]V\t\001IE\002B\007\0323AA\021\001\001\001\naAH]3gS:,W.\0328u}A\031\001\006R\027\n\005\025K#\001E$f]\026\024\030nY\"p[B\fg.[8o!\rAs)L\005\003\021&\0221cR3oKJL7\rU1s\007>l\007/\0318j_:DQA\023\001\005B-\013Q\001^8TKF,\022!M\004\006\033\nA\tAT\001\007!\006\0248+Z9\021\005\021ze!B\001\003\021\003\0016CA(R!\rA#+L\005\003'&\022!\002U1s\r\006\034Go\034:z\021\025)v\n\"\001W\003\031a\024N\\5u}Q\ta\nC\003Y\037\022\r\021,\001\007dC:\024U/\0337e\rJ|W.\006\002[GV\t1\fE\003)9z\023G-\003\002^S\tq1)\0318D_6\024\027N\\3Ge>l\007CA0a\033\005y\025BA1E\005\021\031u\016\0347\021\005Y\031G!\002\rX\005\004I\002c\001\023\001E\")am\024C\001O\006Qa.Z<Ck&dG-\032:\026\005!lW#A5\021\t\005RGN\\\005\003W\022\021\001bQ8nE&tWM\035\t\003-5$Q\001G3C\002e\0012\001\n\001m\021\025\001x\n\"\001r\003-qWm^\"p[\nLg.\032:\026\005I,X#A:\021\t\005RGO\036\t\003-U$Q\001G8C\002e\0012\001\n\001u\001")
public interface ParSeq<T> extends GenSeq<T>, ParSeq<T>, ParIterable<T>, GenericParTemplate<T, ParSeq>, ParSeqLike<T, ParSeq<T>, Seq<T>> {
  GenericCompanion<ParSeq> companion();
  
  ParSeq<T> toSeq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */