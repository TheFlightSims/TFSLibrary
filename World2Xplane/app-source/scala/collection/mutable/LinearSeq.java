package scala.collection.mutable;

import scala.collection.LinearSeq;
import scala.collection.LinearSeqLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y3q!\001\002\021\002\007\005\021BA\005MS:,\027M]*fc*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQc\005\004\001\027=q\022\005\013\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007c\001\t\022'5\t!!\003\002\023\005\t\0311+Z9\021\005Q)B\002\001\003\006-\001\021\ra\006\002\002\003F\021\001d\007\t\003\031eI!A\007\004\003\0179{G\017[5oOB\021A\002H\005\003;\031\0211!\0218z!\ry\002eE\007\002\t%\021\021\001\002\t\005E\025\032r%D\001$\025\t!C!A\004hK:,'/[2\n\005\031\032#AG$f]\026\024\030n\031+sCZ,'o]1cY\026$V-\0349mCR,\007C\001\t\001!\021y\022fE\026\n\005)\"!!\004'j]\026\f'oU3r\031&\\W\rE\002\021\001MAQ!\f\001\005\0029\na\001J5oSR$C#A\030\021\0051\001\024BA\031\007\005\021)f.\033;\t\013M\002A\021\t\033\002\023\r|W\016]1oS>tW#A\033\021\007\t2t%\003\0028G\t\001r)\0328fe&\0347i\\7qC:LwN\034\005\006s\001!\tEO\001\004g\026\fX#A\026\b\013q\022\001\022A\037\002\0231Kg.Z1s'\026\f\bC\001\t?\r\025\t!\001#\001@'\tq\004\tE\002#\003\036J!AQ\022\003\025M+\027OR1di>\024\030\020C\003E}\021\005Q)\001\004=S:LGO\020\013\002{!)qI\020C\002\021\006a1-\0318Ck&dGM\022:p[V\021\021JU\013\002\025B)!eS'R'&\021Aj\t\002\r\007\006t')^5mI\032\023x.\034\t\003\035>k\021AP\005\003!Z\022AaQ8mYB\021AC\025\003\006-\031\023\ra\006\t\004!\001\t\006\"B+?\t\0031\026A\0038fo\n+\030\016\0343feV\021q\013X\013\0021B!\001#W.^\023\tQ&AA\004Ck&dG-\032:\021\005QaF!\002\fU\005\0049\002c\001\t\0017\002")
public interface LinearSeq<A> extends Seq<A>, LinearSeq<A>, GenericTraversableTemplate<A, LinearSeq>, LinearSeqLike<A, LinearSeq<A>> {
  GenericCompanion<LinearSeq> companion();
  
  LinearSeq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinearSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */