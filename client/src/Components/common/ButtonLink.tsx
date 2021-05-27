import React from "react";
import { Button, ButtonProps } from "@chakra-ui/react";
import { Link, LinkProps } from "react-router-dom";

type ButtonLinkProps = ButtonProps & LinkProps & { text: string };

const ButtonLink: React.FC<ButtonLinkProps> = ({ text, to, ...rest }) => {
  return (
    <Button {...rest} as={Link} to={to}>
      {text}
    </Button>
  );
};

export default ButtonLink;
