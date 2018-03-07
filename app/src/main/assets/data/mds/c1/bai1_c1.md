BÀI 1: TỔNG QUAN VỀ ANDROID  
Để học bất cứ cái gì, bắt buộc bạn phải tìm hiểu sơ qua một chút về thứ
mình muốn học. Không có ai bắt đầu đi cày mà lại không có trâu cả, vì
thế trước tiên bạn cần nắm rõ 1 số điều cơ bản dưới đây:

![](file:///android_asset/images/image1.png)

**1,Android là gì ?**

Android là một Hệ điều hành mã nguồn mở và là một hệ điều hành dựa trên
Linux cho các thiết bị mobile như Smartphone và máy tính bảng. Ban đầu
Android được phát triển bởi Công ty Android với sự hỗ trợ tài chính từ
Google, sau đó được Google mua lại vào năm 2005.

\*Lịch sử của Android:

-Ở đây mình chỉ liệt kê ra các version của android. Các bạn có thể lên
google để tìm hiểu rõ hơn về lịch sửa ra đời và phát triển của Android

![](file:///android_asset/images/image2.png)

\*Sơ đồ thị phần version android

![](file:///android_asset/images/image3.png)

\*Kiến trúc của hệ điều hành Android

+Android được hình thành dựa trên nền tảng Linux nhân 2.6, từ phiên bản
4.0 sử dụng Linux nhân 3.x.

+Android bao gồm 3 thành phần chính:

-   **Middleware**

-   **Các thư viện và API viết bằng C**

-   **Các ứng dụng thực thi viết bằng Java**

+Sử dụng máy ảo Dalvik để biên dịch mã .dex(Dalvik Excuteable) sang Java
bytecode.

![](file:///android_asset/images/image4.jpeg)

-   Linux kernel – lõi chính của toàn hệ thống bao gồm các điều khiển
    phần cứng, bộ quản lý xử lý và bộ nhớ, bảo mật, kết nối mạng, bộ
    quản lý năng lượng.

-   Libraries-thực thi trên tầng nhân Linux, bao gồm các thư viện lõi
    khác nhau của C/C++ như libc và SSL. Có các dạng sau:

<!-- -->

-   Thư viện hổ trợ phát các tập tin đa truyền thông.

-   Bộ quản lý hiển thị

-   Thư viện hổ trợ đồ họa OpenGL 2D và 3D

-   SQLite hổ trợ lưu trữ cơ sở dữ liệu

-   SSL và WebKit cho phép tương tác với trình duyệt và bảo
    mật Internet.

<!-- -->

-   Android Run Time – đây chính là điểm làm nên sự khác biệt giữa thiết
    bị Android và thiết bị Linux. Bên trong thành phần này bao gồm máy
    ảo Dalvik và thư viện lõi. Android Run Time ngoài tăng tốc độ cho
    ứng dụng còn làm nền cho tầng Application Framework kết nối đến.

-   Core Libraries – mặc dù hầu hết các ứng dụng Android viết bằng ngôn
    ngữ Java nhưng Dalvik không phải là máy ảo Java. Các thư viện lõi
    Android sẽ cung cấp hầu hết các chức năng chính có thể có trong thư
    viện Java cũng như thư viện riêng biệt của Android.

-   Dalvik VM – dạng máy ảo cho phép tối ưu hóa để có thể chạy được
    nhiều tiến trình một các hiệu quả, dựa trên nhân Linux các máy ảo
    cho phép quản lý các tiểu trình và quản lý bộ nhớ ở bậc thấp.

-   Application Framework – cung cấp các lớp cho việc tạo ra các
    ứng dụng. Bên cạnh đó nó cũng chứa các lớp trừu tượng cho phép truy
    nhập phần cứng, quản lý giao diện người dùng và tài nguyên của
    ứng dụng.

-   Application Layer – gồm các ứng dụng được tích hợp sẵn và các ứng
    dụng của hãng thứ ba. Tầng ứng dụng trong Android Run Time sử dụng
    các lớp từ tầng Application Framework để thực thi ứng dụng.

**2,Tại sao phải học android :**

-Ngày này thiết bị sử dụng hệ điều hành Android ngày càng phát triển
mạnh mẽ.  
-Các bạn có thể xem biểu đồ về các smartphone sử dụng hệ điều hành
Android

![](file:///android_asset/images/image5.jpeg)

-Cơn khát nhân lực phát triển ứng dụng Android ngày này. Dễ dàng tìm
kiếm cơ hội việc làm với mức lương hấp dẫn !

![](file:///android_asset/images/image6.png)

**3,Học Android như thế nào???**

\*Những kiến thức cơ bản để lập trình android:

+ Căn bản về kỹ thuật lập trình

+ Java căn bản hoặc Kotlin(Vào ngày 17/05/2017, Google đã chính thức
công bố Kotlin trở thành ngôn ngữ chính thức để lập trình Android vì sự
tuyệt vời của nó, từ Android Studio 3.0 thì Kotlin sẽ được build sẵn)

+ Chăm chỉ tìm tòi kiến thức!

“Life is 10% what happens to you and 90% of how you react to it”
