package concurrent;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class CompareAndSetObjTest {

	public Integer name;
	
	public String address;
	
	private static final Unsafe unsafe;
	
	static {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe) theUnsafe.get(null);
			nameOffset = unsafe.objectFieldOffset(CompareAndSetObjTest.class.getDeclaredField("name"));
			addressOffset = unsafe.objectFieldOffset(CompareAndSetObjTest.class.getDeclaredField("address"));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	private static long nameOffset;
	
	private static long addressOffset;
	
	public static void main(String[] args) {
		CompareAndSetObjTest testObj = new CompareAndSetObjTest();
		testObj.name = 2;
		testObj.address = "gz";
		System.out.println("before " + testObj);
		unsafe.compareAndSwapObject(testObj, nameOffset, testObj.name, 111);
		System.out.println("after " + testObj);
	}

	@Override
	public String toString() {
		return "CompareAndSetObjTest [name=" + name + ", address=" + address + "]";
	}
}
