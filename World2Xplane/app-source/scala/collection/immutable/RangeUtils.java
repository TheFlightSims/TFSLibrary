package scala.collection.immutable;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001E4q!\001\002\021\002\007\005\021B\001\006SC:<W-\026;jYNT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006+'\t\0011\002\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032DQ\001\005\001\005\002E\ta\001J5oSR$C#\001\n\021\0051\031\022B\001\013\007\005\021)f.\033;\t\013Y\001a\021A\f\002\013M$\030M\035;\026\003a\001\"\001D\r\n\005i1!aA%oi\")A\004\001D\001/\005\031QM\0343\t\013y\001a\021A\f\002\tM$X\r\035\005\006A\0011\t!I\001\nS:\034G.^:jm\026,\022A\t\t\003\031\rJ!\001\n\004\003\017\t{w\016\\3b]\")a\005\001D\001O\00511M]3bi\026$R\001\013\0325ma\002\"!\013\026\r\001\02111\006\001CC\0021\022AAU3qeF\021Q\006\r\t\003\0319J!a\f\004\003\0179{G\017[5oOB\031\021\007\001\025\016\003\tAQaM\023A\002a\taaX:uCJ$\b\"B\033&\001\004A\022\001B0f]\022DQaN\023A\002a\tQaX:uKBDQ!O\023A\002\t\n!bX5oG2,8/\033<f\021\025Y\004\001\"\004\030\0035Ign\0317vg&4X\rT1ti\")Q\b\001C\003/\005)q\f\\1ti\")q\b\001C\003\001\006AqLZ8sK\006\034\007.\006\002B\021R\021!C\021\005\006\007z\002\r\001R\001\002MB!A\"\022\rH\023\t1eAA\005Gk:\034G/[8ocA\021\021\006\023\003\006\023z\022\rA\023\002\002+F\021Qf\023\t\003\0311K!!\024\004\003\007\005s\027\020C\003P\001\021\025q#A\004`Y\026tw\r\0365\t\013E\003AQ\001*\002\r}\013\007\017\0357z)\tA2\013C\003U!\002\007\001$A\002jIbDQA\026\001\005\n]\013a\002\\8dCRLwN\\!gi\026\024h\n\006\002\0311\")\021,\026a\0011\005\ta\016C\003\\\001\021\025A,A\003`i\006\\W\r\006\002);\")\021L\027a\0011!)q\f\001C\003A\006)q\f\032:paR\021\001&\031\005\0063z\003\r\001\007\005\006G\002!)\001Z\001\007?Nd\027nY3\025\007!*w\rC\003gE\002\007\001$\001\003ge>l\007\"\0025c\001\004A\022!B;oi&d\007\006\002\001k[>\004\"\001D6\n\00514!A\0033faJ,7-\031;fI\006\na.\001\016uQ&\034\be\0317bgN\004s/\0337mA\t,\007E]3n_Z,G-I\001q\003\031\021d&\r\031/a\001")
public interface RangeUtils<Repr extends RangeUtils<Repr>> {
  int start();
  
  int end();
  
  int step();
  
  boolean inclusive();
  
  Repr create(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
  
  int _last();
  
  <U> void _foreach(Function1<Object, U> paramFunction1);
  
  int _length();
  
  int _apply(int paramInt);
  
  Repr _take(int paramInt);
  
  Repr _drop(int paramInt);
  
  Repr _slice(int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\RangeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */