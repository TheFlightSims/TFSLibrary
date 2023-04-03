package scala.collection.mutable;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0352q!\001\002\021\002G\005\021B\001\006Tk\n\0348M]5cKJT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\031!\"J\r\024\005\001Y\001C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fM\")\001\003\001D\001#\0051an\034;jMf$2AE\013#!\ta1#\003\002\025\r\t!QK\\5u\021\0251r\0021\001\030\003\r\001XO\031\t\0031ea\001\001\002\004\033\001!\025\ra\007\002\004!V\024\027C\001\017 !\taQ$\003\002\037\r\t9aj\034;iS:<\007C\001\007!\023\t\tcAA\002B]fDQaI\bA\002\021\nQ!\032<f]R\004\"\001G\023\005\r\031\002\001R1\001\034\005\r)e\017\036")
public interface Subscriber<Evt, Pub> {
  void notify(Pub paramPub, Evt paramEvt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Subscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */