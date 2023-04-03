package scala.collection.parallel.immutable;

import scala.collection.GenSet;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericParTemplate;
import scala.collection.immutable.Set;
import scala.collection.parallel.ParSet;
import scala.collection.parallel.ParSetLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005\035aaB\001\003!\003\r\ta\003\002\007!\006\0248+\032;\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\005qCJ\fG\016\\3m\025\t9\001\"\001\006d_2dWm\031;j_:T\021!C\001\006g\016\fG.Y\002\001+\taqcE\004\001\033E\001\003f\013\030\021\0059yQ\"\001\005\n\005AA!AB!osJ+g\rE\002\023'Ui\021AB\005\003)\031\021aaR3o'\026$\bC\001\f\030\031\001!Q\001\007\001C\002e\021\021\001V\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020\005\003\"IU1S\"\001\022\013\005\r2\021aB4f]\026\024\030nY\005\003K\t\022!cR3oKJL7\rU1s)\026l\007\017\\1uKB\021q\005A\007\002\005A\031\021FK\013\016\003\021I!!\001\003\021\007\035bS#\003\002.\005\tY\001+\031:Ji\026\024\030M\0317f!\025Is&F\0313\023\t\001DA\001\006QCJ\034V\r\036'jW\026\0042a\n\001\026!\r\031T'F\007\002i)\0211AB\005\003mQ\0221aU3u\021\025A\004\001\"\001:\003\031!\023N\\5uIQ\t!\b\005\002\017w%\021A\b\003\002\005+:LG\017C\003?\001\021\005s(A\003f[B$\0300F\0012\021\025\t\005\001\"\021C\003%\031w.\0349b]&|g.F\001D%\r!e)\023\004\005\013\002\0011I\001\007=e\0264\027N\\3nK:$h\bE\002\"\017\032J!\001\023\022\003!\035+g.\032:jG\016{W\016]1oS>t\007cA\021KM%\0211J\t\002\024\017\026tWM]5d!\006\0248i\\7qC:LwN\034\005\006\033\002!\tET\001\rgR\024\030N\\4Qe\0264\027\016_\013\002\037B\021\001+V\007\002#*\021!kU\001\005Y\006twMC\001U\003\021Q\027M^1\n\005Y\013&AB*ue&tw\rC\003Y\001\021\005\023,A\003u_N+G/\006\002[;V\t1\fE\002(\001q\003\"AF/\005\013y;&\031A0\003\003U\013\"!F\017\b\013\005\024\001\022\0012\002\rA\013'oU3u!\t93MB\003\002\005!\005Am\005\002dKB\031\021E\032\024\n\005\035\024#!\004)beN+GOR1di>\024\030\020C\003jG\022\005!.\001\004=S:LGO\020\013\002E\")An\031C\001[\006Ya.Z<D_6\024\027N\\3s+\tq7/F\001p!\021I\003O\035;\n\005E$!\001C\"p[\nLg.\032:\021\005Y\031H!\002\rl\005\004I\002cA\024\001e\")ao\031C\002o\006a1-\0318Ck&dGM\022:p[V\031\0010a\001\026\003e\004r!\t>}\003\003\t)!\003\002|E\tq1)\0318D_6\024\027N\\3Ge>l\007CA?\033\005\031\027BA@H\005\021\031u\016\0347\021\007Y\t\031\001B\003\031k\n\007\021\004\005\003(\001\005\005\001")
public interface ParSet<T> extends GenSet<T>, GenericParTemplate<T, ParSet>, ParSet<T>, ParIterable<T>, ParSetLike<T, ParSet<T>, Set<T>> {
  ParSet<T> empty();
  
  GenericCompanion<ParSet> companion();
  
  String stringPrefix();
  
  <U> ParSet<U> toSet();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */