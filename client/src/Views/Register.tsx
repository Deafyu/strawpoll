import React from "react";
import { Flex, Center, Heading } from "@chakra-ui/react";
import { LoginForm, ButtonLink } from "../Components";
import axios from "axios";
import { REGISTER_URL } from "../Utils";

interface Props {}

const initialValues = {
  email: "",
  name: "",
  password: "",
};

const Register: React.FC<Props> = (): JSX.Element => {
  const handleSubmit = async (values: Record<string, string>) => {
    try {
      const data = await axios.post(REGISTER_URL, values);
      console.log(data);
    } catch (error) {
      throw error;
    }
  };

  return (
    <Flex h="100vh">
      <Center w="40%" bg="linkedin.500">
        <Heading as="h2" fontSize={34} color="white" textAlign="center">
          Sign up to strawpool
        </Heading>
      </Center>
      <Flex
        h="100%"
        w="60%"
        flexDir="column"
        justifyContent="center"
        alignItems="center"
      >
        <LoginForm
          initialValues={initialValues}
          buttonText="Register"
          onSubmit={handleSubmit}
        />
        <ButtonLink
          to="/login"
          type="button"
          mt="50px"
          text="Already have account? Login"
        />
      </Flex>
    </Flex>
  );
};

export default Register;
