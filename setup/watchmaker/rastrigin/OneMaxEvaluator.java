package onemax;

import java.util.List;
import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class OneMaxEvaluator implements FitnessEvaluator<BitString>
{

  public double getFitness(BitString candidate,
                           List<? extends BitString> population)
  {
    return candidate.countSetBits();
  }

  /**
   * Always returns true.  A higher score indicates a fitter individual.
   * @return True.
   */

  public boolean isNatural()
  {
    return true;
  }

}
