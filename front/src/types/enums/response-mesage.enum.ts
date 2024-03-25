enum ResponseMessage {
  // Http Status 200
  SUCCESS = "Success.",

  // Http Status 400
  VALIDATION_FAILED = "Validation failed.",
  DUPLICATE_EMAIL = "Duplicate email.",
  DUPLICATE_ID = "Duplicate ID.",
  DUPLICATE_NICKNAME = "Duplicate nickname.",
  DUPLICATE_TEL_NUM = "Duplicate tel number.",
  NOT_EXISTED_USER = "This user does not exist.",
  NOT_EXISTED_BOARD = "this board does not exist.",

  // Http Status 401
  SIGN_IN_FAIL = "Login information mismatch.",
  AUTHORIZATION_FAIL = "Authorization Failed.",

  // Http Stauts 403
  NO_PERMISSION = "Do not have permission.",

  // Http Status 500
  MAIL_FAIL = "Mail send failed.",
  DATABASE_ERROR = "Database error.",
}

export default ResponseMessage;
