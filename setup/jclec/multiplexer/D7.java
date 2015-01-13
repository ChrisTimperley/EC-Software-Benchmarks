import net.sf.jclec.exprtree.fun.Argument;

public class D7 extends Argument<Boolean>
{
  public D7()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D7;
  }

  public String toString()
  {
    return "D7";
  }
}
