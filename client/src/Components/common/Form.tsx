import React from "react";
import { FormControl, FormLabel, Input, Button, Flex } from "@chakra-ui/react";
import { useFormik } from "formik";
import { capitalize } from "../../Utils";

interface Props {
  initialValues: Record<string, string>;
  buttonText: string;
}

const Form: React.FC<Props> = ({ initialValues, buttonText }) => {
  const { handleSubmit, values, handleChange } = useFormik({
    initialValues,
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return (
    <form onSubmit={handleSubmit}>
      <Flex w="50%" flexDir="column" maxW="500px" minWidth="450px">
        {Object.entries(initialValues).map(([key, value]) => {
          return (
            <FormControl id={key} p="5px 0">
              <FormLabel fontWeight={600}>{capitalize(key)}</FormLabel>
              <Input
                type="email"
                name="email"
                placeholder={`Pass your ${key} here`}
                onChange={handleChange}
                value={values[key]}
                required
              />
            </FormControl>
          );
        })}
        <Button
          type="submit"
          alignSelf="center"
          mt="30px"
          colorScheme="linkedin"
        >
          {buttonText}
        </Button>
      </Flex>
    </form>
  );
};

export default Form;
