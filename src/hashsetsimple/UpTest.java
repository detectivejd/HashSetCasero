package hashsetsimple;
public class UpTest extends Test
{
    MySet<String> set;
    public UpTest() {
        set = new MySet();
    }    
    //<editor-fold desc="relleno de datos">
        private Object[] restart(){
            return new Object[]{ "Deborah","Tommy","Franco","Manuela","Miguel","Denisse" };
        }
        private Object[] nullset(){
            return new Object[]{null,null,null};
        }
        private Object[] reverse(){
            return new Object[]{ "Denisse","Franco","Manuela","Deborah","Miguel","Tommy" };
        }        
    //</editor-fold>
    //<editor-fold desc="pruebas">
        private void probando_insercion_repetida() throws Exception{            
            probando_insercion(this.restart());
            set.add("Deborah");
            set.add("Tommy");
            this.comprobar_que(set.size() == 6);
        }
        private void probando_insercion(Object[] arreglo) throws Exception{
            set.clear();
            for (Object obj : arreglo) {
                if(obj == null){
                    set.add(null);
                } else {
                    set.add(obj.toString());
                }            
            }
            for(String s : set){
                this.comprobar_que(set.contains(s));
            }
        }
    //</editor-fold>
    @Override
    void test() {
        try {
            probando_insercion(this.restart());
            probando_insercion(this.nullset());
            probando_insercion(this.reverse());
            probando_insercion_repetida();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}