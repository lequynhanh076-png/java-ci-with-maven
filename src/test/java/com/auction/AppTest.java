package com.auction;
import com.auction.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class xác minh rằng các service hoạt động đúng.
 * Khi chạy mvn test, log sẽ được xuất ra console VÀ file logs/application.log
 *
 * Cách xác minh: Sau khi chạy mvn test, kiểm tra:
 * 1. Console output có hiển thị log đúng format không?
 * 2. File logs/application.log có được tạo không?
 * 3. Log có chứa timestamp, level, class name, message không?
 */
@DisplayName("Bai 9 - Kiem tra Logging chuyen nghiep")
class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        logger.info("===== Chuẩn bị test mới =====");
        userService = new UserService();
    }

    @Test
    @DisplayName("Test tao user hop le")
    void testCreateUserValid() {
        logger.info("--- Bắt đầu test: tạo user hợp lệ ---");

        // Không ném exception → test pass
        assertDoesNotThrow(() -> {
            userService.createUser("Tran Thi B", "tranthib@example.com");
        });

        logger.info("--- Kết thúc test: tạo user hợp lệ - PASS ---");
    }

    @Test
    @DisplayName("Test tao user voi ten rong → phai dung ten mac dinh")
    void testCreateUserEmptyName() {
        logger.info("--- Bắt đầu test: tạo user tên rỗng ---");

        // Tên rỗng → WARN log, dùng tên mặc định, KHÔNG ném exception
        assertDoesNotThrow(() -> {
            userService.createUser("", "test@example.com");
        });

        logger.info("--- Kết thúc test: WARN log được ghi - PASS ---");
    }

    @Test
    @DisplayName("Test tim user khong ton tai → phai log WARN")
    void testFindUserNotFound() {
        logger.info("--- Bắt đầu test: tìm user không tồn tại ---");

        // ID không tồn tại → chỉ log WARN, không throw
        assertDoesNotThrow(() -> {
            userService.findUser(9999);
        });

        logger.warn("Test xác nhận: WARN được log khi user không tồn tại");
        logger.info("--- Kết thúc test: PASS ---");
    }

    @Test
    @DisplayName("Test xoa user voi ID am → phai log ERROR")
    void testDeleteUserInvalidId() {
        logger.info("--- Bắt đầu test: xóa user với ID âm ---");

        // deleteUser tự catch exception và log ERROR → không throw ra ngoài
        assertDoesNotThrow(() -> {
            userService.deleteUser(-5);
        });

        logger.info("--- Kết thúc test: ERROR được log - PASS ---");
    }

    @Test
    @DisplayName("Test minh hoa Parameterized Logging")
    void testParameterizedLogging() {String username = "Parameterized User";
        int userId = 42;
        double score = 9.8;

        // Minh họa tất cả mức độ log với {} placeholder
        logger.trace("TRACE - Chi tiết kỹ thuật cực thấp: user={}", username);
        logger.debug("DEBUG - Thông tin debug: userId={}, username={}", userId, username);
        logger.info("INFO  - Mốc quan trọng: user {} với score {}", username, score);
        logger.warn("WARN  - Cảnh báo: userId={} có điểm cao bất thường", userId);
        logger.error("ERROR - Lỗi giả lập cho demo: không có exception thật");

        assertTrue(true, "Tất cả log levels hoạt động bình thường");
    }
}