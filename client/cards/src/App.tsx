import React from "react";
import styled from "styled-components";
import Eligibility from "./Views/Eligibility";

const AppWrapper = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  padding: 40px;
`;

const App = () => {
  return (
    <AppWrapper>
      <Eligibility />
    </AppWrapper>
  );
};

export default App;
