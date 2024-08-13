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