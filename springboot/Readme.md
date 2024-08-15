### Tight-coupling
- Trong lập trình java, khái niệm tight-coupling(liên kết ràng buộc) ám chỉ mối quan quan hệ các lớp(classes) quá chặt chẽ
- Khi sử dụng sử dụng tight-coupling, các lớp liên kế nốt với nhau 1 cách chặt chẽ, và sự thay đổi của 1 lớp có thể ảnh hưởng đến toàn bộ hệ thống hoặc các lớp khác
- Điều này làm cho mã nguồn trở nên khó bảo trì và mở rộng hơn

### Loose coupling 
- loose coupling(liên kết lỏng) là cách để giảm bớt sự phụ thuộc giữa các lớp với nhau
- Trong loose coupling các lớp hoạt động độc lập và không biết gì về cấu trúc hoắc chi tiết triển khai của lớp khác

### Dependency Injection
- Dependency Injection là một mô hình lập trình và thiết kế phần mềm, không chỉ áp dụng cho java mà còn cho nhiều ngôn ngữ khác
- Đây là 1 phương pháp giúp giảm sự phụ thuộc giữa các thành phần(hoặc lớp) trong ứng dụng
- có 3 cách chính: Constructor Injection, Setter Injection, Interface Injection

### Annotation 
- annotation(chú thích) là một tính năng quan trọng trong lập trình java, cho phép thêm các thông tin bổ sung vào mã nguồn của bạn giúp trình biên dịch và các công cụ phát triển hiểu và xử lý mã nguồn của bạn 1 cách thôn minh
- annotation để đánh dấu và cung cấp metadata cho các lớp, phương thức, biến, hoặc gói
- Cú pháp: @ + tên của annotation (@Override...)
- @Component annotation: 
    + đánh dấu trên các class để cho chúng biết là các bean đc quản lý bới Spring Boot(Spring container - IoC)
    + Spring boot tạo và quản lý các instance của các class được đánh dấu @Component
- @Autowired annotation
    + sử dụng để tiêm(inject) các dependecy và các thành phần khác
    + khi đánh dấu 1 thuộc tính bằng @Autowrired Spring boot sẽ tự động tiêm 1 instance của dependecy tương ứng vào thuộc tính đó
### Inversion of Control (IoC)
- là một nguyên tắc lập trình, trong luồng điều khiển trong ứng dụng không được quyết định bởi ứng dụng mà đc quyết định bởi một framework hoặc container bên ngoài
- IoC thường đi kèm với DI, nơi các dependecy được quản lý và cung cấp bới 1 franework or container
- Framework sẽ quản lý việc tạo và quản lý các đối tượng phụ thuộc

### Bean là gì
- Là thành phần sương sống của ứng dụng 
- Là đối tượng được khởi tạo, lắp ráp, quản lý bới Spring IoC

### Tạo ra Bean ntn?
- Sử dụng annotation đánh dấu tên class (class level): @Component, @Controller, @RestController 
- Sử dụng Bean trên method (method level) trong class @Configuration

### Sử dụng Bean btn?
- Bean thường sử dụng trong 1 bean khác (dependency) A -> B
- 3 cách sử dụng bean
    + Field-based Injection
    + Constructor-based Injection
    + Setter-based Injection

[***Vì sao có @Component rồi lại cần thêm @Bean(Tại sao lại sinh ra các số 2 - sử dụng @Bean)***](https://www.youtube.com/watch?v=cCE5Uw_DtqQ&t=573s)
- khi sử dụng thư viện bên ngoài thì không thể bào đánh @Component và bên trong được thư viện đó được
- sẽ khởi tạo 1 phương thức và trả về đối tượng của thư viện sử dụng và đánh dấu @Bean cho phương thức đó

### Bean Scope
- singleton: container chỉ khởi tạo 1 instance(1 object) của bean và trả về chính nó như yêu cầu(khi khởi tạo 1 instance của bean thì các instance sẽ trỏ đến 1 vùng nhớ có cùng địa chỉ)
- prototype: mỗi khi yêu cầu thì container sẽ tạo ra 1 instance mới và trả về(các instance sẽ trỏ đến các vùng nhớ khác nhau và có địa chỉ khác nhau)

### Lazy-initialized Beans
- Eager(háo hức): Bean được tạo ra ngay khi run app(vd: singleton scope)
- Lazy(lười): Bean được tạo khi chúng ra gọi đến nó(vd: prototype scope hoặc sử dụng @Lazy)

### Component Scan là gì? 
- Component Scan cho phép Spring Boot tự động tìm kiếm và quản lý các bean trong ứng dụng của bạn
- mặc định Spring Boot sẽ quét toàn bộ các package và các package con của package chưa class chứa hàm main

### [Lifecycle của Bean (Vòng đời của Bean)](https://www.youtube.com/watch?v=zHRcfy21R0Q)
- @PostConstruct: là 1 annotation đánh dấu trên 1 method bên trong một Bean, IoC hoặc ApplicationContext sẽ gọi method này sau khi Bean đó được tạo và quản lý
- @PreDestroy: là 1 annotation đánh dấu trên 1 method bên trong một Bean, IoC hoặc ApplicationContext sẽ gọi method này sau khi Bean đó bị xoá hoặc không được quản lý nữa

![Lifecycle-Bean](https://viblo.asia/p/spring-boot-3-spring-bean-life-cycle-atpostconstruct-va-atpredestroy-Qbq5Q4nmlD8)

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@PostConstruct
public void init(){
  System.out.println("Post girl");
}

public Girl() {
  System.out.println("Girl được khởi tạo");
}

public useBean() {
  System.out.println("use Girl");
}

@PreDestroy
public void destroy() {
  System.out.println("Destroy girl");
}
```