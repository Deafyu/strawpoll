import React from "react";
import { Spinner, Center } from "@chakra-ui/react";

const FullScreenSpinner = () => {
  return (
    <Center minH="100vh" w="100%">
      <Spinner size="xl" />
    </Center>
  );
};

export default FullScreenSpinner;
