package scala.collection;

import scala.Tuple2;
import scala.collection.generic.CanBuildFrom;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00194q!\001\002\021\002G\005qAA\bHK:LE/\032:bE2,G*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\rA1CG\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\r\te.\037\t\005\035=\t\022$D\001\003\023\t\001\"A\001\nHK:$&/\031<feN\f'\r\\3MS.,\007C\001\n\024\031\001!a\001\006\001\005\006\004)\"!A!\022\005YI\001C\001\006\030\023\tABAA\004O_RD\027N\\4\021\005IQBAB\016\001\t\013\007QC\001\003SKB\024\b\"B\017\001\r\003q\022\001C5uKJ\fGo\034:\026\003}\0012A\004\021\022\023\t\t#A\001\005Ji\026\024\030\r^8s\021\025\031\003A\"\001%\0031\031\030-\\3FY\026lWM\034;t+\t)s\006\006\002'SA\021!bJ\005\003Q\021\021qAQ8pY\026\fg\016C\003+E\001\0071&\001\003uQ\006$\bc\001\b-]%\021QF\001\002\f\017\026t\027\n^3sC\ndW\r\005\002\023_\021)\001G\tb\001c\t\021\021)M\t\003#%AQa\r\001\007\002Q\n1A_5q+\021)d\t\023\035\025\005YRECA\034;!\t\021\002\bB\003:e\t\007QC\001\003UQ\006$\b\"B\0363\001\ba\024A\0012g!\025i\004)\007\"8\033\005q$BA \003\003\0359WM\\3sS\016L!!\021 \003\031\r\013gNQ;jY\0224%o\\7\021\t)\031UiR\005\003\t\022\021a\001V;qY\026\024\004C\001\nG\t\025\001$G1\0012!\t\021\002\nB\003Je\t\007QCA\001C\021\025Q#\0071\001L!\rqAf\022\005\006\033\0021\tAT\001\ru&\004x+\033;i\023:$W\r_\013\004\037Z\013FC\001)S!\t\021\022\013B\003:\031\n\007Q\003C\003<\031\002\0171\013E\003>\001f!\006\013\005\003\013\007V;\006C\001\nW\t\025\001DJ1\0012!\tQ\001,\003\002Z\t\t\031\021J\034;\t\013m\003a\021\001/\002\riL\007/\0217m+\021iv-\0321\025\tyC'\016\034\013\003?\006\004\"A\0051\005\013eR&\031A\013\t\013mR\0069\0012\021\013u\002\025dY0\021\t)\031EM\032\t\003%\025$Q\001\r.C\002E\002\"AE4\005\013%S&\031A\013\t\013)R\006\031A5\021\0079ac\rC\003l5\002\007A-\001\005uQ&\034X\t\\3n\021\025i'\f1\001g\003!!\b.\031;FY\026l\007")
public interface GenIterableLike<A, Repr> extends GenTraversableLike<A, Repr> {
  Iterator<A> iterator();
  
  <A1> boolean sameElements(GenIterable<A1> paramGenIterable);
  
  <A1, B, That> That zip(GenIterable<B> paramGenIterable, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
  
  <A1, That> That zipWithIndex(CanBuildFrom<Repr, Tuple2<A1, Object>, That> paramCanBuildFrom);
  
  <B, A1, That> That zipAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenIterableLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */