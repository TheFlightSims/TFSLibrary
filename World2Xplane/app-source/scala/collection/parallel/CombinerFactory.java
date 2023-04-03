package scala.collection.parallel;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001)2q!\001\002\021\002G\005\021BA\bD_6\024\027N\\3s\r\006\034Go\034:z\025\t\031A!\001\005qCJ\fG\016\\3m\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQ\001DI\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021\025\001\002A\"\001\022\003\025\t\007\017\0357z)\005\021\002\003B\n\025-\005j\021AA\005\003+\t\021\001bQ8nE&tWM\035\t\003/aa\001\001B\003\032\001\t\007!DA\001V#\tYb\004\005\002\r9%\021QD\002\002\b\035>$\b.\0338h!\taq$\003\002!\r\t\031\021I\\=\021\005]\021C!B\022\001\005\004Q\"\001\002*faJDQ!\n\001\007\002\031\n!\003Z8fgNC\027M]3D_6\024\027N\\3sgV\tq\005\005\002\rQ%\021\021F\002\002\b\005>|G.Z1o\001")
public interface CombinerFactory<U, Repr> {
  Combiner<U, Repr> apply();
  
  boolean doesShareCombiners();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\CombinerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */