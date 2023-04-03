package org.opengis.geometry;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TransfiniteSet", specification = Specification.ISO_19107)
public interface TransfiniteSet {
  boolean contains(TransfiniteSet paramTransfiniteSet);
  
  boolean contains(DirectPosition paramDirectPosition);
  
  boolean intersects(TransfiniteSet paramTransfiniteSet);
  
  boolean equals(TransfiniteSet paramTransfiniteSet);
  
  TransfiniteSet union(TransfiniteSet paramTransfiniteSet);
  
  TransfiniteSet intersection(TransfiniteSet paramTransfiniteSet);
  
  TransfiniteSet difference(TransfiniteSet paramTransfiniteSet);
  
  TransfiniteSet symmetricDifference(TransfiniteSet paramTransfiniteSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\TransfiniteSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */