import React from "react";
import styled from "styled-components";

const ButtonWrapper = styled.div`
  width: 100px;
  height: 40px;
  background-color: rgb(255, 255, 255);
  margin-bottom: 16px;
`;

const Button = styled.button`
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  background-color: #2a62e7;
  font-family: Manrope, -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 0.875rem;
  border: 1px solid rgba(0, 0, 0, 0.16);
  border-radius: 1px;
  position: relative;
  padding: 4px 8px;
  color: #fff;

  :active {
    background-color: #0a2b7b;
  }
  :hover {
    background-color: #1e4ebe;
  }
  :focus {
    outline: #0a2b7b auto 2px;
  }
`;

interface SubmitButtonProps {
  text: string;
}

const SubmitButton = ({ text }: SubmitButtonProps) => {
  return (
    <ButtonWrapper>
      <Button type="submit">{text}</Button>
    </ButtonWrapper>
  );
};

export default SubmitButton;
