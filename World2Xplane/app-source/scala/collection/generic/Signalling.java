package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001=2q!\001\002\021\002G\005\021B\001\006TS\036t\027\r\0347j]\036T!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007\"B\b\001\r\003\001\022!C5t\003\n|'\017^3e+\005\t\002CA\006\023\023\t\031bAA\004C_>dW-\0318\t\013U\001a\021\001\f\002\013\005\024wN\035;\025\003]\001\"a\003\r\n\005e1!\001B+oSRDQa\007\001\007\002q\t\021\"\0338eKb4E.Y4\026\003u\001\"a\003\020\n\005}1!aA%oi\")\021\005\001D\001E\005a1/\032;J]\022,\007P\0227bOR\021qc\t\005\006I\001\002\r!H\001\002M\")a\005\001D\001O\005)2/\032;J]\022,\007P\0227bO&3wI]3bi\026\024HCA\f)\021\025!S\0051\001\036\021\025Q\003A\"\001,\003Q\031X\r^%oI\026Dh\t\\1h\023\032dUm]:feR\021q\003\f\005\006I%\002\r!\b\005\006]\0011\t\001H\001\004i\006<\007")
public interface Signalling {
  boolean isAborted();
  
  void abort();
  
  int indexFlag();
  
  void setIndexFlag(int paramInt);
  
  void setIndexFlagIfGreater(int paramInt);
  
  void setIndexFlagIfLesser(int paramInt);
  
  int tag();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Signalling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */