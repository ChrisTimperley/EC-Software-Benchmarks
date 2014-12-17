import ec.*;
import ec.simple.*;
import ec.util.*;
import ec.vector.*;

public class OneMax extends Problem implements SimpleProblemForm
{

    public void evaluate(final EvolutionState state,
                         final Individual ind,
                         final int subpopulation,
                         final int threadnum)
    {
      if (ind.evaluated) return;

      if (!(ind instanceof BitVectorIndividual))
        state.output.fatal("Invalid individual.", null);

      BitVectorIndividual ind2 = (BitVectorIndividual) ind;

      int sum = 0;        
      for (int x = 0; x < ind2.genome.length; x++)
        sum += (ind2.genome[x] ? 1 : 0);

      if (!(ind2.fitness instanceof SimpleFitness))
        state.output.fatal("Whoa!  It's not a SimpleFitness!!!",null);

      ((SimpleFitness)ind2.fitness).setFitness(state,
        ((double)sum)/ind2.genome.length,
        sum == ind2.genome.length);

      ind2.evaluated = true;
    }

}
