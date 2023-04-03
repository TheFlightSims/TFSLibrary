package scala.collection;

import scala.PartialFunction;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0254q!\001\002\021\002\007\005qAA\002TKFT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)\"\001C\013\024\017\001IQB\b\022&YA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t)q\001cE\005\003\037\021\021q\002U1si&\fGNR;oGRLwN\034\t\003\025EI!A\005\003\003\007%sG\017\005\002\025+1\001AA\002\f\001\t\013\007qCA\001B#\tA2\004\005\002\0133%\021!\004\002\002\b\035>$\b.\0338h!\tQA$\003\002\036\t\t\031\021I\\=\021\007}\0013#D\001\003\023\t\t#A\001\005Ji\026\024\030M\0317f!\ry2eE\005\003I\t\021aaR3o'\026\f\b\003\002\024*'-j\021a\n\006\003Q\t\tqaZ3oKJL7-\003\002+O\tQr)\0328fe&\034GK]1wKJ\034\030M\0317f)\026l\007\017\\1uKB\021q\004\001\t\005?5\032r&\003\002/\005\t91+Z9MS.,\007cA\020\001'!)\021\007\001C\001e\0051A%\0338ji\022\"\022a\r\t\003\025QJ!!\016\003\003\tUs\027\016\036\005\006o\001!\t\005O\001\nG>l\007/\0318j_:,\022!\017\t\004MiZ\023BA\036(\005A9UM\\3sS\016\034u.\0349b]&|g\016C\003>\001\021\005c(A\002tKF,\022aL\004\006\001\nA\t!Q\001\004'\026\f\bCA\020C\r\025\t!\001#\001D'\t\021E\tE\002'\013.J!AR\024\003\025M+\027OR1di>\024\030\020C\003I\005\022\005\021*\001\004=S:LGO\020\013\002\003\")1J\021C\002\031\006a1-\0318Ck&dGM\022:p[V\021QJV\013\002\035B)aeT)V/&\021\001k\n\002\r\007\006t')^5mI\032\023x.\034\t\003%Nk\021AQ\005\003)j\022AaQ8mYB\021AC\026\003\006-)\023\ra\006\t\004?\001)\006\"B-C\t\003Q\026A\0038fo\n+\030\016\0343feV\0211lY\013\0029B!Q\f\0312e\033\005q&BA0\003\003\035iW\017^1cY\026L!!\0310\003\017\t+\030\016\0343feB\021Ac\031\003\006-a\023\ra\006\t\004?\001\021\007")
public interface Seq<A> extends PartialFunction<Object, A>, Iterable<A>, GenSeq<A>, GenericTraversableTemplate<A, Seq>, SeqLike<A, Seq<A>> {
  GenericCompanion<Seq> companion();
  
  Seq<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Seq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */