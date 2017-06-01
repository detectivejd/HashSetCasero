package hashsetsimple;
public class IntersectionTest extends Test
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
        private void probando_interseccion_normal() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            MySet<String>s2 = new MySet();
            s2.add("Deborah");
            s1.retainAll(s2);
            this.comprobar_que(s1.size() == 1);
        }
        private void probando_interseccion_S1_vacia() throws Exception {
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s2.add("Deborah");
            s2.add("Tommy");
            s2.add("Denisse");
            s1.retainAll(s2);
            this.comprobar_que(s1.isEmpty());
        }
        private void probando_interseccion_S2_vacia() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            MySet<String>s2 = new MySet();
            s1.retainAll(s2);
            this.comprobar_que(s1.size() == 6);
        }
        private void probando_interseccion_S1yS2_vacia() throws Exception {
            MySet<String>s1 = new MySet();
            MySet<String>s2 = new MySet();
            s1.retainAll(s2);
            this.comprobar_que(s1.isEmpty());
        }
        private void probando_interseccion_a_S1_mismo() throws Exception {
            MySet<String>s1 = new MySet();
            this.cargando(s1);
            s1.retainAll(s1);
            this.comprobar_que(s1.size() == 6);
        }
    //</editor-fold>    
    @Override
    void test() {
        try {
            this.probando_interseccion_normal();
            this.probando_interseccion_S1_vacia();
            this.probando_interseccion_S2_vacia();
            this.probando_interseccion_S1yS2_vacia();
            this.probando_interseccion_a_S1_mismo();
        } catch(Exception ex){
            ex.printStackTrace();
        }      
    }    
}