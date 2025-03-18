package com.thanh.domain;

public enum AccountStatus {
    PENDING_VERIFICATION,   // Tài khoản đã tạo nhưng chưa xác thực
    ACTIVE,                 // Tài khoản đã kích hoạt và hoạt động tốt
    SUSPENDED,              // Nếu tài khoản của bạn bị tạm ngưng do vi phạm
    DEACTIVATED,            // Tài khoản đã bị vô hiệu hóa, người dùng có thể chọn nó vô hiệu hóa
    BANNED,                 // Tài khoản bị cấm vĩnh viễn do vi phạm nghiêm trọng
    CLOSED,                 // Tài khoản đã bị đóng vĩnh viễn, có thể theo yêu cầu của người dùng
}
