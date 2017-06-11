package hashsetsimple.test;
import hashsetsimple.structs.MySet;
public class UnionTest extends Test
{
    //<editor-fold desc="relleno de datos">
        private void cargando(MySet<String> set){            
            set.add("Deborah");
            set.add("Tommy");
            set.add("Franco");
            set.add("Manuela");
            set.add("Miguel");
            set.add("Denisse");
        }
    //</editor-fold>
    //<editor-fold desc="pruebas">
        private void probando_union_normal() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            MySet<String>s2 = new MySet();
            s2.add("Pepe");
            s2.add("Marcus");
            s2.add("Nancy");
            s1.addAll(s2);
            this.comprobar_que(s1.size() == 9);
        }
        private void probando_union_S1_vacio() throws Exception{
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s2.add("Pepe");
            s2.add("Marcus");
            s2.add("Nancy");
            s1.addAll(s2);
            this.comprobar_que(s1.size() == 3);
        }
        private void probando_union_S2_vacio() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            MySet<String>s2 = new MySet();
            s1.addAll(s2);
            this.comprobar_que(s1.size() == 6);
        }
        private void probando_union_S1yS2_vacio() throws Exception {
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s1.addAll(s2);
            this.comprobar_que(s1.isEmpty());
        }
        private void probando_union_a_S1_mismo() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            s1.addAll(s1);
            this.comprobar_que(s1.size() == 6);
        }
    //</editor-fold>
    @Override
    public void test() {
        try {
            this.probando_union_normal();
            this.probando_union_S1_vacio();
            this.probando_union_S2_vacio();
            this.probando_union_S1yS2_vacio();
            this.probando_union_a_S1_mismo();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}