/*
 * (c) 2017 NewVi Cia. Ltda.
 *   * 
 */
package ec.com.newvi.faces.visorgeografico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author israelavila
 */
public class Collection implements List<Object> {

    protected List<Object> list;

    public Collection() {
        this.list = new ArrayList<>();
    }

    public Collection(List<Object> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(Object e) {
        return this.list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Object> c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, java.util.Collection<? extends Object> c) {
        return this.list.addAll(index, c);
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public Object get(int index) {
        return this.list.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        this.list.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        String string = "[";
        for (Object object : this.list) {
            string = string.concat(object.toString());
            if (this.list.lastIndexOf(object) + 1 != this.list.size()) {
                string = string.concat(",");
            }
        }
        string = string.concat("]");
        return string;
    }
}
