import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
  useLocation,
} from "react-router-dom";
import { Provider } from "react-redux";
import LoginPage from "./components/LoginPage";
import store from "./store";
import SharedStore from "./components/SharedStore";
import { NavbarScroller } from "@iyadmosa/react-library";
import MyStore from "./components/MyStore";
import { getStoreItemsAll } from "./actions/itemAction";

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
  ];

  // Check if the current location is the login page
  let showNavbar = window.location.pathname !== "/";

  useEffect(() => {
    showNavbar = window.location.pathname !== "/";
  }, [window.location.pathname]);

  return (
    <Provider store={store}>
      <Router>
        {showNavbar && <NavbarScroller brand={brand} links={links} />}
        <Switch>
          <Route exact path="/" component={LoginPage} />
          <PrivateRoute path="/my-store" component={MyStore} />
          <PrivateRoute path="/shared-store" component={SharedStore} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
