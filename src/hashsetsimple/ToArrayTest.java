package hashsetsimple;
public class ToArrayTest extends Test
{
    MySet<String> set;
    public ToArrayTest() {
        set = new MySet();
        this.cargando();
    } 
    //<editor-fold desc="relleno de datos">
        private void cargando(){
            set.add("Deborah");
            set.add("Tommy");
            set.add("Franco");
            set.add("Manuela");
            set.add("Miguel");
            set.add("Denisse");
        }
    //</editor-fold>
    //<editor-fold desc="pruebas">
        private void probando_toarray1() throws Exception {
            Object [] arr = new Object[set.size()];
            arr = set.toArray();
            this.comprobar_que(arr.length == 6);
        }
        private void probando_toarray2() throws Exception {
            String [] arr = new String[set.size()];
            arr = set.toArray(arr);
            this.comprobar_que(arr.length == 6);
        }
    //</editor-fold>    
    @Override
    void test() {
        try {
            this.probando_toarray1();
            this.probando_toarray2();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}