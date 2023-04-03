package javax.media.jai.remote;

import java.io.Serializable;

public interface Negotiable extends Serializable {
  Negotiable negotiate(Negotiable paramNegotiable);
  
  Object getNegotiatedValue();
  
  Class getNegotiatedValueClass();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\Negotiable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */