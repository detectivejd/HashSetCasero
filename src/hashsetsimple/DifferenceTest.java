package hashsetsimple;
public class DifferenceTest extends Test
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
        private void probando_diferencia_normal() throws Exception{
            MySet<String>s1 = new MySet();
            this.cargando(s1); 
            MySet<String>s2 = new MySet();
            s2.add("Deborah");
            s2.add("Tommy");
            s2.add("Denisse");
            s1.removeAll(s2);
            this.comprobar_que(s1.size() == 3);
        }
        private void probando_diferencia_S1_vacia() throws Exception{
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s2.add("Deborah");
            s2.add("Tommy");
            s2.add("Denisse");
            s1.removeAll(s2);
            this.comprobar_que(s1.isEmpty());
        }
        private void probando_diferencia_S2_vacia() throws Exception{
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            MySet<String>s2 = new MySet();
            s1.removeAll(s2);
            this.comprobar_que(s1.size() == 6);
        }
        private void probando_diferencia_S1yS2_vacia() throws Exception{
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s1.removeAll(s2);
            this.comprobar_que(s1.isEmpty());
        }
        private void probando_diferencia_a_S1_mismo() throws Exception{
            MySet<String>s1 = new MySet();
            cargando(s1);
            s1.removeAll(s1);
            this.comprobar_que(s1.isEmpty());
        }
    //</editor-fold>
    @Override
    void test() {
        try {
            this.probando_diferencia_normal();
            this.probando_diferencia_S1_vacia();
            this.probando_diferencia_S2_vacia();
            this.probando_diferencia_S1yS2_vacia();
            this.probando_diferencia_a_S1_mismo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}