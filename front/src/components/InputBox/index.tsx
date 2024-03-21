import { ChangeEvent, forwardRef } from "react";
import "./style.css";

interface Props {
  title: string;
  placeholder: string;
  type: "text" | "password";
  value: string;
  message?: string;
  isErrorMessage: boolean;
  buttonTitle?: string;
  onChange: (Event: ChangeEvent<HTMLInputElement>) => void;
  onKeydown?: (Event: React.KeyboardEvent<HTMLInputElement>) => void;
  onButtonClick?: () => void;
}

const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {
  const {
    title,
    placeholder,
    type,
    value,
    message,
    isErrorMessage,
    buttonTitle,
    onChange,
    onKeydown,
    onButtonClick,
  } = props;

  const buttonClass =
    value === "" ? "input-box-button-disable" : "input-box-button";
  const messageclass = isErrorMessage
    ? "input-box-message-error"
    : "input-box-message";

  return (
    <div className="input-box">
      <div className="input-box-title">{title}</div>
      <div className="input-box-content">
        <div className="input-box-body">
          <input
            ref={ref}
            className="input-box-input"
            placeholder={placeholder}
            type={type}
            value={value}
            onChange={onChange}
            onKeyDown={onKeydown}
          />
          {buttonTitle !== undefined && onButtonClick !== undefined && (
            <div className={buttonClass} onClick={onButtonClick}>
              {buttonTitle}
            </div>
          )}
        </div>
        {message !== undefined && <div className={messageclass}>{message}</div>}
      </div>
    </div>
  );
});

export default InputBox;
