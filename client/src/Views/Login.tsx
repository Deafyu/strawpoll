import React from "react";
import { Center, Flex, Heading } from "@chakra-ui/react";
import { ButtonLink, LoginForm } from "../Components";

const initialValues = {
  email: "",
  password: "",
};

interface Props {}

const Login: React.FC<Props> = (): JSX.Element => {
  return (
    <Flex h="100vh">
      <Center w="40%" bg="linkedin.500">
        <Heading as="h2" fontSize={34} color="white" textAlign="center">
          Login to strawpool
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
          buttonText="Login"
          onSubmit={async () => {}}
        />
        <ButtonLink
          to="/register"
          type="button"
          mt="50px"
          text="Dont have account? Register"
        />
      </Flex>
    </Flex>
  );
};

export default Login;
