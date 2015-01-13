import net.sf.jclec.exprtree.fun.Argument;

public class A2 extends Argument<Boolean>
{
  public A2()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof A2;
  }

  public String toString()
  {
    return "A2";
  }
}
