package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005\005aaB\001\003!\003\r\t!\003\002#\017\026tWM]5d\0072\f7o\035+bOR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025U\0013c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\tA\t2CH\007\002\005%\021!C\001\002\016\021\006\034h*Z<Ck&dG-\032:\021\005Q)B\002\001\003\007-\001!)\031A\f\003\003\005\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!os*\022qd\013\t\004)\001\032BAB\021\001\t\013\007!E\001\002D\007V\0211%K\t\0031\021\0022!\n\024)\033\005!\021BA\024\005\005-!&/\031<feN\f'\r\\3\021\005QIC!\002\026!\005\0049\"!\001-,\0031\002\"!\f\032\016\0039R!a\f\031\002\023Ut7\r[3dW\026$'BA\031\007\003)\tgN\\8uCRLwN\\\005\003g9\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\025)\004\001\"\0017\003\031!\023N\\5uIQ\tq\007\005\002\rq%\021\021H\002\002\005+:LG\017C\004<\001\t\007k1\003\037\002\007Q\fw-F\001>!\rq\024iE\007\002)\021\001IB\001\be\0264G.Z2u\023\t\021uH\001\005DY\006\0348\017V1h\021\025!\005A\"\001F\003E\031G.Y:t)\006<7i\\7qC:LwN\\\013\002\rB\031\001cR%\n\005!\023!\001G$f]\026\024\030nY\"mCN\034H+Y4D_6\004\030M\\5p]B\021A\003\t\005\006\027\002!\t\001T\001\027O\026tWM]5d\0072\f7o\035+bO\n+\030\016\0343feV\021Q*\026\013\003\035b\003Ba\024*U/6\t\001K\003\002R\t\0059Q.\036;bE2,\027BA*Q\005\035\021U/\0337eKJ\004\"\001F+\005\013YS%\031A\f\003\003\t\0032\001\006\021U\021\025Y$\nq\001Z!\rq\024\t\026\005\0067\002!\t\001X\001\027G2\f7o]'b]&4Wm\035;D_6\004\030M\\5p]V\tQ\fE\002_C&s!\001E0\n\005\001\024\021a\0029bG.\fw-Z\005\003E\016\024QdR3oKJL7m\0217bgNl\025M\\5gKN$8i\\7qC:LwN\034\006\003A\nACAW3iUB\021ABZ\005\003O\032\021!\002Z3qe\026\034\027\r^3eC\005I\027!H;tK\002\032G.Y:t)\006<7i\\7qC:LwN\034\021j]N$X-\0313\"\003-\faA\r\0302a9\002\004\"B7\001\t\003q\027aG4f]\026\024\030nY\"mCN\034X*\0318jM\026\034HOQ;jY\022,'/\006\002peR\021\001\017\036\t\005\037J\0138\017\005\002\025e\022)a\013\034b\001/A\031A\003I9\t\013Ud\0079\001<\002\0215\fg.\0334fgR\0042a\036>r\035\ta\0010\003\002z\r\0051\001K]3eK\032L!a\037?\003\033\rc\027m]:NC:Lg-Z:u\025\tIh\001\013\003mKzT\027%A@\002EU\034X\rI4f]\026\024\030nY\"mCN\034H+Y4Ck&dG-\032:!S:\034H/Z1e\001")
public interface GenericClassTagTraversableTemplate<A, CC extends scala.collection.Traversable<Object>> extends HasNewBuilder<A, CC> {
  ClassTag<A> tag();
  
  GenericClassTagCompanion<CC> classTagCompanion();
  
  <B> Builder<B, CC> genericClassTagBuilder(ClassTag<B> paramClassTag);
  
  GenericClassTagCompanion<CC> classManifestCompanion();
  
  <B> Builder<B, CC> genericClassManifestBuilder(ClassTag<B> paramClassTag);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericClassTagTraversableTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */