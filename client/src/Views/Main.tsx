import React from "react";
import { Box, Center, Heading } from "@chakra-ui/react";
import { ButtonLink } from "../Components";
interface Props {}

const H1Text = "Strawpool app";
const H2Text = "Create strawpools in seconds";
const Main: React.FC<Props> = (): JSX.Element => {
  return (
    <Box w="100%" h="100vh">
      <Center h="50%" bg="linkedin.600" display="flex" flexDir="column">
        <Heading as="h1" color="white" fontSize={45} textAlign="center">
          {H1Text}
        </Heading>
        <Heading
          as="h2"
          color="white"
          fontSize={30}
          textAlign="center"
          marginTop="30px"
        >
          {H2Text}
        </Heading>
      </Center>
      <Center
        h="50%"
        justifyContent="space-between"
        maxW="1200px"
        margin="auto"
        alignItems="center"
      >
        <ButtonLink to="/register" type="button" text="Sign up" />
        <Heading as="h3" fontSize={24} color="linkedin.900">
          Start now!
        </Heading>
        <ButtonLink to="/login" type="button" text="Sign in" />
      </Center>
    </Box>
  );
};

export default Main;
