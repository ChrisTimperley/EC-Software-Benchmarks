import net.sf.jclec.exprtree.fun.AbstractPrimitive;
import net.sf.jclec.exprtree.fun.ExprTreeFunction;

public class And extends AbstractPrimitive
{
  public And()
  {
    super(new Class[] { Boolean.class, Boolean.class }, Boolean.class);
  }

  protected void evaluate(ExprTreeFunction context)
  {
    Boolean arg1 = (Boolean) pop(context);
    Boolean arg2 = (Boolean) pop(context);

    push(context, arg1 && arg2);
  }

  public boolean equals(Object other)
  {
    return other instanceof And;
  }

  public String toString()
  {
    return "*";
  }
}
