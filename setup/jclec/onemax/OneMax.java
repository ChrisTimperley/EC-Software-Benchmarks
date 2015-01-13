import java.util.Comparator;
import net.sf.jclec.IFitness;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;
import net.sf.jclec.binarray.BinArrayIndividual;

public class OneMax extends AbstractEvaluator
{
  protected boolean maximize = true;
  private Comparator<IFitness> COMPARATOR;

  protected void evaluate(IIndividual ind)
  {
    byte[] genotype = (byte[])((BinArrayIndividual)ind).getGenotype();
    int fitness = 0;

    for (int i = 0; i < genotype.length; i++) {
      if (genotype[i] == 1)
        fitness += 1;
    }

    ind.setFitness(new SimpleValueFitness(fitness));
  }

  public Comparator<IFitness> getComparator()
  {
    if (this.COMPARATOR == null) {
      this.COMPARATOR = new ValueFitnessComparator(!this.maximize);
    }
    return this.COMPARATOR;
  }
}
