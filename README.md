# Ứng dụng quản lý sách

## Bài tập topic 1, ứng dụng Spring Boot và Apache Poi

### Docs:

#### 1. Spring Boot Validation

#### 2. Logging

#### 3. Spring Boot Caching

#### 4. Spring Security
* SecurityContextHolder lưu trữ thông tin chi tiết về người được xác thực
* AuthenticationManager là bộ các API xác định cách SpringSecurityFilter thực hiện authen.
* Authentication: Là đầu vào của AuthenticationManager để cung cấp thông tin xác thực mà người dùng đã đăng nhập hoặc người dùng trong phiên từ SecurityContext
* SecurityContext được lấy từ SecurityContextHolder, chứa Authenticaion của người dùng trong phiên.
* GrantedAuthority: Quyền của principal trong Authentication.
* ProviderManger: cách triển khai phổ biến nhất của AuthenticationManager.
* AuthenticationProvider: được ProviderManager sử dụng để thực hiện một loại authen cụ thể
* Request Credentials with AuthenticationEntryPoint - Yêu cầu thông tin xác thực từ client(chuyển hướng đến trang đăng nhập)
* AbstractAuthenticationProcessingFilter: Filter cơ sở dùng để xác thực, 
#### 5. Apache Poi