import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import { Provider } from "react-redux";
import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import authReducer from "./reducers/authReducer";
import LoginPage from "./components/LoginPage";
import DashboardPage from "./components/DashboardPage";
import store from "./store";

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
  return (
    <Provider store={store}>
      <Router>
        <Switch>
          <Route exact path="/" component={LoginPage} />
          <PrivateRoute path="/dashboard" component={DashboardPage} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
