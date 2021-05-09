import React from "react";
import { useFormik } from "formik";
import styled from "styled-components";

import FormInput from "../../../DesignSystem/Form/FormInput";
import SubmitButton from "../../../DesignSystem/Form/SubmitButton";
import Title from "../../../DesignSystem/Title";

const FormWrapper = styled.div`
  flex: 1 1 auto;
  width: 100%;
`;

interface FormValues {
  name: string;
  email: string;
  address: string;
}

const EligibilityApplication = () => {
  const { handleChange, handleSubmit, values } = useFormik<FormValues>({
    initialValues: {
      name: "",
      email: "",
      address: "",
    },
    onSubmit: (values) => {
      console.log(values)
      fetch('http://localhost:8080/eligibility/check', {
        method: 'post',
        headers: new Headers({
          'content-type': 'application/json',
          'Accept': 'application/json'
        }),
        mode: 'cors',
        body: JSON.stringify({
          name: values.name,
          email: values.email,
          address: values.address
        }),
      }).then(response => response.json())
          .then(data => {
            console.log('Success:', data);
            console.log('TODO: render data to EligibilityResults.tsx file');
          })
          .catch((error) => {
            console.error('Error:', error);
          });
    }
  });
  return (
    <FormWrapper>
      <Title>Cards</Title>
      <form onSubmit={handleSubmit}>
        <FormInput
          type="text"
          name="name"
          id="name"
          onChange={handleChange}
          value={values.name}
          placeholder="Name"
        />
        <FormInput
          type="email"
          name="email"
          id="email"
          onChange={handleChange}
          value={values.email}
          placeholder="Email"
        />
        <FormInput
          type="text"
          name="address"
          id="address"
          onChange={handleChange}
          value={values.address}
          placeholder="Address"
        />
        <SubmitButton text="Submit" />
      </form>
    </FormWrapper>
  );
};

export default EligibilityApplication;
