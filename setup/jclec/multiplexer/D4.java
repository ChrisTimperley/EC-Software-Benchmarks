import net.sf.jclec.exprtree.fun.Argument;

public class D4 extends Argument<Boolean>
{
  public D4()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D4;
  }

  public String toString()
  {
    return "D4";
  }
}
