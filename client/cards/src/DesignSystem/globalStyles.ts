import { createGlobalStyle, css } from "styled-components";

export default createGlobalStyle`
  ${css`
    @font-face {
      font-family: sans-serif;
      font-weight: 100 500;
      font-style: normal;
      font-display: fallback;
    }

    html,
    body {
      margin: 0;
      padding: 0;
      width: 100%;
      height: 100%;
    }
  `}
`;
