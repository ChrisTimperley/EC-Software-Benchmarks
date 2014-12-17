import ec.*;
import ec.simple.*;
import ec.util.*;
import ec.vector.*;

public class Rastrigin extends Problem implements SimpleProblemForm
{

    public void evaluate(final EvolutionState state,
                         final Individual ind,
                         final int subpopulation,
                         final int threadnum)
    {
      if (ind.evaluated) return;

      DoubleVectorIndividual ind2 = (DoubleVectorIndividual) ind;

      double f = 10 * ind2.genome.length;
      for (Double x : ind2.genome) {
        f += x*x - 10 * Math.cos(2 * Math.PI * x);
      }

      ((SimpleFitness)ind2.fitness).setFitness(state, f, f == 0.0);
      ind2.evaluated = true;
    }

}
