package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.collection.generic.CanCombineFrom;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001E3q!\001\002\021\002\007\005\021B\001\006GC\016$xN]=PaNT!a\001\003\002\021A\f'/\0317mK2T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)BA\003\037@\005N\021\001a\003\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\"\002\t\001\t\003\t\022A\002\023j]&$H\005F\001\023!\ta1#\003\002\025\r\t!QK\\5u\r\0351\002\001%A\022\002]\021\021b\024;iKJ<\030n]3\026\005aq2CA\013\f\021\025QRC\"\001\034\003%yG\017[3so&\034X\r\006\002\035OA\021QD\b\007\001\t\025yRC1\001!\005\005\021\026CA\021%!\ta!%\003\002$\r\t9aj\034;iS:<\007C\001\007&\023\t1cAA\002B]fDa\001K\r\005\002\004I\023a\0028pi\n|G-\037\t\004\031)b\022BA\026\007\005!a$-\0378b[\026t\004\"B\027\001\r\003q\023AC5t!\006\024\030\r\0347fYV\tq\006\005\002\ra%\021\021G\002\002\b\005>|G.Z1o\021\025\031\004A\"\0015\003)\t7\017U1sC2dW\r\\\013\002kA)a'O\036?\0036\tqG\003\0029\t\0059q-\0328fe&\034\027B\001\0368\0059\031\025M\\\"p[\nLg.\032$s_6\004\"!\b\037\005\013u\002!\031\001\021\003\t\031\023x.\034\t\003;}\"Q\001\021\001C\002\001\022A!\0227f[B\021QD\021\003\006\007\002\021\r\001\t\002\003)>DQ!\022\001\007\002\031\013!\"\0334QCJ\fG\016\\3m+\t95\n\006\002I\031B\031\021*\006&\016\003\001\001\"!H&\005\013}!%\031\001\021\t\0135#\005\031\001(\002\r%\034(m\0343z!\021aq*\016&\n\005A3!!\003$v]\016$\030n\03482\001")
public interface FactoryOps<From, Elem, To> {
  boolean isParallel();
  
  CanCombineFrom<From, Elem, To> asParallel();
  
  <R> Otherwise<R> ifParallel(Function1<CanCombineFrom<From, Elem, To>, R> paramFunction1);
  
  public interface Otherwise<R> {
    R otherwise(Function0<R> param1Function0);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\FactoryOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */