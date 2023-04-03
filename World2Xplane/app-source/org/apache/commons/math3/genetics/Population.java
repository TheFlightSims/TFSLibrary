package org.apache.commons.math3.genetics;

public interface Population extends Iterable<Chromosome> {
  int getPopulationSize();
  
  int getPopulationLimit();
  
  Population nextGeneration();
  
  void addChromosome(Chromosome paramChromosome);
  
  Chromosome getFittestChromosome();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\Population.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */