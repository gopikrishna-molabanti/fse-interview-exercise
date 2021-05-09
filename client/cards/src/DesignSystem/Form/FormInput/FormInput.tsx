import React from "react";
import styled from "styled-components";

const InputWrapper = styled.div`
  width: 100%;
  height: 40px;
  background-color: rgb(255, 255, 255);
  margin-bottom: 16px;
`;

const Input = styled.input`
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  background-color: transparent;
  font-family: Manrope, -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 0.875rem;
  border: 1px solid rgba(0, 0, 0, 0.16);
  border-radius: 1px;
  position: relative;
  padding: 4px 8px;
`;

interface InputProps {
  type: string;
  name: string;
  id: string;
  onChange: (e: React.ChangeEvent<any>) => void;
  value: string;
  placeholder?: string;
}

const FormInput = (props: InputProps) => {
  return (
    <InputWrapper>
      <Input {...props} />
    </InputWrapper>
  );
};

export default FormInput;
