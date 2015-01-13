import net.sf.jclec.exprtree.fun.Argument;

public class D2 extends Argument<Boolean>
{
  public D2()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D2;
  }

  public String toString()
  {
    return "D2";
  }
}
