import net.sf.jclec.exprtree.fun.AbstractPrimitive;
import net.sf.jclec.exprtree.fun.ExprTreeFunction;

public class Not extends AbstractPrimitive
{
  public Not()
  {
    super(new Class[] { Boolean.class }, Boolean.class);
  }

  protected void evaluate(ExprTreeFunction context)
  {
    Boolean v = (Boolean) pop(context);
    v = !v;
    push(context, !v);
  }

  public boolean equals(Object other)
  {
    return other instanceof Not;
  }

  public String toString()
  {
    return "!";
  }
}
