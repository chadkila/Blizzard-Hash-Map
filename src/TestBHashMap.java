public class TestBHashMap {

	public static void main(String[] args) {
		testSingle();
	}

	public static void testSingle() {
		BHashMap<String, String> hm = new BHashMap<String, String>();
		System.out.println(hm.put("123456789", "v123456789"));
		System.out.println(hm.get("123456789"));
		System.out.println(hm.put("123456789", "s123456789"));
		System.out.println(hm.get("123456789"));
		hm.clear();
		System.out.println(hm.put("123456789", "v123456789"));
		System.out.println(hm.get("123456789"));
		System.out.println(hm.put("123456789", "s123456789"));
		System.out.println(hm.get("123456789"));
		System.out.println(hm.remove("123456789"));
		System.out.println(hm.get("123456789"));
	}

}
