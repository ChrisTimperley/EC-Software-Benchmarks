import net.sf.jclec.exprtree.fun.AbstractPrimitive;
import net.sf.jclec.exprtree.fun.ExprTreeFunction;

public class If extends AbstractPrimitive
{
  public If()
  {
    super(new Class[] { Boolean.class, Boolean.class, Boolean.class }, Boolean.class);
  }

  protected void evaluate(ExprTreeFunction context)
  {
    Boolean arg1 = (Boolean) pop(context);
    Boolean arg2 = (Boolean) pop(context);
    Boolean arg3 = (Boolean) pop(context);

    if (arg1) {
      push(context, arg2);
    } else {
      push(context, arg3);
    }
  }

  public boolean equals(Object other)
  {
    return other instanceof If;
  }

  public String toString()
  {
    return "if";
  }
}
