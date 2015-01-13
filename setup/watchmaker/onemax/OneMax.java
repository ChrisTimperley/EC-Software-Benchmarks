package onemax;

import java.util.ArrayList;
import java.util.List;
import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.examples.EvolutionLogger;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.BitStringFactory;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

public class OneMax
{

  private static final int GENS = 1000;
  private static final int POP_SIZE = 100;
  private static final int BITS = 100;
  private static final int ELITISM = 0;

  public static void main(String[] args)
  {
      evolveBits(BITS);
  }

  public static BitString evolveBits(int length)
  {
      List<EvolutionaryOperator<BitString>> operators = new ArrayList<EvolutionaryOperator<BitString>>(2);
      operators.add(new BitStringCrossover(1, new Probability(1.0d)));
      operators.add(new BitStringMutation(new Probability(0.01d)));
      EvolutionaryOperator<BitString> pipeline = new EvolutionPipeline<BitString>(operators);
      GenerationalEvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<BitString>(
        new BitStringFactory(length),
        pipeline,
        new OneMaxEvaluator(),
        new TournamentSelection(new Probability(1.00d)),
        new MersenneTwisterRNG());
      engine.setSingleThreaded(true);
      engine.addEvolutionObserver(new EvolutionLogger<BitString>());
      return engine.evolve(POP_SIZE,
                           ELITISM,
                           new GenerationCount(GENS));        
  }

}
