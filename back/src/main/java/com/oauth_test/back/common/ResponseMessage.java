package com.oauth_test.back.common;

public interface ResponseMessage {
    // Http Status 200
    String SUCCESS = "Success.";

    // Http Status 400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_ID = "Duplicate ID.";
    String DUPLICATE_NICKNAME = "Duplicate nickname.";
    String DUPLICATE_TEL_NUM = "Duplicate tel number.";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "this board does not exist.";

    // Http Status 401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization Failed.";

    // Http Stauts 403
    String NO_PERMISSION = "Do not have permission.";

    // Http Status 500
    String MAIL_FAIL = "Mail send failed.";
    String DATABASE_ERROR = "Database error.";

}