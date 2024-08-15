package com.example.demoStreamAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// functio interface: là 1 giao diện chưa phương thúc abstract
// lambda expression: được sử dụng để triển khai function interface
@SpringBootApplication
public class DemoStreamApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStreamApiApplication.class, args);

		// C1: sử dụng class impl
		Greeting vietNam = new VietNam();
		vietNam.sayHello("Hiếu");

		// C2: sử dụng Anonymous class
		Greeting english = new Greeting() {
			@Override
			public void sayHello(String name) {
				System.out.println("Hello: " + name);
			}
		};
		english.sayHello("Hiếu");

		// C3: sử dụng Lambda Expression
		Greeting japanese = (name) -> System.out.println("こんにちは、" + name);
		japanese.sayHello("Hiếu");

		Calculator sum = (a, b) -> {
			return a+b;
		};
		System.out.println("Sum = " + sum.calculator(4, 5));

		//
		List<Integer> numbers = new ArrayList<>(List.of(3, 5, 10, 40, 20, 2));

		numbers.forEach(number -> System.out.println(number));

		numbers.removeIf(number -> number%2 == 1);
		System.out.println(numbers);

		numbers.sort((a, b) -> b - a);
		System.out.println(numbers);

		// stream api

		List<Integer> numberList = new ArrayList<>(List.of(3, 5, 10, 40, 20, 2, 2, 10, 3));
		int total = numberList.stream()
				.map(number -> number*2)
				.reduce(0, (a, b) -> a + b);

		System.out.println("Tổng = " + total);

		int max = numbers.stream()
				.filter(number -> number % 2 == 0)
				.max((a, b) -> a - b)
				.orElse(0);

		System.out.println("Max: " + max);

		int max1 = numbers.stream()
				.filter(number -> number % 2 == 0)
				.mapToInt(number -> number)
				.max()
				.orElse(0);

		System.out.println("Max1: " + max1);

//		7. Lấy danh sách các phần tử không trùng nhau
		System.out.println("Lấy danh sách các phần tử không trùng nhau");
		numberList.stream()
				.distinct()
				.forEach(number -> System.out.println(number));

//		8. Lấy 5 phần tử đầu tiên trong mảng
		System.out.println("Lấy 5 phần tử đầu tiên trong mảng");
		numberList.stream()
				.limit(5)
				.forEach(number -> System.out.println(number));

//		9. Lấy phần tử từ thứ 3 -> thứ 5
		System.out.println("Lấy phần tử từ thứ 3 -> thứ 5");
		numberList.stream()
				.skip(2)
				.limit(3)
				.forEach(number -> System.out.println(number));

//		10. Lấy phần tử đầu tiên > 5
		System.out.println("Lấy phần tử đầu tiên > 5");
		int a = numberList.stream()
				.filter(number -> number > 5)
				.findFirst()
				.orElse(0);
		System.out.println("phần tử đầu tiên lớn hơn 5: " + a);

//		11. Kiểm tra xem list có phải là list chẵn hay không
		System.out.println("Kiểm tra xem list có phải là list chẵn hay không");
		boolean isEven = numberList.stream()
				.allMatch(number -> number % 2 == 0);

		System.out.println(isEven);

//		12. Kiểm tra xem list có phần tử > 10 hay không
		System.out.println("Kiểm tra xem list có phần tử > 10 hay không");
		boolean isGreaterThan10 = numberList.stream()
				.anyMatch(number -> number > 10);

		System.out.println(isGreaterThan10);

//		13. Có bao nhiêu phần tử > 5
		System.out.println("Có bao nhiêu phần tử > 5");
		long countGreaterThan5 = numberList.stream()
				.filter(number -> number > 5)
				.count();

		System.out.println(countGreaterThan5);

//		14. Nhân đôi các phần tủ trong list và trả về list mới
		System.out.println("Nhân đôi các phần tủ trong list và trả về list mới");
		List<Integer> newList = numberList.stream()
				.map(number -> number * 2)
				.collect(Collectors.toList());

		System.out.println(newList);

//		15. Kiểm tra xem list không chứa giá trị nào = 8 hay không
		System.out.println("Kiểm tra xem list không chứa giá trị nào = 8 hay không");
		boolean isContain = numberList.stream()
				.anyMatch(number -> number == 8);

		System.out.println(isContain);

	}
}
