import React from "react";
import { FormControl, FormLabel, Input, Button, Flex } from "@chakra-ui/react";
import { useFormik } from "formik";
import { capitalize } from "../../Utils";

interface Props {
  initialValues: Record<string, string>;
  buttonText: string;
  onSubmit: (arg: Record<string, string>) => Promise<void>;
}

const Form: React.FC<Props> = ({ initialValues, buttonText, onSubmit }) => {
  const { handleSubmit, values, handleChange, isSubmitting } = useFormik({
    initialValues,
    onSubmit: async (values, { setSubmitting }) => {
      try {
        await onSubmit(values);
      } catch (error) {}

      setSubmitting(false);
    },
  });

  return (
    <form onSubmit={handleSubmit}>
      <Flex w="50%" flexDir="column" maxW="500px" minWidth="450px">
        {Object.entries(initialValues).map(([key, value], i) => {
          return (
            <FormControl id={key} p="5px 0" key={key}>
              <FormLabel fontWeight={600}>{capitalize(key)}</FormLabel>
              <Input
                type={
                  ["email", "password"].filter((el) => el.includes(key))[0] ??
                  "text"
                }
                isDisabled={isSubmitting}
                name={key}
                placeholder={`Pass your ${key} here`}
                onChange={handleChange}
                value={values.key}
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
          isLoading={isSubmitting}
        >
          {buttonText}
        </Button>
      </Flex>
    </form>
  );
};

export default Form;
