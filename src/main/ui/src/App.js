import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";
import { Provider } from "react-redux";
import LoginPage from "./components/LoginPage";
import store from "./store";
import SharedStore from "./components/SharedStore";
import { NavbarScroller } from "@iyadmosa/react-library";
import MyStore from "./components/MyStore";
import RequestPage from "./components/RequistPage";
import ToConfirmPage from "./components/ToConfirmPage";
import UserPage from "./components/UserPage";

const PrivateRoute = ({ component: Component, ...rest }) => {
  const isAuthenticated = !!localStorage.getItem("token");
  return (
    <Route
      {...rest}
      render={(props) =>
        isAuthenticated ? <Component {...props} /> : <Redirect to="/" />
      }
    />
  );
};

const App = () => {
  const brand = { name: "My Store", to: "/my-store" };
  const links = [
    {
      name: "My Store",
      to: "/my-store",
      component: <MyStore />,
    },
    {
      name: "Shared store",
      to: "/shared-store",
      component: <SharedStore />,
    },
    {
      name: "Shared requested",
      to: "/shared-requested",
      component: <RequestPage />,
    },
    {
      name: "Shared to confirm",
      to: "/shared-confirm",
      component: <ToConfirmPage />,
    },
  ];

  // Check if the current location is the login page
  let showNavbar = window.location.pathname !== "/";

  useEffect(() => {
    showNavbar = window.location.pathname !== "/";
  }, [window.location.pathname]);

  return (
    <Provider store={store}>
      <Router>
        {showNavbar && (
          <NavbarScroller user={<UserPage />} brand={brand} links={links} />
        )}
        <Switch>
          <Route exact path="/" component={LoginPage} />
          <PrivateRoute path="/my-store" component={MyStore} />
          <PrivateRoute path="/shared-store" component={SharedStore} />
          <PrivateRoute path="/shared-requested" component={RequestPage} />
          <PrivateRoute path="/shared-confirm" component={ToConfirmPage} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
