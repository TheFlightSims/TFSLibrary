package scala.collection.generic;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001]2q!\001\002\021\002G\005\021BA\nHK:,'/[2QCJ\034u.\0349b]&|gN\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006&'\t\0011\002\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032DQ\001\005\001\007\002E\t!B\\3x\005VLG\016Z3s+\t\0212$F\001\024!\021!r#\007\023\016\003UQ!A\006\003\002\021A\f'/\0317mK2L!\001G\013\003\021\r{WNY5oKJ\004\"AG\016\r\001\021)Ad\004b\001;\t\t\021)\005\002\037CA\021AbH\005\003A\031\021qAT8uQ&tw\r\005\002\rE%\0211E\002\002\004\003:L\bc\001\016&3\0211a\005\001CC\002\035\022!aQ\"\026\005!j\023C\001\020*!\r!\"\006L\005\003WU\0211\002U1s\023R,'/\0312mKB\021!$\f\003\006]\025\022\r!\b\002\0021\")\001\007\001D\001c\005Ya.Z<D_6\024\027N\\3s+\t\021T'F\0014!\021!r\003\016\034\021\005i)D!\002\0170\005\004i\002c\001\016&i\001")
public interface GenericParCompanion<CC extends scala.collection.parallel.ParIterable<Object>> {
  <A> Combiner<A, CC> newBuilder();
  
  <A> Combiner<A, CC> newCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParCompanion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */