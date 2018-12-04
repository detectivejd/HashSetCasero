package hashsetsimple.structs;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MySet<E> implements Set<E>
{
    protected MyMap<E,Object>map;
    private final Object PRESENT = new Object();
    /**
     * Construye un nuevo HashSet con una cantidad a almacenar por 
     * defecto
     */
    public MySet() {
        map = new MyMap();
    }
    /**
     * Construye un nuevo HashSet según la cantidad de elementos que
     * deseamos almacenar
     * 
     * @param xcap -> capacidad de elementos a almacenar 
     */
    public MySet(int xcap) {
        map = new MyMap(xcap);
    }
    /**
     * Construye un nuevo HashSet que utilizamos para almacenar toda una 
     * estructura de datos tipo collection a nuestra estructura de datos
     * 
     * @param c 
     */
    public MySet(Collection<? extends E> c) {
        this(c.size());
        this.addAll(c);
    }
    /*----------------------------------------------------------*/
    /**
     * Obtiene la cantidad de elementos de nuestra estructura de datos
     * 
     * @return int -> entero 
     */
    @Override
    public int size() {
        return map.size();
    }
    /**
     * Verifica si nuestra estructura de datos esta vacía o no
     * 
     * @return boolean -> verdadero o falso 
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    /**
     * Limpia todas las entradas almacenadas de nuestro HashSet
     */
    @Override
    public void clear() {
        map.clear();
    }
    /**
     * Retorna el iterador de los elementos almacenados en nuestra 
     * estructura de datos
     * 
     * @return Iterator<E> -> iterador de elementos
     */
    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
    /*----------------------------------------------------------*/
    /**
     * Retorna los elementos de nuestra estructura de datos en forma
     * de array.
     * 
     * @return Object[] -> array de objetos
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[map.size()];
        int i=0;
        for(E obj : map.keySet()){
            array[i] = obj;
            i++;
        }
        return array;
    }
    /**
     * Retorna un array genérico de nuestra estructura de datos que es el
     * array pasado por parámetro.
     * 
     * @param <T>
     * @param a
     * @return <T> T[] -> array genérico
     */
    @Override
    public <T> T[] toArray(T[] a) {
        int i=0;
        for(E obj : map.keySet()){
            a[i] = (T)obj;
            i++;
        }
        return a;
    }
    /*----------------------------------------------------------*/
    /**
     * Verifica si existe el elemento pasado por parámetro en nuestra
     * estructura de datos
     * 
     * @param o
     * @return boolean -> verdadero o falso
     */
    @Override
    public boolean contains(Object o) {
        return map.containsKey((E)o);
    }
    /**
     * Verifica si todos los elementos de la colección pasada por parámetro
     * existen en nuestra estructura de datos
     * 
     * @param c
     * @return boolean -> verdadero o falso
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if(c.size() > 0){
            return c.stream().noneMatch((e) -> (!this.contains((E)e)));
        }
        return false;
    }
    /*----------------------------------------------------------*/    
    /**
     * Agrega un elemento a nuestra estructura de datos, siempre y
     * cuando no esté repetido el mismo
     * 
     * @param e
     * @return boolean -> verdadero o falso 
     */
    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
    /**
     * Agrega todas los elementos de la colección pasada por parámetro
     * a nuestra estructura de datos
     * 
     * @param c
     * @return boolean -> verdadero o falso 
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.size() > 0){
            c.forEach((obj) -> {
                this.add(obj);
            });
            return true;
        }
        return false;
    }
    /*----------------------------------------------------------*/
    /**
     * Elimina un elemento de nuestra estructura de datos.
     * 
     * @param o
     * @return boolean -> verdadero o falso.
     */
    @Override
    public boolean remove(Object o) {
        return map.remove((E)o) == PRESENT;
    }
    /**
     * Elimina de nuestra estructura de datos, todos los elementos de la 
     * colección pasada por parámetro
     * 
     * @param c
     * @return boolean -> verdadero o falso 
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if(c.size() > 0){
            c.stream().filter((obj) -> 
                (this.contains((E)obj))).forEachOrdered((obj) -> {
                    map.remove((E)obj);
            });
            return true;
        }
        return false;
    }
    /*----------------------------------------------------------*/
    /**
     * Elimina de la estructura de datos, todos los elementos que no
     * estén en la colección pasada por parámetro
     * 
     * @param c
     * @return boolean -> verdadero o falso 
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if(c.size() > 0){
            map.keySet().stream().filter((e) -> 
                (!c.contains(e))).forEachOrdered((e) -> {
                    this.remove(e);
            });
            return true;
        }
        return false;
    }
}