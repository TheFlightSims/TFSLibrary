package scala.collection.mutable;

import scala.collection.Seq;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005\021BA\002TKFT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\007\001-ya$\t\025\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\002\021#Mi\021AA\005\003%\t\021\001\"\023;fe\006\024G.\032\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\007}\0013#D\001\005\023\t\tA\001\005\003#KM9S\"A\022\013\005\021\"\021aB4f]\026\024\030nY\005\003M\r\022!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004\"\001\005\001\021\tAI3cK\005\003U\t\021qaU3r\031&\\W\rE\002\021\001MAQ!\f\001\005\0029\na\001J5oSR$C#A\030\021\0051\001\024BA\031\007\005\021)f.\033;\t\013M\002A\021\t\033\002\023\r|W\016]1oS>tW#A\033\021\007\t2t%\003\0028G\t\001r)\0328fe&\0347i\\7qC:LwN\034\005\006s\001!\tEO\001\004g\026\fX#A\026\b\013q\022\001\022A\037\002\007M+\027\017\005\002\021}\031)\021A\001E\001M\021a\b\021\t\004E\005;\023B\001\"$\005)\031V-\035$bGR|'/\037\005\006\tz\"\t!R\001\007y%t\027\016\036 \025\003uBQa\022 \005\004!\013AbY1o\005VLG\016\032$s_6,\"!\023*\026\003)\003RAI&N#NK!\001T\022\003\031\r\013gNQ;jY\0224%o\\7\021\0059{U\"\001 \n\005A3$\001B\"pY2\004\"\001\006*\005\013Y1%\031A\f\021\007A\001\021\013C\003V}\021\005a+\001\006oK^\024U/\0337eKJ,\"a\026/\026\003a\003B\001E-\\;&\021!L\001\002\b\005VLG\016Z3s!\t!B\fB\003\027)\n\007q\003E\002\021\001m\003")
public interface Seq<A> extends Iterable<A>, Seq<A>, GenericTraversableTemplate<A, Seq>, SeqLike<A, Seq<A>> {
  GenericCompanion<Seq> companion();
  
  Seq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Seq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */