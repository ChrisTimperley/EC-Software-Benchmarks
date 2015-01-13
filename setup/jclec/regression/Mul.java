import net.sf.jclec.exprtree.fun.AbstractPrimitive;
import net.sf.jclec.exprtree.fun.ExprTreeFunction;

public class Mul extends AbstractPrimitive
{
  public Mul()
  {
    super(new Class[] { Double.class, Double.class }, Double.class);
  }

  protected void evaluate(ExprTreeFunction context)
  {
    Double arg1 = (Double) pop(context);
    Double arg2 = (Double) pop(context);

    push(context, Double.valueOf(arg1.doubleValue() * arg2.doubleValue()));
  }

  public boolean equals(Object other)
  {
    return other instanceof Mul;
  }

  public String toString()
  {
    return "*";
  }
}
