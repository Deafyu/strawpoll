function capitalize([s, ...rest]: string) {
  return s.toUpperCase() + rest.join("");
}

export default capitalize;
