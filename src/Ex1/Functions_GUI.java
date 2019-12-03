package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Functions_GUI implements functions{
	List<functions> list = new ArrayList<functions>();

	@Override
	public boolean add(functions e) {
		// TODO Auto-generated method stub
		return list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends functions> c) {
		// TODO Auto-generated method stub
		return list.addAll(c);
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<functions> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		String s = FileUtils.readFile("test.txt");
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		FileUtils.writeFile("functions.txt", file);
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
