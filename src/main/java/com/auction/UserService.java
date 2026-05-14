package com.auction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service xử lý logic người dùng.
 * Minh họa đầy đủ các mức độ log và Parameterized Logging.
 */
public class UserService {

    // Mỗi class có Logger riêng → dễ lọc log theo class
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Tạo người dùng mới.
     * Minh họa: INFO log + Parameterized Logging với nhiều tham số
     */
    public void createUser(String name, String email) {
        logger.debug("Bắt đầu tạo user: name={}, email={}", name, email);

        // Giả lập validate
        if (name == null || name.isEmpty()) {
            // WARN: Tình huống bất thường nhưng chưa phải lỗi nghiêm trọng
            logger.warn("Tên người dùng rỗng, dùng tên mặc định");
            name = "Unknown";
        }

        // Giả lập lưu vào DB
        int newUserId = (int) (Math.random() * 1000);

        logger.info("Tạo user thành công: id={}, name={}, email={}", newUserId, name, email);
    }

    /**
     * Tìm người dùng theo ID.
     * Minh họa: WARN khi không tìm thấy
     */
    public void findUser(int userId) {
        logger.debug("Đang tìm user với id={}", userId);

        // Giả lập: chỉ có user id=1
        if (userId != 1) {
            // WARN: Không tìm thấy → không phải lỗi hệ thống, nhưng đáng chú ý
            logger.warn("Không tìm thấy user với id={}, trả về null", userId);
            return;
        }

        logger.info("Tìm thấy user: id={}, name=Nguyen Van A", userId);
    }

    /**
     * Xóa người dùng.
     * Minh họa: ERROR với exception đầy đủ stack trace
     */
    public void deleteUser(int userId) {
        logger.info("Yêu cầu xóa user với id={}", userId);

        try {
            if (userId <= 0) {
                // Ném exception để minh họa error logging
                throw new IllegalArgumentException("User ID phải là số dương, nhận được: " + userId);
            }

            logger.info("Xóa user id={} thành công", userId);

        } catch (IllegalArgumentException e) {

            logger.error("Lỗi khi xóa user id={}: {}", userId, e.getMessage(), e);
        }
    }
}