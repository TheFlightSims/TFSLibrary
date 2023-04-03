package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001Y2q!\001\002\021\002G\005\021B\001\007DC:\024U/\0337e\rJ|WN\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Q\003\002\006*5\021\032\"\001A\006\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rC\003\021\001\031\005\021#A\003baBd\027\020\006\002\023MA!1C\006\r$\033\005!\"BA\013\005\003\035iW\017^1cY\026L!a\006\013\003\017\t+\030\016\0343feB\021\021D\007\007\001\t\031Y\002\001#b\0019\t!Q\t\\3n#\ti\002\005\005\002\r=%\021qD\002\002\b\035>$\b.\0338h!\ta\021%\003\002#\r\t\031\021I\\=\021\005e!CAB\023\001\t\013\007AD\001\002U_\")qe\004a\001Q\005!aM]8n!\tI\022\006\002\004+\001!\025\r\001\b\002\005\rJ|W\016C\003\021\001\031\005A\006F\001\023Q\r\001a\006\016\t\003_Ij\021\001\r\006\003c\031\t!\"\0318o_R\fG/[8o\023\t\031\004G\001\tj[Bd\027nY5u\035>$hi\\;oI\006\nQ'\0019DC:tw\016\036\021d_:\034HO];di\002\n\007eY8mY\026\034G/[8oA=4\007\005^=qK\002\"3\020V8~A]LG\017\033\021fY\026lWM\034;tA=4\007\005^=qK\002\"30\0227f[v\004#-Y:fI\002zg\016I1!G>dG.Z2uS>t\007e\0344!if\004X\r\t\023|\rJ|W. \030")
public interface CanBuildFrom<From, Elem, To> {
  Builder<Elem, To> apply(From paramFrom);
  
  Builder<Elem, To> apply();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\CanBuildFrom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */