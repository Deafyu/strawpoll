import React, { Suspense } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Flex } from "@chakra-ui/react";
import { FullScreenSpinner } from "./Components";

const Main = React.lazy(() => import("./Views/Main"));
const Login = React.lazy(() => import("./Views/Login"));
const Register = React.lazy(() => import("./Views/Register"));

function App() {
  return (
    <Flex flexDir="column" minH="100vh" w="100%" bg="white">
      <Suspense fallback={<FullScreenSpinner />}>
        <Router>
          <Switch>
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            <Route path="/" component={Main} />
          </Switch>
        </Router>
      </Suspense>
    </Flex>
  );
}

export default App;
