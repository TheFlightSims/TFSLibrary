package scala.collection.mutable;

import scala.Mutable;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0054q!\001\002\021\002\007\005\021BA\006Ue\0064XM]:bE2,'BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"A\003\013\024\r\001Yq\"H\023*!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\021R\"\001\003\n\005\005!\001CA\n\025\031\001!Q!\006\001C\002Y\021\021!Q\t\003/i\001\"\001\004\r\n\005e1!a\002(pi\"Lgn\032\t\003\031mI!\001\b\004\003\007\005s\027\020\005\003\037CI\031S\"A\020\013\005\001\"\021aB4f]\026\024\030nY\005\003E}\021!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004\"\001\n\001\016\003\t\001B\001\005\024\023Q%\021q\005\002\002\020)J\fg/\032:tC\ndW\rT5lKB\031A\005\001\n\021\0051Q\023BA\026\007\005\035iU\017^1cY\026DQ!\f\001\005\0029\na\001J5oSR$C#A\030\021\0051\001\024BA\031\007\005\021)f.\033;\t\013M\002A\021\t\033\002\023\r|W\016]1oS>tW#A\033\021\007y14%\003\0028?\t\001r)\0328fe&\0347i\\7qC:LwN\034\005\006s\001!\tEO\001\004g\026\fX#\001\025\b\013q\022\001\022A\037\002\027Q\023\030M^3sg\006\024G.\032\t\003Iy2Q!\001\002\t\002}\0322A\020!D!\rq\022iI\005\003\005~\021QcR3o)J\fg/\032:tC\ndWMR1di>\024\030\020E\002\037\t\016J!!R\020\003%Q\023\030M^3sg\006\024G.\032$bGR|'/\037\005\006\017z\"\t\001S\001\007y%t\027\016\036 \025\003uBQA\023 \005\004-\013AbY1o\005VLG\016\032$s_6,\"\001T+\026\0035\003RA\b(Q)ZK!aT\020\003\031\r\013gNQ;jY\0224%o\\7\021\005E\023V\"\001 \n\005M3$\001B\"pY2\004\"aE+\005\013UI%\031\001\f\021\007\021\002A\013C\003Y}\021\005\021,\001\006oK^\024U/\0337eKJ,\"AW0\026\003m\003B\001\n/_A&\021QL\001\002\b\005VLG\016Z3s!\t\031r\fB\003\026/\n\007a\003E\002%\001y\003")
public interface Traversable<A> extends Traversable<A>, GenericTraversableTemplate<A, Traversable>, TraversableLike<A, Traversable<A>>, Mutable {
  GenericCompanion<Traversable> companion();
  
  Traversable<A> seq();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Traversable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */