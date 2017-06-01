package hashsetsimple;
public class Cuerpo 
{
    public static void main(String[] args) {
        Test t1 = null;
        t1 = new ConstructorTest();
        t1.test();
        t1 = new UpTest();
        t1.test();
        t1 = new ContainsTest();
        t1.test();
        t1 = new DownTest();
        t1.test();
        t1 = new ToArrayTest();
        t1.test();
        t1 = new UnionTest();
        t1.test();
        t1 = new DifferenceTest();
        t1.test();
        t1 = new IntersectionTest();
        t1.test();
        t1 = new SubSetTest();
        t1.test();
    }    
}