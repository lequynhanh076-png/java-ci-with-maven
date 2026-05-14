package com.auction;

// ===== IMPORT SLF4J (KHÔNG import Logback trực tiếp) =====
// Luôn dùng org.slf4j.Logger và LoggerFactory
// KHÔNG BAO GIỜ import ch.qos.logback.* trong code ứng dụng
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp chính minh họa Logging chuyên nghiệp với SLF4J + Logback.
 *
 * Nguyên tắc:
 * 1. Xóa bỏ tất cả System.out.println()
 * 2. Dùng Logger với mức độ phù hợp (INFO, ERROR, DEBUG...)
 * 3. Dùng Parameterized Logging: logger.info("Hello {}", name) thay vì "Hello " + name
 */
public class App {

    /*
     * ===== KHAI BÁO LOGGER =====
     * - private static final: Một logger cho cả class, dùng chung, không thay đổi
     * - getLogger(App.class): Tên logger = tên đầy đủ của class → dễ trace trong log
     *   VD: com.example.App
     *
     * SAI: Logger log = LoggerFactory.getLogger("myLogger");  // tên tùy tiện
     * ĐÚNG: Logger log = LoggerFactory.getLogger(App.class);  // dùng class
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("===== Ứng dụng bắt đầu khởi động =====");

        App app = new App();
        app.runDemo();

        logger.info("===== Ứng dụng kết thúc bình thường =====");
    }

    public void runDemo() {
        // INFO: Các mốc quan trọng của luồng xử lý
        logger.info("Bắt đầu chạy demo logging");

        // DEBUG: Thông tin chi tiết hữu ích khi debug
        logger.debug("Đang khởi tạo UserService...");

        UserService userService = new UserService();

        // Thao tác bình thường
        userService.createUser("Nguyen Van A", "nguyenvana@example.com");
        userService.findUser(1);
        userService.findUser(999); // ID không tồn tại → sẽ log WARN

        // Thao tác gây ra ngoại lệ → sẽ log ERROR
        userService.deleteUser(-1);

        logger.info("Kết thúc demo logging");
    }
}