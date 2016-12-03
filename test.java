public class test
{
    public static void perm1(String s)
    {
        perm1("", s);
    }

    private static void perm1(String prefix, String s)
    {
        int n = s.length();
        if (n == 0)
        {
            System.out.println(prefix);
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
            }
        }
    }

    public static void main(String [] args)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        perm1(alphabet);
    }
}
