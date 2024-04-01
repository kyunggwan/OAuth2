import axios, { AxiosResponse } from "axios";
import {
  IdCheckRequestDto,
  emailCertificationRequestDto,
} from "./request/auth";
import {
  EmailCertificationResponseDto,
  IdCheckResponseDto,
} from "./response/auth";
import { ResponseDto } from "./response";
import { error } from "console";

/**
 * Response 템플릿 설정
 * @param response
 * @returns
 */
const responseHandler = <T>(response: AxiosResponse) => {
  const responseBody: T = response.data;
  return responseBody;
};

/**
 * Error 템플릿 설정
 * @param error
 * @returns
 */
const errorHandler = (error: any) => {
  if (!error.response || !error.response.data) return null;
  const responseBody: ResponseDto = error.response.data;
  return responseBody;
};

const DOMAIN = "http://localhost:4040";

const API_DOMAIN = `${DOMAIN}/api/v1`;

const ID_CHECK_URL = () => `${API_DOMAIN}/auth/id-check`;
const EMAIL_CERTIFICATION_URL = () => `${API_DOMAIN}/auth/email-certification`;

export const idCheckRequest = async (requestBody: IdCheckRequestDto) => {
  const result = await axios
    .post(ID_CHECK_URL(), requestBody)
    .then(responseHandler<IdCheckResponseDto>)
    .catch((error) => {
      errorHandler;
    });
  return result;
};

export const emailCertificationRequest = async (
  requestBody: emailCertificationRequestDto
) => {
  const result = await axios
    .post(EMAIL_CERTIFICATION_URL(), requestBody)
    .then(responseHandler<EmailCertificationResponseDto>)
    .catch((error) => {
      errorHandler;
    });
  return result;
};
